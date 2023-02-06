package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

// Class representing a Fantasy NBA team
public class FantasyNbaTeam {
    private String fantasyTeam;
    private int numberOfPlayers;
    private ArrayList<Player> playersOnTeam;

    public FantasyNbaTeam(String fantasyTeamName) {
        fantasyTeam = fantasyTeamName;
        numberOfPlayers = 0;
        playersOnTeam = new ArrayList<Player>();
    }

    public FantasyNbaTeam addPlayerToFantasyTeam(FantasyNbaTeam fnt, Player p) {
        if (fnt.numberOfPlayers <= 12) {
            playersOnTeam.add(p);
        }
        return fnt;
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
}
