
public class MoveBehavior {
	
	private boolean isUserShip = false;
	private boolean leftArrowKeyPressed = false;
	private boolean rightArrowKeyPressed = false;
	private boolean upArrowKeyPressed = false;
	private boolean downArrowKeyPressed = false;
	private boolean isMoving = false;

	public MoveBehavior(int moveType) {
		// TODO Auto-generated constructor stub
		if(moveType == 0){
			isUserShip = true;
		}
		else{
			setEnemyMoveBehavior(moveType);
		}

	}
	
	public boolean setEnemyMoveBehavior(int moveType){
		if(moveType == 1){
			return true; //set enemy for level 1
		}
		else if(moveType == 2){
			return true; //set enemy for level 2, etc
		}
		return false;
	}
	
	public boolean userMove(){
		if(isUserShip){
			if(leftArrowKeyPressed){
				isMoving = true;
				return true; //move ship left
			}
			else if(rightArrowKeyPressed){
				isMoving = true;
				return true; //move ship right
			}
			else if(upArrowKeyPressed){
				isMoving = true;
				return true; //move ship up
			}
			else if(downArrowKeyPressed){
				isMoving = true;
				return true; //move ship down
			}
			isMoving = false;
		}
		return false;
	}
	
	public boolean getIsUserShip(){
		return isUserShip;
	}
	
	public boolean getLeftArrowKeyPressed(){
		return leftArrowKeyPressed;
	}
	
	public boolean getRightArrowKeyPressed(){
		return rightArrowKeyPressed;
	}
	
	public boolean getUpArrowKeyPressed(){
		return upArrowKeyPressed;
	}
	
	public boolean getDownArrowKeyPressed(){
		return downArrowKeyPressed;
	}
	
	public boolean getIsMoving(){
		return isMoving;
	}
	
	public void setLeftArrowKeyPressed(boolean setValue){
		leftArrowKeyPressed = setValue;
	}
	
	public void setRightArrowKeyPressed(boolean setValue){
		rightArrowKeyPressed = setValue;
	}
	
	public void setUpArrowKeyPressed(boolean setValue){
		upArrowKeyPressed = setValue;
	}
	
	public void setDownArrowKeyPressed(boolean setValue){
		downArrowKeyPressed = setValue;
	}

}
