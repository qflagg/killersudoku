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
    val puzzleSeq = (for (p <- puzzle) yield {
      (sumRegex.findFirstIn(p).get
        .split("\\+")
        .map(_.toInt)
        .reduce(_ + _),
        cageRegex.findAllIn(p)
        .map(_.split("-")
          .map(_.toInt)
          .reduce(_ - _)).toSeq.par.seq)
    }).toSeq.par.seq

//    val cellSeq = for (i <- 0 to 80) yield {
//      Cell(i, Set(1, 2, 3, 4, 5, 6, 7, 8, 9))
//    }
//
//    val sumCageSeq = for (s <- puzzleSeq) yield {
//      SumCage(s._1, List(1, 2, 3, 4, 5, 6, 7, 8, 9).combinations(s._2.length).toSeq.par.seq.map(_.toSet))
//    }
//
//    val possibilityCageSeq = for (i <- 1 to 27) yield {
//      PossibilityCage(scala.collection.mutable.Set(1, 2, 3, 4, 5, 6, 7, 8, 9))
//    }
    
    
    puzzleSeq
  }

}