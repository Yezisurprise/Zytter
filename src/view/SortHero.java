package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import entity.Hero;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SortHero extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -643359016795073608L;
	
	JComboBox<String> cb;
	
	JLabel tip;
	
	Hero hero = null;
	int remain;
	
	boolean finish = false;
	
	DataOutputStream dos;
	
	SelectHero s;
	
	public SortHero(SelectHero s,DataOutputStream dos,ArrayList<Hero> sorted,ArrayList<Hero> sure,int i,int max,int remain) {
		
		this.s=s;
		this.dos=dos;
		this.remain=remain;
		
		this.setTitle("请决定第"+i+"位次上场的英雄");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(450, 300);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		
		tip = new JLabel("请决定第"+i+"位次上场的英雄（剩余"+this.remain+"秒）");
		tip.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		tip.setHorizontalAlignment(SwingConstants.CENTER);
		tip.setBounds(10, 10, 414, 58);
		this.add(tip);
		
		cb = new JComboBox<String>();
		cb.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		cb.setBounds(99, 96, 238, 51);
		this.add(cb);
		
		for(int j=0;j<sorted.size();j++) {
			cb.addItem(sorted.get(j).getName());
		}
		
		JButton ok = new JButton("确认选择");
		ok.setFont(new Font("微软雅黑 Light", Font.BOLD, 20));
		ok.setBounds(148, 194, 151, 41);
		this.add(ok);
		
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hero = sorted.get(cb.getSelectedIndex());
				sure.add(hero);
				try {
					dos.writeInt(hero.getId());
					dos.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				SortHero.this.finish = true;
				if(i!=max) {
					sorted.remove(hero);
					new SortHero(s,dos,sorted, sure, i+1, max, SortHero.this.remain).setVisible(true);
				} else {
					try {
						dos.writeInt(-2);
						dos.flush();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
				dispose();
			}
		});
		
		new Thread() {
			@Override
			public void run() {
				while(SortHero.this.remain>0) {
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					SortHero.this.remain--;
					tip.setText("请决定第"+i+"位次上场的英雄（剩余"+SortHero.this.remain+"秒）");
					if(SortHero.this.finish) {
						break;
					}
				}
				if(!SortHero.this.finish) {
					for(int i=0;i<sorted.size();i++) {
						try {
							dos.writeInt(sorted.get(i).getId());
							dos.flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					sure.addAll(sorted);
					try {
						dos.writeInt(-2);
						dos.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				dispose();
			}
		}.start();
		
	}
	
}
