package be.cafeba.cors.config
import play.api.Play.current
import play.api.mvc.RequestHeader


object ConfigReader {

  object AccessControl {

    def getAllowOrigin(requestHeader: RequestHeader): (String, String) = {
      val protocol = if (requestHeader.secure) "https://" else "http://"
      val origin =  protocol + requestHeader.host
      lazy val allowOrigin = current.configuration.getString("cors.allow_origin").getOrElse(origin)
      "Access-Control-Allow-Origin" -> allowOrigin
    }

    def getExposedHeaders(): (String, String) = {
      lazy val exposeHeaders = current.configuration.getString("cors.expose_headers").getOrElse("WWW-Authenticate, Server-Authorization")
      "Access-Control-Expose-Headers" -> exposeHeaders
    }

    def getAllowMethods(): (String, String) = {
      lazy val allowMethods = current.configuration.getString("cors.methods.allowed").getOrElse("POST, GET, OPTIONS, PUT, DELETE")
      "Access-Control-Allow-Methods" -> allowMethods
    }

    def getAllowHeaders(): (String, String) = {
      lazy val headersAllowed = current.configuration.getString("cors.headers.allowed").getOrElse("x-requested-with,content-type,Cache-Control,Pragma,Date")
      "Access-Control-Allow-Headers" -> headersAllowed
    }

    def getAllow(): (String, String) = {
      lazy val allow = current.configuration.getString("cors.allow").getOrElse("*")
      "Allow" -> allow
    }
  }

}
