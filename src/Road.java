/**
 * Created by Alexander on 07.11.2015.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Road extends JPanel implements ActionListener {

    Timer mainTimer = new Timer (20, this);

    Image img = new ImageIcon("resources/doroga.png").getImage();
    Player p = new Player();
    double v = (200/Player.MAX_SPEED) * p.speed;
    double s = (200/Player.MAX_SPEED) * p.way/3600;
    double max_s = 0;

    ArrayList <Enemy> enemies = new ArrayList<Enemy>();
    Thread audio = new Thread(new AudioThread());

    public Road (){
        mainTimer.start();
        enemiesFactory.start();
        audio.start();
        addKeyListener(new KeyAdapted());
        setFocusable(true);
    }

    private class KeyAdapted extends KeyAdapter{

        public void keyPressed (KeyEvent e){
            p.keyPressed(e);
        }

        public void keyReleased (KeyEvent e){
            p.keyReleased(e);
        }

    }

    @Override
    public void paint(Graphics g){
        g = (Graphics2D) g;
        g.drawImage(img, p.layer1, 0, null);
        g.drawImage(img ,p.layer2 ,0 ,null);
        g.drawImage(p.car ,p.x ,p.y ,null);
        g.setColor(Color.WHITE);
        Font font = new Font ("Arial",Font.BOLD,20);
        g.setFont(font);
        g.drawString("Speed :"+ v + " km/h",100,30);
        g.drawString("Distance :"+ s +" km", 300, 30);


        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()){
            Enemy enemy = iterator.next();
            if (enemy.x <= -2000 || enemy.x >= 2000){
                iterator.remove();
            }else {
                enemy.move();
                g.drawImage(enemy.img, enemy.x, enemy.y, null);
            }
        }
    }

    public void actionPerformed (ActionEvent e){
        p.move();
        testCollision();
        v = (200/Player.MAX_SPEED) * p.speed;
        s = (200/Player.MAX_SPEED) * p.way/3600;
        p.min_speed = s ;
        if (s > 100 ){
            JOptionPane.showMessageDialog(null, "Congratulations, you've won!!!\n" +
                    "Your distance = 100 km \n"+
                    "Buy yourself a candy");
            restart();
        }
        repaint();
    }

    private void testCollision(){
        Rectangle testRect = p.getRect();
        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()){
            Enemy enemy = iterator.next();
            if (testRect.intersects(enemy.getRect())){
                if (max_s <= s){
                max_s = s ;}
                JOptionPane.showMessageDialog(null,"You've lost, ha-ha-ha!!!\n " +
                        "Your distance is "+ s +" km\n" +
                        "Maximum distance = "+max_s+" km");
                restart();
            }
        }
    }

    public void restart(){
        p.speed = 0;
        p.min_speed = 0;
        p.acceleration = 0;
        p.way = 0;

        p.x = 30;
        p.y = 100;
        p.dy = 0;

        p.layer1 = 0;
        p.layer2 = 1200;

        p.car = p.carCentre;

        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy e = iterator.next();
            iterator.remove();
        }
        mainTimer.restart();
    }


    Thread enemiesFactory = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true){
                Random random = new Random();
                try {
                    if (s >= 0 && s <=15 ){
                        Thread.sleep(random.nextInt(2000));}
                    else if (s > 15 && s <=30){
                        Thread.sleep(random.nextInt(1500));}
                    else if (s>30 && s<= 45){
                        Thread.sleep(random.nextInt(1000));}
                    else if (s >45 && s<= 65){
                        Thread.sleep(random.nextInt(900));}
                    else if (s> 65) {
                        Thread.sleep(random.nextInt(500));}
                        enemies.add(new Enemy(1200, random.nextInt(600), random.nextInt(30), Road.this));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

}
