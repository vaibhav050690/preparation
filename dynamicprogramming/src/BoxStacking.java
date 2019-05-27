import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class BoxStacking {

    //box[wdth,depth,ht], cannot rotate box
    //TC O(n^2) | SC O(n)
    public static ArrayList<Integer[]> boxStacking(ArrayList<Integer[]> boxes){
        ArrayList<Integer[]> stack = new ArrayList<>();
        if(!boxes.isEmpty()){
            Collections.sort(boxes, new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] o1, Integer[] o2) {
                    return o1[2].compareTo(o2[2]);
                }
            });
            int[] dp = new int[boxes.size()];
            int[] seq = new int[boxes.size()];

            Arrays.fill(seq, -1);
            for(int i = 0; i<boxes.size(); i++){
                dp[i] = boxes.get(i)[2];
            }

            int maxHeight = 0;
            int maxIndex = 0;
            for(int i = 0; i<boxes.size(); i++){
                Integer[] boxI = boxes.get(i);
                for(int j = 0; j<i; j++){
                    Integer[] boxJ = boxes.get(j);
                    boolean isStrictlySmaller = boxJ[0] < boxI[0] && boxJ[1] < boxI[1] && boxJ[2] < boxI[2];
                    if(isStrictlySmaller && dp[i] <= dp[j] + boxI[2]){
                        dp[i] = dp[j] + boxI[2];
                        seq[i] = j;
                    }
                }
                if( maxHeight < dp[i]){
                    maxHeight = dp[i];
                    maxIndex = i;
                }
            }
            System.out.println(maxHeight);
            setSequenceList(boxes, stack, seq, maxIndex);
        }
        return stack;
    }

    public static void setSequenceList(ArrayList<Integer[]> boxes, ArrayList<Integer[]> result, int[] seq, int i){
        if(i != -1){
            setSequenceList(boxes, result, seq, seq[i]);
            result.add(boxes.get(i));
        }
    }

    //{L:1,B:2,H:3}
    public static void addAllRotations(Integer[] box, ArrayList<Integer[]> boxes){
        int h = box[0];
        int b = Math.min(box[1], box[2]);
        int l = Math.max(box[1], box[2]);
        boxes.add(new Integer[]{l,b,h});

        h = box[1];
        b = Math.min(box[0], box[2]);
        l = Math.max(box[0], box[2]);
        boxes.add(new Integer[]{l,b,h});

        h = box[2];
        b = Math.min(box[0], box[1]);
        l = Math.max(box[0], box[1]);
        boxes.add(new Integer[]{l,b,h});
    }

    public static void boxStackingRotationsAllowed(ArrayList<Integer[]> boxes){
        ArrayList<Integer[]> boxList = new ArrayList<>();
        for(Integer[] box : boxes){
            addAllRotations(box, boxList);
        }
        Collections.sort(boxList, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return Integer.valueOf(o2[0]*o2[1]).compareTo(Integer.valueOf(o1[0]*o1[1]));
            }
        });
        int n = boxList.size();
        int[] heights = new int[n];
        int[] seq = new int[n];
        Arrays.fill(seq, -1);
        int maxHeight = 0;
        int maxIndex = 0;
        for(int i=0; i<n; i++){
            Integer[] boxI = boxList.get(i);
            heights[i] = boxI[2];
            for(int j = 0; j<i; j++){
                Integer[] boxJ = boxList.get(j);
                boolean isStrictlyDecreasing = boxJ[0] > boxI[0] && boxJ[1] > boxI[1];
                if(isStrictlyDecreasing && heights[i] < heights[j] + boxI[2]){
                    heights[i] = heights[j] + boxI[2];
                    seq[i] = j;
                }
            }
            if(maxHeight < heights[i]){
                maxHeight = heights[i];
                maxIndex = i;
            }
        }
        System.out.println(maxHeight);
        while (maxIndex != -1){
            System.out.println(Arrays.toString(boxList.get(maxIndex)));
            maxIndex = seq[maxIndex];
        }
    }

    public static void main(String[] args) {

        /*ArrayList<Integer[]> boxes = new ArrayList<>();
        boxes.add(new Integer[]{2,1,2});
        boxes.add(new Integer[]{3,2,3});
        boxes.add(new Integer[]{2,2,8});
        boxes.add(new Integer[]{2,3,4});
        boxes.add(new Integer[]{1,2,1});
        boxes.add(new Integer[]{4,4,5});
        for(Integer[] box : boxStacking(boxes)){
            System.out.println(box[0] + "," + box[1] + "," + box[2]);
        }*/

        //4, 6, 7}, {1, 2, 3}, {4, 5, 6}, {10, 12, 32}
        ArrayList<Integer[]> boxes = new ArrayList<>();
        boxes.add(new Integer[]{7,6,4});
        boxes.add(new Integer[]{3,2,1});
        boxes.add(new Integer[]{6,5,4});
        boxes.add(new Integer[]{32,12,10});
        boxStackingRotationsAllowed(boxes);
    }
}
