package cn.mrlun.servlet;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;

public class CurrentFileSize {
	
	
	public static long getFileSize(HttpServletRequest request,String directoryName){
		String tempPath=request.getSession().getServletContext().getRealPath("/")+"UploadTemp";
		long fileSize=FileUtils.sizeOfDirectory(new File(tempPath));
		
		return fileSize;

	}
	

}
