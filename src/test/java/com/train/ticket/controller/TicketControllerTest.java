package com.train.ticket.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.train.ticket.dto.UserDto;
import com.train.ticket.model.Ticket;
import com.train.ticket.service.TicketService;

class TicketControllerTest {

	private MockMvc mockMvc;

	@Mock
	private TicketService ticketService;

	@InjectMocks
	private TicketController ticketController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
	}

	@Test
	void testPurchaseTicket() throws Exception {
		// Mocking service response
		Ticket mockTicket = new Ticket();
		when(ticketService.purchaseTicket(any(Map.class), any(UserDto.class))).thenReturn(mockTicket);

		// Request body
		UserDto userDto = new UserDto();
		userDto.setFirstName("Vinod");
		userDto.setLastName("Kalwani");
		userDto.setEmail("Vinod@Kalwani.com");

		// Ticket parameters
		Map<String, String> ticketParameters = new HashMap<>();
		ticketParameters.put("param1", "value1");
		ticketParameters.put("param2", "value2");

		// Perform the request and assert the response
		mockMvc.perform(post("/api/tickets/purchase").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto)).param("param1", "value1")
				.param("param2", "value2")).andExpect(status().isCreated());
	}

	@Test
	void testGetTicketDetails() throws Exception {
		// Mocking service response
		Ticket mockTicket = new Ticket();
		when(ticketService.getTicketDetails((long)5000)).thenReturn(mockTicket);

		// Perform the request and assert the response
		mockMvc.perform(get("/api/tickets/details/5001")).andExpect(status().isOk());
	}

	@Test
	void testGetAllTickets() throws Exception {
		// Mocking service response
		List<Ticket> mockTickets = Arrays.asList(new Ticket(), new Ticket());
		when(ticketService.getAllTickets()).thenReturn(mockTickets);

		// Perform the request and assert the response
		mockMvc.perform(get("/api/tickets/details/allticket")).andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$.length()").value(mockTickets.size()));
	}

	@Test
	void testGetAllTicketsBySection() throws Exception {
		// Mocking service response
		List<Ticket> mockTickets = Collections.singletonList(new Ticket());
		when(ticketService.getAllTicketBySection("section1")).thenReturn(mockTickets);

		// Perform the request and assert the response
		mockMvc.perform(get("/api/tickets/details/section").param("section", "section1")).andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$.length()").value(mockTickets.size()));
	}

	@Test
	void testRemoveTicket() throws Exception {
		// Mocking service response
		doNothing().when(ticketService).removeTicket(1L);

		// Perform the request and assert the response
		mockMvc.perform(delete("/api/tickets/remove/1")).andExpect(status().isNoContent());
	}
}
