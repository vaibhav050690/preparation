import java.util.Stack;

/*
https://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 */
public class HistogramLargestRecArea {

    public int largestArea(int[] hist){
        Stack<Integer> stack = new Stack<>();
        int maxArea = -1;
        for(int i =0; i<hist.length; i++){
            while (!stack.isEmpty() && hist[i] < hist[stack.peek()]){
                int temp = hist[stack.pop()];
                int area = temp * (stack.isEmpty() ? i : (i - stack.peek()) -1);
                if(area > maxArea){
                    maxArea = area;
                }
            }
            stack.push(i);
        }
        int i = hist.length;
        while (!stack.isEmpty()){
            int temp = hist[stack.pop()];
            int area = temp * (stack.isEmpty() ? i : (i - stack.peek()) -1);
            if(area > maxArea){
                maxArea = area;
            }
        }
        return maxArea;
    }


    public static void main(String[] args) {
        int hist[] = {6, 2, 5, 4, 5, 1, 6};
        //int hist[] = {1, 2, 3, 4, 5, 6, 7};

        HistogramLargestRecArea obj = new HistogramLargestRecArea();
        System.out.println(obj.largestArea(hist));
    }
}