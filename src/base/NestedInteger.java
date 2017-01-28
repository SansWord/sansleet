package base;

import java.util.List;

/**
 * Created by sansword on 2017/1/28.
 */
public class NestedInteger {
    private boolean integerFlag = false;
    private Integer integer = 0;
    private List<NestedInteger> list = null;

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return integerFlag;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return integer;
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return list;
    }
}