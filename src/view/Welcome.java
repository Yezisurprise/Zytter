package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Config;

public class Welcome extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2472081722335358885L;
	
	public Welcome() {
		
		this.setLayout(null);
		
		this.setTitle(Config.GlobalTitle);
		
		JLabel welcome2=new JLabel("Release Ver."+Config.version,JLabel.RIGHT);
		welcome2.setFont(new Font("等线",Font.BOLD,19));
		welcome2.setForeground(new Color(255,105,180));
		welcome2.setBounds(0,710,1350,60);
		this.add(welcome2);
		
		ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("img/welcome.jpg"));//要设置的背景图片
		JLabel imgLabel = new JLabel(img);//将背景图放在标签里。
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//将背景标签添加到LayeredPane面板里。
		imgLabel.setBounds(0,0,1366,768);//设置背景标签的位置
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false); //将内容面板设为透明。将LayeredPane面板中的背景显示出来。
		
        this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		
        this.setSize(1366,768);
        
		this.setUndecorated(true);
		
		this.setLocationRelativeTo(null);
		
		/**
		 * 3秒之后打开登录界面
		 */
		Timer timer=new Timer();//实例化Timer类 
		timer.schedule(new TimerTask(){ 
			public void run(){
				new Login().setVisible(true);
				this.cancel();
				close();
			}
		},3000);
	}
	
	private void close() {
		this.dispose();
	}
}
