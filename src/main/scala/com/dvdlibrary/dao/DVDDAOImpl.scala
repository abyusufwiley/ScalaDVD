package com.dvdlibrary.dao

import com.dvdlibrary.dto.DVD
import org.springframework.jdbc.core.{JdbcTemplate, RowMapper}
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`


@Repository
class DVDDAOImpl(var jdbcTemplate: JdbcTemplate) extends DVDDAO {

  override def addDvd(dvdTitle: String, dvd: DVD): DVD = {
    val sql = "INSERT INTO DVDs.dvd (title, releaseDate, mpaaRating, directorName, studio, userRating) VALUES (?, ?, ?, ?, ?, ?)"
    jdbcTemplate.update(sql, dvd.title, dvd.releaseDate, dvd.mpaaRating, dvd.directorName, dvd.studio, dvd.userRatingOrNote)
    dvd
  }

  override def getAllDvds: List[DVD] = {
    val sql = "SELECT * FROM DVDs.dvd"
    jdbcTemplate.query(sql, mapResultSetToDVDRowMapper).toList
  }

  override def getDvd(dvdTitle: String): DVD = {
    val sql = "SELECT * FROM DVDs.dvd WHERE title = ?"
    jdbcTemplate.queryForObject(sql, classOf[DVD], dvdTitle)
  }

  override def removeDvd(dvdTitle: String): DVD = {
    val sql = "DELETE FROM DVDs.dvd WHERE title = ?"
    val dvd = getDvd(dvdTitle)
    jdbcTemplate.update(sql, dvdTitle)
    dvd
  }

  override def editDVD(dvdTitle: String, updatedDVD: DVD): DVD = {
    val sql = "UPDATE DVDs.dvd SET title = ?, releaseDate = ?, mpaaRating = ?, directorName = ?, studio = ?, userRating = ? WHERE title = ?"
    jdbcTemplate.update(sql, updatedDVD.title, updatedDVD.releaseDate, updatedDVD.mpaaRating, updatedDVD.directorName, updatedDVD.studio, updatedDVD.userRatingOrNote, dvdTitle)
    updatedDVD
  }

  override def searchDVDs(dvdTitle: String): List[DVD] = {
    val sql = "SELECT * FROM DVDs.dvd WHERE title LIKE ?"
    jdbcTemplate.query(sql, mapResultSetToDVDRowMapper, "%" + dvdTitle + "%").toList
  }

  private val mapResultSetToDVDRowMapper = new RowMapper[DVD] {
    override def mapRow(rs: ResultSet, rowNum: Int): DVD = {
      DVD(
        title = rs.getString("title"),
        releaseDate = rs.getString("releaseDate"),
        mpaaRating = rs.getString("mpaaRating"),
        directorName = rs.getString("directorName"),
        studio = rs.getString("studio"),
        userRatingOrNote = rs.getString("userRating")
      )
    }
  }
}





