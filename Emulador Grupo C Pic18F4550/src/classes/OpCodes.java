package classes;

public class OpCodes
{
	static final byte MOVLW = (byte) 0b00001110;
	static final byte MOVWF_1 = (byte) 0b01101110; // 0b0110111a	
	static final byte MOVWF_2 = (byte) 0b01101111; // 0b0110111a
	static final byte CALL_1 = (byte) 0b11101100;
	static final byte CALL_2 = (byte) 0b11101101;
	static final byte RETURN_Pt1 = (byte) 0b00000000;
	static final byte RETURN_Pt2_1 = (byte) 0b00010010;
	static final byte RETURN_Pt2_2 = (byte) 0b00010011;
	static final byte GOTO = (byte) 0b11101111;
	
	static final int BSR = 4064; // FE0h
	// call
	// goto
	// decfsz
	// nop	
	// return 
	// END
	// TRISD, TRISB
}