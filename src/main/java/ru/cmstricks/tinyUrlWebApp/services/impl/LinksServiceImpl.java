package ru.cmstricks.tinyUrlWebApp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.cmstricks.tinyUrlWebApp.repositories.FreeLinkRepository;
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
    FreeLinkRepository freeLinkRepository;

    @Value("${link.price.default}")
    private BigDecimal defaultPrice;
    private Integer cntr = 0;


    @Override
    public Optional<FreeLink> getFreeLinkById(String id) {
        return freeLinkRepository.findById(id);
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
    public void createFreeLink(FreeLink link) {
        freeLinkRepository.save(link);
    }

    @Override
    public void createPaidLink(PaidLink link) {
        paidLinkRepository.save(link);
    }

    @Override
    public Integer createPreLinks() {
        List<Character> dictionary = getDictionary();
        Integer counter = 0;
        for (char c : dictionary) {
            String str = String.valueOf(c);
            createLink(str);
            counter++;
            for (char d : dictionary) {
                String strD = str + d;
                createLink(strD);
                counter++;
                for (char e : dictionary) {
                    String strE = str + e;
                    createLink(strE);
                    counter++;
                }
            }
        }

        return counter;
    }

    private void createLink(String str) {
        if (isLinkPaid(str)) {
            createPaidLink(new PaidLink(str, defaultPrice));
        } else {
            createFreeLink(new FreeLink(str));
        }
        System.out.println(cntr++ + " created link: " + str);
    }
}
