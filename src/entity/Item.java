package entity;

public class Item {
	private int id;//���ݿ��ж�Ӧ��ID
	private String name;//����
	private String describe;//����
	private int gold=0;//��������
	private String value1,value2;//װ������
	private User user;//�������
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
	 * Wupin's ���췽��
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
	 * ��Ʒ����
	 */
	public String getItem() {
		return "<html>"+name+"<br /><br />"+value1+" "+value2+"<br /><br />Ч��<br />"+describe+"<br /><br />������ģ�"+gold+"</html>";
	}
}
