# BeanDefinition

1. 스프링 빈의 메타 정보를 설정.
2. 스프링 컨테이너는 BeanDefinition 인터페이스만 의존한다.
3. 특정한 설정파일 (XML, Properties, JavaConfig 등) 지원하는 것은 BeanDefinition 구현에서 처리한다.
    3-1. XmlBeanDefinitionReader, AnnotatedBeanDefinitionReader, etc...
4. 