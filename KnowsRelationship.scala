package KillerSudokuSolver
import org.neo4j.graphdb.RelationshipType

object KnowsRelationship extends RelationshipType {
  def name:String="KNOWS"
}