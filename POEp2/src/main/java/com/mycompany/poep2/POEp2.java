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
            System.out.println("-- QuickChat --");
            System.out.println("1) Would you like to send a message.");
            System.out.println("2) Show the recently sent messages. ");
            System.out.println("3) Quit.");

            System.out.println("\n Please select an option1,2,3: ");
            //scans line to read for user input and stores inputed choice in selectedChoice
            Scanner choice = new Scanner(System.in);
            String selectedChoice = choice.next();

            switch (selectedChoice) {
                //if option 1 is selected
                case "1":
                    //loop runs until the amount of messages stated are sent

                    int numMessages = 0;
                    System.out.println("How many messages would you like to send");
                    //until the number of messages to be sent is greater than 0, numMessages is set to the next users input
                    while (numMessages <= 0) {

                        //Creates scanner to read users input 
                        Scanner nM = new Scanner(System.in);
                        //convert from string to integer and saves in numMessages
                        numMessages = Integer.parseInt(nM.next());

                    }
                    //loops for the amount of times the user specified
                    for (int i = 0; i < numMessages; i++) {
                        // calculates the current message number using the loop counter
                        int msgNumber = i + 1;
                        System.out.println("\n--- Message " + msgNumber + " of " + numMessages + " ---");

                        // Create a new message object for each loop iteration
                        Message msg = new Message(msgNumber);

                        // creates a scanner to read the recipient cell number
                        Scanner cellEntered = new Scanner(System.in);

                        System.out.println("Enter recipient cell number:");
                        String cell = cellEntered.nextLine();
                        // validates the cell number and stores the result message
                        String cellResult = msg.checkRecipientCell(cell);
                        // keeps prompting user to enter a until a valid cell number is entered
                        while (cellResult.startsWith("Cell phone number incorrectly")) {
                            // displays the error message to the user
                            System.out.println(cellResult);
                            // asks the user to try again
                            System.out.println("Please re-enter recipient cell number:");
                            // reads the new cell number attempt
                            cell = cellEntered.nextLine();
                            // validates the new cell number
                            cellResult = msg.checkRecipientCell(cell);
                        }
                        // displays the success message if valid cell is entered
                        System.out.println(cellResult);
                        // calls SentMessage which handles message input, hash generation and send/store/disregard
                        String sendResult = msg.SentMessage();
                        // displays the result of the users choice
                        System.out.println(sendResult);

                        // only increments the counter if the user chose to send the message
                        if (sendResult.equals("Message successfully sent")) {
                            totalSent++;
                        }
                    }

                    // Display total after all messages are processed
                    System.out.println("\nTotal messages sent: " + totalSent);
                    break;

                //option 2 selected
                case "2":
                    System.out.println("Coming Soon.");
                    break;

                // option 3 selected
                case "3":
                    // sets quit to true, will exit the while loop
                    quit = true;
                    // displays a goodbye message to show user has quit aplication
                    System.out.println("Thank you for using QuickChat. Goodbye!");
                    break;

                default:
                    // displays invalid message if the user enters anything other than 1, 2 or 3
                    System.out.println("Invalid option. Please enter 1, 2, or 3.");
            }
        }
    }
}
