package rva.models;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.OneToMany;

@Entity
@Table(name = "filijala")
public class Filijala implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "filijalaID")
	private long filijalaID;

	@Column(name = "adresa", nullable = false, length = 50)
	private String adresa;

	@Column(name = "broj_pultova", nullable = false)
	@JsonProperty("broj_pultova")
	private int broj_pultova;

	@Column(name = "poseduje_sef", nullable = false)
	@JsonProperty("poseduje_sef")
	private boolean poseduje_sef;

	@ManyToOne
	@JoinColumn(name = "bankaID")
	private Banka banka;
	

	public long getFilijalaID() {
		return filijalaID;
	}

	public void setFilijalaID(long filijalaID) {
		this.filijalaID = filijalaID;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getBrojPultova() {
		return broj_pultova;
	}

	public void setBrojPultova(int broj_pultova) {
		this.broj_pultova = broj_pultova;
	}

	public boolean isPosedujeSef() {
		return poseduje_sef;
	}

	public void setPosedujeSef(boolean poseduje_sef) {
		this.poseduje_sef = poseduje_sef;
	}

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
