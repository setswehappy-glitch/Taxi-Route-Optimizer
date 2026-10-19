//class that handles the locations information/data
public class Location {
    //attributes to store the location's information
    private String name;
    private double xCoordinate;
    private double yCoordinate;
   //constructors to create new location objects
    public Location(String name, double xCoordinate, double yCoordinate) {
        this.name = name;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
    //method to get and return the name of the location
    public String getName() {
        return name;
    }
    //method to get and return our xCoordinate
    public double getxCoordinate() {
        return xCoordinate;
    }
    //method to get and return our yCoordinate
    public double getyCoordinate() {
        return yCoordinate;
    }
    
}
