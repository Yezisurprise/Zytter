package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import entity.PlayerIcon;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.GridLayout;

public class Moving extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 897600898947503937L;
	
	/**
	 * Create the frame.
	 */
	public Moving() {
		
		this.setTitle("\u884C\u52A8\u754C\u9762");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 838, 624);
		this.setLayout(null);
		
		ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("img/moving.jpg"));
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,838,624);
		Container contain = this.getContentPane();
		((JPanel)contain).setOpaque(false);
		
		JLabel wpmstip = new JLabel("物品描述");
		JLabel jnmstip = new JLabel("技能描述");
		
		JPanel zbgroup = new JPanel();
		zbgroup.setBorder(new TitledBorder(new LineBorder(new Color(255, 175, 175), 2), "\u88C5\u5907\u5217\u8868", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(240, 128, 128)));
		zbgroup.setBounds(237, 30, 93, 185);
		zbgroup.setOpaque(false);
		this.add(zbgroup);
		
		JButton zb1 = new JButton("");
		zb1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e1.png")));
		zb1.setBounds(247, 42, 64, 64);
		zb1.setContentAreaFilled(false);
		zb1.setBorderPainted(false);
		zbgroup.add(zb1);
		
		JButton zb2 = new JButton("");
		zb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		zb2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/e2.png")));
		zb2.setBounds(247, 126, 64, 64);
		zb2.setContentAreaFilled(false);
		zb2.setBorderPainted(false);
		zbgroup.add(zb2);
		
		JPanel skillgroup = new JPanel();
		skillgroup.setBorder(new TitledBorder(new LineBorder(new Color(255, 175, 175), 2), "\u4F7F\u7528\u6280\u80FD", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(240, 128, 128)));
		skillgroup.setBounds(10, 231, 802, 170);
		skillgroup.setOpaque(false);
		this.add(skillgroup);
		
		JButton skill1 = new JButton("");
		skill1.setToolTipText("\u4F7F\u75281\u6280\u80FD");
		skill1.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/s1.png")));
		skill1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		skillgroup.setLayout(new GridLayout(1, 4, 0, 0));
		skill1.setBounds(31, 254, 128, 128);
		skill1.setContentAreaFilled(false);
		skill1.setBorderPainted(false);
		skillgroup.add(skill1);
		
		JButton skill2 = new JButton("");
		skill2.setToolTipText("\u4F7F\u75282\u6280\u80FD");
		skill2.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/s2.png")));
		skill2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		skill2.setBounds(242, 254, 128, 128);
		skill2.setContentAreaFilled(false);
		skill2.setBorderPainted(false);
		skillgroup.add(skill2);
		
		JButton skill3 = new JButton("");
		skill3.setToolTipText("\u4F7F\u75283\u6280\u80FD");
		skill3.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/s3.png")));
		skill3.setBounds(458, 254, 128, 128);
		skill3.setContentAreaFilled(false);
		skill3.setBorderPainted(false);
		skillgroup.add(skill3);
		
		JButton skill4 = new JButton("");
		skill4.setToolTipText("\u4F7F\u75284\u6280\u80FD");
		skill4.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/s4.png")));
		skill4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		skill4.setBounds(677, 254, 128, 128);
		skill4.setContentAreaFilled(false);
		skill4.setBorderPainted(false);
		skillgroup.add(skill4);
		
		JButton atk = new JButton("");
		atk.setToolTipText("\u8FDB\u884C\u666E\u901A\u653B\u51FB");
		atk.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/atk.png")));
		atk.setBounds(30, 427, 128, 128);
		atk.setContentAreaFilled(false);
		this.add(atk);
		
		Image icon=new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage();  
		JPanel tx = new PlayerIcon(icon);  
		tx.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(255, 192, 203)));
		tx.setToolTipText("郈与却");
		tx.setBounds(20, 20, 200, 200);
		this.add(tx);
		
		JButton item = new JButton("");
		item.setFont(new Font("等线", Font.PLAIN, 30));
		item.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/item.png")));
		item.setToolTipText("\u6253\u5F00\u9053\u5177\u76D2\uFF0C\u4F7F\u7528\u9053\u5177");
		item.setBounds(660, 50, 128, 128);
		item.setContentAreaFilled(false);
		this.add(item);
		
		JPanel wpms = new JPanel();
		wpms.setBorder(new TitledBorder(new LineBorder(new Color(255, 175, 175), 5), "\u7269\u54C1\u63CF\u8FF0", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(240, 128, 128)));
		wpms.setBounds(343, 39, 284, 151);
		wpms.setOpaque(false);
		this.add(wpms);
		wpms.add(wpmstip);
		
		JPanel jnms = new JPanel();
		jnms.setBorder(new TitledBorder(new LineBorder(new Color(255, 175, 175), 5), "\u6280\u80FD\u63CF\u8FF0", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(240, 128, 128)));
		jnms.setBounds(186, 414, 454, 151);
		jnms.setOpaque(false);
		this.add(jnms);
		jnms.add(jnmstip);
		
		JButton ok = new JButton("");
		ok.setToolTipText("\u51B3\u5B9A\u8BE5\u56DE\u5408\u7684\u884C\u52A8");
		ok.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/decide.png")));
		ok.setFont(new Font("等线", Font.BOLD, 22));
		ok.setBounds(670, 416, 128, 64);
		ok.setContentAreaFilled(false);
		this.add(ok);
		
		JButton gg = new JButton("");
		gg.setToolTipText("\u653E\u5F03\u8BE5\u56DE\u5408\u7684\u884C\u52A8");
		gg.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/giveup.png")));
		gg.setFont(new Font("等线", Font.BOLD, 22));
		gg.setBounds(670, 500, 128, 64);
		gg.setContentAreaFilled(false);
		this.add(gg);
	}
	
	void closethis(){
		dispose();
	}
	
}