package com.example.spotify.controller;

import com.example.spotify.entity.User;
import com.example.spotify.entity.Album;
import com.example.spotify.repository.UserRepository;
import com.example.spotify.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;

    //ყველა იუზერის წამოღება
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    //  იუზერის წაშლა
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // იუზერის დაბლოკვა
    @PutMapping("/users/{id}/block")
    public ResponseEntity<String> blockUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(Status.BLOCKED);
        userRepository.save(user);
        return ResponseEntity.ok("User blocked successfully");
    }

    //  იუზერის განახლება, როლის შეცვლა
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(updatedUser.getEmail());
        user.setRole(updatedUser.getRole());
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    //  ალბომების წამოღება
    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getAllAlbums() {
        return ResponseEntity.ok(albumRepository.findAll());
    }

    // 🔹 ალბომის წაშლა
    @DeleteMapping("/albums/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 ალბომის განახლება
    @PutMapping("/albums/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album updatedAlbum) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album not found"));
        album.setName(updatedAlbum.getName());
        album.setGenre(updatedAlbum.getGenre());
        albumRepository.save(album);
        return ResponseEntity.ok(album);
    }
}
