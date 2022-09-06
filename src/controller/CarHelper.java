package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Car;

public class CarHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CarList");
	
	public void insertCar(Car car) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(car);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Car>showAllCars(){
		EntityManager em = emfactory.createEntityManager();
		List<Car>allCars = em.createQuery("SELECT i FROM Car i").getResultList();
		return allCars;
	}
	
	public void deleteCar(Car toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Car>typedQuery = em.createQuery("select	car	from Car car	where	car.make	=	:selectedMake	and	car.model	=	:selectedModel",Car.class);
		
		typedQuery.setParameter("selectedMake", toDelete.getMake());
		typedQuery.setParameter("selectedModel", toDelete.getModel());
		
		typedQuery.setMaxResults(1);
		
		Car result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public Car searchForCarById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Car found = em.find(Car.class, idToEdit);
		em.close();
		return found;
	}
	
	public void updateCar(Car toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public List<Car> searchForCarByMake(String make) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Car> typedQuery = em.createQuery("select car from Car car where car.make = :selectedMake", Car.class);
		typedQuery.setParameter("selectedMake", make);
		
		List<Car> foundCar = typedQuery.getResultList();
		em.close();
		return foundCar;
	}
	
	public List<Car> searchForCarByModel(String model) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Car> typedQuery = em.createQuery("select car from Car car where car.model = :selectedModel", Car.class);
		typedQuery.setParameter("selectedModel", model);
		
		List<Car> foundCar = typedQuery.getResultList();
		em.close();
		return foundCar;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
