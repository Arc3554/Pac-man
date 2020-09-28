package pacMan;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Anime implements Runnable{
	private ElfBase elf;
	private int type;
	public Image img1;
	public Image img2;
	ArrayList<Image> images;
	public void run(){
		int dirx = 0; 
		int diry = 0;
		switch(type) {
		
		case 1: // go up;
			/*
			for(int i = 1;i<23;i++)
			{
				
				try {
					elf.setLocation(elf.x*Constant.SCALE, elf.y*Constant.SCALE-i);
					if(i%2==1) {elf.img=img2;}
					else {elf.img=img1;}
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}*/
			dirx = 0;
			diry = -1;
			// elf.y--;
			elf.nextdir=2;
			break;
		case 2: // go down;
			/*
			for(int i = 1;i<23;i++)
			{
				
				try {
					elf.setLocation(elf.x*Constant.SCALE, elf.y*Constant.SCALE+i);
					if(i%2==1) {elf.img=img2;}
					else {elf.img=img1;}
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}*/
			dirx = 0;
			diry = 1;
			// elf.y++;
			elf.nextdir=1;
			break;
		case 3: // go left
			/*
			for(int i = 1;i<23;i++)
			{
				
				try {
					elf.setLocation(elf.x*Constant.SCALE-i, elf.y*Constant.SCALE);
					if(i%2==1) {elf.img=img2;}
					else {elf.img=img1;}
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}*/
			dirx = -1;
			diry = 0;
			// elf.x--;
			elf.nextdir=4;
			break;
		case 4: // go right;
			/*
			for(int i = 1;i<23;i++){
				
				try {
					elf.setLocation(elf.x*Constant.SCALE+i, elf.y*Constant.SCALE);
					if(i%2==1) {elf.img=img2;}
					else {elf.img=img1;}
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}*/
			dirx = 1;
			diry = 0;
			// elf.x++;
			elf.nextdir=3;
			break;
		}
		// System.out.println(elf);
		
		for(int i = 1; i < 23; ++i) {
			try {
				elf.setLocation(elf.x * Constant.SCALE + i * dirx, elf.y * Constant.SCALE + i * diry);
				if(elf.state) {
					elf.img = images.get(i % images.size());
				}else {
					elf.img = elf.deadimg;
				}
				Thread.sleep(10);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		elf.x += dirx;
		elf.y += diry;
	}
	public Anime(ElfBase n,int type2,Image i1,Image i2) {
		elf=n;
		type=type2;
		img1=i1;
		img2=i2;
		this.images = new ArrayList<Image>();
		this.images.add(img1);
		this.images.add(img2);
	}
	
	public Anime(ElfBase n, int type, ArrayList<Image> images) {
		this.elf = n;
		this.images = images;
		this.type = type;
	}

}
