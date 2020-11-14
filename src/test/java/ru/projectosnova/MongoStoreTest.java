package ru.projectosnova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.projectosnova.config.Config;
import ru.projectosnova.config.ConfigConnection;
import ru.projectosnova.config.ConfigType;
import ru.projectosnova.store.Store;
import ru.projectosnova.test.Cat;
import ru.projectosnova.utils.Transform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootTest
public class MongoStoreTest {
    /*
    Database and Collection must be created in MongoDB
     */

    @Test
    void integrationTest() throws Exception {
        final String CATEGORY = "testing";
        final String DOCTYPE = "cats";
        final String CONN_NAME = "mongo-local";
        final String CONN_TYPE = "mongodb";
        final String PROTOCOL = "mongodb://";
        final String HOST = "localhost";
        final String PORT = "27017";
        final String USERNAME = "";
        final String PASSWORD = "";
        final String URI = "/cats"; //Database
        final String COLLECTION = "test"; //Collection

        ConfigConnection connection = new ConfigConnection(CONN_NAME, CONN_TYPE, PROTOCOL, HOST, PORT, USERNAME, PASSWORD);
        ConfigType catsType = new ConfigType(CATEGORY, DOCTYPE, CONN_NAME, URI, COLLECTION);
        Config config = new Config(new ArrayList<>(Arrays.asList(connection)),new ArrayList<>(Arrays.asList(catsType)));
        Store store = config.getStore(CATEGORY, DOCTYPE);

        ArrayList<String> ids = new ArrayList<>();
        ids.add(store.create("{\"name\":\"Murzik\",\"breed\":\"Manul\",\"weight\":5,\"isAngry\":true}"));
        ids.add(store.create("{\"name\":\"Ramesses\",\"breed\":\"Sphynx\",\"weight\":2,\"isAngry\":true}"));
        ids.add(store.create("{\"name\":\"Edward\",\"breed\":\"Britain\",\"weight\":4,\"isAngry\":false}"));
        ids.add(store.create("{\"name\":\"Murka\",\"breed\":\"Breedless\",\"weight\":3,\"isAngry\":true}"));

        ArrayList<Cat> cats = store.findAllAsList(COLLECTION)
                .stream().map(c-> Transform.jsonToObject(c,Cat.class))
                .collect(Collectors.toCollection(ArrayList::new));

        Assertions.assertEquals(4, cats.size());
        Assertions.assertEquals("Murzik",cats.get(0).getName());
        Assertions.assertEquals("Sphynx",cats.get(1).getBreed());
        Assertions.assertEquals(4,cats.get(2).getWeight());
        Assertions.assertTrue(cats.get(3).isAngry());

        store.update(ids.get(0),"{\"breed\":\"Breedless\"}",false);
        store.update(ids.get(1),"{\"name\":\"Tutankhamun\"}",false);
        store.update(ids.get(2),"{\"weight\":6}",false);
        store.update(ids.get(3),"{\"isAngry\":false}",false);

        cats = store.findAllAsList(COLLECTION)
                .stream().map(c-> Transform.jsonToObject(c,Cat.class))
                .collect(Collectors.toCollection(ArrayList::new));

        Assertions.assertEquals("Breedless",cats.get(0).getBreed());
        Assertions.assertEquals("Tutankhamun",cats.get(1).getName());
        Assertions.assertEquals(6,cats.get(2).getWeight());
        Assertions.assertFalse(cats.get(3).isAngry());

        store.update(ids.get(3),"{\"name\":\"Busia\"}",true);
        Cat cat = store.read(ids.get(3),Cat.class);

        Assertions.assertEquals("Busia",cat.getName());
        Assertions.assertNull(cat.getBreed());

        store.delete(ids.get(3));
        store.delete(ids.get(0));

        cats = store.findAllAsList(COLLECTION)
                .stream().map(c-> Transform.jsonToObject(c,Cat.class))
                .collect(Collectors.toCollection(ArrayList::new));

        Assertions.assertEquals(2, cats.size());

        store.delete(ids.get(1));
        store.delete(ids.get(2));

        cats = store.findAllAsList(COLLECTION)
                .stream().map(c-> Transform.jsonToObject(c,Cat.class))
                .collect(Collectors.toCollection(ArrayList::new));

        Assertions.assertEquals(0, cats.size());

    }

}
