package Testing.android;

public class factorial {

    public static void main(String[] args) {
        calcularFactorial(10);
    }

    public static void calcularFactorial(int numero) {
        int factorial = 1;

        if (numero % 2 == 0) {
            for (int i = factorial; i <= numero; ++i) {
                factorial *= i;
            }
            System.out.println(factorial);
        } else {
            System.out.println("Error");
        }

    }

}
