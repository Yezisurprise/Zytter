package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import entity.Hero;
import entity.Item;
import entity.PlayerIcon;
import entity.User;
import util.Config;

public class Store extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3951211588674801070L;
	
	User user;
	
	Hero hero;
	
	Item item = null;
	
	Item willequip = null;
	
	ArrayList<Item> djh;
	
	ArrayList<JButton> select = new ArrayList<JButton>();
	
	JButton addround,hp1,hp2,hp3,fs1,fs2,mp1,mp2,mp3,xdl,sk,qh;
	JButton zysz,hysz,slz,xinye,zzqy,sofa,pjzm,wdln,sydp,jrzzd,shzj,txj,hh,yyzs;
	
	JPanel buy,have,icon;	
	JLabel tip,name,usergold,time;
	JButton submit,e1,e2;
	
	TitledBorder tb;
	
	Fight fight;
	int r,remain,gold;
	
	int hp=0;//最多买1个回复药
	boolean sy=false;//最多买1次回合延时
	
	public Store(Fight fight,int r,int remain,int gold,ArrayList<Item> djh,User user,Hero hero) {
		
		this.fight = fight;
		this.r=r;
		this.remain = remain;
		this.gold = gold;
		this.djh = djh;
		this.user = user;
		this.hero = hero;
		
		this.setTitle("Zytter Store");
		this.setUndecorated(true);
		this.setBackground(new Color(255,255,255,220));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(1000, 550);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		
		JLabel market = new JLabel("商店");
		market.setForeground(new Color(240, 128, 128));
		market.setHorizontalAlignment(SwingConstants.CENTER);
		market.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		market.setBounds(10, 10, 133, 63);
		this.add(market);
		
		usergold = new JLabel("剩余金币："+this.gold);
		usergold.setForeground(new Color(222, 184, 135));
		usergold.setHorizontalAlignment(SwingConstants.RIGHT);
		usergold.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		usergold.setBounds(702, 10, 288, 63);
		this.add(usergold);
		
		time = new JLabel("还有"+this.remain+"秒开始第"+this.r+"回合");
		time.setForeground(new Color(255, 69, 0));
		time.setHorizontalAlignment(SwingConstants.CENTER);
		time.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		time.setBounds(153, 10, 539, 63);
		this.add(time);
		
		buy = new JPanel();
		buy.setBorder(new TitledBorder(new LineBorder(new Color(240, 128, 128), 5), "可供购买的物品列表", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(240, 128, 128)));
		buy.setBounds(10, 83, 656, 403);
		this.add(buy);
		buy.setLayout(new GridLayout(5, 6, 0, 0));
		
		addround = new JButton("");
		addround.setContentAreaFilled(false);
		addround.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/ys.jpg")));
		buy.add(addround);
		select.add(addround);
		
		hp1 = new JButton("");
		hp1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hp1.jpg")));
		hp1.setContentAreaFilled(false);
		buy.add(hp1);
		select.add(hp1);
		
		hp2 = new JButton("");
		hp2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hp2.jpg")));
		hp2.setContentAreaFilled(false);
		buy.add(hp2);
		select.add(hp2);
		
		hp3 = new JButton("");
		hp3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hp3.jpg")));
		hp3.setContentAreaFilled(false);
		buy.add(hp3);
		select.add(hp3);
		
		fs1 = new JButton("");
		fs1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/fs1.jpg")));
		fs1.setContentAreaFilled(false);
		buy.add(fs1);
		select.add(fs1);
		
		fs2 = new JButton("");
		fs2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/fs2.jpg")));
		fs2.setContentAreaFilled(false);
		buy.add(fs2);
		select.add(fs2);
		
		mp1 = new JButton("");
		mp1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/mp1.jpg")));
		mp1.setContentAreaFilled(false);
		buy.add(mp1);
		select.add(mp1);
		
		mp2 = new JButton("");
		mp2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/mp2.jpg")));
		mp2.setContentAreaFilled(false);
		buy.add(mp2);
		select.add(mp2);
		
		mp3 = new JButton("");
		mp3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/mp3.jpg")));
		mp3.setContentAreaFilled(false);
		buy.add(mp3);
		select.add(mp3);
		
		xdl = new JButton("");
		xdl.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/xdl.jpg")));
		xdl.setContentAreaFilled(false);
		buy.add(xdl);
		select.add(xdl);
		
		sk = new JButton("");
		sk.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/skys.jpg")));
		sk.setContentAreaFilled(false);
		buy.add(sk);
		select.add(sk);
		
		qh = new JButton("");
		qh.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/qhys.jpg")));
		qh.setContentAreaFilled(false);
		buy.add(qh);
		select.add(qh);
		
		zysz = new JButton("");
		zysz.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zysz.jpg")));
		zysz.setContentAreaFilled(false);
		buy.add(zysz);
		select.add(zysz);
		
		hysz = new JButton("");
		hysz.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hysz.jpg")));
		hysz.setContentAreaFilled(false);
		buy.add(hysz);
		select.add(hysz);
		
		zzqy = new JButton("");
		zzqy.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zzqy.jpg")));
		zzqy.setContentAreaFilled(false);
		buy.add(zzqy);
		select.add(zzqy);
		
		sofa = new JButton("");
		sofa.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/sofa.jpg")));
		sofa.setContentAreaFilled(false);
		buy.add(sofa);
		select.add(sofa);
		
		slz = new JButton("");
		slz.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/lszbs.jpg")));
		slz.setContentAreaFilled(false);
		buy.add(slz);
		select.add(slz);
		
		xinye = new JButton("");
		xinye.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/xinye.jpg")));
		xinye.setContentAreaFilled(false);
		buy.add(xinye);
		select.add(xinye);
		
		pjzm = new JButton("");
		pjzm.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/pjzm.jpg")));
		pjzm.setContentAreaFilled(false);
		buy.add(pjzm);
		select.add(pjzm);
		
		wdln = new JButton("");
		wdln.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/wdln.jpg")));
		wdln.setContentAreaFilled(false);
		buy.add(wdln);
		select.add(wdln);
		
		sydp = new JButton("");
		sydp.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/sydp.jpg")));
		sydp.setContentAreaFilled(false);
		buy.add(sydp);
		select.add(sydp);
		
		jrzzd = new JButton("");
		jrzzd.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/jrzzd.jpg")));
		jrzzd.setContentAreaFilled(false);
		buy.add(jrzzd);
		select.add(jrzzd);
		
		shzj = new JButton("");
		shzj.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/shzj.jpg")));
		shzj.setContentAreaFilled(false);
		buy.add(shzj);
		select.add(shzj);
		
		txj = new JButton("");
		txj.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/txj.jpg")));
		txj.setContentAreaFilled(false);
		buy.add(txj);
		select.add(txj);
		
		hh = new JButton("");
		hh.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hh.jpg")));
		hh.setContentAreaFilled(false);
		buy.add(hh);
		select.add(hh);
		
		yyzs = new JButton("");
		yyzs.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/yyzs.jpg")));
		yyzs.setContentAreaFilled(false);
		buy.add(yyzs);
		select.add(yyzs);
		
		have = new JPanel();
		tb = new TitledBorder(new LineBorder(new Color(222, 184, 135), 5), "道具盒", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(188, 143, 143));
		have.setBorder(tb);
		have.setBounds(676, 226, 314, 314);
		this.add(have);
		have.setLayout(new GridLayout(5, 6));
		
		tip = new JLabel("请选择一个物品以购买");
		tip.setHorizontalAlignment(SwingConstants.CENTER);
		tip.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		tip.setBounds(10, 496, 483, 42);
		this.add(tip);
		
		submit = new JButton("购买");
		submit.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		submit.setBounds(503, 492, 133, 48);
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BuyItem();
			}
		});
		this.add(submit);
		
		getUserHeroIcon(hero.getId());
		icon.setBorder(new LineBorder(new Color(188, 143, 143), 3));
		icon.setBounds(676, 91, 125, 125);
		this.add(icon);
		
		name = new JLabel(hero.getName());
		name.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		name.setBounds(815, 91, 163, 43);
		this.add(name);
		
		e1 = new JButton("");
		e1.setContentAreaFilled(false);
		e1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e1.png")));
		e1.setBounds(821, 144, 64, 64);
		e1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new openEquip(fight, fight.zb1).setVisible(true);
			}
		});
		this.add(e1);
		
		e2 = new JButton("");
		e2.setContentAreaFilled(false);
		e2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
		e2.setBounds(899, 144, 64, 64);
		e2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new openEquip(fight, fight.zb2).setVisible(true);
			}
		});
		this.add(e2);
		
		if(fight.userh.getZ()!=null) {
			e1.setIcon(Config.s.getItemImageIcon(fight.userh.getZ().getId()));
			e1.setToolTipText(Config.Allitems.get(fight.userh.getZ().getId()-1).getItem());
		}
		if(fight.userh.getX()!=null) {
			e2.setIcon(Config.s.getItemImageIcon(fight.userh.getX().getId()));
			e2.setToolTipText(Config.Allitems.get(fight.userh.getX().getId()-1).getItem());
		}
		
		for(int i=0;i<select.size();i++) {
			select.get(i).addActionListener(new AL(i));
			select.get(i).setToolTipText(Config.Allitems.get(i).getItem());
		}
		
		new countdown().start();
		
	}
	
	public void InitDJH() {
		for(int i=0;i<djh.size();i++) {
			have.add(Config.s.getItemIcon(djh.get(i).getId()));
		}
		tb.setTitle("道具盒"+djh.size()+"/"+30);
		repaint();
	}
	
	class AL implements ActionListener {
		
		int i;
		
		public AL(int i) {
			this.i=i;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			buttongroup(select.get(i),i);
		}
		
	}
	
	void BuyItem() {
		if(item!=null) {
			if(djh.size()!=30) {
				if(this.gold >= item.getGold()) {
					if(item.getId()==1) {
						if(!sy) {
							this.gold -= item.getGold();
							fight.sendAll(user.getUsername()+"（"+hero.getName()+"）购买了"+"【"+item.getName()+"】。");
							fight.UpdateJTextArea("你购买了"+"【"+item.getName()+"】。\n\n");
							sy=true;
							fight.limitr+=5;
							fight.UpdateJTextArea("总回合数增加了5！\n\n");
							fight.setE(0);
							setcanSelect();
							usergold.setText("剩余金币："+this.gold);
							fight.tip1.setText("当前拥有金币："+gold);
							tb.setTitle("道具盒"+djh.size()+"/"+30);
							repaint();
							JOptionPane.showMessageDialog(null, "已完成付款。");
						} else {
							JOptionPane.showMessageDialog(null, "每次限购1次【回合延时道具】。");
						}
					} else if(item.getId()==2) {
						if(hp!=3) {
							this.gold -= item.getGold();
							fight.sendAll(user.getUsername()+"（"+hero.getName()+"）购买了"+"【"+item.getName()+"】。");
							fight.UpdateJTextArea("你购买了"+"【"+item.getName()+"】。\n\n");
							hp++;
							if(fight.userh.getHp()+2<=fight.userhpt.getMaximum()) {
								fight.userh.setHp(fight.userh.getHp()+2);
								fight.hpp+=2;
								fight.hurt-=2;
							} else {
								fight.hpp+=fight.userhpt.getMaximum()-fight.userh.getHp();
								fight.hurt-=fight.userhpt.getMaximum()-fight.userh.getHp();
								fight.userh.setHp(fight.userhpt.getMaximum());
							}
							fight.userhpt.setValue(fight.userh.getHp());
							fight.userhpt.setString(fight.userh.getHp()+" / "+fight.userhpt.getMaximum());
							fight.UpdateJTextArea("你为你的英雄【"+fight.userh.getName()+"】回复了2点生命值！\n\n");
							fight.setE(1);
							setcanSelect();
							usergold.setText("剩余金币："+this.gold);
							fight.tip1.setText("当前拥有金币："+gold);
							tb.setTitle("道具盒"+djh.size()+"/"+30);
							repaint();
							JOptionPane.showMessageDialog(null, "已完成付款。");
						} else {
							JOptionPane.showMessageDialog(null, "每次限购3粒【回复药】。");
						}
					} else if(item.getId()==3||item.getId()==4||(item.getId()>=7&&item.getId()<=9)) {
						if(r<35) {
							this.gold -= item.getGold();
							fight.sendAll(user.getUsername()+"（"+hero.getName()+"）购买了"+"【"+item.getName()+"】。");
							fight.UpdateJTextArea("你购买了"+"【"+item.getName()+"】。\n\n");
							djh.add(item);
							have.add(Config.s.getItemIcon(item.getId()));
							setcanSelect();
							usergold.setText("剩余金币："+this.gold);
							fight.tip1.setText("当前拥有金币："+gold);
							tb.setTitle("道具盒"+djh.size()+"/"+30);
							repaint();
							JOptionPane.showMessageDialog(null, "已完成付款。");
						} else {
							JOptionPane.showMessageDialog(null, "加时赛不再提供回复类道具，无法完成购买！");
						}
					} else {
						this.gold -= item.getGold();
						fight.sendAll(user.getUsername()+"（"+hero.getName()+"）购买了"+"【"+item.getName()+"】。");
						fight.UpdateJTextArea("你购买了"+"【"+item.getName()+"】。\n\n");
						if(item.getId()==5) {
							fight.fsjn++;
						} else if(item.getId()==6) {
							fight.gjfsjn++;
						} else if(item.getId()==26) {
							fight.uyyzs+=3;
							fight.setE(26);
						}
						usergold.setText("剩余金币："+this.gold);
						fight.tip1.setText("当前拥有金币："+gold);
						tb.setTitle("道具盒"+djh.size()+"/"+30);
						if(r<35) {
							djh.add(item);
							have.add(Config.s.getItemIcon(item.getId()));
							JOptionPane.showMessageDialog(null, "已完成付款。");
						} else { // 回合>35 吃装备
							if(item.getId()!=13&&item.getId()!=19&&item.getId()!=22&&item.getId()!=26) {
								if(fight.eatequip) {
									if(hero.getZ()!=null||hero.getX()!=null) {
										if(item.getId()==hero.getZ().getId()||item.getId()==hero.getX().getId()) {
											fight.eatequip=false;
											fight.sendAll(user.getUsername()+"（"+hero.getName()+"）吃掉了"+"【"+item.getName()+"】，永久获得了该装备的效果！");
											fight.UpdateJTextArea("你吃掉了"+"【"+item.getName()+"】，永久获得了该装备的效果！\n\n");
											fight.getUserEquip(item.getId());
											fight.setE(item.getId(), 5);
											JOptionPane.showMessageDialog(null, "已完成付款并永久获得了该装备的效果（不放入道具盒，不用装备）。");
										} else if(matchDJH(item.getId())) {
											fight.eatequip=false;
											fight.sendAll(user.getUsername()+"（"+hero.getName()+"）吃掉了"+"【"+item.getName()+"】，永久获得了该装备的效果！");
											fight.UpdateJTextArea("你吃掉了"+"【"+item.getName()+"】，永久获得了该装备的效果！\n\n");
											fight.getUserEquip(item.getId());
											fight.setE(item.getId(), 5);
											JOptionPane.showMessageDialog(null, "已完成付款并永久获得了该装备的效果（不放入道具盒，不用装备）。");
										} else {
											djh.add(item);
											have.add(Config.s.getItemIcon(item.getId()));
											JOptionPane.showMessageDialog(null, "已完成付款。");
										}
									}
								} else {
									djh.add(item);
									have.add(Config.s.getItemIcon(item.getId()));
									JOptionPane.showMessageDialog(null, "已完成付款。");
								}
							} else {
								djh.add(item);
								have.add(Config.s.getItemIcon(item.getId()));
								JOptionPane.showMessageDialog(null, "已完成付款。");
							}
						}
						setcanSelect();
						repaint();
					}
				} else {
					JOptionPane.showMessageDialog(null, "余额不足，无法购买！");
				}
			} else {
				JOptionPane.showMessageDialog(null, "道具盒已满，无法购买！");
			}
		} else {
			JOptionPane.showMessageDialog(null, "请先选中一个物品以购买。");
		}
	}
	
	class countdown extends Thread {

		@Override
		public void run() {
			super.run();
			while((remain--)>0) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				time.setText("还有"+remain+"秒开始第"+r+"回合");
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			time.setText("商店回合结束！");
			fight.djh = djh;
			fight.gold = gold;
			fight.tip1.setText("当前拥有金币："+gold);
			fight.repaint();
			fight.setEnabled(true);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			dispose();
		}
		
	}
	
	void setcanSelect() {
		tip.setText("请选择一个物品以购买");
		item = null;
		for(int i=0;i<select.size();i++) {
			if(!select.get(i).isEnabled()) {
				select.get(i).setEnabled(true);
			} 
		}
	}
	
	public void buttongroup (JButton jb,int itemid) {
		for(int i=0;i<select.size();i++) {
			if(select.get(i).isEnabled()) {
				item = Config.Allitems.get(itemid);
				tip.setText(item.getName()+"（需要消耗："+item.getGold()+"金）");
				select.get(i).setEnabled(false);
			} else {
				item = null;
				tip.setText("请选择一个物品以购买");
				select.get(i).setEnabled(true);
			}
			jb.setEnabled(true);
		}
	}
	
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
			this.setLocationRelativeTo(icon);
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
			if(willequip!=null) {
				equipid = willequip.getId();
				if(zb.equals(f.zb1)) {
					if(f.userh.getZ()!=null) {
						f.getUserEquip(f.userh.getZ().getId()+20);
						f.setE(f.userh.getZ().getId(),3);
						djh.add(f.userh.getZ());
					}
					f.userh.setZ(willequip);
					f.setE(equipid,1);
				} else if(zb.equals(f.zb2)) {
					if(f.userh.getX()!=null) {
						f.getUserEquip(f.userh.getX().getId()+20);
						f.setE(f.userh.getX().getId(),4);
						djh.add(f.userh.getX());
					}
					f.userh.setX(willequip);
					f.setE(equipid,2);
				}
				f.setEnabled(true);
				djh.remove(willequip);
				zb.setIcon(Config.s.getItemImageIcon(equipid));
				zb.setToolTipText(Config.Allitems.get(equipid-1).getItem());
				f.getUserEquip(equipid);
				willequip=null;
				repaint();
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "未指定任何装备");
			}
			if(fight.userh.getZ()!=null) {
				e1.setIcon(Config.s.getItemImageIcon(fight.userh.getZ().getId()));
				e1.setToolTipText(Config.Allitems.get(fight.userh.getZ().getId()-1).getItem());
			}
			if(fight.userh.getX()!=null) {
				e2.setIcon(Config.s.getItemImageIcon(fight.userh.getX().getId()));
				e2.setToolTipText(Config.Allitems.get(fight.userh.getX().getId()-1).getItem());
			}
		}
		
		public void dontitem() {
			if(zb.equals(f.zb1)) {
				if(f.userh.getZ()!=null) {
					djh.add(f.userh.getZ());
					have.add(Config.s.getItemIcon(f.userh.getZ().getId()));
					f.setE(f.userh.getZ().getId(),3);
					f.getUserEquip(f.userh.getZ().getId()+20);
					f.userh.setZ(null);
					zb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e1.png")));
					zb.setToolTipText("点击选择一个装备并穿戴。");
					e1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
					e1.setToolTipText("点击选择一个装备并穿戴。");
				}
			} else if(zb.equals(f.zb2)) {
				if(f.userh.getX()!=null) {
					djh.add(f.userh.getX());
					have.add(Config.s.getItemIcon(f.userh.getX().getId()));
					f.setE(f.userh.getX().getId(),4);
					f.getUserEquip(f.userh.getX().getId()+20);
					f.userh.setX(null);
					zb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
					zb.setToolTipText("点击选择一个装备并穿戴。");
					e2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
					e2.setToolTipText("点击选择一个装备并穿戴。");
				}
			}
			f.setEnabled(true);
			willequip = null;
			dispose();
			if(fight.userh.getZ()!=null) {
				e1.setIcon(Config.s.getItemImageIcon(fight.userh.getZ().getId()));
				e1.setToolTipText(Config.Allitems.get(fight.userh.getZ().getId()-1).getItem());
			}
			if(fight.userh.getX()!=null) {
				e2.setIcon(Config.s.getItemImageIcon(fight.userh.getX().getId()));
				e2.setToolTipText(Config.Allitems.get(fight.userh.getX().getId()-1).getItem());
			}
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
			if(zb.equals(f.zb1)) {
				tb.setTitle("[1]点√穿戴点×取消穿戴");
			} else if(zb.equals(f.zb2)) {
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
	
	public boolean matchDJH(int id) {
		for(Item array : djh) {
			if(array.getId()==id) {
				return true;
			}
		}
		return false;
	}
	
	public void getUserHeroIcon(int i) {
		switch(i) {
			case 1:{
				icon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());
				break;
			}
			case 2:{
				icon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());
				break;
			}
			case 3:{
				icon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());
				break;
			}
			case 4:{
				icon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());
				break;
			}
			case 5:{
				icon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());
				break;
			}
			case 6:{
				icon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());
				break;
			}
			case 7:{
				icon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());
				break;
			}
			case 8:{
				icon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());
				break;
			}
			case 9:{
				icon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());
				break;
			}
			case 10:{
				icon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());
				break;
			}
			case 11:{
				icon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());
				break;
			}
			case 12:{
				icon = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());
				break;
			}
		}
		icon.setToolTipText(hero.getProperty());
	}
	
}
