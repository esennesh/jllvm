package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMConstantString extends LLVMConstant {
	public LLVMConstantString(String str,boolean nullTerminate) {
		super(Core.LLVMConstString(str,str.length(),nullTerminate ? 0 : 1));
	}
}
