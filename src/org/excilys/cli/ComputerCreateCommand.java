package org.excilys.cli;

import java.util.Scanner;

import org.excilys.beans.Computer;
import org.excilys.exceptions.ConnectionException;
import org.excilys.exceptions.DAOException;
import org.excilys.exceptions.DriverException;
import org.excilys.service.ComputerService;

public class ComputerCreateCommand implements Command {

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		Computer computer = new Computer();
		boolean success = false;
		
		System.out.printf("Enter the computer name:%n>");
		computer.setName( input.nextLine() );
		
		System.out.printf("Enter the computer introduced date: format YYYY-MM-DD (enter to skip)%n>");
		computer.setIntroduced( Command.convertStringToLocalDateTime( input.nextLine() ) );
		
		System.out.printf("Enter the computer discontinued date: format YYYY-MM-DD (enter to skip)%n>");
		computer.setDiscontinued( Command.convertStringToLocalDateTime( input.nextLine() ) );
	
		System.out.printf("Enter the company id:%n>");
		computer.setCompanyId( Command.convertStringToLong( input.nextLine() ) );
		
		try {
			success = ComputerService.getInstance().createComputer( computer );
		} catch (DAOException | ConnectionException | DriverException e) {
			System.out.println("DB error!");
			return;
		}

		if (success == true) {
			System.out.println("Computer added with success.");
		} else {
			System.out.println("Computer not added to the database!");
		}
	}

}
