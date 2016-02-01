/**
 * Created by Alexander on 07.11.2015.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Body {
    static JFrame frame = new JFrame ("Races");
    static JFrame mainMenu = new JFrame("Races");
    static JPanel menu = new JPanel();

    public static void menu(){
        JButton startGame = new JButton("Start Game");
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Body.mainMenu.dispose();
                Body.game();
            }
        });
        menu.add(startGame);
        mainMenu.add(menu);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setSize(400,300);
        mainMenu.setVisible(true);
    }

    public static void game(){
        Road r = new Road();
        JButton restart = new JButton("Restart Game");
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                r.restart();
            }
        });
        r.add(restart);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100,600);
        frame.add(r);
        frame.setVisible(true);
    }

}
