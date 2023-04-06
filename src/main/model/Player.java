package model;

import org.json.JSONObject;
import persistence.Writeable;

import java.util.Objects;

// Represents an NBA player, having a name (first and last), a jersey number, NBA team
//  and statistics: Points, Rebounds and Assists.
public class Player implements Writeable {
    private String name;
    private int jerseyNumber;
    private String nbaTeam;
    private int points;
    private int rebounds;
    private int assists;

    /*
     * REQUIRES: playerName and teamName have non-zero length, jerseyNum >= 0, teamName is a current NBA team
     * EFFECTS: player name is set to playerName; jerseyNum is a positive integer assigned to a player; the NBA
     *          team the player belongs to is set to nbaTeam, and points, rebounds, and assits are set to 0.
     */
    public Player(String playerName, int jerseyNum, String teamName) {
        name = playerName;
        jerseyNumber = jerseyNum;
        nbaTeam = teamName;
        points = 0;
        rebounds = 0;
        assists = 0;
        EventLog.getInstance().logEvent(new Event("A player " + playerName + " was created"));
    }

    // EFFECTS: returns the player's name
    public String getName() {
        return name;
    }

    // REQUIRES: name length is > 0;
    // MODIFIES: this
    // EFFECTS: sets the player's name to "name"
    public void setName(String name) {
        this.name = name;
        EventLog.getInstance().logEvent(new Event("Players name was set to " + this.name));
    }

    // EFFECTS: returns the player's jersey number
    public int getJerseyNumber() {
        return jerseyNumber;
    }

    // REQUIRES: number >= 0;
    // MODIFIES: this
    // EFFECTS: sets the player's jersey number to "number"
    public void setJerseyNumber(int number) {
        this.jerseyNumber = number;
        EventLog.getInstance().logEvent(new Event("Jersey number " + this.getJerseyNumber()
                + " was assigned to " + this.getName()));
    }

    // EFFECTS: returns the player's NBA team
    public String getTeam() {
        return nbaTeam;
    }

    // REQUIRES: teamName to be a current NBA team
    // MODIFIES: this
    // EFFECTS: sets the player's NBA team to "teamName"
    public void setTeamName(String teamName) {
        this.nbaTeam = teamName;
        EventLog.getInstance().logEvent(new Event("Team name for " + this.getName() + "was set to "
                + this.getTeam()));
    }


    // EFFECTS: returns the player's points
    public int getPoints() {
        return points;
    }

    // EFFECTS: returns the player's rebounds
    public int getRebounds() {
        return rebounds;
    }

    // EFFECTS: returns the player's assists
    public int getAssists() {
        return assists;
    }

    // REQUIRES: p >= 0
    // MODIFIES: this
    // EFFECTS: adds p to the player's current points
    public void addPoints(int p) {
        points += p;
        EventLog.getInstance().logEvent(new Event("Points were added for " + this.name));
    }

    // REQUIRES: r >= 0
    // MODIFIES: this
    // EFFECTS: adds r to player's current rebounds

    public void addRebounds(int r) {
        rebounds += r;
        EventLog.getInstance().logEvent(new Event("Rebounds were added for " + this.name));
    }

    // REQUIRES: a >= 0
    // MODIFIES: this
    // EFFECTS: adds a to player's current assists
    public void addAssists(int a) {
        assists += a;
        EventLog.getInstance().logEvent(new Event("Assists were added for " + this.name));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("jersey number", jerseyNumber);
        json.put("NBA team", nbaTeam);
        json.put("points", points);
        json.put("rebounds", rebounds);
        json.put("assists", assists);
        return json;
    }

}