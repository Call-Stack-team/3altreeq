package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
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

/** This is an auto generated class representing the Ride type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Rides", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Ride implements Model {
  public static final QueryField ID = field("Ride", "id");
  public static final QueryField DRIVER_NAME = field("Ride", "driverName");
  public static final QueryField DATE_TIME = field("Ride", "dateTime");
  public static final QueryField NUMBER_OF_SEATS = field("Ride", "numberOfSeats");
  public static final QueryField PRICE = field("Ride", "price");
  public static final QueryField LAT_DROP = field("Ride", "latDrop");
  public static final QueryField LAT_PICK = field("Ride", "latPick");
  public static final QueryField LON_DROP = field("Ride", "lonDrop");
  public static final QueryField LON_PICK = field("Ride", "lonPick");
  public static final QueryField NOTE = field("Ride", "note");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String driverName;
  private final @ModelField(targetType="String", isRequired = true) String dateTime;
  private final @ModelField(targetType="Int", isRequired = true) Integer numberOfSeats;
  private final @ModelField(targetType="String", isRequired = true) String price;
  private final @ModelField(targetType="Float", isRequired = true) Double latDrop;
  private final @ModelField(targetType="Float", isRequired = true) Double latPick;
  private final @ModelField(targetType="Float", isRequired = true) Double lonDrop;
  private final @ModelField(targetType="Float", isRequired = true) Double lonPick;
  private final @ModelField(targetType="RideUser") @HasMany(associatedWith = "ride", type = RideUser.class) List<RideUser> RideUsers = null;
  private final @ModelField(targetType="String") String note;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getDriverName() {
      return driverName;
  }
  
  public String getDateTime() {
      return dateTime;
  }
  
  public Integer getNumberOfSeats() {
      return numberOfSeats;
  }
  
  public String getPrice() {
      return price;
  }
  
  public Double getLatDrop() {
      return latDrop;
  }
  
  public Double getLatPick() {
      return latPick;
  }
  
  public Double getLonDrop() {
      return lonDrop;
  }
  
  public Double getLonPick() {
      return lonPick;
  }
  
  public List<RideUser> getRideUsers() {
      return RideUsers;
  }
  
  public String getNote() {
      return note;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Ride(String id, String driverName, String dateTime, Integer numberOfSeats, String price, Double latDrop, Double latPick, Double lonDrop, Double lonPick, String note) {
    this.id = id;
    this.driverName = driverName;
    this.dateTime = dateTime;
    this.numberOfSeats = numberOfSeats;
    this.price = price;
    this.latDrop = latDrop;
    this.latPick = latPick;
    this.lonDrop = lonDrop;
    this.lonPick = lonPick;
    this.note = note;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Ride ride = (Ride) obj;
      return ObjectsCompat.equals(getId(), ride.getId()) &&
              ObjectsCompat.equals(getDriverName(), ride.getDriverName()) &&
              ObjectsCompat.equals(getDateTime(), ride.getDateTime()) &&
              ObjectsCompat.equals(getNumberOfSeats(), ride.getNumberOfSeats()) &&
              ObjectsCompat.equals(getPrice(), ride.getPrice()) &&
              ObjectsCompat.equals(getLatDrop(), ride.getLatDrop()) &&
              ObjectsCompat.equals(getLatPick(), ride.getLatPick()) &&
              ObjectsCompat.equals(getLonDrop(), ride.getLonDrop()) &&
              ObjectsCompat.equals(getLonPick(), ride.getLonPick()) &&
              ObjectsCompat.equals(getNote(), ride.getNote()) &&
              ObjectsCompat.equals(getCreatedAt(), ride.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), ride.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getDriverName())
      .append(getDateTime())
      .append(getNumberOfSeats())
      .append(getPrice())
      .append(getLatDrop())
      .append(getLatPick())
      .append(getLonDrop())
      .append(getLonPick())
      .append(getNote())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Ride {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("driverName=" + String.valueOf(getDriverName()) + ", ")
      .append("dateTime=" + String.valueOf(getDateTime()) + ", ")
      .append("numberOfSeats=" + String.valueOf(getNumberOfSeats()) + ", ")
      .append("price=" + String.valueOf(getPrice()) + ", ")
      .append("latDrop=" + String.valueOf(getLatDrop()) + ", ")
      .append("latPick=" + String.valueOf(getLatPick()) + ", ")
      .append("lonDrop=" + String.valueOf(getLonDrop()) + ", ")
      .append("lonPick=" + String.valueOf(getLonPick()) + ", ")
      .append("note=" + String.valueOf(getNote()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static DriverNameStep builder() {
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
  public static Ride justId(String id) {
    return new Ride(
      id,
      null,
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
      driverName,
      dateTime,
      numberOfSeats,
      price,
      latDrop,
      latPick,
      lonDrop,
      lonPick,
      note);
  }
  public interface DriverNameStep {
    DateTimeStep driverName(String driverName);
  }
  

  public interface DateTimeStep {
    NumberOfSeatsStep dateTime(String dateTime);
  }
  

  public interface NumberOfSeatsStep {
    PriceStep numberOfSeats(Integer numberOfSeats);
  }
  

  public interface PriceStep {
    LatDropStep price(String price);
  }
  

  public interface LatDropStep {
    LatPickStep latDrop(Double latDrop);
  }
  

  public interface LatPickStep {
    LonDropStep latPick(Double latPick);
  }
  

  public interface LonDropStep {
    LonPickStep lonDrop(Double lonDrop);
  }
  

  public interface LonPickStep {
    BuildStep lonPick(Double lonPick);
  }
  

  public interface BuildStep {
    Ride build();
    BuildStep id(String id);
    BuildStep note(String note);
  }
  

  public static class Builder implements DriverNameStep, DateTimeStep, NumberOfSeatsStep, PriceStep, LatDropStep, LatPickStep, LonDropStep, LonPickStep, BuildStep {
    private String id;
    private String driverName;
    private String dateTime;
    private Integer numberOfSeats;
    private String price;
    private Double latDrop;
    private Double latPick;
    private Double lonDrop;
    private Double lonPick;
    private String note;
    @Override
     public Ride build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Ride(
          id,
          driverName,
          dateTime,
          numberOfSeats,
          price,
          latDrop,
          latPick,
          lonDrop,
          lonPick,
          note);
    }
    
    @Override
     public DateTimeStep driverName(String driverName) {
        Objects.requireNonNull(driverName);
        this.driverName = driverName;
        return this;
    }
    
    @Override
     public NumberOfSeatsStep dateTime(String dateTime) {
        Objects.requireNonNull(dateTime);
        this.dateTime = dateTime;
        return this;
    }
    
    @Override
     public PriceStep numberOfSeats(Integer numberOfSeats) {
        Objects.requireNonNull(numberOfSeats);
        this.numberOfSeats = numberOfSeats;
        return this;
    }
    
    @Override
     public LatDropStep price(String price) {
        Objects.requireNonNull(price);
        this.price = price;
        return this;
    }
    
    @Override
     public LatPickStep latDrop(Double latDrop) {
        Objects.requireNonNull(latDrop);
        this.latDrop = latDrop;
        return this;
    }
    
    @Override
     public LonDropStep latPick(Double latPick) {
        Objects.requireNonNull(latPick);
        this.latPick = latPick;
        return this;
    }
    
    @Override
     public LonPickStep lonDrop(Double lonDrop) {
        Objects.requireNonNull(lonDrop);
        this.lonDrop = lonDrop;
        return this;
    }
    
    @Override
     public BuildStep lonPick(Double lonPick) {
        Objects.requireNonNull(lonPick);
        this.lonPick = lonPick;
        return this;
    }
    
    @Override
     public BuildStep note(String note) {
        this.note = note;
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
    private CopyOfBuilder(String id, String driverName, String dateTime, Integer numberOfSeats, String price, Double latDrop, Double latPick, Double lonDrop, Double lonPick, String note) {
      super.id(id);
      super.driverName(driverName)
        .dateTime(dateTime)
        .numberOfSeats(numberOfSeats)
        .price(price)
        .latDrop(latDrop)
        .latPick(latPick)
        .lonDrop(lonDrop)
        .lonPick(lonPick)
        .note(note);
    }
    
    @Override
     public CopyOfBuilder driverName(String driverName) {
      return (CopyOfBuilder) super.driverName(driverName);
    }
    
    @Override
     public CopyOfBuilder dateTime(String dateTime) {
      return (CopyOfBuilder) super.dateTime(dateTime);
    }
    
    @Override
     public CopyOfBuilder numberOfSeats(Integer numberOfSeats) {
      return (CopyOfBuilder) super.numberOfSeats(numberOfSeats);
    }
    
    @Override
     public CopyOfBuilder price(String price) {
      return (CopyOfBuilder) super.price(price);
    }
    
    @Override
     public CopyOfBuilder latDrop(Double latDrop) {
      return (CopyOfBuilder) super.latDrop(latDrop);
    }
    
    @Override
     public CopyOfBuilder latPick(Double latPick) {
      return (CopyOfBuilder) super.latPick(latPick);
    }
    
    @Override
     public CopyOfBuilder lonDrop(Double lonDrop) {
      return (CopyOfBuilder) super.lonDrop(lonDrop);
    }
    
    @Override
     public CopyOfBuilder lonPick(Double lonPick) {
      return (CopyOfBuilder) super.lonPick(lonPick);
    }
    
    @Override
     public CopyOfBuilder note(String note) {
      return (CopyOfBuilder) super.note(note);
    }
  }
  
}
