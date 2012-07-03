package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import org.jllvm.bindings.LLVMTypeKind;

/* Implements all methods for vector types specified in Core.h */
public class LLVMVectorType extends LLVMSequenceType {
	public LLVMVectorType(LLVMType element_type,long num_elements) {
		super(Core.LLVMVectorType(element_type.getInstance(),num_elements));
	}
	
	public LLVMVectorType(SWIGTYPE_p_LLVMOpaqueType t) {
		super(t);
		assert(Core.LLVMGetTypeKind(t) == LLVMTypeKind.LLVMVectorTypeKind);
	}
	
	public long getSize() {
		return Core.LLVMGetVectorSize(instance);
	}
}
