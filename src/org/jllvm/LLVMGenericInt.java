package org.jllvm;

import org.jllvm.bindings.ExecutionEngine;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueGenericValue;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueGenericValue;
import java.math.BigInteger;

public class LLVMGenericInt extends LLVMGenericValue {
	public LLVMGenericInt(LLVMType t,java.math.BigInteger n,boolean isSigned) {
		super(ExecutionEngine.LLVMCreateGenericValueOfInt(t.getInstance(),n,isSigned));
	}
	
	public java.math.BigInteger toInt(boolean isSigned) {
		return ExecutionEngine.LLVMGenericValueToInt(instance,isSigned);
	}
	
	public long intWidth() {
		return ExecutionEngine.LLVMGenericValueIntWidth(instance);
	}
}
