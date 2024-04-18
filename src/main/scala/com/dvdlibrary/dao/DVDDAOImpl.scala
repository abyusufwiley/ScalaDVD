package com.dvdlibrary.dao

import com.dvdlibrary.dto.DVD

import scala.collection.mutable
import java.io._
import java.io.IOException
import scala.collection.mutable.ListBuffer
import scala.io.Source

class DVDDAOImpl(var fileName: String) extends DVDDAO {
  private val dvdList: mutable.Map[String, DVD] = mutable.Map()
  private val DVD_FILE: String = "dvd.txt"
  private val DELIMITER: String = "::"

  override def addDvd(dvdTitle: String, dvd: DVD): DVD = {
    loadDvds()
    val newDvd: DVD = dvdList.put(dvdTitle, dvd).orNull
    writeDvds()
    newDvd
  }

  override def getAllDvds: List[DVD] = {
    loadDvds()
    dvdList.values.toList
  }

  override def getDvd(dvdTitle: String): DVD = {
    loadDvds()
    dvdList.getOrElse(dvdTitle, throw new NoSuchElementException(s"No DVD found with title: $dvdTitle"))
  }

  override def removeDvd(dvdTitle: String): DVD = {
    loadDvds()
    val removedDvd: Option[DVD] = dvdList.remove(dvdTitle)
    writeDvds()
    removedDvd.getOrElse(throw new NoSuchElementException(s"No DVD found with title: $dvdTitle"))
  }

  override def editDVD(dvdName: String, updatedDVD: DVD): DVD = {
    loadDvds()
    for (dvd <- dvdList.values) {
      if (dvd.title == dvdName) {
        val newTitle = if (updatedDVD.title.nonEmpty) updatedDVD.title else dvd.title
        val newDirectorsName = if (updatedDVD.directorName.nonEmpty) updatedDVD.directorName else dvd.directorName
        val newStudio = if (updatedDVD.studio.nonEmpty) updatedDVD.studio else dvd.studio
        val newMpaaRating = if (updatedDVD.mpaaRating.nonEmpty) updatedDVD.mpaaRating else dvd.mpaaRating
        val newUserRating = if (updatedDVD.userRatingOrNote.nonEmpty) updatedDVD.userRatingOrNote else dvd.userRatingOrNote
        val newReleaseDate = if (updatedDVD.releaseDate.nonEmpty) updatedDVD.releaseDate else dvd.releaseDate

        // Update the DVD with non-empty fields
        dvdList(dvdName) = DVD(newTitle, newReleaseDate, newMpaaRating, newDirectorsName, newStudio, newUserRating)
      }
    }
    writeDvds()
    updatedDVD
  }


  override def searchDVDs(dvdName: String): List[DVD] = {
    loadDvds()
    val results: ListBuffer[DVD] = ListBuffer()
    for (dvd <- dvdList.values) {
      if (dvd.title.contains(dvdName))
        results += dvd
    }
    results.toList
  }

  private def marShallData(dvd: DVD): String = {
    var dvdAsText: String = ""
    dvdAsText += dvd.title + DELIMITER
    dvdAsText += dvd.studio + DELIMITER
    dvdAsText += dvd.mpaaRating + DELIMITER
    dvdAsText += dvd.releaseDate + DELIMITER
    dvdAsText += dvd.directorName + DELIMITER
    dvdAsText += dvd.userRatingOrNote + DELIMITER
    dvdAsText
  }

  private def unmarshallData(dvdAsText: String): DVD = {
    val fields = dvdAsText.split(DELIMITER)
    val title = fields(0)
    val studio = fields(1)
    val mpaaRating = fields(2)
    val releaseDate = fields(3)
    val directorName = fields(4)
    val userRatingOrNote = fields(5)
    DVD(title = title, releaseDate = releaseDate, mpaaRating = mpaaRating, directorName = directorName, studio = studio, userRatingOrNote = userRatingOrNote)

  }

   private def loadDvds(): Unit = {
    try {
      val file = Source.fromFile(DVD_FILE)
      for (line <- file.getLines()) {
        val dvd = unmarshallData(line)
        dvdList += dvd.title -> dvd
      }
    } catch {
      case IOException => throw new IOException("Could not load dvds from file")
    }
  }

   private def writeDvds(): Unit = {
    var out: PrintWriter = null
    try {
      out = new PrintWriter(new FileWriter(DVD_FILE))
    } catch {
      case IOException => throw new IOException("Could not write dvds from file")
    }
    for (dvd <- dvdList.values) {
      val dvdAsText = marShallData(dvd)
      out.println(dvdAsText)
      out.flush()
    }
    out.close()
  }
}





