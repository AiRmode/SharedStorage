package com.sharedstorage.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Download {
	public HttpServletResponse dlHttp(String fileName, HttpServletResponse response, HttpServletRequest request);
}
