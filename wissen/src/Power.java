import java.math.BigInteger;

public class Power {

    public static BigInteger power(int x, int y){
        if(y == 1){
            return BigInteger.valueOf(x);
        }
        BigInteger temp = power(x,y/2);
        if(y % 2 == 0){
            temp = temp.multiply(temp);
        }
        else {
            temp=(temp.multiply(temp)).multiply(BigInteger.valueOf(x));
        }
        return temp;
    }

    public static double squareRoot(int x, int precision){
        int low = 0;
        int high = x;
        int mid;
        double answer = -1;
        boolean isPerfect = false;
        while (low <= high){
            mid = (low + high)/2;
            if(mid*mid == x){
                answer = mid;
                isPerfect = true;
                break;
            }
            else if(mid*mid < x){
                answer = mid;
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        if(!isPerfect){
            double inc = 0.1;
            for(int i =0; i< precision; i++){
                while (answer*answer <= x){
                    answer+=inc;
                }
                answer-=inc;
                inc = inc/10;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        /*System.out.println(Power.power(2,8));
        System.out.println(Power.power(2,7));
        System.out.println(Power.power(2,3));
        System.out.println(Power.power(2,4));
        System.out.println(Power.power(100,100));*/

        System.out.println(squareRoot(2,2));
    }
}