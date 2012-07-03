package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueValue;
import org.jllvm.LLVMUser;

/* Implements all the methods from Core.h for constants. */
public class LLVMConstant extends LLVMUser {
	public boolean isNullValue() {
		return Core.LLVMIsNull(instance) != 0;
	}
	
	public static LLVMConstant allOnes(LLVMType type) {
		return new LLVMConstant(Core.LLVMConstAllOnes(type.getInstance()));
	}
	
	public static LLVMConstant constNull(LLVMType type) {
		SWIGTYPE_p_LLVMOpaqueValue val = Core.LLVMConstNull(type.getInstance());
		if(type instanceof LLVMPointerType)
			return new LLVMConstantPointer(val);
		else
			return new LLVMConstant(val);
	}
	
	public static LLVMConstant getUndef(LLVMType type) {
		return new LLVMConstant(Core.LLVMGetUndef(type.getInstance()));
	}
	
	public boolean isUndef() {
		return Core.LLVMIsUndef(instance) != 0;
	}
	
	public LLVMConstantExpression negative() {
		return new LLVMConstantExpression(Core.LLVMConstNeg(instance));
	}
	
	public LLVMConstantExpression not() {
		return new LLVMConstantExpression(Core.LLVMConstNot(instance));
	}
	
	public LLVMConstantExpression getElementPointer(LLVMConstant[] indices) {
		SWIGTYPE_p_p_LLVMOpaqueValue params = Core.new_LLVMValueRefArray(indices.length);
		for(int i=0;i<indices.length;i++)
			Core.LLVMValueRefArray_setitem(params,i,indices[i].instance);
		LLVMConstantExpression result = new LLVMConstantExpression(Core.LLVMConstGEP(instance,params,indices.length));
		Core.delete_LLVMValueRefArray(params);
		return result;
	}
	
	public LLVMConstantExpression bitCast(LLVMType targetType) {
		return new LLVMConstantExpression(Core.LLVMConstBitCast(instance,targetType.getInstance()));
	}
	
	protected LLVMConstant() {
		instance = null;
	}
	
	public LLVMConstant(SWIGTYPE_p_LLVMOpaqueValue val) {
		super(val);
		assert(Core.LLVMIsConstant(val) != 0);
	}
}
