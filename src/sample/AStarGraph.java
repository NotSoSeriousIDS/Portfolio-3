package sample;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStarGraph {
    private ArrayList<Vertex> vertices;
    private boolean calculateAsManhattan = false;
    public AStarGraph() {
        setVertices(new ArrayList<Vertex>());
    }
    public void addvertex(Vertex v) {
        getVertices().add(v);
    }
    public void newconnection(Vertex v1, Vertex v2, Double dist) {
        v1.addOutEdge(v2,dist);
        v2.addOutEdge(v1,dist);
    }
    public boolean A_Star(Vertex start, Vertex destination, float function, StringBuilder stringBuilder) {
        if (start==null || destination==null )
            return false;
        PriorityQueue<Vertex> openList = new PriorityQueue<Vertex>();
        ArrayList<Vertex> closedList = new ArrayList<Vertex>();
        openList.offer(start);
        Vertex current;
        ArrayList<Vertex> currentNeighbors;
        Vertex Neighbor;
        //Initialize h with chosen heuristic
        for (int i = 0; i< getVertices().size(); i++) {
            getVertices().get(i).setPrev(null); // sets vertice to null, so that its cleaned before rerun
            getVertices().get(i).setg(Double.POSITIVE_INFINITY);// sets vertice to null, so that its cleaned before rerun
            if(function /*call something else */  == 1){
                getVertices().get(i).seth(Manhattan(getVertices().get(i),destination));
            } if(function == 2) {
                getVertices().get(i).seth(Euclidean(getVertices().get(i),destination));
            }
        }
        start.setg(0.0);
        start.calculatef();
        //Start algorithm
        System.out.println("Start Algorithm");
        System.out.println();

        stringBuilder.append("Calculating path...");
        //***************AStar algorithm*******************

        while(openList.size()>0){
            current = openList.remove();
            System.out.println("Remove from open list: " + current.getid());
            if(current.equals(destination)){
                System.out.println(destination.getid());
                System.out.println("Found destination, distance was: "+current.getg());
                return true;
            }

            closedList.add(current);
            System.out.println("add to closedList" + current.getid());
            for (int i = 0; i < current.getNeighbours().size() ; i++) {
                Vertex vertex = current.getNeighbours().get(i);
                stringBuilder.append("\n"+ " - > ").append(vertex.getid());
                double newWeight; // newWeight
                stringBuilder.append( " Distance from origin: " +current.getg() + current.getNeighbourDistance().get(i));
               newWeight = current.getg() + current.getNeighbourDistance().get(i); // calculate distance

                // IF NEW CALCULATED DISTANCE is less than CALCULATED DISTANCE ON VERTEX
                if (newWeight < vertex.getg()) {
                    System.out.println(" We set previous to be: " + current.getid());
                    vertex.setPrev(current);
                    vertex.setg(newWeight);
                    System.out.println("We set the value: " + newWeight + ", to the vertex: " + vertex.getid());
                    vertex.calculatef();
                    // IF closedlist and opelist does not contain vertex
                    if (!closedList.contains(vertex) && !openList.contains(vertex)) {
                        openList.offer(vertex);
                    }
                    if (openList.contains(vertex)) {
                        openList.remove(vertex);
                        openList.offer(vertex);
                    }
                }
            }
        } return false;

    }
    public Double Manhattan(Vertex from,Vertex goal){
        double distance = Math.abs(goal.getx()-from.getx()) + Math.abs(goal.gety()-from.gety());
        return distance; // example of sample.Manhattan A*
    }
    public Double Euclidean( Vertex from,Vertex to){
        double x = to.getx()-from.getx();
        double y = to.getx()-from.gety();
        double distance = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
        return distance; // sample.Euclidean
    }
    public ArrayList<Vertex> getVertices() {
        return vertices;
    }
    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }
}

class Vertex implements Comparable<Vertex>{
    private String id;
    private ArrayList<Vertex> Neighbours=new ArrayList<Vertex>();
    private ArrayList<Double> NeighbourDistance =new ArrayList<Double>();
    private Double f;
    private Double g;
    private Double h;
    private Integer x;
    private Integer y;
    private Vertex prev =null;
    public Vertex(String id, int x,int y){
        this.id = id;
        this.x = x;
        this.y = y;
        f=Double.POSITIVE_INFINITY;
        g=Double.POSITIVE_INFINITY;
        h=0.0;
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
    public String getid(){ return id;};
    public Integer getx(){ return x; }
    public Integer gety(){return y; }

    public void calculatef(){ f=g+h; }

    public Double getg() { return g; }

    public void setg(Double newg){ g=newg; }

    public void seth(Double estimate){ h=estimate; }
    public Vertex getPrev() { return prev; }
    public void setPrev(Vertex v){prev=v;}

    @Override
    public int compareTo(Vertex vertex) {
        if (this.f > vertex.f)
            return 1;
        if (this.f < vertex.f)
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "SetVertex " + id;
    }
}
