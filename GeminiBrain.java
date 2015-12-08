import java.util.*;

public class GeminiBrain {
    private HashMap<String, KnowledgeEntry> knowledge;
    private String name;
    private KnowledgeEntry self;
    private KnowledgeEntry user;
    
    public GeminiBrain() {
         name = "Gemini";
         memInit();
    }
    
    public String name() {
        return name;
    }
    
    public String respond(String input) {
        input = Gland.stripPunctuation(input);
        String[] splitInput = input.split(" ");
        if (Gland.isQuestionWord(splitInput[0]) && Gland.isBe(splitInput[1]) && splitInput.length > 2) { //handle "what is ______" questions
            //check for my/yours
            if (splitInput[2].equalsIgnoreCase("your")) {
                String key = Gland.spaceOutArray(Arrays.copyOfRange(splitInput, 3, splitInput.length));
                if (self.hasProperty(key)) {
                    return "My " + key + " " + Gland.properBe(self.property(key)) + " " + self.property(key).value() + ".";
                } else {
                    return "I don't have a " + key + ".";
                }
            } else if (splitInput[2].equalsIgnoreCase("my")) {
                String key = Gland.spaceOutArray(Arrays.copyOfRange(splitInput, 3, splitInput.length));
                if (self.hasProperty(key)) {
                    return "Your " + user.property(key).name() + " " + Gland.properBe(user.property(key)) + " " + user.property(key).value() + ".";
                } else {
                    return "I don't know your " + key + ".";
                }
            } else {
                String key = Gland.spaceOutArray(Arrays.copyOfRange(splitInput, 2, splitInput.length));
                if (knowledge.containsKey(key)) {
                    return Gland.capitalize(key) + " " + Gland.properBe(knowledge.get(key)) + " " + knowledge.get(key).value() + ".";
                } else if (Gland.isEquation(key)) {
                    return Gland.capitalize(key) + " is " + SmartGland.process(key) + ".";
                } else {
                    return "I don't know anything about " + key + ".";
                }
            }
        }
        return "...";
    }
    
    public void tellUserName(String s) { //lets gemini know what the user's name is
        user.value(s);
        user.createProperty("name", s);
    }
    
    private void memInit() { //create some basic knowledge entries
        knowledge = new HashMap<String, KnowledgeEntry>();
        self = new KnowledgeEntry(name);
        
        //me
        self.createProperty("creator", "FQL");
        
        //self
        self.createProperty("favorite color", "#00FFFF");
        self.createProperty("eye color", "green");
        self.createProperty("name", name);
        //nick
        self.createProperty("daddy", "Nick");
        self.createProperty("dad", self.property("daddy"));
        self.createProperty("father", self.property("daddy"));
        
        self.property("daddy").createProperty("age", new KnowledgeNumber(15));
        
        user = new KnowledgeEntry();
    }
    
}