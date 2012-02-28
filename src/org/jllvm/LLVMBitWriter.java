package org.jllvm;

import org.jllvm.bindings.Core;
import org.jllvm.bindings.BitWriter;
import org.jllvm.bindings.SWIGTYPE_p_LLVMOpaqueModule;

public class LLVMBitWriter {
	protected final LLVMModule module;
	
	public LLVMBitWriter(LLVMModule mod) {
		module = mod;
	}
	
	public boolean writeBitcodeToFile(String path) {
		int result = BitWriter.LLVMWriteBitcodeToFile(module.getInstance(),path);
		return (result == 0);
	}
	
	public boolean writeBitcodeToFD(int fd,boolean shouldClose,boolean unbuffered) {
		int result = BitWriter.LLVMWriteBitcodeToFD(module.getInstance(),fd,shouldClose ? 1 : 0,unbuffered ? 1 : 0);
		return (result == 0);
	}
	
	public boolean writeBitcodeToFileHandle(int handle) {
		int result = BitWriter.LLVMWriteBitcodeToFileHandle(module.getInstance(),handle);
		return (result == 0);
	}
}
