import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Implementation of Circle class
 */

public class Circle extends Shape {

    private int radius;

    // Constructors include new parameter for circle radius
    public Circle(Color newFillColor, Color newBorderColor, int x, int y, int newRadius) {
        super(newFillColor, newBorderColor, x, y);
        radius = newRadius;
    }
    
    @Override
    public void move(double width, double height) {
        if (getX() + 2 * radius >= width) {
            changeDx();
        } else if (getY() + 2 * radius >= height) {
            changeDy();
        }
        
        super.move(width, height);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), radius * 2, radius * 2);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double ellipse = new Ellipse2D.Double(getX(), getY(), radius * 2, radius * 2);
        g2.setPaint(getFillColor());
        g2.fill(ellipse);
        g2.draw(ellipse);
    }
}