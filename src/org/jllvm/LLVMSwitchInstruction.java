package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.LLVMTerminatorInstruction;

public class LLVMSwitchInstruction extends LLVMTerminatorInstruction {
	public LLVMSwitchInstruction(LLVMInstructionBuilder builder,LLVMValue value,LLVMBasicBlock block,long numCases) {
		assert(numCases >= 0);
		instance = Core.LLVMBuildSwitch(builder.getInstance(),value.getInstance(),block.getBBInstance(),numCases);
		llvm_values.put(instance,this);
	}
	
	public void addCase(LLVMValue onValue,LLVMBasicBlock destination) {
		Core.LLVMAddCase(getInstance(),onValue.getInstance(),destination.getBBInstance());
	}
}
