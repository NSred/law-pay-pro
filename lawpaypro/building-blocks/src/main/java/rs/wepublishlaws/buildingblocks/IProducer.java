package rs.wepublishlaws.buildingblocks;

public interface IProducer {
    <T> void sendMessage(String destination, T message);
    <T, R> R sendAndReceive(String destination, T request, Class<R> responseType);
}
