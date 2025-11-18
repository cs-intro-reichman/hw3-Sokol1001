/** Functions for checking if a given string is an anagram. */
public class Anagram {
    public static void main(String args[]) {
        // Tests the isAnagram function.
        System.out.println(isAnagram("silent","listen"));  // true
        System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
        System.out.println(isAnagram("Madam Curie","Radium came")); // true
        System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

        // Tests the preProcess function.
        System.out.println(preProcess("What? No way!!!"));
        
        // Tests the randomAnagram function.
        System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
        
        // Performs a stress test of randomAnagram 
        String str = "1234567";
        Boolean pass = true;
        //// 10 can be changed to much larger values, like 1000
        for (int i = 0; i < 10; i++) {
            String randomAnagram = randomAnagram(str);
            System.out.println(randomAnagram);
            pass = pass && isAnagram(str, randomAnagram);
            if (!pass) break;
        }
        System.out.println(pass ? "test passed" : "test Failed");
    } 

    // Returns true if the two given strings are anagrams, false otherwise.
    public static boolean isAnagram(String str1, String str2) {
        String s1 = preProcess(str1);
        String s2 = preProcess(str2);

        if (s1.length() != s2.length()) {
            return false;
        }

        StringBuilder s2Copy = new StringBuilder(s2);
        
        int i = 0;
        while (i < s1.length()) {
            char c = s1.charAt(i);
            boolean found = false;
            
            int j = 0;
            while (j < s2Copy.length()) {
                if (c == s2Copy.charAt(j)) {
                    s2Copy.deleteCharAt(j);
                    found = true;
                    break;
                }
                j++;
            }
            
            if (!found) {
                return false;
            }
            i++;
        }
        
        return true;
    }
        
    // Returns a preprocessed version of the given string: all the letter characters are converted
    // to lower-case, and all the other characters are deleted, except for spaces, which are left
    // as is. For example, the string "What? No way!" becomes "whatnoway"
    public static String preProcess(String str) {
        String lowerStr = str.toLowerCase();
        StringBuilder result = new StringBuilder();
        
        int i = 0;
        while (i < lowerStr.length()) {
            char c = lowerStr.charAt(i);
            
            // Check if character is a letter
            if (c >= 'a' && c <= 'z') {
                result.append(c);
            }
            // Note: The example "whatnoway" implies spaces are removed too.
            // If the prompt strictly required keeping spaces, the condition would be:
            // else if (c == ' ') { result.append(c); }
            
            i++;
        }
        return result.toString();
    } 
        
    // Returns a random anagram of the given string. The random anagram consists of the same
    // characters as the given string, re-arranged in a random order. 
    public static String randomAnagram(String str) {
        StringBuilder source = new StringBuilder(str);
        StringBuilder result = new StringBuilder();
        
        while (source.length() > 0) {
            double randValue = Math.random();
            int index = (int) (randValue * source.length());
            
            char c = source.charAt(index);
            result.append(c);
            source.deleteCharAt(index);
        }
        
        return result.toString();
    }
}