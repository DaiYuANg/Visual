/**
 *
 */
package org.visual.graph.editor.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.visual.graph.editor.model.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GraphFactoryImpl extends EFactoryImpl implements GraphFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static GraphFactory init() {
        try {
            GraphFactory theGraphFactory = (GraphFactory) EPackage.Registry.INSTANCE.getEFactory(GraphPackage.eNS_URI);
            if (theGraphFactory != null) {
                return theGraphFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new GraphFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GraphFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        return switch (eClass.getClassifierID()) {
            case GraphPackage.GMODEL -> createGModel();
            case GraphPackage.GNODE -> createGNode();
            case GraphPackage.GCONNECTION -> createGConnection();
            case GraphPackage.GCONNECTOR -> createGConnector();
            case GraphPackage.GJOINT -> createGJoint();
            default ->
                    throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        };
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public GModel createGModel() {
        return new GModelImpl();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public GNode createGNode() {
        return new GNodeImpl();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public GConnection createGConnection() {
        GConnectionImpl gConnection = new GConnectionImpl();
        return gConnection;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public GConnector createGConnector() {
        GConnectorImpl gConnector = new GConnectorImpl();
        return gConnector;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public GJoint createGJoint() {
        GJointImpl gJoint = new GJointImpl();
        return gJoint;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public GraphPackage getGraphPackage() {
        return (GraphPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static GraphPackage getPackage() {
        return GraphPackage.eINSTANCE;
    }

} //GraphFactoryImpl
