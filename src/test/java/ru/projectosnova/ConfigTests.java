package ru.projectosnova;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.projectosnova.config.*;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ConfigTests {

    @Autowired
    private Config config;

    @Test
    public void getStoragebyNameTest() throws Exception {

        /*
        String configType = config.getStore("cars").getType();
        assertThat(configType).isEqualTo("domino");

        configType = config.getStore("cats").getType();
        assertThat(configType).isEqualTo("mongo");

         */

    }


}
