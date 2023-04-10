package drawstuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author madeline lewis
 */
public class DrawStuff extends JFrame {

    public static MainPanel mainPanel = new MainPanel();
    public static ColorPanel colorPanel = new ColorPanel();
    public static MenuPanel menuPanel = new MenuPanel();

    public JPanel colorandcanvas = new JPanel();
    public JPanel menu = new JPanel();
    public JPanel container = new JPanel();
    
    private Color bg = new Color(249, 230, 202);

    public DrawStuff() {
        super("Drawing!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(860, 610);
        setResizable(true);
        setBackground(bg);
        
        colorandcanvas.setLayout(new BoxLayout(colorandcanvas, BoxLayout.X_AXIS));
        colorandcanvas.setBackground(bg);
        colorandcanvas.setPreferredSize(new Dimension(850, 500));
        colorandcanvas.add(colorPanel, BorderLayout.WEST);
        colorandcanvas.add(mainPanel, BorderLayout.AFTER_LAST_LINE);
        
        menu.setMinimumSize(new Dimension(850, 100));
        menu.setLayout(new FlowLayout());
        menu.add(menuPanel);
        menu.setBackground(bg);

        MainPanel.startUpdate();
        
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(bg);
        container.add(colorandcanvas, BorderLayout.NORTH);
        container.add(menu, BorderLayout.AFTER_LAST_LINE);

        add(container);

        setVisible(true);
    }

    public static void main(String[] args) {
        new DrawStuff();
    }
}
