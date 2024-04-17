package com.dvdlibrary.dao

import com.dvdlibrary.dto.DVD

trait DVDDAO {
  def addDvd(dvdName: String, dvd: DVD): DVD

  def getAllDvds: List[DVD]

  def getDvd(dvdName: String): DVD

  def removeDvd(dvdName: String): DVD

}
