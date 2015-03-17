package com.sistrans.mundo;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class MateriaPrima
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String nombre;
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return the tonelada
	 */
	public int getTonelada() {
		return tonelada;
	}

	/**
	 * @param tonelada the tonelada to set
	 */
	public void setTonelada(int tonelada) {
		this.tonelada = tonelada;
	}
	






	public MateriaPrima(String nombre, int tonelada) {
		super();
		this.nombre = nombre;
		this.tonelada = tonelada;
	}

	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int tonelada;
	


	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public MateriaPrima(){
		super();
	}

}

