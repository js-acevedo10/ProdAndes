package com.sistrans.mundo;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Componente
{
	public Componente(String nombre, int numInventario, String unidadMedida,
			Producto producto, MateriasProveedor materiasProveedor,
			Inventario inventario) {
		super();
		this.nombre = nombre;
		this.numInventario = numInventario;
		this.unidadMedida = unidadMedida;
		this.producto = producto;
		this.materiasProveedor = materiasProveedor;
		this.inventario = inventario;
	}

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
	 * @return the numInventario
	 */
	public int getNumInventario() {
		return numInventario;
	}

	/**
	 * @param numInventario the numInventario to set
	 */
	public void setNumInventario(int numInventario) {
		this.numInventario = numInventario;
	}

	/**
	 * @return the unidadMedida
	 */
	public String getUnidadMedida() {
		return unidadMedida;
	}

	/**
	 * @param unidadMedida the unidadMedida to set
	 */
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return the materiasProveedor
	 */
	public MateriasProveedor getMateriasProveedor() {
		return materiasProveedor;
	}

	/**
	 * @param materiasProveedor the materiasProveedor to set
	 */
	public void setMateriasProveedor(MateriasProveedor materiasProveedor) {
		this.materiasProveedor = materiasProveedor;
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
	
	private int numInventario;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String unidadMedida;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Producto producto;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public MateriasProveedor materiasProveedor;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Inventario inventario;
	

}

