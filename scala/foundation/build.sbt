version := "1.0.0"

scalaVersion := "2.12.12"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Xfuture",
  "-Xlint"
)

val sparkVersion = "3.1.1"
val hadoopVersion = "3.2.1"
val postgresVersion = "42.2.22"
val verticaVersion = "10.0.1-0"
val typeSafeVersion = "3.9.2"


libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.1",
  "com.typesafe.scala-logging" %% "scala-logging" % typeSafeVersion
)

assemblyJarName in assembly := s"${name.value}-${version.value}.jar"

assemblyMergeStrategy in assembly := {
  case x if Assembly.isConfigFile(x) =>
    MergeStrategy.concat
  case PathList(ps @ _*)
    if Assembly.isReadme(ps.last) || Assembly.isLicenseFile(ps.last) =>
    MergeStrategy.rename
  case PathList("META-INF", xs @ _*) =>
    (xs map { _.toLowerCase }) match {
      case ("manifest.mf" :: Nil) | ("index.list" :: Nil) |
           ("dependencies" :: Nil) =>
        MergeStrategy.discard
      case ps @ (x :: xs)
        if ps.last.endsWith(".sf") || ps.last.endsWith(".dsa") =>
        MergeStrategy.discard
      case "plexus" :: xs =>
        MergeStrategy.discard
      case "services" :: xs =>
        MergeStrategy.filterDistinctLines
      case ("spring.schemas" :: Nil) | ("spring.handlers" :: Nil) =>
        MergeStrategy.filterDistinctLines
      case _ => MergeStrategy.first
    }
  case _ => MergeStrategy.first
}

assemblyOption in assembly := (assemblyOption in assembly).value
  .copy(includeScala = true)
