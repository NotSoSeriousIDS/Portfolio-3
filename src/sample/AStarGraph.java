package sample;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStarGraph {
    private ArrayList<Vertex> vertices;
    public AStarGraph() {
        vertices=new ArrayList<Vertex>();
    }
    public void addvertex(Vertex v) {
        vertices.add(v);
    }

    public void newconnection(Vertex v1, Vertex v2, Double dist) {
        v1.addOutEdge(v2,dist);
        v2.addOutEdge(v1,dist);
    }
    public boolean A_Star(Vertex start, Vertex destination)
    {   if (start==null || destination==null)
        return false;
        PriorityQueue<Vertex> Openlist = new PriorityQueue<Vertex>();
        ArrayList<Vertex> Closedlist = new ArrayList<Vertex>();
        Openlist.offer(start);
        Vertex Current;
        ArrayList<Vertex> CurrentNeighbors;
        Vertex Neighbor;
        //Initialize h with chosen heuristic
        for (int i =0; i<vertices.size();i++)
        {
            vertices.get(i).seth(Manhattan(vertices.get(i),destination)); // initialize the Manhattan distance, need implementation of the Manhattan return function below
        }
        start.setg(0.0);
        start.calculatef(); // should be equal to the H value of my start
        //Start algorithm
        System.out.println("Start Algorithm");
        //Implement the Astar algorithm
        // Use Pseudo code
        /*
        openlist.add(startvertex)
        closedlist = empty
        Current =null
        */
        return false;
    }
    public Double Manhattan(Vertex from,Vertex goal){
        //Implement this
        return 0.0; // example of Manhattan A*
    }
    public Double Euclidean( Vertex from,Vertex to){
        //Implement this
        return 0.0; // Euclidean
    }
}

class Vertex implements Comparable<Vertex>{
    private String id; // just a name
    private ArrayList<Vertex> Neighbours=new ArrayList<Vertex>(); // neighbouring array below
    private ArrayList<Double> NeighbourDistance =new ArrayList<Double>(); // remember to put in stuff in both EX. A neighbour to a is b and c.
    private Double f; // the complete estimate of how far there is from goal to origin vertex
    private Double g; // something that is true, origin of path to any path
    private Double h; // estimate from the Vertex to the Goal. G+H=F
    private Integer x; // coordinates of our Vertex
    private Integer y; // Coordinates of our Vertex
    private Vertex prev =null; // path of prev-Current Vertex, this is the pathway
    public Vertex(String id, int x_cor,int y_cor){
        this.id=id;
        this.x=x_cor;
        this.y = y_cor;
        f=Double.POSITIVE_INFINITY; // How far the total journey
        g=Double.POSITIVE_INFINITY;
        h=0.0; // has to be changed depending on which estimation we'd use, incase of the usage of ecldium or manhatten A*
    }
    public void addOutEdge(Vertex toV, Double dist){
        Neighbours.add(toV);
        NeighbourDistance.add(dist);
    }
    public ArrayList<Vertex> getNeighbours(){
        return Neighbours;
    }
    public ArrayList<Double> getNeighbourDistance(){
        return NeighbourDistance;
    }
    public String getid()
    {
        return id;
    }
    public Integer getx()
    {
        return x;
    }
    public Integer gety()
    {
        return x;
    }
    public Double getf()
    {
        return f;
    }
    public void calculatef()
    {
        f=g+h;
    }
    public Double getg()
    {
        return g;
    }

    public void setg(Double newg)
    {
        g=newg;
    }
    public Double geth()
    {
        return h;
    }
    public void seth(Double estimate)
    {
        h=estimate;
    }
    public Vertex getPrev()
    {
        return prev;
    }
    public void setPrev(Vertex v)
    {
        prev=v;
    }
    public void printVertex()
    {
        System.out.println(id + " g: "+g+ ", h: "+h+", f: "+f);
    }
    @Override
    public int compareTo(Vertex o) {
//Implement this
// so that it can return 1 and -1 depending on the f value
        return 0;
    }
}
