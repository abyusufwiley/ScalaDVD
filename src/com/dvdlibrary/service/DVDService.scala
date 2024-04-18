package com.dvdlibrary.service

import com.dvdlibrary.dao.{DVDDAO, DVDDAOImpl}
import com.dvdlibrary.dto.DVD

class DVDService (dao : DVDDAOImpl) extends DVDServiceTrait {


  override def addDVD(dvdTitle: String, dvd: DVD): DVD = {
    dao.loadDvds()
    val addedDvd = dao.addDvd(dvdTitle, dvd)
    dao.writeDvds()
    addedDvd
  }

  override def getAllDVDs: List[DVD] = {
    dao.loadDvds()
    dao.getAllDvds
  }

  override def getDVD(dvdTitle: String): DVD = {
    dao.loadDvds()
    dao.getDvd(dvdTitle)
  }

  override def removeDVD(dvdTitle: String): DVD = {
    dao.loadDvds()
    val dvdToRemove = dao.removeDvd(dvdTitle)
    dao.writeDvds()
    dvdToRemove
  }
}