package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueBasicBlock;
import java.util.*;

public class LLVMBasicBlock extends LLVMValue {
	public LLVMBasicBlock(SWIGTYPE_p_LLVMOpaqueValue val) {
		assert(Core.LLVMValueIsBasicBlock(val) != 0);
		instance = val;
		llvm_values.put(instance,this);
	}
	
	public LLVMBasicBlock(SWIGTYPE_p_LLVMOpaqueBasicBlock bb) {
		instance = Core.LLVMBasicBlockAsValue(bb);
		assert(Core.LLVMValueIsBasicBlock(instance) != 0);
		llvm_values.put(instance,this);
	}
	
	public SWIGTYPE_p_LLVMOpaqueBasicBlock getBBInstance() {
		return Core.LLVMValueAsBasicBlock(instance);
	}
	
	public LLVMFunction getParent() {
		SWIGTYPE_p_LLVMOpaqueBasicBlock bb = getBBInstance();
		return LLVMFunction.getFunction(Core.LLVMGetBasicBlockParent(bb));
	}
	
	public LLVMBasicBlock getNextBasicBlock() {
		return getBasicBlock(Core.LLVMGetNextBasicBlock(getBBInstance()));
	}
	
	public LLVMBasicBlock getPreviousBasicBlock() {
		return getBasicBlock(Core.LLVMGetPreviousBasicBlock(getBBInstance()));
	}
	
	public LLVMBasicBlock insertBasicBlockBefore(String name) {
		return new LLVMBasicBlock(Core.LLVMInsertBasicBlock(getBBInstance(),name));
	}
	
	public LLVMInstruction getFirstInstruction() {
		return new LLVMInstruction(Core.LLVMGetFirstInstruction(getBBInstance()));
	}
	
	public LLVMInstruction getLastInstruction() {
		return new LLVMInstruction(Core.LLVMGetLastInstruction(getBBInstance()));
	}

	public static LLVMBasicBlock getBasicBlock(SWIGTYPE_p_LLVMOpaqueValue val) {
		assert(val != null);
		LLVMValue possibility = LLVMValue.getValue(val);
		if(possibility == null)
			return new LLVMBasicBlock(val);
		else
			return (LLVMBasicBlock)possibility;
	}

	public static LLVMBasicBlock getBasicBlock(SWIGTYPE_p_LLVMOpaqueBasicBlock bb) {
		assert(bb != null);
		return getBasicBlock(Core.LLVMBasicBlockAsValue(bb));
	}
	
	protected void finalize() {
		super.finalize();
		Core.LLVMDeleteBasicBlock(getBBInstance());
	}
}
