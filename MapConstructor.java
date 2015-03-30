public class MapConstructor {
  private Cell[][] cell;
  public Cell[][] gen(int rows, int cols) {
    cell = new Cell[rows][];
    for(int i = 0; i < cols; i++){
      cell[i] = new Cell[cols];
    }
    // do something here
    return cell;
  }
  
  public Position[] square(Position topLeft, int shape){
    int x = topLeft.x, y = topLeft.y;
    int option = y%2 + shape*2;
    Position[] posList = new Position[25];
    
    switch(option){
      case 0: //y is even and leaning to left
        for(int i = 0; i < 5; i++){
          posList[i] = new Position(x+i,y);
          posList[i+5] = new Position(x+i+1,y+1);
          posList[i+10] = new Position(x+i+1,y+2);
          posList[i+15] = new Position(x+i+2,y+3);
          posList[i+20] = new Position(x+i+2,y+4);
        }
      case 1: //y is odd and leaning to left
        for(int i = 0; i < 5; i++){
          posList[i] = new Position(x+i,y);
          posList[i+5] = new Position(x+i,y+1);
          posList[i+10] = new Position(x+i+1,y+2);
          posList[i+15] = new Position(x+i+1,y+3);
          posList[i+20] = new Position(x+i+2,y+4);
        }
      case 2: //y is even and leaning to right
        for(int i = 0; i < 5; i++){
          posList[i] = new Position(x+i,y);
          posList[i+5] = new Position(x+i,y+1);
          posList[i+10] = new Position(x+i-1,y+2);
          posList[i+15] = new Position(x+i-1,y+3);
          posList[i+20] = new Position(x+i-2,y+4);
        }
      case 3: //y is odd and leaning to right
        for(int i = 0; i < 5; i++){
          posList[i] = new Position(x+i,y);
          posList[i+5] = new Position(x+i-1,y+1);
          posList[i+10] = new Position(x+i-1,y+2);
          posList[i+15] = new Position(x+i-2,y+3);
          posList[i+20] = new Position(x+i-2,y+4);
        }
      default:
        //wrong shape
    }
    return posList;
  }
  
  public Position[] hexagon(Position centre){
    
  }
}
