package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMInsertValueInstruction extends LLVMInstruction {
	public LLVMInsertValueInstruction(LLVMInstructionBuilder builder,LLVMValue aggr,LLVMValue Value,long index,String name) {
		//assert(aggr.typeOf() instanceof LLVMaggrType && Value.typeOf() == ((LLVMaggrType)aggr.typeOf()).getValueType());
		instance = Core.LLVMBuildInsertValue(builder.getInstance(),aggr.getInstance(),Value.getInstance(),index,name);
	}
}
