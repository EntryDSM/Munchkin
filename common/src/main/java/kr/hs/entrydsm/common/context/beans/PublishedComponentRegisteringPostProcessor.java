package kr.hs.entrydsm.common.context.beans;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

import java.util.Objects;

public class PublishedComponentRegisteringPostProcessor implements BeanPostProcessor {

    private static final Class<Published> publishedAnnotation = Published.class;

    private final ConfigurableListableBeanFactory beanFactory;
    private final ConfigurableListableBeanFactory parentBeanFactory;

    public PublishedComponentRegisteringPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        this.parentBeanFactory = getParentBeanFactory(beanFactory);
    }

    private ConfigurableListableBeanFactory getParentBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        if (Objects.isNull(beanFactory)) {
            return null;
        }
        BeanFactory parent = beanFactory.getParentBeanFactory();
        return (parent instanceof ConfigurableListableBeanFactory) ? (ConfigurableListableBeanFactory) parent : null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (Objects.isNull(parentBeanFactory)) {
            return bean;
        }

        if (hasAnnotation(bean)) {
            parentBeanFactory.registerSingleton(beanName, bean);
            return bean;
        }

        try {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (beanDefinition.getSource() instanceof AnnotatedTypeMetadata) {
                AnnotatedTypeMetadata metadata = (AnnotatedTypeMetadata) beanDefinition.getSource();
                if (metadata.isAnnotated(publishedAnnotation.getName())) {
                    parentBeanFactory.registerSingleton(beanName, bean);
                }
            }
        } catch (NoSuchBeanDefinitionException ignore) {
        }

        return bean;
    }

    private boolean hasAnnotation(Object bean) {
        Class<?> beanClass = ClassUtils.getUserClass(bean);
        if (AopUtils.isAopProxy(bean)) {
            beanClass = AopUtils.getTargetClass(bean);
        }
        return AnnotationUtils.findAnnotation(beanClass, publishedAnnotation) != null;
    }

}
