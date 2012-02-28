package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.LLVMTerminatorInstruction;

public class LLVMBranchInstruction extends LLVMTerminatorInstruction {
	public LLVMBranchInstruction(LLVMInstructionBuilder builder,LLVMBasicBlock destination) {
		instance = Core.LLVMBuildBr(builder.getInstance(),destination.getBBInstance());
		llvm_values.put(instance,this);
	}
	
	public LLVMBranchInstruction(LLVMInstructionBuilder builder,LLVMValue condition,LLVMBasicBlock thenBlock,LLVMBasicBlock elseBlock) {
		instance = Core.LLVMBuildCondBr(builder.getInstance(),condition.getInstance(),thenBlock.getBBInstance(),elseBlock.getBBInstance());
		llvm_values.put(instance,this);
	}
}
