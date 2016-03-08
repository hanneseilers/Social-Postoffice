from os import listdir, system
from os.path import isfile, abspath 
import sys

print ">> converting ui files"

vFiles = listdir(".")
if len(sys.argv) > 1:
	vFiles = sys.argv[1:]

for vFile in vFiles:
	if isfile(vFile) and ".ui" in vFile:
		print "processing", vFile
		
		vNewFile = vFile.replace(".ui", ".jui")
		fi = open( vFile, 'r' )
		fo = open( vNewFile, 'w' )
		
		for line in fi:
			if "<ui version=\"4.0\">" in line:
				line = "<ui version=\"4.0\" language=\"jambi\">\n"
			fo.write( line )
		
		fi.close()
		fo.close()
		
		system( "juic " + vNewFile )
		
package = abspath( "." )
if "src/" in package:
	
	package = package.split("src/")
	if len(package) > 1:
		package = package[1].replace("/", ".")
		print ">> adding package", package
		
		for vFile in listdir("."):
			if isfile(vFile) and ".java" in vFile:
			
				# read file data
				fi = open( vFile, 'r' )
				data = "package " + str(package) + ";\n"	# add package
				for line in fi:
					data += line
				fi.close()
				
				# write data back
				fo = open( vFile, 'w' )
				fo.write( data )
				fo.close()



print ">> finished" 
