package ant.game;

/**
 * Class holding the information about each cell and its current state.
 * @author jos23
 */
public class Cell {
    
    int foodAmount;
    boolean empty;
    boolean rock;
    boolean antHillRed;
    boolean antHillBlack;
    boolean antRed;
    boolean antBlack;
    boolean food;
    boolean markerRed;
    boolean markerBlack;
    
    /**
     * As the majority of the cells in the world are blank, it is best to give
     * each new cell created a status of being empty.
     */
    public Cell() {
        setEmpty();
    }
    
    /**
     * Checks if the cell object is an empty cell or not.
     * @return boolean - true if the cell is empty, false otherwise.
     */
    private boolean isEmpty() {
        return empty;
    }
    
    /**
     * Checks if the cell object is has a rock in or not.
     * @return boolean - true if the cell contains a rock, false otherwise.
     */
    private boolean isRock() {
        return rock;
    }
    
    /**
     * Checks if the cell is part of any ant hill or not.
     * @return boolean - true if the cell is part of any anthill, false otherwise.
     */
    private boolean isAntHill() {
        if (antHillRed || antHillBlack) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Checks if the cell is part of an ant hill of a specific colour or not.
     * @param String - Colour of the ant hill to check for.
     * @return boolean - true if the cell is part of an anthill of the specific
     *                   colour, false otherwise.
     */
    private boolean isAntHill(String colour) {
        if (colour.equals("Red") || colour.equals("red")) {
            return antHillRed;
        } else if (colour.equals("Black") || colour.equals("black")) {
            return antHillBlack;
        } else {
            System.out.println("The colour you have supplied is not a correct"
                    + " team colour. Please try again with another colour.");
            return false;
        }
    }
    
    /**
     * Checks if the cell contains an ant or not.
     * @return boolean - true if the cell does contain an ant, false otherwise.
     */
    private boolean isAnt() {
        if (antRed || antBlack) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Checks if the cell contains an ant of a specific colour or not.
     * @param String - Colour of the ant to be checked for.
     * @return boolean - true if the cell does contain an ant of the specified
     *                   colour, false otherwise.
     */
    private boolean isAnt(String colour) {
        if (colour.equals("Red") || colour.equals("red")) {
            return antRed;
        } else if (colour.equals("Black") || colour.equals("black")) {
            return antBlack;
        } else {
            System.out.println("The colour you have supplied is not a correct"
                    + " team colour. Please try again with another colour.");
            return false;
        }
    }
    
    /**
     * Checks if the cell contains an ant or not.
     * @return boolean - true if the cell does contain an ant, false otherwise.
     */
    private boolean isFood() {
        return food;
    }
    
    /**
     * Checks if the cell has been marked by any species of ant.
     * @return boolean - true if the cell does contain a marker from either
     *                   species, false otherwise.
     */
    private boolean isMarker() {
        if (markerRed || markerBlack) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Checks if the cell has been marked by any species of ant.
     * @param String - Colour of the marker to be checked for.
     * @return boolean - true if the cell does contain a marker from either
     *                   species, false otherwise.
     */
    private boolean isMarker(String colour) {
        if (colour.equals("Red") || colour.equals("red")) {
            return markerRed;
        } else if (colour.equals("Black") || colour.equals("black")) {
            return markerBlack;
        } else {
            System.out.println("The colour you have supplied is not a correct"
                    + " team colour. Please try again with another colour.");
            return false;
        }
    }
    
    /**
     * Set the cell's state to be empty.
     */
    private void setEmpty() {
        empty = true;
    }
    
    /**
     * Set the cell to contain a rock.
     */
    private void setRock() {
        rock = true;
    }
    
    /**
     * Set the cell to contain food.
     */
    private void setFood() {
        food = true;
    }
    
    /**
     * Set the cell to contain food and set how much food the cell contains.
     * @param amount - amount of food items the cell holds.
     */
    private void setFood(int amount) {
        food = true;
        foodAmount = amount;
    }
    
    /**
     * Set the cell as part of an ant hill for a specific species of ant.
     * @param colour - Colour of the team the ant hill belongs to.
     */
    private void setAntHill(String colour) {
        if (colour.equals("Red") || colour.equals("red")) {
            antHillRed = true;
        } else if (colour.equals("Black") || colour.equals("black")) {
            antHillBlack = true;
        } else {
            System.out.println("The colour you have supplied is not a correct"
                    + " team colour. Please try again with another colour.");
        }
    }
    
    /**
     * Set the cell to contain a specific species of ant.
     * @param colour - Colour of the team the ant belongs to.
     */
    private void setAnt(String colour) {
        if (colour.equals("Red") || colour.equals("red")) {
            antRed = true;
        } else if (colour.equals("Black") || colour.equals("black")) {
            antBlack = true;
        } else {
            System.out.println("The colour you have supplied is not a correct"
                    + " team colour. Please try again with another colour.");
        }
    }
    
    /**
     * Set the cell to contain a marker of a specific species of ant.
     * @param colour - Colour of the team the marker belongs to.
     */
    private void setMarker(String colour) {
        if (colour.equals("Red") || colour.equals("red")) {
            markerRed = true;
        } else if (colour.equals("Black") || colour.equals("black")) {
            markerBlack = true;
        } else {
            System.out.println("The colour you have supplied is not a correct"
                    + " team colour. Please try again with another colour.");
        }
    }
    
    /**
     * Increases the amount of food in the cell by 1. Important for if an ant
     * were to die.
     */
    private void increaseFood() {
        foodAmount++;
    }
    
    public class Position<X, Y> { 
      public int x; 
      public int y; 
      public Position(int x, int y) { 
        this.x = x; 
        this.y = y; 
      } 
    } 
}
