@echo OFF 

set "TAB=    "

:: preferable to use [] if u can have SPACE in set
if [%1] == [] (
    echo HELP
    echo %TAB%* First arguments are output directory
    echo %TAB%* Further arguments are file names [max: 8 files]
) 

set argList=(%2 %3 %4 %5 %6 %7 %8 %9)

if not exist "%1\" mkdir %1\

for %%i in %argList% do ( copy %~dp0%%i %1 > nul )