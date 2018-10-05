package org.sachin.rest.webservices.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.sachin.rest.webservices.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserService {

	static List<User> users = new ArrayList<>();
	private static int usercount = 0;

	static {
		users.add(new User(++usercount, "Jack", new Date()));
		users.add(new User(++usercount, "Smith", new Date()));
		users.add(new User(++usercount, "David", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		user.setId(++usercount);
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		for (User user : users) {
			if (user.getId().equals(id))
				return user;
		}

		return null;
	}

	public User deleteById(int id) {

		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			if (user.getId().equals(id)) {
				iterator.remove();
				return user;
			}
		}

		return null;
	}

}
