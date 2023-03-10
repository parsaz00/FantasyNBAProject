package persistence;

import model.FantasyNbaTeam;
import model.Player;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// SOURCE: JsonSerializationDemo CPSC 210
public class JsonWriterTest {
    @Test
    void testInvalidJsonFileForWriter() {
        try {
            FantasyNbaTeam ft = new FantasyNbaTeam("Writer Test Team");
            JsonWriter writer = new JsonWriter("./data/my\0fileNameThatIsIllegal.json");
            writer.open();
            fail("IOException should have been thrown");
        } catch (FileNotFoundException e) {
            System.out.println("Success, the exception was thrown correctly!");
        }
    }

    @Test
    void testWriterOnEmptyFantasyNBATeam() {
        try {
            FantasyNbaTeam ft = new FantasyNbaTeam("TestTeam1");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFantasyNBATeam.json");
            writer.open();
            writer.write(ft);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFantasyNBATeam.json");
            ft = reader.read();
            assertEquals("TestTeam1", ft.getFantasyTeamName());
            assertEquals(0, ft.getNumberOfPlayers());
            List<String> playersOnTeam = ft.getPlayersOnTeam();
            assertEquals(0, playersOnTeam.size());
            assertFalse(playersOnTeam.contains("Lebron James"));
        } catch (IOException e) {
            fail("Exception should not have been thrown here!");
        }
    }

    @Test
    void testWriterOnFantasyNBATeamThatHasData() {
        try {
            FantasyNbaTeam ft = new FantasyNbaTeam("TestTeam2");
            ft.addPlayerToFantasyTeam(new Player("Lebron James", 6, "Lakers"));
            ft.addPlayerToFantasyTeam(new Player("Anthony Davis", 3, "Lakers"));
            ft.addPlayerToFantasyTeam(new Player("Steph Curry", 30, "Warriors"));
            JsonWriter jsonWriter = new JsonWriter("./data/testWriterFantasyNBATeam.json");
            jsonWriter.open();
            jsonWriter.write(ft);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/testWriterFantasyNBATeam.json");
            ft = jsonReader.read();
            assertEquals("TestTeam2", ft.getFantasyTeamName());
            assertEquals(3, ft.getNumberOfPlayers());
            List<String> playersOnTeam = ft.getPlayersOnTeam();
            assertEquals(3, playersOnTeam.size());
            assertTrue(playersOnTeam.contains("Lebron James"));
            assertTrue(playersOnTeam.contains("Steph Curry"));
            assertFalse(playersOnTeam.contains("Joel Embid"));

        } catch (FileNotFoundException e) {
            fail("FileNotFoundException should NOT have been thrown");
        } catch (IOException e) {
            System.out.println("IOException should NOT have been thrown!");
        }
    }
}