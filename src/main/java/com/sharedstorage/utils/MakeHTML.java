package com.sharedstorage.utils;

import com.sharedstorage.HomeController;

/**
 *Here we will make HTML lines from simple strings 
 * @author 
 *
 */
public class MakeHTML {

	public MakeHTML(){
		
	}
	
	public String makeHrefDir(String[] items){
		if (items==null){
			return "no data";
		}
		//new*
		String[] fArName;
		// /new*
		StringBuilder sb = new StringBuilder();

        for (String item : items) {
            fArName = item.split("/");
//            if (fArName != null) {
                if (fArName.length == 0)
                    sb.append("<a href=\"").append(HomeController.adress).append("/dir?dirList=").append(item).append("\"").append(item).append(">").append(item).append("</a>").append("<br/>");
//                    continue;
                else if (fArName.length == 1)
                    sb.append("<a href=\"").append(HomeController.adress).append("/dir?dirList=").append(item).append("\"").append(item).append(">").append(fArName[0]).append("</a>").append("<br/>");
//                    continue;
                else
                    sb.append("<a href=\"").append(HomeController.adress).append("/dir?dirList=").append(item).append("\"").append(item).append(">").append(fArName[fArName.length - 1]).append("</a>").append("<br/>");
//                    continue;
//            }
//            sb.append("<a href=\"" + HomeController.adress + "/dir?dirList=" + item + "\"" + item + ">" + item + "</a>" + "<br/>");
        }
		
		return sb.toString(); 
	}
	
	public String makeHrefFile(String[] items){
		if (items==null) return "no data";
		//new*
		String[] fArName;
		// /new*		
		StringBuilder sb = new StringBuilder();
        for (String item : items) {
            fArName = item.split("/");
            sb.append("<a href=\"").append(HomeController.adress).append("/dlHttp?fileName=").append(item).append("\"").append(item).append(">").append(fArName[fArName.length - 1]).append("</a>").append("<br/>");
//            continue;
//            sb.append("<a href=\"" + HomeController.adress + "/dlHttp?fileName=" + item + "\"" + item + ">" + item + "</a>" + "<br/>");
        }
		
//		rootItems += "<a href=Serv1?action=getList&itemName="+str+">"+ str + "</a>" + "<br/>";// style='font-size:25'
		return sb.toString(); 
	}
	
	public String makeHrefHome(String path){
		return "<a href=\""+path+"/"+"\""+"HOME"+">"+ "HOME" + "</a>" + "<br/>";
	}
}
