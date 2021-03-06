package antgame;

import java.util.ArrayList;

public class Ant {

    // variables
    private Position p;	// position
    public int dir;
    public Colour c;	// colour
    public static int restCount;
    public boolean dead;
    public int s;		// state
    public boolean hasFood;
    public Instruction instruction;

    /**
     * Constructs an Ant with initial position, facing direction, colour and
     * state.
     *
     * @param p	initial position
     * @param dir	initial facing direction
     * @param c colour
     * @param s state
     */
    public Ant(Position p, int dir, Colour c, int s) {
        this(p, dir, c, 0, false, s);
    }

    /**
     * Constructs an Ant with initial position, facing direction, colour, number
     * of turns until next move, whether it is dead and state.
     *
     * @param p	position of the ant
     * @param dir	direction
     * @param c colour
     * @param restCount	number of turns to rest until next move
     * @param dead	true if this ant is dead, false if otherwise
     * @param s state
     */
    public Ant(Position p, int dir, Colour c, int restCount, boolean dead, int s) {
        this.p = p;
        this.dir = dir;
        this.c = c;
        this.restCount = restCount;
        this.dead = dead;
        this.s = s;
    }

    /**
     * Returns the direction after rotating left or right.
     *
     * @param lr	left or right
     * @param dir current direction
     * @return the direction after rotation
     */
    public int turn(LeftOrRight lr, int d) {
        return (d + (lr == LeftOrRight.LEFT ? 5 : 1)) % 6;
    }

    /**
     * Returns the colour of foe of ants with colour c.
     *
     * @param c colour
     * @return BLACK if c is RED, OTHERWISE RED.
     */
    public Colour otherColour(Colour c) {
        return (c == Colour.RED ? Colour.BLACK : Colour.RED);
    }

    /**
     * Calculates if the current cell and three cells in front of the ant has a
     * sense. p is the ants current position, d the directions of the three
     * cells in front. (need revision)
     *
     * @param p	position
     * @param d direction
     * @param sd sense direction
     * @return the position of the cell that has a sense, or (-1,-1) if no cell
     * has a sense.
     */
    public Position senseCell(Position p, int d, SenseDirection sd) {
        Position sensePosition;
        HexagonLinker hl;
        switch (sd) {
            case HERE:
                sensePosition = p;
                break;

            case AHEAD:
                hl = new HexagonLinker(p.x, p.y, d);
                sensePosition = new Position(hl.x, hl.y);
                break;

            case LEFTAHEAD:
                hl = new HexagonLinker(p.x, p.y, turn(LeftOrRight.LEFT, d));
                sensePosition = new Position(hl.x, hl.y);
                break;

            case RIGHTAHEAD:
                hl = new HexagonLinker(p.x, p.y, turn(LeftOrRight.RIGHT, d));
                sensePosition = new Position(hl.x, hl.y);
                break;
            default:
                return new Position(-1, -1);
        }

        if (!cellAt(sensePosition).isEmpty()) // ** cellAt is a method to locate a Cell object by its coordinate
        {
            return sensePosition;	// will be defined as soon as the World
        } else // class is available.
        {
            return new Position(-1, -1);
        }
    }

    /**
     * This is Michael's senseCell method. I am not quite sure whether it is
     * correct or not so I just keep it. Calculates if the current cell and
     * three cells in front of the ant has a sense. p is the ants current
     * position, d the directions of the three cells in front. (need revision)
     *
     * @param p	position
     * @param d direction
     * @param sd sense direction
     * @return the Arraylist of positions (x,y) of the cell if that has a sense,
     * or (-1,-1) if no cell has a sense.
     */
    public ArrayList senseCellList(SenseDirection sd) {
        ArrayList<Position> list = new ArrayList<>();
        list.add(p);
        for (int i = (dir - 1); i < (dir + 3); i++) {
            list.add(adjacentCell(p, i));
        }
        return list;
    }

    /**
     * Returns number of ants with colour c which are adjacent (in any
     * direction) to cell at position p.
     *
     * @param p	position
     * @param c colour of ant
     * @return number of adjacent ants of the given colour.
     */
    public int adjAnts(Position p, Colour c) {
        int numAnts = 0;
        int[] xlist = new int[6];
        int[] ylist = {p.y, p.y + 1, p.y + 1, p.y, p.y - 1, p.y - 1};
        xlist[0] = p.x + 1;
        xlist[3] = p.x - 1;
        if (p.y % 2 == 0) {
            xlist[1] = p.x;
            xlist[2] = p.x - 1;
            xlist[4] = p.x - 1;
            xlist[5] = p.x;
        } else {
            xlist[1] = p.x + 1;
            xlist[2] = p.x;
            xlist[4] = p.x;
            xlist[5] = p.x + 1;
        }

        for (int i = 0; i < 6; i++) {
            // if there is an enemy ant at (xi, yi), numAnt++
            // else, do nothing
            // this required an method to search and return 
            // an ant object by its position
        }
        return numAnts;
    }

