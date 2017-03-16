//CS342 space shooter
/* IMPLEMENTED SO FAR: Key listeners, canvas, background image, player object(currently just rectangle) */
//A blue rectangle appears on starry background and can be moved with arrow keys



package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage){

        //set background image
        Image background = new Image("http://1-background.com/images/stars-1/starry-night-purple-tile.jpg");
        ImageView imgView = new ImageView(background);
        double width = background.getWidth();
        double height = background.getHeight();

        //create canvas size of background image
        Canvas canvas = new Canvas(width, height);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        //create stackpane for background image and canvas
        StackPane sp = new StackPane();
        sp.getChildren().addAll(imgView, canvas);

        Scene scene = new Scene(sp);
        primaryStage.setTitle("Galactic Overdrive 3000");
        primaryStage.setScene(scene);
        primaryStage.show();

        //create rectangle, will be replaced with spaceship
        Rectangle r = new Rectangle();
        r.setX(300);
        r.setY(200);
        r.setWidth(30);
        r.setHeight(30);
        r.setArcWidth(20);
        r.setArcHeight(20);

        //draws the rectangle on the canvas
        drawSomething(graphicsContext, r);

        //key listeners for arrow keys, need to add bound checking
        scene.setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.RIGHT){
                clearCanvas(graphicsContext, r);
                r.setX(r.getX() + 5);
                drawSomething(graphicsContext, r);
            }
            else if(e.getCode() == KeyCode.LEFT){
                clearCanvas(graphicsContext, r);
                r.setX(r.getX() - 5);
                drawSomething(graphicsContext, r);
            }
            else if(e.getCode() == KeyCode.UP){
                clearCanvas(graphicsContext, r);
                r.setY(r.getY() - 5);
                drawSomething(graphicsContext, r);
            }
            else if(e.getCode() == KeyCode.DOWN){
                clearCanvas(graphicsContext, r);
                r.setY(r.getY() + 5);
                drawSomething(graphicsContext, r);
            }
        });
    }


    public static void main(String[] args){
        launch(args);
    }

    private void drawSomething(GraphicsContext gc, Rectangle r)
    {
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(3);

        gc.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    private void clearCanvas(GraphicsContext gc, Rectangle r)
    {
        gc.clearRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }
}
