package pacMan;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.management.Query;

public class BlueElf extends ElfBase {

	private HashMap<String, Integer> G_Value = new HashMap<String, Integer>();
	private HashMap<String, Integer> F_Value = new HashMap<String, Integer>();
	private HashMap<String, Integer> H_Value = new HashMap<String, Integer>();
	
	protected Image D;
	protected Image D_M;
	
	protected Image U;
	protected Image U_M;
	
	protected Image L;
	protected Image L_M;
	
	protected Image R;
	protected Image R_M;
	
	// construction for inheritance
	public BlueElf(int startx, int starty, String filename) throws IOException, InterruptedException{
		super(startx, starty, filename);
	}
	public BlueElf(int startx, int starty) throws IOException, InterruptedException {
		
		super(startx, starty, "src/image/ghost/Blue_U.png");
		this.D = ImageIO.read(new File("src/Image/ghost/Blue_D.png"));
		this.D_M = ImageIO.read(new File("src/Image/ghost/Blue_D_moving.png"));
		
		this.U = ImageIO.read(new File("src/image/ghost/Blue_U.png"));
		this.U_M = ImageIO.read(new File("src/image/ghost/Blue_U_moving.png"));
		
		this.L = ImageIO.read(new File("src/image/ghost/Blue_L.png"));
		this.L_M = ImageIO.read(new File("src/image/ghost/Blue_L_moving.png"));

		this.R = ImageIO.read(new File("src/image/ghost/Blue_R.png"));
		this.R_M = ImageIO.read(new File("src/image/ghost/Blue_R_moving.png"));

	
		
		// TODO Auto-generated constructor stub
		/*
		H_Value = new HashMap<String, Integer>();
		F_Value = new HashMap<String, Integer>();
		G_Value = new HashMap<String, Integer>();
		*/
	}
	
	private int ComputeF(int x, int y, int targetx, int targety, String last) {
		String s = Integer.toString(x) + "-" + Integer.toString(y);
		
		int g = 1 + G_Value.get(last);
		double h = Math.sqrt(Math.pow((x - targetx), 2) + Math.pow((y - targety), 2));
		int f = (g + (int)h);
		G_Value.put(s, g);
		H_Value.put(s, (int)h);
		F_Value.put(s, f);
		return f;
	}
	
	
	public ArrayList<String> A_Star(int x, int y) {
		ArrayList<String> q = new ArrayList<String>();
		ArrayList<String> close = new ArrayList<String>();
		ArrayList<String> path = new ArrayList<String>();
		HashMap<String, String> record = new HashMap<String, String>();
		int srcx = this.getX();
		int srcy = this.getY();
		String target = Integer.toString(x) + "-" + Integer.toString(y);
		String s = new String(Integer.toString(this.getX() / Constant.SCALE) + "-" + Integer.toString(this.getY() / Constant.SCALE));
		G_Value.put(s, 0);
		H_Value.put(s, 0);
		F_Value.put(s, 0);
		q.add(s);
		int miniF;
		int tempF;
		int removeIndex = -1;
		int fatherX;
		int fatherY;
		String temp;
		Scanner scanner = new Scanner(System.in);
		while(q.size() != 0) {
			miniF = 10000;
			
			// pick one direction which has minimum f value
			// System.out.println(q);
			for(int i = 0; i < q.size(); ++i) {
				if(F_Value.get(q.get(i)) < miniF) {
					s = q.get(i);
					miniF = F_Value.get(q.get(i));
					removeIndex = i;
				}
			}
			if(removeIndex != -1) {
				q.remove(removeIndex);
				close.add(s);
			}
			path.add(s);
			
			if(s.equals(target)) {
				/*
				System.out.println(path);
				for(String t : path) {
					System.out.printf("%s, ",record.get(t));
				}
				System.out.println();
				*/
				ArrayList<String> exactPath = new ArrayList<String>();
				while(target != null) {
					
					exactPath.add(0, target);
					target = record.get(target);
				}
				// System.out.println(exactPath);
				return exactPath;
			}
			
			// add 4 directions
			// System.out.println(s);
			fatherX = Integer.parseInt(s.split("-")[0]);
			fatherY = Integer.parseInt(s.split("-")[1]);
			if(this.map.avaliable(fatherX - 1, fatherY)) {
				temp = Integer.toString(fatherX - 1) + "-" + Integer.toString(fatherY);
				if(! q.contains(temp) && !close.contains(temp)) {
					q.add(temp);
					record.put(temp, s);
					// System.out.print("push ");
					// System.out.println(temp);

					this.ComputeF(fatherX - 1, fatherY, x, y, s);
				}
			}
			
			if(this.map.avaliable(fatherX + 1, fatherY)) {
				temp = Integer.toString(fatherX + 1) + "-" + Integer.toString(fatherY);
				if(! q.contains(temp) && !close.contains(temp)) {
					q.add(temp);
					record.put(temp, s);
					// System.out.print("push ");
					// System.out.println(temp);
					this.ComputeF(fatherX + 1, fatherY, x, y, s);
				}
			}
			
			if(this.map.avaliable(fatherX, fatherY - 1)) {
				temp = Integer.toString(fatherX) + "-" + Integer.toString(fatherY - 1);
				if(! q.contains(temp) && !close.contains(temp)) {
					q.add(temp);
					record.put(temp, s);
					// System.out.print("push ");
					// System.out.println(temp);
					this.ComputeF(fatherX, fatherY - 1, x, y, s);
				}
			}
			
			if(this.map.avaliable(fatherX, fatherY + 1)) {
				temp = Integer.toString(fatherX) + "-" + Integer.toString(fatherY + 1);
				if(! q.contains(temp) && !close.contains(temp)){
					q.add(temp);
					record.put(temp, s);
					// System.out.print("push ");
					// System.out.println(temp);
					this.ComputeF(fatherX, fatherY + 1, x, y, s);
				}
			}
			// System.out.print("F_Value : ");
			// System.out.println(F_Value);
			// scanner.next();
		}
		// System.out.println("end!!!");
		
		return path;
	}
	
