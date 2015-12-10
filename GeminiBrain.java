import java.util.*;

public class GeminiBrain {
    private HashMap<String, KnowledgeEntry> knowledge;
    private String name;
    private KnowledgeEntry self;
    private KnowledgeEntry user;
    private boolean pigLatin = false;
    private boolean backwards = false;
    private boolean rot13 = false;
    
    public GeminiBrain() {
         name = "Gemini";
         memInit();
    }
    
    public String name() {
        return name;
    }
    
    public String responseHandler(String input) {
        String response = respond(input);
        if (pigLatin) {
            response = WordGland.capitalize(WordGland.pigLatin(response)) + ".";
        }
        if (backwards) {
            response = WordGland.capitalize(WordGland.backwards(response)) + ".";
        }
        if (rot13) {
            response = WordGland.capitalize(WordGland.rot13(response));
        }
        return response;
    }
    
    public String respond(String input) {
        input = WordGland.stripPunctuation(input);
        String[] splitInput = input.split(" ");
        if (WordGland.isQuestionWord(splitInput[0]) && WordGland.isBe(splitInput[1]) && splitInput.length > 2) { //handle "what is ______" questions
            //check for my/yours
            if (splitInput[2].equalsIgnoreCase("your")) {
                String key = WordGland.spaceOutArray(Arrays.copyOfRange(splitInput, 3, splitInput.length));
                if (self.hasProperty(key)) {
                    return "My " + key + " " + WordGland.properBe(self.property(key)) + " " + self.property(key).value() + ".";
                } else {
                    return "I don't have a " + key + ".";
                }
            } else if (splitInput[2].equalsIgnoreCase("my")) {
                String key = WordGland.spaceOutArray(Arrays.copyOfRange(splitInput, 3, splitInput.length));
                if (self.hasProperty(key)) {
                    return "Your " + user.property(key).name() + " " + WordGland.properBe(user.property(key)) + " " + user.property(key).value() + ".";
                } else {
                    return "I don't know your " + key + ".";
                }
            } else {
                String key = WordGland.spaceOutArray(Arrays.copyOfRange(splitInput, 2, splitInput.length));
                if (knowledge.containsKey(key)) {
                    return WordGland.capitalize(key) + " " + WordGland.properBe(knowledge.get(key)) + " " + knowledge.get(key).value() + ".";
                } else if (WordGland.isEquation(key)) {
                    return WordGland.capitalize(key) + " is " + SmartGland.process(key) + ".";
                } else {
                    return "I don't know anything about " + key + ".";
                }
            }
        }
        if (input.equalsIgnoreCase("speak in pig latin")) {
            if (!pigLatin) {
                pigLatin = true;
                return "Okay.";
            } else {
                return "I'm already speaking in Pig Latin.";
            }
        }
        if (input.equalsIgnoreCase("stop speaking in pig latin")) {
            if (pigLatin) {
                pigLatin = false;
                return "Okay.";
            } else {
                return "I wasn't speaking in Pig Latin.";
            }
        }
        if (input.equalsIgnoreCase("speak backwards")) {
            if (!backwards) {
                backwards = true;
                return "Okay.";
            } else {
                return "I'm already speaking backwards.";
            }
        }
        if (input.equalsIgnoreCase("stop speaking backwards")) {
            if (backwards) {
                backwards = false;
                return "Okay.";
            } else {
                return "I wasn't speaking backwards.";
            }
        }
        if (input.equalsIgnoreCase("speak in rot13")) {
            if (!rot13) {
                rot13 = true;
                return "Okay.";
            } else {
                return "I'm already speaking in ROT13.";
            }
        }
        if (input.equalsIgnoreCase("stop speaking in rot13")) {
            if (rot13) {
                rot13 = false;
                return "Okay.";
            } else {
                return "I wasn't speaking in ROT13.";
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