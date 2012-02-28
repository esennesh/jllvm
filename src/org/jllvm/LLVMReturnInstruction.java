package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.LLVMInstruction;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;

public class LLVMReturnInstruction extends LLVMInstruction {
	protected boolean isVoid;

	/* Can accept null as its second parameter. */	
	public LLVMReturnInstruction(LLVMInstructionBuilder builder,LLVMValue val) {
		if(val != null) {
			instance = Core.LLVMBuildRet(builder.getInstance(),val.getInstance());
			isVoid = false;
		}
		else {
			instance = Core.LLVMBuildRetVoid(builder.getInstance());
			isVoid = true;
		}
		llvm_values.put(instance,this);
	}

	public LLVMReturnInstruction(SWIGTYPE_p_LLVMOpaqueValue val) {
		super(val);
	}
	
	public boolean getIsVoid() {
		return isVoid;
	}
	
	public static LLVMReturnInstruction getReturnInstruction(SWIGTYPE_p_LLVMOpaqueValue val) {
		return (LLVMReturnInstruction)LLVMValue.getValue(val);
	}
}
