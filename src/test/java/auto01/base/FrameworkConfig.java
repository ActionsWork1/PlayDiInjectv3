package auto01.base;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:framework.properties")
public interface FrameworkConfig extends Config {
    @Key("base.url")
    String baseUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("test.data")
    String testData();
}
