/* All nodes should eventually reach the same average value */

import java.util.*;
public class Average {
    public static void main(String[] args) {

        Scanner t = new Scanner(System.in);
        System.out.print("Ente number of nodes: ");
        int n = t.nextInt();

        System.out.print("Enter node values: ");
        double nodes[] = new double[n];

        for(int i=0; i<n; i++){
            nodes[i] = t.nextDouble();
        }

        System.out.print("Enter number of rounds : ");
        int r = t.nextInt();

        for(int i=0; i<r; i++){
            double next[] = new double[n];
            for(int j=0; j<n ;j++) {
                next[j] = (nodes[(j-1+n)%n] + nodes[j] + nodes[(j+1+n)%n]) / 3.0 ;
            }
            nodes = next;
            System.out.print("Round " + i + ": ");
            for(double val : nodes) {
                System.out.printf("%.2f ", val);
            }
            System.out.println();
        }
    }
}
