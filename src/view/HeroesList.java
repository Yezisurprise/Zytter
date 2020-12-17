package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.border.TitledBorder;

import entity.Hero;
import entity.Item;
import entity.PlayerIcon;
import util.Config;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class HeroesList extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4005932630726503012L;

	PlayerIcon heroIcon,itemIcon,e1,e2,e3,e4;
	JPanel skillgroup,equipgroup,skill,item,walkthrough,herostatus;
	JButton skill1,skill2,skill3,skill4;
	JComboBox<String> heroselect;
	JComboBox<String> itemselect;
	
	TitledBorder describetb,itemtb;
	
	JLabel heroname,itemname;
	JLabel skilldescribe,herodescribe,itemdescribe,status;
	
	JLabel tip1,tip2;
	
	Hero hero = null;
	
	Item willitem = null;
	
	ArrayList<JButton> select = new ArrayList<JButton>();
	
	/**
	 * Create the frame.
	 */
	
	public HeroesList(Main main) {
		
		main.setEnabled(false);
		
		this.setTitle("Ӣ��ͼ�� "+Config.GlobalTitle);
		this.setUndecorated(true);
		this.setBackground(new Color(255,255,255,220));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(1250, 650);
		this.setLayout(null);
		
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		
		JLabel heroeslist = new JLabel("\u82F1\u96C4&\u7269\u54C1\u56FE\u9274");
		heroeslist.setFont(new Font("����", Font.BOLD, 35));
		heroeslist.setHorizontalAlignment(SwingConstants.CENTER);
		heroeslist.setBounds(10, 10, 1230, 53);
		this.add(heroeslist);
		
		heroselect = new JComboBox<String>();
		heroselect.addItem("��ѡ��Ӣ��");
		heroselect.addItem("����");
		heroselect.addItem("������");
		heroselect.addItem("��ʥŵ");
		heroselect.addItem("�����");
		heroselect.addItem("�ŷ�");
		heroselect.addItem("�C��ȴ");
		heroselect.addItem("л�ƺ�");
		heroselect.addItem("�ſ�ϫ");
		heroselect.addItem("֣����");
		heroselect.addItem("������");
		heroselect.addItem("�խZ��");
		heroselect.addItem("ά������");
		heroselect.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		heroselect.setBounds(166, 76, 135, 31);
		heroselect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(heroselect.getSelectedIndex()!=0) {
					hero = Config.Allheroes.get(heroselect.getSelectedIndex()-1);
				} else {
					hero = null;
				}
				if(hero!=null) {
					setcanSelect();
					getSkillIcon(hero.getId());
					getUserHeroIcon(hero.getId());
					repaint();
				}
			}
		});
		
		heroname = new JLabel("Ӣ�۳ƺ�");
		heroname.setHorizontalAlignment(SwingConstants.CENTER);
		heroname.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		heroname.setBounds(30, 117, 271, 28);
		this.add(heroname);
		
		itemname = new JLabel("��Ʒ����");
		itemname.setHorizontalAlignment(SwingConstants.CENTER);
		itemname.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		itemname.setBounds(623, 474, 281, 28);
		this.add(itemname);
		
		tip1 = new JLabel("\u9009\u62E9\u4E00\u4E2A\u82F1\u96C4");
		tip1.setFont(new Font("΢���ź�", Font.BOLD, 20));
		tip1.setHorizontalAlignment(SwingConstants.CENTER);
		tip1.setBounds(20, 76, 136, 28);
		this.add(tip1);
		
		tip2 = new JLabel("\u9009\u62E9\u4E00\u4E2A\u7269\u54C1");
		tip2.setHorizontalAlignment(SwingConstants.CENTER);
		tip2.setFont(new Font("΢���ź�", Font.BOLD, 20));
		tip2.setBounds(623, 436, 136, 28);
		this.add(tip2);
		this.add(heroselect);
		
		itemselect = new JComboBox<String>();
		itemselect.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		itemselect.setBounds(769, 436, 135, 31);
		this.add(itemselect);
		itemselect.addItem("��ѡ����Ʒ");
		itemselect.addItem("�غ���ʱ����");
		itemselect.addItem("�ظ�ҩ");
		itemselect.addItem("�лظ�ҩ");
		itemselect.addItem("��ظ�ҩ");
		itemselect.addItem("���ս���");
		itemselect.addItem("�߼����ս���");
		itemselect.addItem("ħ������I");
		itemselect.addItem("ħ������II");
		itemselect.addItem("ħ������III");
		itemselect.addItem("�ж�������");
		itemselect.addItem("˫��ҩ��");
		itemselect.addItem("ǿ��ҩˮ");
		itemselect.addItem("��������");
		itemselect.addItem("��������");
		itemselect.addItem("����-��������");
		itemselect.addItem("ӥ�ǹ�");
		itemselect.addItem("������ذ��");
		itemselect.addItem("��Ҷ����������");
		itemselect.addItem("�ƾ�֮ì");
		itemselect.addItem("ά�����ȳ���");
		itemselect.addItem("ʥ�¶���");
		itemselect.addItem("������֮��");
		itemselect.addItem("�ػ�֮��");
		itemselect.addItem("�;ù⻷");
		itemselect.addItem("ѧ����Ļ��");
		itemselect.addItem("ҹ��֮��");
		itemselect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(itemselect.getSelectedIndex()!=0) {
					willitem = Config.Allitems.get(itemselect.getSelectedIndex()-1);
				} else {
					willitem = null;
					itemdescribe.setText("��Ʒ����");
					itemname.setText("��Ʒ����");
					itemtb.setTitle("��Ʒ����");
				}
				if(willitem!=null) {
					getItem(willitem.getId());
					repaint();
				}
			}
		});
		
		heroIcon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());
		heroIcon.setBorder(new LineBorder(new Color(65, 105, 225), 5));
		heroIcon.setBounds(31, 156, 270, 270);
		this.add(heroIcon);
		
		skillgroup = new JPanel();
		skillgroup.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 5), "����[����������ΪQWER]", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(65, 105, 225)));
		skillgroup.setBounds(312, 73, 666, 168);
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
				if(hero!=null) {
					buttongroup(skill1, hero.getQ().getId());
				} else {
					buttongroup(skill1,0);
				}	
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
				if(hero!=null) {
					buttongroup(skill2, hero.getW().getId());
				} else {
					buttongroup(skill2,0);
				}	
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
				if(hero!=null) {
					buttongroup(skill3, hero.getE().getId());
				} else {
					buttongroup(skill3,0);
				}	
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
				if(hero!=null) {
					buttongroup(skill4, hero.getR().getId());
				} else {
					buttongroup(skill4,0);
				}				
			}
		});
		skillgroup.add(skill4);
		
		describetb = new TitledBorder(new LineBorder(new Color(65, 105, 225), 5), "��������", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(65, 105, 225));
		itemtb = new TitledBorder(new LineBorder(new Color(65, 105, 225), 5), "��Ʒ����", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(65, 105, 225));
		
		skill = new JPanel();
		skill.setBorder(describetb);
		skill.setBounds(311, 246, 667, 180);
		skill.setLayout(null);
		skill.setOpaque(false);
		this.add(skill);
		
		skilldescribe = new JLabel();
		skilldescribe.setBounds(15, 10, 645, 177);
		skilldescribe.setText("����˵��");
		skill.add(skilldescribe);
		
		herostatus = new JPanel();
		herostatus.setLayout(null);
		herostatus.setOpaque(false);
		herostatus.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 5), "Ӣ������", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(65, 105, 225)));
		herostatus.setBounds(989, 73, 251, 457);
		this.add(herostatus);
		
		status = new JLabel();
		status.setText("Ӣ������");
		status.setBounds(10, 22, 230, 425);
		status.setHorizontalAlignment(JLabel.CENTER);
		herostatus.add(status);
		
		walkthrough = new JPanel();
		walkthrough.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 5), "Ӣ�۹���", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(65, 105, 225)));
		walkthrough.setBounds(10, 436, 410, 204);
		walkthrough.setLayout(null);
		walkthrough.setOpaque(false);
		this.add(walkthrough);
		
		herodescribe = new JLabel();
		herodescribe.setBounds(15, 10, 385, 184);
		herodescribe.setText("Ӣ�۹���");
		walkthrough.add(herodescribe);
		
		itemIcon = new PlayerIcon((Image) null);
		itemIcon.setBorder(new LineBorder(new Color(65, 105, 225), 5));
		itemIcon.setBounds(914, 437, 64, 64);
		this.add(itemIcon);
		
		item = new JPanel();
		item.setLayout(null);
		item.setOpaque(false);
		item.setBorder(itemtb);
		item.setBounds(623, 511, 355, 129);
		this.add(item);
		
		itemdescribe = new JLabel();
		itemdescribe.setText("��Ʒ����");
		itemdescribe.setBounds(15, 20, 330, 99);
		item.add(itemdescribe);
		
		equipgroup = new JPanel();
		equipgroup.setOpaque(false);
		equipgroup.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 5), "�Ƽ�װ��", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(65, 105, 225)));
		equipgroup.setBounds(430, 436, 183, 204);
		this.add(equipgroup);
		equipgroup.setLayout(null);
		
		e1 = new PlayerIcon((Image) null);
		e1.setBorder(new LineBorder(new Color(65, 105, 225), 5));
		e1.setBounds(23, 44, 64, 64);
		equipgroup.add(e1);
		
		e2 = new PlayerIcon((Image) null);
		e2.setBorder(new LineBorder(new Color(65, 105, 225), 5));
		e2.setBounds(97, 44, 64, 64);
		equipgroup.add(e2);
		
		e3 = new PlayerIcon((Image) null);
		e3.setBorder(new LineBorder(new Color(65, 105, 225), 5));
		e3.setBounds(23, 118, 64, 64);
		equipgroup.add(e3);
		
		e4 = new PlayerIcon((Image) null);
		e4.setBorder(new LineBorder(new Color(65, 105, 225), 5));
		e4.setBounds(97, 118, 64, 64);
		equipgroup.add(e4);
		
		JButton quit = new JButton("");
		quit.setContentAreaFilled(false);
		quit.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/return.png")));
		quit.setBounds(984, 540, 256, 100);
		quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				main.setEnabled(true);
				dispose();
			}
		});
		this.add(quit);
		
		this.setLocationRelativeTo(null);
		
	}
	
	public void setcanSelect() {
		describetb.setTitle("��������");
		skilldescribe.setText("����˵��");
		for(int i=0;i<select.size();i++) {
			if(!select.get(i).isEnabled()) {
				select.get(i).setEnabled(true);
			} 
		}
	}
	
	public void buttongroup (JButton jb,int id) {
		if(id!=0) {
			for(int i=0;i<select.size();i++) {
				if(select.get(i).isEnabled()) {
					describetb.setTitle(Config.Allskills.get(id-1).getName()+"��ħ�����ģ�"+Config.Allskills.get(id-1).getMp()+"��");
					skilldescribe.setText("<html>"+Config.Allskills.get(id-1).getdescribe()+"</html>");
					select.get(i).setEnabled(false);
				} else {
					describetb.setTitle("��������");
					skilldescribe.setText("����˵��");
					select.get(i).setEnabled(true);
				}
				jb.setEnabled(true);
			}
		}
	}
	
	public void getSkillIcon(int x) {
		select.clear();
		switch(x) {
			case 1:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/yy/q.png")));
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/yy/w.png")));
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/yy/e.png")));
				skillgroup.add(skill3);
				select.add(skill3);
				select.remove(skill4);
				skillgroup.remove(skill4);
				break;
			}
			case 2:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/lxs/q.png")));
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/lxs/w.png")));
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/lxs/e.png")));
				skillgroup.add(skill3);
				select.add(skill3);
				skillgroup.remove(skill4);
				select.remove(skill4);
				break;
			}
			case 3:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/ysn/q.png")));
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/ysn/w.png")));
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
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/ltj/w.png")));
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/ltj/e.png")));
				skillgroup.add(skill3);
				select.add(skill3);
				select.remove(skill4);
				skillgroup.remove(skill4);
				break;
			}
			case 5:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zf/q.png")));
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zf/w.png")));
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zf/e.png")));
				skillgroup.add(skill3);
				select.add(skill3);
				select.remove(skill4);
				skillgroup.remove(skill4);
				break;
			}
			case 6:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/hyq/q.png")));
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/hyq/w.png")));
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/hyq/e.png")));
				skillgroup.add(skill3);
				select.add(skill3);
				skill4.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/hyq/r.png")));
				skillgroup.add(skill4);
				select.add(skill4);
				break;
			}
			case 7:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/xyh/q.png")));
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/xyh/w.png")));
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/xyh/e.png")));
				skillgroup.add(skill3);
				select.add(skill3);
				skillgroup.remove(skill4);
				select.remove(skill4);
				break;
			}
			case 8:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zkx/q.png")));
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zkx/w.png")));
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zkx/e.png")));
				skillgroup.add(skill3);
				select.add(skill3);
				skillgroup.remove(skill4);
				select.remove(skill4);
				break;
			}
			case 9:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zxy/q.png")));
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zxy/w.png")));
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zxy/e.png")));
				skillgroup.add(skill3);
				select.add(skill3);
				skill4.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/zxy/r.png")));
				skillgroup.add(skill4);
				select.add(skill4);
				break;
			}
			case 10:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/lm/q.png")));
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/lm/w.png")));
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
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/sjj/w.png")));
				skillgroup.add(skill2);
				select.add(skill2);
				skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/sjj/e.png")));
				skillgroup.add(skill3);
				select.add(skill3);
				skill4.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/sjj/r.png")));
				skillgroup.add(skill4);
				select.add(skill4);
				break;
			}
			case 12:{
				skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/w/q.png")));
				skillgroup.add(skill1);
				select.add(skill1);
				skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/skills/w/w.png")));
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
	
	public void getItem(int i) {
		this.remove(itemIcon);
		itemIcon = new PlayerIcon(Config.s.getItemImageIcon(i).getImage());
		itemIcon.setBounds(914, 437, 64, 64);
		itemname.setText(Config.Allitems.get(i-1).getValue1()+" "+Config.Allitems.get(i-1).getValue2());
		itemtb.setTitle(Config.Allitems.get(i-1).getName()+"��������ģ�"+Config.Allitems.get(i-1).getGold()+"��");
		if(i!=13) itemdescribe.setText("<html>"+Config.Allitems.get(i-1).getDescribe()+"</html>");
		else itemdescribe.setText("<html>"+"����2���ж�����2��ħ���ظ���<br/>Ψһ��������������˺�������2�غ����ÿ�غ�4��ħ���˺����ɵ��ӡ�"+"</html>");
		this.add(itemIcon);
	}
	
	public void getUserHeroIcon(int i) {
		this.remove(heroIcon);
		equipgroup.remove(e1);
		equipgroup.remove(e2);
		equipgroup.remove(e3);
		equipgroup.remove(e4);
		switch(i) {
			case 1:{
				heroIcon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());
				e1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hh.jpg")).getImage());
				e2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/xinye.jpg")).getImage());
				e3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hysz.jpg")).getImage());
				e4 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zysz.jpg")).getImage());
				e1.setToolTipText(Config.Allitems.get(24).getItem());
				e2.setToolTipText(Config.Allitems.get(17).getItem());
				e3.setToolTipText(Config.Allitems.get(13).getItem());
				e4.setToolTipText(Config.Allitems.get(12).getItem());
				heroname.setText("������");
				herodescribe.setText("<html>��������Ҫ���������ж�������������ѡ��װ���ǡ�ѧ����Ļ�ա������ʼ��㣺�Է�����ֵ�ĸ�λ��Ϊ9ʱ������֮����������Ϊ100%��������ֵ�ĸ�λ��Ϊ9ʱ����ɱ֮���������Ϊ100%"
						+ "�ж���Ϊ9ʱ����Ӱ֮�̵�������Ϊ100%������֮��ÿ�����ն��ᴥ�������ȡ���Ч����"+"</html>");
				break;
			}
			case 2:{
				heroIcon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());
				e1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/jrzzd.jpg")).getImage());
				e2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/txj.jpg")).getImage());
				e3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zzqy.jpg")).getImage());
				e4 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zzqy.jpg")).getImage());
				e1.setToolTipText(Config.Allitems.get(21).getItem());
				e2.setToolTipText(Config.Allitems.get(23).getItem());
				e3.setToolTipText(Config.Allitems.get(14).getItem());
				e4.setToolTipText(Config.Allitems.get(14).getItem());
				heroname.setText("ħ��");
				herodescribe.setText("<html>�����͵Ĵ򷨷��������ɣ�����ͻ���������Ϊ��ˮ�롣���Է�Ϊ�����ʱ���ɴ�������װ��������ɱ·�ߡ����Է����ǿӲ�������������������װ�Ĵ����������߽������·�ߡ�</html>");
				break;
			}
			case 3:{
				heroIcon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());
				e1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/lszbs.jpg")).getImage());
				e2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zzqy.jpg")).getImage());
				e3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zysz.jpg")).getImage());
				e4 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/txj.jpg")).getImage());
				e1.setToolTipText(Config.Allitems.get(16).getItem());
				e2.setToolTipText(Config.Allitems.get(14).getItem());
				e3.setToolTipText(Config.Allitems.get(12).getItem());
				e4.setToolTipText(Config.Allitems.get(23).getItem());
				heroname.setText("�ɴ���");
				herodescribe.setText("<html>��ʥŵ�������սʿ�����״��ǳ�����ʱ���������ǳ�̿����һ��С������ͬʱ���ǳ�̴����Ļ��ױ仯Ч��������������ƽA��ɸ�����˺�����Ӣ������ͨ����Ϊ����һ�������ǳ�̿�����Ϊ��������ʹ�á�</html>");
				break;
			}
			case 4:{
				heroIcon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());
				e1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/sofa.jpg")).getImage());
				e2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/lszbs.jpg")).getImage());
				e3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zzqy.jpg")).getImage());
				e4 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/pjzm.jpg")).getImage());
				e1.setToolTipText(Config.Allitems.get(15).getItem());
				e2.setToolTipText(Config.Allitems.get(16).getItem());
				e3.setToolTipText(Config.Allitems.get(14).getItem());
				e4.setToolTipText(Config.Allitems.get(18).getItem());
				herodescribe.setText("<html>�������������ʵ�õļ��ܣ���Դ��չ����͵�Ӣ��ʱ����ܸߡ��ڱ�ŭ�ṩ4�㹥����֮��������ܴ��һ�νϸߵ������˺�����װ��δ���Σ�����+7�㹥����������ͣ���������ʹ�öϹǽ���</html>");
				heroname.setText("������");
				break;
			}
			case 5:{
				heroIcon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());
				e1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/lszbs.jpg")).getImage());
				e2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zzqy.jpg")).getImage());
				e3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/xinye.jpg")).getImage());
				e4 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/txj.jpg")).getImage());
				e1.setToolTipText(Config.Allitems.get(16).getItem());
				e2.setToolTipText(Config.Allitems.get(14).getItem());
				e3.setToolTipText(Config.Allitems.get(17).getItem());
				e4.setToolTipText(Config.Allitems.get(23).getItem());
				heroname.setText("С��ɵ�");
				herodescribe.setText("<html>�ŷ��ȱ�����ں����߻����٣�����֮ն��Ҫȫ��������֧�֣������ŷ���������ҩƿʹ�á�һ��ʮ�������˺�����ת��һ��Ҫ������ã����չ��Ͳ�Ҫһ��ʮ���������������ͷ�����֮ն����֮������������ͨ��Ҫ������ͷš�</html>");
				break;
			}
			case 6:{
				heroIcon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());
				e1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hysz.jpg")).getImage());
				e2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zysz.jpg")).getImage());
				e3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/xinye.jpg")).getImage());
				e4 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zzqy.jpg")).getImage());
				e1.setToolTipText(Config.Allitems.get(13).getItem());
				e2.setToolTipText(Config.Allitems.get(12).getItem());
				e3.setToolTipText(Config.Allitems.get(17).getItem());
				e4.setToolTipText(Config.Allitems.get(14).getItem());
				heroname.setText("�ʱ���");
				herodescribe.setText("<html>�C��ȴ�Ļ���������Ȼ��R+W+A�������е��˺�Ϊ18�㡣�ڼ���ᾧ֮��ʱ����ѡ�������漣������֮�ۣ������漣��9����10�˺��ǳ��ɹۡ����������·�ߣ�����������֮�ۡ�һ��������Ϊ����ʵ���Բ��󣬲�����ʹ�á�</html>");
				break;
			}
			case 7:{
				heroIcon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());
				e1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zysz.jpg")).getImage());
				e2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/txj.jpg")).getImage());
				e3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/xinye.jpg")).getImage());
				e4 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/xinye.jpg")).getImage());
				e1.setToolTipText(Config.Allitems.get(12).getItem());
				e2.setToolTipText(Config.Allitems.get(23).getItem());
				e3.setToolTipText(Config.Allitems.get(17).getItem());
				e4.setToolTipText(Config.Allitems.get(17).getItem());
				heroname.setText("������Ů");
				herodescribe.setText("<html>л�ƺ��ĺ��ļ�������Բ�ط������Խ���ʹ����Բ�ط���˫���غϵĽྻ֮�飬�˺��ǳ��ɹۡ�ʱ��ɳ©�͵����غϵĽྻ֮����ǿ��л�ƺ�����������������ʱ��ɳ©��������ȱ�㣨�ܿ��ƻ��Զ����飩������������Ϻ����ױ���ԡ�</html>");
				break;
			}
			case 8:{
				heroIcon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());
				e1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zysz.jpg")).getImage());
				e2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/xinye.jpg")).getImage());
				e3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/shzj.jpg")).getImage());
				e4 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/txj.jpg")).getImage());
				e1.setToolTipText(Config.Allitems.get(12).getItem());
				e2.setToolTipText(Config.Allitems.get(17).getItem());
				e3.setToolTipText(Config.Allitems.get(22).getItem());
				e4.setToolTipText(Config.Allitems.get(23).getItem());
				heroname.setText("��ѩŮ��");
				herodescribe.setText("<html>ϫ֮����Ҫ�ϳ�3�غϲ���ʹ�ã������ſ�ϫ��ǰ���غ������Q��W��ϫ֮����һ��Ҫ�һ����ͷţ�������Զ�޷���ɱ�Է�������������·�ߣ����ſ�ϫ��˫�ظ����ػ�֮����;ù⻷����״̬���Ǻֲܿ��ģ�"+
						"���Գ�װ���������˫�ظ�����</html>");
				break;
			}
			case 9:{
				heroIcon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());
				e1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zysz.jpg")).getImage());
				e2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/jrzzd.jpg")).getImage());
				e3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/shzj.jpg")).getImage());
				e4 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/xinye.jpg")).getImage());
				e1.setToolTipText(Config.Allitems.get(12).getItem());
				e2.setToolTipText(Config.Allitems.get(21).getItem());
				e3.setToolTipText(Config.Allitems.get(22).getItem());
				e4.setToolTipText(Config.Allitems.get(17).getItem());
				heroname.setText("�ų�");
				herodescribe.setText("<html>֣����ֻ��һ�����Ǽ�����������ܣ������غϱ��������ͷ����ǣ�˫���غϿ��ԳԵ��ߺ��ͷ���Դ����һ����������ΪĳЩ����Ӣ�������Ƶģ�����ͨ����ʹ�á��������װδ���Σ�������ʹ������֮�����غϽ����޷���������</html>");
				break;
			}
			case 10:{
				heroIcon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());
				e1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/lszbs.jpg")).getImage());
				e2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zzqy.jpg")).getImage());
				e3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/sofa.jpg")).getImage());
				e4 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/pjzm.jpg")).getImage());
				e1.setToolTipText(Config.Allitems.get(16).getItem());
				e2.setToolTipText(Config.Allitems.get(14).getItem());
				e3.setToolTipText(Config.Allitems.get(15).getItem());
				e4.setToolTipText(Config.Allitems.get(18).getItem());
				heroname.setText("������");
				herodescribe.setText("<html>�������ڴ��������������£�ֻ��һ�������ǡ�����ʹ�õġ����¹⽣�ǲ�������������������˺��ģ��ڶԷ�Ѫ���ܳ�ԣʱ������ѡ���¹⽣�����Է�Ѫ�����٣����߶Է������׽ϵͣ������������ߵ�����£�ʹ�ý�������׵ġ�</html>");
				break;
			}
			case 11:{
				heroIcon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());
				e1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hysz.jpg")).getImage());
				e2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/xinye.jpg")).getImage());
				e3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zzqy.jpg")).getImage());
				e4 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zzqy.jpg")).getImage());
				e1.setToolTipText(Config.Allitems.get(13).getItem());
				e2.setToolTipText(Config.Allitems.get(17).getItem());
				e3.setToolTipText(Config.Allitems.get(14).getItem());
				e4.setToolTipText(Config.Allitems.get(14).getItem());
				heroname.setText("��������");
				herodescribe.setText("<html>�խZ����ȫ�ܵķ�ʦսʿ�������������һ��ʼ���ͷš���㽣+�ѷ���չ�����õ�һ�����У�����˺��ɴ�27�㡣�խZ��ͬ���߱�������Ӣ��ͷ�۵ġ����֡����ܣ�������+�������ֵļ�ǿ�棬��ϧ���ǣ�����+ֻ�ܳ���2�غϡ�</html>");
				break;
			}
			case 12:{
				heroIcon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());
				e1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hysz.jpg")).getImage());
				e2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/lszbs.jpg")).getImage());
				e3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zzqy.jpg")).getImage());
				e4 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zzqy.jpg")).getImage());
				e1.setToolTipText(Config.Allitems.get(13).getItem());
				e2.setToolTipText(Config.Allitems.get(16).getItem());
				e3.setToolTipText(Config.Allitems.get(14).getItem());
				e4.setToolTipText(Config.Allitems.get(14).getItem());
				heroname.setText("��輧");
				herodescribe.setText("<html>ά�������Ǵ����չ�Ӣ�ۣ���������ʥ����չ���ʥ��Ĺ����������Ͼ�ֻ��ά��һ�غϣ����ʥ��󲻽��չ������˷ѡ�������ΪĳЩӢ�������Ƶļ��ܣ�һ���ò�����һֱ�չ������¶��ˡ�</html>");
				break;
			}
		}
		heroIcon.setToolTipText(hero.getDescribe());
		heroIcon.setBorder(new LineBorder(new Color(65, 105, 225), 5));
		heroIcon.setBounds(31, 156, 270, 270);
		e1.setBounds(23, 44, 64, 64);
		equipgroup.add(e1);
		e2.setBounds(97, 44, 64, 64);
		equipgroup.add(e2);
		e3.setBounds(23, 118, 64, 64);
		equipgroup.add(e3);
		e4.setBounds(97, 118, 64, 64);
		equipgroup.add(e4);
		DecimalFormat df = new DecimalFormat("#0.#");
		status.setText("<html><p align=\"center\">"+"���ݽ���"+hero.getPlay()+"������<br/>�������Ӣ��ͳ������<br/><br/>"+"���ô�����"+hero.getBan()+"��"+df.format(hero.getBanrate()*100)+"%��<br/>"+
				"ѡ�ô�����"+hero.getPick()+"��"+df.format(hero.getPickrate()*100)+"%��<br/>"+"ʤ��������"+hero.getWin()+"��"+df.format(hero.getWinrate()*100)+"%��<br/><br/>"+
				"ƽ���ϳ�ʱ�䣺"+hero.getTime()+"���غϣ�<br/>"+"ƽ������˺���"+df.format(hero.getD())+"<br/>"+"ƽ���غ��˺���"+df.format(hero.getAdr())+"<br/><br/>"+"</p></html>");
		this.add(heroIcon);
	}
	
}
