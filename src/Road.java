//class that handles the diffrent roads data
public class Road {
   //attributes to get the roads data
   private Location startPoint;
   private Location endPoint;
   private double distance;
   //constructors to initialize the road objects
    public Road(Location startPoint, Location endPoint, double distance) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
    }
    //getter method to get the starting point data
    public Location getStartPoint() {
        return startPoint;
    }
    //getter method to get the end point data
    public Location getEndPoint() {
        return endPoint;
    }
    //getter method to get the estimated distance to be travelled
    public double getDistance() {
        return distance;
    }
   
   
}
