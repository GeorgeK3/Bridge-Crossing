import java.util.*;

public class Main{
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        int N = Integer.valueOf(args[0]) ;
        int[] time = new int[N]; 
        int totalTime = 0 ;

        for (int i=1 ; i < args.length ; i+=1){
            time[i-1] = Integer.valueOf(args[i]);
            totalTime += time[i-1];
        }



        State game = new State(time);
        while(!game.isFinal() && game.getTotalTime()<=totalTime){ 
            game.print();
            ArrayList<State> children = new ArrayList<>();
            children = game.getChildren();

            int min_f = children.get(0).getF();
            State min_child = children.get(0);

            for (int i=1;i<children.size();i+=1){
                if (children.get(i).getF() < min_f){
                    min_f = children.get(i).getF();
                    min_child = children.get(i);
                }
            }

            game = min_child;
        }

        if (game.getTotalTime()>totalTime){
            System.out.println("No solution found");
        }else{
            game.print();
            System.out.println("\n\nTotal time left = " + (totalTime-game.getTotalTime()));
        }
        

        long endTime = System.currentTimeMillis();

        long elapsedTime = endTime - startTime;

        System.out.println("Elapsed time: " + elapsedTime + " milliseconds");

        return;
        
    }
}