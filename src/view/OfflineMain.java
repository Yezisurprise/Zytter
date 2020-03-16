package view;

import java.awt.Container;
import java.awt.Desktop;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entity.User;
import util.Config;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.awt.Color;
import javax.swing.SwingConstants;

public class OfflineMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3709488714422742419L;
	
	JLabel username;
	
	JButton match,exit;
	
	Match m;
	
	Boolean ismatching = false;
	
	/**
	 * Main Menu
	 */
	public OfflineMain(User user) {
		
		this.setTitle("第三方社区下的 "+Config.GlobalTitle);
		this.setSize(1366,768);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setUndecorated(true);
		this.setLayout(null);
		
		ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("img/main.jpg"));
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, 1366, 768);
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false);
		
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		
		match = new JButton("");
		match.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/offline.png")));
		match.setFont(new Font("等线", Font.PLAIN, 43));
		match.setBounds(1149, 666, 207, 92);
		match.setContentAreaFilled(false);
		this.add(match);
		
		exit = new JButton("");
		exit.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/quit.png")));
		exit.setForeground(new Color(176, 196, 222));
		exit.setFont(new Font("等线", Font.BOLD, 32));
		exit.setBounds(10, 695, 185, 63);
		exit.setContentAreaFilled(false);
		this.add(exit);
		
		JButton offical = new JButton("");
		offical.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/blog.png")));
		offical.setForeground(new Color(100, 149, 237));
		offical.setFont(new Font("等线", Font.PLAIN, 15));
		offical.setContentAreaFilled(false);
		offical.setBounds(205, 733, 93, 25);
		this.add(offical);
		
		username = new JLabel(user.getUsername());
		username.setBounds(271, 245, 313, 54);
		this.add(username);
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setForeground(new Color(233, 150, 122));
		username.setFont(new Font("微软雅黑", Font.BOLD, 40));
		
		JLabel tip = new JLabel("欢迎来到Zytter的第三方社区");
		tip.setForeground(new Color(255, 20, 147));
		tip.setFont(new Font("微软雅黑", Font.BOLD, 30));
		tip.setHorizontalAlignment(SwingConstants.CENTER);
		tip.setBounds(715, 10, 641, 84);
		this.add(tip);
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				quit(user);
			}
		});
		
		offical.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.wrss.org/zytter.html"));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		match.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				match.setEnabled(false);
				Looking l = new Looking(user,OfflineMain.this);
				l.setVisible(true);
				l.InitList();
			}
		});
		
		this.setLocationRelativeTo(null);
		
		match.setEnabled(false);
		
		new Init().start();
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				if(JOptionPane.showConfirmDialog(null, "确定退出游戏？", "离开Zytter", JOptionPane.OK_CANCEL_OPTION)==0) {
					System.exit(0);
				}
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				
			}
		});
		
	}
	
	/**
	 * Some Function
	 */
	
	void quit(User user) {
		if(JOptionPane.showConfirmDialog(null, "确定退出游戏？", "离开Zytter", JOptionPane.OK_CANCEL_OPTION)==0) {
			System.exit(0);
		}
	}
	
	boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	String getRating(double rating) {
		return new DecimalFormat("#0.00").format(rating);
	}
	
	class Init extends Thread {

		@Override
		public void run() {
			super.run();
			JLabel tip=new JLabel("",JLabel.CENTER);
			add(tip);
			tip.setForeground(Color.white);
			tip.setHorizontalAlignment(SwingConstants.CENTER);
			tip.setBounds(0,0,1366,200);
			repaint();
			if(Config.Allheroes.size()!=12) {
				tip.setText("正在开始载入英雄数据......");
				Config.InitHeroes(tip);
			}
			if(Config.Allskills.size()!=36) {
				tip.setText("正在开始载入技能数据......");
				Config.InitSkills(tip);
			}
			if(Config.Allitems.size()!=26) {
				tip.setText("正在开始载入物品数据......");
				Config.InitItems(tip);
			}
			remove(tip);
			match.setEnabled(true);
			repaint();
		}
		
	}
	
}
