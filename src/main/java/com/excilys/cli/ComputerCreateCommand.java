package com.excilys.cli;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.bean.Computer;
import com.excilys.bean.mapper.DateMapper;
import com.excilys.service.interfaces.ComputerService;

public class ComputerCreateCommand implements Command {
	@Autowired
	private ComputerService computerService;

	@Override
	public void execute() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		Computer computer = new Computer();

		System.out.printf("Enter the computer name:%n>");
		computer.setName(input.nextLine());

		System.out.printf("Enter the computer introduced date: format YYYY-MM-DD (enter to skip)%n>");
		computer.setIntroduced(DateMapper.convertStringToLocalDate(input.nextLine()));

		System.out.printf("Enter the computer discontinued date: format YYYY-MM-DD (enter to skip)%n>");
		computer.setDiscontinued(DateMapper.convertStringToLocalDate(input.nextLine()));

		System.out.printf("Enter the company id:%n>");
		computer.setCompanyId(DateMapper.convertStringToLong(input.nextLine()));

		computerService.create(computer);

		System.out.println("Computer added with success.");
	}

}
