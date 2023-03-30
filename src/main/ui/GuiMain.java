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
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

// Fantasy NBA GUI application
public class GuiMain extends JFrame {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 800;
    private FantasyNbaTeam fantasyNbaTeam;
    private JFrame fantasyTeamCreationFrame;
    private JFrame homeFrame;
    private JFrame playersDisplay;
    private JPanel homePanel;
    private JPanel fantasyTeamPanel;
    private static final String JSON_STORE = "./data/fantasyNbaTeam.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private final Map<String, String> playerStats = new HashMap<>();

    // MODIFIES: this
    // EFFECTS: constructs GUI pane. If any of the button icons fail to load, or file path cannot be found, throws
    //          IOException
    public GuiMain() throws IOException {
        try {
            setUp();
        } catch (InterruptedException e) {
            System.out.println("Gif was interrupted");
        }
    }

    // SOURCE: https://www.java67.com/2018/03/a-simple-example-to-check-if-file-is-empty-in-java.html#:~:text=Well%2C%20it's%20pretty%20easy%20to,the%20file%20doesn't%20exist.
    // MODIFIES: this
    // EFFECTS: sets up the home screen for the GUI for the Fantasy NBA Team. If URL is invalid, throws IOException.
    //          If the gif is interrupted when loading, throws InterruptedException.
    public void setUp() throws IOException, InterruptedException {
        homeScreenSetup();
        addFantasyTeamButtonSetup();
        addPlayerButtonSetUp();
        homePanel.add((homePicture()));
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        File loadFile = new File("./data/fantasyNbaTeam.json");
        if (this.fantasyNbaTeam != null) {
            createReturnToFantasyScreen();
        }
        if (loadFile.length() >= 5) {
            try {
                homePanel.add(loadOption());
            } catch (InterruptedException e) {
                System.out.println("Load time error");
            }
        }
        this.homeFrame.setVisible(true);
    }

    private void createReturnToFantasyScreen() {
        JButton fantasyScreen = new JButton("Fantasy Team Screen");
        fantasyScreenButtonMethod(fantasyScreen);
        homePanel.add(fantasyScreen);
    }

    // MODIFIES: this
    // EFFECTS: creates Frame and Panel for the home screen GUI display
    private void homeScreenSetup() {
        this.homeFrame = new JFrame("Fantasy NBA Application");
        this.homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.homeFrame.setSize(WIDTH, HEIGHT);
        this.homePanel = new JPanel(new GridLayout(5, 1));
        this.homePanel.setBackground(Color.cyan);
        this.homeFrame.add(homePanel);
    }

    // SOURCE: https://stackoverflow.com/questions/26474909/gif-not-playing-in-jframe
    // EFFECTS: Creates the gif to be displayed on the home screen. If URL is invalid, throws IOException.
    //          If the gif is interrupted when loading, throws InterruptedException
    public JLabel homePicture() throws IOException, InterruptedException {
        URL url = new URL("https://media.tenor.com/nC2TXxXn1Y4AAAAi/spurs-bling.gif");
        Image image = Toolkit.getDefaultToolkit().createImage(url).getScaledInstance(200, 200,
                Image.SCALE_DEFAULT);
        MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        mediaTracker.waitForAll();
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel homeLabel = new JLabel(imageIcon, SwingConstants.CENTER);

        return homeLabel;
    }

