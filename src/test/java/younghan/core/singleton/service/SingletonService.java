package younghan.core.singleton.service;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    private SingletonService() {}

    public static SingletonService getInstance() {
        return instance;
    }

    public void print() {
        System.out.println("싱글톤 객체 생성 완료! " + getClass());
    }
}
