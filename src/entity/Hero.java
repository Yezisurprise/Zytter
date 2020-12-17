package entity;

import util.Config;

public class Hero {
	private int id;//���ݿ��ж�Ӧ��ID
	private String name,ename;//���ֺʹ���
	private int hp=0,mp=0;//����ֵ��ħ��ֵ
	private int atk=0,def=0,adf=0,xdl=0,hpp=0,mpp=0;//������������ħ�����ж����������ָ��ٶȣ�ħ���ָ��ٶ�
	private double adp=0,app=0;//����͸��ħ����͸
	private double defrate=0;//�����˺�����
	private User user;//�������
	private PlayerIcon icon;//Ӣ��ͷ��
	private Skill Q,W,E,R;//������
	private Item Z,X;//װ����
	private int play,ban,pick,win;//��Ϸ���Σ�B/P������ʤ��
	private double banrate,pickrate,winrate;//B/P�ʣ�ʤ��
	private int time;//ƽ��ʱ��
	private double d,adr;//ƽ���غ��˺���ƽ�����˺�
	/*
	 * - Ӣ��״̬�ж�
	 */
	private boolean isgone=true;//false=��ȫ�ж�����
	private boolean islimte=true;//false=�ж�����
	private boolean isatk=true;//false=��������
	private boolean isskill=true;//false=ʩ������
	private boolean isfight=true;//false=ս������
	/*
	 * - �ᾧ֮�� �����ж�
	 */
	public int damage=0;//�ۼ�����˺�
	public int jhjj=0;//����ᾧ֮���ķ�֧
	public boolean jjzl=false;//�ᾧ֮������״̬
	/*
	 * - Ӣ�ۼ��� ��Ч�ж�
	 */
	public int yyQ=0;//����Q
	public int yyE=0;//����E���˺�
	public int yyER=0;//����E�Ļغ�
	public int lxsE=3;//������E
	public boolean ysnQ=false;//��ʥŵQ
	public int ysnW=0;//��ʥŵW
	public boolean zfW=false;//�ŷ�W
	public int zkxQ=0;//�ſ�ϫQ���˺�
	public int zkxQC=0;//�ſ�ϫQ���˺�����
	public int zkxW=0;//�ſ�ϫW
	public int zkxWH=0;//�ſ�ϫW�ۼƳ���
	public int zkxE=0;//�ſ�ϫE2���˺�
	public int hyqQ=0;//�C��ȴQ
	public int hyqW=0;//�C��ȴW
	public double hyqJ=0;//�C��ȴQ�˺���ǿ
	public int ltjW=0;//�����W
	public int xyh=0;//л�ƺ�Q�Ľྻ��
	public boolean xyhQ=false;//л�ƺ�Q��Ч
	public int xyhQHPP=0;//л�ƺ�Q��HPP
	public int xyhE=0;//л�ƺ�E
	public int xyhED=0;//л�ƺ�E�ڼ��ۼ��˺�
	public boolean zxyQ=false;//֣����Q
	public int zxyE=0;//֣����E
	public boolean zxyEE=false;//֣����E������
	public int zxyR=0;//֣����R
	public boolean lmQ=false;//������Q
	public boolean lmW=false;//������W
	public int sjjQ=0;//�խZ��Q
	public boolean sjjW=false;//�խZ��W
	public boolean sjjE=false;//�խZ��E
	public int sjjR=0;//�խZ��R
	public int ww=-1;//ά������W
	/*
	 * - Ӣ��װ�� ��Ч�ж�
	 */
	public boolean zy=false;//��������
	public boolean hy=false;//��������
	public boolean yjg=false;//ӥ�ǹ�
	public boolean pjzm=false;//�ƾ�֮ì
	public boolean ispjzm=false;//�ƾ�֮ì������
	public int pjzmcd=0;//�ƾ�֮ìCD
	public boolean jrz=false;//������֮��
	public boolean yyzs=false;//ҹ��֮��
	public boolean xsh=false;//ѧ������
	public boolean fs=false;//����
	/*
	 * - Ӣ��ͼ�� ��ʼ��
	 */
	public BuffIcon lrzj = new BuffIcon("����֮��", 3, Config.s.mdfdown, "��Ӣ�ۻ�����ܵ��������յ�ħ���˺���");
	public BuffIcon tszf = new BuffIcon("��ɱ֮��", 4, Config.s.appup, "��ɱ֮���Ϊ��Ӣ���ṩħ���˺��ӳɡ�");
	public BuffIcon jfzm = new BuffIcon("�������", -1, Config.s.atkup, "ÿ������������Ϊ��Ӣ������2�㹥������1�㻤�ס�");
	public BuffIcon userxxcc = new BuffIcon("���ǳ��", 1, Config.s.defup, "��Ӣ���Ѿ���ȡ�˶Է��������ס�");
	public BuffIcon enemyxxcc = new BuffIcon("���ǳ��", 1, Config.s.defdown, "��Ӣ���Ѿ����Է���ȡ�������ס�");
	public BuffIcon xcyl = new BuffIcon("�ǳ�����", 2, Config.s.mdfdown, "��Ӣ�۵�ħ������������2�㡣");
	public BuffIcon fzjj1 = new BuffIcon("��֮���", 1, Config.s.allunable, "��Ӣ����ȫ�ж����ܡ�");
	public BuffIcon fzjj2 = new BuffIcon("��֮���", 1, Config.s.limited, "��Ӣ���ж����ޡ�");
	public BuffIcon sx = new BuffIcon("����", 3, Config.s.evade, "��Ӣ�۾���60%������ͨ�����ĸ��ʡ�");
	public BuffIcon sxplus = new BuffIcon("����+", 2, Config.s.evade, "��Ӣ�۾���80%������ͨ�����ĸ��ʡ�");
	public BuffIcon sxmk = new BuffIcon("����+", 2, Config.s.mdfup, "��Ӣ�۵�ħ������������2�㡣");
	public BuffIcon xrwz = new BuffIcon("����Ϊ��", 3, Config.s.appup, "��Ӣ�۵�ħ����͸�����ˡ�");
	public BuffIcon qlbx = new BuffIcon("ǿ������", 3, Config.s.defdown, "��Ӣ�۵������׽�����4�㡣");
	public BuffIcon yxzd = new BuffIcon("����֮��", -1, Config.s.atkup, "��Ӣ�۵Ĺ�����������4�㡣");
	public BuffIcon tydf = new BuffIcon("��Բ�ط�", 1, Config.s.atkunable, "��Ӣ��ս�����ܡ�");
	public BuffIcon sgsl = new BuffIcon("ʱ��ɳ©", 3, Config.s.magicimmunity, "��Ӣ����ȫ�����κ��˺���");
	public BuffIcon bxsz = new BuffIcon("��ѩʮ��", 1, Config.s.allunable, "��Ӣ����ȫ�ж����ܡ�");
	public BuffIcon bzyy = new BuffIcon("��֮����", 3, Config.s.defup, "��Ӣ����ȫ���������˺���");
	public BuffIcon xzjz = new BuffIcon("ϫ֮����", -1, Config.s.appup, "ÿ��ϫ֮���񶼻�Ϊ��Ӣ���ṩ2��ħ���˺��ӳɡ�");
	public BuffIcon xzjzsh = new BuffIcon("ϫ֮����", -1, Config.s.appup, "ϫ֮�����Ч��һΪ��ѩʮ���ṩ��4��ħ���˺��ӳɡ�");
	public BuffIcon xzjzmk = new BuffIcon("ϫ֮����", -1, Config.s.mdfup, "��Ӣ�۵�ħ������������4�㡣");
	public BuffIcon xzjzwk = new BuffIcon("ϫ֮����", -1, Config.s.defup, "��Ӣ�۵�������������4�㡣");
	public BuffIcon lz = new BuffIcon("����", -1, Config.s.appup, "��Ӣ����һ�����ħ���˺�ʱ����ٶԷ��൱�������ħ��ֵ42%��ħ��ֵ��");
	public BuffIcon userylzh = new BuffIcon("����֮��", 2, Config.s.defup, "��Ӣ���ܹ��������ܵ���80%�����˺���");
	public BuffIcon enemyylzh = new BuffIcon("����֮��", 2, Config.s.magicunable, "��Ӣ��ʩ�����ܡ�");
	public BuffIcon xysy = new BuffIcon("��Դ����", 2, Config.s.hpadd, "��Ӣ�����ڻغϿ�ʼʱ�ظ�����ֵ��");
	public BuffIcon lf = new BuffIcon("�ѷ�", 1, Config.s.atkunable, "��Ӣ��ս�����ܡ�");
	public BuffIcon lfatk = new BuffIcon("�ѷ�", 1, Config.s.atkup, "��Ӣ�۵Ĺ�����������3�㡣");
	public BuffIcon gcj = new BuffIcon("��㽣", -1, Config.s.adpup, "��Ӣ����һ����ͨ��������˺�ʱ�ḽ��ħ���˺���");
	public BuffIcon gzhl = new BuffIcon("��������", -1, Config.s.mdfup, "��Ӣ���ܵ��˺�ʱ���н�����Ϊ��ֵ�����4���˺���");
	public BuffIcon sy = new BuffIcon("����", 1, Config.s.limited, "��Ӣ�۱����������ͣ�����ͻ���ʧ1�������ס�");
	public BuffIcon sg1 = new BuffIcon("ʥ��", -1, Config.s.atkup, "��Ӣ�۵Ĺ�����������3�㡣");
	public BuffIcon sg2 = new BuffIcon("ʥ��", -1, Config.s.defup, "��Ӣ�۵�������������2�㡣");
	public BuffIcon fsjn = new BuffIcon("����", -1, Config.s.magicimmunity, "��Ӣ�۽������ܵ��κ�ǿ����Ч����Ӱ�졣");
	public BuffIcon mpadd = new BuffIcon("ħ������III", -1, Config.s.hpadd, "��Ӣ�۽�������2��ħ���ظ���");
	public BuffIcon xdladd = new BuffIcon("�ж�������", -1, Config.s.speedup, "��Ӣ�۵��ж���������4�㡣");
	public BuffIcon mkadd = new BuffIcon("˫��ҩ��", -1, Config.s.mdfup, "��Ӣ�۵�ħ������������4�㡣");
	public BuffIcon hjadd = new BuffIcon("˫��ҩ��", -1, Config.s.defup, "��Ӣ�۵�������������4�㡣");
	public BuffIcon atkadd = new BuffIcon("ǿ��ҩˮ", -1, Config.s.atkup, "��Ӣ�۵Ĺ�����������4�㡣");
	/*
	 * Set/Get
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public int getAdf() {
		return adf;
	}
	public void setAdf(int adf) {
		this.adf = adf;
	}

	public int getXdl() {
		return xdl;
	}

	public void setXdl(int xdl) {
		this.xdl = xdl;
	}

	public int getHpp() {
		return hpp;
	}

	public void setHpp(int hpp) {
		this.hpp = hpp;
	}

	public int getMpp() {
		return mpp;
	}

	public void setMpp(int mpp) {
		this.mpp = mpp;
	}

	public double getAdp() {
		return adp;
	}

	public void setAdp(double adp) {
		this.adp= adp;
	}

	public double getApp() {
		return app;
	}
	public void setApp(double app) {
		this.app = app;
	}
	
	public double getDefrate() {
		return defrate;
	}

	public void setDefrate(double defrate) {
		this.defrate = defrate;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Skill getQ() {
		return Q;
	}
	public void setQ(Skill q) {
		Q = q;
	}
	public Skill getW() {
		return W;
	}
	public void setW(Skill w) {
		W = w;
	}
	public Skill getE() {
		return E;
	}
	public void setE(Skill e) {
		E = e;
	}
	public Skill getR() {
		return R;
	}
	public void setR(Skill r) {
		R = r;
	}
	public Item getZ() {
		return Z;
	}
	public void setZ(Item z) {
		Z = z;
	}
	public Item getX() {
		return X;
	}
	public void setX(Item x) {
		X = x;
	}
	public PlayerIcon getIcon() {
		return icon;
	}
	public void setIcon(PlayerIcon icon) {
		this.icon = icon;
	}
	public boolean isIsgone() {
		return isgone;
	}
	public void setIsgone(boolean isgone) {
		this.isgone = isgone;
	}
	public boolean isIslimte() {
		return islimte;
	}
	public void setIslimte(boolean islimte) {
		this.islimte = islimte;
	}
	public boolean isIsatk() {
		return isatk;
	}
	public void setIsatk(boolean isatk) {
		this.isatk = isatk;
	}
	public boolean isIsskill() {
		return isskill;
	}
	public void setIsskill(boolean isskill) {
		this.isskill = isskill;
	}
	public boolean isIsfight() {
		return isfight;
	}
	public void setIsfight(boolean isfight) {
		this.isfight = isfight;
	}
	
	public int getPlay() {
		return play;
	}

	public void setPlay(int play) {
		this.play = play;
	}

	public int getBan() {
		return ban;
	}

	public void setBan(int ban) {
		this.ban = ban;
	}

	public int getPick() {
		return pick;
	}

	public void setPick(int pick) {
		this.pick = pick;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public double getBanrate() {
		return banrate;
	}

	public void setBanrate(double banrate) {
		this.banrate = banrate;
	}

	public double getPickrate() {
		return pickrate;
	}

	public void setPickrate(double pickrate) {
		this.pickrate = pickrate;
	}
	public double getWinrate() {
		return winrate;
	}

	public void setWinrate(double winrate) {
		this.winrate = winrate;
	}
	
	public double getD() {
		return d;
	}
	public void setD(double d) {
		this.d = d;
	}
	public double getAdr() {
		return adr;
	}
	public void setAdr(double adr) {
		this.adr = adr;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	/*
	 * Heroes�� ���췽��
	 */
	
