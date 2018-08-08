package binarysearch;

/*
Binary Search for Rational Numbers without using floating point arithmetic
A rational is represented as p/qb, for example 2/3. Given a sorted array of rational numbers,
how to search an element using Binary Search. Use of floating point arithmetic is not allowed.

Example:

Input:  arr[] = {1/5, 2/3, 3/2, 13/2}
        x = 3/2
Output: Found at index 2

To compare two rational numbers p/q and r/s, we can compare p*s with q*r.
 */


import java.util.Objects;

class Rational {
    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rational rational = (Rational) o;
        return p == rational.p &&
                q == rational.q;
    }

    @Override
    public int hashCode() {
        return Objects.hash(p, q);
    }

    @Override
    public String toString() {
        return "Rational{" +
                "p=" + p +
                ", q=" + q +
                '}';
    }

    public Rational(int p, int q) {
        this.p = p;
        this.q = q;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    private int p;
    private int q;

}

public class BinarySearchForRationalNumbersWithoutUsingFloatingArithmetic {

    public static int compareRational(Rational x, Rational y){
        if(x.equals(y)){
            return 0;
        }
        if(x.getP()*y.getQ() > y.getP()*x.getQ()){
            return 1;
        }
        return -1;
    }

    public static int searchRational(Rational[] arr, Rational x, int low, int high){
        if(low > high){
            return -1;
        }
        int mid = low + (high - low)/2;
        if(arr[mid].equals(x)){
            return mid;
        }
        else if(compareRational(arr[mid],x) == 1){
            return searchRational(arr,x,low,mid-1);
        }
        return searchRational(arr,x,mid+1,high);
    }

    public static void main(String[] args) {
        Rational[] arr = {new Rational(1,5),new Rational(2,3),new Rational(3,2),new Rational(13,2)};
        System.out.println(searchRational(arr,new Rational(3,2),0,arr.length-1));
    }
}