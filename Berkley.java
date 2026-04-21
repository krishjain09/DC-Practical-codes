
import java.util.*;

class Berkley{
    public static void main(String[] args) {
        Scanner t = new Scanner(System.in);

        System.out.print("Enter master value: ");
        int master = t.nextInt();

        System.out.print("Enter number of processes: ");
        int n = t.nextInt();
        int clock[] = new int[n];

        for(int i=0; i<n; i++){
            System.out.print("P" + (i+1) + " = ");
            clock[i] = t.nextInt();
            
        }
        System.out.println("\nBefore Sync");
        for(int i =0 ;i <n; i++){
            System.out.println("P" + (i+1) + " = " + clock[i]);
        }

        int sum = master;
        for(int c : clock) sum+=c;
        int avg = sum / (n + 1);

        for(int i=0; i<n; i++){
            int offset = avg - clock[i];
            clock[i]+=offset;
            System.out.println("P" + (i+1) + " adjusted by " + offset);
        }

        System.out.println("\nAfter Sync");
        for(int i =0 ;i <n; i++){
            System.out.println("P" + (i+1) + " = " + clock[i]);
        }

    }
}