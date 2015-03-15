package com.sistrans.mundo;
import java.util.ArrayList;
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

	/**
	 * @param componente the componente to set
	 */
	public void setComponente(ArrayList<Componente> componente) {
		this.componente = componente;
	}
	public void agregarComponente(Componente a)
	{
		componente.add(a);
	}

	/**
	 * @return the etapadeProduccion
	 */
	public ArrayList<EtapadeProduccion> getEtapadeProduccion() {
		return etapadeProduccion;
	}

	/**
	 * @param etapadeProduccion the etapadeProduccion to set
	 */
	public void setEtapadeProduccion(ArrayList<EtapadeProduccion> etapadeProduccion) {
		this.etapadeProduccion = etapadeProduccion;
	}
	public void agregarEtapaProduccion(EtapadeProduccion a)
	{
		etapadeProduccion.add(a);
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
			ArrayList<MateriaPrima> materiaPrima, ArrayList<Componente> componente,
			ArrayList<EtapadeProduccion> etapadeProduccion, Pedido pedido,
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
	
	public ArrayList<MateriaPrima> materiaPrima;
	
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
	
	public ArrayList<EtapadeProduccion> etapadeProduccion;
	
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

