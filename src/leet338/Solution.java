package leet338;

/**
 * Created by sansword on 2017/1/28.
 */
public class Solution {
    private static final int[] PRE_COMPUTATION = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4, 1};
    private static final int PRE_COMPUTED_NUM = 16;
    public int[] countBits(int num) {
        int[] answer = new int[num + 1];
        if (num <= PRE_COMPUTED_NUM) {
            System.arraycopy(PRE_COMPUTATION, 0, answer, 0, num + 1);
        } else {
            System.arraycopy(PRE_COMPUTATION, 0, answer, 0, PRE_COMPUTED_NUM + 1);
            int currentStage = PRE_COMPUTED_NUM;
            int current = PRE_COMPUTED_NUM + 1;
            while(current <= num) {
                int tail = current - currentStage;
                if(tail == currentStage) {
                    answer[current] = 1;
                    currentStage = currentStage * 2;
                } else {
                    answer[current] = answer[tail] + 1;
                }
                current++;
            }
        }
        return answer;
    }
}
