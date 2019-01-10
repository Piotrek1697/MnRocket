package rocket_app.data;

/**
 * Class created to storing player data.
 */
public class Player implements Comparable<Player>{

    private int rank;
    private String playerName;
    private double fuel;
    private static int iterator = 1;

    /**
     * @param playerName - name of player
     * @param fuel - remaining fuel in tank [kg]
     */
    public Player(String playerName, double fuel) {
        this.playerName = playerName;
        this.fuel = fuel;
        rank = iterator++;
    }

    /**
     * @return Position on list
     */
    public int getRank() {
        return rank;
    }

    /**
     * @return player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @return fuel that remains in tank [kg]
     */
    public double getFuel() {
        return fuel;
    }

    /**
     * @param rank - player position on list
     */
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
