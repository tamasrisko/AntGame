/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

/**
 *
 * @author Hannah
 */
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

public class AntBrain {

    /**
     * Outline of Finite state machine language
     *
     * Instructions of this state Machine can be described as follows: Mark i st
     * - Set mark i in current cell and go to st unMark i st - Clear mark i in
     * current cell and go to st PickUp st1 st2 - Pick up food from current cell
     * and go to st1; go to st2 if there is no food in the current cell Drop st
     * - Drop food in current cell and go to st Turn lr st - Turn left or right
     * and go to st Move st1 st2 - Move forward and go to st1; go to st2 if the
     * cell ahead is blocked Flip p st1 st2 - Choose a random number x from 0 to
     * p-1; go to st1 if x=0 and st2 otherwise Move st1 st2 - Move?? Return to
     * state 0 on failure to find food state st - State?? SeAdHome st1 st2 - [Go
     * Home] is the cell in front of me my anthill?
     *
     * Ant randomly walks around until locates some food. It then picks up the
     * food and wonders randomly until it finds itself at home
     *
     * How the brain can work is have a stack or list which jumps from state to
     * state. So default state is 0, Move is called and jumps to state 2, if
     * failed state 0. Will repeat until can move, this mean trying a different
     * direction each time will trying to move. Once moved, try to pick up food,
     * if failed stay at current state, if found food, go to state 8.
     */

    // ***Varaibles***
    private Ant a;
    private SenseDirection sd;
    Random random;
    Ant.LeftOrRight lr;
    Ant.Condition cond;
    Ant.Colour colour;
    
//    ArrayList<Method> Instructions;
//    ArrayList<Ant> Inst;
//    Class<?> typeInt = int.class;

    Method test;

    public AntBrain(Ant _a) {
        this.a = _a;
//        Instructions = new ArrayList<Method>();
//        Inst = new ArrayList<Ant>();
    }

    /**
     * Retrieve the instruction for the ant to follow
     *
     * @param a To call methods which actions the ant will do - I think this is
     * better than the ant colour
     * @param state current state of ant
     */
    public void getInstructions(Colour c, int state) throws NoSuchMethodException // return an instruction
    {
        //a.getClass().getMethod(null, parameterTypes)
        switch (state) {
            case 0: //-SeAd 4 1 Food
                // Sense Ahead for -food- [Search] is there food in front of me
                //a.senseCell(sd.AHEAD);// Check the cell infront,
                if (a.senseCell(a.getPosition(), a.dir, sd.AHEAD) == cond.FOOD) // if there is food - move to cell, state: 4
                {
                    a.setState(a, 4);
                } else // else state: 1
                {
                    a.setState(a, 1);
                }

                // or this will how it will be programmed
                //return Instructions;
                //return Sense;
                break;
            case 1: //-SeAd 2 6 Enemy
                if (a.senseCell(a.getPosition(), a.dir, sd.AHEAD) == cond.FOE) // Sense Ahead for -enemy- [Search] is there an ememy in front of me
                {
                    a.setState(a, 2);// if there is enemy - sense ally ants, state: 2
                    // Check the cell infront,
                } else {
                    a.setState(a, 6); // else state: 6
                }

                break;
            case 2: //-CheckSurrAnts -SeAd 3 6 Enemies
                // Sense Around current ant for -allies- [Search] is there are allies around me
                if (a.CheckSurrAnts(a.getPosition()) = true)// if there is ally ants - fight?, state: 3
                {
                    // Not sure if allies or enemy ants
                    a.setState(a, 3);
                } else {
                    a.setState(a, 6);
                }
                // else state: 6 ---
                break;
            case 3: //-More ally or enemy ants? 0
                a.checkForSurroundedAnts(a.getPosition());    // If more enemy ants around surrounding me - die, else move
                a.setState(a, 0); // if more allies than enemies
                // 5> enemy ants?
                // if more enemy ants - die - turn to food 
                // else enemy ant dies, state: 0 - checks for food
                break;
            case 4: //-Move 5 0
                // Move forward and go to state 5 0 if failed
                if (a.senseCell(a.getPosition(), a.dir, sd.AHEAD) == cond.ROCK || a.senseCell(a.getPosition(), a.dir, sd.AHEAD) == cond.FOE)// Checks if rock or enmy is ahead which will stop it from moving
                {
                    a.setState(a, 6); // Six because we know there is a rock/enemy infront
                } else {
                    a.setPosition(new Position(0, 0));// if moved was sucsessful, state: 5
                    a.setState(a, 0);
                    // resting has to be here after moving
                }
                // else failure, state: 0

                // Needs to get the ants direction and move it foward
                break;
            case 5: //-PickUp 11 0
                a.setHasFood(a, true); // Pick up food and jump to state 11, 0 if failed
                a.setState(a, 11);
                break;
            case 6: //-Flip 3 7 8
                // p = 3. St1 = 7, St2 = 8
                // x is a random number between 0-p
                // Random number between 0-3
                if (randomInt(3) == 0) {
                    a.setState(a, 7);
                } else {
                    a.setState(a, 8);
                }// if x = 0, state: 7
                // else state: 8
                break;
            case 7: //-Turn Left 0
                a.turn(lr.LEFT, a.dir); // Turn left and go to state 0
                a.setState(a, 0);//left, state: 0
                break;
            case 8: //-Flip 2 9 10
                // p = 2. St1 = 9, St2 = 10
                // x is a random number between 0-p
                // Random number between 0-2
                if (randomInt(2) == 0) // if x = 0, state: 9
                {
                    a.setState(a, 9);
                } else {
                    a.setState(a, 10);
                }
                // else state: 10
                break;
            case 9: //- Turn Right 0
                a.turn(lr.RIGHT, a.dir); // Turn right and go to state 0
                a.setState(a, 0);//right, state: 0
                break;
            case 10: //-Move 0 6
                // Move forward and go to state 0, 3 if failed
                if (a.senseCell(a.getPosition(), a.dir, sd.AHEAD) == cond.ROCK || a.senseCell(a.getPosition(), a.dir, sd.AHEAD) == cond.FOE)// Checks if rock or enmy is ahead which will stop it from moving
                {
                    a.setState(a, 6);
                } else {
                    a.setPosition(new Position(0, 0));
                    a.setState(a, 0);
                    // resting has to be here after moving
                }// if moved was sucsessful, state: 0
                // else failure, state: 6
                break;
            case 11: //-SeAd 12 14 Home
                // Sense Ahead for -Home- [Search] is there antHill in front of me
                // Check the cell infront,
                if (a.senseCell(a.getPosition(), a.dir, sd.AHEAD) == cond.FOOD)// if there is home - move to cell, state: 12
                {
                    a.setState(a, 12);
                } else { // else state: 14
                    a.setState(a, 14);
                }
                break;
            case 12: //-Move 14 13
                // Move forward and go to state 13, 11 if faild
                if (a.senseCell(a.getPosition(), a.dir, sd.AHEAD) == cond.HOME || a.senseCell(a.getPosition(), a.dir, sd.AHEAD) == "enemy ahead")// Checks if rock or enmy is ahead which will stop it from moving
                {
                    a.setState(a, 14);
                } else {
                    a.setPosition(new Position(0, 0));
                    a.setState(a, 13);
                    // resting has to be here after moving
                }// if moved was sucsessfyl, state: 13
                // else failure, state 11
                break;
            case 13: //-Drop 0
                if (a.senseCell(a.getPosition, a.dir, sd.HERE) == cond.HOME) // Drop food and return to searching
                {
                    a.Drop(); // Drop, State: 0
                    a.setHasFood(a, false);
                }
                a.setState(a, 0);
                break;
            case 14: //- Flip 3 15 16
                // p = 3. St1 = 15, St2 = 16
                // x is a random number between 0-p
                // Random number between 0-3
                if (randomInt(3) == 0) {
                    a.setState(a, 15);
                } else {
                    a.setState(a, 16);
                }// if x = 0, state: 15
                // else state: 16
                break;
            case 15: //-Turn Left 11
                a.turn(lr.LEFT, a.dir); // Turn left and go to state 9
                a.setState(a, 11); //left, state: 11
                break;
            case 16: //-Flip 2 17 18
                // p = 2. St1 = 12, St2 = 18
                // x is a random number between 0-p
                // Random number between 0-2
                if (randomInt(2) == 0) {
                    a.setState(a, 17);
                } else {
                    a.setState(a, 18);
                }// if x = 0, state: 17
                // else state: 18
                break;
            case 17: //-Turn Right 11
                a.turn(lr.RIGHT, a.dir); // Turn right and go to state 11
                a.setState(a, 11); //right, state: 11
                break;
            case 18: //-Move 11 14
                // Move forward and go to state 11, 14 if faild
                if (a.senseCell(a.getPosition(), a.dir, sd.AHEAD) == cond.ROCK || a.senseCell(a.getPosition(), a.dir, sd.HERE) == cond.FOE)// Checks if rock or enmy is ahead which will stop it from moving
                {
                    a.setState(a, 11);
                } else {
                    a.setPosition(new Position(0, 0));
                    a.setState(a, 14);
                    // resting has to be here after moving
                }// if moved was sucsessfyl, state: 11
            // else failure, state 14
        }
    }

