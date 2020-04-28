package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("AStar_PathFinder");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        AStarGraph MyMaze = new AStarGraph();
        // Make the graph provided to you in the diagram and table
        //The vertices must be constructed like A
        Vertex A = new Vertex("A", 0, 4);
        MyMaze.addvertex(A);
        Vertex B = new Vertex("B", 0,4);
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
        //MyMaze.printGraph();


            if (MyMaze.A_Star(A, J)) {
                System.out.println("AStar_PathFinder");
                Vertex pvertex = J; // has to be changed so that it is able to take start and goal.
                //to print path -->
                Stack<Vertex> Path = new Stack<>();
                int limit = 0;
                while (pvertex != null) {
                    Path.push(pvertex);
                    pvertex = pvertex.getPrev();
                }
                if (!Path.isEmpty())
                    limit = Path.size();
                for (int i = 0; i < limit - 1; i++)
                    System.out.print(Path.pop().getid() + " - > ");
                if (limit > 0)
                    System.out.println(Path.pop().getid());

            } else
                System.out.println("DID NOT FIND A PATH!!");



        }

    }

