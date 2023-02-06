package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    private Player testPlayer1;
    private Player getTestPlayer2;
    @BeforeEach
    void setUp() {
        testPlayer1 = new Player("Lebron James", 6, "Lakers");
        getTestPlayer2 = new Player("Luka Doncic", 77, "Mavericks");
    }
    @Test
    void constructorTest() {
        assertEquals("Lebron James", testPlayer1.getName());
        assertEquals("Luka Doncic", getTestPlayer2.getName());
        assertEquals(6, testPlayer1.getJerseyNumber());
        assertEquals(77, getTestPlayer2.getJerseyNumber());
        assertEquals("Lakers", testPlayer1.getTeam());
        assertEquals("Mavericks", getTestPlayer2.getTeam());
    }
}