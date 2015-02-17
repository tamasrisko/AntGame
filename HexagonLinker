/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

/**
 *
 * @author MichaelNg
 */
public class HexagonLinker {
    protected int x;
    protected int y;
    
    public HexagonLinker(int x, int y, int direction){
        adjacent_cell(x,y,direction);
    }
    
    private void adjacent_cell(int x, int y, int direction){
  switch (direction){
    case 0: this.x = x+1;
            this.y = y;
            break;
    case 1: if ((y%2) == 0) {this.x = x;
                             this.y = y+1;}
            else {this.x = x+1;
                  this.y = y+1;}
            break;
    case 2: if ((y%2) == 0) {this.x = x-1;
                             this.y = y+1;}
            else {this.x = x;
                  this.y = y+1;}
            break;
    case 3: this.x = x+1;
            this.y = y;
            break;
    case 4: if ((y%2) == 0) {this.x = x-1;
                             this.y = y-1;}
            else {this.x = x;
                  this.y = y-1;}
            break;
    case 5: if ((y%2) == 0) {this.x = x;
                             this.y = y-1;}
            else {this.x = x-1;
                  this.y = y-1;}
            break;
  }
  
    }
}
