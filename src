import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class GalacticOverdrive extends JPanel implements KeyListener{
	
	 private int x,y, width, height;
	 
	 public GalacticOverdrive() {
		 width = 400;
		 height = 300;
		 x = width/2;
		 y = height/2;
	 }
	
	 public int getX()
	 {
		 return x;
	 }
	 
	 public int getY()
	 {
		 return y;
	 }
	 
	 public void setX(int valToAdd)
	 {
		 x += valToAdd;
	 }
	 
	 public void setY(int valToAdd)
	 {
		 y += valToAdd;
	 }
	
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped: "+e);
    }
    public void keyPressed(KeyEvent e) {
     if(e.getKeyCode() == KeyEvent.VK_SPACE){
	      Graphics gg = getGraphics();
		  gg.setColor(Color.black);
		  gg.fillRect(0, 0, width, height);
		  gg.setColor(Color.blue);
		  x = this.getX();
		  y = this.getY();
		  gg.fillRect(x, y, 30, 20);
     }
     else if(e.getKeyCode() == KeyEvent.VK_UP)
     {
    	 this.setY(-5);
     }
     else if(e.getKeyCode() == KeyEvent.VK_DOWN)
     {
    	 this.setY(5);
     }
     else if(e.getKeyCode() == KeyEvent.VK_LEFT)
     {
    	 this.setX(-5);
     }
     else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
     {
    	 this.setX(5);
     }
     

    }
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased: "+e);
    }
    
    public static void main(String[] args) {
        new MyFrame();
    }
}

class MyFrame extends JFrame {
	static Graphics gg = null;
	 
    public MyFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,300);

        GalacticOverdrive gamePanel = new GalacticOverdrive(); //create the game panel
        gamePanel.setBackground(Color.black);
        gamePanel.setBounds(0, 0, 400, 300);
        gamePanel.setSize(400,300);

        gamePanel.addKeyListener(gamePanel);
        gamePanel.setFocusable(true);

        add(gamePanel);
        setVisible(true);
    }

}
