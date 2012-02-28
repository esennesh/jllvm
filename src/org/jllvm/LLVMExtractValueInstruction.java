package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMExtractValueInstruction extends LLVMInstruction {
	public LLVMExtractValueInstruction(LLVMInstructionBuilder builder,LLVMValue aggr,long index,String name) {
		instance = Core.LLVMBuildExtractValue(builder.getInstance(),aggr.getInstance(),index,name);
	}
}
