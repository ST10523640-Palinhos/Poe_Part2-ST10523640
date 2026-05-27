/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poep2;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author matco
 */
public class POEp2Test {
    
    public POEp2Test() {
    }
    /**
     * Test Case 1: Message 1
     * Recipient: +27834557896 (Valid)
     * Message: "Did you get the cake?"
     */
    @Test
    public void testMessage1_ValidRecipient() {
        Message msg = new Message(1);
        String expected = "Recipient cell number successfully captured.";
        String actual = msg.checkRecipientCell("+27834557896");
        assertEquals(expected, actual);
    }

    /**
     * Test Case 2: Message 2
     * Recipient: +27838884567 (Valid)
     * Message text processing check
     */
    @Test
    public void testMessage2_ValidLength() {
        Message msg = new Message(2);
        String text = "Where are you? You are late! I have asked you to be on time.";
        String expected = "Message ready to send.";
        String actual = msg.checkMessageLength(text);
        assertEquals(expected, actual);
    }

    /**
     * Test Case 3: Message 3
     * Recipient: +27834484567 (Valid)
     * Verification of structural hashing framework logic
     */
    @Test
    public void testMessage3_HashFormat() {
        Message msg = new Message(3);
        msg.setMessageText("Yohoooo, I am at your gate.");
        String hash = msg.createMessageHash();
        
        // Assert hash starts with 2 digits of the random ID followed by ":3:"
        // And ends with the capitalized combined first and last word ("YOHOOOO,GATE.")
        assertTrue(hash.matches("\\d{2}:3:YOHOOOO,GATE\\."));
    }

    /**
     * Test Case 4: Message 4
     * Recipient/Developer: 0838884567 (Invalid - missing international prefix +27)
     */
    @Test
    public void testMessage4_InvalidRecipientFormatting() {
        Message msg = new Message(4);
        String expected = "Cell phone number does not contain an international code or is incorrectly formatted.";
        
        // The assignment image provides "0838884567" which should fail validation rules
        String actual = msg.checkRecipientCell("0838884567");
        assertEquals(expected, actual);
    }

    /**
     * Test Case 5: Message 5
     * Recipient: +27838884567 (Valid)
     * Message: "Ok, I am leaving without you."
     */
    @Test
    public void testMessage5_ValidDataCaptured() {
        Message msg = new Message(5);
        String expected = "Recipient cell number successfully captured.";
        String actual = msg.checkRecipientCell("+27838884567");
        assertEquals(expected, actual);
        
        String lengthExpected = "Message ready to send.";
        String lengthActual = msg.checkMessageLength("Ok, I am leaving without you.");
        assertEquals(lengthExpected, lengthActual);
    }
}
    
