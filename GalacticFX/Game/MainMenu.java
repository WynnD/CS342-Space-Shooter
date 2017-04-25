package Game;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    private Button controlButton;
    private BorderPane menu;
    private Scene menuScene;
    private Scene controlScene;
    private Stage stage;
    private MusicPlayer menuSong;


    public MainMenu(Stage stage){

        this.stage = stage;
        setupMenuFrame();
        playMusic();
        createButtons();
        buttonActions();    //this function sets what each button does
        createControlPage();

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

        controlButton = new Button("CONTROLS");
        controlButton.setStyle("-fx-font: 48 impact; -fx-base: #0de818;");
        menu.setAlignment(controlButton, Pos.TOP_CENTER);
        menu.setMargin(controlButton, new Insets(0, 12, 200, 12));
        menu.setBottom(controlButton);
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

        controlButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.out.println("control button pushed");
                stage.setScene(controlScene);
                stage.show();
            }
        });
    }

    public void playMusic(){

        //Menu Music
        menuSong = new MusicPlayer("Music/spaceIntro.mp3");
        menuSong.playSong();

    }

    public void createControlPage(){
        Sprite background = new Sprite("Images/ControlsPage.jpg", 550, 700);
        StackPane controlPane = new StackPane();

        Button backButton = new Button("BACK");
        backButton.setStyle("-fx-font: 48 impact; -fx-base: #0de818;");

        backButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(menuScene);
                stage.show();
            }
        });

        controlPane.setAlignment(backButton, Pos.BOTTOM_CENTER);
        controlPane.getChildren().addAll(background.getImageView(), backButton);

        controlScene = new Scene(controlPane);

    }

}
