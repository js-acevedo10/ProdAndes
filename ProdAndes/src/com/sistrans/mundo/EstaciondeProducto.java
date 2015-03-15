package com.sistrans.mundo;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class EstaciondeProducto
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String codigo;
	
	public EstaciondeProducto(String codigo, String numEtapadeProduccion,
			int capacidadProduccion, Operario operario,
			EtapadeProduccion etapadeProduccion) {
		super();
		this.codigo = codigo;
		this.numEtapadeProduccion = numEtapadeProduccion;
		this.capacidadProduccion = capacidadProduccion;
		this.operario = operario;
		this.etapadeProduccion = etapadeProduccion;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the numEtapadeProduccion
	 */
	public String getNumEtapadeProduccion() {
		return numEtapadeProduccion;
	}

	/**
	 * @param numEtapadeProduccion the numEtapadeProduccion to set
	 */
	public void setNumEtapadeProduccion(String numEtapadeProduccion) {
		this.numEtapadeProduccion = numEtapadeProduccion;
	}

	/**
	 * @return the capacidadProduccion
	 */
	public int getCapacidadProduccion() {
		return capacidadProduccion;
	}

	/**
	 * @param capacidadProduccion the capacidadProduccion to set
	 */
	public void setCapacidadProduccion(int capacidadProduccion) {
		this.capacidadProduccion = capacidadProduccion;
	}

	/**
	 * @return the operario
	 */
	public Operario getOperario() {
		return operario;
	}

	/**
	 * @param operario the operario to set
	 */
	public void setOperario(Operario operario) {
		this.operario = operario;
	}

	/**
	 * @return the etapadeProduccion
	 */
	public EtapadeProduccion getEtapadeProduccion() {
		return etapadeProduccion;
	}

	/**
	 * @param etapadeProduccion the etapadeProduccion to set
	 */
	public void setEtapadeProduccion(EtapadeProduccion etapadeProduccion) {
		this.etapadeProduccion = etapadeProduccion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String numEtapadeProduccion;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int capacidadProduccion;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Operario operario;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public EtapadeProduccion etapadeProduccion;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public EstaciondeProducto(){
		super();
	}

}

