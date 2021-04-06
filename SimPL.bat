@echo off
FOR %%c IN (doc\examples\*.spl) DO (
	java -jar SimPL.jar %%c
	echo --------------------------------------
	)
pause