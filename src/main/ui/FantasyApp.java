package ui;

import model.FantasyNbaTeam;
import model.Player;

import java.util.Objects;
import java.util.Scanner;

public class FantasyApp {
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
            addPlayerToTeam();
        } else {
            System.out.println("Selection not valid");
        }
    }

    private void init() {
        //player = new Player("", 0, "");
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

    private Player createNewPlayer() {
        Player pl = new Player("", 0, "");
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        System.out.println("Enter a player name");
        String pname = sc1.nextLine();
        pl.setName(pname);
        System.out.println("Enter player jersey number");
        int pnum = sc2.nextInt();
        pl.setJerseyNumber(pnum);
        System.out.println("Enter player's team name");
        String tname = sc3.nextLine();
        pl.setTeamName(tname);
        System.out.println(pl.getName() + " " + pl.getJerseyNumber() + " " + pl.getTeam());
        return pl;

    }


    private void createNewFantasyTeam() {
        team = new FantasyNbaTeam(" ");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name for your Fantasy Team");
        String ftname = sc.nextLine();
        team.setFantasyTeamName(ftname);
        System.out.println(team.getFantasyTeamName());
    }

    private void addPlayerToTeam() {
        System.out.println("Would you like to add a player to the fantasy team?"
                +
                " Reply yes to do so, no to stop adding players");
        Scanner sc2 = new Scanner(System.in);
        String answer = sc2.nextLine();
        if (Objects.equals(answer, "yes")) {
            team.addPlayerToFantasyTeam(createNewPlayer());
            System.out.println("Would you like to add another player");
            Scanner sc3 = new Scanner(System.in);
            String answer1 = sc3.nextLine();
            if ((Objects.equals(answer1, "yes"))) {
                addPlayerToTeam();
            } else {
                System.out.println(team.getPlayersOnTeam());
            }
        } else {
            System.out.println("Ok we won't add anyone to the team");
            System.out.println(team.getPlayersOnTeam());
        }
    }

}
