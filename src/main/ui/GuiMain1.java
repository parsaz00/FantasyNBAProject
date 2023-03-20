package ui;

import model.FantasyNbaTeam;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writeable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class GuiMain1 extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 600;
    private FantasyNbaTeam fantasyNbaTeam;
    private JPanel homePanel;
    private JPanel fantasyTeamPanel;
    private static final String JSON_STORE = "./data/fantasyNbaTeam.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs GUI pane

    public GuiMain1() throws FileNotFoundException {
        setUp();
    }

    public void setUp() {
        JFrame homeFrame = new JFrame("Fantasy NBA Application");
        homeFrame.setVisible(true);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(WIDTH, HEIGHT);
        homePanel = new JPanel();
        homeFrame.add(homePanel);
        homePanelButtonSetup();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


    }

    public void homePanelButtonSetup() {
        JButton createFantasyTeam = new JButton("Create Fantasy Team");
        homePanel.add(createFantasyTeam);
        JButton createPlayer = new JButton("Create a Fantasy Player");
        homePanel.add(createPlayer);
        createFantasyTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter Fantasy Team Name");
                fantasyNbaTeam = new FantasyNbaTeam(input);
                fantasyTeamFrame();
            }
        });
        createPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = JOptionPane.showInputDialog("Enter Player Name");
                String playerJerseyNumber = JOptionPane.showInputDialog("Enter Player's Jersey Number");
                String playersTeam = JOptionPane.showInputDialog("Enter Player's NBA team");
                Player player = new Player(playerName, Integer.parseInt(playerJerseyNumber), playersTeam);
            }
        });
    }

    public void fantasyTeamFrame() {
        JFrame fantasyTeamCreationFrame = new JFrame("Fantasy Team");
        fantasyTeamCreationFrame.setVisible(true);
        fantasyTeamCreationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fantasyTeamCreationFrame.setSize(WIDTH, HEIGHT);
        fantasyTeamPanel = new JPanel();
        fantasyTeamCreationFrame.add(fantasyTeamPanel);
        addPlayerToTeam();
        addPlayerStats();
        viewPlayersOnTeam();
        viewPlayerStats();
        saveOption();
        loadOption();
        returnHome();
    }

    public void addPlayerToTeam() {
        JButton addPlayer = new JButton("Add a player to your fantasy team");
        fantasyTeamPanel.add(addPlayer);
        addPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayerToFantasyTeam();
            }
        });
    }

    public void addPlayerToFantasyTeam() {
        String playerName = JOptionPane.showInputDialog("Enter Player Name");
        String playerJerseyNumber = JOptionPane.showInputDialog("Enter Player's Jersey Number");
        String playersTeam = JOptionPane.showInputDialog("Enter Player's NBA team");
        Player player = new Player(playerName, Integer.parseInt(playerJerseyNumber), playersTeam);
        fantasyNbaTeam.addPlayerToFantasyTeam(player);
    }

    public void addPlayerStats() {
        JButton addStats = new JButton("Add Statistics for your Player");
        fantasyTeamPanel.add(addStats);
        addStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayerStatsButtonMethod();

            }
        });
    }

    public void addPlayerStatsButtonMethod() {
        String playerNameInput = JOptionPane.showInputDialog("Enter Player Name You Want to Add Stats For");
        Player playa = fantasyNbaTeam.findPlayerOnTeam(playerNameInput);
        int playerPoints = Integer.parseInt(JOptionPane.showInputDialog("Enter " + playerNameInput + "'s" + " points"));
        int playerRebounds = Integer.parseInt(JOptionPane.showInputDialog("Enter " + playerNameInput
                + "'s" + " rebounds"));
        int playerAssists = Integer.parseInt(JOptionPane.showInputDialog("Enter " + playerNameInput
                + "'s" + " assists"));
        playa.addPoints(playerPoints);
        playa.addRebounds(playerRebounds);
        playa.addAssists(playerAssists);


    }

    public void viewPlayersOnTeam() {
        JButton viewPlayers = new JButton("View players on your team");
        fantasyTeamPanel.add(viewPlayers);
        viewPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> playersOnTeam = fantasyNbaTeam.getPlayersOnTeam();
                StringBuilder playersOnTeamString = new StringBuilder();
                for (String player : playersOnTeam) {
                    playersOnTeamString.append(player).append(". ");
                }
                JOptionPane.showMessageDialog(fantasyTeamPanel, playersOnTeamString, "Players on Your Team",
                        JOptionPane.INFORMATION_MESSAGE);


            }
        });
    }

    public void viewPlayerStats() {
        JButton viewPlayerStats = new JButton("View a player's stats");
        fantasyTeamPanel.add(viewPlayerStats);
        viewPlayerStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerNameInput = JOptionPane.showInputDialog("Whose statistics would you like to see?");
                Player playa = fantasyNbaTeam.findPlayerOnTeam(playerNameInput);
                String playerStats = (playa.getName() + " Points: " + playa.getPoints() + " Rebounds: "
                        + playa.getRebounds() + " Assists: " + playa.getAssists());
                JOptionPane.showMessageDialog(fantasyTeamPanel, playerStats, (playa.getName() + "'s Statistics"),
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public void saveOption() {
        JButton save = new JButton("Save your Fantasy Team");
        fantasyTeamPanel.add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(fantasyNbaTeam);
                    jsonWriter.close();
                    System.out.println("Successfully saved " + fantasyNbaTeam.getFantasyTeamName()
                            + " to " + JSON_STORE);
                } catch (FileNotFoundException fileNotFoundException) {
                    System.out.println("Unable to save to file to: " + JSON_STORE);
                }
            }
        });
    }

    public void loadOption() {
        JButton load = new JButton("Load your team");
        fantasyTeamPanel.add(load);
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fantasyNbaTeam = jsonReader.read();
                    System.out.println("Loaded " + fantasyNbaTeam.getFantasyTeamName() + " from " + JSON_STORE);
                    System.out.println("These are the players currently on the team: "
                            + fantasyNbaTeam.getPlayersOnTeam());
                } catch (IOException ioException) {
                    System.out.println("Failed to read from file: " + JSON_STORE);
                }
            }
        });
    }

    public void returnHome() {
        JButton goHome = new JButton("Return to home screen");
        fantasyTeamPanel.add(goHome);
        goHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUp();
            }
        });
    }

    public static void main(String[] args) {
        try {
            new GuiMain1();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }

}
