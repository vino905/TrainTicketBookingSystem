package com.train.ticket.service.Impl;

import java.util.List;
import java.util.Map;

//TicketServiceImpl.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.train.ticket.dto.UserDto;
import com.train.ticket.exception.BadRequestException;
import com.train.ticket.model.Ticket;
import com.train.ticket.model.Users;
import com.train.ticket.repository.TicketRepository;
import com.train.ticket.repository.UserRepository;
import com.train.ticket.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Ticket purchaseTicket(Map<String, String> ticketParametes, UserDto userDto) {
		Users user = new Users();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		userRepository.save(user);

		Ticket ticket = new Ticket();
		if (!ObjectUtils.isEmpty(ticketParametes.get("fromLocation"))
				|| !ObjectUtils.isEmpty(ticketParametes.get("toLocation"))
				|| !ObjectUtils.isEmpty(ticketParametes.get("section"))) {

			ticket.setFromLocation(ticketParametes.get("fromLocation"));
			ticket.setToLocation(ticketParametes.get("toLocation"));
			ticket.setSection(ticketParametes.get("section"));
		} else {
			throw new BadRequestException("Invalid Params ! Please enter valid Parameter");
		}
		ticket.setUser(user);
		ticket.setPrice(20.0); // Set the price as needed
		return ticketRepository.save(ticket);
	}

	@Override
	public Ticket getTicketDetails(Long ticketId) {
		return ticketRepository.findById(ticketId).orElse(null);
	}

	@Override
	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
	}

	@Override
	public void removeTicket(Long ticketId) {
		ticketRepository.deleteById(ticketId);
	}

	@Override
	public List<Ticket> getAllTicketBySection(String section) {
		return ticketRepository.findBySection(section);
	}
}