	public Hero() {
	}
	public Hero(int id, String name, String ename, int atk, int def, int adf, int hp,
			int mp, int remp, int move) {
		super();
		this.id = id;
		this.name = name;
		this.ename = ename;
		this.hp = hp;
		this.mp = mp;
		this.atk = atk;
		this.def = def;
		this.adf = adf;
		this.xdl = move;
		this.mpp = remp;
	}
	
	public void SwapHeroProperty(Hero hero) {
		this.id = hero.id;
		this.name = hero.name;
		this.ename = hero.ename;
		this.hp = hero.hp;
		this.mp = hero.mp;
		this.atk = hero.atk;
		this.def = hero.def;
		this.adf = hero.adf;
		this.xdl = hero.xdl;
		this.mpp = hero.mpp;
	}
	
	public String getSkill(){
		if(getQ()!=null&&getW()!=null&&getE()!=null&&getR()!=null) {
			return "<br /><br />��ʹ�õļ��ܣ�"+"<br />��Q��"+getQ().getName()+"����W��"+getW().getName()+"����E��"+getE().getName()+
					"����R��"+getR().getName()+"��</html>";
		} else if(getQ()!=null&&getW()!=null&&getE()!=null&&getR()==null) {
			return "<br /><br />��ʹ�õļ��ܣ�"+"<br />��Q��"+getQ().getName()+"����W��"+getW().getName()+"����E��"+getE().getName()+"��</html>";
		} else if(getQ()!=null&&getW()!=null&&getE()==null&&getR()==null) {
			return "<br /><br />��ʹ�õļ��ܣ�"+"<br />��Q��"+getQ().getName()+"����W��"+getW().getName()+"��</html>";
		} else if(getQ()!=null&&getW()==null&&getE()==null&&getR()==null) {
			return "<br /><br />��ʹ�õļ��ܣ�"+"<br />��Q��"+getQ().getName()+"��</html>";
		} else {
			return "";
		}
	}
	
