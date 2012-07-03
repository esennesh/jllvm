package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueValue;

public class LLVMConstantVector extends LLVMConstant {
	public LLVMConstantVector(LLVMConstant[] elements) {
		SWIGTYPE_p_p_LLVMOpaqueValue params = Core.new_LLVMValueRefArray(elements.length);
		for(int i=0;i<elements.length;i++)
			Core.LLVMValueRefArray_setitem(params,i,elements[i].instance);
		instance = Core.LLVMConstVector(params,elements.length);
		Core.delete_LLVMValueRefArray(params);
		assert(Core.LLVMIsConstant(instance) != 0);
	}
	
	public LLVMConstantExpression extractElement(LLVMConstantInteger index) {
		return new LLVMConstantExpression(Core.LLVMConstExtractElement(instance,index.instance));
	}
	
	public LLVMConstantExpression insertElement(LLVMConstant element,LLVMConstantInteger index) {
		assert(element.typeOf().equals(((LLVMVectorType)typeOf()).getElementType()));
		return new LLVMConstantExpression(Core.LLVMConstInsertElement(instance,element.instance,index.instance));
	}
}
