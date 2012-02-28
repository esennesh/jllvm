package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMHeapAllocation extends LLVMAllocationInstruction {
	public LLVMHeapAllocation(LLVMInstructionBuilder builder,LLVMType type,LLVMValue number,String name) {
		if(number != null) {
			assert(number.typeOf() == LLVMIntegerType.i32);
			instance = Core.LLVMBuildArrayMalloc(builder.getInstance(),type.getInstance(),number.getInstance(),name);
		}
		else 
			instance = Core.LLVMBuildMalloc(builder.getInstance(),type.getInstance(),name);
	}
}
