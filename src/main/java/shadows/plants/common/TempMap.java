package shadows.plants.common;

import java.util.HashMap;
import java.util.Map;

public class TempMap {

	//private static EnumTempZone temperate = EnumTempZone.TEMPERATE;
	private static EnumTempZone all = EnumTempZone.ALL;
	private static EnumTempZone desert = EnumTempZone.HOT;

	public static Map<Integer, EnumTempZone> cosmetic_1() {
		Map<Integer, EnumTempZone> map = new HashMap<Integer, EnumTempZone>();
		map.put(0, all);
		map.put(1, all);
		map.put(2, all);
		map.put(3, all);
		map.put(4, all);
		map.put(5, all);
		map.put(6, all);
		map.put(7, all);
		map.put(8, all);
		map.put(9, all);
		map.put(10, all);
		map.put(11, all);
		map.put(12, all);
		map.put(13, all);
		map.put(14, all);
		map.put(15, all);
		return map;

	}
	/*
	 * public static Map<Integer, EnumTempZone> cosmetic_2() { Map<Integer,
	 * EnumTempZone> map = new HashMap<Integer, EnumTempZone>(); map.put(0,
	 * all); map.put(1, all); map.put(2, all); map.put(3, all); map.put(4, all);
	 * map.put(5, all); map.put(6, all); map.put(7, all); map.put(8, all);
	 * map.put(9, all); map.put(10, all); map.put(11, all); map.put(12, all);
	 * map.put(13, all); map.put(14, all); map.put(15, all); return map;
	 * 
	 * }
	 * 
	 * public static Map<Integer, EnumTempZone> cosmetic_3() { Map<Integer,
	 * EnumTempZone> map = new HashMap<Integer, EnumTempZone>(); map.put(0,
	 * all); map.put(1, all); map.put(2, all); map.put(3, all); map.put(4, all);
	 * map.put(5, all); map.put(6, all); map.put(7, all); map.put(8, all);
	 * map.put(9, all); map.put(10, all); map.put(11, all); map.put(12, all);
	 * map.put(13, all); map.put(14, all); map.put(15, all); return map;
	 * 
	 * }
	 * 
	 * public static Map<Integer, EnumTempZone> cosmetic_4() { Map<Integer,
	 * EnumTempZone> map = new HashMap<Integer, EnumTempZone>(); map.put(0,
	 * all); map.put(1, all); map.put(2, all); map.put(3, all); map.put(4, all);
	 * map.put(5, all); map.put(6, all); map.put(7, all); map.put(8, all);
	 * map.put(9, all); map.put(10, all); map.put(11, all); map.put(12, all);
	 * map.put(13, all); map.put(14, all); map.put(15, all); return map;
	 * 
	 * }
	 * 
	 * public static Map<Integer, EnumTempZone> cosmetic_5() { Map<Integer,
	 * EnumTempZone> map = new HashMap<Integer, EnumTempZone>(); map.put(0,
	 * all); map.put(1, all); map.put(2, all); map.put(3, all); map.put(4, all);
	 * map.put(5, all); map.put(6, all); map.put(7, all); return map;
	 * 
	 * }
	 * 
	 * public static Map<Integer, EnumTempZone> cosmetic_6() { Map<Integer,
	 * EnumTempZone> map = new HashMap<Integer, EnumTempZone>(); map.put(0,
	 * all); map.put(1, all); map.put(2, all); map.put(3, all); map.put(4, all);
	 * map.put(5, all); map.put(6, all); map.put(7, all); map.put(8, all);
	 * map.put(9, all); map.put(10, all); map.put(11, all); map.put(12, all);
	 * map.put(13, all); map.put(14, all); map.put(15, all); return map;
	 * 
	 * }
	 */
	  public static final Map<Integer, EnumTempZone> DESERT() { Map<Integer,
	  EnumTempZone> map = new HashMap<Integer, EnumTempZone>(); map.put(0,
	  desert); map.put(1, desert); map.put(2, desert); map.put(3, desert); map.put(4, desert);
	  map.put(5, desert); map.put(6, desert); map.put(7, desert); map.put(8, desert);
	  map.put(9, desert); map.put(10, desert); map.put(11, desert); map.put(12, desert);
	  map.put(13, desert); map.put(14, desert); map.put(15, desert); return map;
	  
	  }
	 
}
