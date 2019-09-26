package com.motorlog.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.motorlog.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ExportService {

    //Supporting services ---------------------------------

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private GarageService garageService;

    @Autowired
    private RepairService repairService;

    @Autowired
    private RevisionService revisionService;

    public String getJson(int varId, boolean pretty) {
        Map<String, Object> map = new HashMap<>();
        map.put("garage", retrieveGarage(varId));
        map.put("repairs", retrieveRepairs(varId));
        map.put("revisions", retrieveRevisions(varId));
        if (pretty) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(map);
        } else {
            Gson gson = new Gson();
            return gson.toJson(map);
        }
    }

    private Map<String, String> retrieveGarage(int varId) {
        Map<String, String> map = new HashMap<>();
        Garage garage = garageService.findOne(varId);
        map.put("name", garage.getName());
        map.put("email", garage.getEmail());
        map.put("identifier", garage.getIdentifier());
        map.put("phone", garage.getPhone());
        map.put("country", garage.getCountry());
        map.put("city", garage.getCity());
        map.put("postalCode", garage.getPostalCode());
        map.put("address", garage.getAddress());
        return map;
    }

    private Map<String, Map<String, Object>> retrieveRepairs(int varId) {
        Map<String, Map<String, Object>> maps = new HashMap<>();
        Collection<Repair> repairs = repairService.getRepairsByGarage(varId);
        for (Repair repair : repairs) {
            Map<String, Object> map = new HashMap<>();
            map.put("personalInfo", repair.getPersonalInfo());
            map.put("entryDate", repair.getEntryDate().toString());
            if (repair.getDepartureDate() != null)
                map.put("departureDate", repair.getDepartureDate().toString());
            map.put("cause", repair.getCause());
            map.put("actions", repair.getActions());
            map.put("labourPrice", repair.getLabourPrice());
            map.put("itemsPrice", repair.getItemsPrice());
            map.put("finalPrice", repair.getFinalPrice());
            maps.put(String.valueOf(repair.getId()), map);
        } return maps;
    }

    private Map<String, Map<String, Object>> retrieveRevisions(int varId) {
        Map<String, Map<String, Object>> maps = new HashMap<>();
        Configuration configuration = this.configurationService.findAll().iterator().next();
        Collection<Revision> revisions = revisionService.getRevisionsByGarage(varId);
        for (Revision revision : revisions) {
            Map<String, Object> map = new HashMap<>();
            map.put("personalInfo", revision.getPersonalInfo());
            map.put("entryDate", revision.getEntryDate().toString());
            if (revision.getDepartureDate() != null)
                map.put("departureDate", revision.getDepartureDate().toString());
            map.put("comments", revision.getComments());
            map.put("labourPrice", revision.getLabourPrice());
            map.put("itemsPrice", revision.getItemsPrice());
            map.put("finalPrice", revision.getFinalPrice());
            map.put("nextRevision", revision.getNextRevision().toString());

            List<String> items;
            Map<String, Boolean> replacements = new HashMap<>();
            List<Boolean> substituted = new ArrayList<>(revision.getIsSubstituted());
            if(revision.getVehicle().getType().equals(VehicleType.motorbike))
                items = new ArrayList<>(configuration.getItemsForMoto());
            else items = new ArrayList<>(configuration.getItemsForCar());
            for (int i = 0; i < substituted.size(); i++)
                replacements.put(items.get(i), substituted.get(i));
            map.put("replacements", replacements);

            maps.put(String.valueOf(revision.getId()), map);
        } return maps;
    }
}
