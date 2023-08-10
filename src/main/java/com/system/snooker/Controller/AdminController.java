package com.system.snooker.Controller;

import com.system.snooker.Pojo.BookingPojo;
import com.system.snooker.Pojo.SnookerPojo;
import com.system.snooker.Service.BookingService;
import com.system.snooker.Service.ContactService;
import com.system.snooker.Service.SnookerService;
import com.system.snooker.Service.UserService;
import com.system.snooker.entity.Booking;
import com.system.snooker.entity.Contact;
import com.system.snooker.entity.Snooker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final SnookerService snookerService;
    private final BookingService bookingService;
    private final ContactService contactService;

    @GetMapping("/dashboard")
    public String fetchAllbooking(Model model){
        List<Booking> adminbooking = bookingService.fetchAll();
        model.addAttribute("book", adminbooking.stream().map(booking ->
                Booking.builder()
                        .bookId(booking.getBookId())
                        .date(booking.getDate())
                        .starting(booking.getStarting())
                        .user(booking.getUser())
                        .snooker(booking.getSnooker())
                        .email(booking.getEmail())
                        .build()
        ));

        model.addAttribute("books", new BookingPojo());



        return "dashboard";
    }

    @GetMapping("/contact")
    public String createcontact(Model model) {
        List<Contact> admincontact = contactService.fetchAll();
        model.addAttribute("contact", admincontact.stream().map(contact ->
                Contact.builder()
                        .contactId(contact.getContactId())
                        .contactname(contact.getContactname())
                        .contactemail(contact.getContactemail())
                        .contactsubject(contact.getContactsubject())
                        .contactmessage(contact.getContactmessage())
                        .build()
        ));
        return "viewreview";
    }

    @GetMapping("/view")
    public String fetchAllSnookerpool(Model model){
        List<Snooker> adminsnooker = snookerService.fetchAll();
        model.addAttribute("snookers", adminsnooker.stream().map(snooker ->
                snooker.builder()
                        .fut_salId(snooker.getFut_salId())
                        .snookername(snooker.getSnookername())
                        .snookerprice(snooker.getSnookerprice())
                        .snookercontact(snooker.getSnookercontact())
                        .snookerlocation(snooker.getSnookerlocation())
                        .Description(snooker.getDescription())
                        .imageBase64(getImageBase64(snooker.getSnookerimage()))
                        .image1Base64(getImageBase64(snooker.getSnookerimage1()))
                        .image2Base64(getImageBase64(snooker.getSnookerimage2()))
                        .build()
        ));
        return "viewsnooker";
    }


    @GetMapping("/del/{id}")
    public String deletereview(@PathVariable("id") Integer id) {
        contactService.deleteById(id);
        return "redirect:/admin/dashboard";
    }


    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/images/";
        File file = new File(filePath + fileName);
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }

//    @GetMapping("/review")
//    public String review() {
//
//        return "viewreview";
//    }
    @GetMapping("/report")
    public String report() {

        return "report";
    }

    @GetMapping("/product/{id}")
    public String getSnookerProfiile(@PathVariable("id") Integer id, Model model ){
        Snooker snooker = snookerService.fetchById(id);
        model.addAttribute("snookers", new SnookerPojo(snooker));

        model.addAttribute("clickedsnooker", snooker);
        return "editsnooker";
    }
    @GetMapping("/edit/{id}")
    public String editsnooker(@PathVariable("id") Integer id, Model model){
        Snooker snooker =snookerService.fetchById(id);
        model.addAttribute("clickedsnooker", new SnookerPojo(snooker));
        return "redirect:/admin/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        snookerService.deleteById(id);
        return "redirect:/admin/view";
    }


    @PostMapping("/changepassword")
    public String changepassword(@RequestParam("email") String email, Model model, @Valid BookingPojo bookingPojo){
        bookingService.processPasswordResetRequest(bookingPojo.getEmail());
        model.addAttribute("message","Your new password has been sent to your email Please " +
                "check your inbox");
        return "redirect:/admin/report";
    }






}