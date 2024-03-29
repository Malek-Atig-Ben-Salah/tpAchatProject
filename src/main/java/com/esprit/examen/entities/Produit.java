package com.esprit.examen.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduit;
	private String codeProduit;
	private String libelleProduit;
	private float prix;
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	@Temporal(TemporalType.DATE)
	private Date dateDerniereModification;
	@ManyToOne
	@JsonIgnore
	private Stock stock;
	@OneToMany(mappedBy = "produit")
	@JsonIgnore
	private Set<DetailFacture> detailFacture;
	@ManyToOne
	@JsonIgnore
	private CategorieProduit categorieProduit;

	public Set<DetailFacture> getDetailFacture() {
		return detailFacture;
	}

	public void setDetailFacture(Set<DetailFacture> detailFacture) {
		this.detailFacture = detailFacture;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}

	public void setDateDerniereModification(Date date) {
		this.dateDerniereModification = date;
	}

	public void setDateCreation(Date date) {
		this.dateCreation =date;
	}

	public void setCategorieProduit(CategorieProduit categorieProduit) {
		this.categorieProduit = categorieProduit;
	}

	public CategorieProduit getCategorieProduit() {
		return categorieProduit;
	}

	public String getCodeProduit() {
		return codeProduit;
	}

	public String getLibelleProduit() {
		return libelleProduit;
	}



	public void setCodeProduit(String codeProduit) {
		this.codeProduit = codeProduit;
	}



	public void setLibelleProduit(String libelleProduit) {
		this.libelleProduit =libelleProduit;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}


	public Object getDateCreation() {
		return this.dateCreation;
	}

	public Object getDateDerniereModification() {
		return  this.dateDerniereModification;
	}
}
