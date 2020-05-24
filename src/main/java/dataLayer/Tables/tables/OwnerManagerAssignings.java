/*
 * This file is generated by jOOQ.
 */
package dataLayer.Tables.tables;


import dataLayer.Tables.Demodb;
import dataLayer.Tables.Indexes;
import dataLayer.Tables.Keys;
import dataLayer.Tables.tables.records.OwnerManagerAssigningsRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OwnerManagerAssignings extends TableImpl<OwnerManagerAssigningsRecord> {

    private static final long serialVersionUID = -2050224959;

    /**
     * The reference instance of <code>demodb.owner_manager_assignings</code>
     */
    public static final OwnerManagerAssignings OWNER_MANAGER_ASSIGNINGS = new OwnerManagerAssignings();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OwnerManagerAssigningsRecord> getRecordType() {
        return OwnerManagerAssigningsRecord.class;
    }

    /**
     * The column <code>demodb.owner_manager_assignings.ownerID</code>.
     */
    public final TableField<OwnerManagerAssigningsRecord, String> OWNERID = createField(DSL.name("ownerID"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>demodb.owner_manager_assignings.teamID</code>.
     */
    public final TableField<OwnerManagerAssigningsRecord, String> TEAMID = createField(DSL.name("teamID"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>demodb.owner_manager_assignings.teamManagerID</code>.
     */
    public final TableField<OwnerManagerAssigningsRecord, String> TEAMMANAGERID = createField(DSL.name("teamManagerID"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * Create a <code>demodb.owner_manager_assignings</code> table reference
     */
    public OwnerManagerAssignings() {
        this(DSL.name("owner_manager_assignings"), null);
    }

    /**
     * Create an aliased <code>demodb.owner_manager_assignings</code> table reference
     */
    public OwnerManagerAssignings(String alias) {
        this(DSL.name(alias), OWNER_MANAGER_ASSIGNINGS);
    }

    /**
     * Create an aliased <code>demodb.owner_manager_assignings</code> table reference
     */
    public OwnerManagerAssignings(Name alias) {
        this(alias, OWNER_MANAGER_ASSIGNINGS);
    }

    private OwnerManagerAssignings(Name alias, Table<OwnerManagerAssigningsRecord> aliased) {
        this(alias, aliased, null);
    }

    private OwnerManagerAssignings(Name alias, Table<OwnerManagerAssigningsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> OwnerManagerAssignings(Table<O> child, ForeignKey<O, OwnerManagerAssigningsRecord> key) {
        super(child, key, OWNER_MANAGER_ASSIGNINGS);
    }

    @Override
    public Schema getSchema() {
        return Demodb.DEMODB;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.OWNER_MANAGER_ASSIGNINGS_OWNERID);
    }

    @Override
    public List<ForeignKey<OwnerManagerAssigningsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<OwnerManagerAssigningsRecord, ?>>asList(Keys.FK_OWNER_MANAGER_ASSIGNINGS_TEAMS, Keys.FK_OWNER_MANAGER_ASSIGNINGS_TEAMMANAGERS);
    }

    public Teams teams() {
        return new Teams(this, Keys.FK_OWNER_MANAGER_ASSIGNINGS_TEAMS);
    }

    public Teammanagers teammanagers() {
        return new Teammanagers(this, Keys.FK_OWNER_MANAGER_ASSIGNINGS_TEAMMANAGERS);
    }

    @Override
    public OwnerManagerAssignings as(String alias) {
        return new OwnerManagerAssignings(DSL.name(alias), this);
    }

    @Override
    public OwnerManagerAssignings as(Name alias) {
        return new OwnerManagerAssignings(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public OwnerManagerAssignings rename(String name) {
        return new OwnerManagerAssignings(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OwnerManagerAssignings rename(Name name) {
        return new OwnerManagerAssignings(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
