package com.kairav.hotelapi;

import com.kairav.hotelapi.entity.*;
import com.kairav.hotelapi.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.math.BigDecimal;

@SpringBootApplication public class HotelApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelApiApplication.class,args);
    }
    @Bean CommandLineRunner seed(UserRepository users,RoomRepository rooms,CustomerRepository customers,StaffRepository staff,HotelServiceRepository services) {
        return args -> {
            BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
            if (users.count() ==0) {
                User admin =new User();
                admin.setFullName("System Admin");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                admin.setStatus(Status.ACTIVE);
                users.save(admin);
                User receptionist =new User();
                receptionist.setFullName("Reception User");
                receptionist.setUsername("reception");
                receptionist.setPassword(passwordEncoder.encode("reception123"));
                receptionist.setRole(Role.RECEPTIONIST);
                receptionist.setStatus(Status.ACTIVE);
                users.save(receptionist);
            }
            if (rooms.count() ==0) {
                rooms.save(new Room("101","Single","Single Bed",1,1,new BigDecimal("1500"),RoomStatus.AVAILABLE,"Budget single room"));
                rooms.save(new Room("103","Single","Single Bed",1,1,new BigDecimal("1800"),RoomStatus.AVAILABLE,"Quiet room for business guest"));
                rooms.save(new Room("102","Double","Double Bed",2,1,new BigDecimal("2500"),RoomStatus.AVAILABLE,"Comfort double room"));
                rooms.save(new Room("201","Deluxe","King Bed",2,2,new BigDecimal("3500"),RoomStatus.AVAILABLE,"Deluxe room with view"));
                rooms.save(new Room("301","Suite","King Bed",4,3,new BigDecimal("6000"),RoomStatus.AVAILABLE,"Family suite"));
                rooms.save(new Room("302","Premium Suite","King Bed",4,3,new BigDecimal("7500"),RoomStatus.AVAILABLE,"Premium room used by auto-upgrade logic"));
            }
            if (customers.count() ==0) {
                customers.save(new Customer("Ramesh","Shrestha","Male","9841000000","ramesh@gmail.com","Kathmandu","Nepali","Citizenship","123-456","9841111111"));
                customers.save(new Customer("Sita","Gurung","Female","9842000000","sita@gmail.com","Pokhara","Nepali","Citizenship","789-111","9842222222"));
            }
            if (staff.count() ==0) {
                staff.save(new Staff("Amit","Thapa","Male","9843000000","amit@gmail.com","Kathmandu","Manager",new BigDecimal("45000"),"Day",StaffStatus.ACTIVE));
                staff.save(new Staff("Mina","Tamang","Female","9844000000","mina@gmail.com","Lalitpur","Housekeeping",new BigDecimal("25000"),"Morning",StaffStatus.ACTIVE));
            }
            if (services.count() ==0) {
                services.save(new HotelService("Laundry",new BigDecimal("500"),"Clothes washing and ironing",true));
                services.save(new HotelService("Airport Pickup",new BigDecimal("1500"),"Airport to hotel transport",true));
                services.save(new HotelService("Extra Bed",new BigDecimal("800"),"Extra bed per night",true));
            }
        };
    }
}
