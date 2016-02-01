
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Alexander on 14.11.2015.
 */
public class AudioThread implements Runnable {
    @Override
    public void run() {
        try {
            FileInputStream fis = new FileInputStream("resources/song.mp3");
            Player p = new Player(fis);
            p.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
