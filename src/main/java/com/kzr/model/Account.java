package com.kzr.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/**
 * Created by Kamil on 2017-08-14.
 */
@Component
public class Account implements Comparable<Account> {
    private String id;
    private BigDecimal birthday;
    private String firstname;
    private String lastname;
    private String occupation;
    private String gender;
    private City city;
    private String work;
    private String[] friends;
    private String school;
    private String location;
    private String relationship;
    private Map<String, String>[] posts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getBirthday() {
        return birthday;
    }

    public void setBirthday(BigDecimal birthday) {
        this.birthday = birthday;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String[] getFriends() {
        return friends;
    }

    public void setFriends(String[] friends) {
        this.friends = friends;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Map<String, String>[] getPosts() {
        return posts;
    }

    public void setPosts(Map<String, String>[] posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", birthday=" + birthday +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", occupation='" + occupation + '\'' +
                ", gender='" + gender + '\'' +
                ", city=" + city +
                ", work='" + work + '\'' +
                ", friends=" + Arrays.toString(friends) +
                ", school='" + school + '\'' +
                ", location='" + location + '\'' +
                ", relationship='" + relationship + '\'' +
                ", posts=" + Arrays.toString(posts) +
                '}';
    }

    @Override
    public int compareTo(Account account) {
        return Comparator.comparing(Account::getFirstname)
                .thenComparing(Account::getLastname)
                .compare(this, account);
    }
}