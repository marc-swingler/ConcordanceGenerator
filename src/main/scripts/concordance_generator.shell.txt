#/bin/bash

generate_classpath() {
	libraries=`find libs -type f`
	for library in $libraries
	do
		CLASSPATH=`echo $library:$CLASSPATH`
	done
	CLASSPATH=`echo "${CLASSPATH%?}"`
}

main() {
	generate_classpath
	java -cp $CLASSPATH com.swingler.concordance_generator.Main $@
}

main $@
