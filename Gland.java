import java.util.*;

public class Gland {
    
    public static boolean isQuestionWord(String s) {
        s = s.toLowerCase();
        return s.matches("(wh(o|at|ere|hy)|how)");
    }
    
    public static String stripPunctuation(String s) {
        String r = "";
        for (int i=0;i<s.length();i++) {
            if (".?,!".indexOf(s.charAt(i)) == -1) {
                r += s.charAt(i);
            }
        }
        return r;
    }
    
    public static boolean isBe(String s) {
        s = s.toLowerCase();
        return s.matches("(is|a(m|re))");
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
        return s.substring(0, 1).toUpperCase() + s.substring(1);
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
}