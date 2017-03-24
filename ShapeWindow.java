////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Spring 17
//
//  Lab 9
//  @Author Andrew Kim
//
///////////////////////////////////////////////////////////////////////////////////

import javax.swing.JFrame;

/**
 * Main application for moving circles app
 */
public class ShapeWindow extends JFrame {

    private ShapeDriver shapeDriver;

    public ShapeWindow() {
        super("Moving Circles");
        shapeDriver = new ShapeDriver();
        add(shapeDriver);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        // run main application 
        JFrame shapeWindow = new ShapeWindow();
    }
}