package riccardogulin.u5d8.users;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import riccardogulin.u5d8.exceptions.BadRequestException;
import riccardogulin.u5d8.exceptions.NotFoundException;

@Service
public class UsersService {
	private final UsersRepository usersRepo;

	@Autowired
	public UsersService(UsersRepository usersRepo) {
		this.usersRepo = usersRepo;
	}

	public User create(UserRequestPayload body) {
		// check if email already in use
		usersRepo.findByEmail(body.getEmail()).ifPresent(user -> {
			throw new BadRequestException("L'email è già stata utilizzata");
		});
		User newUser = new User(body.getName(), body.getSurname(), body.getEmail());
		return usersRepo.save(newUser);
	}

	public List<User> find() {
		return usersRepo.findAll();
	}

	public User findById(UUID id) throws NotFoundException {
		return usersRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public User findByIdAndUpdate(UUID id, UserRequestPayload body) throws NotFoundException {
		User found = this.findById(id);
		found.setEmail(body.getEmail());
		found.setName(body.getName());
		found.setSurname(body.getSurname());

		return usersRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		User found = this.findById(id);
		usersRepo.delete(found);
	}
}
