package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import org.jllvm.bindings.LLVMTypeKind;

public class LLVMOpaqueType extends LLVMType {
	public LLVMOpaqueType() {
		super(Core.LLVMOpaqueType());
	}
	
	public LLVMOpaqueType(SWIGTYPE_p_LLVMOpaqueType t) {
		super(t);
		assert(Core.LLVMGetTypeKind(t) == LLVMTypeKind.LLVMOpaqueTypeKind);
	}
}
