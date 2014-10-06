import java.util.Scanner;

public class test {
	private static Scanner reader;
	public static void main(String[] args) {
		
		reader = new Scanner(System.in);
		char c;
		
		System.out.println("Init memory...");

		Memory memory = new Memory();

		System.out.println("Ready.");

		System.out.println("Memory size: " + (int) memory.c_MaxAddress);

		System.out.println("Adding default machine code program...");

		// load0 value 1
		memory.Write((char)1, (char)1);
		memory.Write((char)2, (char)1);
		
		// load1 value 2
		memory.Write((char)3, (char)2);
		memory.Write((char)4, (char)2);
		
		// add
		memory.Write((char)5, (char)3);
		
		// store to 12
		memory.Write((char)6, (char)5);
		memory.Write((char)7, (char)11);
		
		// print from 12
		memory.Write((char)8, (char)6);
		memory.Write((char)9, (char)11);

		// halt
		memory.Write((char)10, (char)0);
		
		System.out.println("Do you want to list the memory?");
		c = reader.next().charAt(0);
		if(c == 'Y' || c == 'y') {
			for(char i = 0; i < memory.c_MaxAddress; i++) {
				System.out.println("Address [" + (int)i + "] = " + (int)memory.Read(i));
			}
		}
		
		System.out.println("Creating the CPU...");
		CPU cpu = new CPU(memory);
		System.out.println("Ready.");
		
		System.out.println("Starting...\n");
		cpu.Run();
	}
}
