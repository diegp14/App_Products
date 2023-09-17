/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.productos.test;

import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import javax.swing.UIManager;

/**
 *
 * @author Diego Guzman
 */
public class Main {

    
    

    public static void main(String args[]) {

//        BCrypt bc = new BCrypt();
//        String saludo = "Hola";
//        String encriptado = BCrypt.hashpw(saludo, BCrypt.gensalt(6));
//        
//        System.out.println("encriptado: "+ encriptado);
//        boolean matches = BCrypt.checkpw(saludo, encriptado);
//        System.out.println("Coinciden: " +matches);
        try {
            UIManager.setLookAndFeel(new FlatLightFlatIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        

        FAcceso acceso = new FAcceso();
        acceso.setVisible(true);

    }

   
}
