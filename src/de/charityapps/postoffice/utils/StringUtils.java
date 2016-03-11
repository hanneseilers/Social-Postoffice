package de.charityapps.postoffice.utils;

public class StringUtils {
	
	private static void check(String s, int size, String pad){
		if (pad == null)
            throw new NullPointerException("pad cannot be null");
        if (pad.length() <= 0)
            throw new IllegalArgumentException("pad cannot be empty");
        if (s == null)
        	throw new NullPointerException("string cannot be null");
	}

    public static String center(String s, int size) {
        return center(s, size, " ");
    }

    public static String center(String s, int size, String pad) {
        check(s, size, pad);
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }
    
    public static String cut(String s, int size){    
    	check(s, size, " ");
    	if( s.length() > size ){
    		StringBuilder sb = new StringBuilder(s);
    		return sb.subSequence(0, size-1).toString();
    	}
    	return s;
    }
    
    public static String repeat(String s, int size){
    	check(s, size, " ");
    	StringBuilder sb = new StringBuilder();
    	for( int i=0; i < size; i++ ){
    		sb.append(s);
    	}
    	return sb.toString();
    }
    
    public static String padLeft(String s, int size){
    	return padLeft(s, size, " ");
    }
    
    public static String padLeft(String s, int size, String pad){
    	check(s, size, pad);
    	s = cut(s, size);
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < (size - s.length()); i++) {
            sb.append(pad);
        }
    	sb.append(s);
    	return sb.toString();
    }
    
    public static String padRight(String s, int size){
    	return padRight(s, size, " ");
    }
    
    public static String padRight(String s, int size, String pad){
    	check(s, size, pad);
    	s = cut(s, size);
    	StringBuilder sb = new StringBuilder();
    	sb.append(s);
    	for (int i = 0; i < (size - s.length()); i++) {
            sb.append(pad);
        }
    	return sb.toString();
    }
}
