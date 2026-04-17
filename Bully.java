import java.util.*;

class Bully{
    int n;
    boolean visited[];
    boolean alive[];
    int coordinator;

    Bully(int n){
        this.n = n;
        visited = new boolean[n];
        alive = new boolean[n];
        for(int i=0;i<n; i++){
            alive[i] = true;
        }
        coordinator = n-1;
    }

    void crash(int id){
        alive[id]=false;
        System.out.println("Process " + id + " has failed.");
    }

    void election(int initiator){

        if(visited[initiator]) return;
        visited[initiator] = true;

        System.out.println("\nProcess " + initiator + " initiates the election process.");
        List<Integer> responders = new ArrayList<>();

        for(int i= initiator+1;i<n;i++){
            if(alive[i]){
                System.out.println("Election message sent from Process " + initiator + " to Process " + i + ".");
                responders.add(i);
            }
        }

        for(int r : responders){
            System.out.println("Process " + r + " sends OK message to " + initiator + "." );
        }

        for(int r : responders){
            election(r);
        }

        if(responders.isEmpty()){
            coordinator = initiator;
            System.out.println("\nProcess " + coordinator + " is elected as new coordinator.");
            for(int i=0; i<n; i++){
                if(alive[i] && i!=initiator){
                    System.out.println("CO-ORDINATOR message sent from Process " + coordinator + " to Process " + i + ".");
                }
            }
        }

    }

    public static void main(String args[]){
        System.out.println("Bully Algorithm...");

        Scanner t = new Scanner(System.in);
        System.out.println("Enter number of processes: ");
        int n = t.nextInt();
        Bully b = new Bully(n);

        System.out.println("Enter Process Id to crash: ");
        int crashId = t.nextInt();
        b.crash(crashId);
        
        System.out.println("Enter Process id to initiate election");
        int initiator = t.nextInt();
        b.election(initiator);

        t.close();
    }
}