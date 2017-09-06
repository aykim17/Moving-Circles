import javax.swing.JFrame;

/**
 * Main class for Moving-Shapes app
 */

public class ShapeWindow extends JFrame {

    private ShapeDriver shapeDriver;

    public ShapeWindow() {
        super("Moving Shapes");
        shapeDriver = new ShapeDriver();
        add(shapeDriver);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame shapeWindow = new ShapeWindow();
    }
}