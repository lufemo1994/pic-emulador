package classes;

import java.util.HashMap;
import java.util.Map;

public enum OpCodes {
	XYZ (1),
	MOVLW (2),
	NOP (4);
	
	private final int code;

    OpCodes(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return this.code;
    }
    
    public static OpCodes getOpCodeFromInt(int intOpCode)
    {
    	
    	OpCodes type = intToTypeMap.get(Integer.valueOf(intOpCode));
        if (type == null) 
        	 //Depois você deve mudar para a instrução NOP
             return OpCodes.XYZ;
        return type;
    	
    }
    
    
    private static final Map<Integer, OpCodes> intToTypeMap = new HashMap<Integer, OpCodes>();
    
    static {
        for (OpCodes type : OpCodes.values()) {
            intToTypeMap.put(type.code, type);
        }
    }


}