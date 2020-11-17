package spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpiTest {
    public static void main(String[] args) {
        ServiceLoader<SpiService> loadedParsers = ServiceLoader.load(SpiService.class);
        Iterator<SpiService> iterator = loadedParsers.iterator();
        while (iterator.hasNext()) {
            SpiService spiService = iterator.next();
            System.out.println(spiService.getName("siege"));
        }
    }
}
