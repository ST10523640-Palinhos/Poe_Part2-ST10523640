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

            System.out.println("\n Please make sure to select the correct option: ");
            //scans line to read for user input and stores inputed choice in selectedChoice
            Scanner choice = new Scanner(System.in);
            String selectedChoice = choice.next();

            switch (selectedChoice) {
                //if the user picks option 1 
                case "1":
                    //this will loop until the message is sent 

                    int numMessages = 0;
                    System.out.println("How many messages would you like to send.");
                    //until the number of messages to be sent is greater than 0, numMessages is set to the next users input
                    while (numMessages <= 0) {

                        //Creates scanner to read users input 
                        Scanner nM = new Scanner(System.in);
                        //converting from string to integer and saving it in numMessages
                        numMessages = Integer.parseInt(nM.next());

                    }
                    //this will loop for the amount of times the user inputs
                    for (int i = 0; i < numMessages; i++) {
                        // calculates the current message number using the loop counter
                        int msgNumber = i + 1;
                        System.out.println("\n==+ Message " + msgNumber + " of " + numMessages + " ---");

                        // Create a new message object for each loop iteration
                        Message msg = new Message(msgNumber); // This line of code creates a new instance of a Message object in memory, initializes it with the specific identifier msgNumber, and assigns it to the reference variable msg so it can be used later.

                         //scanner created to read the recipient cell number
                            Scanner cellEntered = new Scanner(System.in);
                            
                            System.out.println("Enter recipient cell number:");
                            String cell = cellEntered.nextLine();
                            // validates the cell number and stores the result message
                            String cellResult = msg.checkRecipientCell(cell);
                            //keeps telling the user to enter a valid cell number 
                            while (cellResult.startsWith("Cell phone number incorrectly")) {
                                // displays an error message
                                System.out.println(cellResult);
                                // tells the user to try again 
                                System.out.println("Please re-enter recipient cell number:");
                                // reads the new cellphone number that has been re enterred 
                                cell = cellEntered.nextLine();
                                // now validates the new cell number 
                                cellResult = msg.checkRecipientCell(cell);
                            }
                            // a success if the cell number is correct 
                            System.out.println(cellResult);
                            // calls SentMessage which handles message input
                            String sendResult = msg.SentMessage();
                            // displays users choice 
                            System.out.println(sendResult);

                            // only increments the counter if the user chose to send the message
                            if (sendResult.equals("Successfully seent message.")) {
                                totalSent++;
                            }
                        }
                    }
            }
        }
    }

