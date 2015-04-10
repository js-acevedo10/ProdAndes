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



	public Pedido(String iD, String idCliente, String estadoPago, Date fechaCreacion, Date fechaRecibido, Date deadLine, String anotaciones, String idMateriaPrima, int numMateriaPrima, String idComponente, int numComponente, String idProducto, int numProducto) {
		super();
		 this.id = iD;
		 this.setIdCliente(idCliente);
		 this.setEstadoPago(estadoPago);
		 this.setFechaCreacion(fechaCreacion);
		 this.setFechaRecibido(fechaRecibido);
		 this.setDeadLine(deadLine);
		 this.setAnotaciones(anotaciones);
		 this.setIdMateriaPrima(idMateriaPrima);
		 this.setNumMateriaPrima(numMateriaPrima);
		 this.setIdComponente(idComponente);
		 this.setNumComponente(numComponente);
		 this.setIdProducto(idProducto);
		 this.setNumProducto(numProducto);
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaRecibido() {
		return fechaRecibido;
	}

	public void setFechaRecibido(Date fechaRecibido) {
		this.fechaRecibido = fechaRecibido;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public String getAnotaciones() {
		return anotaciones;
	}

	public void setAnotaciones(String anotaciones) {
		this.anotaciones = anotaciones;
	}

	public String getIdMateriaPrima() {
		return idMateriaPrima;
	}

	public void setIdMateriaPrima(String idMateriaPrima) {
		this.idMateriaPrima = idMateriaPrima;
	}

	public int getNumMateriaPrima() {
		return numMateriaPrima;
	}

	public void setNumMateriaPrima(int numMateriaPrima) {
		this.numMateriaPrima = numMateriaPrima;
	}

	public int getNumComponente() {
		return numComponente;
	}

	public void setNumComponente(int numComponente) {
		this.numComponente = numComponente;
	}

	public String getIdComponente() {
		return idComponente;
	}

	public void setIdComponente(String idComponente) {
		this.idComponente = idComponente;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public int getNumProducto() {
		return numProducto;
	}

	public void setNumProducto(int numProducto) {
		this.numProducto = numProducto;
	}

	private String id;
	private String idCliente;
	private String estadoPago;
	private Date fechaCreacion;
	private Date fechaRecibido;
	private Date deadLine;
	private String anotaciones;
	private String idMateriaPrima;
	private int numMateriaPrima;
	private String idComponente;
	private int numComponente;
	private String idProducto;
	private int numProducto;


}

