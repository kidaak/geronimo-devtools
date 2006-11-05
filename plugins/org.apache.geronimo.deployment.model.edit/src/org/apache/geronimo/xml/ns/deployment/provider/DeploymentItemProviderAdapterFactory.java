/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.geronimo.xml.ns.deployment.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.geronimo.xml.ns.deployment.util.DeploymentAdapterFactory;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 *
 * @version $Rev$ $Date$
 */
public class DeploymentItemProviderAdapterFactory extends DeploymentAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable
{
  /**
   * This keeps track of the root adapter factory that delegates to this adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ComposedAdapterFactory parentAdapterFactory;

  /**
   * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IChangeNotifier changeNotifier = new ChangeNotifier();

  /**
   * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Collection supportedTypes = new ArrayList();

  /**
   * This constructs an instance.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public DeploymentItemProviderAdapterFactory()
  {
    supportedTypes.add(IEditingDomainItemProvider.class);
    supportedTypes.add(IStructuredItemContentProvider.class);
    supportedTypes.add(ITreeItemContentProvider.class);
    supportedTypes.add(IItemLabelProvider.class);
    supportedTypes.add(IItemPropertySource.class);		
    supportedTypes.add(ITableItemLabelProvider.class);
  }

  /**
   * This keeps track of the one adapter used for all {@link org.apache.geronimo.xml.ns.deployment.AttributeType} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AttributeTypeItemProvider attributeTypeItemProvider;

  /**
   * This creates an adapter for a {@link org.apache.geronimo.xml.ns.deployment.AttributeType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Adapter createAttributeTypeAdapter()
  {
    if (attributeTypeItemProvider == null)
    {
      attributeTypeItemProvider = new AttributeTypeItemProvider(this);
    }

    return attributeTypeItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.apache.geronimo.xml.ns.deployment.ClassFilterType} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ClassFilterTypeItemProvider classFilterTypeItemProvider;

  /**
   * This creates an adapter for a {@link org.apache.geronimo.xml.ns.deployment.ClassFilterType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Adapter createClassFilterTypeAdapter()
  {
    if (classFilterTypeItemProvider == null)
    {
      classFilterTypeItemProvider = new ClassFilterTypeItemProvider(this);
    }

    return classFilterTypeItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.apache.geronimo.xml.ns.deployment.ConfigurationType} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConfigurationTypeItemProvider configurationTypeItemProvider;

  /**
   * This creates an adapter for a {@link org.apache.geronimo.xml.ns.deployment.ConfigurationType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Adapter createConfigurationTypeAdapter()
  {
    if (configurationTypeItemProvider == null)
    {
      configurationTypeItemProvider = new ConfigurationTypeItemProvider(this);
    }

    return configurationTypeItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.apache.geronimo.xml.ns.deployment.DependencyType} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DependencyTypeItemProvider dependencyTypeItemProvider;

  /**
   * This creates an adapter for a {@link org.apache.geronimo.xml.ns.deployment.DependencyType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Adapter createDependencyTypeAdapter()
  {
    if (dependencyTypeItemProvider == null)
    {
      dependencyTypeItemProvider = new DependencyTypeItemProvider(this);
    }

    return dependencyTypeItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.apache.geronimo.xml.ns.deployment.DocumentRoot} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DocumentRootItemProvider documentRootItemProvider;

  /**
   * This creates an adapter for a {@link org.apache.geronimo.xml.ns.deployment.DocumentRoot}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Adapter createDocumentRootAdapter()
  {
    if (documentRootItemProvider == null)
    {
      documentRootItemProvider = new DocumentRootItemProvider(this);
    }

    return documentRootItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.apache.geronimo.xml.ns.deployment.GbeanType} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GbeanTypeItemProvider gbeanTypeItemProvider;

  /**
   * This creates an adapter for a {@link org.apache.geronimo.xml.ns.deployment.GbeanType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Adapter createGbeanTypeAdapter()
  {
    if (gbeanTypeItemProvider == null)
    {
      gbeanTypeItemProvider = new GbeanTypeItemProvider(this);
    }

    return gbeanTypeItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.apache.geronimo.xml.ns.deployment.PatternType} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PatternTypeItemProvider patternTypeItemProvider;

  /**
   * This creates an adapter for a {@link org.apache.geronimo.xml.ns.deployment.PatternType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Adapter createPatternTypeAdapter()
  {
    if (patternTypeItemProvider == null)
    {
      patternTypeItemProvider = new PatternTypeItemProvider(this);
    }

    return patternTypeItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.apache.geronimo.xml.ns.deployment.ReferencesType} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ReferencesTypeItemProvider referencesTypeItemProvider;

  /**
   * This creates an adapter for a {@link org.apache.geronimo.xml.ns.deployment.ReferencesType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Adapter createReferencesTypeAdapter()
  {
    if (referencesTypeItemProvider == null)
    {
      referencesTypeItemProvider = new ReferencesTypeItemProvider(this);
    }

    return referencesTypeItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.apache.geronimo.xml.ns.deployment.ReferenceType} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ReferenceTypeItemProvider referenceTypeItemProvider;

  /**
   * This creates an adapter for a {@link org.apache.geronimo.xml.ns.deployment.ReferenceType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Adapter createReferenceTypeAdapter()
  {
    if (referenceTypeItemProvider == null)
    {
      referenceTypeItemProvider = new ReferenceTypeItemProvider(this);
    }

    return referenceTypeItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.apache.geronimo.xml.ns.deployment.ServiceType} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ServiceTypeItemProvider serviceTypeItemProvider;

  /**
   * This creates an adapter for a {@link org.apache.geronimo.xml.ns.deployment.ServiceType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Adapter createServiceTypeAdapter()
  {
    if (serviceTypeItemProvider == null)
    {
      serviceTypeItemProvider = new ServiceTypeItemProvider(this);
    }

    return serviceTypeItemProvider;
  }

  /**
   * This keeps track of the one adapter used for all {@link org.apache.geronimo.xml.ns.deployment.XmlAttributeType} instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XmlAttributeTypeItemProvider xmlAttributeTypeItemProvider;

  /**
   * This creates an adapter for a {@link org.apache.geronimo.xml.ns.deployment.XmlAttributeType}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Adapter createXmlAttributeTypeAdapter()
  {
    if (xmlAttributeTypeItemProvider == null)
    {
      xmlAttributeTypeItemProvider = new XmlAttributeTypeItemProvider(this);
    }

    return xmlAttributeTypeItemProvider;
  }

  /**
   * This returns the root adapter factory that contains this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComposeableAdapterFactory getRootAdapterFactory()
  {
    return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
  }

  /**
   * This sets the composed adapter factory that contains this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory)
  {
    this.parentAdapterFactory = parentAdapterFactory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isFactoryForType(Object type)
  {
    return supportedTypes.contains(type) || super.isFactoryForType(type);
  }

  /**
   * This implementation substitutes the factory itself as the key for the adapter.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Adapter adapt(Notifier notifier, Object type)
  {
    return super.adapt(notifier, this);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object adapt(Object object, Object type)
  {
    if (isFactoryForType(type))
    {
      Object adapter = super.adapt(object, type);
      if (!(type instanceof Class) || (((Class)type).isInstance(adapter)))
      {
        return adapter;
      }
    }

    return null;
  }

  /**
   * This adds a listener.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void addListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.addListener(notifyChangedListener);
  }

  /**
   * This removes a listener.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void removeListener(INotifyChangedListener notifyChangedListener)
  {
    changeNotifier.removeListener(notifyChangedListener);
  }

  /**
   * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void fireNotifyChanged(Notification notification)
  {
    changeNotifier.fireNotifyChanged(notification);

    if (parentAdapterFactory != null)
    {
      parentAdapterFactory.fireNotifyChanged(notification);
    }
  }

  /**
   * This disposes all of the item providers created by this factory. 
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void dispose()
  {
    if (attributeTypeItemProvider != null) attributeTypeItemProvider.dispose();
    if (classFilterTypeItemProvider != null) classFilterTypeItemProvider.dispose();
    if (configurationTypeItemProvider != null) configurationTypeItemProvider.dispose();
    if (dependencyTypeItemProvider != null) dependencyTypeItemProvider.dispose();
    if (documentRootItemProvider != null) documentRootItemProvider.dispose();
    if (gbeanTypeItemProvider != null) gbeanTypeItemProvider.dispose();
    if (patternTypeItemProvider != null) patternTypeItemProvider.dispose();
    if (referencesTypeItemProvider != null) referencesTypeItemProvider.dispose();
    if (referenceTypeItemProvider != null) referenceTypeItemProvider.dispose();
    if (serviceTypeItemProvider != null) serviceTypeItemProvider.dispose();
    if (xmlAttributeTypeItemProvider != null) xmlAttributeTypeItemProvider.dispose();
  }

}
