package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

// Class representing a Fantasy NBA team
public class FantasyNbaTeam {
    public static final int MAX_NUM_PLAYERS = 12;
    private String fantasyTeam;
    private int numberOfPlayers;
    private ArrayList<Player> playersOnTeam;

    public FantasyNbaTeam(String fantasyTeamName) {
        fantasyTeam = fantasyTeamName;
        numberOfPlayers = 0;
        playersOnTeam = new ArrayList<>();
    }

    public boolean addPlayerToFantasyTeam(Player p) {
        if (this.numberOfPlayers < MAX_NUM_PLAYERS) {
            this.playersOnTeam.add(p);
            numberOfPlayers += 1;
            return true;
        }
        return false;
    }

    Player findPlayerOnTeam(String playerName) {
        for (Player player : playersOnTeam) {
            if (Objects.equals(player.getName(), playerName)) {
                return player;
            }
        }
        return null;
    }

    public String getFantasyTeamName() {
        return fantasyTeam;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public ArrayList<Player> getPlayersOnTeam() {
        return playersOnTeam;
    }

    public void setFantasyTeamName(String name) {
        this.fantasyTeam = name;
    }
}
