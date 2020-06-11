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
	 * - ��Ϸ��������
	 */
	public static final String GlobalTitle = "Zytter Client"; // ȫ�ֽ������
	public static final int clientversion = 20200611; // �ͻ��˰汾��
	public static final String clientengversion = "v1.6"; // ��һ���汾��
	public static final String version = clientversion + " by Zy������"; // �汾��
	public static Service s = new Service(); // ���ڵ���Dao���ײ㷽��
	public static boolean isinroom = false; // ����Ƿ��ڷ���״̬
	
	//BGM
	public static BGM bgm;
	
	//����
	public static Font LoginFont = new Font("΢���ź� Light",Font.PLAIN,48);
	public static Font SmallFont = new Font("΢���ź� Light",Font.PLAIN,28);
	public static Font ButtonFont = new Font("΢���ź� Light",Font.PLAIN,32);
	public static Font GlobalFont = new Font("΢���ź� Light",Font.PLAIN,18);
	
	//��ɫ
	public static Color usercolor = new Color(233, 150, 122);
	public static Color enemycolor = new Color(240, 128, 128);
	
	//Ӣ�ۼ�����Ʒ��
	public static ArrayList<Hero> Allheroes = new ArrayList<Hero>();
	public static ArrayList<Skill> Allskills = new ArrayList<Skill>();
	public static ArrayList<Item> Allitems = new ArrayList<Item>();
	
	/*
	 * ������ʼ��
	 */
	public static int jn1 = 1;
	public static int jn2 = 2;
	public static int jn3 = 3;
	public static int jn4 = 4;
	public static int atk = 5;
	public static int wp = 6;
	
	/*
	 * Ӣ�۳�ʼ��
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
		
		x.setText("��������Ӣ������......���Ժ�0%��");
		
		if(Config.s.GetHero(x)) {
			x.setText("��������Ӣ������......���Ժ�100%��");
		}
		
	}
	
	/*
	 * ���ܳ�ʼ��
	 */
	public static void InitSkills(JLabel x) {
		
		x.setText("�������뼼������......���Ժ�"+"��0%��");
		
		if(Config.s.GetSkill(x)) {
			x.setText("�������뼼������......���Ժ�"+"��100%��");
		}
		
		// ����
		yy.setQ(Allskills.get(0));
		
		yy.setW(Allskills.get(1));

		yy.setE(Allskills.get(2));
		// ������

		lxs.setQ(Allskills.get(3));

		lxs.setW(Allskills.get(4));

		lxs.setE(Allskills.get(5));
		// ��ʥŵ

		ysn.setQ(Allskills.get(6));

		ysn.setW(Allskills.get(7));
		// �ŷ�

		zf.setQ(Allskills.get(8));

		zf.setW(Allskills.get(9));

		zf.setE(Allskills.get(10));
		// �����

		ltj.setQ(Allskills.get(11));

		ltj.setW(Allskills.get(12));

		ltj.setE(Allskills.get(13));
		// �C��ȴ

		hyq.setQ(Allskills.get(14));

		hyq.setW(Allskills.get(15));

		hyq.setE(Allskills.get(16));

		hyq.setR(Allskills.get(17));
		// л�ƺ�

		xyh.setQ(Allskills.get(18));

		xyh.setW(Allskills.get(19));

		xyh.setE(Allskills.get(20));
		// �ſ�ϫ

		zkx.setQ(Allskills.get(21));

		zkx.setW(Allskills.get(22));

		zkx.setE(Allskills.get(23));
		// ֣����

		zxy.setQ(Allskills.get(24));

		zxy.setW(Allskills.get(25));

		zxy.setE(Allskills.get(26));

		zxy.setR(Allskills.get(27));
		// ������

		lm.setQ(Allskills.get(28));

		lm.setW(Allskills.get(29));
		// �խZ��

		sjj.setQ(Allskills.get(30));

		sjj.setW(Allskills.get(31));

		sjj.setE(Allskills.get(32));

		sjj.setR(Allskills.get(33));
		// ά������

		w.setQ(Allskills.get(34));

		w.setW(Allskills.get(35));
		
		/**
		 * �����ܸ�ֵ
		 */
		

	}
	
	/*
	 * ��Ʒ��ʼ��
	 */
	public static void InitItems(JLabel x) {
		
		x.setText("����������Ʒ����......���Ժ�"+"��0%��");
		
		if(Config.s.GetItem(x)) {
			x.setText("����������Ʒ����......���Ժ�"+"��100%��");
		}
		
		x.setText("������֤����������......");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