	public void Animation(int currentX, int currentY, int nextX, int nextY) {
		int dirX, dirY;
		Image img1 = null;
		Image img2 = null;
		dirX = nextX - currentX;
		dirY = nextY - currentY;
		if(dirX == 0 && dirY < 0) { // up
			img1 = this.U;
			img2 = this.U_M;
			dir = 1;
		}else if(dirX == 0 && dirY > 0) { // down
			img1 = this.D;
			img2 = this.D_M;
			dir = 2;
		}else if(dirX < 0 && dirY == 0) { // left
			img1 = this.L;
			img2 = this.L_M;
			dir = 3;
		}else if(dirX > 0 && dirY == 0) { // right
			img1 = this.R;
			img2 = this.R_M;
			dir = 4;
		}
		Thread thread = new Thread(new Anime(this,dir,img1,img2));
		thread.start();
		try{
			thread.join();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Move(int x, int y, ElfBase pac) throws IOException{
		// TODO Auto-generated method stub
		ArrayList<String> path;
		if(this.state) {
			x = pac.getX() / 22;
			y = pac.getY() / 22;
		}
		
		int currentX = this.getX() / 22;
		int currentY = this.getY() / 22;
		int dirX, dirY;
		int dir = 0;
		Image img1 = null;
		Image img2 = null;
		
		if(currentX != x || currentY != y) {
			
			path = this.A_Star(x, y);
			if(path.size() > 1) {
				String nextPos = path.get(1);
				int nextX = Integer.parseInt(nextPos.split("-")[0]);
				int nextY = Integer.parseInt(nextPos.split("-")[1]);
				this.Animation(currentX, currentY, nextX, nextY);
			}
			
		}
	}
	/*
	public void MoveRight(int unit) {
		this.setLocation(this.getX() + unit*Constant.SCALE, this.getY());
	}
	
	public void MoveLeft(int unit) {
		this.setLocation(this.getX() - unit*Constant.SCALE, this.getY());
	}
	
	public void MoveUp(int unit) {
		this.setLocation(this.getX(), this.getY() - unit*Constant.SCALE);
	}
	
	public void MoveDown(int unit) {
		this.setLocation(this.getX(), this.getY() + unit * Constant.SCALE);
	}
	*/ 
}
