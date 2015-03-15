package com.sistrans.mundo;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Pedido
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String iD;
	
	/**
	 * @return the iD
	 */
	public String getiD() {
		return iD;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setiD(String iD) {
		this.iD = iD;
	}

	/**
	 * @return the estadoPago
	 */
	public String getEstadoPago() {
		return estadoPago;
	}

	/**
	 * @param estadoPago the estadoPago to set
	 */
	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the deadline
	 */
	public String getDeadline() {
		return deadline;
	}

	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	/**
	 * @return the fechaRecibido
	 */
	public Date getFechaRecibido() {
		return fechaRecibido;
	}

	/**
	 * @param fechaRecibido the fechaRecibido to set
	 */
	public void setFechaRecibido(Date fechaRecibido) {
		this.fechaRecibido = fechaRecibido;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the producto
	 */
	public ArrayList<Producto> getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(ArrayList<Producto> producto) {
		this.producto = producto;
	}
	public void agregarProducto(Producto a)
	{
		producto.add(a);
	}

	/**
	 * @return the materiasProveedor
	 */
	public ArrayList<MateriasProveedor> getMateriasProveedor() {
		return materiasProveedor;
	}
	public void agregarMateirasProveedor(MateriasProveedor a)
	{
		materiasProveedor.add(a);
	}

	/**
	 * @param materiasProveedor the materiasProveedor to set
	 */
	public void setMateriasProveedor(ArrayList<MateriasProveedor> materiasProveedor) {
		this.materiasProveedor = materiasProveedor;
	}

	/**
	 * @return the gerentedeProduccion
	 */
	public GerentedeProduccion getGerentedeProduccion() {
		return gerentedeProduccion;
	}

	/**
	 * @param gerentedeProduccion the gerentedeProduccion to set
	 */
	public void setGerentedeProduccion(GerentedeProduccion gerentedeProduccion) {
		this.gerentedeProduccion = gerentedeProduccion;
	}

	public Pedido(String iD, String estadoPago, Date fechaCreacion,
			String deadline, Date fechaRecibido, Cliente cliente,
			ArrayList<Producto> producto, ArrayList<MateriasProveedor> materiasProveedor,
			GerentedeProduccion gerentedeProduccion) {
		super();
		this.iD = iD;
		this.estadoPago = estadoPago;
		this.fechaCreacion = fechaCreacion;
		this.deadline = deadline;
		this.fechaRecibido = fechaRecibido;
		this.cliente = cliente;
		this.producto = producto;
		this.materiasProveedor = materiasProveedor;
		this.gerentedeProduccion = gerentedeProduccion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String estadoPago;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Date fechaCreacion;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String deadline;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Date fechaRecibido;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Cliente cliente;
	
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
	
	public ArrayList<MateriasProveedor> materiasProveedor;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public GerentedeProduccion gerentedeProduccion;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Pedido(){
		super();
	}

}

