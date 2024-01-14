package com.train.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.train.ticket.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

	@Query("SELECT e FROM Ticket e WHERE e.section = :section")
	List<Ticket> findBySection(String section);
}
