package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMShuffleVectorInstruction extends LLVMInstruction {
	public LLVMShuffleVectorInstruction(LLVMInstructionBuilder builder,LLVMValue vec1,LLVMValue vec2,LLVMValue mask,String name) {
		assert(((LLVMVectorType)vec1.typeOf()).getElementType() == ((LLVMVectorType)vec2.typeOf()).getElementType() && mask.typeOf() == LLVMIntegerType.i32);
		instance = Core.LLVMBuildShuffleVector(builder.getInstance(),vec1.getInstance(),vec2.getInstance(),mask.getInstance(),name);
	}
}
