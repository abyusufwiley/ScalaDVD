package com.dvdlibrary.controller
import com.dvdlibrary.dto.DVD
import com.dvdlibrary.service.DVDService

import javax.inject.Inject
import play.api.mvc._
import com.dvdlibrary.dto.DVDReads._
import com.dvdlibrary.dto.DVDWrites._
import play.api.libs.json.Json


class DVDController @Inject()(dvdService: DVDService, cc: ControllerComponents) extends AbstractController(cc) {

  def getAllDVDs: Action[AnyContent] = Action {
    print("we are here")
    val dvds = dvdService.getAllDVDs
    Ok(Json.toJson(dvds))
  }

  def addDVD: Action[DVD] = Action(parse.json[DVD]) { request =>
    val dvd = request.body
    dvdService.addDVD(dvd.title, dvd)
    Created
  }

  def editDVD(title: String): Action[DVD] = Action(parse.json[DVD]) { request =>
    val dvd = request.body
    dvdService.editDVD(title, dvd)
    Ok
  }

  def getDVD(title: String): Action[AnyContent] = Action {
    val dvd = dvdService.getDVD(title)
    Ok(Json.toJson(dvd))
  }

  def removeDVD(title: String): Action[AnyContent] = Action {
    dvdService.removeDVD(title)
    NoContent
  }
}
