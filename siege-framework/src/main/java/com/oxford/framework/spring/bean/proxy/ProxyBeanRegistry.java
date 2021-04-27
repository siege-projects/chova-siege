package com.oxford.framework.spring.bean.proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 代理Bean操作 - Bean注册类
 *
 * @author Chova
 * @date 2021/04/23
 */
public class ProxyBeanRegistry implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware, ResourceLoaderAware {

    /**
     * 项目包的名称,可以从指定配置文件中配置获取.形如 com.oxford.framework.spring.bean
     */
    private String basePackage;

    /**
     * 应用上下文
     */
    private ApplicationContext applicationContext;

    /**
     * 资源解析器
     */
    private ResourcePatternResolver resourcePatternResolver;

    /**
     * 访问类的元数据的读取器
     */
    private MetadataReaderFactory metadataReaderFactory;


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        /*
         * 首先获取需要进行代理Bean操作的类
         *
         * 通常是使用反射获取需要进行代理Bean操作的类集合。使用Set防止有重复的类
         *
         * 使用反射可以获取指定包下面的类，或者是注解标注的类
         */
        Set<Class<?>> classSet = proxyBeanScan(basePackage);
        for (Class beanClass : classSet) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionBuilder.getRawBeanDefinition();
            /*
             * 给Bean对象的属性中注入对应的实例。比如MyBatis中注入的DataSource和SqlSessionFactory
             *
             * 注意：
             * - 如果使用genericBeanDefinition.getPropertyValues()方式，形如genericBeanDefinition.getPropertyValues().add("beanInterface", beanClass)，
             * 那么要求在FactoryBean中提供setter方法，否则会导致注入失败
             * - 如果使用genericBeanDefinition.getConstructorArgumentValues()方式，那么要求在FactoryBean中提供包含beanClass属性的构造方法，否则会导致注入失败
             */
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanClass);
            // 将Bean对象对应的实例注入到Bean对象的属性中.可以使用AUTOWIRE_BY_TYPE方式和AUTOWIRE_BY_NAME方式
            beanDefinition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
            // 注册Bean定义的对象
            registry.registerBeanDefinition(beanClass.getSimpleName(), beanDefinition);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    /**
     * 扫描获取指定项目包路径下面的类
     *
     * @param basePackage 项目包路径
     * @return Set<Class < ?>> 类的Set集合
     */
    private Set<Class<?>> proxyBeanScan(String basePackage) {
        Set<Class<?>> proxyBeanSet = new LinkedHashSet<>();

        /**
         * 项目基本包的路径
         */
        String basePackagePath = ClassUtils.convertClassNameToResourcePath(applicationContext.getEnvironment().resolveRequiredPlaceholders(basePackage));
        /**
         * 代理Bean操作的类所在路径
         */
        String proxyBeanPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + basePackagePath + "/**/*.class";

        try {
            Resource[] resources = resourcePatternResolver.getResources(proxyBeanPath);
            // 遍历包路径下的所有类，将类添加到代理Bean集合中
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    // 使用类的读取器获取类的元素据
                    MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                    // 获取类的名称
                    String className = metadataReader.getClassMetadata().getClassName();
                    // 根据类的名称获取指定的类
                    Class<?> clazz = Class.forName(className);
                    // 将获取到的类添加到Set集合中
                    proxyBeanSet.add(clazz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proxyBeanSet;
    }

    /**
     * 设置应用上下文
     *
     * @param applicationContext 应用上下文
     * @throws BeansException Bean异常
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 设置资源加载器
     *
     * @param resourceLoader 资源加载器
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
    }
}
