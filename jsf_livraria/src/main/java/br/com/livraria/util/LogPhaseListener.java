package br.com.livraria.util;

import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;

public class LogPhaseListener implements PhaseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6357411719103591326L;

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
	
	@Override
	public void afterPhase(PhaseEvent event) {
		PhaseListener.super.afterPhase(event);
	}
	
	@Override
	public void beforePhase(PhaseEvent event) {
//		System.out.println("FASE: " + event.getPhaseId());
	}
}
