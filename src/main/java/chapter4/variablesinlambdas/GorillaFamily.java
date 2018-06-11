package chapter4.variablesinlambdas;

interface Gorilla {
    String move();
}

public class GorillaFamily {
    String walk = "walk";

    void everybodyPlays(boolean baby) {
        String approach = "amble";
// if uncomment this line approach will not be 'effectively final' anymore,
// and beyond functionality will be broken
// approach = "run";

        play(() -> approach);
        play(() -> walk);
        play(() -> baby ? walk : approach);
    }

    void play(Gorilla g) {
        System.out.println(g.move());
    }

}
