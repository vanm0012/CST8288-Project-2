package io.github.vanm0012.symphonymanager.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sheldon on 2017-04-18.
 */
public class MovementDAOTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void MovmentDAO(){
        new CompositionDAO();
        new MovementDAO();
    }

}