package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMUnwindInstruction extends LLVMTerminatorInstruction {
	public LLVMUnwindInstruction(LLVMInstructionBuilder builder) {
		instance = Core.LLVMBuildUnwind(builder.getInstance());
	}
}
