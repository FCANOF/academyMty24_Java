package com.curso.v8;

public class Resta extends Operacion{
	
	Resta(int x,int y){
		super(x,y);
	}
	
	@Override
	int ejecuta(){
		return x - y;
	}
	
}
