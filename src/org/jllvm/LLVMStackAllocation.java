package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMStackAllocation extends LLVMAllocationInstruction {
	public LLVMStackAllocation(LLVMInstructionBuilder builder,LLVMType type,LLVMValue number,String name) {
		LLVMPointerType ptrtype = new LLVMPointerType(type,0);
		if(number != null) {
			assert(number.typeOf() == LLVMIntegerType.i32);
			instance = Core.LLVMBuildArrayAlloca(builder.getInstance(),ptrtype.getElementType().getInstance(),number.getInstance(),name);
		}
		else
			instance = Core.LLVMBuildAlloca(builder.getInstance(),ptrtype.getElementType().getInstance(),name);
	}
}
