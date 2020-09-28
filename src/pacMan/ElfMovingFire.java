package pacMan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

public class ElfMovingFire extends TimerTask {
	private ArrayList<ElfBase> Elves;
	private ElfBase elf;
	private ElfBase pacman;
	public ElfMovingFire(ElfBase pacman, ElfBase elf) {
		// TODO Auto-generated constructor stub
		this.pacman = pacman;
		this.elf = elf;
		Elves = new ArrayList<ElfBase>();
	}
	
	public void addElf(ElfBase elf) {
		if(!Elves.contains(elf)) {
			Elves.add(elf);
		}
	}
	
	public void addAnElf(ElfBase elf) {
		this.elf = elf;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// System.out.println("fire!!");
		try {
			this.elf.Move(24, 24, this.pacman);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
