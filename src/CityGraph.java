
import java.util.ArrayList;
import java.util.List;

public class CityGraph {

    private List<Location> locations;
    private List<Road> roads;

    public CityGraph() {
        locations = new ArrayList<>();
        roads = new ArrayList<>();
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public void addRoad(Road road) {
        roads.add(road);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public Location findLocationByName(String name) {
        for (Location location : locations) {
            if (location.getName().equalsIgnoreCase(name.trim())) {
                return location;
            }
        }
        return null;
    }
}
