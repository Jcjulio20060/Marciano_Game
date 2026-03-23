public class Ranking {
    public int getAttempts() {
        return attempts;
    }

    public String getName() {
        return name;
    }

    private final String name;
    private final int attempts;

    Ranking(String name, int attempts){
        this.name = name;
        this.attempts = attempts;
    }

    @Override
    public String toString() {
        return name + " - " + attempts + " tentativas";
    }
}