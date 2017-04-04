import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
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
    private int speed;

    public ShapeDriver() {
        super();
        random = new Random();
        setSize(new Dimension(600, 600));
        setBackground(Color.DARK_GRAY);
        shapeList = new ArrayList<Circle>();
        speed = 1;
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
                if (intersecting(circle, circle2)) {
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
        	Circle circ = new Circle(fillColor, borderColor, randX, randY, random.nextInt(20) + 10);
        	circ.setSpeed(speed);
            shapeList.add(circ);
        } else if (e.getKeyCode() == KeyEvent.VK_B) {
        	Color color = JColorChooser.showDialog(this, "Background Color", getBackground());
        	if (!color.equals(getBackground())) {
        		setBackground(color);
        	}
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
        	int newSpeed = Integer.parseInt(JOptionPane.showInputDialog(this, 
        		"Enter the speed", "Speed", JOptionPane.QUESTION_MESSAGE));	
        	
        	if (newSpeed > 0 && newSpeed <= 5) {
        		for (Circle c : shapeList) {
        			speed = newSpeed;
        			c.setSpeed(newSpeed);
        		}
        	} else {
        		JOptionPane.showMessageDialog(this, "Number must be > 0 and <= 5.", "ERROR",
        			JOptionPane.ERROR_MESSAGE);
        	}

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
    private boolean intersecting(Circle c1, Circle c2) {
        double xDifference = c2.getX() - c1.getX();
        double yDifference = c2.getY() - c1.getY();
        double radii = c1.getRadius() + c2.getRadius();

        return xDifference*xDifference + yDifference*yDifference < radii*radii;
    }
}