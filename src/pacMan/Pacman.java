package pacMan;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.Image;

public class Pacman extends ElfBase implements KeyListener{
	
	private int direction  = KeyEvent.VK_DOWN;
	private int lastDirection = KeyEvent.VK_DOWN;
	
	private ArrayList<ArrayList<Image>> Images = new ArrayList<ArrayList<Image>>();
	
	private ArrayList<Image> up_imgs = new ArrayList<Image>();
	private ArrayList<Image> down_imgs = new ArrayList<Image>();
	private ArrayList<Image> left_imgs = new ArrayList<Image>();
	private ArrayList<Image> right_imgs = new ArrayList<Image>();
	public Pacman(int startx, int starty) throws IOException, InterruptedException {
		super(startx, starty, "src/image/pacman/Pacman_D_1.png");
		this.setFocusable(true);
		this.addKeyListener(this);
        this.setFocusable(true);
        lastPos[0] = startx;
        lastPos[1] = starty;
        last2Pos[0] = startx;
        last2Pos[1] = starty;
        String []sufix = "UDLR".split("");
        Image img;
        ArrayList<Image> imgs = new ArrayList<Image>();
        for(int i = 0; i < sufix.length; ++i) {
        	imgs = new ArrayList<Image>();
        	for(int j = 1; j < 4; ++j) {
        		img = ImageIO.read(new File("src/image/pacman/Pacman_" + sufix[i] + "_" + Integer.toString(j) + ".png"));
        		imgs.add(img);
        	}
        	Images.add(imgs);
        }
	}
	
	public int pacDirect() {
		return(direction);
	}

	public int isNextStepOk(int direction, int [] pos) {
		int type = 0;
		if(direction == KeyEvent.VK_UP && this.map.avaliable(pos[0], pos[1] - 1)) {
			type = 1;
			lastDirection = direction;
		}else if(direction == KeyEvent.VK_DOWN && this.map.avaliable(pos[0], pos[1] + 1)) {
			lastDirection = direction;
			type = 2;
		}else if(direction == KeyEvent.VK_RIGHT && this.map.avaliable(pos[0] + 1, pos[1])) {
			lastDirection = direction;
			type = 4;
		}else if(direction == KeyEvent.VK_LEFT && this.map.avaliable(pos[0] - 1, pos[1])) {
			lastDirection = direction;
			type = 3;
		}else {
			return type;
		}
		return type;
	}


	@Override
	public void Move(int x, int y, ElfBase pac) throws IOException {
		// TODO Auto-generated method stub
		Thread thread;
		int [] pos = new int[2];
		pos[0] = this.getX() / Constant.SCALE;
		pos[1] = this.getY() / Constant.SCALE;
		
		int type = isNextStepOk(direction, pos);
		if(type == 0) { // next step not ok
			// next step is not ok , so pacman use last direction
			type = isNextStepOk(lastDirection , pos);
		}
		
		this.map.pacmanWalk(pos[0], pos[1]);
		if(type == 0) {
			thread = new Thread(new Anime(this, type, Images.get(type)));
		}else {
			last2Pos[0] = lastPos[0];
			last2Pos[1] = lastPos[1];
			lastPos[0] = pos[0];
			lastPos[1] = pos[1];
			thread = new Thread(new Anime(this, type, Images.get(type - 1)));
		}
		
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("key pressed!!");
		this.direction = e.getKeyCode();
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
