package com.dvdlibrary.service

import com.dvdlibrary.dao.{DVDDAO, DVDDAOImpl}
import com.dvdlibrary.dto.DVD
import org.springframework.stereotype.Service

@Service
class DVDService (dao : DVDDAOImpl) extends DVDServiceTrait {

  override def addDVD(dvdTitle: String, dvd: DVD): DVD = {
    val addedDvd = dao.addDvd(dvdTitle, dvd)
    addedDvd
  }

  override def getAllDVDs: List[DVD] = {
    dao.getAllDvds
  }

  override def getDVD(dvdTitle: String): DVD = {
    dao.getDvd(dvdTitle)
  }

  override def removeDVD(dvdTitle: String): DVD = {
    val dvdToRemove = dao.removeDvd(dvdTitle)
    dvdToRemove
  }
}