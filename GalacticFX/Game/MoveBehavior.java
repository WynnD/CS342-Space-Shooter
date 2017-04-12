package Game;

/**
 * Created by Wynn on 4/12/2017.
 */
public interface MoveBehavior {

    Coordinate2D tryToMove();
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
    public Coordinate2D tryToMove()
    {
        if (keylistener.getRightKeyPressed()) {
            ship.position.translateX(5);
        }
        if (keylistener.getLeftKeyPressed()){
            ship.position.translateX(-5);
        }
        if (keylistener.getDownKeyPressed()){
            ship.position.translateY(5);
        }
        if (keylistener.getUpKeyPressed()) {
            ship.position.translateY(-5);
        }

        return ship.position;
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
    public Coordinate2D tryToMove()
    {
        if(moveLeft){
            if(position > -100) {
                position -= 2;
                ship.position.translateX(-2);
            }
            else{
                moveRight = true;
                moveLeft = false;
            }
        }
        else if(moveRight){
            if(position < 100){
                position+=2;
                ship.position.translateX(2);
            }
            else{
                moveLeft = true;
                moveRight = false;
            }
        }

        return ship.position;
    }

}

