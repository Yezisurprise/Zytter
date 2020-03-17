package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entity.PlayerIcon;
import entity.Record;
import entity.User;
import util.Config;

public class Records extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2929342851569586735L;

	JTable table;
	DefaultTableModel dtm;
	
	JLabel tips,userrating,userelo,gameip;
	
	JPanel spec;

	JLabel p1,p2,vs,kill,death,adr,rating,elo,p1k,p1d,p1adr,p1r,p1e,p2k,p2d,p2adr,p2r,p2e;
	JLabel p1p,p1b,p2p,p2b;
	JPanel p1p1,p1p2,p1p3,p2p1,p2p2,p2p3,p1b1,p1b2,p2b1,p2b2;
	
	User user;
	
	ArrayList<Record> recordlist;
	Record r;
	
	public Records(User user,Season s) {
		
		this.user = user;
		
		this.setTitle("History Games Records");
		this.setUndecorated(true);
		this.setBackground(new Color(255,255,255,240));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 500);
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		tips = new JLabel("读取中，请稍后......");
		tips.setForeground(new Color(219, 112, 147));
		tips.setFont(new Font("微软雅黑", Font.BOLD, 65));
		tips.setHorizontalAlignment(SwingConstants.CENTER);
		tips.setBounds(10, 10, 980, 480);
		this.add(tips);
		
		JLabel titletip = new JLabel("历史战绩");
		titletip.setForeground(new Color(70, 130, 180));
		titletip.setFont(new Font("微软雅黑", Font.BOLD, 40));
		titletip.setHorizontalAlignment(SwingConstants.CENTER);
		titletip.setBounds(10, 10, 222, 55);
		this.add(titletip);
		
		JButton back = new JButton("");
		back.setContentAreaFilled(false);
		back.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/return.png")));
		back.setBounds(734, 390, 256, 100);
		this.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				s.setVisible(true);
				dispose();
			}
		});
		
		JPanel list = new JPanel();
		list.setLayout(null);
		list.setOpaque(false);
		list.setBounds(10, 75, 222, 415);
		this.add(list);
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		
		table = new JTable();
		table.setGridColor(new Color(255, 255, 255));
		table.setRowSelectionAllowed(true);
		table.setShowGrid(false);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setOpaque(false);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionBackground(new Color(255, 255, 255));
		table.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
		table.setRowHeight(35);
		table.setDefaultRenderer(Object.class, r);
		dtm = new DefaultTableModel(
				new Object[][] {},
				new String[] { "比赛编号", "胜负", "积分变动" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3845315616576303774L;
			boolean[] columnEditables = new boolean[] { false, false, false };
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		table.setModel(dtm);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.setBounds(0, 0, 222, 415);
		dtm.setColumnIdentifiers(new String[] { "编号", "胜负", "+/-" });
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 35));
		table.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 18));
		table.getTableHeader().setEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane sc = new JScrollPane(table);
		sc.setBounds(0, 0, 222, 415);
		sc.setOpaque(false);
		sc.getViewport().setOpaque(false);
		list.add(sc);
		
		spec = new JPanel();
		spec.setLayout(null);
		spec.setOpaque(false);
		spec.setBounds(242, 10, 748, 380);
		this.add(spec);
		
		spec.setVisible(false);
		
		p1 = new JLabel("玩家1");
		p1.setForeground(new Color(233, 150, 122));
		p1.setHorizontalAlignment(SwingConstants.CENTER);
		p1.setFont(new Font("微软雅黑", Font.PLAIN, 42));
		p1.setBounds(10, 10, 307, 62);
		spec.add(p1);
		
		p2 = new JLabel("玩家2");
		p2.setForeground(new Color(240, 128, 128));
		p2.setHorizontalAlignment(SwingConstants.CENTER);
		p2.setFont(new Font("微软雅黑", Font.PLAIN, 42));
		p2.setBounds(439, 10, 299, 62);
		spec.add(p2);
		
		vs = new JLabel("VS.");
		vs.setForeground(new Color(95, 158, 160));
		vs.setHorizontalAlignment(SwingConstants.CENTER);
		vs.setFont(new Font("微软雅黑", Font.PLAIN, 45));
		vs.setBounds(327, 10, 102, 62);
		spec.add(vs);
		
		kill = new JLabel("击杀");
		kill.setForeground(new Color(95, 158, 160));
		kill.setHorizontalAlignment(SwingConstants.CENTER);
		kill.setFont(new Font("微软雅黑", Font.BOLD, 20));
		kill.setBounds(327, 110, 102, 42);
		spec.add(kill);
		
		death = new JLabel("死亡");
		death.setForeground(new Color(95, 158, 160));
		death.setHorizontalAlignment(SwingConstants.CENTER);
		death.setFont(new Font("微软雅黑", Font.BOLD, 20));
		death.setBounds(327, 162, 102, 42);
		spec.add(death);
		
		adr = new JLabel("平均伤害");
		adr.setForeground(new Color(95, 158, 160));
		adr.setHorizontalAlignment(SwingConstants.CENTER);
		adr.setFont(new Font("微软雅黑", Font.BOLD, 20));
		adr.setBounds(327, 214, 102, 42);
		spec.add(adr);
		
		rating = new JLabel("Rating");
		rating.setForeground(new Color(95, 158, 160));
		rating.setHorizontalAlignment(SwingConstants.CENTER);
		rating.setFont(new Font("微软雅黑", Font.BOLD, 20));
		rating.setBounds(327, 266, 102, 42);
		spec.add(rating);
		
		elo = new JLabel("Elo(+/-)");
		elo.setForeground(new Color(95, 158, 160));
		elo.setHorizontalAlignment(SwingConstants.CENTER);
		elo.setFont(new Font("微软雅黑", Font.BOLD, 20));
		elo.setBounds(327, 318, 102, 42);
		spec.add(elo);
		
		p1k = new JLabel("玩家1击杀");
		p1k.setHorizontalAlignment(SwingConstants.CENTER);
		p1k.setForeground(new Color(95, 158, 160));
		p1k.setFont(new Font("微软雅黑", Font.BOLD, 20));
		p1k.setBounds(190, 110, 119, 42);
		spec.add(p1k);
		
		p1d = new JLabel("玩家1死亡");
		p1d.setHorizontalAlignment(SwingConstants.CENTER);
		p1d.setForeground(new Color(95, 158, 160));
		p1d.setFont(new Font("微软雅黑", Font.BOLD, 20));
		p1d.setBounds(190, 162, 119, 42);
		spec.add(p1d);
		
		p1adr = new JLabel("玩家1adr");
		p1adr.setHorizontalAlignment(SwingConstants.CENTER);
		p1adr.setForeground(new Color(95, 158, 160));
		p1adr.setFont(new Font("微软雅黑", Font.BOLD, 20));
		p1adr.setBounds(190, 214, 119, 42);
		spec.add(p1adr);
		
		p1r = new JLabel("玩家1技术分");
		p1r.setHorizontalAlignment(SwingConstants.CENTER);
		p1r.setForeground(new Color(95, 158, 160));
		p1r.setFont(new Font("微软雅黑", Font.BOLD, 20));
		p1r.setBounds(190, 266, 119, 42);
		spec.add(p1r);
		
		p1e = new JLabel("玩家1天梯分");
		p1e.setHorizontalAlignment(SwingConstants.CENTER);
		p1e.setForeground(new Color(95, 158, 160));
		p1e.setFont(new Font("微软雅黑", Font.BOLD, 20));
		p1e.setBounds(190, 318, 119, 42);
		spec.add(p1e);
		
		p2k = new JLabel("玩家2击杀");
		p2k.setHorizontalAlignment(SwingConstants.CENTER);
		p2k.setForeground(new Color(95, 158, 160));
		p2k.setFont(new Font("微软雅黑", Font.BOLD, 20));
		p2k.setBounds(439, 110, 119, 42);
		spec.add(p2k);
		
		p2d = new JLabel("玩家2死亡");
		p2d.setHorizontalAlignment(SwingConstants.CENTER);
		p2d.setForeground(new Color(95, 158, 160));
		p2d.setFont(new Font("微软雅黑", Font.BOLD, 20));
		p2d.setBounds(439, 162, 119, 42);
		spec.add(p2d);
		
		p2adr = new JLabel("玩家2adr");
		p2adr.setHorizontalAlignment(SwingConstants.CENTER);
		p2adr.setForeground(new Color(95, 158, 160));
		p2adr.setFont(new Font("微软雅黑", Font.BOLD, 20));
		p2adr.setBounds(439, 214, 119, 42);
		spec.add(p2adr);
		
		p2r = new JLabel("玩家2技术分");
		p2r.setHorizontalAlignment(SwingConstants.CENTER);
		p2r.setForeground(new Color(95, 158, 160));
		p2r.setFont(new Font("微软雅黑", Font.BOLD, 20));
		p2r.setBounds(439, 266, 119, 42);
		spec.add(p2r);
		
		p2e = new JLabel("玩家2天梯分");
		p2e.setHorizontalAlignment(SwingConstants.CENTER);
		p2e.setForeground(new Color(95, 158, 160));
		p2e.setFont(new Font("微软雅黑", Font.BOLD, 20));
		p2e.setBounds(439, 318, 119, 42);
		spec.add(p2e);
		
		p1p = new JLabel("PICK");
		p1p.setForeground(new Color(233, 150, 122));
		p1p.setHorizontalAlignment(SwingConstants.CENTER);
		p1p.setFont(new Font("微软雅黑", Font.BOLD, 20));
		p1p.setBounds(10, 77, 80, 25);
		spec.add(p1p);
		
		p1b = new JLabel("BAN");
		p1b.setHorizontalAlignment(SwingConstants.CENTER);
		p1b.setForeground(new Color(233, 150, 122));
		p1b.setFont(new Font("微软雅黑", Font.BOLD, 20));
		p1b.setBounds(100, 165, 80, 25);
		spec.add(p1b);
		
		p2p = new JLabel("PICK");
		p2p.setHorizontalAlignment(SwingConstants.CENTER);
		p2p.setForeground(new Color(240, 128, 128));
		p2p.setFont(new Font("微软雅黑", Font.BOLD, 20));
		p2p.setBounds(658, 75, 80, 25);
		spec.add(p2p);
		
		p2b = new JLabel("BAN");
		p2b.setHorizontalAlignment(SwingConstants.CENTER);
		p2b.setForeground(new Color(240, 128, 128));
		p2b.setFont(new Font("微软雅黑", Font.BOLD, 20));
		p2b.setBounds(568, 165, 80, 25);
		spec.add(p2b);
		
		p1p1 = new JPanel();
		p1p1.setBorder(new LineBorder(new Color(233, 150, 122), 3));
		p1p1.setOpaque(false);
		p1p1.setBounds(10, 110, 80, 80);
		spec.add(p1p1);
		
		p1p2 = new JPanel();
		p1p2.setOpaque(false);
		p1p2.setBorder(new LineBorder(new Color(233, 150, 122), 3));
		p1p2.setBounds(10, 200, 80, 80);
		spec.add(p1p2);
		
		p1p3 = new JPanel();
		p1p3.setOpaque(false);
		p1p3.setBorder(new LineBorder(new Color(233, 150, 122), 3));
		p1p3.setBounds(10, 290, 80, 80);
		spec.add(p1p3);
		
		p1b1 = new JPanel();
		p1b1.setOpaque(false);
		p1b1.setBorder(new LineBorder(new Color(233, 150, 122), 3));
		p1b1.setBounds(100, 200, 80, 80);
		spec.add(p1b1);
		
		p1b2 = new JPanel();
		p1b2.setOpaque(false);
		p1b2.setBorder(new LineBorder(new Color(233, 150, 122), 3));
		p1b2.setBounds(100, 290, 80, 80);
		spec.add(p1b2);
		
		p2p1 = new JPanel();
		p2p1.setOpaque(false);
		p2p1.setBorder(new LineBorder(new Color(240, 128, 128), 3));
		p2p1.setBounds(658, 110, 80, 80);
		spec.add(p2p1);
		
		p2p2 = new JPanel();
		p2p2.setOpaque(false);
		p2p2.setBorder(new LineBorder(new Color(240, 128, 128), 3));
		p2p2.setBounds(658, 200, 80, 80);
		spec.add(p2p2);
		
		p2p3 = new JPanel();
		p2p3.setOpaque(false);
		p2p3.setBorder(new LineBorder(new Color(240, 128, 128), 3));
		p2p3.setBounds(658, 290, 80, 80);
		spec.add(p2p3);
		
		p2b1 = new JPanel();
		p2b1.setOpaque(false);
		p2b1.setBorder(new LineBorder(new Color(240, 128, 128), 3));
		p2b1.setBounds(568, 200, 80, 80);
		spec.add(p2b1);
		
		p2b2 = new JPanel();
		p2b2.setOpaque(false);
		p2b2.setBorder(new LineBorder(new Color(240, 128, 128), 3));
		p2b2.setBounds(568, 290, 80, 80);
		spec.add(p2b2);
		
		userrating = new JLabel("正在为您查询比赛记录，请稍后......");
		userrating.setFont(new Font("微软雅黑", Font.BOLD, 20));
		userrating.setBounds(253, 400, 463, 31);
		this.add(userrating);
		
		userelo = new JLabel("数据统计截止于："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		userelo.setFont(new Font("微软雅黑", Font.BOLD, 20));
		userelo.setBounds(253, 441, 463, 31);
		this.add(userelo);
		
		gameip = new JLabel("");
		gameip.setBounds(253, 482, 471, 15);
		gameip.setFont(new Font("微软雅黑 Light",Font.PLAIN,18));
		this.add(gameip);
		
		this.setEnabled(false);
		
	}
	
	private void ShowSpec() {
		spec.setVisible(true);
	}
	
	public void InitGameRecords() {
		
		recordlist = Config.s.getRecordList(user);
		
		if(recordlist.size()==0) {
			userrating.setText("没有查询到您的任何一场比赛记录。 ");
		} else {
			userrating.setText("请从左边的列表中选择一场比赛来查看详细信息。");
		}
		
		for(int i=0;i<recordlist.size();i++) {
			Record temp = recordlist.get(i);
			if(temp.getP1().equals(user.getUsername())) {
				if(temp.getWinner()!=null) {
					if(temp.getWinner().equals(user.getUsername())) {
						if(temp.getP1djs()==0) {
							if(temp.getP1elop()>=0) {
								dtm.addRow(new Object[] { temp.getId(), "胜", "+"+String.valueOf(temp.getP1elop()) });
							} else {
								dtm.addRow(new Object[] { temp.getId(), "胜", temp.getP1elop() });
							}
						} else {
							dtm.addRow(new Object[] { temp.getId(), "胜", "定级赛" });
						}
					} else {
						if(temp.getP1djs()==0) {
							if(temp.getP1elop()>=0) {
								dtm.addRow(new Object[] { temp.getId(), "负", "+"+String.valueOf(temp.getP1elop()) });
							} else {
								dtm.addRow(new Object[] { temp.getId(), "负", temp.getP1elop() });
							}
						} else {
							dtm.addRow(new Object[] { temp.getId(), "负", "定级赛" });
						}
					}
				} else {
					if(temp.getP1djs()==0) {
						if(temp.getP1elop()>=0) {
							dtm.addRow(new Object[] { temp.getId(), "平局", "+"+String.valueOf(temp.getP1elop()) });
						} else {
							dtm.addRow(new Object[] { temp.getId(), "平局", temp.getP1elop() });
						}
					} else {
						dtm.addRow(new Object[] { temp.getId(), "平局", "定级赛" });
					}
				}
			} else {
				if(temp.getWinner()!=null) {
					if(temp.getWinner().equals(user.getUsername())) {
						if(temp.getP2djs()==0) {
							if(temp.getP2elop()>=0) {
								dtm.addRow(new Object[] { temp.getId(), "胜", "+"+String.valueOf(temp.getP2elop()) });
							} else {
								dtm.addRow(new Object[] { temp.getId(), "胜", temp.getP2elop() });
							}
						} else {
							dtm.addRow(new Object[] { temp.getId(), "胜", "定级赛" });
						}
					} else {
						if(temp.getP2djs()==0) {
							if(temp.getP2elop()>=0) {
								dtm.addRow(new Object[] { temp.getId(), "负", "+"+String.valueOf(temp.getP2elop()) });
							} else {
								dtm.addRow(new Object[] { temp.getId(), "负", temp.getP2elop() });
							}
						} else {
							dtm.addRow(new Object[] { temp.getId(), "负", "定级赛" });
						}
					}
				} else {
					if(temp.getP2djs()==0) {
						if(temp.getP2elop()>=0) {
							dtm.addRow(new Object[] { temp.getId(), "平局", "+"+String.valueOf(temp.getP2elop()) });
						} else {
							dtm.addRow(new Object[] { temp.getId(), "平局", temp.getP2elop() });
						}
					} else {
						dtm.addRow(new Object[] { temp.getId(), "平局", "定级赛" });
					}
				}
			}
		}
		
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(table.getSelectedRow()!=-1) {
					ShowSpec();
					r=recordlist.get(table.getSelectedRow());
					if(r.getP1().equals(user.getUsername())) {
						userrating.setText("本场比赛Rating："+r.getP1rating());
						if(r.getP1djs()==0) {
							if(r.getP1elop()>=0) {
								userelo.setText("本场比赛elo变动："+r.getP1elo()+"（+"+r.getP1elop()+"）→ "+(r.getP1elo()+r.getP1elop()));
							} else {
								userelo.setText("本场比赛elo变动："+r.getP1elo()+"（"+r.getP1elop()+"）→ "+(r.getP1elo()+r.getP1elop()));
							}
						} else {
							userelo.setText("本场比赛为定级赛，天梯积分已隐藏。");
						}
					} else {
						userrating.setText("本场比赛Rating："+r.getP2rating());
						if(r.getP2djs()==0) {
							if(r.getP2elop()>=0) {
								userelo.setText("本场比赛elo变动："+r.getP2elo()+"（+"+r.getP2elop()+"）→ "+(r.getP2elo()+r.getP2elop()));
							} else {
								userelo.setText("本场比赛elo变动："+r.getP2elo()+"（"+r.getP2elop()+"）→ "+(r.getP2elo()+r.getP2elop()));
							}
						} else {
							userelo.setText("本场比赛为定级赛，天梯积分已隐藏。");
						}
					}
					p1.setText(r.getP1());
					p2.setText(r.getP2());
					p1k.setText(String.valueOf(r.getP1k()));
					p1d.setText(String.valueOf(r.getP1d()));
					p2k.setText(String.valueOf(r.getP2k()));
					p2d.setText(String.valueOf(r.getP2d()));
					p1adr.setText(String.valueOf(r.getP1adr()));
					p2adr.setText(String.valueOf(r.getP2adr()));
					p1r.setText(String.valueOf(r.getP1rating()));
					p2r.setText(String.valueOf(r.getP2rating()));
					if(r.getP1djs()==0) {
						if(r.getP1elop()>=0) {
							p1e.setText("+"+r.getP1elop());
						} else {
							p1e.setText(String.valueOf(r.getP1elop()));
						}
					} else {
						p1e.setText("定级赛");
					}
					if(r.getP2djs()==0) {
						if(r.getP2elop()>=0) {
							p2e.setText("+"+r.getP2elop());
						} else {
							p2e.setText(String.valueOf(r.getP2elop()));
						}
					} else {
						p2e.setText("定级赛");
					}
					getBannedImage(r.getP1b1(), 1);
					getBannedImage(r.getP2b1(), 2);
					getBannedImage(r.getP1b2(), 3);
					getBannedImage(r.getP2b2(), 4);
					getPickedImage(r.getP1p1(), 1);
					getPickedImage(r.getP2p1(), 2);
					getPickedImage(r.getP1p2(), 3);
					getPickedImage(r.getP2p2(), 4);
					getPickedImage(r.getP1p3(), 5);
					getPickedImage(r.getP2p3(), 6);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		tips.setVisible(false);
		this.setEnabled(true);
		
	}
	
	void getBannedImage(int id,int x) {
		switch(id) {
			case 0:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new JPanel();
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new JPanel();
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new JPanel();
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new JPanel();
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 1:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 2:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 3:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 4:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 5:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 6:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 7:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 8:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 9:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 10:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 11:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
			case 12:{
				if(x==1) {
					spec.remove(p1b1);
					p1b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p1b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b1.setBorder(new LineBorder(Config.usercolor, 3));
					p1b1.setBounds(100, 200, 80, 80);
					p1b1.setOpaque(false);
					spec.add(p1b1);
				} else if(x==2) {
					spec.remove(p2b1);
					p2b1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p2b1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b1.setBounds(568, 200, 80, 80);
					p2b1.setOpaque(false);
					spec.add(p2b1);
				} else if(x==3) {
					spec.remove(p1b2);
					p1b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p1b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1b2.setBorder(new LineBorder(Config.usercolor, 3));
					p1b2.setBounds(100, 290, 80, 80);
					p1b2.setOpaque(false);
					spec.add(p1b2);
				} else if(x==4) {
					spec.remove(p2b2);
					p2b2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p2b2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2b2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2b2.setBounds(568, 290, 80, 80);
					p2b2.setOpaque(false);
					spec.add(p2b2);
				}
				repaint();
				break;
			}
		}
	}
	
	void getPickedImage(int id,int x) {
		/**
		 * 1
		 */
		switch(id) {
			case 0:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new JPanel();
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new JPanel();
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new JPanel();
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new JPanel();
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new JPanel();
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new JPanel();
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 1:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/yy.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 2:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lxs.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 3:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ysn.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 4:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/ltj.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 5:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zf.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 6:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/hyq.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 7:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/xyh.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 8:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zkx.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 9:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/zxy.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 10:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/lm.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 11:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/sjj.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
			case 12:{
				if(x==1) {
					spec.remove(p1p1);
					p1p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p1p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p1.setBorder(new LineBorder(Config.usercolor, 3));
					p1p1.setBounds(10, 110, 80, 80);
					p1p1.setOpaque(false);
					spec.add(p1p1);
				} else if(x==2) {
					spec.remove(p2p1);
					p2p1 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p2p1.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p1.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p1.setBounds(658, 110, 80, 80);
					p2p1.setOpaque(false);
					spec.add(p2p1);
				} else if(x==3) {
					spec.remove(p1p2);
					p1p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p1p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p2.setBorder(new LineBorder(Config.usercolor, 3));
					p1p2.setBounds(10, 200, 80, 80);
					p1p2.setOpaque(false);
					spec.add(p1p2);
				} else if(x==4) {
					spec.remove(p2p2);
					p2p2 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p2p2.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p2.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p2.setBounds(658, 200, 80, 80);
					p2p2.setOpaque(false);
					spec.add(p2p2);
				} else if(x==5) {
					spec.remove(p1p3);
					p1p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p1p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p1p3.setBorder(new LineBorder(Config.usercolor, 3));
					p1p3.setBounds(10, 290, 80, 80);
					p1p3.setOpaque(false);
					spec.add(p1p3);
				} else if(x==6) {
					spec.remove(p2p3);
					p2p3 = new PlayerIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/heroes/w.jpg")).getImage());  
					p2p3.setToolTipText(Config.Allheroes.get(id-1).getName());
					p2p3.setBorder(new LineBorder(Config.enemycolor, 3));
					p2p3.setBounds(658, 290, 80, 80);
					p2p3.setOpaque(false);
					spec.add(p2p3);
				}
				repaint();
				break;
			}
		}
	}
	
}
