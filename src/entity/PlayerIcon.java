package entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class PlayerIcon extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2760102455324698808L;
	
	private Image image = null;  
	  
	public PlayerIcon(Image image) {  
	    this.image = image;  
	}  
	    
	protected void paintComponent(Graphics g) {  
	    g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
	}
}
