package com.sistrans.mundo;
import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Proveedor extends Usuario
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String rLnombre;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String rLtipoDoc;
	
	/**
	 * @return the rLnombre
	 */
	public String getrLnombre() {
		return rLnombre;
	}

	/**
	 * @param rLnombre the rLnombre to set
	 */
	public void setrLnombre(String rLnombre) {
		this.rLnombre = rLnombre;
	}

	/**
	 * @return the rLtipoDoc
	 */
	public String getrLtipoDoc() {
		return rLtipoDoc;
	}

	/**
	 * @param rLtipoDoc the rLtipoDoc to set
	 */
	public void setrLtipoDoc(String rLtipoDoc) {
		this.rLtipoDoc = rLtipoDoc;
	}

	/**
	 * @return the rLnumDoc
	 */
	public String getrLnumDoc() {
		return rLnumDoc;
	}

	/**
	 * @param rLnumDoc the rLnumDoc to set
	 */
	public void setrLnumDoc(String rLnumDoc) {
		this.rLnumDoc = rLnumDoc;
	}

	/**
	 * @return the materiasProveedor
	 */
	public Set<MateriasProveedor> getMateriasProveedor() {
		return materiasProveedor;
	}

	/**
	 * @param materiasProveedor the materiasProveedor to set
	 */
	public void setMateriasProveedor(Set<MateriasProveedor> materiasProveedor) {
		this.materiasProveedor = materiasProveedor;
	}

	public Proveedor(String rLnombre, String rLtipoDoc, String rLnumDoc,
			Set<MateriasProveedor> materiasProveedor) {
		super();
		this.rLnombre = rLnombre;
		this.rLtipoDoc = rLtipoDoc;
		this.rLnumDoc = rLnumDoc;
		this.materiasProveedor = materiasProveedor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String rLnumDoc;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Set<MateriasProveedor> materiasProveedor;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Proveedor(){
		super();
	}

}

