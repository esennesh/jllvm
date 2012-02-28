package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMBinaryBitwiseInstruction extends LLVMInstruction {
	public enum BinaryBitwiseInstructionType {AND,OR,XOR};
	
	protected BinaryBitwiseInstructionType instructionType;
	
	public BinaryBitwiseInstructionType getInstructionType() {
		return instructionType;
	} 
	
	public LLVMBinaryBitwiseInstruction(BinaryBitwiseInstructionType type,LLVMInstructionBuilder builder,LLVMValue lhs,LLVMValue rhs,String name) {
		instructionType = type;
		switch(type) {
			case AND:
				instance = Core.LLVMBuildAnd(builder.getInstance(),lhs.getInstance(),rhs.getInstance(),name);
			case OR:
				instance = Core.LLVMBuildOr(builder.getInstance(),lhs.getInstance(),rhs.getInstance(),name);
			case XOR:
				instance = Core.LLVMBuildXor(builder.getInstance(),lhs.getInstance(),rhs.getInstance(),name);
		}
	}
}
