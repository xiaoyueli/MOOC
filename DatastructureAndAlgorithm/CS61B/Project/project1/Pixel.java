package project1;

class Pixel {
	private short red;
	private short green;
	private short blue;
	
	Pixel(short red, short green, short blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	Pixel(Pixel obj){
		this(obj.red, obj.green, obj.blue);
	}

	short getRed() {
		return red;
	}

	void setRed(short red) {
		this.red = red;
	}

	short getGreen() {
		return green;
	}

	void setGreen(short green) {
		this.green = green;
	}

	short getBlue() {
		return blue;
	}

	void setBlue(short blue) {
		this.blue = blue;
	}
	
	boolean equals(Pixel other){
		if(red == other.getRed() && green == other.getGreen() && blue == other.getBlue()){
			return true;
		}
		return false;
	}
	
	public String toString(){
		return "(" + red + ", " + green + ", " + blue + ")" ;
	}

}
