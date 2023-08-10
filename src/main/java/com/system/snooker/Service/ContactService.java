package com.system.snooker.Service;

import com.system.snooker.Pojo.ContactPojo;
import com.system.snooker.entity.Contact;

import java.util.List;

public interface ContactService {
    String save(ContactPojo contactPojo);

    List<Contact> fetchAll();

    void deleteById(Integer id);
}
