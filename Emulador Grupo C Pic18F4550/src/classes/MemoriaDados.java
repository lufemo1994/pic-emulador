package classes;

public class MemoriaDados {
	private byte[] dadosDaMemoria = new byte[4096];

	
	public void escreverNaMemoria(int pos, byte dado) throws Error
	{
		if (pos >= 0 && pos <= 4096)
		{
			if(pos >= 2048 && pos <= 3935)
			{
				System.out.println("Posição indisponível!");
			}
			else
			{
				this.dadosDaMemoria[pos] = dado;
			}
		}
		else
		{
			throw new Error("Posição inválida!");
		}
		
		
	}
	
	public byte lerNaMemoria(int pos) throws Error
	{ // 0x800 => 2048
	  // 0xF5f => 3935	
		if (pos >= 0 && pos <= 4096)
		{
			if(pos >= 2048 && pos <= 3935)
			{
				return (byte) 0;
			}
			else
			{
				return (byte) this.dadosDaMemoria[pos];
			}
		}
		else
		{
			throw new Error("Posição inválida!");
		}
			
	}

	// < 8000h
	
}
