package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueType;
import org.jllvm.bindings.LLVMTypeKind;
import java.util.*;
import org.jllvm.LLVMStructType;

/* Implements operations on identified struct types from Core.h */
public class LLVMIdentifiedStructType extends LLVMStructType {
	//Creates an identified struct, which can later become recursive
	public LLVMIdentifiedStructType(LLVMContext context, String name) {
		instance = Core.LLVMStructCreateNamed(context.getInstance(),name);
	}
	
	public LLVMIdentifiedStructType(LLVMContext context) {
		instance = Core.LLVMStructCreateNamed(context.getInstance(),"");
	}
	
	public LLVMIdentifiedStructType(String name) {
		instance = Core.LLVMStructCreateNamed(LLVMContext.getGlobalContext().getInstance(),name);
	}
	
	public LLVMIdentifiedStructType(SWIGTYPE_p_LLVMOpaqueType t) {
		super(t);
		assert(Core.LLVMGetTypeKind(t) == LLVMTypeKind.LLVMStructTypeKind);
	}
	
	public String getName() {
		return Core.LLVMGetStructName(instance);
	}
	
	public void setBody(LLVMType[] elementTypes,boolean packed) {
		SWIGTYPE_p_p_LLVMOpaqueType elements = Core.new_LLVMTypeRefArray(elementTypes.length);
		for(int i=0;i<elementTypes.length;i++)
			Core.LLVMTypeRefArray_setitem(elements,i,elementTypes[i].getInstance());
		Core.LLVMStructSetBody(instance,elements,elementTypes.length,packed);
		Core.delete_LLVMTypeRefArray(elements);
	}
	
	public boolean isOpaque() {
		return Core.LLVMIsOpaqueStruct(instance);
	}
}
