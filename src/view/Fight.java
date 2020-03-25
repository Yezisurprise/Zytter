package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
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
import java.math.BigDecimal;
import java.net.Socket;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import entity.BGM;
import entity.Hero;
import entity.Item;
import entity.PlayerIcon;
import entity.Server;
import entity.Skill;
import entity.User;
import entity.Voice;
import util.Config;
import util.Host;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.GridLayout;
import javax.swing.JTextArea;

public class Fight extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4713471001756369179L;
	
	/*
	 * - 对战玩家基本信息
	 */
	
	User user,enemy;
	
	int roomid;
	
	Server server;

	ArrayList<Hero> userheroes,enemyheroes;
	
	ArrayList<Hero> userbanned = new ArrayList<Hero>(),enemybanned = new ArrayList<Hero>();
	
	ArrayList<Hero> userpicked = new ArrayList<Hero>(),enemypicked = new ArrayList<Hero>();
	
	ArrayList<JButton> select = new ArrayList<JButton>(); // 行动按钮的集合
	
	ArrayList<Item> djh = new ArrayList<Item>(); // 道具盒
	
	Hero userh,enemyh;
	
	Item willitem = null;
	
	Item willequip = null;
	
	/*
	 * - 对战界面UI涉及的变量
	 */
	
	JLabel round,whoact,remaintime,tip1,tip2,tip3,tip4;
	
	JLabel now,enemyid,enemyhero,usernext,enemynext;
	
	PlayerIcon usertx,enemytx;
	
	JPanel skillgroup;
	
	JButton zb1,zb2,skill1,skill2,skill3,skill4,atk,ok,gg,item,chat;
	
	JProgressBar userhpt,usermpt,enemyhpt,enemympt;
	
	JTextArea textArea;
	
	JScrollPane gdt;
	
	JPanel userbuff,enemybuff;
	
	GetSkillUp gsu;
	
	/*
	 * - 统计数据
	 */
	
	int fsjn = 0,gjfsjn = 0; // 复苏类道具数量
	
	int p = 0; // 概率值
	
	int op = 0 , userop = 0 , enemyop = 0; // 操作值
	
	int mp = 0; // 魔法消耗
	
	int r = 0 , limitr = 35; // 回合数
	
	int rend = 1; // 回合结束数（总是比r高1）
	
	int remain = 20; //回合剩余时间
	
	int gold = 0; // 金币数
	
	int readyitem = 0; // 准备的道具
	
	int selected = 0; // 选择
	
	int hpp = 0,mpp = 0; // 累计生命回复，累计魔法回复
	
	double adr = 0; // 平均回合伤害
	
	int ark = 0,damage = 0,hurt = 0; // 平均击杀回合，累计造成伤害，累计承受伤害（用于计算rating）
	
	int robd = 0; // 用来判定每造成10点伤害掠夺一块金币
	
	int robh = 0; // 用来判定每受到10点伤害被掠夺一块金币
	
	int kill = 0,death = 0; // 击杀数，死亡数
	
	int herotime = 0; // 英雄上场回合
	
	double herodamage = 0; // 英雄累计伤害
	
	double rating;// rating
	
	int elo;// elo
	
	boolean winlose = false; // 赢还是输
	
	boolean roomcreater; // 是不是房间创建者
	
	int uyyzs = 0,eyyzs = 0; // 夜宴之声的使用次数
	
	boolean xdsx = false; // 行动受限
	
	boolean gameover = false; // 游戏是否结束
	
	int heroup = 0; // 属性提升次数
	
	boolean ispause = false; // 游戏是否暂停
	
	int pausetimes = 3; // 剩余暂停次数
	
	boolean isuserpause = false; // 是否是你暂停
	
	boolean eatequip = true; // 吃装备的机会
	
	/**
	 * Send Socket
	 */
	Socket socket = null;
	
	OutputStream os = null;
	DataOutputStream dos = null;
	ObjectOutputStream oos = null;
	PrintWriter pw = null;
	
	InputStream is = null;
	DataInputStream dis = null;
	ObjectInputStream ois = null;
	InputStreamReader isr = null;
	BufferedReader br = null;
	
	/**
	 * Receive Socket
	 */
	Socket socket2 = null;
	
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
	 * Chat Socket
	 */
	
	Socket socket3 = null;
	
	OutputStream os3 = null;
	DataOutputStream dos3 = null;
	ObjectOutputStream oos3 = null;
	PrintWriter pw3 = null;
	
	InputStream is3 = null;
	DataInputStream dis3 = null;
	ObjectInputStream ois3 = null;
	InputStreamReader isr3 = null;
	BufferedReader br3 = null;
	
	/**
	 * Equip Socket
	 */
	
	Socket socket4 = null;
	
	OutputStream os4 = null;
	DataOutputStream dos4 = null;
	ObjectOutputStream oos4 = null;
	PrintWriter pw4 = null;
	
	InputStream is4 = null;
	DataInputStream dis4 = null;
	ObjectInputStream ois4 = null;
	InputStreamReader isr4 = null;
	BufferedReader br4 = null;
	
	/**
	 * Probability Socket
	 */
	
	Socket socket5 = null;
	
	OutputStream os5 = null;
	DataOutputStream dos5 = null;
	ObjectOutputStream oos5 = null;
	PrintWriter pw5 = null;
	
	InputStream is5 = null;
	DataInputStream dis5 = null;
	ObjectInputStream ois5 = null;
	InputStreamReader isr5 = null;
	BufferedReader br5 = null;
	
	/** 对战界面构造方法
	 * @param user - 玩家
	 * @param enemy - 对手
	 * @param userheroes - 玩家英雄组
	 * @param enemyheroes - 对手英雄组
	 * @param iscreater - 是否为房间拥有者
	 */
	
	public Fight(User user,User enemy,ArrayList<Hero> userbanned,ArrayList<Hero> enemybanned,ArrayList<Hero> userheroes,ArrayList<Hero> enemyheroes,boolean iscreater,int roomid,Server server) {
		
		this.user=user;
		this.enemy=enemy;
		this.userheroes=userheroes;
		this.enemyheroes=enemyheroes;
		this.userbanned.addAll(userbanned);
		this.enemybanned.addAll(enemybanned);
		this.userpicked.addAll(userheroes);
		this.enemypicked.addAll(enemyheroes);
		this.roomcreater=iscreater;
		this.roomid=roomid;
		this.server=server;
				
		remaintime = new JLabel("还剩"+remain+"秒");
		remaintime.setForeground(Color.RED);
		remaintime.setFont(new Font("等线", Font.PLAIN, 35));
		remaintime.setHorizontalAlignment(SwingConstants.CENTER);
		remaintime.setBounds(684, 38, 421, 54);
		this.add(remaintime);
		
		round = new JLabel("比赛即将开始！");
		round.setForeground(Color.white);
		round.setHorizontalAlignment(SwingConstants.CENTER);
		round.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		round.setBounds(268, 10, 837, 28);
		this.add(round);
		
		whoact = new JLabel("等待玩家连接...");
		whoact.setForeground(Color.white);
		whoact.setHorizontalAlignment(SwingConstants.CENTER);
		whoact.setFont(new Font("等线", Font.PLAIN, 35));
		whoact.setBounds(268, 38, 421, 54);
		this.add(whoact);
		
		new waitstart().start();
		
		this.setUndecorated(true);
		this.setTitle(Config.GlobalTitle);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1366, 768);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("img/fight.jpg"));
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,1366,768);
		Container contain = this.getContentPane();
		((JPanel)contain).setOpaque(false);
		
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		
		textArea = new JTextArea();
		textArea.setBorder(new TitledBorder(new LineBorder(new Color(188, 143, 143), 5), "\u6587\u672C\u63D0\u793A\u6846", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(188, 143, 143)));
		textArea.setForeground(Color.white);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBounds(268, 97, 837, 481);
		
		gdt = new JScrollPane();
		gdt.setBounds(268, 97, 837, 481);
		gdt.setOpaque(false);
		gdt.getViewport().setOpaque(false);
		gdt.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		gdt.setViewportView(textArea);

		this.add(gdt);
		
	}
	
	/** 初始化FightUI
	 */
	
	public void InitGUI() {
		
		this.userh = Config.s.GetSkillByHero(getUserHero());
		this.enemyh = Config.s.GetSkillByHero(getEnemyHero());
		
		Config.bgm = new BGM();
		Config.bgm.setBGM(3);
		Config.bgm.start();
		
		tip1 = new JLabel("当前拥有金币："+gold);
		tip1.setHorizontalAlignment(SwingConstants.CENTER);
		tip1.setForeground(Color.white);
		tip1.setFont(new Font("等线", Font.PLAIN, 16));
		tip1.setBounds(10, 23, 239, 30);
		this.add(tip1);
		
		tip2 = new JLabel("累计生命/魔法回复："+hpp+" / "+mpp);
		tip2.setHorizontalAlignment(SwingConstants.CENTER);
		tip2.setForeground(Color.white);
		tip2.setFont(new Font("等线", Font.PLAIN, 16));
		tip2.setBounds(10, 57, 239, 30);
		this.add(tip2);
		
		tip3 = new JLabel("平均伤害/击杀回合："+0+" / "+0);
		tip3.setHorizontalAlignment(SwingConstants.CENTER);
		tip3.setForeground(Color.white);
		tip3.setFont(new Font("等线", Font.PLAIN, 16));
		tip3.setBounds(1117, 23, 239, 30);
		this.add(tip3);
		
		tip4 = new JLabel("累计造成/承受伤害："+damage+" / "+hurt);
		tip4.setHorizontalAlignment(SwingConstants.CENTER);
		tip4.setForeground(Color.white);
		tip4.setFont(new Font("等线", Font.PLAIN, 16));
		tip4.setBounds(1117, 57, 239, 30);
		this.add(tip4);
		
		now = new JLabel("当前出战英雄："+userh.getName());
		now.setHorizontalAlignment(SwingConstants.CENTER);
		now.setForeground(Color.white);
		now.setFont(new Font("等线", Font.PLAIN, 16));
		now.setBounds(33, 108, 200, 30);
		this.add(now);
		
		enemyid = new JLabel(enemy.getUsername());
		enemyid.setForeground(Color.white);
		enemyid.setHorizontalAlignment(SwingConstants.CENTER);
		enemyid.setFont(new Font("等线", Font.PLAIN, 30));
		enemyid.setBounds(1130, 93, 200, 48);
		this.add(enemyid);
		
		enemyhero = new JLabel(enemyh.getName());
		enemyhero.setForeground(Color.white);
		enemyhero.setHorizontalAlignment(SwingConstants.RIGHT);
		enemyhero.setFont(new Font("等线", Font.PLAIN, 12));
		enemyhero.setBounds(1130, 137, 200, 15);
		this.add(enemyhero);

		this.getUserHeroIcon(userh.getId());
		this.add(usertx);
		
		this.getEnemyHeroIcon(enemyh.getId());
		this.add(enemytx);
		
		JLabel userhp = new JLabel("HP");
		userhp.setForeground(Color.RED);
		userhp.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		userhp.setBounds(45, 376, 29, 28);
		this.add(userhp);
		
		JLabel enemyhp = new JLabel("HP");
		enemyhp.setForeground(Color.RED);
		enemyhp.setFont(new Font("微软雅黑 Light", Font.PLAIN, 20));
		enemyhp.setBounds(1145, 376, 29, 28);
		this.add(enemyhp);
		
		JLabel usermp = new JLabel("MP");
		usermp.setForeground(Color.BLUE);
		usermp.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		usermp.setBounds(45, 415, 29, 28);
		this.add(usermp);
		
		JLabel enemymp = new JLabel("MP");
		enemymp.setForeground(Color.BLUE);
		enemymp.setFont(new Font("微软雅黑 Light", Font.PLAIN, 18));
		enemymp.setBounds(1145, 415, 29, 28);
		this.add(enemymp);
		
		userhpt = new JProgressBar();
		userhpt.setForeground(Color.RED);
		userhpt.setStringPainted(true);
		userhpt.setMaximum(userh.getHp());
		userhpt.setValue(userh.getHp());
		userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
		userhpt.setBounds(80, 377, 146, 29);
		this.add(userhpt);
		
		enemyhpt = new JProgressBar();
		enemyhpt.setForeground(Color.RED);
		enemyhpt.setStringPainted(true);
		enemyhpt.setMaximum(enemyh.getHp());
		enemyhpt.setValue(enemyh.getHp());
		enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
		enemyhpt.setBounds(1180, 375, 146, 29);
		this.add(enemyhpt);
		
		usermpt = new JProgressBar();
		usermpt.setForeground(SystemColor.textHighlight);
		usermpt.setStringPainted(true);
		usermpt.setMaximum(userh.getMp());
		usermpt.setValue(userh.getMp());
		usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
		usermpt.setBounds(80, 416, 146, 29);
		this.add(usermpt);
		
		enemympt = new JProgressBar();
		enemympt.setForeground(SystemColor.textHighlight);
		enemympt.setStringPainted(true);
		enemympt.setMaximum(enemyh.getMp());
		enemympt.setValue(enemyh.getMp());
		enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
		enemympt.setBounds(1180, 414, 146, 29);
		this.add(enemympt);
		
		usernext = new JLabel(getUserNext());
		usernext.setForeground(Color.white);
		usernext.setHorizontalAlignment(SwingConstants.CENTER);
		usernext.setFont(new Font("等线", Font.PLAIN, 16));
		usernext.setBounds(33, 467, 200, 32);
		this.add(usernext);
		
		enemynext = new JLabel(getEnemyNext());
		enemynext.setForeground(Color.white);
		enemynext.setHorizontalAlignment(SwingConstants.CENTER);
		enemynext.setFont(new Font("等线", Font.PLAIN, 16));
		enemynext.setBounds(1130, 468, 200, 30);
		this.add(enemynext);
		
		JPanel zbgroup = new JPanel();
		zbgroup.setBorder(new TitledBorder(new LineBorder(Color.white, 2), "\u88C5\u5907", TitledBorder.CENTER, TitledBorder.TOP, null, Color.white));
		zbgroup.setBounds(171, 588, 87, 170);
		zbgroup.setOpaque(false);
		this.add(zbgroup);
		zbgroup.setLayout(new GridLayout(2, 1, 0, 0));
		
		zb1 = new JButton("");
		zb1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e1.png")));
		zb1.setBounds(247, 42, 64, 64);
		zb1.setContentAreaFilled(false);
		zb1.setBorderPainted(false);
		zb1.setToolTipText("点击选择一个装备并穿戴。");
		zb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new openEquip(Fight.this, zb1).setVisible(true);
			}
		});
		zbgroup.add(zb1);
		
		zb2 = new JButton("");
		zb2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
		zb2.setBounds(247, 126, 64, 64);
		zb2.setContentAreaFilled(false);
		zb2.setBorderPainted(false);
		zb2.setToolTipText("点击选择一个装备并穿戴。");
		zb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new openEquip(Fight.this, zb2).setVisible(true);
			}
		});
		zbgroup.add(zb2);
		
		skillgroup = new JPanel();
		skillgroup.setBorder(new TitledBorder(new LineBorder(Color.white, 2), "\u6280\u80FD", TitledBorder.CENTER, TitledBorder.TOP, null, Color.white));
		skillgroup.setBounds(268, 588, 837, 170);
		skillgroup.setOpaque(false);
		skillgroup.setLayout(new GridLayout(1, 4, 0, 0));
		this.add(skillgroup);
		
		skill1 = new JButton("");
		skill1.setToolTipText("\u4F7F\u75281\u6280\u80FD");
		skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/s1.png")));
		skill1.setBounds(31, 254, 128, 128);
		skill1.setContentAreaFilled(false);
		skill1.setBorderPainted(false);
		skill1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userh.getQ()!=null) {
					mp = userh.getQ().getMp();
				}
				buttongroup(skill1, 1);
			}
		});
		skillgroup.add(skill1);
		
		skill2 = new JButton("");
		skill2.setToolTipText("\u4F7F\u75282\u6280\u80FD");
		skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/s2.png")));
		skill2.setBounds(242, 254, 128, 128);
		skill2.setContentAreaFilled(false);
		skill2.setBorderPainted(false);
		skill2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userh.getW()!=null) {
					mp = userh.getW().getMp();
				}
				buttongroup(skill2, 2);
			}
		});
		skillgroup.add(skill2);
		
		skill3 = new JButton("");
		skill3.setToolTipText("\u4F7F\u75283\u6280\u80FD");
		skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/s3.png")));
		skill3.setBounds(458, 254, 128, 128);
		skill3.setContentAreaFilled(false);
		skill3.setBorderPainted(false);
		skill3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userh.getE()!=null) {
					mp = userh.getE().getMp();
				}
				buttongroup(skill3, 3);
			}
		});
		skillgroup.add(skill3);
		
		skill4 = new JButton("");
		skill4.setToolTipText("\u4F7F\u75284\u6280\u80FD");
		skill4.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/s4.png")));
		skill4.setBounds(677, 254, 128, 128);
		skill4.setContentAreaFilled(false);
		skill4.setBorderPainted(false);
		skill4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userh.getR()!=null) {
					mp = userh.getR().getMp();
				}
				buttongroup(skill4, 4);
			}
		});
		skillgroup.add(skill4);
		
		this.getSkillIcon(userh.getId());
		
		atk = new JButton("");
		atk.setToolTipText("<html>使用普通攻击。<br /><br />提示：最多可造成"+userh.getAtk()+"点物理伤害。</html>");
		atk.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/atk.png")));
		atk.setBounds(26, 613, 128, 128);
		atk.setContentAreaFilled(false);
		atk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mp = 0;
				buttongroup(atk, 5);
			}
		});
		this.add(atk);
		select.add(atk);
		
		ok = new JButton("");
		ok.setToolTipText("决定该回合的行动。");
		ok.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/decide.png")));
		ok.setFont(new Font("等线", Font.BOLD, 22));
		ok.setBounds(1202, 603, 128, 64);
		ok.setContentAreaFilled(false);
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(selected!=0) {
					if(!ispause) { // 游戏未暂停
						if(op!=0) { // 未选中
							if(op!=6) { // 不等于使用物品
								if(userh.isIsfight()){  // 不处于战斗不能
									if(op==5){ // 使用普通攻击
										if(userh.isIsatk()){ // 不处于攻击不能
											selected = 0;
											try {
												dos.writeInt(op);
												dos.flush();
											} catch (IOException e) {
												e.printStackTrace();
											}
											userop = op;
											setcanSelect();
											UpdateJTextArea("你已经决定了该回合的行动。\n\n");
										} else {
											JOptionPane.showMessageDialog(null, "你不能进行普通攻击。\n原因：攻击不能。");
										}
									} else if(op>=1&&op<=4){ // 使用技能
										if(userh.isIsskill()){ // 不处于施法不能
											if(userh.getMp()>=mp) { // 判断魔法值够不够
												if(op==2&&userh.getId()==9){ // 郑心予 流星 的使用判断
													if(userh.jhjj==1) {
														selected = 0;
														try {
															dos.writeInt(op);
															dos.flush();
														} catch (IOException e) {
															e.printStackTrace();
														}
														userop = op;
														setcanSelect();
														UpdateJTextArea("你已经决定了该回合的行动。\n\n");
													} else {
														if(r%2!=0){
															selected = 0;
															try {
																dos.writeInt(op);
																dos.flush();
															} catch (IOException e) {
																e.printStackTrace();
															}
															userop = op;
															setcanSelect();
															UpdateJTextArea("你已经决定了该回合的行动。\n\n");
														} else {
															JOptionPane.showMessageDialog(null, "现在不是单数回合，不能使用【流星】。");
														}
													}
												} else if(op==1&&userh.getId()==9){ // 郑心予 礼赞的使用判断
													if(r%2==0){
														if(!userh.zxyQ) {
															selected = 0;
															try {
																dos.writeInt(op);
																dos.flush();
															} catch (IOException e) {
																e.printStackTrace();
															}
															userop = op;
															setcanSelect();
															UpdateJTextArea("你已经决定了该回合的行动。\n\n");
														} else {
															JOptionPane.showMessageDialog(null, "【礼赞】正在生效中，不能重复使用。");
														}
													} else {
														JOptionPane.showMessageDialog(null, "现在不是双数回合，不能使用【礼赞】。");
													}
												} else if(op==3&&userh.getId()==4){ // 罗天杰 断骨剑 的使用判断
													if(herotime>=2) {
														if(userh.getHp()>7){
															selected = 0;
															try {
																dos.writeInt(op);
																dos.flush();
															} catch (IOException e) {
																e.printStackTrace();
															}
															userop = op;
															setcanSelect();
															UpdateJTextArea("你已经决定了该回合的行动。\n\n");
														} else {
															JOptionPane.showMessageDialog(null, "由于你的生命值小于或等于7点，出于安全考虑，禁止你使用【断骨剑】。");
														}
													} else {
														JOptionPane.showMessageDialog(null, "你只有在上场2回合后才能使用【断骨剑】。");
													}
												} else if(op==2&&userh.getId()==Config.zf.getId()){ // 张枫 风之结界 的使用判断
													if(!userh.zfW){
														selected = 0;
														try {
															dos.writeInt(op);
															dos.flush();
														} catch (IOException e) {
															e.printStackTrace();
														}
														userop = op;
														setcanSelect();
														UpdateJTextArea("你已经决定了该回合的行动。\n\n");
													} else {
														JOptionPane.showMessageDialog(null, "对方【行动受限】效果即将生效，本回合你不能使用【风之结界】。");
													}
												} else if(op==3&&userh.getId()==Config.xyh.getId()) { // 谢悠涵 时光沙漏 的使用判断
													if(herotime>=3) {
														if(userh.xyhE==0){
															selected = 0;
															try {
																dos.writeInt(op);
																dos.flush();
															} catch (IOException e) {
																e.printStackTrace();
															}
															userop = op;
															setcanSelect();
															UpdateJTextArea("你已经决定了该回合的行动。\n\n");
														} else {
															JOptionPane.showMessageDialog(null, "【时光沙漏】正在生效中，不能重复使用。");
														}
													} else {
														JOptionPane.showMessageDialog(null, "你只有在上场3回合后才能使用【时光沙漏】。");
													}
												} else if(op==3&&userh.getId()==Config.zkx.getId()) { // 张可汐 汐之抉择 的使用判断
													if(herotime>=3) {
														selected = 0;
														try {
															dos.writeInt(op);
															dos.flush();
														} catch (IOException e) {
															e.printStackTrace();
														}
														userop = op;
														setcanSelect();
														UpdateJTextArea("你已经决定了该回合的行动。\n\n");
													} else {
														JOptionPane.showMessageDialog(null, "你只有在上场3回合后才能使用【汐之抉择】。");
													}
												} else if(op==3&&userh.getId()==Config.sjj.getId()) { // 苏璟静 光炽剑 的使用判断
													if(!userh.sjjE){
														selected = 0;
														try {
															dos.writeInt(op);
															dos.flush();
														} catch (IOException e) {
															e.printStackTrace();
														}
														userop = op;
														setcanSelect();
														UpdateJTextArea("你已经决定了该回合的行动。\n\n");
													} else {
														JOptionPane.showMessageDialog(null, "【光炽剑】正在生效中，不能重复使用。");
													}
												} else {
													if(op==2&&userh.getId()==Config.yy.getId()) { // 奕阳 暗影之刺 的使用判断
														userh.setXdl(userh.getXdl()+999);
													}
													if(op==1&&userh.getId()==Config.xyh.getId()) { // 谢悠涵 洁净之灵  的使用判断
														userh.setXdl(userh.getXdl()+9999);
													}
													if(op==3&&userh.getId()==Config.hyq.getId()) { // 郈与却 星月奇迹 的使用判断
														if(userh.jhjj==2) userh.setXdl(userh.getXdl()+999);
													}
													if(op==4&&userh.getId()==Config.hyq.getId()) { // 郈与却 云霄之巅 的使用判断
														userh.setXdl(userh.getXdl()+999);
													}
													if(op==2&&userh.getId()==Config.ysn.getId()){ // 杨圣诺 陨落星辰 的使用判断
														if(JOptionPane.showConfirmDialog(null,"使用Q技能【新星冲刺】？")==0) {
															if(selected==1) {
																if(userh.getMp()-userh.getW().getMp()>=userh.getQ().getMp()) {
																	userh.ysnQ=true;
																} else {
																	JOptionPane.showMessageDialog(null,"魔法值不足！");
																}
															} else {
																JOptionPane.showMessageDialog(null,"操作超时。");
															}
														}
													}
													selected = 0;
													try {
														dos.writeInt(op);
														dos.flush();
													} catch (IOException e) {
														e.printStackTrace();
													}
													userop = op;
													setcanSelect();
													UpdateJTextArea("你已经决定了该回合的行动。\n\n");
												}
											} else {
												JOptionPane.showMessageDialog(null, "魔法值不足，无法使用此技能。");
											}
										} else {
											JOptionPane.showMessageDialog(null, "你不能使用任何技能。\n原因：施法不能。");
										}
									}
								} else {
									JOptionPane.showMessageDialog(null, "你不能决定此行动。\n原因：战斗不能。");
								}
							} else {
								if(readyitem!=0) {
									selected = 0;
									try {
										dos.writeInt(readyitem+10);
										dos.flush();
									} catch (IOException e) {
										e.printStackTrace();
									}
									djh.remove(willitem);
									willitem = null;
									userop = readyitem+10;
									setcanSelect();
									UpdateJTextArea("你已经决定了该回合的行动。\n\n");
								} else {
									JOptionPane.showMessageDialog(null, "未指定道具，请先准备道具");
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "未指定任何行动");
						}
					} else {
						JOptionPane.showMessageDialog(null, "游戏处于暂停状态，无法决定行动。");
					}
				}
			}
		});
		this.add(ok);
		
		gg = new JButton("");
		gg.setToolTipText("放弃该回合行动。");
		gg.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/giveup.png")));
		gg.setFont(new Font("等线", Font.BOLD, 22));
		gg.setBounds(1202, 677, 128, 64);
		gg.setContentAreaFilled(false);
		gg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(selected!=0) {
					if(JOptionPane.showConfirmDialog(null,"确定放弃本回合行动？","放弃行动",JOptionPane.YES_NO_OPTION)==0) {
						selected=0;
						op=0;
						userop=0;
						try {
							dos.writeInt(-1);
							dos.flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
						UpdateJTextArea("你已经放弃了该回合的行动。\n\n");
					}
				}
			}
		});
		this.add(gg);

		item = new JButton("");
		item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
		item.setToolTipText("点击选定一个道具，以便该回合使用。");
		item.setContentAreaFilled(false);
		item.setBounds(1117, 677, 64, 64);
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openItemBox();
			}
		});
		this.add(item);
		select.add(item);
		
		chat = new JButton("");
		chat.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/tochat.png")));
		chat.setContentAreaFilled(false);
		chat.setToolTipText("你想对TA说些什么？");
		chat.setBounds(1117, 603, 64, 64);
		chat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = JOptionPane.showInputDialog("你想和对方说什么？");
				if(str!=null) {
					if(!str.equals("")) {
						if(!str.equals("-quit")) {
							if(!str.equals("-disconnect")) {
								if(!str.equals("-help")) {
									if(!str.equals("-p")) {
										if(!str.equals("-r")) {
											if(!str.equals("-sur")) {
												if(!str.equals("-jh")) {
													if(!str.equals("-mymax")) {
														if(!str.equals("-enemymax")) {
															if(!str.equals("-lxs")) {
																if(!str.equals("-xyh")) {
																	UpdateJTextArea("你对对方说："+str+"\n\n");
																	pw3.write(user.getUsername()+"（"+userh.getName()+"）说："+str+"\n");
																	pw3.flush();
																} else {
																	if(userh.getId()==Config.xyh.getId()) {
																		UpdateJTextArea("你当前的洁净点为："+userh.xyh+"\n\n");
																	} else {
																		UpdateJTextArea("你无权使用该口令。\n\n");
																	}
																}
															} else {
																if(userh.getId()==Config.lxs.getId()) {
																	UpdateJTextArea("你当前的秒杀概率为："+userh.lxsE*10+"%\n\n");
																} else {
																	UpdateJTextArea("你无权使用该口令。\n\n");
																}
															}
														} else {
															UpdateJTextArea("对方当前英雄："+enemyh.getName()+"\n最大生命值："+enemyhpt.getMaximum()+"\n最大魔法值："+enemympt.getMaximum()+"\n\n");
														}
													} else {
														UpdateJTextArea("你当前的英雄："+userh.getName()+"\n最大生命值："+userhpt.getMaximum()+"\n最大魔法值："+usermpt.getMaximum()+"\n\n");
													}
												} else {
													if(gsu!=null) {
														gsu.setVisible(true);
													} else {
														UpdateJTextArea("任务未完成或无权使用该口令。\n\n");
													}
												}
											} else {
												sur();
											}
										} else {
											setpause(0);
										}
									} else {
										setpause(1);
									}
								} else {
									getHelp();
								}
							} else {
								UpdateJTextArea("你无权使用该口令。\n\n");
							}
						} else {
							UpdateJTextArea("你无权使用该口令。\n\n");
						}
					}
				}
			}
		});
		this.add(chat);
		
		userbuff = new JPanel();
		userbuff.setForeground(Color.WHITE);
		userbuff.setOpaque(false);
		userbuff.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 3), "\u72B6\u6001", TitledBorder.CENTER, TitledBorder.TOP, null, Color.WHITE));
		userbuff.setBounds(10, 502, 248, 64);
		this.add(userbuff);
		userbuff.setLayout(new GridLayout(0, 8, 0, 0));
		
		enemybuff = new JPanel();
		enemybuff.setForeground(Color.WHITE);
		enemybuff.setOpaque(false);
		enemybuff.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 3), "\u5BF9\u65B9\u82F1\u96C4\u7684\u72B6\u6001", TitledBorder.CENTER, TitledBorder.TOP, null, Color.WHITE));
		enemybuff.setBounds(1115, 502, 241, 64);
		this.add(enemybuff);
		enemybuff.setLayout(new GridLayout(0, 8, 0, 0));
		
		UpdateJTextArea("欢迎来到Zytter！现在为热身时间，20秒后比赛正式开始。"+"\n\n");
		
		UpdateJTextArea("当前版本号："+Config.clientversion+"（v1.5a）"+"\n\n");
		
		whoact.setText("热身时间");
		
		repaint();
		
	}
	
	public void openItemBox() {
		new openDJH(this).setVisible(true);
	}
	
	public void startfight() {
		/*
		 * 回合对战
		 */
		try {
			for(int i=1;i<=limitr;i++) {
				if(i==6 || i==13 || i==20 || i==27 || i==32 || (i>35&&((i-33)%5==0))) {
					/*
					 * 商店回合
					 */
					this.setEnabled(false);
					UpdateJTextArea("- 商店回合 -\n\n");
					round.setText("商店回合");
					whoact.setText("请在商店里购买物品");
					if(i<35) {
						UpdateJTextArea("王国商店为大家发放了工资：2块金币。\n\n");
						gold+=2;
						tip1.setText("当前拥有金币："+gold);
						new openStore(this,i,20,gold,djh,user,userh).start();
						remain=22;
						remaintime.setText("还剩" + remain + "秒");
					} else {
						UpdateJTextArea("王国商店为大家发放了工资：4块金币。\n\n");
						gold+=4;
						tip1.setText("当前拥有金币："+gold);
						new openStore(this,i,15,gold,djh,user,userh).start();
						remain=17;
						remaintime.setText("还剩" + remain + "秒");
					}
					while((remain--)>0) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						remaintime.setText("还剩" + remain + "秒");
					}
					switch(i) {
						case 6:{
							userhpt.setMaximum(userhpt.getMaximum()+4);
							userh.setHp(userh.getHp()+4);
							userhpt.setValue(userh.getHp());
							userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
							userh.setDefrate(userh.getDefrate()+0.05);
							enemyhpt.setMaximum(enemyhpt.getMaximum()+4);
							enemyh.setHp(enemyh.getHp()+4);
							enemyhpt.setValue(enemyh.getHp());
							enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
							enemyh.setDefrate(enemyh.getDefrate()+0.05);
							heroup++;
							UpdateJTextArea("双方英雄永久增加了4点生命上限和5%的物理伤害减免！\n\n");
							break;
						}
						case 13:{
							userhpt.setMaximum(userhpt.getMaximum()+4);
							userh.setHp(userh.getHp()+4);
							userhpt.setValue(userh.getHp());
							userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
							userh.setDefrate(userh.getDefrate()+0.05);
							enemyhpt.setMaximum(enemyhpt.getMaximum()+4);
							enemyh.setHp(enemyh.getHp()+4);
							enemyhpt.setValue(enemyh.getHp());
							enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
							enemyh.setDefrate(enemyh.getDefrate()+0.05);
							heroup++;
							UpdateJTextArea("双方英雄永久增加了4点生命上限和5%的物理伤害减免！\n\n");
							break;
						}
						case 20:{
							userhpt.setMaximum(userhpt.getMaximum()+5);
							userh.setHp(userh.getHp()+5);
							userhpt.setValue(userh.getHp());
							userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
							userh.setDefrate(userh.getDefrate()+0.05);
							enemyhpt.setMaximum(enemyhpt.getMaximum()+5);
							enemyh.setHp(enemyh.getHp()+5);
							enemyhpt.setValue(enemyh.getHp());
							enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
							enemyh.setDefrate(enemyh.getDefrate()+0.05);
							heroup++;
							UpdateJTextArea("双方英雄永久增加了5点生命上限和5%的物理伤害减免！\n\n");
							break;
						}
						case 27:{
							userhpt.setMaximum(userhpt.getMaximum()+5);
							userh.setHp(userh.getHp()+5);
							userhpt.setValue(userh.getHp());
							userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
							userh.setDefrate(userh.getDefrate()+0.05);
							enemyhpt.setMaximum(enemyhpt.getMaximum()+5);
							enemyh.setHp(enemyh.getHp()+5);
							enemyhpt.setValue(enemyh.getHp());
							enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
							enemyh.setDefrate(enemyh.getDefrate()+0.05);
							heroup++;
							UpdateJTextArea("双方英雄永久增加了5点生命上限和5%的物理伤害减免！\n\n");
							break;
						}
					}
				}
				/*
				 * 回合开始
				 */
				r++;
				new Thread() {
					@Override
					public void run() {
						discountbuff();
					}
				}.start();
				round.setText("回合（"+r+" / "+limitr+"）");
				herotime++;
				UpdateJTextArea("- 回合（" + r + " / " + limitr + "） -\n\n");
				whoact.setText("准备时间");
				UpdateJTextArea("励兵秣马阶段：\n\n");
				usertx.setToolTipText(userh.getProperty());
				enemytx.setToolTipText(enemyh.getProperty());
				atk.setToolTipText("<html>使用普通攻击。<br /><br />提示：最多可造成"+userh.getAtk()+"点物理伤害。</html>");
				if(r==36) {
					UpdateJTextArea("比赛进入加时赛阶段，为了激励大家，王国商店推出仅有一次的活动：\n在下一个商店的时间里，购买一件重复的装备（正在穿戴的或者道具盒里有的）即可永久获得该装备的效果！\n\n");
				}
				if(!enemyh.isIslimte()||!userh.isIslimte()) {
					remain = 10;
					remaintime.setText("还剩" + remain + "秒");
					if(!enemyh.isIslimte()) {
						whoact.setText("等待对方解除行动受限");
					} else if(!userh.isIslimte()) {
						xdsx = true;
						whoact.setText("请决定是否解除受限");
						new Thread() {
							@Override
							public void run() {
								int n = JOptionPane.showConfirmDialog(null, "点击“是”使用复苏胶囊，点击“否”使用高级复苏胶囊，点击“取消”放弃使用。");
								if(n==0) {
									if(fsjn>0) {
										if(xdsx) {
											setE(4);
											fsjn--;
											djh.remove(Config.Allitems.get(4));
											UpdateJTextArea("【"+userh.getName()+"】使用了道具【复苏胶囊】！"+"\n\n");
											UpdateJTextArea("【"+userh.getName()+"】将在下三个回合获得控制免疫！\n\n");
											userh.setIsgone(true);
											userh.setIsfight(true);
											userh.setIsatk(true);
											userh.setIsskill(true);
											userh.setIslimte(true);
											userh.fs=true;
											new Thread() {
												@Override
												public void run() {
													int whenstop=r+3;
													while(true) {
														if(rend==whenstop) {
															userh.fs=false;
														}
														try {
															sleep(1000);
														} catch (InterruptedException e) {
															e.printStackTrace();
														}
													}
												}
											}.start();
											UpdateJTextArea("你已解除该回合【行动受限】。\n\n");
										} else {
											UpdateJTextArea("操作超时。\n\n");
										}
									} else {
										UpdateJTextArea("你没有【复苏胶囊】。\n\n");
									}
								} else if(n==1) {
									if(gjfsjn>0) {
										if(xdsx) {
											setE(5);
											gjfsjn--;
											djh.remove(Config.Allitems.get(5));
											UpdateJTextArea("【"+userh.getName()+"】使用了道具【高级复苏胶囊】！"+"\n\n");
											if(userh.getHp()+5<=userhpt.getMaximum()) {
												userh.setHp(userh.getHp()+5);
												hpp+=5;
											} else {
												hpp+=userhpt.getMaximum()-userh.getHp();
												userh.setHp(userhpt.getMaximum());
											}
											userhpt.setValue(userh.getHp());
											userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
											UpdateJTextArea("【"+userh.getName()+"】回复了5点生命值并将在下三个回合获得控制免疫！\n\n");
											userh.setIsgone(true);
											userh.setIsfight(true);
											userh.setIsatk(true);
											userh.setIsskill(true);
											userh.setIslimte(true);
											userh.fs=true;
											new Thread() {
												@Override
												public void run() {
													int whenstop=r+3;
													while(true) {
														if(rend==whenstop) {
															userh.fs=false;
														}
														try {
															sleep(1000);
														} catch (InterruptedException e) {
															e.printStackTrace();
														}
													}
												}
											}.start();
											UpdateJTextArea("你已解除该回合【行动受限】。\n\n");
										} else {
											UpdateJTextArea("操作超时。\n\n");
										}
									} else {
										UpdateJTextArea("你没有【高级复苏胶囊】。\n\n");
									}
								} else {
									xdsx = false;
									UpdateJTextArea("你已放弃使用【复苏类道具】。\n\n");
								}
							}
						}.start();
					}
				} else {
					remain = 3;
					remaintime.setText("还剩" + remain + "秒");
				}
				repaint();
				while ((remain--) > 0) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					remaintime.setText("还剩" + remain + "秒");
				}
				xdsx = false;
				/*
				 * 行动阶段
				 */
				UpdateJTextArea("运筹帷幄阶段：\n\n");
				usertx.setToolTipText(userh.getProperty());
				enemytx.setToolTipText(enemyh.getProperty());
				atk.setToolTipText("<html>使用普通攻击。<br /><br />提示：最多可造成"+userh.getAtk()+"点物理伤害。</html>");
				action(user, enemy, i);
				while(selected == 0 && enemyop == 0) {
					Thread.sleep(100);
				}
				/*
				 * 行动结算
				 */
				new Balance().start(); // 调用新线程来处理，省得天天死循环
				repaint();
				remain = 5;
				remaintime.setText("还剩" + remain + "秒");
				repaint();
				while ((remain--) > 0) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					remaintime.setText("还剩" + remain + "秒");
				}
				if(userh.getHp()<=0) {
					if(death(userh)==1) {
						break;
					}
				}
				if(enemyh.getHp()<=0) {
					if(death(enemyh)==1) {
						winlose=true;
						break;
					}
				}
				/*
				 * 结束阶段
				 */
				UpdateJTextArea("偃革倒戈阶段：\n\n王国商店为大家发放了工资：1块金币。\n\n");
				gold++;
				tip1.setText("当前拥有金币：" + gold);
				tip4.setText("累计造成/承受伤害："+damage+" / "+hurt);
				hppNmpp(userh);
				hppNmpp(enemyh);
				adrNark();
			}
			if(r==limitr) {
				UpdateJTextArea("回合数耗尽，英雄数量多或者英雄当前生命值百分比高的玩家获胜！\n\n");
				if(userheroes.size()>enemyheroes.size()) {
					UpdateJTextArea("本场比赛的胜利者为："+user.getUsername()+"，恭喜！！\n\n");//英雄数量
					winlose=true;
				} else if(userheroes.size()<enemyheroes.size()) {
					UpdateJTextArea("本场比赛的胜利者为："+enemy.getUsername()+"，恭喜！！\n\n");
				} else {
					double userhprate = userh.getHp()/userhpt.getMaximum();
					double enemyhprate = enemyh.getHp()/enemyhpt.getMaximum();
					if(userhprate>enemyhprate) {
						UpdateJTextArea("本场比赛的胜利者为："+user.getUsername()+"，恭喜！！\n\n");//百分比
						winlose=true;
					} else if(userhprate<enemyhprate) {
						UpdateJTextArea("本场比赛的胜利者为："+enemy.getUsername()+"，恭喜！！\n\n");
					} else {
						if(userh.getHp()>enemyh.getHp()) {
							UpdateJTextArea("本场比赛的胜利者为："+user.getUsername()+"，恭喜！！\n\n");//具体数值
							winlose=true;
						} else if(userh.getHp()<enemyh.getHp()) {
							UpdateJTextArea("本场比赛的胜利者为："+enemy.getUsername()+"，恭喜！！\n\n");
						} else {
							if(roomcreater) {
								UpdateJTextArea("本场比赛的胜利者为："+user.getUsername()+"，恭喜！！\n\n");//房间创建者
								winlose=true;
							} else {
								UpdateJTextArea("本场比赛的胜利者为："+enemy.getUsername()+"，恭喜！！\n\n");
							}
						}
					}
				}
			}
			remaintime.setText("正在为您上传比赛记录");
			UpdateJTextArea("对局已结束！正在为您上传比赛记录，请稍后……\n\n");
			if(winlose) {
				if(damage<=0) damage=1;
				if(hurt<=0) hurt=1;
				new Voice(getClass().getResourceAsStream("/bgm/win.mp3")).start();
				round.setText("对局已结束");
				whoact.setText("我们是冠军！！！");
				new Thread() {
					@Override
					public void run() {
						try {
							Thread.sleep(2500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						UpdateJTextArea("你对对方说：gg\n\n");
						UpdateJTextArea(enemy.getUsername()+"（"+enemyh.getName()+"）说：gg\n\n");
						gameover = true;
						double useror = user.getRating(),enemyor = enemy.getRating();
						int useroe = user.getElo(),enemyoe = enemy.getElo();
						getElo();//计算出elo和Rating
						if(server.getIP().equals(Host.officialserver.getIP())) {
							Config.s.addHeroPlay();
							user.setAll(user.getAll()+1);//赛季场次+1
							user.setWin(user.getWin()+1);//赛季胜场+1
							DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
							BigDecimal bg = new BigDecimal(df.format((double)user.getWin()/user.getAll()));//胜率计算
							double winrate = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//胜率计算
							user.setWinrate(winrate);//胜率计算
							Config.s.updateUser(user.getAll(),user.getWin(),user.getLose(),user.getWinrate(),user.getUid());//更新用户信息
							user.setAllplay(user.getAllplay()+1);//总场+1
							user.setAllwin(user.getAllwin()+1);//总胜场+1
							bg = new BigDecimal(df.format((double)user.getAllwin()/user.getAllplay()));//更新总胜率
							winrate = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//更新总胜率
							user.setAllwinrate(winrate);//更新总胜率
							Config.s.updateUserAllStatus(user.getAllplay(), user.getAllwin(), user.getAlllose(), user.getAllwinrate(), user.getUid());
							if(user.getAll()==1) user.setRating(rating); 
							else user.setRating(((user.getRating()*user.getAll()-1)+rating)/user.getAll());//Rating计算
							if(user.getElo()+elo<0) user.setElo(0);
							else user.setElo(user.getElo()+elo);//elo计算
							if(user.getDjs()>0) user.setDjs(user.getDjs()-1);//设置定级赛
							user.setRank(getRank());//设置段位
							Config.s.updateUser(user.getUid(),user.getRating(),user.getElo(),user.getRank(),user.getDjs());//更新用户信息
							if(user.getDjs()>0) Config.s.updateUserBestStatus(user.getRank(), 0, user.getRating(), user);//更新最佳信息
							else Config.s.updateUserBestStatus(user.getRank(), user.getElo(), user.getRating(), user);
							Config.s.setWinner(user, roomid, server);//设置对局房间胜者
							new Thread() {
								@Override
								public void run() {
									addherorate();
									Config.s.updateWinnerGameRecord(roomid, roomcreater, userbanned, userpicked, useroe, elo, rating,
											kill, death, adr, user.getDjs(), user);
								};
							}.start();
						} else {
							Config.s.updateWinnerGameRecord(roomid, roomcreater, userbanned, userpicked, useroe, 0, rating,
									kill, death, adr, user.getDjs(), user);
						}
						new BalanceGame(winlose, user, enemy, kill, death, 
								useror, enemyor, adr, getenemyadr(), useroe, enemyoe, rating, 0, Fight.this).setVisible(true);
						Config.Allheroes.clear();
						Config.Allitems.clear();
						Config.Allskills.clear();
						try {
							pw3.write("-disconnect\n");
							pw3.flush();
							dos4.writeInt(-2);
							dos4.flush();
							pw5.write("-quit\n");
							pw5.flush();
							dos5.writeInt(-2);
							dos5.flush();
						} catch (IOException e) {
						}
					}
				}.start();
			} else {
				if(damage<=0) damage=1;
				if(hurt<=0) hurt=1;
				new Voice(getClass().getResourceAsStream("/bgm/lose.mp3")).start();
				round.setText("对局已结束");
				whoact.setText("不要气馁，下次一定赢！");
				new Thread() {
					@Override
					public void run() {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						UpdateJTextArea("你对对方说：gg\n\n");
						UpdateJTextArea(enemy.getUsername()+"（"+enemyh.getName()+"）说：gg\n\n");
						gameover = true;
						String gametime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
						if(server.getIP().equals(Host.officialserver.getIP())) {
							Config.s.CreateRoomBySystem(roomid, user, gametime, "官方上海服务器", server.getIP());
						} else {
							Config.s.CreateRoomBySystem(roomid, user, gametime, server.getName(), server.getIP());
						}
						double useror = user.getRating(),enemyor = enemy.getRating();
						int useroe = user.getElo(),enemyoe = enemy.getElo();
						getElo();//计算出elo和Rating
						if(server.getIP().equals(Host.officialserver.getIP())) {
							user.setAll(user.getAll()+1);//赛季场次+1
							user.setLose(user.getLose()+1);//赛季败场+1
							DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
							BigDecimal bg = new BigDecimal(df.format((double)user.getWin()/user.getAll()));//胜率计算
							double winrate = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//胜率计算
							user.setWinrate(winrate);//胜率计算
							Config.s.updateUser(user.getAll(),user.getWin(),user.getLose(),user.getWinrate(),user.getUid());//更新用户信息
							user.setAllplay(user.getAllplay()+1);//总场+1
							user.setAlllose(user.getAlllose()+1);//总败场+1
							bg = new BigDecimal(df.format((double)user.getAllwin()/user.getAllplay()));//更新总胜率
							winrate = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//更新总胜率
							user.setAllwinrate(winrate);//更新总胜率
							Config.s.updateUserAllStatus(user.getAllplay(), user.getAllwin(), user.getAlllose(), user.getAllwinrate(), user.getUid());
							if(user.getAll()==1) user.setRating(rating); 
							else user.setRating(((user.getRating()*user.getAll()-1)+rating)/user.getAll());//Rating计算
							if(user.getElo()+elo<0) user.setElo(0);
							else user.setElo(user.getElo()+elo);//elo计算
							user.setRank(getRank());//设置段位
							Config.s.updateUser(user.getUid(),user.getRating(),user.getElo(),user.getRank(),user.getDjs());//更新用户信息
							if(user.getDjs()>0) Config.s.updateUserBestStatus(user.getRank(), 0, user.getRating(), user);//更新最佳信息
							else Config.s.updateUserBestStatus(user.getRank(), user.getElo(), user.getRating(), user);
							Config.s.setWinner(enemy, roomid, server);//设置对局房间胜者
							new Thread() {
								@Override
								public void run() {
									addherorate();
									Config.s.updateLoserGameRecord(roomid, roomcreater, userbanned, userpicked, useroe, elo, rating,
											kill, death, adr, user.getDjs(), user);
								};
							}.start();
						} else {
							Config.s.updateLoserGameRecord(roomid, roomcreater, userbanned, userpicked, useroe, 0, rating,
									kill, death, adr, user.getDjs(), user);
						}
						new BalanceGame(winlose, user, enemy, kill, death, 
								useror, enemyor, adr, getenemyadr(), useroe, enemyoe, rating, 0, Fight.this).setVisible(true);
						Config.Allheroes.clear();
						Config.Allitems.clear();
						Config.Allskills.clear();
						try {
							pw3.write("-disconnect\n");
							pw3.flush();
							dos4.writeInt(-2);
							dos4.flush();
							pw5.write("-quit\n");
							pw5.flush();
							dos5.writeInt(-2);
							dos5.flush();
						} catch (IOException e) {
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void hppNmpp(Hero h) {
		/**
		 * hppNmpp - 回合结束的生命回复与魔法回复
		 */
		if(h.equals(userh)) {
			if(userh.getHp()+userh.getHpp()<=userhpt.getMaximum()) {
				userh.setHp(userh.getHp()+userh.getHpp());
				hpp=hpp+userh.getHpp();
			} else {
				hpp=hpp+(userhpt.getMaximum()-userh.getHp());
				userh.setHp(userhpt.getMaximum());
			}
			if(userh.xyhQ) {
				int rehpp = userh.xyhQHPP;
				userh.xyhQHPP=0;
				userh.xyhQ=false;
				if(userh.getHp()+rehpp<=userhpt.getMaximum()) {
					userh.setHp(userh.getHp()+rehpp);
					hpp=hpp+rehpp;
				} else {
					hpp=hpp+(userhpt.getMaximum()-userh.getHp());
					userh.setHp(userhpt.getMaximum());
				}
				UpdateJTextArea("你的【洁净之灵】为你回复了"+rehpp+"点生命值！\n\n");
			}
			if(userh.getHp()<=userhpt.getMaximum()*0.3) {
				int rehpp = (int)Math.round(userhpt.getMaximum()*0.07);
				userh.setHp(userh.getHp()+rehpp);
				hpp=hpp+rehpp;
				UpdateJTextArea("由于你生命值低于最大生命值的30%，已给你回复"+rehpp+"点生命值。\n\n");
			}
			userhpt.setValue(userh.getHp());
			userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
			if(userh.ispjzm) {
				userh.ispjzm=false;
				UpdateJTextArea("由于【破军之矛】的影响，本回合你无法获得魔法回复。\n\n");
			} else if(userh.lmW) {
				userh.lmW=false;
				UpdateJTextArea("由于【月光剑】的影响，本回合你无法获得魔法回复。\n\n");
			} else if(userh.zxyEE) {
				userh.zxyEE=false;
				UpdateJTextArea("由于【予恋之花】的影响，本回合你无法获得魔法回复。\n\n");
			} else {
				if(userh.getMp()+userh.getMpp()<=usermpt.getMaximum()) {
					userh.setMp(userh.getMp()+userh.getMpp());
					mpp=mpp+userh.getMpp();
				} else {
					mpp=mpp+(usermpt.getMaximum()-userh.getMp());
					userh.setMp(usermpt.getMaximum());
				}
			}
			usermpt.setValue(userh.getMp());
			usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
			tip2.setText("累计生命/魔法回复："+hpp+" / "+mpp);
		} else if(h.equals(enemyh)) {
			if(enemyh.getHp()+enemyh.getHpp()<=enemyhpt.getMaximum()) {
				enemyh.setHp(enemyh.getHp()+enemyh.getHpp());
			} else {
				enemyh.setHp(enemyhpt.getMaximum());
			}
			if(enemyh.xyhQ) {
				int rehpp = enemyh.xyhQHPP;
				enemyh.xyhQHPP=0;
				enemyh.xyhQ=false;
				if(enemyh.getHp()+rehpp<=enemyhpt.getMaximum()) {
					enemyh.setHp(enemyh.getHp()+rehpp);
					hpp=hpp+rehpp;
				} else {
					hpp=hpp+(enemyhpt.getMaximum()-enemyh.getHp());
					enemyh.setHp(enemyhpt.getMaximum());
				}
				UpdateJTextArea("对方【洁净之灵】为其回复了"+rehpp+"点生命值！\n\n");
			}
			if(enemyh.getHp()<=enemyhpt.getMaximum()*0.3) {
				int rehpp = (int)Math.round(enemyhpt.getMaximum()*0.07);
				enemyh.setHp(enemyh.getHp()+rehpp);
				UpdateJTextArea("由于对方生命值低于最大生命值的30%，已给对方回复"+rehpp+"点生命值。\n\n");
			}
			enemyhpt.setValue(enemyh.getHp());
			enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
			if(enemyh.ispjzm) {
				enemyh.ispjzm=false;
			} else if(enemyh.lmW) {
				enemyh.lmW=false;
			} else if(enemyh.zxyEE) {
				enemyh.zxyEE=false;
			} else {
				if(enemyh.getMp()+enemyh.getMpp()<=enemympt.getMaximum()) {
					enemyh.setMp(enemyh.getMp()+enemyh.getMpp());
				} else {
					enemyh.setMp(enemympt.getMaximum());
				}
			}
			enemympt.setValue(enemyh.getMp());
			enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
		}
	}

	public void setrobd(int d) {
		robd+=d;
		if(robd>12&&robd<=24) {
			gold++;
			UpdateJTextArea("你累计造成12点物理伤害，从对方身上掠夺了1块金币！\n\n");
			robd-=12;
		} else if(robd>24&&robd<=36) {
			gold+=2;
			UpdateJTextArea("你累计造成24点物理伤害，从对方身上掠夺了2块金币！\n\n");
			robd-=24;
		} else if(robd>36) {
			gold+=3;
			UpdateJTextArea("你累计造成36点物理伤害，从对方身上掠夺了3块金币！\n\n");
			robd-=36;
		}
		tip1.setText("当前拥有金币："+gold);
	}
	
	public void setrobh(int h) {
		robh+=h;
		if(robh>12&&robh<=24) {
			gold--;
			UpdateJTextArea("对方累计对你造成12点物理伤害，从你身上掠夺了1块金币！\n\n");
			robh-=12;
		} else if(robh>24&&robh<=36) {
			gold-=2;
			UpdateJTextArea("对方累计对你造成24点物理伤害，从你身上掠夺了2块金币！\n\n");
			robh-=24;
		} else if(robh>36) {
			gold-=3;
			UpdateJTextArea("对方累计对你造成36点物理伤害，从你身上掠夺了3块金币！\n\n");
			robh-=36;
		}
		tip1.setText("当前拥有金币："+gold);
	}
	
	/** 英雄死亡结算
	 * @param h -  死亡的英雄
	 * @return 0 or 1 -  是否还有下一个英雄
	 */
	public int death(Hero h) {
		if(h.equals(userh)) {
			new Thread() {
				@Override
				public void run() {
					updateherostatus(Config.Allheroes.get(userh.getId()-1));
				}
			}.start();
			userh.xyhED=0;
			new Voice(getClass().getResourceAsStream("/bgm/dead.mp3")).start();
			if(userh.getZ()!=null) {
				//脱掉你的装备
				getUserEquip(userh.getZ().getId()+20);
				djh.add(userh.getZ());
				userh.setZ(null);
				zb1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e1.png")));
				zb1.setToolTipText("点击选择一个装备并穿戴。");
			}
			if(userh.getX()!=null) {
				//脱掉你的装备
				getUserEquip(userh.getX().getId()+20);
				djh.add(userh.getX());
				userh.setX(null);
				zb2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
				zb2.setToolTipText("点击选择一个装备并穿戴。");
			}
			UpdateJTextArea("你的英雄【"+userh.getName()+"】已经被击杀！对方因此获得了3金币的奖励！\n\n");
			death++;
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(ishavenext(user)) {//判断是否有下一个英雄
				//获取下一个英雄
				userh=Config.s.GetSkillByHero(getUserHero());
				//设置当前英雄
				now.setText("当前出战英雄："+userh.getName());
				//重置英雄头像
				remove(usertx);
				getUserHeroIcon(userh.getId());
				add(usertx);
				//设置下一个英雄
				usernext.setText(getUserNext());
				//设置生命值条和魔法值条
				userhpt.setMaximum(userh.getHp());
				usermpt.setMaximum(userh.getMp());
				//计算属性提升
				if(heroup==1) {
					userhpt.setMaximum(userhpt.getMaximum()+4);
					userh.setHp(userh.getHp()+4);
					userh.setDefrate(userh.getDefrate()+0.05);
				} else if(heroup==2) {
					userhpt.setMaximum(userhpt.getMaximum()+8);
					userh.setHp(userh.getHp()+8);
					userh.setDefrate(userh.getDefrate()+0.10);
				} else if(heroup==3) {
					userhpt.setMaximum(userhpt.getMaximum()+13);
					userh.setHp(userh.getHp()+13);
					userh.setDefrate(userh.getDefrate()+0.15);
				} else if(heroup==4) {
					userhpt.setMaximum(userhpt.getMaximum()+18);
					userh.setHp(userh.getHp()+18);
					userh.setDefrate(userh.getDefrate()+0.20);
				}
				userhpt.setValue(userh.getHp());
				userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				//清除结晶之力
				userh.jjzl=false;
				userh.damage=0;
				userh.jhjj=0;
				gsu=null;
				//清除所有BUFF
				userbuff.removeAll();
				if(userh.getId()==Config.lxs.getId()) {
					userbuff.add(userh.jfzm);
					userh.jfzm.setSuperpose("（当前层数："+userh.jfzm.getV1()+"）攻击力+0 护甲+0");
				}
				if(userh.getId()==Config.zkx.getId()) {
					userbuff.add(userh.xzjz);
					userh.xzjz.setSuperpose("（当前层数："+userh.xzjz.getV1()+"）");
				}
				//重新设置英雄上场回合等信息
				herotime = 0;
				herodamage = 0;
				//清空select组
				select.clear();
				//重新设置atk按钮并加入select组
				atk.setToolTipText("<html>使用普通攻击。<br /><br />提示：最多可造成"+userh.getAtk()+"点物理伤害。</html>");
				select.add(atk);
				//将item加入select组
				select.add(item);
				//重新获取技能图标并加入select组
				getSkillIcon(userh.getId());
				//公屏展示
				UpdateJTextArea("你的新英雄【"+userh.getName()+"】已上场！\n\n");
				repaint();
			} else {
				UpdateJTextArea("你已经没有能够轮换的英雄了。你输掉了这场比赛！\n\n");
				return 1;
			}
		} else if(h.equals(enemyh)) {
			enemyh.xyhED=0;
			new Voice(getClass().getResourceAsStream("/bgm/kill.mp3")).start();
			if(enemyh.getZ()!=null) {
				//脱掉他的装备
				getEnemyEquip(enemyh.getZ().getId()+60);
				enemyh.setZ(null);
			}
			if(enemyh.getX()!=null) {
				//脱掉他的装备
				getEnemyEquip(enemyh.getX().getId()+80);
				enemyh.setX(null);
			}
			UpdateJTextArea("对方的英雄【"+enemyh.getName()+"】已经被你击杀！你获得了3块金币的奖励！\n\n");
			kill++;
			gold+=3;
			tip1.setText("当前拥有金币："+gold);
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(ishavenext(enemy)) {//判断是否有下一个英雄
				//获取下一个英雄
				enemyh=Config.s.GetSkillByHero(getEnemyHero());
				//设置当前英雄
				enemyhero.setText(enemyh.getName());
				//重置英雄头像
				remove(enemytx);
				getEnemyHeroIcon(enemyh.getId());
				add(enemytx);
				//设置下一个英雄
				enemynext.setText(getEnemyNext());
				//设置生命值条和魔法值条
				enemyhpt.setMaximum(enemyh.getHp());
				enemympt.setMaximum(enemyh.getMp());
				//计算属性提升
				if(heroup==1) {
					enemyhpt.setMaximum(enemyhpt.getMaximum()+4);
					enemyh.setHp(enemyh.getHp()+4);
					enemyh.setDefrate(enemyh.getDefrate()+0.05);
				} else if(heroup==2) {
					enemyhpt.setMaximum(enemyhpt.getMaximum()+8);
					enemyh.setHp(enemyh.getHp()+8);
					enemyh.setDefrate(enemyh.getDefrate()+0.10);
				} else if(heroup==3) {
					enemyhpt.setMaximum(enemyhpt.getMaximum()+13);
					enemyh.setHp(enemyh.getHp()+13);
					enemyh.setDefrate(enemyh.getDefrate()+0.15);
				} else if(heroup==4) {
					enemyhpt.setMaximum(enemyhpt.getMaximum()+18);
					enemyh.setHp(enemyh.getHp()+18);
					enemyh.setDefrate(enemyh.getDefrate()+0.20);
				}
				enemyhpt.setValue(enemyh.getHp());
				enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				//清除所有BUFF
				enemybuff.removeAll();
				if(enemyh.getId()==Config.lxs.getId()) {
					enemybuff.add(enemyh.jfzm);
					enemyh.jfzm.setSuperpose("（当前层数："+enemyh.jfzm.getV1()+"）攻击力+0 护甲+0");
				}
				if(enemyh.getId()==Config.zkx.getId()) {
					enemybuff.add(enemyh.xzjz);
					enemyh.xzjz.setSuperpose("（当前层数："+enemyh.xzjz.getV1()+"）");
				}
				//公屏展示
				UpdateJTextArea("对方派出了新英雄【"+enemyh.getName()+"】！\n\n");
				repaint();
			} else {
				UpdateJTextArea("对方已经没有能够轮换的英雄了。这场比赛你是冠军！！\n\n");
				return 1;
			}
		}
		
		//更换背景音乐
		if(userheroes.size()==0&&enemyheroes.size()==0) { // lastbattle
			Config.bgm.setBGM(7);
		} else if(userheroes.size()==0&&enemyheroes.size()>0) { // lastonehero
			Config.bgm.setBGM(8);
		} else if(userheroes.size()>0&&enemyheroes.size()==0) { // willwin
			Config.bgm.setBGM(6);
		}
		
		return 0;
	}
	
	public void adrNark() {
		DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
		BigDecimal bg = new BigDecimal(df.format((double)damage / r));
        adr = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		if(kill!=0) ark = (int) r / kill;
		tip3.setText("平均伤害/击杀回合："+adr+" / "+ark);
	}
	
	public void balanceatk(Hero h1) {
		/**
		 * BalanceAtk - 攻击结算
		 * H1用来判定谁是伤害输出者
		 */
		if(h1.equals(userh)) {
			if(enemyh.ltjW>0) {
				p = new Random().nextInt(10);
				sendP(p);
				if(p<6) {
					UpdateJTextArea("【"+enemyh.getName()+"】发动了【闪现】的技能效果并闪避了此攻击！"+"\n\n");
				} else {
					UpdateJTextArea("【"+enemyh.getName()+"】发动了【闪现】的技能效果但未成功闪避此攻击！"+"\n\n");
					int d = userh.getAtk()-(int)Math.round(enemyh.getDef()*(1-userh.getAdp()));
					if(d<0) d=0;
					if(userh.yjg) d=(int)Math.round(d*1.36);
					if(enemyh.jrz) d=(int)Math.round(d*0.75);
					d=(int)Math.round(d*(1-enemyh.getDefrate()));
					if(userh.lmQ) d=(int)Math.round(d*0.5);
					if(enemyh.getHp()-d>0) {
						damage = damage + d;
						herodamage += d;
						enemyh.setHp(enemyh.getHp()-d);
						enemyhpt.setValue(enemyh.getHp());
						enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
					} else {
						damage = damage + (enemyh.getHp());
						herodamage += enemyh.getHp();
						enemyh.setHp(0);
						enemyhpt.setValue(0);
						enemyhpt.setString("0"+" / "+enemyhpt.getMaximum());
					}
					UpdateJTextArea("【"+enemyh.getName()+"】受到了"+d+"点物理伤害！"+"\n\n");
					tip4.setText("累计造成/承受伤害："+damage+" / "+hurt);
					setrobd(d);
					if(userh.pjzm) {
						userh.pjzm=false;
						userh.pjzmcd=2;
						enemyh.ispjzm=true;
						UpdateJTextArea("【"+enemyh.getName()+"】受到了【破军之矛】的重伤效果！"+"\n\n");
					}
					if(userh.sjjE) {
						userh.sjjE=false;
						UpdateJTextArea("【"+userh.getName()+"】发动了【光炽剑】的技能效果并回复了3点生命值！"+"\n\n");
						if(userh.getHp()+3<=userhpt.getMaximum()) {
							hpp += 3;
							userh.setHp(userh.getHp()+3);
							userhpt.setValue(userh.getHp());
							userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
						} else {
							userh.setHp(userhpt.getMaximum());
							userhpt.setValue(userh.getHp());
							userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
						}
						if(userh.jhjj==1) d = 10 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
						else d = 6 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
						if(d<0) d=0;
						balanceskill(userh, d);
						userbuff.remove(userh.gcj);
						repaint();
					}
				}
				p=0;
			} else if(enemyh.sjjQ>0) {
				p = new Random().nextInt(10);
				sendP(p);
				if(p<8) {
					UpdateJTextArea("【"+enemyh.getName()+"】发动了【闪现+】的技能效果并闪避了此攻击！"+"\n\n");
				} else {
					UpdateJTextArea("【"+enemyh.getName()+"】发动了【闪现+】的技能效果但未成功闪避此攻击！"+"\n\n");
					int d = userh.getAtk()-(int)Math.round(enemyh.getDef()*(1-userh.getAdp()));
					if(d<0) d=0;
					if(userh.yjg) d=(int)Math.round(d*1.36);
					if(enemyh.jrz) d=(int)Math.round(d*0.75);
					d=(int)Math.round(d*(1-enemyh.getDefrate()));
					if(userh.lmQ) d=(int)Math.round(d*0.5);
					if(enemyh.getHp()-d>0) {
						damage = damage + d;
						herodamage += d;
						enemyh.setHp(enemyh.getHp()-d);
						enemyhpt.setValue(enemyh.getHp());
						enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
					} else {
						damage = damage + (enemyh.getHp());
						herodamage += enemyh.getHp();
						enemyh.setHp(0);
						enemyhpt.setValue(0);
						enemyhpt.setString("0"+" / "+enemyhpt.getMaximum());
					}
					if(enemyh.sjjR>0&&d>0) {
						if(d-4>=0) {
							UpdateJTextArea("【"+enemyh.getName()+"】牺牲了一名禁卫军，抵挡了4点伤害！"+"\n\n");
							d -= 4;
						}
						else {
							UpdateJTextArea("【"+enemyh.getName()+"】牺牲了一名禁卫军，抵挡了"+d+"点伤害！"+"\n\n");
							d = 0;
						}
						enemyh.sjjR--;
						if(enemyh.sjjR!=0) {
							enemyh.gzhl.setSuperpose("（禁卫军数量："+enemyh.sjjR+"）");
						} else {
							enemybuff.remove(enemyh.gzhl);
							repaint();
						}
					}
					UpdateJTextArea("【"+enemyh.getName()+"】受到了"+d+"点物理伤害！"+"\n\n");
					tip4.setText("累计造成/承受伤害："+damage+" / "+hurt);
					setrobd(d);
					if(userh.pjzm) {
						userh.pjzm=false;
						userh.pjzmcd=2;
						enemyh.ispjzm=true;
						UpdateJTextArea("【"+enemyh.getName()+"】受到了【破军之矛】的重伤效果！"+"\n\n");
					}
					if(userh.sjjE) {
						userh.sjjE=false;
						UpdateJTextArea("【"+userh.getName()+"】发动了【光炽剑】的技能效果并回复了3点生命值！"+"\n\n");
						if(userh.getHp()+3<=userhpt.getMaximum()) {
							hpp += 3;
							userh.setHp(userh.getHp()+3);
							userhpt.setValue(userh.getHp());
							userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
						} else {
							userh.setHp(userhpt.getMaximum());
							userhpt.setValue(userh.getHp());
							userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
						}
						if(userh.jhjj==1) d = 10 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
						else d = 6 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
						if(d<0) d=0;
						balanceskill(userh, d);
						userbuff.remove(userh.gcj);
						repaint();
					}
				}
				p=0;
			} else if(enemyh.zkxW>0) {
				int d = userh.getAtk()-(int)Math.round(enemyh.getDef()*(1-userh.getAdp()));
				if(d<0) d=0;
				if(userh.yjg) d=(int)Math.round(d*1.36);
				if(enemyh.jrz) d=(int)Math.round(d*0.75);
				d=(int)Math.round(d*(1-enemyh.getDefrate()));
				if(userh.lmQ) d=(int)Math.round(d*0.5);
				enemyh.zkxWH+=d;
				enemyh.bzyy.setDescribe("该英雄完全免疫物理伤害。目前已积累（"+enemyh.zkxWH+"/20）点伤害。");
				UpdateJTextArea("【"+enemyh.getName()+"】受到了0点物理伤害！"+"\n\n");
				if(enemyh.zkxWH>=20) {
					enemyh.zkxW=0;
					UpdateJTextArea("【"+enemyh.getName()+"】的【冰之羽翼】已破碎！"+"\n\n");
					enemybuff.remove(enemyh.bzyy);
					repaint();
				}
				if(userh.sjjE) {
					userh.sjjE=false;
					UpdateJTextArea("【"+userh.getName()+"】发动了【光炽剑】的技能效果并回复了3点生命值！"+"\n\n");
					if(userh.getHp()+3<=userhpt.getMaximum()) {
						hpp += 3;
						userh.setHp(userh.getHp()+3);
						userhpt.setValue(userh.getHp());
						userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
					} else {
						userh.setHp(userhpt.getMaximum());
						userhpt.setValue(userh.getHp());
						userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
					}
					if(userh.jhjj==1) d = 10 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
					else d = 6 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
					if(d<0) d=0;
					balanceskill(userh, d);
					userbuff.remove(userh.gcj);
					repaint();
				}
			} else if(enemyh.xyhE>0) {
				int d = userh.getAtk()-(int)Math.round(enemyh.getDef()*(1-userh.getAdp()));
				if(d<0) d=0;
				if(userh.yjg) d=(int)Math.round(d*1.36);
				if(enemyh.jrz) d=(int)Math.round(d*0.75);
				d=(int)Math.round(d*(1-enemyh.getDefrate()));
				if(userh.lmQ) d=(int)Math.round(d*0.5);
				enemyh.xyhED+=d;
				UpdateJTextArea("【"+enemyh.getName()+"】受到了0点物理伤害！"+"\n\n");
				enemyh.sgsl.setDescribe("该英雄完全免疫任何伤害。目前已积累"+enemyh.xyhED+"点伤害。");
				enemyh.sgsl.setRound(enemyh.xyhE);
				repaint();
			} else {
				int d = userh.getAtk()-(int)Math.round(enemyh.getDef()*(1-userh.getAdp()));
				if(d<0) d=0;
				if(userh.yjg) d=(int)Math.round(d*1.36);
				if(enemyh.jrz) d=(int)Math.round(d*0.75);
				if(enemyh.zxyE>0) {
					enemyh.zxyEE=true;
					d = (int)Math.round(d*0.2);
				}
				d=(int)Math.round(d*(1-enemyh.getDefrate()));
				if(userh.lmQ) d=(int)Math.round(d*0.5);
				if(enemyh.getId()==Config.xyh.getId()) {
					if(enemyh.xyh+d<=8) enemyh.xyh+=d;
					else enemyh.xyh=8;
					enemyh.getQ().setMp(enemyh.xyh);
				}
				if(enemyh.sjjR>0&&d>0) {
					if(d-4>=0) {
						UpdateJTextArea("【"+enemyh.getName()+"】牺牲了一名禁卫军，抵挡了4点伤害！"+"\n\n");
						d -= 4;
					}
					else {
						UpdateJTextArea("【"+enemyh.getName()+"】牺牲了一名禁卫军，抵挡了"+d+"点伤害！"+"\n\n");
						d = 0;
					}
					enemyh.sjjR--;
					if(enemyh.sjjR!=0) {
						enemyh.gzhl.setSuperpose("（禁卫军数量："+enemyh.sjjR+"）");
					} else {
						enemybuff.remove(enemyh.gzhl);
						repaint();
					}
				}
				UpdateJTextArea("【"+enemyh.getName()+"】受到了"+d+"点物理伤害！"+"\n\n");
				tip4.setText("累计造成/承受伤害："+damage+" / "+hurt);
				setrobd(d);
				if(userh.pjzm) {
					userh.pjzm=false;
					userh.pjzmcd=2;
					enemyh.ispjzm=true;
					UpdateJTextArea("【"+enemyh.getName()+"】受到了【破军之矛】的重伤效果！"+"\n\n");
				}
				if(enemyh.getHp()-d>0) {
					damage = damage + d;
					herodamage += d;
					enemyh.setHp(enemyh.getHp()-d);
					enemyhpt.setValue(enemyh.getHp());
					enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
				} else {
					damage = damage + (enemyh.getHp());
					herodamage += enemyh.getHp();
					enemyh.setHp(0);
					enemyhpt.setValue(0);
					enemyhpt.setString("0"+" / "+enemyhpt.getMaximum());
				}
				if(userh.sjjE) {
					userh.sjjE=false;
					UpdateJTextArea("【"+userh.getName()+"】发动了【光炽剑】的技能效果并回复了3点生命值！"+"\n\n");
					if(userh.getHp()+3<=userhpt.getMaximum()) {
						hpp += 3;
						userh.setHp(userh.getHp()+3);
						userhpt.setValue(userh.getHp());
						userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
					} else {
						userh.setHp(userhpt.getMaximum());
						userhpt.setValue(userh.getHp());
						userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
					}
					if(userh.jhjj==1) d = 10 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
					else d = 6 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
					if(d<0) d=0;
					balanceskill(userh, d);
					userbuff.remove(userh.gcj);
					repaint();
				}
			}
		} else if(h1.equals(enemyh)) {
			if(userh.ltjW>0) {
				try {
					p = dis5.readInt();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(p<6) {
					UpdateJTextArea("【"+userh.getName()+"】发动了【闪现】的技能效果并闪避了此攻击！"+"\n\n");
				} else {
					UpdateJTextArea("【"+userh.getName()+"】发动了【闪现】的技能效果但未成功闪避此攻击！"+"\n\n");
					int d = enemyh.getAtk()-(int)Math.round(userh.getDef()*(1-enemyh.getAdp()));
					if(d<0) d=0;
					if(enemyh.yjg) d=(int)Math.round(d*1.36);
					if(userh.jrz) d=(int)Math.round(d*0.75);
					d=(int)Math.round(d*(1-userh.getDefrate()));
					if(enemyh.lmQ) d=(int)Math.round(d*0.5);
					if(userh.getHp()-d>0) {
						hurt = hurt + d;
						userh.setHp(userh.getHp()-d);
						userhpt.setValue(userh.getHp());
						userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
					} else {
						hurt = hurt + (d-userh.getHp());
						userh.setHp(0);
						userhpt.setValue(0);
						userhpt.setString("0"+" / "+userhpt.getMaximum());
					}
					UpdateJTextArea("【"+userh.getName()+"】受到了"+d+"点物理伤害！"+"\n\n");
					tip4.setText("累计造成/承受伤害："+damage+" / "+hurt);
					setrobh(d);
					if(enemyh.pjzm) {
						enemyh.pjzm=false;
						enemyh.pjzmcd=2;
						userh.ispjzm=true;
						UpdateJTextArea("【"+userh.getName()+"】受到了【破军之矛】的重伤效果！"+"\n\n");
					}
					if(enemyh.sjjE) {
						enemyh.sjjE=false;
						UpdateJTextArea("【"+enemyh.getName()+"】发动了【光炽剑】的技能效果并回复了3点生命值！"+"\n\n");
						if(enemyh.getHp()+3<=enemyhpt.getMaximum()) {
							enemyh.setHp(enemyh.getHp()+3);
							enemyhpt.setValue(enemyh.getHp());
							enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
						} else {
							enemyh.setHp(enemyhpt.getMaximum());
							enemyhpt.setValue(enemyh.getHp());
							enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
						}
						if(enemyh.jhjj==1) d = 10 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
						else d = 6 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
						if(d<0) d=0;
						balanceskill(enemyh, d);
						enemybuff.remove(enemyh.gcj);
						repaint();
					}
				}
				p=0;
			} else if(userh.sjjQ>0) {
				try {
					p = dis5.readInt();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(p<8) {
					UpdateJTextArea("【"+userh.getName()+"】发动了【闪现+】的技能效果并闪避了此攻击！"+"\n\n");
				} else {
					UpdateJTextArea("【"+userh.getName()+"】发动了【闪现+】的技能效果但未成功闪避此攻击！"+"\n\n");
					int d = enemyh.getAtk()-(int)Math.round(userh.getDef()*(1-enemyh.getAdp()));
					if(d<0) d=0;
					if(enemyh.yjg) d=(int)Math.round(d*1.36);
					if(userh.jrz) d=(int)Math.round(d*0.75);
					d=(int)Math.round(d*(1-userh.getDefrate()));
					if(enemyh.lmQ) d=(int)Math.round(d*0.5);
					if(userh.getHp()-d>0) {
						hurt = hurt + d;
						userh.setHp(userh.getHp()-d);
						userhpt.setValue(userh.getHp());
						userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
					} else {
						hurt = hurt + (d-userh.getHp());
						userh.setHp(0);
						userhpt.setValue(0);
						userhpt.setString("0"+" / "+userhpt.getMaximum());
					}
					if(userh.sjjR>0&&d>0) {
						if(d-4>=0) {
							UpdateJTextArea("【"+userh.getName()+"】牺牲了一名禁卫军，抵挡了4点伤害！"+"\n\n");
							d -= 4;
						}
						else {
							UpdateJTextArea("【"+userh.getName()+"】牺牲了一名禁卫军，抵挡了"+d+"点伤害！"+"\n\n");
							d = 0;
						}
						userh.sjjR--;
						if(userh.sjjR!=0) {
							userh.gzhl.setSuperpose("（禁卫军数量："+userh.sjjR+"）");
						} else {
							userbuff.remove(userh.gzhl);
							repaint();
						}
					}
					UpdateJTextArea("【"+userh.getName()+"】受到了"+d+"点物理伤害！"+"\n\n");
					tip4.setText("累计造成/承受伤害："+damage+" / "+hurt);
					setrobh(d);
					if(enemyh.pjzm) {
						enemyh.pjzm=false;
						enemyh.pjzmcd=2;
						userh.ispjzm=true;
						UpdateJTextArea("【"+userh.getName()+"】受到了【破军之矛】的重伤效果！"+"\n\n");
					}
					if(enemyh.sjjE) {
						enemyh.sjjE=false;
						UpdateJTextArea("【"+enemyh.getName()+"】发动了【光炽剑】的技能效果并回复了3点生命值！"+"\n\n");
						if(enemyh.getHp()+3<=enemyhpt.getMaximum()) {
							enemyh.setHp(enemyh.getHp()+3);
							enemyhpt.setValue(enemyh.getHp());
							enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
						} else {
							enemyh.setHp(enemyhpt.getMaximum());
							enemyhpt.setValue(enemyh.getHp());
							enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
						}
						if(enemyh.jhjj==1) d = 10 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
						else d = 6 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
						if(d<0) d=0;
						balanceskill(enemyh, d);
						enemybuff.remove(enemyh.gcj);
						repaint();
					}
				}
				p=0;
			} else if(userh.zkxW>0) {
				int d = enemyh.getAtk()-(int)Math.round(userh.getDef()*(1-enemyh.getAdp()));
				if(d<0) d=0;
				if(enemyh.yjg) d=(int)Math.round(d*1.36);
				if(userh.jrz) d=(int)Math.round(d*0.75);
				d=(int)Math.round(d*(1-userh.getDefrate()));
				if(enemyh.lmQ) d=(int)Math.round(d*0.5);
				userh.zkxWH+=d;
				userh.bzyy.setDescribe("该英雄完全免疫物理伤害。目前已积累（"+userh.zkxWH+"/20）点伤害。");
				UpdateJTextArea("【"+userh.getName()+"】受到了0点物理伤害！"+"\n\n");
				if(userh.zkxWH>=20) {
					userh.zkxW=0;
					UpdateJTextArea("【"+userh.getName()+"】的【冰之羽翼】已破碎！"+"\n\n");
					userbuff.remove(userh.bzyy);
					repaint();
				}
				if(enemyh.sjjE) {
					enemyh.sjjE=false;
					UpdateJTextArea("【"+enemyh.getName()+"】发动了【光炽剑】的技能效果并回复了3点生命值！"+"\n\n");
					if(enemyh.getHp()+3<=enemyhpt.getMaximum()) {
						hpp += 3;
						enemyh.setHp(enemyh.getHp()+3);
						enemyhpt.setValue(enemyh.getHp());
						enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
					} else {
						enemyh.setHp(enemyhpt.getMaximum());
						enemyhpt.setValue(enemyh.getHp());
						enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
					}
					if(enemyh.jhjj==1) d = 10 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
					else d = 6 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
					if(d<0) d=0;
					balanceskill(enemyh, d);
					enemybuff.remove(enemyh.gcj);
					repaint();
				}
			} else if(userh.xyhE>0) {
				int d = enemyh.getAtk()-(int)Math.round(userh.getDef()*(1-enemyh.getAdp()));
				if(d<0) d=0;
				if(enemyh.yjg) d=(int)Math.round(d*1.36);
				if(userh.jrz) d=(int)Math.round(d*0.75);
				d=(int)Math.round(d*(1-userh.getDefrate()));
				if(enemyh.lmQ) d=(int)Math.round(d*0.5);
				userh.xyhED+=d;
				UpdateJTextArea("【"+userh.getName()+"】受到了0点物理伤害！"+"\n\n");
				userh.sgsl.setDescribe("该英雄完全免疫任何伤害。目前已积累"+userh.xyhED+"点伤害。");
				userh.sgsl.setRound(userh.xyhE);
				repaint();
			} else {
				int d = enemyh.getAtk()-(int)Math.round(userh.getDef()*(1-enemyh.getAdp()));
				if(d<0) d=0;
				if(enemyh.yjg) d=(int)Math.round(d*1.36);
				if(userh.jrz) d=(int)Math.round(d*0.75);
				if(userh.zxyE>0) {
					userh.zxyEE=true;
					d = (int)Math.round(d*0.2);
				}
				d=(int)Math.round(d*(1-userh.getDefrate()));
				if(enemyh.lmQ) d=(int)Math.round(d*0.5);
				if(userh.getId()==Config.xyh.getId()) {
					if(userh.xyh+d<=8) userh.xyh+=d;
					else userh.xyh=8;
					userh.getQ().setMp(userh.xyh);
					skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
				}
				if(userh.sjjR>0&&d>0) {
					if(d-4>=0) {
						UpdateJTextArea("【"+userh.getName()+"】牺牲了一名禁卫军，抵挡了4点伤害！"+"\n\n");
						d -= 4;
					}
					else {
						UpdateJTextArea("【"+userh.getName()+"】牺牲了一名禁卫军，抵挡了"+d+"点伤害！"+"\n\n");
						d = 0;
					}
					userh.sjjR--;
					if(userh.sjjR!=0) {
						userh.gzhl.setSuperpose("（禁卫军数量："+userh.sjjR+"）");
					} else {
						userbuff.remove(userh.gzhl);
						repaint();
					}
				}
				UpdateJTextArea("【"+userh.getName()+"】受到了"+d+"点物理伤害！"+"\n\n");
				tip4.setText("累计造成/承受伤害："+damage+" / "+hurt);
				setrobh(d);
				if(enemyh.pjzm) {
					enemyh.pjzm=false;
					enemyh.pjzmcd=2;
					userh.ispjzm=true;
					UpdateJTextArea("【"+userh.getName()+"】受到了【破军之矛】的重伤效果！"+"\n\n");
				}
				if(userh.getHp()-d>0) {
					hurt = hurt + d;
					userh.setHp(userh.getHp()-d);
					userhpt.setValue(userh.getHp());
					userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
				} else {
					hurt = hurt + (d-userh.getHp());
					userh.setHp(0);
					userhpt.setValue(0);
					userhpt.setString("0"+" / "+userhpt.getMaximum());
				}
				if(enemyh.sjjE) {
					enemyh.sjjE=false;
					UpdateJTextArea("【"+enemyh.getName()+"】发动了【光炽剑】的技能效果并回复了3点生命值！"+"\n\n");
					if(enemyh.getHp()+3<=enemyhpt.getMaximum()) {
						enemyh.setHp(enemyh.getHp()+3);
						enemyhpt.setValue(enemyh.getHp());
						enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
					} else {
						enemyh.setHp(enemyhpt.getMaximum());
						enemyhpt.setValue(enemyh.getHp());
						enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
					}
					if(enemyh.jhjj==1) d = 10 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
					else d = 6 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
					if(d<0) d=0;
					balanceskill(enemyh, d);
					enemybuff.remove(enemyh.gcj);
					repaint();
				}
			}
		}
		
	}
	
	/**
	 * 物理伤害结算
	 */
	public void balancephyical(Hero from,int d) {
		if(from.equals(userh)) {
			if(enemyh.zkxW>0) {
				enemyh.zkxWH+=d;
				enemyh.bzyy.setDescribe("该英雄完全免疫物理伤害。目前已积累（"+enemyh.zkxWH+"/20）点伤害。");
				UpdateJTextArea("【"+enemyh.getName()+"】受到了0点物理伤害！"+"\n\n");
				if(enemyh.zkxWH>=20) {
					enemyh.zkxW=0;
					UpdateJTextArea("【"+enemyh.getName()+"】的【冰之羽翼】已破碎！"+"\n\n");
					enemybuff.remove(enemyh.bzyy);
					repaint();
				}
			} else {
				if(enemyh.jrz) d=(int)Math.round(d*0.75);
				if(enemyh.zxyE>0) {
					enemyh.zxyEE=true;
					d = (int)Math.round(d*0.2);
				}
				d=(int)Math.round(d*(1-enemyh.getDefrate()));
				if(enemyh.getId()==Config.xyh.getId()) {
					if(enemyh.xyh+d<=8) enemyh.xyh+=d;
					else enemyh.xyh=8;
					enemyh.getQ().setMp(enemyh.xyh);
				}
				if(enemyh.sjjR>0&&d>0) {
					if(d-4>=0) {
						UpdateJTextArea("【"+enemyh.getName()+"】牺牲了一名禁卫军，抵挡了4点伤害！"+"\n\n");
						d -= 4;
					}
					else {
						UpdateJTextArea("【"+enemyh.getName()+"】牺牲了一名禁卫军，抵挡了"+d+"点伤害！"+"\n\n");
						d = 0;
					}
					enemyh.sjjR--;
					if(enemyh.sjjR!=0) {
						enemyh.gzhl.setSuperpose("（禁卫军数量："+enemyh.sjjR+"）");
					} else {
						enemybuff.remove(enemyh.gzhl);
						repaint();
					}
				}
				if(enemyh.xyhE>0) {
					enemyh.xyhED+=d;
					d=0;
					enemyh.sgsl.setDescribe("该英雄完全免疫任何伤害。目前已积累"+enemyh.xyhED+"点伤害。");
					enemyh.sgsl.setRound(enemyh.xyhE);
					repaint();
				}
				UpdateJTextArea("【"+enemyh.getName()+"】受到了"+d+"点物理伤害！\n\n");
				setrobd(d);
				if(userh.pjzm) {
					userh.pjzm=false;
					userh.pjzmcd=2;
					enemyh.ispjzm=true;
					UpdateJTextArea("【"+enemyh.getName()+"】受到了【破军之矛】的重伤效果！"+"\n\n");
				}
				if(enemyh.getHp()-d>0) {
					damage += d;
					herodamage += d;
					enemyh.setHp(enemyh.getHp()-d);
					enemyhpt.setValue(enemyh.getHp());
					enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
				} else {
					damage += (enemyh.getHp());
					herodamage += enemyh.getHp();
					enemyh.setHp(0);
					enemyhpt.setValue(0);
					enemyhpt.setString("0"+" / "+enemyhpt.getMaximum());
				}
				tip4.setText("累计造成/承受伤害："+damage+" / "+hurt);
			}
			if(userh.zy) {
				new Thread() {
					@Override
					public void run() {
						int whenstart=r+1;
						int whenstop=r+2;
						while(true) {
							if(rend==whenstart) {
								if(userh.getHp()>0) {
									int d = 4 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
									if(d<0) d=0;
									UpdateJTextArea("【"+userh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
									balancezy(userh, d);
								}
								break;
							}
							try {
								sleep(700);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						while(true) {
							if(rend==whenstop) {
								if(userh.getHp()>0) {
									int d = 4 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
									if(d<0) d=0;
									UpdateJTextArea("【"+userh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
									balancezy(userh, d);
								}
								break;
							}
							try {
								sleep(700);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
			}
		} else if(from.equals(enemyh)) {
			if(userh.zkxW>0) {
				userh.zkxWH+=d;
				userh.bzyy.setDescribe("该英雄完全免疫物理伤害。目前已积累（"+userh.zkxWH+"/20）点伤害。");
				UpdateJTextArea("【"+userh.getName()+"】受到了0点物理伤害！"+"\n\n");
				if(userh.zkxWH>=20) {
					userh.zkxW=0;
					UpdateJTextArea("【"+userh.getName()+"】的【冰之羽翼】已破碎！"+"\n\n");
					userbuff.remove(userh.bzyy);
					repaint();
				}
			} else {
				if(userh.jrz) d=(int)Math.round(d*0.75);
				if(userh.zxyE>0) {
					userh.zxyEE=true;
					d = (int)Math.round(d*0.2);
				}
				d=(int)Math.round(d*(1-userh.getDefrate()));
				if(userh.getId()==Config.xyh.getId()) {
					if(userh.xyh+d<=8) userh.xyh+=d;
					else userh.xyh=8;
					userh.getQ().setMp(userh.xyh);
					skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
				}
				if(userh.sjjR>0&&d>0) {
					if(d-4>=0) {
						UpdateJTextArea("【"+userh.getName()+"】牺牲了一名禁卫军，抵挡了4点伤害！"+"\n\n");
						d -= 4;
					}
					else {
						UpdateJTextArea("【"+userh.getName()+"】牺牲了一名禁卫军，抵挡了"+d+"点伤害！"+"\n\n");
						d = 0;
					}
					userh.sjjR--;
					if(userh.sjjR!=0) {
						userh.gzhl.setSuperpose("（禁卫军数量："+userh.sjjR+"）");
					} else {
						userbuff.remove(userh.gzhl);
						repaint();
					}
				}
				if(userh.xyhE>0) {
					userh.xyhED+=d;
					d=0;
					userh.sgsl.setDescribe("该英雄完全免疫任何伤害。目前已积累"+userh.xyhED+"点伤害。");
					userh.sgsl.setRound(userh.xyhE);
					repaint();
				}
				UpdateJTextArea("【"+userh.getName()+"】受到了"+d+"点物理伤害！\n\n");
				setrobh(d);
				if(enemyh.pjzm) {
					enemyh.pjzm=false;
					enemyh.pjzmcd=2;
					userh.ispjzm=true;
					UpdateJTextArea("【"+userh.getName()+"】受到了【破军之矛】的重伤效果！"+"\n\n");
				}
				if(userh.getHp()-d>0) {
					hurt += d;
					userh.setHp(userh.getHp()-d);
					userhpt.setValue(userh.getHp());
					userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
				} else {
					hurt += (userh.getHp());
					userh.setHp(0);
					userhpt.setValue(0);
					userhpt.setString("0"+" / "+userhpt.getMaximum());
				}
				tip4.setText("累计造成/承受伤害："+damage+" / "+hurt);
			}
			if(enemyh.zy) {
				new Thread() {
					@Override
					public void run() {
						int whenstart=r+1;
						int whenstop=r+2;
						while(true) {
							if(rend==whenstart) {
								if(enemyh.getHp()>0) {
									int d = 4 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
									if(d<0) d=0;
									UpdateJTextArea("【"+enemyh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
									balancezy(enemyh, d);
								}
								break;
							}
							try {
								sleep(700);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						while(true) {
							if(rend==whenstop) {
								if(enemyh.getHp()>0) {
									int d = 4 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
									if(d<0) d=0;
									UpdateJTextArea("【"+enemyh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
									balancezy(enemyh, d);
								}
								break;
							}
							try {
								sleep(700);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
			}
		}
	}
	
	/**
	 * 真实伤害结算
	 */
	public void balancereal(Hero from,int d) {
		if(from.equals(userh)) {
			UpdateJTextArea("【"+enemyh.getName()+"】受到了"+d+"点真实伤害！\n\n");
			if(enemyh.getHp()-d>0) {
				damage += d;
				herodamage += d;
				enemyh.setHp(enemyh.getHp()-d);
				enemyhpt.setValue(enemyh.getHp());
				enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
			} else {
				damage += (enemyh.getHp());
				herodamage += enemyh.getHp();
				enemyh.setHp(0);
				enemyhpt.setValue(0);
				enemyhpt.setString("0"+" / "+enemyhpt.getMaximum());
			}
			tip4.setText("累计造成/承受伤害："+damage+" / "+hurt);
			if(userh.zy) {
				new Thread() {
					@Override
					public void run() {
						int whenstart=r+1;
						int whenstop=r+2;
						while(true) {
							if(rend==whenstart) {
								if(userh.getHp()>0) {
									int d = 4 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
									if(d<0) d=0;
									UpdateJTextArea("【"+userh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
									balancezy(userh, d);
								}
								break;
							}
							try {
								sleep(700);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						while(true) {
							if(rend==whenstop) {
								if(userh.getHp()>0) {
									int d = 4 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
									if(d<0) d=0;
									UpdateJTextArea("【"+userh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
									balancezy(userh, d);
								}
								break;
							}
							try {
								sleep(700);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
			}
		} else if(from.equals(enemyh)) {
			UpdateJTextArea("【"+userh.getName()+"】受到了"+d+"点真实伤害！\n\n");
			if(userh.getHp()-d>0) {
				hurt += d;
				userh.setHp(userh.getHp()-d);
				userhpt.setValue(userh.getHp());
				userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
			} else {
				hurt += (userh.getHp());
				userh.setHp(0);
				userhpt.setValue(0);
				userhpt.setString("0"+" / "+userhpt.getMaximum());
			}
			tip4.setText("累计造成/承受伤害："+damage+" / "+hurt);
			if(enemyh.zy) {
				new Thread() {
					@Override
					public void run() {
						int whenstart=r+1;
						int whenstop=r+2;
						while(true) {
							if(rend==whenstart) {
								if(enemyh.getHp()>0) {
									int d = 4 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
									if(d<0) d=0;
									UpdateJTextArea("【"+enemyh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
									balancezy(enemyh, d);
								}
								break;
							}
							try {
								sleep(700);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						while(true) {
							if(rend==whenstop) {
								if(enemyh.getHp()>0) {
									int d = 4 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
									if(d<0) d=0;
									UpdateJTextArea("【"+enemyh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
									balancezy(enemyh, d);
								}
								break;
							}
							try {
								sleep(700);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
			}
		}
	}
	
	/**
	 * 魔法伤害结算
	 */
	public void balanceskill(Hero from,int d) {
		if(from.equals(userh)) {
			if(enemyh.getId()==Config.xyh.getId()) {
				if(enemyh.xyh+d<=8) enemyh.xyh+=d;
				else enemyh.xyh=8;
				enemyh.getQ().setMp(enemyh.xyh);
			}
			if(enemyh.sjjR>0&&d>0) {
				if(d-4>=0) {
					UpdateJTextArea("【"+enemyh.getName()+"】牺牲了一名禁卫军，抵挡了4点伤害！"+"\n\n");
					d -= 4;
				}
				else {
					UpdateJTextArea("【"+enemyh.getName()+"】牺牲了一名禁卫军，抵挡了"+d+"点伤害！"+"\n\n");
					d = 0;
				}
				enemyh.sjjR--;
				if(enemyh.sjjR!=0) {
					enemyh.gzhl.setSuperpose("（禁卫军数量："+enemyh.sjjR+"）");
				} else {
					enemybuff.remove(enemyh.gzhl);
					repaint();
				}
			}
			if(enemyh.xyhE>0) {
				enemyh.xyhED+=d;
				d=0;
				enemyh.sgsl.setDescribe("该英雄完全免疫任何伤害。目前已积累"+enemyh.xyhED+"点伤害。");
				enemyh.sgsl.setRound(enemyh.xyhE);
				repaint();
			}
			if(userh.hyqQ>0&&userh.jhjj==3) {
				d+=(int)Math.round(d*userh.hyqJ);
			}
			UpdateJTextArea("【"+enemyh.getName()+"】受到了"+d+"点魔法伤害！\n\n");
			if(enemyh.getHp()-d>0) {
				damage += d;
				herodamage += d;
				enemyh.setHp(enemyh.getHp()-d);
				enemyhpt.setValue(enemyh.getHp());
				enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
			} else {
				damage += (enemyh.getHp());
				herodamage += enemyh.getHp();
				enemyh.setHp(0);
				enemyhpt.setValue(0);
				enemyhpt.setString("0"+" / "+enemyhpt.getMaximum());
			}
			userh.damage+=d;
			if(userh.zxyQ) {
				userh.zxyQ=false;
				UpdateJTextArea("【"+userh.getName()+"】发动了【礼赞】的技能效果，减少了"+enemyh.getName()+"的魔法值！"+"\n\n");
				int mp = (int)Math.round(enemympt.getMaximum()*0.42);
				if(enemyh.getMp()-mp>=0) {
					enemyh.setMp(enemyh.getMp()-mp);
				} else {
					enemyh.setMp(0);
				}
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				userbuff.remove(userh.lz);
				repaint();
			}
			if(userh.zy) {
				new Thread() {
					@Override
					public void run() {
						int whenstart=r+1;
						int whenstop=r+2;
						while(true) {
							if(rend==whenstart) {
								if(userh.getHp()>0) {
									int d = 4 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
									if(d<0) d=0;
									UpdateJTextArea("【"+userh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
									balancezy(userh, d);
								}
								break;
							}
							try {
								sleep(700);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						while(true) {
							if(rend==whenstop) {
								if(userh.getHp()>0) {
									int d = 4 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
									if(d<0) d=0;
									UpdateJTextArea("【"+userh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
									balancezy(userh, d);
								}
								break;
							}
							try {
								sleep(700);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
			}
			tip4.setText("累计造成/承受伤害："+damage+" / "+hurt);
		} else if(from.equals(enemyh)) {
			if(userh.getId()==Config.xyh.getId()) {
				if(userh.xyh+d<=8) userh.xyh+=d;
				else userh.xyh=8;
				userh.getQ().setMp(userh.xyh);
				skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
			}
			if(userh.sjjR>0&&d>0) {
				if(d-4>=0) {
					UpdateJTextArea("【"+userh.getName()+"】牺牲了一名禁卫军，抵挡了4点伤害！"+"\n\n");
					d -= 4;
				}
				else {
					UpdateJTextArea("【"+userh.getName()+"】牺牲了一名禁卫军，抵挡了"+d+"点伤害！"+"\n\n");
					d = 0;
				}
				userh.sjjR--;
				if(userh.sjjR!=0) {
					userh.gzhl.setSuperpose("（禁卫军数量："+userh.sjjR+"）");
				} else {
					userbuff.remove(userh.gzhl);
					repaint();
				}
			}
			if(userh.xyhE>0) {
				userh.xyhED+=d;
				d=0;
				userh.sgsl.setDescribe("该英雄完全免疫任何伤害。目前已积累"+userh.xyhED+"点伤害。");
				userh.sgsl.setRound(userh.xyhE);
				repaint();
			}
			if(enemyh.hyqQ>0&&enemyh.jhjj==3) {
				d+=(int)Math.round(d*enemyh.hyqJ);
			}
			UpdateJTextArea("【"+userh.getName()+"】受到了"+d+"点魔法伤害！\n\n");
			if(userh.getHp()-d>0) {
				hurt += d;
				userh.setHp(userh.getHp()-d);
				userhpt.setValue(userh.getHp());
				userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
			} else {
				hurt += (userh.getHp());
				userh.setHp(0);
				userhpt.setValue(0);
				userhpt.setString("0"+" / "+userhpt.getMaximum());
			}
			enemyh.damage+=d;
			if(enemyh.zxyQ) {
				enemyh.zxyQ=false;
				UpdateJTextArea("【"+enemyh.getName()+"】发动了【礼赞】的技能效果，减少了"+userh.getName()+"的魔法值！"+"\n\n");
				int mp = (int)Math.round(usermpt.getMaximum()*0.42);
				if(userh.getMp()-mp>=0) {
					userh.setMp(userh.getMp()-mp);
				} else {
					userh.setMp(0);
				}
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				enemybuff.remove(enemyh.lz);
				repaint();
			}
			if(enemyh.zy) {
				new Thread() {
					@Override
					public void run() {
						int whenstart=r+1;
						int whenstop=r+2;
						while(true) {
							if(rend==whenstart) {
								if(enemyh.getHp()>0) {
									int d = 4 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
									if(d<0) d=0;
									UpdateJTextArea("【"+enemyh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
									balancezy(enemyh, d);
								}
								break;
							}
							try {
								sleep(700);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						while(true) {
							if(rend==whenstop) {
								if(enemyh.getHp()>0) {
									int d = 4 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
									if(d<0) d=0;
									UpdateJTextArea("【"+enemyh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
									balancezy(enemyh, d);
								}
								break;
							}
							try {
								sleep(700);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
			}
			tip4.setText("累计造成/承受伤害："+damage+" / "+hurt);
		}
	}
	
	/**
	 * 紫月神杖魔法伤害结算
	 */
	public void balancezy(Hero from,int d) {
		if(from.equals(userh)) {
			if(enemyh.getId()==Config.xyh.getId()) {
				if(enemyh.xyh+d<=8) enemyh.xyh+=d;
				else enemyh.xyh=8;
				enemyh.getQ().setMp(enemyh.xyh);
			}
			if(enemyh.sjjR>0&&d>0) {
				if(d-4>=0) {
					UpdateJTextArea("【"+enemyh.getName()+"】牺牲了一名禁卫军，抵挡了4点伤害！"+"\n\n");
					d -= 4;
				}
				else {
					UpdateJTextArea("【"+enemyh.getName()+"】牺牲了一名禁卫军，抵挡了"+d+"点伤害！"+"\n\n");
					d = 0;
				}
				enemyh.sjjR--;
				if(enemyh.sjjR!=0) {
					enemyh.gzhl.setSuperpose("（禁卫军数量："+enemyh.sjjR+"）");
				} else {
					enemybuff.remove(enemyh.gzhl);
					repaint();
				}
			}
			if(enemyh.xyhE>0) {
				enemyh.xyhED+=d;
				d=0;
				enemyh.sgsl.setDescribe("该英雄完全免疫任何伤害。目前已积累"+enemyh.xyhED+"点伤害。");
				enemyh.sgsl.setRound(enemyh.xyhE);
				repaint();
			}
			if(userh.hyqQ>0&&userh.jhjj==3) {
				d+=(int)Math.round(d*userh.hyqJ);
			}
			UpdateJTextArea("【"+enemyh.getName()+"】受到了"+d+"点魔法伤害！\n\n");
			if(enemyh.getHp()-d>0) {
				damage += d;
				herodamage += d;
				enemyh.setHp(enemyh.getHp()-d);
				enemyhpt.setValue(enemyh.getHp());
				enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
			} else {
				damage += (enemyh.getHp());
				herodamage += enemyh.getHp();
				enemyh.setHp(0);
				enemyhpt.setValue(0);
				enemyhpt.setString("0"+" / "+enemyhpt.getMaximum());
			}
			userh.damage+=d;
			if(userh.zxyQ) {
				userh.zxyQ=false;
				UpdateJTextArea("【"+userh.getName()+"】发动了【礼赞】的技能效果，减少了"+enemyh.getName()+"的魔法值！"+"\n\n");
				int mp = (int)Math.round(enemympt.getMaximum()*0.42);
				if(enemyh.getMp()-mp>=0) {
					enemyh.setMp(enemyh.getMp()-mp);
				} else {
					enemyh.setMp(0);
				}
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				userbuff.remove(userh.lz);
				repaint();
			}
			tip4.setText("累计造成/承受伤害："+damage+" / "+hurt);
		} else if(from.equals(enemyh)) {
			if(userh.getId()==Config.xyh.getId()) {
				if(userh.xyh+d<=8) userh.xyh+=d;
				else userh.xyh=8;
				userh.getQ().setMp(userh.xyh);
				skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
			}
			if(userh.sjjR>0&&d>0) {
				if(d-4>=0) {
					UpdateJTextArea("【"+userh.getName()+"】牺牲了一名禁卫军，抵挡了4点伤害！"+"\n\n");
					d -= 4;
				}
				else {
					UpdateJTextArea("【"+userh.getName()+"】牺牲了一名禁卫军，抵挡了"+d+"点伤害！"+"\n\n");
					d = 0;
				}
				userh.sjjR--;
				if(userh.sjjR!=0) {
					userh.gzhl.setSuperpose("（禁卫军数量："+userh.sjjR+"）");
				} else {
					userbuff.remove(userh.gzhl);
					repaint();
				}
			}
			if(userh.xyhE>0) {
				userh.xyhED+=d;
				d=0;
				userh.sgsl.setDescribe("该英雄完全免疫任何伤害。目前已积累"+userh.xyhED+"点伤害。");
				userh.sgsl.setRound(userh.xyhE);
				repaint();
			}
			if(enemyh.hyqQ>0&&enemyh.jhjj==3) {
				d+=(int)Math.round(d*enemyh.hyqJ);
			}
			UpdateJTextArea("【"+userh.getName()+"】受到了"+d+"点魔法伤害！\n\n");
			if(userh.getHp()-d>0) {
				hurt += d;
				userh.setHp(userh.getHp()-d);
				userhpt.setValue(userh.getHp());
				userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
			} else {
				hurt += (userh.getHp());
				userh.setHp(0);
				userhpt.setValue(0);
				userhpt.setString("0"+" / "+userhpt.getMaximum());
			}
			enemyh.damage+=d;
			if(enemyh.zxyQ) {
				enemyh.zxyQ=false;
				UpdateJTextArea("【"+enemyh.getName()+"】发动了【礼赞】的技能效果，减少了"+userh.getName()+"的魔法值！"+"\n\n");
				int mp = (int)Math.round(usermpt.getMaximum()*0.42);
				if(userh.getMp()-mp>=0) {
					userh.setMp(userh.getMp()-mp);
				} else {
					userh.setMp(0);
				}
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				enemybuff.remove(enemyh.lz);
				repaint();
			}
			tip4.setText("累计造成/承受伤害："+damage+" / "+hurt);
		}
	}
	
	class waitenemy extends Thread {

		@Override
		public void run() {
			super.run();
			try {
				socket2 = new Socket(server.getIP(), Server.gameport);
			
				socket2.setSoTimeout(350000);	
				
				os2 = socket2.getOutputStream();
				dos2 = new DataOutputStream(os2);
				dos2.writeInt(6);
				dos2.flush();
				oos2 = new ObjectOutputStream(os2);
				oos2.writeObject(user);
				oos2.flush();
				is2 = socket2.getInputStream();
				dis2 = new DataInputStream(is2);	
				
				enemyop = dis2.readInt(); // 获取对方操作值
				
				dos2.writeInt(1);
				dos2.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				close2();
			}
			if(enemyop>0) {
				UpdateJTextArea("对方已经决定了该回合的行动。  \n\n");
			}
			if(enemyop==2&&enemyh.getId()==Config.yy.getId()) { // 奕阳 暗影之刺 的使用判断
				enemyh.setXdl(enemyh.getXdl()+999);
			}
			if(enemyop==1&&enemyh.getId()==Config.xyh.getId()) { // 谢悠涵 洁净之灵 的使用判断
				enemyh.setXdl(enemyh.getXdl()+9999);
			}
			if(enemyop==3&&enemyh.getId()==Config.hyq.getId()) { // 郈与却 星月奇迹 的使用判断
				if(enemyh.jhjj==2) enemyh.setXdl(enemyh.getXdl()+999);
			}
			if(enemyop==4&&enemyh.getId()==Config.hyq.getId()) { // 郈与却 云霄之巅 的使用判断
				enemyh.setXdl(enemyh.getXdl()+999);
			}
		}
		
	}
	
	public void sendP(int p) {
		try {
			dos5.writeInt(p);
			dos5.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class setp extends Thread {
		
		@Override
		public void run() {
			super.run();
			try {
				socket5 = new Socket(server.getIP(), Server.probailityport);
			
				socket5.setSoTimeout(1800000);
				
				os5 = socket5.getOutputStream();
				oos5 = new ObjectOutputStream(os5);
				oos5.writeObject(user);
				oos5.flush();
				oos5.writeObject(enemy);
				oos5.flush();
				
				is5 = socket5.getInputStream();
				dis5 = new DataInputStream(is5);
				
				if(dis5.readInt()==1) {
					dos5 = new DataOutputStream(os5);
				}
				
				new Thread() {
					@Override
					public void run() {
						// 心跳包 防止客户端与概率服务器断开连接导致游戏不同步
						while(!gameover) {
							try {
								sleep(45*1000);
								dos5.writeInt(-4);
								dos5.flush();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						close5();
					}
				}.start();
				
			} catch (IOException e) {
				UpdateJTextArea("你已经与服务器断开连接。\n\n");
			}
		}
		
	}
	
	public void setE(int eq) {
		try {
			dos4.writeInt(eq);
			dos4.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setE(int eq,int zb) {
		try {
			dos4.writeInt(eq+zb*20);
			dos4.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class Equip extends Thread {

		@Override
		public void run() {
			super.run();
			try {
				socket4 = new Socket(server.getIP(), Server.equipport);
			
				socket4.setSoTimeout(1800000);	
				
				os4 = socket4.getOutputStream();
				oos4 = new ObjectOutputStream(os4);
				oos4.writeObject(user);
				oos4.flush();
				oos4.writeObject(enemy);
				oos4.flush();
				
				is4 = socket4.getInputStream();
				dis4 = new DataInputStream(is4);
				
				if(dis4.readInt()==1) {
					dos4 = new DataOutputStream(os4);
					while(!gameover) {
						int i = dis4.readInt();
						if(i!=-2) {
							getEnemyEquip(i);
						} else {
							break;
						}
					}
				}
			} catch (IOException e) {
				UpdateJTextArea("你已经与服务器断开连接。\n\n");
			} finally {
				close4();
			}
		}
		
	}
	
	class chatserver extends Thread {

		@Override
		public void run() {
			super.run();
			try {
				socket3 = new Socket(server.getIP(), Server.chatport);
			
				socket3.setSoTimeout(1800000);	
				
				os3 = socket3.getOutputStream();
				oos3 = new ObjectOutputStream(os3);
				oos3.writeObject(user);
				oos3.flush();
				oos3.writeObject(enemy);
				oos3.flush();
				
				is3 = socket3.getInputStream();
				dis3 = new DataInputStream(is3);
				
				if(dis3.readInt()==1) {
					pw3 = new PrintWriter(os3);
					isr3 = new InputStreamReader(is3);
					br3 = new BufferedReader(isr3);
					UpdateJTextArea("你已成功连接上聊天服务器。\n\n");
					while(!gameover) {
						String str = br3.readLine();
						if(!str.equals("-disconnect")) {
							UpdateJTextArea(str+"\n\n");
						} else {
							break;
						}
					}
					UpdateJTextArea("游戏结束，你已经和聊天房间断开连接。\n\n");
				}
			} catch (IOException e) {
				UpdateJTextArea("你已经与服务器断开连接。\n\n");
			} finally {
				close3();
			}
		}
		
	}
	
	public void action (User user,User enemy,int r) {
		try {
			
			new waitenemy().start();
			
			socket = new Socket(server.getIP(), Server.gameport);
			
			socket.setSoTimeout(350000);	
			
			os = socket.getOutputStream();
			dos = new DataOutputStream(os);
			dos.writeInt(5);
			dos.flush();
				
			is = socket.getInputStream();
			dis = new DataInputStream(is);
			dis.readInt();
				
			dos.writeInt(r);
			dos.flush();
				
			remain=30;
			remaintime.setText("还剩"+remain+"秒");
			whoact.setText("请选择本回合的行动");
			
			if (dis.readInt() == 1) {
				oos = new ObjectOutputStream(os);
				oos.writeObject(user);
				oos.flush();
				oos.writeObject(enemy);
				oos.flush();
				
				selected=1;
				
				if(!userh.isIslimte()) {
					selected=0;
					UpdateJTextArea("由于【行动受限】且未使用复苏类道具，你的行动阶段已被跳过。\n\n");
					new Thread() {
						@Override
						public void run() {
							try {
								dos.writeInt(-1);
								dos.flush();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}.start();
					whoact.setText("你已放弃该回合行动");
				}
				
				if(!userh.isIsgone()) {
					selected=0;
					UpdateJTextArea("由于【完全行动不能】，你的行动阶段已被跳过。\n\n");
					new Thread() {
						@Override
						public void run() {
							try {
								dos.writeInt(-1);
								dos.flush();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}.start();
					whoact.setText("你已放弃该回合行动");
				}
				
				while (remain > 0) {
					if (selected == 0) {
						if (enemyop == 0) {
							whoact.setText("等待对方行动");
						} else {
							remain = 0;
							whoact.setText("行动完成");
							break;
						}
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(!ispause) {
						remain--;
						remaintime.setText("还剩" + remain + "秒");
					}
				}
				if (selected != 0) {
					selected = 0;
					whoact.setText("你已放弃该回合行动");
					dos.writeInt(-1);
					dos.flush();
				}
			}
			
		} catch (IOException e) {
			whoact.setText("你似乎已经断开连接");
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void setcanSelect() {
		mp = 0;
		op = 0;
		readyitem = 0;
		for(int i=0;i<select.size();i++) {
			if(!select.get(i).isEnabled()) {
				select.get(i).setEnabled(true);
			} 
		}
	}
	
	class openDJH extends JFrame {

		/**
		 * 
		 */
		private static final long serialVersionUID = 6856624158000553656L;
		
		JPanel have;
		
		ArrayList<JButton> JButtonSelect = new ArrayList<JButton>();
		
		TitledBorder tb;
		
		Fight f;
		
		public openDJH(Fight f){
			this.f=f;
			this.setLocationRelativeTo(f);
			this.setSize(314,344);
			this.setUndecorated(true);
			this.setBackground(new Color(255,255,255,220));
			this.setAlwaysOnTop(true);
			this.setLayout(null);
			f.setEnabled(false);
			have = new JPanel();
			tb = new TitledBorder(new LineBorder(new Color(222, 184, 135), 5), "道具盒", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(188, 143, 143));
			have.setBorder(tb);
			have.setSize(314, 314);
			have.setOpaque(false);
			this.add(have);
			have.setLayout(new GridLayout(5, 6));
			InitDJH();
			JButton ok = new JButton();
			ok.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/selectitem.png")));
			ok.setContentAreaFilled(false);
			ok.setBounds(110,315,16,16);
			this.add(ok);
			JButton cancel = new JButton();
			cancel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/dontitem.png")));
			cancel.setContentAreaFilled(false);
			cancel.setBounds(210,315,16,16);
			this.add(cancel);
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectitem(ok);
				}
			});
			cancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dontitem();
				}
			});
			repaint();
		}
		
		public void selectitem(JButton ok) {
			buttongroup(ok,6);
			if(willitem!=null) {
				f.setEnabled(true);
				op = 6;
				readyitem = willitem.getId();
				item.setEnabled(true);
				item.setIcon(Config.s.getItemImageIcon(readyitem));
				item.setToolTipText(Config.Allitems.get(readyitem-1).getItem());
				repaint();
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "未指定任何道具");
			}
		}
		
		public void dontitem() {
			f.setEnabled(true);
			op = 0;
			readyitem = 0;
			willitem = null;
			setcanSelect();
			dispose();
		}
		
		public void bg (JButton jb,int itemid) {
			for(int i=0;i<JButtonSelect.size();i++) {
				if(JButtonSelect.get(i).isEnabled()) {
					willitem = Config.Allitems.get(itemid-1);
					JButtonSelect.get(i).setEnabled(false);
				} else {
					willitem = null;
					JButtonSelect.get(i).setEnabled(true);
				}
				jb.setEnabled(true);
			}
		}
		
		public void InitDJH() {
			for(int i=0;i<djh.size();i++) {
				Item item = djh.get(i);
				if(item.getId()<13) {
					JButton aitem = Config.s.getItemIcon(item.getId());
					have.add(aitem);
					JButtonSelect.add(aitem);
					aitem.addActionListener(new AL(JButtonSelect.size()-1,item.getId()));
				}
			}
			tb.setTitle("选择一个道具以便使用它");
			repaint();
		}
		
		class AL implements ActionListener {
			int i,id;
			public AL(int i,int id) {
				this.i=i;
				this.id=id;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				bg(JButtonSelect.get(i),id);
			}
		}
		
	}
	
	/** 装备穿戴类（道具盒）
	 * @author 罗恩龙
	 *
	 */
	class openEquip extends JFrame {

		/**
		 * 
		 */
		private static final long serialVersionUID = 6856624158000553656L;
		
		JPanel have;
		
		ArrayList<JButton> JButtonSelect = new ArrayList<JButton>();
		
		TitledBorder tb;
		
		Fight f;
		
		JButton zb;
		
		int equipid;
		
		public openEquip(Fight f,JButton zb){
			this.f=f;
			this.zb=zb;
			this.setLocationRelativeTo(usertx);
			this.setSize(314,344);
			this.setUndecorated(true);
			this.setBackground(new Color(255,255,255,220));
			this.setAlwaysOnTop(true);
			this.setLayout(null);
			f.setEnabled(false);
			have = new JPanel();
			tb = new TitledBorder(new LineBorder(new Color(222, 184, 135), 5), "道具盒", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(188, 143, 143));
			have.setBorder(tb);
			have.setSize(314, 314);
			have.setOpaque(false);
			this.add(have);
			have.setLayout(new GridLayout(5, 6));
			InitDJH();
			JButton ok = new JButton();
			ok.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/selectitem.png")));
			ok.setContentAreaFilled(false);
			ok.setBounds(110,315,16,16);
			this.add(ok);
			JButton cancel = new JButton();
			cancel.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/dontitem.png")));
			cancel.setContentAreaFilled(false);
			cancel.setBounds(210,315,16,16);
			this.add(cancel);
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(selected==1) {
						selectitem(ok);
					} else {
						JOptionPane.showMessageDialog(null, "不在行动时间内，无法穿戴。");
					}
				}
			});
			cancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(selected==1) {
						dontitem();
					} else {
						f.setEnabled(true);
						willequip = null;
						dispose();
						UpdateJTextArea("不在行动时间内，不能取消穿戴。\n\n");
					}
				}
			});
			repaint();
		}
		
		public void selectitem(JButton ok) {
			if(willequip!=null) {
				equipid = willequip.getId();
				if(zb.equals(zb1)) {
					if(userh.getZ()!=null) {
						getUserEquip(userh.getZ().getId()+20);
						setE(userh.getZ().getId(),3);
						djh.add(userh.getZ());
					}
					userh.setZ(willequip);
					setE(equipid,1);
				} else if(zb.equals(zb2)) {
					if(userh.getX()!=null) {
						getUserEquip(userh.getX().getId()+20);
						setE(userh.getX().getId(),4);
						djh.add(userh.getX());
					}
					userh.setX(willequip);
					setE(equipid,2);
				}
				f.setEnabled(true);
				djh.remove(willequip);
				zb.setIcon(Config.s.getItemImageIcon(equipid));
				zb.setToolTipText(Config.Allitems.get(equipid-1).getItem());
				getUserEquip(equipid);
				willequip=null;
				repaint();
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "未指定任何装备");
			}
		}
		
		public void dontitem() {
			if(zb.equals(zb1)) {
				if(userh.getZ()!=null) {
					djh.add(userh.getZ());
					setE(userh.getZ().getId(),3);
					getUserEquip(userh.getZ().getId()+20);
					userh.setZ(null);
					zb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e1.png")));
					zb.setToolTipText("点击选择一个装备并穿戴。");
				}
			} else if(zb.equals(zb2)) {
				if(userh.getX()!=null) {
					djh.add(userh.getX());
					setE(userh.getX().getId(),4);
					getUserEquip(userh.getX().getId()+20);
					userh.setX(null);
					zb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
					zb.setToolTipText("点击选择一个装备并穿戴。");
				}
			}
			f.setEnabled(true);
			willequip = null;
			dispose();
		}
		
		public void bg (JButton jb,int itemid) {
			for(int i=0;i<JButtonSelect.size();i++) {
				if(JButtonSelect.get(i).isEnabled()) {
					willequip = Config.Allitems.get(itemid-1);
					JButtonSelect.get(i).setEnabled(false);
				} else {
					willequip = null;
					JButtonSelect.get(i).setEnabled(true);
				}
				jb.setEnabled(true);
			}
		}
		
		public void InitDJH() {
			for(int i=0;i<djh.size();i++) {
				Item item = djh.get(i);
				if(item.getId()>=13) {
					JButton aitem = Config.s.getItemIcon(item.getId());
					have.add(aitem);
					JButtonSelect.add(aitem);
					aitem.addActionListener(new AL(JButtonSelect.size()-1,item.getId()));
				}
			}
			if(zb.equals(zb1)) {
				tb.setTitle("[1]点√穿戴点×取消穿戴");
			} else if(zb.equals(zb2)) {
				tb.setTitle("[2]点√穿戴点×取消穿戴");
			}			
			repaint();
		}
		
		class AL implements ActionListener {
			int i,id;
			public AL(int i,int id) {
				this.i=i;
				this.id=id;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				bg(JButtonSelect.get(i),id);
			}
		}
		
	}
	
	public boolean ishavenext(User u) {
		if(u.equals(user)) {
			if(userheroes.size()>0) {
				return true;
			} else return false;
		} else if(u.equals(enemy)) {
			if(enemyheroes.size()>0) {
				return true;
			} else return false;
		}
		return false;
	}
	
	public Hero getUserHero() {
		Hero hero = new Hero();
		if(userheroes.size()>0) {
			hero.SwapHeroProperty(userheroes.get(0));
			userheroes.remove(0);
		}
		return hero;
	}
	
	public Hero getEnemyHero() {
		Hero hero = new Hero();
		if(enemyheroes.size()>0) {
			hero.SwapHeroProperty(enemyheroes.get(0));
			enemyheroes.remove(0);
		}
		return hero;
	}
	
	public String getUserNext() {
		if(userheroes.size()>0) {
			return "下一个英雄："+userheroes.get(0).getName();
		} else {
			return "已无可用的下一个英雄";
		}
	}
	
	public String getEnemyNext() {
		if(enemyheroes.size()>0) {
			return "对方下一个英雄："+enemyheroes.get(0).getName();
		} else {
			return "对方已无下一个英雄";
		}
	}
	
	/** 判断英雄技能并实现效果。
	 * @param from - 用来判断技能使用者
	 * @param s - 使用的技能
	 */
	public void getSkillEffect(Skill s,Hero from) {
		switch(s.getId()) {
			case 1:{//奕阳Q 烈日之箭
				if(from.equals(userh)) {
					if(userh.jhjj==2) {
						UpdateJTextArea("【"+userh.getName()+"】对【"+enemyh.getName()+"】造成了灼烧！\n\n");
						enemyh.yyQ+=3;
						enemybuff.remove(enemyh.lrzj);
						enemybuff.add(enemyh.lrzj);
						enemyh.lrzj.setDescribe("该英雄会持续受到来自灼烧的"+(5+userh.yyE)+"点魔法伤害。");
						enemyh.lrzj.setRound(enemyh.yyQ);
						repaint();
					} else {
						p = new Random().nextInt(10);
						int hp = enemyh.getHp()%10;
						sendP(p);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						UpdateJTextArea("【"+userh.getName()+"】抽取的幸运数字为："+p+"\n\n");
						if(p<=hp) {
							UpdateJTextArea("【"+userh.getName()+"】对【"+enemyh.getName()+"】造成了灼烧！\n\n");
							enemyh.yyQ+=3;
							enemybuff.remove(enemyh.lrzj);
							enemybuff.add(enemyh.lrzj);
							enemyh.lrzj.setDescribe("该英雄会持续受到来自灼烧的"+(5+userh.yyE)+"点魔法伤害。");
							enemyh.lrzj.setRound(enemyh.yyQ);
							repaint();
						} else {
							UpdateJTextArea("这次什么也没有发生。\n\n");
						}
					}
				} else if(from.equals(enemyh)) {
					if(enemyh.jhjj==2) {
						UpdateJTextArea("【"+enemyh.getName()+"】对【"+userh.getName()+"】造成了灼烧！\n\n");
						userh.yyQ+=3;
						userbuff.remove(userh.lrzj);
						userbuff.add(userh.lrzj);
						userh.lrzj.setDescribe("该英雄会持续受到来自灼烧的"+(5+enemyh.yyE)+"点魔法伤害。");
						userh.lrzj.setRound(userh.yyQ);
						repaint();
					} else {
						try {
							p = dis5.readInt();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						int hp = userh.getHp()%10;
						UpdateJTextArea("【"+enemyh.getName()+"】抽取的幸运数字为："+p+"\n\n");
						if(p<=hp) {
							UpdateJTextArea("【"+enemyh.getName()+"】对【"+userh.getName()+"】造成了灼烧！\n\n");
							userh.yyQ+=3;
							userbuff.remove(userh.lrzj);
							userbuff.add(userh.lrzj);
							userh.lrzj.setDescribe("该英雄会持续受到来自灼烧的"+(5+enemyh.yyE)+"点魔法伤害。");
							userh.lrzj.setRound(userh.yyQ);
							repaint();
						} else {
							UpdateJTextArea("这次什么也没有发生。\n\n");
						}
					}
				}
				p=0;
				break;
			}
			case 2:{//奕阳W 暗影之刺
				if(from.equals(userh)) {
					p = new Random().nextInt(10);
					int pd = userh.getAtk()+enemyh.getXdl();
					if(pd<100) pd = pd%10;
					else if(pd<1000) pd = pd%100;
					sendP(p);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					UpdateJTextArea("【"+userh.getName()+"】抽取的幸运数字为："+p+"\n\n");
					if(p<=pd) {
						int d = (10+userh.yyE) - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
						if(d<0) d=0;
						balanceskill(userh, d);
					} else {
						UpdateJTextArea("这次什么也没有发生。\n\n");
					}
					userh.setXdl(userh.getXdl()-999);
				} else if(from.equals(enemyh)) {
					try {
						p = dis5.readInt();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					int pd = enemyh.getAtk()+userh.getXdl();
					if(pd<100) pd = pd%10;
					else if(pd<1000) pd = pd%100;
					UpdateJTextArea("【"+enemyh.getName()+"】抽取的幸运数字为："+p+"\n\n");
					if(p<=pd) {
						int d = (10+enemyh.yyE) - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
						if(d<0) d=0;
						balanceskill(enemyh, d);
					} else {
						UpdateJTextArea("这次什么也没有发生。\n\n");
					}
					enemyh.setXdl(enemyh.getXdl()-999);
				}
				p=0;
				break;
			}
			case 3:{//奕阳E 屠杀之风
				if(from.equals(userh)) {
					p = new Random().nextInt(10);
					sendP(p);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					UpdateJTextArea("【"+userh.getName()+"】抽取的幸运数字为："+p+"\n\n");
					int pd = enemyh.getHp()+userh.getMp();
					if(pd<100) pd = pd%10;
					else if(pd<1000) pd = pd%100;
					if(p<=pd) {
						UpdateJTextArea("【"+userh.getName()+"】获得了魔法伤害加成的效果！\n\n");
						if(userh.jhjj==3) userh.yyE+=6;
						else userh.yyE+=3;
						userh.yyER+=4;
						if(userh.jhjj==2) {
							userh.getQ().setdescribe("<html>对对方造成灼烧，每回合造成"+(5+userh.yyE)+"点魔法伤害，持续3回合。该效果影响的回合数可以叠加。<br/><br/>已激活结晶之力。</html>");
							skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
						} else {
							userh.getQ().setdescribe("从0～9中抽取幸运数字。<br />若该数字小于或等于对方当前生命值的个位数，那么对对方造成灼烧，每回合造成"+(5+userh.yyE)+"点魔法伤害，持续3回合。<br />该效果影响的回合数可以叠加。<br/><br />【激活结晶之力】100%生效。");
							skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
						}
						userh.getW().setdescribe("从0～9中抽取幸运数字。<br />若该数字小于或等于对方行动力和你攻击力之和的个位数，那么对对方造成"+(10+userh.yyE)+"点魔法伤害。该技能无视行动力。");
						skill2.setToolTipText("<html>（W）"+userh.getW().getSkill()+"</html>");
						userbuff.remove(userh.tszf);
						userbuff.add(userh.tszf);
						userh.tszf.setDescribe("屠杀之风会为该英雄提供"+userh.yyE+"点魔法伤害加成。");
						userh.tszf.setRound(userh.yyER);
						repaint();
					} else {
						UpdateJTextArea("这次什么也没有发生。\n\n");
					}
				} else if(from.equals(enemyh)) {
					try {
						p = dis5.readInt();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					UpdateJTextArea("【"+enemyh.getName()+"】抽取的幸运数字为："+p+"\n\n");
					int pd = userh.getHp()+enemyh.getMp();
					if(pd<100) pd = pd%10;
					else if(pd<1000) pd = pd%100;
					if(p<=pd) {
						UpdateJTextArea("【"+enemyh.getName()+"】获得了魔法伤害加成的效果！\n\n");
						if(enemyh.jhjj==3) enemyh.yyE+=6;
						else enemyh.yyE+=3;
						enemyh.yyER+=4;
						enemybuff.remove(enemyh.tszf);
						enemybuff.add(enemyh.tszf);
						enemyh.tszf.setDescribe("屠杀之风会为该英雄提供"+enemyh.yyE+"点魔法伤害加成。");
						enemyh.tszf.setRound(enemyh.yyER);
						repaint();
					} else {
						UpdateJTextArea("这次什么也没有发生。\n\n");
					}
				}
				p=0;
				break;
			}
			case 4:{//刘晓释Q 界限突破
				if(from.equals(userh)) {
					UpdateJTextArea("【"+userh.getName()+"】提升了1点魔法上限！\n\n");
					usermpt.setMaximum(usermpt.getMaximum()+1);
					int mp = userh.getMp();
					userh.setMp(mp+1);
					usermpt.setValue(userh.getMp());
					usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
					userh.getW().setMp(userh.getW().getMp()+1);
					skill2.setToolTipText("<html>（W）"+userh.getW().getSkill()+"</html>");
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("【"+enemyh.getName()+"】提升了1点魔法上限！\n\n");
					enemympt.setMaximum(enemympt.getMaximum()+1);
					int mp = enemyh.getMp();
					enemyh.setMp(mp+1);
					enemympt.setValue(enemyh.getMp());
					enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
					enemyh.getW().setMp(enemyh.getW().getMp()+1);
				}
				break;
			}
			case 5:{//刘晓释W 解放真名
				if(from.equals(userh)) {
					UpdateJTextArea("【"+userh.getName()+"】提升了2点攻击力和1点护甲！\n\n");
					userh.setAtk(userh.getAtk()+2);
					userh.setDef(userh.getDef()+1);
					userh.jfzm.setV1(userh.jfzm.getV1()+1);
					userh.jfzm.setSuperpose("（当前层数："+userh.jfzm.getV1()+"）攻击力+"+(userh.jfzm.getV1()*2)+" 护甲+"+userh.jfzm.getV1());
					new Thread() {
						@Override
						public void run() {
							int whenstop=r+10;
							while(true) {
								if(r==whenstop) {
									if(userh.getId()==Config.lxs.getId()) {
										userh.setAtk(userh.getAtk()-2);
										userh.setDef(userh.getDef()-1);
										userh.jfzm.setV1(userh.jfzm.getV1()-1);
										userh.jfzm.setSuperpose("（当前层数："+userh.jfzm.getV1()+"）攻击力+"+(userh.jfzm.getV1()*2)+" 护甲+"+userh.jfzm.getV1());
									}
									break;
								}
								try {
									sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("【"+enemyh.getName()+"】提升了2点攻击力和1点护甲！\n\n");
					enemyh.setAtk(enemyh.getAtk()+2);
					enemyh.setDef(enemyh.getDef()+1);
					enemyh.jfzm.setV1(enemyh.jfzm.getV1()+1);
					enemyh.jfzm.setSuperpose("（当前层数："+enemyh.jfzm.getV1()+"）攻击力+"+(enemyh.jfzm.getV1()*2)+" 护甲+"+enemyh.jfzm.getV1());
					new Thread() {
						@Override
						public void run() {
							int whenstop=r+10;
							while(true) {
								if(r==whenstop) {
									if(enemyh.getId()==Config.lxs.getId()) {
										enemyh.setAtk(enemyh.getAtk()-2);
										enemyh.setDef(enemyh.getDef()-1);
										enemyh.jfzm.setV1(enemyh.jfzm.getV1()-1);
										enemyh.jfzm.setSuperpose("（当前层数："+enemyh.jfzm.getV1()+"）攻击力+"+(enemyh.jfzm.getV1()*2)+" 护甲+"+enemyh.jfzm.getV1());
									}
									break;
								}
								try {
									sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				break;
			}
			case 6:{//刘晓释E 魔王怒
				if(from.equals(userh)) {
					p = new Random().nextInt(10);
					sendP(p);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(p<userh.lxsE) {
						UpdateJTextArea("【"+enemyh.getName()+"】被秒杀了！！【"+userh.getName()+"】回复了全部魔法值！\n\n");
						damage += (enemyh.getHp());
						enemyh.setHp(0);
						enemyhpt.setValue(0);
						enemyhpt.setString("0"+" / "+enemyhpt.getMaximum());
						userh.setMp(usermpt.getMaximum());
						usermpt.setValue(userh.getMp());
						usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
						if(userh.lxsE-2<0) userh.lxsE=0;
						else userh.lxsE-=2;
					} else {
						UpdateJTextArea("这次什么也没有发生。\n\n");
						if(userh.lxsE+1<10) userh.lxsE++;
						else userh.lxsE=10;
					}
				} else if(from.equals(enemyh)) {
					try {
						p = dis5.readInt();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(p<enemyh.lxsE) {
						UpdateJTextArea("【"+userh.getName()+"】被秒杀了！！【"+enemyh.getName()+"】回复了全部魔法值！\n\n");
						hurt += (userh.getHp());
						userh.setHp(0);
						userhpt.setValue(0);
						userhpt.setString("0"+" / "+userhpt.getMaximum());
						enemyh.setMp(enemympt.getMaximum());
						enemympt.setValue(enemyh.getMp());
						enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
						if(enemyh.lxsE-2<0) enemyh.lxsE=0;
						else enemyh.lxsE-=2;
					} else {
						UpdateJTextArea("这次什么也没有发生。\n\n");
						if(enemyh.lxsE+1<10) enemyh.lxsE++;
						else enemyh.lxsE=10;
					}
				}
				p=0;
				break;
			}
			case 7:{//杨圣诺Q 新星冲刺
				if(from.equals(userh)) {
					int mk = enemyh.getAdf();
					int d = 7 - (int)Math.round((1-userh.getApp())*mk);
					if(d<0) d=0;
					balanceskill(userh, d);
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;
							int whenstop=r+2;//设置技能失效的回合
							int def=0;
							int id=0;
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(r==whenstart) {
									id=enemyh.getId();
									if(userh.getId()==Config.ysn.getId()) { // 只有英雄是杨圣诺时才操作
										def = enemyh.getDef();
										if(def>3) def=3;
										else if(def<0) def=0;
										UpdateJTextArea("【"+userh.getName()+"】偷取了【"+enemyh.getName()+"】的"+def+"点物理护甲！\n\n");
										userh.setDef(userh.getDef()+def);
										usertx.setToolTipText(userh.getProperty());
										enemyh.setDef(enemyh.getDef()-def);
										enemytx.setToolTipText(enemyh.getProperty());
										
										userbuff.remove(userh.userxxcc);
										enemybuff.remove(enemyh.enemyxxcc);
										userbuff.add(userh.userxxcc);
										userh.userxxcc.setDescribe("该英雄已经窃取了对方"+def+"点物理护甲。");
										userh.userxxcc.setRound(1);
										repaint();
										enemybuff.add(enemyh.enemyxxcc);
										enemyh.enemyxxcc.setDescribe("该英雄已经被对方窃取了"+def+"点物理护甲。");
										enemyh.enemyxxcc.setRound(1);
										repaint();
									}
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(r==whenstop) {//回合来到我们设置技能失效的那个回合 跳出循环
									if(userh.getId()==Config.ysn.getId()) { // 只有英雄是杨圣诺时才操作
										userh.setDef(userh.getDef()-def);
										usertx.setToolTipText(userh.getProperty());
										userbuff.remove(userh.userxxcc);
										repaint();
									}
									if(enemyh.getId()==id) {
										enemyh.setDef(enemyh.getDef()+def);
										enemytx.setToolTipText(enemyh.getProperty());
										enemybuff.remove(enemyh.enemyxxcc);
										repaint();
									}
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				} else if(from.equals(enemyh)) {
					int mk = userh.getAdf();
					int d = 7 - (int)Math.round((1-enemyh.getApp())*mk);
					if(d<0) d=0;
					balanceskill(enemyh, d);
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;
							int whenstop=r+2;//设置技能失效的回合
							int def=0;
							int id=0;
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(r==whenstart) {
									id=userh.getId();
									if(enemyh.getId()==Config.ysn.getId()) { // 只有英雄是杨圣诺时才操作
										def = userh.getDef();
										if(def>3) def=3;
										else if(def<0) def=0;
										UpdateJTextArea("【"+enemyh.getName()+"】偷取了【"+userh.getName()+"】的"+def+"点物理护甲！\n\n");
										userh.setDef(userh.getDef()-def);
										usertx.setToolTipText(userh.getProperty());
										enemyh.setDef(enemyh.getDef()+def);
										enemytx.setToolTipText(enemyh.getProperty());
										
										userbuff.remove(userh.enemyxxcc);
										enemybuff.remove(enemyh.userxxcc);
										userbuff.add(userh.enemyxxcc);
										userh.enemyxxcc.setDescribe("该英雄已经被对方窃取了"+def+"点物理护甲。");
										userh.enemyxxcc.setRound(1);
										repaint();
										enemybuff.add(enemyh.userxxcc);
										enemyh.userxxcc.setDescribe("该英雄已经窃取了对方"+def+"点物理护甲。");
										enemyh.userxxcc.setRound(1);
										repaint();
									}
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(r==whenstop) {//回合来到我们设置技能失效的那个回合 跳出循环
									if(enemyh.getId()==Config.ysn.getId()) {
										enemyh.setDef(enemyh.getDef()-def);
										enemytx.setToolTipText(enemyh.getProperty());
										enemybuff.remove(enemyh.userxxcc);
										repaint();
									}
									if(userh.getId()==id) {
										userh.setDef(userh.getDef()+def);
										usertx.setToolTipText(userh.getProperty());
										userbuff.remove(userh.enemyxxcc);
										repaint();
									}
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				break;
			}
			case 8:{//杨圣诺W 星辰陨落
				if(from.equals(userh)) {
					UpdateJTextArea("【"+enemyh.getName()+"】被减少了2点魔法防御！\n\n");
					if(enemyh.ysnW==0) {
						enemyh.setAdf(enemyh.getAdf()-2);
						enemybuff.remove(enemyh.xcyl);
						enemybuff.add(enemyh.xcyl);
						repaint();
					}
					int id = enemyh.getId();
					enemyh.ysnW+=2;
					enemyh.xcyl.setRound(enemyh.ysnW);
					enemytx.setToolTipText(enemyh.getProperty());
					new Thread() {
						@Override
						public void run() {
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(enemyh.getId()==id) {
									if(enemyh.ysnW==0) {//回合来到我们设置技能失效的那个回合 跳出循环
										enemyh.setAdf(enemyh.getAdf()+2);
										enemytx.setToolTipText(enemyh.getProperty());
										enemybuff.remove(enemyh.xcyl);
										repaint();
										break;
									}
								} else {
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
					if(userh.ysnQ) {
						userh.ysnQ=false;
						sendP(1);
						UpdateJTextArea("【"+userh.getName()+"】追加使用了【新星冲刺】！\n\n");
						getSkillEffect(userh.getQ(),userh);
						userh.setMp(userh.getMp()-userh.getQ().getMp());
						usermpt.setValue(userh.getMp());
						usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
					} else {
						sendP(0);
					}
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("【"+userh.getName()+"】被减少了2点魔法防御！\n\n");
					if(userh.ysnW==0) {
						userh.setAdf(userh.getAdf()-2);
						userbuff.remove(userh.xcyl);
						userbuff.add(userh.xcyl);
						repaint();
					}
					int id = userh.getId();
					userh.ysnW+=2;
					userh.xcyl.setRound(userh.ysnW);
					usertx.setToolTipText(userh.getProperty());
					new Thread() {
						@Override
						public void run() {
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(userh.getId()==id) {
									if(userh.ysnW==0) {//回合来到我们设置技能失效的那个回合 跳出循环
										userh.setAdf(userh.getAdf()+2);
										usertx.setToolTipText(userh.getProperty());
										userbuff.remove(userh.xcyl);
										repaint();
										break;
									}
								} else {
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
					try {
						p = dis5.readInt();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if(p==1) {
						UpdateJTextArea("【"+enemyh.getName()+"】追加使用了【新星冲刺】！\n\n");
						getSkillEffect(enemyh.getQ(),enemyh);
						enemyh.setMp(enemyh.getMp()-enemyh.getQ().getMp());
						enemympt.setValue(enemyh.getMp());
						enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
					}
				}
				p=0;
				break;
			}
			case 9:{//张枫Q 一秒十三刀
				if(from.equals(userh)) {//判断使用者是玩家的英雄
					int atk = userh.getAtk();//获取该英雄的攻击力
					int mk = enemyh.getAdf();//获取对方的魔抗
					int d = atk - (int)Math.round((1-userh.getApp())*mk);
					if(d<0) d=0;
					balanceskill(userh, d);
				} else if(from.equals(enemyh)) {//判断使用者是对方英雄
					int atk = enemyh.getAtk();//获取对方英雄的攻击力
					int mk = userh.getAdf();//获取我的魔抗
					int d = atk - (int)Math.round((1-enemyh.getApp())*mk);
					if(d<0) d=0;
					balanceskill(enemyh, d);
				}
				break;
			}
			case 10:{//张枫W 风之结界
				if(from.equals(userh)) {
					UpdateJTextArea("【"+userh.getName()+"】对【"+enemyh.getName()+"】造成了下一回合【完全行动不能】！\n\n");
					p = new Random().nextInt(10);
					sendP(p);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;//设置技能生效的回合
							int whenstop=r+2;//设置技能失效的回合
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(r==whenstart) {
									if(enemyh.yyzs) {
										eyyzs--;
										UpdateJTextArea("【"+enemyh.getName()+"】发动了【夜宴之声】的效果抵挡了一次【完全行动不能】！\n\n");
									} else if(enemyh.fs) {
										UpdateJTextArea("【" + enemyh.getName() + "】发动了【复苏】的效果抵挡了一次【完全行动不能】！\n\n");
									} else {
										enemyh.setIsgone(false);
										if(enemyh.xyhE>0) {
											enemyh.xyhE=0;
											if(enemyh.xyhED>0) {
												UpdateJTextArea("【"+enemyh.getName()+"】释放了他的【时光沙漏】！\n\n");
												int d = (int)Math.round(enemyh.xyhED*0.8 - ((1-enemyh.getApp())*userh.getAdf()));
												if(d<0) d=0;
												balanceskill(enemyh, d);
												enemyh.xyhED=0;
											} else {
												UpdateJTextArea("【"+enemyh.getName()+"】的【时光沙漏】已破碎！\n\n");
											}
										}
										enemybuff.remove(userh.sgsl);
										enemybuff.remove(enemyh.fzjj1);
										enemybuff.add(enemyh.fzjj1);
										enemyh.fzjj1.setRound(1);
										repaint();
									}
									break;
								}
								try {
									sleep(500);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(rend==whenstop) {//回合来到我们设置技能失效的那个回合 跳出循环
									enemyh.setIsgone(true);
									enemybuff.remove(enemyh.fzjj1);
									repaint();
									break;
								}
								try {
									sleep(500);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
					if(p<7) {
						UpdateJTextArea("【"+enemyh.getName()+"】将在【完全行动不能】的下一个回合【行动受限】。\n\n");
						userh.zfW=true;
					}
				} else if(from.equals(enemyh)) {
					try {
						p = dis5.readInt();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					UpdateJTextArea("【"+enemyh.getName()+"】对【"+userh.getName()+"】造成了下一回合【完全行动不能】！\n\n");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;//设置技能生效的回合
							int whenstop=r+2;//设置技能失效的回合
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(r==whenstart) {
									if(userh.yyzs) {
										uyyzs--;
										UpdateJTextArea("【"+userh.getName()+"】发动了【夜宴之声】的效果抵挡了一次【完全行动不能】！\n\n");
										if(uyyzs%3==0) {
											if(userh.getZ().getName().equals("夜宴之声")) {
												setE(userh.getZ().getId(),3);
												getUserEquip(userh.getZ().getId()+20);
												userh.setZ(null);
												zb1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e1.png")));
												zb1.setToolTipText("点击选择一个装备并穿戴。");
											} else if(userh.getX().getName().equals("夜宴之声")) {
												setE(userh.getX().getId(),4);
												getUserEquip(userh.getX().getId()+20);
												userh.setX(null);
												zb2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
												zb2.setToolTipText("点击选择一个装备并穿戴。");
											}
										}
									} else if(userh.fs) {
										UpdateJTextArea("【" + userh.getName() + "】发动了【复苏】的效果抵挡了一次【完全行动不能】！\n\n");
									} else {
										userh.setIsgone(false);
										
										if(userh.xyhE>0) {
											userh.xyhE=0;
											if(userh.xyhED>0) {
												UpdateJTextArea("【"+userh.getName()+"】已经释放了【时光沙漏】！\n\n");
												int d = (int)Math.round(userh.xyhED*0.8 - ((1-userh.getApp())*enemyh.getAdf()));
												if(d<0) d=0;
												balanceskill(userh, d);
												userh.xyhED=0;
											} else {
												UpdateJTextArea("【"+userh.getName()+"】的【时光沙漏】已破碎！\n\n");
											}
										}
										userbuff.remove(userh.sgsl);
										userbuff.remove(userh.fzjj1);
										userbuff.add(userh.fzjj1);
										userh.fzjj1.setRound(1);
										repaint();
									}
									break;
								}
								try {
									sleep(500);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(rend==whenstop) {//回合来到我们设置技能失效的那个回合 跳出循环
									userh.setIsgone(true);
									userbuff.remove(userh.fzjj1);
									repaint();
									break;
								}
								try {
									sleep(500);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
					if(p<7) {
						UpdateJTextArea("【"+userh.getName()+"】将在【完全行动不能】的下一个回合【行动受限】。\n\n");
						enemyh.zfW=true;
					}
				}
				break;
			}
			case 11:{//张枫E 审判之斩
				if(from.equals(userh)) {
					int enemyhp = enemyhpt.getMaximum() - enemyh.getHp(); // 获取对方英雄已损失生命值
					int d = (int)Math.round(enemyhp * 0.8); // 设置已损失生命值的80%为伤害值
					int mk = enemyh.getAdf(); // 获取对方的魔抗
					d = d - (int)Math.round((1-userh.getApp())*mk); // 公式：伤害值 - (1-我的魔法穿透)*他的魔抗
					if(d<0) d=0;
					balanceskill(userh, d);
				} else if(from.equals(enemyh)) {
					int userhp = userhpt.getMaximum() - userh.getHp(); // 获取我的英雄已损失生命值
					int d = (int)Math.round(userhp * 0.8); // 设置已损失生命值的80%为伤害值
					int mk = userh.getAdf(); // 获取我的魔抗
					d = d - (int)Math.round((1-enemyh.getApp())*mk); // 公式：伤害值 - (1-对面的魔法穿透)*我的魔抗
					if(d<0) d=0;
					balanceskill(enemyh, d);
				}
				break;
			}
			case 12:{//罗天杰Q 暴怒
				if(from.equals(userh)) {//使用者是我方
					UpdateJTextArea("【"+userh.getName()+"】提升了4点攻击力并使用了一次【普通攻击】！\n\n");//将效果显示在公屏上
					userh.setAtk(userh.getAtk()+4);//攻击力提升4 立即生效
					balanceatk(userh);//调用结算攻击力的方法
					userh.setAtk(userh.getAtk()-4);//恢复为原来的攻击力 也就是减去4
				} else if(from.equals(enemyh)) {//使用者是对方
					UpdateJTextArea("【"+enemyh.getName()+"】提升了4点攻击力并使用了一次【普通攻击】！\n\n");//将效果显示在公屏上
					enemyh.setAtk(enemyh.getAtk()+4);//攻击力提升4 立即生效
					balanceatk(enemyh);//调用结算攻击力的方法
					enemyh.setAtk(enemyh.getAtk()-4);//恢复为原来的攻击力 也就是减去4
				}
				break;
			}
			case 13:{//罗天杰W 闪现
				if(from.equals(userh)) {//使用者是我
					UpdateJTextArea("【"+userh.getName()+"】获得了60%闪避普通攻击的概率。\n\n");
					userh.ltjW=3;
					userbuff.remove(userh.sx);
					userbuff.add(userh.sx);
					userh.sx.setRound(userh.ltjW);
					repaint();
				} else if(from.equals(enemyh)) {//使用者是对方
					UpdateJTextArea("【"+enemyh.getName()+"】获得了60%闪避普通攻击的概率。\n\n");
					enemyh.ltjW=3;
					enemybuff.remove(enemyh.sx);
					enemybuff.add(enemyh.sx);
					enemyh.sx.setRound(enemyh.ltjW);
					repaint();
				}
				break;
			}
			case 14:{//罗天杰E 断骨剑
				if(from.equals(userh)) {
					if(enemyh.zkxW>0) {
						int d = userh.getAtk()-(int)Math.round(enemyh.getDef()*(1-userh.getAdp()));
						if(d<0) d=0;
						if(userh.yjg) d=(int)Math.round(d*1.36);
						if(enemyh.jrz) d=(int)Math.round(d*0.75);
						d=(int)Math.round(d*(1-enemyh.getDefrate()));
						if(userh.lmQ) d=(int)Math.round(d*0.5);
						enemyh.zkxWH+=d;
						enemyh.bzyy.setDescribe("该英雄完全免疫物理伤害。目前已积累（"+enemyh.zkxWH+"/20）点伤害。");
						UpdateJTextArea("【"+enemyh.getName()+"】受到了0点物理伤害！"+"\n\n");
						if(enemyh.zkxWH>=20) {
							enemyh.zkxW=0;
							UpdateJTextArea("【"+enemyh.getName()+"】的【冰之羽翼】已破碎！"+"\n\n");
							enemybuff.remove(enemyh.bzyy);
							repaint();
						}
						if(userh.sjjE) {
							userh.sjjE=false;
							UpdateJTextArea("【"+userh.getName()+"】发动了【光炽剑】的技能效果并回复了3点生命值！"+"\n\n");
							if(userh.getHp()+3<=userhpt.getMaximum()) {
								hpp += 3;
								userh.setHp(userh.getHp()+3);
								userhpt.setValue(userh.getHp());
								userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
							} else {
								userh.setHp(userhpt.getMaximum());
								userhpt.setValue(userh.getHp());
								userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
							}
							if(userh.jhjj==1) d = 10 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
							else d = 6 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
							if(d<0) d=0;
							balanceskill(userh, d);
							userbuff.remove(userh.gcj);
							repaint();
						}
					} else {
						userh.setHp(userh.getHp()-7);
						userhpt.setValue(userh.getHp());
						userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
						int d = (int)Math.round(3*(userh.getAtk()-enemyh.getDef()*(1-userh.getAdp()))-enemyh.getDef()*(1-userh.getAdp()));
						if(d<0) d=0;
						if(enemyh.jrz) d=(int)Math.round(d*0.75);
						if(enemyh.zxyE>0) {
							enemyh.zxyEE=true;
							d = (int)Math.round(d*0.2);
						}
						d=(int)Math.round(d*(1-enemyh.getDefrate()));
						if(enemyh.getId()==Config.xyh.getId()) {
							if(enemyh.xyh+d<=8) enemyh.xyh+=d;
							else enemyh.xyh=8;
							enemyh.getQ().setMp(enemyh.xyh);
						}
						if(enemyh.sjjR>0&&d>0) {
							if(d-4>=0) {
								UpdateJTextArea("【"+enemyh.getName()+"】牺牲了一名禁卫军，抵挡了4点伤害！"+"\n\n");
								d -= 4;
							}
							else {
								UpdateJTextArea("【"+enemyh.getName()+"】牺牲了一名禁卫军，抵挡了"+d+"点伤害！"+"\n\n");
								d = 0;
							}
							enemyh.sjjR--;
							if(enemyh.sjjR!=0) {
								enemyh.gzhl.setSuperpose("（禁卫军数量："+enemyh.sjjR+"）");
							} else {
								enemybuff.remove(enemyh.gzhl);
								repaint();
							}
						}
						if(enemyh.xyhE>0) {
							enemyh.xyhED+=d;
							d=0;
							enemyh.sgsl.setDescribe("该英雄完全免疫任何伤害。目前已积累"+enemyh.xyhED+"点伤害。");
							enemyh.sgsl.setRound(enemyh.xyhE);
							repaint();
						}
						UpdateJTextArea("【"+enemyh.getName()+"】受到了"+d+"点物理伤害！\n\n");
						if(enemyh.getHp()-d>2) {
							damage = damage + d;
							herodamage += d;
							setrobd(d);
							enemyh.setHp(enemyh.getHp()-d);
							enemyhpt.setValue(enemyh.getHp());
							enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
						} else {
							damage = damage + (enemyh.getHp()-2);
							herodamage += enemyh.getHp()-2;
							setrobd((enemyh.getHp())-2);
							enemyh.setHp(2);
							enemyhpt.setValue(2);
							enemyhpt.setString("2"+" / "+enemyhpt.getMaximum());
						}
						if(userh.zy) {
							new Thread() {
								@Override
								public void run() {
									int whenstart=r+1;
									int whenstop=r+2;
									while(true) {
										if(rend==whenstart) {
											if(userh.getHp()>0) {
												int d = 4 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
												if(d<0) d=0;
												UpdateJTextArea("【"+userh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
												balancezy(userh, d);
											}
											break;
										}
										try {
											sleep(700);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									while(true) {
										if(rend==whenstop) {
											if(userh.getHp()>0) {
												int d = 4 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
												if(d<0) d=0;
												UpdateJTextArea("【"+userh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
												balancezy(userh, d);
											}
											break;
										}
										try {
											sleep(700);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
								}
							}.start();
						}
					}
				} else if(from.equals(enemyh)) {
					if(userh.zkxW>0) {
						int d = enemyh.getAtk()-(int)Math.round(userh.getDef()*(1-enemyh.getAdp()));
						if(d<0) d=0;
						if(enemyh.yjg) d=(int)Math.round(d*1.36);
						if(userh.jrz) d=(int)Math.round(d*0.75);
						d=(int)Math.round(d*(1-userh.getDefrate()));
						if(enemyh.lmQ) d=(int)Math.round(d*0.5);
						userh.zkxWH+=d;
						userh.bzyy.setDescribe("该英雄完全免疫物理伤害。目前已积累（"+userh.zkxWH+"/20）点伤害。");
						UpdateJTextArea("【"+userh.getName()+"】受到了0点物理伤害！"+"\n\n");
						if(userh.zkxWH>=20) {
							userh.zkxW=0;
							UpdateJTextArea("【"+userh.getName()+"】的【冰之羽翼】已破碎！"+"\n\n");
							userbuff.remove(userh.bzyy);
							repaint();
						}
						if(enemyh.sjjE) {
							enemyh.sjjE=false;
							UpdateJTextArea("【"+enemyh.getName()+"】发动了【光炽剑】的技能效果并回复了3点生命值！"+"\n\n");
							if(enemyh.getHp()+3<=enemyhpt.getMaximum()) {
								hpp += 3;
								enemyh.setHp(enemyh.getHp()+3);
								enemyhpt.setValue(enemyh.getHp());
								enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
							} else {
								enemyh.setHp(enemyhpt.getMaximum());
								enemyhpt.setValue(enemyh.getHp());
								enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
							}
							d = 6 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
							if(d<0) d=0;
							balanceskill(enemyh, d);
							enemybuff.remove(enemyh.gcj);
							repaint();
						}
						if(enemyh.zy) {
							new Thread() {
								@Override
								public void run() {
									int whenstart=r+1;
									int whenstop=r+2;
									while(true) {
										if(rend==whenstart) {
											if(enemyh.getHp()>0) {
												int d = 4 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
												if(d<0) d=0;
												UpdateJTextArea("【"+enemyh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
												balancezy(enemyh, d);
											}
											break;
										}
										try {
											sleep(700);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									while(true) {
										if(rend==whenstop) {
											if(enemyh.getHp()>0) {
												int d = 4 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
												if(d<0) d=0;
												UpdateJTextArea("【"+enemyh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
												balancezy(enemyh, d);
											}
											break;
										}
										try {
											sleep(700);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
								}
							}.start();
						}
					} else {
						enemyh.setHp(enemyh.getHp()-7);
						enemyhpt.setValue(enemyh.getHp());
						enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
						int d = (int)Math.round(3*(enemyh.getAtk()-userh.getDef()*(1-enemyh.getAdp()))-userh.getDef()*(1-enemyh.getAdp()));
						if(d<0) d=0;
						if(userh.jrz) d=(int)Math.round(d*0.75);
						if(userh.zxyE>0) {
							userh.zxyEE=true;
							d = (int)Math.round(d*0.2);
						}
						d=(int)Math.round(d*(1-userh.getDefrate()));
						if(userh.getId()==Config.xyh.getId()) {
							if(userh.xyh+d<=8) userh.xyh+=d;
							else userh.xyh=8;
							userh.getQ().setMp(userh.xyh);
							skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
						}
						if(userh.sjjR>0&&d>0) {
							if(d-4>=0) {
								UpdateJTextArea("【"+userh.getName()+"】牺牲了一名禁卫军，抵挡了4点伤害！"+"\n\n");
								d -= 4;
							}
							else {
								UpdateJTextArea("【"+userh.getName()+"】牺牲了一名禁卫军，抵挡了"+d+"点伤害！"+"\n\n");
								d = 0;
							}
							userh.sjjR--;
							if(userh.sjjR!=0) {
								userh.gzhl.setSuperpose("（禁卫军数量："+userh.sjjR+"）");
							} else {
								userbuff.remove(userh.gzhl);
								repaint();
							}
						}
						if(userh.xyhE>0) {
							userh.xyhED+=d;
							d=0;
							userh.sgsl.setDescribe("该英雄完全免疫任何伤害。目前已积累"+userh.xyhED+"点伤害。");
							userh.sgsl.setRound(userh.xyhE);
							repaint();
						}
						UpdateJTextArea("【"+userh.getName()+"】受到了"+d+"点物理伤害！\n\n");
						if(userh.getHp()-d>2) {
							hurt = hurt + d;
							setrobh(d);
							userh.setHp(userh.getHp()-d);
							userhpt.setValue(userh.getHp());
							userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
						} else {
							hurt = hurt + (d-userh.getHp()-2);
							setrobh((d-userh.getHp())-2);
							userh.setHp(2);
							userhpt.setValue(2);
							userhpt.setString("2"+" / "+enemyhpt.getMaximum());
						}
						if(enemyh.zy) {
							new Thread() {
								@Override
								public void run() {
									int whenstart=r+1;
									int whenstop=r+2;
									while(true) {
										if(rend==whenstart) {
											if(enemyh.getHp()>0) {
												int d = 4 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
												if(d<0) d=0;
												UpdateJTextArea("【"+enemyh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
												balancezy(enemyh, d);
											}
											break;
										}
										try {
											sleep(700);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									while(true) {
										if(rend==whenstop) {
											if(enemyh.getHp()>0) {
												int d = 4 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
												if(d<0) d=0;
												UpdateJTextArea("【"+enemyh.getName()+"】发动了【紫月神杖】的技能效果！\n\n");
												balancezy(enemyh, d);
											}
											break;
										}
										try {
											sleep(700);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
								}
							}.start();
						}
					}
				}
				break;
			}
			case 15:{//郈与却Q 先入为主
				if(from.equals(userh)) {
					if(userh.jhjj==3) {
						UpdateJTextArea("【"+userh.getName()+"】的技能伤害提升了30%！\n\n");
						userh.hyqQ+=3;
						userh.hyqJ+=0.3;
						userbuff.remove(userh.xrwz);
						userbuff.add(userh.xrwz);
						userh.xrwz.setRound(userh.hyqQ);
						userh.xrwz.setDescribe("该英雄的技能伤害提升了"+userh.hyqJ*100+"%。");
						repaint();
						new Thread() {
							@Override
							public void run() {
								int whenstop=r+3;
								while(true) {
									if(r==whenstop) {
										if(userh.getId()==Config.hyq.getId()) {
											userh.hyqJ-=0.3;
											if(userh.hyqJ<0) userh.hyqJ=0;
											userbuff.remove(userh.xrwz);
											repaint();
										}
										break;
									}
									try {
										sleep(1000);//可以延时1秒进行判定
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}.start();
					} else {
						UpdateJTextArea("【"+userh.getName()+"】的魔法穿透提升了30%！\n\n");
						double oldapp = userh.getApp();//获取魔法穿透
						if(userh.hyqQ==0) userh.setApp(oldapp+0.3);//设置魔法穿透 +30%
						userh.hyqQ+=3;
						userbuff.remove(userh.xrwz);
						userbuff.add(userh.xrwz);
						userh.xrwz.setRound(userh.hyqQ);
						userh.xrwz.setDescribe("该英雄的魔法穿透提升了30%。");
						repaint();
						new Thread() {
							@Override
							public void run() {
								while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
									if(userh.getId()==Config.hyq.getId()) {
										if(userh.hyqQ==0) {//回合来到我们设置技能失效的那个回合 跳出循环
											double oldapp = userh.getApp();
											userh.setApp(oldapp-0.3);
											userbuff.remove(userh.xrwz);
											repaint();
											break;
										}
									} else {
										break;
									}
									try {
										sleep(1000);//可以延时1秒进行判定
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}.start();
					}
				} else if(from.equals(enemyh)) {
					if(enemyh.jhjj==3) {
						UpdateJTextArea("【"+enemyh.getName()+"】的技能伤害提升了30%！\n\n");
						enemyh.hyqQ+=3;
						enemyh.hyqJ+=0.3;
						enemybuff.remove(enemyh.xrwz);
						enemybuff.add(enemyh.xrwz);
						enemyh.xrwz.setRound(enemyh.hyqQ);
						enemyh.xrwz.setDescribe("该英雄的技能伤害提升了"+enemyh.hyqJ*100+"%。");
						repaint();
						new Thread() {
							@Override
							public void run() {
								int whenstop=r+3;
								while(true) {
									if(r==whenstop) {
										if(enemyh.getId()==Config.hyq.getId()) {
											enemyh.hyqJ-=0.3;
											if(enemyh.hyqJ<0) enemyh.hyqJ=0;
											enemybuff.remove(enemyh.xrwz);
											repaint();
										}
										break;
									}
									try {
										sleep(1000);//可以延时1秒进行判定
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}.start();
					} else {
						UpdateJTextArea("【"+enemyh.getName()+"】的魔法穿透提升了30%！\n\n");
						double oldapp = enemyh.getApp();
						if(enemyh.hyqQ==0) enemyh.setApp(oldapp+0.3);
						enemyh.hyqQ+=3;
						enemybuff.remove(enemyh.xrwz);
						enemybuff.add(enemyh.xrwz);
						enemyh.xrwz.setRound(enemyh.hyqQ);
						enemyh.xrwz.setDescribe("该英雄的魔法穿透提升了30%。");
						repaint();
						new Thread() {
							@Override
							public void run() {
								while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
									if(enemyh.getId()==Config.hyq.getId()) {
										if(enemyh.hyqQ==0) {//回合来到我们设置技能失效的那个回合 跳出循环
											double oldapp = enemyh.getApp();
											enemyh.setApp(oldapp-0.3);
											enemybuff.remove(enemyh.xrwz);
											repaint();
											break;
										}
									} else {
										break;
									}
									try {
										sleep(1000);//可以延时1秒进行判定
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}.start();
					}
				}
				break;
			}
			case 16:{//郈与却W 强力剥削
				if(from.equals(userh)) {
					UpdateJTextArea("【"+enemyh.getName()+"】的物理护甲被减少了4点！\n\n");
					if(enemyh.hyqW==0) enemyh.setDef(enemyh.getDef()-4);
					int id = enemyh.getId();
					enemyh.hyqW+=3;
					enemytx.setToolTipText(enemyh.getProperty());
					enemybuff.remove(enemyh.qlbx);
					enemybuff.add(enemyh.qlbx);
					enemyh.qlbx.setRound(enemyh.hyqW);
					repaint();
					new Thread() {
						@Override
						public void run() {
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(enemyh.getId()==id) {
									if(enemyh.hyqW==0) {//回合来到我们设置技能失效的那个回合 跳出循环
										enemyh.setDef(enemyh.getDef()+4);
										enemytx.setToolTipText(enemyh.getProperty());
										enemybuff.remove(enemyh.qlbx);
										repaint();
										break;
									}
								} else {
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("【"+userh.getName()+"】的物理护甲被减少了4点！\n\n");
					if(userh.hyqW==0) userh.setDef(userh.getDef()-4);
					int id = userh.getId();
					userh.hyqW+=3;
					usertx.setToolTipText(userh.getProperty());
					userbuff.remove(userh.qlbx);
					userbuff.add(userh.qlbx);
					userh.qlbx.setRound(userh.hyqW);
					repaint();
					new Thread() {
						@Override
						public void run() {
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(userh.getId()==id) {
									if(userh.hyqW==0) {//回合来到我们设置技能失效的那个回合 跳出循环
										userh.setDef(userh.getDef()+4);
										usertx.setToolTipText(userh.getProperty());
										userbuff.remove(userh.qlbx);
										repaint();
										break;
									}
								} else {
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				break;
			}
			case 17:{//郈与却E 星月奇迹
				if(from.equals(userh)) {
					int mk = enemyh.getAdf();
					int d = 0;
					if(userh.jhjj==2) d = 14 - (int)Math.round((1-userh.getApp())*mk);
					else d = 10 - (int)Math.round((1-userh.getApp())*mk);
					if(d<0) d=0;
					balanceskill(userh, d);
					if(userh.jhjj==2) userh.setXdl(userh.getXdl()-999);
				} else if(from.equals(enemyh)) {
					int mk = userh.getAdf();
					int d = 0;
					if(enemyh.jhjj==2) d = 14 - (int)Math.round((1-enemyh.getApp())*mk);
					else d = 10 - (int)Math.round((1-enemyh.getApp())*mk);
					if(d<0) d=0;
					balanceskill(enemyh, d);
					if(enemyh.jhjj==2) enemyh.setXdl(enemyh.getXdl()-999);
				}
				break;
			}
			case 18:{//郈与却R 云霄之巅
				if(from.equals(userh)) {
					int d=0;
					if(userh.jhjj==1) d = 14 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());//由公式转换为魔法伤害
					else d = 10 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
					if(d<0) d=0;
					UpdateJTextArea("【"+userh.getName()+"】将在下两回合获得4点攻击力。\n\n");
					balanceskill(userh, d);
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;//设置技能生效的回合
							int whenstop=r+3;//设置技能失效的回合
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(r==whenstart) {
									if(userh.getId()==Config.hyq.getId()) {
										userh.setAtk(userh.getAtk()+4);
									}
									usertx.setToolTipText(userh.getProperty());
									userbuff.remove(userh.yxzd);
									userbuff.add(userh.yxzd);
									repaint();
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(r==whenstop) {//回合来到我们设置技能失效的那个回合 跳出循环
									if(userh.getId()==Config.hyq.getId()) {
										userh.setAtk(userh.getAtk()-4);
									}
									usertx.setToolTipText(userh.getProperty());
									userbuff.remove(userh.yxzd);
									repaint();
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
					userh.setXdl(userh.getXdl()-999);
				} else if(from.equals(enemyh)) {
					int d=0;
					if(enemyh.jhjj==1) d = 14 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
					else d = 10 - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
					if(d<0) d=0;
					UpdateJTextArea("【"+enemyh.getName()+"】将在下两回合获得4点攻击力。\n\n");
					balanceskill(enemyh, d);
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;//设置技能生效的回合
							int whenstop=r+3;//设置技能失效的回合
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(r==whenstart) {
									if(enemyh.getId()==Config.hyq.getId()) {
										enemyh.setAtk(enemyh.getAtk()+4);
									}
									enemytx.setToolTipText(enemyh.getProperty());
									enemybuff.remove(enemyh.yxzd);
									enemybuff.add(enemyh.yxzd);
									repaint();
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(r==whenstop) {//回合来到我们设置技能失效的那个回合 跳出循环
									if(enemyh.getId()==Config.hyq.getId()) {
										enemyh.setAtk(enemyh.getAtk()-4);
									}
									enemytx.setToolTipText(enemyh.getProperty());
									enemybuff.remove(enemyh.yxzd);
									repaint();
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
					enemyh.setXdl(enemyh.getXdl()-999);
				}
				break;
			}
			case 19:{//谢悠涵Q 洁净之灵
				if(from.equals(userh)) {
					if(r%2!=0) { // 单数回合
						userh.xyhQHPP=userh.xyh+4;
						userh.xyh=0;
						userh.getQ().setMp(userh.xyh);
						skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
						userh.xyhQ=true;
						UpdateJTextArea("【"+userh.getName()+"】将在回合结束时获得"+userh.xyhQHPP+"点生命回复。\n\n");
					} else if(r%2==0) { // 双数回合
						balancereal(userh, userh.xyh);
						userh.xyh=0;
						userh.getQ().setMp(userh.xyh);
						skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
					}
					userh.setXdl(userh.getXdl()-9999);
				} else if(from.equals(enemyh)) {
					if(r%2!=0) { // 单数回合
						enemyh.xyhQHPP=enemyh.xyh+4;
						enemyh.xyh=0;
						enemyh.getQ().setMp(enemyh.xyh);
						enemyh.xyhQ=true;
						UpdateJTextArea("【"+enemyh.getName()+"】将在回合结束时获得"+enemyh.xyhQHPP+"点生命回复。\n\n");
					} else if(r%2==0) { // 双数回合
						balancereal(enemyh, enemyh.xyh);
						enemyh.xyh=0;
						enemyh.getQ().setMp(enemyh.xyh);
					}
					enemyh.setXdl(enemyh.getXdl()-9999);
				}
				break;
			}
			case 20:{//谢悠涵W 天圆地方
				if(from.equals(userh)) {
					int mk = enemyh.getAdf();//获取对面魔抗
					int d = 10 - (int)Math.round((1-userh.getApp())*mk);//由公式转换为魔法伤害
					if(d<0) d=0;
					UpdateJTextArea("【"+enemyh.getName()+"】将在下一回合【战斗不能】。\n\n");
					balanceskill(userh, d);
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;//设置技能生效的回合
							int whenstop=r+2;//设置技能失效的回合
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(r==whenstart) {
									if(enemyh.yyzs) {
										eyyzs--;
										UpdateJTextArea("【"+enemyh.getName()+"】发动了【夜宴之声】的效果抵挡了一次【战斗不能】！\n\n");
									} else if(enemyh.fs) {
										UpdateJTextArea("【" + enemyh.getName() + "】发动了【复苏】的效果抵挡了一次【战斗不能】！\n\n");
									} else {
										enemyh.setIsfight(false);
										enemybuff.remove(enemyh.tydf);
										enemybuff.add(enemyh.tydf);
										repaint();
									}
									break;
								}
								try {
									sleep(500);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(rend==whenstop) {//回合来到我们设置技能失效的那个回合 跳出循环
									enemyh.setIsfight(true);
									enemybuff.remove(enemyh.tydf);
									repaint();
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				} else if(from.equals(enemyh)) {
					int mk = userh.getAdf();//获取我的魔抗
					int d = 10 - (int)Math.round((1-enemyh.getApp())*mk);//由公式转换为魔法伤害
					if(d<0) d=0;
					UpdateJTextArea("【"+userh.getName()+"】将在下一回合【战斗不能】。\n\n");
					balanceskill(enemyh, d);
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;//设置技能生效的回合
							int whenstop=r+2;//设置技能失效的回合
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(r==whenstart) {
									if(userh.yyzs) {
										uyyzs--;
										UpdateJTextArea("【"+userh.getName()+"】发动了【夜宴之声】的效果抵挡了一次【战斗不能】！\n\n");
										if(uyyzs%3==0) {
											if(userh.getZ().getName().equals("夜宴之声")) {
												setE(userh.getZ().getId(),3);
												getUserEquip(userh.getZ().getId()+20);
												userh.setZ(null);
												zb1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e1.png")));
												zb1.setToolTipText("点击选择一个装备并穿戴。");
											} else if(userh.getX().getName().equals("夜宴之声")) {
												setE(userh.getX().getId(),4);
												getUserEquip(userh.getX().getId()+20);
												userh.setX(null);
												zb2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
												zb2.setToolTipText("点击选择一个装备并穿戴。");
											}
										}
									} else if(userh.fs) {
										UpdateJTextArea("【" + userh.getName() + "】发动了【复苏】的效果抵挡了一次【战斗不能】！\n\n");
									} else {
										userh.setIsfight(false);
										userbuff.remove(userh.tydf);
										userbuff.add(userh.tydf);
										repaint();
									}
									break;
								}
								try {
									sleep(500);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(rend==whenstop) {//回合来到我们设置技能失效的那个回合 跳出循环
									userh.setIsfight(true);
									userbuff.remove(userh.tydf);
									repaint();
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				break;
			}
			case 21:{//谢悠涵E 时光沙漏
				if(from.equals(userh)) {
					UpdateJTextArea("【"+userh.getName()+"】将对任何伤害免疫（除了魔王怒）！\n\n");
					userh.xyhE+=4;
					userbuff.remove(userh.sgsl);
					userbuff.add(userh.sgsl);
					userh.sgsl.setDescribe("该英雄完全免疫任何伤害。目前已积累"+userh.xyhED+"点伤害。");
					userh.sgsl.setRound(userh.xyhE);
					repaint();
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("【"+enemyh.getName()+"】将对任何伤害免疫（除了魔王怒）！\n\n");
					enemyh.xyhE+=4;
					enemybuff.remove(enemyh.sgsl);
					enemybuff.add(enemyh.sgsl);
					enemyh.sgsl.setDescribe("该英雄完全免疫任何伤害。目前已积累"+enemyh.xyhED+"点伤害。");
					enemyh.sgsl.setRound(enemyh.xyhE);
					repaint();
				}
				break;
			}
			case 22:{//张可汐Q 冰雪十字
				if(from.equals(userh)) {
					int mk = enemyh.getAdf();//获取对面魔抗
					int d = (4+userh.zkxQ) - (int)Math.round((1-userh.getApp())*mk);//由公式转换为魔法伤害
					if(d<0) d=0;
					UpdateJTextArea("【"+enemyh.getName()+"】将在下一回合【完全行动不能】。\n\n");
					balanceskill(userh, d);
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;//设置技能生效的回合
							int whenstop=r+2;//设置技能失效的回合
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(r==whenstart) {
									if(enemyh.yyzs) {
										eyyzs--;
										UpdateJTextArea("【"+enemyh.getName()+"】发动了【夜宴之声】的效果抵挡了一次【完全行动不能】！\n\n");
									} else if(enemyh.fs) {
										UpdateJTextArea("【" + enemyh.getName() + "】发动了【复苏】的效果抵挡了一次【完全行动不能】！\n\n");
									} else {
										enemyh.setIsgone(false);
										if(enemyh.xyhE>0) {
											enemyh.xyhE=0;
											if(enemyh.xyhED>0) {
												UpdateJTextArea("【"+enemyh.getName()+"】释放了他的【时光沙漏】！\n\n");
												int d = (int)Math.round(enemyh.xyhED*0.8 - ((1-enemyh.getApp())*userh.getAdf()));
												if(d<0) d=0;
												balanceskill(enemyh, d);
												enemyh.xyhED=0;
											} else {
												UpdateJTextArea("【"+enemyh.getName()+"】的【时光沙漏】已破碎！\n\n");
											}
											enemybuff.remove(enemyh.sgsl);
											repaint();
										}
										enemybuff.remove(enemyh.bxsz);
										enemybuff.add(enemyh.bxsz);
										repaint();
									}
									break;
								}
								try {
									sleep(500);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(rend==whenstop) {//回合来到我们设置技能失效的那个回合 跳出循环
									enemyh.setIsgone(true);
									enemybuff.remove(enemyh.bxsz);
									repaint();
									break;
								}
								try {
									sleep(500);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				} else if(from.equals(enemyh)) {
					int mk = userh.getAdf();//获取我的魔抗
					int d = (4+enemyh.zkxQ) - (int)Math.round((1-enemyh.getApp())*mk);//由公式转换为魔法伤害
					if(d<0) d=0;
					UpdateJTextArea("【"+userh.getName()+"】将在下一回合【完全行动不能】。\n\n");
					balanceskill(enemyh, d);
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;//设置技能生效的回合
							int whenstop=r+2;//设置技能失效的回合
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(r==whenstart) {
									if(userh.yyzs) {
										uyyzs--;
										UpdateJTextArea("【"+userh.getName()+"】发动了【夜宴之声】的效果抵挡了一次【完全行动不能】！\n\n");
										if(uyyzs%3==0) {
											if(userh.getZ().getName().equals("夜宴之声")) {
												setE(userh.getZ().getId(),3);
												getUserEquip(userh.getZ().getId()+20);
												userh.setZ(null);
												zb1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e1.png")));
												zb1.setToolTipText("点击选择一个装备并穿戴。");
											} else if(userh.getX().getName().equals("夜宴之声")) {
												setE(userh.getX().getId(),4);
												getUserEquip(userh.getX().getId()+20);
												userh.setX(null);
												zb2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
												zb2.setToolTipText("点击选择一个装备并穿戴。");
											}
										}
									} else if(userh.fs) {
										UpdateJTextArea("【" + userh.getName() + "】发动了【复苏】的效果抵挡了一次【完全行动不能】！\n\n");
									} else {
										userh.setIsgone(false);
										if(userh.xyhE>0) {
											userh.xyhE=0;
											if(userh.xyhED>0) {
												UpdateJTextArea("【"+userh.getName()+"】已经释放了【时光沙漏】！\n\n");
												int d = (int)Math.round(userh.xyhED*0.8 - ((1-userh.getApp())*enemyh.getAdf()));
												if(d<0) d=0;
												balanceskill(userh, d);
												userh.xyhED=0;
											} else {
												UpdateJTextArea("【"+userh.getName()+"】的【时光沙漏】已破碎！\n\n");
											}
											userbuff.remove(userh.sgsl);
											repaint();
										}
										userbuff.remove(userh.bxsz);
										userbuff.add(userh.bxsz);
										repaint();
									}
									break;
								}
								try {
									sleep(500);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(rend==whenstop) {//回合来到我们设置技能失效的那个回合 跳出循环
									userh.setIsgone(true);
									userbuff.remove(userh.bxsz);
									repaint();
									break;
								}
								try {
									sleep(500);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				break;
			}
			case 23:{//张可汐W 冰之羽翼
				if(from.equals(userh)) { // 判断使用技能的英雄是我方英雄
					UpdateJTextArea("【"+userh.getName()+"】获得了物理伤害免疫的效果！\n\n");
					userh.zkxW+=3;//这个技能持续3回合
					userh.zkxWH=0;
					userbuff.remove(userh.bzyy);
					userbuff.add(userh.bzyy);
					userh.bzyy.setRound(userh.zkxW);
					userh.bzyy.setDescribe("该英雄完全免疫物理伤害。目前已积累（"+userh.zkxWH+"/20）点伤害。");
					repaint();
				} else if(from.equals(enemyh)) { // 判断使用技能的英雄是对方英雄
					UpdateJTextArea("【"+enemyh.getName()+"】获得了物理伤害免疫的效果！\n\n");
					enemyh.zkxW+=3;//这个技能持续3回合
					enemyh.zkxWH=0;
					enemybuff.remove(enemyh.bzyy);
					enemybuff.add(enemyh.bzyy);
					enemyh.bzyy.setRound(enemyh.zkxW);
					enemyh.bzyy.setDescribe("该英雄完全免疫物理伤害。目前已积累（"+enemyh.zkxWH+"/20）点伤害。");
					repaint();
				}
				break;
			}
			case 24:{//张可汐E 汐之抉择
				if(from.equals(userh)) {
					p = new Random().nextInt(3);
					sendP(p);
					UpdateJTextArea("【"+userh.getName()+"】的【冰雪十字】和【汐之抉择】效果二获得了1点魔法伤害加成！\n\n");
					userh.zkxQ++;
					userh.zkxE++;
					userh.getQ().setdescribe("对对方造成"+(4+userh.zkxQ)+"点魔法伤害，并使对方下个回合【完全行动不能】。");
					userh.getE().setdescribe("<html>为冰雪十字和汐之抉择效果二提供永久的1点魔法伤害加成。<br/>随机触发以下效果（该技能在回合数为3及以上时才能使用）：<br />"+
							"1）下两回合内，提供冰雪十字3点魔法伤害加成；<br />2）对方受到"+(8+userh.zkxE)+"点魔法伤害；<br />3）提升4点双抗，持续3回合。</html>");
					skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
					skill3.setToolTipText("<html>（E）"+userh.getE().getSkill()+"</html>");
					userh.xzjz.setV1(userh.xzjz.getV1()+1);
					userh.xzjz.setSuperpose("（当前层数："+userh.xzjz.getV1()+"）");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(p==0) {
						UpdateJTextArea("【"+userh.getName()+"】随机触发了【汐之抉择】效果一！\n\n");
						UpdateJTextArea("【"+userh.getName()+"】的【冰雪十字】获得了3点魔法伤害加成！\n\n");
						userh.zkxQ+=3;
						userbuff.remove(userh.xzjzsh);
						userbuff.add(userh.xzjzsh);
						userh.xzjzsh.setDescribe("汐之抉择的效果一为冰雪十字提供了"+userh.zkxQ+"点魔法伤害加成。");
						repaint();
						new Thread() {
							@Override
							public void run() {
								int whenstop=r+3;
								userh.getQ().setdescribe("对对方造成"+(4+userh.zkxQ)+"点魔法伤害，并使对方下个回合【完全行动不能】。");
								skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
								while (true) {
									if (r==whenstop) {
										if(userh.getId()==Config.zkx.getId()) {
											userh.zkxQ-=3;
											userh.getQ().setdescribe("对对方造成"+(4+userh.zkxQ)+"点魔法伤害，并使对方下个回合【完全行动不能】。");
											skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
											if(userh.zkxQ==0) {
												userbuff.remove(userh.xzjzsh);
												repaint();
											} else {
												userh.xzjzsh.setDescribe("汐之抉择的效果一为冰雪十字提供了"+userh.zkxQ+"点魔法伤害加成。");
											}
											repaint();
										}
										break;
									}
									try {
										sleep(500);// 可以延时1秒进行判定
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}.start();
					} else if(p==1) {
						UpdateJTextArea("【"+userh.getName()+"】随机触发了【汐之抉择】效果二！\n\n");
						int d = (8+userh.zkxE) - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
						if(d<0) d=0;
						balanceskill(userh, d);
					} else {
						UpdateJTextArea("【"+userh.getName()+"】随机触发了【汐之抉择】效果三！\n\n");
						UpdateJTextArea("【"+userh.getName()+"】提升了4点物理护甲和4点魔法防御！\n\n");
						userh.setDef(userh.getDef()+4);
						userh.setAdf(userh.getAdf()+4);
						userbuff.remove(userh.xzjzwk);
						userbuff.remove(userh.xzjzmk);
						userbuff.add(userh.xzjzwk);
						userbuff.add(userh.xzjzmk);
						repaint();
						new Thread() {
							@Override
							public void run() {
								int whenstop=r+3;
								while(true) {
									if(r==whenstop) {
										if(userh.getId()==Config.zkx.getId()) {
											userh.setDef(userh.getDef()-4);
											userh.setAdf(userh.getAdf()-4);
											userbuff.remove(userh.xzjzwk);
											userbuff.remove(userh.xzjzmk);
											repaint();
										}
										break;
									}
									try {
										sleep(500);//可以延时1秒进行判定
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}.start();
					}
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("【"+enemyh.getName()+"】的【冰雪十字】和【汐之抉择】效果二获得了1点魔法伤害加成！\n\n");
					enemyh.zkxQ++;
					enemyh.zkxE++;
					enemyh.xzjz.setV1(enemyh.xzjz.getV1()+1);
					enemyh.xzjz.setSuperpose("（当前层数："+enemyh.xzjz.getV1()+"）");
					try {
						p = dis5.readInt();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(p==0) {
						UpdateJTextArea("【"+enemyh.getName()+"】随机触发了【汐之抉择】效果一！\n\n");
						UpdateJTextArea("【"+enemyh.getName()+"】的【冰雪十字】获得了3点魔法伤害加成！\n\n");
						enemyh.zkxQ+=3;
						enemybuff.remove(enemyh.xzjzsh);
						enemybuff.add(enemyh.xzjzsh);
						enemyh.xzjzsh.setDescribe("汐之抉择的效果一为冰雪十字提供了"+enemyh.zkxQ+"点魔法伤害加成。");
						repaint();
						new Thread() {
							@Override
							public void run() {
								int whenstop=r+3;
								while (true) {
									if (r==whenstop) {
										if(enemyh.getId()==Config.zkx.getId()) {
											enemyh.zkxQ-=3;
											if(enemyh.zkxQ==0) {
												enemybuff.remove(enemyh.xzjzsh);
												repaint();
											} else {
												enemyh.xzjzsh.setDescribe("汐之抉择的效果一为冰雪十字提供了"+enemyh.zkxQ+"点魔法伤害加成。");
											}
											repaint();
										}
										break;
									}
									try {
										sleep(500);// 可以延时1秒进行判定
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}.start();
					} else if(p==1) {
						UpdateJTextArea("【"+enemyh.getName()+"】随机触发了【汐之抉择】效果二！\n\n");
						int d = (8+enemyh.zkxE) - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
						if(d<0) d=0;
						balanceskill(enemyh, d);
					} else {
						UpdateJTextArea("【"+enemyh.getName()+"】随机触发了【汐之抉择】效果三！\n\n");
						UpdateJTextArea("【"+enemyh.getName()+"】提升了4点物理护甲和4点魔法防御！\n\n");
						enemyh.setDef(enemyh.getDef()+4);
						enemyh.setAdf(enemyh.getAdf()+4);
						enemybuff.remove(enemyh.xzjzwk);
						enemybuff.remove(enemyh.xzjzmk);
						enemybuff.add(enemyh.xzjzwk);
						enemybuff.add(enemyh.xzjzmk);
						repaint();
						new Thread() {
							@Override
							public void run() {
								int whenstop=r+3;
								while(true) {
									if(r==whenstop) {
										if(enemyh.getId()==Config.zkx.getId()) {
											enemyh.setDef(enemyh.getDef()-4);
											enemyh.setAdf(enemyh.getAdf()-4);
											enemybuff.remove(enemyh.xzjzwk);
											enemybuff.remove(enemyh.xzjzmk);
											repaint();
										}
										break;
									}
									try {
										sleep(500);//可以延时1秒进行判定
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}.start();
					}
				}
				p=0;
				break;
			}
			case 25:{//郑心予Q 礼赞
				if(from.equals(userh)) {
					UpdateJTextArea("【"+userh.getName()+"】将在下一次造成魔法伤害时触发【礼赞】效果。\n\n");
					userh.zxyQ=true;
					userbuff.remove(userh.lz);
					userbuff.add(userh.lz);
					repaint();
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("【"+enemyh.getName()+"】将在下一次造成魔法伤害时触发【礼赞】效果。\n\n");
					enemyh.zxyQ=true;
					enemybuff.remove(enemyh.lz);
					enemybuff.add(enemyh.lz);
					repaint();
				}
				break;
			}
			case 26:{//郑心予W 流星
				if(from.equals(userh)) {
					int d=0;
					if(userh.jhjj==2) {
						int mk = enemyh.getAdf();
						d = 20 - (int)Math.round((1-userh.getApp())*mk);
						if(d<0) d=0;
					} else {
						int mk = enemyh.getAdf();
						d = 12 - (int)Math.round((1-userh.getApp())*mk);
						if(d<0) d=0;
					}
					balanceskill(userh, d);
				} else if(from.equals(enemyh)) {
					int d=0;
					if(enemyh.jhjj==2) {
						int mk = userh.getAdf();
						d = 20 - (int)Math.round((1-enemyh.getApp())*mk);
						if(d<0) d=0;
					} else {
						int mk = userh.getAdf();
						d = 12 - (int)Math.round((1-enemyh.getApp())*mk);
						if(d<0) d=0;
					}					
					balanceskill(enemyh, d);
				}
				break;
			}
			case 27:{//郑心予E 予恋之花
				if(from.equals(userh)) {
					UpdateJTextArea("【"+userh.getName()+"】获得了80%物理伤害减免！\n\n");
					UpdateJTextArea("【"+enemyh.getName()+"】将被限制仅能使用普攻。\n\n");
					userh.zxyE+=2;
					userbuff.remove(userh.userylzh);
					userbuff.add(userh.userylzh);
					userh.userylzh.setRound(userh.zxyE);
					repaint();
					if(!enemyh.fs) {
						enemyh.setIsskill(false);
						enemybuff.remove(enemyh.enemyylzh);
						enemybuff.add(enemyh.enemyylzh);
						enemyh.enemyylzh.setRound(userh.zxyE);
						repaint();
					} else {
						UpdateJTextArea("【" + enemyh.getName() + "】发动了【复苏】的效果抵挡了一次【施法不能】！\n\n");
					}
					new Thread() {
						@Override
						public void run() {
							while(true) {
								if(userh.zxyE==0) {//回合来到我们设置技能失效的那个回合 跳出循环
									enemyh.setIsskill(true);
									userbuff.remove(userh.userylzh);
									enemybuff.remove(enemyh.enemyylzh);
									repaint();
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("【"+enemyh.getName()+"】获得了80%物理伤害减免！\n\n");
					UpdateJTextArea("【"+userh.getName()+"】将被限制仅能使用普攻。\n\n");
					enemyh.zxyE+=2;
					enemybuff.remove(enemyh.userylzh);
					enemybuff.add(enemyh.userylzh);
					enemyh.userylzh.setRound(enemyh.zxyE);
					repaint();
					if(!userh.fs) {
						userh.setIsskill(false);
						userbuff.remove(userh.enemyylzh);
						userbuff.add(userh.enemyylzh);
						userh.enemyylzh.setRound(enemyh.zxyE);
						repaint();
					} else {
						UpdateJTextArea("【" + userh.getName() + "】发动了【复苏】的效果抵挡了一次【施法不能】！\n\n");
					}
					new Thread() {
						@Override
						public void run() {
							while(true) {
								if(enemyh.zxyE==0) {//回合来到我们设置技能失效的那个回合 跳出循环
									userh.setIsskill(true);
									userbuff.remove(userh.enemyylzh);
									enemybuff.remove(enemyh.userylzh);
									repaint();
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				break;
			}
			case 28:{//郑心予R 心源神域
				if(from.equals(userh)) {
					UpdateJTextArea("【"+userh.getName()+"】将在下两回合的行动阶段开始前回复生命值。\n\n");
					userh.zxyR+=2;
					userbuff.remove(userh.xysy);
					userbuff.add(userh.xysy);
					userh.xysy.setRound(userh.zxyR);
					repaint();
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("【"+enemyh.getName()+"】将在下两回合的行动阶段开始前回复生命值。\n\n");
					enemyh.zxyR+=2;
					enemybuff.remove(enemyh.xysy);
					enemybuff.add(enemyh.xysy);
					enemyh.xysy.setRound(enemyh.zxyR);
					repaint();
				}
				break;
			}
			case 29:{//刘珂明 Q 剑舞
				if(from.equals(userh)) {//使用者是我方
					UpdateJTextArea("【"+userh.getName()+"】使用了一次【普通攻击】！\n\n");//将效果显示在公屏上
					balanceatk(userh);
					UpdateJTextArea("【"+userh.getName()+"】使用了一次【普通攻击】！\n\n");//将效果显示在公屏上
					userh.lmQ=true;
					balanceatk(userh);
					userh.lmQ=false;
				} else if(from.equals(enemyh)) {//使用者是对方
					UpdateJTextArea("【"+enemyh.getName()+"】使用了一次【普通攻击】！\n\n");//将效果显示在公屏上
					balanceatk(enemyh);
					UpdateJTextArea("【"+enemyh.getName()+"】使用了一次【普通攻击】！\n\n");//将效果显示在公屏上
					enemyh.lmQ=true;
					balanceatk(enemyh);
					enemyh.lmQ=false;
				}
				break;
			}
			case 30:{//刘珂明W 月光剑
				if(from.equals(userh)) {
					int enemyhp=enemyhpt.getMaximum();
					int d=(int)Math.round((enemyhp*0.45)-(1-userh.getAdp())*enemyh.getDef());
					if(d<0) d=0;
					balancephyical(userh, d);
					userh.lmW=true;
				}else if(from.equals(enemyh)) {
					int d=(int)Math.round((userhpt.getMaximum()*0.45)-(1-enemyh.getAdp())*userh.getDef());
					if(d<0) d=0;
					balancephyical(enemyh, d);
					enemyh.lmW=true;
				}
				break;
			}
			case 31:{//苏璟静Q 闪现+
				if(from.equals(userh)) {//使用者是我
					UpdateJTextArea("【"+userh.getName()+"】获得了80%闪避普通攻击的概率和2点魔法防御！\n\n");
					if(userh.jhjj==3) {
						userh.sjjQ+=3;
					} else {
						userh.sjjQ=2;
					}
					userh.setAdf(userh.getAdf()+2);
					usertx.setToolTipText(userh.getProperty());
					userbuff.remove(userh.sxplus);
					userbuff.add(userh.sxplus);
					userh.sxplus.setRound(userh.sjjQ);
					userbuff.remove(userh.sxmk);
					userbuff.add(userh.sxmk);
					userh.sxmk.setRound(userh.sjjQ);
					repaint();
					new Thread() {
						@Override
						public void run() {
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(userh.getId()==Config.sjj.getId()) {
									if(userh.sjjQ==0) {//回合来到我们设置技能失效的那个回合 跳出循环
										userh.setAdf(userh.getAdf()-2);
										usertx.setToolTipText(userh.getProperty());
										userbuff.remove(userh.sxplus);
										userbuff.remove(userh.sxmk);
										repaint();
										break;
									}
								} else {
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				} else if(from.equals(enemyh)) {//使用者是对方
					UpdateJTextArea("【"+enemyh.getName()+"】获得了80%闪避普通攻击的概率和2点魔法防御！\n\n");
					if(enemyh.jhjj==3) {
						enemyh.sjjQ+=3;
					} else {
						enemyh.sjjQ=2;
					}
					enemyh.setAdf(enemyh.getAdf()+2);
					enemytx.setToolTipText(enemyh.getProperty());
					enemybuff.remove(enemyh.sxplus);
					enemybuff.add(enemyh.sxplus);
					enemyh.sxplus.setRound(enemyh.sjjQ);
					enemybuff.remove(enemyh.sxmk);
					enemybuff.add(enemyh.sxmk);
					enemyh.sxmk.setRound(enemyh.sjjQ);
					repaint();
					new Thread() {
						@Override
						public void run() {
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(enemyh.getId()==Config.sjj.getId()) {
									if(enemyh.sjjQ==0) {//回合来到我们设置技能失效的那个回合 跳出循环
										enemyh.setAdf(enemyh.getAdf()-2);
										enemytx.setToolTipText(enemyh.getProperty());
										enemybuff.remove(enemyh.sxplus);
										enemybuff.remove(enemyh.sxmk);
										repaint();
										break;
									}
								} else {
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				break;
			}
			case 32:{//苏璟静W 裂缝
				if(from.equals(userh)) {
					int attack=userh.getAtk();
					UpdateJTextArea("【"+userh.getName()+"】提升了3点攻击力并对【"+enemyh.getName()+"】造成了【战斗不能】！\n\n");
					userh.setAtk(attack+3);
					userbuff.remove(userh.lfatk);
					userbuff.add(userh.lfatk);
					repaint();
					if(enemyh.yyzs) {
						eyyzs--;
						UpdateJTextArea("【"+enemyh.getName()+"】发动了【夜宴之声】的效果抵挡了一次【战斗不能】！\n\n");
					} else if(enemyh.fs) {
						UpdateJTextArea("【" + enemyh.getName() + "】发动了【复苏】的效果抵挡了一次【战斗不能】！\n\n");
					} else {
						enemyh.setIsfight(false);
						if(enemyh.xyhE>0) {
							enemyh.xyhE=0;
							if(enemyh.xyhED>0) {
								UpdateJTextArea("【"+enemyh.getName()+"】释放了他的【时光沙漏】！\n\n");
								int d = (int)Math.round(enemyh.xyhED*0.8 - ((1-enemyh.getApp())*userh.getAdf()));
								if(d<0) d=0;
								balanceskill(enemyh, d);
								enemyh.xyhED=0;
							} else {
								UpdateJTextArea("【"+enemyh.getName()+"】的【时光沙漏】已破碎！\n\n");
							}
							enemybuff.remove(enemyh.sgsl);
							repaint();
						}
						enemybuff.remove(enemyh.lf);
						enemybuff.add(enemyh.lf);
						repaint();
					}
					userh.sjjW=true;
					usertx.setToolTipText(userh.getProperty());
					new Thread() {
						@Override
						public void run() {
							int whenstop=r+2;//设置技能失效的回合
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(rend==whenstop) {//回合来到我们设置技能失效的那个回合 跳出循环
									if(userh.sjjW) {
										userh.sjjW=false;//使苏璟静技能失效
										int attack=userh.getAtk();
										userh.setAtk(attack-3);
										enemyh.setIsfight(true);
										usertx.setToolTipText(userh.getProperty());
										enemybuff.remove(enemyh.lf);
										userbuff.remove(userh.lfatk);
										repaint();
									}
									break;
								}
								try {
									sleep(500);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				} else if(from.equals(enemyh)) {
					int attack=enemyh.getAtk();
					UpdateJTextArea("【"+enemyh.getName()+"】提升了3点攻击力并对【"+userh.getName()+"】造成了【战斗不能】！\n\n");
					enemyh.setAtk(attack+3);
					enemybuff.remove(enemyh.lfatk);
					enemybuff.add(enemyh.lfatk);
					repaint();
					if(userh.yyzs) {
						uyyzs--;
						UpdateJTextArea("【"+userh.getName()+"】发动了【夜宴之声】的效果抵挡了一次【战斗不能】！\n\n");
						if(uyyzs%3==0) {
							if(userh.getZ().getName().equals("夜宴之声")) {
								setE(userh.getZ().getId(),3);
								getUserEquip(userh.getZ().getId()+20);
								userh.setZ(null);
								zb1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e1.png")));
								zb1.setToolTipText("点击选择一个装备并穿戴。");
							} else if(userh.getX().getName().equals("夜宴之声")) {
								setE(userh.getX().getId(),4);
								getUserEquip(userh.getX().getId()+20);
								userh.setX(null);
								zb2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
								zb2.setToolTipText("点击选择一个装备并穿戴。");
							}
						}
					} else if(userh.fs) {
						UpdateJTextArea("【" + userh.getName() + "】发动了【复苏】的效果抵挡了一次【战斗不能】！\n\n");
					} else {
						userh.setIsfight(false);
						if(userh.xyhE>0) {
							userh.xyhE=0;
							if(userh.xyhED>0) {
								UpdateJTextArea("【"+userh.getName()+"】已经释放了【时光沙漏】！\n\n");
								int d = (int)Math.round(userh.xyhED*0.8 - ((1-userh.getApp())*enemyh.getAdf()));
								if(d<0) d=0;
								balanceskill(userh, d);
								userh.xyhED=0;
							} else {
								UpdateJTextArea("【"+userh.getName()+"】的【时光沙漏】已破碎！\n\n");
							}
							userbuff.remove(userh.sgsl);
							repaint();
						}
						userbuff.remove(userh.lf);
						userbuff.add(userh.lf);
						repaint();
					}
					enemyh.sjjW=true;
					enemytx.setToolTipText(enemyh.getProperty());
					new Thread() {
						@Override
						public void run() {
							int whenstop=r+2;//设置技能失效的回合
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(rend==whenstop) {//回合来到我们设置技能失效的那个回合 跳出循环
									if(enemyh.sjjW) {
										enemyh.sjjW=false;//使苏璟静技能失效
										int attack=enemyh.getAtk();
										enemyh.setAtk(attack-3);
										userh.setIsfight(true);
										enemytx.setToolTipText(enemyh.getProperty());
										userbuff.remove(userh.lf);
										enemybuff.remove(enemyh.lfatk);
										repaint();
									}
									break;
								}
								try {
									sleep(500);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				break;
			}
			case 33:{//苏璟静E 光炽剑
				if(from.equals(userh)) {
					UpdateJTextArea("【"+userh.getName()+"】在下一次普攻命中时会触发【光炽剑】的效果。\n\n");
					userh.sjjE=true;
					userbuff.remove(userh.gcj);
					userbuff.add(userh.gcj);
					repaint();
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("【"+enemyh.getName()+"】在下一次普攻命中时会触发【光炽剑】的效果。\n\n");
					enemyh.sjjE=true;
					enemybuff.remove(enemyh.gcj);
					enemybuff.add(enemyh.gcj);
					repaint();
				}
				break;
			}
			case 34:{//苏璟静R 公主号令
				if(from.equals(userh)) {
					UpdateJTextArea("【"+userh.getName()+"】召唤了3名禁卫军！\n\n");
					userh.sjjR+=3;
					userbuff.remove(userh.gzhl);
					userbuff.add(userh.gzhl);
					userh.gzhl.setSuperpose("（禁卫军数量："+userh.sjjR+"）");
					repaint();
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("【"+enemyh.getName()+"】召唤了3名禁卫军！\n\n");
					enemyh.sjjR+=3;
					enemybuff.remove(enemyh.gzhl);
					enemybuff.add(enemyh.gzhl);
					enemyh.gzhl.setSuperpose("（禁卫军数量："+enemyh.sjjR+"）");
					repaint();
				}
				break;
			}
			case 35:{//维多利娜Q 神谕
				if(from.equals(userh)) {
					p = new Random().nextInt(3);
					sendP(p);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					new Thread() {
						@Override
						public void run() {
							int whenstop=r+1;//设置技能失效的回合
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(r==whenstop) {//回合来到我们设置技能失效的那个回合 跳出循环
									enemyh.ww=p+1;
									enemybuff.remove(enemyh.sy);
									enemybuff.add(enemyh.sy);
									repaint();
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
					switch(p) {
						case 0:{
							UpdateJTextArea("【"+enemyh.getName()+"】接受【神谕】的指令，下回合必须使用【普通攻击】。\n\n");
							break;
						}
						case 1:{
							UpdateJTextArea("【"+enemyh.getName()+"】接受【神谕】的指令，下回合必须使用【任意技能】。\n\n");
							break;
						}
						case 2:{
							UpdateJTextArea("【"+enemyh.getName()+"】接受【神谕】的指令，下回合必须【放弃行动】。\n\n");
							break;
						}
					}
				} else if(from.equals(enemyh)) {
					try {
						p = dis5.readInt();
					} catch (IOException e) {
						e.printStackTrace();
					}
					new Thread() {
						@Override
						public void run() {
							int whenstop=r+1;//设置技能失效的回合
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(r==whenstop) {//回合来到我们设置技能失效的那个回合 跳出循环
									userh.ww=p+1;
									userbuff.remove(userh.sy);
									userbuff.add(userh.sy);
									repaint();
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
					switch(p) {
						case 0:{
							UpdateJTextArea("【"+userh.getName()+"】接受【神谕】的指令，下回合必须使用【普通攻击】。\n\n");
							break;
						}
						case 1:{
							UpdateJTextArea("【"+userh.getName()+"】接受【神谕】的指令，下回合必须使用【任意技能】。\n\n");
							break;
						}
						case 2:{
							UpdateJTextArea("【"+userh.getName()+"】接受【神谕】的指令，下回合必须【放弃行动】。\n\n");
							break;
						}
					}
				}
				break;
			}
			case 36:{//维多利娜W 圣歌
				if(from.equals(userh)) {
					UpdateJTextArea("【"+userh.getName()+"】提升了3点攻击力和2点物理护甲！\n\n");
					userh.setAtk(userh.getAtk()+3);
					userh.setDef(userh.getDef()+2);
					UpdateJTextArea("【"+userh.getName()+"】永久提升了1点每回合魔法回复！\n\n");
					userh.setMpp(userh.getMpp()+1);
					usertx.setToolTipText(userh.getProperty());
					userbuff.remove(userh.sg1);
					userbuff.remove(userh.sg2);
					userbuff.add(userh.sg1);
					userbuff.add(userh.sg2);
					repaint();
					new Thread() {
						@Override
						public void run() {
							int whenstop=r+2;//设置技能失效的回合
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(r==whenstop) {//回合来到我们设置技能失效的那个回合 跳出循环
									if(userh.getId()==Config.w.getId()) {
										userh.setAtk(userh.getAtk()-3);
										userh.setDef(userh.getDef()-2);
										usertx.setToolTipText(userh.getProperty());
										userbuff.remove(userh.sg1);
										userbuff.remove(userh.sg2);
										repaint();
									}
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				} else if(from.equals(enemyh)){
					UpdateJTextArea("【"+enemyh.getName()+"】提升了3点攻击力和2点物理护甲！\n\n");
					enemyh.setAtk(enemyh.getAtk()+3);
					enemyh.setDef(enemyh.getDef()+2);
					UpdateJTextArea("【"+enemyh.getName()+"】永久提升了1点每回合魔法回复！\n\n");
					enemyh.setMpp(enemyh.getMpp()+1);
					enemytx.setToolTipText(enemyh.getProperty());
					enemybuff.remove(enemyh.sg1);
					enemybuff.remove(enemyh.sg2);
					enemybuff.add(enemyh.sg1);
					enemybuff.add(enemyh.sg2);
					repaint();
					new Thread() {
						@Override
						public void run() {
							int whenstop=r+2;//设置技能失效的回合
							while(true) {//如果游戏进行到的回合 不等于 我们设置技能失效的那个回合 就一直循环
								if(r==whenstop) {//回合来到我们设置技能失效的那个回合 跳出循环
									if(enemyh.getId()==Config.w.getId()) {
										enemyh.setAtk(enemyh.getAtk()-3);
										enemyh.setDef(enemyh.getDef()-2);
										enemytx.setToolTipText(enemyh.getProperty());
										enemybuff.remove(enemyh.sg1);
										enemybuff.remove(enemyh.sg2);
										repaint();
									}
									break;
								}
								try {
									sleep(1000);//可以延时1秒进行判定
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				break;
			}
		}
	}
	
	public void getSkillIcon(int x) {
		switch(x) {
			case 1:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/yy/q.png")));
				skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/yy/w.png")));
				skill2.setToolTipText("<html>（W）"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/yy/e.png")));
				skill3.setToolTipText("<html>（E）"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				select.remove(skill4);
				skillgroup.remove(skill4);
				break;
			}
			case 2:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/lxs/q.png")));
				skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/lxs/w.png")));
				skill2.setToolTipText("<html>（W）"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/lxs/e.png")));
				skill3.setToolTipText("<html>（E）"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				skillgroup.remove(skill4);
				select.remove(skill4);
				break;
			}
			case 3:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/ysn/q.png")));
				skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/ysn/w.png")));
				skill2.setToolTipText("<html>（W）"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				select.remove(skill3);
				select.remove(skill4);
				skillgroup.remove(skill3);
				skillgroup.remove(skill4);
				break;
			}
			case 4:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/ltj/q.png")));
				skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/ltj/w.png")));
				skill2.setToolTipText("<html>（W）"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/ltj/e.png")));
				skill3.setToolTipText("<html>（E）"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				select.remove(skill4);
				skillgroup.remove(skill4);
				break;
			}
			case 5:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zf/q.png")));
				skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zf/w.png")));
				skill2.setToolTipText("<html>（W）"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zf/e.png")));
				skill3.setToolTipText("<html>（E）"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				select.remove(skill4);
				skillgroup.remove(skill4);
				break;
			}
			case 6:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/hyq/q.png")));
				skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/hyq/w.png")));
				skill2.setToolTipText("<html>（W）"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/hyq/e.png")));
				skill3.setToolTipText("<html>（E）"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				skill4.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/hyq/r.png")));
				skill4.setToolTipText("<html>（R）"+userh.getR().getSkill()+"</html>");
				skillgroup.add(skill4);
				select.add(skill4);
				break;
			}
			case 7:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/xyh/q.png")));
				skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/xyh/w.png")));
				skill2.setToolTipText("<html>（W）"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/xyh/e.png")));
				skill3.setToolTipText("<html>（E）"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				skillgroup.remove(skill4);
				select.remove(skill4);
				break;
			}
			case 8:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zkx/q.png")));
				skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zkx/w.png")));
				skill2.setToolTipText("<html>（W）"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zkx/e.png")));
				skill3.setToolTipText("<html>（E）"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				skillgroup.remove(skill4);
				select.remove(skill4);
				break;
			}
			case 9:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zxy/q.png")));
				skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zxy/w.png")));
				skill2.setToolTipText("<html>（W）"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zxy/e.png")));
				skill3.setToolTipText("<html>（E）"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				skill4.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zxy/r.png")));
				skill4.setToolTipText("<html>（R）"+userh.getR().getSkill()+"</html>");
				skillgroup.add(skill4);
				select.add(skill4);
				break;
			}
			case 10:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/lm/q.png")));
				skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/lm/w.png")));
				skill2.setToolTipText("<html>（W）"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skillgroup.remove(skill3);
				select.remove(skill3);
				skillgroup.remove(skill4);
				select.remove(skill4);
				break;
			}
			case 11:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/sjj/q.png")));
				skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/sjj/w.png")));
				skill2.setToolTipText("<html>（W）"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/sjj/e.png")));
				skill3.setToolTipText("<html>（E）"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				skill4.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/sjj/r.png")));
				skill4.setToolTipText("<html>（R）"+userh.getR().getSkill()+"</html>");
				skillgroup.add(skill4);
				select.add(skill4);
				break;
			}
			case 12:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/w/q.png")));
				skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/w/w.png")));
				skill2.setToolTipText("<html>（W）"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				select.remove(skill3);
				select.remove(skill4);
				skillgroup.remove(skill3);
				skillgroup.remove(skill4);
				break;
			}
		}
	}
	
	public void getUserHeroIcon(int i) {
		switch(i) {
			case 1:{
				usertx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());
				break;
			}
			case 2:{
				usertx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());
				break;
			}
			case 3:{
				usertx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());
				break;
			}
			case 4:{
				usertx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());
				break;
			}
			case 5:{
				usertx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());
				break;
			}
			case 6:{
				usertx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());
				break;
			}
			case 7:{
				usertx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());
				break;
			}
			case 8:{
				usertx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());
				break;
			}
			case 9:{
				usertx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());
				break;
			}
			case 10:{
				usertx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());
				break;
			}
			case 11:{
				usertx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());
				break;
			}
			case 12:{
				usertx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());
				break;
			}
		}
		usertx.setBorder(new MatteBorder(5, 5, 5, 5,Config.usercolor));
		usertx.setToolTipText(userh.getProperty());
		usertx.setBounds(33, 151, 200, 200);
	}
	
	public void getEnemyHeroIcon(int i) {
		switch(i) {
			case 1:{
				enemytx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());
				break;
			}
			case 2:{
				enemytx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());
				break;
			}
			case 3:{
				enemytx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());
				break;
			}
			case 4:{
				enemytx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());
				break;
			}
			case 5:{
				enemytx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());
				break;
			}
			case 6:{
				enemytx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());
				break;
			}
			case 7:{
				enemytx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());
				break;
			}
			case 8:{
				enemytx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());
				break;
			}
			case 9:{
				enemytx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());
				break;
			}
			case 10:{
				enemytx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());
				break;
			}
			case 11:{
				enemytx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());
				break;
			}
			case 12:{
				enemytx = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());
				break;
			}
		}
		enemytx.setBorder(new MatteBorder(5, 5, 5, 5,Config.enemycolor));
		enemytx.setToolTipText(enemyh.getProperty());
		enemytx.setBounds(1130, 151, 200, 200);
	}
	
	class waitstart extends Thread {

		@Override
		public void run() {
			while((remain--)>0){
				if(remain==18) {
					new chatserver().start();
					new setp().start();
					new Equip().start();
					repaint();
				}
				if(remain==17) {
					UpdateJTextArea("游戏过程中，可随时点击右下方【感叹号】来和对方聊天，或输入相应口令以便使用特定功能。"+"\n\n");
					UpdateJTextArea("输入 -help 口令来获得帮助。"+"\n\n");
				}
				if(remain==16) {
					UpdateJTextArea("你对对方说：你好，我亲爱的朋友！\n\n");
					pw3.write(user.getUsername()+"（"+userh.getName()+"）说：你好，我亲爱的朋友！\n");
					pw3.flush();
					if(userh.getId()==Config.lxs.getId()) {
						userbuff.add(userh.jfzm);
						userh.jfzm.setSuperpose("（当前层数："+userh.jfzm.getV1()+"）攻击力+0 护甲+0");
					}
					if(userh.getId()==Config.zkx.getId()) {
						userbuff.add(userh.xzjz);
						userh.xzjz.setSuperpose("（当前层数："+userh.xzjz.getV1()+"）");
					}
					if(enemyh.getId()==Config.lxs.getId()) {
						enemybuff.add(enemyh.jfzm);
						enemyh.jfzm.setSuperpose("（当前层数："+enemyh.jfzm.getV1()+"）攻击力+0 护甲+0");
					}
					if(enemyh.getId()==Config.zkx.getId()) {
						enemybuff.add(enemyh.xzjz);
						enemyh.xzjz.setSuperpose("（当前层数："+enemyh.xzjz.getV1()+"）");
					}
					repaint();
				}
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				remaintime.setText("还剩"+remain+"秒");
			}
			startfight();
		}
		
	}
	
	public void buttongroup (JButton jb,int operation) {
		for(int i=0;i<select.size();i++) {
			if(select.get(i).isEnabled()) {
				op = operation;
				select.get(i).setEnabled(false);
			} else {
				op = 0;
				mp = 0;
				select.get(i).setEnabled(true);
			}
			jb.setEnabled(true);
		}
	}
	
	public void discountbuff() {
		//奕阳E
		if(userh.yyER>0) {
			userh.yyER--;
			if(userh.yyER>0) UpdateJTextArea("【"+userh.getName()+"】的【屠杀之风】持续时间还剩"+userh.yyER+"个回合。\n\n");
			else {
				userh.yyE=0;
				userbuff.remove(userh.tszf);
				repaint();
				if(userh.jhjj==2) {
					userh.getQ().setdescribe("<html>对对方造成灼烧，每回合造成"+(5+userh.yyE)+"点魔法伤害，持续3回合。该效果影响的回合数可以叠加。<br/><br/>已激活结晶之力。</html>");
					skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
				} else {
					userh.getQ().setdescribe("从0～9中抽取幸运数字。<br />若该数字小于或等于对方当前生命值的个位数，那么对对方造成灼烧，每回合造成"+(5+userh.yyE)+"点魔法伤害，持续3回合。<br />该效果影响的回合数可以叠加。<br/><br />【激活结晶之力】100%生效。");
					skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
				}
				userh.getW().setdescribe("从0～9中抽取幸运数字。<br />若该数字小于或等于对方行动力和你攻击力之和的个位数，那么对对方造成"+(10+userh.yyE)+"点魔法伤害。该技能无视行动力。");
				skill2.setToolTipText("<html>（W）"+userh.getW().getSkill()+"</html>");
			}
		}
		if(enemyh.yyER>0) {
			enemyh.yyER--;
			if(enemyh.yyER>0) UpdateJTextArea("【"+enemyh.getName()+"】的【屠杀之风】持续时间还剩"+enemyh.yyER+"个回合。\n\n");
			else {
				enemyh.yyE=0;
				enemybuff.remove(enemyh.tszf);
				repaint();
			}
		}
		//杨圣诺W
		if (userh.ysnW>0) {
			userh.ysnW--;
			if(userh.ysnW>0) {
				UpdateJTextArea("【"+userh.getName()+"】的【魔抗降低】持续时间还剩"+userh.ysnW+"个回合。\n\n");
				userh.xcyl.setRound(userh.ysnW);
			}
		}
		if (enemyh.ysnW>0) {
			enemyh.ysnW--;
			if(enemyh.ysnW>0) {
				UpdateJTextArea("【"+enemyh.getName()+"】的【魔抗降低】持续时间还剩"+enemyh.ysnW+"个回合。\n\n");
				enemyh.xcyl.setRound(enemyh.ysnW);
			}
		}
		//郈与却Q
		if (userh.hyqQ>0) {
			userh.hyqQ--;
			if(userh.hyqQ>0) {
				UpdateJTextArea("【"+userh.getName()+"】的【先入为主】持续时间还剩"+userh.hyqQ+"个回合。\n\n");
				userh.xrwz.setRound(userh.hyqQ);
			}
		}
		if (enemyh.hyqQ>0) {
			enemyh.hyqQ--;
			if(enemyh.hyqQ>0) {
				UpdateJTextArea("【"+enemyh.getName()+"】的【先入为主】持续时间还剩"+enemyh.hyqQ+"个回合。\n\n");
				enemyh.xrwz.setRound(enemyh.hyqQ);
			}
		}
		//郈与却W
		if (userh.hyqW > 0) {
			userh.hyqW--;
			if (userh.hyqW > 0) {
				UpdateJTextArea("【" + userh.getName() + "】的【护甲降低】持续时间还剩" + userh.hyqW + "个回合。\n\n");
				userh.qlbx.setRound(userh.hyqW);
			}
		}
		if (enemyh.hyqW > 0) {
			enemyh.hyqW--;
			if (enemyh.hyqW > 0) {
				UpdateJTextArea("【" + enemyh.getName() + "】的【护甲降低】持续时间还剩" + enemyh.hyqW + "个回合。\n\n");
				enemyh.qlbx.setRound(enemyh.hyqW);
			}
		}
		//张可汐W
		if(userh.zkxW>0) {
			userh.zkxW--;
			if(userh.zkxW>0) {
				UpdateJTextArea("【"+userh.getName()+"】的【冰之羽翼】持续时间还剩"+userh.zkxW+"个回合。\n\n");
				userh.bzyy.setRound(userh.zkxW);
				repaint();
			}
			else {
				userh.zkxWH=0;
				userbuff.remove(userh.bzyy);
				repaint();
			}
		}
		if(enemyh.zkxW>0) {
			enemyh.zkxW--;
			if(enemyh.zkxW>0) {
				UpdateJTextArea("【"+enemyh.getName()+"】的【冰之羽翼】持续时间还剩"+enemyh.zkxW+"个回合。\n\n");
				enemyh.bzyy.setRound(enemyh.zkxW);
				repaint();
			}
			else {
				enemyh.zkxWH=0;
				enemybuff.remove(enemyh.bzyy);
				repaint();
			}
		}
		//罗天杰W
		if(userh.ltjW>0) {
			userh.ltjW--;
			if(userh.ltjW>0) {
				UpdateJTextArea("【"+userh.getName()+"】的【闪现】持续时间还剩"+userh.ltjW+"个回合。\n\n");
				userh.sx.setRound(userh.ltjW);
			} else {
				userbuff.remove(userh.sx);
				repaint();
			}
		}
		if(enemyh.ltjW>0) {
			enemyh.ltjW--;
			if(enemyh.ltjW>0) {
				UpdateJTextArea("【"+enemyh.getName()+"】的【闪现】持续时间还剩"+enemyh.ltjW+"个回合。\n\n");
				enemyh.sx.setRound(enemyh.ltjW);
			} else {
				enemybuff.remove(enemyh.sx);
				repaint();
			}
		}
		//苏璟静Q
		if(userh.sjjQ>0) {
			userh.sjjQ--;
			if(userh.sjjQ>0) {
				UpdateJTextArea("【"+userh.getName()+"】的【闪现+】持续时间还剩"+userh.sjjQ+"个回合。\n\n");
				userh.sxplus.setRound(userh.sjjQ);
				userh.sxmk.setRound(userh.sjjQ);
			}
		}
		if(enemyh.sjjQ>0) {
			enemyh.sjjQ--;
			if(enemyh.sjjQ>0) {
				UpdateJTextArea("【"+enemyh.getName()+"】的【闪现+】持续时间还剩"+enemyh.sjjQ+"个回合。\n\n");
				enemyh.sxplus.setRound(enemyh.sjjQ);
				enemyh.sxmk.setRound(enemyh.sjjQ);
			}
		}
		//郑心予E
		if(userh.zxyE>0) {
			userh.zxyE--;
			if(userh.zxyE>0) {
				UpdateJTextArea("【"+userh.getName()+"】的【予恋之花】持续时间还剩"+userh.zxyE+"个回合。\n\n");
				userh.userylzh.setRound(userh.zxyE);
				enemyh.enemyylzh.setRound(userh.zxyE);
			}
		}
		if(enemyh.zxyE>0) {
			enemyh.zxyE--;
			if(enemyh.zxyE>0) {
				UpdateJTextArea("【"+enemyh.getName()+"】的【予恋之花】持续时间还剩"+enemyh.zxyE+"个回合。\n\n");
				userh.enemyylzh.setRound(enemyh.zxyE);
				enemyh.userylzh.setRound(enemyh.zxyE);
			}
		}
		//郑心予R
		if(userh.zxyR>0) {
			userh.zxyR--;
			int maxmp = userh.getMp();
			int minmp = userh.getMp()/2;
			p = new Random().nextInt(maxmp-minmp);
			sendP(p);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int hpadd=p+minmp;
			hpp += hpadd;
			UpdateJTextArea("【"+userh.getName()+"】回复了"+hpadd+"点生命值。\n\n");
			if(userh.getHp()+hpadd<=userhpt.getMaximum()) {
				userh.setHp(userh.getHp()+hpadd);
				userhpt.setValue(userh.getHp());
				userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
			} else {
				userh.setHp(userhpt.getMaximum());
				userhpt.setValue(userh.getHp());
				userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
			}
			if(userh.zxyR>0) {
				UpdateJTextArea("【"+userh.getName()+"】的【心源神域】持续时间还剩"+userh.zxyR+"个回合。\n\n");
				userh.xysy.setRound(userh.zxyR);
			} else {
				userbuff.remove(userh.xysy);
				repaint();
			}
		}
		if(enemyh.zxyR>0) {
			enemyh.zxyR--;
			int minmp = enemyh.getMp()/2;
			try {
				p = dis5.readInt();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int hpadd=p+minmp;
			UpdateJTextArea("【"+enemyh.getName()+"】回复了"+hpadd+"点生命值。\n\n");
			if(enemyh.getHp()+hpadd<=enemyhpt.getMaximum()) {
				enemyh.setHp(enemyh.getHp()+hpadd);
				enemyhpt.setValue(enemyh.getHp());
				enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
			} else {
				enemyh.setHp(enemyhpt.getMaximum());
				enemyhpt.setValue(enemyh.getHp());
				enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
			}
			if(enemyh.zxyR>0) {
				UpdateJTextArea("【"+enemyh.getName()+"】的【心源神域】持续时间还剩"+enemyh.zxyR+"个回合。\n\n");
				enemyh.xysy.setRound(enemyh.zxyR);
			} else {
				enemybuff.remove(enemyh.xysy);
				repaint();
			}
		}
		//破军之矛
		if(userh.pjzmcd>0) {
			if(userh.getZ()!=null) {
				if(userh.getZ().getName().equals("破军之矛")) {
					userh.pjzmcd--;
				}
			} else if(userh.getX()!=null) {
				if(userh.getX().getName().equals("破军之矛")) {
					userh.pjzmcd--;
				}
			}
			if(userh.pjzmcd==0) {
				userh.pjzm=true;
			}
		}
		if(enemyh.pjzmcd>0) {
			if(enemyh.getZ()!=null) {
				if(enemyh.getZ().getName().equals("破军之矛")) {
					enemyh.pjzmcd--;
				}
			} else if(enemyh.getX()!=null) {
				if(enemyh.getX().getName().equals("破军之矛")) {
					enemyh.pjzmcd--;
				}
			}
			if(enemyh.pjzmcd==0) {
				enemyh.pjzm=true;
			}
		}
	}
	
	public void ActionOfUser(int operation) {
		if(userh.ww>0) { // 神谕
			if(userh.ww==1) {
				if(operation!=5) {
					UpdateJTextArea("【"+userh.getName()+"】没有遵守神谕，因此永久失去了1点物理护甲！"+"\n\n");
					userh.setDef(userh.getDef()-1);
					usertx.setToolTipText(userh.getProperty());
				}
			} else if(userh.ww==2) {
				if(operation<1&&operation>4) {
					UpdateJTextArea("【"+userh.getName()+"】没有遵守神谕，因此永久失去了1点物理护甲！"+"\n\n");
					userh.setDef(userh.getDef()-1);
					usertx.setToolTipText(userh.getProperty());
				}
			} else {
				if(operation>=1) {
					UpdateJTextArea("【"+userh.getName()+"】没有遵守神谕，因此永久失去了1点物理护甲！"+"\n\n");
					userh.setDef(userh.getDef()-1);
					usertx.setToolTipText(userh.getProperty());
				}
			}
			userh.ww=0;
			userbuff.remove(userh.sy);
			repaint();
		}
		switch(operation) {
			case 1:{
				UpdateJTextArea("【"+userh.getName()+"】使用了技能【"+userh.getQ().getName()+"】！"+"\n\n");
				userh.setMp(userh.getMp()-userh.getQ().getMp());
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				getSkillEffect(userh.getQ(),userh);
				break;
			}
			case 2:{
				UpdateJTextArea("【"+userh.getName()+"】使用了技能【"+userh.getW().getName()+"】！"+"\n\n");
				userh.setMp(userh.getMp()-userh.getW().getMp());
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				getSkillEffect(userh.getW(),userh);
				break;
			}
			case 3:{
				UpdateJTextArea("【"+userh.getName()+"】使用了技能【"+userh.getE().getName()+"】！"+"\n\n");
				userh.setMp(userh.getMp()-userh.getE().getMp());
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				getSkillEffect(userh.getE(),userh);
				break;
			}
			case 4:{
				UpdateJTextArea("【"+userh.getName()+"】使用了技能【"+userh.getR().getName()+"】！"+"\n\n");
				userh.setMp(userh.getMp()-userh.getR().getMp());
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				getSkillEffect(userh.getR(),userh);
				break;
			}
			case 5:{
				UpdateJTextArea("【"+userh.getName()+"】使用了【普通攻击】！"+"\n\n");
				balanceatk(userh);
				break;
			}
			case 13:{//中回复药
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("点击选定一个道具，以便该回合使用。");
				repaint();
				int mp = (int)Math.round(usermpt.getMaximum()*0.3);
				UpdateJTextArea("【"+userh.getName()+"】使用了道具【中回复药】！"+"\n\n");
				if(userh.getHp()+6<=userhpt.getMaximum()) {
					userh.setHp(userh.getHp()+6);
					hpp+=6;
				} else {
					hpp+=userhpt.getMaximum()-userh.getHp();
					userh.setHp(userhpt.getMaximum());
				}
				userhpt.setValue(userh.getHp());
				userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
				if(userh.getMp()+mp<=usermpt.getMaximum()) {
					userh.setMp(userh.getMp()+mp);
					mpp+=mp;
				} else {
					mpp+=usermpt.getMaximum()-userh.getMp();
					userh.setMp(usermpt.getMaximum());
				}
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				UpdateJTextArea("【"+userh.getName()+"】回复了6点生命值和"+mp+"点魔法值！\n\n");
				break;
			}
			case 14:{//大回复药
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("点击选定一个道具，以便该回合使用。");
				repaint();
				int mp = (int)Math.round(usermpt.getMaximum()*0.6);
				UpdateJTextArea("【"+userh.getName()+"】使用了道具【大回复药】！"+"\n\n");
				if(userh.getHp()+11<=userhpt.getMaximum()) {
					userh.setHp(userh.getHp()+11);
					hpp+=11;
				} else {
					hpp+=userhpt.getMaximum()-userh.getHp();
					userh.setHp(userhpt.getMaximum());
				}
				userhpt.setValue(userh.getHp());
				userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
				if(userh.getMp()+mp<=usermpt.getMaximum()) {
					userh.setMp(userh.getMp()+mp);
					mpp+=mp;
				} else {
					mpp+=usermpt.getMaximum()-userh.getMp();
					userh.setMp(usermpt.getMaximum());
				}
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				UpdateJTextArea("【"+userh.getName()+"】回复了11点生命值和"+mp+"点魔法值！\n\n");
				break;
			}
			case 15:{//复苏胶囊
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("点击选定一个道具，以便该回合使用。");
				repaint();
				UpdateJTextArea("【"+userh.getName()+"】使用了道具【复苏胶囊】！"+"\n\n");
				UpdateJTextArea("【"+userh.getName()+"】将在下三个回合获得控制免疫！\n\n");
				userh.setIsgone(true);
				userh.setIsfight(true);
				userh.setIsatk(true);
				userh.setIsskill(true);
				userh.setIslimte(true);
				userh.fs=true;
				userbuff.remove(userh.fsjn);
				userbuff.add(userh.fsjn);
				repaint();
				new Thread() {
					@Override
					public void run() {
						int whenstop=r+3;
						while(true) {
							if(rend==whenstop) {
								userh.fs=false;
								userbuff.remove(userh.fsjn);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
				break;
			}
			case 16:{//高级复苏胶囊
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("点击选定一个道具，以便该回合使用。");
				repaint();
				UpdateJTextArea("【"+userh.getName()+"】使用了道具【高级复苏胶囊】！"+"\n\n");
				if(userh.getHp()+5<=userhpt.getMaximum()) {
					userh.setHp(userh.getHp()+5);
					hpp+=5;
				} else {
					hpp+=userhpt.getMaximum()-userh.getHp();
					userh.setHp(userhpt.getMaximum());
				}
				userhpt.setValue(userh.getHp());
				userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
				UpdateJTextArea("【"+userh.getName()+"】回复了5点生命值并将在下三个回合获得控制免疫！\n\n");
				userh.setIsgone(true);
				userh.setIsfight(true);
				userh.setIsatk(true);
				userh.setIsskill(true);
				userh.setIslimte(true);
				userh.fs=true;
				userbuff.remove(userh.fsjn);
				userbuff.add(userh.fsjn);
				repaint();
				new Thread() {
					@Override
					public void run() {
						int whenstop=r+3;
						while(true) {
							if(rend==whenstop) {
								userh.fs=false;
								userbuff.remove(userh.fsjn);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
				break;
			}
			case 17:{//魔力填充剂1
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("点击选定一个道具，以便该回合使用。");
				repaint();
				UpdateJTextArea("【"+userh.getName()+"】使用了道具【魔力填充剂I】！"+"\n\n");
				if(userh.getMp()+7<=usermpt.getMaximum()) {
					userh.setMp(userh.getMp()+7);
					mpp+=7;
				} else {
					mpp+=usermpt.getMaximum()-userh.getMp();
					userh.setMp(usermpt.getMaximum());
				}
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				UpdateJTextArea("【"+userh.getName()+"】回复了7点魔法值！\n\n");
				break;
			}
			case 18:{//魔力填充剂2
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("点击选定一个道具，以便该回合使用。");
				repaint();
				UpdateJTextArea("【"+userh.getName()+"】使用了道具【魔力填充剂II】！"+"\n\n");
				mpp+=usermpt.getMaximum()-userh.getMp();
				userh.setMp(usermpt.getMaximum());
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				UpdateJTextArea("【"+userh.getName()+"】回复了全部的魔法值！\n\n");
				break;
			}
			case 19:{//魔力填充剂3
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("点击选定一个道具，以便该回合使用。");
				repaint();
				UpdateJTextArea("【"+userh.getName()+"】使用了道具【魔力填充剂II】！"+"\n\n");
				mpp+=usermpt.getMaximum()-userh.getMp();
				userh.setMp(usermpt.getMaximum());
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				UpdateJTextArea("【"+userh.getName()+"】回复了全部的魔法值并获得了2点魔法回复！\n\n");
				new Thread() {
					@Override
					public void run() {
						int whenstart=r+1;
						int whenstop=r+3;
						int id=userh.getId();
						while(true) {
							if(r==whenstart&&userh.getId()==id) {
								userh.setMpp(userh.getMpp()+2);
								userbuff.remove(userh.mpadd);
								userbuff.add(userh.mpadd);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						while(true) {
							if(r==whenstop&&userh.getId()==id) {
								userh.setMpp(userh.getMpp()-2);
								userbuff.remove(userh.mpadd);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
				break;
			}
			case 20:{//行动力胶囊
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("点击选定一个道具，以便该回合使用。");
				repaint();
				UpdateJTextArea("【"+userh.getName()+"】使用了道具【行动力胶囊】！"+"\n\n");
				UpdateJTextArea("【"+userh.getName()+"】将在下两回合内获得4点行动力加成！\n\n");
				new Thread() {
					@Override
					public void run() {
						int whenstart=r+1;
						int whenstop=r+3;
						int id=userh.getId();
						while(true) {
							if(r==whenstart&&userh.getId()==id) {
								userh.setXdl(userh.getXdl()+4);
								userbuff.remove(userh.xdladd);
								userbuff.add(userh.xdladd);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						while(true) {
							if(r==whenstop&&userh.getId()==id) {
								userh.setXdl(userh.getXdl()-4);
								userbuff.remove(userh.xdladd);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
				break;
			}
			case 21:{//双抗药贴
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("点击选定一个道具，以便该回合使用。");
				repaint();
				UpdateJTextArea("【"+userh.getName()+"】使用了道具【双抗药贴】！"+"\n\n");
				UpdateJTextArea("【"+userh.getName()+"】将在下两回合内获得4点双抗加成！\n\n");
				new Thread() {
					@Override
					public void run() {
						int whenstart=r+1;
						int whenstop=r+3;
						int id=userh.getId();
						while(true) {
							if(r==whenstart&&userh.getId()==id) {
								userh.setDef(userh.getDef()+4);
								userh.setAdf(userh.getAdf()+4);
								userbuff.remove(userh.hjadd);
								userbuff.remove(userh.mkadd);
								userbuff.add(userh.hjadd);
								userbuff.add(userh.mkadd);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						while(true) {
							if(r==whenstop&&userh.getId()==id) {
								userh.setDef(userh.getDef()-4);
								userh.setAdf(userh.getAdf()-4);
								userbuff.remove(userh.hjadd);
								userbuff.remove(userh.mkadd);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
				break;
			}
			case 22:{//强化药水
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("点击选定一个道具，以便该回合使用。");
				repaint();
				UpdateJTextArea("【"+userh.getName()+"】使用了道具【强化药水】！"+"\n\n");
				UpdateJTextArea("【"+userh.getName()+"】将在下两回合内获得4攻击力加成！\n\n");
				new Thread() {
					@Override
					public void run() {
						int whenstart=r+1;
						int whenstop=r+3;
						int id=userh.getId();
						while(true) {
							if(r==whenstart&&userh.getId()==id) {
								userh.setAtk(userh.getAtk()+4);
								userbuff.remove(userh.atkadd);
								userbuff.add(userh.atkadd);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						while(true) {
							if(r==whenstop&&userh.getId()==id) {
								userh.setAtk(userh.getAtk()-4);
								userbuff.remove(userh.atkadd);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
				break;
			}
		}
	}
	
	public void ActionOfEnemy(int operation) {
		if(enemyh.ww>0) { // 神谕
			if(enemyh.ww==1) {
				if(operation!=5) {
					UpdateJTextArea("【"+enemyh.getName()+"】没有遵守神谕，因此永久失去了1点物理护甲！"+"\n\n");
					enemyh.setDef(enemyh.getDef()-1);
					enemytx.setToolTipText(enemyh.getProperty());
				}
			} else if(enemyh.ww==2) {
				if(operation<1&&operation>4) {
					UpdateJTextArea("【"+enemyh.getName()+"】没有遵守神谕，因此永久失去了1点物理护甲！"+"\n\n");
					enemyh.setDef(enemyh.getDef()-1);
					enemytx.setToolTipText(enemyh.getProperty());
				}
			} else {
				if(operation>=1) {
					UpdateJTextArea("【"+enemyh.getName()+"】没有遵守神谕，因此永久失去了1点物理护甲！"+"\n\n");
					enemyh.setDef(enemyh.getDef()-1);
					enemytx.setToolTipText(enemyh.getProperty());
				}
			}
			enemyh.ww=0;
			enemybuff.remove(enemyh.sy);
			repaint();
		}
		switch(operation) {
			case -1:{
				UpdateJTextArea("【"+enemyh.getName()+"】放弃了该回合的行动。\n\n");
				break;
			}
			case 1:{
				UpdateJTextArea("【"+enemyh.getName()+"】使用了技能【"+enemyh.getQ().getName()+"】！"+"\n\n");
				enemyh.setMp(enemyh.getMp()-enemyh.getQ().getMp());
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				getSkillEffect(enemyh.getQ(),enemyh);
				break;
			}
			case 2:{
				UpdateJTextArea("【"+enemyh.getName()+"】使用了技能【"+enemyh.getW().getName()+"】！"+"\n\n");
				enemyh.setMp(enemyh.getMp()-enemyh.getW().getMp());
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				getSkillEffect(enemyh.getW(),enemyh);
				break;
			}
			case 3:{
				UpdateJTextArea("【"+enemyh.getName()+"】使用了技能【"+enemyh.getE().getName()+"】！"+"\n\n");
				enemyh.setMp(enemyh.getMp()-enemyh.getE().getMp());
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				getSkillEffect(enemyh.getE(),enemyh);
				break;
			}
			case 4:{
				UpdateJTextArea("【"+enemyh.getName()+"】使用了技能【"+enemyh.getR().getName()+"】！"+"\n\n");
				enemyh.setMp(enemyh.getMp()-enemyh.getR().getMp());
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				getSkillEffect(enemyh.getR(),enemyh);
				break;
			}
			case 5:{
				UpdateJTextArea("【"+enemyh.getName()+"】使用了【普通攻击】！"+"\n\n");
				balanceatk(enemyh);
				break;
			}
			case 13:{//中回复药
				int mp = (int)Math.round(enemympt.getMaximum()*0.3);
				UpdateJTextArea("【"+enemyh.getName()+"】使用了道具【中回复药】！"+"\n\n");
				if(enemyh.getHp()+6<=enemyhpt.getMaximum()) {
					enemyh.setHp(enemyh.getHp()+6);
				} else {
					enemyh.setHp(enemyhpt.getMaximum());
				}
				enemyhpt.setValue(enemyh.getHp());
				enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
				if(enemyh.getMp()+mp<=enemympt.getMaximum()) {
					enemyh.setMp(enemyh.getMp()+mp);
				} else {
					enemyh.setMp(enemympt.getMaximum());
				}
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				UpdateJTextArea("【"+enemyh.getName()+"】回复了6点生命值和"+mp+"点魔法值！\n\n");
				break;
			}
			case 14:{//大回复药
				int mp = (int)Math.round(enemympt.getMaximum()*0.6);
				UpdateJTextArea("【"+enemyh.getName()+"】使用了道具【大回复药】！"+"\n\n");
				if(enemyh.getHp()+11<=enemyhpt.getMaximum()) {
					enemyh.setHp(enemyh.getHp()+11);
				} else {
					enemyh.setHp(enemyhpt.getMaximum());
				}
				enemyhpt.setValue(enemyh.getHp());
				enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
				if(enemyh.getMp()+mp<=enemympt.getMaximum()) {
					enemyh.setMp(enemyh.getMp()+mp);
				} else {
					enemyh.setMp(enemympt.getMaximum());
				}
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				UpdateJTextArea("【"+enemyh.getName()+"】回复了11点生命值和"+mp+"点魔法值！\n\n");
				break;
			}
			case 15:{//复苏胶囊
				UpdateJTextArea("【"+enemyh.getName()+"】使用了道具【复苏胶囊】！"+"\n\n");
				UpdateJTextArea("【"+enemyh.getName()+"】将在下三个回合获得控制免疫！\n\n");
				enemyh.setIsgone(true);
				enemyh.setIsfight(true);
				enemyh.setIsatk(true);
				enemyh.setIsskill(true);
				enemyh.setIslimte(true);
				enemyh.fs=true;
				enemybuff.remove(enemyh.fsjn);
				enemybuff.add(enemyh.fsjn);
				repaint();
				new Thread() {
					@Override
					public void run() {
						int whenstop=r+3;
						while(true) {
							if(rend==whenstop) {
								enemyh.fs=false;
								enemybuff.remove(enemyh.fsjn);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
				break;
			}
			case 16:{//高级复苏胶囊
				UpdateJTextArea("【"+enemyh.getName()+"】使用了道具【高级复苏胶囊】！"+"\n\n");
				if(enemyh.getHp()+5<=enemyhpt.getMaximum()) {
					enemyh.setHp(enemyh.getHp()+5);
					hpp+=5;
				} else {
					hpp+=enemyhpt.getMaximum()-enemyh.getHp();
					enemyh.setHp(enemyhpt.getMaximum());
				}
				enemyhpt.setValue(enemyh.getHp());
				enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
				UpdateJTextArea("【"+enemyh.getName()+"】回复了5点生命值并将在下三个回合获得控制免疫！\n\n");
				enemyh.setIsgone(true);
				enemyh.setIsfight(true);
				enemyh.setIsatk(true);
				enemyh.setIsskill(true);
				enemyh.setIslimte(true);
				enemyh.fs=true;
				enemybuff.remove(enemyh.fsjn);
				enemybuff.add(enemyh.fsjn);
				repaint();
				new Thread() {
					@Override
					public void run() {
						int whenstop=r+3;
						while(true) {
							if(rend==whenstop) {
								enemyh.fs=false;
								enemybuff.remove(enemyh.fsjn);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
				break;
			}
			case 17:{//魔力填充剂1
				UpdateJTextArea("【"+enemyh.getName()+"】使用了道具【魔力填充剂I】！"+"\n\n");
				if(enemyh.getMp()+7<=enemympt.getMaximum()) {
					enemyh.setMp(enemyh.getMp()+7);
				} else {
					enemyh.setMp(enemympt.getMaximum());
				}
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				UpdateJTextArea("【"+enemyh.getName()+"】回复了7点魔法值！\n\n");
				break;
			}
			case 18:{//魔力填充剂2
				UpdateJTextArea("【"+enemyh.getName()+"】使用了道具【魔力填充剂II】！"+"\n\n");
				enemyh.setMp(enemympt.getMaximum());
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				UpdateJTextArea("【"+enemyh.getName()+"】回复了全部的魔法值！\n\n");
				break;
			}
			case 19:{//魔力填充剂3
				UpdateJTextArea("【"+enemyh.getName()+"】使用了道具【魔力填充剂II】！"+"\n\n");
				enemyh.setMp(enemympt.getMaximum());
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				UpdateJTextArea("【"+enemyh.getName()+"】回复了全部的魔法值并获得了2点魔法回复！\n\n");
				new Thread() {
					@Override
					public void run() {
						int whenstart=r+1;
						int whenstop=r+3;
						int id=enemyh.getId();
						while(true) {
							if(r==whenstart&&enemyh.getId()==id) {
								enemyh.setMpp(enemyh.getMpp()+2);
								enemybuff.remove(enemyh.mpadd);
								enemybuff.add(enemyh.mpadd);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						while(true) {
							if(r==whenstop&&enemyh.getId()==id) {
								enemyh.setMpp(enemyh.getMpp()-2);
								enemybuff.remove(enemyh.mpadd);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
				break;
			}
			case 20:{//行动力胶囊
				UpdateJTextArea("【"+enemyh.getName()+"】使用了道具【行动力胶囊】！"+"\n\n");
				UpdateJTextArea("【"+enemyh.getName()+"】将在下两回合内获得4点行动力加成！\n\n");
				new Thread() {
					@Override
					public void run() {
						int whenstart=r+1;
						int whenstop=r+3;
						int id=enemyh.getId();
						while(true) {
							if(r==whenstart&&enemyh.getId()==id) {
								enemyh.setXdl(enemyh.getXdl()+4);
								enemybuff.remove(enemyh.xdladd);
								enemybuff.add(enemyh.xdladd);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						while(true) {
							if(r==whenstop&&enemyh.getId()==id) {
								enemyh.setXdl(enemyh.getXdl()-4);
								enemybuff.remove(enemyh.xdladd);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
				break;
			}
			case 21:{//双抗药贴
				UpdateJTextArea("【"+enemyh.getName()+"】使用了道具【双抗药贴】！"+"\n\n");
				UpdateJTextArea("【"+enemyh.getName()+"】将在下两回合内获得4点双抗加成！\n\n");
				new Thread() {
					@Override
					public void run() {
						int whenstart=r+1;
						int whenstop=r+3;
						int id=enemyh.getId();
						while(true) {
							if(r==whenstart&&enemyh.getId()==id) {
								enemyh.setDef(enemyh.getDef()+4);
								enemyh.setAdf(enemyh.getAdf()+4);
								enemybuff.remove(enemyh.hjadd);
								enemybuff.remove(enemyh.mkadd);
								enemybuff.add(enemyh.hjadd);
								enemybuff.add(enemyh.mkadd);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						while(true) {
							if(r==whenstop&&enemyh.getId()==id) {
								enemyh.setDef(enemyh.getDef()-4);
								enemyh.setAdf(enemyh.getAdf()-4);
								enemybuff.remove(enemyh.hjadd);
								enemybuff.remove(enemyh.mkadd);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
				break;
			}
			case 22:{//强化药水
				UpdateJTextArea("【"+enemyh.getName()+"】使用了道具【强化药水】！"+"\n\n");
				UpdateJTextArea("【"+enemyh.getName()+"】将在下两回合内获得4攻击力加成！\n\n");
				new Thread() {
					@Override
					public void run() {
						int whenstart=r+1;
						int whenstop=r+3;
						int id=enemyh.getId();
						while(true) {
							if(r==whenstart&&enemyh.getId()==id) {
								enemyh.setAtk(enemyh.getAtk()+4);
								enemybuff.remove(enemyh.atkadd);
								enemybuff.add(enemyh.atkadd);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						while(true) {
							if(r==whenstop&&enemyh.getId()==id) {
								enemyh.setAtk(enemyh.getAtk()-4);
								enemybuff.remove(enemyh.atkadd);
								repaint();
								break;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
				break;
			}
		}
	}
	

	public void UpdateJTextArea(String str) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				if(str != null) {
					textArea.append(str);
					textArea.setCaretPosition(textArea.getText().length());
				}
			}
		});
	
	}
	
	class Balance extends Thread {

		@Override
		public void run() {
			whoact.setText("正在进行行动结算");
			UpdateJTextArea("兵戎相见阶段：\n\n");
			/**
			 * @function 行动力算法
			 */
			if (userh.getXdl() > enemyh.getXdl()) {
				new Voice(getClass().getResourceAsStream("/bgm/atk.mp3")).start();
				if(userh.getHp()>0) ActionOfUser(userop);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(enemyh.getHp()>0) ActionOfEnemy(enemyop);
			} else if (userh.getXdl() < enemyh.getXdl()) {
				new Voice(getClass().getResourceAsStream("/bgm/magic.mp3")).start();
				if(enemyh.getHp()>0) ActionOfEnemy(enemyop);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(userh.getHp()>0) ActionOfUser(userop);
			} else if (userh.getXdl() == enemyh.getXdl()) {
				if (roomcreater) {
					new Voice(getClass().getResourceAsStream("/bgm/atk.mp3")).start();
					if(userh.getHp()>0) ActionOfUser(userop);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(enemyh.getHp()>0) ActionOfEnemy(enemyop);
				} else {
					new Voice(getClass().getResourceAsStream("/bgm/magic.mp3")).start();
					if(enemyh.getHp()>0) ActionOfEnemy(enemyop);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(userh.getHp()>0) ActionOfUser(userop);
				}
			}
			userop = 0;
			enemyop = 0;
			//奕阳Q
			if(userh.yyQ>0) {
				userh.yyQ--;
				if(enemyh.getHp()>0) {
					int d = (5+enemyh.yyE) - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
					if(d<0) d=0;
					if(userh.getId()==Config.xyh.getId()) {
						if(userh.xyh+d<=8) userh.xyh+=d;
						else userh.xyh=8;
						userh.getQ().setMp(userh.xyh);
						skill1.setToolTipText("<html>（Q）"+userh.getQ().getSkill()+"</html>");
					}
					if(userh.sjjR>0&&d>0) {
						if(d-4>=0) {
							UpdateJTextArea("【"+userh.getName()+"】牺牲了一名禁卫军，抵挡了4点伤害！"+"\n\n");
							d -= 4;
						}
						else {
							UpdateJTextArea("【"+userh.getName()+"】牺牲了一名禁卫军，抵挡了"+d+"点伤害！"+"\n\n");
							d = 0;
						}
						userh.sjjR--;
						if(userh.sjjR!=0) {
							userh.gzhl.setSuperpose("（禁卫军数量："+userh.sjjR+"）");
						} else {
							userbuff.remove(userh.gzhl);
							repaint();
						}
					}
					if(userh.xyhE>0) {
						userh.xyhED+=d;
						userh.sgsl.setDescribe("该英雄完全免疫任何伤害。目前已积累"+userh.xyhED+"点伤害。");
						d=0;
					}
					UpdateJTextArea("【"+userh.getName()+"】受到了"+d+"点来自灼烧的魔法伤害！\n\n");
					if(userh.getHp()-d>0) {
						hurt += d;
						userh.setHp(userh.getHp()-d);
						userhpt.setValue(userh.getHp());
						userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
					} else {
						hurt += (userh.getHp());
						userh.setHp(0);
						userhpt.setValue(0);
						userhpt.setString("0"+" / "+userhpt.getMaximum());
					}
				}
				if(userh.yyQ>0) {
					UpdateJTextArea("【"+userh.getName()+"】的【灼烧】持续时间还剩"+userh.yyQ+"个回合。\n\n");
					userh.lrzj.setRound(userh.yyQ);
				}
				else {
					userbuff.remove(userh.lrzj);
					repaint();
				}
			}
			if(enemyh.yyQ>0) {
				enemyh.yyQ--;
				if(userh.getHp()>0) {
					int d = (5+userh.yyE) - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
					if(d<0) d=0;
					if(enemyh.getId()==Config.xyh.getId()) {
						if(enemyh.xyh+d<=8) enemyh.xyh+=d;
						else enemyh.xyh=8;
						enemyh.getQ().setMp(enemyh.xyh);
					}
					if(enemyh.sjjR>0&&d>0) {
						if(d-4>=0) {
							UpdateJTextArea("【"+enemyh.getName()+"】牺牲了一名禁卫军，抵挡了4点伤害！"+"\n\n");
							d -= 4;
						}
						else {
							UpdateJTextArea("【"+enemyh.getName()+"】牺牲了一名禁卫军，抵挡了"+d+"点伤害！"+"\n\n");
							d = 0;
						}
						enemyh.sjjR--;
						if(enemyh.sjjR!=0) {
							enemyh.gzhl.setSuperpose("（禁卫军数量："+enemyh.sjjR+"）");
						} else {
							enemybuff.remove(enemyh.gzhl);
							repaint();
						}
					}
					if(enemyh.xyhE>0) {
						enemyh.xyhED+=d;
						d=0;
						enemyh.sgsl.setDescribe("该英雄完全免疫任何伤害。目前已积累"+enemyh.xyhED+"点伤害。");
					}
					UpdateJTextArea("【"+enemyh.getName()+"】受到了"+d+"点来自灼烧的魔法伤害！\n\n");
					if(enemyh.getHp()-d>0) {
						damage = damage + d;
						herodamage += d;
						enemyh.setHp(enemyh.getHp()-d);
						enemyhpt.setValue(enemyh.getHp());
						enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
					} else {
						damage = damage + (enemyh.getHp());
						herodamage += enemyh.getHp();
						enemyh.setHp(0);
						enemyhpt.setValue(0);
						enemyhpt.setString("0"+" / "+enemyhpt.getMaximum());
					}
				}
				if(enemyh.yyQ>0) {
					UpdateJTextArea("【"+enemyh.getName()+"】的【灼烧】持续时间还剩"+enemyh.yyQ+"个回合。\n\n");
					enemyh.lrzj.setRound(enemyh.yyQ);
				}
				else {
					enemybuff.remove(enemyh.lrzj);
					repaint();
				}
			}
			//谢悠涵E
			if(userh.xyhE>0) {
				userh.xyhE--;
				if(userh.xyhE>0) {
					UpdateJTextArea("【"+userh.getName()+"】的【时光沙漏】持续时间还剩"+userh.xyhE+"个回合。\n\n");
					userh.sgsl.setDescribe("该英雄完全免疫任何伤害。目前已积累"+userh.xyhED+"点伤害。");
					userh.sgsl.setRound(userh.xyhE);
					repaint();
				} else {
					if(userh.xyhED>0) {
						UpdateJTextArea("【"+userh.getName()+"】已经释放了【时光沙漏】！\n\n");
						int d = (int)Math.round(userh.xyhED*1.6 - ((1-userh.getApp())*enemyh.getAdf()));
						if(d<0) d=0;
						balanceskill(userh, d);
						userh.xyhED=0;
					} else {
						UpdateJTextArea("【"+userh.getName()+"】的【时光沙漏】已破碎！\n\n");
					}
					userbuff.remove(userh.sgsl);
					repaint();
				}
			}
			if(enemyh.xyhE>0) {
				enemyh.xyhE--;
				if(enemyh.xyhE>0) {
					UpdateJTextArea("【"+enemyh.getName()+"】的【时光沙漏】持续时间还剩"+enemyh.xyhE+"个回合。\n\n");
					enemyh.sgsl.setDescribe("该英雄完全免疫任何伤害。目前已积累"+enemyh.xyhED+"点伤害。");
					enemyh.sgsl.setRound(enemyh.xyhE);
					repaint();
				} else {
					if(enemyh.xyhED>0) {
						UpdateJTextArea("【"+enemyh.getName()+"】释放了他的【时光沙漏】！\n\n");
						int d = (int)Math.round(enemyh.xyhED*1.6 - ((1-enemyh.getApp())*userh.getAdf()));
						if(d<0) d=0;
						balanceskill(enemyh, d);
						enemyh.xyhED=0;
					} else {
						UpdateJTextArea("【"+enemyh.getName()+"】的【时光沙漏】已破碎！\n\n");
					}
					enemybuff.remove(enemyh.sgsl);
					repaint();
				}
			}
			//张枫W行动受限生效
			if(userh.zfW) {
				new Thread() {
					@Override
					public void run() {
						int whenstart = r + 2;
						int whenstop = r + 3;// 设置技能失效的回合
						while(true) {
							if(rend == whenstart) {
								if (enemyh.yyzs) {
									eyyzs--;
									UpdateJTextArea("【" + enemyh.getName() + "】发动了【夜宴之声】的效果抵挡了一次【行动受限】！\n\n");
								} else if (enemyh.fs) {
									UpdateJTextArea("【" + enemyh.getName() + "】发动了【复苏】的效果抵挡了一次【行动受限】！\n\n");
								} else {
									enemyh.setIslimte(false);
									if (enemyh.xyhE > 0) {
										enemyh.xyhE = 0;
										if (enemyh.xyhED > 0) {
											UpdateJTextArea("【" + enemyh.getName() + "】释放了他的【时光沙漏】！\n\n");
											int d = (int) Math
													.round(enemyh.xyhED * 0.8 - ((1 - enemyh.getApp()) * userh.getAdf()));
											if (d < 0)
												d = 0;
											balanceskill(enemyh, d);
											enemyh.xyhED = 0;
										} else {
											UpdateJTextArea("【" + enemyh.getName() + "】的【时光沙漏】已破碎！\n\n");
										}
										enemybuff.remove(enemyh.sgsl);
										repaint();
									}
									enemybuff.remove(enemyh.fzjj2);
									enemybuff.add(enemyh.fzjj2);
									enemyh.fzjj2.setRound(1);
									repaint();
								}
								userh.zfW = false;
								break;
							}
							try {
								sleep(700);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						while (true) {
							if (rend == whenstop) {// 回合来到我们设置技能失效的那个回合 跳出循环
								enemyh.setIslimte(true);
								enemybuff.remove(enemyh.fzjj2);
								repaint();
								break;
							}
							try {
								sleep(700);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
			}
			if(enemyh.zfW) {
				new Thread() {
					@Override
					public void run() {
						int whenstart = r + 2;
						int whenstop = r + 3;// 设置技能失效的回合
						while(true) {
							if(rend == whenstart) {
								if (userh.yyzs) {
									uyyzs--;
									UpdateJTextArea("【" + userh.getName() + "】发动了【夜宴之声】的效果抵挡了一次【行动受限】！\n\n");
									if (uyyzs == 0) {
										if (userh.getZ().getName().equals("夜宴之声")) {
											setE(userh.getZ().getId(), 3);
											getUserEquip(userh.getZ().getId() + 20);
											userh.setZ(null);
											zb1.setIcon(
													new ImageIcon(this.getClass().getClassLoader().getResource("img/e1.png")));
											zb1.setToolTipText("点击选择一个装备并穿戴。");
										} else if (userh.getX().getName().equals("夜宴之声")) {
											setE(userh.getX().getId(), 4);
											getUserEquip(userh.getX().getId() + 20);
											userh.setX(null);
											zb2.setIcon(
													new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
											zb2.setToolTipText("点击选择一个装备并穿戴。");
										}
									}
								} else if (userh.fs) {
									UpdateJTextArea("【" + userh.getName() + "】发动了【复苏】的效果抵挡了一次【行动受限】！\n\n");
								} else {
									userh.setIslimte(false);
									if (userh.xyhE > 0) {
										userh.xyhE = 0;
										if (userh.xyhED > 0) {
											UpdateJTextArea("【" + userh.getName() + "】已经释放了【时光沙漏】！\n\n");
											int d = (int) Math
													.round(userh.xyhED * 0.8 - ((1 - userh.getApp()) * enemyh.getAdf()));
											if (d < 0)
												d = 0;
											balanceskill(userh, d);
											userh.xyhED = 0;
										} else {
											UpdateJTextArea("【" + userh.getName() + "】的【时光沙漏】已破碎！\n\n");
										}
										userbuff.remove(userh.sgsl);
										repaint();
									}
									userbuff.remove(userh.fzjj2);
									userbuff.add(userh.fzjj2);
									userh.fzjj2.setRound(1);
									repaint();
								}
								enemyh.zfW = false;
								break;
							}
							try {
								sleep(700);// 可以延时1秒进行判定
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						while (true) {
							if (rend == whenstop) {// 回合来到我们设置技能失效的那个回合 跳出循环
								userh.setIslimte(true);
								userbuff.remove(userh.fzjj2);
								repaint();
								break;
							}
							try {
								sleep(700);// 可以延时1秒进行判定
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
			}
			/*
			 * Skill Up 激活结晶之力（技能增强）
			 */
			if(!userh.jjzl) {
				if(herotime>=7||userh.damage>=20) {
					switch(userh.getId()) {
						case 1:{
							gsu = new GetSkillUp(Fight.this, userh);
							gsu.Inititem();
							UpdateJTextArea("【"+userh.getName()+"】任务已完成！完成时间："+herotime+"个回合。\n\n");
							pw3.write("【"+userh.getName()+"】任务已完成！完成时间："+herotime+"个回合。\n");
							pw3.flush();
							gsu.setVisible(true);
							break;
						}
						case 6:{
							gsu = new GetSkillUp(Fight.this, userh);
							gsu.Inititem();
							UpdateJTextArea("【"+userh.getName()+"】任务已完成！完成时间："+herotime+"个回合。\n\n");
							pw3.write("【"+userh.getName()+"】任务已完成！完成时间："+herotime+"个回合。\n");
							pw3.flush();
							gsu.setVisible(true);
							break;
						}
						case 9:{
							gsu = new GetSkillUp(Fight.this, userh);
							gsu.Inititem();
							UpdateJTextArea("【"+userh.getName()+"】任务已完成！完成时间："+herotime+"个回合。\n\n");
							pw3.write("【"+userh.getName()+"】任务已完成！完成时间："+herotime+"个回合。\n");
							pw3.flush();
							gsu.setVisible(true);
							break;
						}
						case 11:{
							gsu = new GetSkillUp(Fight.this, userh);
							gsu.Inititem();
							UpdateJTextArea("【"+userh.getName()+"】任务已完成！完成时间："+herotime+"个回合。\n\n");
							pw3.write("【"+userh.getName()+"】任务已完成！完成时间："+herotime+"个回合。\n");
							pw3.flush();
							gsu.setVisible(true);
							break;
						}
					}
				}
			}
			rend++; // 回合标志
		}
		
	}
	
	/**
	 * 游戏暂停与解除
	 */
	
	public void setpause(int yn) { // 1=暂停 0=解除
		if(yn==1) {
			if(selected==1) {
				if(pausetimes>0) {
					if(ispause) {
						UpdateJTextArea("游戏已处于暂停状态！如需解除，请使用 -r 口令！\n\n");
					} else {
						ispause = true;
						isuserpause = true;
						pausetimes--;
						new pausetimer(60).start();
						setE(7);
						UpdateJTextArea("游戏已被你暂停1分钟。输入 -r 口令解除暂停。》》剩余"+pausetimes+"次暂停机会《《\n\n");
						pw3.write("游戏已被"+user.getUsername()+"（"+userh.getName()+"）暂停1分钟。》》剩余"+pausetimes+"次暂停机会《《\n");
						pw3.flush();
					}
				} else {
					UpdateJTextArea("本场比赛你已经没有暂停机会了（每人每场最多3次机会）。\n\n");
				}
			} else {
				UpdateJTextArea("不在行动时间内，无法暂停。");
			}
		} else if(yn==0) {
			if(!ispause) {
				UpdateJTextArea("游戏不在暂停状态。\n\n");
			} else {
				if(isuserpause) {
					ispause = false;
					isuserpause = false;
					setE(8);
					UpdateJTextArea("游戏已被你解除暂停。\n\n");
					pw3.write("游戏已被"+user.getUsername()+"（"+userh.getName()+"）解除暂停。\n");
					pw3.flush();
				} else {
					UpdateJTextArea("你无法解除对方的暂停。请耐心等候。\n\n");
				}
			}
		}
		
	}
	
	void sur() { // 投降
		if(r>=13) {
			winlose = false;
			setE(9);
			UpdateJTextArea("你投降了，对方获得了本次比赛的胜利！游戏将在回合结束时结束。\n\n");
		} else {
			UpdateJTextArea("第13回合前不允许投降，请努力进行游戏！\n\n");
		}
	}
	
	void getHelp() {
		UpdateJTextArea("当前可用命令如下：\n\n");
		UpdateJTextArea("-p 暂停游戏\n-r 解除暂停\n-jh 激活结晶之力\n-mymax 查询英雄上限\n-enemymax 查询对方英雄上限\n-lxs 刘晓释魔王怒秒杀概率\n-xyh 谢悠涵洁净点数量"+"\n\n");
	}
	
	class openStore extends Thread {
		
		Fight fight;
		int r,remain,gold;
		ArrayList<Item> djh;
		User user;
		Hero hero;
		
		openStore(Fight fight,int r,int remain,int gold,ArrayList<Item> djh,User user,Hero hero){
			this.fight = fight;
			this.r=r;
			this.remain = remain;
			this.gold = gold;
			this.djh = djh;
			this.user = user;
			this.hero = hero;
		}
		
		@Override
		public void run() {
			super.run();
			Store s = new Store(fight,r,remain,gold,djh,user,hero);
			s.setVisible(true);
			s.InitDJH();
		}
		
	}
	
	public void sendAll(String str) {
		if(str!=null) {
			if(!str.equals("")) {
				pw3.write(str+"\n");
				pw3.flush();
			}
		}
	}
	
	void getElo() {
		DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
		BigDecimal bg = new BigDecimal(df.format((double)damage / hurt));
        rating = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        if(rating>3) rating=2.80 + (new Random().nextInt(10)*2/100);
		if(winlose) { // 胜利
			if(user.getDjs()>0&&user.getAll()<=8&&user.getElo()<1800) {
				elo = (int)Math.round(rating*128);
			} else {
				if(user.getElo()>=enemy.getElo()) { // 分比对方高
					if(user.getElo()-enemy.getElo()<=800) { // 分差小于800
						elo = (int)Math.round(rating*20);
					} else { // 分差大于800
						elo = (int)Math.round(rating*10);
					}
				} else { // 分比对方低
					if(enemy.getElo()-user.getElo()<=800) { // 分差小于800
						elo = (int)Math.round(rating*30);
					} else { // 分差大于800
						elo = 10+(int)Math.round(rating*40);
					}
				}
			}
		} else { // 失败
			if(user.getDjs()>0&&user.getAll()<=8&&user.getElo()<1800) {
				elo = (int)Math.round(rating*28);
			} else {
				if(user.getElo()>=enemy.getElo()) { // 分比对方高
					if(user.getElo()-enemy.getElo()<=800) { // 分差小于800
						elo = -50+(int)Math.round(rating*25);
					} else { // 分差大于800
						elo = -50+(int)Math.round(rating*10);
					}
				} else { // 分比对方低
					if(enemy.getElo()-user.getElo()<=800) { // 分差小于800
						elo = -30+(int)Math.round(rating*20);
					} else { // 分差大于800
						elo = -10+(int)Math.round(rating*10);
					}
				}
			}
		}
	}
	
	String getRank() {
		if(user.getDjs()>0) {
			return "未定级";
		} else {
			if(user.getElo()>=2600) {
				return "S";
			} else if(user.getElo()>=2200&&user.getElo()<2600) {
				return "A+";
			} else if(user.getElo()>=1800&&user.getElo()<2200) {
				return "A";
			} else if(user.getElo()>=1400&&user.getElo()<1800) {
				return "B";
			} else if(user.getElo()>=1000&&user.getElo()<1400) {
				return "C";
			} else if(user.getElo()>=600&&user.getElo()<1000) {
				return "D";
			} else {
				return "E";
			}
		}
	}
	
	double getenemyadr() {
		DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
		BigDecimal bg = new BigDecimal(df.format((double)hurt / r));
        double enemyadr = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return enemyadr;
	}
	
	public void getEnemyEquip(int id) {
		if(id>=33&&id<=46) {//穿装备1
			enemyh.setZ(Config.Allitems.get(id-21));
		}
		if(id>=53&&id<=66) {//穿装备2
			enemyh.setX(Config.Allitems.get(id-41));
		}
		if(id>=73&&id<=86) {//取消装备1
			enemyh.setZ(null);
		}
		if(id>=93&&id<=106) {//取消装备2
			enemyh.setZ(null);
		}
		if(id>=113&&id<=126) {//吃装备
			
		}
		switch(id) {
			case 0:{//回合延时
				limitr+=5;
				UpdateJTextArea("感谢"+enemy.getUsername()+"（"+enemyh.getName()+"）的殷勤奉献，总回合数增加了5！\n\n");
				break;
			}
			case 1:{//回复药
				if(enemyh.getHp()+2<=enemyhpt.getMaximum()) {
					enemyh.setHp(enemyh.getHp()+2);
				} else {
					enemyh.setHp(enemyhpt.getMaximum());
				}
				enemyhpt.setValue(enemyh.getHp());
				enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
				UpdateJTextArea("【"+enemyh.getName()+"】回复了2点生命值！\n\n");
				break;
			}
			case 4:{//复苏胶囊
				UpdateJTextArea("【"+enemyh.getName()+"】使用了道具【高级复苏胶囊】！"+"\n\n");
				UpdateJTextArea("【"+enemyh.getName()+"】将在下三个回合获得控制免疫！\n\n");
				enemyh.setIsgone(true);
				enemyh.setIsfight(true);
				enemyh.setIsatk(true);
				enemyh.setIsskill(true);
				enemyh.setIslimte(true);
				enemyh.fs=true;
				new Thread() {
					@Override
					public void run() {
						int whenstop=r+3;
						while(true) {
							if(rend==whenstop) {
								enemyh.fs=false;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
				break;
			}
			case 5:{//高级复苏胶囊
				UpdateJTextArea("【"+enemyh.getName()+"】使用了道具【高级复苏胶囊】！"+"\n\n");
				if(enemyh.getHp()+5<=enemyhpt.getMaximum()) {
					enemyh.setHp(enemyh.getHp()+5);
					hpp+=5;
				} else {
					hpp+=enemyhpt.getMaximum()-enemyh.getHp();
					enemyh.setHp(enemyhpt.getMaximum());
				}
				enemyhpt.setValue(enemyh.getHp());
				enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
				UpdateJTextArea("【"+enemyh.getName()+"】回复了5点生命值并将在下三个回合获得控制免疫！\n\n");
				enemyh.setIsgone(true);
				enemyh.setIsfight(true);
				enemyh.setIsatk(true);
				enemyh.setIsskill(true);
				enemyh.setIslimte(true);
				enemyh.fs=true;
				new Thread() {
					@Override
					public void run() {
						int whenstop=r+3;
						while(true) {
							if(rend==whenstop) {
								enemyh.fs=false;
							}
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
				break;
			}
			case 7:{//暂停
				ispause=true;
				new pausetimer(60).start();
				break;
			}
			case 8:{//解除暂停
				ispause=false;
				break;
			}
			case 9:{//对方投降
				winlose=true;
				UpdateJTextArea("【"+enemyh.getName()+"】（"+enemy.getUsername()+"）投降了！你获得了本次比赛的胜利！\n\n");
				break;
			}
			case 11:{//结晶之力
				enemyh.jhjj=1;
				UpdateEnemyHeroSkill();
				break;
			}
			case 12:{//结晶之力
				enemyh.jhjj=2;
				UpdateEnemyHeroSkill();
				break;
			}
			case 13:{//结晶之力
				enemyh.jhjj=3;
				UpdateEnemyHeroSkill();
				break;
			}
			case 14:{//
				break;
			}
			case 26:{//夜宴之声
				eyyzs+=3;
				UpdateJTextArea("【"+enemyh.getName()+"】增加了3次【夜宴之声】的使用次数。\n\n");
				break;
			}
			case 33:{//紫月神杖
				enemyh.setXdl(enemyh.getXdl()+2);
				enemyh.setMpp(enemyh.getMpp()+2);
				enemyh.zy=true;
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【紫月神杖】。\n\n");
				break;
			}
			case 34:{//红月神杖
				enemyh.setAtk(enemyh.getAtk()+3);
				enemyh.setMpp(enemyh.getMpp()+1);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【红月神杖】。\n\n");
				break;
			}
			case 35:{//长剑
				enemyh.setAtk(enemyh.getAtk()+5);
				enemyh.setAdp(enemyh.getAdp()+0.3);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【长剑-朝醉青烟】。\n\n");
				break;
			}
			case 36:{//鹰角弓
				enemyh.yjg=true;
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【鹰角弓】。\n\n");
				break;
			}
			case 37:{//狩猎者匕首
				enemyh.setAtk(enemyh.getAtk()+4);
				enemyh.setAdp(enemyh.getAdp()+0.2);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【狩猎者匕首】。\n\n");
				break;
			}
			case 38:{//新叶手札
				enemyh.setApp(enemyh.getApp()+0.4);
				enemyh.setMpp(enemyh.getMpp()+3);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【新叶传教者手札】。\n\n");
				break;
			}
			case 39:{//破军之矛
				enemyh.setAtk(enemyh.getAtk()+3);
				if(enemyh.pjzmcd==0) enemyh.pjzm=true;
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【破军之矛】。\n\n");
				break;
			}
			case 40:{//维多利娜长袍
				enemyh.setDef(enemyh.getDef()+2);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【维多利娜长袍】。\n\n");
				break;
			}
			case 41:{//圣月斗篷
				enemyh.setAdf(enemyh.getAdf()+2);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【圣月斗篷】。\n\n");
				break;
			}
			case 42:{//坚韧者之盾
				enemyh.jrz=true;
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【坚韧者之盾】。\n\n");
				break;
			}
			case 43:{//守护之戒
				enemyh.setHpp(enemyh.getHpp()+4);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【守护之戒】。\n\n");
				break;
			}
			case 44:{//耐久光环
				enemyh.setMpp(enemyh.getMpp()+4);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【耐久光环】。\n\n");
				break;
			}
			case 45:{//会徽
				enemyh.setXdl(enemyh.getXdl()+3);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【学生会的会徽】。\n\n");
				break;			
			}
			case 46:{//夜宴之声
				enemyh.yyzs=true;
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【夜宴之声】。\n\n");
				break;
			}
			case 53:{//紫月神杖
				enemyh.setXdl(enemyh.getXdl()+2);
				enemyh.setMpp(enemyh.getMpp()+2);
				enemyh.zy=true;
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【紫月神杖】。\n\n");
				break;
			}
			case 54:{//红月神杖
				enemyh.setAtk(enemyh.getAtk()+3);
				enemyh.setMpp(enemyh.getMpp()+1);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【红月神杖】。\n\n");
				break;
			}
			case 55:{//长剑
				enemyh.setAtk(enemyh.getAtk()+5);
				enemyh.setAdp(enemyh.getAdp()+0.3);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【长剑-朝醉青烟】。\n\n");
				break;
			}
			case 56:{//鹰角弓
				enemyh.yjg=true;
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【鹰角弓】。\n\n");
				break;
			}
			case 57:{//狩猎者匕首
				enemyh.setAtk(enemyh.getAtk()+4);
				enemyh.setAdp(enemyh.getAdp()+0.2);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【狩猎者匕首】。\n\n");
				break;
			}
			case 58:{//新叶手札
				enemyh.setApp(enemyh.getApp()+0.4);
				enemyh.setMpp(enemyh.getMpp()+3);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【新叶传教者手札】。\n\n");
				break;
			}
			case 59:{//破军之矛
				enemyh.setAtk(enemyh.getAtk()+3);
				if(enemyh.pjzmcd==0) enemyh.pjzm=true;
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【破军之矛】。\n\n");
				break;
			}
			case 60:{//维多利娜长袍
				enemyh.setDef(enemyh.getDef()+2);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【维多利娜长袍】。\n\n");
				break;
			}
			case 61:{//圣月斗篷
				enemyh.setAdf(enemyh.getAdf()+2);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【圣月斗篷】。\n\n");
				break;
			}
			case 62:{//坚韧者之盾
				enemyh.jrz=true;
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【坚韧者之盾】。\n\n");
				break;
			}
			case 63:{//守护之戒
				enemyh.setHpp(enemyh.getHpp()+4);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【守护之戒】。\n\n");
				break;
			}
			case 64:{//耐久光环
				enemyh.setMpp(enemyh.getMpp()+4);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【耐久光环】。\n\n");
				break;
			}
			case 65:{//会徽
				enemyh.setXdl(enemyh.getXdl()+3);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【学生会的会徽】。\n\n");
				break;			
			}
			case 66:{//夜宴之声
				enemyh.yyzs=true;
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【夜宴之声】。\n\n");
				break;
			}
			/**
			 * - 取消穿戴
			 */
			case 73:{//紫月神杖
				enemyh.setXdl(enemyh.getXdl()-2);
				enemyh.setMpp(enemyh.getMpp()-2);
				enemyh.zy=false;
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【紫月神杖】。\n\n");
				break;
			}
			case 74:{//红月神杖
				enemyh.setAtk(enemyh.getAtk()-3);
				enemyh.setMpp(enemyh.getMpp()-1);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【红月神杖】。\n\n");
				break;
			}
			case 75:{//长剑
				enemyh.setAtk(enemyh.getAtk()-5);
				enemyh.setAdp(enemyh.getAdp()-0.3);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【长剑-朝醉青烟】。\n\n");
				break;
			}
			case 76:{//鹰角弓
				enemyh.yjg=true;
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【鹰角弓】。\n\n");
				break;
			}
			case 77:{//狩猎者匕首
				enemyh.setAtk(enemyh.getAtk()-4);
				enemyh.setAdp(enemyh.getAdp()-0.2);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【狩猎者匕首】。\n\n");
				break;
			}
			case 78:{//新叶手札
				enemyh.setApp(enemyh.getApp()-0.4);
				enemyh.setMpp(enemyh.getMpp()-3);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【新叶传教者手札】。\n\n");
				break;
			}
			case 79:{//破军之矛
				enemyh.setAtk(enemyh.getAtk()-3);
				enemyh.pjzm=false;
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【破军之矛】。\n\n");
				break;
			}
			case 80:{//维多利娜长袍
				enemyh.setDef(enemyh.getDef()-2);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【维多利娜长袍】。\n\n");
				break;
			}
			case 81:{//圣月斗篷
				enemyh.setAdf(enemyh.getAdf()-2);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【圣月斗篷】。\n\n");
				break;
			}
			case 82:{//坚韧者之盾
				enemyh.jrz=false;
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【坚韧者之盾】。\n\n");
				break;
			}
			case 83:{//守护之戒
				enemyh.setHpp(enemyh.getHpp()-4);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【守护之戒】。\n\n");
				break;
			}
			case 84:{//耐久光环
				enemyh.setMpp(enemyh.getMpp()-4);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【耐久光环】。\n\n");
				break;
			}
			case 85:{//会徽
				enemyh.setXdl(enemyh.getXdl()-3);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【学生会的会徽】。\n\n");
				break;			
			}
			case 86:{//夜宴之声
				enemyh.yyzs=false;
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【夜宴之声】。\n\n");
				break;
			}
			case 93:{//紫月神杖
				enemyh.setXdl(enemyh.getXdl()-2);
				enemyh.setMpp(enemyh.getMpp()-2);
				enemyh.zy=false;
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【紫月神杖】。\n\n");
				break;
			}
			case 94:{//红月神杖
				enemyh.setAtk(enemyh.getAtk()-3);
				enemyh.setMpp(enemyh.getMpp()-1);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【红月神杖】。\n\n");
				break;
			}
			case 95:{//长剑
				enemyh.setAtk(enemyh.getAtk()-5);
				enemyh.setAdp(enemyh.getAdp()-0.3);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【长剑-朝醉青烟】。\n\n");
				break;
			}
			case 96:{//鹰角弓
				enemyh.yjg=true;
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【鹰角弓】。\n\n");
				break;
			}
			case 97:{//狩猎者匕首
				enemyh.setAtk(enemyh.getAtk()-4);
				enemyh.setAdp(enemyh.getAdp()-0.2);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【狩猎者匕首】。\n\n");
				break;
			}
			case 98:{//新叶手札
				enemyh.setApp(enemyh.getApp()-0.4);
				enemyh.setMpp(enemyh.getMpp()-3);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【新叶传教者手札】。\n\n");
				break;
			}
			case 99:{//破军之矛
				enemyh.setAtk(enemyh.getAtk()-3);
				enemyh.pjzm=false;
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【破军之矛】。\n\n");
				break;
			}
			case 100:{//维多利娜长袍
				enemyh.setDef(enemyh.getDef()-2);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【维多利娜长袍】。\n\n");
				break;
			}
			case 101:{//圣月斗篷
				enemyh.setAdf(enemyh.getAdf()-2);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【圣月斗篷】。\n\n");
				break;
			}
			case 102:{//坚韧者之盾
				enemyh.jrz=false;
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【坚韧者之盾】。\n\n");
				break;
			}
			case 103:{//守护之戒
				enemyh.setHpp(enemyh.getHpp()-4);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【守护之戒】。\n\n");
				break;
			}
			case 104:{//耐久光环
				enemyh.setMpp(enemyh.getMpp()-4);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【耐久光环】。\n\n");
				break;
			}
			case 105:{//会徽
				enemyh.setXdl(enemyh.getXdl()-3);
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【学生会的会徽】。\n\n");
				break;			
			}
			case 106:{//夜宴之声
				enemyh.yyzs=false;
				UpdateJTextArea("【"+enemyh.getName()+"】取消装备了【夜宴之声】。\n\n");
				break;
			}
			/**
			 * - 吃装备
			 */
			case 114:{//红月神杖
				enemyh.setAtk(enemyh.getAtk()+3);
				enemyh.setMpp(enemyh.getMpp()+1);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【红月神杖】。\n\n");
				break;
			}
			case 115:{//长剑
				enemyh.setAtk(enemyh.getAtk()+5);
				enemyh.setAdp(enemyh.getAdp()+0.3);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【长剑-朝醉青烟】。\n\n");
				break;
			}
			case 116:{//鹰角弓
				enemyh.yjg=true;
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【鹰角弓】。\n\n");
				break;
			}
			case 117:{//狩猎者匕首
				enemyh.setAtk(enemyh.getAtk()+4);
				enemyh.setAdp(enemyh.getAdp()+0.2);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【狩猎者匕首】。\n\n");
				break;
			}
			case 118:{//新叶手札
				enemyh.setApp(enemyh.getApp()+0.4);
				enemyh.setMpp(enemyh.getMpp()+3);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【新叶传教者手札】。\n\n");
				break;
			}
			case 120:{//维多利娜长袍
				enemyh.setDef(enemyh.getDef()+2);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【维多利娜长袍】。\n\n");
				break;
			}
			case 121:{//圣月斗篷
				enemyh.setAdf(enemyh.getAdf()+2);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【圣月斗篷】。\n\n");
				break;
			}
			case 123:{//守护之戒
				enemyh.setHpp(enemyh.getHpp()+4);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【守护之戒】。\n\n");
				break;
			}
			case 124:{//耐久光环
				enemyh.setMpp(enemyh.getMpp()+4);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【耐久光环】。\n\n");
				break;
			}
			case 125:{//会徽
				enemyh.setXdl(enemyh.getXdl()+3);
				UpdateJTextArea("【"+enemyh.getName()+"】装备了【学生会的会徽】。\n\n");
				break;			
			}
		}
		usertx.setToolTipText(userh.getProperty());
		enemytx.setToolTipText(enemyh.getProperty());
		atk.setToolTipText("<html>使用普通攻击。<br /><br />提示：最多可造成"+userh.getAtk()+"点物理伤害。</html>");
	}
	
	public void getUserEquip(int id) {
		switch(id) {
			case 13:{//紫月神杖
				userh.setXdl(userh.getXdl()+2);
				userh.setMpp(userh.getMpp()+2);
				userh.zy=true;
				UpdateJTextArea("你装备了【紫月神杖】。\n\n");
				break;
			}
			case 14:{//红月神杖
				userh.setAtk(userh.getAtk()+3);
				userh.setMpp(userh.getMpp()+1);
				UpdateJTextArea("你装备了【红月神杖】。\n\n");
				break;
			}
			case 15:{//长剑
				userh.setAtk(userh.getAtk()+5);
				userh.setAdp(userh.getAdp()+0.3);
				UpdateJTextArea("你装备了【长剑-朝醉青烟】。\n\n");
				break;
			}
			case 16:{//鹰角弓
				userh.yjg=true;
				UpdateJTextArea("你装备了【鹰角弓】。\n\n");
				break;
			}
			case 17:{//狩猎者匕首
				userh.setAtk(userh.getAtk()+4);
				userh.setAdp(userh.getAdp()+0.2);
				UpdateJTextArea("你装备了【狩猎者匕首】。\n\n");
				break;
			}
			case 18:{//新叶手札
				userh.setApp(userh.getApp()+0.4);
				userh.setMpp(userh.getMpp()+3);
				UpdateJTextArea("你装备了【新叶传教者手札】。\n\n");
				break;
			}
			case 19:{//破军之矛
				userh.setAtk(userh.getAtk()+3);
				if(userh.pjzmcd==0) userh.pjzm=true;
				UpdateJTextArea("你装备了【破军之矛】。\n\n");
				break;
			}
			case 20:{//维多利娜长袍
				userh.setDef(userh.getDef()+2);
				UpdateJTextArea("你装备了【维多利娜长袍】。\n\n");
				break;
			}
			case 21:{//圣月斗篷
				userh.setAdf(userh.getAdf()+2);
				UpdateJTextArea("你装备了【圣月斗篷】。\n\n");
				break;
			}
			case 22:{//坚韧者之盾
				userh.jrz=true;
				UpdateJTextArea("你装备了【坚韧者之盾】。\n\n");
				break;
			}
			case 23:{//守护之戒
				userh.setHpp(userh.getHpp()+4);
				UpdateJTextArea("你装备了【守护之戒】。\n\n");
				break;
			}
			case 24:{//耐久光环
				userh.setMpp(userh.getMpp()+4);
				UpdateJTextArea("你装备了【耐久光环】。\n\n");
				break;
			}
			case 25:{//会徽
				userh.setXdl(userh.getXdl()+3);
				UpdateJTextArea("你装备了【学生会的会徽】。\n\n");
				break;			
			}
			case 26:{//夜宴之声
				userh.yyzs=true;
				UpdateJTextArea("你装备了【夜宴之声】。\n\n");
				break;
			}
			/**
			 * - 取消穿戴
			 */
			case 33:{//紫月神杖
				userh.setXdl(userh.getXdl()-2);
				userh.setMpp(userh.getMpp()-2);
				userh.zy=false;
				UpdateJTextArea("你取消装备了【紫月神杖】。\n\n");
				break;
			}
			case 34:{//红月神杖
				userh.setAtk(userh.getAtk()-3);
				userh.setMpp(userh.getMpp()-1);
				UpdateJTextArea("你取消装备了【红月神杖】。\n\n");
				break;
			}
			case 35:{//长剑
				userh.setAtk(userh.getAtk()-5);
				userh.setAdp(userh.getAdp()-0.3);
				UpdateJTextArea("你取消装备了【长剑-朝醉青烟】。\n\n");
				break;
			}
			case 36:{//鹰角弓
				userh.yjg=true;
				UpdateJTextArea("你取消装备了【鹰角弓】。\n\n");
				break;
			}
			case 37:{//狩猎者匕首
				userh.setAtk(userh.getAtk()-4);
				userh.setAdp(userh.getAdp()-0.2);
				UpdateJTextArea("你取消装备了【狩猎者匕首】。\n\n");
				break;
			}
			case 38:{//新叶手札
				userh.setApp(userh.getApp()-0.4);
				userh.setMpp(userh.getMpp()-3);
				UpdateJTextArea("你取消装备了【新叶传教者手札】。\n\n");
				break;
			}
			case 39:{//破军之矛
				userh.setAtk(userh.getAtk()-3);
				userh.pjzm=false;
				UpdateJTextArea("你取消装备了【破军之矛】。\n\n");
				break;
			}
			case 40:{//维多利娜长袍
				userh.setDef(userh.getDef()-2);
				UpdateJTextArea("你取消装备了【维多利娜长袍】。\n\n");
				break;
			}
			case 41:{//圣月斗篷
				userh.setAdf(userh.getAdf()-2);
				UpdateJTextArea("你取消装备了【圣月斗篷】。\n\n");
				break;
			}
			case 42:{//坚韧者之盾
				userh.jrz=false;
				UpdateJTextArea("你取消装备了【坚韧者之盾】。\n\n");
				break;
			}
			case 43:{//守护之戒
				userh.setHpp(userh.getHpp()-4);
				UpdateJTextArea("你取消装备了【守护之戒】。\n\n");
				break;
			}
			case 44:{//耐久光环
				userh.setMpp(userh.getMpp()-4);
				UpdateJTextArea("你取消装备了【耐久光环】。\n\n");
				break;
			}
			case 45:{//会徽
				userh.setXdl(userh.getXdl()-3);
				UpdateJTextArea("你取消装备了【学生会的会徽】。\n\n");
				break;			
			}
			case 46:{//夜宴之声
				userh.yyzs=false;
				UpdateJTextArea("你取消装备了【夜宴之声】。\n\n");
				break;
			}
		}
		usertx.setToolTipText(userh.getProperty());
		enemytx.setToolTipText(enemyh.getProperty());
		atk.setToolTipText("<html>使用普通攻击。<br /><br />提示：最多可造成"+userh.getAtk()+"点物理伤害。</html>");
	}
	
	public void UpdateEnemyHeroSkill() {
		switch(enemyh.getId()) {
			case 1:{
				if(enemyh.jhjj==1) {
					enemyh.setApp(enemyh.getApp()+0.3);
				}
				break;
			}
			case 9:{
				if(enemyh.jhjj==3) {
					enemyh.getQ().setMp(enemyh.getQ().getMp()-2);
					enemyh.getW().setMp(enemyh.getW().getMp()-2);
					enemyh.getE().setMp(enemyh.getE().getMp()-2);
					enemyh.getR().setMp(enemyh.getR().getMp()-2);
				}
				break;
			}
			case 11:{
				if(enemyh.jhjj==2) {
					enemyh.setAtk(enemyh.getAtk()+4);
				}
				break;
			}
		}
	}
	
	public class pausetimer extends Thread {
		int time;
		public pausetimer(int remain){
			time=remain;
		}
		@Override
		public void run() {
			while(time>0) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				time--;
				getremaintime();
			}
			if(ispause&&isuserpause) {
				setpause(0);
			}
		}
		private void getremaintime() {
			if(ispause) {
				if(time==40) UpdateJTextArea("还有40秒自动解除暂停。\n\n");
				if(time==20) UpdateJTextArea("还有20秒自动解除暂停。\n\n");
				if(time==10) UpdateJTextArea("还有10秒自动解除暂停。\n\n");
				if(time==5) UpdateJTextArea("还有5秒自动解除暂停。\n\n");
				if(time==2) UpdateJTextArea("还有2秒自动解除暂停。\n\n");
			} else {
				time=-1;
			}
		}
	}
	
	void updateherostatus(Hero h) {
		int t = herotime;
		double d = herodamage;
		double adr = d / t;
		double status;
		BigDecimal bd;
		double hs;
		
		int htime = ((h.getTime() * h.getPlay() + t) / (h.getPlay() + 1));
		Config.s.UpdateHeroTime(h.getId(), htime);
		
		DecimalFormat df = new DecimalFormat("0.00");// 设置保留位数
		
		status = ((h.getD() * h.getPick() + d) / (h.getPlay() + 1));
		bd = new BigDecimal(df.format((double) status));
		hs = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		Config.s.UpdateHeroDamage(h.getId(), hs);
		
		status = ((h.getAdr() * h.getPick() + adr) / (h.getPlay() + 1));
		bd = new BigDecimal(df.format((double) status));
		hs = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		Config.s.UpdateHeroADR(h.getId(), hs);
	}
	
	void addherorate() {
		Hero temp;
		for(int i=0;i<userbanned.size();i++) {
			temp = userbanned.get(i);
			temp.setPlay(temp.getPlay()+1);
			temp.setBan(temp.getBan()+1);
			DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
			BigDecimal bd = new BigDecimal(df.format((double)temp.getBan()/temp.getPlay()));
			double banrate = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			Config.s.addHeroBan(temp.getId(), banrate);
		}
		for(int i=0;i<userpicked.size();i++) {
			temp = userpicked.get(i);
			temp.setPlay(temp.getPlay()+1);
			temp.setPick(temp.getPick()+1);
			DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
			BigDecimal bd = new BigDecimal(df.format((double)temp.getPick()/temp.getPlay()));
			double pickrate = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			Config.s.addHeroPick(temp.getId(), pickrate);
		}
		if(winlose) {
			for(int i=0;i<userpicked.size();i++) {
				temp = userpicked.get(i);
				temp.setWin(temp.getWin()+1);
				DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
				BigDecimal bd = new BigDecimal(df.format((double)temp.getWin()/temp.getPick()));
				double winrate = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				Config.s.addHeroWin(temp.getId(), winrate);
			}
		}
		Config.s.updateHerorate();
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
	
	void close3() {
		try {
			socket3.shutdownInput();
			socket3.shutdownOutput();
			if(br3!=null) br3.close();
			if(ois3!=null) ois3.close();
			if(dis3!=null) dis3.close();
			if(isr3!=null) isr3.close();
			if(is3!=null) is3.close();
			if(pw3!=null) pw3.close();
			if(dos3!=null) dos3.close();
			if(pw3!=null) pw3.close();
			if(socket3!=null) socket3.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void close4() {
		try {
			socket4.shutdownInput();
			socket4.shutdownOutput();
			if(br4!=null) br4.close();
			if(ois4!=null) ois4.close();
			if(dis4!=null) dis4.close();
			if(isr4!=null) isr4.close();
			if(is4!=null) is4.close();
			if(pw4!=null) pw4.close();
			if(dos4!=null) dos4.close();
			if(pw4!=null) pw4.close();
			if(socket4!=null) socket4.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void close5() {
		try {
			socket5.shutdownInput();
			socket5.shutdownOutput();
			if(br5!=null) br5.close();
			if(ois5!=null) ois5.close();
			if(dis5!=null) dis5.close();
			if(isr5!=null) isr5.close();
			if(is5!=null) is5.close();
			if(pw5!=null) pw5.close();
			if(dos5!=null) dos5.close();
			if(pw5!=null) pw5.close();
			if(socket5!=null) socket5.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}