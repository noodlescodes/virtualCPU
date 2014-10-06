
public class Memory {
	
	public final char c_MaxAddress = 255;
	private char[] m_MemorySpace;	
	
	public Memory() {
		m_MemorySpace = new char[c_MaxAddress];
		clear();
	}
	
	public void clear() {
		for(char i = 0; i < c_MaxAddress; i++) {
			m_MemorySpace[i] = 0;
		}
	}
	
	public char Read(char p_Address) {
		return m_MemorySpace[p_Address];
	}
	
	public void Write(char p_Address, char p_value) {
		m_MemorySpace[p_Address] = p_value;
	}
}
