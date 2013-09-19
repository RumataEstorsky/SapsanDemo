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
 * Fans
 */
@Entity
@Table(name = "fans" /*, schema="ra_dummy" */)
@Label("Fans")
public class Fan extends Model {

    @Id
    @Label("Id")
    public Integer id;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Created at")
    public Date createdAt;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Updated at")
    public Date updatedAt;

    @Constraints.MaxLength(100)
    @Length(min = 0, max = 100)
    @Label("Name")
    public String name;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "fans_teams")
    @Label("Fans teams")
    public List<Team> teams;


    public static final Model.Finder<Integer, Fan> find = new Model.Finder<>(Integer.class, Fan.class);

    /**
     * Для разбивки на страницы списка "Fans".
     *
     * @param page     Номер страницы для выводы
     * @param pageSize Количество записей на одну страницу
     * @param sortBy   Строковое название поля, по которому будет произведена сортировка
     * @param order    Порядок сортировки (возможны: "asc" или "desc")
     */
    public static Page<Fan> page(int page, int pageSize, String sortBy, String order) {
        return find.where()
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }

    public static Map<String, String> options() {
        Map<String, String> result = new LinkedHashMap<>();
        for(Fan item: Fan.find.orderBy("name").findList()) {
            result.put(item.id.toString(), item.name);
        }
        return result;
    }

    @Override
    public String toString() {
    return name;
    }
}