package pacMan;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import javax.swing.JComponent;

public class CustomizeKeyEventListener extends KeyAdapter{
	
	public CustomizeKeyEventListener(JComponent component) {
		// TODO Auto-generated constructor stub
		super();
		System.out.println("KeyEventListner");
	}
	
	public CustomizeKeyEventListener() {
		super();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("key type");
		System.out.println(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("key pressed");
		System.out.println(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
