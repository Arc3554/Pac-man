package pacMan;

import java.util.TimerTask;

public class WinCondition extends TimerTask {
	private Map map;
	public WinCondition(Map map) {
		// TODO Auto-generated constructor stub
		this.map = map;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// System.out.printf("Target : %d, You eat : %d\n", this.map.getDotAmount(), this.map.getEatenDotAmount());
		if(this.map.getDotAmount() == this.map.getEatenDotAmount()) {
			System.out.println("YOU WIN!!!!!!!");
			this.map.showCondition.setToWin();
			this.map.pause(false);
		}
	}

}
