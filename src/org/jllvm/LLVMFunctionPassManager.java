package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMFunctionPassManager extends LLVMPassManager {
	public LLVMFunctionPassManager(LLVMModuleProvider provider) {
		instance = Core.LLVMCreateFunctionPassManager(provider.getInstance());
	}
	
	public boolean initialize() {
		return (1 == Core.LLVMInitializeFunctionPassManager(instance));
	}
	
	public boolean run(LLVMFunction function) {
		return (1 == Core.LLVMRunFunctionPassManager(instance,function.getInstance()));
	}
	
	public boolean finish() {
		return (1 == Core.LLVMFinalizeFunctionPassManager(instance));
	}
	
	public void finalize() {
		finish();
		Core.LLVMDisposePassManager(instance);
	}
}
