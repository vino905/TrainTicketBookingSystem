package com.train.ticket.service;

import java.util.List;
import java.util.Map;

import com.train.ticket.dto.UserDto;
import com.train.ticket.model.Ticket;

// TicketService.java
public interface TicketService {
	Ticket purchaseTicket(Map<String, String> ticketParameters, UserDto userDto);

	Ticket getTicketDetails(Long ticketId);

	List<Ticket> getAllTickets();

	void removeTicket(Long ticketId);

	List<Ticket> getAllTicketBySection(String section);
}
