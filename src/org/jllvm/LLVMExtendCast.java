package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMExtendCast extends LLVMCastInstruction {
	public enum ExtendType { ZERO,SIGN,FLOAT };
	
	protected ExtendType instructionType;
	
	public ExtendType getInstructionType() {
		return instructionType;
	}
	
	public LLVMExtendCast(ExtendType type,LLVMInstructionBuilder builder,LLVMValue val,LLVMType destType,String name) {
		super(destType);
		assert((destType instanceof LLVMIntegerType && val.typeOf() instanceof LLVMIntegerType) ||
		       (destType instanceof LLVMRealType && val.typeOf() instanceof LLVMRealType));
		if(destType instanceof LLVMIntegerType && val.typeOf() instanceof LLVMIntegerType)
			assert(((LLVMIntegerType)destType).getWidth() > ((LLVMIntegerType)val.typeOf()).getWidth());
		instructionType = type;
		switch(type) {
			case ZERO:
				instance = Core.LLVMBuildZExt(builder.getInstance(),val.getInstance(),destType.getInstance(),name);
				break;
			case SIGN:
				instance = Core.LLVMBuildSExt(builder.getInstance(),val.getInstance(),destType.getInstance(),name);
				break;
			case FLOAT:
				instance = Core.LLVMBuildFPExt(builder.getInstance(),val.getInstance(),destType.getInstance(),name);
				break;
		}
	}
}
