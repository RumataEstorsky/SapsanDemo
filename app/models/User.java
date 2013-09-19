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
 * Users
 */
@Entity
@Table(name = "users" /*, schema="ra_dummy" */)
@Label("Users")
public class User extends Model {

    @Id
    @Label("Id")
    public Integer id;

    @Column(unique = true)
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Email
    @Label("Email")
    public String email;

    @Constraints.MaxLength(128)
    @Length(min = 0, max = 128)
    @Encrypted(dbEncryption=false)
    @Label("Encrypted password")
    public String encryptedPassword;

    @Column(unique = true)
    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Encrypted(dbEncryption=false)
    @Label("Reset password token")
    public String resetPasswordToken;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Label("Remember token")
    public String rememberToken;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Remember created at")
    public Date rememberCreatedAt;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Label("Sign in count")
    public Integer signInCount = 0;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Current sign in at")
    public Date currentSignInAt;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Last sign in at")
    public Date lastSignInAt;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Label("Current sign in ip")
    public String currentSignInIp;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Label("Last sign in ip")
    public String lastSignInIp;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Encrypted(dbEncryption=false)
    @Label("Password salt")
    public String passwordSalt;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Created at")
    public Date createdAt;

    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Updated at")
    public Date updatedAt;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Label("Avatar file name")
    public String avatarFileName;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Label("Avatar content type")
    public String avatarContentType;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Label("Avatar file size")
    public Integer avatarFileSize;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Formats.DateTime(pattern="dd.MM.yyyy")
    @Label("Avatar updated at")
    public Date avatarUpdatedAt;

    @NotNull
    @Constraints.Required
    @Formats.NonEmpty
    @Constraints.MaxLength(255)
    @Length(min = 0, max = 255)
    @Label("Roles")
    public String roles;


    public static final Model.Finder<Integer, User> find = new Model.Finder<>(Integer.class, User.class);

    /**
     * Для разбивки на страницы списка "Users".
     *
     * @param page     Номер страницы для выводы
     * @param pageSize Количество записей на одну страницу
     * @param sortBy   Строковое название поля, по которому будет произведена сортировка
     * @param order    Порядок сортировки (возможны: "asc" или "desc")
     */
    public static Page<User> page(int page, int pageSize, String sortBy, String order) {
        return find.where()
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }

    public static Map<String, String> options() {
        Map<String, String> result = new LinkedHashMap<>();
        for(User item: User.find.orderBy("email").findList()) {
            result.put(item.id.toString(), item.email);
        }
        return result;
    }

    @Override
    public String toString() {
    return email;
    }
}