package entity;

import util.Config;

public class Hero {
	private int id;//数据库中对应的ID
	private String name,ename;//名字和代号
	private int hp=0,mp=0;//生命值，魔法值
	private int atk=0,def=0,adf=0,xdl=0,hpp=0,mpp=0;//攻击，防御，魔抗，行动力，生命恢复速度，魔法恢复速度
	private double adp=0,app=0;//物理穿透，魔法穿透
	private double defrate=0;//物理伤害减免
	private User user;//所属玩家
	private PlayerIcon icon;//英雄头像
	private Skill Q,W,E,R;//技能组
	private Item Z,X;//装备组
	private int play,ban,pick,win;//游戏场次，B/P次数，胜场
	private double banrate,pickrate,winrate;//B/P率，胜率
	private int time;//平均时间
	private double d,adr;//平均回合伤害，平均总伤害
	/*
	 * - 英雄状态判断
	 */
	private boolean isgone=true;//false=完全行动不能
	private boolean islimte=true;//false=行动受限
	private boolean isatk=true;//false=攻击不能
	private boolean isskill=true;//false=施法不能
	private boolean isfight=true;//false=战斗不能
	/*
	 * - 结晶之力 激活判断
	 */
	public int damage=0;//累计造成伤害
	public int jhjj=0;//激活结晶之力的分支
	public boolean jjzl=false;//结晶之力激活状态
	/*
	 * - 英雄技能 生效判断
	 */
	public int yyQ=0;//奕阳Q
	public int yyE=0;//奕阳E的伤害
	public int yyER=0;//奕阳E的回合
	public int lxsE=3;//刘晓释E
	public boolean ysnQ=false;//杨圣诺Q
	public int ysnW=0;//杨圣诺W
	public boolean zfW=false;//张枫W
	public int zkxQ=0;//张可汐Q的伤害
	public int zkxQC=0;//张可汐Q的伤害次数
	public int zkxW=0;//张可汐W
	public int zkxWH=0;//张可汐W累计承受
	public int zkxE=0;//张可汐E2的伤害
	public int hyqQ=0;//郈与却Q
	public int hyqW=0;//郈与却W
	public double hyqJ=0;//郈与却Q伤害增强
	public int ltjW=0;//罗天杰W
	public int xyh=0;//谢悠涵Q的洁净点
	public boolean xyhQ=false;//谢悠涵Q生效
	public int xyhQHPP=0;//谢悠涵Q的HPP
	public int xyhE=0;//谢悠涵E
	public int xyhED=0;//谢悠涵E期间累计伤害
	public boolean zxyQ=false;//郑心予Q
	public int zxyE=0;//郑心予E
	public boolean zxyEE=false;//郑心予E不回蓝
	public int zxyR=0;//郑心予R
	public boolean lmQ=false;//刘珂明Q
	public boolean lmW=false;//刘珂明W
	public int sjjQ=0;//苏璟静Q
	public boolean sjjW=false;//苏璟静W
	public boolean sjjE=false;//苏璟静E
	public int sjjR=0;//苏璟静R
	public int ww=-1;//维多利娜W
	/*
	 * - 英雄装备 生效判断
	 */
	public boolean zy=false;//紫月神杖
	public boolean hy=false;//红月神杖
	public boolean yjg=false;//鹰角弓
	public boolean pjzm=false;//破军之矛
	public boolean ispjzm=false;//破军之矛不回蓝
	public int pjzmcd=0;//破军之矛CD
	public boolean jrz=false;//坚韧者之盾
	public boolean yyzs=false;//夜宴之声
	public boolean xsh=false;//学生会会徽
	public boolean fs=false;//复苏
	/*
	 * - 英雄图标 初始化
	 */
	public BuffIcon lrzj = new BuffIcon("烈日之箭", 3, Config.s.mdfdown, "该英雄会持续受到来自灼烧的魔法伤害。");
	public BuffIcon tszf = new BuffIcon("屠杀之风", 4, Config.s.appup, "屠杀之风会为该英雄提供魔法伤害加成。");
	public BuffIcon jfzm = new BuffIcon("解放真名", -1, Config.s.atkup, "每层解放真名都会为该英雄提升2点攻击力和1点护甲。");
	public BuffIcon userxxcc = new BuffIcon("新星冲刺", 1, Config.s.defup, "该英雄已经窃取了对方的物理护甲。");
	public BuffIcon enemyxxcc = new BuffIcon("新星冲刺", 1, Config.s.defdown, "该英雄已经被对方窃取了物理护甲。");
	public BuffIcon xcyl = new BuffIcon("星辰陨落", 2, Config.s.mdfdown, "该英雄的魔法防御减低了2点。");
	public BuffIcon fzjj1 = new BuffIcon("风之结界", 1, Config.s.allunable, "该英雄完全行动不能。");
	public BuffIcon fzjj2 = new BuffIcon("风之结界", 1, Config.s.limited, "该英雄行动受限。");
	public BuffIcon sx = new BuffIcon("闪现", 3, Config.s.evade, "该英雄具有60%闪避普通攻击的概率。");
	public BuffIcon sxplus = new BuffIcon("闪现+", 2, Config.s.evade, "该英雄具有80%闪避普通攻击的概率。");
	public BuffIcon sxmk = new BuffIcon("闪现+", 2, Config.s.mdfup, "该英雄的魔法防御提升了2点。");
	public BuffIcon xrwz = new BuffIcon("先入为主", 3, Config.s.appup, "该英雄的魔法穿透提升了。");
	public BuffIcon qlbx = new BuffIcon("强力剥削", 3, Config.s.defdown, "该英雄的物理护甲降低了4点。");
	public BuffIcon yxzd = new BuffIcon("云霄之巅", -1, Config.s.atkup, "该英雄的攻击力提升了4点。");
	public BuffIcon tydf = new BuffIcon("天圆地方", 1, Config.s.atkunable, "该英雄战斗不能。");
	public BuffIcon sgsl = new BuffIcon("时光沙漏", 3, Config.s.magicimmunity, "该英雄完全免疫任何伤害。");
	public BuffIcon bxsz = new BuffIcon("冰雪十字", 1, Config.s.allunable, "该英雄完全行动不能。");
	public BuffIcon bzyy = new BuffIcon("冰之羽翼", 3, Config.s.defup, "该英雄完全免疫物理伤害。");
	public BuffIcon xzjz = new BuffIcon("汐之抉择", -1, Config.s.appup, "每层汐之抉择都会为该英雄提供2点魔法伤害加成。");
	public BuffIcon xzjzsh = new BuffIcon("汐之抉择", -1, Config.s.appup, "汐之抉择的效果一为冰雪十字提供了4点魔法伤害加成。");
	public BuffIcon xzjzmk = new BuffIcon("汐之抉择", -1, Config.s.mdfup, "该英雄的魔法防御提升了4点。");
	public BuffIcon xzjzwk = new BuffIcon("汐之抉择", -1, Config.s.defup, "该英雄的物理护甲提升了4点。");
	public BuffIcon lz = new BuffIcon("礼赞", -1, Config.s.appup, "该英雄下一次造成魔法伤害时会减少对方相当于其最大魔法值42%的魔法值。");
	public BuffIcon userylzh = new BuffIcon("予恋之花", 2, Config.s.defup, "该英雄能够减免其受到的80%物理伤害。");
	public BuffIcon enemyylzh = new BuffIcon("予恋之花", 2, Config.s.magicunable, "该英雄施法不能。");
	public BuffIcon xysy = new BuffIcon("心源神域", 2, Config.s.hpadd, "该英雄能在回合开始时回复生命值。");
	public BuffIcon lf = new BuffIcon("裂缝", 1, Config.s.atkunable, "该英雄战斗不能。");
	public BuffIcon lfatk = new BuffIcon("裂缝", 1, Config.s.atkup, "该英雄的攻击力提升了3点。");
	public BuffIcon gcj = new BuffIcon("光炽剑", -1, Config.s.adpup, "该英雄下一次普通攻击造成伤害时会附带魔法伤害。");
	public BuffIcon gzhl = new BuffIcon("公主号令", -1, Config.s.mdfup, "该英雄受到伤害时会有禁卫军为其抵挡至多4点伤害。");
	public BuffIcon sy = new BuffIcon("神谕", 1, Config.s.limited, "该英雄必须遵守神谕，否则就会流失1点物理护甲。");
	public BuffIcon sg1 = new BuffIcon("圣歌", -1, Config.s.atkup, "该英雄的攻击力提升了3点。");
	public BuffIcon sg2 = new BuffIcon("圣歌", -1, Config.s.defup, "该英雄的物理护甲提升了2点。");
	public BuffIcon fsjn = new BuffIcon("复苏", -1, Config.s.magicimmunity, "该英雄将不会受到任何强控制效果的影响。");
	public BuffIcon mpadd = new BuffIcon("魔力填充剂III", -1, Config.s.hpadd, "该英雄将额外获得2点魔法回复。");
	public BuffIcon xdladd = new BuffIcon("行动力胶囊", -1, Config.s.speedup, "该英雄的行动力提升了4点。");
	public BuffIcon mkadd = new BuffIcon("双抗药贴", -1, Config.s.mdfup, "该英雄的魔法防御提升了4点。");
	public BuffIcon hjadd = new BuffIcon("双抗药贴", -1, Config.s.defup, "该英雄的物理护甲提升了4点。");
	public BuffIcon atkadd = new BuffIcon("强化药水", -1, Config.s.atkup, "该英雄的攻击力提升了4点。");
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
	 * Heroes’ 构造方法
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
			return "<br /><br />可使用的技能："+"<br />【Q】"+getQ().getName()+"、【W】"+getW().getName()+"、【E】"+getE().getName()+
					"、【R】"+getR().getName()+"。</html>";
		} else if(getQ()!=null&&getW()!=null&&getE()!=null&&getR()==null) {
			return "<br /><br />可使用的技能："+"<br />【Q】"+getQ().getName()+"、【W】"+getW().getName()+"、【E】"+getE().getName()+"。</html>";
		} else if(getQ()!=null&&getW()!=null&&getE()==null&&getR()==null) {
			return "<br /><br />可使用的技能："+"<br />【Q】"+getQ().getName()+"、【W】"+getW().getName()+"。</html>";
		} else if(getQ()!=null&&getW()==null&&getE()==null&&getR()==null) {
			return "<br /><br />可使用的技能："+"<br />【Q】"+getQ().getName()+"。</html>";
		} else {
			return "";
		}
	}
	
	public String getDescribe() {
		return "<html>"+this.getName()+"（"+this.getEname()+"）<br /><br />生命值："+this.getHp()+"<br />魔法值："+this.getMp()+"<br /><br />攻击力："+
				this.getAtk()+"<br />物理护甲："+this.getDef()+"<br />魔法防御："+this.getAdf()+"<br />每回合魔法回复："+this.getMpp()+
				"<br />行动力："+this.getXdl()+this.getSkill();
	}
	
	public String getProperty() {
		if(getZ()!=null&&getX()!=null) {
			return "<html>"+this.getName()+"（"+this.getEname()+"）<br /><br />攻击力："+
					this.getAtk()+"<br />物理护甲："+this.getDef()+"<br />魔法防御："+this.getAdf()+"<br />每回合魔法回复："+this.getMpp()+
					"<br />行动力："+this.getXdl()+"<br /><br />已装备【"+this.getZ().getName()+"】<br />已装备【"+this.getX().getName()+"】";
		} else if(getZ()!=null&&getX()==null) {
			return "<html>"+this.getName()+"（"+this.getEname()+"）<br /><br />攻击力："+
					this.getAtk()+"<br />物理护甲："+this.getDef()+"<br />魔法防御："+this.getAdf()+"<br />每回合魔法回复："+this.getMpp()+
					"<br />行动力："+this.getXdl()+"<br /><br />已装备【"+this.getZ().getName()+"】";
		} else if(getZ()==null&&getX()!=null) {
			return "<html>"+this.getName()+"（"+this.getEname()+"）<br /><br />攻击力："+
					this.getAtk()+"<br />物理护甲："+this.getDef()+"<br />魔法防御："+this.getAdf()+"<br />每回合魔法回复："+this.getMpp()+
					"<br />行动力："+this.getXdl()+"<br /><br />已装备【"+this.getX().getName()+"】";
		} else {
			return "<html>"+this.getName()+"（"+this.getEname()+"）<br /><br />攻击力："+
					this.getAtk()+"<br />物理护甲："+this.getDef()+"<br />魔法防御："+this.getAdf()+"<br />每回合魔法回复："+this.getMpp()+
					"<br />行动力："+this.getXdl()+"<br /><br />该英雄暂无装备";
		}
	}
	
}
