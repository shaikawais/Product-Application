package com.springmvc.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.springmvc.entities.Product;

@Component
@Transactional
public class ProductDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void createProduct(Product product) {
		this.hibernateTemplate.save(product);
	}

//	public void updateProduct(Product product) {
//		this.hibernateTemplate.save(product);
//	}

	public void deleteProduct(int id) {
		Product p = this.hibernateTemplate.load(Product.class, id);
		this.hibernateTemplate.delete(p);
	}

	public Product getProduct(int id) {
		Product p = this.hibernateTemplate.load(Product.class, id);
		return p;
	}

	public List<Product> getAllProduct() {
		List<Product> loadAll = this.hibernateTemplate.loadAll(Product.class);
		return loadAll;
	}
}
