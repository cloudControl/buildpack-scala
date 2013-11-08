import sbt._
import Keys._

object PaasPlugin extends Plugin {
  override def settings = Seq(
    externalResolvers <<= resolvers map { appResolvers =>
      Seq(Resolver.defaultLocal) ++ appResolvers ++
      Seq(Resolver.url("paas-sbt-typesafe") artifacts "http://s3pository." + sys.env("DOMAIN") + "/ivy-typesafe-releases/[organization]/[module]/[revision]/[type]s/[artifact](-[classifier]).[ext]",
          "paas-central" at "http://s3pository." + sys.env("DOMAIN") + "/maven-central/",
          "typesafe" at "http://repo.typesafe.com/typesafe/repo/")
    },
    sources in doc in Compile := List()
  )
}
