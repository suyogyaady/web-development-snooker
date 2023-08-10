package com.system.snooker.Controller;

import com.system.snooker.Pojo.BookingPojo;
import com.system.snooker.Pojo.SnookerPojo;
import com.system.snooker.Service.BookingService;
import com.system.snooker.Service.SnookerService;
import com.system.snooker.Service.UserService;
import com.system.snooker.entity.Snooker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.sql.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ball")
public class SnookerController {
    private final SnookerService snookerService;
    private final UserService userService;
    private final BookingService bookingService;
//    @GetMapping("/product")
//    public String product() {
//
//        return "booksnooker";
//    }

    @GetMapping("/product/{id}")
    public String getSnookerProfiile(@PathVariable("id") Integer id, Model model, Principal principal){
        Snooker snooker = snookerService.fetchById(id);
        model.addAttribute("userdata",userService.findByEmail(principal.getName()));

        model.addAttribute("savebooking", new BookingPojo() );

        model.addAttribute("snookers", new SnookerPojo(snooker));
//
        model.addAttribute("clickedsnooker", snooker);

        return "booksnooker";
    }

    @PostMapping("/sbooking")
    public String savebooking(@Valid BookingPojo bookingPojo){
        if (!bookingService.bookedTime(Date.valueOf(bookingPojo.getDate()), bookingPojo.getFid()).contains(bookingPojo.getStarting())) {
            bookingService.saveOrder(bookingPojo);
            return "redirect:/home/homepage";
        } else return "redirect:/ball/product/"+bookingPojo.getFid();
    }

    @GetMapping("/addsnooker")
    public String createSnooker(Model model) {
        model.addAttribute("snooker", new SnookerPojo());

        return "snooker";
    }
    @PostMapping("/save")
    public String savesnooker(@Valid SnookerPojo snookerPojo, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException{
        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/admin/addsnooker";
        }
        snookerService.savesnooker(snookerPojo);
        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
        return "redirect:/admin/dashboard";
    }

    private Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;
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












}
