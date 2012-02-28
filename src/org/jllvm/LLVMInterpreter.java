package org.jllvm;

import org.jllvm.bindings.ExecutionEngine;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueExecutionEngine;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueExecutionEngine;
import org.jllvm.bindings.SWIGTYPE_p_p_char;

public class LLVMInterpreter extends LLVMExecutionEngine {
	//LLVMCreateInterpreterForModule
	public LLVMInterpreter(LLVMModule mod) throws Exception {
		super(null);
		SWIGTYPE_p_p_LLVMOpaqueExecutionEngine engines = ExecutionEngine.new_LLVMExecutionEngineRefArray(1);
		SWIGTYPE_p_p_char outerrs = ExecutionEngine.new_StringArray(1);
		int success = ExecutionEngine.LLVMCreateInterpreterForModule(engines,mod.getInstance(),outerrs);
		String outerr = ExecutionEngine.StringArray_getitem(outerrs,0);
		ExecutionEngine.delete_StringArray(outerrs); outerrs = null;
		instance = ExecutionEngine.LLVMExecutionEngineRefArray_getitem(engines,0);
		ExecutionEngine.delete_LLVMExecutionEngineRefArray(engines); engines = null;
		if(success == 0)
			throw new Exception(outerr);
	}
}
