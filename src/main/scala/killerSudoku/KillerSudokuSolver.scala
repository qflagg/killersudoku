package killerSudoku

object KillerSudokuSolver {
  def main(args: Array[String]): Unit = {
    val puzzleURL = "http://www.calcudoku.org/killersudoku/en/2014-03-10/9/1"
    val puzzle = DownloadPuzzle.getPuzzle(puzzleURL)    
  }
}