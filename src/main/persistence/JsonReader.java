package persistence;

import model.FantasyNbaTeam;
import model.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads a FantasyNbaTeam from JSON data stored in file
// SOURCE: JsonSerializationDemo
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file

    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads a FantasyNbaTeam from file and returns it. Will throw an IOException if an error occurs
    //          when reading data from the file!
    public FantasyNbaTeam read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFantasyNbaTeam(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses FantasyNbaTeam from JSON object and returns it
    private FantasyNbaTeam parseFantasyNbaTeam(JSONObject jsonObject) {
        String name = jsonObject.getString("team name");
        FantasyNbaTeam fantasyNbaTeam = new FantasyNbaTeam(name);
        addPlayersToFantasyTeam(fantasyNbaTeam, jsonObject);
        return fantasyNbaTeam;
    }

    // MODIFIES: fantasyNbaTeam
    // EFFECTS: parses players from JSON object and adds them to FantasyNbaTeam
    private void addPlayersToFantasyTeam(FantasyNbaTeam fnt, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("players on team");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(fnt, nextPlayer);
        }
    }

    // MODIFIES: fantasyNbaTeam
    // EFFECTS: parses player from JSON object , adds player's point, rebounds, and assits, and
    //          then and adds it to the Fantasy NBA team
    private void addPlayer(FantasyNbaTeam fnt, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int jerseyNumber = jsonObject.getInt("jersey number");
        String nbaTeamName = jsonObject.getString("NBA team");
        Player player = new Player(name, jerseyNumber, nbaTeamName);
        int points = jsonObject.getInt("points");
        int rebounds = jsonObject.getInt("rebounds");
        int assists = jsonObject.getInt("assists");
        player.addPoints(points);
        player.addRebounds(rebounds);
        player.addAssists(assists);
        fnt.addPlayerToFantasyTeam(player);
    }
}
