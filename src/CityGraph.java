import java.util.ArrayList;
import java.util.List;
//class to handle the city data or graph data
public class CityGraph {
    //attributes to store all the locations and roads lists
    private List<Location> locations;
    private List<Road> roads;
    //constructor to initialize the lists
    public CityGraph() {
        locations = new ArrayList<>();
        roads = new ArrayList<>();
    }
    //method to add locations to the city graph
    public void addLocation(Location location){
        locations.add(location);
    }
    //method to add the roads to the city graph
    public void addRoad(Road road){
        roads.add(road);
    }
    //method to return all locations
    public List<Location> getLocations() {
        return locations;
    }
    //method to return all roads
    public List<Road> getRoads() {
        return roads;
    }
    
}
