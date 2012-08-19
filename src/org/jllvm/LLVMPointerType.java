package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import org.jllvm.bindings.LLVMTypeKind;

/* Implements all methods for pointer types specified in Core.h */
public class LLVMPointerType extends LLVMSequenceType {
	public LLVMPointerType(LLVMType element_type,int addressSpace) {
		super(Core.LLVMPointerType(element_type.getInstance(),addressSpace));
	}
	
	public LLVMPointerType(SWIGTYPE_p_LLVMOpaqueType t) {
		super(t);
		assert(Core.LLVMGetTypeKind(t) == LLVMTypeKind.LLVMPointerTypeKind);
	}
	
	public long getAddressSpace() {
		return Core.LLVMGetPointerAddressSpace(instance);
	}
	
	public String toString() {
		return getElementType().toString() + "*";
	}
}
