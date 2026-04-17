import java.util.*;

public class Banker {
    public static void main(String args[]){

        System.out.println("Enter number of processes: ");
        Scanner t = new Scanner(System.in); 
        int p = t.nextInt();

        System.out.println("Enter number of resources: ");
        int r = t.nextInt();

        //Construct Max Matrix
        int max[][] = new int[p][r];
        System.out.println("Enter max matrix: ");
        for(int i=0; i<p; i++){
            for(int j=0; j<r; j++){
                max[i][j] = t.nextInt();
            }
        }

        //Construct Alloc matrix
        int alloc[][] = new int[p][r];
        System.out.println("Enter alloc matrix: ");
        for(int i=0; i<p; i++){
            for(int j=0; j<r; j++){
                alloc[i][j] = t.nextInt();
            }
        }

        //Construct Available resources
        int available[] = new int[r];
        System.out.println("Enter available resources: ");
        for(int i=0; i<r; i++){
            available[i] = t.nextInt();
        }

        //Calculate need matrix
        int need[][] = new int[p][r];
        for(int i=0;i<p;i++){
            for(int j=0; j<r;j++){
                need[i][j] = max[i][j] - alloc[i][j];
            }
        }
        
        
        boolean completed[] = new boolean[p];

        for(int i=0; i<p; i++){
            completed[i] = false;
        }

        int count = 0;
        int process = -1;
        List<Integer> ans = new ArrayList<>();
        do{
            System.out.println("Max Matrix \t Alloc Matrix");
            for(int i=0; i<p; i++){
                for(int j=0; j<r; j++){
                    System.out.print(max[i][j] + " ");
                }
                System.out.print("\t\t\t");
                for(int j=0; j<r; j++){
                    System.out.print(alloc[i][j] + " ");
                }
                System.out.println();
            }
            process = -1;
            for(int i=0; i<p; i++){
               
                if(!completed[i]){
                    process = i; 
                    for(int j=0; j<r; j++){
                        if(need[process][j] > available[j]){
                            process = -1;
                            break;
                        }
                    }
                }
                if(process!=-1){
                    break;
                }
            }

            if(process!=-1){
                completed[process] = true;
                count+=1;
                ans.add(process);
                for(int j=0; j<r; j++){
                    available[j]+=alloc[process][j];
                    max[process][j] = 0;
                    alloc[process][j] = 0;
                }
            }

        }while(process!=-1 && count!=p);

        if(count == p){
            System.out.println("System safe");
            for(int x : ans){
                System.out.print(x + "-> ");
            }
        }else{
            System.out.println("System unsafe");
        }
    }
}
