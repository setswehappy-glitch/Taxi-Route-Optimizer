import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
//class that handles route optimization, to determine the shortest route
public class RouteOptimizer {
    private CityGraph graph;

    // A constructor used to receive the city network
    public RouteOptimizer(CityGraph graph) {
        this.graph = graph;
    }

    // A method to find the shortest route between the starting point and the destination
    public Route findShortestRoute(Location start, Location destination) {
        if (start == null || destination == null) {
            return null;
        }
        if (!graph.getLocations().contains(start) || !graph.getLocations().contains(destination)) {
            return null;
        }

        // Stores the shortest known distance to each location
        Map<Location, Double> distances = new HashMap<>();
        // Stores the previous location on the best known route
        Map<Location, Location> previous = new HashMap<>();
        // Processes the location with the smallest distance first
        PriorityQueue<Location> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        // Initially, every location is infinitely far away
        for (Location location : graph.getLocations()) {
            distances.put(location, Double.POSITIVE_INFINITY);
        }

        // The starting location has a distance of zero
        distances.put(start, 0.0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Location current = queue.poll();

            // Stop once the destination is reached
            if (current.equals(destination)) {
                break;
            }

            // Examine every road connected to the current location
            for (Road road : graph.getRoads()) {
                Location neighbour = null;

                // Check whether the road starts at the current location
                if (road.getStartPoint().equals(current)) {
                    neighbour = road.getEndPoint();
                }
                // Treat the roads as bidirectional roads
                else if (road.getEndPoint().equals(current)) {
                    neighbour = road.getStartPoint();
                }

                // Ignore roads that do not connect to this location
                if (neighbour == null) {
                    continue;
                }

                double newDistance = distances.get(current) + road.getDistance();

                // Update the route if we have found a shorter distance
                if (newDistance < distances.get(neighbour)) {
                    distances.put(neighbour, newDistance);
                    previous.put(neighbour, current);
                    // Reinsert the location so the queue reflects its updated distance
                    queue.remove(neighbour);
                    queue.add(neighbour);
                }
            }
        }

        // If the destination is unreachable, no route exists
        if (Double.isInfinite(distances.get(destination))) {
            return null;
        }

        // Reconstruct the route by following previous locations
        List<Location> routeLocations = new ArrayList<>();
        Location routeLocation = destination;
        while (routeLocation != null) {
            routeLocations.add(routeLocation);
            routeLocation = previous.get(routeLocation);
        }

        // Since the route was reconstructed backwards, we reverse it
        Collections.reverse(routeLocations);

        // Return the route and its calculated total distance
        return new Route(routeLocations, distances.get(destination));
    }

    // Returns the distance of a calculated route
    public double calculateDistance(Route route) {
        if (route == null) {
            return Double.POSITIVE_INFINITY;
        }
        return route.getTotalDistance();
    }
}