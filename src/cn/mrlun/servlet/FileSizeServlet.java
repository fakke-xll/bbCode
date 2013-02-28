package cn.mrlun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileSizeServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		long fileSize=CurrentFileSize.getFileSize(request, "");
		BigDecimal bd=new BigDecimal(fileSize/1024/1024);
		bd.setScale(2,BigDecimal.ROUND_HALF_UP);
		float fileSizes=bd.floatValue();
		PrintWriter out=response.getWriter();
		//out.write("已上传文件大小:"+String.valueOf(fileSizes)+"MB");
		//out.write("已上传文件大小:"+String.valueOf(fileSize/1024)+"KB");
		out.write(String.valueOf(fileSize));
	}

}
