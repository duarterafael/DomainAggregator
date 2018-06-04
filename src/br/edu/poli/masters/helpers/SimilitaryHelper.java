package br.edu.poli.masters.helpers;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class SimilitaryHelper implements Comparator<CharSequence>{
	
	public static boolean equalIgnoreCaseAndAccentuation(String str1, String str2){
		Collator collator = Collator.getInstance (new Locale ("pt", "BR"));  
		collator.setStrength(Collator.PRIMARY);
		
		return collator.compare(str1, str2) == 0? true : false;
	}
	
	public static boolean isSubString(String str1, String str2){
		return str1.toString().contains(str2.toString()) || str2.toString().contains(str1.toString());
	}
	
    private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static int levenshteinDistance(CharSequence str1,  CharSequence str2) {
    		
        int[][] distance = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++)
                distance[i][0] = i;
        for (int j = 1; j <= str2.length(); j++)
                distance[0][j] = j;

        for (int i = 1; i <= str1.length(); i++)
                for (int j = 1; j <= str2.length(); j++)
                        distance[i][j] = minimum(
                                        distance[i - 1][j] + 1,
                                        distance[i][j - 1] + 1,
                                        distance[i - 1][j - 1]
                                                        + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0
                                                                        : 1));

        return distance[str1.length()][str2.length()];    
}

	@Override
	public int compare(CharSequence arg0, CharSequence arg1) {
		return levenshteinDistance(arg0, arg1);
	}
}
