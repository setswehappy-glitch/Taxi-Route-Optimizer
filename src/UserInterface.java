import java.util.List;
import java.util.Scanner;

//class to handle users interface/ interaction with the code
public class UserInterface {
    private RouteOptimizer optimizer;
    private CityGraph graph;
    private Scanner scanner;

    // constructor to initalize all the attributes created on top
    public UserInterface(CityGraph graph, RouteOptimizer optimizer) {
        this.graph = graph;
        this.optimizer = optimizer;
        this.scanner = new Scanner(System.in);
    }

    // method to allow the user to select their starting point/location
    public Location selectStart() {
        System.out.println("\nHi! Please select STARTING LOCATION:");
        return selectLocation();
    }

     // method to allow the user to select their destination/ end point
    public Location selectDestination() {
        System.out.println("\nPlease select your DESTINATION:");
        return selectLocation();
    }

     // method that displays all the the available locations or destination
    private Location selectLocation() {
        List<Location> list = graph.getLocations();

        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getName());
        }

        while (true) {
            System.out.print("Please enter a number: ");
            String input = scanner.nextLine();

            if (input.matches("\\d+")) {
                int choice = Integer.parseInt(input);

                if (choice >= 1 && choice <= list.size()) {
                    return list.get(choice - 1);
                }
            }

            System.out.println("Invalid Choice! Please try again.");
        }
    }

    // Displays the route and its total distance
    public void displayRoute(Route route) {
        if (route == null) {
            System.out.println("No route found - check road connection");
            return;
        }

        System.out.println("\n--- OPTIMIZED ROUTE ---");

        for (int i = 0; i < route.getLocations().size(); i++) {
            System.out.print(route.getLocations().get(i).getName());

            if (i < route.getLocations().size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
        System.out.printf("Total Distance: %.2f km\n",
                route.getTotalDistance());
    }

    // Creates the city network and starts the program
    public static void main(String[] args) {
        CityGraph graph = new CityGraph();

        Location a = new Location("Bellville", 10, 20);
        Location b = new Location("Cape Town CBD", 15, 25);
        Location c = new Location("Kassleslvei", 18, 15);
        Location d = new Location("Belhair", 20, 12);

        graph.addLocation(a);
        graph.addLocation(b);
        graph.addLocation(c);
        graph.addLocation(d);

        graph.addRoad(new Road(a, b, 15.5));
        graph.addRoad(new Road(b, c, 10.2));
        graph.addRoad(new Road(c, d, 3.1));
        graph.addRoad(new Road(a, c, 12.0));

        RouteOptimizer optimizer = new RouteOptimizer(graph);
        UserInterface ui = new UserInterface(graph, optimizer);

        System.out.println("=== TAXI ROUTE OPTIMIZER ===");

        Location start = ui.selectStart();
        Location end = ui.selectDestination();

        Route route = optimizer.findShortestRoute(start, end);

        ui.displayRoute(route);
    }
}