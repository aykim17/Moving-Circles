
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Implementation of Circle class
 */
public class Circle extends Shape {

    private int radius;
    private Point center;

    // Constructors include new parameter for circle radius
    public Circle(Color newFillColor, Color newBorderColor, int x, int y, int newRadius) {
        super(newFillColor, newBorderColor, x, y);
        radius = newRadius;
        center = new Point(x + radius, y + radius);
    }

    // Moves circle and adjusts the circle's center location
    @Override
    public void move() {
        super.move();
        center.x += getDx();
        center.y += getDy();
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), radius * 2, radius * 2);
    }

    // Returns the center point of the circle
    public Point getCenter() {
        return center;
    }

    // Returns circle's radius
    public int getRadius() {
        return radius;
    }

    // Draws a circle
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double ellipse = new Ellipse2D.Double(getX(), getY(), radius * 2, radius * 2);
        g2.setPaint(getFillColor());
        g2.fill(ellipse);
        g2.draw(ellipse);
    }
}