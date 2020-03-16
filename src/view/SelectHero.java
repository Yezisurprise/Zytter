package view;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;

import entity.BGM;
import entity.Hero;
import entity.PlayerIcon;
import entity.Server;
import entity.User;
import util.Config;

import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class SelectHero extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3584323166028702544L;

	ArrayList<JButton> select = new ArrayList<JButton>(); // 候选英雄的按钮
	
	ArrayList<Hero> selected = new ArrayList<Hero>(); // 候选英雄
	
	ArrayList<Hero> userbanned = new ArrayList<Hero>(); // 我方禁用
	
	ArrayList<Hero> enemybanned = new ArrayList<Hero>(); // 对方禁用
	
	ArrayList<Hero> userHeroes = new ArrayList<Hero>(); // 我方选用
	
	ArrayList<Hero> enemyHeroes = new ArrayList<Hero>(); // 对方选用
	
	User user,enemy;
	
	User p1,p2;
	
	int room;
	
	Server server;
	
	int remain = 30;
	
	int c = -1;
	
	JLabel bp,RestOfTime,now;
	
	int round = 0;
	
	Hero hero = null;
	
	JPanel p1ban1,p1ban2,p2ban1,p2ban2;
	JPanel p1pick1,p1pick2,p1pick3,p2pick1,p2pick2,p2pick3;
	JButton yy,lxs,ysn,ltj,zf,hyq,xyh,zkx,zxy,lm,sjj,w;
	
	boolean iscreater;
	
	Socket socket=null;
	
	OutputStream os = null;
	DataOutputStream dos = null;
	ObjectOutputStream oos = null;
	PrintWriter pw = null;
	
	InputStream is = null;
	DataInputStream dis = null;
	ObjectInputStream ois = null;
	InputStreamReader isr = null;
	BufferedReader br = null;
	
	Socket socket2=null;
	
	OutputStream os2 = null;
	DataOutputStream dos2 = null;
	ObjectOutputStream oos2 = null;
	PrintWriter pw2 = null;
	
	InputStream is2 = null;
	DataInputStream dis2 = null;
	ObjectInputStream ois2 = null;
	InputStreamReader isr2 = null;
	BufferedReader br2 = null;

	/**
	 * Create the frame.
	 */
	public SelectHero(User user,User enemy,int room,Server server) {
		
		this.user=user;
		this.enemy=enemy;
		this.room=room;
		this.server=server;
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setUndecorated(true);
		this.setSize(1366, 768);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setTitle(Config.GlobalTitle);
		
		ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("img/select.jpg"));
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, 1366, 768);
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false);
		
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		
		Config.bgm = new BGM();
		Config.bgm.setBGM(2);
		Config.bgm.start();
		
		p1ban1 = new JPanel();
		p1ban1.setBorder(new LineBorder(new Color(233, 150, 122), 3));
		p1ban1.setBounds(10, 70, 150, 150);
		this.add(p1ban1);
		p1ban1.setOpaque(false);
		
		p1ban2 = new JPanel();
		p1ban2.setBorder(new LineBorder(new Color(233, 150, 122), 3));
		p1ban2.setBounds(170, 70, 150, 150);
		this.add(p1ban2);
		p1ban2.setOpaque(false);
		
		p2ban1 = new JPanel();
		p2ban1.setBorder(new LineBorder(new Color(240, 128, 128), 3));
		p2ban1.setBounds(1206, 70, 150, 150);
		this.add(p2ban1);
		p2ban1.setOpaque(false);
		
		p2ban2 = new JPanel();
		p2ban2.setBorder(new LineBorder(new Color(240, 128, 128), 3));
		p2ban2.setBounds(1046, 70, 150, 150);
		this.add(p2ban2);
		p2ban2.setOpaque(false);
		
		JLabel p1ban = new JLabel("PLAYER 1 BAN");
		p1ban.setForeground(new Color(233, 150, 122));
		p1ban.setHorizontalAlignment(SwingConstants.LEFT);
		p1ban.setFont(new Font("微软雅黑", Font.BOLD, 32));
		p1ban.setBounds(10, 10, 370, 50);
		this.add(p1ban);
		
		JLabel p2ban = new JLabel("BAN PLAYER 2");
		p2ban.setForeground(new Color(240, 128, 128));
		p2ban.setHorizontalAlignment(SwingConstants.RIGHT);
		p2ban.setFont(new Font("微软雅黑", Font.BOLD, 32));
		p2ban.setBounds(986, 10, 370, 50);
		this.add(p2ban);
		
		RestOfTime = new JLabel("BAN & PICK");
		RestOfTime.setForeground(new Color(220, 20, 60));
		RestOfTime.setFont(new Font("等线", Font.BOLD, 60));
		RestOfTime.setHorizontalAlignment(SwingConstants.CENTER);
		RestOfTime.setBounds(390, 10, 586, 50);
		this.add(RestOfTime);
		
		bp = new JLabel("等待玩家连接...");
		bp.setHorizontalAlignment(SwingConstants.CENTER);
		bp.setForeground(new Color(95, 158, 160));
		bp.setFont(new Font("等线", Font.BOLD, 80));
		bp.setBounds(390, 88, 586, 95);
		this.add(bp);
		
		JPanel waitting = new JPanel();
		waitting.setBorder(new TitledBorder(new LineBorder(new Color(176, 196, 222), 5), "\u5019\u9009\u82F1\u96C4\u533A", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(176, 196, 222)));
		waitting.setBounds(245, 230, 878, 470);
		this.add(waitting);
		waitting.setLayout(new GridLayout(3, 4, 0, 0));
		waitting.setOpaque(false);
		
		yy = new JButton("");
		yy.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/selects/yy.jpg")));
		yy.setContentAreaFilled(false);
		yy.setToolTipText(Config.yy.getDescribe());
		waitting.add(yy);
		select.add(yy);
		
		lxs = new JButton("");
		lxs.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/selects/lxs.jpg")));
		lxs.setContentAreaFilled(false);
		lxs.setToolTipText(Config.lxs.getDescribe());
		waitting.add(lxs);
		select.add(lxs);
		
		ysn = new JButton("");
		ysn.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/selects/ysn.jpg")));
		ysn.setContentAreaFilled(false);
		ysn.setToolTipText(Config.ysn.getDescribe());
		waitting.add(ysn);
		select.add(ysn);
		
		ltj = new JButton("");
		ltj.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/selects/ltj.jpg")));
		ltj.setContentAreaFilled(false);
		ltj.setToolTipText(Config.ltj.getDescribe());
		waitting.add(ltj);
		select.add(ltj);
		
		zf = new JButton("");
		zf.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/selects/zf.jpg")));
		zf.setContentAreaFilled(false);
		zf.setToolTipText(Config.zf.getDescribe());
		waitting.add(zf);
		select.add(zf);
		
		hyq = new JButton("");
		hyq.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/selects/hyq.jpg")));
		hyq.setContentAreaFilled(false);
		hyq.setToolTipText(Config.hyq.getDescribe());
		waitting.add(hyq);
		select.add(hyq);
		
		xyh = new JButton("");
		xyh.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/selects/xyh.jpg")));
		xyh.setContentAreaFilled(false);
		xyh.setToolTipText(Config.xyh.getDescribe());
		waitting.add(xyh);
		select.add(xyh);
		
		zkx = new JButton("");
		zkx.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/selects/zkx.jpg")));
		zkx.setContentAreaFilled(false);
		zkx.setToolTipText(Config.zkx.getDescribe());
		waitting.add(zkx);
		select.add(zkx);
		
		zxy = new JButton("");
		zxy.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/selects/zxy.jpg")));
		zxy.setContentAreaFilled(false);
		zxy.setToolTipText(Config.zxy.getDescribe());
		waitting.add(zxy);
		select.add(zxy);
		
		lm = new JButton("");
		lm.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/selects/lm.jpg")));
		lm.setContentAreaFilled(false);
		lm.setToolTipText(Config.lm.getDescribe());
		waitting.add(lm);
		select.add(lm);
		
		sjj = new JButton("");
		sjj.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/selects/sjj.jpg")));
		sjj.setContentAreaFilled(false);
		sjj.setToolTipText(Config.sjj.getDescribe());
		waitting.add(sjj);
		select.add(sjj);
		
		w = new JButton("");
		w.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/selects/w.jpg")));
		w.setContentAreaFilled(false);
		w.setToolTipText(Config.w.getDescribe());
		waitting.add(w);
		select.add(w);
		
		p1pick1 = new JPanel();
		p1pick1.setBorder(new LineBorder(new Color(233, 150, 122), 3));
		p1pick1.setBounds(85, 230, 150, 150);
		this.add(p1pick1);
		p1pick1.setOpaque(false);
		
		p1pick2 = new JPanel();
		p1pick2.setBorder(new LineBorder(new Color(233, 150, 122), 3));
		p1pick2.setBounds(85, 390, 150, 150);
		this.add(p1pick2);
		p1pick2.setOpaque(false);
		
		p1pick3 = new JPanel();
		p1pick3.setBorder(new LineBorder(new Color(233, 150, 122), 3));
		p1pick3.setBounds(85, 550, 150, 150);
		this.add(p1pick3);
		p1pick3.setOpaque(false);
		
		p2pick1 = new JPanel();
		p2pick1.setBorder(new LineBorder(new Color(240, 128, 128), 3));
		p2pick1.setBounds(1133, 230, 150, 150);
		this.add(p2pick1);
		p2pick1.setOpaque(false);
		
		p2pick2 = new JPanel();
		p2pick2.setBorder(new LineBorder(new Color(240, 128, 128), 3));
		p2pick2.setBounds(1133, 390, 150, 150);
		this.add(p2pick2);
		p2pick2.setOpaque(false);
		
		p2pick3 = new JPanel();
		p2pick3.setBorder(new LineBorder(new Color(240, 128, 128), 3));
		p2pick3.setBounds(1133, 550, 150, 150);
		this.add(p2pick3);
		p2pick3.setOpaque(false);
		
		JLabel p11 = new JLabel("1");
		p11.setForeground(new Color(233, 150, 122));
		p11.setFont(new Font("微软雅黑", Font.BOLD, 72));
		p11.setHorizontalAlignment(SwingConstants.CENTER);
		p11.setBounds(21, 230, 54, 150);
		this.add(p11);
		
		JLabel p12 = new JLabel("2");
		p12.setForeground(new Color(233, 150, 122));
		p12.setHorizontalAlignment(SwingConstants.CENTER);
		p12.setFont(new Font("微软雅黑", Font.BOLD, 72));
		p12.setBounds(21, 390, 54, 150);
		this.add(p12);
		
		JLabel p13 = new JLabel("3");
		p13.setForeground(new Color(233, 150, 122));
		p13.setHorizontalAlignment(SwingConstants.CENTER);
		p13.setFont(new Font("微软雅黑", Font.BOLD, 72));
		p13.setBounds(21, 550, 54, 150);
		this.add(p13);
		
		JLabel p21 = new JLabel("1");
		p21.setHorizontalAlignment(SwingConstants.CENTER);
		p21.setForeground(new Color(240, 128, 128));
		p21.setFont(new Font("微软雅黑", Font.BOLD, 72));
		p21.setBounds(1293, 230, 54, 150);
		this.add(p21);
		
		JLabel p22 = new JLabel("2");
		p22.setHorizontalAlignment(SwingConstants.CENTER);
		p22.setForeground(new Color(240, 128, 128));
		p22.setFont(new Font("微软雅黑", Font.BOLD, 72));
		p22.setBounds(1293, 390, 54, 150);
		this.add(p22);
		
		JLabel p23 = new JLabel("3");
		p23.setHorizontalAlignment(SwingConstants.CENTER);
		p23.setForeground(new Color(240, 128, 128));
		p23.setFont(new Font("微软雅黑", Font.BOLD, 72));
		p23.setBounds(1293, 550, 54, 150);
		this.add(p23);
		
		JButton decide = new JButton("");
		decide.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/decideselect.png")));
		decide.setFont(new Font("等线", Font.PLAIN, 22));
		decide.setBounds(621, 710, 128, 50);
		this.add(decide);
		decide.setContentAreaFilled(false);
		
		now = new JLabel("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A");
		now.setForeground(new Color(240, 248, 255));
		now.setFont(new Font("等线", Font.PLAIN, 22));
		now.setBounds(257, 720, 333, 30);
		this.add(now);
		
		yy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<select.size();i++) {
					if(select.get(i).isEnabled()) {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A"+Config.yy.getName());
						hero = Config.yy;
						select.get(i).setEnabled(false);
					} else {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A");
						hero = null;
						select.get(i).setEnabled(true);
					}
					yy.setEnabled(true);
				}
			}
		});
		
		lxs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<select.size();i++) {
					if(select.get(i).isEnabled()) {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A"+Config.lxs.getName());
						hero = Config.lxs;
						select.get(i).setEnabled(false);
					} else {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A");
						hero = null;
						select.get(i).setEnabled(true);
					}
					lxs.setEnabled(true);
				}
			}
		});
		
		ysn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<select.size();i++) {
					if(select.get(i).isEnabled()) {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A"+Config.ysn.getName());
						hero = Config.ysn;
						select.get(i).setEnabled(false);
					} else {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A");
						hero = null;
						select.get(i).setEnabled(true);
					}
					ysn.setEnabled(true);
				}
			}
		});
		
		ltj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<select.size();i++) {
					if(select.get(i).isEnabled()) {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A"+Config.ltj.getName());
						hero = Config.ltj;
						select.get(i).setEnabled(false);
					} else {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A");
						hero = null;
						select.get(i).setEnabled(true);
					}
					ltj.setEnabled(true);
				}
			}
		});
		
		zf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<select.size();i++) {
					if(select.get(i).isEnabled()) {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A"+Config.zf.getName());
						hero = Config.zf;
						select.get(i).setEnabled(false);
					} else {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A");
						hero = null;
						select.get(i).setEnabled(true);
					}
					zf.setEnabled(true);
				}
			}
		});
		
		hyq.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<select.size();i++) {
					if(select.get(i).isEnabled()) {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A"+Config.hyq.getName());
						hero = Config.hyq;
						select.get(i).setEnabled(false);
					} else {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A");
						hero = null;
						select.get(i).setEnabled(true);
					}
					hyq.setEnabled(true);
				}
			}
		});
		
		xyh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<select.size();i++) {
					if(select.get(i).isEnabled()) {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A"+Config.xyh.getName());
						hero = Config.xyh;
						select.get(i).setEnabled(false);
					} else {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A");
						hero = null;
						select.get(i).setEnabled(true);
					}
					xyh.setEnabled(true);
				}
			}
		});
		
		zkx.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<select.size();i++) {
					if(select.get(i).isEnabled()) {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A"+Config.zkx.getName());
						hero = Config.zkx;
						select.get(i).setEnabled(false);
					} else {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A");
						hero = null;
						select.get(i).setEnabled(true);
					}
					zkx.setEnabled(true);
				}
			}
		});
		
		zxy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<select.size();i++) {
					if(select.get(i).isEnabled()) {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A"+Config.zxy.getName());
						hero = Config.zxy;
						select.get(i).setEnabled(false);
					} else {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A");
						hero = null;
						select.get(i).setEnabled(true);
					}
					zxy.setEnabled(true);
				}
			}
		});
		
		lm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<select.size();i++) {
					if(select.get(i).isEnabled()) {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A"+Config.lm.getName());
						hero = Config.lm;
						select.get(i).setEnabled(false);
					} else {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A");
						hero = null;
						select.get(i).setEnabled(true);
					}
					lm.setEnabled(true);
				}
			}
		});
		
		sjj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<select.size();i++) {
					if(select.get(i).isEnabled()) {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A"+Config.sjj.getName());
						hero = Config.sjj;
						select.get(i).setEnabled(false);
					} else {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A");
						hero = null;
						select.get(i).setEnabled(true);
					}
					sjj.setEnabled(true);
				}
			}
		});
		
		w.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<select.size();i++) {
					if(select.get(i).isEnabled()) {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A"+Config.w.getName());
						hero = Config.w;
						select.get(i).setEnabled(false);
					} else {
						now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A");
						hero = null;
						select.get(i).setEnabled(true);
					}
					w.setEnabled(true);
				}
			}
		});
		
		decide.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (round != 0) {
					if (hero != null) {
						round = 0;
						try {
							selected.add(hero);
							dos.writeInt(hero.getId());
							dos.flush();
						} catch (IOException ioe) {
							ioe.printStackTrace();
						}
					} else {
						if(JOptionPane.showConfirmDialog(null,"您还未选择英雄，是否放弃这次禁选？","未选择英雄",JOptionPane.YES_NO_OPTION)==0) {
							try {
								round = 0;
								dos.writeInt(0);
								dos.flush();
							} catch (IOException eio) {
								eio.printStackTrace();
							}
						}
					}
				}
			}
		});
		
		/**
		 * 载入玩家信息和数据
		 */
		
		iscreater=Config.s.IsRoomCreater(room, user, server);
		
		if(iscreater) {
			p1=user;
			p2=enemy;
		} else {
			p1=enemy;
			p2=user;
		}
		
		p1ban.setText(p1.getUsername()+" BAN");
		p2ban.setText("BAN "+p2.getUsername());

	}
	
	/**
	 * 方法初始化
	 */
	
	void HeroSelect() {
		int s;
		s=5;
		RestOfTime.setText(String.valueOf(s));
		while((s--)>0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RestOfTime.setText(String.valueOf(s));
		}
		// p1 b1
		BanHeroes(p1,p2,1);
		s=2;
		RestOfTime.setText(String.valueOf(s));
		while((s--)>0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RestOfTime.setText(String.valueOf(s));
		}
		// p2 b1
		BanHeroes(p2,p1,2);
		s=2;
		RestOfTime.setText(String.valueOf(s));
		while((s--)>0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RestOfTime.setText(String.valueOf(s));
		}
		// p1 b2
		BanHeroes(p1,p2,3);
		s=2;
		RestOfTime.setText(String.valueOf(s));
		while((s--)>0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RestOfTime.setText(String.valueOf(s));
		}
		// p2 b2
		BanHeroes(p2,p1,4);
		s=2;
		RestOfTime.setText(String.valueOf(s));
		while((s--)>0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RestOfTime.setText(String.valueOf(s));
		}
		bp.setText("禁用环节完成");
		s=2;
		RestOfTime.setText(String.valueOf(s));
		while((s--)>0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RestOfTime.setText(String.valueOf(s));
		}
		// p1 p1
		PickHeroes(p1,p2,1);
		s=2;
		RestOfTime.setText(String.valueOf(s));
		while((s--)>0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RestOfTime.setText(String.valueOf(s));
		}
		// p2 p1
		PickHeroes(p2,p1,2);
		s=2;
		RestOfTime.setText(String.valueOf(s));
		while((s--)>0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RestOfTime.setText(String.valueOf(s));
		}
		// p1 p2
		PickHeroes(p1,p2,3);
		s=2;
		RestOfTime.setText(String.valueOf(s));
		while((s--)>0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RestOfTime.setText(String.valueOf(s));
		}
		// p2 p2
		PickHeroes(p2,p1,4);
		s=2;
		RestOfTime.setText(String.valueOf(s));
		while((s--)>0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RestOfTime.setText(String.valueOf(s));
		}
		// p1 p3
		PickHeroes(p1,p2,5);
		s=2;
		RestOfTime.setText(String.valueOf(s));
		while((s--)>0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RestOfTime.setText(String.valueOf(s));
		}
		// p2 p3
		PickHeroes(p2,p1,6);
		s=2;
		RestOfTime.setText(String.valueOf(s));
		while((s--)>0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RestOfTime.setText(String.valueOf(s));
		}
		bp.setText("选用环节完成");
		s=2;
		RestOfTime.setText(String.valueOf(s));
		while((s--)>0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RestOfTime.setText(String.valueOf(s));
		}
		bp.setText("决策时间");
		s=22;
		RestOfTime.setText(String.valueOf(s));
		
		ArrayList<Hero> sure = new ArrayList<Hero>();//我方排序
		ArrayList<Hero> enemysure = new ArrayList<Hero>();//我方排序
		new Thread() {
			@Override
			public void run() {
				try {
					socket = new Socket(server.getIP(), Server.gameport);
					socket.setSoTimeout(30 * 1000);
					os = socket.getOutputStream();
					dos = new DataOutputStream(os);
					dos.writeInt(7);
					dos.flush();

					is = socket.getInputStream();
					dis = new DataInputStream(is);
					dis.readInt();

					oos = new ObjectOutputStream(os);
					oos.writeObject(user);
					oos.flush();
					oos.writeObject(enemy);
					oos.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
				new SortHero(SelectHero.this,dos,userHeroes,sure,1,userHeroes.size(),15).setVisible(true);
			}
		}.start();
		
		new Thread() {
			//接收排序
			@Override
			public void run() {
				try {
					socket2 = new Socket(server.getIP(), Server.gameport);
					
					socket2.setSoTimeout(30 * 1000);	
					
					os2 = socket2.getOutputStream();
					dos2 = new DataOutputStream(os2);
					dos2.writeInt(8);
					dos2.flush();
						
					is2 = socket2.getInputStream();
					dis2 = new DataInputStream(is2);
					dis2.readInt();
						
					oos2 = new ObjectOutputStream(os2);
					oos2.writeObject(user);
					oos2.flush();
					while(true) {
						int c = dis2.readInt();
						if(c==-2) {
							break;
						} else {
							enemysure.add(Config.Allheroes.get(c-1));
						}
					}
					dos2.writeInt(1);
					dos2.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		}.start();
		
		while((s--)>0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RestOfTime.setText(String.valueOf(s));
			if(s==3) {
				bp.setText("准备战斗！");
				for(int i=0;i<sure.size();i++) {
					if(user.equals(p1)) {
						getPickedImage(sure.get(i).getId(), 2*i+1);
					} else {
						getPickedImage(sure.get(i).getId(), 2*i+2);
					}
				}
				for(int i=0;i<enemysure.size();i++) {
					if(enemy.equals(p1)) {
						getPickedImage(enemysure.get(i).getId(), 2*i+1);
					} else {
						getPickedImage(enemysure.get(i).getId(), 2*i+2);
					}
				}
			}
		}
		Config.bgm.stopBGM();
		dispose();
		close();
		close2();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(sure.size()>0) {
			if(enemysure.size()>0) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Fight f = new Fight(user,enemy,userbanned,enemybanned,sure,enemysure,iscreater,room,server);
				f.setVisible(true);
				f.InitGUI();
			} else {
				JOptionPane.showMessageDialog(null, "由于对方没有选择任何英雄，本次对局不予继续！");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Config.bgm.stopBGM();
				Config.bgm = new BGM();
				Config.bgm.setBGM(0);
				Config.bgm.start();
				new Main(user,server).setVisible(true);
			}
		} else {
			JOptionPane.showMessageDialog(null, "由于你没有选择任何英雄，本次对局不予继续！");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Config.bgm.stopBGM();
			Config.bgm.setBGM(0);
			Config.bgm.start();
			new Main(user,server).setVisible(true);
		}
	}

	public boolean isselected(Hero hero) {
		for(int i=0;i<selected.size();i++) {
			if(hero.getId()==selected.get(i).getId()) {
				return true;
			}
		}
		return false;
	}
	
	public void BanHeroes(User p1,User p2,int r) {
		try {
			socket = new Socket(server.getIP(), Server.gameport);
			
			socket.setSoTimeout(350000);
			
			if(p1.equals(user)) {
				os = socket.getOutputStream();
				dos = new DataOutputStream(os);
				dos.writeInt(3);
				dos.flush();
				
				is = socket.getInputStream();
				dis = new DataInputStream(is);
				dis.readInt();
				
				dos.writeInt(r);
				dos.flush();
				
				remain=10;
				RestOfTime.setText(String.valueOf(remain));
				bp.setText("请禁用英雄");
				
				if(dis.readInt()==1) {
					oos = new ObjectOutputStream(os);
					oos.writeObject(p1);
					oos.flush();
					oos.writeObject(p2);
					oos.flush();
					round = 1;
					while(remain>0) {
						if(round==0) {
							bp.setText(p1.getUsername()+"完成禁用");
							if(hero!=null) {
								userbanned.add(hero);
								getBannedImage(hero.getId(),r);
							}
							setcanSelect();
							break;
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						remain--;
						RestOfTime.setText(String.valueOf(remain));
					}
					if(round!=0) {
						round = 0;
						bp.setText(p1.getUsername()+"放弃禁用");
						dos.writeInt(0);
						dos.flush();
					}
				}
			} else {
				os = socket.getOutputStream();
				dos = new DataOutputStream(os);
				dos.writeInt(1);
				dos.flush();
				oos = new ObjectOutputStream(os);
				oos.writeObject(p2);
				is = socket.getInputStream();
				dis = new DataInputStream(is);
				
				remain=10;
				RestOfTime.setText(String.valueOf(remain));
				bp.setText("等待"+p1.getUsername()+"禁用");
				if(dis.readInt()==1) {
					new WaitEnemy().start();
					while(remain>0) {
						if(c!=-1) {
							if(c==0) {
								bp.setText(p1.getUsername()+"放弃禁用");
								setcanSelect();
								break;
							} else {
								int k=c;
								bp.setText(p1.getUsername()+"完成禁用");
								enemybanned.add(Config.Allheroes.get(k-1));
								getBannedImage(k,r);
								setcanSelect();
								break;
							}
						} 
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						remain--;
						RestOfTime.setText(String.valueOf(remain));
					}
					while(c == -1) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if(c>=0) {
							if(c==0) {
								bp.setText(p1.getUsername()+"放弃禁用");
							} else {
								bp.setText(p1.getUsername()+"完成禁用");
							}
							setcanSelect();
							c=-1;
							break;
						}
					}
					if(c>=0) c=-1;
				}
			}
		} catch (IOException e) {
			bp.setText("您已断开连接");
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void PickHeroes(User p1,User p2,int r) {
		try {
			socket = new Socket(server.getIP(), Server.gameport);
			
			socket.setSoTimeout(350000);	
			
			if(p1.equals(user)) {
				os = socket.getOutputStream();
				dos = new DataOutputStream(os);
				dos.writeInt(4);
				dos.flush();
				
				is = socket.getInputStream();
				dis = new DataInputStream(is);
				dis.readInt();
				
				dos.writeInt(r);
				dos.flush();
				
				remain=20;
				RestOfTime.setText(String.valueOf(remain));
				bp.setText("请选用英雄");
				
				if(dis.readInt()==1) {
					oos = new ObjectOutputStream(os);
					oos.writeObject(p1);
					oos.flush();
					oos.writeObject(p2);
					oos.flush();
					round = 1;
					while(remain>0) {
						if(round==0) {
							bp.setText(p1.getUsername()+"完成选用");
							if(hero!=null) {
								userHeroes.add(hero);
								getPickedImage(hero.getId(),r);
							}
							setcanSelect();
							break;
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						remain--;
						RestOfTime.setText(String.valueOf(remain));
					}
					if(round!=0) {
						round = 0;
						bp.setText(p1.getUsername()+"放弃选用");
						dos.writeInt(0);
						dos.flush();
					}
				}
			} else {
				os = socket.getOutputStream();
				dos = new DataOutputStream(os);
				dos.writeInt(2);
				dos.flush();
				oos = new ObjectOutputStream(os);
				oos.writeObject(p2);
				is = socket.getInputStream();
				dis = new DataInputStream(is);
				
				remain=20;
				RestOfTime.setText(String.valueOf(remain));
				bp.setText("等待"+p1.getUsername()+"选用");
				if(dis.readInt()==1) {
					new WaitEnemy().start();
					while(remain>0) {
						if(c!=-1) {
							if(c==0) {
								bp.setText(p1.getUsername()+"放弃选用");
								setcanSelect();
								break;
							} else {
								int k=c;
								bp.setText(p1.getUsername()+"完成选用");
								enemyHeroes.add(Config.Allheroes.get(k-1));
								getPickedImage(k,r);
								setcanSelect();
								break;
							}
						} 
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						remain--;
						RestOfTime.setText(String.valueOf(remain));
					}
					while(c == -1) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if(c>=0) {
							if(c==0) {
								bp.setText(p1.getUsername()+"放弃选用");
							} else {
								bp.setText(p1.getUsername()+"完成选用");
							}
							setcanSelect();
							c=-1;
							break;
						}
					}
					if(c>=0) c=-1;
				}
			}
		} catch (IOException e) {
			bp.setText("您已断开连接");
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	void close() {
		try {
			socket.shutdownInput();
			socket.shutdownOutput();
			if(br!=null) br.close();
			if(ois!=null) ois.close();
			if(dis!=null) dis.close();
			if(isr!=null) isr.close();
			if(is!=null) is.close();
			if(pw!=null) pw.close();
			if(dos!=null) dos.close();
			if(pw!=null) pw.close();
			if(socket!=null) socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void close2() {
		try {
			socket2.shutdownInput();
			socket2.shutdownOutput();
			if(br2!=null) br2.close();
			if(ois2!=null) ois2.close();
			if(dis2!=null) dis2.close();
			if(isr2!=null) isr2.close();
			if(is2!=null) is2.close();
			if(pw2!=null) pw2.close();
			if(dos2!=null) dos2.close();
			if(pw2!=null) pw2.close();
			if(socket2!=null) socket2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class WaitEnemy extends Thread {

		@Override
		public void run() {
			try {
				is = socket.getInputStream();
				dis = new DataInputStream(is);
				c = dis.readInt();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	void setcanSelect() {
		now.setText("\u5F53\u524D\u9009\u62E9\u7684\u82F1\u96C4\u4E3A\uFF1A");
		hero = null;
		for(int i=0;i<select.size();i++) {
			if(!select.get(i).isEnabled()) {
				select.get(i).setEnabled(true);
			} 
		}
	}
	
	void getBannedImage(int id,int x) {
		switch(id) {
			case 0:{
				break;
			}
			case 1:{
				yy.setEnabled(false);
				select.remove(yy);
				if(x==1) {
					this.remove(p1ban1);
					p1ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p1ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban1.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban1.setBounds(10, 70, 150, 150);
					p1ban1.setOpaque(false);
					this.add(p1ban1);
				} else if(x==2) {
					this.remove(p2ban1);
					p2ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p2ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban1.setBounds(1206, 70, 150, 150);
					p2ban1.setOpaque(false);
					this.add(p2ban1);
				} else if(x==3) {
					this.remove(p1ban2);
					p1ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p1ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban2.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban2.setBounds(170, 70, 150, 150);
					p1ban2.setOpaque(false);
					this.add(p1ban2);
				} else if(x==4) {
					this.remove(p2ban2);
					p2ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p2ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban2.setBounds(1046, 70, 150, 150);
					p2ban2.setOpaque(false);
					this.add(p2ban2);
				}
				repaint();
				break;
			}
			case 2:{
				lxs.setEnabled(false);
				select.remove(lxs);
				if(x==1) {
					this.remove(p1ban1);
					p1ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p1ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban1.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban1.setBounds(10, 70, 150, 150);
					p1ban1.setOpaque(false);
					this.add(p1ban1);
				} else if(x==2) {
					this.remove(p2ban1);
					p2ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p2ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban1.setBounds(1206, 70, 150, 150);
					p2ban1.setOpaque(false);
					this.add(p2ban1);
				} else if(x==3) {
					this.remove(p1ban2);
					p1ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p1ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban2.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban2.setBounds(170, 70, 150, 150);
					p1ban2.setOpaque(false);
					this.add(p1ban2);
				} else if(x==4) {
					this.remove(p2ban2);
					p2ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p2ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban2.setBounds(1046, 70, 150, 150);
					p2ban2.setOpaque(false);
					this.add(p2ban2);
				}
				repaint();
				break;
			}
			case 3:{
				ysn.setEnabled(false);
				select.remove(ysn);
				if(x==1) {
					this.remove(p1ban1);
					p1ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p1ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban1.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban1.setBounds(10, 70, 150, 150);
					p1ban1.setOpaque(false);
					this.add(p1ban1);
				} else if(x==2) {
					this.remove(p2ban1);
					p2ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p2ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban1.setBounds(1206, 70, 150, 150);
					p2ban1.setOpaque(false);
					this.add(p2ban1);
				} else if(x==3) {
					this.remove(p1ban2);
					p1ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p1ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban2.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban2.setBounds(170, 70, 150, 150);
					p1ban2.setOpaque(false);
					this.add(p1ban2);
				} else if(x==4) {
					this.remove(p2ban2);
					p2ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p2ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban2.setBounds(1046, 70, 150, 150);
					p2ban2.setOpaque(false);
					this.add(p2ban2);
				}
				repaint();
				break;
			}
			case 4:{
				ltj.setEnabled(false);
				select.remove(ltj);
				if(x==1) {
					this.remove(p1ban1);
					p1ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p1ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban1.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban1.setBounds(10, 70, 150, 150);
					p1ban1.setOpaque(false);
					this.add(p1ban1);
				} else if(x==2) {
					this.remove(p2ban1);
					p2ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p2ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban1.setBounds(1206, 70, 150, 150);
					p2ban1.setOpaque(false);
					this.add(p2ban1);
				} else if(x==3) {
					this.remove(p1ban2);
					p1ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p1ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban2.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban2.setBounds(170, 70, 150, 150);
					p1ban2.setOpaque(false);
					this.add(p1ban2);
				} else if(x==4) {
					this.remove(p2ban2);
					p2ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p2ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban2.setBounds(1046, 70, 150, 150);
					p2ban2.setOpaque(false);
					this.add(p2ban2);
				}
				repaint();
				break;
			}
			case 5:{
				zf.setEnabled(false);
				select.remove(zf);
				if(x==1) {
					this.remove(p1ban1);
					p1ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p1ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban1.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban1.setBounds(10, 70, 150, 150);
					p1ban1.setOpaque(false);
					this.add(p1ban1);
				} else if(x==2) {
					this.remove(p2ban1);
					p2ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p2ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban1.setBounds(1206, 70, 150, 150);
					p2ban1.setOpaque(false);
					this.add(p2ban1);
				} else if(x==3) {
					this.remove(p1ban2);
					p1ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p1ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban2.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban2.setBounds(170, 70, 150, 150);
					p1ban2.setOpaque(false);
					this.add(p1ban2);
				} else if(x==4) {
					this.remove(p2ban2);
					p2ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p2ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban2.setBounds(1046, 70, 150, 150);
					p2ban2.setOpaque(false);
					this.add(p2ban2);
				}
				repaint();
				break;
			}
			case 6:{
				hyq.setEnabled(false);
				select.remove(hyq);
				if(x==1) {
					this.remove(p1ban1);
					p1ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p1ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban1.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban1.setBounds(10, 70, 150, 150);
					p1ban1.setOpaque(false);
					this.add(p1ban1);
				} else if(x==2) {
					this.remove(p2ban1);
					p2ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p2ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban1.setBounds(1206, 70, 150, 150);
					p2ban1.setOpaque(false);
					this.add(p2ban1);
				} else if(x==3) {
					this.remove(p1ban2);
					p1ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p1ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban2.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban2.setBounds(170, 70, 150, 150);
					p1ban2.setOpaque(false);
					this.add(p1ban2);
				} else if(x==4) {
					this.remove(p2ban2);
					p2ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p2ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban2.setBounds(1046, 70, 150, 150);
					p2ban2.setOpaque(false);
					this.add(p2ban2);
				}
				repaint();
				break;
			}
			case 7:{
				xyh.setEnabled(false);
				select.remove(xyh);
				if(x==1) {
					this.remove(p1ban1);
					p1ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p1ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban1.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban1.setBounds(10, 70, 150, 150);
					p1ban1.setOpaque(false);
					this.add(p1ban1);
				} else if(x==2) {
					this.remove(p2ban1);
					p2ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p2ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban1.setBounds(1206, 70, 150, 150);
					p2ban1.setOpaque(false);
					this.add(p2ban1);
				} else if(x==3) {
					this.remove(p1ban2);
					p1ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p1ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban2.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban2.setBounds(170, 70, 150, 150);
					p1ban2.setOpaque(false);
					this.add(p1ban2);
				} else if(x==4) {
					this.remove(p2ban2);
					p2ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p2ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban2.setBounds(1046, 70, 150, 150);
					p2ban2.setOpaque(false);
					this.add(p2ban2);
				}
				repaint();
				break;
			}
			case 8:{
				zkx.setEnabled(false);
				select.remove(zkx);
				if(x==1) {
					this.remove(p1ban1);
					p1ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p1ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban1.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban1.setBounds(10, 70, 150, 150);
					p1ban1.setOpaque(false);
					this.add(p1ban1);
				} else if(x==2) {
					this.remove(p2ban1);
					p2ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p2ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban1.setBounds(1206, 70, 150, 150);
					p2ban1.setOpaque(false);
					this.add(p2ban1);
				} else if(x==3) {
					this.remove(p1ban2);
					p1ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p1ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban2.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban2.setBounds(170, 70, 150, 150);
					p1ban2.setOpaque(false);
					this.add(p1ban2);
				} else if(x==4) {
					this.remove(p2ban2);
					p2ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p2ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban2.setBounds(1046, 70, 150, 150);
					p2ban2.setOpaque(false);
					this.add(p2ban2);
				}
				repaint();
				break;
			}
			case 9:{
				zxy.setEnabled(false);
				select.remove(zxy);
				if(x==1) {
					this.remove(p1ban1);
					p1ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p1ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban1.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban1.setBounds(10, 70, 150, 150);
					p1ban1.setOpaque(false);
					this.add(p1ban1);
				} else if(x==2) {
					this.remove(p2ban1);
					p2ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p2ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban1.setBounds(1206, 70, 150, 150);
					p2ban1.setOpaque(false);
					this.add(p2ban1);
				} else if(x==3) {
					this.remove(p1ban2);
					p1ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p1ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban2.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban2.setBounds(170, 70, 150, 150);
					p1ban2.setOpaque(false);
					this.add(p1ban2);
				} else if(x==4) {
					this.remove(p2ban2);
					p2ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p2ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban2.setBounds(1046, 70, 150, 150);
					p2ban2.setOpaque(false);
					this.add(p2ban2);
				}
				repaint();
				break;
			}
			case 10:{
				lm.setEnabled(false);
				select.remove(lm);
				if(x==1) {
					this.remove(p1ban1);
					p1ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p1ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban1.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban1.setBounds(10, 70, 150, 150);
					p1ban1.setOpaque(false);
					this.add(p1ban1);
				} else if(x==2) {
					this.remove(p2ban1);
					p2ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p2ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban1.setBounds(1206, 70, 150, 150);
					p2ban1.setOpaque(false);
					this.add(p2ban1);
				} else if(x==3) {
					this.remove(p1ban2);
					p1ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p1ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban2.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban2.setBounds(170, 70, 150, 150);
					p1ban2.setOpaque(false);
					this.add(p1ban2);
				} else if(x==4) {
					this.remove(p2ban2);
					p2ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p2ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban2.setBounds(1046, 70, 150, 150);
					p2ban2.setOpaque(false);
					this.add(p2ban2);
				}
				repaint();
				break;
			}
			case 11:{
				sjj.setEnabled(false);
				select.remove(sjj);
				if(x==1) {
					this.remove(p1ban1);
					p1ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p1ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban1.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban1.setBounds(10, 70, 150, 150);
					p1ban1.setOpaque(false);
					this.add(p1ban1);
				} else if(x==2) {
					this.remove(p2ban1);
					p2ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p2ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban1.setBounds(1206, 70, 150, 150);
					p2ban1.setOpaque(false);
					this.add(p2ban1);
				} else if(x==3) {
					this.remove(p1ban2);
					p1ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p1ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban2.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban2.setBounds(170, 70, 150, 150);
					p1ban2.setOpaque(false);
					this.add(p1ban2);
				} else if(x==4) {
					this.remove(p2ban2);
					p2ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p2ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban2.setBounds(1046, 70, 150, 150);
					p2ban2.setOpaque(false);
					this.add(p2ban2);
				}
				repaint();
				break;
			}
			case 12:{
				w.setEnabled(false);
				select.remove(w);
				if(x==1) {
					this.remove(p1ban1);
					p1ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p1ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban1.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban1.setBounds(10, 70, 150, 150);
					p1ban1.setOpaque(false);
					this.add(p1ban1);
				} else if(x==2) {
					this.remove(p2ban1);
					p2ban1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p2ban1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban1.setBounds(1206, 70, 150, 150);
					p2ban1.setOpaque(false);
					this.add(p2ban1);
				} else if(x==3) {
					this.remove(p1ban2);
					p1ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p1ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1ban2.setBorder(new LineBorder(Config.usercolor, 3));
					p1ban2.setBounds(170, 70, 150, 150);
					p1ban2.setOpaque(false);
					this.add(p1ban2);
				} else if(x==4) {
					this.remove(p2ban2);
					p2ban2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p2ban2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2ban2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2ban2.setBounds(1046, 70, 150, 150);
					p2ban2.setOpaque(false);
					this.add(p2ban2);
				}
				repaint();
				break;
			}
		}
	}
	
	void getPickedImage(int id,int x) {
		/**
		 * 1
		 */
		switch(id) {
			case 0:{
				break;
			}
			case 1:{
				yy.setEnabled(false);
				select.remove(yy);
				if(x==1) {
					this.remove(p1pick1);
					p1pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p1pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick1.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick1.setBounds(85, 230, 150, 150);
					p1pick1.setOpaque(false);
					this.add(p1pick1);
				} else if(x==2) {
					this.remove(p2pick1);
					p2pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p2pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick1.setBounds(1133, 230, 150, 150);
					p2pick1.setOpaque(false);
					this.add(p2pick1);
				} else if(x==3) {
					this.remove(p1pick2);
					p1pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p1pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick2.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick2.setBounds(85, 390, 150, 150);
					p1pick2.setOpaque(false);
					this.add(p1pick2);
				} else if(x==4) {
					this.remove(p2pick2);
					p2pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p2pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick2.setBounds(1133, 390, 150, 150);
					p2pick2.setOpaque(false);
					this.add(p2pick2);
				} else if(x==5) {
					this.remove(p1pick3);
					p1pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p1pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick3.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick3.setBounds(85, 550, 150, 150);
					p1pick3.setOpaque(false);
					this.add(p1pick3);
				} else if(x==6) {
					this.remove(p2pick3);
					p2pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p2pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick3.setBounds(1133, 550, 150, 150);
					p2pick3.setOpaque(false);
					this.add(p2pick3);
				}
				repaint();
				break;
			}
			case 2:{
				lxs.setEnabled(false);
				select.remove(lxs);
				if(x==1) {
					this.remove(p1pick1);
					p1pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p1pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick1.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick1.setBounds(85, 230, 150, 150);
					p1pick1.setOpaque(false);
					this.add(p1pick1);
				} else if(x==2) {
					this.remove(p2pick1);
					p2pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p2pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick1.setBounds(1133, 230, 150, 150);
					p2pick1.setOpaque(false);
					this.add(p2pick1);
				} else if(x==3) {
					this.remove(p1pick2);
					p1pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p1pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick2.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick2.setBounds(85, 390, 150, 150);
					p1pick2.setOpaque(false);
					this.add(p1pick2);
				} else if(x==4) {
					this.remove(p2pick2);
					p2pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p2pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick2.setBounds(1133, 390, 150, 150);
					p2pick2.setOpaque(false);
					this.add(p2pick2);
				} else if(x==5) {
					this.remove(p1pick3);
					p1pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p1pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick3.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick3.setBounds(85, 550, 150, 150);
					p1pick3.setOpaque(false);
					this.add(p1pick3);
				} else if(x==6) {
					this.remove(p2pick3);
					p2pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p2pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick3.setBounds(1133, 550, 150, 150);
					p2pick3.setOpaque(false);
					this.add(p2pick3);
				}
				repaint();
				break;
			}
			case 3:{
				ysn.setEnabled(false);
				select.remove(ysn);
				if(x==1) {
					this.remove(p1pick1);
					p1pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p1pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick1.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick1.setBounds(85, 230, 150, 150);
					p1pick1.setOpaque(false);
					this.add(p1pick1);
				} else if(x==2) {
					this.remove(p2pick1);
					p2pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p2pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick1.setBounds(1133, 230, 150, 150);
					p2pick1.setOpaque(false);
					this.add(p2pick1);
				} else if(x==3) {
					this.remove(p1pick2);
					p1pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p1pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick2.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick2.setBounds(85, 390, 150, 150);
					p1pick2.setOpaque(false);
					this.add(p1pick2);
				} else if(x==4) {
					this.remove(p2pick2);
					p2pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p2pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick2.setBounds(1133, 390, 150, 150);
					p2pick2.setOpaque(false);
					this.add(p2pick2);
				} else if(x==5) {
					this.remove(p1pick3);
					p1pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p1pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick3.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick3.setBounds(85, 550, 150, 150);
					p1pick3.setOpaque(false);
					this.add(p1pick3);
				} else if(x==6) {
					this.remove(p2pick3);
					p2pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p2pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick3.setBounds(1133, 550, 150, 150);
					p2pick3.setOpaque(false);
					this.add(p2pick3);
				}
				repaint();
				break;
			}
			case 4:{
				ltj.setEnabled(false);
				select.remove(ltj);
				if(x==1) {
					this.remove(p1pick1);
					p1pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p1pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick1.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick1.setBounds(85, 230, 150, 150);
					p1pick1.setOpaque(false);
					this.add(p1pick1);
				} else if(x==2) {
					this.remove(p2pick1);
					p2pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p2pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick1.setBounds(1133, 230, 150, 150);
					p2pick1.setOpaque(false);
					this.add(p2pick1);
				} else if(x==3) {
					this.remove(p1pick2);
					p1pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p1pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick2.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick2.setBounds(85, 390, 150, 150);
					p1pick2.setOpaque(false);
					this.add(p1pick2);
				} else if(x==4) {
					this.remove(p2pick2);
					p2pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p2pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick2.setBounds(1133, 390, 150, 150);
					p2pick2.setOpaque(false);
					this.add(p2pick2);
				} else if(x==5) {
					this.remove(p1pick3);
					p1pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p1pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick3.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick3.setBounds(85, 550, 150, 150);
					p1pick3.setOpaque(false);
					this.add(p1pick3);
				} else if(x==6) {
					this.remove(p2pick3);
					p2pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p2pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick3.setBounds(1133, 550, 150, 150);
					p2pick3.setOpaque(false);
					this.add(p2pick3);
				}
				repaint();
				break;
			}
			case 5:{
				zf.setEnabled(false);
				select.remove(zf);
				if(x==1) {
					this.remove(p1pick1);
					p1pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p1pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick1.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick1.setBounds(85, 230, 150, 150);
					p1pick1.setOpaque(false);
					this.add(p1pick1);
				} else if(x==2) {
					this.remove(p2pick1);
					p2pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p2pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick1.setBounds(1133, 230, 150, 150);
					p2pick1.setOpaque(false);
					this.add(p2pick1);
				} else if(x==3) {
					this.remove(p1pick2);
					p1pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p1pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick2.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick2.setBounds(85, 390, 150, 150);
					p1pick2.setOpaque(false);
					this.add(p1pick2);
				} else if(x==4) {
					this.remove(p2pick2);
					p2pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p2pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick2.setBounds(1133, 390, 150, 150);
					p2pick2.setOpaque(false);
					this.add(p2pick2);
				} else if(x==5) {
					this.remove(p1pick3);
					p1pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p1pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick3.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick3.setBounds(85, 550, 150, 150);
					p1pick3.setOpaque(false);
					this.add(p1pick3);
				} else if(x==6) {
					this.remove(p2pick3);
					p2pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p2pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick3.setBounds(1133, 550, 150, 150);
					p2pick3.setOpaque(false);
					this.add(p2pick3);
				}
				repaint();
				break;
			}
			case 6:{
				hyq.setEnabled(false);
				select.remove(hyq);
				if(x==1) {
					this.remove(p1pick1);
					p1pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p1pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick1.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick1.setBounds(85, 230, 150, 150);
					p1pick1.setOpaque(false);
					this.add(p1pick1);
				} else if(x==2) {
					this.remove(p2pick1);
					p2pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p2pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick1.setBounds(1133, 230, 150, 150);
					p2pick1.setOpaque(false);
					this.add(p2pick1);
				} else if(x==3) {
					this.remove(p1pick2);
					p1pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p1pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick2.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick2.setBounds(85, 390, 150, 150);
					p1pick2.setOpaque(false);
					this.add(p1pick2);
				} else if(x==4) {
					this.remove(p2pick2);
					p2pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p2pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick2.setBounds(1133, 390, 150, 150);
					p2pick2.setOpaque(false);
					this.add(p2pick2);
				} else if(x==5) {
					this.remove(p1pick3);
					p1pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p1pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick3.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick3.setBounds(85, 550, 150, 150);
					p1pick3.setOpaque(false);
					this.add(p1pick3);
				} else if(x==6) {
					this.remove(p2pick3);
					p2pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p2pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick3.setBounds(1133, 550, 150, 150);
					p2pick3.setOpaque(false);
					this.add(p2pick3);
				}
				repaint();
				break;
			}
			case 7:{
				xyh.setEnabled(false);
				select.remove(xyh);
				if(x==1) {
					this.remove(p1pick1);
					p1pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p1pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick1.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick1.setBounds(85, 230, 150, 150);
					p1pick1.setOpaque(false);
					this.add(p1pick1);
				} else if(x==2) {
					this.remove(p2pick1);
					p2pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p2pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick1.setBounds(1133, 230, 150, 150);
					p2pick1.setOpaque(false);
					this.add(p2pick1);
				} else if(x==3) {
					this.remove(p1pick2);
					p1pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p1pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick2.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick2.setBounds(85, 390, 150, 150);
					p1pick2.setOpaque(false);
					this.add(p1pick2);
				} else if(x==4) {
					this.remove(p2pick2);
					p2pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p2pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick2.setBounds(1133, 390, 150, 150);
					p2pick2.setOpaque(false);
					this.add(p2pick2);
				} else if(x==5) {
					this.remove(p1pick3);
					p1pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p1pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick3.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick3.setBounds(85, 550, 150, 150);
					p1pick3.setOpaque(false);
					this.add(p1pick3);
				} else if(x==6) {
					this.remove(p2pick3);
					p2pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p2pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick3.setBounds(1133, 550, 150, 150);
					p2pick3.setOpaque(false);
					this.add(p2pick3);
				}
				repaint();
				break;
			}
			case 8:{
				zkx.setEnabled(false);
				select.remove(zkx);
				if(x==1) {
					this.remove(p1pick1);
					p1pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p1pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick1.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick1.setBounds(85, 230, 150, 150);
					p1pick1.setOpaque(false);
					this.add(p1pick1);
				} else if(x==2) {
					this.remove(p2pick1);
					p2pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p2pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick1.setBounds(1133, 230, 150, 150);
					p2pick1.setOpaque(false);
					this.add(p2pick1);
				} else if(x==3) {
					this.remove(p1pick2);
					p1pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p1pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick2.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick2.setBounds(85, 390, 150, 150);
					p1pick2.setOpaque(false);
					this.add(p1pick2);
				} else if(x==4) {
					this.remove(p2pick2);
					p2pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p2pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick2.setBounds(1133, 390, 150, 150);
					p2pick2.setOpaque(false);
					this.add(p2pick2);
				} else if(x==5) {
					this.remove(p1pick3);
					p1pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p1pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick3.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick3.setBounds(85, 550, 150, 150);
					p1pick3.setOpaque(false);
					this.add(p1pick3);
				} else if(x==6) {
					this.remove(p2pick3);
					p2pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p2pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick3.setBounds(1133, 550, 150, 150);
					p2pick3.setOpaque(false);
					this.add(p2pick3);
				}
				repaint();
				break;
			}
			case 9:{
				zxy.setEnabled(false);
				select.remove(zxy);
				if(x==1) {
					this.remove(p1pick1);
					p1pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p1pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick1.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick1.setBounds(85, 230, 150, 150);
					p1pick1.setOpaque(false);
					this.add(p1pick1);
				} else if(x==2) {
					this.remove(p2pick1);
					p2pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p2pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick1.setBounds(1133, 230, 150, 150);
					p2pick1.setOpaque(false);
					this.add(p2pick1);
				} else if(x==3) {
					this.remove(p1pick2);
					p1pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p1pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick2.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick2.setBounds(85, 390, 150, 150);
					p1pick2.setOpaque(false);
					this.add(p1pick2);
				} else if(x==4) {
					this.remove(p2pick2);
					p2pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p2pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick2.setBounds(1133, 390, 150, 150);
					p2pick2.setOpaque(false);
					this.add(p2pick2);
				} else if(x==5) {
					this.remove(p1pick3);
					p1pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p1pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick3.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick3.setBounds(85, 550, 150, 150);
					p1pick3.setOpaque(false);
					this.add(p1pick3);
				} else if(x==6) {
					this.remove(p2pick3);
					p2pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p2pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick3.setBounds(1133, 550, 150, 150);
					p2pick3.setOpaque(false);
					this.add(p2pick3);
				}
				repaint();
				break;
			}
			case 10:{
				lm.setEnabled(false);
				select.remove(lm);
				if(x==1) {
					this.remove(p1pick1);
					p1pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p1pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick1.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick1.setBounds(85, 230, 150, 150);
					p1pick1.setOpaque(false);
					this.add(p1pick1);
				} else if(x==2) {
					this.remove(p2pick1);
					p2pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p2pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick1.setBounds(1133, 230, 150, 150);
					p2pick1.setOpaque(false);
					this.add(p2pick1);
				} else if(x==3) {
					this.remove(p1pick2);
					p1pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p1pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick2.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick2.setBounds(85, 390, 150, 150);
					p1pick2.setOpaque(false);
					this.add(p1pick2);
				} else if(x==4) {
					this.remove(p2pick2);
					p2pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p2pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick2.setBounds(1133, 390, 150, 150);
					p2pick2.setOpaque(false);
					this.add(p2pick2);
				} else if(x==5) {
					this.remove(p1pick3);
					p1pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p1pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick3.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick3.setBounds(85, 550, 150, 150);
					p1pick3.setOpaque(false);
					this.add(p1pick3);
				} else if(x==6) {
					this.remove(p2pick3);
					p2pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p2pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick3.setBounds(1133, 550, 150, 150);
					p2pick3.setOpaque(false);
					this.add(p2pick3);
				}
				repaint();
				break;
			}
			case 11:{
				sjj.setEnabled(false);
				select.remove(sjj);
				if(x==1) {
					this.remove(p1pick1);
					p1pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p1pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick1.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick1.setBounds(85, 230, 150, 150);
					p1pick1.setOpaque(false);
					this.add(p1pick1);
				} else if(x==2) {
					this.remove(p2pick1);
					p2pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p2pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick1.setBounds(1133, 230, 150, 150);
					p2pick1.setOpaque(false);
					this.add(p2pick1);
				} else if(x==3) {
					this.remove(p1pick2);
					p1pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p1pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick2.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick2.setBounds(85, 390, 150, 150);
					p1pick2.setOpaque(false);
					this.add(p1pick2);
				} else if(x==4) {
					this.remove(p2pick2);
					p2pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p2pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick2.setBounds(1133, 390, 150, 150);
					p2pick2.setOpaque(false);
					this.add(p2pick2);
				} else if(x==5) {
					this.remove(p1pick3);
					p1pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p1pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick3.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick3.setBounds(85, 550, 150, 150);
					p1pick3.setOpaque(false);
					this.add(p1pick3);
				} else if(x==6) {
					this.remove(p2pick3);
					p2pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p2pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick3.setBounds(1133, 550, 150, 150);
					p2pick3.setOpaque(false);
					this.add(p2pick3);
				}
				repaint();
				break;
			}
			case 12:{
				w.setEnabled(false);
				select.remove(w);
				if(x==1) {
					this.remove(p1pick1);
					p1pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p1pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick1.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick1.setBounds(85, 230, 150, 150);
					p1pick1.setOpaque(false);
					this.add(p1pick1);
				} else if(x==2) {
					this.remove(p2pick1);
					p2pick1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p2pick1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick1.setBounds(1133, 230, 150, 150);
					p2pick1.setOpaque(false);
					this.add(p2pick1);
				} else if(x==3) {
					this.remove(p1pick2);
					p1pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p1pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick2.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick2.setBounds(85, 390, 150, 150);
					p1pick2.setOpaque(false);
					this.add(p1pick2);
				} else if(x==4) {
					this.remove(p2pick2);
					p2pick2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p2pick2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick2.setBounds(1133, 390, 150, 150);
					p2pick2.setOpaque(false);
					this.add(p2pick2);
				} else if(x==5) {
					this.remove(p1pick3);
					p1pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p1pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1pick3.setBorder(new LineBorder(Config.usercolor, 3));
					p1pick3.setBounds(85, 550, 150, 150);
					p1pick3.setOpaque(false);
					this.add(p1pick3);
				} else if(x==6) {
					this.remove(p2pick3);
					p2pick3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p2pick3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2pick3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2pick3.setBounds(1133, 550, 150, 150);
					p2pick3.setOpaque(false);
					this.add(p2pick3);
				}
				repaint();
				break;
			}
		}
	}
	
}
