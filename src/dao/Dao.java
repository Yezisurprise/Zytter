package dao;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import entity.Hero;
import entity.Item;
import entity.Record;
import entity.Server;
import entity.Skill;
import entity.User;
import util.Config;
import util.DBUtil;

public class Dao {
	
	/**
	 * public User queryUser(String username,String password)
	 */
	public User queryUser(String username,String password){
		Connection conn = DBUtil.getConnect();
		ResultSet rs =null;
		PreparedStatement psmt=null;
		User user=null;
	    String sql="select * from users where username=? and password=?";
	    	try {
	    		psmt= conn.prepareStatement(sql);
				//给占位符赋值
				psmt.setString(1, username);
				psmt.setString(2, password);
				//执行sql语句
				rs = psmt.executeQuery();
				//处理结果集
				if(rs.next()){//调用next()方法将游标移动到下一行，若无数据，返回false
					int uid=rs.getInt(1);
					String un=rs.getString(2);
					String pw=rs.getString(3);
					double rating=rs.getDouble(4);
					int elo=rs.getInt(5);
					String rank=rs.getString(6);
					int djs=rs.getInt(7);
					int all=rs.getInt(8);
					int win=rs.getInt(9);
					int lose=rs.getInt(10);
					double winrate=rs.getDouble(11);
					int allplay=rs.getInt(14);
					int allwin=rs.getInt(15);
					int alllose=rs.getInt(16);
					double allwinrate=rs.getDouble(17);
					String bestrank=rs.getString(18);
					int bestelo=rs.getInt(19);
					double bestrating=rs.getDouble(20);
					user=new User(uid,un,pw,rating,elo,rank,djs,all,win,lose,winrate,allplay,allwin,alllose,allwinrate,bestrank,bestelo,bestrating);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeRs(rs);
				DBUtil.closePSMT(psmt);
				DBUtil.closeConnect(conn);
			}
		return user;
	}
	
	public User queryUser(int uid,String password) {
		/**
		 * public User queryUser(int uid,String password)
		 */
		Connection conn = DBUtil.getConnect();
		ResultSet rs =null;
		PreparedStatement psmt=null;
		User user=null;
	    String sql="select * from users where uid=? and password=?";
	    	try {
	    		psmt= conn.prepareStatement(sql);	
				//给占位符赋值
				psmt.setInt(1, uid);
				psmt.setString(2, password);
				//执行sql语句
				rs = psmt.executeQuery();
				//处理结果集
				if(rs.next()){//调用next()方法将游标移动到下一行，若无数据，返回false
					String un=rs.getString(2);
					String pw=rs.getString(3);
					double rating=rs.getDouble(4);
					int elo=rs.getInt(5);
					String rank=rs.getString(6);
					int djs=rs.getInt(7);
					int all=rs.getInt(8);
					int win=rs.getInt(9);
					int lose=rs.getInt(10);
					double winrate=rs.getDouble(11);
					int allplay=rs.getInt(14);
					int allwin=rs.getInt(15);
					int alllose=rs.getInt(16);
					double allwinrate=rs.getDouble(17);
					String bestrank=rs.getString(18);
					int bestelo=rs.getInt(19);
					double bestrating=rs.getDouble(20);
					user=new User(uid,un,pw,rating,elo,rank,djs,all,win,lose,winrate,allplay,allwin,alllose,allwinrate,bestrank,bestelo,bestrating);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeRs(rs);
				DBUtil.closePSMT(psmt);
				DBUtil.closeConnect(conn);
			}
		return user;
	}

	public User queryUser(String username,int uid) {
		/**
		 * public User queryUser(String username,int uid)
		 * - 功能：通过用户名和uid查找对应的玩家
		 */
		Connection conn = DBUtil.getConnect();
		ResultSet rs =null;
		PreparedStatement psmt=null;
		User user=null;
	    String sql="select * from users where username=? and uid=?";
	    	try {
	    		psmt= conn.prepareStatement(sql);	
				//给占位符赋值
	    		psmt.setString(1, username);
				psmt.setInt(2, uid);
				//执行sql语句
				rs = psmt.executeQuery();
				//处理结果集
				if(rs.next()){//调用next()方法将游标移动到下一行，若无数据，返回false
					int id=rs.getInt(1);
					String un=rs.getString(2);
					String pw=rs.getString(3);
					double rating=rs.getDouble(4);
					int elo=rs.getInt(5);
					String rank=rs.getString(6);
					int djs=rs.getInt(7);
					int all=rs.getInt(8);
					int win=rs.getInt(9);
					int lose=rs.getInt(10);
					double winrate=rs.getDouble(11);
					int allplay=rs.getInt(14);
					int allwin=rs.getInt(15);
					int alllose=rs.getInt(16);
					double allwinrate=rs.getDouble(17);
					String bestrank=rs.getString(18);
					int bestelo=rs.getInt(19);
					double bestrating=rs.getDouble(20);
					user=new User(id,un,pw,rating,elo,rank,djs,all,win,lose,winrate,allplay,allwin,alllose,allwinrate,bestrank,bestelo,bestrating);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeRs(rs);
				DBUtil.closePSMT(psmt);
				DBUtil.closeConnect(conn);
			}
		return user;
	}
	
	public User queryUser(int uid) {
		/**
		 * public User queryUser(int uid)
		 * - 功能：通过uid查找对应的玩家
		 * - 参数：int uid -- 玩家uid
		 * - 返回值：User -- 为null表示不存在该用户
		 */
		Connection conn = DBUtil.getConnect();
		ResultSet rs =null;
		PreparedStatement psmt=null;
		User user=null;
	    String sql="select * from users where uid=?";
	    	try {
	    		psmt= conn.prepareStatement(sql);	
				//给占位符赋值
				psmt.setInt(1, uid);
				//执行sql语句
				rs = psmt.executeQuery();
				//处理结果集
				if(rs.next()){//调用next()方法将游标移动到下一行，若无数据，返回false
					int id=rs.getInt(1);
					String un=rs.getString(2);
					String pw=rs.getString(3);
					double rating=rs.getDouble(4);
					int elo=rs.getInt(5);
					String rank=rs.getString(6);
					int djs=rs.getInt(7);
					int all=rs.getInt(8);
					int win=rs.getInt(9);
					int lose=rs.getInt(10);
					double winrate=rs.getDouble(11);
					int allplay=rs.getInt(14);
					int allwin=rs.getInt(15);
					int alllose=rs.getInt(16);
					double allwinrate=rs.getDouble(17);
					String bestrank=rs.getString(18);
					int bestelo=rs.getInt(19);
					double bestrating=rs.getDouble(20);
					user=new User(id,un,pw,rating,elo,rank,djs,all,win,lose,winrate,allplay,allwin,alllose,allwinrate,bestrank,bestelo,bestrating);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeRs(rs);
				DBUtil.closePSMT(psmt);
				DBUtil.closeConnect(conn);
			}
		return user;
	}
	
	public int addUser(String username,String password) {
		/**
		 * User addUser(String username,String password)
		 * - 功能：向数据库添加新用户记录
		 * - 参数：String username -- 用户名；String password -- 密码
		 * - 返回值：int -- 影响的行数
		 */
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt=null;
		String sql="insert into users(username,password) values(?,?)";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, username);
			psmt.setString(2, password);
			i=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public boolean isexist(String username) {
		/**
		 * public boolean isexist(String username)
		 * - 功能：判断该用户名是否存在
		 * - 参数：String username -- 需要判断的用户名
		 * - 返回值：boolean -- true or false
		 */
		Connection conn=DBUtil.getConnect();
		PreparedStatement psmt=null;
		ResultSet rs=null;
		String sql="select * from users where username=?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1,username);
			rs=psmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				psmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public int updateUser(int all,int win,int lose,double winrate,int uid) {
		/**
		 * - 功能：更新用户场次和胜率
		 */
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt1=null;
		String sql1="update users set alls=?,win=?,lose=?,winrate=? where uid=?";
		try {
			
			psmt1=conn.prepareStatement(sql1);
			psmt1.setInt(1, all);
			psmt1.setInt(2, win);
			psmt1.setInt(3, lose);
			psmt1.setDouble(4, winrate);
			psmt1.setInt(5, uid);
			i=psmt1.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt1);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int updateUserAllStatus(int all,int win,int lose,double winrate,int uid) {
		/**
		 * - 功能：更新用户总场次和胜率
		 */
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt1=null;
		String sql1="update users set allplay=?,allwin=?,alllose=?,allwinrate=? where uid=?";
		try {
			
			psmt1=conn.prepareStatement(sql1);
			psmt1.setInt(1, all);
			psmt1.setInt(2, win);
			psmt1.setInt(3, lose);
			psmt1.setDouble(4, winrate);
			psmt1.setInt(5, uid);
			i=psmt1.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt1);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int updateUserBestStatus(String rank,int elo,double rating,User user) {
		/**
		 * - 功能：更新用户最佳场次和胜率
		 */
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt1=null;
		String sql1="update users set bestrank=?,bestelo=?,bestrating=? where uid=?";
		try {
			
			psmt1=conn.prepareStatement(sql1);
			if(getRank(user.getBestrank())<getRank(rank)) psmt1.setString(1, rank);
			else psmt1.setString(1, user.getBestrank());
			if(user.getBestelo()<elo) psmt1.setInt(2, elo);
			else psmt1.setInt(2, user.getBestelo());
			if(user.getBestrating()<rating) psmt1.setDouble(3, rating);
			else psmt1.setDouble(3, user.getBestrating());
			psmt1.setInt(4, user.getUid());
			i=psmt1.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt1);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	private int getRank(String rank) {
		if(rank.equals("未定级")) {
			return 0;
		} else {
			if(rank.equals("S")) {
				return 7;
			} else if(rank.equals("A+")) {
				return 6;
			} else if(rank.equals("A")) {
				return 5;
			} else if(rank.equals("B")) {
				return 4;
			} else if(rank.equals("C")) {
				return 3;
			} else if(rank.equals("D")) {
				return 2;
			} else {
				return 1;
			}
		}
	}
	
	public int updateUser(String username,int uid) {
		/**
		 * int updateUser(String username,int uid)
		 * - 功能：更新用户用户名
		 * - 参数：String username -- 新名字；int uid - uid
		 * - 返回值：int -- 影响的行数
		 */
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt=null;
		String sql="update users set username=? where uid=?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, username);
			psmt.setInt(2, uid);
			i=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int updateUser(int uid,double rating,int elo,String rank,int djs) {
		/**
		 * int updateUser(int uid,double rating,int elo,String rank,int djs)
		 * - 功能：更新用户Rating、Elo、Rank、Djs
		 */
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt1=null;
		String sql1="update users set rating=?,elo=?,rank=?,djs=? where uid=?";
		try {
			psmt1=conn.prepareStatement(sql1);
			psmt1.setDouble(1, rating);
			psmt1.setInt(2, elo);
			psmt1.setString(3, rank);
			psmt1.setInt(4, djs);
			psmt1.setInt(5, uid);
			i=psmt1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt1);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int updatePassword(String password,int uid) {
		/**
		 * int updatePassword(String password,int uid)
		 * - 功能：更新用户密码
		 * - 参数：String password -- 新密码；int uid - uid
		 * - 返回值：int -- 影响的行数
		 */
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt=null;
		String sql="update users set password=? where uid=?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, password);
			psmt.setInt(2, uid);
			i=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int ConnectServer(int i,User user,Server server) {
		/**
		 *
		 */
		
		Socket socket=null;
		
		OutputStream os = null;
		DataOutputStream dos = null;
		ObjectOutputStream oos = null;
		PrintWriter pw = null;
		
		InputStream is = null;
		DataInputStream dis = null;
		ObjectInputStream ois = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		int x=0;
		
		try {
			
			socket = new Socket(server.getIP(), Server.matchport);
			
			socket.setSoTimeout(350000);
			
			if(i==2) {
				// 2为加入匹配
				os = socket.getOutputStream();
				dos = new DataOutputStream(os);
				
				dos.writeInt(2);
				dos.flush();
				
				is = socket.getInputStream();
				dis = new DataInputStream(is);
				
				if(dis.readInt()==1) {
					oos = new ObjectOutputStream(os);
					oos.writeObject(user);
					oos.flush();
					x = dis.readInt();
				}
			} else if(i==3) {
				// 3为退出游戏
				os = socket.getOutputStream();
				dos = new DataOutputStream(os);

				dos.writeInt(3);
				dos.flush();
				
				is = socket.getInputStream();
				dis = new DataInputStream(is);
				
				if(dis.readInt()==1) {
					oos = new ObjectOutputStream(os);
					oos.writeObject(user);
					oos.flush();
					x=1;
				}
			} else if(i==10) {
				os = socket.getOutputStream();
				dos = new DataOutputStream(os);
				dos.writeInt(10);
				dos.flush();
				is = socket.getInputStream();
				dis = new DataInputStream(is);

				x=dis.readInt();
				
			}

        } catch (IOException e) {
            e.printStackTrace();
            return 0;
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
				if(oos!=null) oos.close();
				if(dos!=null) dos.close();
				if(pw!=null) pw.close();
				if(socket!=null) socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return x;
    }
	
	public int CreateRoom(User user) {
		/**
		 * 创建房间并将房间号返回到user身上
		 * 返回影响行数i
		 */
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt1=null;
		PreparedStatement psmt2=null;
		PreparedStatement psmt3=null;
		ResultSet rs=null;
		String sql1="insert into rooms(p1) values(?)";
		String sql2="select id from rooms where p1=?";
		String sql3="update users set room=? where uid=?";
		try {
			psmt1=conn.prepareStatement(sql1);
			psmt1.setInt(1, user.getUid());
			if(psmt1.executeUpdate()!=0) {
				psmt2=conn.prepareStatement(sql2);
				psmt2.setInt(1, user.getUid());
				rs=psmt2.executeQuery();
				while(rs.next()) {
					i=rs.getInt(1);
				}
				if(i!=0) {
					psmt3=conn.prepareStatement(sql3);
					psmt3.setString(1, String.valueOf(i));
					psmt3.setInt(2, user.getUid());
					i=psmt3.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt1);
			DBUtil.closePSMT(psmt2);
			DBUtil.closePSMT(psmt3);
			DBUtil.closeRs(rs);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int getRoomid(User user) {
		/**
		 * 获取user所在的房间号
		 */
		int i=0;
		Connection conn=DBUtil.getConnect();
		PreparedStatement psmt=null;
		ResultSet rs=null;
		String sql="select room from users where uid=?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1,user.getUid());
			rs=psmt.executeQuery();
			if(rs.next()) {
				i=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				psmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	
	public int DeleteRoom(User user) {
		/**
		 * 返回影响行数i
		 */
		int i=0;
		int roomid=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt1=null;
		PreparedStatement psmt2=null;
		PreparedStatement psmt3=null;
		ResultSet rs=null;
		String sql1="select id from rooms where p1=? or p2=?";
		String sql2="update users set room=? where uid=?";
		String sql3="delete from rooms where id=?";
		try {
			psmt1=conn.prepareStatement(sql1);
			psmt1.setInt(1, user.getUid());
			psmt1.setInt(2, user.getUid());
			rs=psmt1.executeQuery();
			if(rs.next()) {
				roomid=rs.getInt(1);
			}
			psmt2=conn.prepareStatement(sql2);
			psmt2.setString(1, null);
			psmt2.setInt(2, user.getUid());
			i=psmt2.executeUpdate();
			if(roomid!=0) {
				psmt3=conn.prepareStatement(sql3);
				psmt3.setInt(1, roomid);
				i=psmt3.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt1);
			DBUtil.closePSMT(psmt2);
			DBUtil.closePSMT(psmt3);
			DBUtil.closeRs(rs);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int DisonnectServer(User user,Server server) {
		/**
		 * int DisconnectServer(User user)
		 * - 功能：玩家与服务器断开连接
		 * - 参数：User user -- 用户
		 * - 返回值：int -- 1 与服务器断开连接成功；0 与服务器断开连接失败
		 */
		int i=0;
		Socket socket=null;
		InputStream is=null;
		DataInputStream dis=null;
		OutputStream os=null;
		ObjectOutputStream oos=null;
		DataOutputStream dos=null;
        // 1.创建客户端的Socket，指定服务器的IP和端口
        try {
            socket = new Socket(server.getIP(), Server.matchport);
            os = socket.getOutputStream();
            dos = new DataOutputStream(os);
			dos.writeInt(5);
			dos.flush();
            oos = new ObjectOutputStream(os);
            oos.writeObject(user);
            oos.flush();
            socket.shutdownOutput();
            
            // 获取输入流，取得服务器的信息
            is = socket.getInputStream();  
            dis = new DataInputStream(is);  
            i = dis.readInt();  
            
			socket.shutdownInput();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
			try {
				if(dis!=null) dis.close();
				if(is!=null) is.close();
				if(socket!=null) socket.close();
				if(oos!=null) oos.close();
				if(os!=null) os.close();
				if(is!=null) is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return i;
    }
	
	public ArrayList<User> SearchEnemyByRoomID(int id) {
		/**
		 * User SearchEnemyByRoomID(int id)
		 * - 功能：通过房间号查找房间内的玩家
		 * - 参数：int id -- 房间号
		 * - 返回值：ArrayList<User> -- 房间内的玩家
		 */
		ArrayList<User> players=new ArrayList<User>();
		Connection conn=DBUtil.getConnect();
		PreparedStatement psmt=null;
		ResultSet rs=null;
		String sql="select * from rooms where id=?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1,id);
			rs=psmt.executeQuery();
			if(rs.next()) {
				User u1=queryUser(rs.getInt(2));
				User u2=queryUser(rs.getInt(3));
				if(u1!=null) players.add(u1);
				if(u2!=null) players.add(u2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				psmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return players;
	}
	
	public int AcceptGame(User user,Server server) {
		/**
		 * int AcceptGame()
		 * - 功能：接受比赛
		 * - 参数：User user
		 * - 返回值：int
		 */
		int i=0;
		Socket socket=null;
		OutputStream os=null;
		ObjectOutputStream oos=null;
		InputStream is=null;
		DataInputStream dis=null;
		DataOutputStream dos=null;
        // 1.创建客户端的Socket，指定服务器的IP和端口
        try {
        	socket = new Socket(server.getIP(), Server.matchport);
            os = socket.getOutputStream(); // 输出流的获取
			dos = new DataOutputStream(os);
			dos.writeInt(4);
			dos.flush();
            oos = new ObjectOutputStream(os);
            oos.writeObject(user);
            oos.flush();
            socket.shutdownOutput();
            is = socket.getInputStream();  
            dis = new DataInputStream(is);  
            i = dis.readInt();  
			socket.shutdownInput();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
			try {
				if(dis!=null) dis.close();
				if(is!=null) is.close();
				if(socket!=null) socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return i;
    }
	
	public boolean GetSkill(JLabel x) {
		/**
		 * GetSkillByID
		 */
		Connection conn = DBUtil.getConnect();
		ResultSet rs =null;
		PreparedStatement psmt=null;
	    String sql="select * from skills";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			double y=0;
			while (rs.next()) {// 调用next()方法将游标移动到下一行，若无数据，返回false
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String describe=rs.getString(4);
				int mp=rs.getInt(5);
				// 把结果封装到对象返回
				Config.Allskills.add(new Skill(id,name,describe,mp));
				if(y+2.8<=100) {
					y+=2.8;
					x.setText("正在载入技能数据......请稍后（"+(int)y+"%）");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return true;
	}
	
	public boolean GetItem(JLabel x) {
		/**
		 * GetItemByID
		 */
		Connection conn = DBUtil.getConnect();
		ResultSet rs =null;
		PreparedStatement psmt=null;
	    String sql="select * from items";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			double y=0;
			while (rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String describe=rs.getString(3);
				int gold=rs.getInt(4);
				String value1=rs.getString(5);
				String value2=rs.getString(6);
				Config.Allitems.add(new Item(id,name,describe,gold,value1,value2));
				if(y+3.85<=100) {
					y+=3.85;
					x.setText("正在载入物品数据......请稍后（"+(int)y+"%）");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return true;
	}
	
	public boolean GetHero(JLabel x) {
		/**
		 * GetHeroByID
		 */
		Connection conn = DBUtil.getConnect();
		ResultSet rs1 =null;
		ResultSet rs2 =null;
		PreparedStatement psmt1=null;
		PreparedStatement psmt2=null;
	    String sql1="select * from heroes";
	    String sql2="select * from herorating";
		try {
			psmt1 = conn.prepareStatement(sql1);
			rs1 = psmt1.executeQuery();
			int i=0;
			double y=0;
			while (rs1.next()) {// 调用next()方法将游标移动到下一行，若无数据，返回false
				int id=rs1.getInt(1);
				String name=rs1.getString(2);
				String ename=rs1.getString(3);
				int atk=rs1.getInt(4);
				int def=rs1.getInt(5);
				int adf=rs1.getInt(6);
				int hp=rs1.getInt(7);
				int mp=rs1.getInt(8);
				int remp=rs1.getInt(9);
				int move=rs1.getInt(10);
				// 把结果封装到对象返回
				Hero hero = new Hero(id,name,ename,atk,def,adf,hp,mp,remp,move);
				Config.Allheroes.add(hero);
				i++;
				if(i==1) {
					Config.yy=Config.Allheroes.get(i-1);
				} else if(i==2) {
					Config.lxs=Config.Allheroes.get(i-1);
				} else if(i==3) {
					Config.ysn=Config.Allheroes.get(i-1);
				} else if(i==4) {
					Config.ltj=Config.Allheroes.get(i-1);
				} else if(i==5) {
					Config.zf=Config.Allheroes.get(i-1);
				} else if(i==6) {
					Config.hyq=Config.Allheroes.get(i-1);
				} else if(i==7) {
					Config.xyh=Config.Allheroes.get(i-1);
				} else if(i==8) {
					Config.zkx=Config.Allheroes.get(i-1);
				} else if(i==9) {
					Config.zxy=Config.Allheroes.get(i-1);
				} else if(i==10) {
					Config.lm=Config.Allheroes.get(i-1);
				} else if(i==11) {
					Config.sjj=Config.Allheroes.get(i-1);
				} else if(i==12) {
					Config.w=Config.Allheroes.get(i-1);
				}
				if(y+4.2<=100) {
					y+=4.2;
					x.setText("正在载入英雄数据......请稍后（"+(int)y+"%）");
				}
			}
			psmt2 = conn.prepareStatement(sql2);
			rs2 = psmt2.executeQuery();
			while (rs2.next()) {// 调用next()方法将游标移动到下一行，若无数据，返回false
				for(int j=1;j<=12;j++) {
					if(j==rs2.getInt(1)) {
						Config.Allheroes.get(j-1).setPlay(rs2.getInt(2));
						Config.Allheroes.get(j-1).setBan(rs2.getInt(3));
						Config.Allheroes.get(j-1).setPick(rs2.getInt(4));
						Config.Allheroes.get(j-1).setWin(rs2.getInt(5));
						Config.Allheroes.get(j-1).setBanrate(rs2.getDouble(6));
						Config.Allheroes.get(j-1).setPickrate(rs2.getDouble(7));
						Config.Allheroes.get(j-1).setWinrate(rs2.getDouble(8));
						Config.Allheroes.get(j-1).setTime(rs2.getInt(9));
						Config.Allheroes.get(j-1).setD(rs2.getDouble(10));
						Config.Allheroes.get(j-1).setAdr(rs2.getDouble(11));
					}
				}
				if(y+4.2<=100) {
					y+=4.2;
					x.setText("正在载入英雄数据......请稍后（"+(int)y+"%）");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeRs(rs1);
			DBUtil.closeRs(rs2);
			DBUtil.closePSMT(psmt1);
			DBUtil.closePSMT(psmt2);
			DBUtil.closeConnect(conn);
		}
		return true;
	}
	
	public Hero GetHeroByName(String name) {
		/**
		 * Hero GetHeroByName(String name)
		 * - 功能：通过名字查找对应的英雄
		 * - 参数：String name -- 英雄名字
		 * - 返回值：Hero -- 为null表示不存在该英雄
		 */
		Connection conn = DBUtil.getConnect();
		ResultSet rs =null;
		PreparedStatement psmt=null;
		Hero hero=null;
	    String sql="select * from heroes where name=?";
		try {
			psmt = conn.prepareStatement(sql);
			// 给占位符赋值
			psmt.setString(1, name);
			// 执行sql语句
			rs = psmt.executeQuery();
			// 处理结果集
			if (rs.next()) {// 调用next()方法将游标移动到下一行，若无数据，返回false
				int id=rs.getInt(1);
				String ename=rs.getString(3);
				int atk=rs.getInt(4);
				int def=rs.getInt(5);
				int adf=rs.getInt(6);
				int hp=rs.getInt(7);
				int mp=rs.getInt(8);
				int remp=rs.getInt(9);
				int move=rs.getInt(10);
				// 把结果封装到对象返回
				hero = new Hero(id,name,ename,atk,def,adf,hp,mp,remp,move);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return hero;
	}
	
	public boolean IsRoomCreater(int id,User user,Server server) {
		/**
		 * - 判断玩家是否是房主
		 */
		Socket socket=null;
		InputStream is=null;
		DataInputStream dis=null;
		OutputStream os=null;
		ObjectOutputStream oos=null;
		DataOutputStream dos=null;
        // 1.创建客户端的Socket，指定服务器的IP和端口
        try {
            socket = new Socket(server.getIP(), Server.matchport);
            os = socket.getOutputStream();
            dos = new DataOutputStream(os);
			dos.writeInt(8);
			dos.flush();
			
			dos.writeInt(id);
			dos.flush();
			
            oos = new ObjectOutputStream(os);
            oos.writeObject(user);
            oos.flush();
            socket.shutdownOutput();
            
            // 获取输入流，取得服务器的信息
            is = socket.getInputStream();  
            dis = new DataInputStream(is);  
            int i = dis.readInt();  
            if(i==1) {
            	try {
            		socket.shutdownInput();
    				if(dis!=null) dis.close();
    				if(is!=null) is.close();
    				if(socket!=null) socket.close();
    				if(oos!=null) oos.close();
    				if(os!=null) os.close();
    				if(is!=null) is.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
            	return true;
            }
			socket.shutdownInput();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
			try {
				if(dis!=null) dis.close();
				if(is!=null) is.close();
				if(socket!=null) socket.close();
				if(oos!=null) oos.close();
				if(os!=null) os.close();
				if(is!=null) is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return false;
	}
	
	public int setWinner(User user,int room,Server server) {
		/**
		 * - 设置房间胜利者
		 */
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt=null;
		String sql="update rooms set winner=0 where id=?";
		if(IsRoomCreater(room, user, server)) {
			sql="update rooms set winner=1 where id=?";
		} else {
			sql="update rooms set winner=2 where id=?";
		}
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, room);
			i=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int setIsnotReady1(int room) {
		/**
		 * - 设置玩家1的准备状态
		 */
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt=null;
		String sql="update rooms set p1ready=0 where id=?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, room);
			i=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int setIsnotReady2(int room) {
		/**
		 * - 设置玩家2的准备状态
		 */
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt=null;
		String sql="update rooms set p2ready=0 where id=?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, room);
			i=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int addHeroPlay() {
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt=null;
		String sql="update herorating set play=play+1";
		try {
			psmt=conn.prepareStatement(sql);
			i=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int addHeroBan(int hid,double banrate) {
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt=null;
		String sql="update herorating set ban=ban+1,banrate=? where heroid=?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setDouble(1, banrate);
			psmt.setInt(2, hid);
			i=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int addHeroPick(int hid,double pickrate) {
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt=null;
		String sql="update herorating set pick=pick+1,pickrate=? where heroid=?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setDouble(1, pickrate);
			psmt.setInt(2, hid);
			i=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int addHeroWin(int hid,double winrate) {
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt=null;
		String sql="update herorating set win=win+1,winrate=? where heroid=?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setDouble(1, winrate);
			psmt.setInt(2, hid);
			i=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int UpdateHeroTime(int hid,int round) {
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt=null;
		String sql="update herorating set time=? where heroid=?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, round);
			psmt.setInt(2, hid);
			i=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int UpdateHeroDamage(int hid,double d) {
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt=null;
		String sql="update herorating set damage=? where heroid=?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setDouble(1, d);
			psmt.setInt(2, hid);
			i=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int UpdateHeroADR(int hid,double adr) {
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt=null;
		String sql="update herorating set adr=? where heroid=?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setDouble(1, adr);
			psmt.setInt(2, hid);
			i=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int updateHerorate() {
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt1=null;
		PreparedStatement psmt2=null;
		PreparedStatement psmt3=null;
		String sql1="update herorating set pickrate=ROUND(pick/play,2) where play!=0";
		String sql2="update herorating set banrate=ROUND(ban/play,2) where play!=0";
		String sql3="update herorating set winrate=ROUND(win/pick,2) where pick!=0";
		try {
			psmt1=conn.prepareStatement(sql1);
			i=psmt1.executeUpdate();
			psmt2=conn.prepareStatement(sql2);
			i=psmt2.executeUpdate();
			psmt3=conn.prepareStatement(sql3);
			i=psmt3.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt1);
			DBUtil.closePSMT(psmt2);
			DBUtil.closePSMT(psmt3);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int CreateRoomBySystem(int roomid,User user,String gametime,String server) {
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt=null;
		String sql="insert into games(p1,roomid,gametime,server) values(?,?,?,?)";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, user.getUsername());
			psmt.setInt(2, roomid);
			psmt.setString(3, gametime);
			psmt.setString(4, server);
			i=psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return i;
	}

	public JLabel getSystemTip() {
		Connection conn = DBUtil.getConnect();
		ResultSet rs = null;
		PreparedStatement psmt= null;
		JLabel x = new JLabel();
	    String sql="select post from ads where uid=1";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()) {
				x.setText(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return x;
	}
	
	public JLabel getAD() {
		Connection conn = DBUtil.getConnect();
		ResultSet rs = null;
		PreparedStatement psmt= null;
		JLabel x = new JLabel();
	    String sql="select post from ads where uid=2";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()) {
				x.setText(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return x;
	}
	
	public Record GetGameRecordByID(int recordid) {
		Connection conn = DBUtil.getConnect();
		ResultSet rs = null;
		PreparedStatement psmt= null;
		Record r = null;
	    String sql="select post from games where roomid=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, recordid);
			rs = psmt.executeQuery();
			if(rs.next()) {
				int id = recordid;
				String p1 = rs.getString(3),p2 = rs.getString(4);
				String winner = rs.getString(5);
				int p1elo = rs.getInt(6),p2elo = rs.getInt(7);
				int p1elop = rs.getInt(8),p2elop = rs.getInt(9);
				int p1b1 = rs.getInt(10),p1b2 = rs.getInt(11),p2b1 = rs.getInt(12),p2b2 = rs.getInt(13);
				int p1p1 = rs.getInt(14),p1p2 = rs.getInt(15),p1p3 = rs.getInt(16),p2p1 = rs.getInt(17),p2p2 = rs.getInt(18),p2p3 = rs.getInt(19);
				double p1rating = rs.getDouble(20),p2rating = rs.getDouble(21);
				int p1k = rs.getInt(22),p2k = rs.getInt(23),p1d = rs.getInt(24),p2d = rs.getInt(25);
				double p1adr = rs.getDouble(26),p2adr = rs.getDouble(27);
				int p1djs = rs.getInt(28),p2djs = rs.getInt(29);
				String time = rs.getString(30),server = rs.getString(31);
				r = new Record(id, p1, p2, winner, p1elo, p2elo, p1elop, p2elop, p1b1, p1b2, p2b1, p2b2, p1p1,
						p1p2, p1p3, p2p1, p2p2, p2p3, p1rating, p2rating, p1k, p1d, p2k, p2d, p1adr, p2adr, p1djs, p2djs, time, server);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return r;
	}
	
	public ArrayList<Record> getRecordList(User user) {
		ArrayList<Record> a = new ArrayList<Record>();
		Connection conn = DBUtil.getConnect();
		ResultSet rs =null;
		PreparedStatement psmt=null;
		Record r =null;
		String sql="select * from games where p1=? or p2=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUsername());
			psmt.setString(2, user.getUsername());
			rs = psmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(2);
				String p1 = rs.getString(3),p2 = rs.getString(4);
				String winner = rs.getString(5);
				int p1elo = rs.getInt(6),p2elo = rs.getInt(7);
				int p1elop = rs.getInt(8),p2elop = rs.getInt(9);
				int p1b1 = rs.getInt(10),p1b2 = rs.getInt(11),p2b1 = rs.getInt(12),p2b2 = rs.getInt(13);
				int p1p1 = rs.getInt(14),p1p2 = rs.getInt(15),p1p3 = rs.getInt(16),p2p1 = rs.getInt(17),p2p2 = rs.getInt(18),p2p3 = rs.getInt(19);
				double p1rating = rs.getDouble(20),p2rating = rs.getDouble(21);
				int p1k = rs.getInt(22),p2k = rs.getInt(23),p1d = rs.getInt(24),p2d = rs.getInt(25);
				double p1adr = rs.getDouble(26),p2adr = rs.getDouble(27);
				int p1djs = rs.getInt(28),p2djs = rs.getInt(29);
				String time = rs.getString(30),server = rs.getString(31);
				r = new Record(id, p1, p2, winner, p1elo, p2elo, p1elop, p2elop, p1b1, p1b2, p2b1, p2b2, p1p1,
						p1p2, p1p3, p2p1, p2p2, p2p3, p1rating, p2rating, p1k, p1d, p2k, p2d, p1adr, p2adr, p1djs, p2djs, time, server);
				a.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return a;
	}
	
	public ArrayList<User> getRankingList() {
		ArrayList<User> a = new ArrayList<User>();
		Connection conn = DBUtil.getConnect();
		ResultSet rs =null;
		PreparedStatement psmt=null;
		User user=null;
	    String sql="select username,elo,rank,rating,alls,win,winrate from users order by elo desc";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String username = rs.getString(1);
				int elo = rs.getInt(2);
				String rank = rs.getString(3);
				if(rank.equals("未定级")) elo=0;
				double rating = rs.getDouble(4);
				int all = rs.getInt(5);
				int win = rs.getInt(6);
				double winrate = rs.getDouble(7);
				user = new User(username,elo,rank,rating,all,win,winrate);
				if(a.size()!=11) a.add(user);
				else break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return a;
	}
	
	public ArrayList<Server> getServerList() {
		ArrayList<Server> a = new ArrayList<Server>();
		Connection conn = DBUtil.getConnect();
		ResultSet rs =null;
		PreparedStatement psmt=null;
		Server s=null;
	    String sql="select * from hosts";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				int hostid=rs.getInt(1);
				String hostname=rs.getString(2);
				String hostip=rs.getString(3);
				String hostpassword=rs.getString(4);
				String hosttip=rs.getString(5);
				int hoststatus=rs.getInt(6);
				String ver=rs.getString(7);
				s=new Server(hostname, hostip, hostid, hoststatus, hostpassword, hosttip, ver);
				a.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeRs(rs);
			DBUtil.closePSMT(psmt);
			DBUtil.closeConnect(conn);
		}
		return a;
	}
	
	public int updateLoserGameRecord(int roomid,boolean roomcreater,ArrayList<Hero> userbanned,ArrayList<Hero> userpicked,
				int userelo,int userelop,double rating,int kill,int death,double adr,int djs,User user) {
		/**
		 * - 功能：由更新比赛记录
		 */
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt=null;
		PreparedStatement psmt2=null;
		String sql;
		String sql2;
		if(roomcreater) {
			sql2="update games set p1rating=?,p1k=?,p1d=?,p1adr=?,p1djs=? where roomid=?";
		} else {
			sql2="update games set p2rating=?,p2k=?,p2d=?,p2adr=?,p2djs=? where roomid=?";
		}
		try {
			if(roomcreater) {
				if(userbanned.size()==0) {
					if(userpicked.size()==1) {
						sql="update games set p1=?,p1elo=?,p1elop=?,p1p1=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userpicked.get(0).getId());
						psmt.setInt(5, roomid);
						i=psmt.executeUpdate();
					} else if(userpicked.size()==2) {
						sql="update games set p1=?,p1elo=?,p1elop=?,p1p1=?,p1p2=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userpicked.get(0).getId());
						psmt.setInt(5, userpicked.get(1).getId());
						psmt.setInt(6, roomid);
						i=psmt.executeUpdate();
					} else if(userpicked.size()==3) {
						sql="update games set p1=?,p1elo=?,p1elop=?,p1p1=?,p1p2=?,p1p3=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userpicked.get(0).getId());
						psmt.setInt(5, userpicked.get(1).getId());
						psmt.setInt(6, userpicked.get(2).getId());
						psmt.setInt(7, roomid);
						i=psmt.executeUpdate();
					}
				} else if(userbanned.size()==1) {
					if(userpicked.size()==1) {
						sql="update games set p1=?,p1elo=?,p1elop=?,p1b1=?,p1p1=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userbanned.get(0).getId());
						psmt.setInt(5, userpicked.get(0).getId());
						psmt.setInt(6, roomid);
						i=psmt.executeUpdate();
					} else if(userpicked.size()==2) {
						sql="update games set p1=?,p1elo=?,p1elop=?,p1b1=?,p1p1=?,p1p2=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userbanned.get(0).getId());
						psmt.setInt(5, userpicked.get(0).getId());
						psmt.setInt(6, userpicked.get(1).getId());
						psmt.setInt(7, roomid);
						i=psmt.executeUpdate();
					} else if(userpicked.size()==3) {
						sql="update games set p1=?,p1elo=?,p1elop=?,p1b1=?,p1p1=?,p1p2=?,p1p3=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userbanned.get(0).getId());
						psmt.setInt(5, userpicked.get(0).getId());
						psmt.setInt(6, userpicked.get(1).getId());
						psmt.setInt(7, userpicked.get(2).getId());
						psmt.setInt(8, roomid);
						i=psmt.executeUpdate();
					}
				} else if(userbanned.size()==2) {
					if(userpicked.size()==1) {
						sql="update games set p1=?,p1elo=?,p1elop=?,p1b1=?,p1b2=?,p1p1=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userbanned.get(0).getId());
						psmt.setInt(5, userbanned.get(1).getId());
						psmt.setInt(6, userpicked.get(0).getId());
						psmt.setInt(7, roomid);
						i=psmt.executeUpdate();
					} else if(userpicked.size()==2) {
						sql="update games set p1=?,p1elo=?,p1elop=?,p1b1=?,p1b2=?,p1p1=?,p1p2=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userbanned.get(0).getId());
						psmt.setInt(5, userbanned.get(1).getId());
						psmt.setInt(6, userpicked.get(0).getId());
						psmt.setInt(7, userpicked.get(1).getId());
						psmt.setInt(8, roomid);
						i=psmt.executeUpdate();
					} else if(userpicked.size()==3) {
						sql="update games set p1=?,p1elo=?,p1elop=?,p1b1=?,p1b2=?,p1p1=?,p1p2=?,p1p3=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userbanned.get(0).getId());
						psmt.setInt(5, userbanned.get(1).getId());
						psmt.setInt(6, userpicked.get(0).getId());
						psmt.setInt(7, userpicked.get(1).getId());
						psmt.setInt(8, userpicked.get(2).getId());
						psmt.setInt(9, roomid);
						i=psmt.executeUpdate();
					}
				}
			} else {
				if(userbanned.size()==0) {
					if(userpicked.size()==1) {
						sql="update games set p2=?,p2elo=?,p2elop=?,p2p1=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userpicked.get(0).getId());
						psmt.setInt(5, roomid);
						i=psmt.executeUpdate();
					} else if(userpicked.size()==2) {
						sql="update games set p2=?,p2elo=?,p2elop=?,p2p1=?,p2p2=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userpicked.get(0).getId());
						psmt.setInt(5, userpicked.get(1).getId());
						psmt.setInt(6, roomid);
						i=psmt.executeUpdate();
					} else if(userpicked.size()==3) {
						sql="update games set p2=?,p2elo=?,p2elop=?,p2p1=?,p2p2=?,p2p3=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userpicked.get(0).getId());
						psmt.setInt(5, userpicked.get(1).getId());
						psmt.setInt(6, userpicked.get(2).getId());
						psmt.setInt(7, roomid);
						i=psmt.executeUpdate();
					}
				} else if(userbanned.size()==1) {
					if(userpicked.size()==1) {
						sql="update games set p2=?,p2elo=?,p2elop=?,p2b1=?,p2p1=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userbanned.get(0).getId());
						psmt.setInt(5, userpicked.get(0).getId());
						psmt.setInt(6, roomid);
						i=psmt.executeUpdate();
					} else if(userpicked.size()==2) {
						sql="update games set p2=?,p2elo=?,p2elop=?,p2b1=?,p2p1=?,p2p2=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userbanned.get(0).getId());
						psmt.setInt(5, userpicked.get(0).getId());
						psmt.setInt(6, userpicked.get(1).getId());
						psmt.setInt(7, roomid);
						i=psmt.executeUpdate();
					} else if(userpicked.size()==3) {
						sql="update games set p2=?,p2elo=?,p2elop=?,p2b1=?,p2p1=?,p2p2=?,p2p3=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userbanned.get(0).getId());
						psmt.setInt(5, userpicked.get(0).getId());
						psmt.setInt(6, userpicked.get(1).getId());
						psmt.setInt(7, userpicked.get(2).getId());
						psmt.setInt(8, roomid);
						i=psmt.executeUpdate();
					}
				} else if(userbanned.size()==2) {
					if(userpicked.size()==1) {
						sql="update games set p2=?,p2elo=?,p2elop=?,p2b1=?,p2b2=?,p2p1=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userbanned.get(0).getId());
						psmt.setInt(5, userbanned.get(1).getId());
						psmt.setInt(6, userpicked.get(0).getId());
						psmt.setInt(7, roomid);
						i=psmt.executeUpdate();
					} else if(userpicked.size()==2) {
						sql="update games set p2=?,p2elo=?,p2elop=?,p2b1=?,p2b2=?,p2p1=?,p2p2=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userbanned.get(0).getId());
						psmt.setInt(5, userbanned.get(1).getId());
						psmt.setInt(6, userpicked.get(0).getId());
						psmt.setInt(7, userpicked.get(1).getId());
						psmt.setInt(8, roomid);
						i=psmt.executeUpdate();
					} else if(userpicked.size()==3) {
						sql="update games set p2=?,p2elo=?,p2elop=?,p2b1=?,p2b2=?,p2p1=?,p2p2=?,p2p3=? where roomid=?";
						psmt=conn.prepareStatement(sql);
						psmt.setString(1, user.getUsername());
						psmt.setInt(2, userelo);
						psmt.setInt(3, userelop);
						psmt.setInt(4, userbanned.get(0).getId());
						psmt.setInt(5, userbanned.get(1).getId());
						psmt.setInt(6, userpicked.get(0).getId());
						psmt.setInt(7, userpicked.get(1).getId());
						psmt.setInt(8, userpicked.get(2).getId());
						psmt.setInt(9, roomid);
						i=psmt.executeUpdate();
					}
				}
			}
			psmt2=conn.prepareStatement(sql2);
			psmt2.setDouble(1, rating);
			psmt2.setInt(2, kill);
			psmt2.setInt(3, death);
			psmt2.setDouble(4, adr);
			psmt2.setInt(5, djs);
			psmt2.setInt(6, roomid);
			i=psmt2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt);
			DBUtil.closePSMT(psmt2);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	public int updateWinnerGameRecord(int roomid,boolean roomcreater,ArrayList<Hero> userbanned,ArrayList<Hero> userpicked,
				int userelo,int userelop,double rating,int kill,int death,double adr,int djs,User user) {
		/**
		 * - 功能：由更新比赛记录
		 */
		int i=0;
		Connection conn = DBUtil.getConnect();
		PreparedStatement psmt1=null;
		PreparedStatement psmt2=null;
		PreparedStatement psmt3=null;
		String sql1="update games set winner=? where roomid=?";
		try {
			psmt1=conn.prepareStatement(sql1);
			psmt1.setString(1, user.getUsername());
			psmt1.setInt(2, roomid);
			i=psmt1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt1);
		}
		String sql2;
		String sql3;
		if(roomcreater) {
			sql3="update games set p1rating=?,p1k=?,p1d=?,p1adr=?,p1djs=? where roomid=?";
		} else {
			sql3="update games set p2rating=?,p2k=?,p2d=?,p2adr=?,p2djs=? where roomid=?";
		}
		try {
			if(roomcreater) {
				if(userbanned.size()==0) {
					if(userpicked.size()==1) {
						sql2="update games set p1=?,p1elo=?,p1elop=?,p1p1=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userpicked.get(0).getId());
						psmt2.setInt(5, roomid);
						i=psmt2.executeUpdate();
					} else if(userpicked.size()==2) {
						sql2="update games set p1=?,p1elo=?,p1elop=?,p1p1=?,p1p2=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userpicked.get(0).getId());
						psmt2.setInt(5, userpicked.get(1).getId());
						psmt2.setInt(6, roomid);
						i=psmt2.executeUpdate();
					} else if(userpicked.size()==3) {
						sql2="update games set p1=?,p1elo=?,p1elop=?,p1p1=?,p1p2=?,p1p3=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userpicked.get(0).getId());
						psmt2.setInt(5, userpicked.get(1).getId());
						psmt2.setInt(6, userpicked.get(2).getId());
						psmt2.setInt(7, roomid);
						i=psmt2.executeUpdate();
					}
				} else if(userbanned.size()==1) {
					if(userpicked.size()==1) {
						sql2="update games set p1=?,p1elo=?,p1elop=?,p1b1=?,p1p1=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userbanned.get(0).getId());
						psmt2.setInt(5, userpicked.get(0).getId());
						psmt2.setInt(6, roomid);
						i=psmt2.executeUpdate();
					} else if(userpicked.size()==2) {
						sql2="update games set p1=?,p1elo=?,p1elop=?,p1b1=?,p1p1=?,p1p2=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userbanned.get(0).getId());
						psmt2.setInt(5, userpicked.get(0).getId());
						psmt2.setInt(6, userpicked.get(1).getId());
						psmt2.setInt(7, roomid);
						i=psmt2.executeUpdate();
					} else if(userpicked.size()==3) {
						sql2="update games set p1=?,p1elo=?,p1elop=?,p1b1=?,p1p1=?,p1p2=?,p1p3=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userbanned.get(0).getId());
						psmt2.setInt(5, userpicked.get(0).getId());
						psmt2.setInt(6, userpicked.get(1).getId());
						psmt2.setInt(7, userpicked.get(2).getId());
						psmt2.setInt(8, roomid);
						i=psmt2.executeUpdate();
					}
				} else if(userbanned.size()==2) {
					if(userpicked.size()==1) {
						sql2="update games set p1=?,p1elo=?,p1elop=?,p1b1=?,p1b2=?,p1p1=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userbanned.get(0).getId());
						psmt2.setInt(5, userbanned.get(1).getId());
						psmt2.setInt(6, userpicked.get(0).getId());
						psmt2.setInt(7, roomid);
						i=psmt2.executeUpdate();
					} else if(userpicked.size()==2) {
						sql2="update games set p1=?,p1elo=?,p1elop=?,p1b1=?,p1b2=?,p1p1=?,p1p2=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userbanned.get(0).getId());
						psmt2.setInt(5, userbanned.get(1).getId());
						psmt2.setInt(6, userpicked.get(0).getId());
						psmt2.setInt(7, userpicked.get(1).getId());
						psmt2.setInt(8, roomid);
						i=psmt2.executeUpdate();
					} else if(userpicked.size()==3) {
						sql2="update games set p1=?,p1elo=?,p1elop=?,p1b1=?,p1b2=?,p1p1=?,p1p2=?,p1p3=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userbanned.get(0).getId());
						psmt2.setInt(5, userbanned.get(1).getId());
						psmt2.setInt(6, userpicked.get(0).getId());
						psmt2.setInt(7, userpicked.get(1).getId());
						psmt2.setInt(8, userpicked.get(2).getId());
						psmt2.setInt(9, roomid);
						i=psmt2.executeUpdate();
					}
				}
			} else {
				if(userbanned.size()==0) {
					if(userpicked.size()==1) {
						sql2="update games set p2=?,p2elo=?,p2elop=?,p2p1=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userpicked.get(0).getId());
						psmt2.setInt(5, roomid);
						i=psmt2.executeUpdate();
					} else if(userpicked.size()==2) {
						sql2="update games set p2=?,p2elo=?,p2elop=?,p2p1=?,p2p2=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userpicked.get(0).getId());
						psmt2.setInt(5, userpicked.get(1).getId());
						psmt2.setInt(6, roomid);
						i=psmt2.executeUpdate();
					} else if(userpicked.size()==3) {
						sql2="update games set p2=?,p2elo=?,p2elop=?,p2p1=?,p2p2=?,p2p3=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userpicked.get(0).getId());
						psmt2.setInt(5, userpicked.get(1).getId());
						psmt2.setInt(6, userpicked.get(2).getId());
						psmt2.setInt(7, roomid);
						i=psmt2.executeUpdate();
					}
				} else if(userbanned.size()==1) {
					if(userpicked.size()==1) {
						sql2="update games set p2=?,p2elo=?,p2elop=?,p2b1=?,p2p1=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userbanned.get(0).getId());
						psmt2.setInt(5, userpicked.get(0).getId());
						psmt2.setInt(6, roomid);
						i=psmt2.executeUpdate();
					} else if(userpicked.size()==2) {
						sql2="update games set p2=?,p2elo=?,p2elop=?,p2b1=?,p2p1=?,p2p2=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userbanned.get(0).getId());
						psmt2.setInt(5, userpicked.get(0).getId());
						psmt2.setInt(6, userpicked.get(1).getId());
						psmt2.setInt(7, roomid);
						i=psmt2.executeUpdate();
					} else if(userpicked.size()==3) {
						sql2="update games set p2=?,p2elo=?,p2elop=?,p2b1=?,p2p1=?,p2p2=?,p2p3=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userbanned.get(0).getId());
						psmt2.setInt(5, userpicked.get(0).getId());
						psmt2.setInt(6, userpicked.get(1).getId());
						psmt2.setInt(7, userpicked.get(2).getId());
						psmt2.setInt(8, roomid);
						i=psmt2.executeUpdate();
					}
				} else if(userbanned.size()==2) {
					if(userpicked.size()==1) {
						sql2="update games set p2=?,p2elo=?,p2elop=?,p2b1=?,p2b2=?,p2p1=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userbanned.get(0).getId());
						psmt2.setInt(5, userbanned.get(1).getId());
						psmt2.setInt(6, userpicked.get(0).getId());
						psmt2.setInt(7, roomid);
						i=psmt2.executeUpdate();
					} else if(userpicked.size()==2) {
						sql2="update games set p2=?,p2elo=?,p2elop=?,p2b1=?,p2b2=?,p2p1=?,p2p2=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userbanned.get(0).getId());
						psmt2.setInt(5, userbanned.get(1).getId());
						psmt2.setInt(6, userpicked.get(0).getId());
						psmt2.setInt(7, userpicked.get(1).getId());
						psmt2.setInt(8, roomid);
						i=psmt2.executeUpdate();
					} else if(userpicked.size()==3) {
						sql2="update games set p2=?,p2elo=?,p2elop=?,p2b1=?,p2b2=?,p2p1=?,p2p2=?,p2p3=? where roomid=?";
						psmt2=conn.prepareStatement(sql2);
						psmt2.setString(1, user.getUsername());
						psmt2.setInt(2, userelo);
						psmt2.setInt(3, userelop);
						psmt2.setInt(4, userbanned.get(0).getId());
						psmt2.setInt(5, userbanned.get(1).getId());
						psmt2.setInt(6, userpicked.get(0).getId());
						psmt2.setInt(7, userpicked.get(1).getId());
						psmt2.setInt(8, userpicked.get(2).getId());
						psmt2.setInt(9, roomid);
						i=psmt2.executeUpdate();
					}
				}
			}
			psmt3=conn.prepareStatement(sql3);
			psmt3.setDouble(1, rating);
			psmt3.setInt(2, kill);
			psmt3.setInt(3, death);
			psmt3.setDouble(4, adr);
			psmt3.setInt(5, djs);
			psmt3.setInt(6, roomid);
			i=psmt3.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			DBUtil.closePSMT(psmt2);
			DBUtil.closePSMT(psmt3);
			DBUtil.closeConnect(conn);
		}
		return i;
	}
	
	/**
	 * @author 罗恩龙
	 * @param hero - 英雄
	 * @return Hero - 为英雄设置好技能后将参数再返回
	 */
	
	public Hero GetSkillByHero(Hero hero) {
		if(hero.getId()==1) {
			hero.setQ(Config.yy.getQ());
			hero.setW(Config.yy.getW());
			hero.setE(Config.yy.getE());
		} else if(hero.getId()==2) {
			hero.setQ(Config.lxs.getQ());
			hero.setW(Config.lxs.getW());
			hero.setE(Config.lxs.getE());
		} else if(hero.getId()==3) {
			hero.setQ(Config.ysn.getQ());
			hero.setW(Config.ysn.getW());
		} else if(hero.getId()==4) {
			hero.setQ(Config.ltj.getQ());
			hero.setW(Config.ltj.getW());
			hero.setE(Config.ltj.getE());
		} else if(hero.getId()==5) {
			hero.setQ(Config.zf.getQ());
			hero.setW(Config.zf.getW());
			hero.setE(Config.zf.getE());
		} else if(hero.getId()==6) {
			hero.setQ(Config.hyq.getQ());
			hero.setW(Config.hyq.getW());
			hero.setE(Config.hyq.getE());
			hero.setR(Config.hyq.getR());
		} else if(hero.getId()==7) {
			hero.setQ(Config.xyh.getQ());
			hero.setW(Config.xyh.getW());
			hero.setE(Config.xyh.getE());
		} else if(hero.getId()==8) {
			hero.setQ(Config.zkx.getQ());
			hero.setW(Config.zkx.getW());
			hero.setE(Config.zkx.getE());
		} else if(hero.getId()==9) {
			hero.setQ(Config.zxy.getQ());
			hero.setW(Config.zxy.getW());
			hero.setE(Config.zxy.getE());
			hero.setR(Config.zxy.getR());
		} else if(hero.getId()==10) {
			hero.setQ(Config.lm.getQ());
			hero.setW(Config.lm.getW());
		} else if(hero.getId()==11) {
			hero.setQ(Config.sjj.getQ());
			hero.setW(Config.sjj.getW());
			hero.setE(Config.sjj.getE());
			hero.setR(Config.sjj.getR());
		} else if(hero.getId()==12) {
			hero.setQ(Config.w.getQ());
			hero.setW(Config.w.getW());
		}
		return hero;
	}
	
	public JButton getItemIcon(int itemid) {
		JButton jb = new JButton();
		if (itemid == 1) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/ys.jpg")));
		} else if (itemid == 2) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hp1.jpg")));
		} else if (itemid == 3) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hp2.jpg")));
		} else if (itemid == 4) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hp3.jpg")));
		} else if (itemid == 5) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/fs1.jpg")));
		} else if (itemid == 6) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/fs2.jpg")));
		} else if (itemid == 7) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/mp1.jpg")));
		} else if (itemid == 8) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/mp2.jpg")));
		} else if (itemid == 9) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/mp3.jpg")));
		} else if (itemid == 10) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/xdl.jpg")));
		} else if (itemid == 11) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/skys.jpg")));
		} else if (itemid == 12) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/qhys.jpg")));
		} else if (itemid == 13) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zysz.jpg")));
		} else if (itemid == 14) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hysz.jpg")));
		} else if (itemid == 15) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zzqy.jpg")));
		} else if (itemid == 16) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/sofa.jpg")));
		} else if (itemid == 17) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/lszbs.jpg")));
		} else if (itemid == 18) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/xinye.jpg")));
		} else if (itemid == 19) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/pjzm.jpg")));
		} else if (itemid == 20) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/wdln.jpg")));
		} else if (itemid == 21) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/sydp.jpg")));
		} else if (itemid == 22) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/jrzzd.jpg")));
		} else if (itemid == 23) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/shzj.jpg")));
		} else if (itemid == 24) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/txj.jpg")));
		} else if (itemid == 25) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hh.jpg")));
		} else if (itemid == 26) {
			jb.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/items/yyzs.jpg")));
		}
		jb.setContentAreaFilled(false);
		jb.setToolTipText(Config.Allitems.get(itemid-1).getItem());
		return jb;
	}
	
	public ImageIcon getItemImageIcon(int itemid) {
		if (itemid == 1) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/ys.jpg"));
		} else if (itemid == 2) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hp1.jpg"));
		} else if (itemid == 3) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hp2.jpg"));
		} else if (itemid == 4) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hp3.jpg"));
		} else if (itemid == 5) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/fs1.jpg"));
		} else if (itemid == 6) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/fs2.jpg"));
		} else if (itemid == 7) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/mp1.jpg"));
		} else if (itemid == 8) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/mp2.jpg"));
		} else if (itemid == 9) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/mp3.jpg"));
		} else if (itemid == 10) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/xdl.jpg"));
		} else if (itemid == 11) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/skys.jpg"));
		} else if (itemid == 12) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/qhys.jpg"));
		} else if (itemid == 13) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zysz.jpg"));
		} else if (itemid == 14) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hysz.jpg"));
		} else if (itemid == 15) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/zzqy.jpg"));
		} else if (itemid == 16) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/sofa.jpg"));
		} else if (itemid == 17) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/lszbs.jpg"));
		} else if (itemid == 18) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/xinye.jpg"));
		} else if (itemid == 19) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/pjzm.jpg"));
		} else if (itemid == 20) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/wdln.jpg"));
		} else if (itemid == 21) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/sydp.jpg"));
		} else if (itemid == 22) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/jrzzd.jpg"));
		} else if (itemid == 23) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/shzj.jpg"));
		} else if (itemid == 24) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/txj.jpg"));
		} else if (itemid == 25) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/hh.jpg"));
		} else if (itemid == 26) {
			return new ImageIcon(this.getClass().getClassLoader().getResource("img/items/yyzs.jpg"));
		}
		return new ImageIcon(this.getClass().getClassLoader().getResource("img/useitem.png"));
	}
	
}