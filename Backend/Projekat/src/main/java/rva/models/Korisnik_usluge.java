package rva.models;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "korisnik_usluge")
public class Korisnik_usluge implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "korisnik_uslugeID")
	@JsonProperty("korisnik_uslugeID")
	private long korisnik_uslugeID;

	@Column(name = "ime", nullable = false, length = 50)
	private String ime;

	@Column(name = "prezime", nullable = false, length = 50)
	private String prezime;

	@Column(name = "maticni_broj", nullable = false, length = 50)
	@JsonProperty("maticni_broj")
	private String maticni_broj;
	
	@ManyToOne
	@JoinColumn(name = "filijalaID")
	private Filijala filijala;
	
	@ManyToOne
	@JoinColumn(name = "uslugaID")
	private Usluga usluga;
	
	public Usluga getUsluga() {
		return usluga;
	}
	
	public void setUsluga(Usluga usluga) {
		this.usluga = usluga;
	}
	
	public Filijala getFilijala() {
		return filijala;
	}
	
	public void setFilijala(Filijala filijala) {
		this.filijala = filijala;
	}
	
	public long getKorisnikUslugeID() {
		return korisnik_uslugeID;
	}

	public void setKorisnikUslugeID(long korisnikUslugeID) {
		this.korisnik_uslugeID = korisnikUslugeID;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getMaticniBroj() {
		return maticni_broj;
	}

	public void setMaticniBroj(String maticniBroj) {
		this.maticni_broj = maticniBroj;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
