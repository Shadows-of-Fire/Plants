package shadows.plants.common;

import java.util.HashMap;
import java.util.Map;

public class TempMap {

	private static EnumTempZone temperate = EnumTempZone.TEMPERATE;
	
	public static Map<Integer, EnumTempZone> cosmetic_1() {
		Map <Integer, EnumTempZone> map = new HashMap <Integer, EnumTempZone> ();
		map.put(0, temperate);
		return map;
		
	}
	
	
}
