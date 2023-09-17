package com.productos.utils;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.regex.Pattern;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author Diego Guzman
 */
public class JTextFieldC extends JTextField {

     private DataType dataType;

    public enum DataType {
        TEXT("[a-zA-Z ]*"), // Aceptar solo letras y espacios
        INTEGER("\\d*"), // Aceptar solo números enteros
        DOUBLE("^\\d*(\\.\\d*)?$"), // Aceptar números decimales
        TEXT_AND_NUMBERS("[a-zA-Z0-9 ]*"); // Aceptar letras, números y espacios


        private final String pattern;

        DataType(String pattern) {
            this.pattern = pattern;
        }

        public String getPattern() {
            return pattern;
        }
    }
    
    
    
    
    
    

    private boolean mouseOver = false;
    private Animator animator;
    private boolean animateHinText = true;
    private float location;
    private boolean show;
    private String label = "label";
    private Color lineColor = new Color(3, 155, 216);
   

    public JTextFieldC() {

       this.dataType = DataType.TEXT;
        
        setBorder(new EmptyBorder(20, 3, 10, 3));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOver = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseOver = false;
                repaint();
            }

        });

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                showing(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                showing(true);
            }

        });

     
          

        TimingTargetAdapter target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                animateHinText = getText().equals("");
            }

            @Override
            public void timingEvent(float fraction) {
                location = fraction;
                repaint();
            }

        };

        animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);

    }
    
    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
    
     @Override
    protected void processKeyEvent(KeyEvent e) {
        char c = e.getKeyChar();

        // Verifica el tipo de dato y permite o rechaza caracteres según sea necesario
        String currentText = getText();
        String newText = currentText.substring(0, getSelectionStart()) + c + currentText.substring(getSelectionEnd());

        if (Pattern.matches(dataType.getPattern(), newText) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_ENTER) {
            super.processKeyEvent(e);
            
        } else {
            
            e.consume(); // Descarta caracteres no válidos
            
        }
    }
    

    private void showing(boolean action) {

        if (animator.isRunning()) {
            animator.stop();
        } else {
            location = 1;
        }
        animator.setStartFraction(1f - location);
        show = action;
        location = 1f - location;
        animator.start();

    }

    private void createHintText(Graphics2D g2) {
        Insets in = getInsets();
        g2.setColor(new Color(123, 123, 123));
        FontMetrics fm = g2.getFontMetrics();
        Rectangle2D r2 = fm.getStringBounds(label, g2);
        double height = getHeight() - in.top - in.bottom;
        double textY = (height - r2.getHeight()) / 3;
        double size;
        if (animateHinText) {
            if (show) {
                size = 18 * (1 - location);
            } else {
                size = 18 * location;
            }
        } else {
            size = 18;
        }
        g2.drawString(label, in.right, (int) (in.top + textY + fm.getAscent() - size));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        int width = getWidth();
        int height = getHeight();

        if (mouseOver) {
            g2.setColor(lineColor);
        } else {
            g2.setColor(new Color(150, 150, 150));
        }
        g2.fillRect(2, height - 1, width - 4, 1);
        createHintText(g2);
        createLineStyle(g2);
        g2.dispose();
    }

    private void createLineStyle(Graphics2D g2) {

        if (isFocusOwner()) {
            double width = getWidth() - 4;
            int height = getHeight();
            g2.setColor(lineColor);
            double size;
            if (show) {
                size = width * (1 - location);
            } else {
                size = width * location;
            }
            double x = (width - size) / 2;
            g2.fillRect((int) (x + 2), height - 2, (int) size, 2);
        }
    }

    @Override
    public void setText(String string) {
        if (!getText().equals(string)) {
            showing(string.equals(""));
        }
        super.setText(string);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

 

}
