@echo off
:: Vazkii's JSON creator for blocks
:: Put in your /resources/assets/%modid%/models/block
:: Makes basic block JSON files as well as the acossiated item and simple blockstate
:: Can make multiple blocks at once
::
:: Usage:
:: _make (block name 1) (block name 2) (block name x)
:: for /f "Tokens=*" %f in ('dir /l/b/a-d') do (_make %~nf)
:: Change this to your mod's ID
set modid=plants

setlocal enabledelayedexpansion

for %%x in (%*) do (
	echo Making %%x.json block
	(
	echo	{
	echo 	"parent": "block/cross",
	echo 	"textures": {
	echo    "cross": "plants:blocks/cosmetic/%%x"
	echo 	}
	echo 	}
	) > ../../../models/block/%%x.json
	
	echo Making %%x.json item
	(
	echo	{
	echo	"parent": "item/generated",
	echo	"textures": {
	echo	"layer0": "plants:blocks/cosmetic/%%x"
	echo 	}
	echo	}
	) > ../../../models/item/%%x.json

	echo Making %%x.json blockstate
	(
	echo	{	
	echo	"variants": {
	echo	"normal": { "model": "plants:%%x" }
	echo	}
	echo	}
	) > ../../../blockstates/%%x.json
)
