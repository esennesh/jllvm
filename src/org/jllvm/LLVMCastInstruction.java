package org.jllvm;

import org.jllvm.bindings.Core;

public abstract class LLVMCastInstruction extends LLVMInstruction {
	protected LLVMType destination;
	
	public LLVMType getDestination() {
		return destination;
	}
	
	public LLVMCastInstruction(LLVMType destType) {
		destination = destType;
	}
}
