package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import org.jllvm.bindings.LLVMTypeKind;

public class LLVMVoidType extends LLVMType {
	public LLVMVoidType() {
		super(Core.LLVMVoidType());
	}
	
	public LLVMVoidType(SWIGTYPE_p_LLVMOpaqueType t) {
		super(t);
		assert(Core.LLVMGetTypeKind(t) == LLVMTypeKind.LLVMVoidTypeKind);
	}
}
