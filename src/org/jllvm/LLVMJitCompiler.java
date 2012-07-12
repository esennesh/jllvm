package org.jllvm;

import org.jllvm.bindings.ExecutionEngine;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueExecutionEngine;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueExecutionEngine;
import org.jllvm.bindings.SWIGTYPE_p_p_char;

public class LLVMJitCompiler extends LLVMExecutionEngine {
	public LLVMJitCompiler(LLVMModule mod) throws Exception {
		super(null);
		SWIGTYPE_p_p_LLVMOpaqueExecutionEngine engines = ExecutionEngine.new_LLVMExecutionEngineRefArray(1);
		SWIGTYPE_p_p_char outerrs = ExecutionEngine.new_StringArray(1);
		boolean success = ExecutionEngine.LLVMCreateJITCompilerForModule(engines,mod.getInstance(),0,outerrs);
		String outerr = ExecutionEngine.StringArray_getitem(outerrs,0);
		ExecutionEngine.delete_StringArray(outerrs); outerrs = null;
		instance = ExecutionEngine.LLVMExecutionEngineRefArray_getitem(engines,0);
		ExecutionEngine.delete_LLVMExecutionEngineRefArray(engines); engines = null;
		if(!success)
			throw new Exception(outerr);
	}
}
