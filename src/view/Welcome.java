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
		welcome2.setFont(new Font("����",Font.BOLD,19));
		welcome2.setForeground(new Color(255,105,180));
		welcome2.setBounds(0,710,1350,60);
		this.add(welcome2);
		
		ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("img/welcome.jpg"));//Ҫ���õı���ͼƬ
		JLabel imgLabel = new JLabel(img);//������ͼ���ڱ�ǩ�
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//��������ǩ��ӵ�LayeredPane����
		imgLabel.setBounds(0,0,1366,768);//���ñ�����ǩ��λ��
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false); //�����������Ϊ͸������LayeredPane����еı�����ʾ������
		
        this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		
        this.setSize(1366,768);
        
		this.setUndecorated(true);
		
		this.setLocationRelativeTo(null);
		
		/**
		 * 3��֮��򿪵�¼����
		 */
		Timer timer=new Timer();//ʵ����Timer�� 
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
