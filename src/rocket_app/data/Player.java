package rocket_app.data;

public class Player implements Comparable<Player>{

    private int rank;
    private String playerName;
    private double fuel;
    private static int iterator = 1;

    public Player(String playerName, double fuel) {
        this.playerName = playerName;
        this.fuel = fuel;
        rank = iterator++;
    }

    public int getRank() {
        return rank;
    }

    public String getPlayerName() {
        return playerName;
    }

    public double getFuel() {
        return fuel;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Number: "+ rank + " player: "+playerName + " fuel: "+fuel;
    }

    @Override
    public int compareTo(Player o) {
        return Double.compare(o.fuel,fuel);
    }
}
