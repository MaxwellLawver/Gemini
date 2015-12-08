public class SmartGland {
    public static double process(String e) {
        String[] splat;
        if (e.indexOf(" ") != -1) {
            splat = e.split(" ");
        } else {
            splat = new String[3];
            for (int i=0;i<e.length();i++) {
                switch (e.charAt(i)) {
                    case '+':
                    case '^':
                    case '/':
                    case '-':
                    case '*':
                        splat[0] = e.substring(0, i);
                        splat[1] = e.charAt(i) + "";
                        splat[2] = e.substring(i + 1);
                        break;
                }
            }
        }
        double d1 = WordGland.parseNum(splat[0]);
        double d2 = WordGland.parseNum(splat[2]);
        char c = splat[1].charAt(0);
        return calculate(d1, d2, c);
    }
    
    public static double calculate(double d1, double d2, char c) {
        switch (c) {
            case '+':
                return d1 + d2;
            case '^':
                return Math.pow(d1, d2);
            case '/':
                return d1/d2;
            case '-':
                return d1 - d2;
            case '*':
                return d1 * d2;
            default:
                return 0;
        }
    }
}