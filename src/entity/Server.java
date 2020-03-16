package entity;

public class Server {
	
	private String name;
	private String IP;
	
	private int id,status;
	private String password;
	private String tip;
	private String ver;
	
	public final static int matchport = 17717;
	public final static int gameport = 17718;
	public final static int probailityport = 17719;
	public final static int equipport = 17720;
	public final static int chatport = 17723;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String IP) {
		this.IP = IP;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	
	public Server(String name, String IP) {
		this.name = name;
		this.IP = IP;
	}
	public Server(String name, String IP, int id, int status, String password, String tip, String ver) {
		this.name = name;
		this.IP = IP;
		this.id = id;
		this.status = status;
		this.password = password;
		this.tip = tip;
		this.ver = ver;
	}
	
}