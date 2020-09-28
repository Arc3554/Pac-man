package pacMan;

//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Timer;

import org.json.JSONArray;
import org.json.JSONObject;


//import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Map extends JFrame{
	
	public ArrayList<ArrayList<Dot> >Dots; // should be private but use public is more efficient lol
//	private ElfBase elftesting;
	public ElfBase elf1,elf2,pac, elf3, elf4;
	private Pacman pacman;
	public Condition showCondition;
	private Timer timer1;
	private Timer timer2;
	private Timer timer3;
	private Timer timer4;
	private Timer collisionTimer1;
	private Timer collisionTimer2;
	private Timer collisionTimer3;
	private Timer collisionTimer4;
	private Timer pacmanTimer;
	private Timer chasingTimer;
	private Timer checkWinTimer;
//	private ElfMovingFire EMF1;
//	private ElfMovingFire EMF2;
//	private ElfMovingFire EMF3;
//	private ElfMovingFire EMF4;
//	private ElfMovingFire pacmanEMF;
	private CollisionDetector CDEf1;
	private CollisionDetector CDEf2;
	private CollisionDetector CDEf3;
	private CollisionDetector CDEf4;
	private WinCondition WC;
	private int score;
	private int dotamount;
	private int eatenDotAmount;
	private int pacmanLife;
	private JLabel gametitle = new JLabel("Pac-Man");
	private JLabel scoreTitle = new JLabel("得分計算");
	private JLabel scoreShow = new JLabel(String.valueOf(this.score));
	private JLabel explainDot = new JLabel("糖豆10分");
	private JLabel remainLife = new JLabel("剩餘生命");
	private JLabel explainLoli = new JLabel("棒棒糖50分");
	private JLabel explainGhost = new JLabel("鬼魂100分");
	private JLabel pacDirectionTitle = new JLabel("方向：");
	private JLabel pacDirection = new JLabel("下");
	public Map() throws IOException, InterruptedException {
		// TODO Auto-generated constructor stub
		// variable initialization
		super();
		/*
		this.timer1 = new Timer();
		this.timer2 = new Timer();
		this.timer3 = new Timer();
		this.timer4 = new Timer();
		this.pacmanTimer = new Timer();
		*/
		this.collisionTimer1 = new Timer();
		this.collisionTimer2 = new Timer();
		this.collisionTimer3 = new Timer();
		this.collisionTimer4 = new Timer();
		this.chasingTimer = new Timer();
		this.checkWinTimer = new Timer();
		
		this.score = 0;
		this.dotamount = 0;
		this.eatenDotAmount = 0;
		
		File gameconfig = new File("src/pacman/gameconfig.json");
		FileInputStream gcf = new FileInputStream(gameconfig);
		byte[] data = new byte[(int) gameconfig.length()];
		gcf.read(data);
		gcf.close();
		String gConfig = new String(data, "UTF-8");
		// System.out.println(gConfig);
		JSONObject gconfig = new JSONObject(gConfig);
		JSONArray pacman_start_pos = new JSONArray(gconfig.get("pacman_start_pos").toString());
		JSONArray big_pellet_pos = new JSONArray(gconfig.get("big_pellet_pos").toString());
		JSONArray elves_pos = new JSONArray(gconfig.get("elf_start_pos").toString());
		JSONArray elfpos;
		this.pacmanLife = Integer.parseInt(gconfig.get("pacman_life").toString());
		// Frame Initialize
		
		this.setSize(565, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setContentPane(new MapBackground());
		
		this.showCondition = new Condition(0, 570);
		this.add(this.showCondition);
		
		gametitle.setBounds(310, 530, 300, 130);
		gametitle.setFont(new java.awt.Font("chalkdust",1,55));
		this.add(gametitle);
		
		scoreTitle.setBounds(145, 530, 100, 100);
		scoreTitle.setFont(new java.awt.Font("宋體",1,15));
		this.add(scoreTitle);
		
		scoreShow.setBounds(165, 550, 100, 100);
		this.add(scoreShow);

		explainDot.setBounds(237, 525, 100, 100);
		this.add(explainDot);
		
		explainLoli.setBounds(225, 545, 100, 100);
		this.add(explainLoli);
		
		explainGhost.setBounds(230, 565, 100, 100);
		this.add(explainGhost);
		
		pacDirection.setBounds(185, 565, 100, 100);
		this.add(pacDirection);
		
		pacDirectionTitle.setBounds(145, 565, 100, 100);
		this.add(pacDirectionTitle);
		
		remainLife.setBounds(61, 530, 100, 100);
		remainLife.setFont(new java.awt.Font("宋體",1,15));
		this.add(remainLife);
		
		// setup elf
		elfpos = new JSONArray(elves_pos.get(0).toString());
		this.elf1 = new Blinky(elfpos.getInt(0)*Constant.SCALE,elfpos.getInt(1)*Constant.SCALE);
		add(this.elf1);
		this.elf1.x=9;
		this.elf1.y=9;
		this.elf1.state=true;
		
		elfpos = new JSONArray(elves_pos.get(1).toString());
		this.elf2 = new Pinky(elfpos.getInt(0)*Constant.SCALE,elfpos.getInt(1)*Constant.SCALE);
		add(elf2);
		elf2.x=18;
		elf2.y=16;
		elf2.state=true;
		
		elfpos = new JSONArray(elves_pos.get(2).toString());
		this.elf3 = new BlueElf(elfpos.getInt(0)*Constant.SCALE,elfpos.getInt(1)*Constant.SCALE);
		add(elf3);
		elf3.state = true;
		
		elfpos = new JSONArray(elves_pos.get(3).toString());
		this.elf4= new YellowElf(elfpos.getInt(0)*Constant.SCALE,elfpos.getInt(1)*Constant.SCALE);
		add(elf4);
		elf4.state = true;
		
		pacman = new Pacman(pacman_start_pos.getInt(0)*Constant.SCALE, pacman_start_pos.getInt(1)*Constant.SCALE);
		add(pacman);
		pacman.state=true;
		/*
		EMF1 = new ElfMovingFire(pacman);
		EMF2 = new ElfMovingFire(pacman);
		EMF3 = new ElfMovingFire(pacman);
		EMF4 = new ElfMovingFire(pacman);
		pacmanEMF = new ElfMovingFire(pacman);
		*/
		CDEf1 = new CollisionDetector(pacman, elf1, this);
		CDEf2 = new CollisionDetector(pacman, elf2, this);
		CDEf3 = new CollisionDetector(pacman, elf3, this);
		CDEf4 = new CollisionDetector(pacman, elf4, this);
		WC = new WinCondition(this);
		
		  
		// Dots initialize
		Dots = new ArrayList<ArrayList<Dot> >();
		ArrayList<Dot> row;
		Dot temp;
		for(int i = 0; i < 570; i += Constant.SCALE) {
			row = new ArrayList<Dot>();
			for(int j = 0; j < 570; j += Constant.SCALE) {
				temp = new Dot(i, j,Constant.DOTDIAMETER);
				row.add(temp);
				add(temp);
				this.dotamount += 1;
			}
			Dots.add(row);
		}
		
		File mazeconfigfile = new File("src/pacMan/mazeconfig.json");
		FileInputStream mzcff = new FileInputStream(mazeconfigfile);
		data = new byte[(int) mazeconfigfile.length()];
		mzcff.read(data);
		mzcff.close();
		String config = new String(data, "UTF-8");
		
		JSONArray jar = new JSONArray(config);
		JSONArray temp1;
		for(int i = 0; i < jar.length(); ++i) {
			temp1 = new JSONArray(jar.get(i).toString());
			Dots.get(temp1.getInt(0)).get(temp1.getInt(1)).setWall();
			Dots.get(25 - temp1.getInt(0)).get(temp1.getInt(1)).setWall();
			this.dotamount -= 2;
		}
	
		for(int i = 0; i < big_pellet_pos.length(); ++i) {
			temp1 = new JSONArray(big_pellet_pos.get(i).toString());
			Dots.get(temp1.getInt(0)).get(temp1.getInt(1)).setToSugar();
		}
		
		setVisible(true);
		/*
		EMF1.addAnElf(elf1);
		EMF2.addAnElf(elf2);
		EMF3.addAnElf(elf3);
		EMF4.addAnElf(elf4);
		pacmanEMF.addAnElf(pacman);
		*/
		

		// System.out.print(this.Dots);
	}
	
	public int getDotAmount() {
		return this.dotamount;
	}
	
	
	public int getEatenDotAmount() {
		return this.eatenDotAmount;
	}
	
	public boolean avaliable(int x, int y) {
		try {
			return Dots.get(x).get(y).isOk();		
		}catch(java.lang.IndexOutOfBoundsException e) {
			return false;
		}
	}

	
	
	public void pacmanWalk(int x, int y) {
		if(pacman.pacDirect() == KeyEvent.VK_DOWN) {
			pacDirection.setText("下");
		}else if(pacman.pacDirect() == KeyEvent.VK_UP) {
			pacDirection.setText("上");
		}else if(pacman.pacDirect() == KeyEvent.VK_LEFT) {
			pacDirection.setText("左");
		}else if(pacman.pacDirect() == KeyEvent.VK_RIGHT) {
			pacDirection.setText("右");
		}
		Dot d = this.Dots.get(x).get(y);
		if(!d.isEaten()) {
			if(d.isSugar()) {
				
				this.elf1.beGhost();
				this.elf2.beGhost();
				this.elf3.beGhost();
				this.elf4.beGhost();
				this.chasingTimer.schedule(new ChaseTimerTask(this.elf1, this.elf2, this.elf3, this.elf4), 2500);
				this.score += 40;
			}
			 d.eaten();
			 this.eatenDotAmount += 1;
			// add the score
			 this.score += 10;
			 if(this.score > 999) {
					scoreShow.setBounds(157, 550, 100, 100);
				}
			 scoreShow.setText(String.valueOf(this.score));
		}
	}
	
	
	public void killGhost() {
		this.score += 200;
		if(this.score > 999) {
			scoreShow.setBounds(157, 550, 100, 100);
		}
		scoreShow.setText(String.valueOf(this.score));
	}
	
	public void pacmanIsKilled() {
		if(pacmanLife >= 1) {
			this.pacmanLife -= 1;
			this.pause(true);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			showCondition.subtractTimes();
			pacman.backToStartPos();
			elf1.backToStartPos();
			elf2.backToStartPos();
			elf3.backToStartPos();
			elf4.backToStartPos();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			showCondition.repaint();
			this.moveStart();
		}else {
			// game over!
//			showCondition.paint(getGraphics());
			System.out.println("Game Over!!!");
			this.collisionTimer1.cancel();
			this.collisionTimer2.cancel();
			this.collisionTimer3.cancel();
			this.collisionTimer4.cancel();
			this.pause(false);
			this.showCondition.subtractTimes();
			this.showCondition.setToFailed();
			showCondition.repaint();
		}
		
	}
	public void pause(boolean restart) {
			timer1.cancel();
			timer2.cancel();
			timer3.cancel();
			timer4.cancel();
			pacmanTimer.cancel();
	/*	
		if(restart) {
			try {
				timer1.wait(500);
				timer2.wait(500);
				timer3.wait(500);
				timer4.wait(500);
				pacmanTimer.wait(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
		
		}
		*/
	}
	
	public void moveStart() {
		timer1 = new Timer();
		timer2 = new Timer();
		timer3 = new Timer();
		timer4 = new Timer();
		pacmanTimer = new Timer();
		timer1.schedule(new ElfMovingFire(this.pacman, this.elf1), 0, 200);
		timer2.schedule(new ElfMovingFire(this.pacman, this.elf2), 0, 200);
		timer3.schedule(new ElfMovingFire(this.pacman, this.elf3), 0, 200);
		timer4.schedule(new ElfMovingFire(this.pacman, this.elf4), 0, 200);
		pacmanTimer.schedule(new ElfMovingFire(this.pacman, this.pacman), 0, 150);
	}
	
	public void play() throws IOException, InterruptedException
	{
		elf1.mapIn(this);
		elf2.mapIn(this);
		elf3.mapIn(this);
		elf4.mapIn(this);
		pacman.mapIn(this);
		
		this.moveStart();
		
		collisionTimer1.schedule(CDEf1, 0, 50);
		collisionTimer2.schedule(CDEf2, 0, 50);
		collisionTimer3.schedule(CDEf3, 0, 50);
		collisionTimer4.schedule(CDEf4, 0, 50);
		checkWinTimer.schedule(WC, 0, 100);
		/*
		while(elf2.x!=16||elf1.y!=14)
		{
			elf1.Move(16,14,pac);//需要pac的資訊
			elf2.Move(1,1,pac);
			elf3.Move(24, 24, pac);
			Thread.sleep(50);
			//吃人 吃點數
		}
		*/
	}
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		Map map = new Map();
		map.play();
		// map.elf3.mapIn(map);
		// map.elf3.Move(5, 4, map.pac);
	}

}