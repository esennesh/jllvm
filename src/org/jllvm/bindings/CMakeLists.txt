cmake_minimum_required (VERSION 2.6)
project (jllvm)
add_library(jllvm SHARED Analysis_wrap.c BitReader_wrap.c BitWriter_wrap.c Core_wrap.c Disassembler_wrap.c EnhancedDisassembly_wrap.c ExecutionEngine_wrap.c Initialization_wrap.c LinkTimeOptimizer_wrap.c lto_wrap.c Object_wrap.c Target_wrap.c TargetMachine_wrap.c IPO_wrap.c PassManagerBuilder_wrap.c Scalar_wrap.c Vectorize_wrap.c)
install(TARGETS jllvm DESTINATION lib/)
add_definitions( -D__STDC_LIMIT_MACROS -D__STDC_CONSTANT_MACROS )

# Find LLVM configuration
SET(LLVM_FIND_REQUIRED TRUE)
INCLUDE(FindLLVM.cmake)
INCLUDE_DIRECTORIES(${LLVM_INCLUDE_DIR})
LINK_DIRECTORIES(${LLVM_LIB_DIR})
MACRO(TARGET_LINK_LLVM TARGET)
  TARGET_LINK_LIBRARIES(${TARGET} ${LLVM_LDFLAGS} ${LLVM_LIBS_CORE} ${LLVM_LIBS_JIT} ${LLVM_LIBS_JIT_OBJECTS})
ENDMACRO(TARGET_LINK_LLVM)
# Make sure to include the headers required for Java and JNI.
FIND_PACKAGE(Java REQUIRED)
FIND_PACKAGE(JNI REQUIRED)
INCLUDE_DIRECTORIES(${JAVA_INCLUDE_PATH})
INCLUDE_DIRECTORIES(${JAVA_INCLUDE_PATH2})

TARGET_LINK_LLVM(jllvm)

# LLVM typically comes installed as static libraries, so make sure to link in the C++ runtimes.
set_target_properties(jllvm PROPERTIES LINKER_LANGUAGE CXX)
