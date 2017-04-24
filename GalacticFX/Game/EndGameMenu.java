package Game;

/**
 * Created by noemi_000 on 4/14/2017.
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;

/**
 * Created by Deon on 4/12/2017.
 */

public class EndGameMenu {

    private int width;
    private int height;
    private Game game;
    private Button restartButton;
    private BorderPane menu;
    private Scene menuScene;
    private Stage stage;
    private MusicPlayer menuSong;


    public EndGameMenu(Game game, Stage stage, String result){

        this.stage = stage;
        this.game = game;
        setupMenuFrame(result);
        playMusic();
        createButtons();
        buttonActions();    //this function sets what each button does

    }

    public void setupMenuFrame(String result){

        //set width and height, value based on game background image
        width = 550;
        height = 700;

        menu = new BorderPane();
        menu.setStyle("-fx-background-color: black;");
        menu.setPrefSize(width, height);

        Label endLabel = new Label();
        Font font = new Font("Arial", 70);
        endLabel.setFont(font);

        if(result.equals("victory"))
        {
            endLabel.setText("VICTORY");
            endLabel.setTextFill(Color.GREENYELLOW);
        }
        else
        {
            endLabel.setText("GAME OVER");
            endLabel.setTextFill(Color.RED);
        }
        menu.setCenter(endLabel);
        System.out.println("game over");

        menuScene = new Scene(menu);
        stage.setScene(menuScene);


    }

    public void createButtons(){

        //Make Restart Button
        restartButton = new Button("RESTART");
        restartButton.setStyle("-fx-font: 48 impact; -fx-base: #0de818;");
        menu.setAlignment(restartButton, Pos.CENTER);
        menu.setBottom(restartButton);

    }

    public void buttonActions(){

        restartButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                menuSong.pauseSong();
                System.out.println("restarting game");
                game.initialize();
                game.startTimer();
            }
        });
    }

    public void playMusic(){
        //Menu Music
        menuSong = new MusicPlayer("Music/spaceover.mp3");
        menuSong.playSong();

    }

}
