import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.bmatch.meituan.Application;

public class Log4j2Test {

    private static final Logger logger = LoggerFactory.getLogger(Log4j2Test.class);

    public static void main(String[] args) {

        logger.error("null exception",new NullPointerException());

    }


}
