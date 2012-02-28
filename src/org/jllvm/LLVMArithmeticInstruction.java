package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;

public class LLVMArithmeticInstruction extends LLVMInstruction {
	private boolean fpType;
	
	public boolean isFloatingPoint() {
		return fpType;
	}
	
	public LLVMArithmeticInstruction(SWIGTYPE_p_LLVMOpaqueValue val,boolean fp) {
		super(val);
		fpType = fp;
	}
}
