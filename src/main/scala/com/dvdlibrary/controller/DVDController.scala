package com.dvdlibrary.controller

import com.dvdlibrary.dto.DVD
import com.dvdlibrary.service.DVDService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

@RestController
@CrossOrigin
@RequestMapping(Array("/dvd"))
class DVDController @Autowired() (dvdService: DVDService) {

  @GetMapping(Array("/dvds"))
  def getAllDVDs(): List[DVD] =
    dvdService.getAllDVDs


  @PostMapping(Array("/add"))
  def addDVD(@RequestBody dvd: DVD): DVD =
    dvdService.addDVD(dvd.title, dvd)

  @PutMapping(Array("/edit/{dvdName}"))
  def editDVD(@PathVariable dvdName: String, @RequestBody dvd: DVD): DVD =
    dvdService.editDVD(dvdName, dvd)

  @GetMapping(Array("/{title}"))
  def getDVD(@PathVariable title: String): DVD =
    dvdService.getDVD(title)

  @DeleteMapping(Array("/{title}"))
  def removeDVD(@PathVariable title: String): Unit =
    dvdService.removeDVD(title)

}

