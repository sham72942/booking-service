//package org.example.blogic;
//
//import org.example.interfaces.Device;
//import org.example.interfaces.Person;
//
//import java.util.Date;
//import java.util.Optional;
//
//public class Mobile implements Device {
//    private final Optional<Person> whoBooked;
//    private final Optional<Date> lastBooked;
//
//    public Mobile(Optional<Person> whoBooked, Optional<Date> lastBooked) {
//        this.whoBooked = whoBooked;
//        this.lastBooked = lastBooked;
//    }
//
//    @Override
//    public boolean isAvailable() {
//        return !whoBooked.isPresent();
//    }
//
//    @Override
//    public Optional<Date> getLastBooked() {
//        return lastBooked;
//    }
//
//    @Override
//    public Optional<Person> getWhoBooked() {
//        return whoBooked;
//    }
//
//    @Override
//    public Optional<Device> book(Person person) {
//        if (isAvailable()) {
//            return Optional.empty();
//        } else {
//            return Optional.of(new Mobile(Optional.of(person), lastBooked));
//        }
//    }
//
//    @Override
//    public Device returnDevice() {
//        return new Mobile(Optional.empty(), Optional.empty());
//    }
//}
//
//
