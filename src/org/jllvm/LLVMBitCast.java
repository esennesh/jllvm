package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMBitCast extends LLVMCastInstruction {
	public LLVMBitCast(LLVMInstructionBuilder builder,LLVMValue val,LLVMType destType,String name) {
		super(destType);
		instance = Core.LLVMBuildBitCast(builder.getInstance(),val.getInstance(),destType.getInstance(),name);
	}
}
