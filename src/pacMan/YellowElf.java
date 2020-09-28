package pacMan;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class YellowElf extends BlueElf {
	
	public YellowElf(int startx, int starty) throws IOException, InterruptedException {
		// TODO Auto-generated constructor stub
		
		super(startx, starty, "src/Image/ghost/Yellow_U.png");
		this.D = ImageIO.read(new File("src/Image/ghost/Yellow_D.png"));
		this.D_M = ImageIO.read(new File("src/Image/ghost/Yellow_D_moving.png"));
		
		this.U = ImageIO.read(new File("src/image/ghost/Yellow_U.png"));
		this.U_M = ImageIO.read(new File("src/image/ghost/Yellow_U_moving.png"));
		
		this.L = ImageIO.read(new File("src/image/ghost/Yellow_L.png"));
		this.L_M = ImageIO.read(new File("src/image/ghost/Yellow_L_moving.png"));

		this.R = ImageIO.read(new File("src/image/ghost/Yellow_R.png"));
		this.R_M = ImageIO.read(new File("src/image/ghost/Yellow_R_moving.png"));
	}
	
	@Override
	public void Move(int x, int y, ElfBase pac) {
		ArrayList<String> path;
		int currentX = this.getX() / 22;
		int currentY = this.getY() / 22;
		if(this.state) {
			x = pac.last2Pos[0];
			y = pac.last2Pos[1];
		}
		
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

}
