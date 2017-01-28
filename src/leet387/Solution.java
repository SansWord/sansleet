package leet387;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().firstUniqChar("leetcode"));
        System.out.println(new Solution().firstUniqChar("loveleetcode"));
        System.out.println(new Solution().firstUniqChar(null));
        System.out.println(new Solution().firstUniqChar(""));
    }

    public int firstUniqChar(String s) {
        if (s == null) {
            return -1;
        } else {
            int strLen = s.length();
            if (strLen == 0) {
                return -1;
            } else {
                return slowSolution(s);
            }
        }
    }

    private int slowSolution(String s) {
        int strLen = s.length();
        Map<Character, List<Integer>> indexes = new HashMap<>();
        for (int i = 0; i < strLen; i++) {
            char currentChar = s.charAt(i);
            if (!indexes.containsKey(currentChar)) {
                indexes.put(currentChar, new ArrayList<>());
            }
            indexes.get(currentChar).add(i);
        }

        List<Integer> collection = indexes.values()
                .stream().filter(l -> l.size() == 1).map(l -> l.get(0)).collect(Collectors.toList());

        if (collection.size() == 0) {
            return -1;
        } else {
            Optional<Integer> min = collection.stream().min((a, b) -> a < b ? -1 : (a > b ? 1 : 0));
            return min.get();
        }
    }

    private int fastSolution(String s) {
        int strLen = s.length();
        Map<Character, Integer> firstShown = new HashMap<>();
        List<Integer> candidates = new ArrayList<>();
        Set<Character> shown = new HashSet<>();
        for (int i = 0; i < strLen; i++) {
            char currentChar = s.charAt(i);
            if (shown.contains(currentChar)) {
                Integer first = firstShown.get(currentChar);
                if (first != null) {
                    int index = candidates.indexOf(first);
                    candidates.remove(index);
                    firstShown.remove(currentChar);
                }
            } else {
                shown.add(currentChar);
                firstShown.put(currentChar, i);
                candidates.add(i);
            }
        }

        if (candidates.size() != 0) {
            return candidates.get(0);
        } else {
            return -1;
        }

    }
}
