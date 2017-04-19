package Game;

import java.util.*;

/**
 * Created by Wynn on 4/12/2017.
 */
public interface MoveBehavior {

    Coordinate2D getNextPosition();
}

class UserMoveBehavior implements MoveBehavior {

    private KeyListen keylistener;
    private UserSpaceship ship;


    public UserMoveBehavior(UserSpaceship ship)
    {
        this.ship = ship;
        this.keylistener = ship.getKeyListener();
    }

    @Override
    public Coordinate2D getNextPosition()
    {
        Coordinate2D new_position = new Coordinate2D(ship.getPosition());

        if (keylistener.getRightKeyPressed()) {
            new_position.translateX(5);
        }
        if (keylistener.getLeftKeyPressed()){
            new_position.translateX(-5);
        }
        if (keylistener.getDownKeyPressed()){
            new_position.translateY(5);
        }
        if (keylistener.getUpKeyPressed()) {
            new_position.translateY(-5);
        }

        return new_position;
    }
}

class HorizontalMoveBehavior implements MoveBehavior {
    private boolean moveRight;
    private boolean moveLeft;
    private int position;
    private Vector2D velocity;
    private EnemySpaceship ship;

    public HorizontalMoveBehavior(EnemySpaceship ship)
    {
        this.ship = ship;
        this.moveRight = false;
        this.moveLeft = true;
        this.position = 0;
        this.velocity = ship.getVelocityVector();
    }

    @Override
    public Coordinate2D getNextPosition()
    {
        Coordinate2D new_position = new Coordinate2D(ship.getPosition());
        if(moveLeft){
            if(position > -100) {
                position -= 2;
            }
            else{
                ship.setVelocity(new Vector2D(2,0));
                moveRight = true;
                moveLeft = false;
            }
        }
        else if(moveRight){
            if(position < 100){
                position+=2;
            }
            else{
                ship.setVelocity(new Vector2D(-2,0));
                moveLeft = true;
                moveRight = false;
            }
        }

        new_position.applyVelocity(ship.getVelocityVector());

        return new_position;
    }
}



class ErraticMoveBehavior implements MoveBehavior {
    private Random rand;
    private boolean moveRight;
    private boolean moveLeft;
    private int position;
    private Vector2D velocity;
    private EnemySpaceship ship;

    public ErraticMoveBehavior(EnemySpaceship ship)
    {
        this.ship = ship;
        this.moveRight = false;
        this.moveLeft = true;
        this.position = 0;
        this.velocity = ship.getVelocityVector();
        this.rand = new Random();
    }

    @Override
    public Coordinate2D getNextPosition()
    {
        Coordinate2D new_position = new Coordinate2D(ship.getPosition());
        if(moveLeft){
            if(position > -50) {
                position -= 2;
            }
            else{
                ship.setVelocity(new Vector2D(2,rand.nextInt()%2));
                moveRight = true;
                moveLeft = false;
            }
        }
        else if(moveRight){
            if(position < 50){
                position+=2;
            }
            else{
                ship.setVelocity(new Vector2D(-2,rand.nextInt()%2));
                moveLeft = true;
                moveRight = false;
            }
        }

        new_position.applyVelocity(ship.getVelocityVector());

        return new_position;
    }

}

class CircleMoveBehavior implements MoveBehavior {

    private int position;
    private double rad_angle;
    private int magnitude;
    private Vector2D velocity;
    private EnemySpaceship ship;

    public CircleMoveBehavior(EnemySpaceship ship)
    {
        this.ship = ship;
        this.position = 0;
        this.velocity = ship.getVelocityVector();
        this.rad_angle = 2*Math.PI;
        this.magnitude = 2;
    }

    @Override
    public Coordinate2D getNextPosition()
    {
        Coordinate2D new_position = new Coordinate2D(ship.getPosition());

        Vector2D new_velocity = new Vector2D(magnitude * Math.cos(rad_angle),
                magnitude * Math.sin(rad_angle));
        ship.setVelocity(new_velocity);

        rad_angle += Math.PI/4;

        new_position.applyVelocity(ship.getVelocityVector());

        return new_position;
    }

}

