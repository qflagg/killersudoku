package pingpong.actor.example.db.relationships

import org.neo4j.graphdb.RelationshipType

object KnowsRelationship extends RelationshipType{
  def name:String="KNOWS"
}