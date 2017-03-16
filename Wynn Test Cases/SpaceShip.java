public abstract class SpaceShip {
    //protected MoveBehavior moveBehavior;
    //protected ShootBehavior shootBehavior;
    protected int x,y,h,w;
    protected boolean enemy;

    public SpaceShip(int x, int y, int h, int w) {
        //moveBehavior = new userControlled();
        //shootBehavior = new shootOneBullet();
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    abstract public void display();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
