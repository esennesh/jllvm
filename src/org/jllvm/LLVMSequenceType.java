package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;

/* Implements all methods specified for all sequences in Core.h */
public class LLVMSequenceType extends LLVMType {
	public LLVMType getElementType() {
		return LLVMType.getType(Core.LLVMGetElementType(getInstance()));
	}

	public LLVMSequenceType(SWIGTYPE_p_LLVMOpaqueType t) {
		super(t);
	}
}
