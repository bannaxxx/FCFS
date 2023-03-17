/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.roundrobin;

import java.util.Scanner;
public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int[] at = new int[n];
        int[] bt = new int[n];
        int[] ct = new int[n];
        int[] tat = new int[n];
        int[] wt = new int[n];
        int total_wt = 0, total_tat = 0, time = 0, quantum;

        // Accept arrival times and burst times of all processes
        System.out.println("Enter arrival times:");
        for (int i = 0; i < n; i++) {
            at[i] = sc.nextInt();
        }
        System.out.println("Enter burst times:");
        for (int i = 0; i < n; i++) {
            bt[i] = sc.nextInt();
        }
        System.out.print("Enter time quantum: ");
        quantum = sc.nextInt();
        int[] rem_bt = new int[n];
        for (int i = 0; i < n; i++) {
            rem_bt[i] = bt[i];
        }
        while (true) {
            boolean done = true;
            for (int i = 0; i < n; i++) {
                if (rem_bt[i] > 0) {
                    done = false;
                    if (rem_bt[i] > quantum) {
                        time += quantum;
                        rem_bt[i] -= quantum;
                    } else {
                        time += rem_bt[i];
                        rem_bt[i] = 0;
                        ct[i] = time;
                        tat[i] = ct[i] - at[i];
                        wt[i] = tat[i] - bt[i];
                        total_wt += wt[i];
                        total_tat += tat[i];
                    }
                }
            }
            if (done) {
                break;
            }
        }

        // Print results
        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        for (int i = 0; i < n; i++) {
            System.out.println((i+1) + "\t\t" + at[i] + "\t\t" + bt[i] + "\t\t" + ct[i] + "\t\t" + tat[i] + "\t\t" + wt[i]);
        }
        System.out.println("Average waiting time: " + (float)total_wt/n);
        System.out.println("Average turnaround time: " + (float)total_tat/n);
    }
}

