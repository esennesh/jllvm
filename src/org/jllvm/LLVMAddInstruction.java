package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;

public class LLVMAddInstruction extends LLVMArithmeticInstruction {
	private static SWIGTYPE_p_LLVMOpaqueValue buildInstruction(LLVMInstructionBuilder builder,LLVMValue lhs,LLVMValue rhs,boolean fp,String name) {
		if(fp)
			return Core.LLVMBuildFAdd(builder.getInstance(),lhs.getInstance(),rhs.getInstance(),name);
		else
			return Core.LLVMBuildAdd(builder.getInstance(),lhs.getInstance(),rhs.getInstance(),name);
	}
	
	public LLVMAddInstruction(LLVMInstructionBuilder builder,LLVMValue lhs,LLVMValue rhs,boolean fp,String name) {
		super(buildInstruction(builder,lhs,rhs,fp,name),fp);
	}
}
