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
		tip.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		tip.setHorizontalAlignment(SwingConstants.CENTER);
		tip.setBounds(10, 10, 414, 58);
		this.add(tip);
		
		cb = new JComboBox<String>();
		cb.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		cb.setBounds(99, 96, 238, 51);
		this.add(cb);
		cb.addItem("������");
		cb.setSelectedItem("������");
		
		JButton ok = new JButton("ȷ��ѡ��");
		ok.setFont(new Font("΢���ź� Light", Font.BOLD, 20));
		ok.setBounds(148, 194, 151, 41);
		this.add(ok);
		
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!cb.getSelectedItem().equals("������")) {
					if(f.selected==1) {
						if(f.userh.getId()==h.getId()) {
							if(h.jhjj==0) {
								ok.setText("�Ѿ�����");
								ok.setEnabled(false);
								remove(cb);
								h.jjzl=true;
								h.jhjj=cb.getSelectedIndex();
								f.setE(h.jhjj+10);
								f.UpdateJTextArea("���Ѿ������˽ᾧ֮���ġ�"+cb.getSelectedItem()+"����֧��\n\n");
								f.sendAll("��"+h.getName()+"���Ѿ������˽ᾧ֮���ġ�"+cb.getSelectedItem()+"����֧��");
								UpdateSkillDescribe();
								setVisible(false);
							} else {
								JOptionPane.showMessageDialog(null, "���Ѿ�������ˣ������ظ����");
								setVisible(false);
							}
						} else {
							JOptionPane.showMessageDialog(null, "�㲻�ܼ����Ӣ�۵Ľᾧ֮����");
							dispose();
						}
					} else {
						JOptionPane.showMessageDialog(null, "�����ж�ʱ���ڡ�");
					}
				} else {
					JOptionPane.showMessageDialog(null, "���´�ʹ��-jh�����ֶ��򿪱����档");
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
					h.getQ().setdescribe("<html>�ԶԷ�������գ�ÿ�غ����"+(5+f.userh.yyE)+"��ħ���˺�������3�غϡ���Ч��Ӱ��Ļغ������Ե��ӡ�<br/><br/>�Ѽ���ᾧ֮����</html>");
					f.skill1.setToolTipText("<html>��Q��"+h.getQ().getSkill()+"</html>");
				} else if(h.jhjj==3) {
					h.getE().setdescribe("<html>��0��9�г�ȡ�������֡�<br />��������С�ڻ���ڶԷ���ǰ����ֵ���㵱ǰħ��ֵ֮�͵ĸ�λ������ôʹ����֮���Ͱ�Ӱ֮�̻��6��ħ���˺��ӳɣ�����4�غϡ�<br/><br/>��Ч��Ӱ��Ļغ������Ե��ӡ��Ѽ���ᾧ֮����</html>");
					f.skill3.setToolTipText("<html>��E��"+h.getE().getSkill()+"</html>");
					h.getQ().setdescribe("��0��9�г�ȡ�������֡�<br />��������С�ڻ���ڶԷ���ǰ����ֵ�ĸ�λ������ô�ԶԷ�������գ�ÿ�غ����"+(5+f.userh.yyE)+"��ħ���˺�������3�غϡ�<br />��Ч��Ӱ��Ļغ������Ե��ӡ�<br/><br />������ᾧ֮����100%��Ч��");
					f.skill1.setToolTipText("<html>��Q��"+h.getQ().getSkill()+"</html>");
					h.getW().setdescribe("��0��9�г�ȡ�������֡�<br />��������С�ڻ���ڶԷ��ж������㹥����֮�͵ĸ�λ������ô�ԶԷ����"+(10+f.userh.yyE)+"��ħ���˺����ü��������ж�����");
					f.skill2.setToolTipText("<html>��W��"+h.getW().getSkill()+"</html>");
				}
				break;
			}
			case 6:{
				if(h.jhjj==1) {
					h.getR().setdescribe("<html>�����ж����ԶԷ����14��ħ���˺�����2�غ�������4�㹥������<br/><br/>�Ѽ���ᾧ֮����</html>");
					f.skill4.setToolTipText("<html>��R��"+h.getR().getSkill()+"</html>");
				} else if(h.jhjj==2) {
					h.getE().setdescribe("<html>�����ж����ԶԷ����14��ħ���˺���<br/><br/>�Ѽ���ᾧ֮����</html>");
					f.skill3.setToolTipText("<html>��E��"+h.getE().getSkill()+"</html>");
				} else if(h.jhjj==3) {
					h.getQ().setdescribe("<html>���30%�����˺�������3�غϡ�<br/><br/>��Ч��Ӱ��Ļغ������Ե��ӡ��Ѽ���ᾧ֮����</html>");
					f.skill1.setToolTipText("<html>��Q��"+h.getQ().getSkill()+"</html>");
				}
				break;
			}
			case 9:{
				if(h.jhjj==1) {
					h.getW().setdescribe("<html>��ɶԷ�12��ħ���˺���<br/><br/>�Ѽ���ᾧ֮����</html>");
					f.skill2.setToolTipText("<html>��W��"+h.getW().getSkill()+"</html>");
				} else if(h.jhjj==2) {
					h.getW().setdescribe("<html>��ɶԷ�20��ħ���˺������������غϿ���ʹ�ã�<br/><br/>�Ѽ���ᾧ֮����</html>");
					f.skill2.setToolTipText("<html>��W��"+h.getW().getSkill()+"</html>");
				} else if(h.jhjj==3) {
					h.getQ().setMp(h.getQ().getMp()-2);
					h.getW().setMp(h.getW().getMp()-2);
					h.getE().setMp(h.getE().getMp()-2);
					h.getR().setMp(h.getR().getMp()-2);
					h.getQ().setdescribe("<html>��һ�����ħ���˺�ʱ�����ٶԷ��൱�������ħ��ֵ42%��ħ��ֵ������˫���غϿ���ʹ�ã�<br/><br/>�Ѽ���ᾧ֮����</html>");
					h.getW().setdescribe("<html>��ɶԷ�12��ħ���˺������������غϿ���ʹ�ã�<br/><br/>�Ѽ���ᾧ֮����</html>");
					h.getE().setdescribe("<html>���ܵ������˺�ʱ�����˺�����80%��ͬʱ���ƶԷ�����ʹ���չ�������2�غϡ���Ч��Ӱ��Ļغ����ɵ��ӡ�<br/>������֮���ڸûغ�Ϊ������˳����˺�����ô�غϽ����㽫�޷����ħ���ظ���<br/><br/>�Ѽ���ᾧ֮����</html>");
					h.getR().setdescribe("<html>�غϿ�ʼǰ��������ظ����㵱ǰħ��ֵ��ֵ������ֵ�������ǵ�ǰħ��ֵ��һ�룩������2�غϡ�<br/><br/>��Ч��Ӱ��Ļغ����ɵ��ӡ��Ѽ���ᾧ֮����</html>");
					f.skill1.setToolTipText("<html>��Q��"+h.getQ().getSkill()+"</html>");
					f.skill2.setToolTipText("<html>��W��"+h.getW().getSkill()+"</html>");
					f.skill3.setToolTipText("<html>��E��"+h.getE().getSkill()+"</html>");
					f.skill4.setToolTipText("<html>��R��"+h.getR().getSkill()+"</html>");
				}
				break;
			}
			case 11:{
				if(h.jhjj==1) {
					h.getE().setdescribe("<html>��һ����ͨ��������˺�ʱ��ʹ����ظ�3������ֵ����ɶ����10��ħ���˺������ɵ��ӡ�<br/><br/>�Ѽ���ᾧ֮����</html>");
					f.skill3.setToolTipText("<html>��E��"+h.getE().getSkill()+"</html>");
				} else if(h.jhjj==2) {
					h.setAtk(h.getAtk()+4);
					f.usertx.setToolTipText(h.getProperty());
				} else if(h.jhjj==3) {
					h.getQ().setdescribe("<html>����80%������ͨ���������˺�����ǰ�����ܸù������ĸ��ʲ�����2��ħ������������3�غϡ�<br/><br/>��Ч�����Ե��ӡ��Ѽ���ᾧ֮����</html>");
					f.skill1.setToolTipText("<html>��Q��"+h.getQ().getSkill()+"</html>");
				}
				break;
			}
		}
	}
	
	public void Inititem() {
		switch(h.getId()) {
			case 1:{
				cb.addItem("ħ����͸+30%");
				cb.addItem("����֮��100%��Ч");
				cb.addItem("��ɱ֮��ħ���˺��ӳ�+3");
				break;
			}
			case 6:{
				cb.addItem("����֮�۵�ħ���˺�+4");
				cb.addItem("�����漣��ħ���˺�+4�������ж���");
				cb.addItem("����Ϊ����Ϊ�˺���ǿ");
				break;
			}
			case 9:{
				cb.addItem("ȥ�����ǵĵ����غ�ʹ������");
				cb.addItem("���ǵ�ħ���˺�+8");
				cb.addItem("���м���ħ������-2");
				break;
			}
			case 11:{
				cb.addItem("��㽣��ħ���˺�+4");
				cb.addItem("�����﹥+4");
				cb.addItem("����+�ĳ����غ�+1�һغ����������");
				break;
			}
		}
	}
	
}
