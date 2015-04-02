package antgame;

// i guess it should be integrated with the world class.  

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
  
  // shape: 0 for leaning left, 1 for leaning right
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
    int x = centre.x, y = centre.y;
    Position[] posList = new Position[127];
    if (y%2 == 0){
      int index = 0;
      for(int i = 0; i < 7; i++)
        posList[index++] = new Position(x-3+i, y-6);
      for(int i = 0; i < 8; i++)
        posList[index++] = new Position(x-3+i, y-5);
      for(int i = 0; i < 9; i++)
        posList[index++] = new Position(x-4+i, y-4);
      for(int i = 0; i < 10; i++)
        posList[index++] = new Position(x-4+i, y-3);
      for(int i = 0; i < 11; i++)
        posList[index++] = new Position(x-5+i, y-2);
      for(int i = 0; i < 12; i++)
        posList[index++] = new Position(x-5+i, y-1);
      for(int i = 0; i < 13; i++)
        posList[index++] = new Position(x-6+i, y);
      for(int i = 0; i < 12; i++)
        posList[index++] = new Position(x-5+i, y+1);
      for(int i = 0; i < 11; i++)
        posList[index++] = new Position(x-5+i, y+2);
      for(int i = 0; i < 10; i++)
        posList[index++] = new Position(x-4+i, y+3);
      for(int i = 0; i < 9; i++)
        posList[index++] = new Position(x-4+i, y+4);
      for(int i = 0; i < 8; i++)
        posList[index++] = new Position(x-3+i, y+5);
      for(int i = 0; i < 7; i++)
        posList[index++] = new Position(x-3+i, y+6);
    }
    else{
      int index = 0;
      for(int i = 0; i < 7; i++)
        posList[index++] = new Position(x-3+i, y-6);
      for(int i = 0; i < 8; i++)
        posList[index++] = new Position(x-4+i, y-5);
      for(int i = 0; i < 9; i++)
        posList[index++] = new Position(x-4+i, y-4);
      for(int i = 0; i < 10; i++)
        posList[index++] = new Position(x-5+i, y-3);
      for(int i = 0; i < 11; i++)
        posList[index++] = new Position(x-5+i, y-2);
      for(int i = 0; i < 12; i++)
        posList[index++] = new Position(x-6+i, y-1);
      for(int i = 0; i < 13; i++)
        posList[index++] = new Position(x-6+i, y);
      for(int i = 0; i < 12; i++)
        posList[index++] = new Position(x-6+i, y+1);
      for(int i = 0; i < 11; i++)
        posList[index++] = new Position(x-5+i, y+2);
      for(int i = 0; i < 10; i++)
        posList[index++] = new Position(x-5+i, y+3);
      for(int i = 0; i < 9; i++)
        posList[index++] = new Position(x-4+i, y+4);
      for(int i = 0; i < 8; i++)
        posList[index++] = new Position(x-4+i, y+5);
      for(int i = 0; i < 7; i++)
        posList[index++] = new Position(x-3+i, y+6);
    }
    return posList;
  }
}
