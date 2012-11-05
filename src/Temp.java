import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anton
 * Date: 11/5/12
 * Time: 3:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class Temp {

    public static void main(String[] args){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("dictionary.bin")));
            try {
                Dictionary dictionary = (Dictionary)objectInputStream.readObject();
//                List<String> length4 = dictionary.getByLength(4);
//                System.out.println(length4.size());

                String message = "nci3 y 2p7, h2p g2b 24pc m37xz njbs5bz 7pbz i4vscl d4cy43o3jbme " +
                        "y mpyc4uc66p 6sc635p7xz d3426c5py, 5pri3 1bu5b n4puc6x.;";

                String message2 = message.replace(',',' ');
                message2 = message2.replace('.', ' ');
                message2 = message2.replace(';', ' ');

                String[] words = message2.split("\\s");
                for (String s: words ) {
                    System.out.println(s);
                }
                System.out.println("==========");
                Arrays.sort(words, new Comparator<String>() {
                    @Override
                    public int compare(String s, String s1) {
                        return s1.length()-s.length();
                    }
                });

                CreepyMatcher creepyMatcher = new CreepyMatcher();

                for (String s: words ) {
                //String s = words[0];
                    System.out.println(s);
                    for (String ss: dictionary.getByLength(s.length()) ) {
                        if (creepyMatcher.match(s,ss)){
                            System.out.println(ss);
                        }
                    }
                    System.out.println("----------------");
                }
//                for (int i = 0; i < words.length; i++){
//                    for (int j = 0 ;)
//                }
//                for (String s: dictionary.getByLength(12) ) {
//                    System.out.println(s);
//                }



            } catch (ClassNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }



//    public String recursive(String[] words, int index, String result){
//        if (index == words.length) return result;
//
//    }
}
