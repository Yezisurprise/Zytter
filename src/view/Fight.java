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
	 * - ��ս��һ�����Ϣ
	 */
	
	User user,enemy;
	
	int roomid;
	
	Server server;

	ArrayList<Hero> userheroes,enemyheroes;
	
	ArrayList<Hero> userbanned = new ArrayList<Hero>(),enemybanned = new ArrayList<Hero>();
	
	ArrayList<Hero> userpicked = new ArrayList<Hero>(),enemypicked = new ArrayList<Hero>();
	
	ArrayList<JButton> select = new ArrayList<JButton>(); // �ж���ť�ļ���
	
	ArrayList<Item> djh = new ArrayList<Item>(); // ���ߺ�
	
	Hero userh,enemyh;
	
	Item willitem = null;
	
	Item willequip = null;
	
	/*
	 * - ��ս����UI�漰�ı���
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
	 * - ͳ������
	 */
	
	int fsjn = 0,gjfsjn = 0; // �������������
	
	int p = 0; // ����ֵ
	
	int op = 0 , userop = 0 , enemyop = 0; // ����ֵ
	
	int mp = 0; // ħ������
	
	int r = 0 , limitr = 35; // �غ���
	
	int rend = 1; // �غϽ����������Ǳ�r��1��
	
	int remain = 20; //�غ�ʣ��ʱ��
	
	int gold = 0; // �����
	
	int readyitem = 0; // ׼���ĵ���
	
	int selected = 0; // ѡ��
	
	int hpp = 0,mpp = 0; // �ۼ������ظ����ۼ�ħ���ظ�
	
	double adr = 0; // ƽ���غ��˺�
	
	int ark = 0,damage = 0,hurt = 0; // ƽ����ɱ�غϣ��ۼ�����˺����ۼƳ����˺������ڼ���rating��
	
	int robd = 0; // �����ж�ÿ���10���˺��Ӷ�һ����
	
	int robh = 0; // �����ж�ÿ�ܵ�10���˺����Ӷ�һ����
	
	int kill = 0,death = 0; // ��ɱ����������
	
	int herotime = 0; // Ӣ���ϳ��غ�
	
	double herodamage = 0; // Ӣ���ۼ��˺�
	
	double rating;// rating
	
	int elo;// elo
	
	boolean winlose = false; // Ӯ������
	
	boolean roomcreater; // �ǲ��Ƿ��䴴����
	
	int uyyzs = 0,eyyzs = 0; // ҹ��֮����ʹ�ô���
	
	boolean xdsx = false; // �ж�����
	
	boolean gameover = false; // ��Ϸ�Ƿ����
	
	int heroup = 0; // ������������
	
	boolean ispause = false; // ��Ϸ�Ƿ���ͣ
	
	int pausetimes = 3; // ʣ����ͣ����
	
	boolean isuserpause = false; // �Ƿ�������ͣ
	
	boolean eatequip = true; // ��װ���Ļ���
	
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
	
	/** ��ս���湹�췽��
	 * @param user - ���
	 * @param enemy - ����
	 * @param userheroes - ���Ӣ����
	 * @param enemyheroes - ����Ӣ����
	 * @param iscreater - �Ƿ�Ϊ����ӵ����
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
				
		remaintime = new JLabel("��ʣ"+remain+"��");
		remaintime.setForeground(Color.RED);
		remaintime.setFont(new Font("����", Font.PLAIN, 35));
		remaintime.setHorizontalAlignment(SwingConstants.CENTER);
		remaintime.setBounds(684, 38, 421, 54);
		this.add(remaintime);
		
		round = new JLabel("����������ʼ��");
		round.setForeground(Color.white);
		round.setHorizontalAlignment(SwingConstants.CENTER);
		round.setFont(new Font("΢���ź�", Font.PLAIN, 22));
		round.setBounds(268, 10, 837, 28);
		this.add(round);
		
		whoact = new JLabel("�ȴ��������...");
		whoact.setForeground(Color.white);
		whoact.setHorizontalAlignment(SwingConstants.CENTER);
		whoact.setFont(new Font("����", Font.PLAIN, 35));
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
	
	/** ��ʼ��FightUI
	 */
	
	public void InitGUI() {
		
		this.userh = Config.s.GetSkillByHero(getUserHero());
		this.enemyh = Config.s.GetSkillByHero(getEnemyHero());
		
		Config.bgm = new BGM();
		Config.bgm.setBGM(3);
		Config.bgm.start();
		
		tip1 = new JLabel("��ǰӵ�н�ң�"+gold);
		tip1.setHorizontalAlignment(SwingConstants.CENTER);
		tip1.setForeground(Color.white);
		tip1.setFont(new Font("����", Font.PLAIN, 16));
		tip1.setBounds(10, 23, 239, 30);
		this.add(tip1);
		
		tip2 = new JLabel("�ۼ�����/ħ���ظ���"+hpp+" / "+mpp);
		tip2.setHorizontalAlignment(SwingConstants.CENTER);
		tip2.setForeground(Color.white);
		tip2.setFont(new Font("����", Font.PLAIN, 16));
		tip2.setBounds(10, 57, 239, 30);
		this.add(tip2);
		
		tip3 = new JLabel("ƽ���˺�/��ɱ�غϣ�"+0+" / "+0);
		tip3.setHorizontalAlignment(SwingConstants.CENTER);
		tip3.setForeground(Color.white);
		tip3.setFont(new Font("����", Font.PLAIN, 16));
		tip3.setBounds(1117, 23, 239, 30);
		this.add(tip3);
		
		tip4 = new JLabel("�ۼ����/�����˺���"+damage+" / "+hurt);
		tip4.setHorizontalAlignment(SwingConstants.CENTER);
		tip4.setForeground(Color.white);
		tip4.setFont(new Font("����", Font.PLAIN, 16));
		tip4.setBounds(1117, 57, 239, 30);
		this.add(tip4);
		
		now = new JLabel("��ǰ��սӢ�ۣ�"+userh.getName());
		now.setHorizontalAlignment(SwingConstants.CENTER);
		now.setForeground(Color.white);
		now.setFont(new Font("����", Font.PLAIN, 16));
		now.setBounds(33, 108, 200, 30);
		this.add(now);
		
		enemyid = new JLabel(enemy.getUsername());
		enemyid.setForeground(Color.white);
		enemyid.setHorizontalAlignment(SwingConstants.CENTER);
		enemyid.setFont(new Font("����", Font.PLAIN, 30));
		enemyid.setBounds(1130, 93, 200, 48);
		this.add(enemyid);
		
		enemyhero = new JLabel(enemyh.getName());
		enemyhero.setForeground(Color.white);
		enemyhero.setHorizontalAlignment(SwingConstants.RIGHT);
		enemyhero.setFont(new Font("����", Font.PLAIN, 12));
		enemyhero.setBounds(1130, 137, 200, 15);
		this.add(enemyhero);

		this.getUserHeroIcon(userh.getId());
		this.add(usertx);
		
		this.getEnemyHeroIcon(enemyh.getId());
		this.add(enemytx);
		
		JLabel userhp = new JLabel("HP");
		userhp.setForeground(Color.RED);
		userhp.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		userhp.setBounds(45, 376, 29, 28);
		this.add(userhp);
		
		JLabel enemyhp = new JLabel("HP");
		enemyhp.setForeground(Color.RED);
		enemyhp.setFont(new Font("΢���ź� Light", Font.PLAIN, 20));
		enemyhp.setBounds(1145, 376, 29, 28);
		this.add(enemyhp);
		
		JLabel usermp = new JLabel("MP");
		usermp.setForeground(Color.BLUE);
		usermp.setFont(new Font("΢���ź� Light", Font.PLAIN, 18));
		usermp.setBounds(45, 415, 29, 28);
		this.add(usermp);
		
		JLabel enemymp = new JLabel("MP");
		enemymp.setForeground(Color.BLUE);
		enemymp.setFont(new Font("΢���ź� Light", Font.PLAIN, 18));
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
		usernext.setFont(new Font("����", Font.PLAIN, 16));
		usernext.setBounds(33, 467, 200, 32);
		this.add(usernext);
		
		enemynext = new JLabel(getEnemyNext());
		enemynext.setForeground(Color.white);
		enemynext.setHorizontalAlignment(SwingConstants.CENTER);
		enemynext.setFont(new Font("����", Font.PLAIN, 16));
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
		zb1.setToolTipText("���ѡ��һ��װ����������");
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
		zb2.setToolTipText("���ѡ��һ��װ����������");
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
		atk.setToolTipText("<html>ʹ����ͨ������<br /><br />��ʾ���������"+userh.getAtk()+"�������˺���</html>");
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
		ok.setToolTipText("�����ûغϵ��ж���");
		ok.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/decide.png")));
		ok.setFont(new Font("����", Font.BOLD, 22));
		ok.setBounds(1202, 603, 128, 64);
		ok.setContentAreaFilled(false);
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(selected!=0) {
					if(!ispause) { // ��Ϸδ��ͣ
						if(op!=0) { // δѡ��
							if(op!=6) { // ������ʹ����Ʒ
								if(userh.isIsfight()){  // ������ս������
									if(op==5){ // ʹ����ͨ����
										if(userh.isIsatk()){ // �����ڹ�������
											selected = 0;
											try {
												dos.writeInt(op);
												dos.flush();
											} catch (IOException e) {
												e.printStackTrace();
											}
											userop = op;
											setcanSelect();
											UpdateJTextArea("���Ѿ������˸ûغϵ��ж���\n\n");
										} else {
											JOptionPane.showMessageDialog(null, "�㲻�ܽ�����ͨ������\nԭ�򣺹������ܡ�");
										}
									} else if(op>=1&&op<=4){ // ʹ�ü���
										if(userh.isIsskill()){ // ������ʩ������
											if(userh.getMp()>=mp) { // �ж�ħ��ֵ������
												if(op==2&&userh.getId()==9){ // ֣���� ���� ��ʹ���ж�
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
														UpdateJTextArea("���Ѿ������˸ûغϵ��ж���\n\n");
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
															UpdateJTextArea("���Ѿ������˸ûغϵ��ж���\n\n");
														} else {
															JOptionPane.showMessageDialog(null, "���ڲ��ǵ����غϣ�����ʹ�á����ǡ���");
														}
													}
												} else if(op==1&&userh.getId()==9){ // ֣���� ���޵�ʹ���ж�
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
															UpdateJTextArea("���Ѿ������˸ûغϵ��ж���\n\n");
														} else {
															JOptionPane.showMessageDialog(null, "�����ޡ�������Ч�У������ظ�ʹ�á�");
														}
													} else {
														JOptionPane.showMessageDialog(null, "���ڲ���˫���غϣ�����ʹ�á����ޡ���");
													}
												} else if(op==3&&userh.getId()==4){ // ����� �Ϲǽ� ��ʹ���ж�
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
															UpdateJTextArea("���Ѿ������˸ûغϵ��ж���\n\n");
														} else {
															JOptionPane.showMessageDialog(null, "�����������ֵС�ڻ����7�㣬���ڰ�ȫ���ǣ���ֹ��ʹ�á��Ϲǽ�����");
														}
													} else {
														JOptionPane.showMessageDialog(null, "��ֻ�����ϳ�2�غϺ����ʹ�á��Ϲǽ�����");
													}
												} else if(op==2&&userh.getId()==Config.zf.getId()){ // �ŷ� ��֮��� ��ʹ���ж�
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
														UpdateJTextArea("���Ѿ������˸ûغϵ��ж���\n\n");
													} else {
														JOptionPane.showMessageDialog(null, "�Է����ж����ޡ�Ч��������Ч�����غ��㲻��ʹ�á���֮��硿��");
													}
												} else if(op==3&&userh.getId()==Config.xyh.getId()) { // л�ƺ� ʱ��ɳ© ��ʹ���ж�
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
															UpdateJTextArea("���Ѿ������˸ûغϵ��ж���\n\n");
														} else {
															JOptionPane.showMessageDialog(null, "��ʱ��ɳ©��������Ч�У������ظ�ʹ�á�");
														}
													} else {
														JOptionPane.showMessageDialog(null, "��ֻ�����ϳ�3�غϺ����ʹ�á�ʱ��ɳ©����");
													}
												} else if(op==3&&userh.getId()==Config.zkx.getId()) { // �ſ�ϫ ϫ֮���� ��ʹ���ж�
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
														UpdateJTextArea("���Ѿ������˸ûغϵ��ж���\n\n");
													} else {
														JOptionPane.showMessageDialog(null, "��ֻ�����ϳ�3�غϺ����ʹ�á�ϫ֮���񡿡�");
													}
												} else if(op==3&&userh.getId()==Config.sjj.getId()) { // �խZ�� ��㽣 ��ʹ���ж�
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
														UpdateJTextArea("���Ѿ������˸ûغϵ��ж���\n\n");
													} else {
														JOptionPane.showMessageDialog(null, "����㽣��������Ч�У������ظ�ʹ�á�");
													}
												} else {
													if(op==2&&userh.getId()==Config.yy.getId()) { // ���� ��Ӱ֮�� ��ʹ���ж�
														userh.setXdl(userh.getXdl()+999);
													}
													if(op==1&&userh.getId()==Config.xyh.getId()) { // л�ƺ� �ྻ֮��  ��ʹ���ж�
														userh.setXdl(userh.getXdl()+9999);
													}
													if(op==3&&userh.getId()==Config.hyq.getId()) { // �C��ȴ �����漣 ��ʹ���ж�
														if(userh.jhjj==2) userh.setXdl(userh.getXdl()+999);
													}
													if(op==4&&userh.getId()==Config.hyq.getId()) { // �C��ȴ ����֮�� ��ʹ���ж�
														userh.setXdl(userh.getXdl()+999);
													}
													if(op==2&&userh.getId()==Config.ysn.getId()){ // ��ʥŵ �����ǳ� ��ʹ���ж�
														if(JOptionPane.showConfirmDialog(null,"ʹ��Q���ܡ����ǳ�̡���")==0) {
															if(selected==1) {
																if(userh.getMp()-userh.getW().getMp()>=userh.getQ().getMp()) {
																	userh.ysnQ=true;
																} else {
																	JOptionPane.showMessageDialog(null,"ħ��ֵ���㣡");
																}
															} else {
																JOptionPane.showMessageDialog(null,"������ʱ��");
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
													UpdateJTextArea("���Ѿ������˸ûغϵ��ж���\n\n");
												}
											} else {
												JOptionPane.showMessageDialog(null, "ħ��ֵ���㣬�޷�ʹ�ô˼��ܡ�");
											}
										} else {
											JOptionPane.showMessageDialog(null, "�㲻��ʹ���κμ��ܡ�\nԭ��ʩ�����ܡ�");
										}
									}
								} else {
									JOptionPane.showMessageDialog(null, "�㲻�ܾ������ж���\nԭ��ս�����ܡ�");
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
									UpdateJTextArea("���Ѿ������˸ûغϵ��ж���\n\n");
								} else {
									JOptionPane.showMessageDialog(null, "δָ�����ߣ�����׼������");
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "δָ���κ��ж�");
						}
					} else {
						JOptionPane.showMessageDialog(null, "��Ϸ������ͣ״̬���޷������ж���");
					}
				}
			}
		});
		this.add(ok);
		
		gg = new JButton("");
		gg.setToolTipText("�����ûغ��ж���");
		gg.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/giveup.png")));
		gg.setFont(new Font("����", Font.BOLD, 22));
		gg.setBounds(1202, 677, 128, 64);
		gg.setContentAreaFilled(false);
		gg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(selected!=0) {
					if(JOptionPane.showConfirmDialog(null,"ȷ���������غ��ж���","�����ж�",JOptionPane.YES_NO_OPTION)==0) {
						selected=0;
						op=0;
						userop=0;
						try {
							dos.writeInt(-1);
							dos.flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
						UpdateJTextArea("���Ѿ������˸ûغϵ��ж���\n\n");
					}
				}
			}
		});
		this.add(gg);

		item = new JButton("");
		item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
		item.setToolTipText("���ѡ��һ�����ߣ��Ա�ûغ�ʹ�á�");
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
		chat.setToolTipText("�����TA˵Щʲô��");
		chat.setBounds(1117, 603, 64, 64);
		chat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = JOptionPane.showInputDialog("����ͶԷ�˵ʲô��");
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
																	UpdateJTextArea("��ԶԷ�˵��"+str+"\n\n");
																	pw3.write(user.getUsername()+"��"+userh.getName()+"��˵��"+str+"\n");
																	pw3.flush();
																} else {
																	if(userh.getId()==Config.xyh.getId()) {
																		UpdateJTextArea("�㵱ǰ�Ľྻ��Ϊ��"+userh.xyh+"\n\n");
																	} else {
																		UpdateJTextArea("����Ȩʹ�øÿ��\n\n");
																	}
																}
															} else {
																if(userh.getId()==Config.lxs.getId()) {
																	UpdateJTextArea("�㵱ǰ����ɱ����Ϊ��"+userh.lxsE*10+"%\n\n");
																} else {
																	UpdateJTextArea("����Ȩʹ�øÿ��\n\n");
																}
															}
														} else {
															UpdateJTextArea("�Է���ǰӢ�ۣ�"+enemyh.getName()+"\n�������ֵ��"+enemyhpt.getMaximum()+"\n���ħ��ֵ��"+enemympt.getMaximum()+"\n\n");
														}
													} else {
														UpdateJTextArea("�㵱ǰ��Ӣ�ۣ�"+userh.getName()+"\n�������ֵ��"+userhpt.getMaximum()+"\n���ħ��ֵ��"+usermpt.getMaximum()+"\n\n");
													}
												} else {
													if(gsu!=null) {
														gsu.setVisible(true);
													} else {
														UpdateJTextArea("����δ��ɻ���Ȩʹ�øÿ��\n\n");
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
								UpdateJTextArea("����Ȩʹ�øÿ��\n\n");
							}
						} else {
							UpdateJTextArea("����Ȩʹ�øÿ��\n\n");
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
		
		UpdateJTextArea("��ӭ����Zytter������Ϊ����ʱ�䣬20��������ʽ��ʼ��"+"\n\n");
		
		UpdateJTextArea("��ǰ�汾�ţ�"+Config.clientversion+"��v1.5a��"+"\n\n");
		
		whoact.setText("����ʱ��");
		
		repaint();
		
	}
	
	public void openItemBox() {
		new openDJH(this).setVisible(true);
	}
	
	public void startfight() {
		/*
		 * �غ϶�ս
		 */
		try {
			for(int i=1;i<=limitr;i++) {
				if(i==6 || i==13 || i==20 || i==27 || i==32 || (i>35&&((i-33)%5==0))) {
					/*
					 * �̵�غ�
					 */
					this.setEnabled(false);
					UpdateJTextArea("- �̵�غ� -\n\n");
					round.setText("�̵�غ�");
					whoact.setText("�����̵��ﹺ����Ʒ");
					if(i<35) {
						UpdateJTextArea("�����̵�Ϊ��ҷ����˹��ʣ�2���ҡ�\n\n");
						gold+=2;
						tip1.setText("��ǰӵ�н�ң�"+gold);
						new openStore(this,i,20,gold,djh,user,userh).start();
						remain=22;
						remaintime.setText("��ʣ" + remain + "��");
					} else {
						UpdateJTextArea("�����̵�Ϊ��ҷ����˹��ʣ�4���ҡ�\n\n");
						gold+=4;
						tip1.setText("��ǰӵ�н�ң�"+gold);
						new openStore(this,i,15,gold,djh,user,userh).start();
						remain=17;
						remaintime.setText("��ʣ" + remain + "��");
					}
					while((remain--)>0) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						remaintime.setText("��ʣ" + remain + "��");
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
							UpdateJTextArea("˫��Ӣ������������4���������޺�5%�������˺����⣡\n\n");
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
							UpdateJTextArea("˫��Ӣ������������4���������޺�5%�������˺����⣡\n\n");
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
							UpdateJTextArea("˫��Ӣ������������5���������޺�5%�������˺����⣡\n\n");
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
							UpdateJTextArea("˫��Ӣ������������5���������޺�5%�������˺����⣡\n\n");
							break;
						}
					}
				}
				/*
				 * �غϿ�ʼ
				 */
				r++;
				new Thread() {
					@Override
					public void run() {
						discountbuff();
					}
				}.start();
				round.setText("�غϣ�"+r+" / "+limitr+"��");
				herotime++;
				UpdateJTextArea("- �غϣ�" + r + " / " + limitr + "�� -\n\n");
				whoact.setText("׼��ʱ��");
				UpdateJTextArea("��������׶Σ�\n\n");
				usertx.setToolTipText(userh.getProperty());
				enemytx.setToolTipText(enemyh.getProperty());
				atk.setToolTipText("<html>ʹ����ͨ������<br /><br />��ʾ���������"+userh.getAtk()+"�������˺���</html>");
				if(r==36) {
					UpdateJTextArea("���������ʱ���׶Σ�Ϊ�˼�����ң������̵��Ƴ�����һ�εĻ��\n����һ���̵��ʱ�������һ���ظ���װ�������ڴ����Ļ��ߵ��ߺ����еģ��������û�ø�װ����Ч����\n\n");
				}
				if(!enemyh.isIslimte()||!userh.isIslimte()) {
					remain = 10;
					remaintime.setText("��ʣ" + remain + "��");
					if(!enemyh.isIslimte()) {
						whoact.setText("�ȴ��Է�����ж�����");
					} else if(!userh.isIslimte()) {
						xdsx = true;
						whoact.setText("������Ƿ�������");
						new Thread() {
							@Override
							public void run() {
								int n = JOptionPane.showConfirmDialog(null, "������ǡ�ʹ�ø��ս��ң��������ʹ�ø߼����ս��ң������ȡ��������ʹ�á�");
								if(n==0) {
									if(fsjn>0) {
										if(xdsx) {
											setE(4);
											fsjn--;
											djh.remove(Config.Allitems.get(4));
											UpdateJTextArea("��"+userh.getName()+"��ʹ���˵��ߡ����ս��ҡ���"+"\n\n");
											UpdateJTextArea("��"+userh.getName()+"�������������غϻ�ÿ������ߣ�\n\n");
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
											UpdateJTextArea("���ѽ���ûغϡ��ж����ޡ���\n\n");
										} else {
											UpdateJTextArea("������ʱ��\n\n");
										}
									} else {
										UpdateJTextArea("��û�С����ս��ҡ���\n\n");
									}
								} else if(n==1) {
									if(gjfsjn>0) {
										if(xdsx) {
											setE(5);
											gjfsjn--;
											djh.remove(Config.Allitems.get(5));
											UpdateJTextArea("��"+userh.getName()+"��ʹ���˵��ߡ��߼����ս��ҡ���"+"\n\n");
											if(userh.getHp()+5<=userhpt.getMaximum()) {
												userh.setHp(userh.getHp()+5);
												hpp+=5;
											} else {
												hpp+=userhpt.getMaximum()-userh.getHp();
												userh.setHp(userhpt.getMaximum());
											}
											userhpt.setValue(userh.getHp());
											userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
											UpdateJTextArea("��"+userh.getName()+"���ظ���5������ֵ�������������غϻ�ÿ������ߣ�\n\n");
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
											UpdateJTextArea("���ѽ���ûغϡ��ж����ޡ���\n\n");
										} else {
											UpdateJTextArea("������ʱ��\n\n");
										}
									} else {
										UpdateJTextArea("��û�С��߼����ս��ҡ���\n\n");
									}
								} else {
									xdsx = false;
									UpdateJTextArea("���ѷ���ʹ�á���������ߡ���\n\n");
								}
							}
						}.start();
					}
				} else {
					remain = 3;
					remaintime.setText("��ʣ" + remain + "��");
				}
				repaint();
				while ((remain--) > 0) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					remaintime.setText("��ʣ" + remain + "��");
				}
				xdsx = false;
				/*
				 * �ж��׶�
				 */
				UpdateJTextArea("�˳��ᢽ׶Σ�\n\n");
				usertx.setToolTipText(userh.getProperty());
				enemytx.setToolTipText(enemyh.getProperty());
				atk.setToolTipText("<html>ʹ����ͨ������<br /><br />��ʾ���������"+userh.getAtk()+"�������˺���</html>");
				action(user, enemy, i);
				while(selected == 0 && enemyop == 0) {
					Thread.sleep(100);
				}
				/*
				 * �ж�����
				 */
				new Balance().start(); // �������߳�������ʡ��������ѭ��
				repaint();
				remain = 5;
				remaintime.setText("��ʣ" + remain + "��");
				repaint();
				while ((remain--) > 0) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					remaintime.setText("��ʣ" + remain + "��");
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
				 * �����׶�
				 */
				UpdateJTextArea("�ȸﵹ��׶Σ�\n\n�����̵�Ϊ��ҷ����˹��ʣ�1���ҡ�\n\n");
				gold++;
				tip1.setText("��ǰӵ�н�ң�" + gold);
				tip4.setText("�ۼ����/�����˺���"+damage+" / "+hurt);
				hppNmpp(userh);
				hppNmpp(enemyh);
				adrNark();
			}
			if(r==limitr) {
				UpdateJTextArea("�غ����ľ���Ӣ�����������Ӣ�۵�ǰ����ֵ�ٷֱȸߵ���һ�ʤ��\n\n");
				if(userheroes.size()>enemyheroes.size()) {
					UpdateJTextArea("����������ʤ����Ϊ��"+user.getUsername()+"����ϲ����\n\n");//Ӣ������
					winlose=true;
				} else if(userheroes.size()<enemyheroes.size()) {
					UpdateJTextArea("����������ʤ����Ϊ��"+enemy.getUsername()+"����ϲ����\n\n");
				} else {
					double userhprate = userh.getHp()/userhpt.getMaximum();
					double enemyhprate = enemyh.getHp()/enemyhpt.getMaximum();
					if(userhprate>enemyhprate) {
						UpdateJTextArea("����������ʤ����Ϊ��"+user.getUsername()+"����ϲ����\n\n");//�ٷֱ�
						winlose=true;
					} else if(userhprate<enemyhprate) {
						UpdateJTextArea("����������ʤ����Ϊ��"+enemy.getUsername()+"����ϲ����\n\n");
					} else {
						if(userh.getHp()>enemyh.getHp()) {
							UpdateJTextArea("����������ʤ����Ϊ��"+user.getUsername()+"����ϲ����\n\n");//������ֵ
							winlose=true;
						} else if(userh.getHp()<enemyh.getHp()) {
							UpdateJTextArea("����������ʤ����Ϊ��"+enemy.getUsername()+"����ϲ����\n\n");
						} else {
							if(roomcreater) {
								UpdateJTextArea("����������ʤ����Ϊ��"+user.getUsername()+"����ϲ����\n\n");//���䴴����
								winlose=true;
							} else {
								UpdateJTextArea("����������ʤ����Ϊ��"+enemy.getUsername()+"����ϲ����\n\n");
							}
						}
					}
				}
			}
			remaintime.setText("����Ϊ���ϴ�������¼");
			UpdateJTextArea("�Ծ��ѽ���������Ϊ���ϴ�������¼�����Ժ󡭡�\n\n");
			if(winlose) {
				if(damage<=0) damage=1;
				if(hurt<=0) hurt=1;
				new Voice(getClass().getResourceAsStream("/bgm/win.mp3")).start();
				round.setText("�Ծ��ѽ���");
				whoact.setText("�����ǹھ�������");
				new Thread() {
					@Override
					public void run() {
						try {
							Thread.sleep(2500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						UpdateJTextArea("��ԶԷ�˵��gg\n\n");
						UpdateJTextArea(enemy.getUsername()+"��"+enemyh.getName()+"��˵��gg\n\n");
						gameover = true;
						double useror = user.getRating(),enemyor = enemy.getRating();
						int useroe = user.getElo(),enemyoe = enemy.getElo();
						getElo();//�����elo��Rating
						if(server.getIP().equals(Host.officialserver.getIP())) {
							Config.s.addHeroPlay();
							user.setAll(user.getAll()+1);//��������+1
							user.setWin(user.getWin()+1);//����ʤ��+1
							DecimalFormat df=new DecimalFormat("0.00");//���ñ���λ��
							BigDecimal bg = new BigDecimal(df.format((double)user.getWin()/user.getAll()));//ʤ�ʼ���
							double winrate = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//ʤ�ʼ���
							user.setWinrate(winrate);//ʤ�ʼ���
							Config.s.updateUser(user.getAll(),user.getWin(),user.getLose(),user.getWinrate(),user.getUid());//�����û���Ϣ
							user.setAllplay(user.getAllplay()+1);//�ܳ�+1
							user.setAllwin(user.getAllwin()+1);//��ʤ��+1
							bg = new BigDecimal(df.format((double)user.getAllwin()/user.getAllplay()));//������ʤ��
							winrate = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//������ʤ��
							user.setAllwinrate(winrate);//������ʤ��
							Config.s.updateUserAllStatus(user.getAllplay(), user.getAllwin(), user.getAlllose(), user.getAllwinrate(), user.getUid());
							if(user.getAll()==1) user.setRating(rating); 
							else user.setRating(((user.getRating()*user.getAll()-1)+rating)/user.getAll());//Rating����
							if(user.getElo()+elo<0) user.setElo(0);
							else user.setElo(user.getElo()+elo);//elo����
							if(user.getDjs()>0) user.setDjs(user.getDjs()-1);//���ö�����
							user.setRank(getRank());//���ö�λ
							Config.s.updateUser(user.getUid(),user.getRating(),user.getElo(),user.getRank(),user.getDjs());//�����û���Ϣ
							if(user.getDjs()>0) Config.s.updateUserBestStatus(user.getRank(), 0, user.getRating(), user);//���������Ϣ
							else Config.s.updateUserBestStatus(user.getRank(), user.getElo(), user.getRating(), user);
							Config.s.setWinner(user, roomid, server);//���öԾַ���ʤ��
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
				round.setText("�Ծ��ѽ���");
				whoact.setText("��Ҫ���٣��´�һ��Ӯ��");
				new Thread() {
					@Override
					public void run() {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						UpdateJTextArea("��ԶԷ�˵��gg\n\n");
						UpdateJTextArea(enemy.getUsername()+"��"+enemyh.getName()+"��˵��gg\n\n");
						gameover = true;
						String gametime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
						if(server.getIP().equals(Host.officialserver.getIP())) {
							Config.s.CreateRoomBySystem(roomid, user, gametime, "�ٷ��Ϻ�������", server.getIP());
						} else {
							Config.s.CreateRoomBySystem(roomid, user, gametime, server.getName(), server.getIP());
						}
						double useror = user.getRating(),enemyor = enemy.getRating();
						int useroe = user.getElo(),enemyoe = enemy.getElo();
						getElo();//�����elo��Rating
						if(server.getIP().equals(Host.officialserver.getIP())) {
							user.setAll(user.getAll()+1);//��������+1
							user.setLose(user.getLose()+1);//�����ܳ�+1
							DecimalFormat df=new DecimalFormat("0.00");//���ñ���λ��
							BigDecimal bg = new BigDecimal(df.format((double)user.getWin()/user.getAll()));//ʤ�ʼ���
							double winrate = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//ʤ�ʼ���
							user.setWinrate(winrate);//ʤ�ʼ���
							Config.s.updateUser(user.getAll(),user.getWin(),user.getLose(),user.getWinrate(),user.getUid());//�����û���Ϣ
							user.setAllplay(user.getAllplay()+1);//�ܳ�+1
							user.setAlllose(user.getAlllose()+1);//�ܰܳ�+1
							bg = new BigDecimal(df.format((double)user.getAllwin()/user.getAllplay()));//������ʤ��
							winrate = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//������ʤ��
							user.setAllwinrate(winrate);//������ʤ��
							Config.s.updateUserAllStatus(user.getAllplay(), user.getAllwin(), user.getAlllose(), user.getAllwinrate(), user.getUid());
							if(user.getAll()==1) user.setRating(rating); 
							else user.setRating(((user.getRating()*user.getAll()-1)+rating)/user.getAll());//Rating����
							if(user.getElo()+elo<0) user.setElo(0);
							else user.setElo(user.getElo()+elo);//elo����
							user.setRank(getRank());//���ö�λ
							Config.s.updateUser(user.getUid(),user.getRating(),user.getElo(),user.getRank(),user.getDjs());//�����û���Ϣ
							if(user.getDjs()>0) Config.s.updateUserBestStatus(user.getRank(), 0, user.getRating(), user);//���������Ϣ
							else Config.s.updateUserBestStatus(user.getRank(), user.getElo(), user.getRating(), user);
							Config.s.setWinner(enemy, roomid, server);//���öԾַ���ʤ��
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
		 * hppNmpp - �غϽ����������ظ���ħ���ظ�
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
				UpdateJTextArea("��ġ��ྻ֮�顿Ϊ��ظ���"+rehpp+"������ֵ��\n\n");
			}
			if(userh.getHp()<=userhpt.getMaximum()*0.3) {
				int rehpp = (int)Math.round(userhpt.getMaximum()*0.07);
				userh.setHp(userh.getHp()+rehpp);
				hpp=hpp+rehpp;
				UpdateJTextArea("����������ֵ�����������ֵ��30%���Ѹ���ظ�"+rehpp+"������ֵ��\n\n");
			}
			userhpt.setValue(userh.getHp());
			userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
			if(userh.ispjzm) {
				userh.ispjzm=false;
				UpdateJTextArea("���ڡ��ƾ�֮ì����Ӱ�죬���غ����޷����ħ���ظ���\n\n");
			} else if(userh.lmW) {
				userh.lmW=false;
				UpdateJTextArea("���ڡ��¹⽣����Ӱ�죬���غ����޷����ħ���ظ���\n\n");
			} else if(userh.zxyEE) {
				userh.zxyEE=false;
				UpdateJTextArea("���ڡ�����֮������Ӱ�죬���غ����޷����ħ���ظ���\n\n");
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
			tip2.setText("�ۼ�����/ħ���ظ���"+hpp+" / "+mpp);
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
				UpdateJTextArea("�Է����ྻ֮�顿Ϊ��ظ���"+rehpp+"������ֵ��\n\n");
			}
			if(enemyh.getHp()<=enemyhpt.getMaximum()*0.3) {
				int rehpp = (int)Math.round(enemyhpt.getMaximum()*0.07);
				enemyh.setHp(enemyh.getHp()+rehpp);
				UpdateJTextArea("���ڶԷ�����ֵ�����������ֵ��30%���Ѹ��Է��ظ�"+rehpp+"������ֵ��\n\n");
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
			UpdateJTextArea("���ۼ����12�������˺����ӶԷ������Ӷ���1���ң�\n\n");
			robd-=12;
		} else if(robd>24&&robd<=36) {
			gold+=2;
			UpdateJTextArea("���ۼ����24�������˺����ӶԷ������Ӷ���2���ң�\n\n");
			robd-=24;
		} else if(robd>36) {
			gold+=3;
			UpdateJTextArea("���ۼ����36�������˺����ӶԷ������Ӷ���3���ң�\n\n");
			robd-=36;
		}
		tip1.setText("��ǰӵ�н�ң�"+gold);
	}
	
	public void setrobh(int h) {
		robh+=h;
		if(robh>12&&robh<=24) {
			gold--;
			UpdateJTextArea("�Է��ۼƶ������12�������˺������������Ӷ���1���ң�\n\n");
			robh-=12;
		} else if(robh>24&&robh<=36) {
			gold-=2;
			UpdateJTextArea("�Է��ۼƶ������24�������˺������������Ӷ���2���ң�\n\n");
			robh-=24;
		} else if(robh>36) {
			gold-=3;
			UpdateJTextArea("�Է��ۼƶ������36�������˺������������Ӷ���3���ң�\n\n");
			robh-=36;
		}
		tip1.setText("��ǰӵ�н�ң�"+gold);
	}
	
	/** Ӣ����������
	 * @param h -  ������Ӣ��
	 * @return 0 or 1 -  �Ƿ�����һ��Ӣ��
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
				//�ѵ����װ��
				getUserEquip(userh.getZ().getId()+20);
				djh.add(userh.getZ());
				userh.setZ(null);
				zb1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e1.png")));
				zb1.setToolTipText("���ѡ��һ��װ����������");
			}
			if(userh.getX()!=null) {
				//�ѵ����װ��
				getUserEquip(userh.getX().getId()+20);
				djh.add(userh.getX());
				userh.setX(null);
				zb2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
				zb2.setToolTipText("���ѡ��һ��װ����������");
			}
			UpdateJTextArea("���Ӣ�ۡ�"+userh.getName()+"���Ѿ�����ɱ���Է���˻����3��ҵĽ�����\n\n");
			death++;
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(ishavenext(user)) {//�ж��Ƿ�����һ��Ӣ��
				//��ȡ��һ��Ӣ��
				userh=Config.s.GetSkillByHero(getUserHero());
				//���õ�ǰӢ��
				now.setText("��ǰ��սӢ�ۣ�"+userh.getName());
				//����Ӣ��ͷ��
				remove(usertx);
				getUserHeroIcon(userh.getId());
				add(usertx);
				//������һ��Ӣ��
				usernext.setText(getUserNext());
				//��������ֵ����ħ��ֵ��
				userhpt.setMaximum(userh.getHp());
				usermpt.setMaximum(userh.getMp());
				//������������
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
				//����ᾧ֮��
				userh.jjzl=false;
				userh.damage=0;
				userh.jhjj=0;
				gsu=null;
				//�������BUFF
				userbuff.removeAll();
				if(userh.getId()==Config.lxs.getId()) {
					userbuff.add(userh.jfzm);
					userh.jfzm.setSuperpose("����ǰ������"+userh.jfzm.getV1()+"��������+0 ����+0");
				}
				if(userh.getId()==Config.zkx.getId()) {
					userbuff.add(userh.xzjz);
					userh.xzjz.setSuperpose("����ǰ������"+userh.xzjz.getV1()+"��");
				}
				//��������Ӣ���ϳ��غϵ���Ϣ
				herotime = 0;
				herodamage = 0;
				//���select��
				select.clear();
				//��������atk��ť������select��
				atk.setToolTipText("<html>ʹ����ͨ������<br /><br />��ʾ���������"+userh.getAtk()+"�������˺���</html>");
				select.add(atk);
				//��item����select��
				select.add(item);
				//���»�ȡ����ͼ�겢����select��
				getSkillIcon(userh.getId());
				//����չʾ
				UpdateJTextArea("�����Ӣ�ۡ�"+userh.getName()+"�����ϳ���\n\n");
				repaint();
			} else {
				UpdateJTextArea("���Ѿ�û���ܹ��ֻ���Ӣ���ˡ���������ⳡ������\n\n");
				return 1;
			}
		} else if(h.equals(enemyh)) {
			enemyh.xyhED=0;
			new Voice(getClass().getResourceAsStream("/bgm/kill.mp3")).start();
			if(enemyh.getZ()!=null) {
				//�ѵ�����װ��
				getEnemyEquip(enemyh.getZ().getId()+60);
				enemyh.setZ(null);
			}
			if(enemyh.getX()!=null) {
				//�ѵ�����װ��
				getEnemyEquip(enemyh.getX().getId()+80);
				enemyh.setX(null);
			}
			UpdateJTextArea("�Է���Ӣ�ۡ�"+enemyh.getName()+"���Ѿ������ɱ��������3���ҵĽ�����\n\n");
			kill++;
			gold+=3;
			tip1.setText("��ǰӵ�н�ң�"+gold);
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(ishavenext(enemy)) {//�ж��Ƿ�����һ��Ӣ��
				//��ȡ��һ��Ӣ��
				enemyh=Config.s.GetSkillByHero(getEnemyHero());
				//���õ�ǰӢ��
				enemyhero.setText(enemyh.getName());
				//����Ӣ��ͷ��
				remove(enemytx);
				getEnemyHeroIcon(enemyh.getId());
				add(enemytx);
				//������һ��Ӣ��
				enemynext.setText(getEnemyNext());
				//��������ֵ����ħ��ֵ��
				enemyhpt.setMaximum(enemyh.getHp());
				enemympt.setMaximum(enemyh.getMp());
				//������������
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
				//�������BUFF
				enemybuff.removeAll();
				if(enemyh.getId()==Config.lxs.getId()) {
					enemybuff.add(enemyh.jfzm);
					enemyh.jfzm.setSuperpose("����ǰ������"+enemyh.jfzm.getV1()+"��������+0 ����+0");
				}
				if(enemyh.getId()==Config.zkx.getId()) {
					enemybuff.add(enemyh.xzjz);
					enemyh.xzjz.setSuperpose("����ǰ������"+enemyh.xzjz.getV1()+"��");
				}
				//����չʾ
				UpdateJTextArea("�Է��ɳ�����Ӣ�ۡ�"+enemyh.getName()+"����\n\n");
				repaint();
			} else {
				UpdateJTextArea("�Է��Ѿ�û���ܹ��ֻ���Ӣ���ˡ��ⳡ�������ǹھ�����\n\n");
				return 1;
			}
		}
		
		//������������
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
		DecimalFormat df=new DecimalFormat("0.00");//���ñ���λ��
		BigDecimal bg = new BigDecimal(df.format((double)damage / r));
        adr = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		if(kill!=0) ark = (int) r / kill;
		tip3.setText("ƽ���˺�/��ɱ�غϣ�"+adr+" / "+ark);
	}
	
	public void balanceatk(Hero h1) {
		/**
		 * BalanceAtk - ��������
		 * H1�����ж�˭���˺������
		 */
		if(h1.equals(userh)) {
			if(enemyh.ltjW>0) {
				p = new Random().nextInt(10);
				sendP(p);
				if(p<6) {
					UpdateJTextArea("��"+enemyh.getName()+"�������ˡ����֡��ļ���Ч���������˴˹�����"+"\n\n");
				} else {
					UpdateJTextArea("��"+enemyh.getName()+"�������ˡ����֡��ļ���Ч����δ�ɹ����ܴ˹�����"+"\n\n");
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
					UpdateJTextArea("��"+enemyh.getName()+"���ܵ���"+d+"�������˺���"+"\n\n");
					tip4.setText("�ۼ����/�����˺���"+damage+" / "+hurt);
					setrobd(d);
					if(userh.pjzm) {
						userh.pjzm=false;
						userh.pjzmcd=2;
						enemyh.ispjzm=true;
						UpdateJTextArea("��"+enemyh.getName()+"���ܵ��ˡ��ƾ�֮ì��������Ч����"+"\n\n");
					}
					if(userh.sjjE) {
						userh.sjjE=false;
						UpdateJTextArea("��"+userh.getName()+"�������ˡ���㽣���ļ���Ч�����ظ���3������ֵ��"+"\n\n");
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
					UpdateJTextArea("��"+enemyh.getName()+"�������ˡ�����+���ļ���Ч���������˴˹�����"+"\n\n");
				} else {
					UpdateJTextArea("��"+enemyh.getName()+"�������ˡ�����+���ļ���Ч����δ�ɹ����ܴ˹�����"+"\n\n");
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
							UpdateJTextArea("��"+enemyh.getName()+"��������һ�����������ֵ���4���˺���"+"\n\n");
							d -= 4;
						}
						else {
							UpdateJTextArea("��"+enemyh.getName()+"��������һ�����������ֵ���"+d+"���˺���"+"\n\n");
							d = 0;
						}
						enemyh.sjjR--;
						if(enemyh.sjjR!=0) {
							enemyh.gzhl.setSuperpose("��������������"+enemyh.sjjR+"��");
						} else {
							enemybuff.remove(enemyh.gzhl);
							repaint();
						}
					}
					UpdateJTextArea("��"+enemyh.getName()+"���ܵ���"+d+"�������˺���"+"\n\n");
					tip4.setText("�ۼ����/�����˺���"+damage+" / "+hurt);
					setrobd(d);
					if(userh.pjzm) {
						userh.pjzm=false;
						userh.pjzmcd=2;
						enemyh.ispjzm=true;
						UpdateJTextArea("��"+enemyh.getName()+"���ܵ��ˡ��ƾ�֮ì��������Ч����"+"\n\n");
					}
					if(userh.sjjE) {
						userh.sjjE=false;
						UpdateJTextArea("��"+userh.getName()+"�������ˡ���㽣���ļ���Ч�����ظ���3������ֵ��"+"\n\n");
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
				enemyh.bzyy.setDescribe("��Ӣ����ȫ���������˺���Ŀǰ�ѻ��ۣ�"+enemyh.zkxWH+"/20�����˺���");
				UpdateJTextArea("��"+enemyh.getName()+"���ܵ���0�������˺���"+"\n\n");
				if(enemyh.zkxWH>=20) {
					enemyh.zkxW=0;
					UpdateJTextArea("��"+enemyh.getName()+"���ġ���֮���������飡"+"\n\n");
					enemybuff.remove(enemyh.bzyy);
					repaint();
				}
				if(userh.sjjE) {
					userh.sjjE=false;
					UpdateJTextArea("��"+userh.getName()+"�������ˡ���㽣���ļ���Ч�����ظ���3������ֵ��"+"\n\n");
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
				UpdateJTextArea("��"+enemyh.getName()+"���ܵ���0�������˺���"+"\n\n");
				enemyh.sgsl.setDescribe("��Ӣ����ȫ�����κ��˺���Ŀǰ�ѻ���"+enemyh.xyhED+"���˺���");
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
						UpdateJTextArea("��"+enemyh.getName()+"��������һ�����������ֵ���4���˺���"+"\n\n");
						d -= 4;
					}
					else {
						UpdateJTextArea("��"+enemyh.getName()+"��������һ�����������ֵ���"+d+"���˺���"+"\n\n");
						d = 0;
					}
					enemyh.sjjR--;
					if(enemyh.sjjR!=0) {
						enemyh.gzhl.setSuperpose("��������������"+enemyh.sjjR+"��");
					} else {
						enemybuff.remove(enemyh.gzhl);
						repaint();
					}
				}
				UpdateJTextArea("��"+enemyh.getName()+"���ܵ���"+d+"�������˺���"+"\n\n");
				tip4.setText("�ۼ����/�����˺���"+damage+" / "+hurt);
				setrobd(d);
				if(userh.pjzm) {
					userh.pjzm=false;
					userh.pjzmcd=2;
					enemyh.ispjzm=true;
					UpdateJTextArea("��"+enemyh.getName()+"���ܵ��ˡ��ƾ�֮ì��������Ч����"+"\n\n");
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
					UpdateJTextArea("��"+userh.getName()+"�������ˡ���㽣���ļ���Ч�����ظ���3������ֵ��"+"\n\n");
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
					UpdateJTextArea("��"+userh.getName()+"�������ˡ����֡��ļ���Ч���������˴˹�����"+"\n\n");
				} else {
					UpdateJTextArea("��"+userh.getName()+"�������ˡ����֡��ļ���Ч����δ�ɹ����ܴ˹�����"+"\n\n");
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
					UpdateJTextArea("��"+userh.getName()+"���ܵ���"+d+"�������˺���"+"\n\n");
					tip4.setText("�ۼ����/�����˺���"+damage+" / "+hurt);
					setrobh(d);
					if(enemyh.pjzm) {
						enemyh.pjzm=false;
						enemyh.pjzmcd=2;
						userh.ispjzm=true;
						UpdateJTextArea("��"+userh.getName()+"���ܵ��ˡ��ƾ�֮ì��������Ч����"+"\n\n");
					}
					if(enemyh.sjjE) {
						enemyh.sjjE=false;
						UpdateJTextArea("��"+enemyh.getName()+"�������ˡ���㽣���ļ���Ч�����ظ���3������ֵ��"+"\n\n");
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
					UpdateJTextArea("��"+userh.getName()+"�������ˡ�����+���ļ���Ч���������˴˹�����"+"\n\n");
				} else {
					UpdateJTextArea("��"+userh.getName()+"�������ˡ�����+���ļ���Ч����δ�ɹ����ܴ˹�����"+"\n\n");
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
							UpdateJTextArea("��"+userh.getName()+"��������һ�����������ֵ���4���˺���"+"\n\n");
							d -= 4;
						}
						else {
							UpdateJTextArea("��"+userh.getName()+"��������һ�����������ֵ���"+d+"���˺���"+"\n\n");
							d = 0;
						}
						userh.sjjR--;
						if(userh.sjjR!=0) {
							userh.gzhl.setSuperpose("��������������"+userh.sjjR+"��");
						} else {
							userbuff.remove(userh.gzhl);
							repaint();
						}
					}
					UpdateJTextArea("��"+userh.getName()+"���ܵ���"+d+"�������˺���"+"\n\n");
					tip4.setText("�ۼ����/�����˺���"+damage+" / "+hurt);
					setrobh(d);
					if(enemyh.pjzm) {
						enemyh.pjzm=false;
						enemyh.pjzmcd=2;
						userh.ispjzm=true;
						UpdateJTextArea("��"+userh.getName()+"���ܵ��ˡ��ƾ�֮ì��������Ч����"+"\n\n");
					}
					if(enemyh.sjjE) {
						enemyh.sjjE=false;
						UpdateJTextArea("��"+enemyh.getName()+"�������ˡ���㽣���ļ���Ч�����ظ���3������ֵ��"+"\n\n");
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
				userh.bzyy.setDescribe("��Ӣ����ȫ���������˺���Ŀǰ�ѻ��ۣ�"+userh.zkxWH+"/20�����˺���");
				UpdateJTextArea("��"+userh.getName()+"���ܵ���0�������˺���"+"\n\n");
				if(userh.zkxWH>=20) {
					userh.zkxW=0;
					UpdateJTextArea("��"+userh.getName()+"���ġ���֮���������飡"+"\n\n");
					userbuff.remove(userh.bzyy);
					repaint();
				}
				if(enemyh.sjjE) {
					enemyh.sjjE=false;
					UpdateJTextArea("��"+enemyh.getName()+"�������ˡ���㽣���ļ���Ч�����ظ���3������ֵ��"+"\n\n");
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
				UpdateJTextArea("��"+userh.getName()+"���ܵ���0�������˺���"+"\n\n");
				userh.sgsl.setDescribe("��Ӣ����ȫ�����κ��˺���Ŀǰ�ѻ���"+userh.xyhED+"���˺���");
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
					skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
				}
				if(userh.sjjR>0&&d>0) {
					if(d-4>=0) {
						UpdateJTextArea("��"+userh.getName()+"��������һ�����������ֵ���4���˺���"+"\n\n");
						d -= 4;
					}
					else {
						UpdateJTextArea("��"+userh.getName()+"��������һ�����������ֵ���"+d+"���˺���"+"\n\n");
						d = 0;
					}
					userh.sjjR--;
					if(userh.sjjR!=0) {
						userh.gzhl.setSuperpose("��������������"+userh.sjjR+"��");
					} else {
						userbuff.remove(userh.gzhl);
						repaint();
					}
				}
				UpdateJTextArea("��"+userh.getName()+"���ܵ���"+d+"�������˺���"+"\n\n");
				tip4.setText("�ۼ����/�����˺���"+damage+" / "+hurt);
				setrobh(d);
				if(enemyh.pjzm) {
					enemyh.pjzm=false;
					enemyh.pjzmcd=2;
					userh.ispjzm=true;
					UpdateJTextArea("��"+userh.getName()+"���ܵ��ˡ��ƾ�֮ì��������Ч����"+"\n\n");
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
					UpdateJTextArea("��"+enemyh.getName()+"�������ˡ���㽣���ļ���Ч�����ظ���3������ֵ��"+"\n\n");
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
	 * �����˺�����
	 */
	public void balancephyical(Hero from,int d) {
		if(from.equals(userh)) {
			if(enemyh.zkxW>0) {
				enemyh.zkxWH+=d;
				enemyh.bzyy.setDescribe("��Ӣ����ȫ���������˺���Ŀǰ�ѻ��ۣ�"+enemyh.zkxWH+"/20�����˺���");
				UpdateJTextArea("��"+enemyh.getName()+"���ܵ���0�������˺���"+"\n\n");
				if(enemyh.zkxWH>=20) {
					enemyh.zkxW=0;
					UpdateJTextArea("��"+enemyh.getName()+"���ġ���֮���������飡"+"\n\n");
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
						UpdateJTextArea("��"+enemyh.getName()+"��������һ�����������ֵ���4���˺���"+"\n\n");
						d -= 4;
					}
					else {
						UpdateJTextArea("��"+enemyh.getName()+"��������һ�����������ֵ���"+d+"���˺���"+"\n\n");
						d = 0;
					}
					enemyh.sjjR--;
					if(enemyh.sjjR!=0) {
						enemyh.gzhl.setSuperpose("��������������"+enemyh.sjjR+"��");
					} else {
						enemybuff.remove(enemyh.gzhl);
						repaint();
					}
				}
				if(enemyh.xyhE>0) {
					enemyh.xyhED+=d;
					d=0;
					enemyh.sgsl.setDescribe("��Ӣ����ȫ�����κ��˺���Ŀǰ�ѻ���"+enemyh.xyhED+"���˺���");
					enemyh.sgsl.setRound(enemyh.xyhE);
					repaint();
				}
				UpdateJTextArea("��"+enemyh.getName()+"���ܵ���"+d+"�������˺���\n\n");
				setrobd(d);
				if(userh.pjzm) {
					userh.pjzm=false;
					userh.pjzmcd=2;
					enemyh.ispjzm=true;
					UpdateJTextArea("��"+enemyh.getName()+"���ܵ��ˡ��ƾ�֮ì��������Ч����"+"\n\n");
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
				tip4.setText("�ۼ����/�����˺���"+damage+" / "+hurt);
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
									UpdateJTextArea("��"+userh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
									UpdateJTextArea("��"+userh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
				userh.bzyy.setDescribe("��Ӣ����ȫ���������˺���Ŀǰ�ѻ��ۣ�"+userh.zkxWH+"/20�����˺���");
				UpdateJTextArea("��"+userh.getName()+"���ܵ���0�������˺���"+"\n\n");
				if(userh.zkxWH>=20) {
					userh.zkxW=0;
					UpdateJTextArea("��"+userh.getName()+"���ġ���֮���������飡"+"\n\n");
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
					skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
				}
				if(userh.sjjR>0&&d>0) {
					if(d-4>=0) {
						UpdateJTextArea("��"+userh.getName()+"��������һ�����������ֵ���4���˺���"+"\n\n");
						d -= 4;
					}
					else {
						UpdateJTextArea("��"+userh.getName()+"��������һ�����������ֵ���"+d+"���˺���"+"\n\n");
						d = 0;
					}
					userh.sjjR--;
					if(userh.sjjR!=0) {
						userh.gzhl.setSuperpose("��������������"+userh.sjjR+"��");
					} else {
						userbuff.remove(userh.gzhl);
						repaint();
					}
				}
				if(userh.xyhE>0) {
					userh.xyhED+=d;
					d=0;
					userh.sgsl.setDescribe("��Ӣ����ȫ�����κ��˺���Ŀǰ�ѻ���"+userh.xyhED+"���˺���");
					userh.sgsl.setRound(userh.xyhE);
					repaint();
				}
				UpdateJTextArea("��"+userh.getName()+"���ܵ���"+d+"�������˺���\n\n");
				setrobh(d);
				if(enemyh.pjzm) {
					enemyh.pjzm=false;
					enemyh.pjzmcd=2;
					userh.ispjzm=true;
					UpdateJTextArea("��"+userh.getName()+"���ܵ��ˡ��ƾ�֮ì��������Ч����"+"\n\n");
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
				tip4.setText("�ۼ����/�����˺���"+damage+" / "+hurt);
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
									UpdateJTextArea("��"+enemyh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
									UpdateJTextArea("��"+enemyh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
	 * ��ʵ�˺�����
	 */
	public void balancereal(Hero from,int d) {
		if(from.equals(userh)) {
			UpdateJTextArea("��"+enemyh.getName()+"���ܵ���"+d+"����ʵ�˺���\n\n");
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
			tip4.setText("�ۼ����/�����˺���"+damage+" / "+hurt);
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
									UpdateJTextArea("��"+userh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
									UpdateJTextArea("��"+userh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
			UpdateJTextArea("��"+userh.getName()+"���ܵ���"+d+"����ʵ�˺���\n\n");
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
			tip4.setText("�ۼ����/�����˺���"+damage+" / "+hurt);
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
									UpdateJTextArea("��"+enemyh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
									UpdateJTextArea("��"+enemyh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
	 * ħ���˺�����
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
					UpdateJTextArea("��"+enemyh.getName()+"��������һ�����������ֵ���4���˺���"+"\n\n");
					d -= 4;
				}
				else {
					UpdateJTextArea("��"+enemyh.getName()+"��������һ�����������ֵ���"+d+"���˺���"+"\n\n");
					d = 0;
				}
				enemyh.sjjR--;
				if(enemyh.sjjR!=0) {
					enemyh.gzhl.setSuperpose("��������������"+enemyh.sjjR+"��");
				} else {
					enemybuff.remove(enemyh.gzhl);
					repaint();
				}
			}
			if(enemyh.xyhE>0) {
				enemyh.xyhED+=d;
				d=0;
				enemyh.sgsl.setDescribe("��Ӣ����ȫ�����κ��˺���Ŀǰ�ѻ���"+enemyh.xyhED+"���˺���");
				enemyh.sgsl.setRound(enemyh.xyhE);
				repaint();
			}
			if(userh.hyqQ>0&&userh.jhjj==3) {
				d+=(int)Math.round(d*userh.hyqJ);
			}
			UpdateJTextArea("��"+enemyh.getName()+"���ܵ���"+d+"��ħ���˺���\n\n");
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
				UpdateJTextArea("��"+userh.getName()+"�������ˡ����ޡ��ļ���Ч����������"+enemyh.getName()+"��ħ��ֵ��"+"\n\n");
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
									UpdateJTextArea("��"+userh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
									UpdateJTextArea("��"+userh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
			tip4.setText("�ۼ����/�����˺���"+damage+" / "+hurt);
		} else if(from.equals(enemyh)) {
			if(userh.getId()==Config.xyh.getId()) {
				if(userh.xyh+d<=8) userh.xyh+=d;
				else userh.xyh=8;
				userh.getQ().setMp(userh.xyh);
				skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
			}
			if(userh.sjjR>0&&d>0) {
				if(d-4>=0) {
					UpdateJTextArea("��"+userh.getName()+"��������һ�����������ֵ���4���˺���"+"\n\n");
					d -= 4;
				}
				else {
					UpdateJTextArea("��"+userh.getName()+"��������һ�����������ֵ���"+d+"���˺���"+"\n\n");
					d = 0;
				}
				userh.sjjR--;
				if(userh.sjjR!=0) {
					userh.gzhl.setSuperpose("��������������"+userh.sjjR+"��");
				} else {
					userbuff.remove(userh.gzhl);
					repaint();
				}
			}
			if(userh.xyhE>0) {
				userh.xyhED+=d;
				d=0;
				userh.sgsl.setDescribe("��Ӣ����ȫ�����κ��˺���Ŀǰ�ѻ���"+userh.xyhED+"���˺���");
				userh.sgsl.setRound(userh.xyhE);
				repaint();
			}
			if(enemyh.hyqQ>0&&enemyh.jhjj==3) {
				d+=(int)Math.round(d*enemyh.hyqJ);
			}
			UpdateJTextArea("��"+userh.getName()+"���ܵ���"+d+"��ħ���˺���\n\n");
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
				UpdateJTextArea("��"+enemyh.getName()+"�������ˡ����ޡ��ļ���Ч����������"+userh.getName()+"��ħ��ֵ��"+"\n\n");
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
									UpdateJTextArea("��"+enemyh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
									UpdateJTextArea("��"+enemyh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
			tip4.setText("�ۼ����/�����˺���"+damage+" / "+hurt);
		}
	}
	
	/**
	 * ��������ħ���˺�����
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
					UpdateJTextArea("��"+enemyh.getName()+"��������һ�����������ֵ���4���˺���"+"\n\n");
					d -= 4;
				}
				else {
					UpdateJTextArea("��"+enemyh.getName()+"��������һ�����������ֵ���"+d+"���˺���"+"\n\n");
					d = 0;
				}
				enemyh.sjjR--;
				if(enemyh.sjjR!=0) {
					enemyh.gzhl.setSuperpose("��������������"+enemyh.sjjR+"��");
				} else {
					enemybuff.remove(enemyh.gzhl);
					repaint();
				}
			}
			if(enemyh.xyhE>0) {
				enemyh.xyhED+=d;
				d=0;
				enemyh.sgsl.setDescribe("��Ӣ����ȫ�����κ��˺���Ŀǰ�ѻ���"+enemyh.xyhED+"���˺���");
				enemyh.sgsl.setRound(enemyh.xyhE);
				repaint();
			}
			if(userh.hyqQ>0&&userh.jhjj==3) {
				d+=(int)Math.round(d*userh.hyqJ);
			}
			UpdateJTextArea("��"+enemyh.getName()+"���ܵ���"+d+"��ħ���˺���\n\n");
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
				UpdateJTextArea("��"+userh.getName()+"�������ˡ����ޡ��ļ���Ч����������"+enemyh.getName()+"��ħ��ֵ��"+"\n\n");
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
			tip4.setText("�ۼ����/�����˺���"+damage+" / "+hurt);
		} else if(from.equals(enemyh)) {
			if(userh.getId()==Config.xyh.getId()) {
				if(userh.xyh+d<=8) userh.xyh+=d;
				else userh.xyh=8;
				userh.getQ().setMp(userh.xyh);
				skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
			}
			if(userh.sjjR>0&&d>0) {
				if(d-4>=0) {
					UpdateJTextArea("��"+userh.getName()+"��������һ�����������ֵ���4���˺���"+"\n\n");
					d -= 4;
				}
				else {
					UpdateJTextArea("��"+userh.getName()+"��������һ�����������ֵ���"+d+"���˺���"+"\n\n");
					d = 0;
				}
				userh.sjjR--;
				if(userh.sjjR!=0) {
					userh.gzhl.setSuperpose("��������������"+userh.sjjR+"��");
				} else {
					userbuff.remove(userh.gzhl);
					repaint();
				}
			}
			if(userh.xyhE>0) {
				userh.xyhED+=d;
				d=0;
				userh.sgsl.setDescribe("��Ӣ����ȫ�����κ��˺���Ŀǰ�ѻ���"+userh.xyhED+"���˺���");
				userh.sgsl.setRound(userh.xyhE);
				repaint();
			}
			if(enemyh.hyqQ>0&&enemyh.jhjj==3) {
				d+=(int)Math.round(d*enemyh.hyqJ);
			}
			UpdateJTextArea("��"+userh.getName()+"���ܵ���"+d+"��ħ���˺���\n\n");
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
				UpdateJTextArea("��"+enemyh.getName()+"�������ˡ����ޡ��ļ���Ч����������"+userh.getName()+"��ħ��ֵ��"+"\n\n");
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
			tip4.setText("�ۼ����/�����˺���"+damage+" / "+hurt);
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
				
				enemyop = dis2.readInt(); // ��ȡ�Է�����ֵ
				
				dos2.writeInt(1);
				dos2.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				close2();
			}
			if(enemyop>0) {
				UpdateJTextArea("�Է��Ѿ������˸ûغϵ��ж���  \n\n");
			}
			if(enemyop==2&&enemyh.getId()==Config.yy.getId()) { // ���� ��Ӱ֮�� ��ʹ���ж�
				enemyh.setXdl(enemyh.getXdl()+999);
			}
			if(enemyop==1&&enemyh.getId()==Config.xyh.getId()) { // л�ƺ� �ྻ֮�� ��ʹ���ж�
				enemyh.setXdl(enemyh.getXdl()+9999);
			}
			if(enemyop==3&&enemyh.getId()==Config.hyq.getId()) { // �C��ȴ �����漣 ��ʹ���ж�
				if(enemyh.jhjj==2) enemyh.setXdl(enemyh.getXdl()+999);
			}
			if(enemyop==4&&enemyh.getId()==Config.hyq.getId()) { // �C��ȴ ����֮�� ��ʹ���ж�
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
						// ������ ��ֹ�ͻ�������ʷ������Ͽ����ӵ�����Ϸ��ͬ��
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
				UpdateJTextArea("���Ѿ���������Ͽ����ӡ�\n\n");
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
				UpdateJTextArea("���Ѿ���������Ͽ����ӡ�\n\n");
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
					UpdateJTextArea("���ѳɹ������������������\n\n");
					while(!gameover) {
						String str = br3.readLine();
						if(!str.equals("-disconnect")) {
							UpdateJTextArea(str+"\n\n");
						} else {
							break;
						}
					}
					UpdateJTextArea("��Ϸ���������Ѿ������췿��Ͽ����ӡ�\n\n");
				}
			} catch (IOException e) {
				UpdateJTextArea("���Ѿ���������Ͽ����ӡ�\n\n");
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
			remaintime.setText("��ʣ"+remain+"��");
			whoact.setText("��ѡ�񱾻غϵ��ж�");
			
			if (dis.readInt() == 1) {
				oos = new ObjectOutputStream(os);
				oos.writeObject(user);
				oos.flush();
				oos.writeObject(enemy);
				oos.flush();
				
				selected=1;
				
				if(!userh.isIslimte()) {
					selected=0;
					UpdateJTextArea("���ڡ��ж����ޡ���δʹ�ø�������ߣ�����ж��׶��ѱ�������\n\n");
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
					whoact.setText("���ѷ����ûغ��ж�");
				}
				
				if(!userh.isIsgone()) {
					selected=0;
					UpdateJTextArea("���ڡ���ȫ�ж����ܡ�������ж��׶��ѱ�������\n\n");
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
					whoact.setText("���ѷ����ûغ��ж�");
				}
				
				while (remain > 0) {
					if (selected == 0) {
						if (enemyop == 0) {
							whoact.setText("�ȴ��Է��ж�");
						} else {
							remain = 0;
							whoact.setText("�ж����");
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
						remaintime.setText("��ʣ" + remain + "��");
					}
				}
				if (selected != 0) {
					selected = 0;
					whoact.setText("���ѷ����ûغ��ж�");
					dos.writeInt(-1);
					dos.flush();
				}
			}
			
		} catch (IOException e) {
			whoact.setText("���ƺ��Ѿ��Ͽ�����");
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
			tb = new TitledBorder(new LineBorder(new Color(222, 184, 135), 5), "���ߺ�", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(188, 143, 143));
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
				JOptionPane.showMessageDialog(null, "δָ���κε���");
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
			tb.setTitle("ѡ��һ�������Ա�ʹ����");
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
	
	/** װ�������ࣨ���ߺУ�
	 * @author �޶���
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
			tb = new TitledBorder(new LineBorder(new Color(222, 184, 135), 5), "���ߺ�", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(188, 143, 143));
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
						JOptionPane.showMessageDialog(null, "�����ж�ʱ���ڣ��޷�������");
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
						UpdateJTextArea("�����ж�ʱ���ڣ�����ȡ��������\n\n");
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
				JOptionPane.showMessageDialog(null, "δָ���κ�װ��");
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
					zb.setToolTipText("���ѡ��һ��װ����������");
				}
			} else if(zb.equals(zb2)) {
				if(userh.getX()!=null) {
					djh.add(userh.getX());
					setE(userh.getX().getId(),4);
					getUserEquip(userh.getX().getId()+20);
					userh.setX(null);
					zb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
					zb.setToolTipText("���ѡ��һ��װ����������");
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
				tb.setTitle("[1]��̴������ȡ������");
			} else if(zb.equals(zb2)) {
				tb.setTitle("[2]��̴������ȡ������");
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
			return "��һ��Ӣ�ۣ�"+userheroes.get(0).getName();
		} else {
			return "���޿��õ���һ��Ӣ��";
		}
	}
	
	public String getEnemyNext() {
		if(enemyheroes.size()>0) {
			return "�Է���һ��Ӣ�ۣ�"+enemyheroes.get(0).getName();
		} else {
			return "�Է�������һ��Ӣ��";
		}
	}
	
	/** �ж�Ӣ�ۼ��ܲ�ʵ��Ч����
	 * @param from - �����жϼ���ʹ����
	 * @param s - ʹ�õļ���
	 */
	public void getSkillEffect(Skill s,Hero from) {
		switch(s.getId()) {
			case 1:{//����Q ����֮��
				if(from.equals(userh)) {
					if(userh.jhjj==2) {
						UpdateJTextArea("��"+userh.getName()+"���ԡ�"+enemyh.getName()+"����������գ�\n\n");
						enemyh.yyQ+=3;
						enemybuff.remove(enemyh.lrzj);
						enemybuff.add(enemyh.lrzj);
						enemyh.lrzj.setDescribe("��Ӣ�ۻ�����ܵ��������յ�"+(5+userh.yyE)+"��ħ���˺���");
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
						UpdateJTextArea("��"+userh.getName()+"����ȡ����������Ϊ��"+p+"\n\n");
						if(p<=hp) {
							UpdateJTextArea("��"+userh.getName()+"���ԡ�"+enemyh.getName()+"����������գ�\n\n");
							enemyh.yyQ+=3;
							enemybuff.remove(enemyh.lrzj);
							enemybuff.add(enemyh.lrzj);
							enemyh.lrzj.setDescribe("��Ӣ�ۻ�����ܵ��������յ�"+(5+userh.yyE)+"��ħ���˺���");
							enemyh.lrzj.setRound(enemyh.yyQ);
							repaint();
						} else {
							UpdateJTextArea("���ʲôҲû�з�����\n\n");
						}
					}
				} else if(from.equals(enemyh)) {
					if(enemyh.jhjj==2) {
						UpdateJTextArea("��"+enemyh.getName()+"���ԡ�"+userh.getName()+"����������գ�\n\n");
						userh.yyQ+=3;
						userbuff.remove(userh.lrzj);
						userbuff.add(userh.lrzj);
						userh.lrzj.setDescribe("��Ӣ�ۻ�����ܵ��������յ�"+(5+enemyh.yyE)+"��ħ���˺���");
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
						UpdateJTextArea("��"+enemyh.getName()+"����ȡ����������Ϊ��"+p+"\n\n");
						if(p<=hp) {
							UpdateJTextArea("��"+enemyh.getName()+"���ԡ�"+userh.getName()+"����������գ�\n\n");
							userh.yyQ+=3;
							userbuff.remove(userh.lrzj);
							userbuff.add(userh.lrzj);
							userh.lrzj.setDescribe("��Ӣ�ۻ�����ܵ��������յ�"+(5+enemyh.yyE)+"��ħ���˺���");
							userh.lrzj.setRound(userh.yyQ);
							repaint();
						} else {
							UpdateJTextArea("���ʲôҲû�з�����\n\n");
						}
					}
				}
				p=0;
				break;
			}
			case 2:{//����W ��Ӱ֮��
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
					UpdateJTextArea("��"+userh.getName()+"����ȡ����������Ϊ��"+p+"\n\n");
					if(p<=pd) {
						int d = (10+userh.yyE) - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
						if(d<0) d=0;
						balanceskill(userh, d);
					} else {
						UpdateJTextArea("���ʲôҲû�з�����\n\n");
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
					UpdateJTextArea("��"+enemyh.getName()+"����ȡ����������Ϊ��"+p+"\n\n");
					if(p<=pd) {
						int d = (10+enemyh.yyE) - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
						if(d<0) d=0;
						balanceskill(enemyh, d);
					} else {
						UpdateJTextArea("���ʲôҲû�з�����\n\n");
					}
					enemyh.setXdl(enemyh.getXdl()-999);
				}
				p=0;
				break;
			}
			case 3:{//����E ��ɱ֮��
				if(from.equals(userh)) {
					p = new Random().nextInt(10);
					sendP(p);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					UpdateJTextArea("��"+userh.getName()+"����ȡ����������Ϊ��"+p+"\n\n");
					int pd = enemyh.getHp()+userh.getMp();
					if(pd<100) pd = pd%10;
					else if(pd<1000) pd = pd%100;
					if(p<=pd) {
						UpdateJTextArea("��"+userh.getName()+"�������ħ���˺��ӳɵ�Ч����\n\n");
						if(userh.jhjj==3) userh.yyE+=6;
						else userh.yyE+=3;
						userh.yyER+=4;
						if(userh.jhjj==2) {
							userh.getQ().setdescribe("<html>�ԶԷ�������գ�ÿ�غ����"+(5+userh.yyE)+"��ħ���˺�������3�غϡ���Ч��Ӱ��Ļغ������Ե��ӡ�<br/><br/>�Ѽ���ᾧ֮����</html>");
							skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
						} else {
							userh.getQ().setdescribe("��0��9�г�ȡ�������֡�<br />��������С�ڻ���ڶԷ���ǰ����ֵ�ĸ�λ������ô�ԶԷ�������գ�ÿ�غ����"+(5+userh.yyE)+"��ħ���˺�������3�غϡ�<br />��Ч��Ӱ��Ļغ������Ե��ӡ�<br/><br />������ᾧ֮����100%��Ч��");
							skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
						}
						userh.getW().setdescribe("��0��9�г�ȡ�������֡�<br />��������С�ڻ���ڶԷ��ж������㹥����֮�͵ĸ�λ������ô�ԶԷ����"+(10+userh.yyE)+"��ħ���˺����ü��������ж�����");
						skill2.setToolTipText("<html>��W��"+userh.getW().getSkill()+"</html>");
						userbuff.remove(userh.tszf);
						userbuff.add(userh.tszf);
						userh.tszf.setDescribe("��ɱ֮���Ϊ��Ӣ���ṩ"+userh.yyE+"��ħ���˺��ӳɡ�");
						userh.tszf.setRound(userh.yyER);
						repaint();
					} else {
						UpdateJTextArea("���ʲôҲû�з�����\n\n");
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
					UpdateJTextArea("��"+enemyh.getName()+"����ȡ����������Ϊ��"+p+"\n\n");
					int pd = userh.getHp()+enemyh.getMp();
					if(pd<100) pd = pd%10;
					else if(pd<1000) pd = pd%100;
					if(p<=pd) {
						UpdateJTextArea("��"+enemyh.getName()+"�������ħ���˺��ӳɵ�Ч����\n\n");
						if(enemyh.jhjj==3) enemyh.yyE+=6;
						else enemyh.yyE+=3;
						enemyh.yyER+=4;
						enemybuff.remove(enemyh.tszf);
						enemybuff.add(enemyh.tszf);
						enemyh.tszf.setDescribe("��ɱ֮���Ϊ��Ӣ���ṩ"+enemyh.yyE+"��ħ���˺��ӳɡ�");
						enemyh.tszf.setRound(enemyh.yyER);
						repaint();
					} else {
						UpdateJTextArea("���ʲôҲû�з�����\n\n");
					}
				}
				p=0;
				break;
			}
			case 4:{//������Q ����ͻ��
				if(from.equals(userh)) {
					UpdateJTextArea("��"+userh.getName()+"��������1��ħ�����ޣ�\n\n");
					usermpt.setMaximum(usermpt.getMaximum()+1);
					int mp = userh.getMp();
					userh.setMp(mp+1);
					usermpt.setValue(userh.getMp());
					usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
					userh.getW().setMp(userh.getW().getMp()+1);
					skill2.setToolTipText("<html>��W��"+userh.getW().getSkill()+"</html>");
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("��"+enemyh.getName()+"��������1��ħ�����ޣ�\n\n");
					enemympt.setMaximum(enemympt.getMaximum()+1);
					int mp = enemyh.getMp();
					enemyh.setMp(mp+1);
					enemympt.setValue(enemyh.getMp());
					enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
					enemyh.getW().setMp(enemyh.getW().getMp()+1);
				}
				break;
			}
			case 5:{//������W �������
				if(from.equals(userh)) {
					UpdateJTextArea("��"+userh.getName()+"��������2�㹥������1�㻤�ף�\n\n");
					userh.setAtk(userh.getAtk()+2);
					userh.setDef(userh.getDef()+1);
					userh.jfzm.setV1(userh.jfzm.getV1()+1);
					userh.jfzm.setSuperpose("����ǰ������"+userh.jfzm.getV1()+"��������+"+(userh.jfzm.getV1()*2)+" ����+"+userh.jfzm.getV1());
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
										userh.jfzm.setSuperpose("����ǰ������"+userh.jfzm.getV1()+"��������+"+(userh.jfzm.getV1()*2)+" ����+"+userh.jfzm.getV1());
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
					UpdateJTextArea("��"+enemyh.getName()+"��������2�㹥������1�㻤�ף�\n\n");
					enemyh.setAtk(enemyh.getAtk()+2);
					enemyh.setDef(enemyh.getDef()+1);
					enemyh.jfzm.setV1(enemyh.jfzm.getV1()+1);
					enemyh.jfzm.setSuperpose("����ǰ������"+enemyh.jfzm.getV1()+"��������+"+(enemyh.jfzm.getV1()*2)+" ����+"+enemyh.jfzm.getV1());
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
										enemyh.jfzm.setSuperpose("����ǰ������"+enemyh.jfzm.getV1()+"��������+"+(enemyh.jfzm.getV1()*2)+" ����+"+enemyh.jfzm.getV1());
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
			case 6:{//������E ħ��ŭ
				if(from.equals(userh)) {
					p = new Random().nextInt(10);
					sendP(p);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(p<userh.lxsE) {
						UpdateJTextArea("��"+enemyh.getName()+"������ɱ�ˣ�����"+userh.getName()+"���ظ���ȫ��ħ��ֵ��\n\n");
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
						UpdateJTextArea("���ʲôҲû�з�����\n\n");
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
						UpdateJTextArea("��"+userh.getName()+"������ɱ�ˣ�����"+enemyh.getName()+"���ظ���ȫ��ħ��ֵ��\n\n");
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
						UpdateJTextArea("���ʲôҲû�з�����\n\n");
						if(enemyh.lxsE+1<10) enemyh.lxsE++;
						else enemyh.lxsE=10;
					}
				}
				p=0;
				break;
			}
			case 7:{//��ʥŵQ ���ǳ��
				if(from.equals(userh)) {
					int mk = enemyh.getAdf();
					int d = 7 - (int)Math.round((1-userh.getApp())*mk);
					if(d<0) d=0;
					balanceskill(userh, d);
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;
							int whenstop=r+2;//���ü���ʧЧ�Ļغ�
							int def=0;
							int id=0;
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(r==whenstart) {
									id=enemyh.getId();
									if(userh.getId()==Config.ysn.getId()) { // ֻ��Ӣ������ʥŵʱ�Ų���
										def = enemyh.getDef();
										if(def>3) def=3;
										else if(def<0) def=0;
										UpdateJTextArea("��"+userh.getName()+"��͵ȡ�ˡ�"+enemyh.getName()+"����"+def+"�������ף�\n\n");
										userh.setDef(userh.getDef()+def);
										usertx.setToolTipText(userh.getProperty());
										enemyh.setDef(enemyh.getDef()-def);
										enemytx.setToolTipText(enemyh.getProperty());
										
										userbuff.remove(userh.userxxcc);
										enemybuff.remove(enemyh.enemyxxcc);
										userbuff.add(userh.userxxcc);
										userh.userxxcc.setDescribe("��Ӣ���Ѿ���ȡ�˶Է�"+def+"�������ס�");
										userh.userxxcc.setRound(1);
										repaint();
										enemybuff.add(enemyh.enemyxxcc);
										enemyh.enemyxxcc.setDescribe("��Ӣ���Ѿ����Է���ȡ��"+def+"�������ס�");
										enemyh.enemyxxcc.setRound(1);
										repaint();
									}
									break;
								}
								try {
									sleep(1000);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(r==whenstop) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
									if(userh.getId()==Config.ysn.getId()) { // ֻ��Ӣ������ʥŵʱ�Ų���
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
									sleep(1000);//������ʱ1������ж�
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
							int whenstop=r+2;//���ü���ʧЧ�Ļغ�
							int def=0;
							int id=0;
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(r==whenstart) {
									id=userh.getId();
									if(enemyh.getId()==Config.ysn.getId()) { // ֻ��Ӣ������ʥŵʱ�Ų���
										def = userh.getDef();
										if(def>3) def=3;
										else if(def<0) def=0;
										UpdateJTextArea("��"+enemyh.getName()+"��͵ȡ�ˡ�"+userh.getName()+"����"+def+"�������ף�\n\n");
										userh.setDef(userh.getDef()-def);
										usertx.setToolTipText(userh.getProperty());
										enemyh.setDef(enemyh.getDef()+def);
										enemytx.setToolTipText(enemyh.getProperty());
										
										userbuff.remove(userh.enemyxxcc);
										enemybuff.remove(enemyh.userxxcc);
										userbuff.add(userh.enemyxxcc);
										userh.enemyxxcc.setDescribe("��Ӣ���Ѿ����Է���ȡ��"+def+"�������ס�");
										userh.enemyxxcc.setRound(1);
										repaint();
										enemybuff.add(enemyh.userxxcc);
										enemyh.userxxcc.setDescribe("��Ӣ���Ѿ���ȡ�˶Է�"+def+"�������ס�");
										enemyh.userxxcc.setRound(1);
										repaint();
									}
									break;
								}
								try {
									sleep(1000);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(r==whenstop) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
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
									sleep(1000);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				break;
			}
			case 8:{//��ʥŵW �ǳ�����
				if(from.equals(userh)) {
					UpdateJTextArea("��"+enemyh.getName()+"����������2��ħ��������\n\n");
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
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(enemyh.getId()==id) {
									if(enemyh.ysnW==0) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
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
									sleep(1000);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
					if(userh.ysnQ) {
						userh.ysnQ=false;
						sendP(1);
						UpdateJTextArea("��"+userh.getName()+"��׷��ʹ���ˡ����ǳ�̡���\n\n");
						getSkillEffect(userh.getQ(),userh);
						userh.setMp(userh.getMp()-userh.getQ().getMp());
						usermpt.setValue(userh.getMp());
						usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
					} else {
						sendP(0);
					}
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("��"+userh.getName()+"����������2��ħ��������\n\n");
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
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(userh.getId()==id) {
									if(userh.ysnW==0) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
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
									sleep(1000);//������ʱ1������ж�
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
						UpdateJTextArea("��"+enemyh.getName()+"��׷��ʹ���ˡ����ǳ�̡���\n\n");
						getSkillEffect(enemyh.getQ(),enemyh);
						enemyh.setMp(enemyh.getMp()-enemyh.getQ().getMp());
						enemympt.setValue(enemyh.getMp());
						enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
					}
				}
				p=0;
				break;
			}
			case 9:{//�ŷ�Q һ��ʮ����
				if(from.equals(userh)) {//�ж�ʹ��������ҵ�Ӣ��
					int atk = userh.getAtk();//��ȡ��Ӣ�۵Ĺ�����
					int mk = enemyh.getAdf();//��ȡ�Է���ħ��
					int d = atk - (int)Math.round((1-userh.getApp())*mk);
					if(d<0) d=0;
					balanceskill(userh, d);
				} else if(from.equals(enemyh)) {//�ж�ʹ�����ǶԷ�Ӣ��
					int atk = enemyh.getAtk();//��ȡ�Է�Ӣ�۵Ĺ�����
					int mk = userh.getAdf();//��ȡ�ҵ�ħ��
					int d = atk - (int)Math.round((1-enemyh.getApp())*mk);
					if(d<0) d=0;
					balanceskill(enemyh, d);
				}
				break;
			}
			case 10:{//�ŷ�W ��֮���
				if(from.equals(userh)) {
					UpdateJTextArea("��"+userh.getName()+"���ԡ�"+enemyh.getName()+"���������һ�غϡ���ȫ�ж����ܡ���\n\n");
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
							int whenstart=r+1;//���ü�����Ч�Ļغ�
							int whenstop=r+2;//���ü���ʧЧ�Ļغ�
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(r==whenstart) {
									if(enemyh.yyzs) {
										eyyzs--;
										UpdateJTextArea("��"+enemyh.getName()+"�������ˡ�ҹ��֮������Ч���ֵ���һ�Ρ���ȫ�ж����ܡ���\n\n");
									} else if(enemyh.fs) {
										UpdateJTextArea("��" + enemyh.getName() + "�������ˡ����ա���Ч���ֵ���һ�Ρ���ȫ�ж����ܡ���\n\n");
									} else {
										enemyh.setIsgone(false);
										if(enemyh.xyhE>0) {
											enemyh.xyhE=0;
											if(enemyh.xyhED>0) {
												UpdateJTextArea("��"+enemyh.getName()+"���ͷ������ġ�ʱ��ɳ©����\n\n");
												int d = (int)Math.round(enemyh.xyhED*0.8 - ((1-enemyh.getApp())*userh.getAdf()));
												if(d<0) d=0;
												balanceskill(enemyh, d);
												enemyh.xyhED=0;
											} else {
												UpdateJTextArea("��"+enemyh.getName()+"���ġ�ʱ��ɳ©�������飡\n\n");
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
									sleep(500);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(rend==whenstop) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
									enemyh.setIsgone(true);
									enemybuff.remove(enemyh.fzjj1);
									repaint();
									break;
								}
								try {
									sleep(500);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
					if(p<7) {
						UpdateJTextArea("��"+enemyh.getName()+"�����ڡ���ȫ�ж����ܡ�����һ���غϡ��ж����ޡ���\n\n");
						userh.zfW=true;
					}
				} else if(from.equals(enemyh)) {
					try {
						p = dis5.readInt();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					UpdateJTextArea("��"+enemyh.getName()+"���ԡ�"+userh.getName()+"���������һ�غϡ���ȫ�ж����ܡ���\n\n");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;//���ü�����Ч�Ļغ�
							int whenstop=r+2;//���ü���ʧЧ�Ļغ�
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(r==whenstart) {
									if(userh.yyzs) {
										uyyzs--;
										UpdateJTextArea("��"+userh.getName()+"�������ˡ�ҹ��֮������Ч���ֵ���һ�Ρ���ȫ�ж����ܡ���\n\n");
										if(uyyzs%3==0) {
											if(userh.getZ().getName().equals("ҹ��֮��")) {
												setE(userh.getZ().getId(),3);
												getUserEquip(userh.getZ().getId()+20);
												userh.setZ(null);
												zb1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e1.png")));
												zb1.setToolTipText("���ѡ��һ��װ����������");
											} else if(userh.getX().getName().equals("ҹ��֮��")) {
												setE(userh.getX().getId(),4);
												getUserEquip(userh.getX().getId()+20);
												userh.setX(null);
												zb2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
												zb2.setToolTipText("���ѡ��һ��װ����������");
											}
										}
									} else if(userh.fs) {
										UpdateJTextArea("��" + userh.getName() + "�������ˡ����ա���Ч���ֵ���һ�Ρ���ȫ�ж����ܡ���\n\n");
									} else {
										userh.setIsgone(false);
										
										if(userh.xyhE>0) {
											userh.xyhE=0;
											if(userh.xyhED>0) {
												UpdateJTextArea("��"+userh.getName()+"���Ѿ��ͷ��ˡ�ʱ��ɳ©����\n\n");
												int d = (int)Math.round(userh.xyhED*0.8 - ((1-userh.getApp())*enemyh.getAdf()));
												if(d<0) d=0;
												balanceskill(userh, d);
												userh.xyhED=0;
											} else {
												UpdateJTextArea("��"+userh.getName()+"���ġ�ʱ��ɳ©�������飡\n\n");
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
									sleep(500);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(rend==whenstop) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
									userh.setIsgone(true);
									userbuff.remove(userh.fzjj1);
									repaint();
									break;
								}
								try {
									sleep(500);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
					if(p<7) {
						UpdateJTextArea("��"+userh.getName()+"�����ڡ���ȫ�ж����ܡ�����һ���غϡ��ж����ޡ���\n\n");
						enemyh.zfW=true;
					}
				}
				break;
			}
			case 11:{//�ŷ�E ����֮ն
				if(from.equals(userh)) {
					int enemyhp = enemyhpt.getMaximum() - enemyh.getHp(); // ��ȡ�Է�Ӣ������ʧ����ֵ
					int d = (int)Math.round(enemyhp * 0.8); // ��������ʧ����ֵ��80%Ϊ�˺�ֵ
					int mk = enemyh.getAdf(); // ��ȡ�Է���ħ��
					d = d - (int)Math.round((1-userh.getApp())*mk); // ��ʽ���˺�ֵ - (1-�ҵ�ħ����͸)*����ħ��
					if(d<0) d=0;
					balanceskill(userh, d);
				} else if(from.equals(enemyh)) {
					int userhp = userhpt.getMaximum() - userh.getHp(); // ��ȡ�ҵ�Ӣ������ʧ����ֵ
					int d = (int)Math.round(userhp * 0.8); // ��������ʧ����ֵ��80%Ϊ�˺�ֵ
					int mk = userh.getAdf(); // ��ȡ�ҵ�ħ��
					d = d - (int)Math.round((1-enemyh.getApp())*mk); // ��ʽ���˺�ֵ - (1-�����ħ����͸)*�ҵ�ħ��
					if(d<0) d=0;
					balanceskill(enemyh, d);
				}
				break;
			}
			case 12:{//�����Q ��ŭ
				if(from.equals(userh)) {//ʹ�������ҷ�
					UpdateJTextArea("��"+userh.getName()+"��������4�㹥������ʹ����һ�Ρ���ͨ��������\n\n");//��Ч����ʾ�ڹ�����
					userh.setAtk(userh.getAtk()+4);//����������4 ������Ч
					balanceatk(userh);//���ý��㹥�����ķ���
					userh.setAtk(userh.getAtk()-4);//�ָ�Ϊԭ���Ĺ����� Ҳ���Ǽ�ȥ4
				} else if(from.equals(enemyh)) {//ʹ�����ǶԷ�
					UpdateJTextArea("��"+enemyh.getName()+"��������4�㹥������ʹ����һ�Ρ���ͨ��������\n\n");//��Ч����ʾ�ڹ�����
					enemyh.setAtk(enemyh.getAtk()+4);//����������4 ������Ч
					balanceatk(enemyh);//���ý��㹥�����ķ���
					enemyh.setAtk(enemyh.getAtk()-4);//�ָ�Ϊԭ���Ĺ����� Ҳ���Ǽ�ȥ4
				}
				break;
			}
			case 13:{//�����W ����
				if(from.equals(userh)) {//ʹ��������
					UpdateJTextArea("��"+userh.getName()+"�������60%������ͨ�����ĸ��ʡ�\n\n");
					userh.ltjW=3;
					userbuff.remove(userh.sx);
					userbuff.add(userh.sx);
					userh.sx.setRound(userh.ltjW);
					repaint();
				} else if(from.equals(enemyh)) {//ʹ�����ǶԷ�
					UpdateJTextArea("��"+enemyh.getName()+"�������60%������ͨ�����ĸ��ʡ�\n\n");
					enemyh.ltjW=3;
					enemybuff.remove(enemyh.sx);
					enemybuff.add(enemyh.sx);
					enemyh.sx.setRound(enemyh.ltjW);
					repaint();
				}
				break;
			}
			case 14:{//�����E �Ϲǽ�
				if(from.equals(userh)) {
					if(enemyh.zkxW>0) {
						int d = userh.getAtk()-(int)Math.round(enemyh.getDef()*(1-userh.getAdp()));
						if(d<0) d=0;
						if(userh.yjg) d=(int)Math.round(d*1.36);
						if(enemyh.jrz) d=(int)Math.round(d*0.75);
						d=(int)Math.round(d*(1-enemyh.getDefrate()));
						if(userh.lmQ) d=(int)Math.round(d*0.5);
						enemyh.zkxWH+=d;
						enemyh.bzyy.setDescribe("��Ӣ����ȫ���������˺���Ŀǰ�ѻ��ۣ�"+enemyh.zkxWH+"/20�����˺���");
						UpdateJTextArea("��"+enemyh.getName()+"���ܵ���0�������˺���"+"\n\n");
						if(enemyh.zkxWH>=20) {
							enemyh.zkxW=0;
							UpdateJTextArea("��"+enemyh.getName()+"���ġ���֮���������飡"+"\n\n");
							enemybuff.remove(enemyh.bzyy);
							repaint();
						}
						if(userh.sjjE) {
							userh.sjjE=false;
							UpdateJTextArea("��"+userh.getName()+"�������ˡ���㽣���ļ���Ч�����ظ���3������ֵ��"+"\n\n");
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
								UpdateJTextArea("��"+enemyh.getName()+"��������һ�����������ֵ���4���˺���"+"\n\n");
								d -= 4;
							}
							else {
								UpdateJTextArea("��"+enemyh.getName()+"��������һ�����������ֵ���"+d+"���˺���"+"\n\n");
								d = 0;
							}
							enemyh.sjjR--;
							if(enemyh.sjjR!=0) {
								enemyh.gzhl.setSuperpose("��������������"+enemyh.sjjR+"��");
							} else {
								enemybuff.remove(enemyh.gzhl);
								repaint();
							}
						}
						if(enemyh.xyhE>0) {
							enemyh.xyhED+=d;
							d=0;
							enemyh.sgsl.setDescribe("��Ӣ����ȫ�����κ��˺���Ŀǰ�ѻ���"+enemyh.xyhED+"���˺���");
							enemyh.sgsl.setRound(enemyh.xyhE);
							repaint();
						}
						UpdateJTextArea("��"+enemyh.getName()+"���ܵ���"+d+"�������˺���\n\n");
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
												UpdateJTextArea("��"+userh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
												UpdateJTextArea("��"+userh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
						userh.bzyy.setDescribe("��Ӣ����ȫ���������˺���Ŀǰ�ѻ��ۣ�"+userh.zkxWH+"/20�����˺���");
						UpdateJTextArea("��"+userh.getName()+"���ܵ���0�������˺���"+"\n\n");
						if(userh.zkxWH>=20) {
							userh.zkxW=0;
							UpdateJTextArea("��"+userh.getName()+"���ġ���֮���������飡"+"\n\n");
							userbuff.remove(userh.bzyy);
							repaint();
						}
						if(enemyh.sjjE) {
							enemyh.sjjE=false;
							UpdateJTextArea("��"+enemyh.getName()+"�������ˡ���㽣���ļ���Ч�����ظ���3������ֵ��"+"\n\n");
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
												UpdateJTextArea("��"+enemyh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
												UpdateJTextArea("��"+enemyh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
							skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
						}
						if(userh.sjjR>0&&d>0) {
							if(d-4>=0) {
								UpdateJTextArea("��"+userh.getName()+"��������һ�����������ֵ���4���˺���"+"\n\n");
								d -= 4;
							}
							else {
								UpdateJTextArea("��"+userh.getName()+"��������һ�����������ֵ���"+d+"���˺���"+"\n\n");
								d = 0;
							}
							userh.sjjR--;
							if(userh.sjjR!=0) {
								userh.gzhl.setSuperpose("��������������"+userh.sjjR+"��");
							} else {
								userbuff.remove(userh.gzhl);
								repaint();
							}
						}
						if(userh.xyhE>0) {
							userh.xyhED+=d;
							d=0;
							userh.sgsl.setDescribe("��Ӣ����ȫ�����κ��˺���Ŀǰ�ѻ���"+userh.xyhED+"���˺���");
							userh.sgsl.setRound(userh.xyhE);
							repaint();
						}
						UpdateJTextArea("��"+userh.getName()+"���ܵ���"+d+"�������˺���\n\n");
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
												UpdateJTextArea("��"+enemyh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
												UpdateJTextArea("��"+enemyh.getName()+"�������ˡ��������ȡ��ļ���Ч����\n\n");
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
			case 15:{//�C��ȴQ ����Ϊ��
				if(from.equals(userh)) {
					if(userh.jhjj==3) {
						UpdateJTextArea("��"+userh.getName()+"���ļ����˺�������30%��\n\n");
						userh.hyqQ+=3;
						userh.hyqJ+=0.3;
						userbuff.remove(userh.xrwz);
						userbuff.add(userh.xrwz);
						userh.xrwz.setRound(userh.hyqQ);
						userh.xrwz.setDescribe("��Ӣ�۵ļ����˺�������"+userh.hyqJ*100+"%��");
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
										sleep(1000);//������ʱ1������ж�
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}.start();
					} else {
						UpdateJTextArea("��"+userh.getName()+"����ħ����͸������30%��\n\n");
						double oldapp = userh.getApp();//��ȡħ����͸
						if(userh.hyqQ==0) userh.setApp(oldapp+0.3);//����ħ����͸ +30%
						userh.hyqQ+=3;
						userbuff.remove(userh.xrwz);
						userbuff.add(userh.xrwz);
						userh.xrwz.setRound(userh.hyqQ);
						userh.xrwz.setDescribe("��Ӣ�۵�ħ����͸������30%��");
						repaint();
						new Thread() {
							@Override
							public void run() {
								while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
									if(userh.getId()==Config.hyq.getId()) {
										if(userh.hyqQ==0) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
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
										sleep(1000);//������ʱ1������ж�
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}.start();
					}
				} else if(from.equals(enemyh)) {
					if(enemyh.jhjj==3) {
						UpdateJTextArea("��"+enemyh.getName()+"���ļ����˺�������30%��\n\n");
						enemyh.hyqQ+=3;
						enemyh.hyqJ+=0.3;
						enemybuff.remove(enemyh.xrwz);
						enemybuff.add(enemyh.xrwz);
						enemyh.xrwz.setRound(enemyh.hyqQ);
						enemyh.xrwz.setDescribe("��Ӣ�۵ļ����˺�������"+enemyh.hyqJ*100+"%��");
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
										sleep(1000);//������ʱ1������ж�
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}.start();
					} else {
						UpdateJTextArea("��"+enemyh.getName()+"����ħ����͸������30%��\n\n");
						double oldapp = enemyh.getApp();
						if(enemyh.hyqQ==0) enemyh.setApp(oldapp+0.3);
						enemyh.hyqQ+=3;
						enemybuff.remove(enemyh.xrwz);
						enemybuff.add(enemyh.xrwz);
						enemyh.xrwz.setRound(enemyh.hyqQ);
						enemyh.xrwz.setDescribe("��Ӣ�۵�ħ����͸������30%��");
						repaint();
						new Thread() {
							@Override
							public void run() {
								while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
									if(enemyh.getId()==Config.hyq.getId()) {
										if(enemyh.hyqQ==0) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
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
										sleep(1000);//������ʱ1������ж�
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
			case 16:{//�C��ȴW ǿ������
				if(from.equals(userh)) {
					UpdateJTextArea("��"+enemyh.getName()+"���������ױ�������4�㣡\n\n");
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
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(enemyh.getId()==id) {
									if(enemyh.hyqW==0) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
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
									sleep(1000);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("��"+userh.getName()+"���������ױ�������4�㣡\n\n");
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
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(userh.getId()==id) {
									if(userh.hyqW==0) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
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
									sleep(1000);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				break;
			}
			case 17:{//�C��ȴE �����漣
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
			case 18:{//�C��ȴR ����֮��
				if(from.equals(userh)) {
					int d=0;
					if(userh.jhjj==1) d = 14 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());//�ɹ�ʽת��Ϊħ���˺�
					else d = 10 - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
					if(d<0) d=0;
					UpdateJTextArea("��"+userh.getName()+"�����������غϻ��4�㹥������\n\n");
					balanceskill(userh, d);
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;//���ü�����Ч�Ļغ�
							int whenstop=r+3;//���ü���ʧЧ�Ļغ�
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
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
									sleep(1000);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(r==whenstop) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
									if(userh.getId()==Config.hyq.getId()) {
										userh.setAtk(userh.getAtk()-4);
									}
									usertx.setToolTipText(userh.getProperty());
									userbuff.remove(userh.yxzd);
									repaint();
									break;
								}
								try {
									sleep(1000);//������ʱ1������ж�
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
					UpdateJTextArea("��"+enemyh.getName()+"�����������غϻ��4�㹥������\n\n");
					balanceskill(enemyh, d);
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;//���ü�����Ч�Ļغ�
							int whenstop=r+3;//���ü���ʧЧ�Ļغ�
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
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
									sleep(1000);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(r==whenstop) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
									if(enemyh.getId()==Config.hyq.getId()) {
										enemyh.setAtk(enemyh.getAtk()-4);
									}
									enemytx.setToolTipText(enemyh.getProperty());
									enemybuff.remove(enemyh.yxzd);
									repaint();
									break;
								}
								try {
									sleep(1000);//������ʱ1������ж�
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
			case 19:{//л�ƺ�Q �ྻ֮��
				if(from.equals(userh)) {
					if(r%2!=0) { // �����غ�
						userh.xyhQHPP=userh.xyh+4;
						userh.xyh=0;
						userh.getQ().setMp(userh.xyh);
						skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
						userh.xyhQ=true;
						UpdateJTextArea("��"+userh.getName()+"�����ڻغϽ���ʱ���"+userh.xyhQHPP+"�������ظ���\n\n");
					} else if(r%2==0) { // ˫���غ�
						balancereal(userh, userh.xyh);
						userh.xyh=0;
						userh.getQ().setMp(userh.xyh);
						skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
					}
					userh.setXdl(userh.getXdl()-9999);
				} else if(from.equals(enemyh)) {
					if(r%2!=0) { // �����غ�
						enemyh.xyhQHPP=enemyh.xyh+4;
						enemyh.xyh=0;
						enemyh.getQ().setMp(enemyh.xyh);
						enemyh.xyhQ=true;
						UpdateJTextArea("��"+enemyh.getName()+"�����ڻغϽ���ʱ���"+enemyh.xyhQHPP+"�������ظ���\n\n");
					} else if(r%2==0) { // ˫���غ�
						balancereal(enemyh, enemyh.xyh);
						enemyh.xyh=0;
						enemyh.getQ().setMp(enemyh.xyh);
					}
					enemyh.setXdl(enemyh.getXdl()-9999);
				}
				break;
			}
			case 20:{//л�ƺ�W ��Բ�ط�
				if(from.equals(userh)) {
					int mk = enemyh.getAdf();//��ȡ����ħ��
					int d = 10 - (int)Math.round((1-userh.getApp())*mk);//�ɹ�ʽת��Ϊħ���˺�
					if(d<0) d=0;
					UpdateJTextArea("��"+enemyh.getName()+"��������һ�غϡ�ս�����ܡ���\n\n");
					balanceskill(userh, d);
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;//���ü�����Ч�Ļغ�
							int whenstop=r+2;//���ü���ʧЧ�Ļغ�
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(r==whenstart) {
									if(enemyh.yyzs) {
										eyyzs--;
										UpdateJTextArea("��"+enemyh.getName()+"�������ˡ�ҹ��֮������Ч���ֵ���һ�Ρ�ս�����ܡ���\n\n");
									} else if(enemyh.fs) {
										UpdateJTextArea("��" + enemyh.getName() + "�������ˡ����ա���Ч���ֵ���һ�Ρ�ս�����ܡ���\n\n");
									} else {
										enemyh.setIsfight(false);
										enemybuff.remove(enemyh.tydf);
										enemybuff.add(enemyh.tydf);
										repaint();
									}
									break;
								}
								try {
									sleep(500);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(rend==whenstop) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
									enemyh.setIsfight(true);
									enemybuff.remove(enemyh.tydf);
									repaint();
									break;
								}
								try {
									sleep(1000);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				} else if(from.equals(enemyh)) {
					int mk = userh.getAdf();//��ȡ�ҵ�ħ��
					int d = 10 - (int)Math.round((1-enemyh.getApp())*mk);//�ɹ�ʽת��Ϊħ���˺�
					if(d<0) d=0;
					UpdateJTextArea("��"+userh.getName()+"��������һ�غϡ�ս�����ܡ���\n\n");
					balanceskill(enemyh, d);
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;//���ü�����Ч�Ļغ�
							int whenstop=r+2;//���ü���ʧЧ�Ļغ�
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(r==whenstart) {
									if(userh.yyzs) {
										uyyzs--;
										UpdateJTextArea("��"+userh.getName()+"�������ˡ�ҹ��֮������Ч���ֵ���һ�Ρ�ս�����ܡ���\n\n");
										if(uyyzs%3==0) {
											if(userh.getZ().getName().equals("ҹ��֮��")) {
												setE(userh.getZ().getId(),3);
												getUserEquip(userh.getZ().getId()+20);
												userh.setZ(null);
												zb1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e1.png")));
												zb1.setToolTipText("���ѡ��һ��װ����������");
											} else if(userh.getX().getName().equals("ҹ��֮��")) {
												setE(userh.getX().getId(),4);
												getUserEquip(userh.getX().getId()+20);
												userh.setX(null);
												zb2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
												zb2.setToolTipText("���ѡ��һ��װ����������");
											}
										}
									} else if(userh.fs) {
										UpdateJTextArea("��" + userh.getName() + "�������ˡ����ա���Ч���ֵ���һ�Ρ�ս�����ܡ���\n\n");
									} else {
										userh.setIsfight(false);
										userbuff.remove(userh.tydf);
										userbuff.add(userh.tydf);
										repaint();
									}
									break;
								}
								try {
									sleep(500);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(rend==whenstop) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
									userh.setIsfight(true);
									userbuff.remove(userh.tydf);
									repaint();
									break;
								}
								try {
									sleep(1000);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				break;
			}
			case 21:{//л�ƺ�E ʱ��ɳ©
				if(from.equals(userh)) {
					UpdateJTextArea("��"+userh.getName()+"�������κ��˺����ߣ�����ħ��ŭ����\n\n");
					userh.xyhE+=4;
					userbuff.remove(userh.sgsl);
					userbuff.add(userh.sgsl);
					userh.sgsl.setDescribe("��Ӣ����ȫ�����κ��˺���Ŀǰ�ѻ���"+userh.xyhED+"���˺���");
					userh.sgsl.setRound(userh.xyhE);
					repaint();
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("��"+enemyh.getName()+"�������κ��˺����ߣ�����ħ��ŭ����\n\n");
					enemyh.xyhE+=4;
					enemybuff.remove(enemyh.sgsl);
					enemybuff.add(enemyh.sgsl);
					enemyh.sgsl.setDescribe("��Ӣ����ȫ�����κ��˺���Ŀǰ�ѻ���"+enemyh.xyhED+"���˺���");
					enemyh.sgsl.setRound(enemyh.xyhE);
					repaint();
				}
				break;
			}
			case 22:{//�ſ�ϫQ ��ѩʮ��
				if(from.equals(userh)) {
					int mk = enemyh.getAdf();//��ȡ����ħ��
					int d = (4+userh.zkxQ) - (int)Math.round((1-userh.getApp())*mk);//�ɹ�ʽת��Ϊħ���˺�
					if(d<0) d=0;
					UpdateJTextArea("��"+enemyh.getName()+"��������һ�غϡ���ȫ�ж����ܡ���\n\n");
					balanceskill(userh, d);
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;//���ü�����Ч�Ļغ�
							int whenstop=r+2;//���ü���ʧЧ�Ļغ�
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(r==whenstart) {
									if(enemyh.yyzs) {
										eyyzs--;
										UpdateJTextArea("��"+enemyh.getName()+"�������ˡ�ҹ��֮������Ч���ֵ���һ�Ρ���ȫ�ж����ܡ���\n\n");
									} else if(enemyh.fs) {
										UpdateJTextArea("��" + enemyh.getName() + "�������ˡ����ա���Ч���ֵ���һ�Ρ���ȫ�ж����ܡ���\n\n");
									} else {
										enemyh.setIsgone(false);
										if(enemyh.xyhE>0) {
											enemyh.xyhE=0;
											if(enemyh.xyhED>0) {
												UpdateJTextArea("��"+enemyh.getName()+"���ͷ������ġ�ʱ��ɳ©����\n\n");
												int d = (int)Math.round(enemyh.xyhED*0.8 - ((1-enemyh.getApp())*userh.getAdf()));
												if(d<0) d=0;
												balanceskill(enemyh, d);
												enemyh.xyhED=0;
											} else {
												UpdateJTextArea("��"+enemyh.getName()+"���ġ�ʱ��ɳ©�������飡\n\n");
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
									sleep(500);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(rend==whenstop) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
									enemyh.setIsgone(true);
									enemybuff.remove(enemyh.bxsz);
									repaint();
									break;
								}
								try {
									sleep(500);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				} else if(from.equals(enemyh)) {
					int mk = userh.getAdf();//��ȡ�ҵ�ħ��
					int d = (4+enemyh.zkxQ) - (int)Math.round((1-enemyh.getApp())*mk);//�ɹ�ʽת��Ϊħ���˺�
					if(d<0) d=0;
					UpdateJTextArea("��"+userh.getName()+"��������һ�غϡ���ȫ�ж����ܡ���\n\n");
					balanceskill(enemyh, d);
					new Thread() {
						@Override
						public void run() {
							int whenstart=r+1;//���ü�����Ч�Ļغ�
							int whenstop=r+2;//���ü���ʧЧ�Ļغ�
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(r==whenstart) {
									if(userh.yyzs) {
										uyyzs--;
										UpdateJTextArea("��"+userh.getName()+"�������ˡ�ҹ��֮������Ч���ֵ���һ�Ρ���ȫ�ж����ܡ���\n\n");
										if(uyyzs%3==0) {
											if(userh.getZ().getName().equals("ҹ��֮��")) {
												setE(userh.getZ().getId(),3);
												getUserEquip(userh.getZ().getId()+20);
												userh.setZ(null);
												zb1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e1.png")));
												zb1.setToolTipText("���ѡ��һ��װ����������");
											} else if(userh.getX().getName().equals("ҹ��֮��")) {
												setE(userh.getX().getId(),4);
												getUserEquip(userh.getX().getId()+20);
												userh.setX(null);
												zb2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
												zb2.setToolTipText("���ѡ��һ��װ����������");
											}
										}
									} else if(userh.fs) {
										UpdateJTextArea("��" + userh.getName() + "�������ˡ����ա���Ч���ֵ���һ�Ρ���ȫ�ж����ܡ���\n\n");
									} else {
										userh.setIsgone(false);
										if(userh.xyhE>0) {
											userh.xyhE=0;
											if(userh.xyhED>0) {
												UpdateJTextArea("��"+userh.getName()+"���Ѿ��ͷ��ˡ�ʱ��ɳ©����\n\n");
												int d = (int)Math.round(userh.xyhED*0.8 - ((1-userh.getApp())*enemyh.getAdf()));
												if(d<0) d=0;
												balanceskill(userh, d);
												userh.xyhED=0;
											} else {
												UpdateJTextArea("��"+userh.getName()+"���ġ�ʱ��ɳ©�������飡\n\n");
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
									sleep(500);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							while(true) {
								if(rend==whenstop) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
									userh.setIsgone(true);
									userbuff.remove(userh.bxsz);
									repaint();
									break;
								}
								try {
									sleep(500);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				break;
			}
			case 23:{//�ſ�ϫW ��֮����
				if(from.equals(userh)) { // �ж�ʹ�ü��ܵ�Ӣ�����ҷ�Ӣ��
					UpdateJTextArea("��"+userh.getName()+"������������˺����ߵ�Ч����\n\n");
					userh.zkxW+=3;//������ܳ���3�غ�
					userh.zkxWH=0;
					userbuff.remove(userh.bzyy);
					userbuff.add(userh.bzyy);
					userh.bzyy.setRound(userh.zkxW);
					userh.bzyy.setDescribe("��Ӣ����ȫ���������˺���Ŀǰ�ѻ��ۣ�"+userh.zkxWH+"/20�����˺���");
					repaint();
				} else if(from.equals(enemyh)) { // �ж�ʹ�ü��ܵ�Ӣ���ǶԷ�Ӣ��
					UpdateJTextArea("��"+enemyh.getName()+"������������˺����ߵ�Ч����\n\n");
					enemyh.zkxW+=3;//������ܳ���3�غ�
					enemyh.zkxWH=0;
					enemybuff.remove(enemyh.bzyy);
					enemybuff.add(enemyh.bzyy);
					enemyh.bzyy.setRound(enemyh.zkxW);
					enemyh.bzyy.setDescribe("��Ӣ����ȫ���������˺���Ŀǰ�ѻ��ۣ�"+enemyh.zkxWH+"/20�����˺���");
					repaint();
				}
				break;
			}
			case 24:{//�ſ�ϫE ϫ֮����
				if(from.equals(userh)) {
					p = new Random().nextInt(3);
					sendP(p);
					UpdateJTextArea("��"+userh.getName()+"���ġ���ѩʮ�֡��͡�ϫ֮����Ч���������1��ħ���˺��ӳɣ�\n\n");
					userh.zkxQ++;
					userh.zkxE++;
					userh.getQ().setdescribe("�ԶԷ����"+(4+userh.zkxQ)+"��ħ���˺�����ʹ�Է��¸��غϡ���ȫ�ж����ܡ���");
					userh.getE().setdescribe("<html>Ϊ��ѩʮ�ֺ�ϫ֮����Ч�����ṩ���õ�1��ħ���˺��ӳɡ�<br/>�����������Ч�����ü����ڻغ���Ϊ3������ʱ����ʹ�ã���<br />"+
							"1�������غ��ڣ��ṩ��ѩʮ��3��ħ���˺��ӳɣ�<br />2���Է��ܵ�"+(8+userh.zkxE)+"��ħ���˺���<br />3������4��˫��������3�غϡ�</html>");
					skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
					skill3.setToolTipText("<html>��E��"+userh.getE().getSkill()+"</html>");
					userh.xzjz.setV1(userh.xzjz.getV1()+1);
					userh.xzjz.setSuperpose("����ǰ������"+userh.xzjz.getV1()+"��");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(p==0) {
						UpdateJTextArea("��"+userh.getName()+"����������ˡ�ϫ֮����Ч��һ��\n\n");
						UpdateJTextArea("��"+userh.getName()+"���ġ���ѩʮ�֡������3��ħ���˺��ӳɣ�\n\n");
						userh.zkxQ+=3;
						userbuff.remove(userh.xzjzsh);
						userbuff.add(userh.xzjzsh);
						userh.xzjzsh.setDescribe("ϫ֮�����Ч��һΪ��ѩʮ���ṩ��"+userh.zkxQ+"��ħ���˺��ӳɡ�");
						repaint();
						new Thread() {
							@Override
							public void run() {
								int whenstop=r+3;
								userh.getQ().setdescribe("�ԶԷ����"+(4+userh.zkxQ)+"��ħ���˺�����ʹ�Է��¸��غϡ���ȫ�ж����ܡ���");
								skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
								while (true) {
									if (r==whenstop) {
										if(userh.getId()==Config.zkx.getId()) {
											userh.zkxQ-=3;
											userh.getQ().setdescribe("�ԶԷ����"+(4+userh.zkxQ)+"��ħ���˺�����ʹ�Է��¸��غϡ���ȫ�ж����ܡ���");
											skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
											if(userh.zkxQ==0) {
												userbuff.remove(userh.xzjzsh);
												repaint();
											} else {
												userh.xzjzsh.setDescribe("ϫ֮�����Ч��һΪ��ѩʮ���ṩ��"+userh.zkxQ+"��ħ���˺��ӳɡ�");
											}
											repaint();
										}
										break;
									}
									try {
										sleep(500);// ������ʱ1������ж�
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}.start();
					} else if(p==1) {
						UpdateJTextArea("��"+userh.getName()+"����������ˡ�ϫ֮����Ч������\n\n");
						int d = (8+userh.zkxE) - (int)Math.round((1-userh.getApp())*enemyh.getAdf());
						if(d<0) d=0;
						balanceskill(userh, d);
					} else {
						UpdateJTextArea("��"+userh.getName()+"����������ˡ�ϫ֮����Ч������\n\n");
						UpdateJTextArea("��"+userh.getName()+"��������4�������׺�4��ħ��������\n\n");
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
										sleep(500);//������ʱ1������ж�
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}.start();
					}
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("��"+enemyh.getName()+"���ġ���ѩʮ�֡��͡�ϫ֮����Ч���������1��ħ���˺��ӳɣ�\n\n");
					enemyh.zkxQ++;
					enemyh.zkxE++;
					enemyh.xzjz.setV1(enemyh.xzjz.getV1()+1);
					enemyh.xzjz.setSuperpose("����ǰ������"+enemyh.xzjz.getV1()+"��");
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
						UpdateJTextArea("��"+enemyh.getName()+"����������ˡ�ϫ֮����Ч��һ��\n\n");
						UpdateJTextArea("��"+enemyh.getName()+"���ġ���ѩʮ�֡������3��ħ���˺��ӳɣ�\n\n");
						enemyh.zkxQ+=3;
						enemybuff.remove(enemyh.xzjzsh);
						enemybuff.add(enemyh.xzjzsh);
						enemyh.xzjzsh.setDescribe("ϫ֮�����Ч��һΪ��ѩʮ���ṩ��"+enemyh.zkxQ+"��ħ���˺��ӳɡ�");
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
												enemyh.xzjzsh.setDescribe("ϫ֮�����Ч��һΪ��ѩʮ���ṩ��"+enemyh.zkxQ+"��ħ���˺��ӳɡ�");
											}
											repaint();
										}
										break;
									}
									try {
										sleep(500);// ������ʱ1������ж�
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}.start();
					} else if(p==1) {
						UpdateJTextArea("��"+enemyh.getName()+"����������ˡ�ϫ֮����Ч������\n\n");
						int d = (8+enemyh.zkxE) - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
						if(d<0) d=0;
						balanceskill(enemyh, d);
					} else {
						UpdateJTextArea("��"+enemyh.getName()+"����������ˡ�ϫ֮����Ч������\n\n");
						UpdateJTextArea("��"+enemyh.getName()+"��������4�������׺�4��ħ��������\n\n");
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
										sleep(500);//������ʱ1������ж�
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
			case 25:{//֣����Q ����
				if(from.equals(userh)) {
					UpdateJTextArea("��"+userh.getName()+"��������һ�����ħ���˺�ʱ���������ޡ�Ч����\n\n");
					userh.zxyQ=true;
					userbuff.remove(userh.lz);
					userbuff.add(userh.lz);
					repaint();
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("��"+enemyh.getName()+"��������һ�����ħ���˺�ʱ���������ޡ�Ч����\n\n");
					enemyh.zxyQ=true;
					enemybuff.remove(enemyh.lz);
					enemybuff.add(enemyh.lz);
					repaint();
				}
				break;
			}
			case 26:{//֣����W ����
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
			case 27:{//֣����E ����֮��
				if(from.equals(userh)) {
					UpdateJTextArea("��"+userh.getName()+"�������80%�����˺����⣡\n\n");
					UpdateJTextArea("��"+enemyh.getName()+"���������ƽ���ʹ���չ���\n\n");
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
						UpdateJTextArea("��" + enemyh.getName() + "�������ˡ����ա���Ч���ֵ���һ�Ρ�ʩ�����ܡ���\n\n");
					}
					new Thread() {
						@Override
						public void run() {
							while(true) {
								if(userh.zxyE==0) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
									enemyh.setIsskill(true);
									userbuff.remove(userh.userylzh);
									enemybuff.remove(enemyh.enemyylzh);
									repaint();
									break;
								}
								try {
									sleep(1000);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("��"+enemyh.getName()+"�������80%�����˺����⣡\n\n");
					UpdateJTextArea("��"+userh.getName()+"���������ƽ���ʹ���չ���\n\n");
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
						UpdateJTextArea("��" + userh.getName() + "�������ˡ����ա���Ч���ֵ���һ�Ρ�ʩ�����ܡ���\n\n");
					}
					new Thread() {
						@Override
						public void run() {
							while(true) {
								if(enemyh.zxyE==0) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
									userh.setIsskill(true);
									userbuff.remove(userh.enemyylzh);
									enemybuff.remove(enemyh.userylzh);
									repaint();
									break;
								}
								try {
									sleep(1000);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				break;
			}
			case 28:{//֣����R ��Դ����
				if(from.equals(userh)) {
					UpdateJTextArea("��"+userh.getName()+"�����������غϵ��ж��׶ο�ʼǰ�ظ�����ֵ��\n\n");
					userh.zxyR+=2;
					userbuff.remove(userh.xysy);
					userbuff.add(userh.xysy);
					userh.xysy.setRound(userh.zxyR);
					repaint();
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("��"+enemyh.getName()+"�����������غϵ��ж��׶ο�ʼǰ�ظ�����ֵ��\n\n");
					enemyh.zxyR+=2;
					enemybuff.remove(enemyh.xysy);
					enemybuff.add(enemyh.xysy);
					enemyh.xysy.setRound(enemyh.zxyR);
					repaint();
				}
				break;
			}
			case 29:{//������ Q ����
				if(from.equals(userh)) {//ʹ�������ҷ�
					UpdateJTextArea("��"+userh.getName()+"��ʹ����һ�Ρ���ͨ��������\n\n");//��Ч����ʾ�ڹ�����
					balanceatk(userh);
					UpdateJTextArea("��"+userh.getName()+"��ʹ����һ�Ρ���ͨ��������\n\n");//��Ч����ʾ�ڹ�����
					userh.lmQ=true;
					balanceatk(userh);
					userh.lmQ=false;
				} else if(from.equals(enemyh)) {//ʹ�����ǶԷ�
					UpdateJTextArea("��"+enemyh.getName()+"��ʹ����һ�Ρ���ͨ��������\n\n");//��Ч����ʾ�ڹ�����
					balanceatk(enemyh);
					UpdateJTextArea("��"+enemyh.getName()+"��ʹ����һ�Ρ���ͨ��������\n\n");//��Ч����ʾ�ڹ�����
					enemyh.lmQ=true;
					balanceatk(enemyh);
					enemyh.lmQ=false;
				}
				break;
			}
			case 30:{//������W �¹⽣
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
			case 31:{//�խZ��Q ����+
				if(from.equals(userh)) {//ʹ��������
					UpdateJTextArea("��"+userh.getName()+"�������80%������ͨ�����ĸ��ʺ�2��ħ��������\n\n");
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
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(userh.getId()==Config.sjj.getId()) {
									if(userh.sjjQ==0) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
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
									sleep(1000);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				} else if(from.equals(enemyh)) {//ʹ�����ǶԷ�
					UpdateJTextArea("��"+enemyh.getName()+"�������80%������ͨ�����ĸ��ʺ�2��ħ��������\n\n");
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
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(enemyh.getId()==Config.sjj.getId()) {
									if(enemyh.sjjQ==0) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
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
									sleep(1000);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				break;
			}
			case 32:{//�խZ��W �ѷ�
				if(from.equals(userh)) {
					int attack=userh.getAtk();
					UpdateJTextArea("��"+userh.getName()+"��������3�㹥�������ԡ�"+enemyh.getName()+"������ˡ�ս�����ܡ���\n\n");
					userh.setAtk(attack+3);
					userbuff.remove(userh.lfatk);
					userbuff.add(userh.lfatk);
					repaint();
					if(enemyh.yyzs) {
						eyyzs--;
						UpdateJTextArea("��"+enemyh.getName()+"�������ˡ�ҹ��֮������Ч���ֵ���һ�Ρ�ս�����ܡ���\n\n");
					} else if(enemyh.fs) {
						UpdateJTextArea("��" + enemyh.getName() + "�������ˡ����ա���Ч���ֵ���һ�Ρ�ս�����ܡ���\n\n");
					} else {
						enemyh.setIsfight(false);
						if(enemyh.xyhE>0) {
							enemyh.xyhE=0;
							if(enemyh.xyhED>0) {
								UpdateJTextArea("��"+enemyh.getName()+"���ͷ������ġ�ʱ��ɳ©����\n\n");
								int d = (int)Math.round(enemyh.xyhED*0.8 - ((1-enemyh.getApp())*userh.getAdf()));
								if(d<0) d=0;
								balanceskill(enemyh, d);
								enemyh.xyhED=0;
							} else {
								UpdateJTextArea("��"+enemyh.getName()+"���ġ�ʱ��ɳ©�������飡\n\n");
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
							int whenstop=r+2;//���ü���ʧЧ�Ļغ�
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(rend==whenstop) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
									if(userh.sjjW) {
										userh.sjjW=false;//ʹ�խZ������ʧЧ
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
									sleep(500);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				} else if(from.equals(enemyh)) {
					int attack=enemyh.getAtk();
					UpdateJTextArea("��"+enemyh.getName()+"��������3�㹥�������ԡ�"+userh.getName()+"������ˡ�ս�����ܡ���\n\n");
					enemyh.setAtk(attack+3);
					enemybuff.remove(enemyh.lfatk);
					enemybuff.add(enemyh.lfatk);
					repaint();
					if(userh.yyzs) {
						uyyzs--;
						UpdateJTextArea("��"+userh.getName()+"�������ˡ�ҹ��֮������Ч���ֵ���һ�Ρ�ս�����ܡ���\n\n");
						if(uyyzs%3==0) {
							if(userh.getZ().getName().equals("ҹ��֮��")) {
								setE(userh.getZ().getId(),3);
								getUserEquip(userh.getZ().getId()+20);
								userh.setZ(null);
								zb1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e1.png")));
								zb1.setToolTipText("���ѡ��һ��װ����������");
							} else if(userh.getX().getName().equals("ҹ��֮��")) {
								setE(userh.getX().getId(),4);
								getUserEquip(userh.getX().getId()+20);
								userh.setX(null);
								zb2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
								zb2.setToolTipText("���ѡ��һ��װ����������");
							}
						}
					} else if(userh.fs) {
						UpdateJTextArea("��" + userh.getName() + "�������ˡ����ա���Ч���ֵ���һ�Ρ�ս�����ܡ���\n\n");
					} else {
						userh.setIsfight(false);
						if(userh.xyhE>0) {
							userh.xyhE=0;
							if(userh.xyhED>0) {
								UpdateJTextArea("��"+userh.getName()+"���Ѿ��ͷ��ˡ�ʱ��ɳ©����\n\n");
								int d = (int)Math.round(userh.xyhED*0.8 - ((1-userh.getApp())*enemyh.getAdf()));
								if(d<0) d=0;
								balanceskill(userh, d);
								userh.xyhED=0;
							} else {
								UpdateJTextArea("��"+userh.getName()+"���ġ�ʱ��ɳ©�������飡\n\n");
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
							int whenstop=r+2;//���ü���ʧЧ�Ļغ�
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(rend==whenstop) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
									if(enemyh.sjjW) {
										enemyh.sjjW=false;//ʹ�խZ������ʧЧ
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
									sleep(500);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				break;
			}
			case 33:{//�խZ��E ��㽣
				if(from.equals(userh)) {
					UpdateJTextArea("��"+userh.getName()+"������һ���չ�����ʱ�ᴥ������㽣����Ч����\n\n");
					userh.sjjE=true;
					userbuff.remove(userh.gcj);
					userbuff.add(userh.gcj);
					repaint();
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("��"+enemyh.getName()+"������һ���չ�����ʱ�ᴥ������㽣����Ч����\n\n");
					enemyh.sjjE=true;
					enemybuff.remove(enemyh.gcj);
					enemybuff.add(enemyh.gcj);
					repaint();
				}
				break;
			}
			case 34:{//�խZ��R ��������
				if(from.equals(userh)) {
					UpdateJTextArea("��"+userh.getName()+"���ٻ���3����������\n\n");
					userh.sjjR+=3;
					userbuff.remove(userh.gzhl);
					userbuff.add(userh.gzhl);
					userh.gzhl.setSuperpose("��������������"+userh.sjjR+"��");
					repaint();
				} else if(from.equals(enemyh)) {
					UpdateJTextArea("��"+enemyh.getName()+"���ٻ���3����������\n\n");
					enemyh.sjjR+=3;
					enemybuff.remove(enemyh.gzhl);
					enemybuff.add(enemyh.gzhl);
					enemyh.gzhl.setSuperpose("��������������"+enemyh.sjjR+"��");
					repaint();
				}
				break;
			}
			case 35:{//ά������Q ����
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
							int whenstop=r+1;//���ü���ʧЧ�Ļغ�
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(r==whenstop) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
									enemyh.ww=p+1;
									enemybuff.remove(enemyh.sy);
									enemybuff.add(enemyh.sy);
									repaint();
									break;
								}
								try {
									sleep(1000);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
					switch(p) {
						case 0:{
							UpdateJTextArea("��"+enemyh.getName()+"�����ܡ����͡���ָ��»غϱ���ʹ�á���ͨ��������\n\n");
							break;
						}
						case 1:{
							UpdateJTextArea("��"+enemyh.getName()+"�����ܡ����͡���ָ��»غϱ���ʹ�á����⼼�ܡ���\n\n");
							break;
						}
						case 2:{
							UpdateJTextArea("��"+enemyh.getName()+"�����ܡ����͡���ָ��»غϱ��롾�����ж�����\n\n");
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
							int whenstop=r+1;//���ü���ʧЧ�Ļغ�
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(r==whenstop) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
									userh.ww=p+1;
									userbuff.remove(userh.sy);
									userbuff.add(userh.sy);
									repaint();
									break;
								}
								try {
									sleep(1000);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
					switch(p) {
						case 0:{
							UpdateJTextArea("��"+userh.getName()+"�����ܡ����͡���ָ��»غϱ���ʹ�á���ͨ��������\n\n");
							break;
						}
						case 1:{
							UpdateJTextArea("��"+userh.getName()+"�����ܡ����͡���ָ��»غϱ���ʹ�á����⼼�ܡ���\n\n");
							break;
						}
						case 2:{
							UpdateJTextArea("��"+userh.getName()+"�����ܡ����͡���ָ��»غϱ��롾�����ж�����\n\n");
							break;
						}
					}
				}
				break;
			}
			case 36:{//ά������W ʥ��
				if(from.equals(userh)) {
					UpdateJTextArea("��"+userh.getName()+"��������3�㹥������2�������ף�\n\n");
					userh.setAtk(userh.getAtk()+3);
					userh.setDef(userh.getDef()+2);
					UpdateJTextArea("��"+userh.getName()+"������������1��ÿ�غ�ħ���ظ���\n\n");
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
							int whenstop=r+2;//���ü���ʧЧ�Ļغ�
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(r==whenstop) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
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
									sleep(1000);//������ʱ1������ж�
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				} else if(from.equals(enemyh)){
					UpdateJTextArea("��"+enemyh.getName()+"��������3�㹥������2�������ף�\n\n");
					enemyh.setAtk(enemyh.getAtk()+3);
					enemyh.setDef(enemyh.getDef()+2);
					UpdateJTextArea("��"+enemyh.getName()+"������������1��ÿ�غ�ħ���ظ���\n\n");
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
							int whenstop=r+2;//���ü���ʧЧ�Ļغ�
							while(true) {//�����Ϸ���е��Ļغ� ������ �������ü���ʧЧ���Ǹ��غ� ��һֱѭ��
								if(r==whenstop) {//�غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
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
									sleep(1000);//������ʱ1������ж�
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
				skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/yy/w.png")));
				skill2.setToolTipText("<html>��W��"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/yy/e.png")));
				skill3.setToolTipText("<html>��E��"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				select.remove(skill4);
				skillgroup.remove(skill4);
				break;
			}
			case 2:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/lxs/q.png")));
				skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/lxs/w.png")));
				skill2.setToolTipText("<html>��W��"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/lxs/e.png")));
				skill3.setToolTipText("<html>��E��"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				skillgroup.remove(skill4);
				select.remove(skill4);
				break;
			}
			case 3:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/ysn/q.png")));
				skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/ysn/w.png")));
				skill2.setToolTipText("<html>��W��"+userh.getW().getSkill()+"</html>");
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
				skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/ltj/w.png")));
				skill2.setToolTipText("<html>��W��"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/ltj/e.png")));
				skill3.setToolTipText("<html>��E��"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				select.remove(skill4);
				skillgroup.remove(skill4);
				break;
			}
			case 5:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zf/q.png")));
				skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zf/w.png")));
				skill2.setToolTipText("<html>��W��"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zf/e.png")));
				skill3.setToolTipText("<html>��E��"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				select.remove(skill4);
				skillgroup.remove(skill4);
				break;
			}
			case 6:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/hyq/q.png")));
				skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/hyq/w.png")));
				skill2.setToolTipText("<html>��W��"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/hyq/e.png")));
				skill3.setToolTipText("<html>��E��"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				skill4.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/hyq/r.png")));
				skill4.setToolTipText("<html>��R��"+userh.getR().getSkill()+"</html>");
				skillgroup.add(skill4);
				select.add(skill4);
				break;
			}
			case 7:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/xyh/q.png")));
				skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/xyh/w.png")));
				skill2.setToolTipText("<html>��W��"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/xyh/e.png")));
				skill3.setToolTipText("<html>��E��"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				skillgroup.remove(skill4);
				select.remove(skill4);
				break;
			}
			case 8:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zkx/q.png")));
				skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zkx/w.png")));
				skill2.setToolTipText("<html>��W��"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zkx/e.png")));
				skill3.setToolTipText("<html>��E��"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				skillgroup.remove(skill4);
				select.remove(skill4);
				break;
			}
			case 9:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zxy/q.png")));
				skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zxy/w.png")));
				skill2.setToolTipText("<html>��W��"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zxy/e.png")));
				skill3.setToolTipText("<html>��E��"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				skill4.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zxy/r.png")));
				skill4.setToolTipText("<html>��R��"+userh.getR().getSkill()+"</html>");
				skillgroup.add(skill4);
				select.add(skill4);
				break;
			}
			case 10:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/lm/q.png")));
				skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/lm/w.png")));
				skill2.setToolTipText("<html>��W��"+userh.getW().getSkill()+"</html>");
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
				skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/sjj/w.png")));
				skill2.setToolTipText("<html>��W��"+userh.getW().getSkill()+"</html>");
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/sjj/e.png")));
				skill3.setToolTipText("<html>��E��"+userh.getE().getSkill()+"</html>");
				skillgroup.add(skill3);
				select.add(skill3);
				skill4.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/sjj/r.png")));
				skill4.setToolTipText("<html>��R��"+userh.getR().getSkill()+"</html>");
				skillgroup.add(skill4);
				select.add(skill4);
				break;
			}
			case 12:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/w/q.png")));
				skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/w/w.png")));
				skill2.setToolTipText("<html>��W��"+userh.getW().getSkill()+"</html>");
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
					UpdateJTextArea("��Ϸ�����У�����ʱ������·�����̾�š����ͶԷ����죬��������Ӧ�����Ա�ʹ���ض����ܡ�"+"\n\n");
					UpdateJTextArea("���� -help ��������ð�����"+"\n\n");
				}
				if(remain==16) {
					UpdateJTextArea("��ԶԷ�˵����ã����װ������ѣ�\n\n");
					pw3.write(user.getUsername()+"��"+userh.getName()+"��˵����ã����װ������ѣ�\n");
					pw3.flush();
					if(userh.getId()==Config.lxs.getId()) {
						userbuff.add(userh.jfzm);
						userh.jfzm.setSuperpose("����ǰ������"+userh.jfzm.getV1()+"��������+0 ����+0");
					}
					if(userh.getId()==Config.zkx.getId()) {
						userbuff.add(userh.xzjz);
						userh.xzjz.setSuperpose("����ǰ������"+userh.xzjz.getV1()+"��");
					}
					if(enemyh.getId()==Config.lxs.getId()) {
						enemybuff.add(enemyh.jfzm);
						enemyh.jfzm.setSuperpose("����ǰ������"+enemyh.jfzm.getV1()+"��������+0 ����+0");
					}
					if(enemyh.getId()==Config.zkx.getId()) {
						enemybuff.add(enemyh.xzjz);
						enemyh.xzjz.setSuperpose("����ǰ������"+enemyh.xzjz.getV1()+"��");
					}
					repaint();
				}
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				remaintime.setText("��ʣ"+remain+"��");
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
		//����E
		if(userh.yyER>0) {
			userh.yyER--;
			if(userh.yyER>0) UpdateJTextArea("��"+userh.getName()+"���ġ���ɱ֮�硿����ʱ�仹ʣ"+userh.yyER+"���غϡ�\n\n");
			else {
				userh.yyE=0;
				userbuff.remove(userh.tszf);
				repaint();
				if(userh.jhjj==2) {
					userh.getQ().setdescribe("<html>�ԶԷ�������գ�ÿ�غ����"+(5+userh.yyE)+"��ħ���˺�������3�غϡ���Ч��Ӱ��Ļغ������Ե��ӡ�<br/><br/>�Ѽ���ᾧ֮����</html>");
					skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
				} else {
					userh.getQ().setdescribe("��0��9�г�ȡ�������֡�<br />��������С�ڻ���ڶԷ���ǰ����ֵ�ĸ�λ������ô�ԶԷ�������գ�ÿ�غ����"+(5+userh.yyE)+"��ħ���˺�������3�غϡ�<br />��Ч��Ӱ��Ļغ������Ե��ӡ�<br/><br />������ᾧ֮����100%��Ч��");
					skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
				}
				userh.getW().setdescribe("��0��9�г�ȡ�������֡�<br />��������С�ڻ���ڶԷ��ж������㹥����֮�͵ĸ�λ������ô�ԶԷ����"+(10+userh.yyE)+"��ħ���˺����ü��������ж�����");
				skill2.setToolTipText("<html>��W��"+userh.getW().getSkill()+"</html>");
			}
		}
		if(enemyh.yyER>0) {
			enemyh.yyER--;
			if(enemyh.yyER>0) UpdateJTextArea("��"+enemyh.getName()+"���ġ���ɱ֮�硿����ʱ�仹ʣ"+enemyh.yyER+"���غϡ�\n\n");
			else {
				enemyh.yyE=0;
				enemybuff.remove(enemyh.tszf);
				repaint();
			}
		}
		//��ʥŵW
		if (userh.ysnW>0) {
			userh.ysnW--;
			if(userh.ysnW>0) {
				UpdateJTextArea("��"+userh.getName()+"���ġ�ħ�����͡�����ʱ�仹ʣ"+userh.ysnW+"���غϡ�\n\n");
				userh.xcyl.setRound(userh.ysnW);
			}
		}
		if (enemyh.ysnW>0) {
			enemyh.ysnW--;
			if(enemyh.ysnW>0) {
				UpdateJTextArea("��"+enemyh.getName()+"���ġ�ħ�����͡�����ʱ�仹ʣ"+enemyh.ysnW+"���غϡ�\n\n");
				enemyh.xcyl.setRound(enemyh.ysnW);
			}
		}
		//�C��ȴQ
		if (userh.hyqQ>0) {
			userh.hyqQ--;
			if(userh.hyqQ>0) {
				UpdateJTextArea("��"+userh.getName()+"���ġ�����Ϊ��������ʱ�仹ʣ"+userh.hyqQ+"���غϡ�\n\n");
				userh.xrwz.setRound(userh.hyqQ);
			}
		}
		if (enemyh.hyqQ>0) {
			enemyh.hyqQ--;
			if(enemyh.hyqQ>0) {
				UpdateJTextArea("��"+enemyh.getName()+"���ġ�����Ϊ��������ʱ�仹ʣ"+enemyh.hyqQ+"���غϡ�\n\n");
				enemyh.xrwz.setRound(enemyh.hyqQ);
			}
		}
		//�C��ȴW
		if (userh.hyqW > 0) {
			userh.hyqW--;
			if (userh.hyqW > 0) {
				UpdateJTextArea("��" + userh.getName() + "���ġ����׽��͡�����ʱ�仹ʣ" + userh.hyqW + "���غϡ�\n\n");
				userh.qlbx.setRound(userh.hyqW);
			}
		}
		if (enemyh.hyqW > 0) {
			enemyh.hyqW--;
			if (enemyh.hyqW > 0) {
				UpdateJTextArea("��" + enemyh.getName() + "���ġ����׽��͡�����ʱ�仹ʣ" + enemyh.hyqW + "���غϡ�\n\n");
				enemyh.qlbx.setRound(enemyh.hyqW);
			}
		}
		//�ſ�ϫW
		if(userh.zkxW>0) {
			userh.zkxW--;
			if(userh.zkxW>0) {
				UpdateJTextArea("��"+userh.getName()+"���ġ���֮��������ʱ�仹ʣ"+userh.zkxW+"���غϡ�\n\n");
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
				UpdateJTextArea("��"+enemyh.getName()+"���ġ���֮��������ʱ�仹ʣ"+enemyh.zkxW+"���غϡ�\n\n");
				enemyh.bzyy.setRound(enemyh.zkxW);
				repaint();
			}
			else {
				enemyh.zkxWH=0;
				enemybuff.remove(enemyh.bzyy);
				repaint();
			}
		}
		//�����W
		if(userh.ltjW>0) {
			userh.ltjW--;
			if(userh.ltjW>0) {
				UpdateJTextArea("��"+userh.getName()+"���ġ����֡�����ʱ�仹ʣ"+userh.ltjW+"���غϡ�\n\n");
				userh.sx.setRound(userh.ltjW);
			} else {
				userbuff.remove(userh.sx);
				repaint();
			}
		}
		if(enemyh.ltjW>0) {
			enemyh.ltjW--;
			if(enemyh.ltjW>0) {
				UpdateJTextArea("��"+enemyh.getName()+"���ġ����֡�����ʱ�仹ʣ"+enemyh.ltjW+"���غϡ�\n\n");
				enemyh.sx.setRound(enemyh.ltjW);
			} else {
				enemybuff.remove(enemyh.sx);
				repaint();
			}
		}
		//�խZ��Q
		if(userh.sjjQ>0) {
			userh.sjjQ--;
			if(userh.sjjQ>0) {
				UpdateJTextArea("��"+userh.getName()+"���ġ�����+������ʱ�仹ʣ"+userh.sjjQ+"���غϡ�\n\n");
				userh.sxplus.setRound(userh.sjjQ);
				userh.sxmk.setRound(userh.sjjQ);
			}
		}
		if(enemyh.sjjQ>0) {
			enemyh.sjjQ--;
			if(enemyh.sjjQ>0) {
				UpdateJTextArea("��"+enemyh.getName()+"���ġ�����+������ʱ�仹ʣ"+enemyh.sjjQ+"���غϡ�\n\n");
				enemyh.sxplus.setRound(enemyh.sjjQ);
				enemyh.sxmk.setRound(enemyh.sjjQ);
			}
		}
		//֣����E
		if(userh.zxyE>0) {
			userh.zxyE--;
			if(userh.zxyE>0) {
				UpdateJTextArea("��"+userh.getName()+"���ġ�����֮��������ʱ�仹ʣ"+userh.zxyE+"���غϡ�\n\n");
				userh.userylzh.setRound(userh.zxyE);
				enemyh.enemyylzh.setRound(userh.zxyE);
			}
		}
		if(enemyh.zxyE>0) {
			enemyh.zxyE--;
			if(enemyh.zxyE>0) {
				UpdateJTextArea("��"+enemyh.getName()+"���ġ�����֮��������ʱ�仹ʣ"+enemyh.zxyE+"���غϡ�\n\n");
				userh.enemyylzh.setRound(enemyh.zxyE);
				enemyh.userylzh.setRound(enemyh.zxyE);
			}
		}
		//֣����R
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
			UpdateJTextArea("��"+userh.getName()+"���ظ���"+hpadd+"������ֵ��\n\n");
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
				UpdateJTextArea("��"+userh.getName()+"���ġ���Դ���򡿳���ʱ�仹ʣ"+userh.zxyR+"���غϡ�\n\n");
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
			UpdateJTextArea("��"+enemyh.getName()+"���ظ���"+hpadd+"������ֵ��\n\n");
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
				UpdateJTextArea("��"+enemyh.getName()+"���ġ���Դ���򡿳���ʱ�仹ʣ"+enemyh.zxyR+"���غϡ�\n\n");
				enemyh.xysy.setRound(enemyh.zxyR);
			} else {
				enemybuff.remove(enemyh.xysy);
				repaint();
			}
		}
		//�ƾ�֮ì
		if(userh.pjzmcd>0) {
			if(userh.getZ()!=null) {
				if(userh.getZ().getName().equals("�ƾ�֮ì")) {
					userh.pjzmcd--;
				}
			} else if(userh.getX()!=null) {
				if(userh.getX().getName().equals("�ƾ�֮ì")) {
					userh.pjzmcd--;
				}
			}
			if(userh.pjzmcd==0) {
				userh.pjzm=true;
			}
		}
		if(enemyh.pjzmcd>0) {
			if(enemyh.getZ()!=null) {
				if(enemyh.getZ().getName().equals("�ƾ�֮ì")) {
					enemyh.pjzmcd--;
				}
			} else if(enemyh.getX()!=null) {
				if(enemyh.getX().getName().equals("�ƾ�֮ì")) {
					enemyh.pjzmcd--;
				}
			}
			if(enemyh.pjzmcd==0) {
				enemyh.pjzm=true;
			}
		}
	}
	
	public void ActionOfUser(int operation) {
		if(userh.ww>0) { // ����
			if(userh.ww==1) {
				if(operation!=5) {
					UpdateJTextArea("��"+userh.getName()+"��û���������ͣ��������ʧȥ��1�������ף�"+"\n\n");
					userh.setDef(userh.getDef()-1);
					usertx.setToolTipText(userh.getProperty());
				}
			} else if(userh.ww==2) {
				if(operation<1&&operation>4) {
					UpdateJTextArea("��"+userh.getName()+"��û���������ͣ��������ʧȥ��1�������ף�"+"\n\n");
					userh.setDef(userh.getDef()-1);
					usertx.setToolTipText(userh.getProperty());
				}
			} else {
				if(operation>=1) {
					UpdateJTextArea("��"+userh.getName()+"��û���������ͣ��������ʧȥ��1�������ף�"+"\n\n");
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
				UpdateJTextArea("��"+userh.getName()+"��ʹ���˼��ܡ�"+userh.getQ().getName()+"����"+"\n\n");
				userh.setMp(userh.getMp()-userh.getQ().getMp());
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				getSkillEffect(userh.getQ(),userh);
				break;
			}
			case 2:{
				UpdateJTextArea("��"+userh.getName()+"��ʹ���˼��ܡ�"+userh.getW().getName()+"����"+"\n\n");
				userh.setMp(userh.getMp()-userh.getW().getMp());
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				getSkillEffect(userh.getW(),userh);
				break;
			}
			case 3:{
				UpdateJTextArea("��"+userh.getName()+"��ʹ���˼��ܡ�"+userh.getE().getName()+"����"+"\n\n");
				userh.setMp(userh.getMp()-userh.getE().getMp());
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				getSkillEffect(userh.getE(),userh);
				break;
			}
			case 4:{
				UpdateJTextArea("��"+userh.getName()+"��ʹ���˼��ܡ�"+userh.getR().getName()+"����"+"\n\n");
				userh.setMp(userh.getMp()-userh.getR().getMp());
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				getSkillEffect(userh.getR(),userh);
				break;
			}
			case 5:{
				UpdateJTextArea("��"+userh.getName()+"��ʹ���ˡ���ͨ��������"+"\n\n");
				balanceatk(userh);
				break;
			}
			case 13:{//�лظ�ҩ
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("���ѡ��һ�����ߣ��Ա�ûغ�ʹ�á�");
				repaint();
				int mp = (int)Math.round(usermpt.getMaximum()*0.3);
				UpdateJTextArea("��"+userh.getName()+"��ʹ���˵��ߡ��лظ�ҩ����"+"\n\n");
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
				UpdateJTextArea("��"+userh.getName()+"���ظ���6������ֵ��"+mp+"��ħ��ֵ��\n\n");
				break;
			}
			case 14:{//��ظ�ҩ
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("���ѡ��һ�����ߣ��Ա�ûغ�ʹ�á�");
				repaint();
				int mp = (int)Math.round(usermpt.getMaximum()*0.6);
				UpdateJTextArea("��"+userh.getName()+"��ʹ���˵��ߡ���ظ�ҩ����"+"\n\n");
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
				UpdateJTextArea("��"+userh.getName()+"���ظ���11������ֵ��"+mp+"��ħ��ֵ��\n\n");
				break;
			}
			case 15:{//���ս���
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("���ѡ��һ�����ߣ��Ա�ûغ�ʹ�á�");
				repaint();
				UpdateJTextArea("��"+userh.getName()+"��ʹ���˵��ߡ����ս��ҡ���"+"\n\n");
				UpdateJTextArea("��"+userh.getName()+"�������������غϻ�ÿ������ߣ�\n\n");
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
			case 16:{//�߼����ս���
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("���ѡ��һ�����ߣ��Ա�ûغ�ʹ�á�");
				repaint();
				UpdateJTextArea("��"+userh.getName()+"��ʹ���˵��ߡ��߼����ս��ҡ���"+"\n\n");
				if(userh.getHp()+5<=userhpt.getMaximum()) {
					userh.setHp(userh.getHp()+5);
					hpp+=5;
				} else {
					hpp+=userhpt.getMaximum()-userh.getHp();
					userh.setHp(userhpt.getMaximum());
				}
				userhpt.setValue(userh.getHp());
				userhpt.setString(userh.getHp()+" / "+userhpt.getMaximum());
				UpdateJTextArea("��"+userh.getName()+"���ظ���5������ֵ�������������غϻ�ÿ������ߣ�\n\n");
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
			case 17:{//ħ������1
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("���ѡ��һ�����ߣ��Ա�ûغ�ʹ�á�");
				repaint();
				UpdateJTextArea("��"+userh.getName()+"��ʹ���˵��ߡ�ħ������I����"+"\n\n");
				if(userh.getMp()+7<=usermpt.getMaximum()) {
					userh.setMp(userh.getMp()+7);
					mpp+=7;
				} else {
					mpp+=usermpt.getMaximum()-userh.getMp();
					userh.setMp(usermpt.getMaximum());
				}
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				UpdateJTextArea("��"+userh.getName()+"���ظ���7��ħ��ֵ��\n\n");
				break;
			}
			case 18:{//ħ������2
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("���ѡ��һ�����ߣ��Ա�ûغ�ʹ�á�");
				repaint();
				UpdateJTextArea("��"+userh.getName()+"��ʹ���˵��ߡ�ħ������II����"+"\n\n");
				mpp+=usermpt.getMaximum()-userh.getMp();
				userh.setMp(usermpt.getMaximum());
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				UpdateJTextArea("��"+userh.getName()+"���ظ���ȫ����ħ��ֵ��\n\n");
				break;
			}
			case 19:{//ħ������3
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("���ѡ��һ�����ߣ��Ա�ûغ�ʹ�á�");
				repaint();
				UpdateJTextArea("��"+userh.getName()+"��ʹ���˵��ߡ�ħ������II����"+"\n\n");
				mpp+=usermpt.getMaximum()-userh.getMp();
				userh.setMp(usermpt.getMaximum());
				usermpt.setValue(userh.getMp());
				usermpt.setString(userh.getMp()+" / "+usermpt.getMaximum());
				UpdateJTextArea("��"+userh.getName()+"���ظ���ȫ����ħ��ֵ�������2��ħ���ظ���\n\n");
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
			case 20:{//�ж�������
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("���ѡ��һ�����ߣ��Ա�ûغ�ʹ�á�");
				repaint();
				UpdateJTextArea("��"+userh.getName()+"��ʹ���˵��ߡ��ж������ҡ���"+"\n\n");
				UpdateJTextArea("��"+userh.getName()+"�����������غ��ڻ��4���ж����ӳɣ�\n\n");
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
			case 21:{//˫��ҩ��
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("���ѡ��һ�����ߣ��Ա�ûغ�ʹ�á�");
				repaint();
				UpdateJTextArea("��"+userh.getName()+"��ʹ���˵��ߡ�˫��ҩ������"+"\n\n");
				UpdateJTextArea("��"+userh.getName()+"�����������غ��ڻ��4��˫���ӳɣ�\n\n");
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
			case 22:{//ǿ��ҩˮ
				item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png")));
				item.setToolTipText("���ѡ��һ�����ߣ��Ա�ûغ�ʹ�á�");
				repaint();
				UpdateJTextArea("��"+userh.getName()+"��ʹ���˵��ߡ�ǿ��ҩˮ����"+"\n\n");
				UpdateJTextArea("��"+userh.getName()+"�����������غ��ڻ��4�������ӳɣ�\n\n");
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
		if(enemyh.ww>0) { // ����
			if(enemyh.ww==1) {
				if(operation!=5) {
					UpdateJTextArea("��"+enemyh.getName()+"��û���������ͣ��������ʧȥ��1�������ף�"+"\n\n");
					enemyh.setDef(enemyh.getDef()-1);
					enemytx.setToolTipText(enemyh.getProperty());
				}
			} else if(enemyh.ww==2) {
				if(operation<1&&operation>4) {
					UpdateJTextArea("��"+enemyh.getName()+"��û���������ͣ��������ʧȥ��1�������ף�"+"\n\n");
					enemyh.setDef(enemyh.getDef()-1);
					enemytx.setToolTipText(enemyh.getProperty());
				}
			} else {
				if(operation>=1) {
					UpdateJTextArea("��"+enemyh.getName()+"��û���������ͣ��������ʧȥ��1�������ף�"+"\n\n");
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
				UpdateJTextArea("��"+enemyh.getName()+"�������˸ûغϵ��ж���\n\n");
				break;
			}
			case 1:{
				UpdateJTextArea("��"+enemyh.getName()+"��ʹ���˼��ܡ�"+enemyh.getQ().getName()+"����"+"\n\n");
				enemyh.setMp(enemyh.getMp()-enemyh.getQ().getMp());
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				getSkillEffect(enemyh.getQ(),enemyh);
				break;
			}
			case 2:{
				UpdateJTextArea("��"+enemyh.getName()+"��ʹ���˼��ܡ�"+enemyh.getW().getName()+"����"+"\n\n");
				enemyh.setMp(enemyh.getMp()-enemyh.getW().getMp());
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				getSkillEffect(enemyh.getW(),enemyh);
				break;
			}
			case 3:{
				UpdateJTextArea("��"+enemyh.getName()+"��ʹ���˼��ܡ�"+enemyh.getE().getName()+"����"+"\n\n");
				enemyh.setMp(enemyh.getMp()-enemyh.getE().getMp());
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				getSkillEffect(enemyh.getE(),enemyh);
				break;
			}
			case 4:{
				UpdateJTextArea("��"+enemyh.getName()+"��ʹ���˼��ܡ�"+enemyh.getR().getName()+"����"+"\n\n");
				enemyh.setMp(enemyh.getMp()-enemyh.getR().getMp());
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				getSkillEffect(enemyh.getR(),enemyh);
				break;
			}
			case 5:{
				UpdateJTextArea("��"+enemyh.getName()+"��ʹ���ˡ���ͨ��������"+"\n\n");
				balanceatk(enemyh);
				break;
			}
			case 13:{//�лظ�ҩ
				int mp = (int)Math.round(enemympt.getMaximum()*0.3);
				UpdateJTextArea("��"+enemyh.getName()+"��ʹ���˵��ߡ��лظ�ҩ����"+"\n\n");
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
				UpdateJTextArea("��"+enemyh.getName()+"���ظ���6������ֵ��"+mp+"��ħ��ֵ��\n\n");
				break;
			}
			case 14:{//��ظ�ҩ
				int mp = (int)Math.round(enemympt.getMaximum()*0.6);
				UpdateJTextArea("��"+enemyh.getName()+"��ʹ���˵��ߡ���ظ�ҩ����"+"\n\n");
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
				UpdateJTextArea("��"+enemyh.getName()+"���ظ���11������ֵ��"+mp+"��ħ��ֵ��\n\n");
				break;
			}
			case 15:{//���ս���
				UpdateJTextArea("��"+enemyh.getName()+"��ʹ���˵��ߡ����ս��ҡ���"+"\n\n");
				UpdateJTextArea("��"+enemyh.getName()+"�������������غϻ�ÿ������ߣ�\n\n");
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
			case 16:{//�߼����ս���
				UpdateJTextArea("��"+enemyh.getName()+"��ʹ���˵��ߡ��߼����ս��ҡ���"+"\n\n");
				if(enemyh.getHp()+5<=enemyhpt.getMaximum()) {
					enemyh.setHp(enemyh.getHp()+5);
					hpp+=5;
				} else {
					hpp+=enemyhpt.getMaximum()-enemyh.getHp();
					enemyh.setHp(enemyhpt.getMaximum());
				}
				enemyhpt.setValue(enemyh.getHp());
				enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
				UpdateJTextArea("��"+enemyh.getName()+"���ظ���5������ֵ�������������غϻ�ÿ������ߣ�\n\n");
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
			case 17:{//ħ������1
				UpdateJTextArea("��"+enemyh.getName()+"��ʹ���˵��ߡ�ħ������I����"+"\n\n");
				if(enemyh.getMp()+7<=enemympt.getMaximum()) {
					enemyh.setMp(enemyh.getMp()+7);
				} else {
					enemyh.setMp(enemympt.getMaximum());
				}
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				UpdateJTextArea("��"+enemyh.getName()+"���ظ���7��ħ��ֵ��\n\n");
				break;
			}
			case 18:{//ħ������2
				UpdateJTextArea("��"+enemyh.getName()+"��ʹ���˵��ߡ�ħ������II����"+"\n\n");
				enemyh.setMp(enemympt.getMaximum());
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				UpdateJTextArea("��"+enemyh.getName()+"���ظ���ȫ����ħ��ֵ��\n\n");
				break;
			}
			case 19:{//ħ������3
				UpdateJTextArea("��"+enemyh.getName()+"��ʹ���˵��ߡ�ħ������II����"+"\n\n");
				enemyh.setMp(enemympt.getMaximum());
				enemympt.setValue(enemyh.getMp());
				enemympt.setString(enemyh.getMp()+" / "+enemympt.getMaximum());
				UpdateJTextArea("��"+enemyh.getName()+"���ظ���ȫ����ħ��ֵ�������2��ħ���ظ���\n\n");
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
			case 20:{//�ж�������
				UpdateJTextArea("��"+enemyh.getName()+"��ʹ���˵��ߡ��ж������ҡ���"+"\n\n");
				UpdateJTextArea("��"+enemyh.getName()+"�����������غ��ڻ��4���ж����ӳɣ�\n\n");
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
			case 21:{//˫��ҩ��
				UpdateJTextArea("��"+enemyh.getName()+"��ʹ���˵��ߡ�˫��ҩ������"+"\n\n");
				UpdateJTextArea("��"+enemyh.getName()+"�����������غ��ڻ��4��˫���ӳɣ�\n\n");
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
			case 22:{//ǿ��ҩˮ
				UpdateJTextArea("��"+enemyh.getName()+"��ʹ���˵��ߡ�ǿ��ҩˮ����"+"\n\n");
				UpdateJTextArea("��"+enemyh.getName()+"�����������غ��ڻ��4�������ӳɣ�\n\n");
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
			whoact.setText("���ڽ����ж�����");
			UpdateJTextArea("��������׶Σ�\n\n");
			/**
			 * @function �ж����㷨
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
			//����Q
			if(userh.yyQ>0) {
				userh.yyQ--;
				if(enemyh.getHp()>0) {
					int d = (5+enemyh.yyE) - (int)Math.round((1-enemyh.getApp())*userh.getAdf());
					if(d<0) d=0;
					if(userh.getId()==Config.xyh.getId()) {
						if(userh.xyh+d<=8) userh.xyh+=d;
						else userh.xyh=8;
						userh.getQ().setMp(userh.xyh);
						skill1.setToolTipText("<html>��Q��"+userh.getQ().getSkill()+"</html>");
					}
					if(userh.sjjR>0&&d>0) {
						if(d-4>=0) {
							UpdateJTextArea("��"+userh.getName()+"��������һ�����������ֵ���4���˺���"+"\n\n");
							d -= 4;
						}
						else {
							UpdateJTextArea("��"+userh.getName()+"��������һ�����������ֵ���"+d+"���˺���"+"\n\n");
							d = 0;
						}
						userh.sjjR--;
						if(userh.sjjR!=0) {
							userh.gzhl.setSuperpose("��������������"+userh.sjjR+"��");
						} else {
							userbuff.remove(userh.gzhl);
							repaint();
						}
					}
					if(userh.xyhE>0) {
						userh.xyhED+=d;
						userh.sgsl.setDescribe("��Ӣ����ȫ�����κ��˺���Ŀǰ�ѻ���"+userh.xyhED+"���˺���");
						d=0;
					}
					UpdateJTextArea("��"+userh.getName()+"���ܵ���"+d+"���������յ�ħ���˺���\n\n");
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
					UpdateJTextArea("��"+userh.getName()+"���ġ����ա�����ʱ�仹ʣ"+userh.yyQ+"���غϡ�\n\n");
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
							UpdateJTextArea("��"+enemyh.getName()+"��������һ�����������ֵ���4���˺���"+"\n\n");
							d -= 4;
						}
						else {
							UpdateJTextArea("��"+enemyh.getName()+"��������һ�����������ֵ���"+d+"���˺���"+"\n\n");
							d = 0;
						}
						enemyh.sjjR--;
						if(enemyh.sjjR!=0) {
							enemyh.gzhl.setSuperpose("��������������"+enemyh.sjjR+"��");
						} else {
							enemybuff.remove(enemyh.gzhl);
							repaint();
						}
					}
					if(enemyh.xyhE>0) {
						enemyh.xyhED+=d;
						d=0;
						enemyh.sgsl.setDescribe("��Ӣ����ȫ�����κ��˺���Ŀǰ�ѻ���"+enemyh.xyhED+"���˺���");
					}
					UpdateJTextArea("��"+enemyh.getName()+"���ܵ���"+d+"���������յ�ħ���˺���\n\n");
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
					UpdateJTextArea("��"+enemyh.getName()+"���ġ����ա�����ʱ�仹ʣ"+enemyh.yyQ+"���غϡ�\n\n");
					enemyh.lrzj.setRound(enemyh.yyQ);
				}
				else {
					enemybuff.remove(enemyh.lrzj);
					repaint();
				}
			}
			//л�ƺ�E
			if(userh.xyhE>0) {
				userh.xyhE--;
				if(userh.xyhE>0) {
					UpdateJTextArea("��"+userh.getName()+"���ġ�ʱ��ɳ©������ʱ�仹ʣ"+userh.xyhE+"���غϡ�\n\n");
					userh.sgsl.setDescribe("��Ӣ����ȫ�����κ��˺���Ŀǰ�ѻ���"+userh.xyhED+"���˺���");
					userh.sgsl.setRound(userh.xyhE);
					repaint();
				} else {
					if(userh.xyhED>0) {
						UpdateJTextArea("��"+userh.getName()+"���Ѿ��ͷ��ˡ�ʱ��ɳ©����\n\n");
						int d = (int)Math.round(userh.xyhED*1.6 - ((1-userh.getApp())*enemyh.getAdf()));
						if(d<0) d=0;
						balanceskill(userh, d);
						userh.xyhED=0;
					} else {
						UpdateJTextArea("��"+userh.getName()+"���ġ�ʱ��ɳ©�������飡\n\n");
					}
					userbuff.remove(userh.sgsl);
					repaint();
				}
			}
			if(enemyh.xyhE>0) {
				enemyh.xyhE--;
				if(enemyh.xyhE>0) {
					UpdateJTextArea("��"+enemyh.getName()+"���ġ�ʱ��ɳ©������ʱ�仹ʣ"+enemyh.xyhE+"���غϡ�\n\n");
					enemyh.sgsl.setDescribe("��Ӣ����ȫ�����κ��˺���Ŀǰ�ѻ���"+enemyh.xyhED+"���˺���");
					enemyh.sgsl.setRound(enemyh.xyhE);
					repaint();
				} else {
					if(enemyh.xyhED>0) {
						UpdateJTextArea("��"+enemyh.getName()+"���ͷ������ġ�ʱ��ɳ©����\n\n");
						int d = (int)Math.round(enemyh.xyhED*1.6 - ((1-enemyh.getApp())*userh.getAdf()));
						if(d<0) d=0;
						balanceskill(enemyh, d);
						enemyh.xyhED=0;
					} else {
						UpdateJTextArea("��"+enemyh.getName()+"���ġ�ʱ��ɳ©�������飡\n\n");
					}
					enemybuff.remove(enemyh.sgsl);
					repaint();
				}
			}
			//�ŷ�W�ж�������Ч
			if(userh.zfW) {
				new Thread() {
					@Override
					public void run() {
						int whenstart = r + 2;
						int whenstop = r + 3;// ���ü���ʧЧ�Ļغ�
						while(true) {
							if(rend == whenstart) {
								if (enemyh.yyzs) {
									eyyzs--;
									UpdateJTextArea("��" + enemyh.getName() + "�������ˡ�ҹ��֮������Ч���ֵ���һ�Ρ��ж����ޡ���\n\n");
								} else if (enemyh.fs) {
									UpdateJTextArea("��" + enemyh.getName() + "�������ˡ����ա���Ч���ֵ���һ�Ρ��ж����ޡ���\n\n");
								} else {
									enemyh.setIslimte(false);
									if (enemyh.xyhE > 0) {
										enemyh.xyhE = 0;
										if (enemyh.xyhED > 0) {
											UpdateJTextArea("��" + enemyh.getName() + "���ͷ������ġ�ʱ��ɳ©����\n\n");
											int d = (int) Math
													.round(enemyh.xyhED * 0.8 - ((1 - enemyh.getApp()) * userh.getAdf()));
											if (d < 0)
												d = 0;
											balanceskill(enemyh, d);
											enemyh.xyhED = 0;
										} else {
											UpdateJTextArea("��" + enemyh.getName() + "���ġ�ʱ��ɳ©�������飡\n\n");
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
							if (rend == whenstop) {// �غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
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
						int whenstop = r + 3;// ���ü���ʧЧ�Ļغ�
						while(true) {
							if(rend == whenstart) {
								if (userh.yyzs) {
									uyyzs--;
									UpdateJTextArea("��" + userh.getName() + "�������ˡ�ҹ��֮������Ч���ֵ���һ�Ρ��ж����ޡ���\n\n");
									if (uyyzs == 0) {
										if (userh.getZ().getName().equals("ҹ��֮��")) {
											setE(userh.getZ().getId(), 3);
											getUserEquip(userh.getZ().getId() + 20);
											userh.setZ(null);
											zb1.setIcon(
													new ImageIcon(this.getClass().getClassLoader().getResource("img/e1.png")));
											zb1.setToolTipText("���ѡ��һ��װ����������");
										} else if (userh.getX().getName().equals("ҹ��֮��")) {
											setE(userh.getX().getId(), 4);
											getUserEquip(userh.getX().getId() + 20);
											userh.setX(null);
											zb2.setIcon(
													new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
											zb2.setToolTipText("���ѡ��һ��װ����������");
										}
									}
								} else if (userh.fs) {
									UpdateJTextArea("��" + userh.getName() + "�������ˡ����ա���Ч���ֵ���һ�Ρ��ж����ޡ���\n\n");
								} else {
									userh.setIslimte(false);
									if (userh.xyhE > 0) {
										userh.xyhE = 0;
										if (userh.xyhED > 0) {
											UpdateJTextArea("��" + userh.getName() + "���Ѿ��ͷ��ˡ�ʱ��ɳ©����\n\n");
											int d = (int) Math
													.round(userh.xyhED * 0.8 - ((1 - userh.getApp()) * enemyh.getAdf()));
											if (d < 0)
												d = 0;
											balanceskill(userh, d);
											userh.xyhED = 0;
										} else {
											UpdateJTextArea("��" + userh.getName() + "���ġ�ʱ��ɳ©�������飡\n\n");
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
								sleep(700);// ������ʱ1������ж�
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						while (true) {
							if (rend == whenstop) {// �غ������������ü���ʧЧ���Ǹ��غ� ����ѭ��
								userh.setIslimte(true);
								userbuff.remove(userh.fzjj2);
								repaint();
								break;
							}
							try {
								sleep(700);// ������ʱ1������ж�
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
			}
			/*
			 * Skill Up ����ᾧ֮����������ǿ��
			 */
			if(!userh.jjzl) {
				if(herotime>=7||userh.damage>=20) {
					switch(userh.getId()) {
						case 1:{
							gsu = new GetSkillUp(Fight.this, userh);
							gsu.Inititem();
							UpdateJTextArea("��"+userh.getName()+"����������ɣ����ʱ�䣺"+herotime+"���غϡ�\n\n");
							pw3.write("��"+userh.getName()+"����������ɣ����ʱ�䣺"+herotime+"���غϡ�\n");
							pw3.flush();
							gsu.setVisible(true);
							break;
						}
						case 6:{
							gsu = new GetSkillUp(Fight.this, userh);
							gsu.Inititem();
							UpdateJTextArea("��"+userh.getName()+"����������ɣ����ʱ�䣺"+herotime+"���غϡ�\n\n");
							pw3.write("��"+userh.getName()+"����������ɣ����ʱ�䣺"+herotime+"���غϡ�\n");
							pw3.flush();
							gsu.setVisible(true);
							break;
						}
						case 9:{
							gsu = new GetSkillUp(Fight.this, userh);
							gsu.Inititem();
							UpdateJTextArea("��"+userh.getName()+"����������ɣ����ʱ�䣺"+herotime+"���غϡ�\n\n");
							pw3.write("��"+userh.getName()+"����������ɣ����ʱ�䣺"+herotime+"���غϡ�\n");
							pw3.flush();
							gsu.setVisible(true);
							break;
						}
						case 11:{
							gsu = new GetSkillUp(Fight.this, userh);
							gsu.Inititem();
							UpdateJTextArea("��"+userh.getName()+"����������ɣ����ʱ�䣺"+herotime+"���غϡ�\n\n");
							pw3.write("��"+userh.getName()+"����������ɣ����ʱ�䣺"+herotime+"���غϡ�\n");
							pw3.flush();
							gsu.setVisible(true);
							break;
						}
					}
				}
			}
			rend++; // �غϱ�־
		}
		
	}
	
	/**
	 * ��Ϸ��ͣ����
	 */
	
	public void setpause(int yn) { // 1=��ͣ 0=���
		if(yn==1) {
			if(selected==1) {
				if(pausetimes>0) {
					if(ispause) {
						UpdateJTextArea("��Ϸ�Ѵ�����ͣ״̬������������ʹ�� -r ���\n\n");
					} else {
						ispause = true;
						isuserpause = true;
						pausetimes--;
						new pausetimer(60).start();
						setE(7);
						UpdateJTextArea("��Ϸ�ѱ�����ͣ1���ӡ����� -r ��������ͣ������ʣ��"+pausetimes+"����ͣ���ᡶ��\n\n");
						pw3.write("��Ϸ�ѱ�"+user.getUsername()+"��"+userh.getName()+"����ͣ1���ӡ�����ʣ��"+pausetimes+"����ͣ���ᡶ��\n");
						pw3.flush();
					}
				} else {
					UpdateJTextArea("�����������Ѿ�û����ͣ�����ˣ�ÿ��ÿ�����3�λ��ᣩ��\n\n");
				}
			} else {
				UpdateJTextArea("�����ж�ʱ���ڣ��޷���ͣ��");
			}
		} else if(yn==0) {
			if(!ispause) {
				UpdateJTextArea("��Ϸ������ͣ״̬��\n\n");
			} else {
				if(isuserpause) {
					ispause = false;
					isuserpause = false;
					setE(8);
					UpdateJTextArea("��Ϸ�ѱ�������ͣ��\n\n");
					pw3.write("��Ϸ�ѱ�"+user.getUsername()+"��"+userh.getName()+"�������ͣ��\n");
					pw3.flush();
				} else {
					UpdateJTextArea("���޷�����Է�����ͣ�������ĵȺ�\n\n");
				}
			}
		}
		
	}
	
	void sur() { // Ͷ��
		if(r>=13) {
			winlose = false;
			setE(9);
			UpdateJTextArea("��Ͷ���ˣ��Է�����˱��α�����ʤ������Ϸ���ڻغϽ���ʱ������\n\n");
		} else {
			UpdateJTextArea("��13�غ�ǰ������Ͷ������Ŭ��������Ϸ��\n\n");
		}
	}
	
	void getHelp() {
		UpdateJTextArea("��ǰ�����������£�\n\n");
		UpdateJTextArea("-p ��ͣ��Ϸ\n-r �����ͣ\n-jh ����ᾧ֮��\n-mymax ��ѯӢ������\n-enemymax ��ѯ�Է�Ӣ������\n-lxs ������ħ��ŭ��ɱ����\n-xyh л�ƺ��ྻ������"+"\n\n");
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
		DecimalFormat df=new DecimalFormat("0.00");//���ñ���λ��
		BigDecimal bg = new BigDecimal(df.format((double)damage / hurt));
        rating = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        if(rating>3) rating=2.80 + (new Random().nextInt(10)*2/100);
		if(winlose) { // ʤ��
			if(user.getDjs()>0&&user.getAll()<=8&&user.getElo()<1800) {
				elo = (int)Math.round(rating*128);
			} else {
				if(user.getElo()>=enemy.getElo()) { // �ֱȶԷ���
					if(user.getElo()-enemy.getElo()<=800) { // �ֲ�С��800
						elo = (int)Math.round(rating*20);
					} else { // �ֲ����800
						elo = (int)Math.round(rating*10);
					}
				} else { // �ֱȶԷ���
					if(enemy.getElo()-user.getElo()<=800) { // �ֲ�С��800
						elo = (int)Math.round(rating*30);
					} else { // �ֲ����800
						elo = 10+(int)Math.round(rating*40);
					}
				}
			}
		} else { // ʧ��
			if(user.getDjs()>0&&user.getAll()<=8&&user.getElo()<1800) {
				elo = (int)Math.round(rating*28);
			} else {
				if(user.getElo()>=enemy.getElo()) { // �ֱȶԷ���
					if(user.getElo()-enemy.getElo()<=800) { // �ֲ�С��800
						elo = -50+(int)Math.round(rating*25);
					} else { // �ֲ����800
						elo = -50+(int)Math.round(rating*10);
					}
				} else { // �ֱȶԷ���
					if(enemy.getElo()-user.getElo()<=800) { // �ֲ�С��800
						elo = -30+(int)Math.round(rating*20);
					} else { // �ֲ����800
						elo = -10+(int)Math.round(rating*10);
					}
				}
			}
		}
	}
	
	String getRank() {
		if(user.getDjs()>0) {
			return "δ����";
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
		DecimalFormat df=new DecimalFormat("0.00");//���ñ���λ��
		BigDecimal bg = new BigDecimal(df.format((double)hurt / r));
        double enemyadr = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return enemyadr;
	}
	
	public void getEnemyEquip(int id) {
		if(id>=33&&id<=46) {//��װ��1
			enemyh.setZ(Config.Allitems.get(id-21));
		}
		if(id>=53&&id<=66) {//��װ��2
			enemyh.setX(Config.Allitems.get(id-41));
		}
		if(id>=73&&id<=86) {//ȡ��װ��1
			enemyh.setZ(null);
		}
		if(id>=93&&id<=106) {//ȡ��װ��2
			enemyh.setZ(null);
		}
		if(id>=113&&id<=126) {//��װ��
			
		}
		switch(id) {
			case 0:{//�غ���ʱ
				limitr+=5;
				UpdateJTextArea("��л"+enemy.getUsername()+"��"+enemyh.getName()+"�������ڷ��ף��ܻغ���������5��\n\n");
				break;
			}
			case 1:{//�ظ�ҩ
				if(enemyh.getHp()+2<=enemyhpt.getMaximum()) {
					enemyh.setHp(enemyh.getHp()+2);
				} else {
					enemyh.setHp(enemyhpt.getMaximum());
				}
				enemyhpt.setValue(enemyh.getHp());
				enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
				UpdateJTextArea("��"+enemyh.getName()+"���ظ���2������ֵ��\n\n");
				break;
			}
			case 4:{//���ս���
				UpdateJTextArea("��"+enemyh.getName()+"��ʹ���˵��ߡ��߼����ս��ҡ���"+"\n\n");
				UpdateJTextArea("��"+enemyh.getName()+"�������������غϻ�ÿ������ߣ�\n\n");
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
			case 5:{//�߼����ս���
				UpdateJTextArea("��"+enemyh.getName()+"��ʹ���˵��ߡ��߼����ս��ҡ���"+"\n\n");
				if(enemyh.getHp()+5<=enemyhpt.getMaximum()) {
					enemyh.setHp(enemyh.getHp()+5);
					hpp+=5;
				} else {
					hpp+=enemyhpt.getMaximum()-enemyh.getHp();
					enemyh.setHp(enemyhpt.getMaximum());
				}
				enemyhpt.setValue(enemyh.getHp());
				enemyhpt.setString(enemyh.getHp()+" / "+enemyhpt.getMaximum());
				UpdateJTextArea("��"+enemyh.getName()+"���ظ���5������ֵ�������������غϻ�ÿ������ߣ�\n\n");
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
			case 7:{//��ͣ
				ispause=true;
				new pausetimer(60).start();
				break;
			}
			case 8:{//�����ͣ
				ispause=false;
				break;
			}
			case 9:{//�Է�Ͷ��
				winlose=true;
				UpdateJTextArea("��"+enemyh.getName()+"����"+enemy.getUsername()+"��Ͷ���ˣ������˱��α�����ʤ����\n\n");
				break;
			}
			case 11:{//�ᾧ֮��
				enemyh.jhjj=1;
				UpdateEnemyHeroSkill();
				break;
			}
			case 12:{//�ᾧ֮��
				enemyh.jhjj=2;
				UpdateEnemyHeroSkill();
				break;
			}
			case 13:{//�ᾧ֮��
				enemyh.jhjj=3;
				UpdateEnemyHeroSkill();
				break;
			}
			case 14:{//
				break;
			}
			case 26:{//ҹ��֮��
				eyyzs+=3;
				UpdateJTextArea("��"+enemyh.getName()+"��������3�Ρ�ҹ��֮������ʹ�ô�����\n\n");
				break;
			}
			case 33:{//��������
				enemyh.setXdl(enemyh.getXdl()+2);
				enemyh.setMpp(enemyh.getMpp()+2);
				enemyh.zy=true;
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ��������ȡ���\n\n");
				break;
			}
			case 34:{//��������
				enemyh.setAtk(enemyh.getAtk()+3);
				enemyh.setMpp(enemyh.getMpp()+1);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ��������ȡ���\n\n");
				break;
			}
			case 35:{//����
				enemyh.setAtk(enemyh.getAtk()+5);
				enemyh.setAdp(enemyh.getAdp()+0.3);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�����-�������̡���\n\n");
				break;
			}
			case 36:{//ӥ�ǹ�
				enemyh.yjg=true;
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�ӥ�ǹ�����\n\n");
				break;
			}
			case 37:{//������ذ��
				enemyh.setAtk(enemyh.getAtk()+4);
				enemyh.setAdp(enemyh.getAdp()+0.2);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�������ذ�ס���\n\n");
				break;
			}
			case 38:{//��Ҷ����
				enemyh.setApp(enemyh.getApp()+0.4);
				enemyh.setMpp(enemyh.getMpp()+3);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ���Ҷ��������������\n\n");
				break;
			}
			case 39:{//�ƾ�֮ì
				enemyh.setAtk(enemyh.getAtk()+3);
				if(enemyh.pjzmcd==0) enemyh.pjzm=true;
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ��ƾ�֮ì����\n\n");
				break;
			}
			case 40:{//ά�����ȳ���
				enemyh.setDef(enemyh.getDef()+2);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�ά�����ȳ��ۡ���\n\n");
				break;
			}
			case 41:{//ʥ�¶���
				enemyh.setAdf(enemyh.getAdf()+2);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�ʥ�¶��񡿡�\n\n");
				break;
			}
			case 42:{//������֮��
				enemyh.jrz=true;
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�������֮�ܡ���\n\n");
				break;
			}
			case 43:{//�ػ�֮��
				enemyh.setHpp(enemyh.getHpp()+4);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ��ػ�֮�䡿��\n\n");
				break;
			}
			case 44:{//�;ù⻷
				enemyh.setMpp(enemyh.getMpp()+4);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ��;ù⻷����\n\n");
				break;
			}
			case 45:{//���
				enemyh.setXdl(enemyh.getXdl()+3);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�ѧ����Ļ�ա���\n\n");
				break;			
			}
			case 46:{//ҹ��֮��
				enemyh.yyzs=true;
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�ҹ��֮������\n\n");
				break;
			}
			case 53:{//��������
				enemyh.setXdl(enemyh.getXdl()+2);
				enemyh.setMpp(enemyh.getMpp()+2);
				enemyh.zy=true;
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ��������ȡ���\n\n");
				break;
			}
			case 54:{//��������
				enemyh.setAtk(enemyh.getAtk()+3);
				enemyh.setMpp(enemyh.getMpp()+1);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ��������ȡ���\n\n");
				break;
			}
			case 55:{//����
				enemyh.setAtk(enemyh.getAtk()+5);
				enemyh.setAdp(enemyh.getAdp()+0.3);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�����-�������̡���\n\n");
				break;
			}
			case 56:{//ӥ�ǹ�
				enemyh.yjg=true;
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�ӥ�ǹ�����\n\n");
				break;
			}
			case 57:{//������ذ��
				enemyh.setAtk(enemyh.getAtk()+4);
				enemyh.setAdp(enemyh.getAdp()+0.2);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�������ذ�ס���\n\n");
				break;
			}
			case 58:{//��Ҷ����
				enemyh.setApp(enemyh.getApp()+0.4);
				enemyh.setMpp(enemyh.getMpp()+3);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ���Ҷ��������������\n\n");
				break;
			}
			case 59:{//�ƾ�֮ì
				enemyh.setAtk(enemyh.getAtk()+3);
				if(enemyh.pjzmcd==0) enemyh.pjzm=true;
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ��ƾ�֮ì����\n\n");
				break;
			}
			case 60:{//ά�����ȳ���
				enemyh.setDef(enemyh.getDef()+2);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�ά�����ȳ��ۡ���\n\n");
				break;
			}
			case 61:{//ʥ�¶���
				enemyh.setAdf(enemyh.getAdf()+2);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�ʥ�¶��񡿡�\n\n");
				break;
			}
			case 62:{//������֮��
				enemyh.jrz=true;
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�������֮�ܡ���\n\n");
				break;
			}
			case 63:{//�ػ�֮��
				enemyh.setHpp(enemyh.getHpp()+4);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ��ػ�֮�䡿��\n\n");
				break;
			}
			case 64:{//�;ù⻷
				enemyh.setMpp(enemyh.getMpp()+4);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ��;ù⻷����\n\n");
				break;
			}
			case 65:{//���
				enemyh.setXdl(enemyh.getXdl()+3);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�ѧ����Ļ�ա���\n\n");
				break;			
			}
			case 66:{//ҹ��֮��
				enemyh.yyzs=true;
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�ҹ��֮������\n\n");
				break;
			}
			/**
			 * - ȡ������
			 */
			case 73:{//��������
				enemyh.setXdl(enemyh.getXdl()-2);
				enemyh.setMpp(enemyh.getMpp()-2);
				enemyh.zy=false;
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ��������ȡ���\n\n");
				break;
			}
			case 74:{//��������
				enemyh.setAtk(enemyh.getAtk()-3);
				enemyh.setMpp(enemyh.getMpp()-1);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ��������ȡ���\n\n");
				break;
			}
			case 75:{//����
				enemyh.setAtk(enemyh.getAtk()-5);
				enemyh.setAdp(enemyh.getAdp()-0.3);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ�����-�������̡���\n\n");
				break;
			}
			case 76:{//ӥ�ǹ�
				enemyh.yjg=true;
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ�ӥ�ǹ�����\n\n");
				break;
			}
			case 77:{//������ذ��
				enemyh.setAtk(enemyh.getAtk()-4);
				enemyh.setAdp(enemyh.getAdp()-0.2);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ�������ذ�ס���\n\n");
				break;
			}
			case 78:{//��Ҷ����
				enemyh.setApp(enemyh.getApp()-0.4);
				enemyh.setMpp(enemyh.getMpp()-3);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ���Ҷ��������������\n\n");
				break;
			}
			case 79:{//�ƾ�֮ì
				enemyh.setAtk(enemyh.getAtk()-3);
				enemyh.pjzm=false;
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ��ƾ�֮ì����\n\n");
				break;
			}
			case 80:{//ά�����ȳ���
				enemyh.setDef(enemyh.getDef()-2);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ�ά�����ȳ��ۡ���\n\n");
				break;
			}
			case 81:{//ʥ�¶���
				enemyh.setAdf(enemyh.getAdf()-2);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ�ʥ�¶��񡿡�\n\n");
				break;
			}
			case 82:{//������֮��
				enemyh.jrz=false;
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ�������֮�ܡ���\n\n");
				break;
			}
			case 83:{//�ػ�֮��
				enemyh.setHpp(enemyh.getHpp()-4);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ��ػ�֮�䡿��\n\n");
				break;
			}
			case 84:{//�;ù⻷
				enemyh.setMpp(enemyh.getMpp()-4);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ��;ù⻷����\n\n");
				break;
			}
			case 85:{//���
				enemyh.setXdl(enemyh.getXdl()-3);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ�ѧ����Ļ�ա���\n\n");
				break;			
			}
			case 86:{//ҹ��֮��
				enemyh.yyzs=false;
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ�ҹ��֮������\n\n");
				break;
			}
			case 93:{//��������
				enemyh.setXdl(enemyh.getXdl()-2);
				enemyh.setMpp(enemyh.getMpp()-2);
				enemyh.zy=false;
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ��������ȡ���\n\n");
				break;
			}
			case 94:{//��������
				enemyh.setAtk(enemyh.getAtk()-3);
				enemyh.setMpp(enemyh.getMpp()-1);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ��������ȡ���\n\n");
				break;
			}
			case 95:{//����
				enemyh.setAtk(enemyh.getAtk()-5);
				enemyh.setAdp(enemyh.getAdp()-0.3);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ�����-�������̡���\n\n");
				break;
			}
			case 96:{//ӥ�ǹ�
				enemyh.yjg=true;
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ�ӥ�ǹ�����\n\n");
				break;
			}
			case 97:{//������ذ��
				enemyh.setAtk(enemyh.getAtk()-4);
				enemyh.setAdp(enemyh.getAdp()-0.2);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ�������ذ�ס���\n\n");
				break;
			}
			case 98:{//��Ҷ����
				enemyh.setApp(enemyh.getApp()-0.4);
				enemyh.setMpp(enemyh.getMpp()-3);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ���Ҷ��������������\n\n");
				break;
			}
			case 99:{//�ƾ�֮ì
				enemyh.setAtk(enemyh.getAtk()-3);
				enemyh.pjzm=false;
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ��ƾ�֮ì����\n\n");
				break;
			}
			case 100:{//ά�����ȳ���
				enemyh.setDef(enemyh.getDef()-2);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ�ά�����ȳ��ۡ���\n\n");
				break;
			}
			case 101:{//ʥ�¶���
				enemyh.setAdf(enemyh.getAdf()-2);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ�ʥ�¶��񡿡�\n\n");
				break;
			}
			case 102:{//������֮��
				enemyh.jrz=false;
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ�������֮�ܡ���\n\n");
				break;
			}
			case 103:{//�ػ�֮��
				enemyh.setHpp(enemyh.getHpp()-4);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ��ػ�֮�䡿��\n\n");
				break;
			}
			case 104:{//�;ù⻷
				enemyh.setMpp(enemyh.getMpp()-4);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ��;ù⻷����\n\n");
				break;
			}
			case 105:{//���
				enemyh.setXdl(enemyh.getXdl()-3);
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ�ѧ����Ļ�ա���\n\n");
				break;			
			}
			case 106:{//ҹ��֮��
				enemyh.yyzs=false;
				UpdateJTextArea("��"+enemyh.getName()+"��ȡ��װ���ˡ�ҹ��֮������\n\n");
				break;
			}
			/**
			 * - ��װ��
			 */
			case 114:{//��������
				enemyh.setAtk(enemyh.getAtk()+3);
				enemyh.setMpp(enemyh.getMpp()+1);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ��������ȡ���\n\n");
				break;
			}
			case 115:{//����
				enemyh.setAtk(enemyh.getAtk()+5);
				enemyh.setAdp(enemyh.getAdp()+0.3);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�����-�������̡���\n\n");
				break;
			}
			case 116:{//ӥ�ǹ�
				enemyh.yjg=true;
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�ӥ�ǹ�����\n\n");
				break;
			}
			case 117:{//������ذ��
				enemyh.setAtk(enemyh.getAtk()+4);
				enemyh.setAdp(enemyh.getAdp()+0.2);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�������ذ�ס���\n\n");
				break;
			}
			case 118:{//��Ҷ����
				enemyh.setApp(enemyh.getApp()+0.4);
				enemyh.setMpp(enemyh.getMpp()+3);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ���Ҷ��������������\n\n");
				break;
			}
			case 120:{//ά�����ȳ���
				enemyh.setDef(enemyh.getDef()+2);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�ά�����ȳ��ۡ���\n\n");
				break;
			}
			case 121:{//ʥ�¶���
				enemyh.setAdf(enemyh.getAdf()+2);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�ʥ�¶��񡿡�\n\n");
				break;
			}
			case 123:{//�ػ�֮��
				enemyh.setHpp(enemyh.getHpp()+4);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ��ػ�֮�䡿��\n\n");
				break;
			}
			case 124:{//�;ù⻷
				enemyh.setMpp(enemyh.getMpp()+4);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ��;ù⻷����\n\n");
				break;
			}
			case 125:{//���
				enemyh.setXdl(enemyh.getXdl()+3);
				UpdateJTextArea("��"+enemyh.getName()+"��װ���ˡ�ѧ����Ļ�ա���\n\n");
				break;			
			}
		}
		usertx.setToolTipText(userh.getProperty());
		enemytx.setToolTipText(enemyh.getProperty());
		atk.setToolTipText("<html>ʹ����ͨ������<br /><br />��ʾ���������"+userh.getAtk()+"�������˺���</html>");
	}
	
	public void getUserEquip(int id) {
		switch(id) {
			case 13:{//��������
				userh.setXdl(userh.getXdl()+2);
				userh.setMpp(userh.getMpp()+2);
				userh.zy=true;
				UpdateJTextArea("��װ���ˡ��������ȡ���\n\n");
				break;
			}
			case 14:{//��������
				userh.setAtk(userh.getAtk()+3);
				userh.setMpp(userh.getMpp()+1);
				UpdateJTextArea("��װ���ˡ��������ȡ���\n\n");
				break;
			}
			case 15:{//����
				userh.setAtk(userh.getAtk()+5);
				userh.setAdp(userh.getAdp()+0.3);
				UpdateJTextArea("��װ���ˡ�����-�������̡���\n\n");
				break;
			}
			case 16:{//ӥ�ǹ�
				userh.yjg=true;
				UpdateJTextArea("��װ���ˡ�ӥ�ǹ�����\n\n");
				break;
			}
			case 17:{//������ذ��
				userh.setAtk(userh.getAtk()+4);
				userh.setAdp(userh.getAdp()+0.2);
				UpdateJTextArea("��װ���ˡ�������ذ�ס���\n\n");
				break;
			}
			case 18:{//��Ҷ����
				userh.setApp(userh.getApp()+0.4);
				userh.setMpp(userh.getMpp()+3);
				UpdateJTextArea("��װ���ˡ���Ҷ��������������\n\n");
				break;
			}
			case 19:{//�ƾ�֮ì
				userh.setAtk(userh.getAtk()+3);
				if(userh.pjzmcd==0) userh.pjzm=true;
				UpdateJTextArea("��װ���ˡ��ƾ�֮ì����\n\n");
				break;
			}
			case 20:{//ά�����ȳ���
				userh.setDef(userh.getDef()+2);
				UpdateJTextArea("��װ���ˡ�ά�����ȳ��ۡ���\n\n");
				break;
			}
			case 21:{//ʥ�¶���
				userh.setAdf(userh.getAdf()+2);
				UpdateJTextArea("��װ���ˡ�ʥ�¶��񡿡�\n\n");
				break;
			}
			case 22:{//������֮��
				userh.jrz=true;
				UpdateJTextArea("��װ���ˡ�������֮�ܡ���\n\n");
				break;
			}
			case 23:{//�ػ�֮��
				userh.setHpp(userh.getHpp()+4);
				UpdateJTextArea("��װ���ˡ��ػ�֮�䡿��\n\n");
				break;
			}
			case 24:{//�;ù⻷
				userh.setMpp(userh.getMpp()+4);
				UpdateJTextArea("��װ���ˡ��;ù⻷����\n\n");
				break;
			}
			case 25:{//���
				userh.setXdl(userh.getXdl()+3);
				UpdateJTextArea("��װ���ˡ�ѧ����Ļ�ա���\n\n");
				break;			
			}
			case 26:{//ҹ��֮��
				userh.yyzs=true;
				UpdateJTextArea("��װ���ˡ�ҹ��֮������\n\n");
				break;
			}
			/**
			 * - ȡ������
			 */
			case 33:{//��������
				userh.setXdl(userh.getXdl()-2);
				userh.setMpp(userh.getMpp()-2);
				userh.zy=false;
				UpdateJTextArea("��ȡ��װ���ˡ��������ȡ���\n\n");
				break;
			}
			case 34:{//��������
				userh.setAtk(userh.getAtk()-3);
				userh.setMpp(userh.getMpp()-1);
				UpdateJTextArea("��ȡ��װ���ˡ��������ȡ���\n\n");
				break;
			}
			case 35:{//����
				userh.setAtk(userh.getAtk()-5);
				userh.setAdp(userh.getAdp()-0.3);
				UpdateJTextArea("��ȡ��װ���ˡ�����-�������̡���\n\n");
				break;
			}
			case 36:{//ӥ�ǹ�
				userh.yjg=true;
				UpdateJTextArea("��ȡ��װ���ˡ�ӥ�ǹ�����\n\n");
				break;
			}
			case 37:{//������ذ��
				userh.setAtk(userh.getAtk()-4);
				userh.setAdp(userh.getAdp()-0.2);
				UpdateJTextArea("��ȡ��װ���ˡ�������ذ�ס���\n\n");
				break;
			}
			case 38:{//��Ҷ����
				userh.setApp(userh.getApp()-0.4);
				userh.setMpp(userh.getMpp()-3);
				UpdateJTextArea("��ȡ��װ���ˡ���Ҷ��������������\n\n");
				break;
			}
			case 39:{//�ƾ�֮ì
				userh.setAtk(userh.getAtk()-3);
				userh.pjzm=false;
				UpdateJTextArea("��ȡ��װ���ˡ��ƾ�֮ì����\n\n");
				break;
			}
			case 40:{//ά�����ȳ���
				userh.setDef(userh.getDef()-2);
				UpdateJTextArea("��ȡ��װ���ˡ�ά�����ȳ��ۡ���\n\n");
				break;
			}
			case 41:{//ʥ�¶���
				userh.setAdf(userh.getAdf()-2);
				UpdateJTextArea("��ȡ��װ���ˡ�ʥ�¶��񡿡�\n\n");
				break;
			}
			case 42:{//������֮��
				userh.jrz=false;
				UpdateJTextArea("��ȡ��װ���ˡ�������֮�ܡ���\n\n");
				break;
			}
			case 43:{//�ػ�֮��
				userh.setHpp(userh.getHpp()-4);
				UpdateJTextArea("��ȡ��װ���ˡ��ػ�֮�䡿��\n\n");
				break;
			}
			case 44:{//�;ù⻷
				userh.setMpp(userh.getMpp()-4);
				UpdateJTextArea("��ȡ��װ���ˡ��;ù⻷����\n\n");
				break;
			}
			case 45:{//���
				userh.setXdl(userh.getXdl()-3);
				UpdateJTextArea("��ȡ��װ���ˡ�ѧ����Ļ�ա���\n\n");
				break;			
			}
			case 46:{//ҹ��֮��
				userh.yyzs=false;
				UpdateJTextArea("��ȡ��װ���ˡ�ҹ��֮������\n\n");
				break;
			}
		}
		usertx.setToolTipText(userh.getProperty());
		enemytx.setToolTipText(enemyh.getProperty());
		atk.setToolTipText("<html>ʹ����ͨ������<br /><br />��ʾ���������"+userh.getAtk()+"�������˺���</html>");
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
				if(time==40) UpdateJTextArea("����40���Զ������ͣ��\n\n");
				if(time==20) UpdateJTextArea("����20���Զ������ͣ��\n\n");
				if(time==10) UpdateJTextArea("����10���Զ������ͣ��\n\n");
				if(time==5) UpdateJTextArea("����5���Զ������ͣ��\n\n");
				if(time==2) UpdateJTextArea("����2���Զ������ͣ��\n\n");
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
		
		DecimalFormat df = new DecimalFormat("0.00");// ���ñ���λ��
		
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
			DecimalFormat df=new DecimalFormat("0.00");//���ñ���λ��
			BigDecimal bd = new BigDecimal(df.format((double)temp.getBan()/temp.getPlay()));
			double banrate = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			Config.s.addHeroBan(temp.getId(), banrate);
		}
		for(int i=0;i<userpicked.size();i++) {
			temp = userpicked.get(i);
			temp.setPlay(temp.getPlay()+1);
			temp.setPick(temp.getPick()+1);
			DecimalFormat df=new DecimalFormat("0.00");//���ñ���λ��
			BigDecimal bd = new BigDecimal(df.format((double)temp.getPick()/temp.getPlay()));
			double pickrate = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			Config.s.addHeroPick(temp.getId(), pickrate);
		}
		if(winlose) {
			for(int i=0;i<userpicked.size();i++) {
				temp = userpicked.get(i);
				temp.setWin(temp.getWin()+1);
				DecimalFormat df=new DecimalFormat("0.00");//���ñ���λ��
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