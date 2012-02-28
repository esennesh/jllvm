package org.jllvm;

import org.jllvm.LLVMInstruction;

/* This class exists solely to mirror the C++ bindings and have a superclass for terminator instructions. */
public class LLVMTerminatorInstruction extends LLVMInstruction {
	public LLVMTerminatorInstruction() {
		instance = null;
	}
}
