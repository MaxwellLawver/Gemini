import java.util.*;

public class KnowledgeEntry {
    
    private String name;
    private String value;
    private HashMap<String, KnowledgeEntry> properties = new HashMap<String, KnowledgeEntry>();
    private ArrayList<String> adjectives = new ArrayList<String>();
    private KnowledgeEntry parent;
    
    public KnowledgeEntry() {
        name = "";
        value = "";
    }
    
    public KnowledgeEntry(String n, String v) {
        name = n;
        value = v;
    }
    
    public KnowledgeEntry(String v) {
         name = "";
         value = v;
    }
    
    //name getters/accessors
    
    public String name() {
        return name;
    }
    
    public void name(String n) {
        name = n;
    }
    
    //value getters/accessors
    
    public String value() {
        return value;
    }
    
    public void value(String v) {
        value = v;
    }
    
    //property getters
    
    public String propertyValue(String key) {
        key = key.toLowerCase();
        if (hasProperty(key)) {
            return property(key).value();
        } else {
            return "";
        }
    }
    
    public KnowledgeEntry property(String key) {
        key = key.toLowerCase();
        if (properties.containsKey(key)) {
            return properties.get(key);
        } else {
            String[] splat = key.split(" ");
            for (int i=0;i<splat.length;i++) {
                if (splat[i].endsWith("'s")) {
                    String subkey = WordGland.spaceOutArray(splat, i + 1);
                    splat[i] = splat[i].substring(0, splat[i].length() - 2);
                    String prekey = WordGland.spaceOutArray(splat, 0, i + 1);
                    if (hasProperty(prekey)) {
                        return property(prekey).property(subkey);
                    }
                }
            }
            return null;
        }
    }
    
    public void createProperty(String key, String value) {
        KnowledgeEntry other = new KnowledgeEntry(key, value);
        other.setParent(this);
        properties.put(key, other);
    }
    
    public void createProperty(String key, KnowledgeEntry value) {
        value.setParent(this);
        properties.put(key, value);
    }
    
    public boolean hasProperty(String key) {
        return !(property(key) == null);
    }
    
    public String toString() {
        return value;
    }
    
    private void setParent(KnowledgeEntry p) {
        parent = p;
    }
    
}