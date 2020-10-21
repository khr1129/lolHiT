package com.sbs.khr.lolHiT.util;

import java.math.BigInteger;

public class Util {

	public static int getAsInt(Object object) {
		if ( object instanceof BigInteger ) {
			return ((BigInteger)object).intValue();
		} else if ( object instanceof Long) {
			return (int)object;
		} else if ( object instanceof Integer) {
			return (int)object;
		} else if ( object instanceof String ) {
			return Integer.parseInt((String)object);
		}
		
		return -1;
	}

	public static String getAsStr(Object object, String defaultValue) {
		if (object == null) {
			return defaultValue;
		}

		if (object instanceof String) {
			return (String)object;
		}

		return object.toString();
	}
	
	
}
