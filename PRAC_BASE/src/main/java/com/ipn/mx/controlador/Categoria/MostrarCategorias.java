/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.Categoria;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.entidades.modelo.dto.CategoriaDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author akbal
 */
@WebServlet(name = "MostrarCategorias", urlPatterns = {"/MostrarCategorias"})
public class MostrarCategorias extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Mostrar Categorias</title>");
            out.println("<link href=\'https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css\' rel=\'stylesheet\'>");
            out.println("<script src=\'https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js\'></script>");

            out.println("<script src=\'https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js\'></script>");
            out.println("<script src=\'https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js\'></script>");

            out.println("</head>");
            out.println("<body>");

            out.println("<div class='container'>");
            out.println("<nav class='navbar navbar-expand-lg navbar-light bg-light'>");
            out.println("<div class='container-fluid'>");
            out.println("<a class='navbar-brand' href='#'>Navbar</a>");
            out.println("<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>");
            out.println("<span class='navbar-toggler-icon'></span>");
            out.println("</button>");
            out.println("<div class='collapse navbar-collapse' id='navbarNav'>");
            out.println("<ul class='navbar-nav'>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link active' aria-current='page' href='#'>Home</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='MostrarCategorias'>Listar Categorias</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='categoriaForm.html'>Nuevo</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='ProductoController?accion=listadoDeProductos'>Producto</a>");
            out.println("</li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("</nav>");
            out.println("</div>");

            out.println("<div class='container'>");
            out.println("<table class='table table table-striped'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<td>Clave</td>");
            out.println("<td>Nombre</td>");
            out.println("<td>Descripcion</td>");
            out.println("<td>Eliminar</td>");
            out.println("<td>Actualizar</td>");
            out.println("</tr>");
            out.println("</thead>");
            CategoriaDAO dao = new CategoriaDAO();
            try {
                List lista = dao.readAll();
                for (int i = 0; i < lista.size(); i++) {
                    CategoriaDTO dto = (CategoriaDTO) lista.get(i);
                    out.println("<tbody>");
                    out.println("<tr>");
                    out.println("<td>");
                    out.println("<a href='VerCategoria?id=" + dto.getEntidad().getIdCategoria() + "'class='btn btn-warning'>");
                    out.println(dto.getEntidad().getIdCategoria());
                    out.println("</a>");
                    out.println("</td>");

                    out.println("<td>" + dto.getEntidad().getNombreCategoria() + "</td>");
                    out.println("<td>" + dto.getEntidad().getDescripcionCategoria() + "</td>");
                    out.println("<td>");
                    out.println("<a href = 'EliminarCategoria?id" + dto.getEntidad().getIdCategoria() + "'class='btn btn-danger'>Eliminar</a>");
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<a href = 'ActualizarCategoria?id" + dto.getEntidad().getIdCategoria() + "'class='btn btn-success'>Actualizar</a>");
                    out.println("</td>");
                    out.println("</tr>");
                    out.println("</tbody>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(MostrarCategorias.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</tab;e>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
