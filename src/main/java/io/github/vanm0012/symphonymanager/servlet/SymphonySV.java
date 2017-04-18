package io.github.vanm0012.symphonymanager.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SymphonySV extends HttpServlet
{
    public void init(ServletConfig cfg) throws ServletException
    {
        super.init(cfg);
    }

    public void destroy()
    {
        super.destroy();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doGet(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

    }
}
