cmake_minimum_required (VERSION 2.6)
project (jllvm)
add_library(jllvm SHARED Analysis_wrap.c BitReader_wrap.c BitWriter_wrap.c Core_wrap.c Disassembler_wrap.c EnhancedDisassembly_wrap.c ExecutionEngine_wrap.c Initialization_wrap.c LinkTimeOptimizer_wrap.c lto_wrap.c Object_wrap.c Target_wrap.c TargetMachine_wrap.c Transforms/IPO_wrap.c Transforms/PassManagerBuilder_wrap.c Transforms/Scalar_wrap.c Transforms/Vectorize_wrap.c)
install(TARGETS jllvm DESTINATION lib/)
add_definitions( -D__STDC_LIMIT_MACROS -D__STDC_CONSTANT_MACROS )

# A convenience variable:
set(LLVM_ROOT "/usr/" CACHE /usr/ "Root of LLVM install.")
set(CMAKE_INSTALL_PREFIX "/usr")
# A bit of a sanity check:
if( NOT EXISTS ${LLVM_ROOT}/include/llvm )
message(FATAL_ERROR "LLVM_ROOT (${LLVM_ROOT}) is not a valid LLVM install")
endif()
# We incorporate the CMake features provided by LLVM:
set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} "${LLVM_ROOT}/share/llvm/cmake")
include(LLVMConfig)
# Now set the header and library paths:
include_directories( ${LLVM_ROOT}/include )
link_directories( ${LLVM_ROOT}/lib )
add_definitions( ${LLVM_DEFINITIONS} )
# Make sure to include the headers required for Java and JNI.
FIND_PACKAGE(Java REQUIRED)
FIND_PACKAGE(JNI REQUIRED)
INCLUDE_DIRECTORIES(${JAVA_INCLUDE_PATH})
INCLUDE_DIRECTORIES(${JAVA_INCLUDE_PATH2})
# Let's suppose we want to build a JIT compiler with support for
# binary code (no interpreter):
#llvm_map_components_to_libraries(REQ_LLVM_LIBRARIES jit native)
llvm_map_components_to_libraries(REQ_LLVM_LIBRARIES native analysis)
# Finally, we link the LLVM libraries to our executable:
target_link_libraries(jllvm ${REQ_LLVM_LIBRARIES})
# LLVM typically comes installed as static libraries, so make sure to link in the C++ runtimes.
set_target_properties(jllvm PROPERTIES LINKER_LANGUAGE CXX)
