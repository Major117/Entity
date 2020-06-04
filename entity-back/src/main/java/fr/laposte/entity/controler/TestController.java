package fr.laposte.entity.controler;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('Admin') or hasRole('Gestionnaire')")
    public String userAccess() {
        return "Admin et Gestionnaire Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('Gestionnaire')")
    public String moderatorAccess() {
        return "Gestionnaire Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('Admin')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
