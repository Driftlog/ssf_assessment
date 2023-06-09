package vttp2023.batch3.ssf.frontcontroller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2023.batch3.ssf.frontcontroller.respositories.AuthenticationRepository;

@Service
public class AuthenticationService {

	@Value("${RAILWAY_URL}")
	private String railwayURL;

	@Autowired
	private AuthenticationRepository repo;	
	

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public void authenticate(String username, String password) throws Exception {
		RestTemplate template = new RestTemplate();
		String url = UriComponentsBuilder
				.fromUriString(railwayURL)
				.toUriString();

		JsonObject userJson = Json.createObjectBuilder()
						.add("username", username)
						.add("password", password)
						.build();

		RequestEntity<String> request = RequestEntity
									.post(url)
									.contentType(MediaType.APPLICATION_JSON)
									.accept(MediaType.APPLICATION_JSON)
									.body(userJson.toString());

		ResponseEntity<String> resp = template.exchange(request, String.class);

		if (resp.getStatusCode().is4xxClientError()) {
			throw new HttpServerErrorException(resp.getStatusCode(), resp.getBody());
		}
	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
		repo.disableUser(username);
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		if (repo.isUserDisabled(username)) {
			return true;
		}
		return false;
	}
}
