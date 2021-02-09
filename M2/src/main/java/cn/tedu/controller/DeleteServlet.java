package cn.tedu.controller;

import cn.tedu.dao.ImageDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ImageDao dao = new ImageDao();

        String url=dao.findUrl(id);
        String path = request.getServletContext().getRealPath(url);
        new File(path).delete();


        dao.deleteById(id);
        response.sendRedirect("/findall");

    }
}
