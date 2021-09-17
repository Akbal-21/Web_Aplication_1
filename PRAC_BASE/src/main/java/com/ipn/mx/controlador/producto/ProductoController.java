/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.producto;

import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.entidades.modelo.dto.ProductoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author akbal
 */
@WebServlet(name = "ProductoController", urlPatterns = {"/ProductoController"})
public class ProductoController extends HttpServlet {

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

        String accion = request.getParameter("accion");
        if (accion.equals("listadoDeProductos")) {
            listadoDeProductos(request, response);
        } else {
            if (accion.equals("nuevo")) {
                agregarProducto(request, response);
            } else {
                if (accion.equals("eliminar")) {
                    eliminarProducto(request, response);
                } else {
                    if (accion.equals("actualizar")) {
                        actualizarProducto(request, response);
                    } else {
                        if (accion.equals("ver")) {
                            mostrarProducto(request, response);
                        } else {
                            if (accion.equals("guardar")) {
                                almacenarProducto(request, response);
                            }
                        }
                    }
                }
            }

        }

        /*
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductoController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductoController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
         */
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

    private void listadoDeProductos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductoController</title>");
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
            out.println("<a class='nav-link active' aria-current='page' href='/'>Home</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='ProductoController?accion=listadoDeProductos'>Lista Productos</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='ProductoController?accion=nuevo'>Nuevo</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='MostrarCategorias'>Categorias</a>");
            out.println("</li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("</nav>");
            out.println("</div>");
            
            
            out.println("<div class='container'>");
            out.println("<table class='table table-striped'>");
            out.println("<thead>");
            out.println("<tr>");

            out.println("<td>Clave</td>");
            out.println("<td>Nombre</td>");
            out.println("<td>Descipcion</td>");
            out.println("<td>precio</td>");
            out.println("<td>Existencia</td>");
            out.println("<td>Categoria</td>");

            out.println("<td>Eliminar</td>");
            out.println("<td>Actualizar</td>");

            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            ProductoDAO dao = new ProductoDAO();
            List lista;
            try {
                lista = dao.readAll();
                for (int i = 0; i < lista.size(); i++) {
                    ProductoDTO dto = (ProductoDTO) lista.get(i);
                    out.println("<tr>");
                    
                    out.println("<td>");
                    out.println("<a href='ProductoController?accion=ver&id=" + dto.getEntidad().getIdProducto() + "'class='btn btn-warning'>");
                    out.println(dto.getEntidad().getIdProducto());
                    out.println("</a>");
                    out.println("</td>");
                    
                    out.println("<td>" + dto.getEntidad().getNombreProducto()+ "</td>");
                    out.println("<td>" + dto.getEntidad().getDescripcion() + "</td>");
                    out.println("<td>" + dto.getEntidad().getPrecioProducto()+ "</td>");
                    out.println("<td>" + dto.getEntidad().getExistenciaProducto() + "</td>");
                    out.println("<td>" + dto.getEntidad().getIdCategoria() + "</dt>");

                    out.println("<td><a href='ProductoController?accion=eliminar&id=" + dto.getEntidad().getIdProducto() + "' class='btn btn-danger'>Eliminar</a></td>");
                    out.println("<td><a href='ProductoController?accion=actualizar&id=" + dto.getEntidad().getIdProducto() + "' class='btn btn-success'>Actualizar</a></td>");

                    out.println("</tr>");

                }
            } catch (SQLException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }

            out.println("</tbody>");
            out.println("</table>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("profuctoForm.html");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();

        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
        String msg = ""; 
        try {
            dao.delete(dto);
            msg = "Registro Eliminado";
        } catch (SQLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<div align='center'>");
            out.println("<br/>");
            out.println("<a href='ProductoController?accion=listadoDeProductos' class='btn btn-success'>Listado de Productos</a>");
            out.println("</div>");
        }

        /*catch (Exception e) {
        }*/
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void mostrarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductoController</title>");
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
            out.println("<a class='nav-link active' aria-current='page' href='/'>Home</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='ProductoController?accion=listadoDeProductos'>Lista Productos</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='ProductoController?accion=nuevo'>Nuevo</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='MostrarCategorias'>Categorias</a>");
            out.println("</li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("</nav>");
            out.println("</div>");
            
            
            out.println("<div class='container'>");
            out.println("<table class='table table-striped'>");

            ProductoDAO dao = new ProductoDAO();
            ProductoDTO dto = new ProductoDTO();
            dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
            try {
                dto = dao.read(dto);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (dto != null) {
                out.println("<tr>");
                out.println("<th>Clave</th>");
                out.println("<td>"+dto.getEntidad().getIdProducto()+"</td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<th>Nombre</th>");
                out.println("<td>"+dto.getEntidad().getNombreProducto()+"</td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<th>Descipcion</th>");
                out.println("<td>"+dto.getEntidad().getDescripcion()+"</td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<th>precio</th>");
                out.println("<td>"+dto.getEntidad().getPrecioProducto()+"</td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<th>Existencia</th>");
                out.println("<td>"+dto.getEntidad().getExistenciaProducto()+"</td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<th>Categoria</th>");
                out.println("<td>"+dto.getEntidad().getIdCategoria()+"</td>");
                out.println("</tr>");
            } else {
                out.println("<tr>");
                out.println("<th>Cualquier Mensaje</th>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void almacenarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setNombreProducto(request.getParameter("txtNombre"));
        dto.getEntidad().setDescripcion(request.getParameter("txtDescripcion"));
        dto.getEntidad().setPrecioProducto(Double.parseDouble(request.getParameter("txtPrecio")));
        dto.getEntidad().setExistenciaProducto(Integer.parseInt(request.getParameter("txtExistencia")));
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("txtIdCaregoria")));
        try {
            dao.create(dto);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<div align='center'>");
            out.println("<br/>");
            out.println("<a href='ProductoController?accion=listadoDeProductos' class='btn btn-success'>Listado de Productos</a>");
            out.println("</div>");

        }
        
    }
}
