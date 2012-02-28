package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueBuilder;
import java.util.*;

public class LLVMInstructionBuilder {
	protected SWIGTYPE_p_LLVMOpaqueBuilder instance;
	protected static HashMap<SWIGTYPE_p_LLVMOpaqueBuilder,LLVMInstructionBuilder> instr_builders;
	
	public LLVMInstructionBuilder() {
		if(instr_builders == null)
			instr_builders = new HashMap<SWIGTYPE_p_LLVMOpaqueBuilder,LLVMInstructionBuilder>();
		instance = Core.LLVMCreateBuilder();
		instr_builders.put(instance,this);
	}
	
	public void positionBuilder(LLVMBasicBlock block,LLVMInstruction instr) {
		Core.LLVMPositionBuilder(instance,block.getBBInstance(),instr.getInstance());
	}
	
	public void positionBuilderBefore(LLVMInstruction instr) {
		Core.LLVMPositionBuilderBefore(instance,instr.getInstance());
	}
	
	public void positionBuilderAtEnd(LLVMBasicBlock block) {
		Core.LLVMPositionBuilderAtEnd(instance,block.getBBInstance());
	}
	
	public LLVMBasicBlock getInsertBlock() {
		return LLVMBasicBlock.getBasicBlock(Core.LLVMGetInsertBlock(instance));
	}
	
	public void clearInsertionPosition() {
		Core.LLVMClearInsertionPosition(instance);
	}
	
	public void insertIntoBuilder(LLVMInstruction instr) {
		Core.LLVMInsertIntoBuilder(instance,instr.getInstance());
	}
	
	public SWIGTYPE_p_LLVMOpaqueBuilder getInstance() {
		return instance;
	}
	
	public static LLVMInstructionBuilder getBuilder(SWIGTYPE_p_LLVMOpaqueBuilder b) {
		if(instr_builders == null)
			instr_builders = new HashMap<SWIGTYPE_p_LLVMOpaqueBuilder,LLVMInstructionBuilder>();
		assert(b != null);
		return instr_builders.get(b);
	}
	
	protected void finalize() {
		Core.LLVMDisposeBuilder(instance);
	}
}
