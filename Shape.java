import java.awt.*;

/**
 * Contains important methods that all shapes share
 */
public abstract class Shape {

    private Color fillColor, borderColor;
    private Point location;
    private int dx = 1, dy = 1;
    
    // Shape constructor
    public Shape(Color newFillColor, Color newBorderColor, int x, int y) {
        fillColor = newFillColor;
        borderColor = newBorderColor;
        location = new Point(x, y);
    }
    
    // Returns current fill color
    public Color getFillColor() {
        return fillColor;
    }
    
    // Changes the fill color
    public void setFillColor(Color newFillColor) {
        fillColor = newFillColor;
    }
    
    // Changes the location by dx and dy
    public void move() {
        location.x += dx;
        location.y += dy;
    }
    
    // Returns the x-coordinate
    public int getX() {
        return location.x;
    }
    
    // Returns the y-coordinate
    public int getY() {
        return location.y;
    }
    
    // Returns the x direction
    public int getDx() {
        return dx;
    }
    
    // Returns the y direction
    public int getDy() {
        return dy;
    }

    public void setSpeed(int n) {  // Change the speed of the shape
        dx = n;
        dy = n;
    }
    
    // Changes the x direction
    public void changeDx() {
        dx *= -1;
    }
    
    // Changes the y direction
    public void changeDy() {
        dy *= -1;
    }
    
    // Abstract methods to be implemented in subclasses
    abstract Rectangle getRectangle();
    abstract void draw(Graphics g);
}