package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

// Class representing a Fantasy NBA team
public class FantasyNbaTeam implements Writeable {
    public static final int MAX_NUM_PLAYERS = 12;
    private String fantasyTeam;
    private int numberOfPlayers;
    private final ArrayList<Player> playersOnTeam;

    // REQUIRES: fantasyTeamName has non-zero length
    // EFFECTS: creates a new fantasy team, with fantasy team name set to fantasyTeamName and
    //          an empty roster (i.e., no players on the team), and numberOfPlayers set to zero.
    public FantasyNbaTeam(String fantasyTeamName) {
        fantasyTeam = fantasyTeamName;
        numberOfPlayers = 0;
        playersOnTeam = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("Fantasy NBA team " + this.fantasyTeam + " was created"));
    }

    // REQUIRES: p has to be a valid player that's already been created
    // MODIFIES: this
    // EFFECTS: if the fantasy team has less than MAX_NUM_PLAYERS, add the (p)layer to the fantasy team, increments the
    //          number of players on the team by 1, and returns true. If the fantasy team has >= MAX_NUM_PLAYERS
    //          number of players, does NOT add the (p)layer and returns false.
    public boolean addPlayerToFantasyTeam(Player p) {
        if (this.numberOfPlayers < MAX_NUM_PLAYERS) {
            this.playersOnTeam.add(p);
            numberOfPlayers += 1;
            EventLog.getInstance().logEvent(new Event("Player was added to team"));
            return true;
        }
        return false;
    }

    // EFFECTS: returns the player based on the inputted playerName. If the player is NOT on the fantasy team, returns
    //          null.
    public Player findPlayerOnTeam(String playerName) {
        for (Player player : playersOnTeam) {
            if (Objects.equals(player.getName(), playerName)) {
                EventLog.getInstance().logEvent(new Event(playerName + " was searched for and returned"));
                return player;
            }
        }
        EventLog.getInstance().logEvent(new Event("Could not find player, so null was returned"));
        return null;
    }

    // EFFECTS: returns fantasy team name
    public String getFantasyTeamName() {
        return fantasyTeam;
    }

    // EFFECTS: returns the number of players on the fantasy team.
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public List<Player> getPlayers() {
        return this.playersOnTeam;
    }

    // EFFECTS: returns the names of the players on the fantasy team
    public List<String> getPlayersOnTeam() {
        List<String> playersOnFTeam = new ArrayList<>();
        for (Player playa : playersOnTeam) {
            playersOnFTeam.add(playa.getName());
        }
        EventLog.getInstance().logEvent(new Event("Players on the Fantasy Team were returned"));
        return playersOnFTeam;
    }

    // EFFECTS: Returns the player with the most points on the team
    public Player getPointsLeader() {
        Player pointsLeader = this.playersOnTeam.get(0);
        for (Player player : this.playersOnTeam) {
            if (player.getPoints() >= pointsLeader.getPoints()) {
                pointsLeader = player;
            }
        }
        EventLog.getInstance().logEvent(new Event("Points leader " + pointsLeader.getName()
                + " was returned"));
        return pointsLeader;
    }

    // EFFECTS: Returns the player with the most rebounds on the team
    public Player getReboundsLeader() {
        Player reboundLeader = this.playersOnTeam.get(0);
        for (Player player : this.playersOnTeam) {
            if (player.getRebounds() >= reboundLeader.getRebounds()) {
                reboundLeader = player;
            }
        }
        EventLog.getInstance().logEvent(new Event("Rebounds leader " + reboundLeader.getName()
                + " was returned"));
        return reboundLeader;
    }

    // EFFECTS: Returns the player with the most assists on the team
    public Player getAssistsLeader() {
        Player assistsLeader = this.playersOnTeam.get(0);
        for (Player player : this.playersOnTeam) {
            if (player.getAssists() >= assistsLeader.getAssists()) {
                assistsLeader = player;
            }
        }
        EventLog.getInstance().logEvent(new Event("Assists leader " + assistsLeader.getName()
                + " was returned"));

        return assistsLeader;
    }


    // REQUIRES: name has to have a string-length > 0
    // MODIFIES: this
    // EFFECTS: sets the fantasy team name to name.
    public void setFantasyTeamName(String name) {
        this.fantasyTeam = name;
        EventLog.getInstance().logEvent(new Event("Fantasy Team name was set to " + name));

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("team name", fantasyTeam);
        json.put("number of players", numberOfPlayers);
        json.put("players on team", playerOnTeamToJson());
        return json;
    }

    // EFFECTS: returns players on this Fantasy NBA team as a JSON array
    private JSONArray playerOnTeamToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Player p : playersOnTeam) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

}
