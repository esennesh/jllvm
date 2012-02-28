package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMUnreachableInstruction extends LLVMTerminatorInstruction {
	public LLVMUnreachableInstruction(LLVMInstructionBuilder builder) {
		instance = Core.LLVMBuildUnreachable(builder.getInstance());
	}
}
