package classes;

public class ProgTeste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PIC18F4550 pic = new PIC18F4550();
		try {
			//pic.getMemProg().escreverInstrucao((short)0b0000111000001010, 0);
			
			//pic.getMemDados().escreverNaMemoria(OpCodes.BSR, (byte)0b00000001);
			//pic.getMemProg().escreverInstrucao((short)0b011011100001010, 0);
			
			// Teste com o Call
			// Endereço Parte 1: 0001 0001 = 17
			// Endereço Parte 2: 1111 0010 1100 0100
			// Endereço Final: 181265 = 1011000100 
			
			//1100 0100 0001 0001
			
			// 1111 0000 0000 0000 0110 0100 = 100
			 
			// MOVLW 1
			pic.getMemProg().escreverInstrucao((short)0b0000111000000001, 0);
			
			
			// Call 100
			pic.getMemProg().escreverInstrucao((short)0b1110110001100100, 2);
			pic.getMemProg().escreverInstrucao((short)0b1111000000000000, 4);
			
			// MOVLW 3
			pic.getMemProg().escreverInstrucao((short)0b0000111000000011, 6);
			
			// GOTO 110 = 01101110
			pic.getMemProg().escreverInstrucao((short)0b1110111100000000, 8);
			pic.getMemProg().escreverInstrucao((short)0b1111000000000000, 10);
			
			// MOVLW 2
			pic.getMemProg().escreverInstrucao((short)0b0000111000000010, 100);
			
			// Return
			pic.getMemProg().escreverInstrucao((short)0b0000000000010010, 102);
			
			// MOVLW 94
			pic.getMemProg().escreverInstrucao((short)0b0000111001011110, 110);
			
			for(;;)
			{
				pic.executarCicloDeMaq();
				System.out.println(pic);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}

}
