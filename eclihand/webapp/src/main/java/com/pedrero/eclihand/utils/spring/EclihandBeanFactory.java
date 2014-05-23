package com.pedrero.eclihand.utils.spring;

import org.springframework.beans.factory.BeanFactory;

public interface EclihandBeanFactory extends BeanFactory {
    
    /**
     * Recupère un bean dans le conteneur Spring.
     * Le bean doit avoir le nom par defaut (uncapitalize(Class.getSimpleName())
     * @param clazz
     *            : la classe du bean
     * @param args
     *            : liste de paramètres
     * @return : le bean
     */
    <T> T getBean(Class<T> clazz, Object... args);
    
}
