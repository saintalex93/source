package br.com.neolog.currentuser;

import br.com.neolog.models.Customer;

public class CurrentUserHolder {
	private static ThreadLocal<Customer> threadLocalUser = new ThreadLocal<>();

	public static Customer getUser() {
		return threadLocalUser.get();
	}

	public static void setUser(Customer user) {
		if (user == null) {
			threadLocalUser.remove();
		}
		threadLocalUser.set(user);
	}

	public static void remove() {
		threadLocalUser.remove();
	}
}
