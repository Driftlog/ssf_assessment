package vttp2023.batch3.ssf.frontcontroller.respositories;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class AuthenticationRepository {

	@Autowired
	private RedisTemplate<String,Object> template;

	// TODO Task 5
	// Use this class to implement CRUD operations on Redis

	//place the username and duration to be disabled
	public boolean disableUser(String username) {
		template.opsForValue().set(username, "disabled", Duration.ofMinutes(30));
		return true;

	}

	public boolean isUserDisabled(String username) {
		//if username is disabled, return true
		if (template.opsForValue().get(username) != null) {
			return true;
		}

		return false;
	}


}
