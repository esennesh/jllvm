package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMTruncateInstruction extends LLVMCastInstruction {
	public enum TruncateType { FLOAT,INTEGER };
	
	protected TruncateType instructionType;
	
	public TruncateType getInstructionType() {
		return instructionType;
	}
	
	public LLVMTruncateInstruction(TruncateType type,LLVMInstructionBuilder builder,LLVMValue val,LLVMType destType,String name) {
		super(destType);
		instructionType = type;
		switch(type) {
			case FLOAT: {
				assert(val.typeOf() instanceof LLVMRealType);
				assert(destType instanceof LLVMRealType);
				instance = Core.LLVMBuildFPTrunc(builder.getInstance(),val.getInstance(),destType.getInstance(),name);
			}
			case INTEGER: {
				assert(val.typeOf() instanceof LLVMIntegerType);
				assert(destType instanceof LLVMIntegerType);
				instance = Core.LLVMBuildTrunc(builder.getInstance(),val.getInstance(),destType.getInstance(),name);
			}
		}
	}
}