    /**
     * Checks if an ant at a position is surrounded.
     *
     * @param p	position
     * @return true if it is surrounded, false if otherwise.
     * @see checkForSurroundedAntAt
     */
    public static boolean checkForSurroundedAnts(Position p) {
        return (adjAnts(p, otherColour(this.c)) >= 5);
    }

    /**
     * Returns the position of a cell next to the cell at p in direction d.
     *
     * @param p	position
     * @param d direction
     * @return the position of an adjacent cell
     */
    public Position adjacentCell(Position p, int d) {
        int x = p.x;
        int y = p.y;
        Position newPos = new Position(-1, -1);

        switch (d) {
            case 0:
                newPos.x = x + 1;
                newPos.y = y;
                break;

            case 1:
                if (y % 2 == 0) {
                    newPos.x = x;
                    newPos.y = y + 1;
                } else {
                    newPos.x = x + 1;
                    newPos.y = y + 1;
                }
                break;

            case 2:
                if (y % 2 == 0) {
                    newPos.x = x - 1;
                    newPos.y = y + 1;
                } else {
                    newPos.x = x;
                    newPos.y = y + 1;
                }
                break;

            case 3:
                newPos.x = x - 1;
                newPos.y = y;
                break;

            case 4:
                if (y % 2 == 0) {
                    newPos.x = x - 1;
                    newPos.y = y - 1;
                } else {
                    newPos.x = x;
                    newPos.y = y - 1;
                }
                break;

            case 5:
                if (y % 2 == 0) {
                    newPos.x = x;
                    newPos.y = y - 1;
                } else {
                    newPos.x = x + 1;
                    newPos.y = y - 1;
                }
                break;
        }

        return newPos;
    }

    /**
     * Returns the current state of an ant.
     *
     * @param a the target ant
     * @return state number of the ant
     */
    public static int state(Ant a) {
        return a.s;
    }

    /**
     * Returns the colour of an ant.
     *
     * @param a the target ant
     * @return colour of the ant
     */
    public static Colour colour(Ant a) {
        return a.c;
    }

    /**
     * Returns whether an ant is resting.
     *
     * @param a the target ant
     * @return true if it is resting, false if otherwise.
     */
    public static boolean resting(Ant a) {
        return (restCount != 0);
    }

    /**
     * Returns the facing direction of ant ant.
     *
     * @param a the target ant
     * @return the direction of the ant currently facing.
     */
    public static int direction(Ant a) {
        return a.dir;
    }

    /**
     * Returns whether an ant has food.
     *
     * @param a the target ant
     * @return true if it has food, false if otherwise.
     */
    public static boolean hasFood(Ant a) {
        return (a.Food != 0);
    }

    /**
     * Sets the state of an ant to s.
     *
     * @param a the target ant
     * @param s the new state
     */
    public static void setState(Ant a, int s) {
        a.s = s;
    }

    /**
     * Sets rest counter of an ant to r.
     *
     * @param a the target ant
     * @param r turns until next move
     */
    public static void setResting(Ant a, int r) {
        a.restCount = r;
    }

    /**
     * Sets direction of an ant to dir.
     *
     * @param a the target ant
     * @param dir the nre direction
     */
    public static void setDirection(Ant a, int dir) {
        a.dir = dir;
    }

    /**
     * Sets hasFood of an ant to f.
     *
     * @param a the target ant
     * @param f the new hasFood value
     */
    public static void setHasFood(Ant a, boolean f) {
        a.hasFood = f;
    }

    public enum SenseDirection{
    	HERE, AHEAD, LEFTAHEAD, RIGHTAHEAD
    }
    
    public enum Colour {
        RED, BLACK
    }

    public enum LeftOrRight {
        LEFT, RIGHT
    }

    public enum Condition {
        FRIEND, FOE,
        FRIEND_WITH_FOOD, FOR_WITH_FOOD,
        FOOD, ROCK,
        MARKER,
        FOE_MARKER,
        HOME, FOE_HOME
    }
    
    abstract class Instruction {}

    public class Sense extends Instruction {
        SenseDirection sensedir;
        int st1, st2;
        Condition cond;
        int markerType;
        public Sense(SenseDirection sd, int s1, int s2, Condition c, int m) {
            if(c != Condition.MARKER){    // for all non-MARKER, m = -1
                m = -1;
            }
            sensedir = sd;
            st1 = s1;
            st2 = s2;
            cond = d;
            markerType = m;
        }
        public Sense(SenseDirection sd, int s1, int s2, Condition c) {
            this(sd, s1, s2, c, -1);
        }
    }

    public class Mark extends Instruction {
        int i, st;
    }

    public class PickUp extends Instruction {
        int st1, st2;
    }

    public class Drop extends Instruction {
        int st;
    }

    public class Turn extends Instruction {
        LeftOrRight lr;
        int st;
    }

    public class Move extends Instruction {
        int st1, st2;
    }

    public class Flip extends Instruction {
        int p, st1, st2;
    }
}
