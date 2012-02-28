package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMInsertElementInstruction extends LLVMInstruction {
	public LLVMInsertElementInstruction(LLVMInstructionBuilder builder,LLVMValue vector,LLVMValue element,LLVMValue index,String name) {
		assert(vector.typeOf() instanceof LLVMVectorType && index.typeOf() instanceof LLVMIntegerType && element.typeOf() == ((LLVMVectorType)vector.typeOf()).getElementType());
		instance = Core.LLVMBuildInsertElement(builder.getInstance(),vector.getInstance(),element.getInstance(),index.getInstance(),name);
	}
}
