package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entity.BGM;
import entity.Server;
import entity.User;
import util.Config;
import util.Host;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4211596209144142236L;
	
	public Login() {
		
		this.setLayout(null);
		
		this.setSize(1366,768);
		this.setTitle("登录至学园激斗事件簿");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setBackground(new Color(255,255,255,220));
		
		ImageIcon img = new ImageIcon(this.getClass().getClassLoader().getResource("img/login.jpg"));
		JLabel imgLabel = new JLabel(img);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,1366,768);
		Container contain = this.getContentPane();
		((JPanel)contain).setOpaque(false);
		
		this.setIconImage(new ImageIcon(this.getClass().getClassLoader().getResource("img/logo.png")).getImage());
		
		JLabel yhm=new JLabel("学园通行证");
		yhm.setFont(Config.LoginFont);
		yhm.setBounds(620,170,300,80);
		yhm.setForeground(Color.white);
		
		JLabel mm=new JLabel("通行证密码");
		mm.setFont(Config.LoginFont);
		mm.setBounds(620,260,300,80);
		mm.setForeground(Color.white);
		
		JTextField sryhm=new JTextField(15);
		sryhm.setBounds(880,180,320,60);
		sryhm.setFont(Config.ButtonFont);
		sryhm.setOpaque(false);
		sryhm.setForeground(Color.white);
		
		JPasswordField srmm=new JPasswordField();
		srmm.setBounds(880,271,320,60);
		srmm.setFont(Config.LoginFont);
		srmm.setOpaque(false);
		srmm.setForeground(Color.white);
		
		JComboBox<String> fwq = new JComboBox<String>();
		fwq.addItem("官方上海服务器");
		fwq.addItem("第三方社区");
		fwq.setFont(Config.SmallFont);
		fwq.setBounds(880,365,320,50);
		fwq.setOpaque(false);
		
		JLabel copyright=new JLabel("游戏内所有背景设定版权归紫荆学园所有");
		copyright.setFont(new Font("微软雅黑 Light",Font.PLAIN,20));
		copyright.setBounds(983,685,375,50);
		copyright.setForeground(new Color(205,92,92));
		
		JLabel copyright2=new JLabel("Copyright \u00A92014-2020 紫荆学园 Inc.");
		copyright2.setFont(new Font("微软雅黑 Light",Font.PLAIN,16));
		copyright2.setBounds(1080,710,375,50);
		copyright2.setForeground(new Color(205,92,92));
		
		JButton reg=new JButton("注册入口");
		reg.setBounds(1185,185,200,50);
		reg.setFont(Config.SmallFont);
		reg.setForeground(Color.white);
		reg.setContentAreaFilled(false);
		reg.setBorderPainted(false);
		
		reg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openregister();
			}
		});
		
		JButton find=new JButton("找回密码");
		find.setBounds(1185,276,200,50);
		find.setFont(Config.SmallFont);
		find.setForeground(Color.white);
		find.setContentAreaFilled(false);
		find.setBorderPainted(false);
		
		find.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"该功能正在开发中，如需找回密码，请联系管理员邮箱：yezi@wrss.org。");
			}
		});
		
		JButton ok=new JButton("进入游戏");
		ok.setBounds(1080,475,200,50);
		ok.setFont(Config.ButtonFont);
		ok.setForeground(new Color(230,102,131));
		ok.setContentAreaFilled(false);
		ok.setBorderPainted(false);
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(sryhm.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"未提供学园通行证");
				} else {
					if(String.valueOf(srmm.getPassword()).equals("")) {
						JOptionPane.showMessageDialog(null,"密码不能为空");
					} else {
						if(fwq.getSelectedItem().equals("官方上海服务器")) {
							User user=LoginGame(sryhm.getText().trim(),String.valueOf(srmm.getPassword()));
							if(user!=null) {
								if(!user.getUsername().equals("游戏服务器处于关闭状态！")) {
									if(!user.getUsername().equals("与服务器连接失败了！")) {
										if(!user.getUsername().equals("客户端版本过低！")) {
											if(!user.getUsername().equals("该账号已经处于登录状态！")) {
												if(!user.getUsername().equals("该账号已被封停！")) {
													if(!user.getUsername().equals("账号或密码错误！")) {
														disposethis();
														Config.bgm = new BGM();
														Config.bgm.start();
														new Main(user,Host.officialserver).setVisible(true);
													} else {
														JOptionPane.showMessageDialog(null,"通行证用户名或密码错误，请重新输入。");
													}
												} else {
													JOptionPane.showMessageDialog(null,"该通行证已被封停！！！请联系管理员解封。");
												}
											} else {
												JOptionPane.showMessageDialog(null,"该通行证处于登录状态，不能重复登录！请联系管理员取消登录限制。");
											}
										} else {
											JOptionPane.showMessageDialog(null,"客户端版本过低，无法登录，请前往官方博客下载最新版本！");
										}
									} else {
										JOptionPane.showMessageDialog(null,"与服务器连接失败。");
									}
								} else {
									JOptionPane.showMessageDialog(null,"游戏服务器处于关闭状态，暂时无法登录。");
								}
							} else {
								JOptionPane.showMessageDialog(null,"遇到未知错误，请稍后重试。");
							}
						} else {
							User user=Config.s.queryUser(sryhm.getText().trim(),String.valueOf(srmm.getPassword()));
							if(user!=null) {
								disposethis();
								Config.bgm = new BGM();
								Config.bgm.start();
								new OfflineMain(user).setVisible(true);
							} else {
								JOptionPane.showMessageDialog(null,"登录失败，请检查通行证用户名和密码是否正确。");
							}
						}
					}
				}
			}
		});
		
		JButton x=new JButton("退出游戏");
		x.setBounds(1180,640,200,50);
		x.setFont(Config.SmallFont);
		x.setForeground(new Color(230,102,131));
		x.setContentAreaFilled(false);
		x.setBorderPainted(false);
		
		x.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JButton offical=new JButton("官网博客");
		offical.setBounds(1180,580,200,50);
		offical.setFont(Config.SmallFont);
		offical.setForeground(new Color(230,102,131));
		offical.setContentAreaFilled(false);
		offical.setBorderPainted(false);
		
		offical.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.wrss.org/zytter"));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		this.add(yhm);
		this.add(mm);
		this.add(sryhm);
		this.add(srmm);
		this.add(fwq);
		this.add(copyright);
		this.add(copyright2);
		this.add(reg);
		this.add(find);
		this.add(ok);
		this.add(x);
		this.add(offical);
		
		this.setLocationRelativeTo(null);
		
		new Thread() {

			@Override
			public void run() {
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				showsystemtip();
			}
			
		}.start();
		
	}
	
	public void showsystemtip() {
		JLabel tip = Config.s.getSystemTip();
		if(!tip.getText().equals("1")) {
			JOptionPane.showMessageDialog(null, tip.getText());
		}
	}
	
	public void OfflineLogin() {
		
	}
	
	public User LoginGame(String username,String password) {
		/**
		 * - 使用socket通信 实现服务器端登录
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
			
			socket = new Socket(Host.officialserver.getIP(), Server.matchport);
		
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
			
			if(x==1) { // 版本号一致
				pw = new PrintWriter(os);
				pw.write(username+"\n");
				pw.flush();
				pw.write(password+"\n");
				pw.flush();
				
				int y = dis.readInt();
				
				if(y==1) {
					//1为验证成功
					try {
						ois = new ObjectInputStream(is);
						user = (User) ois.readObject();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} if(y==2) {
					//2为用户名或密码错误
					user = new User("账号或密码错误！");
				} else  if(y==3) {
					//3为重复登录
					user = new User("该账号已经处于登录状态！");
				} else if(y==4) {
					//4被封号
					user = new User("该账号已被封停！");
				}
			} else if(x==2) {
				//2为客户端版本过低
				user = new User("客户端版本过低！");
			} else {
				user = new User("与服务器连接失败了！");
			}
		} catch (ConnectException e) {
			//服务器没打开
			user = new User("游戏服务器处于关闭状态！");
        } catch (IOException e) {
        	//保留
        	user = new User("与服务器连接失败了！");
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
	
	void disposethis(){
		dispose();
	}
	
	void openregister() {
		setEnabled(false);
		new Reg(this).setVisible(true);
	}
	
}
