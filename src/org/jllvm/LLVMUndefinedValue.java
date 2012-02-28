package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMUndefinedValue extends LLVMValue {
	public LLVMUndefinedValue(LLVMType t) {
		instance = Core.LLVMGetUndef(t.getInstance());
	}
}
