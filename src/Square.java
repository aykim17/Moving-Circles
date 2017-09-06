import java.awt.*;

public class Square extends Box {

    private int sideLength;

    public Square(Color newFillColor, Color newBorderColor, int x, int y, int newLength) {
        super(newFillColor, newBorderColor, x, y, newLength, newLength);
        sideLength = newLength;
    }
    
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), sideLength, sideLength);
    }
}