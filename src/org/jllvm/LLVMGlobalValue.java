package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;
import org.jllvm.bindings.LLVMLinkage;
import org.jllvm.bindings.LLVMVisibility;
import org.jllvm.LLVMConstant;

/* Implements every function from Core.h dealing with global values. */
public class LLVMGlobalValue extends LLVMConstant {
	public LLVMModule getParent() {
		return LLVMModule.getModule(Core.LLVMGetGlobalParent(instance));
	}
	
	public boolean isDeclaration() {
		return Core.LLVMIsDeclaration(instance) != 0;
	}
	
	public LLVMLinkage getLinkage() {
		return Core.LLVMGetLinkage(instance);
	}
	
	public void setLinkage(LLVMLinkage l) {
		Core.LLVMSetLinkage(instance,l);
	}
	
	public String getSection() {
		return Core.LLVMGetSection(instance);
	}
	
	public void setSection(String section) {
		Core.LLVMSetSection(instance,section);
	}
	
	public LLVMVisibility getVisibility() {
		return Core.LLVMGetVisibility(instance);
	}
	
	public void setVisibility(LLVMVisibility v) {
		Core.LLVMSetVisibility(instance,v);
	}
	
	public long getAlignment() {
		return Core.LLVMGetAlignment(instance);
	}
	
	public void setAlignment(long bytes) {
		Core.LLVMSetAlignment(instance,bytes);
	}
	
	public boolean isNullValue() {
		return false;
	}
	
	public LLVMGlobalValue(SWIGTYPE_p_LLVMOpaqueValue val) {
		super(Core.LLVMIsAGlobalValue(val));
	}
}
