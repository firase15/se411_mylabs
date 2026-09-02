package edu.psu.se411.model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class StackTest{

    @Test
    void testPushAndPop() 
    {
        Stack<String> stack = new Stack<String>();
        stack.push("Z");
        stack.push("A");
        assertEquals("A", stack.pop());
    }

    
    
    @Test
    void testEmptyStackException() {
        Stack<String> stack = new Stack<String>();
        try {
            stack.pop();
            fail("Throw an Exception");
        } catch (NoSuchElementException e) {
            
        }
    }

    @Test
    void testManyPushdPop() {
        Stack<String> stack = new  Stack<String>();
        
        
        stack.push("X");
        stack.push("Y");
        assertEquals("Y", stack.pop());
        assertEquals("X", stack.pop());
    }

}