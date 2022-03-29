/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Boris Leonel
 */
@WebServlet(name = "ProcesaServlet", urlPatterns = {"/ProcesaServlet"})
public class ProcesaServlet extends HttpServlet {

    
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
        //obtener acceso al objeto session
        HttpSession ses = request.getSession();
        ArrayList<Tareas> lista = (ArrayList<Tareas>) ses.getAttribute("lista");
        String id = request.getParameter("id");
        String op = request.getParameter("op");
        //PrintWriter out = response.getWriter();
        //out.print("id:"+id + "   operacion:"+op);
        if (op.equals("elim")) {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getId().equals(id)) {
                    lista.remove(i);
                    response.sendRedirect("tarea.jsp");
                }
            }  
        }else{
            //se elimina la sesión
            ses.invalidate();
            //transfiere el control a index.jsp
            response.sendRedirect("tarea.jsp");
        }  
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //Recupera el producto enviado desde el formulario
        String marcado="1";
        String id = request.getParameter("id");
        String tar = request.getParameter("tarea");
        String com = request.getParameter("completado");
        if (com == null) {
            marcado = "0";
        }
        Tareas tr = new Tareas();
        tr.setId(id);
        tr.setTarea(tar);
        tr.setCompletado(marcado);

        //Se otiene el acceso a la session
        HttpSession ses = request.getSession();
        //Se obtiene la lista que esta como atributo de session
        ArrayList<Tareas> lista = (ArrayList<Tareas>) ses.getAttribute("lista");
        //Agremos el producto a la lista
        lista.add(tr);
        
        response.sendRedirect("tarea.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
