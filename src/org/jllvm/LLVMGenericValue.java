package org.jllvm;

import org.jllvm.bindings.ExecutionEngine;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueGenericValue;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueGenericValue;

public class LLVMGenericValue {
	protected SWIGTYPE_p_LLVMOpaqueGenericValue instance;
	
	public LLVMGenericValue(SWIGTYPE_p_LLVMOpaqueGenericValue val) {
		instance = val;
	}
	
	protected void finalize() {
		ExecutionEngine.LLVMDisposeGenericValue(instance);
	}
	
	public SWIGTYPE_p_LLVMOpaqueGenericValue getInstance() {
		return instance;
	}
}
