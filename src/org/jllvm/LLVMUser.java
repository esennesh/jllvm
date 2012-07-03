package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;
import org.jllvm.LLVMValue;

/* Core.h doesn't specify anything about the type User, so there's pretty much nothing here. */
public class LLVMUser extends LLVMValue {
	protected LLVMUser() {
		instance = null;
	}
	
	public LLVMUser(SWIGTYPE_p_LLVMOpaqueValue val) {
		super(Core.LLVMIsAUser(val));
	}
}
