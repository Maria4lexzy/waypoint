import service.RouteAnalyzer;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello, World!");
    RouteAnalyzer analyzer = new RouteAnalyzer("src/resource/vessels.xml");
    analyzer.analyze();

  }
}
