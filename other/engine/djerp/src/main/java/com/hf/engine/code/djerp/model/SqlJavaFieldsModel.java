package com.hf.engine.code.djerp.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库字段类型映射java类型实体
 * Created by rain on 2017/6/4.
 */
@Component
public class SqlJavaFieldsModel implements Serializable{

    private static final long serialVersionUID = 1350365094321851322L;
    /**
     *
     */
    @Column(name="TABLE_CATALOG")
    private String tableCatalog;

    /**
     *
     */
    @Column(name="TABLE_SCHEMA")
    private String tableSchema;

    /**
     *
     */
    @Column(name="TABLE_NAME")
    private String tableName;

    /**
     *
     */
    @Column(name="COLUMN_NAME")
    private String columnName;

    /**
     *
     */
    @Column(name="ORDINAL_POSITION")
    private String ordinalPosition;

    /**
     *
     */
    @Column(name="COLUMN_DEFAULT")
    private String columnDefault;

    /**
     *
     */
    @Column(name="IS_NULLABLE")
    private String isNullable;

    /**
     *
     */
    @Column(name="DATA_TYPE")
    private String dataType;

    /**
     *
     */
    @Column(name="CHARACTER_MAXIMUM_LENGTH")
    private String characterMaximumLength;

    /**
     *
     */
    @Column(name="CHARACTER_OCTET_LENGTH")
    private String characterOctetLength;

    /**
     *
     */
    @Column(name="NUMERIC_PRECISION")
    private String numericPrecision;

    /**
     *
     */
    @Column(name="NUMERIC_SCALE")
    private String numericScale;

    /**
     *
     */
    @Column(name="DATETIME_PRECISION")
    private String datetimePrecision;

    /**
     *
     */
    @Column(name="CHARACTER_SET_NAME")
    private String characterSetName;

    /**
     *
     */
    @Column(name="COLLATION_NAME")
    private String collationName;

    /**
     *
     */
    @Column(name="COLUMN_TYPE")
    private String columnType;

    /**
     *
     */
    @Column(name="COLUMN_KEY")
    private String columnKey;

    /**
     *
     */
    @Column(name="EXTRA")
    private String extra;

    /**
     *
     */
    @Column(name="PRIVILEGES")
    private String privileges;

    /**
     *
     */
    @Column(name="COLUMN_COMMENT")
    private String columnComment;

    /**
     *
     */
    @Column(name="GENERATION_EXPRESSION")
    private String generationExpression;

    public String getTableCatalog() {
        return this.tableCatalog;
    }

    public void setTableCatalog () {
        this.tableCatalog = tableCatalog;
    }

    public String getTableSchema() {
        return this.tableSchema;
    }

