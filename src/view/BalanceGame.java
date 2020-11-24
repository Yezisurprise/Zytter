package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import entity.BGM;
import entity.Hero;
import entity.PlayerIcon;
import entity.User;
import util.Config;

public class BalanceGame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2929342851569586735L;
	
	JPanel spec;

	JLabel p1,p2,vs,kills,deaths,adr,rating,elo,p1k,p1d,p1adr,p1r,p1e,p2k,p2d,p2adr,p2r,p2e;
	JLabel p1p,p1b,p2p,p2b;
	JPanel p1p1,p1p2,p1p3,p2p1,p2p2,p2p3,p1b1,p1b2,p2b1,p2b2;
	
	JLabel game,userelo,userrating,userrank,gametitle;
	
	User user;
	
	public BalanceGame(boolean winlose,User user,User enemy,int kill,int death,double p1rating,double p2rating,
			double p1a,double p2a,int p1elo,int p2elo,double ratingadd,int eloadd,Fight f,
			ArrayList<Hero> userbanned,ArrayList<Hero> enemybanned,ArrayList<Hero> userpicked,ArrayList<Hero> enemypicked) {
		
		this.user = user;
		
		this.setTitle("Zytter ±ÈÈü½áËã");
		this.setUndecorated(true);
		this.setBackground(new Color(255,255,255,220));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1030, 500);
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		JButton back = new JButton("");
		back.setContentAreaFilled(false);
		back.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/return.png")));
		back.setBounds(768, 390, 256, 100);
		this.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				f.dispose();
				Config.bgm.stopBGM();
				new Thread() {
					@Override
					public void run() {
						Config.s.DeleteRoom(user);
					}
				}.start();
				new Main(Config.s.queryUser(user.getUid()),f.server).setVisible(true);
				Config.bgm = new BGM();
				Config.bgm.setBGM(0);
				Config.bgm.start();
			}
		});
		
		spec = new JPanel();
		spec.setLayout(null);
		spec.setOpaque(false);
		spec.setBounds(10, 110, 748, 380);
		this.add(spec);
		
		p1 = new JLabel("Íæ¼Ò1");
		p1.setForeground(new Color(233, 150, 122));
		p1.setHorizontalAlignment(SwingConstants.CENTER);
		p1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 42));
		p1.setBounds(10, 10, 307, 62);
		spec.add(p1);
		
		p2 = new JLabel("Íæ¼Ò2");
		p2.setForeground(new Color(240, 128, 128));
		p2.setHorizontalAlignment(SwingConstants.CENTER);
		p2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 42));
		p2.setBounds(439, 10, 299, 62);
		spec.add(p2);
		
		vs = new JLabel("VS.");
		vs.setForeground(new Color(95, 158, 160));
		vs.setHorizontalAlignment(SwingConstants.CENTER);
		vs.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 45));
		vs.setBounds(327, 10, 102, 62);
		spec.add(vs);
		
		kills = new JLabel("»÷É±");
		kills.setForeground(new Color(95, 158, 160));
		kills.setHorizontalAlignment(SwingConstants.CENTER);
		kills.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		kills.setBounds(327, 110, 102, 42);
		spec.add(kills);
		
		deaths = new JLabel("ËÀÍö");
		deaths.setForeground(new Color(95, 158, 160));
		deaths.setHorizontalAlignment(SwingConstants.CENTER);
		deaths.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		deaths.setBounds(327, 162, 102, 42);
		spec.add(deaths);
		
		adr = new JLabel("Æ½¾ùÉËº¦");
		adr.setForeground(new Color(95, 158, 160));
		adr.setHorizontalAlignment(SwingConstants.CENTER);
		adr.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		adr.setBounds(327, 214, 102, 42);
		spec.add(adr);
		
		rating = new JLabel("Rating");
		rating.setForeground(new Color(95, 158, 160));
		rating.setHorizontalAlignment(SwingConstants.CENTER);
		rating.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		rating.setBounds(327, 266, 102, 42);
		spec.add(rating);
		
		elo = new JLabel("Elo(+/-)");
		elo.setForeground(new Color(95, 158, 160));
		elo.setHorizontalAlignment(SwingConstants.CENTER);
		elo.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		elo.setBounds(327, 318, 102, 42);
		spec.add(elo);
		
		p1k = new JLabel("Íæ¼Ò1»÷É±");
		p1k.setHorizontalAlignment(SwingConstants.CENTER);
		p1k.setForeground(new Color(95, 158, 160));
		p1k.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		p1k.setBounds(190, 110, 119, 42);
		spec.add(p1k);
		
		p1d = new JLabel("Íæ¼Ò1ËÀÍö");
		p1d.setHorizontalAlignment(SwingConstants.CENTER);
		p1d.setForeground(new Color(95, 158, 160));
		p1d.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		p1d.setBounds(190, 162, 119, 42);
		spec.add(p1d);
		
		p1adr = new JLabel("Íæ¼Ò1adr");
		p1adr.setHorizontalAlignment(SwingConstants.CENTER);
		p1adr.setForeground(new Color(95, 158, 160));
		p1adr.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		p1adr.setBounds(190, 214, 119, 42);
		spec.add(p1adr);
		
		p1r = new JLabel("Íæ¼Ò1¼¼Êõ·Ö");
		p1r.setHorizontalAlignment(SwingConstants.CENTER);
		p1r.setForeground(new Color(95, 158, 160));
		p1r.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		p1r.setBounds(190, 266, 119, 42);
		spec.add(p1r);
		
		p1e = new JLabel("Íæ¼Ò1ÌìÌÝ·Ö");
		p1e.setHorizontalAlignment(SwingConstants.CENTER);
		p1e.setForeground(new Color(95, 158, 160));
		p1e.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		p1e.setBounds(190, 318, 119, 42);
		spec.add(p1e);
		
		p2k = new JLabel("Íæ¼Ò2»÷É±");
		p2k.setHorizontalAlignment(SwingConstants.CENTER);
		p2k.setForeground(new Color(95, 158, 160));
		p2k.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		p2k.setBounds(439, 110, 119, 42);
		spec.add(p2k);
		
		p2d = new JLabel("Íæ¼Ò2ËÀÍö");
		p2d.setHorizontalAlignment(SwingConstants.CENTER);
		p2d.setForeground(new Color(95, 158, 160));
		p2d.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		p2d.setBounds(439, 162, 119, 42);
		spec.add(p2d);
		
		p2adr = new JLabel("Íæ¼Ò2adr");
		p2adr.setHorizontalAlignment(SwingConstants.CENTER);
		p2adr.setForeground(new Color(95, 158, 160));
		p2adr.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		p2adr.setBounds(439, 214, 119, 42);
		spec.add(p2adr);
		
		p2r = new JLabel("Íæ¼Ò2¼¼Êõ·Ö");
		p2r.setHorizontalAlignment(SwingConstants.CENTER);
		p2r.setForeground(new Color(95, 158, 160));
		p2r.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		p2r.setBounds(439, 266, 119, 42);
		spec.add(p2r);
		
		p2e = new JLabel("Íæ¼Ò2ÌìÌÝ·Ö");
		p2e.setHorizontalAlignment(SwingConstants.CENTER);
		p2e.setForeground(new Color(95, 158, 160));
		p2e.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		p2e.setBounds(439, 318, 119, 42);
		spec.add(p2e);
		
		p1p = new JLabel("PICK");
		p1p.setForeground(new Color(233, 150, 122));
		p1p.setHorizontalAlignment(SwingConstants.CENTER);
		p1p.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		p1p.setBounds(10, 77, 80, 25);
		spec.add(p1p);
		
		p1b = new JLabel("BAN");
		p1b.setHorizontalAlignment(SwingConstants.CENTER);
		p1b.setForeground(new Color(233, 150, 122));
		p1b.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		p1b.setBounds(100, 165, 80, 25);
		spec.add(p1b);
		
		p2p = new JLabel("PICK");
		p2p.setHorizontalAlignment(SwingConstants.CENTER);
		p2p.setForeground(new Color(240, 128, 128));
		p2p.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		p2p.setBounds(658, 75, 80, 25);
		spec.add(p2p);
		
		p2b = new JLabel("BAN");
		p2b.setHorizontalAlignment(SwingConstants.CENTER);
		p2b.setForeground(new Color(240, 128, 128));
		p2b.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 20));
		p2b.setBounds(568, 165, 80, 25);
		spec.add(p2b);
		
		p1p1 = new JPanel();
		p1p1.setBorder(new LineBorder(new Color(233, 150, 122), 3));
		p1p1.setOpaque(false);
		p1p1.setBounds(10, 110, 80, 80);
		spec.add(p1p1);
		
		p1p2 = new JPanel();
		p1p2.setOpaque(false);
		p1p2.setBorder(new LineBorder(new Color(233, 150, 122), 3));
		p1p2.setBounds(10, 200, 80, 80);
		spec.add(p1p2);
		
		p1p3 = new JPanel();
		p1p3.setOpaque(false);
		p1p3.setBorder(new LineBorder(new Color(233, 150, 122), 3));
		p1p3.setBounds(10, 290, 80, 80);
		spec.add(p1p3);
		
		p1b1 = new JPanel();
		p1b1.setOpaque(false);
		p1b1.setBorder(new LineBorder(new Color(233, 150, 122), 3));
		p1b1.setBounds(100, 200, 80, 80);
		spec.add(p1b1);
		
		p1b2 = new JPanel();
		p1b2.setOpaque(false);
		p1b2.setBorder(new LineBorder(new Color(233, 150, 122), 3));
		p1b2.setBounds(100, 290, 80, 80);
		spec.add(p1b2);
		
		p2p1 = new JPanel();
		p2p1.setOpaque(false);
		p2p1.setBorder(new LineBorder(new Color(240, 128, 128), 3));
		p2p1.setBounds(658, 110, 80, 80);
		spec.add(p2p1);
		
		p2p2 = new JPanel();
		p2p2.setOpaque(false);
		p2p2.setBorder(new LineBorder(new Color(240, 128, 128), 3));
		p2p2.setBounds(658, 200, 80, 80);
		spec.add(p2p2);
		
		p2p3 = new JPanel();
		p2p3.setOpaque(false);
		p2p3.setBorder(new LineBorder(new Color(240, 128, 128), 3));
		p2p3.setBounds(658, 290, 80, 80);
		spec.add(p2p3);
		
		p2b1 = new JPanel();
		p2b1.setOpaque(false);
		p2b1.setBorder(new LineBorder(new Color(240, 128, 128), 3));
		p2b1.setBounds(568, 200, 80, 80);
		spec.add(p2b1);
		
		p2b2 = new JPanel();
		p2b2.setOpaque(false);
		p2b2.setBorder(new LineBorder(new Color(240, 128, 128), 3));
		p2b2.setBounds(568, 290, 80, 80);
		spec.add(p2b2);
		
		game = new JLabel("±ÈÈüÊ¤Àû/Ê§°Ü");
		game.setForeground(new Color(95, 158, 160));
		game.setHorizontalAlignment(SwingConstants.CENTER);
		game.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 44));
		game.setBounds(10, 10, 1014, 90);
		this.add(game);
		
		userelo = new JLabel();
		userelo.setToolTipText("");
		userelo.setText("Elo£º2999£¨+999£©");
		userelo.setHorizontalAlignment(SwingConstants.CENTER);
		userelo.setForeground(new Color(95, 158, 160));
		userelo.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 26));
		userelo.setBounds(772, 259, 252, 40);
		this.add(userelo);
		
		userrating = new JLabel();
		userrating.setToolTipText("");
		userrating.setText("Rating£º2.22 ¡ü");
		userrating.setHorizontalAlignment(SwingConstants.CENTER);
		userrating.setForeground(new Color(95, 158, 160));
		userrating.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 26));
		userrating.setBounds(772, 185, 252, 40);
		this.add(userrating);
		
		userrank = new JLabel();
		userrank.setToolTipText("");
		userrank.setBounds(768, 326, 256, 40);
		this.add(userrank);
		userrank.setText("¶¨¼¶ÈüÊ£Óà£º5¾Ö");
		userrank.setHorizontalAlignment(SwingConstants.CENTER);
		userrank.setForeground(new Color(95, 158, 160));
		userrank.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 26));
		
		gametitle = new JLabel();
		gametitle.setToolTipText("");
		gametitle.setText("±ÈÈü½áËã");
		gametitle.setHorizontalAlignment(SwingConstants.CENTER);
		gametitle.setForeground(new Color(199, 21, 133));
		gametitle.setFont(new Font("µÈÏß", Font.BOLD, 30));
		gametitle.setBounds(772, 110, 252, 40);
		this.add(gametitle);
		
		// TODO ±ÈÈü½áËã
		
		if(winlose) {
			game.setText("±ÈÈüÊ¤Àû");
		} else {
			game.setText("±ÈÈüÊ§°Ü");
		}
		
		if(ratingadd>=user.getRating()) {
			userrating.setText("Rating£º"+ratingadd+" ¡ü");
		} else {
			userrating.setText("Rating£º"+ratingadd+" ¡ý");
		}
		
		if(user.getDjs()<=0) {
			if(eloadd>=0) {
				userelo.setText("Elo£º"+user.getElo()+"£¨+"+eloadd+"£©");
			} else {
				userelo.setText("Elo£º"+user.getElo()+"£¨"+eloadd+"£©");
			}
		} else {
			userelo.setText("Elo£º¶¨¼¶Èü");
		}
		
		if(user.getDjs()<=0) {
			userrank.setText("Rank£º"+user.getRank());
		} else {
			userrank.setText("¶¨¼¶ÈüÊ£Óà£º"+user.getDjs()+"¾Ö");
			userrank.setToolTipText("ÔÙ»ñµÃ"+user.getDjs()+"¾ÖÌìÌÝÆ¥ÅäµÄÊ¤ÀûÒÔ¼¤»î¶ÎÎ»ÆÀ¼Û");
		}
		
		// TODO ±ÈÈü¼ÇÂ¼
		
		p1.setText(user.getUsername());
		p2.setText(enemy.getUsername());
		p1k.setText(String.valueOf(kill));
		p1d.setText(String.valueOf(death));
		p2k.setText(String.valueOf(death));
		p2d.setText(String.valueOf(kill));
		p1adr.setText(String.valueOf(p1a));
		p2adr.setText(String.valueOf(p2a));
		p1r.setText(String.valueOf(ratingadd));
		p2r.setText("-");
		
		if(user.getDjs()==0) {
			if(eloadd>=0) {
				p1e.setText("+"+eloadd);
			} else {
				p1e.setText(String.valueOf(eloadd));
			}
		} else {
			p1e.setText("¶¨¼¶Èü");
		}
		if(enemy.getDjs()==0) {
			p2e.setText("-");
		} else {
			p2e.setText("¶¨¼¶Èü");
		}
		
		for(int i=0,j=1;i<userbanned.size();i++,j+=2) {
			getBannedImage(userbanned.get(i).getId(), j);
		}
		
		for(int i=0,j=1;i<userpicked.size();i++,j+=2) {
			getPickedImage(userpicked.get(i).getId(), j);
		}
		
		for(int i=0,j=2;i<enemybanned.size();i++,j+=2) {
			getBannedImage(enemybanned.get(i).getId(), j);
		}
		
		for(int i=0,j=2;i<enemypicked.size();i++,j+=2) {
			getPickedImage(enemypicked.get(i).getId(), j);
		}
		
	}
	
	void getBannedImage(int id,int x) {
		switch(id) {
			case 0:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new JPanel();
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new JPanel();
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new JPanel();
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new JPanel();
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 1:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 2:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 3:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 4:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 5:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 6:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 7:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 8:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 9:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 10:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 11:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 12:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
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
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new JPanel();
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new JPanel();
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new JPanel();
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new JPanel();
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new JPanel();
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new JPanel();
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 1:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 2:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 3:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 4:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 5:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 6:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 7:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 8:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 9:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 10:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 11:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 12:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
		}
	}
}
