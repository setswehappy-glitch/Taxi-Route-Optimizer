
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Reads and writes the location and road data files.
 * Locations use the format name,x,y; roads use startName,endName,distance.
 */
public class FileManager {

    private static final String LOCATION_FILE = "data/locations.txt";
    private static final String ROAD_FILE = "data/roads.txt";

    /** Loads locations from locations.txt into the graph. */
    public void loadLocations(CityGraph graph) {
        File file = new File(LOCATION_FILE);
        if (!file.exists()) {
            System.out.println("locations.txt not found - starting with no locations.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    continue;
                }
                try {
                    String name = parts[0].trim();
                    double x = Double.parseDouble(parts[1].trim());
                    double y = Double.parseDouble(parts[2].trim());
                    graph.addLocation(new Location(name, x, y));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping bad location line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read locations: " + e.getMessage());
        }
    }

    /** Loads roads from roads.txt. Locations must be loaded first. */
    public void loadRoads(CityGraph graph) {
        File file = new File(ROAD_FILE);
        if (!file.exists()) {
            System.out.println("roads.txt not found - starting with no roads.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    continue;
                }
                Location start = graph.findLocationByName(parts[0]);
                Location end = graph.findLocationByName(parts[1]);
                if (start == null || end == null) {
                    System.out.println("Skipping road with unknown location: " + line);
                    continue;
                }
                try {
                    double distance = Double.parseDouble(parts[2].trim());
                    graph.addRoad(new Road(start, end, distance));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping bad road line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read roads: " + e.getMessage());
        }
    }

    /** Saves the graph's locations and roads to their data files. */
    public void saveGraph(CityGraph graph) {
        new File("data").mkdirs();

        try (PrintWriter pw = new PrintWriter(new FileWriter(LOCATION_FILE))) {
            for (Location l : graph.getLocations()) {
                pw.println(l.getName() + "," + l.getxCoordinate() + "," + l.getyCoordinate());
            }
        } catch (IOException e) {
            System.out.println("Could not save locations: " + e.getMessage());
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(ROAD_FILE))) {
            for (Road r : graph.getRoads()) {
                pw.println(r.getStartPoint().getName() + "," + r.getEndPoint().getName() + "," + r.getDistance());
            }
        } catch (IOException e) {
            System.out.println("Could not save roads: " + e.getMessage());
        }
    }
}
