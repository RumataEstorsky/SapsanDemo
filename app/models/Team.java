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
 * Teams
 */
@Entity
@Table(name = "teams" /*, schema="ra_dummy" */)
@Label("Teams")
public class Team extends Model {

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
    @Label("Division id")
    public Division division;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(50)
    @Length(min = 0, max = 50)
    @Label("Name")
    public String name;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Label("Logo url")
    public String logoUrl;

    @Constraints.MaxLength(100)
    @Length(min = 0, max = 100)
    @Label("Manager")
    public String manager;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(100)
    @Length(min = 0, max = 100)
    @Label("Ballpark")
    public String ballpark;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(100)
    @Length(min = 0, max = 100)
    @Label("Mascot")
    public String mascot;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Label("Founded")
    public Integer founded;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Label("Wins")
    public Integer wins;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Label("Losses")
    public Integer losses;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Label("Win percentage")
    public Float winPercentage;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Label("Revenue")
    public BigDecimal revenue;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Label("Color")
    public String color;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "players_teams")
    @Label("Drafts")
    public List<Player> players;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "fans_teams")
    @Label("Fans teams")
    public List<Fan> fans;


    public static final Model.Finder<Integer, Team> find = new Model.Finder<>(Integer.class, Team.class);

    /**
     * Для разбивки на страницы списка "Teams".
     *
     * @param page     Номер страницы для выводы
     * @param pageSize Количество записей на одну страницу
     * @param sortBy   Строковое название поля, по которому будет произведена сортировка
     * @param order    Порядок сортировки (возможны: "asc" или "desc")
     */
    public static Page<Team> page(int page, int pageSize, String sortBy, String order) {
        return find.where()
                .orderBy(sortBy + " " + order)
                .fetch("division")
                .findPagingList(pageSize)
                .getPage(page);
    }

    public static Map<String, String> options() {
        Map<String, String> result = new LinkedHashMap<>();
        for(Team item: Team.find.orderBy("name").findList()) {
            result.put(item.id.toString(), item.name);
        }
        return result;
    }

    @Override
    public String toString() {
    return name;
    }
}