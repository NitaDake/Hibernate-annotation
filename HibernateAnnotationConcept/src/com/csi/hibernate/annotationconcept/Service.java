package com.csi.hibernate.annotationconcept;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class Service {
	public static SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();

	public static void main(String[] args) {
		// saveProductData();
		//updateProductData(3);
		deleteProductData(1);
		getPublicData();
	}

	public static void saveProductData() {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Product p1 = new Product();
		Scanner sc = new Scanner(System.in);
		p1.setProdName("SAMSUNG TV");
		p1.setProdPrice(339546.234);
		session.save(p1);
		transaction.commit();
	}

	public static void getPublicData() {
		Session session = factory.openSession();
		List<Product> productList = session.createQuery("from Product").list();
		productList.forEach(System.out::println);
	}

	public static void updateProductData(int ProdId) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		List<Product> pList = session.createQuery("from Product").list();
		for (Product product : pList) {// here Product is reference variable
			if (product.getProdId() == ProdId) {
				product.setProdName("DELL LAPTOP");
				product.setProdPrice(35645.29);
				session.update(product);
				transaction.commit();
			}
		}
	}

	public static void deleteProductData(int ProdId) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		List<Product>pList=session.createQuery("from Product").list();
		for(Product product :pList) {
			if(product.getProdId()==ProdId) {
				session.delete(product);
				transaction.commit();
			}
		}

	}
}
