import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Created by Alexander on 08.11.2015.
 */
public class Enemy {
    int x ;
    int y ;
    int speed;

    Image img = new ImageIcon("resources/Enemey.png").getImage();
    ImageObserver forRect = new ImageObserver() {
        @Override
        public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
            return false;
        }
    };
    int width = img.getWidth(forRect);
    int heigh = img.getHeight(forRect);

    Road road;

    public Rectangle getRect(){
        return new Rectangle(x, y, width-7, heigh-7);
    }

    public Enemy (int x, int y,int speed,Road road) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.road = road;
    }

    public void move(){
        x = x - road.p.speed + speed;
    }

}
