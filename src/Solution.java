import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    // Minimum Cost of Merging Files
    public int getMinTime(int num, List<Integer> files) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int size : files) {
            pq.offer(size);
        }

        int res = 0;
        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            int sum = first + second;
            pq.offer(sum);
            res += sum;
        }
        return res;
    }

    public void testGetMinTime() {
        //List<Integer> lst = Arrays.asList(4, 8, 6, 12);
        List<Integer> lst = Arrays.asList(4, 8, 4, 20, 2);
        System.out.println(getMinTime(lst.size(), lst));
    }


}
