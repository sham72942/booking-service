//package org.example.blogic;
//
//import org.example.interfaces.Device;
//import org.example.interfaces.DeviceManagerService;
//import org.example.models.DeviceUpdate;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Queue;
//import java.util.concurrent.ConcurrentLinkedQueue;
//import java.util.concurrent.CopyOnWriteArrayList;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.atomic.AtomicReference;
//
//import static org.example.models.DeviceUpdate.Type.ADD;
//import static org.example.models.DeviceUpdate.Type.REMOVE;
//
//@Service
//public class DeviceManager implements DeviceManagerService {
//    private ExecutorService executorService;
//    private Queue<DeviceUpdate> updateQueue;
//    private AtomicReference<List<Device>> cachedDeviceList;
//
//    public DeviceManager() {
//        executorService = Executors.newCachedThreadPool();
//        updateQueue = new ConcurrentLinkedQueue<>();
//        cachedDeviceList = new AtomicReference<>(new CopyOnWriteArrayList<>());
//
//        startUpdateProcessor();
//    }
//
//    @Override
//    public List<Device> addDevice(Device device) {
//        updateQueue.offer(new DeviceUpdate(device, ADD));
//        return listAllDevice(); // Return the updated list
//    }
//
//    @Override
//    public List<Device> removeDevice(Device device) {
//        updateQueue.offer(new DeviceUpdate(device, REMOVE));
//        return listAllDevice(); // Return the updated list
//    }
//
//    @Override
//    public List<Device> listAllDevice() {
//        return cachedDeviceList.get();
//    }
//
//    private void startUpdateProcessor() {
//        executorService.submit(() -> {
//            while (true) {
//                DeviceUpdate update = updateQueue.poll();
//                if (update != null) {
//                    List<Device> updatedDevices = new CopyOnWriteArrayList<>(cachedDeviceList.get());
//                    if (update.getType() == ADD) {
//                        updatedDevices.add(update.getDevice());
//                    } else if (update.getType() == REMOVE) {
//                        updatedDevices.remove(update.getDevice());
//                    }
//                    cachedDeviceList.set(updatedDevices);
//                }
//
//                // Introduce a short sleep to reduce CPU usage
//                try {
//                    Thread.sleep(10); // Sleep for 10 milliseconds
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                    break;
//                }
//            }
//        });
//    }
//}
//
