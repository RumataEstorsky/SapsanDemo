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
 * Rel tests
 */
@Entity
@Table(name = "rel_tests" /*, schema="ra_dummy" */)
@Label("Rel tests")
public class RelTest extends Model {

    @Id
    @Label("Id")
    public Integer id;

    @ManyToOne
    @Label("League id")
    public League league;

    @ManyToOne
    @Label("Division id")
    public Division division;

    @ManyToOne
    @Label("Player id")
    public Player player;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Created at")
    public Date createdAt;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Updated at")
    public Date updatedAt;


    public static final Model.Finder<Integer, RelTest> find = new Model.Finder<>(Integer.class, RelTest.class);

    /**
     * Для разбивки на страницы списка "Rel tests".
     *
     * @param page     Номер страницы для выводы
     * @param pageSize Количество записей на одну страницу
     * @param sortBy   Строковое название поля, по которому будет произведена сортировка
     * @param order    Порядок сортировки (возможны: "asc" или "desc")
     */
    public static Page<RelTest> page(int page, int pageSize, String sortBy, String order) {
        return find.where()
                .orderBy(sortBy + " " + order)
                .fetch("league")
                .fetch("division")
                .fetch("player")
                .findPagingList(pageSize)
                .getPage(page);
    }
}