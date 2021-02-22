package observatory

//import org.junit.Assert._
import org.junit.Test
import Extraction.spark
import java.time.LocalDate

trait ExtractionTest extends MilestoneSuite {
  private val milestoneTest = namedMilestoneTest("data extraction", 1) _

  // Implement tests for the methods of the `Extraction` object
  @Test def `empty all`() {
    val year = 0
    val stations = spark.sparkContext.parallelize(List(""))
    val temperatures = spark.sparkContext.parallelize(List(""))

    val computed = Extraction.locateTemperaturesSpark(year, stations, temperatures).collect()
    val expected = Array[(LocalDate, Location, Temperature)]()

    assert(computed.sameElements(expected))
  }

  @Test def `empty stations`() {
    val year = 0
    val stations = spark.sparkContext.parallelize(List(""))
    val temperatures = spark.sparkContext.parallelize(List("4,,01,01,32"))

    val computed = Extraction.locateTemperaturesSpark(year, stations, temperatures).collect()
    val expected = Array[(LocalDate, Location, Temperature)]()

    assert(computed.sameElements(expected))
  }

  @Test def `empty temperatures`() {
    val year = 0
    val stations = spark.sparkContext.parallelize(List("4,,+1,+1"))
    val temperatures = spark.sparkContext.parallelize(List(""))

    val computed = Extraction.locateTemperaturesSpark(year, stations, temperatures).collect()
    val expected = Array[(LocalDate, Location, Temperature)]()

    assert(computed.sameElements(expected))
  }

  @Test def `both nonempty`() {
    val year = 0
    val stations = spark.sparkContext.parallelize(List("4,,+1,+2", "4,5,+1,+2"))
    val temperatures = spark.sparkContext.parallelize(List("4,,01,01,32", "4,10,01,01,32"))

    val computed = Extraction.locateTemperaturesSpark(year, stations, temperatures).collect()
    val expected = Array((LocalDate.of(year, 1, 1), Location(1, 2), 0))

    assert(computed.sameElements(expected))
  }

  @Test def `average empty`() {
    val records = spark.sparkContext.parallelize(List[(LocalDate, Location, Temperature)]())

    val computed = Extraction.locationYearlyAverageRecordsSpark(records).collect()
    val expected = Array[(Location, Temperature)]()

    assert(computed.sameElements(expected))
  }

  @Test def `average non-empty`() {
    val year = 4
    val records = spark.sparkContext.parallelize(
      List[(LocalDate, Location, Temperature)]
        ((LocalDate.of(year, 1, 1), Location(1, 2), 4),
          (LocalDate.of(year, 4, 1), Location(1, 2), 5),
          (LocalDate.of(year, 1, 5), Location(1, 2), 6),
          (LocalDate.of(year, 1, 1), Location(2, 2), 4)))

    val computed = Extraction.locationYearlyAverageRecordsSpark(records).collect().toSet
    val expected = Set[(Location, Temperature)]((Location(1, 2), 5), (Location(2, 2), 4))

    assert(computed == expected)
  }

}
