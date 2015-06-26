package classes;

public class EstadoProcessador {
	// int pc byte w byte status byte bsr
	private int pc;
	private byte w;
	private byte status;
	private byte bsr;
	
	
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
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public byte getBsr() {
		return bsr;
	}
	public void setBsr(byte bsr) {
		this.bsr = bsr;
	}
	
	
}
