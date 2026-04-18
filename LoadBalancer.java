
import java.util.*;

class LoadBalancer {
    
    static LinkedHashMap<Integer,Integer> load = new LinkedHashMap<>();
    
    static int getMinNode(){
        int minLoad = Integer.MAX_VALUE;
        int minNode = 0;
        
        for(int node : load.keySet()){
            if(load.get(node) < minLoad){
                minLoad = load.get(node);
                minNode = node;
            }
        }
        return minNode;
    }
    
    static void printLoad(){
        System.out.print("\nUpdated Load Disribution: {");
        boolean first = true;
        
        for(int node : load.keySet()){
            if(!first) System.out.print(", ");
            System.out.print("Node " + node + " : " + load.get(node) + " processes.");
            first = false;
        }
        System.out.print("}");
        System.out.println();
    }
    
    
    
    public static void main(String args[]){
        
        System.out.println("Enter number of nodes: ");
        Scanner t = new Scanner(System.in);
        int nodes = t.nextInt();
        
        System.out.println("Enter number of processes: ");
        int processes = t.nextInt();
        
        for(int i=0; i<nodes; i++){
            load.put(i,0);
        }
        
        for(int i=0; i<processes; i++){
            int node = getMinNode();
            load.put(node, load.get(node)+1);
        }
        
        for(int x : load.keySet()){
            System.out.println("Node " + x +" has " + load.get(x) + " processes.");
        }
        
        System.out.print("\nCurrent Load Disribution: {");
        boolean first = true;
        
        for(int node : load.keySet()){
            if(!first) System.out.print(", ");
            System.out.print("Node " + node + " : " + load.get(node) + " processes");
            first = false;
        }
        System.out.print("}");
        System.out.println();
        
        while(true){
            
            System.out.println("1. Add a Process");
            System.out.println("2. Remove a Process");
            System.out.println("3. Add a node");
            System.out.println("4. Remove a node");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
            
            int choice = t.nextInt();
            
            switch(choice){
                case 1 :
                    int node = getMinNode();
                    load.put(node, load.get(node)+1);
                    System.out.println("Added process in node: " + node);
                    printLoad();
                    break;
                case 2: 
                     System.out.println("Enter node : ");
                     int node_val = t.nextInt();
                     if(load.containsKey(node_val) && load.get(node_val) > 0){
                         load.put(node_val, load.get(node_val)-1);
                          System.out.println("Removed process from  node_val: " + node_val);
                          printLoad();
                     }
                     else{
                          System.out.println("Invalid operation");
                     }
                     break;
                case 3:
                    System.out.println("Enter node : ");
                    int node_2 = t.nextInt();
                    load.put(node_2,0);
                    System.out.println("Added node " + node_2);
                    printLoad();
                    break;
                case 4:
                    System.out.println("Enter node : ");
                    int node_3 = t.nextInt();
                    if(!load.containsKey(node_3)){
                        System.out.println("Invalid operation");
                        break;
                    }
                    int p = load.get(node_3);
                    load.remove(node_3);
                    System.out.println("Removed node successfully");
                    
                    System.out.println("Redistributing processes..");
                    for(int i=0; i<p; i++){
                        int minNode = getMinNode();
                        load.put(minNode, load.get(minNode) + 1);
                    }
                    printLoad();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Default");
            }
        }
        
    }
}