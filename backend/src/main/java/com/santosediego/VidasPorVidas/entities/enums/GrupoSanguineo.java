package com.santosediego.VidasPorVidas.entities.enums;

public enum GrupoSanguineo {

	A_POSITIVO("A+", "A+"),
	B_POSITIVO("B+", "B+"),
	AB_POSITIVO("AB+", "AB+"),
	O_POSITIVO("O+", "O+"),
	A_NEGATIVO("A-", "A-"),
	B_NEGATIVO("B-", "B-"),
	AB_NEGATIVO("AB-", "AB-"),
	O_NEGATIVO("O-", "O-"),
	FALSO_O_NEGATIVO("FO-", "Falso O-"),
	SANGUE_DOURADO("SD", "Sangue dourado");
	
	private String id;
	private String descricao;

	private GrupoSanguineo(String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static GrupoSanguineo toEnum (String cod) {
		if(cod == null) {
			return null;
		}

		for (GrupoSanguineo x : GrupoSanguineo.values()) {
			if(cod.equals(x.getId())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}