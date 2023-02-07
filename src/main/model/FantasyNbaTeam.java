package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

// Class representing a Fantasy NBA team
public class FantasyNbaTeam {
    public static final int MAX_NUM_PLAYERS = 12;
    private String fantasyTeam;
    private int numberOfPlayers;
    private LinkedList<Player> playersOnTeam;

    public FantasyNbaTeam(String fantasyTeamName) {
        fantasyTeam = fantasyTeamName;
        numberOfPlayers = 0;
        playersOnTeam = new LinkedList<>();
    }

    public boolean addPlayerToFantasyTeam(Player p) {
        if (this.numberOfPlayers <= MAX_NUM_PLAYERS) {
            this.playersOnTeam.add(p);
            numberOfPlayers += 1;
            return true;
        }
        return false;
    }

    public String getFantasyTeamName() {
        return fantasyTeam;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public LinkedList<Player> getPlayersOnTeam() {
        return playersOnTeam;
    }
}
