package killerSudoku

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class PuzzleTest extends FlatSpec with ShouldMatchers {
  /*puzzle:
   * _______
   * |A|A|B|
   * |A|B|B|
   * |C|C|C|
   */

  val cells = scala.collection.mutable.Set(Cell(1),
    Cell(2),
    Cell(3),
    Cell(4),
    Cell(5),
    Cell(6),
    Cell(7),
    Cell(8),
    Cell(9))

  val cages = scala.collection.mutable.Set(SumCage(6, Seq(Set(1, 2, 3)), cells.filter(p => p.cellNumber == 1 || p.cellNumber == 2 || p.cellNumber == 4)),
    SumCage(15, Seq(Set(4, 5, 6)), cells.filter(p => p.cellNumber == 3 || p.cellNumber == 5 || p.cellNumber == 6)),
    SumCage(24, Seq(Set(7, 8, 9)), cells.filter(p => p.cellNumber == 7 || p.cellNumber == 8 || p.cellNumber == 9)))

  val possibilityCages = scala.collection.mutable.Set(PossibilityCage(scala.collection.mutable.Set(1, 2, 4), cells.filter(p => p.cellNumber == 1 || p.cellNumber == 2 || p.cellNumber == 3)),
    PossibilityCage(scala.collection.mutable.Set(1, 3, 7), cells.filter(p => p.cellNumber == 1 || p.cellNumber == 4 || p.cellNumber == 7)))

  "Puzzle Copies" should "contain the same cells as the original" in {
    val puzzleA = new Puzzle(cells.toSet, cages, possibilityCages, scala.collection.mutable.Set(1, 2, 3, 4, 5, 6, 7, 8, 9))
    val puzzleB = puzzleA.copy
    (for (i <- (1 to 9)) yield {
      puzzleA.getCell(i) == puzzleB.getCell(i)
    }).reduce(_ && _)
  }

  "Puzzle Copies" should "contain the same sumCages as the original" in {
    val puzzleA = new Puzzle(cells.toSet, cages, possibilityCages, scala.collection.mutable.Set(1, 2, 3, 4, 5, 6, 7, 8, 9))
    val puzzleB = puzzleA.copy
    (for (i <- (1 to 9)) yield {
      puzzleA.getSumCages(puzzleA.getCell(i)) == puzzleB.getSumCages(puzzleB.getCell(i))
    }).reduce(_ && _)
  }

  "Puzzle Copies" should "contain the same possibilityCages as the original" in {
    val puzzleA = new Puzzle(cells.toSet, cages, possibilityCages, scala.collection.mutable.Set(1, 2, 3, 4, 5, 6, 7, 8, 9))
    val puzzleB = puzzleA.copy
    (for (i <- (1 to 9)) yield {
      puzzleA.getPossibilityCages(puzzleA.getCell(i)) == puzzleB.getPossibilityCages(puzzleB.getCell(i))
    }).reduce(_ && _)
  }

  "Modifying Puzzle Copies cells" should "not effect the original" in {
    val puzzleA = new Puzzle(cells.toSet, cages, possibilityCages, scala.collection.mutable.Set(1, 2, 3, 4, 5, 6, 7, 8, 9))
    val puzzleB = puzzleA.copy
    puzzleA.getCell(1).value = Some(1)
    puzzleA.getCell(1).value == None
  }

}