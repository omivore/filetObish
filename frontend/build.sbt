name := """filetObish"""
organization := "com.filetObish"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.2"

libraryDependencies += guice
libraryDependencies += "com.google.cloud" % "google-cloud-language" % "0.26.0-beta"