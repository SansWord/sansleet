package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sansword on 2017/1/28.
 */
public class ForEachPerformanceTest {
    public static void main(String[] args) {

        long differenceSum = 0;
        long maxDifference = 0;
        int loopNum = 10000;
        int singleTestNum = 1000000;
        int listLength = 100;
        int maxIndex = -1;
        int lastDifferenceIndex = -1;
        Integer[] differenceArray = new Integer[200];
        Arrays.fill(differenceArray, 0);
        for (int looping = 0; looping < loopNum; looping++) {
            System.out.println("== " + looping + "th");
            List<Integer> input = new ArrayList<>(listLength);
            for (int i = 0; i < listLength; i++) {
                input.add((int) (1 + Math.random() * 100));
            }
            long startFor = System.currentTimeMillis();
            for (int k = 0; k < singleTestNum; k++) {
                final int[] sumFor = {0};
                for (Integer integer : input) {
                    sumFor[0] += integer;
                }

            }
            long endFor = System.currentTimeMillis();

            long startForEach = System.currentTimeMillis();
            for (int k = 0; k < singleTestNum; k++) {
                final int[] sumForEach = {0};
                input.forEach(integer -> sumForEach[0] += integer);
            }
            long endForEach = System.currentTimeMillis();
            Long singleDifference = Math.abs((endForEach - startForEach) - (endFor - startFor));
            differenceSum += singleDifference;

            if (singleDifference > maxDifference) {
                maxDifference = singleDifference;
                maxIndex = looping;
            }

            int arrIdx = singleDifference.intValue();
            if (arrIdx >= differenceArray.length) {
                Integer[] newDifferenceArray = new Integer[differenceArray.length * 2];
                Arrays.fill(newDifferenceArray, 0);
                System.arraycopy(differenceArray, 0, newDifferenceArray, 0, differenceArray.length);
                differenceArray = newDifferenceArray;
            }
            differenceArray[arrIdx] += 1;
            if (singleDifference > 0) {
                lastDifferenceIndex = looping;
            }
        }

        System.out.println("difference sum :" + differenceSum);
        System.out.println("Max difference:" + maxDifference + ", @" + maxIndex);
        System.out.println("last difference index:" + lastDifferenceIndex);
        System.out.println("difference avg. :" + ((differenceSum + 0.0) / loopNum));
        System.out.println("===== difference array =====");
        for (int i = 0; i < differenceArray.length; i++) {
            Integer currentDifference = differenceArray[i];
            if (currentDifference > 0) {
                System.out.println(i + ":" + currentDifference);
            }
        }

    }
}

/*
difference sum :314922
Max difference:99, @1
last difference index:9999
difference avg. :31.4922
===== difference array =====
1:1
2:1
9:2
11:1
15:2
17:1
18:1
19:1
20:2
21:4
22:10
23:17
24:29
25:52
26:117
27:216
28:290
29:538
30:1555
31:2745
32:2009
33:1016
34:487
35:336
36:228
37:131
38:59
39:51
40:26
41:14
42:15
43:10
44:9
45:6
46:4
47:1
48:3
49:1
50:1
52:2
53:1
55:2
67:2
99:1

Process finished with exit code 0
* */