    public void setTableSchema () {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName () {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName () {
        this.columnName = columnName;
    }

    public String getOrdinalPosition() {
        return this.ordinalPosition;
    }

    public void setOrdinalPosition () {
        this.ordinalPosition = ordinalPosition;
    }

    public String getColumnDefault() {
        return this.columnDefault;
    }

    public void setColumnDefault () {
        this.columnDefault = columnDefault;
    }

    public String getIsNullable() {
        return this.isNullable;
    }

    public void setIsNullable () {
        this.isNullable = isNullable;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType () {
        this.dataType = dataType;
    }

    public String getCharacterMaximumLength() {
        return this.characterMaximumLength;
    }

    public void setCharacterMaximumLength () {
        this.characterMaximumLength = characterMaximumLength;
    }

    public String getCharacterOctetLength() {
        return this.characterOctetLength;
    }

    public void setCharacterOctetLength () {
        this.characterOctetLength = characterOctetLength;
    }

    public String getNumericPrecision() {
        return this.numericPrecision;
    }

    public void setNumericPrecision () {
        this.numericPrecision = numericPrecision;
    }

    public String getNumericScale() {
        return this.numericScale;
    }

    public void setNumericScale () {
        this.numericScale = numericScale;
    }

    public String getDatetimePrecision() {
        return this.datetimePrecision;
    }

    public void setDatetimePrecision () {
        this.datetimePrecision = datetimePrecision;
    }

    public String getCharacterSetName() {
        return this.characterSetName;
    }

    public void setCharacterSetName () {
        this.characterSetName = characterSetName;
    }

    public String getCollationName() {
        return this.collationName;
    }

    public void setCollationName () {
        this.collationName = collationName;
    }

    public String getColumnType() {
        return this.columnType;
    }

    public void setColumnType () {
        this.columnType = columnType;
    }

    public String getColumnKey() {
        return this.columnKey;
    }

    public void setColumnKey () {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra () {
        this.extra = extra;
    }

    public String getPrivileges() {
        return this.privileges;
    }

    public void setPrivileges () {
        this.privileges = privileges;
    }

    public String getColumnComment() {
        return this.columnComment;
    }

    public void setColumnComment () {
        this.columnComment = columnComment;
    }

    public String getGenerationExpression() {
        return this.generationExpression;
    }

    public void setGenerationExpression () {
        this.generationExpression = generationExpression;
    }



    public static void main(String[] args) {

        /*
        String str = "{0}{1}{2}{3}{4}{5}{6}{7}{8}{9}{10}{11}{12}{13}{14}{15}{16}";
        Object[] array = new Object[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"};
        String value = MessageFormat.format(str, array);
        System.out.println(value); // ABCDEFGHIJKLMNOPQ


        String message = "oh, {0} is a person";
        Object[] array = new Object[]{"ZhangSan"};
        String value = MessageFormat.format(message, array);
        System.out.println(value); // oh, ZhangSan is a person
         */
        String COLUMNS = "TABLE_CATALOG	TABLE_SCHEMA	TABLE_NAME	COLUMN_NAME	ORDINAL_POSITION	COLUMN_DEFAULT	IS_NULLABLE	DATA_TYPE	CHARACTER_MAXIMUM_LENGTH	CHARACTER_OCTET_LENGTH	NUMERIC_PRECISION	NUMERIC_SCALE	DATETIME_PRECISION	CHARACTER_SET_NAME	COLLATION_NAME	COLUMN_TYPE	COLUMN_KEY	EXTRA	PRIVILEGES	COLUMN_COMMENT	GENERATION_EXPRESSION";
        String[] fields = COLUMNS.split("\t");

        // 0 AdBb  1 abgCddf
        String fieldVar =
                "\n/**" +
                        "\n * " +
                        "\n */" +
                        "\n@Column(name=\"{3}\")" +
                        "\nprivate String {1}; ";
        String fieldGetFunction =
                "\n public String get{0}() '{'" +
                "\n  return this.{1};" +
                "\n }";

        String fieldSetFunction =
                "\npublic void set{0} () '{'" +
                "\n this.{1} = {2};" +
                "\n}";
        List<Object[]> formartFields = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {

            String FIELDStr = fields[i];
            String[] FIELDS = FIELDStr.split("_");
            String fieldName = "";
            String FieldName = "";
            for (String FIELD : FIELDS) {
                FieldName += StringUtils.capitalize(FIELD.toLowerCase());
            }
            fieldName = StringUtils.uncapitalize(FieldName);
            Object[] formartField = new String[4];
            formartField[0] = FieldName;
            formartField[1] = fieldName;
            formartField[2] = fieldName;
            formartField[3] = FIELDStr;
            formartFields.add(formartField);
        }

        for (Object[] formartField : formartFields) {
            System.out.println(MessageFormat.format(fieldVar, formartField));
        }

        for (Object[] formartField : formartFields) {
            System.out.println(MessageFormat.format(fieldGetFunction, formartField));
            System.out.println(MessageFormat.format(fieldSetFunction, formartField));
        }
    }
}
