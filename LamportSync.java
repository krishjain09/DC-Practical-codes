import java.util.*;

class LamportSync{
    public static void main(String args[]){

        Scanner t = new Scanner(System.in);
        System.out.println("Enter number of processes: ");

        int n = t.nextInt();
        int clock[] = new int[n];
        
        for(int i=0; i<n; i++){
            clock[i] = 0;
        }


        while(true){
            System.out.println("\n1. Internal Event");
            System.out.println("2. Send Event");
            System.out.println("3. Exit");
            System.out.println("Enter your choice");
            int choice = t.nextInt();


            switch (choice) {
                case 1:
                    System.out.println("Enter process id: ");
                    int p = t.nextInt();
                    clock[p]++;
                    System.out.println("Clock of process " + p + " = " + clock[p]);
                    break;


                case 2: 
                    System.out.println("Enter sender id");
                    int sender = t.nextInt();

                    System.out.println("Enter receiver id");
                    int receiver = t.nextInt();

                    clock[sender]++;
                    int msgTime = clock[sender];
                    System.out.println("Message sent from P" + sender + " with timestamp " + msgTime);

                    clock[receiver] = Math.max(clock[receiver], msgTime)+ 1;
                    System.out.println("Clock of Process " + receiver + " updated to " + clock[receiver]);

                    break;
                case 3:
                    System.out.println("Exiting..");
                    return;
                default:
                    break;
            }
            System.out.println("Current CLock Values: ");
            for(int i=0; i<n; i++){
                System.out.print("P" + i + ":" + clock[i] + " ");
            }
        }
    }
    
}

/*
    Lamport logical clocks provide partial ordering because they can only order events that are causally related (via same process or message passing).
Events with no communication are independent, so their order cannot be determined.
Short Example (Independent Events)
P0: event A  
P1: event B  
(no communication)

👉 No message between A and B

✅ Conclusion
A → B ❌  
B → A ❌

👉 A and B are independent (concurrent)
No message = independent events
 */