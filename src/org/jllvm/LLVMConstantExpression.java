package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;
import org.jllvm.bindings.LLVMIntPredicate;
import org.jllvm.bindings.LLVMRealPredicate;
import org.jllvm.LLVMConstant;

public class LLVMConstantExpression extends LLVMConstant {
	public enum DivType {SIGNEDINT,UNSIGNEDINT,FLOAT};
	public static LLVMConstantExpression getSizeOf(LLVMType type) {
		return new LLVMConstantExpression(Core.LLVMSizeOf(type.getInstance()));
	}
	
	public static LLVMConstantExpression add(LLVMConstant lhs,LLVMConstant rhs) {
		assert(lhs.typeOf().equals(rhs.typeOf()));
		return new LLVMConstantExpression(Core.LLVMConstAdd(lhs.instance,rhs.instance));
	}
	
	public static LLVMConstantExpression subtract(LLVMConstant lhs,LLVMConstant rhs) {
		assert(lhs.typeOf().equals(rhs.typeOf()));
		return new LLVMConstantExpression(Core.LLVMConstSub(lhs.instance,rhs.instance));
	}
	
	public static LLVMConstantExpression multiply(LLVMConstant lhs,LLVMConstant rhs) {
		assert(lhs.typeOf().equals(rhs.typeOf()));
		return new LLVMConstantExpression(Core.LLVMConstMul(lhs.instance,rhs.instance));
	}
	
	public static LLVMConstantExpression divide(LLVMConstant lhs,LLVMConstant rhs,DivType type) {
		assert(lhs.typeOf().equals(rhs.typeOf()));
		if(type == DivType.SIGNEDINT)
			return new LLVMConstantExpression(Core.LLVMConstSDiv(lhs.instance,rhs.instance));
		else if(type == DivType.UNSIGNEDINT)
			return new LLVMConstantExpression(Core.LLVMConstUDiv(lhs.instance,rhs.instance));
		else if(type == DivType.FLOAT)
			return new LLVMConstantExpression(Core.LLVMConstFDiv(lhs.instance,rhs.instance));
		return null;
	}
	
	public static LLVMConstantExpression remainder(LLVMConstant lhs,LLVMConstant rhs,DivType type) {
		assert(lhs.typeOf().equals(rhs.typeOf()));
		if(type == DivType.SIGNEDINT)
			return new LLVMConstantExpression(Core.LLVMConstSRem(lhs.instance,rhs.instance));
		else if(type == DivType.UNSIGNEDINT)
			return new LLVMConstantExpression(Core.LLVMConstURem(lhs.instance,rhs.instance));
		else if(type == DivType.FLOAT)
			return new LLVMConstantExpression(Core.LLVMConstFRem(lhs.instance,rhs.instance));
		return null;
	}
	
	public static LLVMConstantExpression or(LLVMConstant lhs,LLVMConstant rhs) {
		assert(lhs.typeOf().equals(rhs.typeOf()));
		return new LLVMConstantExpression(Core.LLVMConstOr(lhs.instance,rhs.instance));
	}
	
	public static LLVMConstantExpression and(LLVMConstant lhs,LLVMConstant rhs) {
		assert(lhs.typeOf().equals(rhs.typeOf()));
		return new LLVMConstantExpression(Core.LLVMConstAnd(lhs.instance,rhs.instance));
	}
	
	public static LLVMConstantExpression xor(LLVMConstant lhs,LLVMConstant rhs) {
		assert(lhs.typeOf().equals(rhs.typeOf()));
		return new LLVMConstantExpression(Core.LLVMConstXor(lhs.instance,rhs.instance));
	}
	
	public static LLVMConstantExpression intComparison(LLVMConstant lhs,LLVMConstant rhs,LLVMIntPredicate pred) {
		assert(lhs.typeOf().equals(rhs.typeOf()));
		return new LLVMConstantExpression(Core.LLVMConstICmp(pred,lhs.instance,rhs.instance));
	}
	
	public static LLVMConstantExpression realComparison(LLVMConstant lhs,LLVMConstant rhs,LLVMRealPredicate pred) {
		assert(lhs.typeOf().equals(rhs.typeOf()));
		return new LLVMConstantExpression(Core.LLVMConstFCmp(pred,lhs.instance,rhs.instance));
	}
	
	public static LLVMConstantExpression shiftLeft(LLVMConstant lhs,LLVMConstant rhs) {
		assert(lhs.typeOf().equals(rhs.typeOf()));
		return new LLVMConstantExpression(Core.LLVMConstShl(lhs.instance,rhs.instance));
	}
	
	/* Pass in false for a logical shift-right (zero-fill) and true for an arithmetic shift-right (sign-extension). */
	public static LLVMConstantExpression shiftRight(LLVMConstant lhs,LLVMConstant rhs,boolean arithmetic) {
		assert(lhs.typeOf().equals(rhs.typeOf()));
		if(arithmetic)
			return new LLVMConstantExpression(Core.LLVMConstAShr(lhs.instance,rhs.instance));
		else
			return new LLVMConstantExpression(Core.LLVMConstLShr(lhs.instance,rhs.instance));
	}
	
	public static LLVMConstantExpression select(LLVMConstant condition,LLVMConstant True,LLVMConstant False) {
		assert(condition instanceof LLVMConstantVector || condition instanceof LLVMConstantBoolean);
		assert(True.typeOf().equals(False.typeOf()));
		return new LLVMConstantExpression(Core.LLVMConstSelect(condition.instance,True.instance,False.instance));	
	}
	
	public static LLVMConstantExpression shuffleVector(LLVMConstantVector a,LLVMConstantVector b,LLVMConstantVector mask) {
		assert(mask.typeOf() instanceof LLVMVectorType && ((LLVMVectorType)mask.typeOf()).getElementType().equals(LLVMIntegerType.i32));
		return new LLVMConstantExpression(Core.LLVMConstShuffleVector(a.instance,b.instance,mask.instance));
	}
	
	public LLVMConstantExpression(SWIGTYPE_p_LLVMOpaqueValue val) {
		super(val);
	}		
}
