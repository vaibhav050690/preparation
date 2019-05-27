import java.util.ArrayList;

public class KnapsackAlgoExperts {

    public static ArrayList<ArrayList<Integer>> knapsackProblem(int[][] items, int capacity) {
        int n = items.length;
        int[][] dp = new int[n + 1][capacity + 1];

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for(int i = 1; i<=n; i++){
            int wt = items[i-1][1];
            int value = items[i-1][0];
            for(int j = 0; j<= capacity; j++){
                dp[i][j] = dp[i-1][j];
                if(wt <= j){
                    dp[i][j] = Math.max(dp[i][j], value + dp[i-1][j - wt]);
                }
            }
        }

        ArrayList<Integer> maxValue = new ArrayList<>();
        maxValue.add(dp[n][capacity]);
        ArrayList<Integer> indices = new ArrayList<>();
        setIndices(dp, items, indices, n, capacity);
        result.add(maxValue);
        result.add(indices);
        return result;
    }

    public static void setIndices(int[][] dp, int[][] items, ArrayList<Integer> indices, int i, int j){
        if(i > 0 && j > 0){
            if(dp[i][j] == dp[i-1][j]){
                setIndices(dp, items, indices, i-1, j);
            }
            else {
                setIndices(dp, items, indices, i-1, j - items[i-1][1]);
                indices.add(i-1);
            }
        }
    }

    public static void main(String[] args) {
        int[][] items = new int[][] {
                {1,2},
                {4,3},
                {5,6},
                {6,7}
        };
        int capacity = 10;
        System.out.println(knapsackProblem(items, capacity));
    }
}
