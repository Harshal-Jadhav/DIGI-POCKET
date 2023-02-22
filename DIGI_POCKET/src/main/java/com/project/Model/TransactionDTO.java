package com.project.Model;

import java.time.LocalDate;

public class TransactionDTO {

	private LocalDate from;
	private LocalDate to;

	public TransactionDTO() {
		// TODO Auto-generated constructor stub
	}

	public TransactionDTO(LocalDate from, LocalDate to) {
		this.from = from;
		this.to = to;
	}

	public LocalDate getFrom() {
		return from;
	}

	public void setFrom(LocalDate from) {
		this.from = from;
	}

	public LocalDate getTo() {
		return to;
	}

	public void setTo(LocalDate to) {
		this.to = to;
	}

}
