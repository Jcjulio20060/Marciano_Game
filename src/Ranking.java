public class Ranking {
    String name;
    int attempts;

    Ranking(String name, int attempts){
        this.name = name;
        this.attempts = attempts;
    }

    @Override
    public String toString() {
        return name + " - " + attempts + " tentativas";
    }
}