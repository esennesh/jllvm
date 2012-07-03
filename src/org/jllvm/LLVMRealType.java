package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import org.jllvm.bindings.LLVMTypeKind;

public class LLVMRealType extends LLVMType {
	public LLVMRealType(SWIGTYPE_p_LLVMOpaqueType tr) {
		super(tr);
		LLVMTypeKind kind = getTypeKind();
		assert(kind == LLVMTypeKind.LLVMFloatTypeKind || kind == LLVMTypeKind.LLVMDoubleTypeKind || kind == LLVMTypeKind.LLVMX86_FP80TypeKind || kind == LLVMTypeKind.LLVMFP128TypeKind || kind == LLVMTypeKind.LLVMPPC_FP128TypeKind);
	}
}
