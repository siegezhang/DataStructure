package stream;

@FunctionalInterface
interface Converter {
  Integer convert(String from);
}
