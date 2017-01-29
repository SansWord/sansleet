package leet341;

/**
 * Created by sansword on 2017/1/28.
 */

import base.NestedInteger;

import java.util.*;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 * <p>
 * // @return true if this NestedInteger holds a single integer, rather than a nested list.
 * public boolean isInteger();
 * <p>
 * // @return the single integer that this NestedInteger holds, if it holds a single integer
 * // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 * <p>
 * // @return the nested list that this NestedInteger holds, if it holds a nested list
 * // Return null if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
 */

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
public class NestedIterator implements Iterator<Integer> {
    private List<Integer> valueList = new ArrayList<>();
    private Integer[] valueArray;
    private int currentValueCursor = -1;
    private int size = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            deepExtractNestedInteger(nestedInteger);
        }
        valueArray = valueList.toArray(new Integer[0]);
        valueList = null;
        size = valueArray.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            currentValueCursor++;
            return valueArray[currentValueCursor];
        }
    }

    @Override
    public boolean hasNext() {
        return currentValueCursor + 1 < size;
    }

    private void deepExtractNestedInteger(NestedInteger extractingRaw) {
        if (extractingRaw.isInteger()) {
            valueList.add(extractingRaw.getInteger());
        } else {
            List<NestedInteger> list = extractingRaw.getList();
            for (NestedInteger nestedInteger : list) {
                deepExtractNestedInteger(nestedInteger);
            }
        }
    }
}