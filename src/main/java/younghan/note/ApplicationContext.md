# ApplicationContext

## BeanFactory

1. 클래스를 빈으로 등록하고 생명주기 관리 등 다양한 기능 처리

### AnnotationConfigApplicationContext

1. AppConfig.class 사용

### GenericXmlApplicationContext

1. appConfig.xml 사용
2. 컴파일 과정 없이 구성정보를 변경할 수 있다.

## MessageSource

1. 한글, 영어 등 접속주소에 따라서 다른 언어를 출력하도록 처리

## EnvironmentCapable

1. 로컬, 개발, 스테이징, 운영 등 구분해서 처리

## ApplicationEventPublisher

1. 앱 내부에서 이벤트를 발행하고 구독하는 기능을 제공

## ResourceLoader

1. 파일, 클래스패스, 외부 서버 등에서 리소스 조회하는 것을 도와주는 기능을 제공