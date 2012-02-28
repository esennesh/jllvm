package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMIntegerToFloatCast extends LLVMCastInstruction {
	public enum IntCastType {SIGNED,UNSIGNED};
	
	protected IntCastType castType;
	
	public IntCastType getCastType() {
		return castType;
	}
	
	public LLVMIntegerToFloatCast(LLVMInstructionBuilder builder,LLVMValue val,LLVMRealType destType,String name,IntCastType type) {
		super(destType);
		castType = type;
		switch(type) {
			case SIGNED:
				instance = Core.LLVMBuildSIToFP(builder.getInstance(),val.getInstance(),destType.getInstance(),name);
			case UNSIGNED:
				instance = Core.LLVMBuildUIToFP(builder.getInstance(),val.getInstance(),destType.getInstance(),name);
		}
	}
}
