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
        
    public AntBrain()
    {
        
        
    }
    
    /**
     * Retrieve the instruction for the ant to follow
     * @param c colour of ant attached to this brain
     * @param state current state of ant
     */
    public void getInstructions(Colour c, int state)    // return an instruction
    {
        
        switch (state)
        {
            case 0: //-SeAd 1 3 Food
                    // Sense Ahead for -food- [Search] is there food in front of me
                // Check the cell infront,
                // if there is food - move to cell, state: 1
                // else state: 3
                break;
            case 1: //-Move 2 0
                    // Move forward and go to state 2, 0 if failed
                // if moved was sucsessful, state: 2
                // else failure, state: 0
                
                // NEeeds to get the ants direction and move it foward
                break;
            case 2: //-PickUp 8 0
                    // Pick up food and jump to state 8, 0 if failed
                break;
            case 3: //-Flip 3 4 5
                    // p = 3. St1 = 4, St2 = 5
                    // x is a random number between 0-p
                // Random number between 0-3
                // if x = 0, state: 4
                // else state: 5
                break;
            case 4: //-Turn Left 0
                    // Turn left and go to state 0
                //left, state: 0
                break;
            case 5: //-Flip 2 6 7
                    // p = 2. St1 = 6, St2 = 7
                    // x is a random number between 0-p
                // Random number between 0-2
                // if x = 0, state: 6
                // else state: 7
                break;
            case 6: //- Turn Right 0
                    // Turn right and go to state 0
                //right, state: 0
                break;
            case 7: //-Move 0 3
                    // Move forward and go to state 0, 3 if failed
                // if moved was sucsessful, state: 0
                // else failure, state: 3
                break;
            case 8: //-SeAd 9 11 Home
                    // Sense Ahead for -Home- [Search] is there antHill in front of me
                // Check the cell infront,
                // if there is home - move to cell, state: 9
                // else state: 11
                break;
            case 9: //-Move 10 8
                    // Move forward and go to state 10, 8 if faild
                // if moved was sucsessfyl, state: 10
                // else failure, state 8
                break;
            case 10: //-Drop 0
                     // Drop food and return to searching
                // Drop, State: 0
                break;
            case 11: //- Flip 3 12 13
                    // p = 3. St1 = 12, St2 = 13
                    // x is a random number between 0-p
                // Random number between 0-3
                // if x = 0, state: 12
                // else state: 13
                break;
            case 12: //-Turn Left 8
                    // Turn left and go to state 8
                //left, state: 8
                break;
            case 13: //-Flip 2 14 15
                    // p = 2. St1 = 14, St2 = 15
                    // x is a random number between 0-p
                // Random number between 0-2
                // if x = 0, state: 14
                // else state: 15
                break;
            case 14: //-Turn Right 8
                    // Turn right and go to state 8
                //right, state: 8
                break;
            case 15: //-Move 8 11
                // Move forward and go to state 8, 11 if faild
                // if moved was sucsessfyl, state: 8
                // else failure, state 11
        }
    }    
}
