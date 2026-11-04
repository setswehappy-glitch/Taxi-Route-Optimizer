#Taxi Route Optimizer 

A Java console program for the SDN260S project, developed in NetBeans. It uses Dijkstra’s algorithm to find the shortest route between two locations.

What it does:

Loads locations and roads from text files.
Lets you select a starting point and destination from numbered menus.
Shows the route, stops and total distance in kilometres.
Lets you search again without restarting.
Records route searches, invalid input and file errors in a log file.

Run the program
1. Install a Java JDK and open NetBeans.
2. Go to File > Open Project and open the TaxiRouteOptimizer folder.
3. Keep the data folder next to src. It must contain locations.txt and roads.txt.
4. Right-click UserInterface.java and select Run File.
5. Enter the menu numbers for your starting point and destination.

Example output
The route and distance depend on the loaded data. Example:

OPTIMIZED ROUTE
Cape Town CBD -> Kasselsvlei -> Belhar
Total Distance: 13.30 km

If you enter invalid menu input, the program asks again. If no route connects the selected locations, it shows No route found.

Data files
Keep both files in data. Use one record per line, with values separated by commas.

locations.txt: name,x,y
Example:
Bellville,10,20
Cape Town CBD,15,25

roads.txt: startName,endName,distanceInKm
Example:
Bellville,Cape Town CBD,15.5

Use matching location names in both files. Add a location before adding roads linked to it. Do not use commas inside names. Save your changes and restart the program.

Logging
The program adds timestamped entries to data/app.log.
INFO records normal events, such as route results.
WARNING records invalid input or data problems.
SEVERE records file read or save failures.
Check this file when investigating a problem.

Classes
Location -> stores a place name and coordinates.
Road -> connects two locations and stores their distance.
Route -> stores the route stops and total distance.
CityGraph -> holds the locations and roads.
RouteOptimizer -> finds the shortest route.
UserInterface - >handles menus and starts the program.
FileManager -> loads locations and roads from text files.
AppLogger -> writes events and errors to the log file.

Limits
The prototype uses sample data. It has no live traffic information or graphical interface.


