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
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

        AStarGraph MyMaze = new AStarGraph();
            // Make the graph provided to you in the diagram and table
            //The vertices must be constructed like A
            Vertex A = new Vertex("A", 0, 4);
            Vertex J = null; //This must be changed

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

