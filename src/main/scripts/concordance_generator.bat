@echo off
SETLOCAL EnableDelayedExpansion

SET SCRIPT_HOME=%~dp0%
SET SCRIPT_HOME=%SCRIPT_HOME:~0,-1%
CD /D "%SCRIPT_HOME%"
SET LOG_FILE=concordance.txt
CALL :main %* > %LOG_FILE% 2>&1
GOTO :EOF

:genClassPath
	SET SOURCE_DIR=%1%
	for /f "useback tokens=*" %%a in ('%SOURCE_DIR%') do set SOURCE_DIR=%%~a
	SET GENCLASSPATH=
	FOR /f "usebackq delims=|" %%f in (`dir /b /a-D "%SOURCE_DIR%"`) do (
		set GENCLASSPATH=!GENCLASSPATH!;%SOURCE_DIR%\%%f
	)
	SET GENCLASSPATH=!GENCLASSPATH!
	SET "%~2=!GENCLASSPATH!"
GOTO :EOF

:main
	CALL :genClassPath "%SCRIPT_HOME%\libs" CLASSPATH
	SET CLASSPATH="%CLASSPATH:~1%"

	java -cp %CLASSPATH% com.swingler.concordance_generator.Main %*
GOTO :EOF

ENDLOCAL
