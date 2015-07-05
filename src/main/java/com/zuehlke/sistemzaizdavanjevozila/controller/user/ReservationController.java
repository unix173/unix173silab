package com.zuehlke.sistemzaizdavanjevozila.controller.user;

import com.zuehlke.sistemzaizdavanjevozila.core.ReservationUtil;
import com.zuehlke.sistemzaizdavanjevozila.form.AddReservationEntryForm;
import com.zuehlke.sistemzaizdavanjevozila.model.Reservation;
import com.zuehlke.sistemzaizdavanjevozila.model.ReservationEntry;
import com.zuehlke.sistemzaizdavanjevozila.service.ItemTypeService;
import com.zuehlke.sistemzaizdavanjevozila.service.ReservationService;
import com.zuehlke.sistemzaizdavanjevozila.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"addReservationEntryForms"})
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemTypeService itemTypeService;

    @RequestMapping(value = "user/addReservation", method = RequestMethod.GET)
    public String addReservationView(Model model) {
        if (!model.containsAttribute("addReservationEntryForms")) {
            model.addAttribute("addReservationEntryForms", new ArrayList<AddReservationEntryForm>());
        }
        if (!model.containsAttribute("addReservationEntryForm")) {
            model.addAttribute("addReservationEntryForm", new AddReservationEntryForm());
        }
        model.addAttribute("reservationInProgress", false);
        return "user/addReservation";
    }

    @RequestMapping(value = "user/addReservation", method = RequestMethod.POST)
    public String addReservationProcess(@ModelAttribute("addReservationEntryForm") @Valid AddReservationEntryForm addReservationEntryForm,
                                        BindingResult result, Model model,
                                        @ModelAttribute("addReservationEntryForms") List<AddReservationEntryForm> addReservationEntryForms) {
        if (result.hasErrors()) {
            model.addAttribute("reservationInProgress", false);
            model.addAttribute("dateValidationError", "Invalid date interval");
            addReservationEntryForm.setReservationEndDate(null);
            addReservationEntryForm.setReservationStartDate(null);
        } else {
            model.addAttribute("reservationInProgress", true);
            model.addAttribute("itemTypeDTOList", itemTypeService.getAvailableItemTypes(ReservationUtil.reservedItemTypeIdList(addReservationEntryForms),
                    addReservationEntryForm.getReservationStartDate(), addReservationEntryForm.getReservationEndDate()));
        }
        model.addAttribute("addReservationEntryForm", addReservationEntryForm);
        return "user/addReservation";
    }

    @RequestMapping(value = "user/actionAddEntry", method = RequestMethod.POST)
    public String addReservationEntry(@ModelAttribute("addReservationEntryForm") @Valid AddReservationEntryForm addReservationEntryForm, BindingResult result, @ModelAttribute("addReservationEntryForms") List<AddReservationEntryForm> addReservationEntryForms,
                                      WebRequest request, Errors errors, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            addReservationEntryForm.setDesiredQuantity(null);
            redirectAttributes.addFlashAttribute("addReservationEntryForm", addReservationEntryForm);
            redirectAttributes.addFlashAttribute("validationFailed", true);
            redirectAttributes.addFlashAttribute("itemTypeDTOList", itemTypeService.getAvailableItemTypes(ReservationUtil.reservedItemTypeIdList(addReservationEntryForms),
                    addReservationEntryForm.getReservationStartDate(), addReservationEntryForm.getReservationEndDate()));
            return "redirect:/user/addReservation";
        } else {
            addReservationEntryForms.add(addReservationEntryForm);
        }
        return "redirect:/user/addReservation";
    }

    @RequestMapping(value = "user/actionAddReservation", method = RequestMethod.POST)
    public String addReservation(@ModelAttribute("addReservationEntryForms") List<AddReservationEntryForm> addReservationEntryForms, Principal principal, RedirectAttributes redirectAttributes) {

        reservationService.addReservation(reservationService.createReservation(addReservationEntryForms, userService.getUserByUsername(((User) ((Authentication) principal).getPrincipal()).getUsername())));
        redirectAttributes.addFlashAttribute("successfulReservation", true);
        //do a redirect to user's reservation page
        redirectAttributes.addFlashAttribute("addReservationEntryForms", new ArrayList<AddReservationEntryForm>());
        redirectAttributes.addFlashAttribute("reservationEntry", new ReservationEntry());
        return "redirect:/user/addReservation";
    }

    @RequestMapping(value = "user/actionRestartReservation", method = RequestMethod.POST)
    public String restartReservation(@ModelAttribute("addReservationEntryForms") List<AddReservationEntryForm> addReservationEntryForms){
        addReservationEntryForms.clear();
        return "redirect:/user/addReservation";
    }

    @RequestMapping(value = "user/viewUserReservations", method = RequestMethod.GET)
    public String viewUserReservations(Model model, Principal principal) {
        List<Reservation> reservations = reservationService.getReservationsByUserId(userService.getUserByUsername(((User) ((Authentication) principal).getPrincipal()).getUsername()).getId());
        Reservation reservation = new Reservation();
        reservation.setUser(new com.zuehlke.sistemzaizdavanjevozila.model.User());
        model.addAttribute("reservation", reservation);
        model.addAttribute("reservations", reservations);
        return "user/viewUserReservations";
    }

    @RequestMapping(value = "user/viewUserReservationEntries", method = RequestMethod.GET)
    public String viewUserReservationEntriesAction(@ModelAttribute("reservation") Reservation reservation, Model model) {
        List<ReservationEntry> reservationEntries = new ArrayList<ReservationEntry>(reservationService.getReservationById(reservation.getId()).getReservationEntries());
        model.addAttribute("reservationEntries", reservationEntries);
        return "user/viewUserReservationEntries";
    }



}