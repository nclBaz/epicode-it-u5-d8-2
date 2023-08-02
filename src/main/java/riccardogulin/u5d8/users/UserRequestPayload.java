package riccardogulin.u5d8.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestPayload {
	private String name;
	private String surname;
	private String email;
}
