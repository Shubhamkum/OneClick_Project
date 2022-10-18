package com.oneclick.carbon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oneclick.carbon.Resource;
import com.oneclick.carbon.Repository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ResourceController {

    @Autowired
    Repository Repository;

    @GetMapping("/resources")
    public ResponseEntity<List<Resource>> getAllResources(@RequestParam(required = false) String title) {
        try {
            List<Resource> resources = new ArrayList<Resource>();

            if (title == null)
                Repository.findAll().forEach(resources::add);
        //    else
       //         Repository.findByTitleContaining(title).forEach(resources::add);

            if (resources.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(resources, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/resources/total")
    public ResponseEntity<List<Resource>> getAllResourcesTotal(@RequestParam(required = false) String title) {
        try {
            List<Resource> resources = new ArrayList<Resource>();

            if (title == null)
                Repository.findAll().forEach(resources::add);
            //    else
            //         Repository.findByTitleContaining(title).forEach(resources::add);
            double total_impact=0;
            double total_ImpactGWP100_kgCO2=0;
            double total_getImpactAP_kgSO2e=0;
            for (Resource i : resources) {
                total_impact += i.getTotal_impact();
                total_ImpactGWP100_kgCO2+=i.getTotal_impactGWP100_kgCO2e();
                total_getImpactAP_kgSO2e+=i.getTotal_impactAP_kgSO2e();

                // Printing the elements of ArrayList
                 System.out.print(i + " ");

            }
            List l=new ArrayList();
            l.add(total_impact);
            l.add(total_ImpactGWP100_kgCO2);
            l.add(total_getImpactAP_kgSO2e);
            if (resources.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }


            return new ResponseEntity<>(l, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/resources/{id}")
    public ResponseEntity<Resource> getResourcesById(@PathVariable("id") long id) {
        Optional<Resource> resourceData = Repository.findById(id);

        if (resourceData.isPresent()) {
            return new ResponseEntity<>(resourceData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/resources")
    public ResponseEntity<Resource> createResources(@RequestBody Resource resource) {
        double total_ImpactGWP100_kgCO2=resource.getQuantity()*resource.getImpactGWP100_kgCO2e();
        double total_getImpactAP_kgSO2e=resource.getImpactAP_kgSO2e()*resource.getQuantity();
        double total_impact=total_ImpactGWP100_kgCO2+total_getImpactAP_kgSO2e;
        try {
            Resource _resource = Repository
                    .save(new Resource(resource.getId(), resource.getResourceId(), resource.getName(),resource.getImpactGWP100_kgCO2e(),resource.getImpactAP_kgSO2e(),resource.getQuantity(),total_ImpactGWP100_kgCO2,total_getImpactAP_kgSO2e,total_impact));
            return new ResponseEntity<>(_resource, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/resources/{id}")
    public ResponseEntity<Resource> updateResources(@PathVariable("id") long id, @RequestBody Resource resource) {
        Optional<Resource> resourceData = Repository.findById(id);
        double total_ImpactGWP100_kgCO2=resource.getQuantity()*resource.getImpactGWP100_kgCO2e();
        double total_getImpactAP_kgSO2e=resource.getImpactAP_kgSO2e()*resource.getQuantity();
        double total_impact=total_ImpactGWP100_kgCO2+total_getImpactAP_kgSO2e;
        if (resourceData.isPresent()) {
            Resource _resource = resourceData.get();
            _resource.setResourceId(resource.getResourceId());
            _resource.setName(resource.getName());
            _resource.setImpactAP_kgSO2e(resource.getImpactAP_kgSO2e());
            _resource.setImpactGWP100_kgCO2e(resource.getImpactGWP100_kgCO2e());
            _resource.setQuantity(resource.getQuantity());
            _resource.setTotal_impactAP_kgSO2e(total_getImpactAP_kgSO2e);
            _resource.setTotal_impactGWP100_kgCO2e(total_ImpactGWP100_kgCO2);
            _resource.setTotal_impact(total_impact);
            return new ResponseEntity<>(Repository.save(_resource), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/resources")
    public ResponseEntity<HttpStatus> deleteAllResources() {
        try {
            Repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
