package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entity.User;
import util.Config;

public class Season extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1734285013147401412L;
	
	JTable table;
	DefaultTableModel dtm;
	
	User user;
	
	ArrayList<User> rankinglist;
	
	JLabel tips;

	public Season(User user,Main main) {
		
		main.setEnabled(false);
		
		this.setTitle("Season Status");
		this.setLayout(null);
		this.setUndecorated(true);
		this.setBackground(new Color(255,255,255,220));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(1000, 550);
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		this.setLocationRelativeTo(null);
		
		tips = new JLabel("正在加载排行榜，请稍后......");
		tips.setForeground(new Color(219, 112, 147));
		tips.setFont(new Font("微软雅黑", Font.BOLD, 65));
		tips.setHorizontalAlignment(SwingConstants.CENTER);
		tips.setBounds(10, 10, 980, 530);
		this.add(tips);
		
		JLabel season = new JLabel("[S2] 天赋梦路");
		season.setForeground(new Color(32, 178, 170));
		season.setFont(new Font("微软雅黑", Font.BOLD, 35));
		season.setHorizontalAlignment(SwingConstants.CENTER);
		season.setBounds(561, 11, 429, 50);
		this.add(season);
		
		JLabel seasontime = new JLabel("赛季时间：2020年11月1日~2021年7月31日");
		seasontime.setFont(new Font("微软雅黑", Font.BOLD, 18));
		seasontime.setHorizontalAlignment(SwingConstants.CENTER);
		seasontime.setBounds(561, 71, 429, 42);
		this.add(seasontime);
		
		JLabel status = new JLabel("\u5F53\u524D\u8D5B\u5B63\u6570\u636E");
		status.setForeground(new Color(233, 150, 122));
		status.setHorizontalAlignment(SwingConstants.LEFT);
		status.setFont(new Font("微软雅黑", Font.BOLD, 25));
		status.setBounds(577, 197, 190, 42);
		this.add(status);
		
		JLabel elo = new JLabel();
		if(user.getDjs()>0) elo.setText("Elo：暂无（Best："+user.getBestelo()+"）");
		else elo.setText("Elo："+user.getElo()+"（Best："+user.getBestelo()+"）");
		elo.setFont(new Font("微软雅黑 Light", Font.BOLD, 17));
		elo.setHorizontalAlignment(SwingConstants.LEFT);
		elo.setBounds(577, 249, 235, 50);
		this.add(elo);
		
		JLabel rating = new JLabel("Rating："+GetDouble(user.getRating(),"#0.00")+"（Best："+GetDouble(user.getBestrating(),"#0.00")+"）");
		rating.setHorizontalAlignment(SwingConstants.LEFT);
		rating.setFont(new Font("微软雅黑 Light", Font.BOLD, 17));
		rating.setBounds(577, 309, 235, 50);
		this.add(rating);
		
		JLabel rank = new JLabel();
		if(user.getDjs()>0) {
			if(user.getBestrank().equals("未定级")) rank.setText("Rank：未定级");
			else rank.setText("Rank：未定级（Best："+user.getBestrank()+"）");
		} else {
			rank.setText("Rank："+user.getRank()+"（Best："+user.getBestrank()+"）");
		}
		rank.setHorizontalAlignment(SwingConstants.LEFT);
		rank.setFont(new Font("微软雅黑 Light", Font.BOLD, 17));
		rank.setBounds(577, 369, 235, 50);
		this.add(rank);
		
		JLabel ranking = new JLabel("\u8D5B\u5B63\u5929\u68AF\u6392\u884C\u699C");
		ranking.setForeground(new Color(219, 112, 147));
		ranking.setFont(new Font("微软雅黑", Font.BOLD, 30));
		ranking.setHorizontalAlignment(SwingConstants.CENTER);
		ranking.setBounds(10, 11, 541, 42);
		this.add(ranking);
		
		JLabel lastest = new JLabel("\u6570\u636E\u7EDF\u8BA1\u622A\u6B62\u4E8E\uFF1A"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		lastest.setFont(new Font("微软雅黑", Font.BOLD, 22));
		lastest.setHorizontalAlignment(SwingConstants.CENTER);
		lastest.setBounds(10, 493, 551, 47);
		this.add(lastest);
		
		JLabel username = new JLabel(user.getUsername());
		username.setForeground(new Color(199, 21, 133));
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setFont(new Font("微软雅黑", Font.BOLD, 30));
		username.setBounds(561, 123, 429, 64);
		this.add(username);
		
		JLabel play = new JLabel("赛季场次："+user.getAll());
		play.setHorizontalAlignment(SwingConstants.LEFT);
		play.setFont(new Font("微软雅黑 Light", Font.BOLD, 18));
		play.setBounds(822, 249, 168, 38);
		this.add(play);
		
		JLabel win = new JLabel("赛季胜场："+user.getWin());
		win.setHorizontalAlignment(SwingConstants.LEFT);
		win.setFont(new Font("微软雅黑 Light", Font.BOLD, 18));
		win.setBounds(822, 292, 168, 38);
		this.add(win);
		
		JLabel lose = new JLabel("赛季败场："+user.getLose());
		lose.setHorizontalAlignment(SwingConstants.LEFT);
		lose.setFont(new Font("微软雅黑 Light", Font.BOLD, 18));
		lose.setBounds(822, 335, 168, 38);
		this.add(lose);
		
		DecimalFormat df = new DecimalFormat("#0.#");
		
		JLabel winrate = new JLabel("赛季胜率："+df.format(user.getWinrate()*100)+"%");
		winrate.setHorizontalAlignment(SwingConstants.LEFT);
		winrate.setFont(new Font("微软雅黑 Light", Font.BOLD, 18));
		winrate.setBounds(822, 380, 168, 38);
		this.add(winrate);
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		
		table = new JTable();
		table.setGridColor(new Color(255, 255, 255));
		table.setShowGrid(false);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setOpaque(false);
		table.setEnabled(false);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionBackground(new Color(255, 255, 255));
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("微软雅黑 Light", Font.BOLD, 15));
		table.setRowHeight(35);
		table.setDefaultRenderer(Object.class, r);
		dtm = new DefaultTableModel(
				new Object[][] { { "赛季排名", "玩家ID", "天梯积分", "段位评价", "技术得分", "赛季场次", "赛季胜场", "赛季胜率" }, },
				new String[] { "赛季排名", "玩家ID", "天梯积分", "段位评价", "技术得分", "赛季场次", "赛季胜场", "赛季胜率" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3845315616576303774L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		table.setModel(dtm);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.setBounds(10, 63, 551, 420);
		this.add(table);
		
		JButton back = new JButton("");
		back.setContentAreaFilled(false);
		back.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/return.png")));
		back.setBounds(734, 440, 256, 100);
		this.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.setEnabled(true);
				dispose();
			}
		});
		
		JButton history = new JButton("");
		history.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/history.png")));
		history.setContentAreaFilled(false);
		history.setBounds(571, 440, 153, 100);
		this.add(history);
		history.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Records r = new Records(user,Season.this);
				r.setVisible(true);
				r.InitGameRecords();
			}
		});
		
	}
	
	public void InitTable() {
		rankinglist = Config.s.getRankingList();
		rankinglist.sort(new Comparator<User>() {
			@Override
			public int compare(User i, User j) {
				if(i.getElo()<j.getElo()) return 1;
				else if(i.getElo()==j.getElo()) return 0;
				else return -1;
			}
		});
		DecimalFormat df = new DecimalFormat("#0.#");
		for(int i=0;table.getRowCount()<=rankinglist.size();i++) {
			User temp;
			if(i!=rankinglist.size()) temp = rankinglist.get(i);
			else break;
			if(!temp.getRank().equals("未定级")) {
				dtm.addRow(new Object[] { "TOP"+(i+1), temp.getUsername(), temp.getElo(), temp.getRank(), temp.getRating(),
						temp.getAll(), temp.getWin(), df.format(temp.getWinrate()*100)+"%" });
				if(table.getRowCount()==11) {
					dtm.addRow(new Object[] { "候补委员", temp.getUsername(), temp.getElo(), temp.getRank(), temp.getRating(),
						temp.getAll(), temp.getWin(), df.format(temp.getWinrate()*100)+"%" });
				}
			} else {
				dtm.addRow(new Object[] { "候补委员", temp.getUsername(), temp.getElo(), temp.getRank(), temp.getRating(),
						temp.getAll(), temp.getWin(), df.format(temp.getWinrate()*100)+"%" });
			}
		}
		tips.setVisible(false);
	}
	
	public double GetDouble(double d, String f) {
		DecimalFormat df=new DecimalFormat(f);
		BigDecimal bg = new BigDecimal(df.format(d));
		return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
}
