package drawstuff;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.LineBorder;

/**
 *
 * @author madeline lewis
 */
public class MainPanel extends JPanel implements MouseMotionListener, Runnable {

    private static int CANVAS_WIDTH = 500;
    private static int CANVAS_HEIGHT = 500;

    private static BufferedImage canvas;

    private static Color[][] pixelColor = new Color[CANVAS_WIDTH][CANVAS_HEIGHT];
    private static Color backgroundColor = Color.WHITE;

    int pixelWidth = 9;
    Color drawingColor;

    static int mouseXPos = -1;
    static int mouseYPos = -1;
    
    static int canvasSaveNum = 1;

    static Thread updatingCanvas;

    public MainPanel() {
        setMaximumSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        setBackground(Color.red);
        setBorder(new LineBorder(Color.black, 2));
        this.addMouseMotionListener(this);
        canvas = new BufferedImage(CANVAS_HEIGHT, CANVAS_WIDTH, BufferedImage.TYPE_INT_ARGB);
        updatingCanvas = new Thread(this);
        setVisible(true);

        for (int w = 0; w < pixelColor.length; w++) {
            for (int h = 0; h < pixelColor[0].length; h++) {
                pixelColor[w][h] = backgroundColor;
            }
        }
    }

    public static void startUpdate() {
        updatingCanvas.start();
    }

    public static void updateBackground(Color newBG) {
        Color previousBg = backgroundColor;
        backgroundColor = newBG;
        for (int w = 0; w < pixelColor.length; w++) {
            for (int h = 0; h < pixelColor[0].length; h++) {
                if (pixelColor[w][h] == previousBg) {
                    pixelColor[w][h] = backgroundColor;
                }
            }
        }
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        for (int w = 0; w < CANVAS_WIDTH; w++) {
            for (int h = 0; h < CANVAS_HEIGHT; h++) {
                canvas.setRGB(w, h, pixelColor[w][h].getRGB());
            }
        }

        g.drawImage(canvas, 0, 0, this);
    }

    public void updateCanvas(int Mousx, int Mousy) {
        updateUserInputs();
        if (Mousx >= CANVAS_WIDTH || Mousy >= CANVAS_HEIGHT || Mousx < 0 || Mousy < 0) {
            // do nothing
        } else {
            for (int x = 0; x < pixelColor.length; x++) {
                for (int y = 0; y < pixelColor[0].length; y++) {
                    if (abs(((y - Mousy) * (y - Mousy)) + ((x - Mousx) * (x - Mousx))) <= pixelWidth * pixelWidth) {
                        pixelColor[x][y] = drawingColor;
                    }
                }
            }
        }
    }

    public void run() {
        new Timer(0, (ActionEvent event) -> {
            if (mouseXPos > 0 && mouseYPos > 0) {
                updateCanvas(mouseXPos, mouseYPos);
            }
            repaint();
        }).start();
    }

    public void updateUserInputs() {
        pixelWidth = ColorPanel.getPixelWidth();
        drawingColor = ColorPanel.getDrawingColor();
    }

    public static void wipeCanvas() {
        backgroundColor = Color.WHITE;
        for (int w = 0; w < pixelColor.length; w++) {
            for (int h = 0; h < pixelColor[0].length; h++) {
                pixelColor[w][h] = backgroundColor;
            }
        }

    }

    public static void saveCanvas() throws IOException {
        System.out.println("saved!");
        String userHomeFolder = System.getProperty("user.home", "Desktop");
        File outputfile = new File(userHomeFolder, "canvas" + canvasSaveNum + ".png");
        ImageIO.write(canvas, "png", outputfile);
        canvasSaveNum++;
    }

    public static Color getBgColor() {
        return backgroundColor;
    }

    public static void nullMousePoses() {
        mouseXPos = -1;
        mouseYPos = -1;
    }

    public void mouseDragged(MouseEvent e) {
        mouseXPos = e.getX();
        mouseYPos = e.getY();
    }

    public void mouseMoved(MouseEvent e) {

    }

}
