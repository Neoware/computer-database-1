package com.excilys.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.excilys.ResetDB;
import com.excilys.core.date.DateMapper;
import com.excilys.core.dto.ComputerDTO;
import com.excilys.persistence.pagination.PageRequest;
import com.excilys.persistence.repository.interfaces.ComputerDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=com.excilys.persistence.config.PersistenceConfig.class, loader=AnnotationConfigContextLoader.class)
public class TestComputerDAO {
	@Autowired
	private ComputerDAO computerDAO;
	
	private LocalDate introduced;
	private LocalDate discontinued;

	@Before
	public void setUp() throws Exception {
		ResetDB.setupTest();

		introduced = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1991-01-01 00:00:00").getTime())
				.toLocalDateTime().toLocalDate();
		discontinued = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2006-01-10 00:00:00").getTime())
				.toLocalDateTime().toLocalDate();
	}

	@Test
	public void testReadAll() {
		List<ComputerDTO> computersExpected = new ArrayList<>();
		computersExpected.add(new ComputerDTO("1", "MacBook Pro 15.4 inch", "", "", "1", "Apple Inc"));
		computersExpected.add(new ComputerDTO("2", "CM-2a", "", "", "2", "Microsoft"));
		computersExpected.add(new ComputerDTO("3", "CM-200","", "", "2", "Microsoft"));
		computersExpected.add(new ComputerDTO("4", "CM-5e", "", "", "2", "Microsoft"));
		computersExpected.add(new ComputerDTO("5", "CM-5", DateMapper.dateEnToFr(introduced.toString()), "", "2", "Microsoft"));
		computersExpected.add(new ComputerDTO("6", "MacBook Pro", DateMapper.dateEnToFr(discontinued.toString()), "", "1", "Apple Inc"));

		List<ComputerDTO> computers = computerDAO.findAll(PageRequest.create().build());

		Assert.assertThat(computers, is(computersExpected));
	}
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testReadFromTo() {
		List<ComputerDTO> computersExpected = new ArrayList<>();
		computersExpected.add(new ComputerDTO("1", "MacBook Pro 15.4 inch", "", "", "1", "Apple Inc"));

		List<ComputerDTO> computers = computerDAO.findAll(PageRequest.create().page(1).eltByPage(1).build());
		Assert.assertThat(computers, is(computersExpected));
	}

	@Test
	public void testReadOne() {
		List<ComputerDTO> computersExpected = computerDAO.findAll(PageRequest.create().id(5L).build());
		
		// computersExpected
		// Expect not null
		// Expect 1 elt
		
		ComputerDTO computerExpected = computersExpected.get(0);
		
		Assert.assertEquals("5", computerExpected.getId());
		Assert.assertEquals("CM-5", computerExpected.getName());
		Assert.assertEquals(DateMapper.dateEnToFr(introduced.toString()), computerExpected.getIntroduced());
		Assert.assertEquals("", computerExpected.getDiscontinued());
		Assert.assertEquals("2", computerExpected.getCompanyId());
		Assert.assertEquals("Microsoft", computerExpected.getCompanyName());
	}

	@Test
	@Ignore("Feature not implemented yet!")
	public void testCreateAndDelete() {
		fail("not implemented!");
	}

	@Test
	@Ignore("Not implemented yet!")
	public void testUpdate() {
		fail("not implemented!");
	}
	
	@Test
	public void testCount() {
		int rowsNumber = computerDAO.count(PageRequest.create().build());
		
		Assert.assertEquals(6, rowsNumber);
	}

}
