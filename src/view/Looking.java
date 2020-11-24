package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entity.BGM;
import entity.Server;
import entity.User;
import util.Config;
import util.Host;

import javax.swing.JButton;

public class Looking extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5132422289932770520L;
	
	User user;
	
	JLabel tips;
	JTable table;
	DefaultTableModel dtm;
	
	ArrayList<Server> servers;
	
	OfflineMain om;
	
	boolean isleft = false;
	
	public Looking(User user, OfflineMain om) {
		
		this.user=user;
		this.om=om;
		
		this.setTitle("Ѱ�����߷�����");
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		this.setSize(1005, 610);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("img/server.jpg"));
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, 1005, 610);
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false);
		
		tips = new JLabel("���ڼ��ط������б�......");
		tips.setForeground(new Color(219, 112, 147));
		tips.setFont(new Font("΢���ź�", Font.BOLD, 65));
		tips.setHorizontalAlignment(SwingConstants.CENTER);
		tips.setBounds(10, 63, 980, 420);
		this.add(tips);
		
		JLabel servertip = new JLabel("�ͻ��˰汾����ͷ������汾��ͬ���ܼ��롣");
		servertip.setFont(new Font("΢���ź�", Font.BOLD, 25));
		servertip.setBounds(20, 509, 658, 48);
		this.add(servertip);
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		
		table = new JTable();
		table.setGridColor(new Color(255, 255, 255));
		table.setRowSelectionAllowed(true);
		table.setShowGrid(false);
		table.setOpaque(false);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionBackground(Config.enemycolor);
		table.setFont(new Font("΢���ź� Light", Font.BOLD, 18));
		table.setForeground(Color.darkGray);
		table.setRowHeight(35);
		table.setDefaultRenderer(Object.class, r);
		dtm = new DefaultTableModel(
				new Object[][] {},
				new String[] { "��������ʶ", "����������", "������IP", "��Ҫ����", "��������ע", "״̬" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -4811177388371159536L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };
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
		table.getColumnModel().getColumn(0).setWidth(40);
		table.getColumnModel().getColumn(1).setWidth(250);
		table.getColumnModel().getColumn(2).setWidth(120);
		table.getColumnModel().getColumn(3).setWidth(40);
		table.getColumnModel().getColumn(4).setWidth(450);
		table.getColumnModel().getColumn(5).setWidth(80);
		table.setBounds(10, 63, 980, 420);
		dtm.setColumnIdentifiers(new String[] { "��������ʶ", "����������", "������IP", "��Ҫ����", "��������ע", "״̬" });
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 35));
		table.getTableHeader().setFont(new Font("΢���ź�", Font.BOLD, 18));
		table.getTableHeader().setEnabled(false);
		table.getTableHeader().setOpaque(false);
		JScrollPane sc = new JScrollPane(table);
		sc.setBounds(10, 63, 980, 420);
		sc.setOpaque(false);
		sc.getViewport().setOpaque(false);
		this.add(sc);
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedRow()!=-1) {
					Server s=servers.get(table.getSelectedRow());
					servertip.setText("�÷������İ汾��"+s.getVer());
				}
			}
		});
		
		JLabel hostslist = new JLabel("\u670D\u52A1\u5668\u5217\u8868");
		hostslist.setHorizontalAlignment(SwingConstants.CENTER);
		hostslist.setForeground(new Color(220, 20, 60));
		hostslist.setFont(new Font("΢���ź�", Font.BOLD, 30));
		hostslist.setBounds(10, 10, 980, 40);
		this.add(hostslist);
		
		JButton join = new JButton("");
		
		join.setFont(new Font("΢���ź�", Font.BOLD, 25));
		join.setContentAreaFilled(false);
		join.setForeground(new Color(205, 92, 92));
		join.setBounds(846, 493, 144, 77);
		join.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/connect.png")));
		this.add(join);
		join.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1) {
					if(JOptionPane.showConfirmDialog(null, "ȷ�����ӵ��˷�������", "���ӷ�����", JOptionPane.YES_NO_OPTION)==0) {
						om.setEnabled(false);
						Looking.this.setEnabled(false);
						Server s=servers.get(table.getSelectedRow());
						if(s.getStatus()==1) {
							om.setEnabled(true);
							Looking.this.setEnabled(true);
							JOptionPane.showMessageDialog(null, "�÷����������˽�ֹ���ӣ��޷��������÷�������");
						} else {
							String pw = s.getPassword();
							if(pw!=null) { // ���벻Ϊnull
								if(!pw.equals("")) { // ���벻Ϊ��
									String input = JOptionPane.showInputDialog("������÷��������������룺");
									if(input!=null&&input.equals(pw)) {
										ConnectServer(s);
									} else {
										om.setEnabled(true);
										Looking.this.setEnabled(true);
										JOptionPane.showMessageDialog(null, "������֤����");
										om.setVisible(true);
										Looking.this.setVisible(true);
									}
								} else {
									om.setVisible(true);
									Looking.this.setVisible(true);
									ConnectServer(s);
								}
							} else {
								om.setVisible(true);
								Looking.this.setVisible(true);
								ConnectServer(s);
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "δѡ���κη�������");
				}
			}
		});
		
		JButton lookfor = new JButton("");
		lookfor.setForeground(new Color(205, 92, 92));
		lookfor.setContentAreaFilled(false);
		lookfor.setFont(new Font("΢���ź�", Font.BOLD, 25));
		lookfor.setBounds(688, 493, 148, 77);
		lookfor.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/searchip.png")));
		this.add(lookfor);
		lookfor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String ip = JOptionPane.showInputDialog("��ָ��һ��IP��ַ��");
				if(ip!=null) {
					if(!ip.equals("")) {
						if(isIPAddressByRegex(ip)) {
							if(!ip.equals(Host.officialserver.getIP())) {
								if(!isExistIP(ip)) {
									if(JOptionPane.showConfirmDialog(null, "ȷ�����ӵ��˷�������", "���ӷ�����", JOptionPane.YES_NO_OPTION)==0) {
										om.setEnabled(false);
										Looking.this.setEnabled(false);
										Server s = new Server(ip, ip);
										ConnectServer(s);
									}
								} else {
									JOptionPane.showMessageDialog(null, "����ͨ�����ַ�ʽ�����б��д��ڵķ�������");
								}
							} else {
								JOptionPane.showMessageDialog(null, "����ͨ�����ַ�ʽ���ӹٷ���������");
							}
						} else {
							JOptionPane.showMessageDialog(null, "�ⲻ��һ��IP��ַ��");
						}
					}
				}
			}
		});
		
		this.addWindowListener(new WindowListener() {
			@Override
			public void windowClosing(WindowEvent e) {
				om.match.setEnabled(true);
			}
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}
			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}
			@Override
			public void windowIconified(WindowEvent e) {
				
			}
			@Override
			public void windowOpened(WindowEvent e) {
				
			}
		});
		
	}
	
	public boolean isExistIP(String ip) {
		for (Server s : servers) {
			if (ip.equals(s.getIP())) {
				return true;
			}
		}
		return false;
	}
	
    public boolean isIPAddressByRegex(String str) {
        String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
        if (str.matches(regex)) {
            String[] arr = str.split("\\.");
            for (int i = 0; i < 4; i++) {
                int temp = Integer.parseInt(arr[i]);
                if (temp < 0 || temp > 255) return false;
            }
            return true;
        } else return false;
    }
	
	public void ConnectServer(Server s) {
		User user2 = LoginGame(user.getUsername(), user.getPassword(), s);
		if (user2 != null) {
			if (!user2.getUsername().equals("��Ϸ���������ڹر�״̬��")) {
				if (!user2.getUsername().equals("�����������ʧ���ˣ�")) {
					if (!user2.getUsername().equals("�ͻ��˰汾���ͣ�")) {
						if (!user2.getUsername().equals("���˺��Ѿ����ڵ�¼״̬��")) {
							if (!user2.getUsername().equals("���˺��ѱ���ͣ��")) {
								if (!user2.getUsername().equals("�˺Ż��������")) {
									om.dispose();
									Looking.this.dispose();
									Config.bgm.stopBGM();
									Config.bgm = new BGM();
									Config.bgm.start();
									new Main(user2, s).setVisible(true);
								} else {
									om.setEnabled(true);
									Looking.this.setEnabled(true);
									JOptionPane.showMessageDialog(null, "ͨ��֤�û���������������������롣");
								}
							} else {
								om.setEnabled(true);
								Looking.this.setEnabled(true);
								JOptionPane.showMessageDialog(null, "��ͨ��֤�ѱ��ٷ���ͣ�޷������κη�������");
							}
						} else {
							om.setEnabled(true);
							Looking.this.setEnabled(true);
							JOptionPane.showMessageDialog(null, "��ͨ��֤�ڸ÷������ϴ��ڵ�¼״̬�������ظ���¼��");
						}
					} else {
						om.setEnabled(true);
						Looking.this.setEnabled(true);
						JOptionPane.showMessageDialog(null, "�ͻ��˰汾�͸÷�����������");
					}
				} else {
					om.setEnabled(true);
					Looking.this.setEnabled(true);
					JOptionPane.showMessageDialog(null, "�޷����ӵ��˷�������");
				}
			} else {
				om.setEnabled(true);
				Looking.this.setEnabled(true);
				JOptionPane.showMessageDialog(null, "�޷����ӵ��˷�������");
			}
		} else {
			om.setEnabled(true);
			Looking.this.setEnabled(true);
			JOptionPane.showMessageDialog(null, "����δ֪�������Ժ����ԡ�");
		}
	}
	
	public User LoginGame(String username,String password,Server s) {
		/**
		 * - ʹ��socketͨ�� ʵ�ַ������˵�¼
		 */
		
		Socket socket=null;
		
		OutputStream os = null;
		DataOutputStream dos = null;
		PrintWriter pw = null;
		
		InputStream is = null;
		DataInputStream dis = null;
		ObjectInputStream ois = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		User user = null;
		
		try {
			
			socket = new Socket(s.getIP(), Server.matchport);
		
			socket.setSoTimeout(350000);

			os = socket.getOutputStream();
			dos = new DataOutputStream(os);

			dos.writeInt(1);
			dos.flush();
			
			dos.writeInt(Config.clientversion);
			dos.flush();
			
			is = socket.getInputStream();
			dis = new DataInputStream(is);
			
			int x = dis.readInt();
			
			if(x==1) { // �汾��һ��
				pw = new PrintWriter(os);
				pw.write(username+"\n");
				pw.flush();
				pw.write(password+"\n");
				pw.flush();
				
				int y = dis.readInt();
				
				if(y==1) {
					//1Ϊ��֤�ɹ�
					try {
						ois = new ObjectInputStream(is);
						user = (User) ois.readObject();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} if(y==2) {
					//2Ϊ�û������������
					user = new User("�˺Ż��������");
				} else  if(y==3) {
					//3Ϊ�ظ���¼
					user = new User("���˺��Ѿ����ڵ�¼״̬��");
				} else if(y==4) {
					//4�����
					user = new User("���˺��ѱ���ͣ��");
				}
			} else if(x==2) {
				//2Ϊ�ͻ��˰汾����
				user = new User("�ͻ��˰汾���ͣ�");
			} else {
				user = new User("�����������ʧ���ˣ�");
			}
		} catch (ConnectException e) {
			//������û��
			user = new User("��Ϸ���������ڹر�״̬��");
        } catch (IOException e) {
        	//����
        	user = new User("�����������ʧ���ˣ�");
        } finally {
			try {
				if(socket!=null) socket.shutdownInput();
				if(socket!=null) socket.shutdownOutput();
				if(br!=null) br.close();
				if(ois!=null) ois.close();
				if(dis!=null) dis.close();
				if(isr!=null) isr.close();
				if(is!=null) is.close();
				if(pw!=null) pw.close();
				if(dos!=null) dos.close();
				if(pw!=null) pw.close();
				if(socket!=null) socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return user;
    }
	
	void InitList() {
		servers = Config.s.getServerList();
		for(Server s : servers) {
			if(s.getPassword()!=null) {
				if(s.getPassword().equals("")) {
					if(s.getStatus()!=1) {
						dtm.addRow(new Object[] { s.getId(), s.getName(), s.getIP(), "��", s.getTip(), "����" });
					} else {
						dtm.addRow(new Object[] { s.getId(), s.getName(), s.getIP(), "��", s.getTip(), "��ֹ����" });
					}
				} else {
					if(s.getStatus()!=1) {
						dtm.addRow(new Object[] { s.getId(), s.getName(), s.getIP(), "��", s.getTip(), "����" });
					} else {
						dtm.addRow(new Object[] { s.getId(), s.getName(), s.getIP(), "��", s.getTip(), "��ֹ����" });
					}
				}
			} else {
				if(s.getStatus()!=1) {
					dtm.addRow(new Object[] { s.getId(), s.getName(), s.getIP(), "��", s.getTip(), "����" });
				} else {
					dtm.addRow(new Object[] { s.getId(), s.getName(), s.getIP(), "��", s.getTip(), "��ֹ����" });
				}
			}
			
		}
		this.remove(tips);
		this.repaint();
	}
}
