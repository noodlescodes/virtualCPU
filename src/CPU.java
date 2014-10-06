
public class CPU {
	
	public final char c_ReservedAddress;
	public final char c_BaseAddress;
	public final char c_AddressCeiling;
	private char m_ProgramCounter;
	private char m_Register0;
	private char m_Register1;
	private boolean m_OverflowError;
	private boolean m_UnderflowError;
	private boolean m_SignedMode;
	private boolean m_Halt;
	private Memory m_Memory;
	
	public CPU(Memory p_Memory) {
		c_ReservedAddress = 0;
		c_BaseAddress = 1;
		c_AddressCeiling = 253;
		m_ProgramCounter = c_BaseAddress;
		m_Register0 = 0;
		m_Register1 = 1;
		m_OverflowError = false;
		m_UnderflowError = false;
		m_SignedMode = false;
		m_Memory = p_Memory;
		m_Halt = false;
	}
	
	public void Reset() {
		m_Halt = false;
		m_ProgramCounter = c_BaseAddress;
		m_OverflowError = false;
		m_UnderflowError = false;
		m_SignedMode = false;
	}
	
	public void Run() {
		
		// while not halted, continue
		while(!m_Halt) {
			// fetch into the reserved memory location
			m_Memory.Write(c_ReservedAddress, Fetch());
			
			// decode from the memory reserved address
			Decode(m_Memory.Read(c_ReservedAddress));
		}
	}
	
	private char Fetch() {
		char l_opCode = 0;
		l_opCode = m_Memory.Read(m_ProgramCounter);
		m_ProgramCounter++;
		if(m_ProgramCounter > c_AddressCeiling) {
			Halt();
		}
		return l_opCode;
	}
	
	private void Decode(char p_OpCode) {
		if(m_Halt) {
			return;
		}

		switch(p_OpCode) {
		case 0:
			// halt
			Halt();
			break;
		case 1:
			// load 0
			m_Register0 = m_Memory.Read(m_ProgramCounter);
			m_ProgramCounter++;
			break;
		case 2:
			// load 1
			m_Register1 = m_Memory.Read(m_ProgramCounter);
			m_ProgramCounter++;
			break;
		case 3:
			// add
			Add();
			break;
		case 4:
			// beep
			Beep();
			break;
		case 5:
			// store operation
			Store();
			break;
		case 6:
			// print
			Print();
			break;
		}
	}
	
	private void Halt() {
		m_Halt = true;
	}
	
	private void Add() {
		m_Register0 = (char) (m_Register0 + m_Register1);
	}
	
	private void Beep() {
		System.out.println("beep");
	}
	
	private void Store() {
		// load the target address into register 1
		m_Register1 = m_Memory.Read(m_ProgramCounter);
		
		m_ProgramCounter++; // skip the memory location data
		
		// write the register 0 value to this address
		m_Memory.Write(m_Register1, m_Register0);
	}
	
	private void Print() {
		// load the target address into register 1
		m_Register1 = m_Memory.Read(m_ProgramCounter);
		m_ProgramCounter++;
		
		// the value of the register is now the value to load
		m_Register0 = m_Memory.Read(m_Register1);
		
		// output the register
		System.out.println((int)m_Register0);
	}
}
