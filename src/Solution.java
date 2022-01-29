import java.util.*;

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

    // Amazon Prime Air Route
    public int[] getPair(int max, int[][] forward, int[][] backward) {
        Arrays.sort(forward, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        Arrays.sort(backward, new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int f = 0, b = backward.length - 1;
        int maxSum = 0;
        int[] res = new int[2];
        while (f < forward.length && b >= 0) {
            int sum = forward[f][1] + backward[b][1];
            if (sum > max) {
                b--;
                continue;
            }
            if (sum > maxSum) {
                maxSum = sum;
                res = new int[]{forward[f][0], backward[b][0]};
            }
            f++;
        }
        return res;
    }

    public void testGetPair() {
        int[][] forward = new int[][]{
                {1, 3000},
                {2, 5000},
                {3, 4000},
                {4, 10000}
        };
        int[][] backward = new int[][]{
                {1, 2000},
                {2, 3000},
                {3, 4000}
        };
        int[] res = getPair(11000, forward, backward);
        System.out.println(res[0] + "-" + res[1]);
    }

    // deliver goods
    public int canDeliver(int[] input) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : input) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int count = 0;
        for (int value : map.values()) {
            if (value == 1)
                return -1;
            count += value / 3;
            if (value % 3 != 0)
                count++;
        }
        return count;
    }

    public void testCanDeliver() {
        int[] input1 = new int[]{2, 2, 3, 3, 4};
        int[] input2 = new int[]{2, 2, 3, 3, 3};
        System.out.println(canDeliver(input1));
        System.out.println(canDeliver(input2));
    }

    // greyness
    public int maxGreyness(int[][] g) {
        int m = g.length;
        int n = g[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) {
                    rows[i]++;
                    cols[j]++;
                } else {
                    rows[i]--;
                    cols[j]--;
                }
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int value = rows[i] + cols[j];
                value += g[i][j] == 1 ? -1 : 1;
                res = Math.max(value, res);
            }
        }
        return res;
    }

    public void testMaxGreyness() {
        int[][] g = new int[][]{
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0}
        };
        System.out.println(maxGreyness(g));
    }

    // move data
    public int[] moveData(int[] locations, int[] moveFrom, int[] moveTo) {
        HashSet<Integer> set = new HashSet<>();
        for (int l : locations) {
            set.add(l);
        }

        int m = moveFrom.length;
        for (int i = 0; i < m; i++) {
            set.add(moveTo[i]);
            set.remove(moveFrom[i]);
        }
        int[] res = new int[locations.length];
        int idx = 0;
        for (int newL : set) {
            res[idx++] = newL;
        }
        Arrays.sort(res);
        return res;
    }

    public void testMoveData() {
        int[] locations = new int[]{1, 5, 2, 6};
        int[] moveFrom = new int[]{1, 4, 5, 7};
        int[] moveTo = new int[]{4, 7, 1, 3};
        int[] res = moveData(locations, moveFrom, moveTo);
        for (int r : res) {
            System.out.println(r);
        }
    }

    // sign-in API
    public String[] signAPI(String[] logs) {
        HashMap<String, String> users = new HashMap<>();
        HashSet<String> login = new HashSet<>();

        String[] res = new String[logs.length];
        for (int i = 0; i < logs.length; i++) {
            String[] strs = logs[i].split(" ");
            if (strs.length == 2) {
                if (login.contains(strs[1])) {
                    res[i] = "Logged Out Successfully";
                    login.remove(strs[1]);
                } else {
                    res[i] = "Logout Unsuccessful";
                }
            } else if (strs[0].equals("register")) {
                if (users.containsKey(strs[1])) {
                    res[i] = "Username already exists";
                } else {
                    res[i] = "Registered Successfully";
                    users.put(strs[1], strs[2]);
                }
            } else {
                if (!users.containsKey(strs[1]) || login.contains(strs[1])) {
                    res[i] = "Login Unsuccessful";
                } else {
                    if (users.get(strs[1]).equals(strs[2])) {
                        res[i] = "Logged In Successful";
                        login.add(strs[1]);
                    } else {
                        res[i] = "Login Unsuccessful";
                    }
                }
            }
        }
        return res;
    }

    public void testSignAPI() {
        String[] logs = new String[]{
                "register david david123",
                "register adam 1Adam1",
                "login david david123",
                "login adam 1adam1",
                "logout david"
        };
        String[] res = signAPI(logs);
        for (String s : res) {
            System.out.println(s);
        }
    }

    // match word
    public int matchWord(String searchWord, String resultWord) {
        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < searchWord.length() && idx2 < resultWord.length()) {
            if (searchWord.charAt(idx1) == resultWord.charAt(idx2)) {
                idx1++;
                idx2++;
            } else {
                idx1++;
            }
        }
        return resultWord.length() - idx2;
    }

    public void testMatchWord() {
        String searchWord = "armaze";
        String resultWord = "amazon";
        System.out.println(matchWord(searchWord, resultWord));
    }

    // construct string
    public int constructStr(String s, String t) {
        HashMap<Character, Integer> mapS = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            mapS.put(s.charAt(i), mapS.getOrDefault(s.charAt(i), 0) + 1);
        }
        HashMap<Character, Integer> mapT = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            mapT.put(t.charAt(i), mapT.getOrDefault(t.charAt(i), 0) + 1);
        }
        int res = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> entry : mapT.entrySet()) {
            res = Math.min(res, mapS.get(entry.getKey()) / entry.getValue());
        }
        return res;
    }

    public void testConstructStr() {
        String s = "mononom";
        String t = "mon";
        System.out.println(constructStr(s, t));
    }

    // max sum
    public int maxSum(int k, int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            if (map.containsKey(nums[i])) {
                int value = map.get(nums[i]);
                if (value == 1) {
                    count++;
                }
                map.put(nums[i], value + 1);
            } else {
                map.put(nums[i], 1);
            }
            sum += nums[i];
        }

        int res = count == 0 ? sum : Integer.MIN_VALUE;

        for (int i = k; i < nums.length; i++) {
            // left
            int leftKey = nums[i - k];
            int leftValue = map.get(leftKey);
            if (leftValue == 1) {
                map.remove(leftKey);
            } else if (leftValue == 2) {
                count--;
                map.put(nums[leftKey], 1);
            } else {
                map.put(nums[leftKey], leftValue - 1);
            }
            sum -= leftKey;

            // right
            int rightKey = nums[i];
            if (!map.containsKey(rightKey)) {
                map.put(rightKey, 1);
            } else {
                int rightValue = map.get(rightKey);
                if (rightValue == 1) {
                    count++;
                }
                map.put(rightKey, rightValue);
            }
            sum += rightKey;

            if (count == 0)
                res = Math.max(res, sum);
        }
        return res;
    }

    public void testMaxSum() {
        int[] nums = new int[]{1, 2, 3, 4};
        int k = 2;
        System.out.println(maxSum(k, nums));
    }
}
