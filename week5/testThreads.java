package Uebungen_AD.week5;
import Uebungen_AD.week4.IHashTableArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class testThreads {
    public static final Logger LOG = LoggerFactory.getLogger(testThreads.class);

    public static void main(final String[] args) { //
        final int nr = Runtime.getRuntime().availableProcessors();
        LOG.info("Available processors " + nr);
        final Thread self = Thread.currentThread();
        LOG.info("Name : " + self.getName());
        LOG.info("Priority : " + self.getPriority());
        LOG.info("ID : " + self.getId());
        LOG.info("Deamon? : " + self.isDaemon());
    }
}
