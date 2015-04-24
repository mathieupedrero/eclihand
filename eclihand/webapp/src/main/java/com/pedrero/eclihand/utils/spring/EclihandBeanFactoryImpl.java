package com.pedrero.eclihand.utils.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class EclihandBeanFactoryImpl implements EclihandBeanFactory {

	@Autowired
	private BeanFactory beanFactory;

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<T> clazz, Object... args) {
		return (T) beanFactory.getBean(getDefaultBeanName(clazz), args);
	}

	private String getDefaultBeanName(Class<?> clazz) {
		if (countFirstUpperCase(clazz.getSimpleName()) > 1) {
			return clazz.getSimpleName();
		} else {
			return StringUtils.uncapitalize(clazz.getSimpleName());
		}
	}

	private int countFirstUpperCase(String arg) {
		int count = 0;
		for (char ch : arg.toCharArray()) {
			if (Character.isUpperCase(ch)) {
				count++;
			} else {
				return count;
			}
		}
		return count;
	}

	@Override
	public boolean containsBean(String arg0) {
		return beanFactory.containsBean(arg0);
	}

	@Override
	public String[] getAliases(String arg0) {
		return beanFactory.getAliases(arg0);
	}

	@Override
	public Object getBean(String arg0) {
		return beanFactory.getBean(arg0);
	}

	@Override
	public <T> T getBean(Class<T> arg0) {
		return beanFactory.getBean(arg0);
	}

	@Override
	public <T> T getBean(String arg0, Class<T> arg1) {
		return beanFactory.getBean(arg0, arg1);
	}

	@Override
	public Object getBean(String arg0, Object... arg1) {
		return beanFactory.getBean(arg0, arg1);
	}

	@Override
	public Class<?> getType(String arg0) {
		return beanFactory.getType(arg0);
	}

	@Override
	public boolean isPrototype(String arg0) {
		return beanFactory.isPrototype(arg0);
	}

	@Override
	public boolean isSingleton(String arg0) {
		return beanFactory.isSingleton(arg0);
	}

	@Override
	public boolean isTypeMatch(String arg0, Class<?> arg1) {
		return beanFactory.isTypeMatch(arg0, arg1);
	}

}
