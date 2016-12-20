@echo off
FOR %%c IN (doc\examples\*.spl) DO (
	java -jar 5140219073.jar %%c
	echo ----------------------------------
	)
pause