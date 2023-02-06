package model;

// Represents a NBA player, having a name (first and last), a jersey number, and NBA team.
public class Player {
    private String name;
    private int jerseyNumber;
    private String nbaTeam;

    /*
     * REQUIRES: playerName and teamName has non-zero length, jerseyNum >= 0, teamName is a current NBA team
     * EFFECTS: player name is set to playerName; jerseyNum is a positive integer assigned to a player; the NBA
     *          team the player belongs to is set to nbaTeam
     */
    public Player(String playerName, int jerseyNum, String teamName) {
        name = playerName;
        jerseyNumber = jerseyNum;
        nbaTeam = teamName;
    }

    public String getName() {
        return name;
    }
    public int getJerseyNumber() {
        return jerseyNumber;
    }
    public String getTeam() {
        return nbaTeam;
    }
}