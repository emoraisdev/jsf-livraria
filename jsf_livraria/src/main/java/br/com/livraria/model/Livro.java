package br.com.livraria.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "livros")
public class Livro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8895554967865608141L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private String isbn;
	
	private double preco;
	
	private Date dataLancamento;
	
	@ManyToMany
	private List<Autor> autores;
	
	public void adicionarAutor(Autor autor) {
		if (autores == null) {
			autores = new ArrayList<Autor>();
		}
		
		autores.add(autor);
	}
}
