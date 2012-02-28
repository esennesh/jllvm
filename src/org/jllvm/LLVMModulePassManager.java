package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMModulePassManager extends LLVMPassManager {
	public LLVMModulePassManager() {
		instance = Core.LLVMCreatePassManager();
	}
	
	public boolean run(LLVMModule val) {
		return (Core.LLVMRunPassManager(instance,val.getInstance()) == 1);
	}
}
