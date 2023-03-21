package ui;

import model.FantasyNbaTeam;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class GuiMain extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 600;
    private FantasyNbaTeam fantasyNbaTeam;
    private JPanel homePanel;
    private JPanel fantasyTeamPanel;
    private static final String JSON_STORE = "./data/fantasyNbaTeam.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs GUI pane

    public GuiMain() throws IOException {
        setUp();
    }

    public void setUp() throws IOException {
        JFrame homeFrame = new JFrame("Fantasy NBA Application");
        homeFrame.setVisible(true);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(WIDTH, HEIGHT);
        homePanel = new JPanel(new GridLayout(4, 1));
        homeFrame.add(homePanel);
        homePanelButtonSetup();
        homePanel.add((homePicture()));
        //homePanel.add(homePanelImageDisplay());
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        if (this.fantasyNbaTeam != null) {
            JButton fantasyScreen = new JButton("Fantasy Team Screen ");
            fantasyScreenButtonMethod(fantasyScreen);
            homePanel.add(fantasyScreen);
        }

    }

    public JLabel homePicture() throws IOException {

        BufferedImage bufferedImage = ImageIO.read(new File("./data/images/fantasyBaskeball.png"));
        Image image = bufferedImage.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel homeLabel = new JLabel(imageIcon, SwingConstants.CENTER);
        return homeLabel;
    }

    public void fantasyScreenButtonMethod(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fantasyTeamFrame();
                } catch (IOException ex) {
                    System.out.println("Failed to load fantasy team!");
                }
            }
        });
    }

//    public JLabel homePanelImageDisplay() throws IOException {
//        BufferedImage bufferedImage = ImageIO.read(new File("./data/images/basketball.png"));
//        Image image = bufferedImage.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
//        ImageIcon imageIcon = new ImageIcon(image);
//        JLabel basketballPic = new JLabel();
//        basketballPic.setIcon(imageIcon);
//        return basketballPic;
//    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
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
                try {
                    fantasyTeamFrame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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

    public void fantasyTeamFrame() throws IOException {
        JFrame fantasyTeamCreationFrame = new JFrame("Fantasy Team");
        fantasyTeamCreationFrame.setVisible(true);
        fantasyTeamCreationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fantasyTeamCreationFrame.setSize(WIDTH, HEIGHT);
        fantasyTeamPanel = new JPanel(new GridLayout(4, 2));
        fantasyTeamCreationFrame.add(fantasyTeamPanel);
        addPlayerToTeam();
        addPlayerStats();
        viewPlayersOnTeam();
        viewPlayerStats();
        saveOption();
        loadOption();
        returnHome();
    }

    public void addPlayerToTeam() throws IOException {
        JButton addPlayer = new JButton("Add a player to your fantasy team");
        BufferedImage bufferedImage = ImageIO.read(new File("./data/images/plusSymbol.png"));
        Image image = bufferedImage.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(image);
        addPlayer.setIcon(imageIcon);
        addPlayer.setPreferredSize(new Dimension(50, 50));
        fantasyTeamPanel.add(addPlayer);
        addPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayerToFantasyTeam();
                JOptionPane.showMessageDialog(fantasyTeamPanel, "Player successfully added to "
                                + fantasyNbaTeam.getFantasyTeamName(), "Message",
                        JOptionPane.INFORMATION_MESSAGE);
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
        try {
            playerStatsAddedDisplay();
        } catch (IOException e) {
            System.out.println("Failed to load display");
        }

    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void playerStatsAddedDisplay() throws IOException {
        JFrame statsAddedFrame = new JFrame();
        statsAddedFrame.setVisible(true);
        statsAddedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        statsAddedFrame.setSize(WIDTH, HEIGHT);
        JPanel statsAddedPanel = new JPanel(new GridLayout(3, 1));
        statsAddedFrame.add(statsAddedPanel);
        BufferedImage bufferedImageImage = ImageIO.read(new File("./data/images/basketballPlayer.jpg"));
        Image image = bufferedImageImage.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel basketballPlayer = new JLabel(imageIcon);
        statsAddedPanel.add(basketballPlayer);
        JLabel text = new JLabel("Player's stats have been successfully added", SwingConstants.CENTER);
        statsAddedPanel.add(text);
        JButton returnFantasyPage = new JButton("Return to Fantasy Team Screen");
        statsAddedPanel.add(returnFantasyPage);
        returnFantasyPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fantasyTeamFrame();
                } catch (IOException ex) {
                    System.out.println("Failed to load fantasy screen");
                }
            }
        });

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
                try {
                    viewPlayersDisplay();
                } catch (IOException ex) {
                    System.out.println("Failed to load display");
                }


            }
        });
    }

    public void viewPlayersDisplay() throws IOException {
        JFrame playersDisplay = new JFrame();
        playersDisplay.setVisible(true);
        playersDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playersDisplay.setSize(WIDTH, HEIGHT);
        JPanel playerDisplayPanel = new JPanel(new GridLayout(this.fantasyNbaTeam.getNumberOfPlayers() + 1, 1));
        playersDisplay.add(playerDisplayPanel);
        for (String player : this.fantasyNbaTeam.getPlayersOnTeam()) {
            playerDisplayPanel.add(createPlayerLabel(player));
        }
        playerDisplayPanel.add(returnToFantasyScreen());
    }

    public JButton returnToFantasyScreen() {
        JButton returnToFantasyScreenButton = new JButton("Return to Fantasy Screen");
        returnToFantasyScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fantasyTeamFrame();
                } catch (IOException ex) {
                    System.out.println("Failed to return home");
                }
            }
        });
        return returnToFantasyScreenButton;
    }

    public JLabel createPlayerLabel(String name) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File("./data/images/basketball.png"));
        Image image = bufferedImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel label = new JLabel(name, imageIcon, JLabel.CENTER);
        return label;

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

    public void returnHome() throws IOException {
        JButton goHome = new JButton("Return to home screen");
        fantasyTeamPanel.add(goHome);
        BufferedImage bufferedImage = ImageIO.read(new File("./data/images/homeScreen.png"));
        Image image = bufferedImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(image);
        goHome.setIcon(imageIcon);
        goHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setUp();
                } catch (IOException ex) {
                    System.out.println("Unable to load image");
                }
            }
        });
    }

    public static void main(String[] args) {
        try {
            new GuiMain();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        } catch (IOException e) {
            System.out.println("Unable to load images");
        }
    }

}
