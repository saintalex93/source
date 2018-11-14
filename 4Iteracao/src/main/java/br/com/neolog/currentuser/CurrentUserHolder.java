package br.com.neolog.currentuser;

import br.com.neolog.pojo.User;

public class CurrentUserHolder {
	private static ThreadLocal<User> threadLocalUser = new ThreadLocal<>();

	public static User getUser() {
		return threadLocalUser.get();
	}

	public static void setUser(User user) {
		if (user == null) {
			threadLocalUser.remove();
		}
		threadLocalUser.set(user);
	}

	public static void remove() {
		threadLocalUser.remove();
	}
}
