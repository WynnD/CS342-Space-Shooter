package Game;

/**
 * Created by noemi_000 on 4/5/2017.
 */


public abstract class MoveBehavior {

    protected Coordinate2D currentPosition;

    public MoveBehavior(Coordinate2D initialPosition)
    {
        this.currentPosition = initialPosition;
    }

    public Coordinate2D getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Coordinate2D currentPosition) {
        this.currentPosition = currentPosition;
    }

    public abstract Coordinate2D tryToMove();

}

class UserMoveBehavior extends MoveBehavior{

    private KeyListen keylistener;

    public UserMoveBehavior(Coordinate2D initalPosition, KeyListen keylistener)
    {
        super(initalPosition);
        this.keylistener = keylistener;
    }

    @Override
    public Coordinate2D tryToMove()
    {
        Coordinate2D newPosition = new Coordinate2D(currentPosition.getX(),currentPosition.getY());
        if (keylistener.getRightKeyPressed()) {
            newPosition.translateX(5);
        }
        if (keylistener.getLeftKeyPressed()){
            newPosition.translateX(-5);
        }
        if (keylistener.getDownKeyPressed()){
            newPosition.translateY(5);
        }
        if (keylistener.getUpKeyPressed()) {
            newPosition.translateY(-5);
        }

        return newPosition;
    }
}

class HorizontalMoveBehavior extends MoveBehavior{
    private boolean moveRight;
    private boolean moveLeft;
    private int position;

    public HorizontalMoveBehavior(Coordinate2D initialPosition)
    {
        super(initialPosition);
        this.moveRight = false;
        this.moveLeft = true;
        this.position = 0;
    }

    @Override
    public Coordinate2D tryToMove()
    {
        Coordinate2D newPosition = new Coordinate2D(currentPosition.getX(),currentPosition.getY());

        if(moveLeft){
            if(position > -100) {
                position -= 2;
                newPosition.translateX(-2);
            }
            else{
                moveRight = true;
                moveLeft = false;
            }
        }
        else if(moveRight){
            if(position < 100){
                position+=2;
                newPosition.translateX(2);
            }
            else{
                moveLeft = true;
                moveRight = false;
            }
        }

        return newPosition;
    }


}

