package pacMan;

import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.imageio.ImageIO;

public class Pinky extends ElfBase{
	public void Move(int tx, int ty,ElfBase pac) throws IOException {
		if(this.state) {
			tx = pac.getX() / 22;
			ty = pac.getY() / 22;	
		}
		tx+=2;
		ty+=2;
		int i=0,j=0,k=0,temp;
		temp=dir;
		dir=0;
		if(state || !state)
		{
			if(x==tx) {
				if(y<ty) {
					for(i=y;i<ty;i++){if(map.avaliable(x,i)){break;}}
					if(i==ty){dir=2;}
					else if(map.avaliable(x,y+1)&&nextdir!=2){dir=2;}
					else {
						for(i=x+1;map.avaliable(i,y);i++){++j;if(map.avaliable(i,y+1)){break;}}
						for(i=x-1;map.avaliable(i,y);i--){++k;if(map.avaliable(i,y+1)){break;}}
						if(j==0&&k==0){dir=1;}
						else if(j==0){dir=3;}
						else if(k==0){dir=4;}
						else if(j>k){if(nextdir==3||temp==4){dir=4;}else{dir=3;}}
						else if(j<k){if(nextdir==4||temp==3){dir=3;}else{dir=4;}}
						else if(j==k){if(nextdir==3||temp==4){dir=4;}else{dir=3;}}
					}
				}
				else {
					for(i=ty;i<y;i++){if(map.avaliable(x,i)){break;}}
					if(i==y){dir=1;}
					else if(map.avaliable(x,y-1)&&nextdir!=1){dir=1;}
					else {
						for(i=x+1;map.avaliable(i,y);i++){++j;if(map.avaliable(i,y-1)){break;}}
						for(i=x-1;map.avaliable(i,y);i++){++k;if(map.avaliable(i,y-1)){break;}}
						if(j==0&&k==0){dir=2;}
						else if(j==0){dir=3;}
						else if(k==0){dir=4;}
						else if(j>k){if(nextdir==3||temp==4){dir=4;}else{dir=3;}}
						else if(j<k){if(nextdir==4||temp==3){dir=3;}else{dir=4;}}
						else if(j==k){if(nextdir==4||temp==3){dir=3;}else{dir=4;}}
					}
				}
			}
			else if(y==ty) {
				if(tx>x)
				{
					for(i=x;i<tx;++i){if(map.avaliable(i,y)){break;}}
					if(i==tx){dir=4;}
					else if(map.avaliable(x+1,y)&&nextdir!=4){dir=4;}
					else {
						for(i=y+1;map.avaliable(x,i);i++){++j;if(map.avaliable(x+1,i)){break;}}
						for(i=y-1;map.avaliable(x,i);i--){++k;if(map.avaliable(x+1,i)){break;}}
						if(j==0&&k==0){dir=3;}
						else if(j==0){dir=1;}
						else if(k==0){dir=2;}
						else if(j>k){if(nextdir==1||temp==2){dir=2;}else{dir=1;}}
						else if(j<k){if(nextdir==2||temp==1){dir=1;}else{dir=2;}}
						else if(j==k){if(nextdir==2||temp==1){dir=1;}else{dir=2;}}
					}
				}
				else
				{
					for(i=tx;i<x;++i){if(map.avaliable(i,y)){break;}}
					if(i==x){dir=3;}
					else if(map.avaliable(x-1,y)&&nextdir!=3){dir=3;}
					else {
						for(i=y+1;map.avaliable(x,i);i++){++j;if(map.avaliable(x-1,i)){break;}}
						for(i=y-1;map.avaliable(x,i);i--){++k;if(map.avaliable(x-1,i)){break;}}
						if(j==0&&k==0){dir=4;}
						else if(j==0){dir=1;}
						else if(k==0){dir=2;}
						else if(j>k){if(nextdir==1||temp==2){dir=2;}else{dir=1;}}
						else if(j<k){if(nextdir==2||temp==1){dir=1;}else{dir=2;}}
						else if(j==k){if(nextdir==1||temp==2){dir=2;}else{dir=1;}}
					}
				}
			}
			else if(x<tx&&y<ty) {
				if(tx-x >= ty-y)
				{
					if(nextdir!=4&&map.avaliable(x+1,y)){dir=4;}
					else if(nextdir!=2&&map.avaliable(x,y+1)){dir=2;}
					else
					{
						for(i=y-1;map.avaliable(x,i);i--){++j;if(map.avaliable(x+1,i)){break;}}
						for(i=x-1;map.avaliable(i,y);i--){++k;if(map.avaliable(i,y+1)){break;}}
						if(j==0){dir=3;}
						else if(k==0){dir=1;}
						else if(j>k){if(nextdir==3||temp==1){dir=1;}else{dir=3;}}
						else if(j<k){if(nextdir==1||temp==3){dir=3;}else{dir=1;}}
						else if(j==k){if(nextdir==3||temp==1){dir=1;}else{dir=3;}}
					}
				}
				else {
					if(nextdir!=2&&map.avaliable(x,y+1)){dir=2;}
					else if(nextdir!=4&&map.avaliable(x+1,y)){dir=4;}
					else
					{
						for(i=y-1;map.avaliable(x,i);i--){++j;if(map.avaliable(x+1,i)){break;}}
						for(i=x-1;map.avaliable(i,y);i--){++k;if(map.avaliable(i,y+1)){break;}}
						if(j==0){dir=3;}
						else if(k==0){dir=1;}
						else if(j>k){if(nextdir==3||temp==1){dir=1;}else{dir=3;}}
						else if(j<k){if(nextdir==1||temp==3){dir=3;}else{dir=1;}}
						else if(j==k){if(nextdir==1||temp==3){dir=3;}else{dir=1;}}
					}
				}
			}//人在鬼右下
			else if(x<tx&&y>ty) {
				if(tx-x >= y-ty)
				{
					if(nextdir!=4&&map.avaliable(x+1,y)){dir=4;}
					else if(nextdir!=1&&map.avaliable(x,y-1)){dir=1;}
					else
					{
						for(i=y+1;map.avaliable(x,i);i++){++j;if(map.avaliable(x+1,i)){break;}}
						for(i=x-1;map.avaliable(i,y);i--){++k;if(map.avaliable(i,y-1)){break;}}
						if(j==0){dir=3;}
						else if(k==0){dir=2;}
						else if(j>k){if(nextdir==3||temp==2){dir=2;}else{dir=3;}}
						else if(j<k){if(nextdir==2||temp==3){dir=3;}else{dir=2;}}
						else if(j==k){if(nextdir==2||temp==3){dir=3;}else{dir=2;}}
					}
				}
				else {
					if(nextdir!=1&&map.avaliable(x,y-1)){dir=1;}
					else if(nextdir!=4&&map.avaliable(x+1,y)){dir=4;}
					else
					{
						for(i=y+1;map.avaliable(x,i);i++){++j;if(map.avaliable(x+1,i)){break;}}
						for(i=x-1;map.avaliable(i,y);i--){++k;if(map.avaliable(i,y-1)){break;}}
						if(j==0){dir=3;}
						else if(k==0){dir=2;}
						else if(j>k){if(nextdir==3||temp==2){dir=2;}else{dir=3;}}
						else if(j<k){if(nextdir==2||temp==3){dir=3;}else{dir=2;}}
						else if(j==k){if(nextdir==3||temp==2){dir=2;}else{dir=3;}}
					}
				}
			}//人在鬼右上
			else if(x>tx&&y>ty) {
				if(x-tx >= y-ty)
				{
					if(nextdir!=3&&map.avaliable(x-1,y)){dir=3;}
					else if(nextdir!=1&&map.avaliable(x,y-1)){dir=1;}
					else
					{
						for(i=x+1;map.avaliable(i,y);i++){++j;if(map.avaliable(i,y-1)){break;}}
						for(i=y+1;map.avaliable(x,i);i++){++k;if(map.avaliable(x-1,i)){break;}}
						if(j==0){dir=2;}
						else if(k==0){dir=4;}
						else if(j>k){if(nextdir==2||temp==4){dir=4;}else{dir=2;}}
						else if(j<k){if(nextdir==4||temp==2){dir=2;}else{dir=4;}}
						else if(j==k){if(nextdir==2||temp==4){dir=4;}else{dir=2;}}
					}
				}
				else {
					if(nextdir!=1&&map.avaliable(x,y-1)){dir=1;}
					else if(nextdir!=3&&map.avaliable(x-1,y)){dir=3;}
					else
					{
						for(i=x+1;map.avaliable(i,y);i++){++j;if(map.avaliable(i,y-1)){break;}}
						for(i=y+1;map.avaliable(x,i);i++){++k;if(map.avaliable(x-1,i)){break;}}
						if(j==0){dir=2;}
						else if(k==0){dir=4;}
						else if(j>k){if(nextdir==2||temp==4){dir=4;}else{dir=2;}}
						else if(j<k){if(nextdir==4||temp==2){dir=2;}else{dir=4;}}
						else if(j==k){if(nextdir==4||temp==2){dir=2;}else{dir=4;}}
					}
				}
			}//人在鬼左上
			else if(x>tx&&y<ty) {
				if(x-tx >= ty-y)
				{
					if(nextdir!=3&&map.avaliable(x-1,y)){dir=3;}
					else if(nextdir!=2&&map.avaliable(x,y+1)){dir=2;}
					else
					{
						for(i=y-1;map.avaliable(x,i);i--){++j;if(map.avaliable(x-1,y)){break;}}
						for(i=x+1;map.avaliable(i,y);i++){++k;if(map.avaliable(i,y+1)){break;}}
						if(j==0){dir=4;}
						else if(k==0){dir=1;}
						else if(j>k){if(nextdir==4||temp==1){dir=1;}else{dir=4;}}
						else if(j<k){if(nextdir==1||temp==4){dir=4;}else{dir=1;}}
						else if(j==k){if(nextdir==4||temp==1){dir=1;}else{dir=4;}}
					}
				}
				else {
					if(nextdir!=2&&map.avaliable(x,y+1)){dir=2;}
					else if(nextdir!=3&&map.avaliable(x-1,y)){dir=3;}
					else
					{
						for(i=y-1;map.avaliable(x,i);i--){++j;if(map.avaliable(x-1,y)){break;}}
						for(i=x+1;map.avaliable(i,y);i++){++k;if(map.avaliable(i,y+1)){break;}}
						if(j==0){dir=4;}
						else if(k==0){dir=1;}
						else if(j>k){if(nextdir==4||temp==1){dir=1;}else{dir=4;}}
						else if(j<k){if(nextdir==1||temp==4){dir=4;}else{dir=1;}}
						else if(j==k){if(nextdir==4||temp==1){dir=1;}else{dir=4;}}
					}
				}
			}//人在鬼左下
			switch(dir) {
				case 1:
					if(map.avaliable(x,y-1))
					{
						Image img1=ImageIO.read(new File("src/image/ghost/PINK_U.png"));
						Image img2=ImageIO.read(new File("src/image/ghost/PINK_U_moving.png"));
						Thread thread = new Thread(new Anime(this,1,img1,img2));
						thread.start();
						try{thread.join();}
						catch (InterruptedException e) {
							e.printStackTrace();
							}
					}
					break;
				case 2:
					if(map.avaliable(x,y+1))
					{
						Image img1=ImageIO.read(new File("src/image/ghost/PINK_D.png"));
						Image img2=ImageIO.read(new File("src/image/ghost/PINK_D_moving.png"));
						Thread thread = new Thread(new Anime(this,2,img1,img2));
						thread.start();
						try{thread.join();}
						catch (InterruptedException e) {
							e.printStackTrace();
							}
					}
					break;
				case 3:
					if(map.avaliable(x-1,y))
					{
						Image img1=ImageIO.read(new File("src/image/ghost/PINK_L.png"));
						Image img2=ImageIO.read(new File("src/image/ghost/PINK_L_moving.png"));
						Thread thread = new Thread(new Anime(this,3,img1,img2));
						thread.start();
						try{thread.join();}
						catch (InterruptedException e) {
							e.printStackTrace();
							}
					}
					break;
				case 4:
					if(map.avaliable(x+1,y))
					{
						Image img1=ImageIO.read(new File("src/image/ghost/PINK_R.png"));
						Image img2=ImageIO.read(new File("src/image/ghost/PINK_R_moving.png"));
						Thread thread = new Thread(new Anime(this,4,img1,img2));
						thread.start();
						try{thread.join();}
						catch (InterruptedException e) {
							e.printStackTrace();
							}
					}
					break;
			}
		}
		else {
	        switch(pac.dir){
	        case 1:if(map.avaliable(x,y-1)){dir=1;}else if(map.avaliable(x-1,y)){dir=3;}else if(map.avaliable(x+1,y)){dir=4;} break;
	        case 2:if(map.avaliable(x,y+1)){dir=2;}else if(map.avaliable(x-1,y)){dir=3;}else if(map.avaliable(x+1,y)){dir=4;} break;
	        case 3:if(map.avaliable(x-1,y)){dir=3;}else if(map.avaliable(x,y-1)){dir=1;}else if(map.avaliable(x,y+1)){dir=2;} break;
	        case 4:if(map.avaliable(x+1,y)){dir=4;}else if(map.avaliable(x,y+1)){dir=2;}else if(map.avaliable(x,y-1)){dir=1;} break;
	        }
			switch(dir) 
			{
			case 1:
				if(map.avaliable(x,y-1))
				{
					Image img1=ImageIO.read(new File("src/image/ghost/PINK_U.png"));
					Image img2=ImageIO.read(new File("src/image/ghost/PINK_U_moving.png"));
					Thread thread = new Thread(new Anime(this,1,img1,img2));
					thread.start();
					try{thread.join();}
					catch (InterruptedException e) {
						e.printStackTrace();
						}
					nextdir=0;
				}
				break;
			case 2:
				if(map.avaliable(x,y+1))
				{
					Image img1=ImageIO.read(new File("src/image/ghost/PINK_D.png"));
					Image img2=ImageIO.read(new File("src/image/ghost/PINK_D_moving.png"));
					Thread thread = new Thread(new Anime(this,2,img1,img2));
					thread.start();
					try{thread.join();}
					catch (InterruptedException e) {
						e.printStackTrace();
						}
					nextdir=0;
				}
				break;
			case 3:
				if(map.avaliable(x-1,y))
				{
					Image img1=ImageIO.read(new File("src/image/ghost/PINK_L.png"));
					Image img2=ImageIO.read(new File("src/image/ghost/PINK_L_moving.png"));
					Thread thread = new Thread(new Anime(this,3,img1,img2));
					thread.start();
					try{thread.join();}
					catch (InterruptedException e) {
						e.printStackTrace();
						}
					nextdir=0;
				}
				break;
			case 4:
				if(map.avaliable(x+1,y))
				{
					Image img1=ImageIO.read(new File("src/image/ghost/PINK_R.png"));
					Image img2=ImageIO.read(new File("src/image/ghost/PINK_R_moving.png"));
					Thread thread = new Thread(new Anime(this,4,img1,img2));
					thread.start();
					try{thread.join();}
					catch (InterruptedException e) {
						e.printStackTrace();
						}
					nextdir=0;
				}
				break;
		}//跑
		}
	}
	public Pinky(int x , int y)throws IOException, InterruptedException
	{
		super(x,y,"src/image/ghost/Pink_U.png");
	}

}
