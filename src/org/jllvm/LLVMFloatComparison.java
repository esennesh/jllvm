package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.LLVMRealPredicate;

public class LLVMFloatComparison extends LLVMComparisonInstruction {
	public LLVMFloatComparison(LLVMInstructionBuilder builder,LLVMRealPredicate Op,LLVMValue lhs,LLVMValue rhs,String name) {
		instance = Core.LLVMBuildFCmp(builder.getInstance(),Op,lhs.getInstance(),rhs.getInstance(),name);
	}
}
