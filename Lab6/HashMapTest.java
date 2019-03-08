
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    public static void main(String[] args) {
        System.out.println("Test HashMap");

        Map<String, Integer> HM = new HashMap<String, Integer>(11);

        In.init();
        long startTime = System.currentTimeMillis();
        while (!In.empty()) {
            String s = In.getString();

            if (HM.containsKey(s)) {
                int count = HM.get(s);
                HM.put(s, count + 1);
            } else {
                HM.put(s, 1);
            }
        }

        long endTime = System.currentTimeMillis();
        long hmTime = endTime - startTime;
        System.out.println("HashMap construction time = " + hmTime);

        System.out.println("contains 'and' " + HM.get("and") + " times");
        System.out.println("contains 'astonished' " + HM.get("astonished") + " times");
        System.out.println("contains 'boat' " + HM.get("boat") + " times");
        System.out.println("contains 'path' " + HM.get("path") + " times");
        System.out.println("contains 'the' " + HM.get("the") + " times");
        System.out.println("contains 'train' " + HM.get("train") + " times");
        System.out.println("contains 'tom' " + HM.get("tom") + " times");
        System.out.println("contains 'wondered' " + HM.get("wondered") + " times");

        endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("total running time = " + totalTime); 
    }
}
