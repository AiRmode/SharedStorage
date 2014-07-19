package ua.com.bp.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Upload {

	public void ulHttp(HttpServletRequest request, HttpServletResponse response);
    public String getUploadPath();
}
