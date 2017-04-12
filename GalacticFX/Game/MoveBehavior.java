package Game;

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
    private Spaceship ship;

    public HorizontalMoveBehavior(Spaceship ship)
    {
        this.ship = ship;
        this.moveRight = false;
        this.moveLeft = true;
        this.position = 0;
    }

    @Override
    public Coordinate2D getNextPosition()
    {
        Coordinate2D new_position = new Coordinate2D(ship.getPosition());
        if(moveLeft){
            if(position > -100) {
                position -= 2;
                new_position.translateX(-2);
            }
            else{
                moveRight = true;
                moveLeft = false;
            }
        }
        else if(moveRight){
            if(position < 100){
                position+=2;
                new_position.translateX(2);
            }
            else{
                moveLeft = true;
                moveRight = false;
            }
        }

        return new_position;
    }

}

