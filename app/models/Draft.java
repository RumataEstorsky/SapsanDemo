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
 * Drafts
 */
@Entity
@Table(name = "drafts" /*, schema="ra_dummy" */)
@Label("Drafts")
public class Draft extends Model {

    @Id
    @Label("Id")
    public Integer id;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Created at")
    public Date createdAt;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Updated at")
    public Date updatedAt;

    @ManyToOne
    @Label("Player id")
    public Player player;

    @ManyToOne
    @Label("Team id")
    public Team team;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Date")
    public Date date;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Label("Round")
    public Integer round;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Label("Pick")
    public Integer pick;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Label("Overall")
    public Integer overall;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(100)
    @Length(min = 0, max = 100)
    @Label("College")
    public String college;

    @Column(columnDefinition = "TEXT")
    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(65535)
    @Length(min = 0, max = 65535)
    @Label("Notes")
    public String notes;


    public static final Model.Finder<Integer, Draft> find = new Model.Finder<>(Integer.class, Draft.class);

    /**
     * Для разбивки на страницы списка "Drafts".
     *
     * @param page     Номер страницы для выводы
     * @param pageSize Количество записей на одну страницу
     * @param sortBy   Строковое название поля, по которому будет произведена сортировка
     * @param order    Порядок сортировки (возможны: "asc" или "desc")
     */
    public static Page<Draft> page(int page, int pageSize, String sortBy, String order) {
        return find.where()
                .orderBy(sortBy + " " + order)
                .fetch("player")
                .fetch("team")
                .findPagingList(pageSize)
                .getPage(page);
    }

    public static Map<String, String> options() {
        Map<String, String> result = new LinkedHashMap<>();
        for(Draft item: Draft.find.orderBy("college").findList()) {
            result.put(item.id.toString(), item.college);
        }
        return result;
    }

    @Override
    public String toString() {
    return college;
    }
}