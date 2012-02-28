package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.LLVMInstruction;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueValue;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueBasicBlock;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueBasicBlock;

public class LLVMPhiNode extends LLVMInstruction {
	public LLVMPhiNode(LLVMInstructionBuilder builder,LLVMType type,String name) {
		instance = Core.LLVMBuildPhi(builder.getInstance(),type.getInstance(),name);
	}
	
	public void addIncoming(LLVMValue[] values,LLVMBasicBlock[] blocks) {
		assert(values.length == blocks.length);
		SWIGTYPE_p_p_LLVMOpaqueBasicBlock blockArray = Core.new_LLVMBasicBlockRefArray(values.length);
		for(int i=0;i<values.length;i++)
			Core.LLVMBasicBlockRefArray_setitem(blockArray,i,blocks[i].getBBInstance());
		SWIGTYPE_p_p_LLVMOpaqueValue valueArray = Core.new_LLVMValueRefArray(values.length);
		for(int i=0;i<values.length;i++)
			Core.LLVMValueRefArray_setitem(valueArray,i,values[i].getInstance());
		Core.LLVMAddIncoming(instance,valueArray,blockArray,values.length);
		Core.delete_LLVMValueRefArray(valueArray);
		Core.delete_LLVMBasicBlockRefArray(blockArray);
	}
	
	public long countIncoming() {
		return Core.LLVMCountIncoming(instance);
	}
	
	public LLVMValue getIncomingValue(long index) {
		assert(index >= 0);
		return LLVMValue.getValue(Core.LLVMGetIncomingValue(instance,index));
	}
	
	public LLVMBasicBlock getIncomingBlock(long index) {
		assert(index >= 0);
		return LLVMBasicBlock.getBasicBlock(Core.LLVMGetIncomingBlock(instance,index));
	}
}
