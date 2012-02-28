package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueModuleProvider;

public class LLVMModuleProvider {
	protected SWIGTYPE_p_LLVMOpaqueModuleProvider instance;
	
	public SWIGTYPE_p_LLVMOpaqueModuleProvider getInstance() {
		return instance;
	}
	
	public LLVMModuleProvider(LLVMModule mod) {
		instance = Core.LLVMCreateModuleProviderForExistingModule(mod.getInstance());
	}
	
	protected void finalize() {
		Core.LLVMDisposeModuleProvider(instance);
	}
}
