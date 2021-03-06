package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import org.apache.commons.lang3.StringUtils;

import utils.DateParser;
import views.form.ActorForm;

import com.avaje.ebean.Model;

@Entity(name = "actor")
public class Actor extends Model {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;
  @NotNull
  @Size(min = 1, max = 30)
  public String name;
  public Integer height;
  @Pattern(regexp = "A|B|AB|O")
  public String blood;
  public Date birthday;
  @Min(1)
  @Max(47)
  public Integer birthplaceId;
  @Version
  public Date updateAt;

  public Actor() {
  }

  public Actor(Long id, String name, Integer height, String blood, Date birthday, Integer birthplaceId) {
  	  this.id = id;
  	  this.name = name;
  	  this.height = height;
  	  this.blood = blood;
  	  this.birthday = birthday;
  	  this.birthplaceId = birthplaceId;
  }

  public static Find<Long, Actor> finder = new Find<Long, Actor>(){};

  public static Actor convertToModel(ActorForm form, Actor actor) {
  	  actor.id = StringUtils.isNotEmpty(form.id) ? Long.valueOf(form.id) : actor.id;
  	  actor.name = form.name;
  	  actor.height = StringUtils.isNotEmpty(form.height) ? Integer.valueOf(form.height) : null;
  	  actor.blood = form.blood;
  	  actor.birthday = StringUtils.isNotEmpty(form.birthday) ? DateParser.parse(form.birthday) : null;
  	  actor.birthplaceId = StringUtils.isNotEmpty(form.birthplaceId) ? Integer.valueOf(form.birthplaceId) : null;
  	  return actor;
  }

  @Override
  public String toString() {
  		return "Actor [id=" + id + ", name=" + name + ", height=" + height
        + ", blood=" + blood + ", birthday=" + birthday + ", birthplaceId="
        + birthplaceId + ", updateAt=" + updateAt + "]";
  }
}
