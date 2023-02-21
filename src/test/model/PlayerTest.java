package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    private Player testPlayer1;
    private Player testPlayer2;
    @BeforeEach
    void setUp() {
        testPlayer1 = new Player("Lebron James", 6, "Lakers");
        testPlayer2 = new Player("Luka Doncic", 77, "Mavericks");
    }
    @Test
    void setNameTest() {
        testPlayer1.setName("jim");
        assertEquals("jim", testPlayer1.getName());
        testPlayer1.setName("Bob");
        assertEquals("Bob", testPlayer1.getName());
    }
    @Test
    void setJerseyNumberTest() {
        testPlayer1.setJerseyNumber(8);
        assertEquals(8, testPlayer1.getJerseyNumber());
        testPlayer2.setJerseyNumber(42);
        assertEquals(42, testPlayer2.getJerseyNumber());
    }
    @Test
    void setTeamNameTest() {
        testPlayer1.setTeamName("76ers");
        assertEquals("76ers", testPlayer1.getTeam());
        testPlayer2.setTeamName("Grizzlies");
        assertEquals("Grizzlies", testPlayer2.getTeam());
    }
    @Test
    void constructorTest() {
        assertEquals("Lebron James", testPlayer1.getName());
        assertEquals("Luka Doncic", testPlayer2.getName());
        assertEquals(6, testPlayer1.getJerseyNumber());
        assertEquals(77, testPlayer2.getJerseyNumber());
        assertEquals("Lakers", testPlayer1.getTeam());
        assertEquals("Mavericks", testPlayer2.getTeam());
        assertEquals(0, testPlayer1.getAssists());
        assertEquals(0, testPlayer2.getPoints());
        assertEquals(0, testPlayer1.getRebounds());
    }
    @Test
    void addPointsTest() {
        testPlayer1.addPoints(1);
        assertEquals(1, testPlayer1.getPoints());
        testPlayer2.addPoints(100);
        assertEquals(100, testPlayer2.getPoints());
    }
    @Test
    void addAssistsTest() {
        testPlayer1.addAssists(10);
        assertEquals(10, testPlayer1.getAssists());
        testPlayer2.addAssists(6);
        assertEquals(6, testPlayer2.getAssists());
    }
    @Test
    void addReboundsTest() {
        testPlayer1.addRebounds(53);
        assertEquals(53, testPlayer1.getRebounds());
        testPlayer2.addRebounds(13);
        assertEquals(13, testPlayer2.getRebounds());
    }
}