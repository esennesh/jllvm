package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueContext;
import java.util.*;

public class LLVMContext {
	protected static HashMap<SWIGTYPE_p_LLVMOpaqueContext,LLVMContext> llvm_contexts;
	protected SWIGTYPE_p_LLVMOpaqueContext instance;
	
	public static LLVMContext getContext(SWIGTYPE_p_LLVMOpaqueContext c) {
		if(llvm_contexts == null)
			llvm_contexts = new HashMap<SWIGTYPE_p_LLVMOpaqueContext,LLVMContext>();
		LLVMContext result = llvm_contexts.get(c);
		if(result == null) {
			result = new LLVMContext(c);
			llvm_contexts.put(c,result);
		}
		assert(result != null);
		return result;
	}
	
	public LLVMContext() {
		instance = Core.LLVMContextCreate();
	}
	
	protected LLVMContext(SWIGTYPE_p_LLVMOpaqueContext c) {
		instance = c;
	}
	
	public SWIGTYPE_p_LLVMOpaqueContext getInstance() {
		return instance;
	}
	
	public long getMetadataKindID(String name) {
		return Core.LLVMGetMDKindIDInContext(instance,name,name.length());
	}
	
	public static LLVMContext getGlobalContext() {
		return getContext(Core.LLVMGetGlobalContext());
	}
	
	protected void finalize() {
		if(instance != Core.LLVMGetGlobalContext())
			Core.LLVMContextDispose(instance);
	}
}
