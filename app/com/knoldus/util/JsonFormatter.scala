package com.knoldus.util

import domain.Content
import play.api.libs.json.Json


object JsonFormatter {

  implicit val barChartJsonFormat = Json.format[Content]
}
