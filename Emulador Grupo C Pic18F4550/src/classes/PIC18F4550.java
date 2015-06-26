package classes;

import java.util.Stack;


public class PIC18F4550 {
	private byte w;
	private int pc = 0;
	private byte[] insAtual; // (hexa) [0] -> OpCode; [1] -> argumento
	private MemoriaDados memDados;
	private MemoriaPrograma memProg;
	private Stack<EstadoProcessador> pilha = new Stack<EstadoProcessador>();
	
	
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
	{ // Identifica op code e os argumentos, pra cada opcode, uma ação diferente
		
		switch(this.insAtual[0])
		{
			case OpCodes.MOVLW:
				//
				executarMovlw();
				break;
			case OpCodes.MOVWF_1:
			case OpCodes.MOVWF_2:
				//
				executarMovwf();
				break;	
		}
		
		
		atualizar();
	}
	
	private void executarMovwf()
	{ // BSR -> 0xFE0H
		byte teste = (byte) (insAtual[0] & 0b00000001); 
		byte nsn;
		byte k = insAtual[1];
		// 0b00000001
		// 0b00000000
		// [NSN+kkkkkkkk]
		
		if(teste == 0b00000001) // If ‘a’ is ‘1’, the BSR is used to select the GPR bank (default). 
		{ // Difícil: endereço de 12 bits
			System.out.println("A = 1");
			// BSR+kkkkkkkk
			nsn = memDados.lerNaMemoria(OpCodes.BSR);
			//System.out.println("Valor em HEX: "+temp.shortValue());
			//System.out.println("Valor em INT: "+temp.intValue());
			//System.out.println("Move o valor de W("+this.w+") para o endereço ");
		}
		else // If ‘a’ is ‘0’, the Access Bank is selected
		{  // Fácil: endereço 8 bits
			System.out.println("A == 0");
			
			//if(k >= 0 && k <= 59h) -> NSN = 0000
			if(insAtual[1] >= 0 && insAtual[1] <= 0x59)
			{
				nsn = 0x00;
			}
			else
			{
				nsn = (byte)0b00001111;
			}
		
			//else if(K >= 60h <= FFh) -> NSN 1111
		}
		
		this.w = (byte)0b00001001;
		//System.out.println("NSN: "+nsn);
		Short endereco = new Short((short) ((nsn << 8) + k));
		//memDados.escreverNaMemoria(endereco.intValue(), this.w);
		//System.out.println("Endereço: "+endereco.intValue());
		//System.out.println("Valor de W: "+this.w);
		//System.out.println(memDados.lerNaMemoria(endereco.intValue()));
	}
	
	private void executarMovlw()
	{
		this.w = this.insAtual[1];
	}
	
	private void executarCall() throws Error
	{
		if(pilha.size() <= 31)
		{
			EstadoProcessador estadoAtual = new EstadoProcessador();
			estadoAtual.setPc(this.pc);
			estadoAtual.setW(this.w);
			estadoAtual.setStatus((byte)0);
			estadoAtual.setBsr(memDados.lerNaMemoria(OpCodes.BSR));
			pilha.push(estadoAtual);
			
			
		}
		else
		{
			throw new Error("Pilha está cheia!");
		}
	}
	
	private void executarReturn()
	{
		
	}
	
	private void atualizar() // Incrementa o PC
	{ 
		this.pc += 2;
	}
	
	@Override
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
