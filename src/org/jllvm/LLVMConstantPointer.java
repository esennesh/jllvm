package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;

public class LLVMConstantPointer extends LLVMConstant {
	public LLVMConstantPointer(SWIGTYPE_p_LLVMOpaqueValue c) {
		super(c);
		assert(typeOf() instanceof LLVMPointerType);
	}
	
	public LLVMConstantExpression toInteger(LLVMPointerType targetType) {
		return new LLVMConstantExpression(Core.LLVMConstPtrToInt(instance,targetType.getInstance()));
	}
}
