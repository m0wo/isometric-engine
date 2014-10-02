package data;

public enum TileType {
	grass("grass"), water("water");
	
	String textureName;
	
	TileType(String textureName){
		this.textureName = textureName;
	}
}
