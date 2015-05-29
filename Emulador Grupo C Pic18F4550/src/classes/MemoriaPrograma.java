package classes;

public class MemoriaPrograma {
	private byte[] dadosDaMemoria = new byte[32768];
	
	public void escreverNaMemoria(int pos, byte dado) throws Error
	{
		if(pos >= 0 && pos <= 32768)
		{
			this.dadosDaMemoria[pos] = dado;
		}
		else
		{
			throw new Error("Posição inválida!");
		}
	}
	
	public byte lerNaMemoria(int pos) throws Error
	{
		if(pos >= 0 && pos < 2097152)
		{
			if(pos >= 0 && pos <= 32768)
			{
				return this.dadosDaMemoria[pos];
			}
			else
			{
				return 0;
			}
		}
		else
		{
			throw new Error("Posição inválida!");
		}
	}
	
	public void escreverInstrucao(short instrucao, int pos) throws Error
	{
		if(pos >= 0 && pos <= 32768)
		{
			this.dadosDaMemoria[pos+1] = (byte)instrucao;
			int temp = instrucao >> 8;
			this.dadosDaMemoria[pos] = (byte)temp;
		}
		else
		{
			throw new Error("Posição inválida!");
		}
	}
	
	public short lerInstrucao(int pos) throws Error
	{
		if(pos >= 0 && pos < 2097152)
		{
			if(pos >= 0 && pos <= 32768)
			{
				int x=0;
				int temp = this.dadosDaMemoria[pos];
				x = temp << 8;
				temp = x | (0xFF&this.dadosDaMemoria[pos+1]);
				return (short)temp;
			}
			else
			{
				return 0;
			}
		}
		else
		{
			throw new Error("Posição inválida!");
		}
	}
	
	
}
