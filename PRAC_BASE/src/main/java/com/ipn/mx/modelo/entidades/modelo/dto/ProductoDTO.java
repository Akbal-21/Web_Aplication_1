/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades.modelo.dto;

import com.ipn.mx.modelo.entidades.Producto;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author akbal
 */
@Data
public class ProductoDTO implements Serializable{
    private Producto entidad;
    
    public ProductoDTO(){
        entidad = new Producto();
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Clave Prodcuto: ").append(getEntidad().getIdProducto()).append("/n");
        sb.append("Nombre Producto : ").append(getEntidad().getNombreProducto()).append("/n");
        sb.append("Descripcion Producto : ").append(getEntidad().getDescripcion()).append("/n");
        sb.append("Precio Producto : ").append(getEntidad().getPrecioProducto()).append("/n");
        sb.append("Existencia Producto : ").append(getEntidad().getExistenciaProducto()).append("/n");
        sb.append("Clave Categoria : ").append(getEntidad().getIdCategoria()).append("/n");
        return sb.toString();
    } 
    
    
}
