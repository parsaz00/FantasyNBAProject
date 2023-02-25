package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FantasyNbaTeamTest {
    private FantasyNbaTeam fantasyNbaTeam1;
    private FantasyNbaTeam fantasyNbaTeam2;
    private FantasyNbaTeam fantasyNbaTeam3;
    private Player testPlayer1;
    private Player testPlayer2;
    private Player testPlayer3;

    @BeforeEach
    void setUp() {
        fantasyNbaTeam1 = new FantasyNbaTeam("The Rollers");
        fantasyNbaTeam2 = new FantasyNbaTeam("The Bucket Getters");
        fantasyNbaTeam3 = new FantasyNbaTeam("The High tops");
        testPlayer1 = new Player("Lebron James", 6, "Lakers");
        testPlayer2 = new Player("Luka Doncic", 77, "Mavericks");
        testPlayer3 = new Player("Kevin Durant", 7, "Suns");
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
        for (int i = 0; i < 13; i++) {
            fantasyNbaTeam1.addPlayerToFantasyTeam(testPlayer2);
        }
        assertFalse(fantasyNbaTeam1.addPlayerToFantasyTeam(testPlayer2));
        assertEquals(12, fantasyNbaTeam1.getNumberOfPlayers());
        assertEquals(0, fantasyNbaTeam3.getNumberOfPlayers());
        for (int i = 0; i < 11; i++) {
            fantasyNbaTeam3.addPlayerToFantasyTeam(testPlayer2);
        }
        assertTrue(fantasyNbaTeam3.addPlayerToFantasyTeam(testPlayer1));
        assertFalse(fantasyNbaTeam3.addPlayerToFantasyTeam(testPlayer2));

    }
    @Test
    void findPlayerOnTeamTest() {
        fantasyNbaTeam1.addPlayerToFantasyTeam(testPlayer1);
        fantasyNbaTeam1.addPlayerToFantasyTeam(testPlayer2);
        assertEquals(testPlayer1, fantasyNbaTeam1.findPlayerOnTeam("Lebron James"));
        assertEquals(testPlayer2, fantasyNbaTeam1.findPlayerOnTeam("Luka Doncic"));
        assertNull(fantasyNbaTeam2.findPlayerOnTeam("Michael Jordan"));
        assertNull(fantasyNbaTeam1.findPlayerOnTeam("Anthony Davis"));
    }

    @Test
    void getPlayersOnTeamTest() {
        fantasyNbaTeam1.addPlayerToFantasyTeam(testPlayer1);
        fantasyNbaTeam1.addPlayerToFantasyTeam(testPlayer2);
        fantasyNbaTeam1.addPlayerToFantasyTeam(testPlayer3);
        List<String> testList = fantasyNbaTeam1.getPlayersOnTeam();
        assertEquals(3, testList.size());
        assertTrue(testList.contains("Kevin Durant"));
        assertTrue(testList.contains("Lebron James"));
        assertTrue(testList.contains("Luka Doncic"));
        assertFalse(testList.contains("Michael Jordan"));
        assertEquals("Lebron James", testList.get(0));
        assertEquals("Luka Doncic", testList.get(1));
        assertEquals("Kevin Durant", testList.get(2));
    }

    @Test
    void setTeamNameTest() {
        fantasyNbaTeam1.setFantasyTeamName("Lakers");
        assertEquals("Lakers", fantasyNbaTeam1.getFantasyTeamName());
        fantasyNbaTeam2.setFantasyTeamName("Test");
        assertEquals("Test", fantasyNbaTeam2.getFantasyTeamName());
        fantasyNbaTeam2.setFantasyTeamName("Test2");
        assertEquals("Test2", fantasyNbaTeam2.getFantasyTeamName());
    }

}
