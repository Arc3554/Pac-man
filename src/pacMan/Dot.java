package pacMan;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Dot extends JComponent {
	private Image dotImg;
	private Image sugarImg;
	private boolean ok;
	private boolean dot;
	private boolean sugar;
	private int diameter;
	public Dot(int posx, int posy, int diameter) throws IOException {
		// TODO Auto-generated constructor stub
		super();
		this.dot = true;
		this.ok = true;
		this.sugar = false;
		this.diameter = diameter;
		this.dotImg = ImageIO.read(new File("src/image/bonus/dot.png"));
		this.sugarImg = ImageIO.read(new File("src/image/bonus/lalipop.png"));
		
		this.setLocation(posx, posy);
		this.setSize(this.diameter, this.diameter);
		this.setVisible(true);
		this.addMouseListener(new CustomizeMouseEventListener("Mouse Click", this));
		//System.out.printf("%d %d\n",posx,posy);
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		if(this.dot == true)
			g.drawImage(this.dotImg,  0,  0, this.diameter, this.diameter, null);
		else if(this.sugar == true) {
			g.drawImage(this.sugarImg, 0, 0, this.diameter, this.diameter, null);
		}
	}
	
	public void setToSugar() {
		this.dot = false;
		this.sugar = true;
		this.repaint();
	}
	
	public boolean isSugar() {
		return this.sugar;
	}
	
	public boolean isEaten() {
		return !(this.dot || this.sugar);
	}

	public void eaten() {
		System.out.printf("[%d, %d] eaten is called\n", this.getX() / 22, this.getY() / 22);
		this.dot = false;
		this.sugar = false;
		// this.remove(this);
		this.repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	
	public boolean isOk() {
		return this.ok;
	}
	
	public void setWall() {
		this.dot = false;
		this.ok = false;
		repaint();
	}
}
