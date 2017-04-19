package io.github.vanm0012.symphonymanager.domain;

import java.util.ArrayList;

/**
 * This class holds and manipulates the data needed for a composition.
 * The composition holds an array of the movements within it as well as the name and composer.
 */
public class Composition
{
    private ArrayList<Movement> movements;
    private String composer;
    private String name;
    private String id;

    /**
     * The default constructor initializes the movements arraylist
     */
    public Composition()
    {
        this.movements = new ArrayList<>();
    }

    /**
     * This constructor initializes the arraylist and sets all the variables.
     * @param composer The name of the composer for this composition
     * @param name The name of the composition
     * @param id The identification number for the composition
     * @param movements The arraylist of the movements in the composition
     */
    public Composition(String composer, String name, String id, ArrayList<Movement> movements)
    {
        this.movements = movements;
        this.composer = composer;
        this.name = name;
        this.id = id;
    }

    /**
     * Sets the movement arraylist from a given arraylist
     * @param movements The arraylist of the movements in the composition
     */
    public void setMovements(ArrayList<Movement> movements)
    {
        this.movements = movements;
    }

    /**
     * Sets the composer from a given string
     * @param composer The name of the composer for this composition
     */
    public void setComposer(String composer)
    {
        this.composer = composer;
    }

    /**
     * Sets the name of the composition from a given string
     * @param name The name of the composition
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the ID from a given string
     * @param id The identification number for the composition
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * Returns the current movement arraylist
     * @return Arraylist The current movement list
     */
    public ArrayList<Movement> getMovements()
    {
        return movements;
    }

    /**
     * Returns the composer name
     * @return String The current composer name
     */
    public String getComposer()
    {
        return composer;
    }

    public String getName()
    {
        return name;
    }

    /**
     * Returns the current ID of the composition
     * @return String The current ID
     */
    public String getId()
    {
        return id;
    }

    public void addMovement(String name,int id){
        movements.add(new Movement(name,id));
    }
}
