package com.sistrans.mundo;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Inventario
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList<MateriaPrima> materiaPrima;
	
	/**
	 * @return the materiaPrima
	 */
	public ArrayList<MateriaPrima> getMateriaPrima() {
		return materiaPrima;
	}

	/**
	 * @param materiaPrima the materiaPrima to set
	 */
	public void setMateriaPrima(ArrayList<MateriaPrima> materiaPrima) {
		this.materiaPrima = materiaPrima;
	}
	public void agregarMateriaPrima(MateriaPrima a)
	{
		materiaPrima.add(a);
	}

	/**
	 * @return the componente
	 */
	public ArrayList<Componente> getComponente() {
		return componente;
	}
	public void agregarComponente(Componente a)
	{
		componente.add(a);
	}
	/**
	 * @param componente the componente to set
	 */
	public void setComponente(ArrayList<Componente> componente) {
		this.componente = componente;
	}

	/**
	 * @return the producto
	 */
	public ArrayList<Producto> getProducto() {
		return producto;
	}
	public void agregarProducto(Producto a)
	{
		producto.add(a);
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(ArrayList<Producto> producto) {
		this.producto = producto;
	}


	public Inventario(ArrayList<MateriaPrima> materiaPrima,
			ArrayList<Componente> componente, ArrayList<Producto> producto) {
		super();
		this.materiaPrima = materiaPrima;
		this.componente = componente;
		this.producto = producto;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList<Componente> componente;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList<Producto> producto;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Inventario(){
		super();
	}

}

