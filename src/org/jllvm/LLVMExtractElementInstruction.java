package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMExtractElementInstruction extends LLVMInstruction {
	public LLVMExtractElementInstruction(LLVMInstructionBuilder builder,LLVMValue vector,LLVMValue index,String name) {
		assert(vector.typeOf() instanceof LLVMVectorType && index.typeOf() instanceof LLVMIntegerType);
		instance = Core.LLVMBuildExtractElement(builder.getInstance(),vector.getInstance(),index.getInstance(),name);
	}
}
