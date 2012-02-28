package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMUnaryBitwiseInstruction extends LLVMInstruction {
	public enum UnaryBitwiseInstructionType {NOT,NEGATIVE};
	
	protected UnaryBitwiseInstructionType instructionType;
	
	public UnaryBitwiseInstructionType getInstructionType() {
		return instructionType;
	}
	
	public LLVMUnaryBitwiseInstruction(UnaryBitwiseInstructionType type,LLVMInstructionBuilder builder,LLVMValue val,String name) {
		instructionType = type;
		switch(type) {
			case NOT:
				instance = Core.LLVMBuildNot(builder.getInstance(),val.getInstance(),name);
			case NEGATIVE:
				instance = Core.LLVMBuildNeg(builder.getInstance(),val.getInstance(),name);
		}
	}
} 
