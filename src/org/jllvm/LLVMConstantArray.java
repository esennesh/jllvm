package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueValue;

public class LLVMConstantArray extends LLVMConstantAggregate {
	public LLVMConstantArray(LLVMType elementType,LLVMConstant[] elements) {
		SWIGTYPE_p_p_LLVMOpaqueValue params = Core.new_LLVMValueRefArray(elements.length);
		for(int i=0;i<elements.length;i++)
			Core.LLVMValueRefArray_setitem(params,i,elements[i].instance);
		SWIGTYPE_p_LLVMOpaqueValue array = Core.LLVMConstArray(elementType.getInstance(),params,elements.length);
		Core.delete_LLVMValueRefArray(params);
		assert(Core.LLVMIsConstant(array) != 0);
		instance = array;
	}
}
