package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMDoubleType extends LLVMRealType {
	public LLVMDoubleType() {
		super(Core.LLVMDoubleType());
	}
}
