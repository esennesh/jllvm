package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import org.jllvm.bindings.LLVMTypeKind;

public class LLVMFloatType extends LLVMRealType {
	public LLVMFloatType() {
		super(Core.LLVMFloatType());
	}
	
	public LLVMFloatType(SWIGTYPE_p_LLVMOpaqueType t) {
		super(t);
		assert(Core.LLVMGetTypeKind(t) == LLVMTypeKind.LLVMFloatTypeKind);
	}
}
