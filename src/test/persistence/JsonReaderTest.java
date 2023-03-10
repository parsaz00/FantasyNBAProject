package persistence;

import model.FantasyNbaTeam;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

// SOURCE: JsonSerializationDemo CPSC 210
public class JsonReaderTest {
    @Test
    void testReaderOnFileThatDoesNotExist() {
        JsonReader jsonReader = new JsonReader("./data/randomFile.json");
        try {
            FantasyNbaTeam ft = jsonReader.read();
            fail("IOException should have been thrown");
        } catch (IOException e) {
            System.out.println("Successfully threw exception when file did not exist, so it could not read");
        }
    }
    @Test
    void testReaderOnEmptyFantasyTeamFile() {
        JsonReader jsonReader = new JsonReader("./data/testReaderEmptyFantasyNBATeam.json");
        try {
            FantasyNbaTeam ft = jsonReader.read();
            assertEquals("TestTeam1", ft.getFantasyTeamName());
            assertEquals(0, ft.getNumberOfPlayers());
            List<String> playersOnTeam = ft.getPlayersOnTeam();
            assertEquals(0, playersOnTeam.size());
            assertFalse(playersOnTeam.contains("Lebron James"));
        } catch (IOException e) {
            fail("IOException should NOT have been thrown");
        }
    }

    @Test
    void testReaderOnActualFantasyTeamFile() {
        JsonReader jsonReader = new JsonReader("./data/testReaderFantasyNBATeam.json");
        try {
            FantasyNbaTeam ft = jsonReader.read();
            assertEquals("Test Team 2", ft.getFantasyTeamName());
            assertEquals(4, ft.getNumberOfPlayers());
            List<String> playersOnTeam = ft.getPlayersOnTeam();
            assertEquals(4, playersOnTeam.size());
            assertTrue(playersOnTeam.contains("Lebron James"));
            assertTrue(playersOnTeam.contains("D'Angelo Russel"));
            assertFalse(playersOnTeam.contains("Michael Jordan"));
        } catch (IOException e) {
            fail("IOException should not have been thrown, failing to red from file");
        }
    }
}
