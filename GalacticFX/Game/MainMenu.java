package Game;


import javafx.scene.control.Button;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

/**
 * Created by Deon on 4/12/2017.
 */

public class MainMenu {

    private int width;
    private int height;
    private Game game;
    private Button startButton;
    private BorderPane menu;
    private Scene menuScene;
    private Stage stage;
    private MusicPlayer menuSong;


    public MainMenu(Stage stage){

        this.stage = stage;
        setupMenuFrame();
        playMusic();
        createButtons();
        buttonActions();    //this function sets what each button does

    }

    public void setupMenuFrame(){

        //set width and height, value based on game background image
        width = 550;
        height = 700;

        menu = new BorderPane();
        menu.setStyle("-fx-background-color: black;");
        menu.setPrefSize(width, height);

        menuScene = new Scene(menu);
        stage.setScene(menuScene);


    }

    public void createButtons(){

        //Make Start Button
        startButton = new Button("START");
        startButton.setStyle("-fx-font: 48 impact; -fx-base: #0de818;");
        menu.setCenter(startButton);


    }

    public void buttonActions(){

        startButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                menuSong.pauseSong();
                System.out.println("start pushed, creating new game");
                game = new Game(stage);
            }
        });
    }

    public void playMusic(){

        //Menu Music
        menuSong = new MusicPlayer("Music/spaceIntro.mp3");
        menuSong.playSong();

    }

    public void createControlPage(){

    }

}
