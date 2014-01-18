package com.github.webdriverextensions.internal;

import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import com.github.webdriverextensions.WebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class WebComponentList<T extends WebComponent> implements List<T> {

    private Class<T> webComponentClass;
    private List<WebElement> wrappedWebElements;
    private List<T> webComponents;
    private WebComponentFactory webComponentFactory = new DefaultWebComponentFactory();
    private WebDriver driver;

    public WebComponentList(Class<T> webComponentClass, List<WebElement> webElements, WebComponentFactory webComponentFactory, WebDriver driver) {
        this.webComponentClass = webComponentClass;
        this.wrappedWebElements = webElements;
        this.webComponentFactory = webComponentFactory;
        this.driver = driver;
    }

    public void createWebComponents() {
        webComponents = new ArrayList<T>();
        for (WebElement webElement : wrappedWebElements) {
            try {
                // Create web component and add it to list
                T webComponent = webComponentFactory.create(webComponentClass, webElement);
                PageFactory.initElements(new WebDriverExtensionFieldDecorator(webElement, driver), webComponent);
                webComponents.add(webComponent);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int size() {
        createWebComponents();
        return webComponents.size();
    }

    @Override
    public boolean isEmpty() {
        createWebComponents();
        return webComponents.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        createWebComponents();
        return webComponents.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        createWebComponents();
        return webComponents.iterator();
    }

    @Override
    public Object[] toArray() {
        createWebComponents();
        return webComponents.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        createWebComponents();
        return webComponents.toArray(ts);
    }

    @Override
    public boolean add(T e) {
        throw new UnsupportedOperationException("This collection is imnmutable and therefore this method cannot be called.");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("This collection is imnmutable and therefore this method cannot be called.");
    }

    @Override
    public boolean containsAll(Collection<?> clctn) {
        createWebComponents();
        return webComponents.containsAll(clctn);
    }

    @Override
    public boolean addAll(Collection<? extends T> clctn) {
        throw new UnsupportedOperationException("This collection is imnmutable and therefore this method cannot be called.");
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> clctn) {
        throw new UnsupportedOperationException("This collection is imnmutable and therefore this method cannot be called.");
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("This collection is imnmutable and therefore this method cannot be called.");
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("This collection is imnmutable and therefore this method cannot be called.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("This collection is imnmutable and therefore this method cannot be called.");
    }

    @Override
    public T get(int i) {
        createWebComponents();
        return webComponents.get(i);
    }

    @Override
    public T set(int i, T e) {
        throw new UnsupportedOperationException("This collection is imnmutable and therefore this method cannot be called.");
    }

    @Override
    public void add(int i, T e) {
        throw new UnsupportedOperationException("This collection is imnmutable and therefore this method cannot be called.");
    }

    @Override
    public T remove(int i) {
        throw new UnsupportedOperationException("This collection is imnmutable and therefore this method cannot be called.");
    }

    @Override
    public int indexOf(Object o) {
        createWebComponents();
        return webComponents.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        createWebComponents();
        return webComponents.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        createWebComponents();
        return webComponents.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        createWebComponents();
        return webComponents.listIterator();
    }

    @Override
    public List<T> subList(int i, int i1) {
        createWebComponents();
        return webComponents.subList(i, i1);
    }
}