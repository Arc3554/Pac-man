package pacMan;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Condition extends JComponent {
	Image loseIconImage;
	Image winIconImage;
	Image heart1;
	Image heart2;
	Image heart3;
	int times;
	boolean win;
	boolean failed;
	public Condition(int x, int y) throws IOException {
		this.loadImage();
		this.times = 3;
		this.setSize(656, 50);
		this.setVisible(true);
		this.setLocation(x, y);
	}
	
	public void loadImage() throws IOException {
		this.loseIconImage = ImageIO.read(new File("src/image/win_fail/fail.png"));
		this.winIconImage = ImageIO.read(new File("src/image/win_fail/win.png"));
		this.heart1 = ImageIO.read(new File("src/image/win_fail/heart1.png"));
		this.heart2 = ImageIO.read(new File("src/image/win_fail/emptyheart.png"));
	}
	
	public void subtractTimes() {
		this.times -= 1;
		// repaint();
	}
	
	public void setToWin() {
		this.win = true;
	}
	
	public void setToFailed() {
		this.failed = true;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < times; ++i)
			g.drawImage(this.heart1, 60 + i * 21, 25,  20,  20,  null);
		
		for(int i = 0; i < (3 - times); ++i)
			g.drawImage(this.heart2, 102 - i * 21, 25,  20,  20,  null);
		
		if(this.win) {
			g.drawImage(this.winIconImage, this.times * 20 + 40, 0, 50,  50,  null);
		}
		
		if(this.failed) {
			g.drawImage(this.loseIconImage, 0, 0, 50, 50, null);
		}
		
	}
	
}
