
package antgame;

import java.util.ArrayList;

public class Ant{
	// variables
	private Position p;	// position
	public int dir;
	public Colour c;	// colour
	public int restCount;
        public boolean resting;
	public boolean dead;
	public int s;		// state
	public boolean hasFood;
	public Instruction instruction;

	/**
	 * Constructs an Ant with initial position, facing 
	 * direction, colour and state.
	 *
	 * @param  p	initial position
	 * @param  dir	initial facing direction
	 * @param  c 	colour
	 * @param  s 	state
	 */
	public Ant(Position p, int dir, Colour c, int s){
		this(p,dir,c,0,false,s);
	}

	/**
	 * Constructs an Ant with initial position, facing 
	 * direction, colour, number of turns until next
	 * move, whether it is dead and state.
	 *
	 * @param  p			position of the ant
	 * @param  dir			direction
	 * @param  c 			colour
	 * @param  restCount	number of turns to rest until next move
	 * @param  dead			true if this ant is dead, false if otherwise
	 * @param  s 			state
	 */
	public Ant(Position p, int dir, Colour c, int restCount, boolean dead, int s){
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
	 * @param  lr	left or right
	 * @param  dir  current direction
	 * @return the direction after rotation
	 */
	public int turn(LeftOrRight lr, int d){
		return (d + (lr == LeftOrRight.LEFT ? 5 : 1)) % 6;
	}

	/**
	 * Returns the colour of foe of ants with colour c.
	 *
	 * @param  c 	colour
	 * @return BLACK if c is RED, OTHERWISE RED.
	 */
	public Colour otherColour(Colour c){
		return (c == Colour.RED ? Colour.BLACK : Colour.RED);
	}

	/**
	 * Calculates if the current cell and three cells in front 
	 * of the ant has a sense. p is the ants current position, 
	 * d the directions of the three cells in front.
	 * (need revision)
	 *
	 * @param  p	position
	 * @param  d 	direction
	 * @param  sd 	sense direction
	 * @return the Arraylist of positions (x,y) of the cell if that has a sense, 
	 * or (-1,-1) if no cell has a sense.
	 */
	public ArrayList senseCell(SenseDirection sd){
                ArrayList<Position> list  = new ArrayList<>();
                list.add(p);
                for(int i=(dir-1);i<(dir+3);i++){
                    list.add(adjacentCell(i));
                }
                return list;
	}
        
        
        public int state(){
            return s;
        }
        
        public Colour colour(){
		return c;
	}

	public boolean resting(){
		return resting;
	}

	public int direction(){
		return dir;
	}

	public boolean hasFood(Ant a){
		return hasFood;
            }
        
        public void setState(int state)
        {
           s = state;
        }
    
        public void setResting(int r)
        {
            restCount+=r;
        }
    
        public void setDirection(int d)
        {
            dir = d;
        }
    
        public void setHasFood(boolean f)
        {
            hasFood = f;
        }

	/**
	 * Returns number of ants with colour c which are adjacent 
	 * (in any direction) to cell at position p.
	 *
	 * @param  p	position
	 * @param  c 	colour of ant
	 * @return number of adjacent ants of the given colour.
	 */
	public int adjAnts(Position p, Colour c){
		int numAnts = 0;
		return numAnts;
	}

	/**
	 * Checks if an ant at a position is surrounded.
	 *
	 * @param  p	position
	 * @return true if it is surrounded, false if otherwise.
	 */
	public boolean checkForSurroundedAntAt(Position p){
		return false;
	}
	
	/**
	 * Checks if an ant at a position is surrounded. [depilcated?]
	 *
	 * @param  p	position
	 * @return true if it is surrounded, false if otherwise.
	 * @see checkForSurroundedAntAt
	 */
	public boolean checkForSurroundedAnts(Ant a){
            int surroundingAnt =0 ;
            for (int i=0;i<6;i++){
                if (getCell(a.adjacentCell(i)).hasAnt()){
                    surroundingAnt++;
                }
            }
            if (surroundingAnt>=5){
                return true;
            }else{
		return false;
            }
	}
        
        public Position adjacentCell(int d){
		int x = p.X;
		int y = p.Y;
		Position newPos = new Position(-1,-1);

		switch(d){
			case 0: 
				newPos.X = x+1;
                                newPos.Y = y;
				break;
			case 1:	
				if(y % 2 == 0){ 
                                    newPos.X = x;
                                    newPos.Y = y+1;
                                }else {
                                    newPos.X = x+1;
                                    newPos.Y = y+1;
                                }
				break;
			case 2:	
				if(y % 2 == 0) {
                                    newPos.X = x-1;
                                    newPos.Y = y+1;
                                }else {
                                    newPos.X = x;
                                    newPos.Y = y+1;
                                }
				break;
			case 3: 
                                newPos.X = x-1;
                                newPos.Y = y;
				break;
			case 4:	
				if(y % 2 == 0){
                                    newPos.X = x-1;
                                    newPos.Y = y-1;
                                }else{ 
                                    newPos.X = x;
                                    newPos.Y = y-1;
                                }
				break;
			case 5:	
                                if(y %  2== 0) {
                                    newPos.X = x;
                                    newPos.Y = y-1;
                                }else {
                                    newPos.X = x+1;
                                    newPos.Y = y-1;
                                }
                                break;
		}

		return newPos;
	}
}
