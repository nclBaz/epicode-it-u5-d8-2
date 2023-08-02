package riccardogulin.u5d8;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import riccardogulin.u5d8.users.UserRequestPayload;
import riccardogulin.u5d8.users.UsersService;

@Component
public class UsersRunner implements CommandLineRunner {
	@Autowired
	UsersService usersServ;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		for (int i = 0; i < 30; i++) {
			String name = faker.name().firstName();
			String surname = faker.name().lastName();
			String email = faker.internet().emailAddress();
			UserRequestPayload user = new UserRequestPayload(name, surname, email);
			// usersServ.create(user);
		}

	}

}
