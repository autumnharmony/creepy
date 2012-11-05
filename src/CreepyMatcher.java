import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: anton
 * Date: 11/5/12
 * Time: 4:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class CreepyMatcher {

    boolean match(String t1, String t2) {
        Map<Character, Set<Integer>> characterSetMap1 = charPositions(t1);
        Map<Character, Set<Integer>> characterSetMap2 = charPositions(t2);
        if (characterSetMap2.size()!=characterSetMap2.size()){
           return false;
        }
        int count = 0;
        for (Set<Integer> set : characterSetMap1.values()){
            if (characterSetMap2.containsValue(set)) count++;
        }
        return count == characterSetMap1.size();
    }

    private Map<Character,Set<Integer>> charPositions(String t1) {
        Map<Character,Set<Integer>> map = new HashMap<Character, Set<Integer>>();
        for (int i = 0; i < t1.length(); i++){
            Character character1 = t1.charAt(i);
            Set<Integer> positions;
            if (map.containsKey(character1)){
                positions = map.get(character1);
            } else {
                positions = new HashSet<Integer>();
            }

            for (int j = 0; j < t1.length(); j++){
                Character character2 = t1.charAt(j);
                if (character1.equals(character2)) positions.add(j);
            }
            map.put(character1, positions);
        }
        return  map;
    }
}
