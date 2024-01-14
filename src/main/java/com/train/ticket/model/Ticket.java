package com.train.ticket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_sequence")
	@SequenceGenerator(name = "ticket_sequence", sequenceName = "ticket_sequence", initialValue = 5000)
	private Long ticketId;

	private String section;
	private String fromLocation;
	private String toLocation;
	private double price;

	@ManyToOne
	private Users user;

}
