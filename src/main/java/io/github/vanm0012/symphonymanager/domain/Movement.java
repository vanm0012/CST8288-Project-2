package io.github.vanm0012.symphonymanager.domain;

/**
 * This holds and manipulates all the data for a movement
 */
public class Movement
{
    private String name;
    private int id;

    /**
     * Default constructor
     */
    public Movement() {}

    /**
     * Constructor sets all the variables
     * @param name The name of the movement
     * @param id The ID of the movement
     */
    public Movement(String name, int id)
    {
        this.name = name;
        this.id = id;
    }

    /**
     * Sets the name of the movement from given string
     * @param name The new name for the movement
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the ID from the given integer
     * @param id The new ID for the movement
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Returns the current name of the movement
     * @return The current name of the movement
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the current ID of the movement
     * @return The current ID of the movement
     */
    public int getId()
    {
        return id;
    }
}
