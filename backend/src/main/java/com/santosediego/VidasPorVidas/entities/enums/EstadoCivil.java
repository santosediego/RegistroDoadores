package com.santosediego.VidasPorVidas.entities.enums;

public enum EstadoCivil {
	
	SOLTEIRO(1, "Solteiro"),
	CASADO(2, "Casado"),
	SEPARADO(3, "Separado"),
	DIVORCIADO(4, "Divorciado"),
	VIUVO(5, "Viúvo");

	private int id;
	private String descrição;

	private EstadoCivil(int id, String descrição) {
		this.id = id;
		this.descrição = descrição;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrição() {
		return descrição;
	}

	public void setDescrição(String descrição) {
		this.descrição = descrição;
	}

	public static EstadoCivil toEnum (Integer cod) {
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