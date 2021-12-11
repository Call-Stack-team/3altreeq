package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the User type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users")
public final class User implements Model {
  public static final QueryField ID = field("User", "id");
  public static final QueryField USER_NAME = field("User", "userName");
  public static final QueryField FIRST_NAME = field("User", "firstName");
  public static final QueryField LAST_NAME = field("User", "lastName");
  public static final QueryField EMAIL = field("User", "email");
  public static final QueryField PHONE_NUMBER = field("User", "phoneNumber");
  public static final QueryField PICTURE_KEY = field("User", "pictureKey");
  public static final QueryField DATE_OF_BIRTH = field("User", "dateOfBirth");
  public static final QueryField GENDER = field("User", "Gender");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String userName;
  private final @ModelField(targetType="String", isRequired = true) String firstName;
  private final @ModelField(targetType="String", isRequired = true) String lastName;
  private final @ModelField(targetType="String", isRequired = true) String email;
  private final @ModelField(targetType="String", isRequired = true) String phoneNumber;
  private final @ModelField(targetType="String") String pictureKey;
  private final @ModelField(targetType="String") String dateOfBirth;
  private final @ModelField(targetType="String") String Gender;
  private final @ModelField(targetType="RideUser") @HasMany(associatedWith = "user", type = RideUser.class) List<RideUser> rides = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getUserName() {
      return userName;
  }
  
  public String getFirstName() {
      return firstName;
  }
  
  public String getLastName() {
      return lastName;
  }
  
  public String getEmail() {
      return email;
  }
  
  public String getPhoneNumber() {
      return phoneNumber;
  }
  
  public String getPictureKey() {
      return pictureKey;
  }
  
  public String getDateOfBirth() {
      return dateOfBirth;
  }
  
  public String getGender() {
      return Gender;
  }
  
  public List<RideUser> getRides() {
      return rides;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private User(String id, String userName, String firstName, String lastName, String email, String phoneNumber, String pictureKey, String dateOfBirth, String Gender) {
    this.id = id;
    this.userName = userName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.pictureKey = pictureKey;
    this.dateOfBirth = dateOfBirth;
    this.Gender = Gender;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      User user = (User) obj;
      return ObjectsCompat.equals(getId(), user.getId()) &&
              ObjectsCompat.equals(getUserName(), user.getUserName()) &&
              ObjectsCompat.equals(getFirstName(), user.getFirstName()) &&
              ObjectsCompat.equals(getLastName(), user.getLastName()) &&
              ObjectsCompat.equals(getEmail(), user.getEmail()) &&
              ObjectsCompat.equals(getPhoneNumber(), user.getPhoneNumber()) &&
              ObjectsCompat.equals(getPictureKey(), user.getPictureKey()) &&
              ObjectsCompat.equals(getDateOfBirth(), user.getDateOfBirth()) &&
              ObjectsCompat.equals(getGender(), user.getGender()) &&
              ObjectsCompat.equals(getCreatedAt(), user.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), user.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUserName())
      .append(getFirstName())
      .append(getLastName())
      .append(getEmail())
      .append(getPhoneNumber())
      .append(getPictureKey())
      .append(getDateOfBirth())
      .append(getGender())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("User {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("userName=" + String.valueOf(getUserName()) + ", ")
      .append("firstName=" + String.valueOf(getFirstName()) + ", ")
      .append("lastName=" + String.valueOf(getLastName()) + ", ")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("phoneNumber=" + String.valueOf(getPhoneNumber()) + ", ")
      .append("pictureKey=" + String.valueOf(getPictureKey()) + ", ")
      .append("dateOfBirth=" + String.valueOf(getDateOfBirth()) + ", ")
      .append("Gender=" + String.valueOf(getGender()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static UserNameStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static User justId(String id) {
    return new User(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      userName,
      firstName,
      lastName,
      email,
      phoneNumber,
      pictureKey,
      dateOfBirth,
      Gender);
  }
  public interface UserNameStep {
    FirstNameStep userName(String userName);
  }
  

  public interface FirstNameStep {
    LastNameStep firstName(String firstName);
  }
  

  public interface LastNameStep {
    EmailStep lastName(String lastName);
  }
  

  public interface EmailStep {
    PhoneNumberStep email(String email);
  }
  

  public interface PhoneNumberStep {
    BuildStep phoneNumber(String phoneNumber);
  }
  

  public interface BuildStep {
    User build();
    BuildStep id(String id);
    BuildStep pictureKey(String pictureKey);
    BuildStep dateOfBirth(String dateOfBirth);
    BuildStep gender(String gender);
  }
  

  public static class Builder implements UserNameStep, FirstNameStep, LastNameStep, EmailStep, PhoneNumberStep, BuildStep {
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String pictureKey;
    private String dateOfBirth;
    private String Gender;
    @Override
     public User build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new User(
          id,
          userName,
          firstName,
          lastName,
          email,
          phoneNumber,
          pictureKey,
          dateOfBirth,
          Gender);
    }
    
    @Override
     public FirstNameStep userName(String userName) {
        Objects.requireNonNull(userName);
        this.userName = userName;
        return this;
    }
    
    @Override
     public LastNameStep firstName(String firstName) {
        Objects.requireNonNull(firstName);
        this.firstName = firstName;
        return this;
    }
    
    @Override
     public EmailStep lastName(String lastName) {
        Objects.requireNonNull(lastName);
        this.lastName = lastName;
        return this;
    }
    
    @Override
     public PhoneNumberStep email(String email) {
        Objects.requireNonNull(email);
        this.email = email;
        return this;
    }
    
    @Override
     public BuildStep phoneNumber(String phoneNumber) {
        Objects.requireNonNull(phoneNumber);
        this.phoneNumber = phoneNumber;
        return this;
    }
    
    @Override
     public BuildStep pictureKey(String pictureKey) {
        this.pictureKey = pictureKey;
        return this;
    }
    
    @Override
     public BuildStep dateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }
    
    @Override
     public BuildStep gender(String gender) {
        this.Gender = gender;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String userName, String firstName, String lastName, String email, String phoneNumber, String pictureKey, String dateOfBirth, String gender) {
      super.id(id);
      super.userName(userName)
        .firstName(firstName)
        .lastName(lastName)
        .email(email)
        .phoneNumber(phoneNumber)
        .pictureKey(pictureKey)
        .dateOfBirth(dateOfBirth)
        .gender(gender);
    }
    
    @Override
     public CopyOfBuilder userName(String userName) {
      return (CopyOfBuilder) super.userName(userName);
    }
    
    @Override
     public CopyOfBuilder firstName(String firstName) {
      return (CopyOfBuilder) super.firstName(firstName);
    }
    
    @Override
     public CopyOfBuilder lastName(String lastName) {
      return (CopyOfBuilder) super.lastName(lastName);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder phoneNumber(String phoneNumber) {
      return (CopyOfBuilder) super.phoneNumber(phoneNumber);
    }
    
    @Override
     public CopyOfBuilder pictureKey(String pictureKey) {
      return (CopyOfBuilder) super.pictureKey(pictureKey);
    }
    
    @Override
     public CopyOfBuilder dateOfBirth(String dateOfBirth) {
      return (CopyOfBuilder) super.dateOfBirth(dateOfBirth);
    }
    
    @Override
     public CopyOfBuilder gender(String gender) {
      return (CopyOfBuilder) super.gender(gender);
    }
  }
  
}
