package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import org.jllvm.bindings.LLVMTypeKind;

public class LLVMPPCFP128Type extends LLVMRealType {
	public LLVMPPCFP128Type() {
		super(Core.LLVMPPCFP128Type());
	}
	
	public LLVMPPCFP128Type(SWIGTYPE_p_LLVMOpaqueType t) {
		super(t);
		assert(Core.LLVMGetTypeKind(t) == LLVMTypeKind.LLVMPPC_FP128TypeKind);
	}
}
