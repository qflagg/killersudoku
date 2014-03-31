package killerSudoku

import scala.collection.mutable.ListBuffer

case class Cell(cellNumber: Int, var value: Option[Int] = None)
case class SumCage(var sum: Int, var possibleCombinations: Seq[Set[Int]], cells: scala.collection.mutable.Set[Cell])
case class PossibilityCage(possibleValues: scala.collection.mutable.Set[Int], cells: scala.collection.mutable.Set[Cell])

class Puzzle(cells: Set[Cell], sumCages: scala.collection.mutable.Set[SumCage], possibilityCages: scala.collection.mutable.Set[PossibilityCage], activeCells: scala.collection.mutable.Set[Int]) {
  def getCell(cellNumber: Int): Cell = {
    cells.filter(_.cellNumber == cellNumber).head
  }

  def getSumCages(cell: Cell): Set[SumCage] = {
    sumCages.filter(_.cells.contains(cell)).toSet
  }

  def getPossibilityCages(cell: Cell): Set[PossibilityCage] = {
    possibilityCages.filter(_.cells.contains(cell)).toSet
  }

  def getActiveCells(): Set[Int] = {
    activeCells.toSet
  }

  def solveCell(cell: Cell, value: Int): Unit = {
    activeCells.remove(cell.cellNumber)
    cell.value=Some(value)
    for(sc<-sumCages.filter(_.cells.contains(cell))){
      sc.cells-=cell
      sc.sum-=value
    }
    for(pc<-possibilityCages.filter(_.cells.contains(cell))){
      pc.cells-=cell
      pc.possibleValues-=value
    }
  }

  def copy(): Puzzle = {
    new Puzzle(cells, sumCages, possibilityCages, activeCells)
  }
}