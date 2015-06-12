package classes;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;



public class PIC18F4550 {
	private byte w;
	private int pc = 0;
	private byte[] insAtual; // (hexa) [0] -> OpCode; [1] -> argumento
	private MemoriaDados memDados;
	private MemoriaPrograma memProg;

	
	public PIC18F4550() 
	{
		this.memDados = new MemoriaDados();
		this.memProg = new MemoriaPrograma();
	}

	public void executarCicloDeMaq()
	{
		buscarInstrucao();
	}
	
	private void buscarInstrucao()
	{ // Lê a memória do programa usando o PC, lê o valor e joga no instrução atual
		this.insAtual = memProg.lerInstrucao(pc);
		decodificar();
	}
	
	private void decodificar()
	{ // identifica op code e os argumentos, pra cada opcode, uma ação diferente
		
		switch(this.insAtual[0])
		{
			case OpCodes.MOVLW:
				//
				executarMovlw();
				break;
		}
		
		atualizar();
	}
	
	private void executarMovlw()
	{
		this.w = this.insAtual[1];
	}
	
	private void atualizar() // Incrementa o PC
	{ 
		this.pc += 2;
	}
	
	public String toString()
	{
		return "W: "+this.w+"; PC: "+this.pc;
	}

	public byte getW() {
		return w;
	}

	public void setW(byte w) {
		this.w = w;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public byte[] getInsAtual() {
		return insAtual;
	}

	public void setInsAtual(byte[] insAtual) {
		this.insAtual = insAtual;
	}

	public MemoriaDados getMemDados() {
		return memDados;
	}

	public void setMemDados(MemoriaDados memDados) {
		this.memDados = memDados;
	}

	public MemoriaPrograma getMemProg() {
		return memProg;
	}

	public void setMemProg(MemoriaPrograma memProg) {
		this.memProg = memProg;
	}
	
	
}
