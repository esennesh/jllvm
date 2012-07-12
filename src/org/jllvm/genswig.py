#!/usr/bin/python
import os
import subprocess
import sys

standard_include = '/usr/include/'

def header_include(name,path,std):
	if std:
		return '<' + os.path.join(path[len(standard_include):],name + '.h') + '>'
	else:
		return '"' + path + '/' + name + '.h"'
		
def runprog(prog,args):
	popen_args = prog
	for arg in args:
		popen_args = popen_args + ' ' + arg
	proc = subprocess.Popen(popen_args,stdout=subprocess.PIPE,shell=True)
	while proc.poll() == None:
		pass
	return proc.stdout.read()
	
def join_paths(paths):
	def do_joins(path,paths):
		if len(paths) == 1:
			return os.path.join(path,paths[0])
		else:
			return do_joins(os.path.join(path,paths[0]),paths[1:])
	return do_joins(paths[0],paths[1:])
	
def last_token(string,delimiter):
	s = string.split(delimiter)
	if s[len(s)-1] != '':
		return s[len(s)-1]
	else:
		return s[len(s)-2]

def generate_swig_interfaces(header_path):
	assert(os.path.isdir(header_path))
	if header_path[len(header_path)-1] != '/':
		header_path = header_path + '/'
	std_include = False
	if header_path.find(standard_include) == 0:
		std_include = True
	headers = []
	for root, dirs, files in os.walk(header_path):
		for file in files:
			if file.rpartition('.')[2] == 'h':
				headers.append(os.path.join(root, file))
	print 'Generating JNI interface for headers in ' + header_path + ' using SWIG.'
	for header in headers:
		name = os.path.basename(header).rpartition('.')[0]
		relpath = os.path.normpath('./' + last_token(header_path,'/') + '/' + os.path.dirname(header)[len(header_path):] + '/')
		print 'Preprocessing ' + header + ' for SWIG at ' + relpath
		if os.path.isdir(relpath) == False:
				os.makedirs(relpath)
		hfile = open(header)
		contents = hfile.read()
		hfile.close()
		out = open(join_paths([relpath,name + '.i']),"w")
		out.write('%module ' + name + '\n%{\n#include ' + header_include(name,os.path.dirname(header),std_include) + '\n%}\n')
		if contents.find('LLVMTypeRef *') >=0 or contents.find('LLVMValueRef *') >= 0 or contents.find('unsigned *') >=0 or contents.find('LLVMModuleRef *') >= 0 or contents.find('LLVMBasicBlockRef *') >= 0 or contents.find('LLVMExecutionEngineRef *') >= 0 or contents.find('char **') >= 0:
			out.write('\n%include "carrays.i"\n')
		if contents.find('LLVMTypeRef *') >= 0:
			out.write('%array_functions(LLVMTypeRef,LLVMTypeRefArray)\n')
		if contents.find('LLVMValueRef *') >= 0:
			out.write('%array_functions(LLVMValueRef,LLVMValueRefArray)\n')
		if contents.find('unsigned *') >= 0:
			out.write('%array_functions(unsigned,UnsignedIntArray)\n')
		if contents.find('LLVMModuleRef *') >= 0:
			out.write('%array_functions(LLVMModuleRef,LLVMModuleRefArray)\n')
		if contents.find('LLVMBasicBlockRef *') >= 0:
			out.write('%array_functions(LLVMBasicBlockRef,LLVMBasicBlockRefArray)\n')
		if contents.find('LLVMExecutionEngineRef *') >= 0:
			out.write('%array_functions(LLVMExecutionEngineRef,LLVMExecutionEngineRefArray)\n')
		if contents.find('char **') >= 0:
			out.write('%array_functions(char *,StringArray)\n')
		out.write(contents)
		out.close()
		cwd = os.getcwd()
		os.chdir(relpath)
		print 'swig ' + '-package org.jllvm.bindings -java ' + name + '.i'
		out = runprog("swig",["-package org.jllvm.bindings -java",name + '.i'])
		os.chdir(cwd)
		print out
		
if __name__ == '__main__':
	generate_swig_interfaces(sys.argv[1])
