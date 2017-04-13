package Game;


import javafx.application.Application;
import javafx.stage.Stage;

//TODO: upgrades, levels, scoring, save/pause/restart, enemy flying patterns, explosion animation

public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage){

        stage = primaryStage;
        stage.setTitle("Galactic Overdrive 3000");
        new MainMenu(stage);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}