/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poep2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author matco
 */
public class Message {
    //stores the unique ID for this specific message
    private String messageID;
    //stores the number of this message e.g. 1, 2, 3
    private int messageNumber;
    //stores the recipient cell number for this message
    private String recipient;
    //stores the text content of this message
    private String messageText;
    //stores the generated hash for this message
    private String messageHash;
    //one shared list that stores all sent message details across all Message objects
    private static ArrayList<String> sentMessages = new ArrayList<String>();
    //one shared counter that tracks how many messages have been sent across all Message objects
    private static int totalMessagesSent = 0;

    //constructor , runs when a new message object is created
    public Message(int inMessageNumber) {
        //stores the message number passed from main
        messageNumber = inMessageNumber;
        // generates and stores a randome 10 digit ID for the message
        messageID = generateMessageID();

    }

    //method to check message ID is not more than 10 characters
    public boolean checkMessageID() {
        //returns true if ID length is 10
        //message ID needs to be 10 characters long and not less so use == 
        //if <= used then a ID of less than 10 characters long will be parsed
        return messageID.length() == 10;
    }

    //method to check recipient cell is valid
    public String checkRecipientCell(String inCell) {
        //if the number does not start with the international code or does not meet the correct-
        //amount of character to be a cellphone number, the number is rejected
        if (!inCell.startsWith("+27") || inCell.length() != 12) {
            //display message for invalid cellphone number
            return "Cell phone number does not contain an international code or is incorrectly formatted.";
        }
        //stores valid cellphone number in recipient
        recipient = inCell;
        //returns successfull stored cell number message
        return "Recipient cell number successfully captured.";
    }

    //method to return the created hash message using string manipulation
    public String createMessageHash() {
        //splits the message into indvidual word, using spaces as the seperators
        // the use of the "(\\s+") takes into account for spaces, tabs and extra spacing, more reliable;
        String[] words = messageText.trim().split("\\s+");
        //use substring to extract the first 2 characters of the message ID
        //stores in idPrefix
        String idPrefix = messageID.substring(0, 2);
        //creates variable to hold the first word
        String firstWord = words[0];
        //creates variable to store last word of message, if only 1 word, last word will be the same as the first word
        String lastWord = words[words.length - 1];
        //creates the message hash by combining all the gathered data
        messageHash = idPrefix + ":" + messageNumber + ":" + (firstWord + lastWord).toUpperCase();
        //returns the completed message hash as a string
        return messageHash;
    }

    //method to allow the user to input the message, send, store or disregard the message
    public String SentMessage() {
        //create scanner to read user input
        Scanner sc = new Scanner(System.in);

        //ask user to input text message
        System.out.println("Enter your message:");
        //reads and stores message the user entered 
        String inMessage = sc.nextLine();
        //keep asking for the user to enter a message less than 250 characters or fewer
        while (inMessage.length() > 250) {
            System.out.println("Enter a message less then 250 characters.");
            System.out.println("Enter your message:");
            //stores new message entered 
            inMessage = sc.nextLine();
        }
        //stored valid message text in messageText field
        messageText = inMessage;
        //tells user the message is sent 
        System.out.println("Message sent");

        //calls createMessageHash now that messageText is set
        createMessageHash();
        //displays generated hash to user
        System.out.println("Message Hash: " + messageHash);

        //Ask user what to do with the message
        System.out.println("\nWhat would you like to do with this message?");
        //option 1
        System.out.println("1) Send Message");
        //option 2
        System.out.println("2) Disregard Message");
        //option 3
        System.out.println("3) Store Message to send later");
        //asks user to eneter choice
        System.out.println("Enter your choice:");
        //reads users choice
        String choice = sc.nextLine();

        
        switch (choice) {
            //case if option 1 is selected
            case "1":
                // Display full message details in required order
                String details = "Message ID:   " + messageID + "\n"
                        + "Message Hash: " + messageHash + "\n"
                        + "Recipient:    " + recipient + "\n"
                        + "Message:      " + messageText;
                //displays full message details to user
                System.out.println(details);
                //adds the message details to the shared sent messages list
                sentMessages.add(details);
                //increases count for total messages sent
                totalMessagesSent++;
                //tells user message has been sent succesfully
                return "Message successfully sent";

                //case if option 2 is selected
            case "2":
                //displays message to user
                return "Press 0 to delete the message";

                //case if option 3 is selected
            case "3":
                //calls storeMessage to save the message to a JSON file
                storeMessage();
                //displays message to user
                return "Message successfully stored";

                //if an invalid selection is made
            default:
                //displays invalid option made to user
                return "Invalid option. Message was not processed.";
        }
    }

