package cn.mrlun.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

public class SaveServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String dirnum=request.getParameter("val");
		String path=request.getSession().getServletContext().getRealPath("/")+"UploadFile";
		String realPath=request.getSession().getServletContext().getRealPath("/")+dirnum;
		File temp=new File(realPath);
		File dirs=new File(path);
		if(!dirs.exists()){
			dirs.mkdirs();
		}
		
			try{
				FileUtils.copyDirectoryToDirectory(temp, dirs);
				FileUtils.deleteDirectory(temp);
				out.print("保存成功！");
				}catch(Exception e){
					out.print("系统错误！");
				}
			
	}

}
