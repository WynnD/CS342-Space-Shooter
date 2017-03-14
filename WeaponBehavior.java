
public class WeaponBehavior {
	
	private boolean isUserShip = false;
	private int weaponType;
	private boolean isShooting = false;
	private boolean spaceBarPressed = false;
	

	public WeaponBehavior(int shipType, int weapon) {
		if(shipType == 0){
			isUserShip = true;
			weaponType = weapon;
			setUserWeapon(weaponType);
		}
		else{
			weaponType = weapon;
			setEnemyWeapon(shipType, weaponType);
		}	
	}
	
	public boolean setUserWeapon(int newWeapon){
		if(isUserShip){
			weaponType = newWeapon;
			return true;
		}
		return false;
	}
	
	public boolean setEnemyWeapon(int ship,int newWeapon){
		if(!isUserShip){
			weaponType = newWeapon;
			return true;
		}
		return false;
	}
	
	public boolean userShoot(){
		if(spaceBarPressed && isUserShip){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean getisUserShip(){
		return isUserShip;
	}
	public boolean getWeaponType(){
		if(weaponType >= 0){
			return true;
		}
		return false;
	}
	public boolean getIsShooting(){
		return isShooting;
	}
	public boolean getSpaceBarPressed(){
		return spaceBarPressed;
	}
	public void setIsShooting(boolean setValue){
		isShooting = setValue;
	}
	public void setSpaceBarPressed(boolean setValue){
		spaceBarPressed = setValue;
	}
}
