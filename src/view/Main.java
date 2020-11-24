package view;

import java.awt.Container;
import java.awt.Desktop;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entity.Server;
import entity.User;
import entity.Voice;
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

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3709488714422742419L;
	
	JLabel username,matching,matchingtimer;
	
	JButton match,cancel,heroes,personal,rankinglist,exit;
	
	Match m;
	
	Boolean ismatching = false;
	
	Server server;

	/**
	 * Main Menu
	 */
	public Main(User user,Server server) {
		
		this.server=server;
		
		this.setTitle(Config.GlobalTitle);
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
		match.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/ranking.png")));
		match.setFont(new Font("等线", Font.PLAIN, 43));
		match.setBounds(1149, 666, 207, 92);
		match.setContentAreaFilled(false);
		this.add(match);
		
		cancel = new JButton("");
		cancel.setVisible(false);
		cancel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/cancel.png")));
		cancel.setFont(new Font("等线", Font.PLAIN, 43));
		cancel.setContentAreaFilled(false);
		cancel.setBounds(1276, 672, 80, 80);
		this.add(cancel);
		
		exit = new JButton("");
		exit.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/quit.png")));
		exit.setForeground(new Color(176, 196, 222));
		exit.setFont(new Font("等线", Font.BOLD, 32));
		exit.setBounds(10, 695, 185, 63);
		exit.setContentAreaFilled(false);
		this.add(exit);
		
		heroes = new JButton("");
		heroes.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes.png")));
		heroes.setFont(new Font("等线", Font.PLAIN, 43));
		heroes.setContentAreaFilled(false);
		heroes.setBounds(1149, 462, 207, 92);
		this.add(heroes);
		
		JButton offical = new JButton("");
		offical.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/blog.png")));
		offical.setForeground(new Color(100, 149, 237));
		offical.setFont(new Font("等线", Font.PLAIN, 15));
		offical.setContentAreaFilled(false);
		offical.setBounds(205, 698, 93, 25);
		this.add(offical);
		
		personal = new JButton("");
		personal.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/id.png")));
		personal.setForeground(new Color(100, 149, 237));
		personal.setFont(new Font("等线", Font.PLAIN, 15));
		personal.setContentAreaFilled(false);
		personal.setBounds(205, 733, 93, 25);
		this.add(personal);
		
		rankinglist = new JButton("");
		rankinglist.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/rankinglist.png")));
		rankinglist.setFont(new Font("等线", Font.PLAIN, 43));
		rankinglist.setContentAreaFilled(false);
		rankinglist.setBounds(1149, 564, 207, 92);
		this.add(rankinglist);
		
		JLabel status = new JLabel(Config.seasonversion);
		status.setForeground(new Color(255, 20, 147));
		status.setFont(new Font("微软雅黑", Font.BOLD, 45));
		status.setHorizontalAlignment(SwingConstants.CENTER);
		status.setBounds(230, 160, 399, 69);
		this.add(status);
		
		username = new JLabel(user.getUsername());
		username.setBounds(271, 234, 313, 54);
		this.add(username);
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setForeground(new Color(233, 150, 122));
		username.setFont(new Font("微软雅黑", Font.BOLD, 40));
		
		JLabel playerrating = new JLabel(getRating(user.getRating()));
		playerrating.setToolTipText("\u6280\u672F\u8BC4\u4EF7");
		if(user.getRating()<=0) {
			playerrating.setText("0");
		}
		playerrating.setBounds(306, 298, 64, 45);
		this.add(playerrating);
		playerrating.setToolTipText("\u6280\u672F\u8BC4\u4EF7");
		playerrating.setForeground(new Color(240, 128, 128));
		playerrating.setFont(new Font("微软雅黑", Font.BOLD, 20));
		playerrating.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JLabel playerelo = new JLabel();
		if(user.getDjs()>0) playerelo.setText("0");
		else playerelo.setText(String.valueOf(user.getElo()));
		playerelo.setBounds(404, 298, 64, 45);
		this.add(playerelo);
		playerelo.setToolTipText("\u5929\u68AF\u79EF\u5206");
		playerelo.setForeground(new Color(240, 128, 128));
		playerelo.setHorizontalAlignment(SwingConstants.CENTER);
		playerelo.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		JLabel playerrank = new JLabel();
		if(user.getDjs()>0) playerrank.setText("未定级");
		else playerrank.setText(user.getRank());
		if(playerrank.getText().equals("未定级")) playerrank.setToolTipText("打赢5局天梯匹配来显示您的Rank");
		else playerrank.setToolTipText("\u6BB5\u4F4D\u8BC4\u4EF7");
		playerrank.setBounds(493, 298, 64, 45);
		this.add(playerrank);
		playerrank.setForeground(new Color(240, 128, 128));
		playerrank.setHorizontalAlignment(SwingConstants.CENTER);
		playerrank.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		JLabel rating = new JLabel("Rating");
		rating.setForeground(new Color(219, 112, 147));
		rating.setBounds(306, 331, 64, 45);
		this.add(rating);
		rating.setToolTipText("\u6280\u672F\u8BC4\u4EF7");
		rating.setHorizontalAlignment(SwingConstants.CENTER);
		rating.setFont(new Font("微软雅黑", Font.BOLD, 18));
		
		JLabel elo = new JLabel("Elo");
		elo.setForeground(new Color(219, 112, 147));
		elo.setBounds(404, 331, 64, 45);
		this.add(elo);
		elo.setToolTipText("\u5929\u68AF\u79EF\u5206");
		elo.setHorizontalAlignment(SwingConstants.CENTER);
		elo.setFont(new Font("微软雅黑", Font.BOLD, 18));
		
		JLabel rank = new JLabel("Rank");
		rank.setForeground(new Color(219, 112, 147));
		rank.setBounds(493, 331, 64, 45);
		this.add(rank);
		rank.setToolTipText("\u6BB5\u4F4D\u8BC4\u4EF7");
		rank.setHorizontalAlignment(SwingConstants.CENTER);
		rank.setFont(new Font("微软雅黑", Font.BOLD, 18));
		
		JLabel tip = Config.s.getAD();
		tip.setForeground(new Color(255, 20, 147));
		tip.setFont(new Font("微软雅黑", Font.BOLD, 30));
		tip.setHorizontalAlignment(SwingConstants.CENTER);
		tip.setBounds(715, 10, 641, 84);
		this.add(tip);
		
		matching = new JLabel("Matching Players...");
		matching.setVisible(false);
		matching.setForeground(new Color(240, 128, 128));
		matching.setFont(new Font("微软雅黑", Font.BOLD, 37));
		matching.setHorizontalAlignment(SwingConstants.RIGHT);
		matching.setBounds(855, 708, 420, 44);
		this.add(matching);
		
		matchingtimer = new JLabel("已匹配时长：0分0秒");
		matchingtimer.setVisible(false);
		matchingtimer.setForeground(new Color(240, 128, 128));
		matchingtimer.setHorizontalAlignment(SwingConstants.CENTER);
		matchingtimer.setFont(new Font("微软雅黑", Font.BOLD, 21));
		matchingtimer.setBounds(992, 674, 282, 37);
		this.add(matchingtimer);
		
		match.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ismatching = true;
				exit.setEnabled(false);
				matching.setText("正在连接至匹配服务器...");
				match.setVisible(false);
				matching.setVisible(true);
				match(user);
				new Thread() {
					@Override
					public void run() {
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						new Voice(getClass().getResourceAsStream("/bgm/startmatch.mp3")).start();
						matching.setText("正在寻找比赛...");
						matchingtimer.setVisible(true);
						cancel.setVisible(true);
					}
				}.start();
			}
			
		});
		
		heroes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HeroesList(Main.this).setVisible(true);
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ismatching = false;
				m.close();
				new Thread() {
					@Override
					public void run() {
						cancel.setVisible(false);
						matching.setVisible(false);
						matchingtimer.setVisible(false);
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						match.setVisible(true);
						exit.setEnabled(true);
					}
				}.start();
			}
		});
		
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
					Desktop.getDesktop().browse(new URI("https://www.wrss.org/zytter?username="+user.getUsername()+"&id="+user.getUid()));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		personal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				personal(user);
			}
		});
		
		rankinglist.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Season s = new Season(user,Main.this);
				s.setVisible(true);
				s.InitTable();
			}
		});
		
		this.setLocationRelativeTo(null);
		
		match.setEnabled(false);
		heroes.setEnabled(false);
		personal.setEnabled(false);
		rankinglist.setEnabled(false);
		
		new Init().start();
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				if(JOptionPane.showConfirmDialog(null, "确定退出游戏？", "登出学园激斗事件簿", JOptionPane.OK_CANCEL_OPTION)==0) {
					if(Config.s.ConnectServer(3,user,server)==1) {
						System.exit(0);
					} else {
						JOptionPane.showMessageDialog(null, "您已与服务器断开连接，若退出后出现意外情况，请立刻联系管理员。");
						System.exit(0);
					}
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
	
	void personal(User user) {
		this.setEnabled(false);
		new Personal(user,this).setVisible(true);
	}
	
	void quit(User user) {
		int n=JOptionPane.showConfirmDialog(null,"确认退出游戏？","登出学园激斗事件簿", JOptionPane.OK_CANCEL_OPTION);
		if(n==0) {
			if(Config.s.ConnectServer(3,user,server)==1) {
				System.exit(0);
			} else {
				JOptionPane.showMessageDialog(null, "您已与服务器断开连接，若退出后出现意外情况，请立刻联系管理员。");
				System.exit(0);
			}
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
	
	void match(User user) {
		m = new Match(user,this,server);
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
			heroes.setEnabled(true);
			personal.setEnabled(true);
			rankinglist.setEnabled(true);
			repaint();
		}
		
	}
	
}
