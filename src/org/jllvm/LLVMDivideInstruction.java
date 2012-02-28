package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;

public class LLVMDivideInstruction extends LLVMArithmeticInstruction {
	public enum DivisionType { FLOAT, SIGNEDINT, UNSIGNEDINT };
	
	private static SWIGTYPE_p_LLVMOpaqueValue buildInstruction(LLVMInstructionBuilder builder,LLVMValue lhs,LLVMValue rhs,DivisionType kind,String name) {
		switch(kind) {
			case FLOAT:
				return Core.LLVMBuildFDiv(builder.getInstance(),lhs.getInstance(),rhs.getInstance(),name);
			case SIGNEDINT:
				return Core.LLVMBuildSDiv(builder.getInstance(),lhs.getInstance(),rhs.getInstance(),name);
			case UNSIGNEDINT:
				return Core.LLVMBuildUDiv(builder.getInstance(),lhs.getInstance(),rhs.getInstance(),name);
		}
		//This should never run.
		return null;
	}
	
	public LLVMDivideInstruction(LLVMInstructionBuilder builder,LLVMValue lhs,LLVMValue rhs,DivisionType kind,String name) {
		super(buildInstruction(builder,lhs,rhs,kind,name),(kind == DivisionType.FLOAT));
	}
}
