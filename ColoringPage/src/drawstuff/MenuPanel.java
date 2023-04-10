package drawstuff;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author madeline lewis
 */
public class MenuPanel extends JPanel implements ActionListener, Runnable {

    private static JButton reset = new JButton("reset canvas!");
    private static JButton save = new JButton("save canvas!");
    private static JButton setBg = new JButton("change background!");
    private static JButton customColor = new JButton("custom color!");

    private Color userColor;
    private static boolean customColorBeingUsed = false;

    private static JPanel resetP = new JPanel();
    private static JPanel saveP = new JPanel();
    private static JPanel setBgP = new JPanel();
    private static JPanel customColorP = new JPanel();

    private JPanel resetAndSave = new JPanel();
    private JPanel bgAndCustom = new JPanel();
    private JPanel eraserPanel = new JPanel();
    private JLabel eraser = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("misc/eraser.png")));

    private Thread updater;
    
    private Color bg = new Color(249, 230, 202);
    private static Color bgColorPasser = Color.WHITE;
    
    private static int lastr = 0;
    private static int lastg = 0;
    private static int lastb = 0;
    
    
    public MenuPanel() {
        this.setMinimumSize(new Dimension(850, 100));
        this.setMaximumSize(new Dimension(850, 100));
        this.setPreferredSize(new Dimension(850, 100));
        this.setBackground(new Color(244, 239, 240));

        GridLayout panelsLay = new GridLayout(2, 0, 5, 5);

        setLayout(new GridLayout(1, 0, 5, 5));

        setBackground(bg);

        Font buttonFont = new Font("SANS_SERIF", Font.PLAIN, 25);

        resetP.add(reset);
        reset.setBorder(new LineBorder(new Color(255, 255, 91), 1));
        reset.setFont(buttonFont);
        resetP.setBackground(new Color(255, 255, 91));

        saveP.add(save);
        save.setBorder(new LineBorder(new Color(84, 172, 255), 1));
        save.setFont(buttonFont);
        saveP.setBackground(new Color(84, 172, 255));

        setBgP.add(setBg);
        setBg.setBorder(new LineBorder(new Color(255, 84, 180), 1));
        setBg.setFont(buttonFont);
        setBgP.setBackground(new Color(255, 84, 180));

        customColorP.add(customColor);
        customColor.setBorder(new LineBorder(new Color(255, 216, 76), 1));
        customColor.setFont(buttonFont);
        customColorP.setBackground(new Color(255, 216, 76));

        reset.addActionListener(this);
        resetAndSave.add(resetP);
        save.addActionListener(this);
        resetAndSave.add(saveP);
        resetAndSave.setLayout(panelsLay);
        customColor.addActionListener(this);
        bgAndCustom.add(customColorP);
        setBg.addActionListener(this);
        bgAndCustom.add(setBgP);
        bgAndCustom.setLayout(panelsLay);
        eraserPanel.add(eraser);
        eraserPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                MainPanel.nullMousePoses();
                ColorPanel.setCurrentColor(MainPanel.getBgColor());
            }
        }
        );

        add(eraserPanel);
        eraserPanel.setBackground(bg);
        add(resetAndSave);
        resetAndSave.setBackground(bg);
        add(bgAndCustom);
        bgAndCustom.setBackground(bg);

        updater = new Thread(this);
        updater.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("reset canvas!")) {
            MainPanel.nullMousePoses();
            MainPanel.wipeCanvas();
        } else if (e.getActionCommand().equals("custom color!")) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ColorPicker colorPick = new ColorPicker(lastr, lastg, lastb);

                }
            });
        } else if (e.getActionCommand().equals("change background!")) {
            MainPanel.updateBackground(ColorPanel.getDrawingColor());
        } else if (e.getActionCommand().equals("save canvas!")) {
            try {
                MainPanel.saveCanvas();
            } catch (IOException ex) {
                System.out.println("whoops");
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(ColorPanel.getDrawingColor());
        g.fillRect(67, 63, 150, 30);
    }

    public static boolean getCustomStatus() {
        return customColorBeingUsed;
    }

    public static void setCustomStatus(boolean yup) {
        customColorBeingUsed = yup;
    }
    
    public static void setLastRUsed(int r) {
        lastr = r;
    }
    
    public static void setLastGUsed(int g) {
        lastg = g;
    }
    
    public static void setLastBUsed(int b) {
        lastb = b;
    }
    
    public static Color getBackgroundColor() {
        return bgColorPasser;
    }

    public void run() {
        new Timer(5, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                repaint();
            }
        }).start();
    }

}
