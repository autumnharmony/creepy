import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: anton
 * Date: 11/5/12
 * Time: 3:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class Dictionary implements Serializable {

    Set<String> stringSet;



    Map<Integer, List<String>> map;

    public Dictionary() {
        stringSet = new HashSet<String>();
        map = new HashMap<Integer, List<String>>();
    }

    public void fill(String string){
        String[] strings = string.split("\\s+");
        for (String s : strings) {
            stringSet.add(s);
            Integer key = s.length();
            if (map.containsKey(key)){
                map.get(key).add(s);
            } else {
                map.put(key, new ArrayList<String>());
                map.get(key).add(s);
            }
        }
    }

    public void fill(List<String> strings){
        for (String s : strings) {
            stringSet.add(s);
            Integer key = s.length();
            if (map.containsKey(key)){
                map.get(key).add(s);
            } else {
                map.put(key, new ArrayList<String>());
                map.get(key).add(s);
            }
        }
    }

    public void add(String s){
        stringSet.add(s);
        Integer key = s.length();
        if (map.containsKey(key)){
            map.get(key).add(s);
        } else {
            map.put(key, new ArrayList<String>());
            map.get(key).add(s);
        }
    }

    public List<String> getByLength(int length){
           return map.get(length);
    }

    public static void main(String[] args){
        Dictionary dictionary = new Dictionary();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader= null;
        try {
            File file = new File("areverse.txt");
            inputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(inputStream, Charset.forName("koi8-r"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String s;
            try {
                while ((s = bufferedReader.readLine())!=null){

                     dictionary.add(s);
                }

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("dictionary.bin"));
                objectOutputStream.writeObject(dictionary);
                objectOutputStream.close();


            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }




}