import java.util.TimerTask;

public class MyTimer extends TimerTask{
	
   private GalacticOverdrive gamePanel;
   
   public MyTimer(GalacticOverdrive gamePanel)
   {
	   this.gamePanel = gamePanel;
   }
	
	public void run() { 	
		
	    gamePanel.clearPanel();	 
	    gamePanel.updatePanel();    
	   }
}
