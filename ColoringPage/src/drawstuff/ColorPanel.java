package drawstuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.event.ChangeListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author madeline lewis
 */
public class ColorPanel extends JPanel implements MouseListener {

    private BufferedImage palette = null;
    private BufferedImage blue = null;
    private BufferedImage yellow = null;
    private BufferedImage red = null;
    private BufferedImage purple = null;
    private BufferedImage lgBlue = null;
    private BufferedImage green = null;
    private BufferedImage pink = null;

    private JSlider thickness = new JSlider(JSlider.HORIZONTAL, 1, 50, 9);

    private static int pixelWidth = 9;

    private Color bg = new Color(255, 252, 216);
    
    private static Color clickedColor = new Color(0, 0, 0);

    public ColorPanel() {
        this.addMouseListener(this);
        setBackground(bg);
        this.setBorder(new LineBorder(new Color(140, 136, 131), 2));
        setVisible(true);
        setMaximumSize(new Dimension(350, 500));
        setLayout(new BorderLayout());
        add(thickness, BorderLayout.SOUTH);
        thickness.setBackground(bg);
        thickness.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                JSlider slider = (JSlider) evt.getSource();
                if (!slider.getValueIsAdjusting()) {
                    pixelWidth = slider.getValue();
                    MainPanel.nullMousePoses();
                }
            }
        });

        try {
            palette = ImageIO.read(getClass().getClassLoader().getResource("misc/pal.png"));
        } catch (IOException e) {
        }

        try {
            red = ImageIO.read(getClass().getClassLoader().getResource("colors/208_42_65.png"));
        } catch (IOException e) {
        }
        try {
            blue = ImageIO.read(getClass().getClassLoader().getResource("colors/42_69_208.png"));
        } catch (IOException e) {
        }
        try {
            purple = ImageIO.read(getClass().getClassLoader().getResource("colors/143_42_208.png"));
        } catch (IOException e) {
        }
        try {
            yellow = ImageIO.read(getClass().getClassLoader().getResource("colors/231_239_20.png"));
        } catch (IOException e) {
        }
        try {
            lgBlue = ImageIO.read(getClass().getClassLoader().getResource("colors/114_232_225.png"));
        } catch (IOException e) {
        }
        try {
            green = ImageIO.read(getClass().getClassLoader().getResource("colors/103_215_129.png"));
        } catch (IOException e) {
        }
        try {
            pink = ImageIO.read(getClass().getClassLoader().getResource("colors/249_173_192.png"));
        } catch (IOException e) {
        }
    }

    public static int getPixelWidth() {
        return pixelWidth;
    }
    
    public static Color getDrawingColor() {
        if (MenuPanel.getCustomStatus()) {
            MenuPanel.setLastRUsed(ColorPicker.getUserColor().getRed());
            MenuPanel.setLastGUsed(ColorPicker.getUserColor().getGreen());
            MenuPanel.setLastBUsed(ColorPicker.getUserColor().getBlue());
            return ColorPicker.getUserColor();
        } else {
            return clickedColor;
        }
    }
    
    public static void setCurrentColor(Color clr) {
        clickedColor = clr;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(palette, 50, 30, this);
        g.drawImage(blue, 230, 240, this);
        g.drawImage(red, 89, 195, this);
        g.drawImage(yellow, 205, 105, this);
        g.drawImage(purple, 85, 121, this);
        g.drawImage(lgBlue, 105, 270, this);
        g.drawImage(green, 176, 303, this);
        g.drawImage(pink, 160, 179, this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int clickx = e.getX();
        int clicky = e.getY();
        MenuPanel.setCustomStatus(false);
        
        if (clickx > 230 && clickx < 271 && clicky > 240 && clicky < 282) {
            // blue conditions
            clickedColor = new Color(42, 69, 208);
        } else if (clickx > 89 && clickx < 130 && clicky > 195 && clicky < 237) {
            // red coniditons
            clickedColor = new Color(208, 42, 65);
        } else if (clickx > 205 && clickx < 253 && clicky > 105 && clicky < 154) {
            // yellow conditions
            clickedColor = new Color(244, 244, 66);
        } else if (clickx > 85 && clickx < 136 && clicky > 121 && clicky < 174) {
            //purp conditions
            clickedColor = new Color(143, 42, 208);
        } else if (clickx > 105 && clickx < 154 && clicky > 270 && clicky < 320) {
            // lg blue
            clickedColor = new Color(114, 232, 225);
        } else if (clickx > 176 && clickx < 217 && clicky > 303 && clicky < 345) {
            clickedColor = new Color(103, 215, 129);
        } else if (clickx > 160 && clickx < 211 && clicky > 179 && clicky < 230) {
            clickedColor = new Color(249, 173, 192);
        }
        
        MainPanel.nullMousePoses();
    }


    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }


    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

}
