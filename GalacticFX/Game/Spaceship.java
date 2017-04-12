package Game;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * Created by noemi_000 on 4/6/2017.
 */
public class Spaceship {

    protected MoveBehavior moveBehavior;
    protected ShootBehavior shootBehavior;
    protected String shipType;
    protected Sprite shipSprite;
    protected ArrayList<Projectile> projectiles;
    protected Coordinate2D position;

    public Spaceship(String shipType, Sprite shipSprite, Coordinate2D position)
    {
        this.shipType = shipType;
        this.shipSprite = shipSprite;
        this.position = position;
        projectiles = new ArrayList<>();

    }

    public void moveShip(Coordinate2D newPosition)
    {
        this.position = newPosition;
    }

    public Coordinate2D tryToMove(){
        return moveBehavior.tryToMove();
    }

    public Coordinate2D getPosition() {
        return position;
    }

    public void tryToShoot() {
        shootBehavior.update();
    }

    public void display(GraphicsContext gc) {

        gc.drawImage(shipSprite.getImageView().getImage(), position.getX(), position.getY(), shipSprite.getWidth(), shipSprite.getHeight());
    }

    public String getShipType(){ return shipType; }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public int getX() { return position.getX(); }

    public int getY() { return position.getY(); }

    public double getW() { return shipSprite.getWidth(); }

    public double getH() { return shipSprite.getHeight(); }

    public Sprite getShipSprite()
    {
        return shipSprite;
    }
}

class UserSpaceship extends Spaceship
{
    private KeyListen keyListener;

    public UserSpaceship(String shipType, Sprite shipSprite, KeyListen keyListener, Coordinate2D position)
    {
        super(shipType, shipSprite, position);

        this.keyListener = keyListener;
        this.moveBehavior = new UserMoveBehavior(this);
        this.shootBehavior = new ShootBehavior(this, keyListener, projectiles);
    }

    public KeyListen getKeyListener() {
        return keyListener;
    }

}

class EnemySpaceship1 extends Spaceship
{
    public EnemySpaceship1(String shipType, Sprite shipSprite, Coordinate2D position )
    {
        super(shipType, shipSprite, position);

        this.moveBehavior = new HorizontalMoveBehavior(this);
    }
}
