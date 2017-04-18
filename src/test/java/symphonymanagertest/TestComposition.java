package symphonymanagertest;

import io.github.vanm0012.symphonymanager.domain.Composition;
import io.github.vanm0012.symphonymanager.domain.Movement;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestComposition
{
    final Movement testMovement = new Movement("Movement 1", 123);
    final String testComposer = "Composer Name";
    final String testName = "Composition 1";
    final String testId = "comp_1";

    Composition testComposition;

    @Before
    public void setUp()
    {
        ArrayList<Movement> movements = new ArrayList<>();
        movements.add(testMovement);
        testComposition = new Composition(testComposer, testName, testId, movements);
    }

    @Test
    public void testConstructor()
    {
        Composition composition = new Composition();
        assertNotNull(composition);
    }

    @Test
    public void testConstructorWithParams()
    {
        ArrayList<Movement> movements = new ArrayList<>();
        movements.add(testMovement);
        Composition composition = new Composition(testComposer, testName, testId, movements);
        assertNotNull(composition);
    }

    @Test
    public void testSetMovements()
    {
        Composition composition = new Composition();
        ArrayList<Movement> movements = new ArrayList<>();
        movements.add(testMovement);
        composition.setMovements(movements);
        assertEquals(composition.getMovements(), movements);
    }

    @Test
    public void testSetComposer()
    {
        Composition composition = new Composition();
        composition.setComposer(testComposer);
        assertEquals(composition.getComposer(), testComposer);
    }

    @Test
    public void testSetName()
    {
        Composition composition = new Composition();
        composition.setName(testName);
        assertEquals(composition.getName(), testName);
    }

    @Test
    public void testSetId()
    {
        Composition composition = new Composition();
        composition.setId(testId);
        assertEquals(composition.getId(), testId);
    }

    @Test
    public void testGetMovements()
    {
        assertEquals(testComposition.getMovements().get(0), testMovement);
    }

    @Test
    public void testGetComposer()
    {
        assertEquals(testComposition.getComposer(), testComposer);
    }

    @Test
    public void testGetName()
    {
        assertEquals(testComposition.getName(), testName);
    }

    @Test
    public void testGetId()
    {
        assertEquals(testComposition.getId(), testId);
    }
}
