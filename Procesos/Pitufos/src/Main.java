import Model.Pitufo;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static final List<Pitufo>PITUFOS = List.of(new Pitufo("PAPA ", 2000L), new Pitufo("MAMA", 5000L), new Pitufo("HERMANO",1000L),new Pitufo("HERMANA ",3000L));

    public static void main(String[] args) {

        for ( Pitufo pitufo : PITUFOS) {
            Thread thread = new Thread(pitufo);
            thread.start();
        }
    }
}