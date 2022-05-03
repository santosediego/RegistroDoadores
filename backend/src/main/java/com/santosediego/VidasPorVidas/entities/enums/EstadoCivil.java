package com.santosediego.VidasPorVidas.entities.enums;

public enum EstadoCivil {
	
	SOLTEIRO("SOL", "Solteiro"),
	CASADO("CAS", "Casado"),
	SEPARADO("SEP", "Separado"),
	DIVORCIADO("DIV", "Divorciado"),
	VIUVO("VIU", "Viúvo");

	private String id;
	private String descrição;

	private EstadoCivil(String id, String descrição) {
		this.id = id;
		this.descrição = descrição;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescrição() {
		return descrição;
	}

	public void setDescrição(String descrição) {
		this.descrição = descrição;
	}

	public static EstadoCivil toEnum (String cod) {
		if(cod == null) {
			return null;
		}

		for (EstadoCivil x : EstadoCivil.values()) {
			if(cod.equals(x.getId())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}