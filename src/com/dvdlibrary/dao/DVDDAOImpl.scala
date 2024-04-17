package com.dvdlibrary.dao
import com.dvdlibrary.dto.DVD

import scala.collection.mutable

class DVDDAOImpl extends DVDDAO {
  private val dvdList: mutable.Map[String, DVD] = mutable.Map()
  private val DVD_FILE: String = "dvd.txt"
  private val DELIMITER: String = "::"
  override def addDvd(dvdTitle: String, dvd: DVD): DVD = {
    loadDvd()
    val newDvd: DVD = dvdList.put(dvdTitle, dvd).orNull
    writeDvd()
    newDvd
}

  override def getAllDvds: List[DVD] = {
    loadDvd()
    dvdList.values.toList
  }

  override def getDvd(dvdTitle: String): DVD =  {
    loadDvd()
    dvdList.getOrElse(dvdTitle, throw new NoSuchElementException(s"No DVD found with title: $dvdTitle"))
  }

  override def removeDvd(dvdTitle: String): DVD = {
    loadDvd()
    val removedDvd: Option[DVD] = dvdList.remove(dvdTitle)
    writeDvd()
    removedDvd.getOrElse(throw new NoSuchElementException(s"No DVD found with title: $dvdTitle"))
  }

  private def loadDvd(): Unit = {
    // Implementation to load DVDs from storage
  }

  private def writeDvd(): Unit = {
    // Implementation to write DVDs to storage
  }
}
