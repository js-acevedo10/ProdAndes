package com.sistrans.mundo;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;


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
	
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
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
	public EtapadeProduccion(int num, String nombre) {
		super();
		this.num = num;
		this.nombre = nombre;
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

}

