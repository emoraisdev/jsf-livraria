package br.com.livraria.util;

import java.util.Arrays;
import java.util.List;

import br.com.livraria.model.Usuario;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;

public class Autorizador implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1432237620270001394L;

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	@Override
	public void afterPhase(PhaseEvent event) {

		FacesContext context = event.getFacesContext();

		String pagina = context.getViewRoot().getViewId();

		if ("/login.xhtml".equals(pagina)) {
			return;
		}

		Usuario usuario = (Usuario) context.getExternalContext()
				.getSessionMap().get("usuarioLogado");
		
		if (usuario != null) {
			return;
		}

		//Redirecionamento para o Login.
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "/login?faces-redirect=true");
		context.renderResponse();
		
		return;

	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}
}
