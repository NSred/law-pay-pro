package rs.wepublishlaws.buildingblocks;

public interface IActiveMqProducer {
    <T> void sendMessage(String destination, T message);
}
