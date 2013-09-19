package models;

import com.avaje.ebean.Page;
import com.avaje.ebean.validation.NotNull;
import play.data.format.Formats;
import play.db.ebean.Model;
import sapsan.annotation.Label;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * rails_admin_histories.
 */
@Entity
@Table(name = "sapsan_histories" /*, schema="ra_dummy" */)
public class SapsanHistory extends Model {

    public enum ActionType {
        CREATE, RETRIVE, UPDATE, REMOVE,
    }


    @Id
    @Label("id")
    public Integer id;

    @Label("message")
    public String message;

    @Label("username")
    public String userName;

    @Label("item")
    public Integer item;

    @Label("table")
    public String tableName;

    @NotNull
    @Formats.DateTime(pattern = "dd.MM.yyyy")
    @Label("created_at")
    public Date createdAt = new Date();


    public static final Model.Finder<Integer, SapsanHistory> find = new Model.Finder<>(Integer.class, SapsanHistory.class);

    /**
     * Для разбивки на страницы списка "rails_admin_histories".
     *
     * @param page     Номер страницы для выводы
     * @param pageSize Количество записей на одну страницу
     * @param sortBy   Строковое название поля, по которому будет произведена сортировка
     * @param order    Порядок сортировки (возможны: "asc" или "desc")
     */
    public static Page<SapsanHistory> page(int page, int pageSize, String sortBy, String order) {
        return find.where()
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }

    public static Map<String, String> options() {
        Map<String, String> result = new LinkedHashMap<>();
        for (SapsanHistory c : SapsanHistory.find.orderBy("message").findList()) {
            result.put(c.id.toString(), c.message);
        }
        return result;
    }


    @Override
    public String toString() {
        return message;
    }

}
