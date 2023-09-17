/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.productos.Core;

import com.productos.BD.ConexionBD;
import java.util.ArrayList;

/**
 *
 * @author Diego Guzman
 */
public class ProductosCore {

    static int ESTADO_ELIMINADO = 0;
    static int ESTADO_ACTIVO = 1;

    static String nameDB = "BD_prueba_conexion";

    public static ArrayList<Object[]> buscarProductos(String busqueda) {
        //            query += " AND (a.clave like '%" + ClaveArt + "%' OR  a.CodigoBarras like '%" + ClaveArt + "%') "
        String query = "SELECT id, Nombre, "
                + "CONCAT('$',FORMAT(Precio,2)), "
                + "IF(AplicaCredito, 'SI', 'NO'), "
                // + "IF(Estado>0, 'ACTIVO','INACTIVO'),"
                + "' ' AS Modificar,"
                + "' ' AS Eliminar  "
                + "FROM articulo"
                + " WHERE Estado <> " + ESTADO_ELIMINADO
                + " AND (id like '%"+busqueda+"%' OR Nombre like '%" + busqueda  + "%' "
                + " OR Precio like '%" + busqueda + "%')";
        System.out.println("query:" + query);
        ConexionBD cnx = new ConexionBD("BD_prueba_conexion");

        ArrayList<Object[]> datos = cnx.obtenerDatosObjetos(query);
        cnx.cerrarConexion();
        return datos;
    }

    public static void eliminarProducto(int idProducto) {
        String query = "UPDATE articulo SET Estado = " + ESTADO_ELIMINADO
                + " WHERE id = " + idProducto;
        System.out.println("query:" + query);
        ConexionBD cnx = new ConexionBD("BD_prueba_conexion");
        cnx.ejecutarConsulta(query);
        cnx.cerrarConexion();
    }

    public static void crearProducto(String producto, double precio, int aplCredito) {
        String query = "INSERT INTO articulo "
                + "VALUES(null, '" + producto + "',"
                + "" + precio + "," + aplCredito + ", " + ESTADO_ACTIVO + ")";
        System.out.println("insert: " + query);
        ConexionBD cnx = new ConexionBD(nameDB);

        cnx.ejecutarConsulta(query);
        cnx.cerrarConexion();
    }

    public static void actualizarProducto(int idProducto, String producto, double precio, int aplCredito) {
        String query = "UPDATE articulo SET "
                + "Nombre = '" + producto + "', "
                + "Precio = " + precio + ","
                + "AplicaCredito = " + aplCredito
                + " WHERE id = " + idProducto;
        System.out.println("act prd: " + query);
        ConexionBD cnx = new ConexionBD(nameDB);
        cnx.ejecutarConsulta(query);
        cnx.cerrarConexion();

    }

}
