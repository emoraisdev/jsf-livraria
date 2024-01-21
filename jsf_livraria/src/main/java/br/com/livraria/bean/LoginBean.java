package br.com.livraria.bean;

import java.io.Serializable;

import br.com.livraria.dao.UsuarioDAO;
import br.com.livraria.model.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;

@Named
@ViewScoped
@Data
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6781170508428482625L;

	private Usuario usuario;
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public String logar() {

		FacesContext context = FacesContext.getCurrentInstance();
		if (new UsuarioDAO().existe(usuario)) {

			context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);

			return "livro?faces-redirect=true";
		}

		init();
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário não encontrado"));

		return "login?faces-redirect=true";

	}
	
	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");

		return "login?faces-redirect=true";
	}
}
