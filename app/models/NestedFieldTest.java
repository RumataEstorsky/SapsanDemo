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
 * Nested field tests
 */
@Entity
@Table(name = "nested_field_tests" /*, schema="ra_dummy" */)
@Label("Nested field tests")
public class NestedFieldTest extends Model {

    @Id
    @Label("Id")
    public Integer id;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Label("Title")
    public String title;

    @ManyToOne
    @Label("Field test id")
    public FieldTest fieldTest;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Created at")
    public Date createdAt;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Updated at")
    public Date updatedAt;


    public static final Model.Finder<Integer, NestedFieldTest> find = new Model.Finder<>(Integer.class, NestedFieldTest.class);

    /**
     * Для разбивки на страницы списка "Nested field tests".
     *
     * @param page     Номер страницы для выводы
     * @param pageSize Количество записей на одну страницу
     * @param sortBy   Строковое название поля, по которому будет произведена сортировка
     * @param order    Порядок сортировки (возможны: "asc" или "desc")
     */
    public static Page<NestedFieldTest> page(int page, int pageSize, String sortBy, String order) {
        return find.where()
                .orderBy(sortBy + " " + order)
                .fetch("fieldTest")
                .findPagingList(pageSize)
                .getPage(page);
    }

    public static Map<String, String> options() {
        Map<String, String> result = new LinkedHashMap<>();
        for(NestedFieldTest item: NestedFieldTest.find.orderBy("title").findList()) {
            result.put(item.id.toString(), item.title);
        }
        return result;
    }

    @Override
    public String toString() {
    return title;
    }
}