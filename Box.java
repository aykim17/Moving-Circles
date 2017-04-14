
import java.awt.*;

public class Box extends Shape {

    private int length;
    private int width;

    // New constructors with added new length and width parameters
    public Box(Color newFillColor, Color newBorderColor, int x, int y, int newLength, int newWidth) {
        super(newFillColor, newBorderColor, x, y);
        length = newLength;
        width = newWidth;
    }
    
    @Override
    public void move(double width, double height) {
        if (getX() + this.width >= width) {
            changeDx();
        } else if (getY() + length >= height) {
            changeDy();
        }
        
        super.move(width, height);
    }
    
    // Draws the rectangle
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle rectangle = new Rectangle(getX(), getY(), width, length);
        g2.setPaint(getFillColor());
        g2.fill(rectangle);
        g2.draw(rectangle);
    }
    
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), width, length);
    }
}