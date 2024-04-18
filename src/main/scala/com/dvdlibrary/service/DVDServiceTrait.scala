package com.dvdlibrary.service

import com.dvdlibrary.dto.DVD

trait DVDServiceTrait {
  def addDVD(dvdTitle: String, dvd: DVD): DVD

  def getAllDVDs: List[DVD]

  def getDVD(dvdTitle: String): DVD

  def removeDVD(dvdTitle: String): DVD

  def editDVD(dvdName: String, updatedDVD: DVD): DVD
}
