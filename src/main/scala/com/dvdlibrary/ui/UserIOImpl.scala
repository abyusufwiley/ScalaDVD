package com.dvdlibrary.ui

import scala.io.StdIn
import scala.util.{Try, Failure, Success}

class UserIOImpl extends UserIO {

  private def safelyRead[T](prompt: String, parser: String => T): T = {
    var valid = false
    var result: T = null.asInstanceOf[T]
    while (!valid) {
      print(prompt)
      val input = StdIn.readLine()
      Try(parser(input)) match {
        case Success(value) =>
          result = value
          valid = true
        case Failure(exception) =>
          print("Input error. Please try again.")
      }
    }
    result
  }

  override def print(msg: String): Unit = println(msg)

  override def readDouble(prompt: String): Double =
    safelyRead(prompt, _.toDouble)

  override def readDouble(prompt: String, min: Double, max: Double): Double = {
    Iterator.continually(readDouble(prompt)).find(d => d >= min && d <= max).get
  }

  override def readFloat(prompt: String): Float =
    safelyRead(prompt, _.toFloat)

  override def readFloat(prompt: String, min: Float, max: Float): Float = {
    Iterator.continually(readFloat(prompt)).find(f => f >= min && f <= max).get
  }

  override def readInt(prompt: String): Int =
    safelyRead(prompt, _.toInt)

  override def readInt(prompt: String, min: Int, max: Int): Int = {
    Iterator.continually(readInt(prompt)).find(i => i >= min && i <= max).get
  }

  override def readLong(prompt: String): Long =
    safelyRead(prompt, _.toLong)

  override def readLong(prompt: String, min: Long, max: Long): Long = {
    Iterator.continually(readLong(prompt)).find(l => l >= min && l <= max).get
  }

  override def readString(prompt: String): String = {
    print(prompt)
    StdIn.readLine()
  }

}
