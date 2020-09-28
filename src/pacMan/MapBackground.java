package pacMan;

import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.imageio.*;

public class MapBackground extends JComponent{
	private Image img;
	
	public MapBackground() throws IOException {
		// TODO Auto-generated constructor stub
		this.img = ImageIO.read(new File("src/image/map/map1.jpg"));
	}
	
	protected void paintComponent(Graphics g) {
		g.drawImage(img,  0,  0,  null);
	}
}
