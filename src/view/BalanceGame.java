package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import entity.BGM;
import entity.User;
import util.Config;

public class BalanceGame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4633977666927622823L;

	public BalanceGame(boolean winlose,User user,User enemy,int kill,int death,double p1rating,double p2rating,
			double p1a,double p2a,int p1elo,int p2elo,double ratingadd,int eloadd,Fight f) {
		this.setTitle("Zytter 比赛结算");
		this.setLayout(null);
		this.setUndecorated(true);
		this.setBackground(new Color(255,255,255,220));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(1000, 550);
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		
		JButton back = new JButton("");
		back.setContentAreaFilled(false);
		back.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/return.png")));
		back.setBounds(373, 440, 256, 100);
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
		this.add(back);
		
		JLabel game = new JLabel();
		if(winlose) {
			game.setText("比赛胜利");
		} else {
			game.setText("比赛失败");
		}
		game.setHorizontalAlignment(SwingConstants.CENTER);
		game.setForeground(new Color(106, 90, 205));
		game.setFont(new Font("等线", Font.BOLD, 42));
		game.setBounds(22, 22, 245, 68);
		this.add(game);
		
		JLabel p1name = new JLabel(user.getUsername());
		p1name.setForeground(new Color(205, 92, 92));
		p1name.setFont(new Font("等线", Font.BOLD, 28));
		p1name.setBounds(60, 128, 207, 52);
		this.add(p1name);
		
		JLabel p2name = new JLabel(enemy.getUsername());
		p2name.setForeground(new Color(205, 92, 92));
		p2name.setFont(new Font("等线", Font.BOLD, 28));
		p2name.setBounds(60, 205, 207, 52);
		this.add(p2name);
		
		JLabel plk = new JLabel("击杀");
		plk.setHorizontalAlignment(SwingConstants.CENTER);
		plk.setFont(new Font("等线", Font.BOLD, 28));
		plk.setForeground(new Color(240, 128, 128));
		plk.setBounds(277, 34, 93, 52);
		this.add(plk);
		
		JLabel pld = new JLabel("死亡");
		pld.setHorizontalAlignment(SwingConstants.CENTER);
		pld.setForeground(new Color(240, 128, 128));
		pld.setFont(new Font("等线", Font.BOLD, 28));
		pld.setBounds(391, 34, 93, 52);
		this.add(pld);
		
		JLabel plrate = new JLabel("Rating");
		plrate.setHorizontalAlignment(SwingConstants.CENTER);
		plrate.setForeground(new Color(240, 128, 128));
		plrate.setFont(new Font("等线", Font.BOLD, 28));
		plrate.setBounds(512, 34, 93, 52);
		this.add(plrate);
		
		JLabel pladr = new JLabel("平均伤害");
		pladr.setHorizontalAlignment(SwingConstants.CENTER);
		pladr.setForeground(new Color(240, 128, 128));
		pladr.setFont(new Font("等线", Font.BOLD, 28));
		pladr.setBounds(649, 34, 119, 52);
		this.add(pladr);
		
		JLabel plelo = new JLabel("天梯积分");
		plelo.setHorizontalAlignment(SwingConstants.CENTER);
		plelo.setForeground(new Color(240, 128, 128));
		plelo.setFont(new Font("等线", Font.BOLD, 28));
		plelo.setBounds(795, 34, 169, 52);
		this.add(plelo);
		
		JLabel p1k = new JLabel(String.valueOf(kill));
		p1k.setHorizontalAlignment(SwingConstants.CENTER);
		p1k.setForeground(new Color(240, 128, 128));
		p1k.setFont(new Font("等线", Font.BOLD, 28));
		p1k.setBounds(277, 128, 93, 52);
		this.add(p1k);
		
		JLabel p1d = new JLabel(String.valueOf(death));
		p1d.setHorizontalAlignment(SwingConstants.CENTER);
		p1d.setForeground(new Color(240, 128, 128));
		p1d.setFont(new Font("等线", Font.BOLD, 28));
		p1d.setBounds(391, 128, 93, 52);
		this.add(p1d);
		
		JLabel p1r = new JLabel(String.valueOf(p1rating));
		p1r.setHorizontalAlignment(SwingConstants.CENTER);
		p1r.setForeground(new Color(240, 128, 128));
		p1r.setFont(new Font("等线", Font.BOLD, 28));
		p1r.setBounds(512, 128, 93, 52);
		this.add(p1r);
		
		JLabel p1adr = new JLabel(String.valueOf(p1a));
		p1adr.setHorizontalAlignment(SwingConstants.CENTER);
		p1adr.setForeground(new Color(240, 128, 128));
		p1adr.setFont(new Font("等线", Font.BOLD, 28));
		p1adr.setBounds(649, 128, 119, 52);
		this.add(p1adr);
		
		JLabel p1e = new JLabel();
		if(user.getDjs()<=0) {
			p1e.setText(String.valueOf(p1elo));
		} else {
			p1e.setText("未定级");
		}
		p1e.setHorizontalAlignment(SwingConstants.CENTER);
		p1e.setForeground(new Color(240, 128, 128));
		p1e.setFont(new Font("等线", Font.BOLD, 28));
		p1e.setBounds(795, 128, 169, 52);
		this.add(p1e);
		
		JLabel p2k = new JLabel(String.valueOf(death));
		p2k.setHorizontalAlignment(SwingConstants.CENTER);
		p2k.setForeground(new Color(240, 128, 128));
		p2k.setFont(new Font("等线", Font.BOLD, 28));
		p2k.setBounds(277, 205, 93, 52);
		this.add(p2k);
		
		JLabel p2d = new JLabel(String.valueOf(kill));
		p2d.setHorizontalAlignment(SwingConstants.CENTER);
		p2d.setForeground(new Color(240, 128, 128));
		p2d.setFont(new Font("等线", Font.BOLD, 28));
		p2d.setBounds(391, 205, 93, 52);
		this.add(p2d);
		
		JLabel p2r = new JLabel(String.valueOf(p2rating));
		p2r.setHorizontalAlignment(SwingConstants.CENTER);
		p2r.setForeground(new Color(240, 128, 128));
		p2r.setFont(new Font("等线", Font.BOLD, 28));
		p2r.setBounds(512, 205, 93, 52);
		this.add(p2r);
		
		JLabel p2adr = new JLabel(String.valueOf(p2a));
		p2adr.setHorizontalAlignment(SwingConstants.CENTER);
		p2adr.setForeground(new Color(240, 128, 128));
		p2adr.setFont(new Font("等线", Font.BOLD, 28));
		p2adr.setBounds(649, 205, 119, 52);
		this.add(p2adr);
		
		JLabel p2e = new JLabel();
		if(enemy.getDjs()<=0) {
			p2e.setText(String.valueOf(p2elo));
		} else {
			p2e.setText("未定级");
		}
		p2e.setHorizontalAlignment(SwingConstants.CENTER);
		p2e.setForeground(new Color(240, 128, 128));
		p2e.setFont(new Font("等线", Font.BOLD, 28));
		p2e.setBounds(795, 205, 169, 52);
		this.add(p2e);
		
		JLabel rating = new JLabel("您本次比赛的技术得分："+ratingadd);
		rating.setHorizontalAlignment(SwingConstants.CENTER);
		rating.setForeground(new Color(95, 158, 160));
		rating.setFont(new Font("等线", Font.BOLD, 28));
		rating.setBounds(10, 289, 980, 40);
		this.add(rating);
		
		JLabel elo = new JLabel();
		if(user.getDjs()<=0) {
			if(eloadd>=0) {
				elo.setText("您当前的天梯积分："+user.getElo()+"（+"+eloadd+"）");
			} else {
				elo.setText("您当前的天梯积分："+user.getElo()+"（"+eloadd+"）");
			}
		} else {
			elo.setText("您当前尚未激活天梯积分");
		}
		elo.setHorizontalAlignment(SwingConstants.CENTER);
		elo.setForeground(new Color(95, 158, 160));
		elo.setFont(new Font("等线", Font.BOLD, 28));
		elo.setBounds(10, 339, 980, 40);
		this.add(elo);
		
		JLabel rank = new JLabel();
		if(user.getDjs()<=0) {
			rank.setText("您当前的段位："+user.getRank());
		} else {
			rank.setText("再胜利"+user.getDjs()+"场天梯匹配即可显示您的段位");
		}
		rank.setHorizontalAlignment(SwingConstants.CENTER);
		rank.setForeground(new Color(95, 158, 160));
		rank.setFont(new Font("等线", Font.BOLD, 28));
		rank.setBounds(10, 390, 980, 40);
		this.add(rank);
		
		this.setLocationRelativeTo(null);
	}
}
