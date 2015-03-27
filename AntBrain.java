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
import java.util.Random;

public class AntBrain
{    
    /**
     * Outline of Finite state machine language
     * 
     * Instructions of this state Machine can be described as follows:
     * Mark i st        - Set mark i in current cell and go to st
     * unMark  i st     - Clear mark i in current cell and go to st
     * PickUp st1 st2   - Pick up food from current cell and go to st1;
     *                    go to st2 if there is no food in the current cell
     * Drop st          - Drop food in current cell and go to st
     * Turn lr st       - Turn left or right and go to st
     * Move st1 st2     - Move forward and go to st1;
     *                    go to st2 if the cell ahead is blocked
     * Flip p st1 st2   - Choose a random number x from 0 to p-1;
     *                    go to st1 if x=0 and st2 otherwise
     * Move st1 st2     - Move?? Return to state 0 on failure to find food
     * state st         - State??
     * SeAdHome st1 st2 - [Go Home] is the cell in front of me my anthill?
     * 
     * Ant randomly walks around until locates some food. It then picks up the
     * food and wonders randomly until it finds itself at home
     * 
     * How the brain can work is have a stack or list which jumps from state to 
     * state. So default state is 0, Move is called and jumps to state 2, if 
     * failed state 0. Will repeat until can move, this mean trying a different 
     * direction each time will trying to move.
     * Once moved, try to pick up food, if failed stay at current state, if
     * found food, go to state 8.
     */
        
    
    // ***Varaibles***
    private Ant a;
    
    public AntBrain(Ant _a)
    {
        this.a = _a;
    }
    
    /**
     * Retrieve the instruction for the ant to follow
     * @param a To call methods which actions the ant will do -  I think this is better than the ant colour
     * @param state current state of ant
     */
    public void getInstructions(Ant a, int state)    // return an instruction
    {
        
        switch (state)
        {
            case 0: //-SeAd 4 1 Food
                    // Sense Ahead for -food- [Search] is there food in front of me
                // Check the cell infront,
                // if there is food - move to cell, state: 4
                // else state: 1
                break;
            case 1: //-SeAd 2 6 Enemy
                    // Sense Ahead for -enemy- [Search] is there an ememy in front of me
                // Check the cell infront,
                // if there is enemy - sense ally ants, state: 2
                // else state: 6
                break;
            case 2: //-CheckSurrAnts -SeAd 3 6 Ally
                    // Sense Around current ant for -allies- [Search] is there are allies around me
                // if there is ally ants - fight?, state: 3
                // else state: 6 ---
                break;
            case 3: //-More ally or enemy ants? 0
                    // If more enemy ants around surrounding me - die, else move
                // 5> enemy ants?
                // if more enemy ants - die - turn to food 
                // else enemy ant dies, state: 0 - checks for food
                break;
            case 4: //-Move 5 0
                    // Move forward and go to state 5 0 if failed
                // if moved was sucsessful, state: 5
                // else failure, state: 0
                
                // Needs to get the ants direction and move it foward
                break;
            case 5: //-PickUp 11 0
                    // Pick up food and jump to state 9, 0 if failed
                break;
            case 6: //-Flip 3 7 8
                    // p = 3. St1 = 7, St2 = 8
                    // x is a random number between 0-p
                // Random number between 0-3
                // if x = 0, state: 7
                // else state: 8
                break;
            case 7: //-Turn Left 0
                    // Turn left and go to state 0
                //left, state: 0
                break;
            case 8: //-Flip 2 9 10
                    // p = 2. St1 = 9, St2 = 10
                    // x is a random number between 0-p
                // Random number between 0-2
                // if x = 0, state: 9
                // else state: 10
                break;
            case 9: //- Turn Right 0
                    // Turn right and go to state 0
                //right, state: 0
                break;
            case 10: //-Move 0 6
                    // Move forward and go to state 0, 3 if failed
                // if moved was sucsessful, state: 0
                // else failure, state: 6
                break;
            case 11: //-SeAd 12 14 Home
                    // Sense Ahead for -Home- [Search] is there antHill in front of me
                // Check the cell infront,
                // if there is home - move to cell, state: 12
                // else state: 14
                break;
            case 12: //-Move 13 11
                    // Move forward and go to state 13, 11 if faild
                // if moved was sucsessfyl, state: 13
                // else failure, state 11
                break;
            case 13: //-Drop 0
                     // Drop food and return to searching
                // Drop, State: 0
                break;
            case 14: //- Flip 3 15 16
                    // p = 3. St1 = 15, St2 = 16
                    // x is a random number between 0-p
                // Random number between 0-3
                // if x = 0, state: 15
                // else state: 16
                break;
            case 15: //-Turn Left 10
                    // Turn left and go to state 9
                //left, state: 11
                break;
            case 16: //-Flip 2 17 18
                    // p = 2. St1 = 12, St2 = 18
                    // x is a random number between 0-p
                // Random number between 0-2
                // if x = 0, state: 17
                // else state: 18
                break;
            case 17: //-Turn Right 11
                    // Turn right and go to state 11
                //right, state: 11
                break;
            case 18: //-Move 11 14
                // Move forward and go to state 11, 14 if faild
                // if moved was sucsessfyl, state: 11
                // else failure, state 14
        }
    }
    
    public int State(Ant a)
    {
        return a.state();
    }
    
    public Colour Colour(Ant a)
    {
        return a.colour();
    }
    
    public int resting(Ant a)
    {
        return a.resting();
    }
    
    public int direction(Ant a)
    {
        return a.direction();
    }
    
    public boolean hasFood(Ant a)
    {
        return a.hasFood();
    }
    
    public void setState(int s)
    {
        a.setState(s);
    }
    
    public void setResting(int r)
    {
        a.setResting(r);
    }
    
    public void setDirection(int d)
    {
        if (d < 0 || d > 5)
        {
            a.setDirection(0);
            System.out.println("State was set to 0 as it was not between 0-5");
        }
        else
        {
            a.setDirection(d);
        }
    }
    
    public void setHasFood(boolean f)
    {
        a.setHasFood(f);
    }
}
