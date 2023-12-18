package io.cedricksonga.springpro.controller;

import io.cedricksonga.springpro.model.Citizen;
import io.cedricksonga.springpro.securityy.EmailSenderServiceConfig;
import io.cedricksonga.springpro.service.CitizenInterface;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CitizenController {
    @Autowired
    private CitizenInterface citizenService;
    @Autowired
    private EmailSenderServiceConfig emailSenderServiceConfig;

    @GetMapping("/home")
    public String homePage(Model model){
        return findPagenated(1, model);
    }
    @GetMapping("/inde")
    public String ind() {
        return "homes";
    }
    @GetMapping("/")
    public String homme() {
        return "aboutus";
    }
    @GetMapping("/come")
    public String homes() {
        return "aboutus";
    }
    @GetMapping("/citizen")
    public String candidate() {
        return "package";
    }
    @GetMapping("/tem")
    public String temp() {
        return "templa";
    }
    @GetMapping("/registration1")
    public String registerCitizenPage(Model model) {
        Citizen cit = new Citizen();
        model.addAttribute("citizen", cit);
        return "registrar";
    }
    @GetMapping("/citizen-page")
    public String studentpage(Model model) {
        Citizen cit = new Citizen();
        model.addAttribute("citizen", cit);
        return "citizen";
    }
    @PostMapping("/register")
    public String registerCitizen(@ModelAttribute("citizen") Citizen theCitizen) throws MessagingException {

        Citizen savedCitizen = citizenService.registerCitizen(theCitizen);
        if (savedCitizen != null) {
            emailSenderServiceConfig.sendCitizenEmail(theCitizen.getEmail(), "REGISTRATION", theCitizen.getNames());
            return "redirect:/citizen-page?success";
        } else {
            return "redirect:/citizen-page?error";
        }
    }
    @PostMapping("/reg")
    public String registerCitizeninDb(@ModelAttribute("citizen") Citizen theCitizen) throws MessagingException {

        Citizen savedCitizen = citizenService.registerCitizen(theCitizen);
        if (savedCitizen != null) {
            emailSenderServiceConfig.sendCitizenEmail(theCitizen.getEmail(), "REGISTRATION", theCitizen.getNames());

            return "redirect:/registration1?success";
        } else {
            return "redirect:/registration1?error";
        }
    }
    @GetMapping("/home/update/{id}")
    public String editCitizen(@PathVariable String id, Model model) {

        Long citizenId = Long.parseLong(id);
        model.addAttribute("citizen", citizenService.findCitizenByCitizenId(citizenId));
        return "update";
    }

    @PostMapping("/home/{id}")
    public String updateCitizen(@PathVariable String id, @ModelAttribute("citizen") Citizen citizen, Model model){
        Long citizenId = Long.parseLong(id);
        Citizen existingCitizen = citizenService.findCitizenByCitizenId(citizenId);
        existingCitizen.setTel(citizen.getTel());
        existingCitizen.setId(citizen.getId());
        existingCitizen.setNames(citizen.getNames());
        existingCitizen.setFile(citizen.getFile());
        existingCitizen.setDate(citizen.getDate());
        existingCitizen.setEmail(citizen.getEmail());
        citizenService.updateCitizen(existingCitizen);
        return "redirect:/home";
    }

    @GetMapping("/home/{id}")
    public String deleteCitizen(@PathVariable String id){
        Long citizenId = Long.parseLong(id);
        citizenService.deleteCitizen(citizenId);
        return "redirect:/home";
    }

    @GetMapping("/sear")
    public String search(Model model){
        Citizen citizen = new Citizen();
        model.addAttribute("search", citizen);
        return "search";
    }

    @PostMapping("/sear")
    public String searchh(@ModelAttribute("search") Citizen citizen, Model model){
        Citizen citizen1 = citizenService.findCitizenByCitizenId(citizen.getId());
        if(citizen1 != null) {
            model.addAttribute("citizen1", citizen1);
            return "search";
        } else {
            model.addAttribute("error", "this citizen does not exist");
            return "search";
        }
    }


    @GetMapping("/page/{pageNo}")
    private String findPagenated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;
        Page<Citizen> page = citizenService.pagenateCitizen(pageNo, pageSize);
        List<Citizen> citizenList = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listCitizens",citizenList);
        return "home-page";
    }

}