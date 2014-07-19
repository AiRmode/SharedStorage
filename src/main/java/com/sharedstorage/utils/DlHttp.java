package com.sharedstorage.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class DlHttp implements Download{
	private static final Logger logger = LoggerFactory.getLogger(DlHttp.class);
	
	@Override
	public HttpServletResponse dlHttp(String fileName, HttpServletResponse response, HttpServletRequest request) {
		
		File f = new File(fileName);
		if(!f.exists()){
			logger.error("target object: "+fileName+" not exist");
			return null;
		}
		
		if(f.isDirectory()){
			logger.error("target object: "+fileName+" is directory");
			return null;
		}
		
		// get MIME type of the file
		//1.
		ServletContext context = request.getSession().getServletContext();
		String mimeType = context.getMimeType(fileName);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
	    
		response.setContentLength(new Long(f.length()).intValue());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + f.getName() + "\";");
		response.setContentType(mimeType);

		response.setCharacterEncoding(request.getCharacterEncoding());//Cp1251 ISO-8859-1 UTF-8
		
		ServletOutputStream out;
		try {
			out = response.getOutputStream();
			InputStream in = new FileInputStream(f);
			byte[] buffer = new byte[4 * 1024];
			int len;
			while ((len = in.read(buffer)) != -1) {
			    out.write(buffer, 0, len);
			}
			in.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return response;
	}
}
