package shadows.plants.common;

public enum EnumModule {
	COSMETIC ("cosmetic"),
	APPLIED ("applied"),
	BOTANICAL ("botanical"),
	EMBER ("ember"),
	ROOTED ("rooted"),
	BLOOD ("blood");
	
    private final String name;       

    private EnumModule(String s) {
        name = s;
    }
	
    @Override
    public String toString() {
        return this.name;
     }
    
}
