package com.project.cooking;
import com.project.cooking.actors.ChefTask;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ChefTaskTest {

    private ChefTask task;

    @Before
    public void setUp() {
        task = new ChefTask();
    }

    @Test
    public void testNoArgConstructor() {
        assertNull(task.getDescription());
        assertEquals(0, task.getPriority());
        assertFalse(task.isCompleted());
    }

    @Test
    public void testConstructorWithDescriptionAndPriority() {
        ChefTask t = new ChefTask("Prepare sauce", 1);
        assertEquals("Prepare sauce", t.getDescription());
        assertEquals(1, t.getPriority());
        assertFalse(t.isCompleted());  
    }

    @Test
    public void testConstructorWithDescriptionOnly() {
        ChefTask t = new ChefTask("Chop vegetables");
        assertEquals("Chop vegetables", t.getDescription());
        assertEquals(0, t.getPriority());  
        assertFalse(t.isCompleted());
    }

    @Test
    public void testGetSetDescription() {
        task.setDescription("Bake bread");
        assertEquals("Bake bread", task.getDescription());
    }

    @Test
    public void testGetSetPriority() {
        task.setPriority(2);
        assertEquals(2, task.getPriority());
    }

    @Test
    public void testIsSetCompleted() {
        task.setCompleted(true);
        assertTrue(task.isCompleted());
        task.setCompleted(false);
        assertFalse(task.isCompleted());
    }

    @Test
    public void testToStringHighPriorityCompleted() {
        task.setDescription("Boil water");
        task.setPriority(1);
        task.setCompleted(true);

        String expected = "[High] Boil water (Completed)";
        assertEquals(expected, task.toString());
    }

    @Test
    public void testToStringMediumPriorityPending() {
        task.setDescription("Slice onions");
        task.setPriority(2);
        task.setCompleted(false);

        String expected = "[Medium] Slice onions (Pending)";
        assertEquals(expected, task.toString());
    }

    @Test
    public void testToStringLowPriorityPending() {
        task.setDescription("Clean kitchen");
        task.setPriority(3);
        task.setCompleted(false);

        String expected = "[Low] Clean kitchen (Pending)";
        assertEquals(expected, task.toString());
    }

    @Test
    public void testToStringUnknownPriority() {
        task.setDescription("Unknown task");
        task.setPriority(99); 
        task.setCompleted(false);

        String expected = "[Unknown] Unknown task (Pending)";
        assertEquals(expected, task.toString());
    }
}