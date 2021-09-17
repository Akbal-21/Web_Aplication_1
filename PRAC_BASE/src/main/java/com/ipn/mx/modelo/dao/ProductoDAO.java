/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.modelo.dto.ProductoDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author akbal
 */
public class ProductoDAO {

    private static final String SQL_INSERT = "Insert into Producto (nombreProducto,descripcion,precioProducto,existenciaProducto,idCategoria)"
            + "values(?,?,?,?,?)";
    private static final String SQL_UPDATE = "update Producto set nombreProducto=?, descripcion=?, precioProducto=?, existenciaProducto=?, idCategoria=?"
            + "where idProducto=?";
    private static final String SQL_DELETE = "delete from Producto where idProducto=?";
    private static final String SQL_SELECT = "select * from Producto where idProducto=?";
    private static final String SQL_SELECT_ALL = "select * from Producto";

    private Connection conexion;

    private void obtenerConexion() {
        String usuario = "postgres";
        String clave = "12345";
        String url = "jdbc:postgresql://localhost:5432/Base3CM18";
        String driverPostgreSql = "org.postgresql.Driver";
        try {
            Class.forName(driverPostgreSql);
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void create(ProductoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getNombreProducto());
            ps.setString(2, dto.getEntidad().getDescripcion());
            ps.setDouble(3, dto.getEntidad().getPrecioProducto());
            ps.setInt(4, dto.getEntidad().getExistenciaProducto());
            ps.setInt(5, dto.getEntidad().getIdCategoria());

            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
    
    public void update(ProductoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getEntidad().getNombreProducto());
            ps.setString(2, dto.getEntidad().getDescripcion());
            ps.setDouble(3, dto.getEntidad().getPrecioProducto());
            ps.setInt(4, dto.getEntidad().getExistenciaProducto());
            ps.setInt(5, dto.getEntidad().getIdCategoria());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    public void delete (ProductoDTO dto) throws SQLException {
        obtenerConexion();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdProducto());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
    
    public ProductoDTO read(ProductoDTO dto) throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs =null;
        try{
            ps = conexion.prepareStatement(SQL_SELECT);
            ps.setInt(1, dto.getEntidad().getIdProducto());
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size() > 0){
                return (ProductoDTO) resultados.get(0);
            }else{
                return null;
            }
        }finally{
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conexion != null) conexion.close();
        }
    }
    
    public List readAll() throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conexion.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if (resultados.size() > 0){
                return resultados;
            }else{
                return null;
            }
        }finally{
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conexion != null) conexion.close();
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while(rs.next()){
            ProductoDTO dto = new ProductoDTO();
            dto.getEntidad().setIdProducto(rs.getInt("idProducto"));
            dto.getEntidad().setNombreProducto(rs.getString("nombreProducto"));
            dto.getEntidad().setDescripcion(rs.getString("descripcion"));
            dto.getEntidad().setPrecioProducto(rs.getDouble("precioProducto"));
            dto.getEntidad().setExistenciaProducto(rs.getInt("existenciaProducto"));
            dto.getEntidad().setIdCategoria(rs.getInt("idCategoria"));
           
            
            resultados.add(dto);
        }
        return resultados;
    }
    
    public static void main(String[] args) {
        ProductoDTO dto = new ProductoDTO();

        ProductoDAO dao = new ProductoDAO();

        try {
            dao.create(dto);
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

}
