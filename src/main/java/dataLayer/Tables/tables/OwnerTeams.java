/*
 * This file is generated by jOOQ.
 */
package dataLayer.Tables.tables;


import dataLayer.Tables.Demodb;
import dataLayer.Tables.Indexes;
import dataLayer.Tables.Keys;
import dataLayer.Tables.tables.records.OwnerTeamsRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
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
public class OwnerTeams extends TableImpl<OwnerTeamsRecord> {

    private static final long serialVersionUID = 553168868;

    /**
     * The reference instance of <code>demodb.owner_teams</code>
     */
    public static final OwnerTeams OWNER_TEAMS = new OwnerTeams();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OwnerTeamsRecord> getRecordType() {
        return OwnerTeamsRecord.class;
    }

    /**
     * The column <code>demodb.owner_teams.OwnerID</code>.
     */
    public final TableField<OwnerTeamsRecord, String> OWNERID = createField(DSL.name("OwnerID"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>demodb.owner_teams.teamID</code>.
     */
    public final TableField<OwnerTeamsRecord, String> TEAMID = createField(DSL.name("teamID"), org.jooq.impl.SQLDataType.VARCHAR(50).defaultValue(org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * Create a <code>demodb.owner_teams</code> table reference
     */
    public OwnerTeams() {
        this(DSL.name("owner_teams"), null);
    }

    /**
     * Create an aliased <code>demodb.owner_teams</code> table reference
     */
    public OwnerTeams(String alias) {
        this(DSL.name(alias), OWNER_TEAMS);
    }

    /**
     * Create an aliased <code>demodb.owner_teams</code> table reference
     */
    public OwnerTeams(Name alias) {
        this(alias, OWNER_TEAMS);
    }

    private OwnerTeams(Name alias, Table<OwnerTeamsRecord> aliased) {
        this(alias, aliased, null);
    }

    private OwnerTeams(Name alias, Table<OwnerTeamsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> OwnerTeams(Table<O> child, ForeignKey<O, OwnerTeamsRecord> key) {
        super(child, key, OWNER_TEAMS);
    }

    @Override
    public Schema getSchema() {
        return Demodb.DEMODB;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.OWNER_TEAMS_OWNERID);
    }

    @Override
    public List<ForeignKey<OwnerTeamsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<OwnerTeamsRecord, ?>>asList(Keys.FK_OWNER_TEAMS_SUBSCRIBERS, Keys.TEAMFK);
    }

    public Subscribers subscribers() {
        return new Subscribers(this, Keys.FK_OWNER_TEAMS_SUBSCRIBERS);
    }

    public Teams teams() {
        return new Teams(this, Keys.TEAMFK);
    }

    @Override
    public OwnerTeams as(String alias) {
        return new OwnerTeams(DSL.name(alias), this);
    }

    @Override
    public OwnerTeams as(Name alias) {
        return new OwnerTeams(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public OwnerTeams rename(String name) {
        return new OwnerTeams(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OwnerTeams rename(Name name) {
        return new OwnerTeams(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
