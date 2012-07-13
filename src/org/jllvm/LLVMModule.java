package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueModule;
import java.util.*;

/* Fully translated all functions for Modules from Core.java/Core.h back to a Module class. */
public class LLVMModule {
	protected static HashMap<SWIGTYPE_p_LLVMOpaqueModule,LLVMModule> llvm_modules;
	protected SWIGTYPE_p_LLVMOpaqueModule instance;
	protected String identifier;
	
	public SWIGTYPE_p_LLVMOpaqueModule getInstance() {
		return instance;
	}
	
	public String getModuleIdentifier() {
		return identifier;
	}
	
	public String getDataLayout() {
		return Core.LLVMGetDataLayout(instance);
	}
	
	public void setDataLayout(String triple) {
		Core.LLVMSetDataLayout(instance,triple);
	}
	
	public String getTargetTriple() {
		return Core.LLVMGetTarget(instance);
	} 
	
	public void setTargetTriple(String triple) {
		Core.LLVMSetTarget(instance,triple);
	}
	
	public void dump() {
		Core.LLVMDumpModule(instance);
	}
	
	public LLVMGlobalVariable addGlobal(LLVMType type,String name) {
		return new LLVMGlobalVariable(Core.LLVMAddGlobal(instance,type.getInstance(),name));
	}
	
	public LLVMGlobalVariable getNamedGlobal(String name) {
		return LLVMGlobalVariable.getGlobalVariable(Core.LLVMGetNamedGlobal(instance,name));
	}
	
	public LLVMGlobalVariable getFirstGlobal() {
		return LLVMGlobalVariable.getGlobalVariable(Core.LLVMGetFirstGlobal(instance));
	}
	
	public LLVMGlobalVariable getLastGlobal() {
		return LLVMGlobalVariable.getGlobalVariable(Core.LLVMGetLastGlobal(instance));
	}
	
	public LLVMFunction getNamedFunction(String name) {
		return LLVMFunction.getFunction(Core.LLVMGetNamedFunction(instance,name));
	}
	
	public LLVMFunction getFirstFunction() {
		return LLVMFunction.getFunction(Core.LLVMGetFirstFunction(instance));
	}
	
	public LLVMFunction getLastFunction() {
		return LLVMFunction.getFunction(Core.LLVMGetLastFunction(instance));
	}
	
	public LLVMContext getContext() {
		return LLVMContext.getContext(Core.LLVMGetModuleContext(instance));
	}
	
	public LLVMModule(String moduleid) {
		instance = Core.LLVMModuleCreateWithName(moduleid);
		identifier = moduleid;
		if(llvm_modules == null)
			llvm_modules = new HashMap<SWIGTYPE_p_LLVMOpaqueModule,LLVMModule>();
		llvm_modules.put(instance,this);
	}
	
	public static LLVMModule getModule(SWIGTYPE_p_LLVMOpaqueModule m) {
		if(llvm_modules == null)
			llvm_modules = new HashMap<SWIGTYPE_p_LLVMOpaqueModule,LLVMModule>();
		LLVMModule result = llvm_modules.get(m);
		assert(result != null);
		return result;
	}
	
	protected void finalize() {
		Core.LLVMDisposeModule(instance);
	}
}
