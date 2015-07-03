package classes;

import java.util.Stack;

import javafx.geometry.Pos;


public class PIC18F4550 {
	private byte w; // Registrador
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
			case OpCodes.CALL_1:
			case OpCodes.CALL_2:
				executarCall();
				break;
			case OpCodes.GOTO:
				executarGoto();
				break;
			case OpCodes.RETURN_Pt1:
				switch(this.insAtual[1])
				{
					case OpCodes.RETURN_Pt2_1:
					case OpCodes.RETURN_Pt2_2:
						executarReturn();
						break;
				}
				break;
			
		}
		
		
		atualizar();
	}
	
	private void executarMovwf()
	{
		byte teste = (byte) (insAtual[0] & 0b00000001); 
		byte nsn;
		byte k = insAtual[1];
		// 0b00000001
		// 0b00000000
		// [NSN+kkkkkkkk]
		
		if(teste == 0b00000001) // If ‘a’ is ‘1’, the BSR is used to select the GPR bank (default). 
		{ // Difícil: endereço de 12 bits
			// BSR+kkkkkkkk
			nsn = memDados.lerNaMemoria(OpCodes.BSR);
		}
		else // If ‘a’ is ‘0’, the Access Bank is selected
		{  // Fácil: endereço 8 bits
			if(insAtual[1] >= 0 && insAtual[1] <= 0x59)
			{
				nsn = 0x00;
			}
			else
			{
				nsn = (byte)0b00001111;
			}
		}
		
		this.w = (byte)0b00001001;
		Short endereco = new Short((short) ((nsn << 8) + k));
	}
	
	private void executarMovlw()
	{
		this.w = this.insAtual[1];
	}
	
	private void executarCall() throws Error
	{
		if(pilha.size() <= 31)
		{ // 1 -> vc manda só o pc; 0 -> vc manda tudo	
			byte[] instrucaoParte2 = memProg.lerInstrucao(pc+2);
			int endereco = this.insAtual[1];
			int part2Desloc = (0xff & (int)instrucaoParte2[1]);
			endereco = (part2Desloc << 8) | endereco;
			endereco = ((instrucaoParte2[0] & 0b00001111) << 16)+ endereco;
			if((insAtual[0] & 0b00000001) == (byte)0b00000001)
			{
				pilha.push(new EstadoProcessador(this.pc+2));
			}
			else
			{
				pilha.push(new EstadoProcessador(this.pc+2, this.w, this.insAtual, memDados.lerNaMemoria(OpCodes.BSR)));
			}
			
					
			this.pc = endereco-2;
		}
		else
		{
			throw new Error("Pilha está cheia!");
		}
		
		
	}
	
	private void executarGoto()
	{
		byte[] instrucaoParte2 = memProg.lerInstrucao(pc+2);
		int endereco = this.insAtual[1];
		int part2Desloc = (0xff & (int)instrucaoParte2[1]);
		endereco = (part2Desloc << 8) | endereco;
		endereco = ((instrucaoParte2[0] & 0b00001111) << 16)+ endereco;
		
		this.pc = endereco-2;
	}
	
	private void executarReturn()
	{  
		// 0 ou 1
		// 1 -> puxa só o pc
		// 0 -> puxa tudo
		int s = insAtual[1] & 0b00000001;
		if(s == 1)
		{
			EstadoProcessador retorno = pilha.pop();
			this.pc = retorno.getPc();
		}
		else
		{ // 0
			EstadoProcessador retorno = pilha.pop();
			this.pc = retorno.getPc();
			this.w = retorno.getW();
			this.insAtual = retorno.getInsAtual();
			memDados.escreverNaMemoria(OpCodes.BSR, retorno.getBsr());
		}
	}
	
	private void atualizar() // Incrementa o PC
	{ 
		this.pc += 2;
	}
	
	@Override
	public String toString()
	{
		return "W: "+this.w+"; PC: "+(this.pc-2);
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
