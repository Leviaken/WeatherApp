import java.util.HashMap;
import java.util.Map;

public class States {

    // A map that stores lowercase state names as keys and their abbreviations as values
    private static final Map<String, String> stateCodes = new HashMap<>();

    // Static block to fill the map with all U.S. states and their abbreviations
    static {
        stateCodes.put("alabama", "AL");
        stateCodes.put("alaska", "AK");
        stateCodes.put("arizona", "AZ");
        stateCodes.put("arkansas", "AR");
        stateCodes.put("california", "CA");
        stateCodes.put("colorado", "CO");
        stateCodes.put("connecticut", "CT");
        stateCodes.put("delaware", "DE");
        stateCodes.put("florida", "FL");
        stateCodes.put("georgia", "GA");
        stateCodes.put("hawaii", "HI");
        stateCodes.put("idaho", "ID");
        stateCodes.put("illinois", "IL");
        stateCodes.put("indiana", "IN");
        stateCodes.put("iowa", "IA");
        stateCodes.put("kansas", "KS");
        stateCodes.put("kentucky", "KY");
        stateCodes.put("louisiana", "LA");
        stateCodes.put("maine", "ME");
        stateCodes.put("maryland", "MD");
        stateCodes.put("massachusetts", "MA");
        stateCodes.put("michigan", "MI");
        stateCodes.put("minnesota", "MN");
        stateCodes.put("mississippi", "MS");
        stateCodes.put("missouri", "MO");
        stateCodes.put("montana", "MT");
        stateCodes.put("nebraska", "NE");
        stateCodes.put("nevada", "NV");
        stateCodes.put("new hampshire", "NH");
        stateCodes.put("new jersey", "NJ");
        stateCodes.put("new mexico", "NM");
        stateCodes.put("new york", "NY");
        stateCodes.put("north carolina", "NC");
        stateCodes.put("north dakota", "ND");
        stateCodes.put("ohio", "OH");
        stateCodes.put("oklahoma", "OK");
        stateCodes.put("oregon", "OR");
        stateCodes.put("pennsylvania", "PA");
        stateCodes.put("rhode island", "RI");
        stateCodes.put("south carolina", "SC");
        stateCodes.put("south dakota", "SD");
        stateCodes.put("tennessee", "TN");
        stateCodes.put("texas", "TX");
        stateCodes.put("utah", "UT");
        stateCodes.put("vermont", "VT");
        stateCodes.put("virginia", "VA");
        stateCodes.put("washington", "WA");
        stateCodes.put("west virginia", "WV");
        stateCodes.put("wisconsin", "WI");
        stateCodes.put("wyoming", "WY");
    }

    // Method to convert state names into their abbreviations
    public static String convertToAbbreviation(String stateName) {
        return stateCodes.getOrDefault(stateName.trim().toLowerCase(), "Unknown");
    }
}