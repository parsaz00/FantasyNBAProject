package model;

// Represents an NBA player, having a name (first and last), a jersey number, NBA team
//  and statistics: Points, Rebounds and Assists.
public class Player {
    private String name;
    private int jerseyNumber;
    private String nbaTeam;
    private int points;
    private int rebounds;
    private int assists;

    /*
     * REQUIRES: playerName and teamName has non-zero length, jerseyNum >= 0, teamName is a current NBA team
     * EFFECTS: player name is set to playerName; jerseyNum is a positive integer assigned to a player; the NBA
     *          team the player belongs to is set to nbaTeam
     */
    public Player(String playerName, int jerseyNum, String teamName) {
        name = playerName;
        jerseyNumber = jerseyNum;
        nbaTeam = teamName;
        points = 0;
        rebounds = 0;
        assists = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int number) {
        this.jerseyNumber = number;
    }

    public String getTeam() {
        return nbaTeam;
    }

    public void setTeamName(String teamName) {
        this.nbaTeam = teamName;
    }


    public int getPoints() {
        return points;
    }

    public int getRebounds() {
        return rebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void addPoints(int p) {
        points += p;
    }

    public void addRebounds(int r) {
        rebounds += r;
    }

    public void addAssists(int a) {
        assists += a;
    }


}