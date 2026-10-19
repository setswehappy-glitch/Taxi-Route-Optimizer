import java.util.ArrayList;
import java.util.List;
//class to handle all the possible routes data
public class Route {
    private List<Location> locations;
    private double totalDistance;

    public Route(List<Location> locations, double totalDistance) {
        this.locations = new ArrayList<>(locations);
        this.totalDistance = totalDistance;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public double getTotalDistance() {
        return totalDistance;
    }
    
    
}
