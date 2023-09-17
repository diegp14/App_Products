/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.productos.test;

import com.productos.Core.ProductosCore;
import java.awt.Frame;

/**
 *
 * @author Diego Guzman
 */
public class DActualizarProducto extends DAltaProducto {

    int idProducto;
    String producto;
    double precio;
    int aplCredito;
    Object[] data;
    public DActualizarProducto(Frame parent, boolean modal, Object... datos) {

        super(parent, modal,  datos);
        

        LTitulo.setText("Modificar Producto");
        idProducto = (int) datos[0];
        producto = (String) datos[1];
        precio = (double) datos[2];
        boolean isSelected = ((int) datos[3] > 0 ? true : false);

        CTProducto.setText(producto);
        CTPrecio.setText(precio+"");
        TBCredito.setSelected(isSelected);
        
        
        System.out.println("productos2: " + producto);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    @Override
    public void init(Object... datos) {
        this.data = datos;
        
        System.out.println("prod : "+ datos[1]);
        CTProducto.setText(data[1]+"");
    }

    @Override
    protected void guardarProducto(String producto, double precio, int aplCredito) {
        
        ProductosCore.actualizarProducto(idProducto, producto, precio, aplCredito);
        this.dispose();
    }
    
    

}
