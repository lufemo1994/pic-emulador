package classes;

public class ProgTeste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PIC18F4550 pic = new PIC18F4550();
		try {
			pic.getMemProg().escreverInstrucao((short)0b0000111000001010, 0);
			pic.executarCicloDeMaq();
			System.out.println(pic);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}

}
