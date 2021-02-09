package cn.tedu.controller;

import cn.tedu.dao.ImageDao;
import cn.tedu.entity.Image;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "UploadServlet", urlPatterns = "/upload")
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String title = request.getParameter("title");
        Part part = request.getPart("pic");
        String info = part.getHeader("content-disposition");
        String suffix = info.substring(info.lastIndexOf("."), info.length() - 1);
        System.out.println("info:"+info);
        System.out.println("title：" + title);
        System.out.println("suffix：" + suffix);
        String filename= UUID.randomUUID()+suffix;
        System.out.println("文件名："+filename);
        String path = request.getServletContext().getRealPath("images/");
        System.out.println("path:"+path);
        new File(path).mkdirs();
        part.write(path+filename);

        Image image= new Image(0,title,"images/"+filename);
        System.out.println(image.toString());
        ImageDao dao=new ImageDao();
        dao.add(image);
        response.setContentType("text/html;charset=uft-8");
        PrintWriter pw=response.getWriter();
        pw.println("发布完成");
        pw.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
