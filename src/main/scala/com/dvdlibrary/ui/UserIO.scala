package com.dvdlibrary.ui

trait UserIO {
  def print(msg: String): Unit
  def readDouble(prompt: String): Double
  def readDouble(prompt: String, min: Double, max: Double): Double
  def readFloat(prompt: String): Float
  def readFloat(prompt: String, min: Float, max: Float): Float
  def readInt(prompt: String): Int
  def readInt(prompt: String, min: Int, max: Int): Int
  def readLong(prompt: String): Long
  def readLong(prompt: String, min: Long, max: Long): Long
  def readString(prompt: String): String
}
