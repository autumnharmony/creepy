/**
 * Created with IntelliJ IDEA.
 * User: anton
 * Date: 11/5/12
 * Time: 3:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class WordMatcher extends Matcher<String> {
    @Override
    double match(String t1, String t2) {
        if (t1.length() != t2.length()){
            return 0;
        }
        double length = t1.length();
        double count = 0;
        for (int i = 0; i < length; i++){
            count+= (t1.charAt(i) == t2.charAt(i) ? 1 : 0);
        }
        return count / length;
    }
}
