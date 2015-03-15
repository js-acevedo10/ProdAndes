package com.sistrans.mundo;
import java.util.Set;
import java.util.HashSet;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Producto
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String nombre;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int costoVenta;
	
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
	 * @return the costoVenta
	 */
	public int getCostoVenta() {
		return costoVenta;
	}

	/**
	 * @param costoVenta the costoVenta to set
	 */
	public void setCostoVenta(int costoVenta) {
		this.costoVenta = costoVenta;
	}

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
	 * @return the etapadeProduccion
	 */
	public Set<EtapadeProduccion> getEtapadeProduccion() {
		return etapadeProduccion;
	}

	/**
	 * @param etapadeProduccion the etapadeProduccion to set
	 */
	public void setEtapadeProduccion(Set<EtapadeProduccion> etapadeProduccion) {
		this.etapadeProduccion = etapadeProduccion;
	}

	/**
	 * @return the pedido
	 */
	public Pedido getPedido() {
		return pedido;
	}

	/**
	 * @param pedido the pedido to set
	 */
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	/**
	 * @return the inventario
	 */
	public Inventario getInventario() {
		return inventario;
	}

	/**
	 * @param inventario the inventario to set
	 */
	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public Producto(String nombre, int costoVenta,
			Set<MateriaPrima> materiaPrima, Set<Componente> componente,
			Set<EtapadeProduccion> etapadeProduccion, Pedido pedido,
			Inventario inventario) {
		super();
		this.nombre = nombre;
		this.costoVenta = costoVenta;
		this.materiaPrima = materiaPrima;
		this.componente = componente;
		this.etapadeProduccion = etapadeProduccion;
		this.pedido = pedido;
		this.inventario = inventario;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Set<MateriaPrima> materiaPrima;
	
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
	
	public Set<EtapadeProduccion> etapadeProduccion;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Pedido pedido;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Inventario inventario;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Producto(){
		super();
	}

}

