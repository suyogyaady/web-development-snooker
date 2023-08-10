package com.system.snooker.Controller;

import com.system.snooker.Pojo.BookingPojo;
import com.system.snooker.Pojo.ContactPojo;
import com.system.snooker.Pojo.UserPojo;
import com.system.snooker.Service.BookingService;
import com.system.snooker.Service.ContactService;
import com.system.snooker.Service.SnookerService;
import com.system.snooker.Service.UserService;
import com.system.snooker.entity.Booking;
import com.system.snooker.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class User_Controller {
    private final UserService userService;
    private final BookingService bookingService;
    private final SnookerService snookerService;
    private final ContactService contactService;

    @GetMapping("/index")
    public String index() {

        return "index";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new UserPojo());

        return "signup";
    }


//    @GetMapping("/booked")
//    public String fetchAllbook(Model model ,Integer id){
//        List<Booking> ed = bookingService.fetchAll();
//        model.addAttribute("books", ed.stream().map(booking ->
//                Booking.builder()
//                        .bookId(booking.getBookId())
//                        .date(booking.getDate())
//                        .starting(booking.getStarting())
//                        .ending(booking.getEnding())
//                        .user(booking.getUser())
//                        .snooker(booking.getSnooker())
//                        .build()
//        ));
//        return "bookedsnooker";
//    }

    @GetMapping("/booked/{id}")
    public String fetchAllbook(@PathVariable("id") Integer id, Model model , Principal principal){
        List<Booking> booking= bookingService.findBookingById(id);
        model.addAttribute("books",booking);
        model.addAttribute("userdata",userService.findByEmail(principal.getName()));

        return "bookedsnooker";
    }


    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/home/homepage";
    }

    @PostMapping("/save")
    public String saveUser(@Valid UserPojo userPojo){
        userService.save(userPojo);
        return "redirect:/user/login";
    }

    @GetMapping("/profile")
    public String profile() {

        return "editprofile";
    }

    @GetMapping("/profile/{id}")
    public String getUserProfiile(@PathVariable("id") Integer id, Model model){
        User user = userService.fetchById(id);
        model.addAttribute("users", new UserPojo(user));
        model.addAttribute("currentUser", user);

        return "editprofile";
    }


    @GetMapping("/contactus")
    public String contact(Model model) {
        model.addAttribute("contact", new ContactPojo());
        return "Contactus";
    }


    @PostMapping("/savecontact")
    public String save(@Valid ContactPojo contactPojo) {
        contactService.save(contactPojo);
        return "redirect:/home/homepage";
    }



        @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model){
        User user =userService.fetchById(id);
        model.addAttribute("currentUser", new UserPojo(user));
        return "redirect:/home/homepage";
    }

    @GetMapping("/product/{id}")
    public String getSnookerProfiile(@PathVariable("id") Integer id, Model model, Principal principal ){
        Booking booking = bookingService.fetchById(id);

        model.addAttribute("userdata",userService.findByEmail(principal.getName()));
        model.addAttribute("bookedsnooker", booking);
        return "editbooking";
    }

//    @GetMapping("/editbook/{id}")
//    public String editbook(@PathVariable("id") Integer id, Model model){
//        Booking booking =bookingService.fetchById(id);
//        model.addAttribute("bookedsnooker", new BookingPojo(booking));
//        return "redirect:/home/homepage";
//    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return "redirect:/user/login";
    }

    @GetMapping("/deletebook/{id}")
    public String deletebooking(@PathVariable("id") Integer id) {
        bookingService.deleteById(id);
        return "redirect:/home/homepage";
    }


    @PostMapping("/updatebooking")
    public String savebooking(@Valid BookingPojo bookingPojo){
        bookingService.saveOrder(bookingPojo);
        return "redirect:/home/homepage";
    }




    @GetMapping("/forgotpassword")
    public String forgotpassword(Model model){
        model.addAttribute("users",new UserPojo());
        return ("forgetpassword");
    }

    @PostMapping("/changepassword")
    public String changepassword(@RequestParam("email") String email, Model model, @Valid UserPojo userPojo){
        userService.processPasswordResetRequest(userPojo.getEmail());
        model.addAttribute("message","Your new password has been sent to your email Please " +
                "check your inbox");
        return "redirect:/user/login";
    }



}







