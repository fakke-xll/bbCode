package cn.mrlun.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.mrlun.exception.ExtensionException;
import cn.mrlun.exception.SizeLimitException;

public class UpServlet extends HttpServlet {

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		int FILE_MAX_SIZE=6*1024;
		String[] FILE_ALL_EXTENSION={"doc","docx","rar","zip"};
		List<String> extensionList=new ArrayList();
		for(String str:FILE_ALL_EXTENSION){
			extensionList.add(str);
		}
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setSizeThreshold(4096);		//最大内存大小
		String tempPath=request.getSession().getServletContext().getRealPath("/")+"UploadTemp";
		String realPath=request.getSession().getServletContext().getRealPath("/")+"UploadFile";
		File f=new File(tempPath);
		if(!f.exists()){
			f.mkdirs();
		}
		File f1=new File(realPath);
		if(!f1.exists()){
			f1.mkdirs();
		}
		factory.setRepository(new File(tempPath));//当内存不够时，存放在硬盘的临时目录
		File fileTemp=factory.getRepository();
		//System.out.println("fileTemp.getName() "+fileTemp.getName());
		//System.out.println("fileTemp.length()"+fileTemp.length());
		
		/**
		   * 用以上工厂实例化长传组件
		   */
		ServletFileUpload sfu=new ServletFileUpload(factory);
		//sfu.setFileSizeMax(50 * 1024 * 1024);
		//sfu.setSizeMax(sizeMax)
		List<FileItem> fileList=null;
		try {
			fileList=sfu.parseRequest(request);
			for(FileItem fi:fileList){
				if(fi.isFormField()){
					System.out.println(fi.getString());//获取表单值
					System.out.println(fi.getFieldName());//获取域名
				}else{
					String fileName=fi.getName();//获取文件名
					System.out.println(fileName);
					System.out.println(fi.getFieldName());//获取域名
					
					//获取文件标准名
					//fileName=fileName.substring(fileName.lastIndexOf("\\"));
					System.out.println("文件标准名   :"+fileName);
					//获取文件后缀名
					String flieExtension =fileName.substring(fileName.lastIndexOf(".")+1);
					System.out.println("文件后缀名   :"+flieExtension);
					if(!extensionList.contains(flieExtension)){
						throw new ExtensionException();
					}
					String finalName=realPath+"\\"+fileName;
					//String extension=
					if(fi.getSize()/1024/1024>FILE_MAX_SIZE){
						throw new SizeLimitException();
					}
					fi.write(new File(finalName));
					out.print("文件上传成功！ 文件大小"+fi.getSize()/1024/1024+"MB");
					out.print("<script>parent.callback('文件上传成功！')</script>");
				}
			}
			
		}catch(ExtensionException e){
			out.print("<script>parent.callback('文件上传失败！ 文件后缀名必须为其中之一"+extensionList+"')</script>");
		}catch(SizeLimitException e){
			out.print("<script>parent.callback('文件上传失败！ 文件大小超过限制"+FILE_MAX_SIZE+"')</script>");
			System.out.println("文件上传失败！ 文件大小超过限制");
		}catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("<script>parent.callback('文件上传失败！')</script>");
		}
	}

}
