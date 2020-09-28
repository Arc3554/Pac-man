package pacMan;

import java.awt.Rectangle;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

public class CollisionDetector extends TimerTask {
	private ElfBase pacman, elf;
	private Map map;
	public CollisionDetector(ElfBase pacman, ElfBase elf, Map m) {
		// TODO Auto-generated constructor stub
		this.pacman = pacman;
		this.elf = elf;
		this.map = m;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Rectangle rectB = elf.getBounds();
		Rectangle result = SwingUtilities.computeIntersection(this.pacman.getX(), this.pacman.getY(),  this.pacman.getWidth(), this.pacman.getHeight(), rectB);
		if(result.getWidth() > 3 && result.getHeight() > 3) {
			if(elf.state) {
				this.map.pacmanIsKilled();
				// this.map.pause(false);
			}else {
				elf.backToStartPos();
				this.map.killGhost();
			}
		}
		
	}

}
