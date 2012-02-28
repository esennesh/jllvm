package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.Target;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaquePassManager;

public abstract class LLVMPassManager {
	protected SWIGTYPE_p_LLVMOpaquePassManager instance;
	
	public SWIGTYPE_p_LLVMOpaquePassManager getInstance() {
		return instance;
	}
	
	public void addTargetData(LLVMTargetData target) {
		Target.LLVMAddTargetData(target.getInstance(),instance);
	}
	
	protected void finalize() {
		Core.LLVMDisposePassManager(instance);
	}
}
