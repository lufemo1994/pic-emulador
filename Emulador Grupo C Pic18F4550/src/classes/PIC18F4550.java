package classes;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;



public class PIC18F4550 {
	private byte w;
	private int pc;
	private byte[] insAtual; // (hexa) [0] -> OpCode; [1] -> argumento
	private MemoriaDados memDados;
	private MemoriaPrograma memProg;
	



	
	public void executarCicloDeMaq()
	{
		
	}
	
	private void buscarInstrucao()
	{ // Lê a memória do programa usando o PC, lê o valor e joga no instrução atual
		insAtual = memProg.lerInstrucao(pc);
		decodificar();
	}
	
	private void decodificar()
	{ // identifica op code e os argumentos, pra cada opcode, uma ação diferente
		
		//OpCodes MOVLW = OpCodes.MOVLW;
		
		OpCodes currentCode = OpCodes.getOpCodeFromInt(insAtual[0]);
		
		
		
		int a = OpCodes.MOVLW.getCode();
		switch(currentCode)
		{
			case MOVLW:
				// Movlw: Move literal to WREG
				
				break;
		}
		
	}
	
	private void executar()
	{ // Faz a função do OP code
		
	}
	
	private void atualizar()
	{ // Incrementa o PC
		
	}
}
