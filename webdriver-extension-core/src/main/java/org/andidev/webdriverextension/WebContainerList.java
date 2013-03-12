package org.andidev.webdriverextension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class WebContainerList<T extends WebContainer> implements List<T> {

    private Class<T> webContainerClass;
    private List<WebElement> wrappedWebElements;
    private List<T> webContainers;
    private WebContainerFactory webContainerFactory = new DefaultWebContainerFactory();
    private WebDriver driver;

    public WebContainerList(Class<T> webContainerClass, List<WebElement> webElements, WebContainerFactory webContainerFactory, WebDriver driver) {
        this.webContainerClass = webContainerClass;
        this.wrappedWebElements = webElements;
        this.webContainerFactory = webContainerFactory;
        this.driver = driver;
    }

    public void createWebContainers() {
        webContainers = new ArrayList<T>();
        for (WebElement webElement : wrappedWebElements) {
            try {
                // Create web container and add it to list
                T webContainer = webContainerFactory.create(webContainerClass, webElement);
                PageFactory.initElements(new DefaultWebContainerFieldDecorator(webElement, driver), webContainer);
                webContainers.add(webContainer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int size() {
        createWebContainers();
        return webContainers.size();
    }

    @Override
    public boolean isEmpty() {
        createWebContainers();
        return webContainers.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        createWebContainers();
        return webContainers.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        createWebContainers();
        return webContainers.iterator();
    }

    @Override
    public Object[] toArray() {
        createWebContainers();
        return webContainers.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        createWebContainers();
        return webContainers.toArray(ts);
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
        createWebContainers();
        return webContainers.containsAll(clctn);
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
        createWebContainers();
        return webContainers.get(i);
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
        createWebContainers();
        return webContainers.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        createWebContainers();
        return webContainers.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        createWebContainers();
        return webContainers.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        createWebContainers();
        return webContainers.listIterator();
    }

    @Override
    public List<T> subList(int i, int i1) {
        createWebContainers();
        return webContainers.subList(i, i1);
    }
}