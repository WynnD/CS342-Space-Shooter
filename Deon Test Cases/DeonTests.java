
public class DeonTests {

	MoveBehavior move;
	WeaponBehavior weapon;
	
	public DeonTests() {

	}

	
	public static void main(String[] args){
		DeonTests test = new DeonTests();
		test.runTests();
	}

	private void runTests(){
		testUserMove();
		testEnemyMove();
		testInvalidEnemyMove();
		testUserWeapon();
		testEnemyWeapon();
	}
	
	private void testUserMove(){
		//set user moveBehavior, indicated by parameter 0
		move = new MoveBehavior(0);
		move.setDownArrowKeyPressed(true);
		if(move.userMove()){
			System.out.println("User moved down, test passed");
		}
		else{
			System.out.println("testUserMove() failed");
		}
	}
	
	private void testEnemyMove(){
		//set enemy moveBehavior indicated by parameter 1 (meaning level 1)
		move = new MoveBehavior(1);
		move.setDownArrowKeyPressed(true);
		if(move.setEnemyMoveBehavior(1)){
			System.out.println("Enemy ship move behavior set, test passed");
		}
		else{
			System.out.println("testEnemyMove() failed");
		}
	}
	
	private void testInvalidEnemyMove(){
		move = new MoveBehavior(1);
		move.setDownArrowKeyPressed(true);
		if(!move.userMove()){
			System.out.println("Enemy ship did not move down upon down button press, test passed");
		}
		else{
			System.out.println("testInvalidEnemyMove() failed");
		}
	}
	
	private void testUserWeapon(){
		weapon = new WeaponBehavior(0,1);
		if(weapon.getWeaponType()){
			System.out.println("User weapon set, test passed");
		}
		else{
			System.out.println("testUserWeapon() failed");
		}
	}

	private void testEnemyWeapon(){
		weapon = new WeaponBehavior(1,1);
		if(weapon.getWeaponType()){
			System.out.println("Enemy weapon set, test passed");
		}
		else{
			System.out.println("testEnemyWeapon() failed");
		}
	}
}
