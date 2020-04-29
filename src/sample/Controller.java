package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;

import javax.print.attribute.standard.Destination;
import java.util.Stack;

public class Controller  {


        @FXML
        ComboBox ComboboxEndpath;

        @FXML
        ComboBox ComboboxStartpath;

        @FXML
        javafx.scene.control.TextArea textArea;

        @FXML
        Button print;

        @FXML
        RadioButton runEucledian;

        @FXML
        ToggleGroup Group1;

        @FXML
        RadioButton runManhattan;



        Vertex start;
        Vertex slut;
        AStarGraph graphModel = CreateGraph();


        public void initialize() {
                ComboboxEndpath.getItems().addAll(graphModel.getVertices());
                ComboboxStartpath.getItems().addAll(graphModel.getVertices());
                Group1 = new ToggleGroup();
                runManhattan.setToggleGroup(Group1);
                runEucledian.setToggleGroup(Group1);



        }

        public void Print(ActionEvent actionEvent) {

                //når denne knap trykkes, skal den tage A_star formlen og printe den ud med Heurstik funktionen -
                // "function" og sætte den til 2 samt. Start og endpath variablerne



                if(runEucledian.isSelected()){
                        System.out.println("Eucledian is selected");

                        try{

                                String s = Astarsolver(start,slut,2,graphModel);
                                textArea.setText(s);
                                textArea.appendText("\n" + "Weight: " + slut.getg());
                                //textArea.appendText((slut.getg()) + "Shortest Path is \n" + start +" - > " +slut);
                                // String.valueOf
                                //String.valueOf
                        }
                        catch (Exception e) {
                                textArea.setText("vælg Start og slut path først");
                        }
                }if(runManhattan.isSelected()) {
                        System.out.println("Manhattan is selected");

                        //System.out.print(Path.pop().getid() +" - > ");
                        try{
                                String s = Astarsolver(start,slut,1,graphModel);
                                textArea.setText(s);
                                textArea.appendText("\n" + "Weight: " + slut.getg());
                        }
                        catch (Exception e) {
                                textArea.setText("vælg Start og slut path først");
                        }
                }
        }

        public void PrintGraph(InputMethodEvent inputMethodEvent) {

                //sætte print ind i textarea
        }

        public void SetStart(ActionEvent actionEvent) {
                ComboBox <Vertex> Start = (ComboBox<Vertex>) actionEvent.getSource();
                System.out.println(Start.getValue());
                start = Start.getValue();
        }

        public void SetEnd(ActionEvent actionEvent) {
                ComboBox <Vertex> End = (ComboBox<Vertex>) actionEvent.getSource();
                System.out.println(End.getValue());
                slut = End.getValue();
        }

        public void Manhattan(ActionEvent actionEvent) {



        }

        public void Eucledian(ActionEvent actionEvent) {

        }


        public AStarGraph CreateGraph() {
                AStarGraph MyMaze = new AStarGraph();
                // Make the graph provided to you in the diagram and table
                //The vertices must be constructed like A

                Vertex A = new Vertex("A", 0, 4);
                MyMaze.addvertex(A);
                Vertex B = new Vertex("B", 1, 7);
                MyMaze.addvertex(B);
                Vertex C = new Vertex("C", 4, 0);
                MyMaze.addvertex(C);
                Vertex D = new Vertex("D", 3, 7);
                MyMaze.addvertex(D);
                Vertex E = new Vertex("E", 3, 3);
                MyMaze.addvertex(E);
                Vertex F = new Vertex("F", 6, 6);
                MyMaze.addvertex(F);
                Vertex G = new Vertex("G", 7, 7);
                MyMaze.addvertex(G);
                Vertex H = new Vertex("H", 8, 7);
                MyMaze.addvertex(H);
                Vertex I = new Vertex("I", 9, 2);
                MyMaze.addvertex(I);
                Vertex J = new Vertex("J", 11, 5);
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
                return MyMaze;
                // TEST A STAR <-----------------

        }
        public String Astarsolver(Vertex start,Vertex slut, int DistanceMethod, AStarGraph graph) {
                StringBuilder stringBuilder = new StringBuilder();
                if(graph.A_Star(start,slut,DistanceMethod, stringBuilder))
                {
                        int limit=0;
                        //System.out.println("Found a path");
                        stringBuilder.append("\n"+ "Shortest path is:");
                        stringBuilder.append(System.lineSeparator());
                        Vertex pvertex=slut;
                        Stack<Vertex> Path = new Stack<>();
                        while (pvertex!=null)
                        {
                                Path.push(pvertex);
                                pvertex=pvertex.getPrev();
                        }
                        if(!Path.isEmpty())
                                limit = Path.size();
                        for(int i=0;i<limit-1;i++)

                              stringBuilder.append(Path.pop().getid() + " - > ");
                               //System.out.print(Path.pop().getid() + " - > "); // her printes endelige resultat
                        if (limit>0)
                                stringBuilder.append(Path.pop().getid() );
                               //System.out.println(Path.pop().getid());

                }
                else
                        stringBuilder.append("DID NOT FIND A PATH");
                     //   System.out.println("DID NOT FIND A PATH!!");
                return stringBuilder.toString();
        }

        public void HandlePathLine() {

        }
}