    /**
     *
     * @param a to check their current state to return
     * @return the current state of the ant
     */
    public int State(Ant a) {
        return a.state(a);
    }

    /**
     *
     * @param a to check their colour to return
     * @return the ant a colour
     */
    public Colour Colour(Ant a) {
        return a.colour(a);
    }

    /**
     *
     * @param a to be check their current resting time to return
     * @return the current resting time of ant
     */
    public int resting(Ant a) {
        return a.resting();
    }

    /**
     *
     * @param a to have their direction
     * @return the direction the ant a is currently facing
     */
    public int direction(Ant a) {
        return a.direction(a);
    }

    /**
     *
     * @param a to be checking if they have food to return
     * @return the value true if ant has food, false is not
     */
    public boolean hasFood(Ant a) {
        return a.hasFood(a);
    }

    /**
     * Set the state of the ant so the ant will know what instruction should be
     * done next
     *
     * @param s max state is 18 (will do a check, set state to 0 is else)
     */
    public void setState(int s) {
        if (s < 0 || s > 18) {
            a.setState(a, 0);
        } else {
            a.setState(a, s);
        }
    }

    /**
     * Set the ant to resting for 14 round before ant does another action
     *
     * @param r 14
     */
    public void setResting(int r) {
        a.setResting(a, r);
    }

    /**
     * Set direction on where ant will be facing
     *
     * @param d 0-5
     */
    public void setDirection(int d) {
        if (d < 0 || d > 5) {
            a.setDirection(a, 0);
            System.out.println("Direction was set to 0 as it was not between 0-5");
        } else {
            a.setDirection(a, d);
        }
    }

    /**
     * Set if ant has food or not
     *
     * @param f set true is ant will have food, false if ant dropped food
     */
    public void setHasFood(boolean f) {
        a.setHasFood(a, f);
    }
    
    public int randomInt(int n)
    {
        int h;
        this.random = new Random(n);
        
        h = random.nextInt(n) + 1;
        return h;
    }
}
