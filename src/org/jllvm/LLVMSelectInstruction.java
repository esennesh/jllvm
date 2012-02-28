package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMSelectInstruction extends LLVMInstruction {
	public LLVMSelectInstruction(LLVMInstructionBuilder builder,LLVMValue condition,LLVMValue then,LLVMValue otherwise,String name) {
		assert(condition.typeOf() == LLVMIntegerType.i1 || (condition.typeOf() instanceof LLVMVectorType && ((LLVMVectorType)condition.typeOf()).getElementType() == LLVMIntegerType.i1));
		assert(then.typeOf() == otherwise.typeOf());
		instance = Core.LLVMBuildSelect(builder.getInstance(),condition.getInstance(),then.getInstance(),otherwise.getInstance(),name);
	}
}
