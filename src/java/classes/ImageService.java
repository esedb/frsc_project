/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ese
 */
@WebServlet(name = "ImageService", urlPatterns = {"/imageService/*"})
public class ImageService extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        try{
            
            String req=request.getPathInfo();
            System.out.println("req uri is " + req);
            
           
            String filereq=request.getParameter("file");
            if(filereq==null)
            {
                response.sendRedirect("/henry_project/");
            }
            System.out.println("file req " + filereq);
            File fl=new File("C:" + File.separator + "images_im" + File.separator + filereq);
            
            
            BufferedInputStream bin=new BufferedInputStream(new FileInputStream(fl));
            byte[] bytes=new  byte[bin.available()];
            
            bin.read(bytes);
            bin.close();
            response.getOutputStream().write(bytes);
            System.out.println("response run");
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
