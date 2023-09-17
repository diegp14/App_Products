/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.productos.test;

import com.productos.Core.ProductosCore;
import com.productos.utils.DefaultTableModelC;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diego Guzman
 */
public class FAcceso extends javax.swing.JFrame {

    /**
     * Creates new form FAcceso
     */
    DefaultTableModelC modelo;
    String rutaRoboto = "../fonts/Roboto/Roboto-Regular.ttf";
    public static Font RobotoRegular;
    Icon imageModificar;

    public FAcceso() {
        initComponents();
        //RobotoRegular = cargarFuenteS("../fonts/Roboto/Roboto-Regular.ttf", 14);
        setLocationRelativeTo(null);
        setTitle("Mostrar Productos");
        configTabla();
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        imageModificar = new ImageIcon("update.png");
        setIconImage(devolverImagen("principal.png"));
        

        modelo = new DefaultTableModelC();
        modelo = (DefaultTableModelC) TProductos.getModel();
        TProductos.setForeground(new Color(33, 33, 33));

        TProductos.setDefaultRenderer(Object.class, new TableRenderTraArticulos());
        
        //TProductos.setFont(RobotoRegular.deriveFont(Font.TRUETYPE_FONT, 16f));
        buscarProductos("");

    }
    
 private Image devolverImagen(String imagePath) {
        BufferedImage image = null;
        Image imgDevolver;
        try {
            // Carga la imagen desde el directorio de recursos
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(imagePath);
            image = ImageIO.read(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        imgDevolver = new ImageIcon(image).getImage();
        return imgDevolver;
 }

    public static Font cargarFuenteS(String fontName, float size) {
        //create the font
        Font customFont = null;
        try {
            //create the font to use. Specify the size!
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontName)).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontName)));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return customFont;
    }

    public void configTabla() {

        TProductos.setModel(new DefaultTableModelC(
                new Object[][]{},
                new String[]{
                    "Código", "Producto", "Precio", "Aplica Crédito", //3
                    "Modificar", "Eliminar"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        TProductos.setRowHeight(50);
        
        SCTProductos.setViewportView(TProductos);

        if (TProductos.getColumnModel().getColumnCount() > 0) {
            TProductos.getColumnModel().getColumn(0).setMinWidth(70);
            //TProductos.getColumnModel().getColumn(1).setMinWidth(70);
            TProductos.getColumnModel().getColumn(2).setMinWidth(100);
            TProductos.getColumnModel().getColumn(3).setMinWidth(50);
            TProductos.getColumnModel().getColumn(4).setMinWidth(50);
            TProductos.getColumnModel().getColumn(5).setMinWidth(100);
            TProductos.getColumnModel().getColumn(5).setPreferredWidth(100);
            TProductos.getColumnModel().getColumn(5).setMaxWidth(100);
//            TProductos.getColumnModel().getColumn(6).setMinWidth(100);
        }
        
           JScrollBar verticalScrollBar = SCTProductos.getVerticalScrollBar();
          // Personalizar la apariencia de la barra de desplazamiento vertical
            verticalScrollBar.setBackground(Color.LIGHT_GRAY);
            verticalScrollBar.setForeground(Color.DARK_GRAY);
            verticalScrollBar.setPreferredSize(new Dimension(10, 0));
         verticalScrollBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            
            
            //verticalScrollBar.set
           
        
//Estilos
        TProductos.setGridColor(Color.white);
        SCTProductos.setBorder(BorderFactory.createEmptyBorder());
        
        
        TProductos.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        
        TProductos.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
        
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {

                JLabel c = (JLabel) super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                c.setBackground(new Color(55, 55, 55));
                c.setForeground(new Color(200, 200, 200));
                c.setPreferredSize(new Dimension(c.getWidth(), 40));
                c.setBorder(BorderFactory.createEmptyBorder());
                c.setHorizontalAlignment(CENTER);
                c.setFont(new java.awt.Font("Arial", 1, 16 ));

                return c;
            }
        });

        //
    }

    public JButton createButtonWithImage(String imagePath, String text) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout());
        button.setBorder(new EmptyBorder(10, 10, 10, 10));
        //button.setBackground(Color.red);

        button.setFont(new Font("Arial", 0, 12));
        LayoutManager lm = button.getLayout();

        try {
            // Carga la imagen desde el directorio de recursos
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(imagePath);
            BufferedImage image = ImageIO.read(inputStream);

            // Establece la imagen en el botón
            button.setIcon(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Puedes personalizar el botón según tus necesidades aquí
        button.setText(text);

        return button;
    }

    public void buscarProductos(String busqueda) {
        JButton bMdidicar = createButtonWithImage("update.png", "");
        JButton bEliminar = createButtonWithImage("delete.png", "");

        modelo.setRowCount(0); // Borra las filas 

        ArrayList<Object[]> data = ProductosCore.buscarProductos(busqueda);

        for (int i = 0; i < data.size(); i++) {
            System.out.println(i);
            Object[] fila = data.get(i);
            fila[fila.length - 2] = bMdidicar;
            fila[fila.length - 1] = bEliminar;
            modelo.addRow(fila); // Inserta nuevas filas

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SCTProductos = new javax.swing.JScrollPane();
        TProductos = new javax.swing.JTable();
        BAgregar = new javax.swing.JButton();
        CTBusqueda = new com.productos.utils.JTextFieldC();
        BBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        SCTProductos.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        TProductos.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        TProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Producto", "Precio", "Aplica Crédito", "Estado", "Modificar", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TProductos.getTableHeader().setReorderingAllowed(false);
        TProductos.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                TProductosMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                TProductosMouseMoved(evt);
            }
        });
        TProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TProductosMousePressed(evt);
            }
        });
        SCTProductos.setViewportView(TProductos);
        if (TProductos.getColumnModel().getColumnCount() > 0) {
            TProductos.getColumnModel().getColumn(0).setResizable(false);
            TProductos.getColumnModel().getColumn(1).setResizable(false);
            TProductos.getColumnModel().getColumn(1).setPreferredWidth(200);
            TProductos.getColumnModel().getColumn(2).setResizable(false);
            TProductos.getColumnModel().getColumn(3).setResizable(false);
            TProductos.getColumnModel().getColumn(4).setResizable(false);
            TProductos.getColumnModel().getColumn(6).setResizable(false);
        }

        BAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png"))); // NOI18N
        BAgregar.setText("Agregar");
        BAgregar.setToolTipText("Agregar producto");
        BAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BAgregarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BAgregarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BAgregarMousePressed(evt);
            }
        });

        CTBusqueda.setDataType(com.productos.utils.JTextFieldC.DataType.TEXT_AND_NUMBERS);
        CTBusqueda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        CTBusqueda.setLabel("Busqueda");
        CTBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CTBusquedaKeyReleased(evt);
            }
        });

        BBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/filter2.png"))); // NOI18N
        BBuscar.setText("Buscar");
        BBuscar.setToolTipText("Buscar producto");
        BBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BBuscarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BBuscarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BBuscarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CTBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addGap(252, 252, 252)
                .addComponent(BBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(SCTProductos)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CTBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BAgregar)
                    .addComponent(BBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SCTProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TProductosMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TProductosMouseDragged

    }//GEN-LAST:event_TProductosMouseDragged

    private void TProductosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TProductosMouseMoved
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        if (TProductos.columnAtPoint(evt.getPoint()) >= 4) {

            setCursor(new Cursor(Cursor.HAND_CURSOR));
            repaint();
        }
        repaint();

