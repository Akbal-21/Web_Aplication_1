/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author akbal
 */
@NoArgsConstructor
@Data
public class Producto implements Serializable{
    private int idProducto;
    private String nombreProducto;
    private String descripcion;
    private double precioProducto;
    private int existenciaProducto;
    private int idCategoria;
                    
    
}
