:: for /f "Tokens=*" %f in ('dir /l/b/a-d') do (genlist %~nf)
setlocal enabledelayedexpansion

for %%x in (%*) do (
	echo Making list again
	(
	echo public static BushBase %%x = new BushBase("%%x", EnumModule.COSMETIC, null^^^);
	) >> list.txt
	)