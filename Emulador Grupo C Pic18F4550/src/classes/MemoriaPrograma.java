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
	
	public byte[] lerInstrucao(int pos) throws Error
	{
		if(pos >= 0 && pos < 2097152)
		{
			if(pos >= 0 && pos <= 32768)
			{
				byte[] instrucao = new byte[2];
				instrucao[0] = this.dadosDaMemoria[pos]; // OpCode (esquerda)
				instrucao[1] = this.dadosDaMemoria[pos+1]; // Arg (direita)
				return instrucao;
			}
			else
			{
				return new byte[]{0};
			}
		}
		else
		{
			throw new Error("Posição inválida!");
		}
	}
	
	
}
