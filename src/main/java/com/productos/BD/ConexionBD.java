/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.productos.BD;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Guzman
 */
public class ConexionBD {

    Connection con;
    Statement st;
    ResultSet rs;

    String nombre_bd = "BD_prueba_conexion";
    String user = "root";
    String pass = "";
    String direccion = "localhost";
    int puerto = 3306;

    public ConexionBD() {

    }

    public ConexionBD(String nameDB) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la URL de conexión a la base de datos
            String url = "jdbc:mysql://" + direccion + ":" + puerto + "/" + nombre_bd + "";
            con = DriverManager.getConnection(url, user, pass);
            st = con.createStatement();
            System.out.println("Conexion exitosa!");

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error al conectarse a la BD");
            ex.printStackTrace();

        }
    }

    public void ejecutarConsulta(String query) {

        try {
            st.execute(query);
                    
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public ArrayList<Object[]> obtenerDatosObjetos(String query) {

        ArrayList<Object[]> datos = new ArrayList<>();

        try {
            rs = st.executeQuery(query);
            while (rs.next()) {
                int numColumnas = rs.getMetaData().getColumnCount();
                Object[] fila = new Object[numColumnas];

                for (int c = 0; c < numColumnas; c++) {
                    fila[c] = rs.getObject(c + 1);

                }
                datos.add(fila);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return datos;
    }

    public void cerrarConexion() {

        try {
            if (!con.isClosed()) {
                con.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
