/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.master.holiday;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.joda.beans.BeanDefinition;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectMetaProperty;

import com.opengamma.core.holiday.Holiday;
import com.opengamma.id.Identifier;
import com.opengamma.id.UniqueIdentifier;
import com.opengamma.master.AbstractDocument;
import com.opengamma.util.ArgumentChecker;

/**
 * A document used to pass into and out of the holiday master.
 * <p>
 * The holiday master provides full management of the holiday database.
 * Each element is stored in a document.
 */
@BeanDefinition
public class HolidayDocument extends AbstractDocument implements Serializable {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The holiday unique identifier.
   * This field is managed by the master but must be set for updates.
   */
  @PropertyDefinition
  private UniqueIdentifier _uniqueId;
  /**
   * The name of the holiday.
   */
  @PropertyDefinition
  private String _name;
  /**
   * The provider key identifier for the data.
   * This optional field can be used to capture the identifier used by the data provider.
   * This can be useful when receiving updates from the same provider.
   */
  @PropertyDefinition
  private Identifier _providerKey;
  /**
   * The holiday.
   */
  @PropertyDefinition
  private ManageableHoliday _holiday;

  /**
   * Creates an instance.
   */
  public HolidayDocument() {
  }

  /**
   * Creates an instance from a holiday.
   * <p>
   * This will call {@link #createName()} to build a suitable name.
   * 
   * @param holiday  the holiday, not null
   */
  public HolidayDocument(final Holiday holiday) {
    ArgumentChecker.notNull(holiday, "holiday");
    setUniqueId(holiday.getUniqueId());
    setHoliday(new ManageableHoliday(holiday));
    createName();
  }

  //-------------------------------------------------------------------------
  /**
   * Creates a name based on the holiday.
   */
  public void createName() {
    ManageableHoliday holiday = getHoliday();
    switch (holiday.getType()) {
      case BANK:
        setName(holiday.getRegionKey().getValue());
        break;
      case CURRENCY:
        setName(holiday.getCurrency().getISOCode());
        break;
      case SETTLEMENT:
      case TRADING:
        setName(holiday.getExchangeKey().getValue());
        break;
      default:
        throw new IllegalArgumentException("Unsupported holiday type");
    }
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code HolidayDocument}.
   * @return the meta-bean, not null
   */
  public static HolidayDocument.Meta meta() {
    return HolidayDocument.Meta.INSTANCE;
  }

  @Override
  public HolidayDocument.Meta metaBean() {
    return HolidayDocument.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName) {
    switch (propertyName.hashCode()) {
      case -294460212:  // uniqueId
        return getUniqueId();
      case 3373707:  // name
        return getName();
      case 2064682670:  // providerKey
        return getProviderKey();
      case 1091905624:  // holiday
        return getHoliday();
    }
    return super.propertyGet(propertyName);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue) {
    switch (propertyName.hashCode()) {
      case -294460212:  // uniqueId
        setUniqueId((UniqueIdentifier) newValue);
        return;
      case 3373707:  // name
        setName((String) newValue);
        return;
      case 2064682670:  // providerKey
        setProviderKey((Identifier) newValue);
        return;
      case 1091905624:  // holiday
        setHoliday((ManageableHoliday) newValue);
        return;
    }
    super.propertySet(propertyName, newValue);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the holiday unique identifier.
   * This field is managed by the master but must be set for updates.
   * @return the value of the property
   */
  public UniqueIdentifier getUniqueId() {
    return _uniqueId;
  }

  /**
   * Sets the holiday unique identifier.
   * This field is managed by the master but must be set for updates.
   * @param uniqueId  the new value of the property
   */
  public void setUniqueId(UniqueIdentifier uniqueId) {
    this._uniqueId = uniqueId;
  }

  /**
   * Gets the the {@code uniqueId} property.
   * This field is managed by the master but must be set for updates.
   * @return the property, not null
   */
  public final Property<UniqueIdentifier> uniqueId() {
    return metaBean().uniqueId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the name of the holiday.
   * @return the value of the property
   */
  public String getName() {
    return _name;
  }

  /**
   * Sets the name of the holiday.
   * @param name  the new value of the property
   */
  public void setName(String name) {
    this._name = name;
  }

  /**
   * Gets the the {@code name} property.
   * @return the property, not null
   */
  public final Property<String> name() {
    return metaBean().name().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the provider key identifier for the data.
   * This optional field can be used to capture the identifier used by the data provider.
   * This can be useful when receiving updates from the same provider.
   * @return the value of the property
   */
  public Identifier getProviderKey() {
    return _providerKey;
  }

  /**
   * Sets the provider key identifier for the data.
   * This optional field can be used to capture the identifier used by the data provider.
   * This can be useful when receiving updates from the same provider.
   * @param providerKey  the new value of the property
   */
  public void setProviderKey(Identifier providerKey) {
    this._providerKey = providerKey;
  }

  /**
   * Gets the the {@code providerKey} property.
   * This optional field can be used to capture the identifier used by the data provider.
   * This can be useful when receiving updates from the same provider.
   * @return the property, not null
   */
  public final Property<Identifier> providerKey() {
    return metaBean().providerKey().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the holiday.
   * @return the value of the property
   */
  public ManageableHoliday getHoliday() {
    return _holiday;
  }

  /**
   * Sets the holiday.
   * @param holiday  the new value of the property
   */
  public void setHoliday(ManageableHoliday holiday) {
    this._holiday = holiday;
  }

  /**
   * Gets the the {@code holiday} property.
   * @return the property, not null
   */
  public final Property<ManageableHoliday> holiday() {
    return metaBean().holiday().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code HolidayDocument}.
   */
  public static class Meta extends AbstractDocument.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code uniqueId} property.
     */
    private final MetaProperty<UniqueIdentifier> _uniqueId = DirectMetaProperty.ofReadWrite(this, "uniqueId", UniqueIdentifier.class);
    /**
     * The meta-property for the {@code name} property.
     */
    private final MetaProperty<String> _name = DirectMetaProperty.ofReadWrite(this, "name", String.class);
    /**
     * The meta-property for the {@code providerKey} property.
     */
    private final MetaProperty<Identifier> _providerKey = DirectMetaProperty.ofReadWrite(this, "providerKey", Identifier.class);
    /**
     * The meta-property for the {@code holiday} property.
     */
    private final MetaProperty<ManageableHoliday> _holiday = DirectMetaProperty.ofReadWrite(this, "holiday", ManageableHoliday.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map;

    @SuppressWarnings({"unchecked", "rawtypes" })
    protected Meta() {
      LinkedHashMap temp = new LinkedHashMap(super.metaPropertyMap());
      temp.put("uniqueId", _uniqueId);
      temp.put("name", _name);
      temp.put("providerKey", _providerKey);
      temp.put("holiday", _holiday);
      _map = Collections.unmodifiableMap(temp);
    }

    @Override
    public HolidayDocument createBean() {
      return new HolidayDocument();
    }

    @Override
    public Class<? extends HolidayDocument> beanType() {
      return HolidayDocument.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code uniqueId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<UniqueIdentifier> uniqueId() {
      return _uniqueId;
    }

    /**
     * The meta-property for the {@code name} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> name() {
      return _name;
    }

    /**
     * The meta-property for the {@code providerKey} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Identifier> providerKey() {
      return _providerKey;
    }

    /**
     * The meta-property for the {@code holiday} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ManageableHoliday> holiday() {
      return _holiday;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
