package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entity.User;
import util.Config;

public class Personal extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 70564710787316524L;

	JLabel id = new JLabel("Zytter ID："), numberid = new JLabel("数字ID："), numberid1 = new JLabel("这里填什么");
	JLabel total = new JLabel("总场次："), total1 = new JLabel("这里填什么"), victory = new JLabel("胜场："),
			victory1 = new JLabel("这里填什么");
	JLabel fail = new JLabel("败场："), fail1 = new JLabel("这里填什么"), probability = new JLabel("胜率："),
			probability1 = new JLabel("这里填什么");
	JLabel Rating = new JLabel("Rating："), Rating1 = new JLabel("这是啥"), Elo = new JLabel("Elo："),
			Elo1 = new JLabel("看不懂"), Rank = new JLabel("Rank:"), Rank1 = new JLabel("不会做");
	JTextField id1 = new JTextField();
	JButton quit = new JButton("关闭"), ok = new JButton("确认"), cancel = new JButton("取消"), changeid = new JButton("修改ID"), changepassword = new JButton("修改密码");

	public Personal(User user, Main main) {
		id1.setEditable(false);
		this.setUndecorated(true);
		this.setBackground(new Color(255,255,255,220));
		this.setSize(923,457);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setTitle(user.getUsername()+"的账号信息");
		this.add(id);
		this.add(id1);
		this.add(changeid);
		this.add(numberid);
		this.add(numberid1);
		this.add(changepassword);
		this.add(total);
		this.add(total1);
		this.add(victory);
		this.add(victory1);
		this.add(fail);
		this.add(fail1);
		this.add(probability);
		this.add(probability1);
		this.add(Rating);
		this.add(Rating1);
		this.add(Elo);
		this.add(Elo1);
		this.add(Rank);
		this.add(Rank1);
		this.add(quit);
		
		id.setBounds(200, 30, 200, 100);
		id1.setBounds(300, 65, 300, 35);
		id1.setText(user.getUsername());
		
		changeid.setBounds(630, 64, 115, 35);
		changeid.setContentAreaFilled(false);
		changeid.setBorderPainted(false);
		
		numberid.setBounds(200, 80, 200, 100);
		numberid1.setBounds(300, 80, 200, 100);
		numberid1.setText(String.valueOf(user.getUid()));
		
		changepassword.setBounds(630, 110, 115, 33);
		changepassword.setContentAreaFilled(false);
		changepassword.setBorderPainted(false);
		
		total.setBounds(200, 130, 200, 100);
		total1.setBounds(300, 130, 200, 100);
		total1.setText(String.valueOf(user.getAllplay()));
		
		victory.setBounds(200, 180, 200, 100);
		victory1.setBounds(300, 180, 200, 100);
		victory1.setText(String.valueOf(user.getAllwin()));
		
		fail.setBounds(200, 230, 200, 100);
		fail1.setBounds(300, 230, 200, 100);
		fail1.setText(String.valueOf(user.getAlllose()));
		
		probability.setBounds(200, 280, 200, 100);
		probability1.setBounds(300, 280, 200, 100);
		probability1.setText(getWinrate(user.getAllwinrate()));
		
		Rating.setBounds(630, 175, 200, 100);
		Rating1.setBounds(700, 175, 200, 100);
		Rating1.setText(String.valueOf(getRating(user.getRating())));
		
		Elo.setBounds(630, 225, 200, 100);
		Elo1.setBounds(700, 225, 200, 100);
		if(user.getDjs()<=0) {
			Elo1.setText(String.valueOf(user.getElo()));
		} else {
			Elo1.setText("还需胜利"+user.getDjs()+"场才能激活");
		}
		
		Rank.setBounds(630, 275, 200, 100);
		Rank1.setBounds(700, 275, 220, 100);
		if(user.getDjs()<=0) {
			Rank1.setText(String.valueOf(user.getRank()));
		} else {
			Rank1.setText("未定级");
		}
		
		quit.setBounds(390, 380, 100, 50);
		quit.setContentAreaFilled(false);
		quit.setBorderPainted(false);
		
		ok.setBounds(630, 64, 115, 35);
		ok.setContentAreaFilled(false);
		ok.setBorderPainted(false);
		
		cancel.setBounds(750, 64, 115, 35);
		cancel.setContentAreaFilled(false);
		cancel.setBorderPainted(false);
		
		quit.setFont(Config.ButtonFont);

		changeid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				id1.setEditable(true);
				remove(changeid);
				add(ok);
				add(cancel);
				repaint();
			}

		});
		
		changepassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Changepassword(user).setVisible(true);
			}
		});
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null,"确认修改？","修改Zytter ID",JOptionPane.YES_NO_OPTION) == 0) {
					String newname = id1.getText();
					if(newname.length()>=2&&newname.length()<=6) {
						if(!isSpace(newname)) {
							if(!Config.s.isexist(newname)) {
								if(Config.s.updateUser(newname, user.getUid()) != 0) {
									user.setUsername(newname);
									JOptionPane.showMessageDialog(null, "修改成功！请点击确认重启客户端！");
									new Thread() {
										@Override
										public void run() {
											try {
												sleep(3000);
											} catch (InterruptedException e) {
												e.printStackTrace();
											}
											new Main(user,main.server).setVisible(true);
										}
									}.start();
									dispose();
									main.dispose();
								} else {
									JOptionPane.showMessageDialog(null, "修改失败");
								}
							} else {
								JOptionPane.showMessageDialog(null, "该Zytter ID已被使用，请重新输入");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Zytter ID不允许包含空格");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Zytter ID必须控制在2~6个字符内");
					}
				}
			}
			
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				id1.setEditable(false);
				remove(cancel);
				remove(ok);
				add(changeid);
				repaint();
			}
		});

		quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				main.setEnabled(true);
				dispose();
			}
		});
		
	}
	
	String getRating(double rating) {
		return new DecimalFormat("#0.00").format(rating);
	}
	
	String getWinrate(double winrate) {
		return new DecimalFormat("#0.#").format(winrate*100)+"%";
	}
	
	boolean isSpace(String str) {
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)==' ') {
				return true;
			}
		}
		return false;
	}
	
}
