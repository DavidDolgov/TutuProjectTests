import org.aeonbits.owner.Config;

@Config.Sources("file:E:\\JAVA\\TESTS\\TutuProjectTest\\src\\main\\resources\\ConfigProject.properties")
public interface ConfigProject extends Config {
    @Key("baseUrl")
    String baseUrl();
}
