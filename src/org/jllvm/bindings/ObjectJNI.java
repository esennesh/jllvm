/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.4
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.jllvm.bindings;

public class ObjectJNI {
  public final static native long LLVMCreateObjectFile(long jarg1);
  public final static native void LLVMDisposeObjectFile(long jarg1);
  public final static native long LLVMGetSections(long jarg1);
  public final static native void LLVMDisposeSectionIterator(long jarg1);
  public final static native long LLVMIsSectionIteratorAtEnd(long jarg1, long jarg2);
  public final static native void LLVMMoveToNextSection(long jarg1);
  public final static native void LLVMMoveToContainingSection(long jarg1, long jarg2);
  public final static native long LLVMGetSymbols(long jarg1);
  public final static native void LLVMDisposeSymbolIterator(long jarg1);
  public final static native long LLVMIsSymbolIteratorAtEnd(long jarg1, long jarg2);
  public final static native void LLVMMoveToNextSymbol(long jarg1);
  public final static native String LLVMGetSectionName(long jarg1);
  public final static native long LLVMGetSectionSize(long jarg1);
  public final static native String LLVMGetSectionContents(long jarg1);
  public final static native long LLVMGetSectionAddress(long jarg1);
  public final static native long LLVMGetSectionContainsSymbol(long jarg1, long jarg2);
  public final static native long LLVMGetRelocations(long jarg1);
  public final static native void LLVMDisposeRelocationIterator(long jarg1);
  public final static native long LLVMIsRelocationIteratorAtEnd(long jarg1, long jarg2);
  public final static native void LLVMMoveToNextRelocation(long jarg1);
  public final static native String LLVMGetSymbolName(long jarg1);
  public final static native long LLVMGetSymbolAddress(long jarg1);
  public final static native long LLVMGetSymbolFileOffset(long jarg1);
  public final static native long LLVMGetSymbolSize(long jarg1);
  public final static native long LLVMGetRelocationAddress(long jarg1);
  public final static native long LLVMGetRelocationOffset(long jarg1);
  public final static native long LLVMGetRelocationSymbol(long jarg1);
  public final static native long LLVMGetRelocationType(long jarg1);
  public final static native String LLVMGetRelocationTypeName(long jarg1);
  public final static native String LLVMGetRelocationValueString(long jarg1);
}
