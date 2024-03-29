package Uebungen_AD.week1;
//test comment for push over intellij UI
public class Ackermann {

    public long ackermannFunction(long n, long m){

        if(n == 0 && m > 0){
            return m + 1;
        }
        if (n > 0 && m==0){
            return ackermannFunction(n-1, 1);
        }
        if(n == 0 &&  m == 0){
            return 1;
        }
        else {
            return ackermannFunction(n-1, ackermannFunction(n, m-1));
        }
    }
}
