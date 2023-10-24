//package org.example.blogic;
//
//import org.example.interfaces.BookingManagerService;
//import org.example.interfaces.Device;
//import org.example.interfaces.Person;
//import org.springframework.stereotype.Service;
//
//@Service
//public class BookingManager implements BookingManagerService {
//    public boolean bookDevice(Device device, Person person) {
//        if (device.isAvailable()) {
//            device.book(person);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public boolean returnDevice(Device device) {
//        if (!device.isAvailable()) {
//            device.returnDevice();
//            return true;
//        } else {
//            return false;
//        }
//    }
//}
//
//
//
