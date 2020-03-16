package entity;

public class Skill {
	private int id;//数据库中对应的ID
	private String name;//名称
	private String describe;//描述
	private int mp=0;//魔法消耗
	private int type=0;//伤害类型：0=魔法，1=物理，2=其他
	private int hurt=0;//伤害性魔法或者回复性魔法的伤害/回复值
	private int value1=0,value2=0,value3=0,value4=0;//备用数值
	private Hero hero;//所属英雄
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
	public String getdescribe() {
		return describe;
	}
	public void setdescribe(String describe) {
		this.describe = describe;
	}
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getHurt() {
		return hurt;
	}
	public void setHurt(int hurt) {
		this.hurt = hurt;
	}
	public int getValue1() {
		return value1;
	}
	public void setValue1(int value1) {
		this.value1 = value1;
	}
	public int getValue2() {
		return value2;
	}
	public void setValue2(int value2) {
		this.value2 = value2;
	}
	public int getValue3() {
		return value3;
	}
	public void setValue3(int value3) {
		this.value3 = value3;
	}
	public int getValue4() {
		return value4;
	}
	public void setValue4(int value4) {
		this.value4 = value4;
	}
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	/*
	 * Jineng's 构造方法
	 */
	
	public Skill(Hero hero) {
		this.hero = hero;
	}
	public Skill(int id, String name, String describe, int mp, int type, int hurt, int value1, int value2, int value3,
			int value4, Hero hero) {
		super();
		this.id = id;
		this.name = name;
		this.describe = describe;
		this.mp = mp;
		this.type = type;
		this.hurt = hurt;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.value4 = value4;
		this.hero = hero;
	}
	public Skill(int id, String name, String describe, int mp, int type, int hurt, int value1, int value2, int value3, Hero hero) {
		super();
		this.id = id;
		this.name = name;
		this.describe = describe;
		this.mp = mp;
		this.type = type;
		this.hurt = hurt;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.hero = hero;
	}
	public Skill(int id, String name, String describe, int mp, int type, int hurt, int value1, int value2, Hero hero) {
		super();
		this.id = id;
		this.name = name;
		this.describe = describe;
		this.mp = mp;
		this.type = type;
		this.hurt = hurt;
		this.value1 = value1;
		this.value2 = value2;
		this.hero = hero;
	}
	public Skill(int id, String name, String describe, int mp, int type, int hurt, int value1, Hero hero) {
		super();
		this.id = id;
		this.name = name;
		this.describe = describe;
		this.mp = mp;
		this.type = type;
		this.hurt = hurt;
		this.value1 = value1;
		this.hero = hero;
	}
	public Skill(int id, String name, String describe, int mp, int type, int hurt, Hero hero) {
		super();
		this.id = id;
		this.name = name;
		this.describe = describe;
		this.mp = mp;
		this.type = type;
		this.hurt = hurt;
		this.hero = hero;
	}
	public Skill(int id, String name, String describe, int mp) {
		super();
		this.id = id;
		this.name = name;
		this.describe = describe;
		this.mp = mp;
	}
	
	public String getSkill() {
		return name+"（魔法消耗："+mp+"）<br />"+describe;
	}
	
}
