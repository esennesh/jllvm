package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;
import java.util.*;

/* Implements all methods from Core.h dealing with the base class Value. */
public class LLVMValue {
	protected static HashMap<SWIGTYPE_p_LLVMOpaqueValue,LLVMValue> llvm_values;
	protected SWIGTYPE_p_LLVMOpaqueValue instance;
	
	protected LLVMValue() {
		instance = null;
		if(llvm_values == null)
			llvm_values = new HashMap<SWIGTYPE_p_LLVMOpaqueValue,LLVMValue>();
	}
	
	public LLVMValue(SWIGTYPE_p_LLVMOpaqueValue val) {
		assert(val != null);
		instance = val;
		if(llvm_values == null)
			llvm_values = new HashMap<SWIGTYPE_p_LLVMOpaqueValue,LLVMValue>();
		llvm_values.put(instance,this);
	}
	
	public LLVMType typeOf() {
		return LLVMType.getType(Core.LLVMTypeOf(instance));
	}
	
	public String getValueName() {
		return Core.LLVMGetValueName(instance);
	}
	
	public void setValueName(String name) {
		Core.LLVMSetValueName(instance,name);
	}
	
	public void dump() {
		Core.LLVMDumpValue(instance);
	}
	
	public SWIGTYPE_p_LLVMOpaqueValue getInstance() {
		return instance;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LLVMValue)
			return ((LLVMValue)obj).instance == instance;
		else
			return false;
	}
	
	public static LLVMValue getValue(SWIGTYPE_p_LLVMOpaqueValue val) {
		if(llvm_values == null)
			llvm_values = new HashMap<SWIGTYPE_p_LLVMOpaqueValue,LLVMValue>();
		LLVMValue result = llvm_values.get(val);
		return result;
	}
	
	protected void finalize() {
		llvm_values.remove(instance);
	}
}
