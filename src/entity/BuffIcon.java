package entity;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class BuffIcon extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1353190170646382389L;
	
	private int round;
	
	/**
	 * 预留给一些需要明确数值的技能用的
	 * 如果要需要使用 请和setDescribe()重新赋值
	 */
	private int v1=0,v2=0;
	
	private String name,superpose,describe;
	private Image image;
	
	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
		this.setToolTipText(getDescribe());
	}

	public int getV1() {
		return v1;
	}

	public void setV1(int v1) {
		this.v1 = v1;
	}

	public int getV2() {
		return v2;
	}

	public void setV2(int v2) {
		this.v2 = v2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public void setDescribe(String describe) {
		this.describe = describe;
		this.setToolTipText(getDescribe());
	}
	
	public void setSuperpose(String superpose) {
		this.superpose = superpose;
		this.setToolTipText(getDescribe());
	}
	
	public String getDescribe() {
		if(round == -1) {
			if(superpose != null) {
				return "<html>【"+name+"】"+superpose+"<br/>"+describe+"</html>";
			} else {
				return "<html>【"+name+"】<br/>"+describe+"</html>";
			}
		} else {
			return "<html>【"+name+"】（剩余："+round+"回合）<br/>"+describe+"</html>";
		}
	}

	public BuffIcon(String name,int round,Image image,String describe) {
		this.name = name;
		this.round = round;
	    this.image = image;
	    this.describe = describe;
	    this.setSize(32,32);
		this.setToolTipText(getDescribe());
	}  

	protected void paintComponent(Graphics g) {  
	    g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);  
	}
	
}
