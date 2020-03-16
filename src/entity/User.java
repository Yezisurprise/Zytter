package entity;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5350964189158865105L;
	
	private int uid;
	private String username,password;
	private String rank;//��������
	private double rating;//�����÷�
	private int elo;//���ݷ�
	private int ismatching=0;//0=����ƥ�� 1=����ƥ�� 2=���ҵ�������δ����
	private int isonline=0;//0=������ 1=���ߣ����� 2=���ߣ�ƥ���� 3=���ߣ���Ϸ��
	private int gold=0;//Ǯ�����
	private int djs;//������
	private int all,win,lose;//�������Σ�ʤ�����ܳ�
	private double winrate;//����ʤ��
	private int allplay,allwin,alllose;//ȫ�����Σ�ʤ�����ܳ�
	private double allwinrate;//����ʤ��
	private String bestrank;//��߶�λ
	private int bestelo;//������ݻ���
	private double bestrating;//��߼����÷�
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getElo() {
		return elo;
	}

	public void setElo(int elo) {
		this.elo = elo;
	}

	public int getIsmatching() {
		return ismatching;
	}

	public void setIsmatching(int ismatching) {
		this.ismatching = ismatching;
	}

	public int getIsonline() {
		return isonline;
	}

	public void setIsonline(int isonline) {
		this.isonline = isonline;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getDjs() {
		return djs;
	}
	public void setDjs(int djs) {
		this.djs = djs;
	}
	public int getAll() {
		return all;
	}
	public void setAll(int all) {
		this.all = all;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	public double getWinrate() {
		return winrate;
	}
	public void setWinrate(double winrate) {
		this.winrate = winrate;
	}
	
	public int getAllplay() {
		return allplay;
	}
	public void setAllplay(int allplay) {
		this.allplay = allplay;
	}
	public int getAllwin() {
		return allwin;
	}
	public void setAllwin(int allwin) {
		this.allwin = allwin;
	}
	public int getAlllose() {
		return alllose;
	}
	public void setAlllose(int alllose) {
		this.alllose = alllose;
	}
	public double getAllwinrate() {
		return allwinrate;
	}
	public void setAllwinrate(double allwinrate) {
		this.allwinrate = allwinrate;
	}
	public String getBestrank() {
		return bestrank;
	}
	public void setBestrank(String bestrank) {
		this.bestrank = bestrank;
	}
	public int getBestelo() {
		return bestelo;
	}
	public void setBestelo(int bestelo) {
		this.bestelo = bestelo;
	}
	public double getBestrating() {
		return bestrating;
	}
	public void setBestrating(double bestrating) {
		this.bestrating = bestrating;
	}
	//
	public User(String name) {
		username = name;
	}
	
	public User(String username, int elo, String rank, double rating, int all, int win, double winrate) {
		this.username=username;
		this.elo=elo;
		this.rank=rank;
		this.rating=rating;
		this.all=all;
		this.win=win;
		this.winrate=winrate;
	}
	
	public User(int uid, String username, String password, double rating, int elo, String rank) {
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.rank = rank;
		this.rating = rating;
		this.elo = elo;
	}
	
	public User(int uid, String username, String password, double rating, int elo, String rank, int djs, int all, int win, int lose, double winrate,
			int allplay, int allwin, int alllose, double allwinrate, String bestrank, int bestelo, double bestrating) {
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.rank = rank;
		this.rating = rating;
		this.elo = elo;
		this.djs = djs;
		this.all = all;
		this.win = win;
		this.lose = lose;
		this.winrate = winrate;
		this.allplay = allplay;
		this.allwin = allwin;
		this.alllose = alllose;
		this.allwinrate = allwinrate;
		this.bestrank = bestrank;
		this.bestelo = bestelo;
		this.bestrating = bestrating;
	}
	@Override
	public String toString() {
		return "��� "+username+" ��ǰ����rank�� "+rank+" ��"+elo+"�֣�";
	}
}
