package com.dvdlibrary.controller

import com.dvdlibrary.dto.DVD
import com.dvdlibrary.service.DVDService
import com.dvdlibrary.ui.DVDView
import com.softwaremill.macwire._
import org.springframework.web.bind.annotation._

@RestController
@CrossOrigin
@RequestMapping("/dvd")
class DVDController(dvdService: DVDService) {

  @GetMapping("/dvds")
  def getAllDVDs(): List[DVD] =
    dvdService.getAllDVDs


  @PostMapping("/add")
  def addDVD(@RequestBody dvd: DVD): DVD =
    dvdService.addDVD(dvd.title, dvd)

  @PutMapping("/edit/{dvdName}")
  def editDVD(@PathVariable dvdName: String, @RequestBody dvd: DVD): DVD =
    dvdService.editDVD(dvdName, dvd)

  @GetMapping("/{title}")
  def getDVD(@PathVariable title: String): DVD =
    dvdService.getDVD(title)

  @DeleteMapping("/{title}")
  def removeDVD(@PathVariable title: String): Unit =
    dvdService.removeDVD(title)

}


// auto wiring dependencies using macwire similar to using @autowired annotation in java
object ControllerWiring {
  lazy val dvdService: DVDService = wire[DVDService]
  lazy val dvdController: DVDController = wire[DVDController]
}
