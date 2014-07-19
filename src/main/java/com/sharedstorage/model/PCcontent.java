package com.sharedstorage.model;

import com.sharedstorage.utils.ChangePath;
import com.sharedstorage.utils.MakeHTML;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class PCcontent {
	
	private ChangePath changePath = new ChangePath();
	private MakeHTML makeHTML = new MakeHTML();
	
	public PCcontent(){
		
	}

	public String getRootList(){
		String rootItems="no items in root directory found";
		File[] roots = File.listRoots();
		if (roots != null) {
			int i=0;
			String[] rootString = new String[roots.length];
			for(File file:roots){
				rootString[i] = file.getAbsolutePath();
				i++;
			}
			
			return makeHTML.makeHrefDir(changePath.change(rootString));
		}
		return rootItems;
	}
	
	/**
	 * 
	 * @param path must be in format C:/, D:/new/test/
	 * @return string:)
	 */
	public String getDirList(String path){
		if(path==null){
			return "Path = null!";
		}
		
		//create object with path
		File item = new File(path);
		if(!item.exists()){
			return "Directory <"+path+"> is not exist";
		}
		
		if(!item.isDirectory()){
			return "It's NOT directory (or no such directory) <"+path+">";
		}
		
		//get item list
		String[] items = item.list();
		if(items==null){
			return "Can not acces to directory <"+path+">";
		}
		

		List<String> folders = new LinkedList<>();
		List<String> files = new LinkedList<>();
//		int itemsLength = items.length;
        for (String item1 : items) {
            item = new File(path + "/" + item1);
            if (item.isFile()) {
                //actions for item==file
                files.add(changePath.change(item.getAbsolutePath()));
            } else {
                //actions for item==directory
                folders.add(changePath.change(item.getAbsolutePath()));
            }
        }
		
		//add comment <Folders>
		//get String from folders<String>
		//add some new lines
		//add comment <Files>
		//get String from files<String>
		String[] arFolders;
		String[] arFiles;
		
//		if(folders!=null){
        arFolders = new String[folders.size()];
        for (int i = 0; i < folders.size(); i++) {
            arFolders[i] = folders.get(i);
        }
//		}

//		if (files != null) {
        arFiles = new String[files.size()];
        for (int i = 0; i < files.size(); i++) {
            arFiles[i] = files.get(i);
        }
//		}

        StringBuilder sb = new StringBuilder();
        sb.append("Folders: <br/>");
//		if(arFolders!=null){
        sb.append(makeHTML.makeHrefDir(arFolders));
//		}
        sb.append("<br/> <br/>");
        sb.append("Files: <br/>");
//		if(arFiles!=null){
        sb.append(makeHTML.makeHrefFile(arFiles));
//		}

        return sb.toString();
	}
}
