package com.dvdlibrary.controller

import com.dvdlibrary.dto.DVD
import com.dvdlibrary.service.DVDService
import com.dvdlibrary.ui.DVDView

import scala.util.{Failure, Success, Try}

class DVDController(DVDView: DVDView, DVDService: DVDService) {
  def run(): Unit = {
    var running = true
    while (running) {
      val menuSelection = getMenuSelection
      menuSelection match {
        case 1 => displayAllDVDs()
        case 2 => addDVD()
        case 3 => viewDVD()
        case 4 => removeDVD()
        case 5 => totalDVDs()
        case 6 => running = false
        case _ => DVDView.displayErrorSelection()
      }
    }
    DVDView.displayExit()
  }

  private def getMenuSelection: Int = {
    DVDView.DisplayWelcomeBanner()
    DVDView.DisplayMenu()
  }

  private def displayAllDVDs(): Unit = {

    DVDView.displayDisplayAllBanner()
    val result: Try[List[DVD]] = Try(DVDService.getAllDVDs)

    result match {
      case Success(dvdList) => DVDView.DisplayDVDList(dvdList)
      case Failure(exception) => throw new Exception("Error finding list", exception)
    }
  }

  private def addDVD(): Unit = {
    DVDView.displayCreateDVDBanner()
    val result: Try[DVD] = Try(DVDView.getMovieInfo())

    result match{
      case Success(newDVD) =>
        DVDService.addDVD(newDVD.title, newDVD)
        DVDView.displayCreateSuccessBanner()
      case Failure(exception) => throw new Exception("Error adding DVD", exception)
    }
  }

  private def viewDVD(): Unit = {
    val DVDTitle = DVDView.getTitle()
    val DVDs = DVDService.getDVD(DVDTitle)
    DVDView.getMovieInfo()
  }

  private def removeDVD(): Unit = {
    val title = DVDView.getTitle()
    val removedDVD = DVDService.removeDVD(title)

    if (removedDVD != null) {
      DVDView.displaySuccess(s"DVD '$title' removed successfully.")
    } else {
      DVDView.displayFailure(s"Failed to remove DVD '$title'.")
    }
  }

  private def totalDVDs(): Unit = {
    val total = DVDService.getAllDVDs.length
    DVDView.displayTotalDVDs(total)
  }
}
