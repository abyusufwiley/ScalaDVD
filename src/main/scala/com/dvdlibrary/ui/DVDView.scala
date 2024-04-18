package com.dvdlibrary.ui

import com.dvdlibrary.dto.DVD

import scala.io.StdIn

class DVDView(var io: UserIO) {
  def DisplayWelcomeBanner(): Unit = {
    io.print("Welcome to the DVD APP")
  }

  def DisplayMenu(): Int = {
    io.print("")
    io.print("Main Menu")
    io.print("1. List Movies")
    io.print("2. Create DVD")
    io.print("3. Find DVD")
    io.print("4. Remove a DVD")
    io.print("5. View number of DVD")
    io.print("6. Exit")

    io.readInt("Please select an option:", 1, 6)
  }

  def getMovieInfo(): DVD = {
    val title = io.readString("Please enter the movie title")
    val MPAARating = io.readString("Please MPAA Rating")
    val ReleaseDate = io.readString("Please enter release date")
    val DirectorName = io.readString("Please enter the directors name")
    val Studio = io.readString("Enter the studio")
    val UserRating = io.readString("Enter the User rating")
    DVD(title, MPAARating, ReleaseDate, DirectorName, Studio, UserRating)
  }

  def DiplayMovie(movie: DVD): Unit = {
    if (movie != null) {
      io.print(movie.title)
      io.print(movie.mpaaRating)
      io.print(movie.releaseDate)
      io.print(movie.directorName)
      io.print("")
    }
  }

  def DisplayDVDList(DVDList: List[DVD]): Unit = {
    for (currentMovie <- DVDList) {
      val DVDInfo = String.format("#%s : %s %s", currentMovie.title, currentMovie.releaseDate, currentMovie.directorName, currentMovie.mpaaRating)
      io.print(DVDInfo)
    }
    io.readString("Please hit enter to continue.")
  }

  def displayCreateDVDBanner(): Unit = {
    io.print("=== Create new entry ===")
  }

  def displayCreateSuccessBanner(): Unit = {
    io.readString("DVD successfully created.  Please hit enter to continue")
  }

  def displayDisplayAllBanner(): Unit = {
    io.print("=== Display All movies ===")
  }

  def displayDisplayDVDBanner(): Unit = {
    io.print("=== Display movie ===")
  }

  def displayRemoveDVDBanner(): Unit = {
    io.print("=== Remove Address ===")
  }

  def displayErrorSelection(): Unit = {
    io.print("=== Please Select a valid option ===")
  }

  def displayExit(): Unit = {
    io.print("=== GOODBYE ===")
  }

  def getTitle(): String = {
    io.readString("Please enter the DVD title: ")
  }

  def displayFailure(message: String): Unit = {
    io.print(s"Failure: $message")
  }

  def displaySuccess(message: String): Unit = {
    io.print(s"Success: $message")
  }

  def displayTotalDVDs(total: Int): Unit = {
    io.print(s"Total number of DVDs: $total")
  }
}
