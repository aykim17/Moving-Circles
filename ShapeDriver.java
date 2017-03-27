import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Driver program for moving circle app
 */
public class ShapeDriver extends JPanel implements KeyListener, ActionListener {

    private Random random;
    private ArrayList<Circle> shapeList;
    private Timer timer;

    public ShapeDriver() {
        super();
        random = new Random();
        setSize(new Dimension(600, 600));
        setBackground(Color.DARK_GRAY);
        shapeList = new ArrayList<Circle>();
        timer = new Timer(1000/60, this);
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
        for (Circle circle: shapeList) {
            circle.draw(g);
        }
    }
    
    // Moves circles according to rules
    @Override
    public void actionPerformed(ActionEvent e) {
        for (Circle circle : shapeList) {
            circle.move();
            if (circle.getX() + circle.getRadius()*2 >= getSize().getWidth()) {
                circle.changeDx();
            } else if (circle.getX() <= 0) {
                circle.changeDx();
            } else if (circle.getY() + circle.getRadius()*2 >= getSize().getHeight()) {
                circle.changeDy();
            } else if (circle.getY() <= 0) {
                circle.changeDy();
            }
            
            for (Circle circle2 : shapeList) {
                if (getDistance(circle.getCenter(), circle2.getCenter()) <=
                        circle.getRadius() + circle2.getRadius()) {
                    circle.changeDx();  
                    circle.changeDy();
                    circle2.changeDx();
                    circle2.changeDy();
                    
                    // The circles should swap colors
                    Color tempColor = circle2.getFillColor();
                    circle2.setFillColor(circle.getFillColor());
                    circle.setFillColor(tempColor);
                }
            }
        }
        
        repaint();
    }

    // Adds Circle to list to be drawn
    @Override
    public void keyPressed(KeyEvent e) {
        Color fillColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        Color borderColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        int randX = random.nextInt(500);
        int randY = random.nextInt(500);

        if (e.getKeyCode() == KeyEvent.VK_C) {
            shapeList.add(new Circle(fillColor, borderColor, randX, randY, random.nextInt(25)));
        }

        repaint();
    }
    
    // Do not neet to do anything with this method from KeyListener
    // but must have since this class implements KeyListiner 
    @Override
    public void keyReleased(KeyEvent e) {
    }

    // Do not need to do anything with this method from KeyListener
    // but must have since this class implements KeyListiner 
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    // Returns distance between two points
    private double getDistance(Point p1, Point p2) {
        double xDifference = p2.x - p1.x;
        double yDifference = p2.y - p1.y;
        
        return Math.sqrt(xDifference*xDifference + yDifference*yDifference);
    }
}