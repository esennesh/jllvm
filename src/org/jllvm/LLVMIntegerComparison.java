package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.LLVMIntPredicate;

public class LLVMIntegerComparison extends LLVMComparisonInstruction {
	public LLVMIntegerComparison(LLVMInstructionBuilder builder,LLVMIntPredicate Op,LLVMValue lhs,LLVMValue rhs,String name) {
		instance = Core.LLVMBuildICmp(builder.getInstance(),Op,lhs.getInstance(),rhs.getInstance(),name);
	}
}
