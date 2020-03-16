package entity;

public class Item {
	private int id;//数据库中对应的ID
	private String name;//名称
	private String describe;//描述
	private int gold=0;//购买消费
	private String value1,value2;//装备类型
	private User user;//所属玩家
	/*
	 * Get/Set
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	/*
	 * Wupin's 构造方法
	 */
	public Item(int id,String name,String describe,int gold,String value1,String value2) {
		super();
		this.id = id;
		this.name = name;
		this.describe = describe;
		this.gold = gold;
		this.value1 = value1;
		this.value2 = value2;
	}
	/*
	 * 物品描述
	 */
	public String getItem() {
		return "<html>"+name+"<br /><br />"+value1+" "+value2+"<br /><br />效果<br />"+describe+"<br /><br />金币消耗："+gold+"</html>";
	}
}
