package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import org.jllvm.bindings.LLVMTypeKind;

/* Implements all methods for array types specified in Core.h */
public class LLVMArrayType extends LLVMSequenceType {
	public LLVMArrayType(LLVMType element_type,long num_elements) {
		super(Core.LLVMArrayType(element_type.getInstance(),num_elements));
	}
	
	public LLVMArrayType(SWIGTYPE_p_LLVMOpaqueType t) {
		super(t);
		assert(Core.LLVMGetTypeKind(t) == LLVMTypeKind.LLVMArrayTypeKind);
	}
	
	public long getLength() {
		return Core.LLVMGetArrayLength(instance);
	}
}
