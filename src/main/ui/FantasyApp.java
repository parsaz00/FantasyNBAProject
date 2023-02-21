package ui;

import model.FantasyNbaTeam;
import model.Player;
import java.util.Objects;
import java.util.Scanner;

public class FantasyApp {
    private Player player;
    private FantasyNbaTeam team;
    private Scanner input;

    //EFFECTS: runs the NBA fantasy application
    public FantasyApp() {
        runFantasyApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user input

    private void runFantasyApp() {
        boolean keepGoing = true;
        String command = null;

        init();

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

    private void processCommand(String command) {
        if (command.equals("cp")) {
            createNewPlayer();
        } else if (command.equals("ct")) {
            createNewFantasyTeam();
        } else if (command.equals("apt")) {
            addPlayerToFantasyTeam();
        } else {
            System.out.println("Selection not valid");
        }
    }

    private void init() {
        player = new Player("", 0, "");
        team = new FantasyNbaTeam("");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tcp -> create player");
        System.out.println("\tct -> new team");
        System.out.println("\tapt -> add a player to a fantasy team");
        System.out.println("\tq -> quit");
    }

    private void createNewPlayer() {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        System.out.println("Enter a player name");
        String pname = sc1.nextLine();
        player.setName(pname);
        System.out.println("Enter player jersey number");
        int pnum = sc2.nextInt();
        player.setJerseyNumber(pnum);
        System.out.println("Enter player's team name");
        String tname = sc3.nextLine();
        player.setTeamName(tname);
        System.out.println(player.getName() + " " + player.getJerseyNumber() + " " + player.getTeam());

    }


    private void createNewFantasyTeam() {
        team = new FantasyNbaTeam(" ");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name for your Fantasy Team");
        String ftname = sc.nextLine();
        team.setFantasyTeamName(ftname);
        System.out.println(team.getFantasyTeamName());
    }

    private void addPlayerToFantasyTeam() {
        createNewPlayer();
        createNewFantasyTeam();
        System.out.println("Would you like to add this player to your fantasy team? Reply yes or no");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        if (Objects.equals(answer, "yes")) {
            team.addPlayerToFantasyTeam(player);
            System.out.println(player.getName() + " has been added to " + team.getFantasyTeamName());
        } else {
            System.out.println("Ok, we won't add them!");
        }
    }

}
