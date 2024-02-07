package com.ipartek.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.modelo.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
