package com.jdbSpringBootCaseStudy.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.jdbSpringBootCaseStudy.model.Product;




public class ProductService {

	public List<Product> getAllProducts(){
		
		List<Product> courseList = new ArrayList<Product>();
		
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("UseCaseDB");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		

		try {
			Query query = entitymanager.createNamedQuery("queryAllProducts");
			courseList = query.getResultList();
			
		} catch(PersistenceException e) {
			e.getMessage();
		}
		finally {
			entitymanager.close();
			entitymanagerfactory.close();
		}
		
		return courseList ;
		
	}

	public Product getProductById(int pId) {
		Product product = null;
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("UseCaseDB");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();

		try {
			product = entitymanager.find(Product.class, pId);

		} catch (PersistenceException e) {
			e.getMessage();
		} finally {
			entitymanager.close();
			entitymanagerfactory.close();
		}

		return product;
	}
	
	public Product getIDByProductName(String pName) {
		Product productFound = new Product();
		
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("UseCaseDB");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();

		try {
			
			 productFound = entitymanager.find(Product.class, pName);

			
		} catch (PersistenceException e) {
			e.getMessage();
		} finally {
			entitymanager.close();
			entitymanagerfactory.close();
		}

		return productFound;
	}
	
	
	public void showAllProducts(List<Product> productList) {
	
		for (Product c : productList) {
			System.out.println(c.toString());
		}
		
	}
	
	public boolean insertProduct(Product product) {
		boolean result = true;
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("UseCaseDB");	
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		
		try {
			entitymanager.getTransaction().begin();
			entitymanager.persist(product);
			entitymanager.getTransaction().commit();
			
		} catch(PersistenceException e) {
			e.getMessage();
			result = false;
		}
		finally {
			entitymanager.close();
			entitymanagerfactory.close();
		}
		return result;
	}


	public boolean updateProduct(Product product) {
		boolean result = true;
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("UseCaseDB");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		Product foundProduct;
		
	try {
		entitymanager.getTransaction().begin();
		foundProduct = entitymanager.find(Product.class, product.getpId());
		foundProduct.setpCategory(product.getpCategory());
		foundProduct.setpDescription(product.getpDescription());
		foundProduct.setpImage(product.getpImage());
		foundProduct.setpName(product.getpName());
		foundProduct.setpPrice(product.getpPrice());
		foundProduct.setpServing(product.getpServing());
		foundProduct.setpSize(product.getpSize());
		foundProduct.setpSummary(product.getpSummary());
		foundProduct.setpWeight(product.getpWeight());
		entitymanager.getTransaction().commit();
	} catch (PersistenceException e) {
		e.printStackTrace();
		result = false;
	} finally {
		entitymanager.close();
		entitymanagerfactory.close();
	}
		return result;
	}
	

	public boolean removeProductById(int pId) {
		boolean result;
		
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("UseCaseDB");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		
		try {
			entitymanager.getTransaction().begin();
			Product productFound = entitymanager.find(Product.class, pId);
			entitymanager.remove(productFound);
			entitymanager.getTransaction().commit();
			result = true;
		} catch (PersistenceException e) {
			e.printStackTrace();
			result = false;
		} finally {
			entitymanager.close();
			entitymanagerfactory.close();
		}
		return result;
	}
	

	public List<Product> getProductsByCategory( String category){
		List<Product> productList = new ArrayList<Product>();
		
		EntityManagerFactory entitymanagerfactory = Persistence.createEntityManagerFactory("UseCaseDB");
		EntityManager entitymanager = entitymanagerfactory.createEntityManager();
		

		try {
			Query query = entitymanager.createNamedQuery("queryProductsByCategory");
			query.setParameter("category", category);

			productList = query.getResultList();
			
		} catch(PersistenceException e) {
			e.getMessage();
		}
		finally {
			entitymanager.close();
			entitymanagerfactory.close();
		}
		
		return productList ;
	}
	
	

}
