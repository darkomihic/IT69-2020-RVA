package rva.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "usluga")
public class Usluga implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uslugaID")
	private long uslugaID;
	
	@Column(name = "naziv", nullable = false, length = 50)
	private String naziv;

	@Column(name = "opis_usluge", nullable = false, length = 50)
	@JsonProperty("opis_usluge")
	private String opis_usluge;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datum_ugovora", nullable = false)
	@JsonProperty("datum_ugovora")
	private Date datum_ugovora;

	@Column(name = "provizija", nullable = false, precision = 5, scale = 2)
	private BigDecimal provizija;

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	

	// Getter and Setter for uslugaID
	public long getUslugaID() {
		return uslugaID;
	}

	public void setUslugaID(long uslugaID) {
		this.uslugaID = uslugaID;
	}

	// Getter and Setter for opisUsluge
	public String getOpisUsluge() {
		return opis_usluge;
	}

	public void setOpisUsluge(String opisUsluge) {
		this.opis_usluge = opisUsluge;
	}

	// Getter and Setter for datumUgovora
	public Date getDatumUgovora() {
		return datum_ugovora;
	}

	public void setDatumUgovora(Date datumUgovora) {
		this.datum_ugovora = datumUgovora;
	}

	// Getter and Setter for provizija
	public BigDecimal getProvizija() {
		return provizija;
	}

	public void setProvizija(BigDecimal provizija) {
		this.provizija = provizija;
	}



}