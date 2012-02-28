package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueValue;

public class LLVMConstantStruct extends LLVMConstantAggregate {
	public LLVMConstantStruct(LLVMConstant[] elements,boolean packed) {
		SWIGTYPE_p_p_LLVMOpaqueValue params = Core.new_LLVMValueRefArray(elements.length);
		for(int i=0;i<elements.length;i++)
			Core.LLVMValueRefArray_setitem(params,i,elements[i].instance);
		SWIGTYPE_p_LLVMOpaqueValue struct = Core.LLVMConstStruct(params,elements.length,packed ? 1 : 0);
		Core.delete_LLVMValueRefArray(params);
		assert(Core.LLVMIsConstant(struct) != 0);
		instance = struct;
	}
}
