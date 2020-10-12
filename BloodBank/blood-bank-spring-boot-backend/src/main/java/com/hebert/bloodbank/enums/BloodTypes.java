

package com.hebert.bloodbank.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum BloodTypes {
	A_POS("A+", Arrays.asList("AB+", "A+" ), Arrays.asList("A+", "A-","O+", "O-")), 
	A_NEG("A-", Arrays.asList("A+", "A-", "AB+", "AB-"), Arrays.asList("A-", "O-")), 
	
	B_POS("B+", Arrays.asList("B+", "AB+"), Arrays.asList("B+", "B-", "O+", "O-")), 
	B_NEG("B-", Arrays.asList("B+", "B-", "AB+", "AB-"), Arrays.asList("B-", "O-")), 
	
	AB_POS("AB+", Arrays.asList("AB+"), Arrays.asList("A+", "B+", "O+", "AB+", "A-", "B-", "O-", "AB-")),  
	AB_NEG("AB-", Arrays.asList("AB+", "AB-"), Arrays.asList("A-", "B-", "O-", "AB-")),
	
	O_POS("O+", Arrays.asList("A+", "B+", "O+", "AB+"), Arrays.asList("O+", "0-")),
	O_NEG("O-", Arrays.asList("A+", "B+", "O+", "AB+", "A-", "B-", "O-", "AB-"), Arrays.asList("O-"));
	
	
	private final String type;
	private final List<String> donateTo;
	private final List<String> receiveFrom;
	
	BloodTypes(final String type, final List<String> canDonateTo, final List<String> canReceiveFrom){
		this.type = type;
		this.donateTo = canDonateTo;
		this.receiveFrom = canReceiveFrom;
	}

	public String getType() {
		return type;
	}
	
    public Boolean canDonate(String type) {    	
    	return (findType(getCanDonateTo(), type) != null);
    }
    
    public Boolean canReceive(String type) {    	
    	return (findType(getCanReceiveFrom(), type) != null);
    }
    
	public List<String> getCanDonateTo() {
		return donateTo;
	}

	public List<String> getCanReceiveFrom() {
		return receiveFrom;
	}
    
    private String findType (List<String> types, String type) {    	
    	String found = types 
    		.stream() 
    		.filter(t -> t.equals(type)) 
    		.findFirst() 
    		.orElse(null);
    	
    	return found;
    }
		
	public static BloodTypes getByType(String type) {
		BloodTypes[] values = BloodTypes.values();
		BloodTypes result = null;

		for (int i = 0; i < values.length; i++) {
			BloodTypes nameRef = values[i];
			if (nameRef.getType().equalsIgnoreCase(type)) {
				result = nameRef;
				break;
			}
		}

		return result;
	}
	
	public static BloodTypes getByOrdinal(Integer ordinal) {
        return BloodTypes.values()[ordinal];
    }
	
	public List<Integer> getOrdinalsReceiveFrom() {
		List<String> receiveFromList = getCanReceiveFrom();
		List<Integer> ordinals = new ArrayList<>();

		for (String item : receiveFromList) {
			BloodTypes type = getByType(item);
			if (Objects.nonNull(type)) {
				ordinals.add(type.ordinal());
			}
		}

		return ordinals;
	}

}