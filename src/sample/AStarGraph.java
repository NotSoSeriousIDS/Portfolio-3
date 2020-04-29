package sample;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStarGraph {
    private ArrayList<Vertex> vertices;
    public boolean calculateAsManhattan = false;
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
    public boolean A_Star(Vertex start, Vertex destination, int function) {
        if (start==null || destination==null )
            return false;

        PriorityQueue<Vertex> openList = new PriorityQueue<Vertex>();
        ArrayList<Vertex> closedList = new ArrayList<Vertex>();
        openList.offer(start);
        Vertex current;
        ArrayList<Vertex> currentNeighbors;
        Vertex Neighbor;
        //Initialize h with chosen heuristic
        for (int i =0; i<vertices.size();i++) {
            vertices.get(i).setPrev(null); // sets vertice to null, so that its cleaned before rerun
            vertices.get(i).setg(Double.POSITIVE_INFINITY);// sets vertice to null, so that its cleaned before rerun
            if(function /*call something else */  == 1){
                vertices.get(i).seth(Manhattan(vertices.get(i),destination));
            } if(function == 2) {
                vertices.get(i).seth(Euclidean(vertices.get(i),destination));
            }


        }
        start.setg(0.0);
        start.calculatef();
        //Start algorithm
        System.out.println("Start Algorithm");
        System.out.println();
        //Implement the Astar algorithm

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

                System.out.println("We go to: " + vertex.getid());
                double newWeight; // newWeight


                // CALCULATE DISTANCE
               /* if (calculateAsManhattan) {
                    newWeight = current.getg() + Manhattan(current, vertex);
                    System.out.println("we calculated the Manhattan to be: " + newWeight);
                } else {
                    newWeight = current.getg() + Euclidean(current, vertex);
                    System.out.println("we calculated the Euclidean to be: " + newWeight);
                } */

               newWeight = current.getg() + current.getNeighbourDistance().get(i); // calculate distance

                // IF NEW CALCULATED DISTANCE is less than CALCULATED DISTANCE ON VERTEX
                if (newWeight < vertex.getg()) {
                    System.out.println("We set previous to be: " + current.getid());
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
        }







        return false;
    }
    public Double Manhattan(Vertex from,Vertex goal){
        //Implement this
        System.out.println(goal.getx());
        double distance = Math.abs(goal.getx()-from.getx()) + Math.abs(goal.gety()-from.gety());
        return distance; // example of sample.Manhattan A*
    }
    public Double Euclidean( Vertex from,Vertex to){
        //Implement this
        //System.out.println("From v: "+from.getid()+", fromX: "+from.getx()+", fromY: "+from.gety());
        //System.out.println("To v: "+to.getid()+", ToX: "+to.getx()+", toY: "+to.gety());
        double x = to.getx()-from.getx();
        double y = to.getx()-from.gety();
        double distance = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
        //System.out.println(distance);
        return distance; // sample.Euclidean


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
    public Double getf() { return f; }
    public void calculatef(){ f=g+h; }

    public Double getg() { return g; }

    public void setg(Double newg){ g=newg; }
    public Double geth(){ return h; }
    public void seth(Double estimate){ h=estimate; }
    public Vertex getPrev() { return prev; }
    public void setPrev(Vertex v){prev=v;}
    public void printVertex(){
        System.out.println(id + " g: "+g+ ", h: "+h+", f: "+f);
    }
    @Override
    public int compareTo(Vertex vertex) {
        if (this.f > vertex.f)
            return 1;
        if (this.f < vertex.f)
            return -1;
        return 0;
    }
}
