package _00_Intro_To_String_Methods;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * Visit the JavaDocs for the String class to view everything you can do with a String.
 * https://docs.oracle.com/javase/7/docs/api/java/lang/String.html
 * https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html
 *
 * HINT:  Here are some String methods you might find useful 
 * contains
 * replace
 * trim
 * length
 * getBytes
 * endsWith
 * split 
 * indexOf
 * lastIndexOf
 * compareTo(IgnoreCase)
 * substring
 * toUpperCase/toLowerCase
 * valueOf
 *
 * Here are some Character methods you might find useful:
 * Character.toLowerCase(char c);
 * Character.toUpperCase(char c);
 * Character.isLetter(char c);
 * Character.isDigit(char c);
 * Character.getNumericValue(char c);
 */

public class _01_StringMethods {

    // Given Strings s1 and s2, return the longer String
    public static String longerString(String s1, String s2) {
        if(s1.length() > s2.length()) {
        	return s1;
        }
        else return s2;
    }

    // If String s contains the word "underscores", change all of the spaces
    // to underscores
    public static String formatSpaces(String s) {
        if(s.contains("underscores")) {
        	s = s.replace(" ", "_");
        }
        return s;
    }

    // Return the name of the person whose LAST name would appear first if they
    // were in alphabetical order.
    // You cannot assume there are no extra spaces around the name, but you can
    // assume there is only one space between the first and last name
    public static String lineLeader(String s1, String s2, String s3) {
    	
    ArrayList<String> strList = new ArrayList<String>();
    strList.add(s1);
    strList.add(s2);
    strList.add(s3);
    
	class MyCustomCompareClass implements Comparator<String> {
		
		
        @Override
        public int compare(String s1, String s2) {
            String lastname1 = s1.trim().split(" ")[1];
            String lastname2 = s2.trim().split(" ")[1];
            
            
            return lastname1.compareTo(lastname2);
        	}
    	}
        
        Collections.sort(strList, new MyCustomCompareClass());
        return strList.get(0).trim();
        
    }

    // Return the sum of all numerical digits in the String
    public static int numeralSum(String s) {
    	int sum = 0;
        for(int i = 0; i < s.length(); i++) {
        	if(Character.isDigit(s.charAt(i))) {
        		sum += (int) s.charAt(i) - 48;
        	}
        }
//        System.out.println(sum);
        return sum;
    }

    // Return the number of times String substring appears in String s
    public static int substringCount(String s, String substring) {
        int amt = 0;
        int sublen = substring.length();
        for(int i = 0; i < s.length() - sublen + 1; i++) {
        	if (substring.equals(s.substring(i, i + sublen))) {
        		amt ++;
        	}
        }
        return amt;
    }

    // Call Utilities.encrypt at the bottom of this file to encrypt String s
    public static String encrypt(String s, char key) {
        return Utilities.encrypt(s.getBytes(), (byte) key);
    }

    // Call Utilities.decrypt at the bottom of this file to decrypt the
    // cyphertext (encrypted text)
    public static String decrypt(String s, char key) {
        return Utilities.decrypt(s, (byte) key);
    }

    // Return the number of words in String s that end with String substring
    // You can assume there are no punctuation marks between words
    public static int wordsEndsWithSubstring(String s, String substring) {
        int amt = 0;
        int sublen = substring.length();
        String[] strs = s.trim().split(" ");
        for(String str: strs) {
        	int start = str.length() - sublen;
        	if (start > -1) {
        		if(substring.equals(str.substring(start, str.length()))) {
        		amt ++;
        		}
        	}
        	
        }
        return amt;
    }

    // Given String s, return the number of characters between the first
    // occurrence of String substring and the final occurrence
    // You can assume that substring will appear at least twice
    public static int distance(String s, String substring) {
    	int first_start = 0;
    	int last_start = 0;
    	int amt = 0;
    	int sublen = substring.length();
        for(int i = 0; i < s.length() - sublen + 1; i++) {
        	if (substring.equals(s.substring(i, i + sublen))) {
        		first_start = i;
        		break;
        	}
        }
        for(int end = s.length(); end > 0 + sublen; end--) {
        	if (substring.equals(s.substring(end - sublen, end))) {
        		last_start = end - sublen;
        		break;
        	}
        }
        return - first_start - sublen + last_start;
    }

    // Return true if String s is a palindrome
    // palindromes are words or phrases are read the same forward as backward.
    // HINT: ignore/remove all punctuation and spaces in the String
    public static boolean palindrome(String s) {
    	s = s.toLowerCase();
    	char[] chars = s.toCharArray();
    	s = "";
    	for(char c: chars) {
    		if(Character.isAlphabetic(c)) {
    			s += c;
    		}
    	}
    	String reverse = "";
    	char[] charList = s.toCharArray();
    	for(int i = charList.length - 1; i >=0; i--) {
    		reverse = reverse + charList[i];
    	}
    	System.out.println(reverse);
    	System.out.println(s + "\n");
        if(s.equals(reverse)) {
        	return true;
        }
        return false;
    }
}

class Utilities {
    // This basic encryption scheme is called single-byte xor. It takes a
    // single byte and uses exclusive-or on every character in the String.
    public static String encrypt(byte[] plaintext, byte key) {
        for (int i = 0; i < plaintext.length; i++) {
            plaintext[i] = (byte) (plaintext[i] ^ key);
        }
        return Base64.getEncoder().encodeToString(plaintext);
    }

    public static String decrypt(String cyphertext, byte key) {
        byte[] b = Base64.getDecoder().decode(cyphertext);
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) (b[i] ^ key);
        }
        return new String(b);
    }
}
