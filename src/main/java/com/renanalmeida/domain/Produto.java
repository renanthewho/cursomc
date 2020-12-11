package com.renanalmeida.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Produto implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Estratégia para gerar ids automaticamente
	private Integer id;
	private String nome;
	private double preco;
	//JsonBackReference indica que a referência foi feita na classe pau
	@JsonBackReference
	//Notação ManyToMany, como o nome ja diz, é devido ao relacionamento de muitos para muitos
	@ManyToMany
	//Notação JoinTable serve para criar uma tabela para "ligar" as entidades Categoria e Produtos
	//Campo name = Nome da Tabela; Campo JoinColumns, o nome do campo correspondente a chave estrangeira da classe
	//Campo name = Nome da Tabela; Campo JoinColumns, o nome do campo correspondente a chave estrangeira da classe
	//inverseJoinColumns = Nome do campo correspondente a chave est5angeira da classe referenciada.
	@JoinTable(name = "PRODUTO_CATEGORIA",
		joinColumns = @JoinColumn(name="produto_id"),
		inverseJoinColumns = @JoinColumn(name="categoria_id")
	)
	//Criar uma lista de categorias pois um produto possui um ou mais categorias.
	private List<Categoria> categorias = new ArrayList<>();

	public Produto(Integer id, String nome, double preco) {
		
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	public Produto() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
