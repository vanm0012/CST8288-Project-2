package io.github.vanm0012.symphonymanager.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMovement
{
    final String testName = "Movement 1";
    final int testId = 123;

    Movement testMovement;

    @Before
    public void setUp()
    {
        testMovement = new Movement(testName, testId);
    }

    @Test
    public void setName()
    {
        Movement movement = new Movement();
        movement.setName(testName);
        assertEquals(movement.getName(), testName);
    }

    @Test
    public void setId()
    {
        Movement movement = new Movement();
        movement.setId(testId);
        assertEquals(movement.getId(), testId);
    }

    @Test
    public void getName()
    {
        assertEquals(testMovement.getName(), testName);
    }

    @Test
    public void getId()
    {
        assertEquals(testMovement.getId(), testId);
    }

}