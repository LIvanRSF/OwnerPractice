package config;

import java.net.URL;
import org.aeonbits.owner.Config;

public interface WebDriverConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://github.com")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("CHROME")
    Browser getBrowser();

    @Key("version")
    @DefaultValue("100")
    String getVersion();

    @Key("remoteUrl")
    @DefaultValue("https://localhost:4444")
    URL getRemoteUrl();
}
