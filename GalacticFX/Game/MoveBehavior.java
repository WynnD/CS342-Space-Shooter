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
    private Vector2D velocity;
    private EnemySpaceship1 ship;

    public HorizontalMoveBehavior(EnemySpaceship1 ship)
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

