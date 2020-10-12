package com.hebert.bloodbank.enums;

public enum GenderTypes {
	    MALE("Masculino"), FEMALE("Feminino"), OTHER("Outros");
	
	private final String type;
	
	GenderTypes(final String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public static GenderTypes getByType(String type) {
		GenderTypes[] values = GenderTypes.values();
		GenderTypes result = null;

		for (int i = 0; i < values.length; i++) {
			GenderTypes nameRef = values[i];
			if (nameRef.getType().equalsIgnoreCase(type)) {
				result = nameRef;
				break;
			}
		}

		return result;
	}
	
	public static GenderTypes getByOrdinal(Integer ordinal) {
        return GenderTypes.values()[ordinal];
    }
}


