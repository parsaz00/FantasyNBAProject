package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FantasyNbaTeamTest {
    private FantasyNbaTeam fantasyNbaTeam1;
    private FantasyNbaTeam fantasyNbaTeam2;
    private Player testPlayer1;
    private Player testPlayer2;

    @BeforeEach
    void setUp() {
        fantasyNbaTeam1 = new FantasyNbaTeam("The Rollers");
        fantasyNbaTeam2 = new FantasyNbaTeam("The Bucket Getters");
        testPlayer1 = new Player("Lebron James", 6, "Lakers");
        testPlayer2 = new Player("Luka Doncic", 77, "Mavericks");
    }
    @Test
    void constructorTest() {
        assertEquals("The Rollers", fantasyNbaTeam1.getFantasyTeamName());
        assertEquals("The Bucket Getters", fantasyNbaTeam2.getFantasyTeamName());
        assertEquals(0, fantasyNbaTeam1.getNumberOfPlayers());
        assertEquals(0, fantasyNbaTeam2.getNumberOfPlayers());
    }
    @Test
    void addPlayersToFantasyTeamTest() {
        assertEquals(0, fantasyNbaTeam1.getNumberOfPlayers());
        assertTrue(fantasyNbaTeam1.addPlayerToFantasyTeam(testPlayer1));
        fantasyNbaTeam2.addPlayerToFantasyTeam(testPlayer2);
        assertEquals(1, fantasyNbaTeam2.getNumberOfPlayers());

    }

}
