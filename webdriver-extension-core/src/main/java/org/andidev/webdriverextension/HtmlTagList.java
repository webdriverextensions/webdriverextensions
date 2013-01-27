package org.andidev.webdriverextension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HtmlTagList<T extends HtmlTag> implements List<T> {

    private Class<T> htmlTagClass;
    public By by;
    private List<WebElement> wrappedWebElements;
    private List<T> htmlTags;
    private HtmlTagFactory htmlTagFactory = new DefaultHtmlTagFactory();
    private WebDriver driver;

    public HtmlTagList(Class<T> htmlTagClass, List<WebElement> webElements, HtmlTagFactory htmlTagFactory, By by, WebDriver driver) {
        this.htmlTagClass = htmlTagClass;
        this.by = by;
        this.wrappedWebElements = webElements;
        this.htmlTagFactory = htmlTagFactory;
        this.driver = driver;
    }

    public void createHtmlTags() {
        htmlTags = new ArrayList<T>();
        for (WebElement webElement : wrappedWebElements) {
            try {
                // Create html tag and add to list
                T htmlTag = htmlTagFactory.create(htmlTagClass, webElement, by);
                PageFactory.initElements(new DefaultHtmlTagFieldDecorator(webElement, driver), htmlTag);
                htmlTags.add(htmlTag);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int size() {
        createHtmlTags();
        return htmlTags.size();
    }

    @Override
    public boolean isEmpty() {
        createHtmlTags();
        return htmlTags.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        createHtmlTags();
        return htmlTags.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        createHtmlTags();
        return htmlTags.iterator();
    }

    @Override
    public Object[] toArray() {
        createHtmlTags();
        return htmlTags.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        createHtmlTags();
        return htmlTags.toArray(ts);
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
        createHtmlTags();
        return htmlTags.containsAll(clctn);
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
        createHtmlTags();
        return htmlTags.get(i);
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
        createHtmlTags();
        return htmlTags.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        createHtmlTags();
        return htmlTags.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        createHtmlTags();
        return htmlTags.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        createHtmlTags();
        return htmlTags.listIterator();
    }

    @Override
    public List<T> subList(int i, int i1) {
        createHtmlTags();
        return htmlTags.subList(i, i1);
    }
}