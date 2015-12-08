public class KnowledgeNumber extends KnowledgeEntry {
    
    private double value;
    
    public KnowledgeNumber(double v) {
        value = v;
    }
    
    //value getters/accessors
    
    public String value() {
        return value + "";
    }
    
    public void value(double v) {
        value = v;
    }
    
    public KnowledgeEntry property(String s) {
         s = s.toLowerCase();
         if (s.equals("square root")) {
             return new KnowledgeNumber(Math.sqrt(this.value));
         }
         return null;
    }
    
}