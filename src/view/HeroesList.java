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
		
		this.setTitle("英雄图鉴 "+Config.GlobalTitle);
		this.setUndecorated(true);
		this.setBackground(new Color(255,255,255,220));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(1250, 650);
		this.setLayout(null);
		
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		
		JLabel heroeslist = new JLabel("\u82F1\u96C4&\u7269\u54C1\u56FE\u9274");
		heroeslist.setFont(new Font("等线", Font.BOLD, 35));
		heroeslist.setHorizontalAlignment(SwingConstants.CENTER);
		heroeslist.setBounds(10, 10, 1230, 53);
		this.add(heroeslist);
		
		heroselect = new JComboBox<String>();
		heroselect.addItem("请选择英雄");
		heroselect.addItem("奕阳");
		heroselect.addItem("刘晓释");
		heroselect.addItem("杨圣诺");
		heroselect.addItem("罗天杰");
		heroselect.addItem("张枫");
		heroselect.addItem("郈与却");
		heroselect.addItem("谢悠涵");
		heroselect.addItem("张可汐");
		heroselect.addItem("郑心予");
		heroselect.addItem("刘珂明");
		heroselect.addItem("苏璟静");
		heroselect.addItem("维多利娜");
		heroselect.setFont(new Font("微软雅黑", Font.PLAIN, 20));
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
		
		heroname = new JLabel("英雄称号");
		heroname.setHorizontalAlignment(SwingConstants.CENTER);
		heroname.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		heroname.setBounds(30, 117, 271, 28);
		this.add(heroname);
		
		itemname = new JLabel("物品类型");
		itemname.setHorizontalAlignment(SwingConstants.CENTER);
		itemname.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		itemname.setBounds(623, 474, 281, 28);
		this.add(itemname);
		
		tip1 = new JLabel("\u9009\u62E9\u4E00\u4E2A\u82F1\u96C4");
		tip1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		tip1.setHorizontalAlignment(SwingConstants.CENTER);
		tip1.setBounds(20, 76, 136, 28);
		this.add(tip1);
		
		tip2 = new JLabel("\u9009\u62E9\u4E00\u4E2A\u7269\u54C1");
		tip2.setHorizontalAlignment(SwingConstants.CENTER);
		tip2.setFont(new Font("微软雅黑", Font.BOLD, 20));
		tip2.setBounds(623, 436, 136, 28);
		this.add(tip2);
		this.add(heroselect);
		
		itemselect = new JComboBox<String>();
		itemselect.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		itemselect.setBounds(769, 436, 135, 31);
		this.add(itemselect);
		itemselect.addItem("请选择物品");
		itemselect.addItem("回合延时道具");
		itemselect.addItem("回复药");
		itemselect.addItem("中回复药");
		itemselect.addItem("大回复药");
		itemselect.addItem("复苏胶囊");
		itemselect.addItem("高级复苏胶囊");
		itemselect.addItem("魔力填充剂I");
		itemselect.addItem("魔力填充剂II");
		itemselect.addItem("魔力填充剂III");
		itemselect.addItem("行动力胶囊");
		itemselect.addItem("双抗药贴");
		itemselect.addItem("强化药水");
		itemselect.addItem("紫月神杖");
		itemselect.addItem("红月神杖");
		itemselect.addItem("长剑-朝醉青烟");
		itemselect.addItem("鹰角弓");
		itemselect.addItem("狩猎者匕首");
		itemselect.addItem("新叶传教者手札");
		itemselect.addItem("破军之矛");
		itemselect.addItem("维多利娜长袍");
		itemselect.addItem("圣月斗篷");
		itemselect.addItem("坚韧者之盾");
		itemselect.addItem("守护之戒");
		itemselect.addItem("耐久光环");
		itemselect.addItem("学生会的会徽");
		itemselect.addItem("夜宴之声");
		itemselect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(itemselect.getSelectedIndex()!=0) {
					willitem = Config.Allitems.get(itemselect.getSelectedIndex()-1);
				} else {
					willitem = null;
					itemdescribe.setText("物品描述");
					itemname.setText("物品类型");
					itemtb.setTitle("物品描述");
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
		skillgroup.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 5), "技能[从左到右依次为QWER]", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(65, 105, 225)));
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
		
		describetb = new TitledBorder(new LineBorder(new Color(65, 105, 225), 5), "技能名字", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(65, 105, 225));
		itemtb = new TitledBorder(new LineBorder(new Color(65, 105, 225), 5), "物品描述", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(65, 105, 225));
		
		skill = new JPanel();
		skill.setBorder(describetb);
		skill.setBounds(311, 246, 667, 180);
		skill.setLayout(null);
		skill.setOpaque(false);
		this.add(skill);
		
		skilldescribe = new JLabel();
		skilldescribe.setBounds(15, 10, 645, 177);
		skilldescribe.setText("技能说明");
		skill.add(skilldescribe);
		
		herostatus = new JPanel();
		herostatus.setLayout(null);
		herostatus.setOpaque(false);
		herostatus.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 5), "英雄数据", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(65, 105, 225)));
		herostatus.setBounds(989, 73, 251, 457);
		this.add(herostatus);
		
		status = new JLabel();
		status.setText("英雄数据");
		status.setBounds(10, 22, 230, 425);
		status.setHorizontalAlignment(JLabel.CENTER);
		herostatus.add(status);
		
		walkthrough = new JPanel();
		walkthrough.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 5), "英雄攻略", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(65, 105, 225)));
		walkthrough.setBounds(10, 436, 410, 204);
		walkthrough.setLayout(null);
		walkthrough.setOpaque(false);
		this.add(walkthrough);
		
		herodescribe = new JLabel();
		herodescribe.setBounds(15, 10, 385, 184);
		herodescribe.setText("英雄攻略");
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
		itemdescribe.setText("物品描述");
		itemdescribe.setBounds(15, 20, 330, 99);
		item.add(itemdescribe);
		
		equipgroup = new JPanel();
		equipgroup.setOpaque(false);
		equipgroup.setBorder(new TitledBorder(new LineBorder(new Color(65, 105, 225), 5), "推荐装备", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(65, 105, 225)));
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
		describetb.setTitle("技能名字");
		skilldescribe.setText("技能说明");
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
					describetb.setTitle(Config.Allskills.get(id-1).getName()+"（魔法消耗："+Config.Allskills.get(id-1).getMp()+"）");
					skilldescribe.setText("<html>"+Config.Allskills.get(id-1).getdescribe()+"</html>");
					select.get(i).setEnabled(false);
				} else {
					describetb.setTitle("技能名字");
					skilldescribe.setText("技能说明");
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
		itemtb.setTitle(Config.Allitems.get(i-1).getName()+"（金币消耗："+Config.Allitems.get(i-1).getGold()+"）");
		if(i!=13) itemdescribe.setText("<html>"+Config.Allitems.get(i-1).getDescribe()+"</html>");
		else itemdescribe.setText("<html>"+"增加2点行动力和2点魔法回复。<br/>唯一被动：技能造成伤害后连续2回合造成每回合4点魔法伤害，可叠加。"+"</html>");
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
				heroname.setText("概率论");
				herodescribe.setText("<html>奕阳最重要的属性是行动力，所以他首选的装备是【学生会的会徽】。概率计算：对方生命值的个位数为9时，烈日之箭的命中率为100%；你生命值的个位数为9时，屠杀之风的命中率为100%"
						+ "行动力为9时，暗影之刺的命中率为100%。烈日之箭每次灼烧都会触发【神杖】的效果。"+"</html>");
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
				heroname.setText("魔王");
				herodescribe.setText("<html>刘晓释的打法分两种流派，界限突破这个技能为分水岭。当对方为软输出时，可穿戴防御装备，走秒杀路线。当对方输出强硬，并且自身有物理输出装的储备，建议走解放真名路线。</html>");
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
				heroname.setText("派大星");
				herodescribe.setText("<html>杨圣诺是万金油战士。在首次星辰陨落时，搭配新星冲刺可完成一次小爆发。同时新星冲刺带来的护甲变化效果，可以立即接平A造成更多的伤害。该英雄以普通攻击为主，一技能新星冲刺可以作为防御技能使用。</html>");
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
				herodescribe.setText("<html>闪现是罗天杰最实用的技能，面对纯普攻类型的英雄时收益很高。在暴怒提供4点攻击力之后，罗天杰能打出一次较高的物理伤害。若装备未成形（至少+7点攻击力才算成型），不建议使用断骨剑。</html>");
				heroname.setText("闪现人");
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
				heroname.setText("小李飞刀");
				herodescribe.setText("<html>张枫的缺点在于耗蓝高回蓝少，审判之斩需要全部蓝量的支持，所以张枫可以配合蓝药瓶使用。一秒十三刀的伤害类型转换一定要灵活运用，能普攻就不要一秒十三刀，留蓝量来释放审判之斩。风之结界的连续控制通常要看情况释放。</html>");
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
				heroname.setText("资本家");
				herodescribe.setText("<html>郈与却的基础连招仍然是R+W+A，该连招的伤害为18点。在激活结晶之力时尽量选择星月奇迹和云霄之巅，星月奇迹的9耗蓝10伤害非常可观。如果是物理路线，就升级云霄之巅。一技能先入为主的实用性不大，不建议使用。</html>");
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
				heroname.setText("青蛙少女");
				herodescribe.setText("<html>谢悠涵的核心技能是天圆地方。可以交替使用天圆地方和双数回合的洁净之灵，伤害非常可观。时光沙漏和单数回合的洁净之灵增强了谢悠涵的生存能力，但是时光沙漏具有致命缺点（受控制会自动破碎），因此在阵容上很容易被针对。</html>");
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
				heroname.setText("冰雪女王");
				herodescribe.setText("<html>汐之抉择要上场3回合才能使用，所以张可汐的前两回合最好先Q后W。汐之抉择一定要找机会释放，否则将永远无法击杀对方（除非走物理路线）。张可汐在双回复（守护之戒和耐久光环）的状态下是很恐怖的，"+
						"所以出装方向可以向双回复靠。</html>");
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
				heroname.setText("团宠");
				herodescribe.setText("<html>郑心予只有一个流星技能是输出技能，单数回合必须留着释放流星，双数回合可以吃道具和释放心源神域。一技能礼赞是为某些物理英雄量身定制的，所以通常不使用。如果回蓝装未成形，不建议使用予恋之花（回合结束无法回蓝）。</html>");
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
				heroname.setText("二刀流");
				herodescribe.setText("<html>刘珂明在纯物理输出的情况下，只有一个技能是“可以使用的”。月光剑是不依靠自身攻击力来造成伤害的，在对方血量很充裕时，可以选择月光剑。而对方血量较少，或者对方物理护甲较低，且自身攻击力高的情况下，使用剑舞是最靠谱的。</html>");
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
				heroname.setText("公主殿下");
				herodescribe.setText("<html>苏璟静是全能的法师战士。公主号令最好一开始就释放。光炽剑+裂缝接普攻是最好的一套连招，最高伤害可达27点。苏璟静同样具备让物理英雄头疼的“闪现”技能，而闪现+则是闪现的加强版，可惜的是，闪现+只能持续2回合。</html>");
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
				heroname.setText("大歌姬");
				herodescribe.setText("<html>维多利娜是纯粹普攻英雄，基本连招圣歌接普攻。圣歌的攻击力基本上就只能维持一回合，因此圣歌后不接普攻就是浪费。神谕是为某些英雄量身定制的技能，一般用不到，一直普攻就完事儿了。</html>");
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
		status.setText("<html><p align=\"center\">"+"根据近期"+hero.getPlay()+"场比赛<br/>所整理的英雄统计数据<br/><br/>"+"禁用次数："+hero.getBan()+"（"+df.format(hero.getBanrate()*100)+"%）<br/>"+
				"选用次数："+hero.getPick()+"（"+df.format(hero.getPickrate()*100)+"%）<br/>"+"胜利次数："+hero.getWin()+"（"+df.format(hero.getWinrate()*100)+"%）<br/><br/>"+
				"平均上场时间："+hero.getTime()+"（回合）<br/>"+"平均造成伤害："+df.format(hero.getD())+"<br/>"+"平均回合伤害："+df.format(hero.getAdr())+"<br/><br/>"+"</p></html>");
		this.add(heroIcon);
	}
	
}
