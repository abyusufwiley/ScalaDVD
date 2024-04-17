package com.dvdlibrary.dao

import com.dvdlibrary.dto.DVD

trait DVDDAO {
  def addDvd(dvdTitle: String, dvd: DVD): DVD

  def getAllDvds: List[DVD]

  def getDvd(dvdTitle: String): DVD

  def removeDvd(dvdTitle: String): DVD

}
