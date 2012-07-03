package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueValue;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueBasicBlock;
import org.jllvm.bindings.SWIGTYPE_p_p_LLVMOpaqueBasicBlock;
import java.util.*;

public class LLVMFunction extends LLVMGlobalValue {
	public LLVMFunction(LLVMModule mod,String name,LLVMFunctionType funcType) {
		super(Core.LLVMAddFunction(mod != null ? mod.getInstance() : null,name,funcType.getInstance()));
		llvm_values.put(instance,this);
	}
	
	public LLVMFunction(SWIGTYPE_p_LLVMOpaqueValue val) {
		super(val);
		assert(Core.LLVMIsAFunction(val) != null);
	}
	
	public LLVMFunction getNextFunction() {
		return getFunction(Core.LLVMGetNextFunction(instance));
	}
	
	public LLVMFunction getPreviousFunction() {
		return getFunction(Core.LLVMGetPreviousFunction(instance));
	}
	
	public long getIntrinsicID() {
		return Core.LLVMGetIntrinsicID(instance);
	}
	
	public long getCallingConvention() {
		return Core.LLVMGetFunctionCallConv(instance);
	}
	
	public void setCallingConvention(long callconv) {
		assert(callconv >= 0);
		Core.LLVMSetFunctionCallConv(instance,callconv);
	}
	
	public String getGC() {
		return Core.LLVMGetGC(instance);
	}
	
	public void setGC(String name) {
		Core.LLVMSetGC(instance,name);
	}
	
	public long countParameters() {
		return Core.LLVMCountParams(instance);
	}
	
	public LLVMArgument[] getParameters() {
		int num_parameters = (int)countParameters();
		SWIGTYPE_p_p_LLVMOpaqueValue params = Core.new_LLVMValueRefArray(num_parameters);
		Core.LLVMGetParams(instance,params);
		LLVMArgument[] result = new LLVMArgument[num_parameters];
		for(int i=0;i<num_parameters;i++)
			result[i] = new LLVMArgument(Core.LLVMValueRefArray_getitem(params,i));
		Core.delete_LLVMValueRefArray(params);
		return result;
	}
	
	public LLVMArgument getParameter(int i) {
		return new LLVMArgument(Core.LLVMGetParam(instance,i));
	}
	
	public LLVMArgument getFirstParameter() {
		return new LLVMArgument(Core.LLVMGetFirstParam(instance));
	}
	
	public LLVMArgument getLastParameter() {
		return new LLVMArgument(Core.LLVMGetLastParam(instance));
	}
	
	public long countBasicBlocks() {
		return Core.LLVMCountBasicBlocks(instance);
	}
	
	public LLVMBasicBlock[] getBasicBlocks() {
		int num_blocks = (int)countBasicBlocks();
		LLVMBasicBlock[] blocks = new LLVMBasicBlock[num_blocks];
		SWIGTYPE_p_p_LLVMOpaqueBasicBlock bbs = Core.new_LLVMBasicBlockRefArray(num_blocks);
		Core.LLVMGetBasicBlocks(instance,bbs);
		for(int i=0;i<num_blocks;i++)
			blocks[i] = LLVMBasicBlock.getBasicBlock(Core.LLVMBasicBlockRefArray_getitem(bbs,i));
		Core.delete_LLVMBasicBlockRefArray(bbs);
		return blocks;
	}
	
	public LLVMBasicBlock getFirstBasicBlock() {
		return LLVMBasicBlock.getBasicBlock(Core.LLVMGetFirstBasicBlock(instance));
	}
	
	public LLVMBasicBlock getLastBasicBlock() {
		return LLVMBasicBlock.getBasicBlock(Core.LLVMGetLastBasicBlock(instance));
	}
	
	public LLVMBasicBlock getEntryBasicBlock() {
		return LLVMBasicBlock.getBasicBlock(Core.LLVMGetEntryBasicBlock(instance));
	}
	
	public LLVMBasicBlock appendBasicBlock(String name) {
		return new LLVMBasicBlock(Core.LLVMAppendBasicBlock(instance,name));
	}
	
	public static LLVMFunction getFunction(SWIGTYPE_p_LLVMOpaqueValue f) {
		LLVMValue possibility = LLVMValue.getValue(f);
		if(possibility == null)
			return new LLVMFunction(f);
		else
			return (LLVMFunction)possibility;
	}
	
	protected void finalize() {
		Core.LLVMDeleteFunction(instance);
	}
}