	public String getDescribe() {
		return "<html>"+this.getName()+"��"+this.getEname()+"��<br /><br />����ֵ��"+this.getHp()+"<br />ħ��ֵ��"+this.getMp()+"<br /><br />��������"+
				this.getAtk()+"<br />�����ף�"+this.getDef()+"<br />ħ��������"+this.getAdf()+"<br />ÿ�غ�ħ���ظ���"+this.getMpp()+
				"<br />�ж�����"+this.getXdl()+this.getSkill();
	}
	
	public String getProperty() {
		if(getZ()!=null&&getX()!=null) {
			return "<html>"+this.getName()+"��"+this.getEname()+"��<br /><br />��������"+
					this.getAtk()+"<br />�����ף�"+this.getDef()+"<br />ħ��������"+this.getAdf()+"<br />ÿ�غ�ħ���ظ���"+this.getMpp()+
					"<br />�ж�����"+this.getXdl()+"<br /><br />��װ����"+this.getZ().getName()+"��<br />��װ����"+this.getX().getName()+"��";
		} else if(getZ()!=null&&getX()==null) {
			return "<html>"+this.getName()+"��"+this.getEname()+"��<br /><br />��������"+
					this.getAtk()+"<br />�����ף�"+this.getDef()+"<br />ħ��������"+this.getAdf()+"<br />ÿ�غ�ħ���ظ���"+this.getMpp()+
					"<br />�ж�����"+this.getXdl()+"<br /><br />��װ����"+this.getZ().getName()+"��";
		} else if(getZ()==null&&getX()!=null) {
			return "<html>"+this.getName()+"��"+this.getEname()+"��<br /><br />��������"+
					this.getAtk()+"<br />�����ף�"+this.getDef()+"<br />ħ��������"+this.getAdf()+"<br />ÿ�غ�ħ���ظ���"+this.getMpp()+
					"<br />�ж�����"+this.getXdl()+"<br /><br />��װ����"+this.getX().getName()+"��";
		} else {
			return "<html>"+this.getName()+"��"+this.getEname()+"��<br /><br />��������"+
					this.getAtk()+"<br />�����ף�"+this.getDef()+"<br />ħ��������"+this.getAdf()+"<br />ÿ�غ�ħ���ظ���"+this.getMpp()+
					"<br />�ж�����"+this.getXdl()+"<br /><br />��Ӣ������װ��";
		}
	}
	
}
