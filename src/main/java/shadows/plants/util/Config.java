package shadows.plants.util;

import shadows.plants.proxy.CommonProxy;

public class Config {

	
	public static void syncConfig() { // Gets called from preInit
	    try {
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    } catch (Exception e) {
	        // Failed reading/writing, just continue
	    } finally {
	        // Save props to config IF config changed
	        if (CommonProxy.config.hasChanged()) CommonProxy.config.save();
	    }
	
	    }
	
	
}
