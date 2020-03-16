package entity;

public class Record {
	
	private int id;//比赛记录号（房间号）
	private String p1,p2;//玩家ID
	private String winner;//胜利者ID
	private int p1elo,p2elo;//天梯积分
	private int p1elop,p2elop;//积分变动
	private int p1b1,p1b2,p2b1,p2b2;//BAN
	private int p1p1,p1p2,p1p3,p2p1,p2p2,p2p3;//PICK
	private double p1rating,p2rating;//rating
	private int p1k,p1d,p2k,p2d;//杀敌死亡数
	private double p1adr,p2adr;//平均伤害
	private int p1djs,p2djs;//定级赛
	private String time,server;//比赛时间，服务器
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getP1() {
		return p1;
	}
	public void setP1(String p1) {
		this.p1 = p1;
	}
	public String getP2() {
		return p2;
	}
	public void setP2(String p2) {
		this.p2 = p2;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public int getP1elo() {
		return p1elo;
	}
	public void setP1elo(int p1elo) {
		this.p1elo = p1elo;
	}
	public int getP2elo() {
		return p2elo;
	}
	public void setP2elo(int p2elo) {
		this.p2elo = p2elo;
	}
	public int getP1elop() {
		return p1elop;
	}
	public void setP1elop(int p1elop) {
		this.p1elop = p1elop;
	}
	public int getP2elop() {
		return p2elop;
	}
	public void setP2elop(int p2elop) {
		this.p2elop = p2elop;
	}
	public int getP1b1() {
		return p1b1;
	}
	public void setP1b1(int p1b1) {
		this.p1b1 = p1b1;
	}
	public int getP1b2() {
		return p1b2;
	}
	public void setP1b2(int p1b2) {
		this.p1b2 = p1b2;
	}
	public int getP2b1() {
		return p2b1;
	}
	public void setP2b1(int p2b1) {
		this.p2b1 = p2b1;
	}
	public int getP2b2() {
		return p2b2;
	}
	public void setP2b2(int p2b2) {
		this.p2b2 = p2b2;
	}
	public int getP1p1() {
		return p1p1;
	}
	public void setP1p1(int p1p1) {
		this.p1p1 = p1p1;
	}
	public int getP1p2() {
		return p1p2;
	}
	public void setP1p2(int p1p2) {
		this.p1p2 = p1p2;
	}
	public int getP1p3() {
		return p1p3;
	}
	public void setP1p3(int p1p3) {
		this.p1p3 = p1p3;
	}
	public int getP2p1() {
		return p2p1;
	}
	public void setP2p1(int p2p1) {
		this.p2p1 = p2p1;
	}
	public int getP2p2() {
		return p2p2;
	}
	public void setP2p2(int p2p2) {
		this.p2p2 = p2p2;
	}
	public int getP2p3() {
		return p2p3;
	}
	public void setP2p3(int p2p3) {
		this.p2p3 = p2p3;
	}
	public double getP1rating() {
		return p1rating;
	}
	public void setP1rating(double p1rating) {
		this.p1rating = p1rating;
	}
	public double getP2rating() {
		return p2rating;
	}
	public void setP2rating(double p2rating) {
		this.p2rating = p2rating;
	}
	public int getP1k() {
		return p1k;
	}
	public void setP1k(int p1k) {
		this.p1k = p1k;
	}
	public int getP1d() {
		return p1d;
	}
	public void setP1d(int p1d) {
		this.p1d = p1d;
	}
	public int getP2k() {
		return p2k;
	}
	public void setP2k(int p2k) {
		this.p2k = p2k;
	}
	public int getP2d() {
		return p2d;
	}
	public void setP2d(int p2d) {
		this.p2d = p2d;
	}
	public double getP1adr() {
		return p1adr;
	}
	public void setP1adr(double p1adr) {
		this.p1adr = p1adr;
	}
	public double getP2adr() {
		return p2adr;
	}
	public void setP2adr(double p2adr) {
		this.p2adr = p2adr;
	}
	public int getP1djs() {
		return p1djs;
	}
	public void setP1djs(int p1djs) {
		this.p1djs = p1djs;
	}
	public int getP2djs() {
		return p2djs;
	}
	public void setP2djs(int p2djs) {
		this.p2djs = p2djs;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	
	public Record(int id, String p1, String p2, String winner, int p1elo, int p2elo, int p1elop, int p2elop, int p1b1,
			int p1b2, int p2b1, int p2b2, int p1p1, int p1p2, int p1p3, int p2p1, int p2p2, int p2p3, double p1rating,
			double p2rating, int p1k, int p1d, int p2k, int p2d, double p1adr, double p2adr, int p1djs, int p2djs, String time, String server) {
		super();
		this.id = id;
		this.p1 = p1;
		this.p2 = p2;
		this.winner = winner;
		this.p1elo = p1elo;
		this.p2elo = p2elo;
		this.p1elop = p1elop;
		this.p2elop = p2elop;
		this.p1b1 = p1b1;
		this.p1b2 = p1b2;
		this.p2b1 = p2b1;
		this.p2b2 = p2b2;
		this.p1p1 = p1p1;
		this.p1p2 = p1p2;
		this.p1p3 = p1p3;
		this.p2p1 = p2p1;
		this.p2p2 = p2p2;
		this.p2p3 = p2p3;
		this.p1rating = p1rating;
		this.p2rating = p2rating;
		this.p1k = p1k;
		this.p1d = p1d;
		this.p2k = p2k;
		this.p2d = p2d;
		this.p1adr = p1adr;
		this.p2adr = p2adr;
		this.p1djs = p1djs;
		this.p2djs = p2djs;
		this.time = time;
		this.server = server;
	}
	
}
