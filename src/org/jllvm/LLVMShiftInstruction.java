package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMShiftInstruction extends LLVMInstruction {
	public enum ShiftType {SHL,LOGICAL_SHR,ARITHMETIC_SHR}
	
	protected ShiftType shiftType;
	
	public ShiftType getShiftType() {
		return shiftType;
	}
	
	public LLVMShiftInstruction(ShiftType type,LLVMInstructionBuilder builder,LLVMValue lhs,LLVMValue rhs,String name) {
		shiftType = type;
		switch(type) {
			case SHL:
				instance = Core.LLVMBuildShl(builder.getInstance(),lhs.getInstance(),rhs.getInstance(),name);
			case LOGICAL_SHR:
				instance = Core.LLVMBuildLShr(builder.getInstance(),lhs.getInstance(),rhs.getInstance(),name);
			case ARITHMETIC_SHR:
								instance = Core.LLVMBuildAShr(builder.getInstance(),lhs.getInstance(),rhs.getInstance(),name);
		}
	}
}
