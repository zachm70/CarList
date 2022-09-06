import java.util.List;
import java.util.Scanner;

import controller.CarHelper;
import model.Car;

public class StartProgram {

	static Scanner in = new Scanner(System.in);
	static CarHelper ch = new CarHelper();
	
	private static void addACar() {
		// TODO Auto-generated method stub
		System.out.print("Enter a make: ");
		String make = in.nextLine();
		System.out.print("Enter an model: ");
		String model = in.nextLine();
		
		Car toAdd = new Car(make,model);
		ch.insertCar(toAdd);
	}
	
	private static void deleteACar() {
		// TODO Auto-generated method stub
		System.out.print("Enter Make to delete: ");
		String make = in.nextLine();
		System.out.print("Enter the model to delete: ");
		String model = in.nextLine();

		Car toDelete = new Car(make,model);
		ch.deleteCar(toDelete);
	}
	
	private static void editACar() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Make");
		System.out.println("2 : Search by Model");
		int searchBy = in.nextInt();
		in.nextLine();
		
		List<Car> foundCar;
		if (searchBy == 1) {
			System.out.print("Enter the make: ");
			String make = in.nextLine();
			foundCar=ch.searchForCarByMake(make);
			
		} else {
			System.out.print("Enter the model: ");
			String model = in.nextLine();
			foundCar=ch.searchForCarByModel(model);

		}

		if (!foundCar.isEmpty()) {
			System.out.println("Found Results.");
			for (Car l : foundCar) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			Car toEdit = ch.searchForCarById(idToEdit);
			System.out.println("Retrieved " + toEdit.getMake() + " " + toEdit.getModel());
			System.out.println("1 : Update Make");
			System.out.println("2 : Update Model");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Make: ");
				String newMake = in.nextLine();
				toEdit.setMake(newMake);
			} else if (update == 2) {
				System.out.print("New Model: ");
				String newModel = in.nextLine();
				toEdit.setModel(newModel);
			}

			ch.updateCar(toEdit);

		} else {
			System.out.println("---- No results found");
		}

	}
	
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("Car list");
		while (goAgain) {
			System.out.println("*  Select an item:");
			System.out.println("*  1 -- Add a car");
			System.out.println("*  2 -- Edit a car");
			System.out.println("*  3 -- Delete a car");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addACar();
			} else if (selection == 2) {
				editACar();
			} else if (selection == 3) {
				deleteACar();
			} else if (selection == 4) {
				viewTheList();
			} else {
				ch.cleanUp();
				System.out.println("bye");
				goAgain = false;
			}

		}

	}
	
	private static void viewTheList() {
		// TODO Auto-generated method stub
		List<Car>allCars = ch.showAllCars();
		for(Car singleItem:allCars) {
			System.out.println(singleItem.returnCar());
		}

	}
	
	public static void main(String[] args) {
		runMenu();
	}
}
