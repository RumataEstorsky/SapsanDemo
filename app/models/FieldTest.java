package models;

import java.util.*;
import java.math.BigDecimal;
import java.sql.Time;
import javax.persistence.*;
import com.avaje.ebean.validation.*;
import com.avaje.ebean.annotation.*;
import play.data.validation.*;
import sapsan.annotation.Label;
import com.avaje.ebean.*;
import play.db.ebean.*;
import play.data.format.*;
import play.i18n.Messages;
import play.data.format.Formats;

/**
 * Field tests
 */
@Entity
@Table(name = "field_tests" /*, schema="ra_dummy" */)
@Label("Field tests")
public class FieldTest extends Model {

    @Id
    @Label("Id")
    public Integer id;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Label("String field")
    public String stringField;

    @Column(columnDefinition = "TEXT")
    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(65535)
    @Length(min = 0, max = 65535)
    @Label("Text field")
    public String textField;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Label("Integer field")
    public Integer integerField;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Label("Float field")
    public Float floatField;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Label("Decimal field")
    public BigDecimal decimalField;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Datetime field")
    public Date datetimeField;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Timestamp field")
    public Date timestampField;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Formats.DateTime(pattern="HH:mm:ss")
    @Label("Time field")
    public Time timeField;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Date field")
    public Date dateField;

    @Label("Boolean field")
    public Boolean booleanField;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Created at")
    public Date createdAt;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Updated at")
    public Date updatedAt;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Label("Format")
    public String format;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Label("Restricted field")
    public String restrictedField;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Label("Protected field")
    public String protectedField;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Label("Paperclip asset file name")
    public String paperclipAssetFileName;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Label("Dragonfly asset uid")
    public String dragonflyAssetUid;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Label("Carrierwave asset")
    public String carrierwaveAsset;

    @OneToMany(cascade=CascadeType.ALL)
    @Label("Nested field tests")
    public List<NestedFieldTest> nestedFieldTests;


    public static final Model.Finder<Integer, FieldTest> find = new Model.Finder<>(Integer.class, FieldTest.class);

    /**
     * Для разбивки на страницы списка "Field tests".
     *
     * @param page     Номер страницы для выводы
     * @param pageSize Количество записей на одну страницу
     * @param sortBy   Строковое название поля, по которому будет произведена сортировка
     * @param order    Порядок сортировки (возможны: "asc" или "desc")
     */
    public static Page<FieldTest> page(int page, int pageSize, String sortBy, String order) {
        return find.where()
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }

    public static Map<String, String> options() {
        Map<String, String> result = new LinkedHashMap<>();
        for(FieldTest item: FieldTest.find.orderBy("string_field").findList()) {
            result.put(item.id.toString(), item.stringField);
        }
        return result;
    }

    @Override
    public String toString() {
    return stringField;
    }
}