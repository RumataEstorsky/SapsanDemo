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
 * Players
 */
@Entity
@Table(name = "players" /*, schema="ra_dummy" */)
@Label("Players")
public class Player extends Model {

    @Id
    @Label("Id")
    public Integer id;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Created at")
    public Date createdAt;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Updated at")
    public Date updatedAt;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Deleted at")
    public Date deletedAt;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Label("Team id")
    public Integer teamId;

    @Constraints.MaxLength(100)
    @Length(min = 0, max = 100)
    @Label("Name")
    public String name;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(50)
    @Length(min = 0, max = 50)
    @Label("Position")
    public String position;

    @Label("Number")
    public Integer number;

    @Label("Retired")
    public Boolean retired = false;

    @Label("Injured")
    public Boolean injured = false;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Born on")
    public Date bornOn;

    @Column(columnDefinition = "TEXT")
    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(65535)
    @Length(min = 0, max = 65535)
    @Label("Notes")
    public String notes;

    @Label("Suspended")
    public Boolean suspended = false;

    @OneToMany(cascade=CascadeType.ALL)
    @Label("Rel tests")
    public List<RelTest> relTests;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "players_teams")
    @Label("Drafts")
    public List<Team> teams;


    public static final Model.Finder<Integer, Player> find = new Model.Finder<>(Integer.class, Player.class);

    /**
     * Для разбивки на страницы списка "Players".
     *
     * @param page     Номер страницы для выводы
     * @param pageSize Количество записей на одну страницу
     * @param sortBy   Строковое название поля, по которому будет произведена сортировка
     * @param order    Порядок сортировки (возможны: "asc" или "desc")
     */
    public static Page<Player> page(int page, int pageSize, String sortBy, String order) {
        return find.where()
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }

    public static Map<String, String> options() {
        Map<String, String> result = new LinkedHashMap<>();
        for(Player item: Player.find.orderBy("name").findList()) {
            result.put(item.id.toString(), item.name);
        }
        return result;
    }

    @Override
    public String toString() {
    return name;
    }
}