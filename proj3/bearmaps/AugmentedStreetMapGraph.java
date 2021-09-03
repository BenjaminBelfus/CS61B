package bearmaps;

import bearmaps.utils.Constants;
import bearmaps.utils.graph.streetmap.Node;
import bearmaps.utils.graph.streetmap.StreetMapGraph;
import bearmaps.utils.ps.KDTree;
import bearmaps.utils.ps.Point;
import edu.princeton.cs.algs4.TrieSET;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    // we are doing point -> node and not node -> point because for closest we use nearest function which returns a point
    // by having the point we can access the node and then its id.
    HashMap<Point, Node> recommended_map = new HashMap<>();
    List<Point> allPoints = new ArrayList<>();
    KDTree myTree;





    //EXTRA CREDITS

    // can get all the names that start with the prefix, one I have the names, I can use my allLocation hashmap to get the
    //node and then its locations.
    TrieSET allWords =  new TrieSET();

    // based on the name, I would need to access its location. The only way to do this is with somehow having the node.
    // but what if a name has multiple locatiins? our value node, should be a list of nodes. Clened String -> list of nodes.
    HashMap<String, HashSet<Node>> allLocotions = new HashMap<>();

    // Hashset that goes into the value of allLocations. Why hashset? because it does not allow any duplicate, so we will
    // never have the same node for a specific name.
    HashSet<Node> allNodesFromOneName = new HashSet<>();



    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        List<Node> nodes = this.getNodes();

        for (Node n : nodes) {
            // creating the points based on the lan and lon of the node
            Point p = new Point(n.lon(), n.lat());
            recommended_map.put(p,n);

            // this is to avoid cases like the bongo burger in the spec where it has no neighbours since it is not a place along the road
            if (neighbors(n.id()).size() > 0) {
                allPoints.add(p);
            }


            //can the n.name be null? I have no idea but I am getting a nullpointerException
            if(n.name() != null) {
                String fullName = n.name();
                String nameCleaned = cleanString(fullName);
                allWords.add(nameCleaned);

                if (!allLocotions.isEmpty()) {
                    if (allLocotions.containsKey(nameCleaned)) {
                        HashSet<Node> allCurrentNodes = allLocotions.get(nameCleaned);
                        allCurrentNodes.add(n);
                        allLocotions.replace(nameCleaned, allCurrentNodes);
                    } else {
                        HashSet<Node> addingNodeForFirstTime = new HashSet<>();
                        addingNodeForFirstTime.add(n);
                        allLocotions.put(nameCleaned, addingNodeForFirstTime);
                    }
                } else {
                    HashSet<Node> addingNodeForFirstTime = new HashSet<>();
                    addingNodeForFirstTime.add(n);
                    allLocotions.put(nameCleaned, addingNodeForFirstTime);
                }
            }
        }
        myTree = new KDTree(allPoints);
    }


    /**
     * For Project Part III
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        double x = projectToX(lon, lat);
        double y = projectToY(lon, lat);

        Point p = myTree.nearest(lon, lat);
        Node toReutrn = recommended_map.get(p);

        return toReutrn.id();
    }

    /**
     * Return the Euclidean x-value for some point, p, in Berkeley. Found by computing the
     * Transverse Mercator projection centered at Berkeley.
     * @param lon The longitude for p.
     * @param lat The latitude for p.
     * @return The flattened, Euclidean x-value for p.
     * @source https://en.wikipedia.org/wiki/Transverse_Mercator_projection
     */
    static double projectToX(double lon, double lat) {
        double dlon = Math.toRadians(lon - ROOT_LON);
        double phi = Math.toRadians(lat);
        double b = Math.sin(dlon) * Math.cos(phi);
        return (K0 / 2) * Math.log((1 + b) / (1 - b));
    }

    /**
     * Return the Euclidean y-value for some point, p, in Berkeley. Found by computing the
     * Transverse Mercator projection centered at Berkeley.
     * @param lon The longitude for p.
     * @param lat The latitude for p.
     * @return The flattened, Euclidean y-value for p.
     * @source https://en.wikipedia.org/wiki/Transverse_Mercator_projection
     */
    static double projectToY(double lon, double lat) {
        double dlon = Math.toRadians(lon - ROOT_LON);
        double phi = Math.toRadians(lat);
        double con = Math.atan(Math.tan(phi) / Math.cos(dlon));
        return K0 * (con - Math.toRadians(ROOT_LAT));
    }


    /**
     * For Project Part IV (extra credit)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        LinkedList<String> toReturn = new LinkedList<>();

        String prefixCleaned = cleanString(prefix);
        LinkedList<String> allLocationsPrefix = (LinkedList<String>) allWords.keysWithPrefix(prefixCleaned);

        for (String s : allLocationsPrefix) {
            for (Node n : allLocotions.get(s)) {
                toReturn.add(n.name());
            }
        }
        return toReturn;
    }

    /**
     * For Project Part IV (extra credit)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        String locationNameCleaned = cleanString(locationName);
        List<Map<String, Object>> toReturn = new ArrayList<>();

        if (!allLocotions.get(locationNameCleaned).isEmpty()) {
            for (Node n : allLocotions.get(locationNameCleaned)) {
                // a map that goes inside my toReturn
                Map<String, Object> m = new HashMap<>();
                m.put("lat", n.lat());
                m.put("lon", n.lon());
                m.put("name", n.name());
                m.put("id", n.id());

                toReturn.add(m);
            }
        }
        return toReturn;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }


    /**
     * Scale factor at the natural origin, Berkeley. Prefer to use 1 instead of 0.9996 as in UTM.
     * @source https://gis.stackexchange.com/a/7298
     */
    private static final double K0 = 1.0;
    /** Latitude centered on Berkeley. */
    private static final double ROOT_LAT = (Constants.ROOT_ULLAT + Constants.ROOT_LRLAT) / 2;
    /** Longitude centered on Berkeley. */
    private static final double ROOT_LON = (Constants.ROOT_ULLON + Constants.ROOT_LRLON) / 2;

}