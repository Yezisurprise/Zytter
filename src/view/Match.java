package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import entity.Server;
import entity.User;
import entity.Voice;
import util.Config;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Container;

public class Match extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5409374029041038714L;

	int seconds = 0;
	int mins = 0;
	int accepttime=0;
	boolean ismatching = true;
	boolean isconnecting = false;
	boolean ready=false;
	
	User enemy=null;
	int room=0;
	
	Main main;
	User user;
	
	Server server;
	
	JButton cancel,receive,waiting;
	JLabel enemyname;
	JLabel playername;
	JLabel matching;
	
	Socket socket = null;
	OutputStream os = null;
	DataOutputStream dos = null;
	InputStream is = null;
	DataInputStream dis = null;
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	
	/**
	 * Create the frame.
	 */
	public Match(User user,Main main,Server server) {

		this.user=user;
		this.main=main;
		this.cancel=main.cancel;
		this.server=server;
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setSize(337,250);
		this.setLayout(null);
		this.setResizable(false);
		
		this.setTitle(user.getUsername()+"����ƥ����...");
		
		ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("img/matching.jpg"));
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, 337, 250);
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false);
		
		receive = new JButton("");
		receive.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/accept.png")));
		receive.setBounds(82, 115, 152, 48);
		receive.setContentAreaFilled(false);
		receive.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ready=true;
				setTitle("�ȴ��Է����ܱ���...");
				receive(user);
			}
		});
		
		waiting = new JButton("");
		waiting.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/accept.png")));
		waiting.setBounds(82, 115, 152, 48);
		waiting.setContentAreaFilled(false);
		waiting.setEnabled(false);
		
		enemyname = new JLabel("�������ӵ�ƥ�������...");
		enemyname.setForeground(new Color(255, 0, 0));
		enemyname.setFont(new Font("����", Font.BOLD, 22));
		enemyname.setHorizontalAlignment(SwingConstants.CENTER);
		enemyname.setBounds(10, 173, 301, 28);
		this.add(enemyname);
		
		playername = new JLabel(user.getUsername());
		playername.setForeground(new Color(233, 150, 122));
		playername.setHorizontalAlignment(SwingConstants.CENTER);
		playername.setFont(new Font("����", Font.BOLD, 22));
		playername.setBounds(10, 23, 301, 28);
		this.add(playername);
		
		matching = new JLabel("\u6B63\u5728\u5339\u914D\u4E2D...");
		matching.setForeground(Color.pink);
		matching.setHorizontalAlignment(SwingConstants.CENTER);
		matching.setFont(new Font("����", Font.BOLD, 34));
		matching.setBounds(10, 61, 305, 44);
		this.add(matching);
		
		this.setAlwaysOnTop(true);
		this.setType(JFrame.Type.UTILITY);
		this.setLocationRelativeTo(null);
		
		try {
			socket = new Socket(server.getIP(), Server.matchport);
			socket.setSoTimeout(350000);
			os = socket.getOutputStream(); // ������Ļ�ȡ
			dos = new DataOutputStream(os);
			dos.writeInt(2);
			dos.flush();
			is = socket.getInputStream(); // �������Ļ�ȡ
			dis = new DataInputStream(is);
			
			if(dis.readInt()==1) {
				enemyname.setText("�ѳɹ�����ƥ�����");
				new MatchingTimer().start();
				new FindingEnemy(user).start();
			} else {
				enemyname.setText("������ƥ�������ʧ��...");
				main.matching.setText("������ƥ�������ʧ��...");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	class HeartTimer extends Thread {

		@Override
		public void run() {
			while(ismatching) {
				try {
					dos.writeInt(-1);
					dos.flush();
					if(ismatching) sleep(3000);
				} catch (Exception e) {
				}
			}
			closeall();
		}
		
	}
	
	class MatchingTimer extends Thread {
		
		@Override
		public void run() {
			super.run();
			while(ismatching) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(seconds<59) {
					seconds++;
				} else {
					mins++;
					seconds = 0;
				}
				if(seconds<10) {
					enemyname.setText("��ƥ��ʱ����"+mins+"��0"+seconds+"��");
					main.matchingtimer.setText("��ƥ��ʱ����"+mins+"��0"+seconds+"��");
				} else {
					enemyname.setText("��ƥ��ʱ����"+mins+"��"+seconds+"��");
					main.matchingtimer.setText("��ƥ��ʱ����"+mins+"��"+seconds+"��");
				}
				
			}
		}
		
	}
	
	class ConnectingTimer extends Thread {
		
		@Override
		public void run() {
			super.run();
			while(isconnecting) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(seconds<59) {
					seconds++;
				} else {
					mins++;
					seconds = 0;
				}
				if(seconds<10) {
					enemyname.setText("����ʱ����"+mins+"��0"+seconds+"��");
					main.matchingtimer.setText("����ʱ����"+mins+"��0"+seconds+"��");
				} else {
					enemyname.setText("����ʱ����"+mins+"��"+seconds+"��");
					main.matchingtimer.setText("����ʱ����"+mins+"��"+seconds+"��");
				}
				
			}
		}
		
	}
	
	class FindingEnemy extends Thread {
		
		User user;
		
		public FindingEnemy(User user) {
			this.user=user;
		}

		@Override
		public void run() {
			super.run();
			room=0;
			try {
				os = socket.getOutputStream();
	            oos = new ObjectOutputStream(os);
	            oos.writeObject(user);
	            oos.flush();
	            dos = new DataOutputStream(os);
	            new HeartTimer().start();
	            is = socket.getInputStream();
	            ois = new ObjectInputStream(is);
				enemy = (User) ois.readObject();
	            dis = new DataInputStream(is);
	            room = dis.readInt();
			} catch (Exception e) {
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (enemy != null) {
				receive.setEnabled(true);
				matching.setText("����ȷ�ϱ���...");
				main.matching.setText("����ȷ�ϱ���...");
				cancel.setEnabled(false);
				matching.setForeground(new Color(46, 139, 87));
				ismatching = false;
				new Acceptgame(15).start();
				user.setIsmatching(2);
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				setVisible(true);
				new Voice(getClass().getResourceAsStream("/bgm/gamematchisready.mp3")).start();
				setTitle("���ܱ�������ʱ�� " + accepttime + " ��");
				matching.setText("�����Ѿ�����");
				add(receive);
				repaint();
				enemyname.setText("����ID��" + enemy.getUsername());
			} else {
				setAlwaysOnTop(false);
				ismatching = false;
				close();
				main.matching.setText("�����뿪ƥ����С�");
			}
		}
	}
	
	class Acceptgame extends Thread {
		
		public Acceptgame(int i) {
			accepttime=i;
		}
		
		@Override
		public void run() {
			super.run();
			while(accepttime>0) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				accepttime--;
				if(!ready) {
					setTitle("���ܱ�������ʱ�� "+accepttime+" ��");
				}
			}
			if(!ready) {
				if(Config.s.DisonnectServer(user,server)!=0) {
					setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null,"���ѷ�����ǰ�Ծ�");
					new Thread() {
						@Override
						public void run() {
							main.cancel.setVisible(false);
							main.matching.setVisible(false);
							main.matchingtimer.setVisible(false);
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							main.cancel.setEnabled(true);
							main.exit.setEnabled(true);
							main.match.setVisible(true);
						}
					}.start();
					new Thread() {
						@Override
						public void run() {
							Config.s.DeleteRoom(user);
						}
					}.start();
					dispose();
				} else {
					setAlwaysOnTop(false);
					JOptionPane.showMessageDialog(null,"�������ͨѶʧ�ܣ�������������");
					main.setEnabled(true);
					new Thread() {
						@Override
						public void run() {
							main.cancel.setVisible(false);
							main.matching.setVisible(false);
							main.matchingtimer.setVisible(false);
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							main.cancel.setEnabled(true);
							main.exit.setEnabled(true);
							main.match.setVisible(true);
						}
					}.start();
					dispose();
				}
			}
		}
		
	}
	
	User getEnemy(User user,int room) {
		ArrayList<User> players=Config.s.SearchEnemyByRoomID(room);
		for(int i=0;i<players.size();i++) {
			if(user.getUid()!=players.get(i).getUid()) {
				return players.get(i);
			}
		}
		return null;
	}
	
	void closeall() {
		try {
			socket.shutdownInput();
			socket.shutdownOutput();
			if(ois!=null) ois.close();
			if(dis!=null) dis.close();
			if(is!=null) is.close();
			if(dos!=null) dos.close();
			if(socket!=null) socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void close() {
		ismatching=false;
		new Thread() {
			@Override
			public void run() {
				Config.s.DeleteRoom(user);
			}
		}.start();
		if(Config.s.DisonnectServer(user,server)!=0) {
			dispose();
		} else {
			this.setAlwaysOnTop(false);
			JOptionPane.showMessageDialog(null,"�������ͨѶʧ�ܣ�������������");
			dispose();
		}
	}
	
	void receive(User user) {
		new changeButton().start();
		new Thread() {
			@Override
			public void run() {
				while (accepttime > 0) {
					setTitle("�ȴ��Է����ܱ���...");
					if (Config.s.AcceptGame(user,server) != 0) {
						setAlwaysOnTop(false);
						isconnecting = true;
						seconds = 0;
						mins = 0;
						main.matchingtimer.setVisible(false);
						main.matching.setVisible(false);
						main.matching.setText("������������Ϸ������...");
						new OpenSelectHero().start();
						new Voice(getClass().getResourceAsStream("/bgm/startmatch.mp3")).start();
						new ConnectingTimer().start();
						dispose();
						try {
							sleep(1000);
							main.matchingtimer.setVisible(true);
							main.matching.setVisible(true);
							sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Config.bgm.stopBGM();
						main.dispose();
						return;
					}
				}
				try {
					socket = new Socket(server.getIP(), Server.matchport);
					socket.setSoTimeout(350000);
					os = socket.getOutputStream();
					dos = new DataOutputStream(os);
					dos.writeInt(2);
					dos.flush();
					is = socket.getInputStream();
					dis = new DataInputStream(is);
					if(dis.readInt()==1) {
						ready=false;
						remove(waiting);
						remove(receive);
						repaint();
						enemyname.setText("�Է�δ���ܱ���������ƥ��");
						if(Config.s.IsRoomCreater(room, user, server)) {
							Config.s.setIsnotReady1(room);
						} else {
							Config.s.setIsnotReady2(room);
						}
						setVisible(false);
						matching.setText("����ƥ����...");
						main.matching.setText("����Ѱ�ұ���...");
						matching.setForeground(Color.pink);
						setTitle(user.getUsername() + "����ƥ����...");
						ismatching=true;
						cancel.setEnabled(true);
						new MatchingTimer().start();
						new FindingEnemy(user).start();
					} else {
						enemyname.setText("������ƥ�������ʧ��...");
						main.matching.setText("������ƥ�������ʧ��...");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	class changeButton extends Thread {

		@Override
		public void run() {
			super.run();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			remove(receive);
			add(waiting);
			repaint();
			matching.setText("�ȴ��Է�...");
			repaint();
		}
		
	}
	
	class OpenSelectHero extends Thread {

		@Override
		public void run() {
			super.run();
			try {
				sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			SelectHero sh = new SelectHero(user,enemy,room,server);
			sh.setVisible(true);
			sh.HeroSelect();
		}
		
	}
	
}