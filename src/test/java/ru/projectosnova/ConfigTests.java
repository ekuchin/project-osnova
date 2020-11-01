package ru.projectosnova;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.projectosnova.config.*;
import ru.projectosnova.store.Store;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ConfigTests {

    @Autowired
    private Config config;

    @Test
    public void storageReadTest() throws Exception {

        ConfigStore mk2auto = new ConfigStore("domino-dev", "domino","https://www.project-osnova.ru", "", "");
        config.addStore(mk2auto);

        ConfigType cars = new ConfigType("mk","cars","domino-dev","");
        config.addType(cars);

        Store store = config.getStore("mk","cars");
        System.out.println(store.findAll("All"));

        assertThat(store.getConfig().getType()).isEqualTo("domino");

    }

}
