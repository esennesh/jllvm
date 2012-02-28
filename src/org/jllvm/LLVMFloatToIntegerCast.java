package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMFloatToIntegerCast extends LLVMCastInstruction {
	public enum FPToIntCastType {SIGNED,UNSIGNED};
	
	protected FPToIntCastType castType;
	
	public FPToIntCastType getCastType() {
		return castType;
	}
	
	public LLVMFloatToIntegerCast(LLVMInstructionBuilder builder,LLVMValue val,LLVMIntegerType destType,String name,FPToIntCastType type) {
		super(destType);
		castType = type;
		switch(type) {
			case SIGNED:
				instance = Core.LLVMBuildFPToSI(builder.getInstance(),val.getInstance(),destType.getInstance(),name);
			case UNSIGNED:
				instance = Core.LLVMBuildFPToUI(builder.getInstance(),val.getInstance(),destType.getInstance(),name);
		}
	}
}
