package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.LLVMTypeKind;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import java.util.*;

/* Implements all methods from Core.h dealing with the base class Type. */
public class LLVMType {
	protected static HashMap<SWIGTYPE_p_LLVMOpaqueType,LLVMType> llvm_types;
	protected SWIGTYPE_p_LLVMOpaqueType instance;
	
	public LLVMTypeKind getTypeKind() {
		return Core.LLVMGetTypeKind(instance);
	}
	
	public SWIGTYPE_p_LLVMOpaqueType getInstance() {
		return instance;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LLVMType)
			return ((LLVMType)obj).instance == instance;
		else
			return false;
	}
	
	protected LLVMType() {
		instance = null;
		if(llvm_types == null)
			llvm_types = new HashMap<SWIGTYPE_p_LLVMOpaqueType,LLVMType>();
	}
	
	public LLVMType(SWIGTYPE_p_LLVMOpaqueType tr) {
		instance = tr;
		if(llvm_types == null)
			llvm_types = new HashMap<SWIGTYPE_p_LLVMOpaqueType,LLVMType>();
		llvm_types.put(instance,this);
	}
	
	public static LLVMType getType(SWIGTYPE_p_LLVMOpaqueType tr) {
		if(llvm_types == null)
			llvm_types = new HashMap<SWIGTYPE_p_LLVMOpaqueType,LLVMType>();
		LLVMType result = llvm_types.get(tr);
		if(result == null) {
			LLVMTypeKind kind = Core.LLVMGetTypeKind(tr);
			if(kind == LLVMTypeKind.LLVMVoidTypeKind)
				result = new LLVMVoidType(tr);
			else if(kind == LLVMTypeKind.LLVMFloatTypeKind)
				result = new LLVMFloatType();
			else if(kind == LLVMTypeKind.LLVMDoubleTypeKind)
				result = new LLVMDoubleType();
			else if(kind == LLVMTypeKind.LLVMX86_FP80TypeKind)
				result = new LLVMX86FP80Type(tr);
			else if(kind == LLVMTypeKind.LLVMFP128TypeKind)
				result = new LLVMFP128Type();
			else if(kind == LLVMTypeKind.LLVMPPC_FP128TypeKind)
				result = new LLVMPPCFP128Type(tr);
			else if(kind == LLVMTypeKind.LLVMLabelTypeKind)
				result = new LLVMLabelType();
			else if(kind == LLVMTypeKind.LLVMIntegerTypeKind)
				result = new LLVMIntegerType(tr);
			else if(kind == LLVMTypeKind.LLVMFunctionTypeKind)
				result = new LLVMFunctionType(tr);
			else if(kind == LLVMTypeKind.LLVMStructTypeKind)
				result = new LLVMStructType(tr);
			else if(kind == LLVMTypeKind.LLVMArrayTypeKind)
				result = new LLVMArrayType(tr);
			else if(kind == LLVMTypeKind.LLVMPointerTypeKind)
				result = new LLVMPointerType(tr);
			else if(kind == LLVMTypeKind.LLVMVectorTypeKind)
				result = new LLVMVectorType(tr);
			/*else if(kind == LLVMMetadataTypeKind)
				result = new LLVMMetadataType();*/
		}
		assert(result != null);
		return result;
	}
}
