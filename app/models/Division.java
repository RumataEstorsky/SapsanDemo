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
 * Divisions
 */
@Entity
@Table(name = "divisions" /*, schema="ra_dummy" */)
@Label("Divisions")
public class Division extends Model {

    @Id
    @Label("Custom id")
    public Integer customId;

    @Constraints.MaxLength(50)
    @Length(min = 0, max = 50)
    @Label("Name")
    public String name;


    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Created at")
    public Date createdAt;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Updated at")
    public Date updatedAt;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Label("Custom league id")
    public Integer customLeagueId;


    @OneToMany(cascade=CascadeType.ALL)
    @Label("Rel tests")
    public List<RelTest> relTests;

    @OneToMany(cascade=CascadeType.ALL)
    @Label("Teams")
    public List<Team> teams;


    public static final Model.Finder<Integer, Division> find = new Model.Finder<>(Integer.class, Division.class);

    /**
     * Для разбивки на страницы списка "Divisions".
     *
     * @param page     Номер страницы для выводы
     * @param pageSize Количество записей на одну страницу
     * @param sortBy   Строковое название поля, по которому будет произведена сортировка
     * @param order    Порядок сортировки (возможны: "asc" или "desc")
     */
    public static Page<Division> page(int page, int pageSize, String sortBy, String order) {
        return find.where()
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }

    public static Map<String, String> options() {
        Map<String, String> result = new LinkedHashMap<>();
        for(Division item: Division.find.orderBy("name").findList()) {
            result.put(item.customId.toString(), item.name);
        }
        return result;
    }

    @Override
    public String toString() {
    return name;
    }
}