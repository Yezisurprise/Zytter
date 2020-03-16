package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import entity.Hero;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GetSkillUp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6555515756443142896L;
	
	public JComboBox<String> cb;
	public Hero h;
	public Fight f;
	
	public GetSkillUp(Fight f,Hero h) {
		
		this.h=h;
		this.f=f;
		
		this.setTitle("\u6FC0\u6D3B\u7ED3\u6676\u4E4B\u529B");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(450, 300);
		this.setLocationRelativeTo(f);
		this.setLayout(null);
		this.setResizable(false);
		
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		
		JLabel tip = new JLabel("\u8BF7\u9009\u62E9\u4E00\u4E2A\u7ED3\u6676\u4E4B\u529B\u7684\u6FC0\u6D3B\u5206\u652F");
		tip.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		tip.setHorizontalAlignment(SwingConstants.CENTER);
		tip.setBounds(10, 10, 414, 58);
		this.add(tip);
		
		cb = new JComboBox<String>();
		cb.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		cb.setBounds(99, 96, 238, 51);
		this.add(cb);
		cb.addItem("再想想");
		cb.setSelectedItem("再想想");
		
		JButton ok = new JButton("确认选择");
		ok.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		ok.setBounds(148, 194, 151, 41);
		this.add(ok);
		
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!cb.getSelectedItem().equals("再想想")) {
					if(f.selected==1) {
						if(f.userh.getId()==h.getId()) {
							if(h.jhjj==0) {
								ok.setText("已经激活");
								ok.setEnabled(false);
								remove(cb);
								h.jjzl=true;
								h.jhjj=cb.getSelectedIndex();
								f.setE(h.jhjj+10);
								f.UpdateJTextArea("你已经激活了结晶之力的【"+cb.getSelectedItem()+"】分支！\n\n");
								f.sendAll("【"+h.getName()+"】已经激活了结晶之力的【"+cb.getSelectedItem()+"】分支！");
								UpdateSkillDescribe();
								setVisible(false);
							} else {
								JOptionPane.showMessageDialog(null, "你已经激活过了，不能重复激活。");
								setVisible(false);
							}
						} else {
							JOptionPane.showMessageDialog(null, "你不能激活该英雄的结晶之力！");
							dispose();
						}
					} else {
						JOptionPane.showMessageDialog(null, "不在行动时间内。");
					}
				} else {
					JOptionPane.showMessageDialog(null, "请下次使用-jh口令手动打开本界面。");
					setVisible(false);
				}
			}
		});
		
	}
	
	public void UpdateSkillDescribe() {
		switch(h.getId()) {
			case 1:{
				if(h.jhjj==1) {
					h.setApp(h.getApp()+0.3);
					f.usertx.setToolTipText(h.getProperty());
				} else if(h.jhjj==2) {
					h.getQ().setdescribe("<html>对对方造成灼烧，每回合造成"+(5+f.userh.yyE)+"点魔法伤害，持续3回合。该效果影响的回合数可以叠加。<br/><br/>已激活结晶之力。</html>");
					f.skill1.setToolTipText("<html>（Q）"+h.getQ().getSkill()+"</html>");
				} else if(h.jhjj==3) {
					h.getE().setdescribe("<html>从0～9中抽取幸运数字。<br />若该数字小于或等于对方当前生命值和你当前魔法值之和的个位数，那么使烈日之箭和暗影之刺获得6点魔法伤害加成，持续4回合。<br/><br/>该效果影响的回合数可以叠加。已激活结晶之力。</html>");
					f.skill3.setToolTipText("<html>（E）"+h.getE().getSkill()+"</html>");
					h.getQ().setdescribe("从0～9中抽取幸运数字。<br />若该数字小于或等于对方当前生命值的个位数，那么对对方造成灼烧，每回合造成"+(5+f.userh.yyE)+"点魔法伤害，持续3回合。<br />该效果影响的回合数可以叠加。<br/><br />【激活结晶之力】100%生效。");
					f.skill1.setToolTipText("<html>（Q）"+h.getQ().getSkill()+"</html>");
					h.getW().setdescribe("从0～9中抽取幸运数字。<br />若该数字小于或等于对方行动力和你攻击力之和的个位数，那么对对方造成"+(10+f.userh.yyE)+"点魔法伤害。该技能无视行动力。");
					f.skill2.setToolTipText("<html>（W）"+h.getW().getSkill()+"</html>");
				}
				break;
			}
			case 6:{
				if(h.jhjj==1) {
					h.getR().setdescribe("<html>无视行动力对对方造成14点魔法伤害，下2回合你提升4点攻击力。<br/><br/>已激活结晶之力。</html>");
					f.skill4.setToolTipText("<html>（R）"+h.getR().getSkill()+"</html>");
				} else if(h.jhjj==2) {
					h.getE().setdescribe("<html>无视行动力对对方造成14点魔法伤害。<br/><br/>已激活结晶之力。</html>");
					f.skill3.setToolTipText("<html>（E）"+h.getE().getSkill()+"</html>");
				} else if(h.jhjj==3) {
					h.getQ().setdescribe("<html>提高30%技能伤害，持续3回合。<br/><br/>该效果影响的回合数可以叠加。已激活结晶之力。</html>");
					f.skill1.setToolTipText("<html>（Q）"+h.getQ().getSkill()+"</html>");
				}
				break;
			}
			case 9:{
				if(h.jhjj==1) {
					h.getW().setdescribe("<html>造成对方12点魔法伤害。<br/><br/>已激活结晶之力。</html>");
					f.skill2.setToolTipText("<html>（W）"+h.getW().getSkill()+"</html>");
				} else if(h.jhjj==2) {
					h.getW().setdescribe("<html>造成对方20点魔法伤害。（仅单数回合可以使用）<br/><br/>已激活结晶之力。</html>");
					f.skill2.setToolTipText("<html>（W）"+h.getW().getSkill()+"</html>");
				} else if(h.jhjj==3) {
					h.getQ().setMp(h.getQ().getMp()-2);
					h.getW().setMp(h.getW().getMp()-2);
					h.getE().setMp(h.getE().getMp()-2);
					h.getR().setMp(h.getR().getMp()-2);
					h.getQ().setdescribe("<html>下一次造成魔法伤害时将减少对方相当于其最大魔法值42%的魔法值。（仅双数回合可以使用）<br/><br/>已激活结晶之力。</html>");
					h.getW().setdescribe("<html>造成对方12点魔法伤害。（仅单数回合可以使用）<br/><br/>已激活结晶之力。</html>");
					h.getE().setdescribe("<html>在受到物理伤害时，该伤害减少80%；同时限制对方仅能使用普攻，持续2回合。该效果影响的回合数可叠加。<br/>若予恋之花在该回合为你减少了承受伤害，那么回合结束你将无法获得魔法回复。<br/><br/>已激活结晶之力。</html>");
					h.getR().setdescribe("<html>回合开始前，你至多回复与你当前魔法值等值的生命值（保底是当前魔法值的一半），持续2回合。<br/><br/>该效果影响的回合数可叠加。已激活结晶之力。</html>");
					f.skill1.setToolTipText("<html>（Q）"+h.getQ().getSkill()+"</html>");
					f.skill2.setToolTipText("<html>（W）"+h.getW().getSkill()+"</html>");
					f.skill3.setToolTipText("<html>（E）"+h.getE().getSkill()+"</html>");
					f.skill4.setToolTipText("<html>（R）"+h.getR().getSkill()+"</html>");
				}
				break;
			}
			case 11:{
				if(h.jhjj==1) {
					h.getE().setdescribe("<html>下一次普通攻击造成伤害时会使得你回复3点生命值并造成额外的10点魔法伤害。不可叠加。<br/><br/>已激活结晶之力。</html>");
					f.skill3.setToolTipText("<html>（E）"+h.getE().getSkill()+"</html>");
				} else if(h.jhjj==2) {
					h.setAtk(h.getAtk()+4);
					f.usertx.setToolTipText(h.getProperty());
				} else if(h.jhjj==3) {
					h.getQ().setdescribe("<html>增加80%闪避普通攻击（在伤害结算前，闪避该攻击）的概率并提升2点魔法防御，持续3回合。<br/><br/>该效果可以叠加。已激活结晶之力。</html>");
					f.skill1.setToolTipText("<html>（Q）"+h.getQ().getSkill()+"</html>");
				}
				break;
			}
		}
	}
	
	public void Inititem() {
		switch(h.getId()) {
			case 1:{
				cb.addItem("魔法穿透+30%");
				cb.addItem("烈日之箭100%生效");
				cb.addItem("屠杀之风魔法伤害加成+3");
				break;
			}
			case 6:{
				cb.addItem("云霄之巅的魔法伤害+4");
				cb.addItem("星月奇迹的魔法伤害+4且无视行动力");
				cb.addItem("先入为主变为伤害增强");
				break;
			}
			case 9:{
				cb.addItem("去除流星的单数回合使用限制");
				cb.addItem("流星的魔法伤害+8");
				cb.addItem("所有技能魔法消耗-2");
				break;
			}
			case 11:{
				cb.addItem("光炽剑的魔法伤害+4");
				cb.addItem("基础物攻+4");
				cb.addItem("闪现+的持续回合+1且回合数允许叠加");
				break;
			}
		}
	}
	
}
