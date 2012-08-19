package org.jllvm;

import org.jllvm.bindings.ExecutionEngine;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueGenericValue;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueExecutionEngine;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueExecutionEngine;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueValue;
import org.jllvm.bindings.SWIGTYPE_p_p_char;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueGenericValue;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueModule;
import org.jllvm.bindings.SWIGTYPE_p_void;

public class LLVMExecutionEngine {
	protected SWIGTYPE_p_LLVMOpaqueExecutionEngine instance;
  
	public LLVMExecutionEngine(LLVMModule mod) throws Exception {
		SWIGTYPE_p_p_LLVMOpaqueExecutionEngine engines = ExecutionEngine.new_LLVMExecutionEngineRefArray(1);
		SWIGTYPE_p_p_char outerrs = ExecutionEngine.new_StringArray(1);
		boolean success = ExecutionEngine.LLVMCreateExecutionEngineForModule(engines,mod.getInstance(),outerrs) > 0;
		String outerr = ExecutionEngine.StringArray_getitem(outerrs,0);
		ExecutionEngine.delete_StringArray(outerrs); outerrs = null;
		instance = ExecutionEngine.LLVMExecutionEngineRefArray_getitem(engines,0);
		ExecutionEngine.delete_LLVMExecutionEngineRefArray(engines); engines = null;
		if(!success)
			throw new Exception(outerr);
	}
	
	public static void linkInJit() {
		ExecutionEngine.LLVMLinkInJIT();
	}

	public static void linkInInterpreter() {
		ExecutionEngine.LLVMLinkInInterpreter();
	}
	
	public SWIGTYPE_p_LLVMOpaqueExecutionEngine getInstance() {
		return instance;
	}
	
	public void runStaticConstructors() {
		ExecutionEngine.LLVMRunStaticConstructors(instance);
	}
	
	public void runStaticDestructors() {
		ExecutionEngine.LLVMRunStaticDestructors(instance);
	}
	
	public int runFunctionAsMain(LLVMFunction f,String[] argv,String[] envp) {
		SWIGTYPE_p_p_char args = ExecutionEngine.new_StringArray(argv.length);
		SWIGTYPE_p_p_char envs = ExecutionEngine.new_StringArray(envp.length);
		for(int i=0;i<argv.length;i++)
			ExecutionEngine.StringArray_setitem(args,i,argv[i]);
		for(int i=0;i<envp.length;i++)
			ExecutionEngine.StringArray_setitem(envs,i,envp[i]);
		int result = ExecutionEngine.LLVMRunFunctionAsMain(instance,f.getInstance(),argv.length,args,envs);
		ExecutionEngine.delete_StringArray(envs);
		ExecutionEngine.delete_StringArray(args);
		return result;
	}
	
	public LLVMGenericValue runFunction(LLVMFunction f,LLVMGenericValue[] args) {
		SWIGTYPE_p_p_LLVMOpaqueGenericValue arg_array = ExecutionEngine.new_LLVMGenericValueRefArray(args.length);
		for(int i=0;i<args.length;i++)
			ExecutionEngine.LLVMGenericValueRefArray_setitem(arg_array,i,args[i].getInstance());
		SWIGTYPE_p_LLVMOpaqueGenericValue res = ExecutionEngine.LLVMRunFunction(instance,f.getInstance(),args.length,arg_array);
		ExecutionEngine.delete_LLVMGenericValueRefArray(arg_array);
		return new LLVMGenericValue(res);
	}
	
	public void freeMachineCodeForFunction(LLVMFunction f) {
		ExecutionEngine.LLVMFreeMachineCodeForFunction(instance,f.getInstance());
	}
	
	public void addModule(LLVMModule m) {
		ExecutionEngine.LLVMAddModule(instance,m.getInstance());
	}
	
	public boolean removeModule(LLVMModule m) {
		SWIGTYPE_p_p_char outerr = ExecutionEngine.new_StringArray(1);
		SWIGTYPE_p_p_LLVMOpaqueModule outmod = ExecutionEngine.new_LLVMModuleRefArray(1);
		boolean result = ExecutionEngine.LLVMRemoveModule(instance,m.getInstance(),outmod,outerr) > 0;
		ExecutionEngine.delete_LLVMModuleRefArray(outmod);
		ExecutionEngine.delete_StringArray(outerr);
		return result;
	}
	
	public LLVMFunction findFunction(String name) {
		SWIGTYPE_p_p_LLVMOpaqueValue resarray = ExecutionEngine.new_LLVMValueRefArray(1);
		boolean success = ExecutionEngine.LLVMFindFunction(instance,name,resarray) > 0;
		SWIGTYPE_p_LLVMOpaqueValue result = ExecutionEngine.LLVMValueRefArray_getitem(resarray,0);
		ExecutionEngine.delete_LLVMValueRefArray(resarray);
		if(success && result != null)
			return new LLVMFunction(result);
		else
			return null;
	}
	
	public LLVMTargetData getTargetData() {
		return new LLVMTargetData(ExecutionEngine.LLVMGetExecutionEngineTargetData(instance));
	}
	
	public void addGlobalMapping(LLVMValue v,SWIGTYPE_p_void addr) {
		ExecutionEngine.LLVMAddGlobalMapping(instance,v.getInstance(),addr);
	}
	
	public SWIGTYPE_p_void getPointerToGlobal(LLVMValue v) {
		return ExecutionEngine.LLVMGetPointerToGlobal(instance,v.getInstance());
	}
	
	protected void finalize() {
		ExecutionEngine.LLVMDisposeExecutionEngine(instance);
	}
}
