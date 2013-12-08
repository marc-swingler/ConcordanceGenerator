@echo off
SETLOCAL EnableDelayedExpansion

SET SCRIPT_HOME=%~dp0%
SET SCRIPT_HOME=%SCRIPT_HOME:~0,-1%
CD /D "%SCRIPT_HOME%
CALL :main %*
GOTO :EOF

:genClassPath
	SET LIBS_DIR=%1%
	for /f "useback tokens=*" %%a in ('%LIBS_DIR%') do set LIBS_DIR=%%~a
	SET GENCLASSPATH=
	FOR /f "usebackq delims=|" %%f in (`dir /b /a-D /S "%LIBS_DIR%"`) do (
		set NEXT_LIB=%%f
		set NEXT_LIB=!NEXT_LIB:*%SCRIPT_HOME%\=!
		set GENCLASSPATH=!GENCLASSPATH!;!NEXT_LIB!
	)
	SET GENCLASSPATH="!GENCLASSPATH:~1!"
	SET "%~2=!GENCLASSPATH!"
GOTO :EOF

:main
	CALL :genClassPath libs CLASSPATH
	java -cp %CLASSPATH% com.swingler.concordance_generator.Main %*
GOTO :EOF

ENDLOCAL
