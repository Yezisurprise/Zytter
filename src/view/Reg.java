package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.Config;

public class Reg extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2290060827197023005L;
	
	Login login=null;
	
	JLabel id=new JLabel("�û���",JLabel.CENTER),passwor=new JLabel("����",JLabel.CENTER),password=new JLabel("ȷ������",JLabel.CENTER);
	JTextField id1=new JTextField();
	JPasswordField passwor1=new JPasswordField(),password1=new JPasswordField();
	JButton ok=new JButton("ע��");
	JButton quit=new JButton("����");
	
	public Reg(Login login){
		
		this.setSize(337,250);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setUndecorated(true);
		this.setBackground(new Color(255,255,255,220));
		this.setTitle("ע��ѧ԰ͨ��֤");
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		this.add(id);
		this.add(id1);
		this.add(ok);
		this.add(quit);
		this.add(passwor);
		this.add(passwor1);
		this.add(password);
		this.add(password1);
		
		id.setBounds(10, 20, 120, 50);
		id.setForeground(new Color(139,69,19));
		id.setFont(Config.SmallFont); 
		
		id1.setBounds(150, 25, 162, 40);
		id1.setFont(new Font("����", Font.PLAIN, 20)); 
		
		passwor.setBounds(10, 70,120, 50);
		passwor.setForeground(new Color(139,69,19));
		passwor.setFont(Config.SmallFont); 
		
		passwor1.setBounds(150, 75, 162, 40);
		passwor1.setFont(new Font("����", Font.PLAIN, 20)); 
		
		password.setBounds(10, 120, 120, 50);
		password.setForeground(new Color(139,69,19));
		password.setFont(Config.SmallFont); 
		
		password1.setBounds(150, 125, 162, 40);
		password1.setFont(new Font("����", Font.PLAIN, 20)); 
		
		ok.setBounds(170, 185, 100, 50);
		ok.setFont(Config.SmallFont);
		ok.setForeground(new Color(139,69,19));
		ok.setContentAreaFilled(false);
		ok.setBorderPainted(false);
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username=id1.getText();
				String pw1=String.valueOf(passwor1.getPassword());
				String pw2=String.valueOf(password1.getPassword());
				if(username.length()<2||username.length()>6) {
					JOptionPane.showMessageDialog(null,"ͨ��֤���û������������2~6���ַ���");
				} else {
					if(isSpace(username)) {
						JOptionPane.showMessageDialog(null,"ͨ��֤���û�������������ո�");
					} else {
						if(!Config.s.isexist(username)) {
							if(pw1.length()<6||pw1.length()>18) {
								JOptionPane.showMessageDialog(null,"������������6~18���ַ���");
							} else {
								if(pw2.length()<6||pw2.length()>18) {
									JOptionPane.showMessageDialog(null,"������������6~18���ַ���");
								} else {
									if(pw1.equals(pw2)) {
										if(Config.s.addUser(username,pw1)!=0) {
											JOptionPane.showMessageDialog(null,"��ϲ����ע��ɹ���"+"\n���μ������û�����"+id1.getText()+"\n���룺"+String.valueOf(passwor1.getPassword()));
											login.setEnabled(true);
											dispose();
										} else {
											JOptionPane.showMessageDialog(null,"ע��ʧ�ܣ���������������������ӣ�");
										}
									} else {
										JOptionPane.showMessageDialog(null,"������������벻��ȷ");
									}
								}
							}
						} else {
							JOptionPane.showMessageDialog(null,"�û����ظ��򲻿���");
						}
					}

				}
			}
		});
		
		quit.setBounds(50, 185, 100, 50);
		quit.setFont(Config.SmallFont);
		quit.setForeground(new Color(139,69,19));
		quit.setContentAreaFilled(false);
		quit.setBorderPainted(false);
		quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				login.setEnabled(true);
				dispose();
			}
		});
		
		this.setLocationRelativeTo(null);
		
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
