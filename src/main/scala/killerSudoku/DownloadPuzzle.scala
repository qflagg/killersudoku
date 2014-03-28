package killerSudoku

import scala.Array.canBuildFrom
import scalaj.http.Http

object DownloadPuzzle {
  def getPuzzle(url: String) = {
    val calcudokuHTML = Http(url).asString
    val puzzleRegex = """(?<=fr3i3\(0\);\n)[\s\S]+?(?=gfqk6\(1\))""".r
    val cellRegex = """(?<=(c_g8\(\);\n))[\S\s]*?(?=c_g8\(\);\n|$)""".r
    val sumRegex = """(?<=o1_\()[\d|+]*(?=\);)""".r
    val cageRegex = """(?<=wwe_\()[\d|-]*""".r
    val puzzle = puzzleRegex.findAllIn(calcudokuHTML)
      .flatMap(puzzle => cellRegex.findAllIn(puzzle))
    for (p <- puzzle) yield {
      (sumRegex.findFirstIn(p).get
        .split("\\+")
        .map(_.toInt)
        .reduce(_ + _),
        cageRegex.findAllIn(p)
        .map(_.split("-")
          .map(_.toInt)
          .reduce(_ - _)))
    }
  }
}