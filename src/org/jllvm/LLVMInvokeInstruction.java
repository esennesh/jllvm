package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueValue;
import org.jllvm.LLVMTerminatorInstruction;

public class LLVMInvokeInstruction extends LLVMTerminatorInstruction {
	public LLVMInvokeInstruction(LLVMInstructionBuilder builder,LLVMFunction func,LLVMValue[] arguments,LLVMBasicBlock destination,LLVMBasicBlock unwind,String name) {
		SWIGTYPE_p_p_LLVMOpaqueValue argvalues = Core.new_LLVMValueRefArray(arguments.length);
		for(int i=0;i<arguments.length;i++)
			Core.LLVMValueRefArray_setitem(argvalues,i,arguments[i].getInstance());
		instance = Core.LLVMBuildInvoke(builder.getInstance(),func.getInstance(),argvalues,(long)arguments.length,destination.getBBInstance(),unwind.getBBInstance(),name);
		Core.delete_LLVMValueRefArray(argvalues);
		llvm_values.put(instance,this);
	}
}
