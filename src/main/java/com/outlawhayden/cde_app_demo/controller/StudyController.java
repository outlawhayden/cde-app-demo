package com.outlawhayden.cde_app_demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.outlawhayden.cde_app_demo.model.Study;
import com.outlawhayden.cde_app_demo.repository.StudyRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class StudyController {

    @Autowired
    StudyRepository studyRepository;

    @GetMapping("/studies")
    public ResponseEntity<List<Study>> getAllStudies(@RequestParam(required = false) String title) {
        List<Study> studies = new ArrayList<Study>();

        try {

            if (title == null)
                studyRepository.findAll().forEach(studies::add);
            else
                studyRepository.findByTitleContaining(title).forEach(studies::add);

            if (studies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(studies, HttpStatus.OK);
    }



    @GetMapping("/studies/{id}")
    public ResponseEntity<Study> getStudyByID(@PathVariable("id") long id) {
        Optional<Study> studyData = studyRepository.findById(id);

        if (studyData.isPresent()) {
            return new ResponseEntity<>(studyData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/studies")
    public ResponseEntity<Study> createStudy(@RequestBody Study study) {
        try {
            Study _study = studyRepository
                    .save(new Study(study.getTitle(), study.getDescription(), false));
            return new ResponseEntity<>(_study, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/studies/{id}")
    public ResponseEntity<Study> updateStudy(@PathVariable("id") long id, @RequestBody Study study) {
        Optional<Study> studyData = studyRepository.findById(id);

        if (studyData.isPresent()) {
            Study _study = studyData.get();
            _study.setTitle(study.getTitle());
            _study.setDescription(study.getDescription());
            _study.setPublished(study.isPublished());
            return new ResponseEntity<>(studyRepository.save(_study), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteStudy(@PathVariable("id") long id) {
        try {
            studyRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/studies")
    public ResponseEntity<HttpStatus> deleteAllStudies() {
        try {
            studyRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/studies/published")
    public ResponseEntity<List<Study>> findByPublished() {
        try {
            List<Study> studies = studyRepository.findByPublished(true);

            if (studies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(studies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}