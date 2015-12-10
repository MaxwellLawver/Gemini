import java.util.*;

public class WordGland {
    
    public static boolean isQuestionWord(String s) {
        s = s.toLowerCase();
        return s.matches("(wh(o|at|ere|hy)|how)");
    }
    
    public static String stripPunctuation(String s) {
        String r = "";
        for (int i=0;i<s.length();i++) {
            if (".?,!".indexOf(s.charAt(i)) == -1 || (i < s.length() - 1 && i != 0 && s.charAt(i) == '.' && isNumber(s.charAt(i - 1)) && isNumber(s.charAt(i + 1)))) {
                r += s.charAt(i);
            }
        }
        return r;
    }
    
    public static boolean isBe(String s) {
        s = s.toLowerCase();
        return s.matches("(is|a(m|re))");
    }
    
    public static boolean isNumber(char x) {
        return "0123456789".indexOf(x) != -1;
    }
    
    public static boolean isNumber(String x) {
        for (int i=0;i<x.length();i++) {
            if ("0123456789.".indexOf(x.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }
    
    public static char lowercaseChar(char x) {
        String s = x + "";
        s = s.toLowerCase();
        return s.charAt(0);
    }
    
    public static char uppercaseChar(char x) {
        String s = x + "";
        s = s.toUpperCase();
        return s.charAt(0);
    }
    
    public static int alphaIndex(char x) {
        x = lowercaseChar(x);
        return "!abcdefghijklmnopqrstuvwxyz".indexOf(x);
    }
    
    public static char alphaIndex(int x) {
        if (x > 0 && x < 27) {
            return "!abcdefghijklmnopqrstuvwxyz".charAt(x);
        }
        return '!';
    }
    
    public static boolean isVowel(char c) {
        return "AaEeIiOoUu".indexOf(c) != -1;
    }
    
    public static boolean isConsonant(char c) {
        return "BbCcDdFfGgHhJjKkLlMmNnPpQqRrSsTtVvWwXxYyZz".indexOf(c) != -1;
    }
    
    public static boolean isLetter(char c) {
        return isVowel(c) || isConsonant(c);
    }
    
    public static boolean isUpperCase(char c) {
        String s = c + "";
        return s.toUpperCase().charAt(0) == c;
    }
    
    public static String spaceOutArray(String[] a) {
        String r = "";
        for (int i=0;i<a.length;i++) {
            r += a[i] + " ";
        }
        if (r.length() == 0) {
            return "";
        }
        return r.substring(0, r.length() - 1);
    }
    
    public static String spaceOutArray(String[] a, int index) {
        return spaceOutArray(Arrays.copyOfRange(a, index, a.length));
    }
    
    public static String spaceOutArray(String[] a, int index, int endex) {
        return spaceOutArray(Arrays.copyOfRange(a, index, endex));
    }
    
    public static String capitalize(String s) {
        if (s.length() == 0) {
            return "";
        }
        String r = s.substring(0, 1).toUpperCase();
        if (s.length() > 1) {
            r += s.substring(1);
        }
        return r;
    }
    
    public static String properBe(KnowledgeEntry k) {
        return "is";
    }
    
    public static boolean isEquation(String e) {
        e = e.toLowerCase();
        return (e.indexOf("+") != -1 || e.indexOf("-") != -1 || e.indexOf("*") != -1 || e.indexOf("/") != -1 || e.indexOf("^") != -1);
    }
    
    public static double parseNum(String e) {
        if (e.indexOf(".") != -1) {
            return Double.parseDouble(e);
        } else {
            return Integer.parseInt(e);
        }
    }
    
    public static String pigLatin(String e) {
        e = stripPunctuation(e);
        String[] splat = e.split(" ");
        for (int i=0;i<splat.length;i++) {
            splat[i] = pigLatinWord(splat[i]);
        }
        return spaceOutArray(splat);
    }
    
    public static String pigLatinWord(String e) {
        String r = "";
        boolean capitalizeWord = isUpperCase(e.charAt(0));
        e = e.toLowerCase();
        if (isVowel(e.charAt(0))) {
            r = e + "way";
        } else if (isConsonant(e.charAt(0))) {
            String leading = "";
            for (int i=0;i<e.length();i++) {
                if (isConsonant(e.charAt(i)) || (i != 0 && e.substring(i - 1, i + 1).equalsIgnoreCase("qu"))) {
                    leading += e.charAt(i);
                } else {
                    r = e.substring(i) + leading + "ay";
                    break;
                }
            }
        } else {
            r = e;
        }
        if (capitalizeWord) {
            return capitalize(r);
        } else {
            return r;
        }
    }
    
    public static String backwards(String e) {
        e = stripPunctuation(e);
        String[] splat = e.split(" ");
        for (int i=0;i<splat.length;i++) {
            splat[i] = backwardsWord(splat[i]);
        }
        return spaceOutArray(splat);
    }
    
    public static String backwardsWord(String e) {
        String r = "";
        if (e.length() == 0) {
            return "";
        }
        boolean capitalizeWord = isUpperCase(e.charAt(0));
        e = e.toLowerCase();
        for (int i=e.length() - 1;i>=0;i--) {
            r += e.charAt(i);
        }
        if (capitalizeWord) {
            return capitalize(r);
        } else {
            return r;
        }
    }
    
    public static String rot13(String e) {
        String r = "";
        for (int i=0;i<e.length();i++) {
            char m = e.charAt(i);
            boolean cap = isUpperCase(m);
            if (alphaIndex(m) == -1) {
                r += m;
            } else {
                m = alphaIndex((alphaIndex(m) + 12)%26 + 1);
                if (cap) {
                    m = uppercaseChar(m);
                }
                r += m;
            }
        }
        return r;
    }
}