package ru.projectosnova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.projectosnova.config.Config;
import ru.projectosnova.store.Store;

import java.util.List;

@RestController
public class StoreController {

    private Config config;

    @Autowired
    public StoreController(Config config) {
        this.config = config;
    }

    @PostMapping("/{category}/{typeName}")
    public String create(
            @PathVariable String category,
            @PathVariable String typeName,
            @RequestBody Object payload) throws Exception {
        Store store = config.getStore(category,typeName);
        return store.create(payload);
    }

    @GetMapping("/{category}/{typeName}/{id}")
    public String read(
            @PathVariable String category,
            @PathVariable String typeName,
            @PathVariable String id) throws Exception {
        Store store = config.getStore(category,typeName);
        return store.read(id);
    }

    @PostMapping("/{category}/{typeName}/{id}")
    public boolean update(
            @PathVariable String category,
            @PathVariable String typeName,
            @PathVariable String id,
            @RequestParam(name = "replace", required = false,defaultValue = "false") boolean replace,
            @RequestBody Object payload
            ) throws Exception {
        Store store = config.getStore(category,typeName);
        return store.update(id,payload,replace);
    }

    @DeleteMapping("/{category}/{typeName}/{id}")
    public boolean delete(
            @PathVariable String category,
            @PathVariable String typeName,
            @PathVariable String id) throws Exception {
        Store store = config.getStore(category,typeName);
        return store.delete(id);
    }

    @GetMapping("/{category}/{typeName}")
    public List<String> readAll(
            @PathVariable String category,
            @PathVariable String typeName) throws Exception {
        Store store = config.getStore(category,typeName);
        return store.findAll();
    }

    public String readPaged(){
        return "";
    }


}
