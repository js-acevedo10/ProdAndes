package com.sistrans.mundo;
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
	
	public Set<MateriaPrima> materiaPrima;
	
	/**
	 * @return the materiaPrima
	 */
	public Set<MateriaPrima> getMateriaPrima() {
		return materiaPrima;
	}

	/**
	 * @param materiaPrima the materiaPrima to set
	 */
	public void setMateriaPrima(Set<MateriaPrima> materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	/**
	 * @return the componente
	 */
	public Set<Componente> getComponente() {
		return componente;
	}

	/**
	 * @param componente the componente to set
	 */
	public void setComponente(Set<Componente> componente) {
		this.componente = componente;
	}

	/**
	 * @return the producto
	 */
	public Set<Producto> getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Set<Producto> producto) {
		this.producto = producto;
	}

	/**
	 * @return the administrador
	 */
	public Set<Administrador> getAdministrador() {
		return administrador;
	}

	/**
	 * @param administrador the administrador to set
	 */
	public void setAdministrador(Set<Administrador> administrador) {
		this.administrador = administrador;
	}

	public Inventario(Set<MateriaPrima> materiaPrima,
			Set<Componente> componente, Set<Producto> producto,
			Set<Administrador> administrador) {
		super();
		this.materiaPrima = materiaPrima;
		this.componente = componente;
		this.producto = producto;
		this.administrador = administrador;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Set<Componente> componente;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Set<Producto> producto;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Set<Administrador> administrador;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Inventario(){
		super();
	}

}

