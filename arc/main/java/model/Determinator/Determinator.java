package arc.main.java.model.Determinator;

import java.util.Random;

public class Determinator {
    Boolean result;
    Random random = new Random();

    public Determinator() {
        result = false;
    }

    public Boolean getResult(int diff, int add) {
        int randomInt = random.nextInt(10) + 2;
        return (randomInt + add) >= diff;
    }

}