    //method to returns all messages sent during this session
    public static String printMessages() {
         //checks if no messages have been sent yet
        if (sentMessages.isEmpty()) {
             //displays a message if the list is empty
            return "No messages have been sent yet.";
        }
        //starts every message empty to build the full message list
        String allMessages = "";
        //loops through all sent messages
        for (int i = 0; i < sentMessages.size(); i++) {
            //adds each message to its string with its number label
            allMessages = allMessages + "\n[Message " + (i + 1) + "]\n" + sentMessages.get(i) + "\n";
        }
        //returns the list of sent messages
        return allMessages;
    }

    //method to return total messages
    public static int returnTotalMessages() {
        return totalMessagesSent;
    }

    //method to save message to a json file
    //Adapted from GeeksforGeeks (2026)
    //available at https://www.geeksforgeeks.org/java/how-to-read-and-write-json-files-in-java/
    public void storeMessage() {
         //builds a JSON formatted string using the message details
        String json = "{\n" + "  \"messageID\": \"" + messageID + "\",\n" + "  \"messageHash\": \"" + messageHash + "\",\n" + "  \"recipient\": \"" + recipient + "\",\n" + "  \"message\": \"" + messageText + "\"\n" + "}";

          //tries to write the JSON string to messages.json, appending to existing content
          //"true" means append mode , new data will be added and not delete old data
          //without "true" the json file will be overwritten each time;
        try (FileWriter fw = new FileWriter("messages.json", true)) {
            //writes the JSON entry followed by a new line
            fw.write(json + "\n");
             //confirms the message was saved
            System.out.println("Message saved to messages.json");
        } catch (IOException e) {
             //if an error occurs during writing, displays the error message
            System.out.println("Error saving message: " + e.getMessage());
        }
    }

    //Adapted from  W3Docs (n.d.)
    //available at https://www.w3docs.com/snippets/java/how-to-generate-a-random-alpha-numeric-string.html?utm_source=chatgpt.com
    //method to generate a random 10 digit message ID
    private String generateMessageID() {
         //creates a Random object to generate random numbers
        Random rand = new Random();
        //creates a StringBuilder to build the ID digit by digit
        //stringbuilder is like a string accept it keeps the same string,
        //while adding to it instead of creating a new one each time
        StringBuilder id = new StringBuilder();
        //loops exactly 10 times to build a 10 digit ID
        for (int i = 0; i < 10; i++) {
             //appends a random digit between 0 and 9 each iteration
            id.append(rand.nextInt(10));
        }
        //converts the StringBuilder to a String and returns it
        return id.toString();
    }

    //sets the message text directly — used for unit testing purposes only
    public void setMessageText(String inMessage) {
        //stores the provided message text in the messageText field
        messageText = inMessage;
    }

    //checks that the message does not exceed 250 characters
    public String checkMessageLength(String inMessage) {
        //checks if the message is longer than 250 characters
        if (inMessage.length() > 250) {
            //calculates exactly how many characters over 250 the message is
            int exceededBy = inMessage.length() - 250;
            //returns an error message showing how many characters need to be removed
            return "Message exceeds 250 characters by " + exceededBy + "; please reduce the size.";
        }
        //stores the valid message text in the messageText field
        messageText = inMessage;
        //return success message
        return "Message ready to send.";
    }
}

