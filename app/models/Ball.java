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
 * Balls
 */
@Entity
@Table(name = "balls" /*, schema="ra_dummy" */)
@Label("Balls")
public class Ball extends Model {

    @Id
    @Label("Id")
    public Integer id;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Label("Color")
    public String color;

    @Label("Created at")
    public Date createdAt;

    @Label("Updated at")
    public Date updatedAt;


    public static final Model.Finder<Integer, Ball> find = new Model.Finder<>(Integer.class, Ball.class);

    /**
     * Для разбивки на страницы списка "Balls".
     *
     * @param page     Номер страницы для выводы
     * @param pageSize Количество записей на одну страницу
     * @param sortBy   Строковое название поля, по которому будет произведена сортировка
     * @param order    Порядок сортировки (возможны: "asc" или "desc")
     */
    public static Page<Ball> page(int page, int pageSize, String sortBy, String order) {
        return find.where()
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }

    public static Map<String, String> options() {
        Map<String, String> result = new LinkedHashMap<>();
        for(Ball item: Ball.find.orderBy("color").findList()) {
            result.put(item.id.toString(), item.color);
        }
        return result;
    }

    @Override
    public String toString() {
    return color;
    }
}