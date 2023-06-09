package ui;

import model.FantasyNbaTeam;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

// Fantasy NBA application
public class FantasyApp {
    private static final String JSON_STORE = "./data/fantasyNbaTeam.json";
    private FantasyNbaTeam team;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the NBA fantasy application
    public FantasyApp() throws FileNotFoundException {
        runFantasyApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    //SOURCE: TellerApp
    private void runFantasyApp() {
        boolean keepGoing = true;
        String command = null;

        initialize();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    //SOURCE: TellerApp(edited)
    private void processCommand(String command) {
        if (command.equals("cp")) {
            createNewPlayer();
        } else if (command.equals("ct")) {
            createNewFantasyTeam();
        } else if (command.equals("apt")) {
            addPlayerToTeam();
        } else if (command.equals("vp")) {
            displayPlayers();
        } else if (command.equals("as")) {
            addStatistics();
        } else if (command.equals("vps")) {
            viewStatistics();
        } else if (command.equals("s")) {
            saveFantasyNbaTeam();
        } else if (command.equals("l")) {
            loadFantasyNbaTeam();
        } else {
            System.out.println("Selection not valid");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes fantasy NBA team
    //SOURCE: TellerApp(edited)
    private void initialize() {
        team = new FantasyNbaTeam("");
        input = new Scanner(System.in);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of user input options
    //SOURCE: TellerApp(edited)
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tcp -> create player");
        System.out.println("\tct -> create team");
        System.out.println("\tapt -> add a player to a fantasy team");
        System.out.println("Functions below only work if players have already been added to your fantasy team");
        System.out.println("\tas -> add statistics for player on your fantasy team");
        System.out.println("\tvp -> view players on fantasy team");
        System.out.println("\tvps -> view a player's stats");
        System.out.println("\ts -> save fantasy NBA team to file");
        System.out.println("\tl -> load fantasy NBA team from file");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: creates a new player and returns that player.
    private Player createNewPlayer() {
        Player pl = new Player("", 0, "");
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        System.out.println("Enter a player name");
        String pname = sc1.nextLine();
        pl.setName(pname);
        System.out.println("Enter player's jersey number");
        int pnum = sc2.nextInt();
        pl.setJerseyNumber(pnum);
        System.out.println("Enter player's NBA team name");
        String tname = sc3.nextLine();
        pl.setTeamName(tname);
        System.out.println(pl.getName() + " " + pl.getJerseyNumber() + " " + pl.getTeam());
        return pl;

    }


    // MODIFIES: this
    // EFFECTS: creates a new fantasy nba team, and sets the team name to user inputted name.
    private void createNewFantasyTeam() {
        team = new FantasyNbaTeam("");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name for your Fantasy Team");
        String ftname = sc.nextLine();
        team.setFantasyTeamName(ftname);
        System.out.println(team.getFantasyTeamName());
    }

    // MODIFIES: this
    // EFFECTS: creates a player and adds it to the fantasy team.
    private void addPlayerToTeam() {
        team.addPlayerToFantasyTeam(createNewPlayer());
        System.out.println("Would you like to add another player?");
        Scanner sc3 = new Scanner(System.in);
        String answer1 = sc3.nextLine();
        if ((Objects.equals(answer1, "yes"))) {
            addPlayerToTeam();
        } else {
            System.out.println("Ok, we won't add another player");
            System.out.println(team.getPlayersOnTeam());
        }
    }

    // EFFECTS: displays the name of the players currently on the fantasy team roster
    private void displayPlayers() {
        System.out.println(team.getPlayersOnTeam());
    }

    // REQUIRES: player that statistics want to be added for must already be on the fantasy team
    // MODIFIES: this
    // EFFECTS: finds player on fantasy team based on user input, then adds points, rebounds, and assists for that
    //          player, again based on user input.
    private void addStatistics() {
        System.out.println("Enter player who you want to add statistics for");
        Scanner sc = new Scanner(System.in);
        String playerName = sc.nextLine();
        Player playa = team.findPlayerOnTeam(playerName);
        System.out.println("Enter points");
        Scanner sc1 = new Scanner(System.in);
        int points = sc1.nextInt();
        playa.addPoints(points);
        System.out.println("Enter rebounds");
        Scanner sc2 = new Scanner(System.in);
        int rebounds = sc2.nextInt();
        playa.addRebounds(rebounds);
        System.out.println("Enter assists");
        Scanner sc3 = new Scanner(System.in);
        int assists = sc3.nextInt();
        playa.addAssists(assists);
        System.out.println(playerName + ":" + " Points = " + playa.getPoints() + " Rebounds = " + playa.getRebounds()
                + " Assists = " + playa.getAssists());


    }

    // REQUIRES: playerName must be of a player already added to the team.
    // EFFECTS: returns inputted players stats: points, rebounds, and assists
    private void viewStatistics() {
        System.out.println("Enter player's name");
        Scanner sc = new Scanner(System.in);
        String playerName = sc.nextLine();
        Player statsPlayer = team.findPlayerOnTeam(playerName);
        System.out.println("Points: " + statsPlayer.getPoints() + " Rebounds: " + statsPlayer.getRebounds()
                + " Assists: " + statsPlayer.getAssists());
    }

    //SOURCE: JsonSerializationDemo CPSC 210
    // EFFECTS: saves the fantasy NBA team to file
    private void saveFantasyNbaTeam() {
        try {
            jsonWriter.open();
            jsonWriter.write(team);
            jsonWriter.close();
            System.out.println("Successfully saved " + team.getFantasyTeamName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file to: " + JSON_STORE);
        }
    }

    // SOURCE: JsonSerializationDemo CPSC 210
    // MODIFIES: this
    // EFFECTS: loads fantasy NBA team from file
    private void loadFantasyNbaTeam() {
        try {
            team = jsonReader.read();
            System.out.println("Loaded " + team.getFantasyTeamName() + " from " + JSON_STORE);
            System.out.println("These are the players currently on the team: " + team.getPlayersOnTeam());
        } catch (IOException e) {
            System.out.println("Failed to read from file: " + JSON_STORE);
        }
    }
}
