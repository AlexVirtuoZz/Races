/**
 * Created by Alexander on 07.11.2015.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    public static final int MAX_SPEED = 100;
    public static double min_speed = 0;
    public static final int MAX_TOP = 500;
    public static final int MAX_BOTTOM = 10;

    Image carCentre = new ImageIcon("resources/Player.png").getImage();
    Image carLeft = new ImageIcon("resources/Player_Left.png").getImage();
    Image carRight = new ImageIcon("resources/Player_Right.png").getImage();
    Image car = carCentre;

    int speed = 0;
    int acceleration = 0;
    int way = 0;

    int x = 30;
    int y = 100;
    int dy = 0;

    int layer1 = 0;
    int layer2 = 1200;

    public Rectangle getRect(){
        return new Rectangle(x, y, 150, 50);
    }

    public void move() {
        way += speed ;
        speed += acceleration;

        if (speed <= min_speed) speed = (int) min_speed;
        if (speed >= MAX_SPEED) speed = MAX_SPEED;

        y -= dy;

        if (y >= MAX_TOP) y= MAX_TOP;
        if (y <= MAX_BOTTOM) y= MAX_BOTTOM;

        if (layer2 - speed <= 0 ){
            layer1 = 0;
            layer2 = 1200;
        }else{
            layer1 -= speed;
            layer2 -= speed;
        }
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT){
            acceleration = 2;
        }
        if (key == KeyEvent.VK_LEFT){
            acceleration = -2;
        }
        if (key == KeyEvent.VK_UP){
            dy = 10;
            car = carLeft;
        }
        if (key == KeyEvent.VK_DOWN){
            dy = -10;
            car = carRight;
        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT){
            acceleration = 0;
            car = carCentre;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN){
            dy = 0;
            car = carCentre;
        }
    }
}
