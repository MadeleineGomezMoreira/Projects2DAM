package dao.modelo;

import lombok.Data;

@Data
public class Wand{
	private String core;
	private String length;
	private String wood;

	public String getCore(){
		return core;
	}

	public String getLength(){
		return length;
	}

	public String getWood(){
		return wood;
	}
}
