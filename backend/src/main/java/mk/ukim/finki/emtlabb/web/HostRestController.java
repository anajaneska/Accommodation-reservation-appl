package mk.ukim.finki.emtlabb.web;

import mk.ukim.finki.emtlabb.model.Host;
import mk.ukim.finki.emtlabb.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/hosts")
public class HostRestController {
    private final HostService hostService;

    public HostRestController(HostService hostService) {
        this.hostService = hostService;
    }
    @GetMapping
    public List<Host> findAll() {
        return this.hostService.findAllHosts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Host> findById(@PathVariable Long id) {
        return this.hostService.getHostById(id)
                .map(host -> ResponseEntity.ok().body(host))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Host> save(@RequestParam String name,
                                     @RequestParam String surname,
                                     @RequestParam Long countryId) {
        return this.hostService.saveHost(name, surname,countryId)
                .map(host -> ResponseEntity.ok().body(host))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.hostService.deleteHost(id);
        if (this.hostService.getHostById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
