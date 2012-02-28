package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMFP128Type extends LLVMRealType {
	public LLVMFP128Type() {
		super(Core.LLVMFP128Type());
	}
}
