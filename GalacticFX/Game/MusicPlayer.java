package Game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Created by noemi_000 on 3/31/2017.
 */
public class MusicPlayer {
    MediaPlayer mediaPlayer;

    public MusicPlayer(String musicFile)
    {
        Media Sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer newMediaPlayer = new MediaPlayer(Sound);
        newMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); //loops music
        this.mediaPlayer = newMediaPlayer;
    }

    public void playSong()
    {
        mediaPlayer.play();
    }

    public void pauseSong()
    {
        mediaPlayer.pause();
    }

}
