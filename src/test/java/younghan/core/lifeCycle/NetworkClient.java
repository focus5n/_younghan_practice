package younghan.core.lifeCycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출. 클라이언트 시작했습니다. url = " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 중입니다...");
    }

    private void connect() {
        System.out.println("connect = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void call(String message) {
        System.out.println("url = " + url + ", message = " + message);
    }

    public void disconnect() {
        System.out.println("close : " + url);
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