//else{
//              setCursor(getCursor());
//        }

    }//GEN-LAST:event_TProductosMouseMoved

    private void BAgregarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BAgregarMouseExited
     
    }//GEN-LAST:event_BAgregarMouseExited

    private void BAgregarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BAgregarMouseEntered
        
    }//GEN-LAST:event_BAgregarMouseEntered

    private void TProductosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TProductosMousePressed

        int col = TProductos.getSelectedColumn();
        int fila = TProductos.getSelectedRow();

        int idProducto = (int) TProductos.getValueAt(fila, 0);
        String producto = (String) TProductos.getValueAt(fila, 1);

        String price = TProductos.getValueAt(fila, 2).toString().substring(1);

        double precio = Double.valueOf(price);
        int aplCredito = (int) (TProductos.getValueAt(fila, 3).toString().equals("SI") ? 1 : 0);

        //System.out.println("Producto: " + idProducto + ": " + producto + " : " + precio + " : "
        //        + aplCredito);

        if (col == 4 || evt.getClickCount() == 2) {

            modificarProducto(idProducto, producto, precio, aplCredito);
            
            buscarProductos("");
           
        } else if (col == 5) {
            String nombre = (String) TProductos.getValueAt(fila, 1);
            int confirm = JOptionPane.showConfirmDialog(this, "Realmente desea borrar el producto '" + nombre + "' \n (Si/No)",
                    "Borrar Producto", JOptionPane.OK_CANCEL_OPTION);

            if (confirm == 0) {

                eliminarProducto(idProducto);
                buscarProductos("");
            }

        } else if (fila > -1 && evt.getButton() == MouseEvent.BUTTON3 && evt.getClickCount() == 1) {
            System.out.println("click derecho");

        }


    }//GEN-LAST:event_TProductosMousePressed

    private void BAgregarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BAgregarMousePressed

        DAltaProducto dap = new DAltaProducto(this, true);
        buscarProductos("");

    }//GEN-LAST:event_BAgregarMousePressed

    private void BBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BBuscarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_BBuscarMouseEntered

    private void BBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BBuscarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_BBuscarMouseExited

    private void BBuscarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BBuscarMousePressed
     
        String busqueda = CTBusqueda.getText();
        buscarProductos(busqueda);
        
        
    }//GEN-LAST:event_BBuscarMousePressed

    private void CTBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CTBusquedaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
             buscarProductos(CTBusqueda.getText());
        }
    }//GEN-LAST:event_CTBusquedaKeyReleased

    private void eliminarProducto(int idProducto) {

        ProductosCore.eliminarProducto(idProducto);
    }

    private void modificarProducto(int idProducto, String producto, double precio, int aplCredito) {

       

        DActualizarProducto act = new DActualizarProducto(this, true, idProducto,
                producto, precio, aplCredito);

    }

    public class TableRenderTraArticulos extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object valor, boolean bln, boolean bln1, int fila, int col) {

            Component c = super.getTableCellRendererComponent(jtable, valor, bln,
                    bln1, fila, col);
            if (bln == false) {
                if (fila % 2 == 0) {

                    setBackground(new Color(245, 245, 250));
                    setForeground(Color.BLACK);

                } else {
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                }
            }

            if (valor instanceof JLabel) {
                JLabel label = (JLabel) valor;
                label.setOpaque(true);
                fillColor(jtable, label, bln);
                return label;
            }

            if (valor instanceof JButton) {//Si lo que hay en la celda es un botón lo regresa
                return (JButton) valor;
            }

            if (valor instanceof JCheckBox) {//Si lo que hay en la celda es un botón lo regresa
                return (JCheckBox) valor;
            }

            if (col == 7) {
                setToolTipText("Precio 1");
                c.setFont(new java.awt.Font("Helvetica", 0, 18));
            }

            if (col == 2) {
                setHorizontalAlignment(JLabel.RIGHT);

            } else if (col == 3) {
                setHorizontalAlignment(JLabel.CENTER);
            } else {
                setHorizontalAlignment(JLabel.LEFT);
            }

            return c;
            //super.getTableCellRendererComponent(jtable, valor, bln, bln1, fila, col); //To change body of generated methods, choose Tools | Templates.
        }

        public void fillColor(JTable t, JLabel l, boolean isSelected) {
            if (isSelected) {

                l.setBackground(t.getSelectionBackground());
                l.setForeground(t.getSelectionForeground());
                //System.out.println(t.getSelectionBackground()+"-"+t.getSelectionForeground());

            } else {

                l.setBackground(t.getBackground());
                l.setForeground(t.getForeground());

            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAgregar;
    private javax.swing.JButton BBuscar;
    private com.productos.utils.JTextFieldC CTBusqueda;
    private javax.swing.JScrollPane SCTProductos;
    private javax.swing.JTable TProductos;
    // End of variables declaration//GEN-END:variables
}
