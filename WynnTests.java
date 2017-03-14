public class WynnTests {

    public static void main(String[] args) {
        WynnTests test = new WynnTests();
        test.runAll();
    }

    private void runAll() {
        boolean a,b,c,d,e;
        a = testEnemySpaceShipBuilt();
        b = testUserSpaceShipBuilt();
        c = testUserShipBuiltWithFactory();
        d = testEnemyShipBuiltWithFactory();

        testUserShipDisplays();
        testEnemyShipDisplays();

        if (a&&b&&c&&d) {
            System.out.println("All test cases passed!");

        } else {
            System.out.println("User cases failed!");
        }
    }

    private boolean testEnemySpaceShipBuilt() {
        EnemySpaceShip enemy = new EnemySpaceShip(0,0,0,0);
        return (enemy != null);
    }


    private boolean testUserSpaceShipBuilt() {
        UserSpaceShip user_ship = new UserSpaceShip(0,0,0,0);
        return (user_ship != null);
    }

    private boolean testUserShipBuiltWithFactory() {
        SpaceShipFactory fact = new SpaceShipFactory();
        SpaceShip user_ship = fact.makeShip("user",0,0,0,0);
        return (user_ship != null);
    }

    private boolean testEnemyShipBuiltWithFactory() {
        SpaceShipFactory fact = new SpaceShipFactory();
        SpaceShip enemy = fact.makeShip("enemy",0,0,0,0);
        return (enemy != null);
    }

    private void testUserShipDisplays() {
        UserSpaceShip user = new UserSpaceShip(0,0,0,0);
        user.display();
        return;
    }

    private void testEnemyShipDisplays() {
        EnemySpaceShip enemy = new EnemySpaceShip(0,0,0,0);
        enemy.display();
        return;
    }

}
