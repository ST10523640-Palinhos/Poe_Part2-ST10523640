/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poep2;

import java.util.Scanner;

/**
 *
 * @author matco
 */
public class POEp2 {

    public static void main(String[] args) {
       // POE Part 2
            
            //welcome message displayed after succesfull login
            System.out.println("Welcome to QuickChat");

            //number of messages that the user wants to send is initialized
            //initialize quite to false
            boolean quit = false;
            System.out.println("");
            int totalSent = 0;
            //until user selects quite the loop will runs
            while (!quit) {
                System.out.println("==== QuickChat Menu ====");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quite");

                System.out.println("\n Please select an option: ");
                //scans line to read for user input and stores inputed choice in selectedChoice
                Scanner choice = new Scanner(System.in);
                String selectedChoice = choice.next();

                
            }
        }
    }

