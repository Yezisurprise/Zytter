package util;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;

import entity.BGM;
import entity.Hero;
import entity.Item;
import entity.Skill;
import service.Service;

public class Config {
	/*
	 * - 游戏常量定义
	 */
	public static final String GlobalTitle = "Zytter Client"; // 全局界面标题
	public static final int clientversion = 20200611; // 客户端版本号
	public static final String clientengversion = "v1.6"; // 另一个版本号
	public static final String version = clientversion + " by Zy初心组"; // 版本号
	public static Service s = new Service(); // 用于调用Dao包底层方法
	public static boolean isinroom = false; // 玩家是否处于房间状态
	
	//BGM
	public static BGM bgm;
	
	//字体
	public static Font LoginFont = new Font("微软雅黑 Light",Font.PLAIN,48);
	public static Font SmallFont = new Font("微软雅黑 Light",Font.PLAIN,28);
	public static Font ButtonFont = new Font("微软雅黑 Light",Font.PLAIN,32);
	public static Font GlobalFont = new Font("微软雅黑 Light",Font.PLAIN,18);
	
	//颜色
	public static Color usercolor = new Color(233, 150, 122);
	public static Color enemycolor = new Color(240, 128, 128);
	
	//英雄技能物品组
	public static ArrayList<Hero> Allheroes = new ArrayList<Hero>();
	public static ArrayList<Skill> Allskills = new ArrayList<Skill>();
	public static ArrayList<Item> Allitems = new ArrayList<Item>();
	
	/*
	 * 操作初始化
	 */
	public static int jn1 = 1;
	public static int jn2 = 2;
	public static int jn3 = 3;
	public static int jn4 = 4;
	public static int atk = 5;
	public static int wp = 6;
	
	/*
	 * 英雄初始化
	 */
	public static Hero yy;
	public static Hero lxs;
	public static Hero ysn;
	public static Hero ltj;
	public static Hero zf;
	public static Hero hyq;
	public static Hero xyh;
	public static Hero zkx;
	public static Hero zxy;
	public static Hero lm;
	public static Hero sjj;
	public static Hero w;
	
	public static void InitHeroes(JLabel x) {
		
		x.setText("正在载入英雄数据......请稍后（0%）");
		
		if(Config.s.GetHero(x)) {
			x.setText("正在载入英雄数据......请稍后（100%）");
		}
		
	}
	
	/*
	 * 技能初始化
	 */
	public static void InitSkills(JLabel x) {
		
		x.setText("正在载入技能数据......请稍后"+"（0%）");
		
		if(Config.s.GetSkill(x)) {
			x.setText("正在载入技能数据......请稍后"+"（100%）");
		}
		
		// 奕阳
		yy.setQ(Allskills.get(0));
		
		yy.setW(Allskills.get(1));

		yy.setE(Allskills.get(2));
		// 刘晓释

		lxs.setQ(Allskills.get(3));

		lxs.setW(Allskills.get(4));

		lxs.setE(Allskills.get(5));
		// 杨圣诺

		ysn.setQ(Allskills.get(6));

		ysn.setW(Allskills.get(7));
		// 张枫

		zf.setQ(Allskills.get(8));

		zf.setW(Allskills.get(9));

		zf.setE(Allskills.get(10));
		// 罗天杰

		ltj.setQ(Allskills.get(11));

		ltj.setW(Allskills.get(12));

		ltj.setE(Allskills.get(13));
		// 郈与却

		hyq.setQ(Allskills.get(14));

		hyq.setW(Allskills.get(15));

		hyq.setE(Allskills.get(16));

		hyq.setR(Allskills.get(17));
		// 谢悠涵

		xyh.setQ(Allskills.get(18));

		xyh.setW(Allskills.get(19));

		xyh.setE(Allskills.get(20));
		// 张可汐

		zkx.setQ(Allskills.get(21));

		zkx.setW(Allskills.get(22));

		zkx.setE(Allskills.get(23));
		// 郑心予

		zxy.setQ(Allskills.get(24));

		zxy.setW(Allskills.get(25));

		zxy.setE(Allskills.get(26));

		zxy.setR(Allskills.get(27));
		// 刘珂明

		lm.setQ(Allskills.get(28));

		lm.setW(Allskills.get(29));
		// 苏璟静

		sjj.setQ(Allskills.get(30));

		sjj.setW(Allskills.get(31));

		sjj.setE(Allskills.get(32));

		sjj.setR(Allskills.get(33));
		// 维多利娜

		w.setQ(Allskills.get(34));

		w.setW(Allskills.get(35));
		
		/**
		 * 给技能赋值
		 */
		

	}
	
	/*
	 * 物品初始化
	 */
	public static void InitItems(JLabel x) {
		
		x.setText("正在载入物品数据......请稍后"+"（0%）");
		
		if(Config.s.GetItem(x)) {
			x.setText("正在载入物品数据......请稍后"+"（100%）");
		}
		
		x.setText("正在验证数据完整性......");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
