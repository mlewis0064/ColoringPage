package drawstuff;

/**
 *
 * @author juno
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;

public class ColorPicker extends JFrame implements ChangeListener {

    MiniColorPanel canvas;
    JSlider red;
    JSlider green;
    JSlider blue;
    JButton save;
    static Color savedColor;

    public ColorPicker(int r, int g, int b) {
        super("Pick a color!");
        setSize(270, 300);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);

        canvas = new MiniColorPanel(r, g, b);
        red = new JSlider(0, 255, r);
        green = new JSlider(0, 255, g);
        blue = new JSlider(0, 255, b);

        red.setMajorTickSpacing(50);
        red.setMinorTickSpacing(10);
        red.setPaintTicks(true);
        red.setPaintLabels(true);
        red.addChangeListener(this);

        green.setMajorTickSpacing(50);
        green.setMinorTickSpacing(10);
        green.setPaintTicks(true);
        green.setPaintLabels(true);
        green.addChangeListener(this);

        blue.setMajorTickSpacing(50);
        blue.setMinorTickSpacing(10);
        blue.setPaintTicks(true);
        blue.setPaintLabels(true);
        blue.addChangeListener(this);

        JLabel redLabel = new JLabel("red: ");
        JLabel greenLabel = new JLabel("green: ");
        JLabel blueLabel = new JLabel("blue: ");
        GridLayout grid = new GridLayout(5, 1);
        FlowLayout right = new FlowLayout(FlowLayout.RIGHT);
        setLayout(grid);

        JPanel redPanel = new JPanel();
        redPanel.setLayout(right);
        redPanel.add(redLabel);
        redPanel.add(red);
        add(redPanel);

        JPanel greenPanel = new JPanel();
        greenPanel.setLayout(right);
        greenPanel.add(greenLabel);
        greenPanel.add(green);
        add(greenPanel);

        JPanel bluePanel = new JPanel();
        bluePanel.setLayout(right);
        bluePanel.add(blueLabel);
        bluePanel.add(blue);
        add(bluePanel);
        add(canvas);

        add(new JButton(new AbstractAction("save color!") {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MainPanel.nullMousePoses();
                savedColor = new Color(red.getValue(), green.getValue(), blue.getValue());
                MenuPanel.setCustomStatus(true);
            }
        }));

        setVisible(true);
    }

    public void stateChanged(ChangeEvent event) {
        JSlider source = (JSlider) event.getSource();
        if (source.getValueIsAdjusting() != true) {
            Color current = new Color(red.getValue(), green.getValue(), blue.getValue());
            canvas.changeColor(current);
            canvas.repaint();
        }
    }
    
    public static Color getUserColor() {
        return savedColor;
    }

}

class MiniColorPanel extends JPanel {

    Color background;

    MiniColorPanel(int r, int g, int b) {
        background = new Color(r, g, b);
    }

    public void paintComponent(Graphics comp) {
        Graphics2D comp2D = (Graphics2D) comp;
        comp2D.setColor(background);
        comp2D.fillRect(0, 0, getSize().width, getSize().height);
    }

    public void changeColor(Color newBackground) {
        background = newBackground;
    }
}
