import com.sun.tools.javac.Main
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
object Main {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Main], args: _*)
  }
}