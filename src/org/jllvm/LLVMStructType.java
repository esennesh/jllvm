package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueType;
import org.jllvm.bindings.LLVMTypeKind;
import java.util.*;
import org.jllvm.LLVMType;

/* Implements all operations on struct types from Core.h */
public class LLVMStructType extends LLVMType {
	public LLVMStructType(LLVMType[] elements,boolean packed) {
		SWIGTYPE_p_p_LLVMOpaqueType elmnts = Core.new_LLVMTypeRefArray(elements.length);
		for(int i=0;i<elements.length;i++)
			Core.LLVMTypeRefArray_setitem(elmnts,i,elements[i].getInstance());
		SWIGTYPE_p_LLVMOpaqueType tr = Core.LLVMStructType(elmnts,elements.length,packed ? 0 : 1);
		Core.delete_LLVMTypeRefArray(elmnts);
		instance = tr;
		llvm_types.put(instance,this);
	}
	
	public LLVMStructType(SWIGTYPE_p_LLVMOpaqueType t) {
		super(t);
		assert(Core.LLVMGetTypeKind(t) == LLVMTypeKind.LLVMStructTypeKind);
	}
	
	public long countElementTypes() {
		return Core.LLVMCountStructElementTypes(instance);
	}
	
	public LLVMType[] getElementTypes() {
		int num_elements = (int)countElementTypes();
		SWIGTYPE_p_p_LLVMOpaqueType elements = Core.new_LLVMTypeRefArray(num_elements);
		Core.LLVMGetStructElementTypes(instance,elements);
		LLVMType[] result = new LLVMType[num_elements];
		for(int i=0;i<result.length;i++)
			result[i] = LLVMType.getType(Core.LLVMTypeRefArray_getitem(elements,i));
		Core.delete_LLVMTypeRefArray(elements);
		return result;
	}
	
	public boolean isPacked() {
		return Core.LLVMIsPackedStruct(instance) != 0;
	}
}