    // REQUIRES: this.fantasyNbaTeam != null
    // MODIFIES: this
    // EFFECTS: disposes of the home display frame, and generates the Fantasy Team frame. (User presses it to return
    //          to the fantasy screen if they are on the home screen
    public void fantasyScreenButtonMethod(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    homeFrame.dispose();
                    fantasyTeamFrame();
                } catch (IOException ex) {
                    System.out.println("Failed to load fantasy team!");
                    JOptionPane.showMessageDialog(homePanel, "Failed to load fantasy team, please try again.",
                            "Error Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }


    // EFFECTS: Creates button to create a fantasy team, and when pressed, disposes of home frame and takes user
    //          to the fantasy frame
    public void addFantasyTeamButtonSetup() {
        JButton createFantasyTeam = new JButton("Create Fantasy Team");
        createFantasyTeam.setBackground(Color.blue);
        homePanel.add(createFantasyTeam);
        createFantasyTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter Fantasy Team Name");
                fantasyNbaTeam = new FantasyNbaTeam(input);
                try {
                    homeFrame.dispose();
                    fantasyTeamFrame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    // EFFECTS: creates the add player button, and instantiates a new Player with appropriate fields
    public void addPlayerButtonSetUp() {
        JButton createPlayer = new JButton("Create a Fantasy Player");
        homePanel.add(createPlayer);
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

    // MODIFIES: this
    // EFFECTS: creates the fantasy team frame and panel, with the following buttons:
    //          "Add a player to your fantasy team"; "Add Statistics for your Player" ; "View players on your team"
    //          "View a player's stats" ; "View Statistical Leaders on Your Team" ; "Save your Fantasy Team";
    //          "Load your Fantasy Team"; "Return to home screen". If file path for button imageIcons doesn't exist
    //           throws IOException.
    public void fantasyTeamFrame() throws IOException {
        fantasyTeamCreationFrame = new JFrame("Fantasy Team");
        fantasyTeamCreationFrame.setVisible(true);
        fantasyTeamCreationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fantasyTeamCreationFrame.setSize(WIDTH, HEIGHT);
        fantasyTeamPanel = new JPanel(new GridLayout(4, 2));
        fantasyTeamCreationFrame.add(fantasyTeamPanel);
        addPlayerToTeam();
        addPlayerStats();
        viewPlayersOnTeam();
        viewPlayerStats();
        viewStatisticsLeaders();
        saveOption();
//        try {
//            loadOption();
//        } catch (InterruptedException e) {
//            System.out.println("Load time error");
//        }
        returnHome();
    }

    // EFFECTS: creates button to add player to your fantasy team. If button image icon cannot be found/read, throws
    //          IOException
    public void addPlayerToTeam() throws IOException {
        JButton addPlayer = new JButton("Add a player to your fantasy team");
        ImageIcon imageIcon = getImageIcon("./data/images/plusSymbol.png");
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

    // SOURCE: https://docs.oracle.com/javase/8/docs/api/index.html?java/awt/image/BufferedImage.html
    // EFFECTS: creates an image icon from filePath
    private ImageIcon getImageIcon(String filePath) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(filePath));
        Image image = bufferedImage.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(image);
        return imageIcon;
    }


    // MODIFIES: this.fantasyNbaTeam
    // EFFECTS: adds a player to your fantasy NBA team
    public void addPlayerToFantasyTeam() {
        String playerName = JOptionPane.showInputDialog("Enter Player Name");
        String playerJerseyNumber = JOptionPane.showInputDialog("Enter Player's Jersey Number");
        String playersTeam = JOptionPane.showInputDialog("Enter Player's NBA team");
        Player player = new Player(playerName, Integer.parseInt(playerJerseyNumber), playersTeam);
        fantasyNbaTeam.addPlayerToFantasyTeam(player);
    }

    // MODIFIES: this.fantasyTeamPanel
    // EFFECTS: creates a button to add statistics for a player. If the button icon file cannot be found, throws a
    //          IOException
    public void addPlayerStats() throws IOException {
        JButton addStats = new JButton("Add Statistics for your Player");
        fantasyTeamPanel.add(addStats);
        ImageIcon imageIcon = getImageIcon("./data/images/statisticsIcon.png");
        addStats.setIcon(imageIcon);
        addStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlayerStatsButtonMethod();

            }
        });
    }

    // MODIFIES: JButton addStats
    // EFFECTS: creates teh ActionListener even for the addStats button. Stores player stats (points rebounds and
    //          assists) in a map, with the player name and the date of the stat line as the key.
    public void addPlayerStatsButtonMethod() {
        String playerNameInput = JOptionPane.showInputDialog("Enter Player Name You Want to Add Stats For");
        String dateOfStatInput = JOptionPane.showInputDialog("Enter Date:  "
                + "MM/DD/YYYY (ex. 12/01/2023)");
        Player playa = fantasyNbaTeam.findPlayerOnTeam(playerNameInput);
        int playerPoints = Integer.parseInt(JOptionPane.showInputDialog("Enter " + playerNameInput + "'s" + " points"));
        int playerRebounds = Integer.parseInt(JOptionPane.showInputDialog("Enter " + playerNameInput
                + "'s" + " rebounds"));
        int playerAssists = Integer.parseInt(JOptionPane.showInputDialog("Enter " + playerNameInput
                + "'s" + " assists"));
        playa.addPoints(playerPoints);
        playa.addRebounds(playerRebounds);
        playa.addAssists(playerAssists);
        String keyForStat = playerNameInput + " " + dateOfStatInput;
        String statsString = (playa.getName() + " Points: " + playa.getPoints() + " Rebounds: "
                + playa.getRebounds() + " Assists: " + playa.getAssists());

        playerStats.put(keyForStat, statsString);
        try {
            playerStatsAddedDisplay();
        } catch (IOException e) {
            System.out.println("Failed to load display");
        }
    }

    // MODIFIES: this
    // EFFECTS: instantiates the statsAddedFrame for the GUI. Displays a message indicating that the player's stats
    //          have successfully been added. Creates a JButton that, when pressed, disposes of the statsAddedFrame
    //          and returns the user to the fantasy frame. If the basketball player image file cannot be located or
    //          loaded, throws IOException

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
                    statsAddedFrame.dispose();
                } catch (IOException ex) {
                    System.out.println("Failed to load fantasy screen");
                }
            }
        });
    }

    // MODIFIES: this.fantasyTeamPanel
    // EFFECTS: creates button to view the players on the current fantasy nba team. If files fail to load, throws
    //          IOException
    public void viewPlayersOnTeam() throws IOException {
        JButton viewPlayers = new JButton("View players on your team");
        fantasyTeamPanel.add(viewPlayers);
        addIconForViewPlayer(viewPlayers);
        viewPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    viewPlayersDisplay();
                } catch (IOException ex) {
                    System.out.println("Failed to load display");
                }
            }
        });
    }

    // SOURCE: https://docs.oracle.com/javase/8/docs/api/index.html?java/awt/image/BufferedImage.html
    // MODIFIES: button
    // EFFECTS: adds an image icon to the button
    public JButton addIconForViewPlayer(JButton button) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File("./data/images/viewPlayerOnTeamIcon.png"));
        Image image = bufferedImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(image);
        button.setIcon(imageIcon);
        return button;
    }

    // MODIFIES: this
    // EFFECTS: creates the method for the viewPlayer JButton. Displays players currently on the fantasy team.
    //          Instantiates playerDisplay and displays the current players on the roster. Throws IOException if icon
    //          cannot be found/loaded.
    public void viewPlayersDisplay() throws IOException {
        playersDisplay = new JFrame();
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

    // EFFECTS: creates the method for the returnToFantasyScreen button in this.playerDisplay. Returns user to the
    //          fantasyTeam display and disposes the playersDisplay.
    public JButton returnToFantasyScreen() {
        JButton returnToFantasyScreenButton = new JButton("Return to Fantasy Screen");
        returnToFantasyScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fantasyTeamFrame();
                    playersDisplay.dispose();
                } catch (IOException ex) {
                    System.out.println("Failed to return home");
                }
            }
        });
        return returnToFantasyScreenButton;
    }

    // SOURCE: https://docs.oracle.com/javase/8/docs/api/index.html?java/awt/image/BufferedImage.html
    // MODIFIES: this.playerDisplay
    // EFFECTS: adds labels to this.playerDisplay
    public JLabel createPlayerLabel(String name) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File("./data/images/basketball.png"));
        Image image = bufferedImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel label = new JLabel(name, imageIcon, JLabel.CENTER);
        return label;

    }

    // SOURCE: https://docs.oracle.com/javase/8/docs/api/index.html?java/awt/image/BufferedImage.html
    // EFFECTS: Creates JButton that allows user to view player statistics. If the icon for the button cannot load,
    //          throws IOException.
    public void viewPlayerStats() throws IOException {
        JButton viewPlayerStats = new JButton("View a player's stats");
        fantasyTeamPanel.add(viewPlayerStats);
        BufferedImage bufferedImage = ImageIO.read(new File("./data/images/viewPlayerStatsIcon.png"));
        Image image = bufferedImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(image);
        viewPlayerStats.setIcon(imageIcon);
        viewPlayerStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyNameInput = JOptionPane.showInputDialog("Whose total statistics would you like to see?"
                        + " Enter Player Name, a space, and then the date (MM/DD/YYYY) "
                        + " for which you want to see the total stats"
                        + " Example: Lebron James 12/23/2023");
                JOptionPane.showMessageDialog(fantasyTeamPanel, playerStats.get(keyNameInput),
                        "Player's statistics", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    // SOURCE: https://docs.oracle.com/javase/8/docs/api/index.html?java/awt/image/BufferedImage.html
    // EFFECTS: Creates button to view statistical leaders for points, rebounds or assists on the fantasy team.
    //          Throws IOException if file for button icon cannot be found.
    public void viewStatisticsLeaders() throws IOException {
        JButton viewStatsLeaders = new JButton("View Statistical Leaders on Your Team");
        fantasyTeamPanel.add(viewStatsLeaders);
        BufferedImage bufferedImage = ImageIO.read(new File("./data/images/leaderIcon.png"));
        Image image = bufferedImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(image);
        viewStatsLeaders.setIcon(imageIcon);
        viewStatsLeaders.setPreferredSize(new Dimension(50, 50));
        viewStatsLeaders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String statisticOfInterest = JOptionPane.showInputDialog("Which leader do you want to see: "
                        + "Points? Rebounds? Assists?");
                displayStatisticsLeader(statisticOfInterest);
            }
        });
    }

    // EFFECTS: displays statistical leader for either points, rebounds, or assists based on user input
    public void displayStatisticsLeader(String statisticOfInterest) {
        Player statLeader;
        if (statisticOfInterest.equals("Points") || statisticOfInterest.equals("points")) {
            statLeader = fantasyNbaTeam.getPointsLeader();
            JOptionPane.showMessageDialog(fantasyTeamPanel, "The points leader is: "
                            + statLeader.getName() + " with " + statLeader.getPoints() + " points.", "Points Leader",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (statisticOfInterest.equals("Rebounds") || statisticOfInterest.equals("rebounds")) {
            statLeader = fantasyNbaTeam.getReboundsLeader();
            JOptionPane.showMessageDialog(fantasyTeamPanel, "The rebounds leader is: "
                    + statLeader.getName() + " with " + statLeader.getRebounds()
                    + " rebounds.", "Rebounds Leader", JOptionPane.INFORMATION_MESSAGE);
        }
        if (statisticOfInterest.equals("Assists") || statisticOfInterest.equals("assists")) {
            statLeader = fantasyNbaTeam.getAssistsLeader();
            JOptionPane.showMessageDialog(fantasyTeamPanel, "The assists leader is: "
                    + statLeader.getName() + " with " + statLeader.getAssists()
                    + " assists.", "Assists Leader", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // SOURCE: https://docs.oracle.com/javase/8/docs/api/index.html?java/awt/image/BufferedImage.html
    //SOURCE: JsonSerializationDemo CPSC 210
    // EFFECTS: saves the fantasy NBA team to file
    public void saveOption() throws IOException {
        JButton save = new JButton("Save your Fantasy Team");
        fantasyTeamPanel.add(save);
        BufferedImage bufferedImage = ImageIO.read(new File("./data/images/saveIcon.png"));
        Image image = bufferedImage.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(image);
        save.setIcon(imageIcon);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(fantasyNbaTeam);
                    jsonWriter.close();
                    JOptionPane.showMessageDialog(fantasyTeamPanel, "Team has been successfully saved!",
                            "Save Message", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Successfully saved " + fantasyNbaTeam.getFantasyTeamName()
                            + " to " + JSON_STORE);
                } catch (FileNotFoundException fileNotFoundException) {
                    System.out.println("Unable to save to file to: " + JSON_STORE);
                }
            }
        });
    }

    // SOURCE: https://stackoverflow.com/questions/26474909/gif-not-playing-in-jframe
    // SOURCE: JsonSerializationDemo CPSC 210
    // MODIFIES: this
    // EFFECTS: loads fantasy NBA team from file
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public JButton loadOption() throws IOException, InterruptedException {
        JButton load = new JButton("Load your team");
        URL url = new
                URL("https://upload.wikimedia.org/wikipedia/commons/b/b9/Youtube_loading_symbol_1_(wobbly).gif");
        Image image = Toolkit.getDefaultToolkit().createImage(url).getScaledInstance(35, 35,
                Image.SCALE_DEFAULT);
        MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        mediaTracker.waitForAll();
        ImageIcon imageIcon = new ImageIcon(image);
        load.setIcon(imageIcon);
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fantasyNbaTeam == null) {
                        createReturnToFantasyScreen();
                    }
                    fantasyNbaTeam = jsonReader.read();
                    JOptionPane.showMessageDialog(fantasyTeamPanel, "Team has been successfully loaded!",
                            "Load Message", JOptionPane.INFORMATION_MESSAGE);
                    homeFrame.setVisible(true);
                } catch (IOException ioException) {
                    System.out.println("Failed to read from file: " + JSON_STORE);
                }
            }
        });
        return load;
    }

    // MODIFIES this:
    // EFFECTS: creates JButton to return to home screen. Throws IOException if file for Button label cannot be found or
    //          loaded.
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
                    fantasyTeamCreationFrame.dispose();
                    setUp();
                } catch (IOException ex) {
                    System.out.println("Unable to load image");
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
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
