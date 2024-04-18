package com.dvdlibrary.service

import com.dvdlibrary.dao.{DVDDAO, DVDDAOImpl, DVDPersistenceException}
import com.dvdlibrary.dto.DVD
import org.springframework.stereotype.Service

@Service
class DVDService(dao: DVDDAOImpl) extends DVDServiceTrait {
  @throws(classOf[DVDPersistenceException])
  override def addDVD(dvdTitle: String, dvd: DVD): DVD = {
    try {
      val addedDvd = dao.addDvd(dvdTitle, dvd)
      addedDvd
    } catch {
      case e: DVDPersistenceException =>
        throw new DVDPersistenceException(s"Failed to add DVD: ${e.getMessage}", e)
    }
  }

  @throws(classOf[DVDPersistenceException])
  override def getAllDVDs: List[DVD] = {
    try {
      dao.getAllDvds
    } catch {
      case e: DVDPersistenceException =>
        throw new DVDPersistenceException(s"Failed to retrieve all DVDs: ${e.getMessage}", e)
    }
  }

  @throws(classOf[DVDPersistenceException])
  override def getDVD(dvdTitle: String): DVD = {
    try {
      dao.getDvd(dvdTitle)
    } catch {
      case e: DVDPersistenceException =>
        throw new DVDPersistenceException(s"No DVD found with title: $dvdTitle: ${e.getMessage}", e)
    }
  }

  @throws(classOf[DVDPersistenceException])
  override def removeDVD(dvdTitle: String): DVD = {
    try {
      dao.removeDvd(dvdTitle)
    } catch {
      case e: DVDPersistenceException =>
        throw new DVDPersistenceException(s"Failed to remove DVD: $dvdTitle: ${e.getMessage}", e)
    }
  }

  @throws(classOf[DVDPersistenceException])
  override def editDVD(dvdTitle: String, updatedDVD: DVD): DVD =
    try {
      this.getDVD(dvdTitle)
      dao.editDVD(dvdTitle, updatedDVD)
    }
    catch {
      case e: DVDPersistenceException =>
        throw new DVDPersistenceException(s"No DVD found with title: $dvdTitle: ${e.getMessage}", e)

    }
}