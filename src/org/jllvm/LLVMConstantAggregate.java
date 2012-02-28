package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueValue;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;
import org.jllvm.bindings.SWIGTYPE_p_unsigned_int;

public abstract class LLVMConstantAggregate extends LLVMConstant {
	public LLVMConstantExpression extractValue(long[] indices) {
		SWIGTYPE_p_unsigned_int params = Core.new_UnsignedIntArray(indices.length);
		for(int i=0;i<indices.length;i++)
			Core.UnsignedIntArray_setitem(params,i,indices[i]);
		LLVMConstantExpression result = new LLVMConstantExpression(Core.LLVMConstExtractValue(instance,params,indices.length));
		Core.delete_UnsignedIntArray(params);
		return result;
	}
	
	public LLVMConstantExpression insertValue(LLVMConstant value,long[] indices) {
		SWIGTYPE_p_unsigned_int params = Core.new_UnsignedIntArray(indices.length);
		for(int i=0;i<indices.length;i++)
			Core.UnsignedIntArray_setitem(params,i,indices[i]);
		LLVMConstantExpression result = new LLVMConstantExpression(Core.LLVMConstInsertValue(instance,value.instance,params,indices.length));
		Core.delete_UnsignedIntArray(params);
		return result;
	}
}
