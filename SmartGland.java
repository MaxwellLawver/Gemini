public class SmartGland {
    public static double process(String e) {
        String[] splat = e.split(" ");
        double d1 = Gland.parseNum(splat[0]);
        double d2 = Gland.parseNum(splat[2]);
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