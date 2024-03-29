package com.esprit.examen.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Operateur implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOperateur;
	private String nom;
	private String prenom;

	private String password;

	public Long getIdOperateur() {
		return idOperateur;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setIdOperateur(Long idOperateur) {
		this.idOperateur = idOperateur;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFactures(Set<Facture> factures) {
		this.factures = factures;
	}

	public String getPassword() {
		return password;
	}

	public Set<Facture> getFactures() {
		return factures;
	}

	@OneToMany
	@JsonIgnore
	private Set<Facture> factures;

}
