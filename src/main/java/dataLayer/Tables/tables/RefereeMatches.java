/*
 * This file is generated by jOOQ.
 */
package dataLayer.Tables.tables;


import dataLayer.Tables.Demodb;
import dataLayer.Tables.Indexes;
import dataLayer.Tables.Keys;
import dataLayer.Tables.tables.records.RefereeMatchesRecord;

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
public class RefereeMatches extends TableImpl<RefereeMatchesRecord> {

    private static final long serialVersionUID = 1973786023;

    /**
     * The reference instance of <code>demodb.referee_matches</code>
     */
    public static final RefereeMatches REFEREE_MATCHES = new RefereeMatches();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RefereeMatchesRecord> getRecordType() {
        return RefereeMatchesRecord.class;
    }

    /**
     * The column <code>demodb.referee_matches.refereeID</code>.
     */
    public final TableField<RefereeMatchesRecord, String> REFEREEID = createField(DSL.name("refereeID"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>demodb.referee_matches.matchID</code>.
     */
    public final TableField<RefereeMatchesRecord, Integer> MATCHID = createField(DSL.name("matchID"), org.jooq.impl.SQLDataType.INTEGER.defaultValue(org.jooq.impl.DSL.field("NULL", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * Create a <code>demodb.referee_matches</code> table reference
     */
    public RefereeMatches() {
        this(DSL.name("referee_matches"), null);
    }

    /**
     * Create an aliased <code>demodb.referee_matches</code> table reference
     */
    public RefereeMatches(String alias) {
        this(DSL.name(alias), REFEREE_MATCHES);
    }

    /**
     * Create an aliased <code>demodb.referee_matches</code> table reference
     */
    public RefereeMatches(Name alias) {
        this(alias, REFEREE_MATCHES);
    }

    private RefereeMatches(Name alias, Table<RefereeMatchesRecord> aliased) {
        this(alias, aliased, null);
    }

    private RefereeMatches(Name alias, Table<RefereeMatchesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> RefereeMatches(Table<O> child, ForeignKey<O, RefereeMatchesRecord> key) {
        super(child, key, REFEREE_MATCHES);
    }

    @Override
    public Schema getSchema() {
        return Demodb.DEMODB;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.REFEREE_MATCHES_REFEREEID);
    }

    @Override
    public List<ForeignKey<RefereeMatchesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<RefereeMatchesRecord, ?>>asList(Keys.FK_REFEREE_MATCHES_REFEREES, Keys.FK_REFEREE_MATCHES_MATCH);
    }

    public Referees referees() {
        return new Referees(this, Keys.FK_REFEREE_MATCHES_REFEREES);
    }

    public Match match() {
        return new Match(this, Keys.FK_REFEREE_MATCHES_MATCH);
    }

    @Override
    public RefereeMatches as(String alias) {
        return new RefereeMatches(DSL.name(alias), this);
    }

    @Override
    public RefereeMatches as(Name alias) {
        return new RefereeMatches(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RefereeMatches rename(String name) {
        return new RefereeMatches(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RefereeMatches rename(Name name) {
        return new RefereeMatches(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<String, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
