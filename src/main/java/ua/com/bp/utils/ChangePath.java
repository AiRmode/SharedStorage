package ua.com.bp.utils;

public class ChangePath {
	
	public ChangePath() {

	}
	
	public String change(String items) {
		if (items == null) {
			return "no data";
		}

		char[] c;
		c = items.toCharArray();
//		if (c != null) {
			for (int ii = 0; ii < c.length; ii++) {
				if (c[ii] == '\\') {
					c[ii] = '/';
				}
			}
//		}

		return new String(c);
	}
	
	public String[] change(String[] items) {

		if(items==null){
			return new String[]{"no data"};
		}
		
		char[] c;
		for(int i=0;i<items.length;i++){
			c=items[i].toCharArray();
//			if(c!=null){
				for(int ii=0;ii<c.length;ii++){
					if(c[ii]=='\\'){
						c[ii]='/';
					}
				}
//			}
			items[i]=new String(c);
		}
		
		return items;
	}

}
