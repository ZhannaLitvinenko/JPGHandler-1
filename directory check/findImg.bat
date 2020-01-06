SET save=%cd%
cd /d %1%
dir /b *.jpg> "%save%\file.txt"
exit