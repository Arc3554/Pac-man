package pacMan;

import java.util.TimerTask;

public class ChaseTimerTask extends TimerTask {
	ElfBase elf1, elf2, elf3, elf4;
	public ChaseTimerTask(ElfBase elf1, ElfBase elf2, ElfBase elf3, ElfBase elf4) {
		// TODO Auto-generated constructor stub
		this.elf1 = elf1;
		this.elf2 = elf2;
		this.elf3 = elf3;
		this.elf4 = elf4;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.elf1.beElf();
		this.elf2.beElf();
		this.elf3.beElf();
		this.elf4.beElf();
	}

}
