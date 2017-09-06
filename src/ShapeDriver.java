import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JColorChooser;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Driver program for Moving-Shapes app
 */
public class ShapeDriver extends JPanel implements KeyListener, ActionListener {

    private Random random;
    private HashSet<Shape> shapeList;
    private Timer timer;
    private boolean paused;

    public ShapeDriver() {
        super();
        random = new Random();
        setSize(new Dimension(600, 600));
        setBackground(Color.DARK_GRAY);
        shapeList = new HashSet<>();
        paused = false;
        timer = new Timer(1000 / 60, this);
        timer.start();
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        // Call super class paintComponent method
        // Background will not be colored otherwise
        super.paintComponent(g);

        // Draws all the shapes in the list so far
        for (Shape shape : shapeList) {
            shape.draw(g);
        }
    }

    // Moves Shapes according to rules
    @Override
    public void actionPerformed(ActionEvent e) {
        for (Shape shape : shapeList) {
            shape.move(getSize().getWidth(), getSize().getHeight());

            for (Shape shape2 : shapeList) {
                if (shape.getRectangle().intersects(shape2.getRectangle())) {
                    shape.changeDx();
                    shape.changeDy();
                    shape2.changeDx();
                    shape2.changeDy();
                    
                    // The circles should swap colors
                    Color tempColor = shape2.getFillColor();
                    shape2.setFillColor(shape.getFillColor());
                    shape.setFillColor(tempColor);
                }
            }
        }

        repaint();
    }

    // Handles keyboard input
    @Override
    public void keyPressed(KeyEvent e) {
        Color fillColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        Color borderColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        int randX = random.nextInt(500);
        int randY = random.nextInt(500);

        switch (e.getKeyCode()) {
            case KeyEvent.VK_C:
                Circle circ = new Circle(fillColor, borderColor, randX, randY, random.nextInt(20) + 10);
                shapeList.add(circ);
                break;
            case KeyEvent.VK_R:
                Box box = new Box(fillColor, borderColor, randX, randY, random.nextInt(20) + 20, random.nextInt(20) + 20);
                shapeList.add(box);
                break;
            case KeyEvent.VK_S:
                Square square = new Square(fillColor, borderColor, randX, randY, random.nextInt(20) + 20);
                shapeList.add(square);
                break;
            case KeyEvent.VK_B:  // Change background color
                Color color = JColorChooser.showDialog(this, "Background Color", getBackground());
                if (!color.equals(getBackground())) {
                    setBackground(color);
                }
                break;
            case KeyEvent.VK_SPACE:  // Pause and start Shape movement
                if (!paused && !shapeList.isEmpty()) {
                    timer.stop();
                    paused = true;
                } else {
                    timer.start();
                }
                break;
            case KeyEvent.VK_ESCAPE:  // Clears panel
                shapeList.clear();
                break;
            default:
                break;
        }

        repaint();
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
}