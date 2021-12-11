package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the RideUser type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "RideUsers", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
@Index(name = "byRide", fields = {"rideID","userID"})
@Index(name = "byUser", fields = {"userID","rideID"})
public final class RideUser implements Model {
  public static final QueryField ID = field("RideUser", "id");
  public static final QueryField RIDE = field("RideUser", "rideID");
  public static final QueryField USER = field("RideUser", "userID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Ride", isRequired = true) @BelongsTo(targetName = "rideID", type = Ride.class) Ride ride;
  private final @ModelField(targetType="User", isRequired = true) @BelongsTo(targetName = "userID", type = User.class) User user;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public Ride getRide() {
      return ride;
  }
  
  public User getUser() {
      return user;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private RideUser(String id, Ride ride, User user) {
    this.id = id;
    this.ride = ride;
    this.user = user;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      RideUser rideUser = (RideUser) obj;
      return ObjectsCompat.equals(getId(), rideUser.getId()) &&
              ObjectsCompat.equals(getRide(), rideUser.getRide()) &&
              ObjectsCompat.equals(getUser(), rideUser.getUser()) &&
              ObjectsCompat.equals(getCreatedAt(), rideUser.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), rideUser.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getRide())
      .append(getUser())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("RideUser {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("ride=" + String.valueOf(getRide()) + ", ")
      .append("user=" + String.valueOf(getUser()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static RideStep builder() {
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
  public static RideUser justId(String id) {
    return new RideUser(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      ride,
      user);
  }
  public interface RideStep {
    UserStep ride(Ride ride);
  }
  

  public interface UserStep {
    BuildStep user(User user);
  }
  

  public interface BuildStep {
    RideUser build();
    BuildStep id(String id);
  }
  

  public static class Builder implements RideStep, UserStep, BuildStep {
    private String id;
    private Ride ride;
    private User user;
    @Override
     public RideUser build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new RideUser(
          id,
          ride,
          user);
    }
    
    @Override
     public UserStep ride(Ride ride) {
        Objects.requireNonNull(ride);
        this.ride = ride;
        return this;
    }
    
    @Override
     public BuildStep user(User user) {
        Objects.requireNonNull(user);
        this.user = user;
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
    private CopyOfBuilder(String id, Ride ride, User user) {
      super.id(id);
      super.ride(ride)
        .user(user);
    }
    
    @Override
     public CopyOfBuilder ride(Ride ride) {
      return (CopyOfBuilder) super.ride(ride);
    }
    
    @Override
     public CopyOfBuilder user(User user) {
      return (CopyOfBuilder) super.user(user);
    }
  }
  
}
