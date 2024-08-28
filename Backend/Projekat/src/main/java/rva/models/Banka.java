package rva.models;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "banka")
public class Banka implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bankaid")
	private long bankaid;

	@Column(name = "naziv", nullable = false, length = 50)
	private String naziv;

	@Column(name = "kontakt", nullable = false, length = 50)
	private String kontakt;

	@Column(name = "pib", nullable = false)
	private long pib;
	
	@JsonIgnore
	@OneToMany(mappedBy = "banka", fetch = FetchType.LAZY)
	private List<Filijala> filijale;

	public long getBankaID() {
		return bankaid;
	}

	public void setBankaID(long bankaid) {
		this.bankaid = bankaid;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

	public long getPib() {
		return pib;
	}

	public void setPib(long pib) {
		this.pib = pib;
	}

	public List<Filijala> getFilijale() {
		return filijale;
	}

	public void setFilijale(List<Filijala> filijale) {
		this.filijale = filijale;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
