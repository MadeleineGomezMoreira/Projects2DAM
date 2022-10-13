package dao.modelo;

import lombok.Data;

@Data
public class ResponseSpelltem {
	private String name;
	private String description;

	public String getName(){
		return name;
	}

	public String getDescription(){
		return description;
	}
}
