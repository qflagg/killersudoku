package killerSudoku

object KillerSudokuSolver {
  def main(args: Array[String]): Unit = {
    val puzzleURL = "http://www.calcudoku.org/killersudoku/en/2014-03-29/9/3"
    val puzzle = DownloadPuzzle.getPuzzle(puzzleURL)
    println(puzzle)
  }
}