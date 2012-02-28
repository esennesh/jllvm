package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMPtrIntCast extends LLVMCastInstruction {
	public enum PtrIntCastType {PTR_TO_INT,INT_TO_PTR};
	
	protected PtrIntCastType castType;
	
	public PtrIntCastType getCastType() {
		return castType;
	}
	
	public LLVMPtrIntCast(LLVMInstructionBuilder builder,LLVMValue val,LLVMType destType,String name,PtrIntCastType type) {
		super(destType);
		castType = type;
		switch(type) {
			case PTR_TO_INT: {
				assert(destType instanceof LLVMIntegerType);
				instance = Core.LLVMBuildPtrToInt(builder.getInstance(),val.getInstance(),destType.getInstance(),name);
			}
			case INT_TO_PTR: {
				assert(destType instanceof LLVMPointerType);
				instance = Core.LLVMBuildIntToPtr(builder.getInstance(),val.getInstance(),destType.getInstance(),name);
			}
		}
	}
}
