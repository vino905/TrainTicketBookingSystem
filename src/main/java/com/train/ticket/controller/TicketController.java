package com.train.ticket.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.train.ticket.dto.UserDto;
import com.train.ticket.model.Ticket;
import com.train.ticket.service.TicketService;
import com.train.ticket.util.Constant;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
	@Autowired
	private TicketService ticketService;

	@PostMapping("/purchase")
	public ResponseEntity<Ticket> purchaseTicket(@NotNull @RequestParam Map<String, String> ticketParameters,
			@Valid @RequestBody UserDto userDto) {
		Ticket ticket = ticketService.purchaseTicket(ticketParameters, userDto);
		return new ResponseEntity<>(ticket, HttpStatus.CREATED);
	}

	@GetMapping("/details/{ticketId}")
	public ResponseEntity<Ticket> getTicketDetails(@NotNull @PathVariable Long ticketId) {
		Ticket ticket = ticketService.getTicketDetails(ticketId);
		if (ticket != null) {
			return new ResponseEntity<>(ticket, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/details/allticket")
	public ResponseEntity<List<Ticket>> getAllTickets() {
		List<Ticket> tickets = ticketService.getAllTickets();
		return new ResponseEntity<>(tickets, HttpStatus.OK);
	}

	@GetMapping("/details/section")
	public ResponseEntity<List<Ticket>> getAllTicketsBySection(@NotNull @RequestParam String section) {
		List<Ticket> tickets = ticketService.getAllTicketBySection(section);
		return new ResponseEntity<>(tickets, HttpStatus.OK);
	}

	@DeleteMapping("/remove/{ticketId}")
	public ResponseEntity<String> removeTicket(@NotNull @PathVariable Long ticketId) {
		try {

			ticketService.removeTicket(ticketId);
			return new ResponseEntity<>(Constant.DELET_MESSAGE, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(Constant.NOT_FOUND, HttpStatus.NOT_FOUND);
		}
	}
}
