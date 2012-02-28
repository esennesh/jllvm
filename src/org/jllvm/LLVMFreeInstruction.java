package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMFreeInstruction extends LLVMInstruction {
	public LLVMFreeInstruction(LLVMInstructionBuilder builder,LLVMValue pointerValue) {
		assert(pointerValue.typeOf() instanceof LLVMPointerType);
		instance = Core.LLVMBuildFree(builder.getInstance(),pointerValue.getInstance());
	}
}
