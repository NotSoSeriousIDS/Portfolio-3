package sample;

import java.util.Stack;

public class Controller {
        public void initialize() {
                AStarGraph GraphModel =CreateGraph();

        }
        public AStarGraph CreateGraph() {
                AStarGraph MyMaze = new AStarGraph();
                // Make the graph provided to you in the diagram and table
                //The vertices must be constructed like A

                Vertex A = new Vertex("A", 0, 4);
                MyMaze.addvertex(A);
                Vertex B = new Vertex("B", 1,7);
                MyMaze.addvertex(B);
                Vertex C = new Vertex("C", 4,0);
                MyMaze.addvertex(C);
                Vertex D = new Vertex("D",3,7);
                MyMaze.addvertex(D);
                Vertex E = new Vertex("E",3,3);
                MyMaze.addvertex(E);
                Vertex F = new Vertex("F",6,6);
                MyMaze.addvertex(F);
                Vertex G = new Vertex("G",7,7);
                MyMaze.addvertex(G);
                Vertex H = new Vertex("H",8,7);
                MyMaze.addvertex(H);
                Vertex I = new Vertex("I",9,2);
                MyMaze.addvertex(I);
                Vertex J = new Vertex("J",11,5);
                MyMaze.addvertex(J);
                MyMaze.newconnection(A, B, 3.41);
                MyMaze.newconnection(A, C, 6.82);
                MyMaze.newconnection(B, D, 2.0);
                MyMaze.newconnection(C, G, 4.41);
                MyMaze.newconnection(C, I, 4.82);
                MyMaze.newconnection(D, E, 4.0);
                MyMaze.newconnection(E, F, 6.23);
                MyMaze.newconnection(F, G, 4.41);
                MyMaze.newconnection(F, H, 3.82);
                MyMaze.newconnection(G, H, 5.41);
                MyMaze.newconnection(G, I, 2.82);
                MyMaze.newconnection(H, J, 4.41);
                MyMaze.newconnection(I, J, 3.82);

                // TEST A STAR <-----------------
                Vertex start = E;
                Vertex slut = J;

                if(MyMaze.A_Star(start,slut,1))
                {
                        int limit=0;
                        System.out.println("Found a path");
                        Vertex pvertex=slut;
                        Stack<Vertex> Path = new Stack<>();
                        while (pvertex!=null)
                        {
                                Path.push(pvertex);
                                pvertex=pvertex.getPrev();
                        }
                        if(!Path.isEmpty())
                                limit =Path.size();
                        for(int i=0;i<limit-1;i++)
                                System.out.print(Path.pop().getid() +" - > ");
                        if (limit>0)
                                System.out.println(Path.pop().getid());

                }
                else
                        System.out.println("DID NOT FIND A PATH!!");
                return MyMaze;
        }
}


