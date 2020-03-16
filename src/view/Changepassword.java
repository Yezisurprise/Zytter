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

import entity.User;
import util.Config;

public class Changepassword extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8147858245286219794L;

	JLabel id = new JLabel("Zytter ID:"), oldpassword = new JLabel("�����룺"), newpassword = new JLabel("�����룺"),
			cnewpassword = new JLabel("ȷ�����룺"), id1 = new JLabel();
	JPasswordField oldpassword1 = new JPasswordField(), newpassword1 = new JPasswordField(),
			cnewpassword1 = new JPasswordField();
	JButton ok = new JButton("ȷ���޸�"), cancel = new JButton("�����޸�");

	public Changepassword(User user) {
		this.setSize(720,390);
		this.setUndecorated(true);
		this.setBackground(new Color(255,255,255,220));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLayout(null);
		this.setTitle("�޸��������");
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		this.add(id);
		this.add(id1);
		this.add(oldpassword);
		this.add(oldpassword1);
		this.add(newpassword);
		this.add(newpassword1);
		this.add(cnewpassword);
		this.add(cnewpassword1);
		this.add(ok);
		this.add(cancel);
		id.setBounds(200, 30, 200, 100);
		id.setFont(new Font("����", Font.PLAIN, 20));
		id1.setBounds(300, 65, 300, 35);
		id1.setFont(new Font("����", Font.PLAIN, 20));
		id1.setText(String.valueOf(user.getUsername()));
		oldpassword.setBounds(200, 80, 200, 100);
		oldpassword.setFont(new Font("����", Font.PLAIN, 20));
		oldpassword1.setBounds(300, 115, 230, 30);
		oldpassword1.setFont(new Font("����", Font.PLAIN, 20));
		newpassword.setBounds(200, 130, 200, 100);
		newpassword.setFont(new Font("����", Font.PLAIN, 20));
		newpassword1.setBounds(300, 165, 230, 30);
		newpassword1.setFont(new Font("����", Font.PLAIN, 20));
		cnewpassword.setBounds(200, 180, 200, 100);
		cnewpassword.setFont(new Font("����", Font.PLAIN, 20));
		cnewpassword1.setBounds(300, 215, 230, 30);
		cnewpassword1.setFont(new Font("����", Font.PLAIN, 20));
		ok.setBounds(180, 270, 180, 40);
		ok.setContentAreaFilled(false);
		ok.setBorderPainted(false);
		ok.setFont(Config.ButtonFont);
		cancel.setBounds(375, 270, 180, 40);
		cancel.setContentAreaFilled(false);
		cancel.setBorderPainted(false);
		cancel.setFont(Config.ButtonFont);

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (String.valueOf(oldpassword1.getPassword()).equals(user.getPassword())) {
					String npw1 = String.valueOf(newpassword1.getPassword());
					String npw2 = String.valueOf(cnewpassword1.getPassword());
					if (npw1.length() >= 6 && npw1.length() <= 18) {
						if (npw2.length() >= 6 && npw2.length() <= 18) {
							if (npw1.equals(npw2)) {
								if (Config.s.updatePassword(npw1, user.getUid()) != 0) {
									user.setPassword(npw1);
									JOptionPane.showMessageDialog(null, "�޸ĳɹ����´ε�¼ʱ��ʹ���������¼");
									dispose();
								} else {
									JOptionPane.showMessageDialog(null, "�޸�ʧ��");
								}
							} else {
								JOptionPane.showMessageDialog(null, "�����������벻һ��");
							}
						} else {
							JOptionPane.showMessageDialog(null, "������������6~18���ַ���");
						}
					} else {
						JOptionPane.showMessageDialog(null, "������������6~18���ַ���");
					}
				} else {
					JOptionPane.showMessageDialog(null, "�����벻��ȷ");
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	}

}
