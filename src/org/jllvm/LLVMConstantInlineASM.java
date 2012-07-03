package org.jllvm;

import org.jllvm.bindings.Core;

public class LLVMConstantInlineASM extends LLVMConstantExpression {
	public LLVMConstantInlineASM(LLVMType type,String asm,String constraints,boolean hasSideEffects,boolean isAlignStack) {
		super(Core.LLVMConstInlineAsm(type.getInstance(),asm,constraints,hasSideEffects ? 1 : 0,isAlignStack ? 1 : 0));
	}
}
