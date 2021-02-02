package map.app.controller;

import map.app.manager.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class AppController implements CommandLineRunner {

    private final Manager manager;

    @Autowired
    public AppController(Manager manager) {
        this.manager = manager;
    }


    @Override
    public void run(String... args) {
        this.manager.run();
    }
}
