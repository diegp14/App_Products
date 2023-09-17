/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.productos.utils;

import java.io.InputStream;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diego Guzman
 */

    
    
    public class DefaultTableModelC extends DefaultTableModel {

     int ancho, alto;

    public DefaultTableModelC() {
        this.ancho = 50;
        this.alto = 50;
    }

    public DefaultTableModelC(int rowCount, int columnCount) {
        super(rowCount, columnCount);
    }

    public DefaultTableModelC(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    public DefaultTableModelC(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
        this.ancho = 50;
        this.alto = 50;
    }

    public DefaultTableModelC(Object[][] data, Object[] columnNames, int ancho, int alto) {
        super(data, columnNames);
        this.ancho = ancho;
        this.alto = alto;
    }

    @Override
    public void addRow(Object[] rowData) {

        insertRow(getRowCount(), rowData);
        for (int i = 0; i < rowData.length; i++) {
            setValueAt(rowData[i], getRowCount() - 1, i);
        }
    }

 

    public void eliminarFilas(JTable tabla) {

        setRowCount(0);//borra las filas
    }

    
}
