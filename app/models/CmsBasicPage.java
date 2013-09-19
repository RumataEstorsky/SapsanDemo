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
 * Cms basic pages
 */
@Entity
@Table(name = "cms_basic_pages" /*, schema="ra_dummy" */)
@Label("Cms basic pages")
public class CmsBasicPage extends Model {

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

    @Column(columnDefinition = "TEXT")
    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(65535)
    @Length(min = 0, max = 65535)
    @Label("Content")
    public String content;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Created at")
    public Date createdAt;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Updated at")
    public Date updatedAt;


    public static final Model.Finder<Integer, CmsBasicPage> find = new Model.Finder<>(Integer.class, CmsBasicPage.class);

    /**
     * Для разбивки на страницы списка "Cms basic pages".
     *
     * @param page     Номер страницы для выводы
     * @param pageSize Количество записей на одну страницу
     * @param sortBy   Строковое название поля, по которому будет произведена сортировка
     * @param order    Порядок сортировки (возможны: "asc" или "desc")
     */
    public static Page<CmsBasicPage> page(int page, int pageSize, String sortBy, String order) {
        return find.where()
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }

    public static Map<String, String> options() {
        Map<String, String> result = new LinkedHashMap<>();
        for(CmsBasicPage item: CmsBasicPage.find.orderBy("title").findList()) {
            result.put(item.id.toString(), item.title);
        }
        return result;
    }

    @Override
    public String toString() {
    return title;
    }
}