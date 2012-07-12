package org.jllvm;

import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueTargetData;
import org.jllvm.bindings.Target;
import org.jllvm.bindings.LLVMByteOrdering;
import java.math.BigInteger;

public class LLVMTargetData {
	protected SWIGTYPE_p_LLVMOpaqueTargetData instance;
	
	public SWIGTYPE_p_LLVMOpaqueTargetData getInstance() {
		return instance;
	}
	
	public static void initializeAllTargetInfos() {
		Target.LLVMInitializeAllTargetInfos();
	}
	
	public static void initializeAllTargets() {
		Target.LLVMInitializeAllTargets();
	}

	public static boolean initializeNativeTarget() {
		return Target.LLVMInitializeNativeTarget() != 0;
	}
	
	public String stringRepresentation() {
		return Target.LLVMCopyStringRepOfTargetData(instance);
	}
	
	public LLVMByteOrdering getByteOrdering() {
		return Target.LLVMByteOrder(instance);
	}
	
	public long getPointerSize() {
		return Target.LLVMPointerSize(instance);
	}
	
	public LLVMIntegerType intPtrType() {
		return new LLVMIntegerType(Target.LLVMIntPtrType(instance));
	}
	
	public long sizeOfTypeInBits(LLVMType t) {
		return Target.LLVMSizeOfTypeInBits(instance,t.getInstance()).longValue();
	}
	
	public long storeSizeOfType(LLVMType t) {
		return Target.LLVMStoreSizeOfType(instance,t.getInstance()).longValue();
	}
	
	public long abiSizeOfType(LLVMType t) {
		return Target.LLVMABISizeOfType(instance,t.getInstance()).longValue();
	}
	
	public long abiAlignmentOfType(LLVMType t) {
		return Target.LLVMABIAlignmentOfType(instance,t.getInstance());
	}
	
	public long callFrameAlignmentOfType(LLVMType t) {
		return Target.LLVMCallFrameAlignmentOfType(instance,t.getInstance());
	}
	
	public long preferredAlignmentOfType(LLVMType t) {
		return Target.LLVMPreferredAlignmentOfType(instance,t.getInstance());
	}
	
	public long preferredAlignmentOfGlobal(LLVMGlobalValue global) {
		return Target.LLVMPreferredAlignmentOfGlobal(instance,global.getInstance());
	}
	
	public long elementAtOffset(LLVMStructType struct,long offset) {
		return Target.LLVMElementAtOffset(instance,struct.getInstance(),BigInteger.valueOf(offset));
	}
	
	public long offsetOfElement(LLVMStructType struct,int element) {
		return Target.LLVMOffsetOfElement(instance,struct.getInstance(),element).longValue();
	}
	
	public LLVMTargetData(SWIGTYPE_p_LLVMOpaqueTargetData td) {
		assert(td != null);
		instance = td;
	}
	
	public LLVMTargetData(String stringRep) {
		instance = Target.LLVMCreateTargetData(stringRep);
		assert(instance != null);
	}
	
	protected void finalize() {
		Target.LLVMDisposeTargetData(instance);
	} 
}
