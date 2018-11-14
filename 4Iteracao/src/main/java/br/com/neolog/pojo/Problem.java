package br.com.neolog.pojo;

import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class Problem {

	Set<HolderCodePrice> products;
	double target;

	public Problem() {
	}

	public Set<HolderCodePrice> getProducts() {
		return products;
	}

	public void setProducts(Set<HolderCodePrice> products) {
		this.products = products;
	}

	public double getTarget() {
		return target;
	}

	public void setTarget(double target) {
		this.target = target;
	}

}
