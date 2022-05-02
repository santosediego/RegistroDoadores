package com.santosediego.VidasPorVidas.entities.enums;

public enum GrupoSanguineo {

	A_POSITIVO(1, "A+"),
	B_POSITIVO(2, "B+"),
	AB_POSITIVO(3, "AB+"),
	O_POSITIVO(4, "O+"),
	A_NEGATIVO(5, "A-"),
	B_NEGATIVO(6, "B-"),
	AB_NEGATIVO(7, "AB-"),
	O_NEGATIVO(8, "O-"),
	FALSO_O_NEGATIVO(9, "Falso O-"),
	SANGUE_DOURADO(10, "Sangue dourado");
	
	private int id;
	private String descricao;

	private GrupoSanguineo(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static GrupoSanguineo toEnum (Integer cod) {
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