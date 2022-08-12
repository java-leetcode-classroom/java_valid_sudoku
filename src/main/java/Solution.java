import java.util.HashMap;

public class Solution {
  public boolean isValidSudoku(char[][] board) {
    int ROWS = board.length;
    int COLS = board[0].length;
    boolean[][] rows = new boolean[ROWS][9];
    boolean[][] cols = new boolean[COLS][9];
    HashMap<String, HashMap<Character, Boolean>> masks = new HashMap<>();
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        if (board[row][col] == '.') {
          continue;
        }
        if (checkIsValid(board, row, col, rows, cols, masks) == false) {
          return false;
        }
      }
    }
    return true;
  }
  private boolean checkIsValid(char[][] board, int row, int col, boolean[][] rows, boolean[][] cols,
                               HashMap<String, HashMap<Character, Boolean>> masks) {
    char val = board[row][col];
    int maskRow = row/3, maskCol = col/3;
    String maskKey = String.valueOf(maskRow) + String.valueOf(maskCol);
    if (masks.containsKey(maskKey) && masks.get(maskKey).containsKey(val)) {
      return false;
    }
    if (rows[row][val-'1'] == true || cols[col][val-'1'] == true) {
      return false;
    }
    if (!masks.containsKey(maskKey)) {
      masks.put(maskKey, new HashMap<>());
    }
    masks.get(maskKey).put(val, true);
    rows[row][val-'1'] = true;
    cols[col][val-'1'] = true;
    return true;
  }
}
