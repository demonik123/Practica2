/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Boris Leonel
 */
@WebServlet(name = "ContadorServlet", urlPatterns = {"/ContadorServlet"})
public class ContadorServlet extends HttpServlet {

   
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int contador = 0;
        PrintWriter out = response.getWriter();
        Cookie[] cukis = request.getCookies();
        
        
        if (cukis != null) {
            for (Cookie cu : cukis) {
                if (cu.getName().equals("visitas")) {
                    //antes de la asignación se convierte un valor en cadena
                    contador = Integer.parseInt(cu.getValue());
                }
            }
        }
        if (contador <= 0) {
            out.println("Bienvenido a nuestro sitio WEB");
        }else{
            out.println("Gracias por visitarnos nuevamente");
        }
        contador ++;
        Cookie ca = new Cookie("visitas",Integer.toString(contador));
        //establece el tiempo de la cookie en segundos
        ca.setMaxAge(20);
        response.addCookie(ca);   
        response.setContentType("text/html");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
