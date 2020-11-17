package spi;

public class JavaSpiImpl implements SpiService {
    @Override
    public String getName(String name) {
        return name;
    }
}