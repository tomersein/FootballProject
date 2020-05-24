/*
 * This file is generated by jOOQ.
 */
package dataLayer.Tables.tables.records;


import dataLayer.Tables.tables.Fans;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Row1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FansRecord extends UpdatableRecordImpl<FansRecord> implements Record1<String> {

    private static final long serialVersionUID = 1365506959;

    /**
     * Setter for <code>demodb.fans.fanID</code>.
     */
    public void setFanid(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>demodb.fans.fanID</code>.
     */
    public String getFanid() {
        return (String) get(0);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record1 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row1<String> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    @Override
    public Row1<String> valuesRow() {
        return (Row1) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Fans.FANS.FANID;
    }

    @Override
    public String component1() {
        return getFanid();
    }

    @Override
    public String value1() {
        return getFanid();
    }

    @Override
    public FansRecord value1(String value) {
        setFanid(value);
        return this;
    }

    @Override
    public FansRecord values(String value1) {
        value1(value1);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FansRecord
     */
    public FansRecord() {
        super(Fans.FANS);
    }

    /**
     * Create a detached, initialised FansRecord
     */
    public FansRecord(String fanid) {
        super(Fans.FANS);

        set(0, fanid);
    }
}
