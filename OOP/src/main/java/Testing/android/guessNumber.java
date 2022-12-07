package Testing.android;

import Lib.Util;

public class guessNumber {

    public static void main(String[] args) {
        System.out.println(number());
    }

    public static int number() {
        return Util.randBetween(1, 10);

    }

}
