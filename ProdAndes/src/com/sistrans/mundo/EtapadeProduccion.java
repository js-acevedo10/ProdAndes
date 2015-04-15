package com.sistrans.mundo;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class EtapadeProduccion
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int num;
	
	private boolean estado;
	
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	
	public boolean getEstado() {
		return estado;
	}
	
	public void setEstado(boolean x) {
		estado = x;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * @return the estaciondeProducto
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param estaciondeProducto the estaciondeProducto to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public EtapadeProduccion(int num, String nombre, boolean estado) {
		super();
		this.num = num;
		this.nombre = nombre;
		this.estado = estado;
	}
	
private String nombre;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public EtapadeProduccion(){
		super();
	}

	public EtapadeProduccion(int num2, String nombreT) {
		this.num = num2;
		nombre = nombreT;
	}

}

