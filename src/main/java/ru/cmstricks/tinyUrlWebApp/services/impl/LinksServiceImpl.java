package ru.cmstricks.tinyUrlWebApp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.cmstricks.tinyUrlWebApp.repositories.FreeLinkCrudRepository;
import ru.cmstricks.tinyUrlWebApp.repositories.PaidLinkRepository;
import ru.cmstricks.tinyUrlWebApp.repositories.entities.FreeLink;
import ru.cmstricks.tinyUrlWebApp.repositories.entities.PaidLink;
import ru.cmstricks.tinyUrlWebApp.services.LinksService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LinksServiceImpl implements LinksService {

    @Autowired
    PaidLinkRepository paidLinkRepository;
    @Autowired
    FreeLinkCrudRepository freeLinkCrudRepository;

    @Value("${link.price.default}")
    private BigDecimal defaultPrice;
    private int batchSize = 100;
    private Integer cntr = 0;
    private ArrayList<FreeLink> freeLinks = new ArrayList<>();
    private ArrayList<PaidLink> paidLinks = new ArrayList<>();


    @Override
    public Optional<FreeLink> getFreeLinkById(String id) {
        return freeLinkCrudRepository.findById(id);
    }

    @Override
    public Optional<PaidLink> getPaidLinkById(String id) {
        return paidLinkRepository.findById(id);
    }

    private List<Character> getDictionary() {
        ArrayList<Character> dictionary = new ArrayList<>(52);
        for (char c = 'A'; c <= 'Z'; c++) {
            dictionary.add(c);
        }
        for (char c = '0'; c <= '9'; c++) {
            dictionary.add(c);
        }

        return dictionary;
    }

    @Override
    public boolean isLinkPaid(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("isLinkFree: given empty string");
        }
        if (s.length() < 2) {
            return true;
        }

        return s.matches("(.)\\1*") || s.matches("^[0-9]*$");
    }

    @Override
    public void createFreeLinks(Iterable<FreeLink> links) {
        freeLinkCrudRepository.saveAll(links);
    }

    @Override
    public void createPaidLinks(Iterable<PaidLink> links) {
        paidLinkRepository.saveAll(links);
    }

    @Override
    public void deleteFreeLink(FreeLink link) {
        freeLinkCrudRepository.delete(link);
    }

    @Override
    public Integer createPreLinks() {
        List<Character> dictionary = getDictionary();
        Integer counter = 0;
        for (char c : dictionary) {
            String str = String.valueOf(c);
            addLinkToTempArray(str);
            counter++;
            for (char d : dictionary) {
                String strD = str + d;
                addLinkToTempArray(strD);
                counter++;
                for (char e : dictionary) {
                    String strE = strD + e;
                    addLinkToTempArray(strE);
                    counter++;
                }
                if (freeLinks.size() > batchSize) {
                    saveFreeLinks();
                }
            }
        }
        saveFreeLinks();
        savePaidLinks();

        return counter;
    }

    private void saveFreeLinks() {
        createFreeLinks(freeLinks);
        freeLinks = new ArrayList<>();
    }

    private void savePaidLinks() {
        createPaidLinks(paidLinks);
        paidLinks = new ArrayList<>();
    }

    private void addLinkToTempArray(String str) {
        if (isLinkPaid(str)) {
            paidLinks.add(new PaidLink(str, defaultPrice));
        } else {
            freeLinks.add(new FreeLink(str));
        }
        System.out.println(cntr++ + " added link: " + str);
    }
}
