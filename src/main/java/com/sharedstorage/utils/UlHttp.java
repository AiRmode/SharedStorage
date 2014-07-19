package com.sharedstorage.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sharedstorage.HomeController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

public class UlHttp implements Upload {

    private static final Logger logger = LoggerFactory.getLogger(UlHttp.class);
    private HttpServletRequest request1;

    private String uploadPath="";

    public void ulHttp(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCharacterEncoding() == null) {
            try {
                request.setCharacterEncoding("ISO-8859-1");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }

        request1 = request;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            logger.error("is NOT multipart!>>>>>!!!!!!!!!");
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024);

        File tempDir = new File(HomeController.pathToTempDir);
        if (!tempDir.exists()) {
            if (tempDir.mkdirs()) {

            }
        }
        factory.setRepository(tempDir);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(-1);//1024 * 1024 * 10

        try {
            List<? extends FileItem> items = upload.parseRequest(request);
            Iterator<? extends FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();

                if (item.isFormField()) {
                    processFormField(item);
                } else {
                    //?
                }
            }

            iter = items.iterator();

            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (item.isFormField()) {
                } else {
                    processUploadedFile(item);
                }
            }
        } catch (Exception e) {
            logger.error("ERROR!! ulHttp:\n");
            e.printStackTrace();
        }

    }

    /**
     * @param item file item....
     * @throws Exception
     */
    private void processUploadedFile(FileItem item) throws Exception {
        File uploadetFile;
        String fileName = makeUTF8(item.getName());
        fileName = fileName.replace('\\', '/');
        String[] nameParts;
        if ((nameParts = fileName.split("/")) != null && nameParts.length > 1) {
            fileName = nameParts[nameParts.length - 1];
        }
        logger.info("fileName1:" + fileName);

//		String[] nameArray = fileName.split(".");
//		if(nameArray!=null && nameArray.length>=1) {
//			fileName=nameArray[nameArray.length-1];
//		}		
//		ServletContext servletContext = request1.getSession().getServletContext();

        uploadetFile = new File(uploadPath + "/" + fileName);
        if (uploadetFile.isDirectory()) {
            return;
        }

        String tempFileName = fileName;
        int i = 1;
        while (uploadetFile.exists()) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(fileName);
            tempFileName = sb.toString();
            uploadetFile = new File(uploadPath + "/" + tempFileName);
            i++;
        }

        logger.info("File_name>>>" + tempFileName + "<<<");

        uploadetFile.createNewFile();
        item.write(uploadetFile);
    }

    /**
     * @param item
     */
    private void processFormField(FileItem item) {
        //
        //UTF-8
        if (item.getFieldName().equals("description")) {
            uploadPath = makeUTF8(item.getString());
			logger.info("processFormField:> "+uploadPath);
        }

//		logger.info(item.getFieldName()+"->field name, + "+item.getString()+"->item get String");		
    }

    private String makeUTF8(String param) {
        byte[] b = null;
        if (request1 != null) {
//			logger.info("2.req encoding="+request1.getCharacterEncoding());
            try {
//				b = param.getBytes(request1.getCharacterEncoding());
//				if(b==null){
                b = param.getBytes("ISO-8859-1");
//				}
                param = new String(b, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return param;
    }

    public String getUploadPath() {
        return uploadPath;
    }
}
