package com.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class ActionServlet extends HttpServlet {

    public ActionServlet() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String action = request.getParameter("action");
        String conditionname = request.getParameter("conditionname");
        String parametersname = request.getParameter("parametersname");//类名

            try{
                Object obj = Class.forName(parametersname).newInstance();
                Class clazz = obj.getClass();
                Method mothod = clazz.getDeclaredMethod(conditionname, HttpServletRequest.class,HttpServletResponse.class);
                String result = (String) mothod.invoke(obj, request,response);
            }catch(Exception e){}

    }

}
