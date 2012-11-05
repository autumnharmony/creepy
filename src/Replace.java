import java.util.Map;

public class Replace {

    public static String replace(String original, Map<Character,Character> replaceRule){
        StringBuilder sb = new StringBuilder(original.length());
        for (int i = 0; i < original.length(); i++){
            sb.append(replaceRule.get(original.charAt(i)));
        }
        return sb.toString();
    }

}
