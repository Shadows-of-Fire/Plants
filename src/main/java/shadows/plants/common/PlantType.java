package shadows.plants.common;

public enum PlantType {
	COSMETIC ("cosmetic"),
	APPPLIED ("applied"),
	BOTANICAL ("botanical"),
	EMBER ("ember"),
	ROOTED ("rooted"),
	BLOOD ("blood");
	
    private final String name;       

    private PlantType(String s) {
        name = s;
    }
	
    @Override
    public String toString() {
        return this.name;
     }
    
}
