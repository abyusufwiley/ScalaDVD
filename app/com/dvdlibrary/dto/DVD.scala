package com.dvdlibrary.dto

import play.api.libs.json.{Json, Reads, Writes}

case class DVD(title: String,
               releaseDate: String,
               mpaaRating: String,
               directorName: String,
               studio: String,
               userRatingOrNote: String)

object DVDReads {
  implicit val dvdReads: Reads[DVD] = Json.reads[DVD]
}

object DVDWrites {
  implicit val dvdWrites: Writes[DVD] = Json.writes[DVD]
}
