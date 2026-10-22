# Railway Route Search

A console railway route planner in Java for six cities in Maharashtra, India: Pune, Mumbai, Nashik, Jalgaon, Dhule, and Bhusaval. It uses two graph algorithms to plan routes:

- **Passenger train:** a minimum spanning tree built with **Kruskal's algorithm**. It links every city using the least total track length.
- **Express train:** the shortest path from a chosen city to Bhusaval Junction, found with **Dijkstra's algorithm**.

## Features

**Admin** (demo login `admin` / `admin`, hard coded for this console app)
- View all routes sorted by distance
- Add or remove a route
- Add or remove a city, which also removes its routes
- Search routes

**User**
- Passenger train route (Kruskal)
- Express train route to Bhusaval (Dijkstra)

## Run it

```bash
javac RailwaySearchRoute.java DijkstraRoute.java
java RailwaySearchRoute
```

## Example

User menu, passenger train. Routes are first sorted by distance:

```
Jalgaon   ->  Bhusaval   31
Dhule     ->  Jalgaon    91
Pune      ->  Mumbai     148
Nashik    ->  Dhule      158
Mumbai    ->  Nashik     166
Pune      ->  Nashik     211
...

Selected cities for Passenger Train are:
[ Pune -> Mumbai -> Nashik -> Dhule -> Jalgaon -> Bhusaval ]
Distance by Passenger Train:: 594
```

Kruskal's algorithm takes the 5 shortest routes that don't form a loop: 31 + 91 + 148 + 158 + 166 = 594 km.

Express train from Pune or Mumbai:

```
 -> Pune -> Bhusaval
 -> Mumbai -> Bhusaval
```

From both cities the direct line is the shortest. From Pune, it's 395 km direct versus 455 km via Dhule and Jalgaon.

## Files

```
RailwaySearchRoute.java   menus, route list, sorting, Kruskal's algorithm
DijkstraRoute.java        distance matrix and Dijkstra's algorithm for express routes
```
