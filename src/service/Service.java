package service;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import dao.Dao;
import entity.Hero;
import entity.Record;
import entity.Server;
import entity.User;

public class Service {
	
	Dao d = new Dao();
	
	public Image adpup = new ImageIcon(this.getClass().getClassLoader().getResource("img/buffs/adpup.png")).getImage();
	public Image allunable = new ImageIcon(this.getClass().getClassLoader().getResource("img/buffs/allunable.png")).getImage();
	public Image appup = new ImageIcon(this.getClass().getClassLoader().getResource("img/buffs/appup.png")).getImage();
	public Image atkunable = new ImageIcon(this.getClass().getClassLoader().getResource("img/buffs/atkunable.png")).getImage();
	public Image atkup = new ImageIcon(this.getClass().getClassLoader().getResource("img/buffs/atkup.png")).getImage();
	public Image defdown = new ImageIcon(this.getClass().getClassLoader().getResource("img/buffs/defdown.png")).getImage();
	public Image defup = new ImageIcon(this.getClass().getClassLoader().getResource("img/buffs/defup.png")).getImage();
	public Image evade = new ImageIcon(this.getClass().getClassLoader().getResource("img/buffs/evade.png")).getImage();
	public Image hpadd = new ImageIcon(this.getClass().getClassLoader().getResource("img/buffs/hpadd.png")).getImage();
	public Image limited = new ImageIcon(this.getClass().getClassLoader().getResource("img/buffs/limited.png")).getImage();
	public Image magicimmunity = new ImageIcon(this.getClass().getClassLoader().getResource("img/buffs/magicimmunity.png")).getImage();
	public Image magicunable = new ImageIcon(this.getClass().getClassLoader().getResource("img/buffs/magicunable.png")).getImage();
	public Image mdfdown = new ImageIcon(this.getClass().getClassLoader().getResource("img/buffs/mdfdown.png")).getImage();
	public Image mdfup = new ImageIcon(this.getClass().getClassLoader().getResource("img/buffs/mdfup.png")).getImage();
	public Image speedup = new ImageIcon(this.getClass().getClassLoader().getResource("img/buffs/speedup.png")).getImage();
	
	public User queryUser(String username,String password) {
		return d.queryUser(username, password);
	}
	
	public User queryUser(int uid) {
		return d.queryUser(uid);
	}
	
	public User queryUser(String username,int uid) {
		return d.queryUser(username, uid);
	}
	
	public User queryUser(int uid,String password) {
		return d.queryUser(uid, password);
	}
	
	public int addUser(String username,String password) {
		return d.addUser(username, password);
	}
	
	public boolean isexist(String username) {
		return d.isexist(username);
	}
	
	public int ConnectServer(int i,User user,Server server) {
		return d.ConnectServer(i,user,server);
	}
	
	public ArrayList<User> SearchEnemyByRoomID(int id){
		return d.SearchEnemyByRoomID(id);
	}
	
	public int updateUser(String username,int uid) {
		return d.updateUser(username, uid);
	}
	
	public int updateUser(int all,int win,int lose,double winrate,int uid) {
		return d.updateUser(all, win, lose, winrate, uid);
	}
	
	public int updateUser(int uid,double rating,int elo,String rank,int djs) {
		return d.updateUser(uid, rating, elo, rank, djs);
	}
	
	public int updateUserAllStatus(int all,int win,int lose,double winrate,int uid) {
		return d.updateUserAllStatus(all, win, lose, winrate, uid);
	}
	
	public int updateUserBestStatus(String rank,int elo,double rating,User user) {
		return d.updateUserBestStatus(rank, elo, rating, user);
	}
	
	public int updatePassword(String password,int uid) {
		return d.updatePassword(password, uid);
	}
	
	public int DisonnectServer(User user,Server server) {
		return d.DisonnectServer(user, server);
	}
	
	public int AcceptGame(User user,Server server) {
		return d.AcceptGame(user, server);
	}
	
	public boolean GetSkill(JLabel x) {
		return d.GetSkill(x);
	}
	
	public boolean GetHero(JLabel x) {
		return d.GetHero(x);
	}
	
	public boolean GetItem(JLabel x) {
		return d.GetItem(x);
	}
	
	public Hero GetHeroByName(String name) {
		return d.GetHeroByName(name);
	}
	
	public boolean IsRoomCreater(int id,User user,Server server) {
		return d.IsRoomCreater(id, user, server);
	}
	
	public int CreateRoom(User user) {
		return d.CreateRoom(user);
	}
	
	public int getRoomid(User user) {
		return d.getRoomid(user);
	}
	
	public int DeleteRoom(User user) {
		return d.DeleteRoom(user);
	}
	
	public int setWinner(User user,int room,Server server) {
		return d.setWinner(user, room, server);
	}
	
	public int setIsnotReady1(int room) {
		return d.setIsnotReady1(room);
	}
	
	public int setIsnotReady2(int room) {
		return d.setIsnotReady2(room);
	}
	
	public Hero GetSkillByHero(Hero hero) {
		return d.GetSkillByHero(hero);
	}
	
	public JButton getItemIcon(int itemid) {
		return d.getItemIcon(itemid);
	}
	
	public ImageIcon getItemImageIcon(int itemid) {
		return d.getItemImageIcon(itemid);
	}
	
	public int addHeroPlay() {
		return d.addHeroPlay();
	}
	
	public int addHeroBan(int hid, double banrate) {
		return d.addHeroBan(hid, banrate);
	}
	
	public int addHeroPick(int hid, double pickrate) {
		return d.addHeroPick(hid,pickrate);
	}
	
	public int addHeroWin(int hid,double winrate) {
		return d.addHeroWin(hid, winrate);
	}
	
	public int UpdateHeroTime(int hid,int round) {
		return d.UpdateHeroTime(hid, round);
	}
	
	public int UpdateHeroDamage(int hid,double damage) {
		return d.UpdateHeroDamage(hid, damage);
	}
	
	public int UpdateHeroADR(int hid,double adr) {
		return d.UpdateHeroADR(hid, adr);
	}
	
	public int updateHerorate() {
		return d.updateHerorate();
	}
	
	public int CreateRoomBySystem(int roomid,User user,String gametime,String server) {
		return d.CreateRoomBySystem(roomid, user, gametime, server);
	}
	
	public ArrayList<User> getRankingList() {
		return d.getRankingList();
	}
	
	public JLabel getSystemTip() {
		return d.getSystemTip();
	}
	
	public JLabel getAD() {
		return d.getAD();
	}
	
	public ArrayList<Server> getServerList() {
		return d.getServerList();
	}
	
	public int updateLoserGameRecord(int roomid,boolean roomcreater,ArrayList<Hero> userbanned,ArrayList<Hero> userpicked,
			int userelo,int userelop,double rating,int kill,int death,double adr,int djs,User user) {
		return d.updateLoserGameRecord(roomid, roomcreater, userbanned, userpicked, userelo, userelop, rating, kill, death, adr, djs, user);
	}
	
	public int updateWinnerGameRecord(int roomid,boolean roomcreater,ArrayList<Hero> userbanned,ArrayList<Hero> userpicked,
			int userelo,int userelop,double rating,int kill,int death,double adr,int djs,User user) {
		return d.updateWinnerGameRecord(roomid, roomcreater, userbanned, userpicked, userelo, userelop, rating, kill, death, adr, djs, user);
	}
	
	public Record GetGameRecordByID(int recordid) {
		return d.GetGameRecordByID(recordid);
	}
	
	public ArrayList<Record> getRecordList(User user) {
		return d.getRecordList(user);
	}
	
}