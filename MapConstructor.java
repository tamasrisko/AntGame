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
  
}
