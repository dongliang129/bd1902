import org.apache.spark.{SparkConf, SparkContext}
import scala.util.control.Breaks._

object datesetDemo {
  def main(args: Array[String]): Unit = {

    for (i <- 1 to 10) {
      breakable {
        if (i == 5) break;
        else println(i)
      }
    }


  }

}
