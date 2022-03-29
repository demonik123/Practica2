<%-- 
    Document   : index
    Created on : 22 mar. 2022, 20:15:49
    Author     : Boris Leonel
--%>
<%@page import="com.emergentes.Tareas"%>
<%@page import="java.util.ArrayList"%>
<%
    //verifica si existe la colección en el objeto session
    if (session.getAttribute("lista") == null) {
        ArrayList<Tareas> lis  = new ArrayList<Tareas>();
        session.setAttribute("lista", lis);
    }
    //se obtiene la colección ñista que esta como atributo en session
    ArrayList<Tareas> lista = (ArrayList<Tareas>) session.getAttribute("lista");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/estilos.css"/>
    </head>
    <body>
        <h1>Tareas Pendientes</h1>
        <p>Inserte las tareas que desea</p>
        <form action="ProcesaServlet" method="POST">
            Id: <input type="text" name="id">
            Tarea: <input type="text" name="tarea">
            Completado: <input type="checkbox" name="completado"><br>
            <input type="submit" value="Enviar">
        </form>
        <br>
        <a href="ProcesaServlet">Vaciar Tareas</a>
        
        <table border="1">
            <th>ID</th>
            <th>Tarea</th>
            <th>Compleado</th>
            <th>Opción</th>
            <%
            if(lista!=null){
                for(int i=0;i<lista.size();i++){
            %>
                    <tr>
                        <td><%=lista.get(i).getId()%></td>
                        <td><%=lista.get(i).getTarea()%></td>
                        <%
                            if(Integer.parseInt(lista.get(i).getCompletado())==0){
                        %>
                            <td><input type="checkbox" name=""/> </td>   
                        <%
                            }else{
                        %>
                             <td><input type="checkbox" name="" checked="<%=lista.get(i).getCompletado()%>" /></td>   
                        <%
                            }
                        %>           
                        <td><a href="">Eliminar</a></td>
                    </tr>
            <%
                }
            }
            %>
        </table>

    </body>
</html>
