package com.productos.utils.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.border.Border;

/**
 *
 * @author Diego Guzman
 */
public class JDialogC extends JDialog {

    public JDialogC() {
        super();

    }

    public JDialogC(Frame parent, boolean modal, String titulo) {
        super(parent, modal);
        Border customBorder = BorderFactory.createDashedBorder(Color.BLUE, 2, 5, 2, true);
        
        //super(parent, modal);
        //setTitle(titulo);

    }

    public JDialogC(JFrame parent, boolean modal, String titulo) {
        super(parent, modal);

        Border customBorder = BorderFactory.createDashedBorder(Color.BLUE, 2, 5, 2, true);
        getContentPane().setLayout(new BorderLayout());
        //super(parent, modal);
        //setTitle(titulo);

    }

}
