package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueType;
import org.jllvm.bindings.LLVMTypeKind;
import java.util.*;

/* Implements every method specified in Core.h for function types. */
public class LLVMFunctionType extends LLVMType {
	public LLVMFunctionType(LLVMType ReturnType,LLVMType[] ParamTypes,boolean isVarArg) {
		//Use the functions SWIG wrote for me to make a C-style array of LLVMTypeRefs.
		SWIGTYPE_p_p_LLVMOpaqueType params = Core.new_LLVMTypeRefArray(ParamTypes.length);
		//Populate that array.
		for(int i=0;i<ParamTypes.length;i++)
			Core.LLVMTypeRefArray_setitem(params,i,ParamTypes[i].getInstance());
		//Pass it to LLVMFunctionType().
		SWIGTYPE_p_LLVMOpaqueType tr = Core.LLVMFunctionType(ReturnType.getInstance(),params,ParamTypes.length,(isVarArg ? 1 : 0));
		//And delete it.  tr now contains the new opaque reference to a function type.
		Core.delete_LLVMTypeRefArray(params);
		//Proceed with the constructor as normal.
		instance = tr;
		llvm_types.put(instance,this);
	}
	
	public LLVMFunctionType(SWIGTYPE_p_LLVMOpaqueType t) {
		super(t);
		assert(Core.LLVMGetTypeKind(t) == LLVMTypeKind.LLVMFunctionTypeKind);
	}
	
	public boolean isVarArg() {
		return Core.LLVMIsFunctionVarArg(instance) != 0;
	}
	
	public LLVMType getReturnType() {
		return LLVMType.getType(Core.LLVMGetReturnType(instance));
	}
	
	public long countParamTypes() {
		return Core.LLVMCountParamTypes(instance);
	}
	
	public LLVMType[] getParamTypes() {
		//Make a C-style array of LLVMTypeRefs.
		int num_params = (int)countParamTypes();
		SWIGTYPE_p_p_LLVMOpaqueType params = Core.new_LLVMTypeRefArray(num_params);
		//Pass that array to the actual function.
		Core.LLVMGetParamTypes(instance,params);
		//Create the resulting array of neat LLVMType objects.
		LLVMType[] result = new LLVMType[num_params];
		for(int i=0;i<num_params;i++)
			result[i] = LLVMType.getType(Core.LLVMTypeRefArray_getitem(params,i));
		//Delete the temporary C-style array.  The garbage collector doesn't know about it.
		Core.delete_LLVMTypeRefArray(params);
		return result;
	}
}
