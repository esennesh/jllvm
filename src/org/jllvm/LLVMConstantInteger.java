package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueType;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueValue;
import org.jllvm.LLVMIntegerType;
import java.math.BigInteger;

public class LLVMConstantInteger extends LLVMConstant {
	public static LLVMConstantInteger constantInteger(LLVMIntegerType intType,long N,boolean signExtend) {
		if(intType.equals(LLVMIntegerType.i1))
			return new LLVMConstantBoolean(N != 0);
		return new LLVMConstantInteger(Core.LLVMConstInt(intType.getInstance(),BigInteger.valueOf(N),signExtend ? 1 : 0));
	}
	
	public LLVMConstantInteger(SWIGTYPE_p_LLVMOpaqueValue c) {
		super(c);
		assert(typeOf() instanceof LLVMIntegerType);
	}
	
	public LLVMConstantExpression truncate(LLVMIntegerType targetType) {
		return new LLVMConstantExpression(Core.LLVMConstTrunc(instance,targetType.getInstance()));
	}
	
	public LLVMConstantExpression signExtend(LLVMIntegerType targetType) {
		return new LLVMConstantExpression(Core.LLVMConstSExt(instance,targetType.getInstance()));
	}
	
	public LLVMConstantExpression zeroExtend(LLVMIntegerType targetType) {
		return new LLVMConstantExpression(Core.LLVMConstZExt(instance,targetType.getInstance()));
	}
	
	public LLVMConstantExpression toFloatingPoint(LLVMRealType targetType,boolean signed) {
		if(signed)
			return new LLVMConstantExpression(Core.LLVMConstSIToFP(instance,targetType.getInstance()));
		else
			return new LLVMConstantExpression(Core.LLVMConstUIToFP(instance,targetType.getInstance()));
	}
	
	public LLVMConstantExpression toPointer(LLVMPointerType targetType) {
		return new LLVMConstantExpression(Core.LLVMConstIntToPtr(instance,targetType.getInstance()));
	}
}
