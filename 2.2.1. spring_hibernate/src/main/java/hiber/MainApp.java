package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Lionel", "Messi", "messi@mail.ru", new Car(10, "Audi A5"));
        User user2 = new User("Roberto", "Carlos", "carlos@mail.ru", new Car(5, "BMW X5"));
        User user3 = new User("Cristiano", "Ronaldo", "ronaldo@mai.ru", new Car(7, "Toyota Corolla"));
        User user4 = new User("Eden", "Azar", "azar@mail.ru", new Car(15, "Lotus Eclipse"));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        System.out.println(userService.getUserBySerialAndModel(15,"Lotus Eclipse"));

        for (User user : userService.listUsers()) {
            System.out.println(("Id = " + user.getId() + ";") +
                    (" First Name = " + user.getFirstName() + ";") +
                    (" Last Name = " + user.getLastName() + ";") +
                    (" Email = " + user.getEmail() + ";") +
                    (" Id = " + user.getCar().getId() + ";") +
                    (" Series = " + user.getCar().getSeries() + ";") +
                    (" Model = " + user.getCar().getModel()));
        }

        context.close();
    }
}