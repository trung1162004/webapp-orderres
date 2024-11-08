package doan._java_food.models;

import doan._java_food.models.Converter.ListToStringConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
public class Restaurant {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name =  "name")
    private String name;

    @Column(name =  "logo")
    private String logo;

    @Column(name =  "status", columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'")
    private String status;

    @Column(name = "type", columnDefinition = "VARCHAR(255) DEFAULT 'REGISTER'")
    private String type;

    @Column(name =  "email")
    private String email;

    @Column(name =  "work_time_open")
    private String workTimeOpen;
    
    @Column(name =  "work_time_close")
    private String workTimeClose;

    @Column(name =  "hot")
    private String hot;

    @Column(name =  "hot_discount")
    private String hot_discount;

    @Column(name =  "discount")
    private Float discount;

    @Column(name = "price")
    private String price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate  endDate;

    @Column(columnDefinition = "TEXT", name =  "description")
    private String description;

    @Convert(converter = ListToStringConverter.class)
    @Column(columnDefinition = "LONGTEXT", name =  "images")
    private List<String> images;

    @Column(columnDefinition = "TEXT", name =  "address")
    private String address;

    @Column(columnDefinition = "TEXT", name =  "content")
    private String content;

    @Column(columnDefinition = "TEXT", name =  "note")
    private String note;

    @Column(name =  "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id",referencedColumnName = "id")
    private Location location;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER)
    private Set<RestaurantCategory> categories;


    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
    //mới thêm
    private String fit;
    private String specialDish;
    private String space;
    private String parking;
    private String feature;


    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "restaurant_utilities", joinColumns = @JoinColumn(name = "restaurant_id"))
//    @Column(name = "utility")
    private Set<String> utilities;

    // Getter và Setter cho utilities
    public Set<String> getUtilities() {
        return utilities;
    }

    public void setUtilities(Set<String> utilities) {
        this.utilities = utilities;
    }
    private String depositRegulation;
    private String discountRegulation;
    private String pasgoTimeRegulation;
    private String reservationTimeRegulation;
    private String holdTimeRegulation;
    private String minimumGuestsRegulation;
    private String invoiceRegulation;
    private String serviceFeeRegulation;
    private String bringInFeeRegulation;
    
   
}
