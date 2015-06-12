package classes;

public class ProgTeste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemoriaDados mem = new MemoriaDados();
		MemoriaPrograma mep = new MemoriaPrograma();
		// 
		/*try
		{
			mem.escreverNaMemoria(-1, (byte)2);
		}catch(Error e)
		{
			System.out.println(e.getMessage());
		}*/
		short bytezinho = (short)0b0101000011011110;
		//System.out.println(String.format(e"Bytezinho: %x", bytezinho));
		try {
			mep.escreverInstrucao(bytezinho, 0);
			byte[] instrucao = mep.lerInstrucao(0);
			System.out.println(String.format("%x | %x",  instrucao[0], instrucao[1]));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		
	}

}
