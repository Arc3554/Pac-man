package pacMan;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class CustomizeMouseEventListener implements MouseListener {
	private String outputString = null;
	private JComponent component;
	public CustomizeMouseEventListener(String outputString, JComponent component) {
		// TODO Auto-generated constructor stub
		super();
		this.outputString = outputString;
		this.component = component;
	}
	
	public CustomizeMouseEventListener() {
		super();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.printf("[%d, %d],\n", this.component.getX() / Constant.SCALE, this.component.getY() / Constant.SCALE);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
