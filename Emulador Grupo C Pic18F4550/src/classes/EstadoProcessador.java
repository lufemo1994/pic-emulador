package classes;

public class EstadoProcessador {
	// int pc byte w byte status byte bsr
	private int pc;
	private byte w;
	private byte[] insAtual;
	private byte bsr;
	
	public EstadoProcessador(int pc)
	{
		this.pc = pc;
	}
	
	
	public EstadoProcessador(int pc, byte w, byte[] insAtual, byte bsr) {
		this.pc = pc;
		this.w = w;
		this.insAtual = insAtual;
		this.bsr = bsr;
	}



	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	public byte getW() {
		return w;
	}
	public void setW(byte w) {
		this.w = w;
	}
	public byte[] getInsAtual() {
		return insAtual;
	}
	public void setInsAtual(byte[] insAtual) {
		this.insAtual = insAtual;
	}
	public byte getBsr() {
		return bsr;
	}
	public void setBsr(byte bsr) {
		this.bsr = bsr;
	}
	
	
}
